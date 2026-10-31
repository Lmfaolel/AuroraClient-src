package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.module.modules.AntiAltModule;
import dev.quelchi.aurora.module.modules.NametagsModule;
import dev.quelchi.aurora.module.modules.TierTaggerModule;
import dev.quelchi.aurora.module.modules.TotemCounterModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={PlayerListHud.class})
public abstract class PlayerListHudMixin {
    @Inject(method={"method_1918"}, at={@At(value="RETURN")}, cancellable=true)
    private void divine$spoofTabName(PlayerListEntry playerListEntry, CallbackInfoReturnable<Text> callbackInfoReturnable) {
        Text text;
        TierTaggerModule tierTaggerModule;
        Text text2;
        Text text3;
        Text text4;
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        boolean bl = minecraftClient.field_1724 != null && playerListEntry.method_2966().id().equals(minecraftClient.field_1724.method_5667());
        Text text5 = (Text)callbackInfoReturnable.getReturnValue();
        AntiAltModule antiAltModule = AntiAltModule.get();
        if (antiAltModule != null && bl && (text4 = antiAltModule.spoofedName()) != null) {
            text5 = text4;
        }
        Text text6 = text3 = (text4 = NametagsModule.badgeFor(playerListEntry.method_2966().id())) == null ? null : NametagsModule.beforeName(text5, playerListEntry.method_2966().name(), playerListEntry.method_2966().id(), text4);
        if (text3 != null) {
            text5 = text3;
        }
        if ((text2 = TotemCounterModule.tabListSegment(playerListEntry.method_2966().id())) != null) {
            text5 = Text.method_43473().method_10852(text5).method_10852(text2);
            callbackInfoReturnable.setReturnValue((Object)text5);
        }
        if ((tierTaggerModule = TierTaggerModule.get()) != null && (text = tierTaggerModule.segment(playerListEntry.method_2966().id(), playerListEntry.method_2966().name())) != null) {
            MutableText mutableText = text5 = tierTaggerModule.onRight() ? Text.method_43473().method_10852(text5).method_10852((Text)Text.method_43470((String)" ")).method_10852(text) : Text.method_43473().method_10852(text).method_10852((Text)Text.method_43470((String)" ")).method_10852(text5);
        }
        if (text4 != null && text3 == null) {
            text5 = Text.method_43473().method_10852(text4).method_10852(NametagsModule.badgeGap()).method_10852(text5);
        }
        if (text5 != callbackInfoReturnable.getReturnValue()) {
            callbackInfoReturnable.setReturnValue((Object)text5);
        }
    }
}
