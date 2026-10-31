package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import dev.quelchi.aurora.module.modules.AntiAltModule;
import dev.quelchi.aurora.module.modules.CrosshairModule;
import dev.quelchi.aurora.module.modules.GuiTweaksModule;
import dev.quelchi.aurora.module.modules.GuiTweaksModule$Key;
import dev.quelchi.aurora.module.modules.GuiTweaksModule$Part;
import dev.quelchi.aurora.module.modules.SaturationOverlayModule;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={InGameHud.class})
public abstract class InGameHudMixin {
    @Unique
    private static final Identifier DIVINE$SELECTION = Identifier.method_60656((String)"hud/hotbar_selection");
    @Unique
    private static final Identifier DIVINE$CROSSHAIR = Identifier.method_60656((String)"hud/crosshair");
    @Unique
    private boolean aurora$hotbarPushed;
    @Unique
    private boolean aurora$healthPushed;
    @Unique
    private boolean aurora$foodPushed;
    @Unique
    private static boolean aurora$armorPushed;
    @Unique
    private boolean aurora$airPushed;
    @Unique
    private boolean aurora$mountPushed;

    @Inject(method={"method_55805"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$survivalPush(DrawContext drawContext, RenderTickCounter renderTickCounter, CallbackInfo callbackInfo) {
        drawContext.method_51448().pushMatrix();
        GuiTweaksModule$Part guiTweaksModule$Part = GuiTweaksModule.partOrNull(GuiTweaksModule$Key.SURVIVAL);
        if (guiTweaksModule$Part == null) {
            return;
        }
        int n = drawContext.method_51421();
        int n2 = drawContext.method_51443();
        guiTweaksModule$Part.reportAnchor(n / 2 - 91, n2 - 40, 182, 40);
        if (InGameHudMixin.aurora$isHidden(GuiTweaksModule$Key.SURVIVAL)) {
            drawContext.method_51448().popMatrix();
            callbackInfo.cancel();
            return;
        }
        if (guiTweaksModule$Part.isDefault()) {
            return;
        }
        InGameHudMixin.aurora$apply(drawContext, guiTweaksModule$Part);
    }

    @Inject(method={"method_55805"}, at={@At(value="RETURN")})
    private void aurora$survivalPop(DrawContext drawContext, RenderTickCounter renderTickCounter, CallbackInfo callbackInfo) {
        drawContext.method_51448().popMatrix();
    }

    @Unique
    private static void aurora$apply(DrawContext drawContext, GuiTweaksModule$Part guiTweaksModule$Part) {
        guiTweaksModule$Part.applyTransform(drawContext);
    }

    @Unique
    private static void aurora$cancel(DrawContext drawContext, CallbackInfo callbackInfo, boolean bl) {
        if (bl) {
            drawContext.method_51448().popMatrix();
        }
        callbackInfo.cancel();
    }

    @Unique
    private static boolean aurora$begin(DrawContext drawContext, GuiTweaksModule$Key guiTweaksModule$Key, int n, int n2, int n3, int n4) {
        return GuiTweaksModule.beginPart(drawContext, guiTweaksModule$Key, n, n2, n3, n4, true);
    }

    @Unique
    private static boolean aurora$isHidden(GuiTweaksModule$Key guiTweaksModule$Key) {
        return GuiTweaksModule.partHiddenInPlay(guiTweaksModule$Key);
    }

    @Inject(method={"method_1759"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$hotbarHead(DrawContext drawContext, RenderTickCounter renderTickCounter, CallbackInfo callbackInfo) {
        int n = drawContext.method_51421();
        int n2 = drawContext.method_51443();
        this.aurora$hotbarPushed = InGameHudMixin.aurora$begin(drawContext, GuiTweaksModule$Key.HOTBAR, n / 2 - 91, n2 - 22, 182, 22);
        if (InGameHudMixin.aurora$isHidden(GuiTweaksModule$Key.HOTBAR)) {
            InGameHudMixin.aurora$cancel(drawContext, callbackInfo, this.aurora$hotbarPushed);
            this.aurora$hotbarPushed = false;
        }
    }

    @Inject(method={"method_1759"}, at={@At(value="RETURN")})
    private void aurora$hotbarReturn(DrawContext drawContext, RenderTickCounter renderTickCounter, CallbackInfo callbackInfo) {
        if (this.aurora$hotbarPushed) {
            drawContext.method_51448().popMatrix();
            this.aurora$hotbarPushed = false;
        }
    }

    @Inject(method={"method_37298"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$healthHead(DrawContext drawContext, PlayerEntity playerEntity, int n, int n2, int n3, int n4, float f, int n5, int n6, int n7, boolean bl, CallbackInfo callbackInfo) {
        int n8 = drawContext.method_51421();
        int n9 = drawContext.method_51443();
        this.aurora$healthPushed = InGameHudMixin.aurora$begin(drawContext, GuiTweaksModule$Key.HEALTH, n8 / 2 - 91, n9 - 39, 81, 10);
        if (InGameHudMixin.aurora$isHidden(GuiTweaksModule$Key.HEALTH)) {
            InGameHudMixin.aurora$cancel(drawContext, callbackInfo, this.aurora$healthPushed);
            this.aurora$healthPushed = false;
        }
    }

    @Inject(method={"method_37298"}, at={@At(value="RETURN")})
    private void aurora$healthReturn(DrawContext drawContext, PlayerEntity playerEntity, int n, int n2, int n3, int n4, float f, int n5, int n6, int n7, boolean bl, CallbackInfo callbackInfo) {
        if (this.aurora$healthPushed) {
            drawContext.method_51448().popMatrix();
            this.aurora$healthPushed = false;
        }
    }

    @Inject(method={"method_58478"}, at={@At(value="HEAD")}, cancellable=true)
    private static void aurora$armorHead(DrawContext drawContext, PlayerEntity playerEntity, int n, int n2, int n3, int n4, CallbackInfo callbackInfo) {
        int n5 = drawContext.method_51421();
        int n6 = drawContext.method_51443();
        aurora$armorPushed = InGameHudMixin.aurora$begin(drawContext, GuiTweaksModule$Key.ARMOR, n5 / 2 - 91, n6 - 49, 81, 10);
        if (InGameHudMixin.aurora$isHidden(GuiTweaksModule$Key.ARMOR)) {
            InGameHudMixin.aurora$cancel(drawContext, callbackInfo, aurora$armorPushed);
            aurora$armorPushed = false;
        }
    }

    @Inject(method={"method_58478"}, at={@At(value="RETURN")})
    private static void aurora$armorReturn(DrawContext drawContext, PlayerEntity playerEntity, int n, int n2, int n3, int n4, CallbackInfo callbackInfo) {
        if (aurora$armorPushed) {
            drawContext.method_51448().popMatrix();
            aurora$armorPushed = false;
        }
    }

    @Unique
    private static boolean aurora$beginLoose(DrawContext drawContext, GuiTweaksModule$Key guiTweaksModule$Key, int n, int n2, int n3, int n4) {
        return GuiTweaksModule.beginPart(drawContext, guiTweaksModule$Key, n, n2, n3, n4, false);
    }

    @Shadow
    private int method_65019(int n, int n2) {
        throw new AssertionError();
    }

    @Inject(method={"method_65022"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$airHead(DrawContext drawContext, PlayerEntity playerEntity, int n, int n2, int n3, CallbackInfo callbackInfo) {
        this.aurora$airPushed = InGameHudMixin.aurora$begin(drawContext, GuiTweaksModule$Key.AIR, n3 - 81, this.method_65019(n, n2), 81, 10);
        if (InGameHudMixin.aurora$isHidden(GuiTweaksModule$Key.AIR)) {
            InGameHudMixin.aurora$cancel(drawContext, callbackInfo, this.aurora$airPushed);
            this.aurora$airPushed = false;
        }
    }

    @Inject(method={"method_65022"}, at={@At(value="RETURN")})
    private void aurora$airReturn(DrawContext drawContext, PlayerEntity playerEntity, int n, int n2, int n3, CallbackInfo callbackInfo) {
        if (this.aurora$airPushed) {
            drawContext.method_51448().popMatrix();
            this.aurora$airPushed = false;
        }
    }

    @Inject(method={"method_1741"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$mountHead(DrawContext drawContext, CallbackInfo callbackInfo) {
        int n = drawContext.method_51421();
        int n2 = drawContext.method_51443();
        this.aurora$mountPushed = InGameHudMixin.aurora$begin(drawContext, GuiTweaksModule$Key.MOUNT, n / 2 + 10, n2 - 39, 81, 10);
        if (InGameHudMixin.aurora$isHidden(GuiTweaksModule$Key.MOUNT)) {
            InGameHudMixin.aurora$cancel(drawContext, callbackInfo, this.aurora$mountPushed);
            this.aurora$mountPushed = false;
        }
    }

    @Inject(method={"method_1741"}, at={@At(value="RETURN")})
    private void aurora$mountReturn(DrawContext drawContext, CallbackInfo callbackInfo) {
        if (this.aurora$mountPushed) {
            drawContext.method_51448().popMatrix();
            this.aurora$mountPushed = false;
        }
    }

    @Inject(method={"method_58477"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$foodHead(DrawContext drawContext, PlayerEntity playerEntity, int n, int n2, CallbackInfo callbackInfo) {
        int n3 = drawContext.method_51421();
        int n4 = drawContext.method_51443();
        this.aurora$foodPushed = InGameHudMixin.aurora$begin(drawContext, GuiTweaksModule$Key.HUNGER, n3 / 2 + 10, n4 - 39, 81, 10);
        if (InGameHudMixin.aurora$isHidden(GuiTweaksModule$Key.HUNGER)) {
            InGameHudMixin.aurora$cancel(drawContext, callbackInfo, this.aurora$foodPushed);
            this.aurora$foodPushed = false;
        }
    }

    @Inject(method={"method_58477"}, at={@At(value="RETURN")})
    private void aurora$foodReturn(DrawContext drawContext, PlayerEntity playerEntity, int n, int n2, CallbackInfo callbackInfo) {
        SaturationOverlayModule.drawOnFood(drawContext, playerEntity, n, n2);
        if (this.aurora$foodPushed) {
            drawContext.method_51448().popMatrix();
            this.aurora$foodPushed = false;
        }
    }

    @WrapOperation(method={"method_55805"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_11223;method_70866(Lnet/minecraft/class_332;Lnet/minecraft/class_327;I)V")})
    private void aurora$experienceLevel(DrawContext drawContext, TextRenderer textRenderer, int n, Operation<Void> operation) {
        boolean bl;
        if (GuiTweaksModule.partHiddenInPlay(GuiTweaksModule$Key.EXPERIENCE)) {
            return;
        }
        GuiTweaksModule$Part guiTweaksModule$Part = GuiTweaksModule.partOrNull(GuiTweaksModule$Key.EXPERIENCE);
        boolean bl2 = bl = guiTweaksModule$Part != null && !guiTweaksModule$Part.isDefault();
        if (bl) {
            drawContext.method_51448().pushMatrix();
            guiTweaksModule$Part.applyTransform(drawContext);
        }
        operation.call(new Object[]{drawContext, textRenderer, n});
        if (bl) {
            drawContext.method_51448().popMatrix();
        }
    }

    @WrapOperation(method={"method_1736"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_332;method_52706(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/class_2960;IIII)V")})
    private void divine$customCrosshair(DrawContext drawContext, RenderPipeline renderPipeline, Identifier identifier, int n, int n2, int n3, int n4, Operation<Void> operation) {
        CrosshairModule crosshairModule = CrosshairModule.get();
        if (crosshairModule != null && crosshairModule.isEnabled() && DIVINE$CROSSHAIR.equals((Object)identifier)) {
            crosshairModule.render(drawContext);
            return;
        }
        operation.call(new Object[]{drawContext, renderPipeline, identifier, n, n2, n3, n4});
    }

    @Inject(method={"method_55800"}, at={@At(value="HEAD")}, cancellable=true)
    private void divine$hideActionBar(DrawContext drawContext, RenderTickCounter renderTickCounter, CallbackInfo callbackInfo) {
        GuiTweaksModule guiTweaksModule = GuiTweaksModule.get();
        if (guiTweaksModule != null && guiTweaksModule.hideActionBar()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"method_1749"}, at={@At(value="HEAD")}, cancellable=true)
    private void divine$noItemName(DrawContext drawContext, CallbackInfo callbackInfo) {
        GuiTweaksModule guiTweaksModule = GuiTweaksModule.get();
        if (guiTweaksModule != null && guiTweaksModule.hideItemName()) {
            callbackInfo.cancel();
        }
    }

    @WrapOperation(method={"method_1759"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_332;method_52706(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/class_2960;IIII)V")})
    private void divine$hideSelector(DrawContext drawContext, RenderPipeline renderPipeline, Identifier identifier, int n, int n2, int n3, int n4, Operation<Void> operation) {
        GuiTweaksModule guiTweaksModule = GuiTweaksModule.get();
        if (guiTweaksModule != null && guiTweaksModule.hideSelector() && DIVINE$SELECTION.equals((Object)identifier)) {
            return;
        }
        operation.call(new Object[]{drawContext, renderPipeline, identifier, n, n2, n3, n4});
    }

    @ModifyVariable(method={"method_34004(Lnet/minecraft/class_2561;)V", "method_34002(Lnet/minecraft/class_2561;)V", "method_1758(Lnet/minecraft/class_2561;Z)V"}, at={@At(value="HEAD")}, argsOnly=true, ordinal=0)
    private Text aurora$antiAltTitle(Text text) {
        AntiAltModule antiAltModule = AntiAltModule.get();
        return antiAltModule != null ? antiAltModule.replaceInChat(text) : text;
    }
}
