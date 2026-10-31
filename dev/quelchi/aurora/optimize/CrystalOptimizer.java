package dev.quelchi.aurora.optimize;

import dev.quelchi.aurora.module.modules.OptimizersModule;
import dev.quelchi.aurora.optimize.KeptCrystals;
import dev.quelchi.aurora.optimize.MarlowProtocol;
import dev.quelchi.aurora.optimize.ProjectileOptimizer;
import dev.quelchi.aurora.prediction.Prediction;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.event.GameEvent;

public final class CrystalOptimizer {
    private CrystalOptimizer() {
    }

    
    public static boolean on() {
        boolean bl;
        boolean bl2;
        block10: {
            OptimizersModule optimizersModule;
            block9: {
                OptimizersModule optimizersModule2;
                bl2 = ProjectileOptimizer.a;
                boolean bl3 = MarlowProtocol.optedOut();
                if (bl2) return bl3;
                if (bl3) {
                    return false;
                }
                optimizersModule = optimizersModule2 = OptimizersModule.get();
                if (bl2) break block9;
                if (optimizersModule == null) break block10;
                optimizersModule = optimizersModule2;
            }
            bl = optimizersModule.crystals();
            if (bl2) return bl;
            if (bl) return true;
        }
        bl = Prediction.active();
        if (bl2) return bl;
        if (bl) return true;
        return false;
    }

    public static void onAttack(Entity entity) {
        MinecraftClient minecraftClient;
        EndCrystalEntity endCrystalEntity;
        block38: {
            block36: {
                block37: {
                    boolean bl;
                    boolean bl2;
                    block35: {
                        block33: {
                            block34: {
                                OptimizersModule optimizersModule;
                                block32: {
                                    MinecraftClient minecraftClient2;
                                    block30: {
                                        block31: {
                                            boolean bl3;
                                            block28: {
                                                block29: {
                                                    block27: {
                                                        Entity entity2;
                                                        block26: {
                                                            bl2 = ProjectileOptimizer.a;
                                                            entity2 = entity;
                                                            if (bl2) break block26;
                                                            if (!(entity2 instanceof EndCrystalEntity)) break block27;
                                                            entity2 = entity;
                                                        }
                                                        endCrystalEntity = (EndCrystalEntity)entity2;
                                                        bl3 = endCrystalEntity.method_31481();
                                                        if (bl2) break block28;
                                                        if (!bl3) break block29;
                                                    }
                                                    return;
                                                }
                                                bl3 = CrystalOptimizer.on();
                                            }
                                            if (!bl3) {
                                                return;
                                            }
                                            minecraftClient = MinecraftClient.method_1551();
                                            minecraftClient2 = minecraftClient;
                                            if (bl2) break block30;
                                            if (minecraftClient2.field_1724 != null) break block31;
                                            return;
                                        }
                                        minecraftClient2 = minecraftClient;
                                    }
                                    if (!CrystalOptimizer.breaks((LivingEntity)minecraftClient2.field_1724)) {
                                        return;
                                    }
                                    OptimizersModule optimizersModule2 = OptimizersModule.get();
                                    optimizersModule = optimizersModule2;
                                    if (bl2) break block32;
                                    if (optimizersModule == null) break block33;
                                    optimizersModule = optimizersModule2;
                                }
                                bl = optimizersModule.keepVisible();
                                if (bl2) break block34;
                                if (!bl) break block33;
                                bl = Prediction.active();
                            }
                            if (bl2) break block35;
                            if (bl) break block33;
                            bl = true;
                            break block35;
                        }
                        bl = false;
                    }
                    boolean bl4 = bl;
                    if (bl2) break block36;
                    if (!bl4) break block37;
                    KeptCrystals.keep(endCrystalEntity);
                    if (!bl2) break block38;
                }
                endCrystalEntity.method_5650(Entity.RemovalReason.field_26998);
            }
            endCrystalEntity.method_32876((RegistryEntry)GameEvent.field_37676);
        }
        CrystalOptimizer.retarget(minecraftClient, (Entity)endCrystalEntity);
    }

    public static void clearAll() {
        KeptCrystals.reset();
    }

    private static void retarget(MinecraftClient minecraftClient, Entity entity) {
        block5: {
            MinecraftClient minecraftClient2;
            block4: {
                boolean bl = ProjectileOptimizer.a;
                minecraftClient2 = minecraftClient;
                if (bl) break block4;
                if (minecraftClient2.field_1692 != entity) break block5;
                minecraftClient2 = minecraftClient;
            }
            minecraftClient2.field_1773.method_3190(1.0f);
        }
    }

    public static boolean breaks(LivingEntity livingEntity) {
        double d;
        block2: {
            block3: {
                boolean bl = ProjectileOptimizer.a;
                double d2 = CrystalOptimizer.damageOf(livingEntity) - 0.0;
                d = d2 == 0.0 ? 0 : (d2 > 0.0 ? 1 : -1);
                if (bl) break block2;
                if (d <= 0) break block3;
                d = 1.0;
                break block2;
            }
            d = 0.0;
        }
        return (boolean)d;
    }

    private static double damageOf(LivingEntity livingEntity) {
        StatusEffectInstance statusEffectInstance;
        StatusEffectInstance statusEffectInstance2;
        double d;
        block3: {
            block4: {
                d = livingEntity.method_45326(EntityAttributes.field_23721);
                d += CrystalOptimizer.weaponDamage(livingEntity.method_6047());
                StatusEffectInstance statusEffectInstance3 = livingEntity.method_6112(StatusEffects.field_5910);
                boolean bl = ProjectileOptimizer.a;
                statusEffectInstance2 = statusEffectInstance3;
                if (bl) break block3;
                if (statusEffectInstance2 == null) break block4;
                d += 3.0 * (double)(statusEffectInstance3.method_5578() + 1);
            }
            statusEffectInstance2 = livingEntity.method_6112(StatusEffects.field_5911);
        }
        if ((statusEffectInstance = statusEffectInstance2) != null) {
            d -= 4.0 * (double)(statusEffectInstance.method_5578() + 1);
        }
        return d;
    }

    private static double weaponDamage(ItemStack itemStack) {
        int n;
        block4: {
            block5: {
                boolean bl = ProjectileOptimizer.a;
                n = itemStack.method_7960();
                if (bl) break block4;
                if (n == 0) break block5;
                return 0.0;
            }
            n = 1;
        }
        double[] dArray = new double[n];
        dArray[0] = 0.0;
        double[] dArray2 = dArray;
        itemStack.method_57354(EquipmentSlot.field_6173, (arg_0, arg_1) -> CrystalOptimizer.lambda$weaponDamage$0(dArray2, arg_0, arg_1));
        return dArray2[0];
    }

    private static void lambda$weaponDamage$0(double[] dArray, RegistryEntry registryEntry, EntityAttributeModifier entityAttributeModifier) {
        if (EntityAttributes.field_23721.equals((Object)registryEntry)) {
            dArray[0] = dArray[0] + entityAttributeModifier.comp_2449();
        }
    }
}
