package dev.quelchi.aurora.prediction;

import dev.quelchi.aurora.popchams.PositionHandler;
import dev.quelchi.aurora.prediction.Prediction;
import dev.quelchi.aurora.prediction.PredictionHandshake;
import dev.quelchi.aurora.util.TotemPops;
import java.util.ArrayDeque;
import java.util.Optional;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.DamageUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.explosion.ExplosionImpl;

public final class SelfDamagePrediction {
    private static final float POP_MARGIN = 0.5f;
    private static final float FLOOR = 0.5f;
    private static final long POP_ECHO_MS;
    private static final ArrayDeque<Long> POP_ECHOES;
    private static float lastRaw;
    private static int lastFullHitAge;
    private static final long[] a;
    private static final Long[] b;

    private SelfDamagePrediction() {
    }

    
    public static void onLocalExplosion(Vec3d var0, float var1_1, boolean var2_2) {
        block88: {
            block86: {
                block87: {
                    block85: {
                        block83: {
                            block84: {
                                block81: {
                                    block82: {
                                        block80: {
                                            block77: {
                                                block76: {
                                                    block78: {
                                                        block79: {
                                                            block74: {
                                                                block75: {
                                                                    block72: {
                                                                        block73: {
                                                                            block71: {
                                                                                block70: {
                                                                                    block69: {
                                                                                        block68: {
                                                                                            var3_3 = PredictionHandshake.a;
                                                                                            if (Prediction.active() && var0 != null) break block68;
                                                                                            return;
                                                                                        }
                                                                                        var4_4 = MinecraftClient.method_1551();
                                                                                        var5_5 = var4_4.field_1724;
                                                                                        if (var5_5 != null && var4_4.field_1687 != null) break block69;
                                                                                        return;
                                                                                    }
                                                                                    v4 = var5_5;
                                                                                    if (var3_3) break block70;
                                                                                    if (v4.method_5805()) {
                                                                                    }
                                                                                    v4 = var5_5;
                                                                                }
                                                                                if (var3_3) break block71;
                                                                                if (!v4.method_7325()) {
                                                                                }
                                                                                v4 = var5_5;
                                                                            }
                                                                            if (var3_3) break block72;
                                                                            if (!v4.method_68878()) break block73;
lbl58:

                                                                            return;
                                                                        }
                                                                        v4 = var5_5;
                                                                    }
                                                                    if (v4.method_31549().field_7480) {
                                                                        return;
                                                                    }
                                                                    var6_6 = SelfDamagePrediction.blastDamage(var0, var1_1, (Entity)var5_5);
                                                                    cfr_temp_0 = var6_6 - 0.0f;
                                                                    v12 = cfr_temp_0 == 0.0f ? 0 : (cfr_temp_0 < 0.0f ? -1 : 1);
                                                                    if (var3_3) break block74;
                                                                    if (v12 > 0) break block75;
                                                                    return;
                                                                }
                                                                v12 = var5_5.field_6012;
                                                            }
                                                            var7_7 = v12;
                                                            if (var3_3) break block76;
                                                            if (var7_7 >= SelfDamagePrediction.lastFullHitAge) {
                                                            }
                                                            v15 = var7_7;
                                                            if (var3_3) break block77;
                                                            if (v15 - SelfDamagePrediction.lastFullHitAge < 10) {
                                                            }
                                                            v18 = var6_6;
                                                            if (var3_3) break block78;
                                                            if (!(v18 <= SelfDamagePrediction.lastRaw)) break block79;
                                                            return;
                                                        }
                                                        v18 = var6_6;
                                                    }
                                                    var8_8 = v18;
                                                    var6_6 -= SelfDamagePrediction.lastRaw;
                                                    SelfDamagePrediction.lastRaw = var8_8;
                                                    if (!var3_3) break block80;
lbl126:

                                                    SelfDamagePrediction.lastRaw = var6_6;
                                                }
                                                v15 = var7_7;
                                            }
                                            SelfDamagePrediction.lastFullHitAge = (int)v15;
                                        }
                                        var8_9 = var5_5.method_48923().method_48819(null, null);
                                        var9_10 = var5_5.method_6096();
                                        var10_11 = (float)var5_5.method_45325(EntityAttributes.field_23725);
                                        v23 = var6_6;
                                        if (var3_3) break block81;
                                        if (!(v23 <= 0.0f)) break block82;
                                        v23 = 0.0f;
                                        break block81;
                                    }
                                    v23 = DamageUtil.method_5496((LivingEntity)var5_5, (float)var6_6, (DamageSource)var8_9, (float)var9_10, (float)var10_11);
                                }
                                var11_12 = v23;
                                v26 = var5_5;
                                if (!var3_3) {
                                    var11_12 = SelfDamagePrediction.afterEffectsAndProtection(v26, var11_12);
                                    if (var11_12 <= 0.0f) {
                                        return;
                                    }
                                    v26 = var5_5;
                                }
                                var12_13 = v26.method_6067();
                                var13_14 = Math.max(0.0f, var11_12 - var12_13);
                                var5_5.method_6073(Math.max(0.0f, var12_13 - var11_12));
                                var14_15 = var5_5.method_6032();
                                v28 = var14_15 - var13_14;
                                if (var3_3) break block83;
                                if (!(v28 > 0.0f)) break block84;
                                var5_5.method_6033(var14_15 - var13_14);
                                return;
                            }
                            v28 = var14_15 - var13_14;
                        }
                        var15_16 = v28;
                        v31 = var2_2;
                        if (var3_3) break block85;
                        if (v31 == 0) break block86;
                        cfr_temp_1 = var15_16 - -0.5f;
                        v31 = cfr_temp_1 == 0.0f ? 0 : (cfr_temp_1 < 0.0f ? -1 : 1);
                    }
                    if (var3_3) break block87;
                    if (v31 > 0) break block86;
                    v31 = (int)SelfDamagePrediction.popTotem(var4_4, var5_5);
                }
                if (var3_3) break block88;
                if (v31 == 0) break block86;
                v31 = 1;
                break block88;
            }
            v31 = 0;
        }
        var16_17 = v31;
        if (var16_17 == 0) {
            var5_5.method_6033(Math.max(0.5f, var15_16));
        }
    }

    private static float afterEffectsAndProtection(ClientPlayerEntity clientPlayerEntity, float f) {
        block9: {
            StatusEffectInstance statusEffectInstance;
            block8: {
                boolean bl;
                block7: {
                    float f2;
                    block6: {
                        bl = PredictionHandshake.a;
                        f2 = f;
                        if (bl) break block6;
                        if (!(f2 <= 0.0f)) break block7;
                        f2 = f;
                    }
                    return f2;
                }
                StatusEffectInstance statusEffectInstance2 = clientPlayerEntity.method_6112(StatusEffects.field_5907);
                statusEffectInstance = statusEffectInstance2;
                if (bl) break block8;
                if (statusEffectInstance == null) break block9;
                statusEffectInstance = statusEffectInstance2;
            }
            int n = statusEffectInstance.method_5578() + 1;
            f = Math.max(0.0f, f * (float)(25 - n * 5) / 25.0f);
        }
        return DamageUtil.method_5497((float)f, (float)SelfDamagePrediction.explosionProtection(clientPlayerEntity));
    }

    
    private static float explosionProtection(ClientPlayerEntity clientPlayerEntity) {
        boolean bl = PredictionHandshake.a;
        try {
            int n;
            Optional optional;
            Optional optional2;
            block23: {
                block24: {
                    block31: {
                        block30: {
                            Registry registry = clientPlayerEntity.method_73183().method_30349().method_30530(RegistryKeys.field_41265);
                            optional2 = registry.method_46746(Enchantments.field_9111);
                            optional = registry.method_46746(Enchantments.field_9107);
                            n = optional2.isEmpty();
                            if (bl) break block23;
                            if (n == 0) break block24;
                            break block30;
                            catch (Exception exception) {
                                throw exception;
                            }
                        }
                        n = optional.isEmpty();
                        if (bl) break block23;
                        break block31;
                        catch (Exception exception) {
                            throw exception;
                        }
                    }
                    if (n == 0) break block24;
                    return 0.0f;
                }
                n = 0;
            }
            int n2 = n;
            for (EquipmentSlot equipmentSlot : EquipmentSlot.field_54086) {
                int n3;
                int n4;
                block34: {
                    block29: {
                        Optional optional3;
                        ItemStack itemStack;
                        block28: {
                            block33: {
                                int n5;
                                block32: {
                                    block27: {
                                        Optional optional4;
                                        block26: {
                                            boolean bl2;
                                            block25: {
                                                if (equipmentSlot.method_5925() != EquipmentSlot.Type.field_6178) {
                                                    continue;
                                                }
                                                itemStack = clientPlayerEntity.method_6118(equipmentSlot);
                                                bl2 = itemStack.method_7960();
                                                if (bl) break block25;
                                                if (bl2) {
                                                    continue;
                                                }
                                                optional4 = optional2;
                                                if (bl) break block26;
                                                bl2 = optional4.isPresent();
                                            }
                                            if (!bl2) break block27;
                                            optional4 = optional2.get();
                                        }
                                        n5 = EnchantmentHelper.method_8225((RegistryEntry)((RegistryEntry)optional4), (ItemStack)itemStack);
                                        break block32;
                                    }
                                    n5 = 0;
                                }
                                n4 = n5;
                                optional3 = optional;
                                if (bl) break block28;
                                if (!optional3.isPresent()) break block29;
                                break block33;
                                catch (Exception exception) {
                                    throw exception;
                                }
                            }
                            optional3 = optional.get();
                        }
                        n3 = EnchantmentHelper.method_8225((RegistryEntry)((RegistryEntry)optional3), (ItemStack)itemStack);
                        break block34;
                    }
                    n3 = 0;
                }
                int n6 = n3;
                n2 += n4 + n6;
                if (!bl) continue;
            }
            return n2;
        }
        catch (Exception exception) {
            return 0.0f;
        }
    }

    private static float blastDamage(Vec3d vec3d, float f, Entity entity) {
        float f2;
        double d;
        double d2;
        block8: {
            float f3;
            block7: {
                d2 = (double)f * 2.0;
                d = Math.sqrt(entity.method_5707(vec3d)) / d2;
                boolean bl = PredictionHandshake.a;
                if (d > 1.0) {
                    return 0.0f;
                }
                f2 = ExplosionImpl.method_61731((Vec3d)vec3d, (Entity)entity);
                f3 = f2;
                if (bl) break block7;
                if (!(f3 <= 0.0f)) break block8;
                f3 = 0.0f;
            }
            return f3;
        }
        double d3 = (1.0 - d) * (double)f2;
        return (float)((d3 * d3 + d3) / 2.0 * 7.0 * d2 + 1.0);
    }

    
    private static boolean popTotem(MinecraftClient minecraftClient, ClientPlayerEntity clientPlayerEntity) {
        ItemStack itemStack;
        ItemStack itemStack2;
        boolean bl;
        ItemStack itemStack3;
        block13: {
            itemStack3 = ItemStack.field_8037;
            bl = PredictionHandshake.a;
            itemStack2 = Hand.values();
            int n = ((Hand[])itemStack2).length;
            int n2 = 0;
            while (n2 < n) {
                Hand hand = itemStack2[n2];
                itemStack = clientPlayerEntity.method_5998(hand);
                if (!bl) {
                    ItemStack itemStack4 = itemStack;
                    if (!bl) {
                        if (itemStack4.method_31574(Items.field_8288)) {
                            itemStack3 = itemStack4;
                            if (!bl) break;
                        }
                        ++n2;
                    }
                    if (!bl) continue;
                }
                break block13;
            }
            itemStack = itemStack3;
        }
        if (!bl) {
            if (itemStack.method_7960()) {
                return false;
            }
            itemStack = itemStack3.method_7972();
        }
        itemStack2 = itemStack;
        itemStack3.method_7934(1);
        clientPlayerEntity.method_6033(1.0f);
        clientPlayerEntity.method_6012();
        clientPlayerEntity.method_6092(new StatusEffectInstance(StatusEffects.field_5924, 900, 1));
        clientPlayerEntity.method_6092(new StatusEffectInstance(StatusEffects.field_5898, 100, 1));
        boolean bl2 = clientPlayerEntity.method_6092(new StatusEffectInstance(StatusEffects.field_5918, 800, 0));
        if (bl) return bl2;
        minecraftClient.field_1713.method_3051((Entity)clientPlayerEntity, (ParticleEffect)ParticleTypes.field_11220, 30);
        minecraftClient.field_1687.method_8486(clientPlayerEntity.method_23317(), clientPlayerEntity.method_23318(), clientPlayerEntity.method_23321(), SoundEvents.field_14931, SoundCategory.field_15248, 1.0f, 1.0f, false);
        if (minecraftClient.field_1773 != null) {
            minecraftClient.field_1773.method_3189(itemStack2);
        }
        TotemPops.onPop(clientPlayerEntity.method_5667());
        PositionHandler.onTotemStatus((PlayerEntity)clientPlayerEntity);
        POP_ECHOES.addLast(Util.method_658() + SelfDamagePrediction.a(18652, 938603292818191296L));
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean swallowsPop(Entity entity) {
        long l;
        Long l2;
        ClientPlayerEntity clientPlayerEntity;
        MinecraftClient minecraftClient;
        boolean bl;
        block15: {
            block14: {
                bl = PredictionHandshake.a;
                boolean bl2 = POP_ECHOES.isEmpty();
                if (bl) return bl2;
                if (!bl2) break block14;
                return false;
            }
            minecraftClient = MinecraftClient.method_1551();
            clientPlayerEntity = minecraftClient.field_1724;
            if (bl) break block15;
            if (clientPlayerEntity == null) return false;
            clientPlayerEntity = entity;
        }
        if (clientPlayerEntity != minecraftClient.field_1724) {
            return false;
        }
        long l3 = Util.method_658();
        while (true) {
            l2 = POP_ECHOES.pollFirst();
            if (l2 == null) break;
            long l4 = l3 - l2;
            l = l4 == 0L ? 0 : (l4 < 0L ? -1 : 1);
            if (bl) return (boolean)l;
            if (l <= 0) break;
        }
        l = l2 != null ? (long)1 : (long)0;
        return (boolean)l;
    }

    
    static {
        long l = 7312414694772746991L;
        long l2 = 0xC35E5EE4CC46AAEFL ^ l;
        long l3 = 3739431628266000649L;
        long[] lArray = new long[2];
        int n = 0;
        String string = ">\u00e3\u00b7\u00f8%\u00d7g\u0015q\u00behB\u00fc7\u0089\u00f6";
        int n2 = ">\u00e3\u00b7\u00f8%\u00d7g\u0015q\u00behB\u00fc7\u0089\u00f6".length();
        int n3 = 0;
        do {
            byte[] byArray = string.substring(n3, n3 += 8).getBytes("ISO-8859-1");
            int n4 = n++;
            lArray[n4] = (((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL) ^ l3;
        } while (n3 < n2);
        a = lArray;
        b = new Long[2];
        POP_ECHO_MS = SelfDamagePrediction.a(9790, 4781495702667681059L);
        POP_ECHOES = new ArrayDeque();
        lastFullHitAge = (int)l2;
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x7B1C) & Short.MAX_VALUE;
        if (b[n2] == null) {
            SelfDamagePrediction.b[n2] = a[n2] ^ l;
        }
        return b[n2];
    }
}
