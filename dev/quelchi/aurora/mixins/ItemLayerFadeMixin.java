package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.render.TotemFade;
import java.util.List;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.item.ItemRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value={ItemRenderState.LayerRenderState.class})
public abstract class ItemLayerFadeMixin {
    @ModifyArgs(method={"method_65614"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_11659;method_73480(Lnet/minecraft/class_4587;Lnet/minecraft/class_811;III[ILjava/util/List;Lnet/minecraft/class_1921;Lnet/minecraft/class_10444$class_10445;)V")}, require=0)
    private void aurora$totemFade(Args args) {
        if (!TotemFade.active()) {
            return;
        }
        List list = (List)args.get(6);
        if (list == null || list.isEmpty()) {
            return;
        }
        args.set(5, (Object)TotemFade.tints());
        args.set(6, TotemFade.tinted(list));
        args.set(7, (Object)TotemFade.layerFor(list, (RenderLayer)args.get(7)));
    }
}
