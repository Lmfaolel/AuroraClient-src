package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.quelchi.aurora.module.modules.DamageTintModule;
import dev.quelchi.aurora.render.GradientOverlayVertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.OutlineVertexConsumerProvider;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.command.ModelCommandRenderer;
import net.minecraft.client.render.command.OrderedRenderCommandQueueImpl;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value={ModelCommandRenderer.class})
public abstract class ModelCommandRendererMixin {
    @WrapOperation(method={"method_73000(Lnet/minecraft/class_11661$class_11670;Lnet/minecraft/class_1921;Lnet/minecraft/class_4588;Lnet/minecraft/class_4618;Lnet/minecraft/class_4597$class_4598;)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_3879;method_62100(Lnet/minecraft/class_4587;Lnet/minecraft/class_4588;III)V")})
    private void aurora$gradientModelRender(Model<?> model, MatrixStack matrixStack, VertexConsumer vertexConsumer, int n, int n2, int n3, Operation<Void> operation, OrderedRenderCommandQueueImpl.ModelCommand<?> modelCommand, RenderLayer renderLayer, VertexConsumer vertexConsumer2, OutlineVertexConsumerProvider outlineVertexConsumerProvider, VertexConsumerProvider.Immediate immediate) {
        Object object;
        DamageTintModule damageTintModule = DamageTintModule.get();
        if (damageTintModule != null && damageTintModule.gradientActive() && (object = modelCommand.comp_4506()) instanceof PlayerEntityRenderState) {
            PlayerEntityRenderState playerEntityRenderState = (PlayerEntityRenderState)object;
            if (playerEntityRenderState.field_53460) {
                object = new Matrix4f((Matrix4fc)matrixStack.method_23760().method_23761()).invert();
                vertexConsumer = new GradientOverlayVertexConsumer(vertexConsumer, (Matrix4f)object);
            }
        }
        operation.call(new Object[]{model, matrixStack, vertexConsumer, n, n2, n3});
    }
}
