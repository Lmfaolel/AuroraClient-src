package dev.quelchi.aurora.hud;

import dev.quelchi.aurora.gui.HudStyle$Look;
import dev.quelchi.aurora.gui.Round;
import dev.quelchi.aurora.gui.Theme;
import dev.quelchi.aurora.hud.ItemHighlights$Style;
import dev.quelchi.aurora.hud.KeystrokesHud$1;
import dev.quelchi.aurora.input.ClickTracker;
import dev.quelchi.aurora.input.KeyInput;
import dev.quelchi.aurora.module.modules.KeystrokesModule;
import java.util.Objects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.option.KeyBinding;

public final class KeystrokesHud {
    private static final float ANIM_RATE = 16.0f;
    private static final int PAD_X;
    private static final int LINE_GAP = 1;
    private static final float[] keyAnim;
    private static float lmbAnim;
    private static float rmbAnim;
    private static long lastNanos;
    private static final String[] a;
    private static final long[] b;
    private static final Integer[] c;

    private KeystrokesHud() {
    }

    public static int[] draw(DrawContext drawContext, MinecraftClient minecraftClient, int n, int n2) {
        int n3;
        int n4;
        int n5;
        block104: {
            String string72;
            block105: {
                String string;
                String string3;
                int n6;
                int n7;
                int n8;
                String[] stringArray;
                TextRenderer textRenderer;
                HudStyle$Look look;
                int n9;
                int n10;
                KeystrokesModule keystrokesModule;
                block116: {
                    block111: {
                        int n11;
                        block110: {
                            String string2;
                            int n12;
                            int n13;
                            block115: {
                                block109: {
                                    int n14;
                                    block108: {
                                        int n15;
                                        float f;
                                        block106: {
                                            block107: {
                                                int n16;
                                                int n17;
                                                block97: {
                                                    int n18;
                                                    int n19;
                                                    int[] nArray;
                                                    String[][] stringArrayArray;
                                                    KeyBinding[] keyBindingArray;
                                                    int n20;
                                                    block114: {
                                                        block94: {
                                                            int n21;
                                                            block95: {
                                                                block96: {
                                                                    block93: {
                                                                        block91: {
                                                                            block92: {
                                                                                block90: {
                                                                                    block89: {
                                                                                        int n22;
                                                                                        int n23;
                                                                                        block113: {
                                                                                            block88: {
                                                                                                int n24;
                                                                                                int n25;
                                                                                                block112: {
                                                                                                    int n26;
                                                                                                    boolean bl;
                                                                                                    block82: {
                                                                                                        int n27;
                                                                                                        block80: {
                                                                                                            block81: {
                                                                                                                keystrokesModule = KeystrokesModule.get();
                                                                                                                n13 = ItemHighlights$Style.a;
                                                                                                                if (keystrokesModule == null) {
                                                                                                                    return new int[]{n, n2, 0, 0};
                                                                                                                }
                                                                                                                n23 = Math.max(8, keystrokesModule.keySize());
                                                                                                                n10 = Math.max(0, keystrokesModule.gap());
                                                                                                                n9 = Math.max(8, keystrokesModule.mouseHeight());
                                                                                                                n17 = keystrokesModule.showKeys();
                                                                                                                n20 = keystrokesModule.showMouse();
                                                                                                                look = keystrokesModule.panelLook();
                                                                                                                n27 = n17;
                                                                                                                if (n13 != 0) break block80;
                                                                                                                if (n27 != 0) break block81;
                                                                                                                n27 = n20;
                                                                                                                if (n13 != 0) break block80;
                                                                                                                if (n27 != 0) break block81;
                                                                                                                return new int[]{n, n2, 0, 0};
                                                                                                            }
                                                                                                            n27 = keystrokesModule.showCps();
                                                                                                        }
                                                                                                        n12 = n27;
                                                                                                        textRenderer = minecraftClient.field_1772;
                                                                                                        Objects.requireNonNull(textRenderer);
                                                                                                        int n28 = 9;
                                                                                                        keyBindingArray = minecraftClient.field_1690.field_1852;
                                                                                                        stringArrayArray = new String[9][];
                                                                                                        nArray = new int[9];
                                                                                                        bl = false;
                                                                                                        n26 = 0;
                                                                                                        while (n26 < 9) {
                                                                                                            block87: {
                                                                                                                int n29;
                                                                                                                String[] stringArray2;
                                                                                                                block85: {
                                                                                                                    block86: {
                                                                                                                        String string4;
                                                                                                                        block84: {
                                                                                                                            block83: {
                                                                                                                                if (n13 != 0) break block82;
                                                                                                                                if (keyBindingArray == null) break block83;
                                                                                                                                if (n26 >= keyBindingArray.length) break block83;
                                                                                                                                string4 = KeyInput.label(keyBindingArray[n26]);
                                                                                                                                break block84;
                                                                                                                            }
                                                                                                                            string4 = "";
                                                                                                                        }
                                                                                                                        String string6 = string4;
                                                                                                                        stringArray2 = KeystrokesHud.splitLabel(string6, n26 + 1);
                                                                                                                        stringArrayArray[n26] = stringArray2;
                                                                                                                        n29 = stringArray2.length;
                                                                                                                        if (n13 != 0) break block85;
                                                                                                                        if (n29 <= 1) break block86;
                                                                                                                        bl = true;
                                                                                                                    }
                                                                                                                    n29 = 0;
                                                                                                                }
                                                                                                                n5 = n29;
                                                                                                                for (String string72 : stringArray2) {
                                                                                                                    if (n13 == 0) {
                                                                                                                        n5 = Math.max(n5, textRenderer.method_1727(string72));
                                                                                                                        if (n13 == 0) continue;
                                                                                                                    }
                                                                                                                    break block87;
                                                                                                                }
                                                                                                                nArray[n26] = Math.max(n23, n5 + 8);
                                                                                                                ++n26;
                                                                                                            }
                                                                                                            if (n13 == 0) continue;
                                                                                                        }
                                                                                                        int n24 = 2;
                                                                                                        n24 = n28;
                                                                                                        if (n13 != 0) break block112;
                                                                                                        n26 = n25 * n24 + 1 + 3;
                                                                                                    }
                                                                                                    if (!bl) break block88;
                                                                                                    int n24 = n23;
                                                                                                    n24 = n26;
                                                                                                }
                                                                                                n22 = Math.max(n25, n24);
                                                                                                break block113;
                                                                                            }
                                                                                            n22 = n23;
                                                                                        }
                                                                                        n16 = n22;
                                                                                        n4 = 8 * n10;
                                                                                        for (n5 = 0; n5 < 9; ++n5) {
                                                                                            n4 += nArray[n5];
                                                                                            if (n13 == 0) {
                                                                                                if (n13 == 0) continue;
                                                                                                break;
                                                                                            }
                                                                                            break block89;
                                                                                        }
                                                                                        n19 = n17;
                                                                                        if (n13 != 0) break block90;
                                                                                        if (n19 != 0) break block89;
                                                                                        n16 = 0;
                                                                                        stringArray = a;
                                                                                        n5 = Math.max(n23 * 2, textRenderer.method_1727(stringArray[2]) + 16);
                                                                                        n4 = n5 * 2 + n10;
                                                                                    }
                                                                                    n19 = n17;
                                                                                }
                                                                                if (n13 != 0) break block91;
                                                                                if (n19 == 0) break block92;
                                                                                n19 = n16;
                                                                                break block91;
                                                                            }
                                                                            n19 = 0;
                                                                        }
                                                                        n21 = n20;
                                                                        if (n13 != 0) break block93;
                                                                        if (n21 == 0) break block94;
                                                                        n21 = n17;
                                                                    }
                                                                    if (n13 != 0) break block95;
                                                                    if (n21 == 0) break block96;
                                                                    n21 = n10;
                                                                    break block95;
                                                                }
                                                                n21 = 0;
                                                            }
                                                            n18 = n21 + n9;
                                                            break block114;
                                                        }
                                                        n18 = 0;
                                                    }
                                                    n5 = n19 + n18;
                                                    f = KeystrokesHud.frameDelta();
                                                    int n30 = minecraftClient.field_1724.method_31548().method_67532();
                                                    float f2 = (float)Math.max(0.1, keystrokesModule.scale());
                                                    string72 = drawContext.method_51448();
                                                    string72.pushMatrix();
                                                    string72.translate((float)n, (float)n2);
                                                    string72.scale(f2, f2);
                                                    int n31 = 0;
                                                    n8 = 0;
                                                    while (n17 != 0) {
                                                        int n32;
                                                        block103: {
                                                            block102: {
                                                                block101: {
                                                                    int n33;
                                                                    block100: {
                                                                        block98: {
                                                                            block99: {
                                                                                n3 = n8;
                                                                                if (n13 != 0 || n13 != 0) break block97;
                                                                                if (n3 >= 9) break;
                                                                                if (keyBindingArray == null) break block98;
                                                                                n33 = n8;
                                                                                if (n13 != 0) break block99;
                                                                                if (n33 >= keyBindingArray.length) break block98;
                                                                                n33 = KeyInput.isDown(minecraftClient, keyBindingArray[n8]) ? 1 : 0;
                                                                            }
                                                                            if (n13 != 0) break block100;
                                                                            if (n33 == 0) break block98;
                                                                            n33 = 1;
                                                                            break block100;
                                                                        }
                                                                        n33 = 0;
                                                                    }
                                                                    n7 = n33;
                                                                    float[] fArray = keyAnim;
                                                                    int n34 = n8;
                                                                    float f4 = keyAnim[n8];
                                                                    f4 = n7 != 0 ? 1.0f : 0.0f;
                                                                    fArray[n34] = KeystrokesHud.approach(f3, f4, f);
                                                                    n32 = keystrokesModule.highlightSelected();
                                                                    if (n13 != 0) break block101;
                                                                    if (n32 == 0) break block102;
                                                                    n32 = n8;
                                                                }
                                                                if (n13 != 0) break block103;
                                                                if (n32 != n30) break block102;
                                                                n32 = 1;
                                                                break block103;
                                                            }
                                                            n32 = 0;
                                                        }
                                                        n6 = n32;
                                                        KeystrokesHud.drawBox(drawContext, textRenderer, keystrokesModule, n31, 0, nArray[n8], n16, keyAnim[n8], n6 != 0, stringArrayArray[n8], null, look);
                                                        n31 += nArray[n8] + n10;
                                                        ++n8;
                                                        if (n13 == 0) continue;
                                                    }
                                                    n3 = n20;
                                                }
                                                if (n13 != 0) break block104;
                                                if (n3 == 0) break block105;
                                                n15 = n17;
                                                if (n13 != 0) break block106;
                                                if (n15 == 0) break block107;
                                                n15 = n16 + n10;
                                                break block106;
                                            }
                                            n15 = 0;
                                        }
                                        n8 = n15;
                                        n7 = (n4 - n10) / 2;
                                        n6 = n4 - n10 - n7;
                                        boolean bl = KeyInput.mouseDown(minecraftClient, 0);
                                        boolean bl2 = KeyInput.mouseDown(minecraftClient, 1);
                                        float f6 = lmbAnim;
                                        f6 = bl ? 1.0f : 0.0f;
                                        lmbAnim = KeystrokesHud.approach(f5, f6, f);
                                        float f8 = rmbAnim;
                                        f8 = bl2 ? 1.0f : 0.0f;
                                        rmbAnim = KeystrokesHud.approach(f7, f8, f);
                                        n14 = n12;
                                        if (n13 != 0) break block108;
                                        if (n14 == 0) break block109;
                                        n14 = ClickTracker.cps(0);
                                    }
                                    string2 = String.valueOf(n14);
                                    break block115;
                                }
                                string2 = null;
                            }
                            string3 = string2;
                            n11 = n12;
                            if (n13 != 0) break block110;
                            if (n11 == 0) break block111;
                            n11 = ClickTracker.cps(1);
                        }
                        string = String.valueOf(n11);
                        break block116;
                    }
                    string = null;
                }
                String string8 = string;
                String[] stringArray2 = new String[1];
                stringArray = a;
                stringArray2[0] = stringArray[0];
                KeystrokesHud.drawBox(drawContext, textRenderer, keystrokesModule, 0, n8, n7, n9, lmbAnim, false, stringArray2, string3, look);
                KeystrokesHud.drawBox(drawContext, textRenderer, keystrokesModule, n7 + n10, n8, n6, n9, rmbAnim, false, new String[]{stringArray[1]}, string8, look);
            }
            string72.popMatrix();
            n3 = 4;
        }
        int[] nArray = new int[n3];
        nArray[0] = n;
        nArray[1] = n2;
        nArray[2] = n4;
        nArray[3] = n5;
        return nArray;
    }

    
    private static void drawBox(DrawContext var0, TextRenderer var1_1, KeystrokesModule var2_2, int var3_3, int var4_4, int var5_5, int var6_6, float var7_7, boolean var8_8, String[] var9_9, String var10_10, HudStyle$Look var11_11) {
        block46: {
            var13_12 = KeystrokesHud.lerp(var2_2.idleBg(), var2_2.pressedBg(), var7_7);
            var12_13 = ItemHighlights$Style.a;
            var14_14 = Math.min(8, Math.min(var5_5, var6_6) / 2);
            v0 = KeystrokesHud$1.$SwitchMap$dev$quelchi$aurora$gui$HudStyle$Look[var11_11.ordinal()];
            if (var12_13 != 0) break block46;
            block21 : switch (v0) {
                case 1: {
                    if (var2_2.border() >>> 24 == 0) {
                        Round.rect(var0, var3_3, var4_4, var5_5, var6_6, var14_14, var13_12);
                        if (var12_13 == 0) break;
                    }
                    Round.framed(var0, var3_3, var4_4, var5_5, var6_6, var14_14, var13_12, var2_2.border());
                    if (var12_13 == 0) break;
                }
                case 2: {
                    var15_15 = Math.min(2, Math.min(var5_5, var6_6) / 2);
                    Round.framed(var0, var3_3, var4_4, var5_5, var6_6, var15_15, var13_12, Theme.alpha(var2_2.accent(), 90 + (int)(140.0f * var7_7)));
                    if (var12_13 == 0) break;
                }
                case 3: {
                    Round.rect(var0, var3_3, var4_4, var5_5, var6_6, var14_14, Theme.alpha(var13_12, (int)((float)(var13_12 >>> 24 & 255) * 0.25f)));
                    Round.outline(var0, var3_3, var4_4, var5_5, var6_6, var14_14, Theme.alpha(var2_2.accent(), 110 + (int)(145.0f * var7_7)));
                    if (var12_13 == 0) break;
                }
                case 4: {
                    var15_15 = Math.max(3, Math.min(8, Math.min(var5_5, var6_6) / 3));
                    cfr_temp_0 = var7_7 - 0.02f;
                    v1 = cfr_temp_0 == 0.0f ? 0 : (cfr_temp_0 > 0.0f ? 1 : -1);
                    if (var12_13 == 0) {
                        v1 = v1 > 0 ? (float)var2_2.pressedBg() : (float)var2_2.accent();
                    }
                    var16_16 = Theme.alpha((int)v1, 150 + (int)(105.0f * var7_7));
                    var17_17 = 0;
                    while (var17_17 <= 1 && var12_13 == 0) {
                        for (var18_18 = 0; var18_18 <= 1; ++var18_18) {
                            if (var12_13 != 0) // goto
                            v2 = var17_17;
                            if (var12_13 != 0) // goto
                            if (v2 == 0) {
                                v3 = var3_3;
                            } else {
                                v2 = var3_3 + var5_5;
lbl38:

                                v3 = v2 - var15_15;
                            }
                            var19_19 = v3;
                            v4 = var18_18;
                            if (var12_13 != 0) // goto
                            if (v4 == 0) {
                                v5 = var4_4;
                            } else {
                                v4 = var4_4 + var6_6;
lbl46:

                                v5 = v4 - 1;
                            }
                            var20_20 = v5;
                            var0.method_25294(var19_19, var20_20, var19_19 + var15_15, var20_20 + 1, var16_16);
                            v6 = var17_17;
                            if (var12_13 != 0) // goto
                            if (v6 == 0) {
                                v7 = var3_3;
                            } else {
                                v6 = var3_3 + var5_5;
lbl55:

                                v7 = v6 - 1;
                            }
                            var21_23 = v7;
                            v8 = var18_18;
                            if (var12_13 != 0) // goto
                            if (v8 == 0) {
                                v9 = var4_4;
                            } else {
                                v8 = var4_4 + var6_6;
lbl63:

                                v9 = v8 - var15_15;
                            }
                            var22_24 = v9;
                            var0.method_25294(var21_23, var22_24, var21_23 + 1, var22_24 + var15_15, var16_16);
                            if (var12_13 == 0) continue;
                        }
                        ++var17_17;
lbl68:

                        if (var12_13 == 0) continue;
                        break block21;
                    }
                    break;
                }
            }
            v0 = (int)var8_8;
        }
        if (var12_13 == 0) {
            if (v0 != 0) {
                Round.rect(var0, var3_3 + 3, var4_4 + var6_6 - 4, var5_5 - 6, 2, 1, var2_2.accent());
            }
            Objects.requireNonNull(var1_1);
            v0 = 9;
        }
        var15_15 = v0;
        var16_16 = var9_9.length + (var10_10 != null ? 1 : 0);
        var17_17 = var16_16 * var15_15 + (var16_16 - 1) * 1;
        v10 = var18_18 = var4_4 + (var6_6 - var17_17) / 2;
        if (var12_13 == 0) {
            if (v10 < var4_4 + 1) {
                var18_18 = var4_4 + 1;
            }
            v10 = KeystrokesHud.lerp(var2_2.idleText(), var2_2.pressedText(), var7_7);
        }
        var19_19 = v10;
        var20_21 = var9_9;
        var21_23 = var20_21.length;
        for (var22_24 = 0; var22_24 < var21_23; var18_18 += var15_15 + 1, ++var22_24) {
            var23_25 = var20_21[var22_24];
            KeystrokesHud.centeredText(var0, var1_1, var23_25, var3_3, var5_5, var18_18, var19_19);
            if (var12_13 != 0) return;
            if (var12_13 == 0) continue;
        }
        if (var10_10 == null) return;
        var20_22 = KeystrokesHud.lerp(var2_2.cpsText(), var2_2.pressedText(), var7_7);
        KeystrokesHud.centeredText(var0, var1_1, var10_10, var3_3, var5_5, var18_18, var20_22);
    }

    private static String[] splitLabel(String string, int n) {
        int n2;
        String string2;
        block19: {
            block20: {
                String string3;
                int n3;
                block17: {
                    block18: {
                        String string4;
                        block16: {
                            String string5;
                            block14: {
                                block15: {
                                    n3 = ItemHighlights$Style.a;
                                    string5 = string;
                                    if (n3 != 0) break block14;
                                    if (string5 != null) break block15;
                                    string4 = "";
                                    break block16;
                                }
                                string5 = string;
                            }
                            string4 = string5.trim();
                        }
                        string2 = string4;
                        string3 = string2;
                        if (n3 != 0) break block17;
                        if (!string3.isEmpty()) break block18;
                        string2 = String.valueOf(n);
                    }
                    string3 = string2;
                }
                int n4 = KeystrokesHud.balancedSpace(string3);
                n2 = n4;
                if (n3 != 0) break block19;
                if (n2 <= 0) break block20;
                n2 = n4;
                if (n3 != 0) break block19;
                if (n2 >= string2.length() - 1) break block20;
                return new String[]{string2.substring(0, n4).trim(), string2.substring(n4 + 1).trim()};
            }
            n2 = 1;
        }
        String[] stringArray = new String[n2];
        stringArray[0] = string2;
        return stringArray;
    }

    private static int balancedSpace(String string) {
        int n;
        block9: {
            int n2 = string.length() / 2;
            int n3 = -1;
            int n4 = ItemHighlights$Style.a;
            int n5 = KeystrokesHud.a(8894, 4303497947496099885L);
            int n6 = 0;
            while (n6 < string.length()) {
                block12: {
                    block11: {
                        int n7;
                        block10: {
                            n = string.charAt(n6);
                            if (n4 != 0) break block9;
                            if (n4 != 0) break block10;
                            if (n != 32) break block11;
                            n7 = Math.abs(n6 - n2);
                        }
                        void var6_6 = n7;
                        if (n4 != 0) break block12;
                        if (var6_6 >= n5) break block11;
                        n5 = var6_6;
                        n3 = n6;
                    }
                    ++n6;
                }
                if (n4 == 0) continue;
            }
            n = n3;
        }
        return n;
    }

    private static void centeredText(DrawContext drawContext, TextRenderer textRenderer, String string, int n, int n2, int n3, int n4) {
        int n5 = n + (n2 - textRenderer.method_1727(string)) / 2;
        drawContext.method_51433(textRenderer, string, n5, n3, n4, true);
    }

    private static float frameDelta() {
        float f;
        long l;
        block6: {
            long l2;
            block4: {
                block5: {
                    l = System.nanoTime();
                    int n = ItemHighlights$Style.a;
                    l2 = lastNanos;
                    if (n != 0) break block4;
                    if (l2 != 0L) break block5;
                    f = 0.0f;
                    break block6;
                }
                l2 = l - lastNanos;
            }
            f = (float)l2 / 1.0E9f;
        }
        float f2 = f;
        lastNanos = l;
        return Math.min(f2, 0.1f);
    }

    private static float approach(float f, float f2, float f3) {
        float f4 = Math.min(1.0f, f3 * 16.0f);
        return f + (f2 - f) * f4;
    }

    private static int lerp(int n, int n2, float f) {
        f = Math.max(0.0f, Math.min(1.0f, f));
        int n3 = n >>> 24 & 0xFF;
        int n4 = n >>> 16 & 0xFF;
        int n5 = n >>> 8 & 0xFF;
        int n6 = n & 0xFF;
        int n7 = n2 >>> 24 & 0xFF;
        int n8 = n2 >>> 16 & 0xFF;
        int n9 = n2 >>> 8 & 0xFF;
        int n10 = n2 & 0xFF;
        int n11 = Math.round((float)n3 + (float)(n7 - n3) * f);
        int n12 = Math.round((float)n4 + (float)(n8 - n4) * f);
        int n13 = Math.round((float)n5 + (float)(n9 - n5) * f);
        int n14 = Math.round((float)n6 + (float)(n10 - n6) * f);
        return n11 << 24 | n12 << 16 | n13 << 8 | n14;
    }

    
    static {
        block16: {
            block15: {
                block14: {
                    var13 = new String[3];
                    var11_1 = 0;
                    var10_2 = "\u0014`u\u0003\n`u\u0003\n`u";
                    var12_3 = "\u0014`u\u0003\n`u\u0003\n`u".length();
                    var9_4 = 3;
                    var8_5 = -1;
lbl7:

                    while (true) {
                        continue;
                        break;
                    }
lbl9:

                    while (true) {
                        var13[var11_1++] = new String(v0).intern();
                        if ((var8_5 += var9_4) < var12_3) {
                            var9_4 = var10_2.charAt(var8_5);
                            continue;
                        }
                        break block14;
                        break;
                    }
                    v1 = ++var8_5;
                    v2 = var10_2.substring(v1, v1 + var9_4).toCharArray();
                    v0 = v2;
                    v3 = v2.length;
                    var14_6 = 0;
                    do {
                        v0 = v0;
                        v4 = var14_6;
                        v5 = v0[v4];
                        switch (var14_6 % 7) {
                            case 0: {
                                v6 = 88;
                                break;
                            }
                            case 1: {
                                v6 = 45;
                                break;
                            }
                            case 2: {
                                v6 = 55;
                                break;
                            }
                            case 3: {
                                v6 = 120;
                                break;
                            }
                            case 4: {
                                v6 = 5;
                                break;
                            }
                            case 5: {
                                v6 = 54;
                                break;
                            }
                            default: {
                                v6 = 73;
                            }
                        }
                        v0[v4] = (char)(v5 ^ v6);
                        ++var14_6;
lbl48:

                        v3 = v3;
                    } while (v3 > var14_6);
                    while (true)
                }
                KeystrokesHud.a = var13;
                var0_7 = 2431884613399543368L;
                var6_8 = new long[2];
                var3_9 = 0;
                var4_10 = "\u00b03{[\u0012\u00c2\u00b1P\u00d9\u00d0\u000e;\u00bf\u00b5\u00b1\u009a";
                var5_11 = "\u00b03{[\u0012\u00c2\u00b1P\u00d9\u00d0\u000e;\u00bf\u00b5\u00b1\u009a".length();
                var2_12 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl63:

                while (true) {
                    var6_8[v7] = (((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L) ^ var0_7;
                    if (var2_12 < var5_11) continue;
                    break block16;
                    break;
                }
            }
            var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
            v7 = var3_9++;
            while (true)
        }
        KeystrokesHud.b = var6_8;
        KeystrokesHud.c = new Integer[2];
        KeystrokesHud.PAD_X = KeystrokesHud.a(23950, 2743499076202417948L);
        KeystrokesHud.keyAnim = new float[9];
        KeystrokesHud.lastNanos = 0L;
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1292;
        if (c[n2] == null) {
            KeystrokesHud.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }
}
