package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.quelchi.aurora.gui.MenuButton;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.EntryListWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value={EntryListWidget.class})
public class ListSelectionMixin {
    private static final String FILL = "Lnet/minecraft/client/gui/DrawContext;fill(IIIII)V";

    @WrapOperation(method={"method_44398"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_332;method_25294(IIIII)V", ordinal=0)})
    private void aurora$selected(DrawContext drawContext, int n, int n2, int n3, int n4, int n5, Operation<Void> operation) {
        MenuButton.selected(drawContext, n, n2, n3 - n, n4 - n2);
    }

    @WrapWithCondition(method={"method_44398"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_332;method_25294(IIIII)V", ordinal=1)})
    private boolean aurora$noBlackFill(DrawContext drawContext, int n, int n2, int n3, int n4, int n5) {
        return false;
    }
}
