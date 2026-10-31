package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.module.modules.GuiTweaksModule;
import dev.quelchi.aurora.module.modules.GuiTweaksModule$Key;
import java.util.Map;
import java.util.UUID;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.BossBarHud;
import net.minecraft.client.gui.hud.ClientBossBar;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={BossBarHud.class})
public class BossBarHudMixin {
    @Shadow
    @Final
    Map<UUID, ClientBossBar> field_2060;
    @Unique
    private boolean aurora$bossPushed;

    @Inject(method={"method_1796"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$bossHead(DrawContext drawContext, CallbackInfo callbackInfo) {
        int n = this.field_2060.size();
        if (n == 0) {
            return;
        }
        this.aurora$bossPushed = GuiTweaksModule.beginPart(drawContext, GuiTweaksModule$Key.BOSSBAR, drawContext.method_51421() / 2 - 91, 12, 182, 19 * n, false);
        if (GuiTweaksModule.partHiddenInPlay(GuiTweaksModule$Key.BOSSBAR)) {
            if (this.aurora$bossPushed) {
                drawContext.method_51448().popMatrix();
            }
            this.aurora$bossPushed = false;
            callbackInfo.cancel();
        }
    }

    @Inject(method={"method_1796"}, at={@At(value="RETURN")})
    private void aurora$bossReturn(DrawContext drawContext, CallbackInfo callbackInfo) {
        if (this.aurora$bossPushed) {
            drawContext.method_51448().popMatrix();
            this.aurora$bossPushed = false;
        }
    }
}
