package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.mixins.ClientWorldInvoker;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={MinecraftClient.class})
public abstract class DisplayTickSpreadMixin {
    @Shadow
    public ClientWorld field_1687;
    @Unique
    private static final int ROUNDS = 667;
    @Unique
    private static final Random aurora$random = Random.method_43047();
    @Unique
    private static final BlockPos.Mutable aurora$pos = new BlockPos.Mutable();
    @Unique
    private static ClientWorld aurora$world;
    @Unique
    private static Block aurora$marker;
    @Unique
    private static int aurora$x;
    @Unique
    private static int aurora$y;
    @Unique
    private static int aurora$z;
    @Unique
    private static int aurora$left;
    @Unique
    private static int aurora$frames;
    @Unique
    private static int aurora$perFrame;

    @Redirect(method={"method_1574()V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_638;method_2941(III)V")})
    private void aurora$queueDisplayTicks(ClientWorld clientWorld, int n, int n2, int n3) {
        this.aurora$run(aurora$left);
        aurora$perFrame = (667 + Math.max(1, aurora$frames) - 1) / Math.max(1, aurora$frames);
        aurora$frames = 0;
        aurora$world = clientWorld;
        aurora$marker = ((ClientWorldInvoker)clientWorld).aurora$blockParticle();
        aurora$x = n;
        aurora$y = n2;
        aurora$z = n3;
        aurora$left = 667;
    }

    @Inject(method={"method_1523(Z)V"}, at={@At(value="HEAD")})
    private void aurora$displayTickSlice(boolean bl, CallbackInfo callbackInfo) {
        ++aurora$frames;
        this.aurora$run(Math.min(aurora$left, aurora$perFrame));
    }

    @Unique
    private void aurora$run(int n) {
        if (n <= 0) {
            return;
        }
        aurora$left -= n;
        if (aurora$world != this.field_1687) {
            aurora$left = 0;
            aurora$world = null;
            return;
        }
        for (int i = 0; i < n; ++i) {
            aurora$world.method_2943(aurora$x, aurora$y, aurora$z, 16, aurora$random, aurora$marker, aurora$pos);
            aurora$world.method_2943(aurora$x, aurora$y, aurora$z, 32, aurora$random, aurora$marker, aurora$pos);
        }
        if (aurora$left == 0) {
            aurora$world = null;
        }
    }

    static {
        aurora$perFrame = 667;
    }
}
