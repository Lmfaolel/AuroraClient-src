package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import dev.quelchi.aurora.mixins.ClientWorldInvoker;
import dev.quelchi.aurora.module.modules.NostalgiaModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.world.ClientWorld;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value={LightmapTextureManager.class})
public abstract class LightmapTextureManagerMixin {
    @Shadow
    @Final
    private MinecraftClient field_4137;

    @ModifyExpressionValue(method={"method_3313(F)V"}, at={@At(value="FIELD", opcode=178, target="Lnet/minecraft/class_10799;field_56844:Lcom/mojang/blaze3d/pipeline/RenderPipeline;")})
    private RenderPipeline aurora$oldLightmap(RenderPipeline renderPipeline) {
        return NostalgiaModule.lightmapPipeline(renderPipeline);
    }

    @ModifyExpressionValue(method={"method_3313(F)V"}, at={@At(value="INVOKE", target="Ljava/lang/Float;floatValue()F")})
    private float aurora$oldSkyFactor(float f) {
        ClientWorld clientWorld = this.field_4137.field_1687;
        if (clientWorld == null) {
            return f;
        }
        boolean bl = ((ClientWorldInvoker)clientWorld).aurora$lightningTicksLeft() > 0;
        return NostalgiaModule.oldSkyLightFactor(clientWorld, bl, f);
    }

    @ModifyExpressionValue(method={"method_3313(F)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_9848;method_64963(I)Lorg/joml/Vector3f;")})
    private Vector3f aurora$oldSkyColor(Vector3f vector3f) {
        ClientWorld clientWorld = this.field_4137.field_1687;
        return clientWorld == null ? vector3f : NostalgiaModule.oldSkyLightColor(clientWorld, vector3f);
    }
}
