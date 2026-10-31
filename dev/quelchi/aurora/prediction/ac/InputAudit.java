package dev.quelchi.aurora.prediction.ac;

import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.prediction.ac.AntiCheat;
import dev.quelchi.aurora.prediction.ac.CombatAudit;
import dev.quelchi.aurora.prediction.ac.Win32Input;
import java.util.Arrays;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RespawnAnchorBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.state.property.Property;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.glfw.GLFW;

public final class InputAudit {
    private static final long PRESS_MS;
    private static final long[] keyPress;
    private static final long[] mousePress;
    private static final boolean[] keyPosted;
    private static final boolean[] mousePosted;
    private static long lastSlotInput;
    private static long lastAnyInput;
    private static int vanillaDepth;
    private static BlockPos lastInteractPos;
    private static int replayDepth;
    private static final String NOT_A_CLICK;
    private static int pendingClicks;
    public static int a;
    private static final String[] b;
    private static final long[] c;
    private static final Long[] d;

    private InputAudit() {
    }

    static void reset() {
        Arrays.fill(keyPress, 0L);
        Arrays.fill(mousePress, 0L);
        Arrays.fill(keyPosted, false);
        Arrays.fill(mousePosted, false);
        lastSlotInput = 0L;
        lastAnyInput = 0L;
        pendingClicks = 0;
        vanillaDepth = 0;
        replayDepth = 0;
        lastInteractPos = null;
    }

    
    public static void onMouse(long var0, int var2_1, int var3_2) {
        block81: {
            block80: {
                block77: {
                    block78: {
                        block79: {
                            block75: {
                                block76: {
                                    block73: {
                                        block74: {
                                            block71: {
                                                block72: {
                                                    block70: {
                                                        block69: {
                                                            block68: {
                                                                block66: {
                                                                    block67: {
                                                                        block61: {
                                                                            block62: {
                                                                                block64: {
                                                                                    block65: {
                                                                                        block63: {
                                                                                            block60: {
                                                                                                block59: {
                                                                                                    var4_3 = InputAudit.a;
                                                                                                    v0 = var2_1;
                                                                                                    if (var4_3 != 0) break block59;
                                                                                                    if (v0 >= 0) {
                                                                                                    }
                                                                                                    v0 = var2_1;
                                                                                                }
                                                                                                if (v0 <= 7) break block60;
lbl14:

                                                                                                return;
                                                                                            }
                                                                                            var5_4 = System.currentTimeMillis();
                                                                                            v3 = var3_2;
                                                                                            if (var4_3 != 0) break block61;
                                                                                            if (v3 != 0) break block62;
                                                                                            v5 = InputAudit.mousePosted[var2_1];
                                                                                            if (var4_3 != 0) break block63;
                                                                                            if (v5) break block64;
                                                                                            v5 = InputAudit.audited();
                                                                                        }
                                                                                        if (var4_3 != 0) break block65;
                                                                                        if (v5) {
                                                                                        }
                                                                                        v5 = InputAudit.posted();
                                                                                    }
                                                                                    if (v5) break block64;
lbl50:

                                                                                    InputAudit.lastAnyInput = var5_4;
                                                                                }
                                                                                return;
                                                                            }
                                                                            v3 = var3_2;
                                                                        }
                                                                        if (var4_3 != 0) break block66;
                                                                        if (v3 == 1) break block67;
                                                                        return;
                                                                    }
                                                                    v3 = (int)InputAudit.audited();
                                                                }
                                                                var7_5 = v3;
                                                                v12 = var7_5;
                                                                if (var4_3 != 0) break block68;
                                                                if (v12 == 0) break block69;
                                                                v12 = (int)InputAudit.posted();
                                                            }
                                                            if (var4_3 != 0) break block70;
                                                            if (v12 == 0) break block69;
                                                            v12 = 1;
                                                            break block70;
                                                        }
                                                        v12 = 0;
                                                    }
                                                    var8_6 = v12;
                                                    InputAudit.mousePosted[var2_1] = var8_6;
                                                    InputAudit.mousePress[var2_1] = var5_4;
                                                    v15 = var8_6;
                                                    if (var4_3 != 0) break block71;
                                                    if (v15 != 0) break block72;
                                                    InputAudit.lastAnyInput = var5_4;
                                                    v15 = var2_1;
                                                    if (var4_3 != 0) break block71;
                                                    if (v15 <= 1) break block72;
                                                    InputAudit.lastSlotInput = var5_4;
                                                }
                                                v15 = var7_5;
                                            }
                                            if (var4_3 != 0) break block73;
                                            if (v15 != 0) break block74;
                                            return;
                                        }
                                        v15 = GLFW.glfwGetMouseButton((long)var0, (int)var2_1);
                                    }
                                    if (var4_3 != 0) break block75;
                                    if (v15 == 1) break block76;
                                    AntiCheat.flag(InputAudit.b[25], InputAudit.b[17] + var2_1);
                                    if (var4_3 == 0) break block77;
                                }
                                v15 = (int)InputAudit.injected();
                            }
                            if (var4_3 != 0) break block78;
                            if (v15 == 0) break block79;
                            AntiCheat.flag(InputAudit.b[68], InputAudit.b[2] + var2_1);
                            if (var4_3 == 0) break block77;
                        }
                        v15 = var8_6;
                    }
                    if (v15 != 0) {
                        AntiCheat.flag(InputAudit.b[25], InputAudit.b[47] + var2_1);
                    }
                }
                var9_7 = MinecraftClient.method_1551();
                v28 = var9_7;
                if (var4_3 != 0) break block80;
                if (v28.field_1755 != null) break block81;
                v28 = var9_7;
            }
            if (InputAudit.boundTo(v28.field_1690.field_1886, var2_1, InputUtil.Type.field_1672)) {
                CombatAudit.onAttackPress(var5_4);
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void onKey(long l, int n, int n2, int n3) {
        int n4;
        block94: {
            int n5;
            block95: {
                int n6;
                block92: {
                    int n7;
                    block93: {
                        block90: {
                            block91: {
                                block87: {
                                    int n8;
                                    block88: {
                                        long l2;
                                        block89: {
                                            int n9;
                                            block86: {
                                                block85: {
                                                    block84: {
                                                        block81: {
                                                            block82: {
                                                                int n10;
                                                                block83: {
                                                                    block79: {
                                                                        block80: {
                                                                            int n11;
                                                                            block78: {
                                                                                block76: {
                                                                                    block77: {
                                                                                        block75: {
                                                                                            int n12;
                                                                                            block74: {
                                                                                                block73: {
                                                                                                    block72: {
                                                                                                        n6 = a;
                                                                                                        n12 = n;
                                                                                                        if (n6 != 0) break block72;
                                                                                                        if (n12 < 340) break block73;
                                                                                                        n12 = n;
                                                                                                    }
                                                                                                    if (n6 != 0) break block74;
                                                                                                    if (n12 > 347) break block73;
                                                                                                    n12 = 1;
                                                                                                    break block74;
                                                                                                }
                                                                                                n12 = 0;
                                                                                            }
                                                                                            n7 = n12;
                                                                                            n8 = InputAudit.audited();
                                                                                            n11 = n8;
                                                                                            if (n6 != 0) break block75;
                                                                                            if (n11 == 0) break block76;
                                                                                            n11 = n7;
                                                                                        }
                                                                                        if (n6 != 0) break block77;
                                                                                        if (n11 != 0) break block76;
                                                                                        n11 = InputAudit.posted() ? 1 : 0;
                                                                                    }
                                                                                    if (n6 != 0) break block78;
                                                                                    if (n11 == 0) break block76;
                                                                                    n11 = 1;
                                                                                    break block78;
                                                                                }
                                                                                n11 = 0;
                                                                            }
                                                                            n5 = n11;
                                                                            n9 = n3;
                                                                            if (n6 != 0) break block79;
                                                                            if (n9 != 2) break block80;
                                                                            n9 = n5;
                                                                            if (n6 != 0) break block79;
                                                                            if (n9 != 0) break block80;
                                                                            lastAnyInput = System.currentTimeMillis();
                                                                        }
                                                                        n9 = n;
                                                                    }
                                                                    if (n6 != 0) break block81;
                                                                    if (n9 != -1) break block82;
                                                                    n10 = n3;
                                                                    if (n6 != 0) break block83;
                                                                    if (n10 != 1) return;
                                                                    n10 = n5;
                                                                }
                                                                if (n10 != 0) return;
                                                                long l3 = System.currentTimeMillis();
                                                                if (InputAudit.slotKey(n2, InputUtil.Type.field_1671)) {
                                                                    lastSlotInput = l3;
                                                                }
                                                                lastAnyInput = l3;
                                                                return;
                                                            }
                                                            n9 = n3;
                                                        }
                                                        if (n6 != 0) break block84;
                                                        if (n9 != 1) return;
                                                        n9 = n;
                                                    }
                                                    if (n6 != 0) break block85;
                                                    if (n9 < 0) return;
                                                    n9 = n;
                                                }
                                                if (n6 != 0) break block86;
                                                if (n9 > 348) return;
                                                n9 = n;
                                            }
                                            if (n9 == 283) {
                                                return;
                                            }
                                            l2 = System.currentTimeMillis();
                                            InputAudit.keyPosted[n] = n5;
                                            InputAudit.keyPress[n] = l2;
                                            n4 = n5;
                                            if (n6 != 0) break block87;
                                            if (n4 != 0) break block88;
                                            if (!InputAudit.slotKey(n, InputUtil.Type.field_1668)) break block89;
                                            lastSlotInput = l2;
                                        }
                                        lastAnyInput = l2;
                                    }
                                    n4 = n8;
                                }
                                if (n6 != 0) break block90;
                                if (n4 != 0) break block91;
                                return;
                            }
                            n4 = GLFW.glfwGetKey((long)l, (int)n);
                        }
                        if (n6 != 0) break block92;
                        if (n4 == 1) break block93;
                        AntiCheat.flag(b[58], b[63] + n);
                        if (n6 == 0) return;
                    }
                    n4 = n7;
                }
                if (n6 != 0) break block94;
                if (n4 != 0) break block95;
                n4 = InputAudit.injected() ? 1 : 0;
                if (n6 != 0) break block94;
                if (n4 == 0) break block95;
                AntiCheat.flag(b[30], b[60] + n);
                if (n6 == 0) return;
            }
            n4 = n5;
        }
        if (n4 == 0) return;
        AntiCheat.flag(b[25], b[57] + n);
        return;
    }

    private static boolean posted() {
        int n;
        block12: {
            block11: {
                block13: {
                    int n2;
                    int n3;
                    block10: {
                        int n4 = Win32Input.source();
                        n3 = a;
                        n2 = n4 & 0xFF;
                        n = n4;
                        if (n3 != 0) break block10;
                        if (n < 0) break block11;
                        n = n2;
                    }
                    if (n3 != 0) break block12;
                    if (n == 0) break block13;
                    n = n2;
                    if (n3 != 0) break block12;
                    if (n != 4) break block11;
                }
                n = 1;
                break block12;
            }
            n = 0;
        }
        return n != 0;
    }

    private static boolean injected() {
        int n;
        block10: {
            block9: {
                int n2;
                block8: {
                    int n3 = Win32Input.source();
                    int n4 = a;
                    n2 = n3;
                    if (n4 != 0) break block8;
                    if (n2 < 0) break block9;
                    n = n3;
                    if (n4 != 0) break block10;
                    n2 = n & 0xFF;
                }
                if (n2 != 2) break block9;
                n = 1;
                break block10;
            }
            n = 0;
        }
        return n != 0;
    }

    public static void onScroll() {
        lastSlotInput = System.currentTimeMillis();
    }

    static void onServerSlot() {
        lastSlotInput = System.currentTimeMillis();
    }

    
    public static void enterVanilla(KeyBinding keyBinding) {
        block24: {
            MinecraftClient minecraftClient;
            String string;
            block27: {
                MinecraftClient minecraftClient2;
                block26: {
                    HitResult hitResult;
                    int n;
                    block25: {
                        HitResult hitResult2;
                        block21: {
                            block22: {
                                block23: {
                                    block20: {
                                        block19: {
                                            int n2;
                                            block18: {
                                                n = a;
                                                n2 = vanillaDepth + 1;
                                                if (n != 0) break block18;
                                                vanillaDepth = n2;
                                                if (!InputAudit.inGame()) break block19;
                                                n2 = InputAudit.physical(keyBinding) ? 1 : 0;
                                            }
                                            if (n2 == 0) break block20;
                                        }
                                        return;
                                    }
                                    minecraftClient2 = MinecraftClient.method_1551();
                                    if (InputAudit.posted(keyBinding)) {
                                        v1 = b[5];
                                    } else {
                                        String[] stringArray = b;
                                        v1 = string = stringArray[50];
                                    }
                                    if (keyBinding != minecraftClient2.field_1690.field_1886) break block21;
                                    HitResult hitResult3 = minecraftClient2.field_1765;
                                    if (n != 0) break block22;
                                    if (!(hitResult3 instanceof EntityHitResult)) break block23;
                                    hitResult2 = (EntityHitResult)hitResult3;
                                    InputAudit.attack(hitResult2.method_17782(), string);
                                    if (n == 0) break block24;
                                }
                                AntiCheat.flag(b[83], string);
                            }
                            if (n == 0) break block24;
                        }
                        hitResult = hitResult2 = minecraftClient2.field_1765;
                        if (n != 0) break block25;
                        if (!(hitResult instanceof BlockHitResult)) break block26;
                        hitResult = hitResult2;
                    }
                    BlockHitResult blockHitResult = (BlockHitResult)hitResult;
                    minecraftClient = minecraftClient2;
                    if (n != 0) break block27;
                    if (minecraftClient.field_1765.method_17783() != HitResult.Type.field_1332) break block26;
                    InputAudit.block(minecraftClient2.field_1724.method_6047(), minecraftClient2.field_1687.method_8320(blockHitResult.method_17777()), string);
                    if (n == 0) break block24;
                }
                minecraftClient = minecraftClient2;
            }
            InputAudit.item(minecraftClient.field_1724.method_6047(), string);
        }
    }

    public static void exitVanilla() {
        block5: {
            int n;
            block4: {
                int n2 = a;
                n = vanillaDepth;
                if (n2 != 0) break block4;
                if (n <= 0) break block5;
                n = vanillaDepth - 1;
            }
            vanillaDepth = n;
        }
    }

    public static boolean inVanilla() {
        boolean bl;
        block2: {
            block3: {
                int n = a;
                bl = vanillaDepth;
                if (n != 0) break block2;
                if (bl <= false) break block3;
                bl = true;
                break block2;
            }
            bl = false;
        }
        return bl;
    }

    public static void enterReplay() {
        ++vanillaDepth;
        ++replayDepth;
    }

    public static void exitReplay() {
        block5: {
            int n;
            block4: {
                InputAudit.exitVanilla();
                int n2 = a;
                n = replayDepth;
                if (n2 != 0) break block4;
                if (n <= 0) break block5;
                n = replayDepth - 1;
            }
            replayDepth = n;
        }
    }

    static boolean replaying() {
        boolean bl;
        block2: {
            block3: {
                int n = a;
                bl = replayDepth;
                if (n != 0) break block2;
                if (bl <= false) break block3;
                bl = true;
                break block2;
            }
            bl = false;
        }
        return bl;
    }

    
    static void onBlockInteract(PlayerInteractBlockC2SPacket playerInteractBlockC2SPacket) {
        int n;
        BlockState blockState;
        MinecraftClient minecraftClient;
        block35: {
            int n2;
            block34: {
                boolean bl;
                block33: {
                    block32: {
                        long l;
                        block26: {
                            ItemStack itemStack;
                            block27: {
                                block29: {
                                    block31: {
                                        int n3;
                                        block30: {
                                            int n4;
                                            block28: {
                                                minecraftClient = MinecraftClient.method_1551();
                                                BlockPos blockPos = playerInteractBlockC2SPacket.method_12543().method_17777();
                                                bl = blockPos.equals((Object)lastInteractPos);
                                                lastInteractPos = blockPos;
                                                blockState = minecraftClient.field_1687.method_8320(blockPos);
                                                itemStack = minecraftClient.field_1724.method_5998(playerInteractBlockC2SPacket.method_12546());
                                                l = InputAudit.lastPress(minecraftClient.field_1690.field_1904);
                                                n = a;
                                                n2 = blockState.method_27852(Blocks.field_23152);
                                                if (n != 0) break block26;
                                                if (n2 == 0) break block27;
                                                n3 = n4 = ((Integer)blockState.method_11654((Property)RespawnAnchorBlock.field_23153)).intValue();
                                                if (n != 0) break block28;
                                                if (n3 <= 0) break block29;
                                                n3 = itemStack.method_31574(Items.field_8801) ? 1 : 0;
                                            }
                                            if (n != 0) break block30;
                                            if (n3 == 0) break block31;
                                            n3 = n4;
                                        }
                                        if (n3 < 4) break block29;
                                    }
                                    CombatAudit.onAnchorDetonated(l);
                                }
                                if (n == 0) break block32;
                            }
                            n2 = itemStack.method_31574(Items.field_23141);
                        }
                        if (n != 0) break block33;
                        if (n2 != 0) {
                            CombatAudit.onAnchorPlaced(l);
                        }
                    }
                    n2 = vanillaDepth;
                }
                if (n == 0) {
                    if (n2 > 0) {
                        return;
                    }
                    n2 = bl ? 1 : 0;
                }
                if (n != 0) break block34;
                if (n2 == 0) break block35;
                n2 = blockState.method_27852(Blocks.field_23152) ? 1 : 0;
            }
            if (n2 != 0) {
                AntiCheat.flag(b[9], b[46]);
                return;
            }
        }
        InputAudit.block(minecraftClient.field_1724.method_5998(playerInteractBlockC2SPacket.method_12546()), blockState, b[43]);
        if (!Module.a) return;
        a = ++n;
    }

    static void onItemUse(PlayerInteractItemC2SPacket playerInteractItemC2SPacket) {
        if (vanillaDepth > 0) {
            return;
        }
        InputAudit.item(MinecraftClient.method_1551().field_1724.method_5998(playerInteractItemC2SPacket.method_12551()), b[43]);
    }

    static void onEntityInteract() {
        if (vanillaDepth == 0) {
            AntiCheat.flag(b[65], b[40]);
        }
    }

    static void onAttack(Entity entity) {
        if (vanillaDepth == 0) {
            InputAudit.attack(entity, b[12]);
        }
    }

    
    static void onSlotPacket(int var0, int var1_1) {
        block51: {
            block59: {
                block57: {
                    block58: {
                        block55: {
                            block56: {
                                block53: {
                                    block54: {
                                        block52: {
                                            block50: {
                                                block48: {
                                                    block49: {
                                                        block47: {
                                                            block45: {
                                                                block46: {
                                                                    var2_2 = InputAudit.a;
                                                                    if (System.currentTimeMillis() - InputAudit.lastSlotInput <= InputAudit.a(16155, 2159404089388076658L)) {
                                                                        return;
                                                                    }
                                                                    var3_3 = MinecraftClient.method_1551().field_1724.method_31548();
                                                                    var4_4 = var3_3.method_5438(var1_1);
                                                                    v1 = var0;
                                                                    if (var2_2 != 0) break block45;
                                                                    if (v1 >= 0) break block46;
                                                                    v3 = "";
                                                                    break block47;
                                                                }
                                                                v1 = var0 + 1;
                                                            }
                                                            var9_5 = InputAudit.b;
                                                            v3 = var9_5[78] + v1 + var9_5[3] + InputAudit.name(var3_3.method_5438(var0)) + var9_5[55];
                                                        }
                                                        var6_6 = InputAudit.name(var4_4);
                                                        var7_7 = var1_1 + 1;
                                                        var8_8 = v3;
                                                        var9_5 = InputAudit.b;
                                                        var5_9 = var9_5[53] + var8_8 + var9_5[66] + var7_7 + var9_5[81] + var6_6 + var9_5[41];
                                                        if (var2_2 != 0) break block48;
                                                        if (var4_4.method_31574(Items.field_23141)) break block49;
                                                        v6 = var4_4;
                                                        v7 = Items.field_8801;
                                                        if (var2_2 != 0) break block50;
                                                        if (v6.method_31574(v7)) {
                                                        }
                                                    }
                                                    AntiCheat.flag(InputAudit.b[35], var5_9);
                                                }
                                                if (var2_2 == 0) break block51;
lbl63:

                                                v6 = var4_4;
                                                if (var2_2 != 0) break block52;
                                                v7 = Items.field_8301;
                                            }
                                            if (!v6.method_31574(v7)) {
                                                v6 = var4_4;
                                            }
                                        }
                                        v14 = Items.field_8281;
                                        if (var2_2 != 0) break block53;
                                        if (!v6.method_31574(v14)) break block54;
lbl91:

                                        AntiCheat.flag(InputAudit.b[51], var5_9);
                                        if (var2_2 == 0) break block51;
                                    }
                                    v6 = var4_4;
                                    if (var2_2 != 0) break block55;
                                    v14 = Items.field_8288;
                                }
                                if (!v6.method_31574(v14)) break block56;
                                AntiCheat.flag(InputAudit.b[18], var5_9);
                                if (var2_2 == 0) break block51;
                            }
                            v6 = var4_4;
                        }
                        if (var2_2 != 0) break block57;
                        if (!v6.method_31574(Items.field_8634)) break block58;
                        AntiCheat.flag(InputAudit.b[11], var5_9);
                        if (var2_2 == 0) break block51;
                    }
                    v6 = var4_4;
                }
                if (!InputAudit.isWeapon(v6)) break block59;
                AntiCheat.flag(InputAudit.b[1], var5_9);
                if (var2_2 == 0) break block51;
            }
            AntiCheat.flag(InputAudit.b[67], var5_9);
        }
    }

    
    private static void block(ItemStack var0, BlockState var1_1, String var2_2) {
        block44: {
            block51: {
                block52: {
                    block49: {
                        block50: {
                            block47: {
                                block48: {
                                    block45: {
                                        block46: {
                                            block39: {
                                                block53: {
                                                    block43: {
                                                        block42: {
                                                            block40: {
                                                                block41: {
                                                                    var15_3 = InputAudit.b;
                                                                    var4_4 = var15_3[45] + InputAudit.name(var1_1);
                                                                    var3_5 = InputAudit.a;
                                                                    v0 = var1_1.method_27852(Blocks.field_23152);
                                                                    if (var3_5 != 0) break block39;
                                                                    if (v0) {
                                                                    }
                                                                    var5_6 = (Integer)var1_1.method_11654((Property)RespawnAnchorBlock.field_23153);
                                                                    var6_7 = 4;
                                                                    v2 = var0.method_31574(Items.field_8801);
                                                                    if (var3_5 != 0) break block40;
                                                                    if (v2 == 0) break block41;
                                                                    v2 = var5_6;
                                                                    if (var3_5 != 0) break block40;
                                                                    if (v2 >= var6_7) break block41;
                                                                    var8_8 = var6_7;
                                                                    var9_9 = var5_6 + 1;
                                                                    var10_10 = var6_7;
                                                                    var11_11 = var5_6;
                                                                    var15_3 = InputAudit.b;
                                                                    v6 = var15_3[29] + var11_11 + "/" + var10_10 + var15_3[82] + var9_9 + "/" + var8_8 + var15_3[39];
                                                                    break block53;
                                                                }
                                                                v2 = var5_6;
                                                            }
                                                            if (var3_5 != 0) break block42;
                                                            if (v2 <= 0) break block43;
                                                            v2 = var5_6;
                                                        }
                                                        var12_12 = InputAudit.name(var0);
                                                        var13_13 = var6_7;
                                                        var14_14 = v2;
                                                        var15_3 = InputAudit.b;
                                                        v6 = var15_3[48] + var14_14 + "/" + var13_13 + var15_3[23] + var12_12;
                                                        break block53;
                                                    }
                                                    var15_3 = InputAudit.b;
                                                    v6 = var15_3[14] + InputAudit.name(var0);
                                                }
                                                var7_15 = v6;
                                                AntiCheat.flag(InputAudit.b[69], var7_15 + InputAudit.b[32] + var2_2);
                                                if (var3_5 == 0) break block44;
lbl65:

                                                v8 = var0;
                                                v9 = Items.field_23141;
                                                if (var3_5 != 0) break block45;
                                                v0 = v8.method_31574(v9);
                                            }
                                            if (!v0) break block46;
                                            AntiCheat.flag(InputAudit.b[35], InputAudit.b[71] + var4_4 + InputAudit.b[32] + var2_2);
                                            if (var3_5 == 0) break block44;
                                        }
                                        v8 = var0;
                                        if (var3_5 != 0) break block47;
                                        v9 = Items.field_8301;
                                    }
                                    if (!v8.method_31574(v9)) break block48;
                                    AntiCheat.flag(InputAudit.b[49], InputAudit.b[10] + var4_4 + InputAudit.b[32] + var2_2);
                                    if (var3_5 == 0) break block44;
                                }
                                v8 = var0;
                            }
                            if (var3_5 != 0) break block49;
                            if (v8.method_31574(Items.field_8801)) {
                            }
                            break block50;
                            AntiCheat.flag(InputAudit.b[79], InputAudit.b[6] + var4_4 + InputAudit.b[32] + var2_2);
                            if (var3_5 == 0) break block44;
                        }
                        v8 = var0;
                    }
                    if (var3_5 != 0) break block51;
                    if (v8.method_31574(Items.field_8281)) {
                    }
                    break block52;
                    AntiCheat.flag(InputAudit.b[51], InputAudit.b[27] + var4_4 + InputAudit.b[32] + var2_2);
                    if (var3_5 == 0) break block44;
                }
                v8 = var0;
            }
            InputAudit.item(v8, var2_2);
        }
    }

    
    private static void item(ItemStack itemStack, String string) {
        String string2;
        block19: {
            int n;
            block18: {
                Item item;
                ItemStack itemStack2;
                block17: {
                    String[] stringArray = b;
                    string2 = stringArray[24] + InputAudit.name(itemStack) + stringArray[26] + string;
                    n = a;
                    itemStack2 = itemStack;
                    if (n == 0) {
                        if (itemStack2.method_31574(Items.field_8634)) {
                            AntiCheat.flag(b[38], string2);
                            if (n == 0) return;
                        }
                        itemStack2 = itemStack;
                    }
                    if (n == 0) {
                        if (itemStack2.method_57826(DataComponentTypes.field_50075)) {
                            AntiCheat.flag(b[34], string2);
                            if (n == 0) return;
                        }
                        itemStack2 = itemStack;
                    }
                    item = Items.field_8436;
                    if (n != 0) break block17;
                    if (itemStack2.method_31574(item)) break block18;
                    itemStack2 = itemStack;
                    item = Items.field_8150;
                }
                if (!itemStack2.method_31574(item)) break block19;
            }
            AntiCheat.flag(b[44], string2);
            if (n == 0) return;
        }
        AntiCheat.flag(b[22], string2);
    }

    private static void attack(Entity entity, String string) {
        block20: {
            String string2;
            String[] stringArray;
            block22: {
                Entity entity2;
                int n;
                block21: {
                    block18: {
                        block19: {
                            n = a;
                            if (entity == null) {
                                AntiCheat.flag(b[52], b[70] + string);
                                return;
                            }
                            stringArray = b;
                            string2 = String.format(stringArray[36], Math.sqrt(MinecraftClient.method_1551().field_1724.method_5858(entity)));
                            entity2 = entity;
                            if (n != 0) break block18;
                            if (entity2 instanceof EndCrystalEntity) {
                            }
                            break block19;
                            AntiCheat.flag(b[51], b[76] + string2 + b[32] + string);
                            if (n == 0) break block20;
                        }
                        entity2 = entity;
                    }
                    if (n != 0) break block21;
                    if (!(entity2 instanceof PlayerEntity)) break block22;
                    entity2 = entity;
                }
                PlayerEntity playerEntity = (PlayerEntity)entity2;
                stringArray = b;
                String string3 = string;
                String string4 = string2;
                String string5 = playerEntity.method_5477().getString();
                AntiCheat.flag(stringArray[33], stringArray[21] + string5 + string4 + stringArray[32] + string3);
                if (n == 0) break block20;
            }
            stringArray = b;
            String string6 = string;
            String string7 = string2;
            String string8 = Registries.field_41177.method_10221((Object)entity.method_5864()).method_12832();
            AntiCheat.flag(stringArray[83], stringArray[8] + string8 + string7 + stringArray[32] + string6);
        }
    }

    private static boolean isWeapon(ItemStack itemStack) {
        boolean bl;
        block10: {
            block12: {
                block11: {
                    int n = a;
                    bl = itemStack.method_31574(Items.field_49814);
                    if (n != 0) break block10;
                    if (bl) break block11;
                    bl = itemStack.method_31573(ItemTags.field_42611);
                    if (n != 0) break block10;
                    if (bl) break block11;
                    bl = itemStack.method_31573(ItemTags.field_42612);
                    if (n != 0) break block10;
                    if (!bl) break block12;
                }
                bl = true;
                break block10;
            }
            bl = false;
        }
        return bl;
    }

    private static String name(ItemStack itemStack) {
        String string = itemStack.method_7960() ? b[31] : Registries.field_41178.method_10221((Object)itemStack.method_7909()).method_12832();
        return string;
    }

    private static String name(BlockState blockState) {
        return Registries.field_41175.method_10221((Object)blockState.method_26204()).method_12832();
    }

    /*
     * Exception decompiling
     */
    public static void onClickSlot(int var0, int var1_1, SlotActionType var2_2, PlayerEntity var3_3) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [14[TRYBLOCK]], but top level block is 26[SWITCH]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:845)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1042)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:929)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:73)
         *     at org.benf.cfr.reader.Main.main(Main.java:49)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    
    static void onClickPacket() {
        block6: {
            int n;
            int n2;
            block5: {
                n2 = a;
                n = pendingClicks;
                if (n2 != 0) break block5;
                if (n <= 0) break block6;
                n = pendingClicks - 1;
            }
            pendingClicks = n;
            if (n2 == 0) return;
        }
        AntiCheat.flag(b[77], b[7]);
    }

    static void onOffhandSwap() {
        boolean bl;
        MinecraftClient minecraftClient;
        block14: {
            block16: {
                block15: {
                    MinecraftClient minecraftClient2;
                    int n;
                    block12: {
                        block13: {
                            minecraftClient = MinecraftClient.method_1551();
                            n = a;
                            minecraftClient2 = minecraftClient;
                            if (n != 0) break block12;
                            if (!InputAudit.physical(minecraftClient2.field_1690.field_1831)) break block13;
                            return;
                        }
                        minecraftClient2 = minecraftClient;
                    }
                    bl = minecraftClient2.field_1724.method_6047().method_31574(Items.field_8288);
                    if (n != 0) break block14;
                    if (bl) break block15;
                    bl = minecraftClient.field_1724.method_6079().method_31574(Items.field_8288);
                    if (n != 0) break block14;
                    if (!bl) break block16;
                }
                bl = true;
                break block14;
            }
            bl = false;
        }
        boolean bl2 = bl;
        String[] stringArray = b;
        String string = stringArray[72] + InputAudit.name(minecraftClient.field_1724.method_6047()) + stringArray[19] + InputAudit.name(minecraftClient.field_1724.method_6079()) + stringArray[42];
        String string2 = bl2 ? stringArray[15] : b[0];
        AntiCheat.flag(string2, string);
    }

    private static boolean anyButtonDown() {
        int n;
        block7: {
            long l = MinecraftClient.method_1551().method_22683().method_4490();
            int n2 = a;
            for (int i = 0; i <= 7; ++i) {
                boolean bl;
                block8: {
                    n = GLFW.glfwGetMouseButton((long)l, (int)i);
                    if (n2 != 0) break block7;
                    if (n2 != 0) break block8;
                    if (n != 1) continue;
                    bl = true;
                }
                return bl;
            }
            n = 0;
        }
        return n != 0;
    }

    static void endTick() {
        lastInteractPos = null;
    }

    
    private static boolean slotKey(int var0, InputUtil.Type var1_1) {
        block22: {
            var3_2 = MinecraftClient.method_1551().field_1690;
            var2_3 = InputAudit.a;
            var4_4 = var3_2.field_1852;
            var5_5 = var4_4.length;
            var6_6 = 0;
            while (var6_6 < var5_5) {
                block19: {
                    block20: {
                        var7_7 = var4_4[var6_6];
                        if (var2_3 != 0) break block19;
                        v0 = var7_7;
                        v1 = var0;
                        v2 = var1_1;
                        if (var2_3 == 0) {
                        }
                        if (!InputAudit.boundTo(v0, v1, v2)) break block20;
                        return true;
                    }
                    ++var6_6;
                }
                if (var2_3 == 0) continue;
            }
            var4_4 = var3_2.field_1839;
            var5_5 = var4_4.length;
            var6_6 = 0;
            while (var6_6 < var5_5) {
                block21: {
                    block23: {
                        block24: {
                            var7_7 = var4_4[var6_6];
                            if (var2_3 != 0) break block21;
                            v0 = var7_7;
                            v1 = var0;
                            v2 = var1_1;
lbl48:

                            v7 = InputAudit.boundTo(v0, v1, v2);
                            if (var2_3 != 0) break block22;
                            if (!v7) break block23;
                            v9 = var7_7.method_1431().indexOf(46);
                            if (var2_3 != 0) break block24;
                            if (v9 != var7_7.method_1431().lastIndexOf(46)) break block23;
                            v9 = 0;
                        }
                        return (boolean)v9;
                    }
                    ++var6_6;
                }
                if (var2_3 == 0) continue;
            }
            v7 = true;
        }
        return v7;
    }

    private static boolean boundTo(KeyBinding keyBinding, int n, InputUtil.Type type) {
        int n2;
        block10: {
            block9: {
                InputUtil.Key key;
                int n3;
                block8: {
                    InputUtil.Key key2 = InputUtil.method_15981((String)keyBinding.method_1428());
                    n3 = a;
                    key = key2;
                    if (n3 != 0) break block8;
                    if (key.method_1442() != type) break block9;
                    key = key2;
                }
                n2 = key.method_1444();
                if (n3 != 0) break block10;
                if (n2 != n) break block9;
                n2 = 1;
                break block10;
            }
            n2 = 0;
        }
        return n2 != 0;
    }

    private static long lastPress(KeyBinding keyBinding) {
        block19: {
            long l;
            block22: {
                block21: {
                    int n;
                    int n2;
                    block20: {
                        InputUtil.Type type;
                        int n3;
                        block14: {
                            InputUtil.Key key;
                            block15: {
                                long l2;
                                block18: {
                                    block17: {
                                        int n4;
                                        block16: {
                                            key = InputUtil.method_15981((String)keyBinding.method_1428());
                                            n2 = key.method_1444();
                                            n3 = a;
                                            type = key.method_1442();
                                            if (n3 != 0) break block14;
                                            if (type != InputUtil.Type.field_1672) break block15;
                                            n4 = n2;
                                            if (n3 != 0) break block16;
                                            if (n4 < 0) break block17;
                                            n4 = n2;
                                        }
                                        if (n4 > 7) break block17;
                                        l2 = mousePress[n2];
                                        break block18;
                                    }
                                    l2 = 0L;
                                }
                                return l2;
                            }
                            type = key.method_1442();
                        }
                        if (type != InputUtil.Type.field_1668) break block19;
                        n = n2;
                        if (n3 != 0) break block20;
                        if (n < 0) break block21;
                        n = n2;
                    }
                    if (n > 348) break block21;
                    l = keyPress[n2];
                    break block22;
                }
                l = 0L;
            }
            return l;
        }
        return 0L;
    }

    private static boolean audited() {
        boolean bl;
        block14: {
            block11: {
                block13: {
                    MinecraftClient minecraftClient;
                    int n;
                    block12: {
                        MinecraftClient minecraftClient2;
                        block10: {
                            minecraftClient2 = MinecraftClient.method_1551();
                            n = a;
                            minecraftClient = minecraftClient2;
                            if (n != 0) break block10;
                            if (minecraftClient.field_1724 == null) break block11;
                            minecraftClient = minecraftClient2;
                        }
                        if (n != 0) break block12;
                        if (minecraftClient.field_1755 == null) break block13;
                        minecraftClient = minecraftClient2;
                    }
                    bl = minecraftClient.field_1755 instanceof HandledScreen;
                    if (n != 0) break block14;
                    if (!bl) break block11;
                }
                bl = true;
                break block14;
            }
            bl = false;
        }
        return bl;
    }

    private static boolean inGame() {
        boolean bl;
        block8: {
            block7: {
                MinecraftClient minecraftClient;
                block6: {
                    MinecraftClient minecraftClient2 = MinecraftClient.method_1551();
                    int n = a;
                    minecraftClient = minecraftClient2;
                    if (n != 0) break block6;
                    if (minecraftClient.field_1724 == null) break block7;
                    minecraftClient = minecraftClient2;
                }
                if (minecraftClient.field_1755 != null) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    private static boolean anyPosted() {
        boolean bl;
        block9: {
            long l = System.currentTimeMillis();
            int n = a;
            for (int i = 0; i <= 7; ++i) {
                boolean bl2;
                block11: {
                    block10: {
                        bl = mousePosted[i];
                        if (n != 0) break block9;
                        if (n != 0) break block10;
                        if (!bl) continue;
                        long l2 = l - mousePress[i] - InputAudit.a(16155, 2159404089388076658L);
                        bl2 = l2 == 0L ? 0 : (l2 < 0L ? -1 : 1);
                    }
                    if (n != 0) break block11;
                    if (bl2 >= 0) continue;
                    bl2 = true;
                }
                return bl2;
            }
            bl = false;
        }
        return bl;
    }

    private static boolean posted(KeyBinding keyBinding) {
        block28: {
            int n;
            block32: {
                block30: {
                    int n2;
                    block31: {
                        int n3;
                        block29: {
                            InputUtil.Type type;
                            block22: {
                                InputUtil.Key key;
                                block23: {
                                    int n4;
                                    block27: {
                                        block25: {
                                            block26: {
                                                block24: {
                                                    key = InputUtil.method_15981((String)keyBinding.method_1428());
                                                    n2 = a;
                                                    n3 = key.method_1444();
                                                    type = key.method_1442();
                                                    if (n2 != 0) break block22;
                                                    if (type != InputUtil.Type.field_1672) break block23;
                                                    n4 = n3;
                                                    if (n2 != 0) break block24;
                                                    if (n4 < 0) break block25;
                                                    n4 = n3;
                                                }
                                                if (n2 != 0) break block26;
                                                if (n4 > 7) break block25;
                                                n4 = mousePosted[n3];
                                            }
                                            if (n2 != 0) break block27;
                                            if (n4 == 0) break block25;
                                            n4 = 1;
                                            break block27;
                                        }
                                        n4 = 0;
                                    }
                                    return n4 != 0;
                                }
                                type = key.method_1442();
                            }
                            if (type != InputUtil.Type.field_1668) break block28;
                            n = n3;
                            if (n2 != 0) break block29;
                            if (n < 0) break block30;
                            n = n3;
                        }
                        if (n2 != 0) break block31;
                        if (n > 348) break block30;
                        n = keyPosted[n3];
                    }
                    if (n2 != 0) break block32;
                    if (n == 0) break block30;
                    n = 1;
                    break block32;
                }
                n = 0;
            }
            return n != 0;
        }
        return false;
    }

    private static boolean physical(KeyBinding keyBinding) {
        block53: {
            int n;
            block60: {
                block62: {
                    block61: {
                        int n2;
                        long l;
                        int n3;
                        block58: {
                            long l2;
                            block59: {
                                block56: {
                                    block57: {
                                        int n4;
                                        block54: {
                                            block55: {
                                                InputUtil.Type type;
                                                block42: {
                                                    InputUtil.Key key;
                                                    block43: {
                                                        int n5;
                                                        block50: {
                                                            block52: {
                                                                block51: {
                                                                    block48: {
                                                                        block49: {
                                                                            block46: {
                                                                                block47: {
                                                                                    int n6;
                                                                                    block44: {
                                                                                        block45: {
                                                                                            key = InputUtil.method_15981((String)keyBinding.method_1428());
                                                                                            n3 = key.method_1444();
                                                                                            l2 = MinecraftClient.method_1551().method_22683().method_4490();
                                                                                            l = System.currentTimeMillis();
                                                                                            n2 = a;
                                                                                            type = key.method_1442();
                                                                                            if (n2 != 0) break block42;
                                                                                            if (type != InputUtil.Type.field_1672) break block43;
                                                                                            n6 = n3;
                                                                                            if (n2 != 0) break block44;
                                                                                            if (n6 < 0) break block45;
                                                                                            n5 = n3;
                                                                                            if (n2 != 0) break block46;
                                                                                            if (n5 <= 7) break block47;
                                                                                        }
                                                                                        n6 = 1;
                                                                                    }
                                                                                    return n6 != 0;
                                                                                }
                                                                                n5 = mousePosted[n3];
                                                                            }
                                                                            if (n2 != 0) break block48;
                                                                            if (n5 == 0) break block49;
                                                                            return false;
                                                                        }
                                                                        n5 = GLFW.glfwGetMouseButton((long)l2, (int)n3);
                                                                    }
                                                                    if (n2 != 0) break block50;
                                                                    if (n5 == 1) break block51;
                                                                    long l3 = l - mousePress[n3] - InputAudit.a(22693, 4626729695392819663L);
                                                                    n5 = l3 == 0L ? 0 : (l3 < 0L ? -1 : 1);
                                                                    if (n2 != 0) break block50;
                                                                    if (n5 >= 0) break block52;
                                                                }
                                                                n5 = 1;
                                                                break block50;
                                                            }
                                                            n5 = 0;
                                                        }
                                                        return n5 != 0;
                                                    }
                                                    type = key.method_1442();
                                                }
                                                if (type != InputUtil.Type.field_1668) break block53;
                                                n4 = n3;
                                                if (n2 != 0) break block54;
                                                if (n4 < 0) break block55;
                                                n = n3;
                                                if (n2 != 0) break block56;
                                                if (n <= 348) break block57;
                                            }
                                            n4 = 1;
                                        }
                                        return n4 != 0;
                                    }
                                    n = keyPosted[n3];
                                }
                                if (n2 != 0) break block58;
                                if (n == 0) break block59;
                                return false;
                            }
                            n = GLFW.glfwGetKey((long)l2, (int)n3);
                        }
                        if (n2 != 0) break block60;
                        if (n == 1) break block61;
                        long l4 = l - keyPress[n3] - InputAudit.a(16155, 2159404089388076658L);
                        n = l4 == 0L ? 0 : (l4 < 0L ? -1 : 1);
                        if (n2 != 0) break block60;
                        if (n >= 0) break block62;
                    }
                    n = 1;
                    break block60;
                }
                n = 0;
            }
            return n != 0;
        }
        return true;
    }

    
    static {
        block23: {
            block22: {
                block21: {
                    block20: {
                        var13 = new String[84];
                        var11_1 = 0;
                        var10_2 = "\u0012$]R\u0006\u0003y6?]R=\u0014\n\u00044HM \u0003\\$0Y&0=@^$M|6?]\u001d-\u0014/2?FI'\b}s![R(\u001fn>}\t_:\u0019{<?\t\u0002sy\n\u001b>_X=9`'4D\u000f 8DH#\f{65\t^#\u0004l8\u000f#=H^*Mh?>^N;\u0002a6$:?_X!\u0019`!(\t^#\u0004l8qY\\,\u0006j'qZX!\u0019/78[X,\u0019c*\u0004;8]\u001d\u0010\u0012$]R\u000b\u0002z1=L|!\u000eg<#\u0011#=H^*Mj=5v^=\u0014|'0E\t\u0012$]R\u001f\bn!=\u0010=>]\u001d)\u001f`>qH\u001d,\u0001f0:\u000b'9L\u001d \u000bi;0GY#0=@^$Mj>!]Do\u001fj !HJ!2n=2AR=Mg<=MT!\n/\t\u0012$]R\u001b\u0002{6<\u0006s&@I'M*0=@^$Ml2=E_.\u000eds&@I'M{;4\t_:\u0019{<?\tH?A/1$]I \u0003/\t\u0012$]R\u001b\u0002{6<\u0005s0GYo\u000bs7[R\"M|?>]\u001d\u0004;8]\u001d\u0007\u0012$]R\u001a\u001ej\ts9FQ+\u0004a4q\u0004&\"L\u001d\t\u00150BX\f\u0001f0:\u0002\u007fq\u000e#=H^*M`1\"@Y&\fa\u000bs8GI M|?>]\u001d\u001609HO(\b/!4ZM.\u001aa\f0G^'\u0002}s\u0005\u001e0JO \n6<YI6Mg2?M\u0002\u007fq\n\u0007#@Z(\b}\u0011>]\u0007\u0012$]R\n\f{\n\u0012$]R\u000e\u0003l;>[\u000fs0]\u001djC>5qKQ \u000ed \u0013\u007fqGRo\u0004a%4GI \u001fvs>YX!\t\u0012$]R\u001f\bn!=\u000fs&@I'Mh?>^N;\u0002a6\u00176?]T;\u0014/=>]\u001d)\u001f`>qH\u001d,\u0001f0:\rzq^T;\u0005/=>\tV*\u0014\u0015s&@I'M{;4\tN8\f\u007fs:LDo\u0018\u007f\u0010=>]\u001d)\u001f`>qH\u001d,\u0001f0:\u0007\u0012$]R\u001f\u0002{\u0004s>G\u001d/'&F\u001d.\u0003l;>[\u001d,\u0001f0:Z\u001d&\u0003/<?L\u001d;\u0004l8}\tS \u0019/5#FPo\f/0=@^$<0=@^$M\u007f<\"]X+M{<q]U*Mh2<L\u001d8\u0004a7>^\u0011o\u0003`s<FH<\b/14AT!\t/:%\u0005\u001d-\u0018{'>G\u001d\u001b74]R!\f{6q[X<\u001dn$?v\\!\u000eg<#\t\\;M\u000b\u0012$]R\f\u001fv %HQ\u001084P\u001d'\bc7q@So\u000e`74\u000b\u0012$]R\f\u001fv %HQ\n\u0012$]R\u000e\u0019{22B\u0007 &@I,\u0005/\ts8G\u001d<\u0001`'q\u0002zq\u0007;>]_.\u001f/:84P\u001d?\u0002|'4M\u001d;\u0002/'9L\u001d(\fb6q^T!\t`$}\tS Md6(KR.\u001fks3LU&\u0003ks8]\u0011o\u0006j*q\t\u00150BX\f\u0001f0:\u0005 &HMo!84P\u001d<\ba'qKDo\fa<%AX=M\u007f!>NO.\u0000#s:LDo\u0011\u007fqZT\"\u0018c2%LYo\u000ec:2B\u0011\u007fqGRo\u000ec:2B\u001d \u001f/84P\"84P\u001d,\fc?3H^$Mx:%A\u001d;\u0005js:LDo\u0018\u007f\u007fqBX6M\ts8G\u001d<\u0001`'q\f\u0012$]R\u0006\u0003{6#H^;\b'>\tN#\u0002{s\n\u0012$]R\u001c\u001af'2A\u0005\u001e0JO \n\u0012$]R\u000e\u0003l;>[\u0017;8]\u001d.\u0003/&?BS \u001aas4GI&\u0019v\u007fq\u0014#=H^*M}6\"Y\\8\u0003P2?JU \u001f\u0005 &HMo\b#8JVo\u0018\u007fs\f 9@[;Ml?8JVo\rs>\\Io\u0002is\"ER;M\u00111#L\\$Mj=5v^=\u0014|'0E\r\u0012$]R\u0006\u0003y6?]R=\u0014\n5#FPo\u001ec<%\t\n\u00000OX\u000e\u0003l;>[\u0004#$]\u001d\u0002sy";
                        var12_3 = "\u0012$]R\u0006\u0003y6?]R=\u0014\n\u00044HM \u0003\\$0Y&0=@^$M|6?]\u001d-\u0014/2?FI'\b}s![R(\u001fn>}\t_:\u0019{<?\t\u0002sy\n\u001b>_X=9`'4D\u000f 8DH#\f{65\t^#\u0004l8\u000f#=H^*Mh?>^N;\u0002a6$:?_X!\u0019`!(\t^#\u0004l8qY\\,\u0006j'qZX!\u0019/78[X,\u0019c*\u0004;8]\u001d\u0010\u0012$]R\u000b\u0002z1=L|!\u000eg<#\u0011#=H^*Mj=5v^=\u0014|'0E\t\u0012$]R\u001f\bn!=\u0010=>]\u001d)\u001f`>qH\u001d,\u0001f0:\u000b'9L\u001d \u000bi;0GY#0=@^$Mj>!]Do\u001fj !HJ!2n=2AR=Mg<=MT!\n/\t\u0012$]R\u001b\u0002{6<\u0006s&@I'M*0=@^$Ml2=E_.\u000eds&@I'M{;4\t_:\u0019{<?\tH?A/1$]I \u0003/\t\u0012$]R\u001b\u0002{6<\u0005s0GYo\u000bs7[R\"M|?>]\u001d\u0004;8]\u001d\u0007\u0012$]R\u001a\u001ej\ts9FQ+\u0004a4q\u0004&\"L\u001d\t\u00150BX\f\u0001f0:\u0002\u007fq\u000e#=H^*M`1\"@Y&\fa\u000bs8GI M|?>]\u001d\u001609HO(\b/!4ZM.\u001aa\f0G^'\u0002}s\u0005\u001e0JO \n6<YI6Mg2?M\u0002\u007fq\n\u0007#@Z(\b}\u0011>]\u0007\u0012$]R\n\f{\n\u0012$]R\u000e\u0003l;>[\u000fs0]\u001djC>5qKQ \u000ed \u0013\u007fqGRo\u0004a%4GI \u001fvs>YX!\t\u0012$]R\u001f\bn!=\u000fs&@I'Mh?>^N;\u0002a6\u00176?]T;\u0014/=>]\u001d)\u001f`>qH\u001d,\u0001f0:\rzq^T;\u0005/=>\tV*\u0014\u0015s&@I'M{;4\tN8\f\u007fs:LDo\u0018\u007f\u0010=>]\u001d)\u001f`>qH\u001d,\u0001f0:\u0007\u0012$]R\u001f\u0002{\u0004s>G\u001d/'&F\u001d.\u0003l;>[\u001d,\u0001f0:Z\u001d&\u0003/<?L\u001d;\u0004l8}\tS \u0019/5#FPo\f/0=@^$<0=@^$M\u007f<\"]X+M{<q]U*Mh2<L\u001d8\u0004a7>^\u0011o\u0003`s<FH<\b/14AT!\t/:%\u0005\u001d-\u0018{'>G\u001d\u001b74]R!\f{6q[X<\u001dn$?v\\!\u000eg<#\t\\;M\u000b\u0012$]R\f\u001fv %HQ\u001084P\u001d'\bc7q@So\u000e`74\u000b\u0012$]R\f\u001fv %HQ\n\u0012$]R\u000e\u0019{22B\u0007 &@I,\u0005/\ts8G\u001d<\u0001`'q\u0002zq\u0007;>]_.\u001f/:84P\u001d?\u0002|'4M\u001d;\u0002/'9L\u001d(\fb6q^T!\t`$}\tS Md6(KR.\u001fks3LU&\u0003ks8]\u0011o\u0006j*q\t\u00150BX\f\u0001f0:\u0005 &HMo!84P\u001d<\ba'qKDo\fa<%AX=M\u007f!>NO.\u0000#s:LDo\u0011\u007fqZT\"\u0018c2%LYo\u000ec:2B\u0011\u007fqGRo\u000ec:2B\u001d \u001f/84P\"84P\u001d,\fc?3H^$Mx:%A\u001d;\u0005js:LDo\u0018\u007f\u007fqBX6M\ts8G\u001d<\u0001`'q\f\u0012$]R\u0006\u0003{6#H^;\b'>\tN#\u0002{s\n\u0012$]R\u001c\u001af'2A\u0005\u001e0JO \n\u0012$]R\u000e\u0003l;>[\u0017;8]\u001d.\u0003/&?BS \u001aas4GI&\u0019v\u007fq\u0014#=H^*M}6\"Y\\8\u0003P2?JU \u001f\u0005 &HMo\b#8JVo\u0018\u007fs\f 9@[;Ml?8JVo\rs>\\Io\u0002is\"ER;M\u00111#L\\$Mj=5v^=\u0014|'0E\r\u0012$]R\u0006\u0003y6?]R=\u0014\n5#FPo\u001ec<%\t\n\u00000OX\u000e\u0003l;>[\u0004#$]\u001d\u0002sy".length();
                        var9_4 = 13;
                        var8_5 = -1;
lbl7:

                        while (true) {
                            v0 = ++var8_5;
                            v1 = var10_2.substring(v0, v0 + var9_4);
                            v2 = -1;
                            break block20;
                            break;
                        }
lbl12:

                        while (true) {
                            var13[var11_1++] = v3.intern();
                            if ((var8_5 += var9_4) < var12_3) {
                                var9_4 = var10_2.charAt(var8_5);
                                continue;
                            }
                            var10_2 = "s%F\u001d\n\u0012$]R\u000e\u0019{22B";
                            var12_3 = "s%F\u001d\n\u0012$]R\u000e\u0019{22B".length();
                            var9_4 = 4;
                            var8_5 = -1;
lbl21:

                            while (true) {
                                v4 = ++var8_5;
                                v1 = var10_2.substring(v4, v4 + var9_4);
                                v2 = 0;
                                break block20;
                                break;
                            }
                            break;
                        }
lbl26:

                        while (true) {
                            var13[var11_1++] = v3.intern();
                            if ((var8_5 += var9_4) < var12_3) {
                                var9_4 = var10_2.charAt(var8_5);
                                continue;
                            }
                            break block21;
                            break;
                        }
                    }
                    v5 = v1.toCharArray();
                    v6 = v5;
                    v7 = v5.length;
                    var14_6 = 0;
                    do {
                        v6 = v6;
                        v8 = var14_6;
                        v9 = v6[v8];
                        switch (var14_6 % 7) {
                            case 0: {
                                v10 = 83;
                                break;
                            }
                            case 1: {
                                v10 = 81;
                                break;
                            }
                            case 2: {
                                v10 = 41;
                                break;
                            }
                            case 3: {
                                v10 = 61;
                                break;
                            }
                            case 4: {
                                v10 = 79;
                                break;
                            }
                            case 5: {
                                v10 = 109;
                                break;
                            }
                            default: {
                                v10 = 15;
                            }
                        }
                        v6[v8] = (char)(v9 ^ v10);
                        ++var14_6;
lbl65:

                        v7 = v7;
                    } while (v7 > var14_6);
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
                InputAudit.b = var13;
                InputAudit.NOT_A_CLICK = InputAudit.b[43];
                var0_7 = 8176310147238688584L;
                var6_8 = new long[3];
                var3_9 = 0;
                var4_10 = "\"b\u00ee\nK\u00a3\u000b\u0005l\u008f\u00a8VA\u0015\u00f9\u00c01Md\u00e6\u00b66\u009e}";
                var5_11 = "\"b\u00ee\nK\u00a3\u000b\u0005l\u008f\u00a8VA\u0015\u00f9\u00c01Md\u00e6\u00b66\u009e}".length();
                var2_12 = 0;
                while (true) {
                    break block22;
                    break;
                }
lbl86:

                while (true) {
                    var6_8[v11] = (((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L) ^ var0_7;
                    if (var2_12 < var5_11) continue;
                    break block23;
                    break;
                }
            }
            var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
            v11 = var3_9++;
            while (true)
        }
        InputAudit.c = var6_8;
        InputAudit.d = new Long[3];
        InputAudit.PRESS_MS = InputAudit.a(19935, 5988372192423214263L);
        InputAudit.keyPress = new long[349];
        InputAudit.mousePress = new long[8];
        InputAudit.keyPosted = new boolean[349];
        InputAudit.mousePosted = new boolean[8];
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x3168) & Short.MAX_VALUE;
        if (d[n2] == null) {
            InputAudit.d[n2] = c[n2] ^ l;
        }
        return d[n2];
    }
}
