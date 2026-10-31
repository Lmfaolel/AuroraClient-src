package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.social.FeaturedServers;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.option.ServerList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ServerList.class})
public abstract class ServerListMixin {
    @Shadow
    @Final
    private List<ServerInfo> field_3749;
    @Unique
    private final List<ServerInfo> aurora$heldBack = new ArrayList<ServerInfo>();

    @Inject(method={"method_2981"}, at={@At(value="RETURN")})
    private void aurora$pinFeatured(CallbackInfo callbackInfo) {
        if (!MinecraftClient.method_1551().method_18854()) {
            return;
        }
        this.field_3749.addAll(0, FeaturedServers.fresh());
    }

    @Inject(method={"method_2987"}, at={@At(value="HEAD")})
    private void aurora$holdBackFeatured(CallbackInfo callbackInfo) {
        this.aurora$heldBack.clear();
        this.field_3749.removeIf(this::lambda$aurora$holdBackFeatured$0);
    }

    @Inject(method={"method_2987"}, at={@At(value="RETURN")})
    private void aurora$restoreFeatured(CallbackInfo callbackInfo) {
        this.field_3749.addAll(0, this.aurora$heldBack);
        this.aurora$heldBack.clear();
    }

    private boolean lambda$aurora$holdBackFeatured$0(ServerInfo serverInfo) {
        return FeaturedServers.is(serverInfo) && this.aurora$heldBack.add(serverInfo);
    }
}
