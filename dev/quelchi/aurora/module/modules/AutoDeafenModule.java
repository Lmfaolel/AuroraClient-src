package dev.quelchi.aurora.module.modules;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.AutoDeafenModule$Modifier;
import dev.quelchi.aurora.module.modules.AutoDeafenModule$Trigger;
import dev.quelchi.aurora.module.setting.EnumSetting;
import dev.quelchi.aurora.module.setting.KeybindSetting;
import dev.quelchi.aurora.module.setting.NumberSetting;
import dev.quelchi.aurora.util.TotemPops;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.lwjgl.glfw.GLFW;

public final class AutoDeafenModule
extends Module {
    private static final int VK_LSHIFT;
    private static final int VK_LCONTROL;
    private static final int VK_LMENU;
    private static AutoDeafenModule instance;
    private final KeybindSetting key;
    private final EnumSetting<AutoDeafenModule$Modifier> modifier;
    private final EnumSetting<AutoDeafenModule$Trigger> trigger;
    private final NumberSetting pops;
    private final NumberSetting undeafen;
    private final Set<UUID> opponents;
    private long lastCombat;
    private volatile int[][] deafenedWith;
    private static final String[] b;
    private static final long[] d;
    private static final Integer[] e;
    private static final long f;

    public AutoDeafenModule() {
        String[] stringArray = b;
        super(stringArray[2], stringArray[1], Category.COMBAT, false);
        this.key = new KeybindSetting(stringArray[9], -1);
        this.modifier = new EnumSetting<AutoDeafenModule$Modifier>(stringArray[10], AutoDeafenModule$Modifier.ALT);
        this.trigger = new EnumSetting<AutoDeafenModule$Trigger>(stringArray[0], AutoDeafenModule$Trigger.COMBAT);
        this.pops = new NumberSetting(stringArray[6], 1.0, 10.0, 2.0, 1.0);
        this.undeafen = new NumberSetting(stringArray[7], 1.0, 60.0, 8.0, 1.0);
        this.opponents = new HashSet<UUID>();
        instance = this;
        this.pops.visibleWhen(this::lambda$new$0);
        this.addSettings(this.key, this.modifier, this.trigger, this.pops, this.undeafen);
        ClientPlayConnectionEvents.DISCONNECT.register(this::lambda$new$1);
        Runtime.getRuntime().addShutdownHook(new Thread(this::lambda$new$2, stringArray[3]));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void onDamage(int n, int n2) {
        Entity entity;
        block35: {
            int n3;
            int n4;
            boolean bl;
            block33: {
                int n5;
                block34: {
                    block31: {
                        block32: {
                            AutoDeafenModule autoDeafenModule;
                            block29: {
                                block30: {
                                    block28: {
                                        block27: {
                                            block26: {
                                                bl = AmbienceModule.b;
                                                autoDeafenModule = instance;
                                                if (bl) break block26;
                                                if (autoDeafenModule == null) return;
                                                autoDeafenModule = instance;
                                            }
                                            if (bl) break block27;
                                            if (!autoDeafenModule.isEnabled()) return;
                                            autoDeafenModule = instance;
                                        }
                                        if (bl) break block28;
                                        if (autoDeafenModule.mc.field_1724 == null) return;
                                        autoDeafenModule = instance;
                                    }
                                    if (bl) break block29;
                                    if (autoDeafenModule.mc.field_1687 != null) break block30;
                                    return;
                                }
                                autoDeafenModule = instance;
                            }
                            n4 = autoDeafenModule.mc.field_1724.method_5628();
                            n5 = n;
                            if (bl) break block31;
                            if (n5 != n4) break block32;
                            n5 = n2;
                            break block33;
                        }
                        n5 = n2;
                    }
                    if (bl) break block33;
                    if (n5 != n4) break block34;
                    n5 = n;
                    break block33;
                }
                n5 = n3 = -1;
            }
            if (n3 == n4) return;
            Entity entity2 = AutoDeafenModule.instance.mc.field_1687.method_8469(n3);
            entity = entity2;
            if (bl) break block35;
            if (!(entity instanceof PlayerEntity)) return;
            entity = entity2;
        }
        PlayerEntity playerEntity = (PlayerEntity)entity;
        AutoDeafenModule.onFight(playerEntity);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void onFight(PlayerEntity playerEntity) {
        block7: {
            AutoDeafenModule autoDeafenModule;
            block6: {
                boolean bl = AmbienceModule.b;
                autoDeafenModule = instance;
                if (bl) break block6;
                if (autoDeafenModule == null) return;
                autoDeafenModule = instance;
            }
            if (autoDeafenModule.isEnabled() && playerEntity != AutoDeafenModule.instance.mc.field_1724) break block7;
            return;
        }
        AutoDeafenModule.instance.lastCombat = System.currentTimeMillis();
        AutoDeafenModule.instance.opponents.add(playerEntity.method_5667());
    }

    
    @Override
    public void onTick() {
        block41: {
            block39: {
                block40: {
                    block38: {
                        block35: {
                            block37: {
                                block36: {
                                    block34: {
                                        block32: {
                                            block33: {
                                                block30: {
                                                    block31: {
                                                        var1_1 = AmbienceModule.b;
                                                        v0 = this;
                                                        if (var1_1) break block30;
                                                        if (v0.mc.field_1724 != null) break block31;
                                                        return;
                                                    }
                                                    v0 = this;
                                                }
                                                TotemPops.tick(v0.mc);
                                                cfr_temp_0 = (double)(System.currentTimeMillis() - this.lastCombat) - this.undeafen.getValue() * 1000.0;
                                                v3 = cfr_temp_0 == 0.0 ? 0 : (cfr_temp_0 < 0.0 ? -1 : 1);
                                                if (var1_1) break block32;
                                                if (v3 >= 0) break block33;
                                                v3 = (double)true;
                                                break block32;
                                            }
                                            v3 = (double)false;
                                        }
                                        var2_2 = v3;
                                        if (var2_2 == false) {
                                            this.opponents.clear();
                                        }
                                        if (this.deafenedWith == null) break block34;
                                        v6 = var2_2;
                                        break block35;
                                    }
                                    v6 = var2_2;
                                    if (var1_1) break block36;
                                    if (v6 == false) break block37;
                                    v6 = (double)this.triggered();
                                }
                                if (var1_1) break block35;
                                if (v6 == false) break block37;
                                v6 = (double)true;
                                break block35;
                            }
                            v6 = (double)false;
                        }
                        var3_3 = v6;
                        v11 = var3_3;
                        v12 = this.deafenedWith != null;
                        if (v11 != v12 && this.mc.field_1755 == null) break block38;
                        return;
                    }
                    if (var3_3 == false) // goto
                    var4_4 = this.combo();
                    if (var1_1) break block39;
                    if (var4_4 != null) break block40;
                    return;
                }
                this.deafenedWith = var4_4;
                AutoDeafenModule.send(var4_4);
            }
            if (!var1_1) break block41;
lbl102:

            this.release();
        }
    }

    @Override
    public void onDisable() {
        this.release();
        this.opponents.clear();
    }

    private boolean triggered() {
        int n;
        block13: {
            AutoDeafenModule autoDeafenModule;
            boolean bl;
            block11: {
                block12: {
                    bl = AmbienceModule.b;
                    autoDeafenModule = this;
                    if (bl) break block11;
                    if (autoDeafenModule.trigger.getValue() != AutoDeafenModule$Trigger.COMBAT) break block12;
                    return true;
                }
                autoDeafenModule = this;
            }
            for (UUID uUID : autoDeafenModule.opponents) {
                block15: {
                    boolean bl2;
                    block14: {
                        n = TotemPops.get(uUID);
                        if (bl) break block13;
                        if (bl) break block14;
                        if (!((double)n >= this.pops.getValue())) break block15;
                        bl2 = true;
                    }
                    return bl2;
                }
                if (!bl) continue;
            }
            n = 0;
        }
        return n != 0;
    }

    private void release() {
        block4: {
            int[][] nArray;
            block5: {
                boolean bl = AmbienceModule.b;
                nArray = this.deafenedWith;
                if (bl) break block4;
                if (nArray != null) break block5;
                return;
            }
            this.deafenedWith = null;
            AutoDeafenModule.send(nArray);
        }
    }

    
    private int[][] combo() {
        int[][] nArrayArray;
        int n;
        int n2;
        int n3;
        boolean bl;
        block35: {
            int n4;
            block34: {
                int n5;
                block33: {
                    int n6;
                    block32: {
                        int n7 = this.key.getKey();
                        bl = AmbienceModule.b;
                        int n8 = n7;
                        if (!bl) {
                            if (n8 <= 8) {
                                return null;
                            }
                            n8 = GLFW.glfwGetKeyScancode((int)n7);
                        }
                        n6 = n3 = n8;
                        if (bl) break block32;
                        if (n6 <= 0) {
                            return null;
                        }
                        n5 = n3;
                        if (bl) break block33;
                        n6 = n5 & 0x100;
                    }
                    n5 = n6 != 0 ? 1 : 0;
                }
                int n9 = n2 = n5;
                if (!bl) {
                    n9 = n9 != 0 ? AutoDeafenModule.a(25814, 2824919773847235438L) | n3 & 0xFF : n3;
                }
                n4 = n = User32.INSTANCE.MapVirtualKeyEx(n9, 3, null);
                if (!bl) {
                    if (n4 == 0) {
                        return null;
                    }
                    n4 = this.modifier.getValue().ordinal();
                }
                if (bl) break block34;
                switch (n4) {
                    default: {
                        throw new MatchException(null, null);
                    }
                    case 0: {
                        nArrayArray = new int[][]{};
                        break block35;
                    }
                    case 1: {
                        int[][] nArrayArray2 = new int[1][];
                        nArrayArray = nArrayArray2;
                        nArrayArray2[0] = new int[]{162, 29, 0};
                        break block35;
                    }
                    case 2: {
                        int[][] nArrayArray3 = new int[1][];
                        nArrayArray = nArrayArray3;
                        nArrayArray3[0] = new int[]{160, 42, 0};
                        break block35;
                    }
                    case 3: {
                        int[][] nArrayArray4 = new int[1][];
                        nArrayArray = nArrayArray4;
                        nArrayArray4[0] = new int[]{164, 56, 0};
                        break block35;
                    }
                    case 4: {
                        int[][] nArrayArray5 = new int[2][];
                        nArrayArray5[0] = new int[]{162, 29, 0};
                        nArrayArray = nArrayArray5;
                        nArrayArray5[1] = new int[]{160, 42, 0};
                        break block35;
                    }
                    case 5: {
                        int[][] nArrayArray6 = new int[2][];
                        nArrayArray6[0] = new int[]{162, 29, 0};
                        nArrayArray = nArrayArray6;
                        nArrayArray6[1] = new int[]{164, 56, 0};
                        break block35;
                    }
                    case 6: {
                        n4 = 2;
                    }
                }
            }
            int[][] nArrayArray7 = new int[n4][];
            nArrayArray7[0] = new int[]{160, 42, 0};
            nArrayArray = nArrayArray7;
            nArrayArray7[1] = new int[]{164, 56, 0};
        }
        int[][] nArrayArray8 = nArrayArray;
        int[][] nArrayArray9 = new int[nArrayArray8.length + 1][];
        System.arraycopy(nArrayArray8, 0, nArrayArray9, 0, nArrayArray8.length);
        int n10 = nArrayArray8.length;
        int[] nArray = new int[3];
        nArray[0] = n;
        int[] nArray2 = nArray;
        int[] nArray3 = nArray;
        int n11 = 1;
        int n12 = n3;
        if (!bl) {
            nArray2[n11] = n12 & 0xFF;
            nArray2 = nArray3;
            nArray3 = nArray3;
            n11 = 2;
            n12 = n2 != 0 ? 1 : 0;
        }
        nArray2[n11] = n12;
        nArrayArray9[n10] = nArray3;
        return nArrayArray9;
    }

    private static void send(int[][] nArray) {
        new Thread(() -> AutoDeafenModule.lambda$send$3(nArray), b[5]).start();
    }

    
    private static synchronized void press(int[][] var0) {
        block30: {
            block26: {
                var2_1 = new boolean[var0.length];
                var1_2 = AmbienceModule.b;
                for (var3_3 = 0; var3_3 < var0.length - 1; ++var3_3) {
                    block28: {
                        block27: {
                            block33: {
                                block32: {
                                    if (var1_2) break block26;
                                    v0 = User32.INSTANCE.GetAsyncKeyState(var0[var3_3][0]);
                                    if (var1_2) break block28;
                                    break block32;
                                    catch (InterruptedException v1) {
                                        throw v1;
                                    }
                                }
                                if ((v0 & AutoDeafenModule.a(27009, 4808771566470276669L)) == 0) break block27;
                                break block33;
                                catch (InterruptedException v2) {
                                    throw v2;
                                }
                            }
                            v0 = 1;
                            break block28;
                        }
                        v0 = 0;
                    }
                    var2_1[var3_3] = v0;
                    if (!var1_2) continue;
                }
                var3_3 = 1;
            }
            for (var4_4 = 0; var4_4 < var0.length; ++var4_4) {
                block29: {
                    block35: {
                        block34: {
                            v4 = var2_1;
                            v5 = var4_4;
                            if (var1_2) // goto
                            v6 = v4[v5];
                            if (var1_2) break block29;
                            break block34;
                            catch (InterruptedException v7) {
                                throw v7;
                            }
                        }
                        if (v6 != 0) continue;
                        break block35;
                        catch (InterruptedException v8) {
                            throw v8;
                        }
                    }
                    v6 = var3_3 & AutoDeafenModule.key(var0[var4_4], false);
                }
                var3_3 = v6;
                if (!var1_2) continue;
            }
            try {
                Thread.sleep(AutoDeafenModule.f);
            }
            catch (InterruptedException var4_5) {
            }
            for (var4_4 = var0.length - 1; var4_4 >= 0; --var4_4) {
                block31: {
                    if (var1_2) break block30;
                    v4 = var2_1;
                    v5 = var4_4;
lbl70:

                    v11 = v4[v5];
                    if (var1_2) break block31;
                    if (v11 != 0) continue;
                    v11 = var3_3 & AutoDeafenModule.key(var0[var4_4], true);
                }
                var3_3 = v11;
                if (!var1_2) continue;
            }
            if (var3_3 == 0) {
                Aurora.LOG.warn(AutoDeafenModule.b[4]);
            }
        }
    }

    private static boolean key(int[] nArray, boolean bl) {
        boolean bl2;
        block14: {
            block15: {
                int n;
                boolean bl3;
                WinUser.INPUT iNPUT;
                block13: {
                    int n2;
                    block12: {
                        block10: {
                            block11: {
                                iNPUT = new WinUser.INPUT();
                                iNPUT.type = new WinDef.DWORD(1L);
                                bl3 = AmbienceModule.b;
                                iNPUT.input.setType(b[8]);
                                iNPUT.input.ki.wVk = new WinDef.WORD((long)nArray[0]);
                                iNPUT.input.ki.wScan = new WinDef.WORD((long)nArray[1]);
                                n = 0;
                                n2 = nArray[2];
                                if (bl3) break block10;
                                if (n2 == 0) break block11;
                                n |= 1;
                            }
                            n2 = bl ? 1 : 0;
                        }
                        if (bl3) break block12;
                        if (n2 == 0) break block13;
                        n2 = n | 2;
                    }
                    n = n2;
                }
                iNPUT.input.ki.dwFlags = new WinDef.DWORD((long)n);
                WinUser.INPUT[] iNPUTArray = (WinUser.INPUT[])iNPUT.toArray(1);
                bl2 = User32.INSTANCE.SendInput(new WinDef.DWORD(1L), iNPUTArray, iNPUT.size()).intValue();
                if (bl3) break block14;
                if (!bl2) break block15;
                bl2 = true;
                break block14;
            }
            bl2 = false;
        }
        return bl2;
    }

    private static void lambda$send$3(int[][] nArray) {
        AutoDeafenModule.press(nArray);
    }

    
    private void lambda$new$2() {
        Object object = AutoDeafenModule.class;
        synchronized (AutoDeafenModule.class) {
            block6: {
                Object object2;
                block5: {
                    boolean bl = AmbienceModule.b;
                    object = this.deafenedWith;
                    object2 = object;
                    if (bl) break block5;
                    if (object2 == null) break block6;
                    object2 = object;
                }
                AutoDeafenModule.press((int[][])object2);
            }
            return;
        }
    }

    private void lambda$new$1(ClientPlayNetworkHandler clientPlayNetworkHandler, MinecraftClient minecraftClient) {
        this.release();
    }

    private Boolean lambda$new$0() {
        boolean bl = this.trigger.getValue() == AutoDeafenModule$Trigger.POPS;
        return bl;
    }

    
    static {
        block30: {
            block29: {
                block28: {
                    block27: {
                        block26: {
                            var15 = new String[11];
                            var13_1 = 0;
                            var12_2 = "\n@$O\u001asD3\u001aW,N\u0018xE~K\"]]yX~v$[\u001eyD:\u0012)]\u000f\u007fX9\u0012+A\u001a~B-\u0012e\u007f\u0014xR1E>\b\u0012xZ'\u001b\u000b\u001fG9G]RS?T(F\u001a\u001fG?G\u000fw\u001b\u001fG9G9sW8W#\u0005\u000e~C*V\"_\u0013\"\u001fG9G]RS?T(FG6e;\\)a\u0013fC*\u0012:I\u000e6T2].C\u0018r\u0011\u001fG?G\u000fw\u001b\u001fG9G9sW8W#\u0004\u000e]=[\u0012\u000b\\)M\u001cpS0\u0012,N\tsD~\u001a>\u0001\u00025[";
                            var14_3 = "\n@$O\u001asD3\u001aW,N\u0018xE~K\"]]yX~v$[\u001eyD:\u0012)]\u000f\u007fX9\u0012+A\u001a~B-\u0012e\u007f\u0014xR1E>\b\u0012xZ'\u001b\u000b\u001fG9G]RS?T(F\u001a\u001fG?G\u000fw\u001b\u001fG9G9sW8W#\u0005\u000e~C*V\"_\u0013\"\u001fG9G]RS?T(FG6e;\\)a\u0013fC*\u0012:I\u000e6T2].C\u0018r\u0011\u001fG?G\u000fw\u001b\u001fG9G9sW8W#\u0004\u000e]=[\u0012\u000b\\)M\u001cpS0\u0012,N\tsD~\u001a>\u0001\u00025[".length();
                            var11_4 = 7;
                            var10_5 = -1;
lbl7:

                            while (true) {
                                v0 = ++var10_5;
                                v1 = var12_2.substring(v0, v0 + var11_4);
                                v2 = -1;
                                break block26;
                                break;
                            }
lbl12:

                            while (true) {
                                var15[var13_1++] = v3.intern();
                                if ((var10_5 += var11_4) < var14_3) {
                                    var11_4 = var12_2.charAt(var10_5);
                                    continue;
                                }
                                var12_2 = "\u001a[>K\u0012dR~y(Q\b\u0013])A\u001b\u007fS,";
                                var14_3 = "\u001a[>K\u0012dR~y(Q\b\u0013])A\u001b\u007fS,".length();
                                var11_4 = 11;
                                var10_5 = -1;
lbl21:

                                while (true) {
                                    v4 = ++var10_5;
                                    v1 = var12_2.substring(v4, v4 + var11_4);
                                    v2 = 0;
                                    break block26;
                                    break;
                                }
                                break;
                            }
lbl26:

                            while (true) {
                                var15[var13_1++] = v3.intern();
                                if ((var10_5 += var11_4) < var14_3) {
                                    var11_4 = var12_2.charAt(var10_5);
                                    continue;
                                }
                                break block27;
                                break;
                            }
                        }
                        v5 = v1.toCharArray();
                        v6 = v5;
                        v7 = v5.length;
                        var16_6 = 0;
                        do {
                            v6 = v6;
                            v8 = var16_6;
                            v9 = v6[v8];
                            switch (var16_6 % 7) {
                                case 0: {
                                    v10 = 94;
                                    break;
                                }
                                case 1: {
                                    v10 = 50;
                                    break;
                                }
                                case 2: {
                                    v10 = 77;
                                    break;
                                }
                                case 3: {
                                    v10 = 40;
                                    break;
                                }
                                case 4: {
                                    v10 = 125;
                                    break;
                                }
                                case 5: {
                                    v10 = 22;
                                    break;
                                }
                                default: {
                                    v10 = 54;
                                }
                            }
                            v6[v8] = (char)(v9 ^ v10);
                            ++var16_6;
lbl65:

                            v7 = v7;
                        } while (v7 > var16_6);
                        v3 = new String(v6);
                        switch (v2) {
                            default: {
                                continue;
                            }
                            case 0:
lbl73:

                            continue;
                        }
                    }
                    AutoDeafenModule.b = var15;
                    var2_7 = 1933376692417065787L;
                    var8_8 = new long[5];
                    var5_9 = 0;
                    var6_10 = "\u0016\u0081>\u00bc\u008f\u00ad8UiHt\u00d0X\n\u00e5DQt{D6p\u0011\u00a9";
                    var7_11 = "\u0016\u0081>\u00bc\u008f\u00ad8UiHt\u00d0X\n\u00e5DQt{D6p\u0011\u00a9".length();
                    var4_12 = 0;
                    while (true) {
                        var9_13 = var6_10.substring(var4_12, var4_12 += 8).getBytes("ISO-8859-1");
                        v11 = var8_8;
                        v12 = var5_9++;
                        v13 = ((long)var9_13[0] & 255L) << 56 | ((long)var9_13[1] & 255L) << 48 | ((long)var9_13[2] & 255L) << 40 | ((long)var9_13[3] & 255L) << 32 | ((long)var9_13[4] & 255L) << 24 | ((long)var9_13[5] & 255L) << 16 | ((long)var9_13[6] & 255L) << 8 | (long)var9_13[7] & 255L;
                        v14 = -1;
                        break block28;
                        break;
                    }
lbl89:

                    while (true) {
                        v11[v12] = v15;
                        if (var4_12 < var7_11) continue;
                        var6_10 = "?\u00ed\u00b8\u00ecR\u00e5C\u00c7\u00028\u0095\u008a\u00b9\u00fc\u00d5\u0006";
                        var7_11 = "?\u00ed\u00b8\u00ecR\u00e5C\u00c7\u00028\u0095\u008a\u00b9\u00fc\u00d5\u0006".length();
                        var4_12 = 0;
                        while (true) {
                            var9_13 = var6_10.substring(var4_12, var4_12 += 8).getBytes("ISO-8859-1");
                            v11 = var8_8;
                            v12 = var5_9++;
                            v13 = ((long)var9_13[0] & 255L) << 56 | ((long)var9_13[1] & 255L) << 48 | ((long)var9_13[2] & 255L) << 40 | ((long)var9_13[3] & 255L) << 32 | ((long)var9_13[4] & 255L) << 24 | ((long)var9_13[5] & 255L) << 16 | ((long)var9_13[6] & 255L) << 8 | (long)var9_13[7] & 255L;
                            v14 = 0;
                            break block28;
                            break;
                        }
                        break;
                    }
lbl102:

                    while (true) {
                        v11[v12] = v15;
                        if (var4_12 < var7_11) continue;
                        break block29;
                        break;
                    }
                }
                v15 = v13 ^ var2_7;
                switch (v14) {
                    default: {
                        continue;
                    }
                    case 0:
lbl113:

                    continue;
                }
            }
            AutoDeafenModule.d = var8_8;
            AutoDeafenModule.e = new Integer[5];
            AutoDeafenModule.VK_LCONTROL = AutoDeafenModule.a(11658, 6700917023095513648L);
            AutoDeafenModule.VK_LMENU = AutoDeafenModule.a(22882, 1683875085016045275L);
            AutoDeafenModule.VK_LSHIFT = AutoDeafenModule.a(32743, 8714509950554779740L);
            break block30;
lbl121:

            while (true) {
                continue;
                break;
            }
        }
        var0_14 = 1261449975691362442L;
        while (true)
        AutoDeafenModule.f = 1261449975691362452L ^ var0_14;
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4FB8;
        if (e[n2] == null) {
            AutoDeafenModule.e[n2] = (int)(d[n2] ^ l);
        }
        return e[n2];
    }
}
