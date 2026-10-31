package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.social.FeaturedServers;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerServerListWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.EntryListWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={MultiplayerScreen.class})
public abstract class MultiplayerScreenMixin {
    @Shadow
    protected MultiplayerServerListWidget field_3043;
    @Shadow
    private ButtonWidget field_3041;
    @Shadow
    private ButtonWidget field_3047;
    private static final int STRIP = 12;

    @Inject(method={"method_20121"}, at={@At(value="TAIL")})
    private void aurora$lockFeatured(CallbackInfo callbackInfo) {
        MultiplayerServerListWidget.ServerEntry serverEntry;
        EntryListWidget.Entry entry = this.field_3043.method_25334();
        if (entry instanceof MultiplayerServerListWidget.ServerEntry && FeaturedServers.is((serverEntry = (MultiplayerServerListWidget.ServerEntry)entry).method_20133())) {
            this.field_3041.field_22763 = false;
            this.field_3047.field_22763 = false;
        }
    }
}
