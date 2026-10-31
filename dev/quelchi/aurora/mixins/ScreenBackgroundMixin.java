package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.gui.AuroraBackdrop;
import dev.quelchi.aurora.gui.Glass;
import dev.quelchi.aurora.gui.GlassScreen;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.ImScreen;
import dev.quelchi.aurora.module.modules.GuiTweaksModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Screen.class})
public class ScreenBackgroundMixin {
    @Inject(method={"method_25420"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$menuBackdrop(DrawContext drawContext, int n, int n2, float f, CallbackInfo callbackInfo) {
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        Screen screen = (Screen)this;
        if (screen instanceof GlassScreen) {
            if (minecraftClient == null || minecraftClient.field_1687 == null) {
                AuroraBackdrop.render(drawContext, screen.field_22789, screen.field_22790);
            }
            callbackInfo.cancel();
            return;
        }
        if (minecraftClient == null) {
            return;
        }
        if (minecraftClient.field_1687 != null) {
            return;
        }
        if (screen instanceof TitleScreen) {
            return;
        }
        if (Im.available()) {
            ImScreen.panorama(drawContext, screen.field_22789, screen.field_22790);
        } else {
            AuroraBackdrop.render(drawContext, screen.field_22789, screen.field_22790);
        }
        Glass.mark(drawContext);
        callbackInfo.cancel();
    }

    @Inject(method={"method_57728(Lnet/minecraft/class_332;F)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$panorama(DrawContext drawContext, float f, CallbackInfo callbackInfo) {
        Screen screen = (Screen)this;
        if (Im.available()) {
            ImScreen.panorama(drawContext, screen.field_22789, screen.field_22790);
        } else {
            AuroraBackdrop.render(drawContext, screen.field_22789, screen.field_22790);
        }
        callbackInfo.cancel();
    }

    @Inject(method={"method_52752"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$containerDim(DrawContext drawContext, CallbackInfo callbackInfo) {
        float f = GuiTweaksModule.containerDim();
        Screen screen = (Screen)this;
        if (f >= 1.0f || !(screen instanceof HandledScreen)) {
            return;
        }
        callbackInfo.cancel();
        if (f <= 0.0f) {
            return;
        }
        drawContext.method_25296(0, 0, screen.field_22789, screen.field_22790, Math.round(192.0f * f) << 24 | 0x101010, Math.round(208.0f * f) << 24 | 0x101010);
    }
}
