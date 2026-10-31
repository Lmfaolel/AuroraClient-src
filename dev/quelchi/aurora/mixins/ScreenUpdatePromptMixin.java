package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.gui.SocialToast;
import dev.quelchi.aurora.gui.UpdatePrompt;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Screen.class})
public abstract class ScreenUpdatePromptMixin {
    @Inject(method={"method_25394(Lnet/minecraft/class_332;IIF)V"}, at={@At(value="TAIL")})
    private void aurora$drawUpdatePrompt(DrawContext drawContext, int n, int n2, float f, CallbackInfo callbackInfo) {
        UpdatePrompt.renderOverScreen(drawContext, ((Screen)this).field_22789);
        SocialToast.renderOverScreen(drawContext);
    }
}
