package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.ambience.AmbienceColors;
import dev.quelchi.aurora.ambience.AmbienceDimensions;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.AmbienceModule$CloudStyle;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.CloudRenderMode;
import net.minecraft.client.render.FrameGraphBuilder;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value={WorldRenderer.class})
public abstract class AmbienceWorldRendererMixin {
    @Unique
    private static double aurora$cloudClock = Double.NaN;
    @Unique
    private static double aurora$lastRaw;

    @ModifyVariable(method={"method_62204"}, at={@At(value="HEAD")}, ordinal=0, argsOnly=true)
    private int aurora$overrideCloudColor(int n) {
        AmbienceModule ambienceModule = AmbienceModule.get();
        if (ambienceModule != null && ambienceModule.isEnabled() && ambienceModule.clouds()) {
            int n2 = Math.max(10, Math.round(255.0f * ambienceModule.cloudOpacity()));
            return n2 << 24 | ambienceModule.cloudRgb() & 0xFFFFFF;
        }
        return n;
    }

    @Inject(method={"method_62204(Lnet/minecraft/class_9909;Lnet/minecraft/class_4063;Lnet/minecraft/class_243;JFIF)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$ownClouds(FrameGraphBuilder frameGraphBuilder, CloudRenderMode cloudRenderMode, Vec3d vec3d, long l, float f, int n, float f2, CallbackInfo callbackInfo) {
        AmbienceModule ambienceModule = AmbienceModule.get();
        if (ambienceModule == null || !ambienceModule.isEnabled()) {
            return;
        }
        AmbienceModule$CloudStyle ambienceModule$CloudStyle = ambienceModule.cloudStyle();
        if (ambienceModule$CloudStyle == AmbienceModule$CloudStyle.Soft || ambienceModule$CloudStyle == AmbienceModule$CloudStyle.Wispy) {
            callbackInfo.cancel();
        }
    }

    @ModifyArgs(method={"method_62205"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_9955;method_62168(ILnet/minecraft/class_4063;FLnet/minecraft/class_243;JF)V")})
    private void aurora$cloudShape(Args args) {
        AmbienceModule ambienceModule = AmbienceModule.get();
        if (ambienceModule == null || !ambienceModule.isEnabled() || !ambienceModule.clouds()) {
            aurora$cloudClock = Double.NaN;
            return;
        }
        if (ambienceModule.cloudStyle() == AmbienceModule$CloudStyle.Flat) {
            args.set(1, (Object)CloudRenderMode.field_18163);
        }
        args.set(2, (Object)Float.valueOf(ambienceModule.cloudHeight()));
        long l = (Long)args.get(4);
        float f = ((Float)args.get(5)).floatValue();
        double d = (float)l + f;
        double d2 = d - aurora$lastRaw;
        aurora$lastRaw = d;
        aurora$cloudClock = Double.isNaN(aurora$cloudClock) || d2 < 0.0 || d2 > 200.0 ? d : (aurora$cloudClock += d2 * (double)ambienceModule.cloudSpeed());
        long l2 = (long)Math.floor(aurora$cloudClock);
        args.set(4, (Object)l2);
        args.set(5, (Object)Float.valueOf((float)(aurora$cloudClock - (double)l2)));
    }

    @ModifyArg(method={"method_62215"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_9975;method_62306(Lnet/minecraft/class_4587;FI)V")}, index=2)
    private static int aurora$overrideGlowingSkyColor(int n) {
        AmbienceModule ambienceModule = AmbienceModule.get();
        if (ambienceModule == null || !ambienceModule.isEnabled() || !ambienceModule.skyGradient()) {
            return n;
        }
        if (!AmbienceDimensions.isOverworldLike(MinecraftClient.method_1551().field_1687)) {
            return n;
        }
        int n2 = n >>> 24 & 0xFF;
        if (n2 == 0) {
            n2 = 255;
        }
        int n3 = AmbienceColors.scaleRgb(ambienceModule.gradientRgb(), (float)ambienceModule.gradientIntensity() / 16.0f);
        return n2 << 24 | n3 & 0xFFFFFF;
    }
}
