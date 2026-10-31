package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import dev.quelchi.aurora.perf.Tweaks;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.CapeFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.feature.StuckObjectsFeatureRenderer;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value={LivingEntityRenderer.class})
public abstract class FeatureDetailMixin {
    @WrapWithCondition(method={"method_4054(Lnet/minecraft/class_10042;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;Lnet/minecraft/class_12075;)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_3887;method_4199(Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;ILnet/minecraft/class_10017;FF)V")})
    private boolean aurora$skipFarDetail(FeatureRenderer<?, ?> featureRenderer, MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, int n, EntityRenderState entityRenderState, float f, float f2) {
        if (!Tweaks.farDetail() || entityRenderState.field_53332 < 2304.0) {
            return true;
        }
        return !(featureRenderer instanceof HeldItemFeatureRenderer) && !(featureRenderer instanceof CapeFeatureRenderer) && !(featureRenderer instanceof StuckObjectsFeatureRenderer);
    }
}
