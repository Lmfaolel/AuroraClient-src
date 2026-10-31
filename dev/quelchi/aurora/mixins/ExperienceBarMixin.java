package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.module.modules.GuiTweaksModule;
import dev.quelchi.aurora.module.modules.GuiTweaksModule$Key;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.bar.Bar;
import net.minecraft.client.gui.hud.bar.ExperienceBar;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ExperienceBar.class})
public class ExperienceBarMixin {
    @Unique
    private boolean aurora$xpPushed;

    @Inject(method={"method_70865"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$xpHead(DrawContext drawContext, RenderTickCounter renderTickCounter, CallbackInfo callbackInfo) {
        Bar bar = (Bar)this;
        Window window = MinecraftClient.method_1551().method_22683();
        this.aurora$xpPushed = GuiTweaksModule.beginPart(drawContext, GuiTweaksModule$Key.EXPERIENCE, bar.method_70864(window), bar.method_70867(window), 182, 5, true);
        if (GuiTweaksModule.partHiddenInPlay(GuiTweaksModule$Key.EXPERIENCE)) {
            if (this.aurora$xpPushed) {
                drawContext.method_51448().popMatrix();
            }
            this.aurora$xpPushed = false;
            callbackInfo.cancel();
        }
    }

    @Inject(method={"method_70865"}, at={@At(value="RETURN")})
    private void aurora$xpReturn(DrawContext drawContext, RenderTickCounter renderTickCounter, CallbackInfo callbackInfo) {
        if (this.aurora$xpPushed) {
            drawContext.method_51448().popMatrix();
            this.aurora$xpPushed = false;
        }
    }
}
