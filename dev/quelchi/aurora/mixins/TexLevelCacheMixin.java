package dev.quelchi.aurora.mixins;

import com.mojang.blaze3d.opengl.GlStateManager;
import it.unimi.dsi.fastutil.ints.Int2LongOpenHashMap;
import java.util.Arrays;
import net.fabricmc.loader.api.FabricLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={GlStateManager.class})
public abstract class TexLevelCacheMixin {
    @Unique
    private static final boolean VULKAN = FabricLoader.getInstance().isModLoaded("vulkanmod");
    @Unique
    private static final int TEXTURE_2D = 3553;
    @Unique
    private static final int BASE_LEVEL = 33084;
    @Unique
    private static final int MAX_LEVEL = 33085;
    @Unique
    private static final int[] aurora$bound = new int[32];
    @Unique
    private static final Int2LongOpenHashMap aurora$levels = new Int2LongOpenHashMap();
    @Unique
    private static int aurora$unit;

    @Inject(method={"_activeTexture(I)V"}, at={@At(value="HEAD")})
    private static void aurora$unit(int n, CallbackInfo callbackInfo) {
        aurora$unit = n - 33984;
    }

    @Inject(method={"_bindTexture(I)V"}, at={@At(value="HEAD")})
    private static void aurora$bind(int n, CallbackInfo callbackInfo) {
        if (aurora$unit >= 0 && aurora$unit < aurora$bound.length) {
            TexLevelCacheMixin.aurora$bound[TexLevelCacheMixin.aurora$unit] = n;
        }
    }

    @Inject(method={"_genTexture()I"}, at={@At(value="RETURN")})
    private static void aurora$gen(CallbackInfoReturnable<Integer> callbackInfoReturnable) {
        aurora$levels.remove(((Integer)callbackInfoReturnable.getReturnValue()).intValue());
    }

    @Inject(method={"_deleteTexture(I)V"}, at={@At(value="HEAD")})
    private static void aurora$delete(int n, CallbackInfo callbackInfo) {
        aurora$levels.remove(n);
        for (int i = 0; i < aurora$bound.length; ++i) {
            if (aurora$bound[i] != n) continue;
            TexLevelCacheMixin.aurora$bound[i] = -1;
        }
    }

    @Inject(method={"_texParameter(III)V"}, at={@At(value="HEAD")}, cancellable=true)
    private static void aurora$skipSameLevel(int n, int n2, int n3, CallbackInfo callbackInfo) {
        int n4;
        if (n != 3553 || n2 != 33084 && n2 != 33085) {
            return;
        }
        if (VULKAN) {
            return;
        }
        if (aurora$unit < 0 || aurora$unit >= aurora$bound.length) {
            return;
        }
        int n5 = aurora$bound[aurora$unit];
        if (n5 <= 0) {
            return;
        }
        long l = aurora$levels.get(n5);
        int n6 = n4 = n2 == 33084 ? 32 : 0;
        if ((int)(l >>> n4) == n3) {
            callbackInfo.cancel();
            return;
        }
        aurora$levels.put(n5, l & (0xFFFFFFFFL << n4 ^ 0xFFFFFFFFFFFFFFFFL) | ((long)n3 & 0xFFFFFFFFL) << n4);
    }

    static {
        Arrays.fill(aurora$bound, -1);
        aurora$levels.defaultReturnValue(-1L);
    }
}
