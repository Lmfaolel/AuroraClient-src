package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import dev.quelchi.aurora.social.FeaturedServers;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerServerListWidget;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.option.ServerList;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={MultiplayerServerListWidget.ServerEntry.class})
public abstract class ServerEntryMixin {
    private static final String RENDER = "render(Lnet/minecraft/client/gui/DrawContext;IIZF)V";
    @Shadow
    @Final
    private MultiplayerScreen field_19118;
    @Shadow
    @Final
    private MinecraftClient field_19119;
    @Shadow
    @Final
    private ServerInfo field_19120;

    @Inject(method={"method_22110(II)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$keepFeaturedInPlace(int n, int n2, CallbackInfo callbackInfo) {
        if (Math.min(n, n2) < FeaturedServers.leading(this.field_19118.method_2529())) {
            callbackInfo.cancel();
        }
    }

    @WrapWithCondition(method={"method_25343(Lnet/minecraft/class_332;IIZF)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_332;method_52706(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/class_2960;IIII)V")})
    private boolean aurora$hideLockedArrows(DrawContext drawContext, RenderPipeline renderPipeline, Identifier identifier, int n, int n2, int n3, int n4) {
        String string = identifier.method_12832();
        boolean bl = string.startsWith("server_list/move_up");
        if (!bl && !string.startsWith("server_list/move_down")) {
            return true;
        }
        ServerList serverList = this.field_19118.method_2529();
        int n5 = FeaturedServers.indexOf(serverList, this.field_19120);
        int n6 = FeaturedServers.leading(serverList);
        return bl ? n5 - 1 >= n6 : n5 >= n6;
    }
}
