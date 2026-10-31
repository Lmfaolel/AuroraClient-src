package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.ambience.AmbienceColors;
import dev.quelchi.aurora.ambience.SkyFogState;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.render.PaintedSky;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.fog.FogRenderer;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value={FogRenderer.class})
public abstract class AmbienceFogRendererMixin {
    @Inject(method={"method_62185"}, at={@At(value="RETURN")}, cancellable=true)
    private void aurora$overrideFogColor(CallbackInfoReturnable<Vector4f> callbackInfoReturnable) {
        float[] fArray = PaintedSky.fogColor();
        if (fArray != null) {
            callbackInfoReturnable.setReturnValue((Object)new Vector4f(fArray[0], fArray[1], fArray[2], ((Vector4f)callbackInfoReturnable.getReturnValue()).w));
            return;
        }
        AmbienceModule ambienceModule = AmbienceModule.get();
        if (ambienceModule == null || !ambienceModule.isEnabled()) {
            return;
        }
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        if (minecraftClient.field_1687 == null) {
            return;
        }
        Vector4f vector4f = (Vector4f)callbackInfoReturnable.getReturnValue();
        int n = AmbienceColors.resolveFogRgb(minecraftClient.field_1687);
        if (n != -1) {
            callbackInfoReturnable.setReturnValue((Object)new Vector4f((float)(n >> 16 & 0xFF) / 255.0f, (float)(n >> 8 & 0xFF) / 255.0f, (float)(n & 0xFF) / 255.0f, vector4f.w));
        }
    }

    @ModifyArgs(method={"method_3211(Lnet/minecraft/class_4184;ILnet/minecraft/class_9779;FLnet/minecraft/class_638;)Lorg/joml/Vector4f;"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_758;method_71110(Ljava/nio/ByteBuffer;ILorg/joml/Vector4f;FFFFFF)V")})
    private void aurora$moveSkyFog(Args args) {
        AmbienceModule ambienceModule = AmbienceModule.get();
        if (ambienceModule == null || !ambienceModule.isEnabled()) {
            SkyFogState.reset();
            return;
        }
        float f = ((Float)args.get(5)).floatValue();
        float f2 = ((Float)args.get(6)).floatValue();
        SkyFogState.record(f, f2);
        if (ambienceModule.skyGradient()) {
            float f3 = ambienceModule.gradientHeight();
            args.set(5, (Object)Float.valueOf(Math.max(0.0f, f - f3)));
            args.set(6, (Object)Float.valueOf(Math.max(0.0f, f2 - f3)));
        }
    }
}
