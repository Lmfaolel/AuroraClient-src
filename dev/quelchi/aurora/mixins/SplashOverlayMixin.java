package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.gui.AuroraSplash;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.SplashOverlay;
import net.minecraft.resource.ResourceReload;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={SplashOverlay.class})
public abstract class SplashOverlayMixin {
    @Shadow
    @Final
    private MinecraftClient field_18217;
    @Shadow
    @Final
    private ResourceReload field_17767;
    @Shadow
    @Final
    private boolean field_18219;
    @Shadow
    private float field_17770;
    @Shadow
    private long field_17771;
    @Shadow
    private long field_18220;

    @Inject(method={"method_25394(Lnet/minecraft/class_332;IIF)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$render(DrawContext drawContext, int n, int n2, float f, CallbackInfo callbackInfo) {
        if (AuroraSplash.broken) {
            return;
        }
        int n3 = drawContext.method_51421();
        int n4 = drawContext.method_51443();
        long l = Util.method_658();
        if (this.field_18219 && this.field_18220 == -1L) {
            this.field_18220 = l;
        }
        float f2 = this.field_17771 > -1L ? (float)(l - this.field_17771) / 1000.0f : -1.0f;
        float f3 = this.field_18220 > -1L ? (float)(l - this.field_18220) / 500.0f : -1.0f;
        try {
            float f4;
            float f5;
            if (!this.field_18219 && f2 >= 0.0f && this.field_18217.field_1755 != null) {
                if (!AuroraSplash.revealing && !AuroraSplash.assets(drawContext, n3, n4)) {
                    callbackInfo.cancel();
                    return;
                }
                this.field_18217.method_18502(null);
                AuroraSplash.done();
                callbackInfo.cancel();
                return;
            }
            if (this.field_18219 && f2 >= 1.0f) {
                if (this.field_18217.field_1755 != null) {
                    this.field_18217.field_1755.method_47413(drawContext, 0, 0, f);
                } else {
                    this.field_18217.field_1705.method_72738();
                }
                drawContext.method_71048();
                f4 = f5 = 1.0f - MathHelper.method_15363((float)(f2 - 1.0f), (float)0.0f, (float)1.0f);
            } else if (this.field_18219) {
                if (this.field_18217.field_1755 != null && f3 < 1.0f) {
                    this.field_18217.field_1755.method_47413(drawContext, n, n2, f);
                } else {
                    this.field_18217.field_1705.method_72738();
                }
                drawContext.method_71048();
                f4 = MathHelper.method_15363((float)f3, (float)0.15f, (float)1.0f);
                f5 = MathHelper.method_15363((float)f3, (float)0.0f, (float)1.0f);
            } else {
                f5 = 1.0f;
                f4 = 1.0f;
            }
            float f6 = f2 >= 0.0f ? 1.0f : this.field_17767.method_18229();
            this.field_17770 = MathHelper.method_15363((float)(this.field_17770 * 0.95f + f6 * 0.05f), (float)0.0f, (float)1.0f);
            float f7 = f2 < 0.0f ? 1.0f : 1.0f - MathHelper.method_15363((float)f2, (float)0.0f, (float)1.0f);
            AuroraSplash.draw(drawContext, n3, n4, f4, f5, this.field_17770, f7, !this.field_18219);
        }
        catch (Throwable throwable) {
            AuroraSplash.broken = true;
            Aurora.LOG.error("Aurora loading screen failed, using vanilla's", throwable);
            return;
        }
        if (this.field_18219 && f2 >= 2.0f) {
            this.field_18217.method_18502(null);
            AuroraSplash.done();
        }
        callbackInfo.cancel();
    }
}
