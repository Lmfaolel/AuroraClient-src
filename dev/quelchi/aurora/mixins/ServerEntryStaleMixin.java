package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.perf.Tweaks;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerServerListWidget;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={MultiplayerServerListWidget.ServerEntry.class})
public abstract class ServerEntryStaleMixin {
    @Unique
    private static final Map<String, Text> aurora$lastLabel = new HashMap<String, Text>();
    @Shadow
    @Final
    private ServerInfo field_19120;

    @Inject(method={"method_25343(Lnet/minecraft/class_332;IIZF)V"}, at={@At(value="INVOKE", target="Ljava/util/concurrent/ThreadPoolExecutor;submit(Ljava/lang/Runnable;)Ljava/util/concurrent/Future;")})
    private void aurora$showLastLabel(DrawContext drawContext, int n, int n2, boolean bl, float f, CallbackInfo callbackInfo) {
        if (!Tweaks.SERVER_PINGS) {
            return;
        }
        Text text = aurora$lastLabel.get(this.field_19120.field_3761);
        if (text != null) {
            this.field_19120.field_3757 = text;
        }
    }

    @Inject(method={"method_55815"}, at={@At(value="HEAD")})
    private void aurora$rememberLabel(CallbackInfo callbackInfo) {
        if (!Tweaks.SERVER_PINGS) {
            return;
        }
        ServerInfo.Status status = this.field_19120.method_55825();
        if (status == ServerInfo.Status.field_47884 || status == ServerInfo.Status.field_47883) {
            aurora$lastLabel.put(this.field_19120.field_3761, this.field_19120.field_3757);
        }
    }
}
