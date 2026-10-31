package dev.quelchi.aurora.gui;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.world.ClientWorld;

class PreviewPlayer$Guarded
extends OtherClientPlayerEntity {
    private PreviewPlayer$Guarded(ClientWorld clientWorld, GameProfile gameProfile) {
        super(clientWorld, gameProfile);
    }

    protected PlayerListEntry method_3123() {
        PlayerListEntry playerListEntry = MinecraftClient.method_1551().method_1562() == null ? null : super.method_3123();
        return playerListEntry;
    }
}
