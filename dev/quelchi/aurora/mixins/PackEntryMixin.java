package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.quelchi.aurora.gui.PackPreview;
import dev.quelchi.aurora.gui.PackTextures;
import dev.quelchi.aurora.mixins.AbstractPackAccessor;
import java.util.List;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.pack.PackListWidget;
import net.minecraft.client.gui.screen.pack.ResourcePackOrganizer;
import net.minecraft.client.gui.widget.MultilineTextWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.resource.ResourcePack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={PackListWidget.ResourcePackEntry.class})
public abstract class PackEntryMixin {
    private static final String RENDER = "render(Lnet/minecraft/client/gui/DrawContext;IIZF)V";
    @Shadow
    @Final
    private ResourcePackOrganizer.Pack field_19129;
    @Unique
    private boolean aurora$previewing;

    @Inject(method={"method_25343(Lnet/minecraft/class_332;IIZF)V"}, at={@At(value="HEAD")})
    private void aurora$wantTextures(DrawContext drawContext, int n, int n2, boolean bl, float f, CallbackInfo callbackInfo) {
        this.aurora$previewing = false;
        if (!bl) {
            return;
        }
        String string = this.field_19129.method_48276();
        if (PackPreview.ready(string)) {
            this.aurora$previewing = true;
            return;
        }
        ResourcePackOrganizer.Pack pack = this.field_19129;
        if (pack instanceof AbstractPackAccessor) {
            AbstractPackAccessor abstractPackAccessor = (AbstractPackAccessor)pack;
            PackTextures.want(string, () -> PackEntryMixin.lambda$aurora$wantTextures$0(abstractPackAccessor));
        }
    }

    @WrapWithCondition(method={"method_25343(Lnet/minecraft/class_332;IIZF)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_7842;method_25394(Lnet/minecraft/class_332;IIF)V")})
    private boolean aurora$hideName(TextWidget textWidget, DrawContext drawContext, int n, int n2, float f) {
        return !this.aurora$previewing;
    }

    @WrapWithCondition(method={"method_25343(Lnet/minecraft/class_332;IIZF)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_7940;method_25394(Lnet/minecraft/class_332;IIF)V")})
    private boolean aurora$hideDescription(MultilineTextWidget multilineTextWidget, DrawContext drawContext, int n, int n2, float f) {
        return !this.aurora$previewing;
    }

    @WrapWithCondition(method={"method_25343(Lnet/minecraft/class_332;IIZF)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_332;method_25294(IIIII)V", ordinal=0)})
    private boolean aurora$noIncompatibleWash(DrawContext drawContext, int n, int n2, int n3, int n4, int n5) {
        return false;
    }

    @ModifyExpressionValue(method={"method_25343(Lnet/minecraft/class_332;IIZF)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_3281;method_14437()Z")})
    private boolean aurora$alwaysCompatible(boolean bl) {
        return true;
    }

    @WrapOperation(method={"method_25343(Lnet/minecraft/class_332;IIZF)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_5369$class_5371;method_29653()Lnet/minecraft/class_2561;")})
    private Text aurora$plainDescription(ResourcePackOrganizer.Pack pack, Operation<Text> operation) {
        return pack.method_29651();
    }

    @Inject(method={"method_48478"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$noIncompatibleConfirm(CallbackInfo callbackInfo) {
        this.field_19129.method_29656();
        callbackInfo.cancel();
    }

    @Inject(method={"method_25343(Lnet/minecraft/class_332;IIZF)V"}, at={@At(value="TAIL")})
    private void aurora$drawPreview(DrawContext drawContext, int n, int n2, boolean bl, float f, CallbackInfo callbackInfo) {
        if (!this.aurora$previewing) {
            return;
        }
        PackListWidget.ResourcePackEntry resourcePackEntry = (PackListWidget.ResourcePackEntry)this;
        int n3 = resourcePackEntry.method_73380() + 32 + 3;
        PackPreview.render(drawContext, this.field_19129.method_48276(), n3, resourcePackEntry.method_73382(), resourcePackEntry.method_73389() - n3);
        List<Text> list = PackPreview.stats(this.field_19129.method_48276());
        if (!list.isEmpty()) {
            drawContext.method_51434(MinecraftClient.method_1551().field_1772, list, n, n2);
        }
    }

    private static ResourcePack lambda$aurora$wantTextures$0(AbstractPackAccessor abstractPackAccessor) {
        return abstractPackAccessor.getProfile().method_14458();
    }
}
