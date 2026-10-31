package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.quelchi.aurora.module.ModuleManager;
import dev.quelchi.aurora.module.modules.AntiAltModule;
import dev.quelchi.aurora.module.modules.GuiTweaksModule;
import dev.quelchi.aurora.module.modules.GuiTweaksModule$Key;
import dev.quelchi.aurora.module.modules.GuiTweaksModule$Part;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={InGameHud.class})
public class ScoreboardSidebarMixin {
    @Unique
    private boolean aurora$sidebarPushed;
    @Unique
    private int aurora$boxX;
    @Unique
    private int aurora$boxY;
    @Unique
    private int aurora$boxW;
    @Unique
    private int aurora$boxH;
    @Unique
    private int aurora$minX;
    @Unique
    private int aurora$minY;
    @Unique
    private int aurora$maxX;
    @Unique
    private int aurora$maxY;

    @Inject(method={"method_1757(Lnet/minecraft/class_332;Lnet/minecraft/class_266;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$sidebarHead(DrawContext drawContext, ScoreboardObjective scoreboardObjective, CallbackInfo callbackInfo) {
        this.aurora$sidebarPushed = false;
        this.aurora$minY = Integer.MAX_VALUE;
        this.aurora$minX = Integer.MAX_VALUE;
        this.aurora$maxY = Integer.MIN_VALUE;
        this.aurora$maxX = Integer.MIN_VALUE;
        GuiTweaksModule$Part guiTweaksModule$Part = GuiTweaksModule.partOrNull(GuiTweaksModule$Key.SCOREBOARD);
        if (guiTweaksModule$Part == null) {
            return;
        }
        int n = this.aurora$boxX;
        int n2 = this.aurora$boxY;
        int n3 = this.aurora$boxW;
        int n4 = this.aurora$boxH;
        if (n3 <= 0) {
            n3 = GuiTweaksModule$Key.SCOREBOARD.defaultWidth;
            n4 = GuiTweaksModule$Key.SCOREBOARD.defaultHeight;
            n = drawContext.method_51421() - n3 - 1;
            n2 = (drawContext.method_51443() - n4) / 2;
        }
        float f = guiTweaksModule$Part.getHudScale();
        int n5 = Math.round((float)n + (float)n3 * (1.0f - f));
        int n6 = Math.round((float)n2 + (float)n4 * (1.0f - f) / 2.0f);
        guiTweaksModule$Part.reportAnchor(n5, n6, n3, n4);
        if (guiTweaksModule$Part.hidden() && !ModuleManager.editing()) {
            callbackInfo.cancel();
            return;
        }
        if (guiTweaksModule$Part.isDefault()) {
            return;
        }
        drawContext.method_51448().pushMatrix();
        this.aurora$sidebarPushed = true;
        guiTweaksModule$Part.applyTransform(drawContext);
        drawContext.method_51448().translate((float)(n5 - n), (float)(n6 - n2));
    }

    @Inject(method={"method_1757(Lnet/minecraft/class_332;Lnet/minecraft/class_266;)V"}, at={@At(value="RETURN")})
    private void aurora$sidebarReturn(DrawContext drawContext, ScoreboardObjective scoreboardObjective, CallbackInfo callbackInfo) {
        if (this.aurora$sidebarPushed) {
            drawContext.method_51448().popMatrix();
            this.aurora$sidebarPushed = false;
        }
        if (this.aurora$maxX > this.aurora$minX) {
            this.aurora$boxX = this.aurora$minX;
            this.aurora$boxY = this.aurora$minY;
            this.aurora$boxW = this.aurora$maxX - this.aurora$minX;
            this.aurora$boxH = this.aurora$maxY - this.aurora$minY;
        }
    }

    @ModifyArg(method={"method_55439(Lnet/minecraft/class_269;Lnet/minecraft/class_9022;Lnet/minecraft/class_9011;)Lnet/minecraft/class_329$class_9016;"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_329$class_9016;<init>(Lnet/minecraft/class_2561;Lnet/minecraft/class_2561;I)V")}, index=0)
    private Text aurora$antiAltRow(Text text) {
        AntiAltModule antiAltModule = AntiAltModule.get();
        return antiAltModule != null ? antiAltModule.replaceInChat(text) : text;
    }

    @WrapOperation(method={"method_1757(Lnet/minecraft/class_332;Lnet/minecraft/class_266;)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_332;method_25294(IIIII)V")}, require=0)
    private void aurora$sidebarBackground(DrawContext drawContext, int n, int n2, int n3, int n4, int n5, Operation<Void> operation) {
        this.aurora$minX = Math.min(this.aurora$minX, n);
        this.aurora$minY = Math.min(this.aurora$minY, n2);
        this.aurora$maxX = Math.max(this.aurora$maxX, n3);
        this.aurora$maxY = Math.max(this.aurora$maxY, n4);
        GuiTweaksModule guiTweaksModule = GuiTweaksModule.get();
        operation.call(new Object[]{drawContext, n, n2, n3, n4, guiTweaksModule != null && guiTweaksModule.scoreboardBackgroundActive() ? guiTweaksModule.scoreboardBackgroundArgb() : n5});
    }
}
