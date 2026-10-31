package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.gui.MenuButton;
import java.util.function.Supplier;
import net.minecraft.client.font.DrawnTextConsumer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.PressableWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={PressableWidget.class})
public abstract class PressableWidgetMixin {
    @Shadow
    private Supplier<Boolean> field_64534;

    @Inject(method={"method_75794"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$drawButton(DrawContext drawContext, CallbackInfo callbackInfo) {
        ClickableWidget clickableWidget = (ClickableWidget)this;
        MenuButton.slab(drawContext, clickableWidget, clickableWidget.method_46426(), clickableWidget.method_46427(), clickableWidget.method_25368(), clickableWidget.method_25364(), this.aurora$lit());
        callbackInfo.cancel();
    }

    @Unique
    private boolean aurora$lit() {
        return this.field_64534 != null ? this.field_64534.get().booleanValue() : ((ClickableWidget)this).method_25367();
    }

    @Inject(method={"method_75793"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$styledLabel(DrawnTextConsumer drawnTextConsumer, CallbackInfo callbackInfo) {
        ClickableWidget clickableWidget = (ClickableWidget)this;
        callbackInfo.cancel();
        drawnTextConsumer.method_75770(MenuButton.label(clickableWidget, clickableWidget.field_22763 && this.aurora$lit()), clickableWidget.method_46426() + 2, clickableWidget.method_46426() + clickableWidget.method_25368() - 2, clickableWidget.method_46427(), clickableWidget.method_46427() + clickableWidget.method_25364());
    }
}
