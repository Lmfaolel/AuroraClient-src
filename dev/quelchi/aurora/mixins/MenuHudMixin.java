package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.gui.MenuButton;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={InGameHud.class})
public class MenuHudMixin {
    @Inject(method={"method_1753"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$noHudUnderMenu(DrawContext drawContext, RenderTickCounter renderTickCounter, CallbackInfo callbackInfo) {
        if (MenuButton.menu(MinecraftClient.method_1551().field_1755)) {
            callbackInfo.cancel();
        }
    }
}
