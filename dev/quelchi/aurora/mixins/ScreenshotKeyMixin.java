package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.module.modules.ScreenshotModule;
import java.io.File;
import java.util.function.Consumer;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.util.ScreenshotRecorder;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ScreenshotRecorder.class})
public class ScreenshotKeyMixin {
    @Inject(method={"method_1659(Ljava/io/File;Lnet/minecraft/class_276;Ljava/util/function/Consumer;)V"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private static void aurora$routeToModule(File file, Framebuffer framebuffer, Consumer<Text> consumer, CallbackInfo callbackInfo) {
        if (ScreenshotModule.capturing) {
            return;
        }
        ScreenshotModule screenshotModule = ScreenshotModule.get();
        if (screenshotModule == null || !screenshotModule.isEnabled()) {
            return;
        }
        callbackInfo.cancel();
        screenshotModule.capture();
    }
}
