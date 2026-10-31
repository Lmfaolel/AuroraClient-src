package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.gui.MenuButton;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Screen.class})
public class MenuDimMixin {
    @Inject(method={"method_25420"}, at={@At(value="TAIL")})
    private void aurora$menuDim(DrawContext drawContext, int n, int n2, float f, CallbackInfo callbackInfo) {
        Screen screen = (Screen)this;
        if (MinecraftClient.method_1551().field_1687 == null || !MenuButton.menu(screen)) {
            return;
        }
        drawContext.method_25294(0, 0, screen.field_22789, screen.field_22790, -1643837942);
    }
}
