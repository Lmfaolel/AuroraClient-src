package dev.quelchi.aurora.input;

import dev.quelchi.aurora.cosmetics.content.Cosmetics;
import dev.quelchi.aurora.cosmetics.renderer.EmoteEngine;
import dev.quelchi.aurora.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.MathHelper;

public final class FreeLook {
    private static boolean active;
    private static float yaw;
    private static float pitch;
    private static final float SENSITIVITY = 0.15f;
    public static int a;

    private FreeLook() {
    }

    public static boolean active() {
        return active;
    }

    public static float yaw() {
        return yaw;
    }

    public static float pitch() {
        return pitch;
    }

    public static boolean owns(Object object) {
        boolean bl;
        block5: {
            block4: {
                if (!active || object != MinecraftClient.method_1551().field_1724) break block4;
                bl = true;
                break block5;
            }
            bl = false;
        }
        return bl;
    }

    public static void update(MinecraftClient minecraftClient) {
        block33: {
            block38: {
                boolean bl;
                int n;
                ClientPlayerEntity clientPlayerEntity;
                block37: {
                    block36: {
                        block31: {
                            boolean bl2;
                            block32: {
                                boolean bl3;
                                block35: {
                                    block34: {
                                        boolean bl4;
                                        block30: {
                                            block28: {
                                                String string;
                                                block29: {
                                                    clientPlayerEntity = minecraftClient.field_1724;
                                                    n = a;
                                                    String string2 = Cosmetics.getOwnEmote();
                                                    if (clientPlayerEntity == null) break block28;
                                                    string = string2;
                                                    if (n != 0) break block29;
                                                    if (string.isEmpty()) break block28;
                                                    string = string2;
                                                }
                                                bl4 = EmoteEngine.isTraversal(string);
                                                if (n != 0) break block30;
                                                if (bl4) break block28;
                                                bl4 = true;
                                                break block30;
                                            }
                                            bl4 = false;
                                        }
                                        bl2 = bl4;
                                        bl = bl2;
                                        if (n != 0) break block31;
                                        if (!bl) break block32;
                                        bl = active;
                                        if (n != 0) break block31;
                                        if (bl) break block32;
                                        yaw = clientPlayerEntity.method_36454();
                                        pitch = clientPlayerEntity.method_36455();
                                        active = true;
                                        if (n == 0) break block33;
                                        if (!Module.a) break block34;
                                        bl3 = false;
                                        break block35;
                                    }
                                    bl3 = true;
                                }
                                Module.a = bl3;
                            }
                            bl = bl2;
                        }
                        if (n != 0) break block36;
                        if (bl) break block33;
                        bl = active;
                    }
                    if (n != 0) break block37;
                    if (!bl) break block33;
                    bl = false;
                }
                active = bl;
                if (n != 0) break block38;
                if (clientPlayerEntity == null) break block33;
                clientPlayerEntity.method_36456(yaw);
                clientPlayerEntity.method_36457(pitch);
                clientPlayerEntity.method_5847(yaw);
                clientPlayerEntity.method_5636(yaw);
                clientPlayerEntity.field_5982 = yaw;
            }
            clientPlayerEntity.field_6004 = pitch;
        }
    }

    public static void look(double d, double d2) {
        yaw += (float)d * 0.15f;
        pitch = MathHelper.method_15363((float)(pitch + (float)d2 * 0.15f), (float)-90.0f, (float)90.0f);
        int n = a;
        if (Module.a) {
            a = ++n;
        }
    }
}
