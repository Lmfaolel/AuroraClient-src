package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.quelchi.aurora.gui.AuroraBackdrop;
import dev.quelchi.aurora.gui.UpdateReadyScreen;
import dev.quelchi.aurora.util.Updater;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.LogoDrawer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.SplashTextRenderer;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={TitleScreen.class})
public class TitleScreenMixin {
    @Shadow
    private boolean field_18222;

    @Inject(method={"method_25394(Lnet/minecraft/class_332;IIF)V"}, at={@At(value="HEAD")})
    private void aurora$noFade(DrawContext drawContext, int n, int n2, float f, CallbackInfo callbackInfo) {
        this.field_18222 = false;
    }

    @Inject(method={"method_25394(Lnet/minecraft/class_332;IIF)V"}, at={@At(value="TAIL")})
    private void aurora$updateNotice(DrawContext drawContext, int n, int n2, float f, CallbackInfo callbackInfo) {
        if (Updater.ready.isEmpty() || Updater.promptShown) {
            return;
        }
        Updater.promptShown = true;
        MinecraftClient.method_1551().method_1507((Screen)new UpdateReadyScreen((Screen)((TitleScreen)this)));
    }

    @Inject(method={"method_25420"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$background(DrawContext drawContext, int n, int n2, float f, CallbackInfo callbackInfo) {
        TitleScreen titleScreen = (TitleScreen)this;
        AuroraBackdrop.render(drawContext, titleScreen.field_22789, titleScreen.field_22790);
        callbackInfo.cancel();
    }

    @WrapOperation(method={"method_25394"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_442;method_57728(Lnet/minecraft/class_332;F)V")}, require=0)
    private void aurora$noPanorama(TitleScreen titleScreen, DrawContext drawContext, float f, Operation<Void> operation) {
    }

    @WrapOperation(method={"method_25394"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_8020;method_48209(Lnet/minecraft/class_332;IF)V")}, require=0)
    private void aurora$logo(LogoDrawer logoDrawer, DrawContext drawContext, int n, float f, Operation<Void> operation) {
    }

    @WrapOperation(method={"method_25394"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_8020;method_48210(Lnet/minecraft/class_332;IFI)V")}, require=0)
    private void aurora$logoAlpha(LogoDrawer logoDrawer, DrawContext drawContext, int n, float f, int n2, Operation<Void> operation) {
    }

    @WrapOperation(method={"method_25394"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_332;method_25303(Lnet/minecraft/class_327;Ljava/lang/String;III)V")}, require=0)
    private void aurora$hideVanillaText(DrawContext drawContext, TextRenderer textRenderer, String string, int n, int n2, int n3, Operation<Void> operation) {
    }

    @WrapOperation(method={"method_25394"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_8519;method_51453(Lnet/minecraft/class_332;ILnet/minecraft/class_327;F)V")}, require=0)
    private void aurora$splash(SplashTextRenderer splashTextRenderer, DrawContext drawContext, int n, TextRenderer textRenderer, float f, Operation<Void> operation) {
    }
}
