package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.quelchi.aurora.module.modules.TransparentPlayersModule;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={LivingEntityRenderer.class})
public class TransparentPlayersMixin {
    @Inject(method={"method_4054(Lnet/minecraft/class_10042;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;Lnet/minecraft/class_12075;)V"}, at={@At(value="HEAD")})
    private void aurora$beginFade(LivingEntityRenderState livingEntityRenderState, MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, CameraRenderState cameraRenderState, CallbackInfo callbackInfo) {
        TransparentPlayersModule.setFading(TransparentPlayersModule.applies(livingEntityRenderState));
    }

    @Inject(method={"method_4054(Lnet/minecraft/class_10042;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;Lnet/minecraft/class_12075;)V"}, at={@At(value="RETURN")})
    private void aurora$endFade(LivingEntityRenderState livingEntityRenderState, MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, CameraRenderState cameraRenderState, CallbackInfo callbackInfo) {
        TransparentPlayersModule.setFading(false);
    }

    @ModifyVariable(method={"method_24302"}, at={@At(value="HEAD")}, ordinal=1, argsOnly=true)
    private boolean aurora$forceTranslucent(boolean bl, LivingEntityRenderState livingEntityRenderState) {
        return bl || TransparentPlayersModule.applies(livingEntityRenderState);
    }

    @WrapOperation(method={"method_24302"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_12249;method_75998(Lnet/minecraft/class_2960;)Lnet/minecraft/class_1921;")}, require=0)
    private RenderLayer aurora$overlayCapableLayer(Identifier identifier, Operation<RenderLayer> operation) {
        return TransparentPlayersModule.isFading() ? RenderLayers.method_76000((Identifier)identifier) : (RenderLayer)operation.call(new Object[]{identifier});
    }

    @WrapOperation(method={"method_4054(Lnet/minecraft/class_10042;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;Lnet/minecraft/class_12075;)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_9848;method_61322(II)I")}, require=0)
    private int aurora$fadeTint(int n, int n2, Operation<Integer> operation) {
        int n3 = (Integer)operation.call(new Object[]{n, n2});
        return TransparentPlayersModule.isFading() ? TransparentPlayersModule.withAlpha(n3) : n3;
    }
}
