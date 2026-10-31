package dev.quelchi.aurora.cosmetics.renderer;

import dev.quelchi.aurora.cosmetics.content.Cosmetics;
import dev.quelchi.aurora.cosmetics.renderer.EffectList;
import dev.quelchi.aurora.render.EffectEngine;
import java.util.UUID;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntityDamageS2CPacket;

public final class HitEffects {
    public static final EffectList LIST = new EffectList();

    private HitEffects() {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void onDamage(EntityDamageS2CPacket entityDamageS2CPacket) {
        Entity entity;
        PlayerEntity playerEntity;
        Entity entity2;
        MinecraftClient minecraftClient;
        block36: {
            Entity entity3;
            int n;
            block35: {
                EntityDamageS2CPacket entityDamageS2CPacket2;
                block34: {
                    MinecraftClient minecraftClient2;
                    block33: {
                        minecraftClient = MinecraftClient.method_1551();
                        n = EffectList.a;
                        minecraftClient2 = minecraftClient;
                        if (n != 0) break block33;
                        if (minecraftClient2.field_1724 == null) return;
                        minecraftClient2 = minecraftClient;
                    }
                    if (minecraftClient2.field_1687 == null) {
                        return;
                    }
                    entityDamageS2CPacket2 = entityDamageS2CPacket;
                    if (n != 0) break block34;
                    if (entityDamageS2CPacket2.comp_1269() != entityDamageS2CPacket.comp_1270()) return;
                    entityDamageS2CPacket2 = entityDamageS2CPacket;
                }
                if (!entityDamageS2CPacket2.comp_1268().method_40225(DamageTypes.field_42320)) {
                    return;
                }
                entity2 = minecraftClient.field_1687.method_8469(entityDamageS2CPacket.comp_1269());
                entity3 = entity2;
                if (n != 0) break block35;
                if (!(entity3 instanceof PlayerEntity)) return;
                entity3 = entity2;
            }
            playerEntity = (PlayerEntity)entity3;
            if (n != 0) {
                return;
            }
            entity = minecraftClient.field_1687.method_8469(entityDamageS2CPacket.comp_1267());
            if (n == 0) {
                entity2 = entity;
                if (entity2 == null) return;
                entity = entity2;
            }
            if (n != 0) break block36;
            if (entity == playerEntity) return;
            entity = entity2;
        }
        if (entity == minecraftClient.field_1724) {
            return;
        }
        EffectList effectList = LIST;
        UUID uUID = playerEntity.method_5667();
        boolean bl = playerEntity == minecraftClient.field_1724;
        EffectEngine.play(effectList.byId(Cosmetics.hitEffectFor(uUID, bl)), entity2, entity2.method_73189(), playerEntity.method_33571(), 1.0f, false);
    }
}
