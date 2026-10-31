package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.module.modules.GuiTweaksModule;
import dev.quelchi.aurora.module.modules.GuiTweaksModule$Key;
import dev.quelchi.aurora.module.modules.PotionHudModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={InGameHud.class})
public class InGameHudEffectsMixin {
    @Unique
    private boolean aurora$effectsPushed;

    @Inject(method={"method_1765"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$effectsHead(DrawContext drawContext, RenderTickCounter renderTickCounter, CallbackInfo callbackInfo) {
        this.aurora$effectsPushed = false;
        PotionHudModule potionHudModule = PotionHudModule.get();
        if (potionHudModule != null && potionHudModule.isEnabled() && potionHudModule.hidesVanilla()) {
            callbackInfo.cancel();
            return;
        }
        if (GuiTweaksModule.partHiddenInPlay(GuiTweaksModule$Key.EFFECTS)) {
            callbackInfo.cancel();
            return;
        }
        int n = 0;
        int n2 = 0;
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        if (minecraftClient.field_1724 != null) {
            for (StatusEffectInstance statusEffectInstance : minecraftClient.field_1724.method_6026()) {
                if (!statusEffectInstance.method_5592()) continue;
                if (((StatusEffect)statusEffectInstance.method_5579().comp_349()).method_5573()) {
                    ++n;
                    continue;
                }
                ++n2;
            }
        }
        if (n + n2 == 0) {
            return;
        }
        int n3 = Math.max(n, n2);
        int n4 = n > 0 && n2 > 0 ? 2 : 1;
        this.aurora$effectsPushed = GuiTweaksModule.beginPart(drawContext, GuiTweaksModule$Key.EFFECTS, drawContext.method_51421() - 25 * n3, 1, 25 * n3, 26 * n4, false);
    }

    @Inject(method={"method_1765"}, at={@At(value="RETURN")})
    private void aurora$effectsReturn(DrawContext drawContext, RenderTickCounter renderTickCounter, CallbackInfo callbackInfo) {
        if (this.aurora$effectsPushed) {
            drawContext.method_51448().popMatrix();
            this.aurora$effectsPushed = false;
        }
    }
}
