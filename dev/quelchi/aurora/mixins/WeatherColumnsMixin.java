package dev.quelchi.aurora.mixins;

import java.lang.ref.WeakReference;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.WeatherRendering;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.state.WeatherRenderState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={WeatherRendering.class})
public abstract class WeatherColumnsMixin {
    @Unique
    private static final Random aurora$random = Random.method_43047();
    @Unique
    private WeakReference<World> aurora$world = new WeakReference<Object>(null);
    @Unique
    private int aurora$ticks = Integer.MIN_VALUE;
    @Unique
    private int aurora$x;
    @Unique
    private int aurora$y;
    @Unique
    private int aurora$z;
    @Unique
    private int aurora$radius;
    @Unique
    private int aurora$count;
    @Unique
    private int[] aurora$cols = new int[0];

    @Shadow
    private Biome.Precipitation method_62317(World world, BlockPos blockPos) {
        throw new AssertionError();
    }

    @Shadow
    private WeatherRendering.Piece method_62314(Random random, int n, int n2, int n3, int n4, int n5, int n6, float f) {
        throw new AssertionError();
    }

    @Shadow
    private WeatherRendering.Piece method_62321(Random random, int n, int n2, int n3, int n4, int n5, int n6, float f) {
        throw new AssertionError();
    }

    @Inject(method={"method_62315(Lnet/minecraft/class_1937;IFLnet/minecraft/class_243;Lnet/minecraft/class_12077;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$fromColumns(World world, int n, float f, Vec3d vec3d, WeatherRenderState weatherRenderState, CallbackInfo callbackInfo) {
        callbackInfo.cancel();
        weatherRenderState.field_63103 = world.method_8430(f);
        if (weatherRenderState.field_63103 <= 0.0f) {
            return;
        }
        weatherRenderState.field_63104 = (Integer)MinecraftClient.method_1551().field_1690.method_75333().method_41753();
        int n2 = MathHelper.method_15357((double)vec3d.field_1352);
        int n3 = MathHelper.method_15357((double)vec3d.field_1351);
        int n4 = MathHelper.method_15357((double)vec3d.field_1350);
        int n5 = weatherRenderState.field_63104;
        if (world != this.aurora$world.get() || n != this.aurora$ticks || n2 != this.aurora$x || n3 != this.aurora$y || n4 != this.aurora$z || n5 != this.aurora$radius) {
            this.aurora$world = new WeakReference<World>(world);
            this.aurora$ticks = n;
            this.aurora$x = n2;
            this.aurora$y = n3;
            this.aurora$z = n4;
            this.aurora$radius = n5;
            this.aurora$scan(world, n2, n3, n4, n5);
        }
        int[] nArray = this.aurora$cols;
        for (int i = 0; i < this.aurora$count; ++i) {
            int n6 = i * 6;
            int n7 = nArray[n6];
            int n8 = nArray[n6 + 1];
            aurora$random.method_43052((long)(n7 * n7 * 3121 + n7 * 45238971 ^ n8 * n8 * 418711 + n8 * 13761));
            if (nArray[n6 + 5] == 1) {
                weatherRenderState.field_63101.add(this.method_62314(aurora$random, n, n7, nArray[n6 + 2], nArray[n6 + 3], n8, nArray[n6 + 4], f));
                continue;
            }
            weatherRenderState.field_63102.add(this.method_62321(aurora$random, n, n7, nArray[n6 + 2], nArray[n6 + 3], n8, nArray[n6 + 4], f));
        }
    }

    @Unique
    private void aurora$scan(World world, int n, int n2, int n3, int n4) {
        int n5 = 2 * n4 + 1;
        if (this.aurora$cols.length < n5 * n5 * 6) {
            this.aurora$cols = new int[n5 * n5 * 6];
        }
        int[] nArray = this.aurora$cols;
        int n6 = 0;
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        for (int i = n3 - n4; i <= n3 + n4; ++i) {
            for (int j = n - n4; j <= n + n4; ++j) {
                Biome.Precipitation precipitation;
                int n7 = world.method_8624(Heightmap.Type.field_13197, j, i);
                int n8 = Math.max(n2 - n4, n7);
                int n9 = Math.max(n2 + n4, n7);
                if (n9 - n8 == 0 || (precipitation = this.method_62317(world, (BlockPos)mutable.method_10103(j, n2, i))) == Biome.Precipitation.field_9384) continue;
                int n10 = n6++ * 6;
                nArray[n10] = j;
                nArray[n10 + 1] = i;
                nArray[n10 + 2] = n8;
                nArray[n10 + 3] = n9;
                nArray[n10 + 4] = WorldRenderer.method_23794((BlockRenderView)world, (BlockPos)mutable.method_10103(j, Math.max(n2, n7), i));
                nArray[n10 + 5] = precipitation == Biome.Precipitation.field_9382 ? 1 : 2;
            }
        }
        this.aurora$count = n6;
    }
}
