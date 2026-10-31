package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.module.modules.NostalgiaModule;
import dev.quelchi.aurora.render.OldCrystals;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.EndCrystalEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value={EndCrystalEntityRenderer.class})
public abstract class EndCrystalRenderLayerMixin {
    @ModifyArg(method={"method_3908(Lnet/minecraft/class_10014;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;Lnet/minecraft/class_12075;)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_11659;method_73489(Lnet/minecraft/class_3879;Ljava/lang/Object;Lnet/minecraft/class_4587;Lnet/minecraft/class_1921;IIILnet/minecraft/class_11683$class_11792;)V")}, index=3)
    private RenderLayer aurora$oldCrystalLayer(RenderLayer renderLayer) {
        if (!NostalgiaModule.oldCrystals()) {
            return renderLayer;
        }
        RenderLayer renderLayer2 = OldCrystals.layer();
        return renderLayer2 != null ? renderLayer2 : renderLayer;
    }
}
