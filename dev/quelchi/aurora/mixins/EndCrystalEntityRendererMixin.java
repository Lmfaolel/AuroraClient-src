package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.quelchi.aurora.duck.GhostCrystal;
import dev.quelchi.aurora.module.modules.CustomCrystalsModule;
import dev.quelchi.aurora.module.modules.NostalgiaModule;
import dev.quelchi.aurora.module.modules.OptimizersModule;
import dev.quelchi.aurora.optimize.CrystalPrediction;
import dev.quelchi.aurora.optimize.SeamlessAges;
import dev.quelchi.aurora.render.CustomCrystalRenderer;
import dev.quelchi.aurora.render.GhostCrystals;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.command.ModelCommandRenderer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EndCrystalEntityRenderer;
import net.minecraft.client.render.entity.state.EndCrystalEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={EndCrystalEntityRenderer.class})
public abstract class EndCrystalEntityRendererMixin {
    @Inject(method={"method_62417(Lnet/minecraft/class_1511;Lnet/minecraft/class_10014;F)V"}, at={@At(value="TAIL")})
    private void aurora$seamlessCrystalAge(EndCrystalEntity endCrystalEntity, EndCrystalEntityRenderState endCrystalEntityRenderState, float f, CallbackInfo callbackInfo) {
        boolean bl = CrystalPrediction.on() && CrystalPrediction.isLocal((Entity)endCrystalEntity);
        ((GhostCrystal)endCrystalEntityRenderState).aurora$setGhost(bl);
        if (!CrystalPrediction.on()) {
            return;
        }
        if (bl) {
            SeamlessAges.recordRendered(endCrystalEntity.method_5628(), endCrystalEntityRenderState.field_53328);
        } else {
            endCrystalEntityRenderState.field_53328 = SeamlessAges.apply(endCrystalEntity.method_5628(), endCrystalEntityRenderState.field_53328);
        }
    }

    @WrapOperation(method={"method_3908(Lnet/minecraft/class_10014;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;Lnet/minecraft/class_12075;)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_11659;method_73489(Lnet/minecraft/class_3879;Ljava/lang/Object;Lnet/minecraft/class_4587;Lnet/minecraft/class_1921;IIILnet/minecraft/class_11683$class_11792;)V")})
    private void aurora$ghostCrystal(OrderedRenderCommandQueue orderedRenderCommandQueue, Model<?> model, Object object, MatrixStack matrixStack, RenderLayer renderLayer, int n, int n2, int n3, ModelCommandRenderer.CrumblingOverlayCommand crumblingOverlayCommand, Operation<Void> operation) {
        float f = EndCrystalEntityRendererMixin.aurora$ghostOpacity(object);
        if (CustomCrystalsModule.active() && object instanceof EndCrystalEntityRenderState) {
            EndCrystalEntityRenderState endCrystalEntityRenderState = (EndCrystalEntityRenderState)object;
            CustomCrystalRenderer.submit(orderedRenderCommandQueue, endCrystalEntityRenderState, matrixStack, renderLayer, n, n2, n3, crumblingOverlayCommand, f);
            return;
        }
        if (f >= 1.0f) {
            operation.call(new Object[]{orderedRenderCommandQueue, model, object, matrixStack, renderLayer, n, n2, n3, crumblingOverlayCommand});
            return;
        }
        RenderLayer renderLayer2 = GhostCrystals.layer(NostalgiaModule.oldCrystals());
        if (renderLayer2 == null) {
            renderLayer2 = renderLayer;
        }
        EndCrystalEntityRendererMixin.aurora$submitTinted(orderedRenderCommandQueue, model, object, matrixStack, renderLayer2, n, n2, GhostCrystals.tint(f), n3, crumblingOverlayCommand);
    }

    @Unique
    private static float aurora$ghostOpacity(Object object) {
        GhostCrystal ghostCrystal;
        if (!(object instanceof GhostCrystal) || !(ghostCrystal = (GhostCrystal)object).aurora$isGhost()) {
            return 1.0f;
        }
        OptimizersModule optimizersModule = OptimizersModule.get();
        return optimizersModule == null ? 1.0f : optimizersModule.crystalPreviewOpacity();
    }

    @Unique
    private static void aurora$submitTinted(OrderedRenderCommandQueue orderedRenderCommandQueue, Model model, Object object, MatrixStack matrixStack, RenderLayer renderLayer, int n, int n2, int n3, int n4, ModelCommandRenderer.CrumblingOverlayCommand crumblingOverlayCommand) {
        orderedRenderCommandQueue.method_73490(model, object, matrixStack, renderLayer, n, n2, n3, null, n4, crumblingOverlayCommand);
    }
}
