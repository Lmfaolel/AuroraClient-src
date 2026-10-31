package dev.quelchi.aurora.render;

import dev.quelchi.aurora.module.modules.HandTweaksModule;
import dev.quelchi.aurora.module.modules.HandTweaksModule$Style;
import dev.quelchi.aurora.render.EffectEngine;
import dev.quelchi.aurora.render.SwingStyles$1;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Arm;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.joml.Quaternionfc;

public final class SwingStyles {
    private SwingStyles() {
    }

    private static void hand(MatrixStack matrixStack, int n) {
        matrixStack.method_46416((float)n * 0.56f, -0.52f, -0.72f);
    }

    public static boolean apply(MatrixStack matrixStack, int n, Arm arm, float f) {
        return SwingStyles.apply(matrixStack, n, arm, f, false);
    }

    
    public static boolean apply(MatrixStack var0, int var1_1, Arm var2_2, float var3_3, boolean var4_4) {
        var6_5 = HandTweaksModule.style();
        var5_6 = EffectEngine.a;
        if (var6_5 == HandTweaksModule$Style.Normal) {
            return false;
        }
        var7_7 = MinecraftClient.method_1551();
        if (var7_7.field_1724 == null) return false;
        if (var2_2 != var7_7.field_1724.method_6068()) {
            return false;
        }
        var8_8 = var3_3;
        var9_9 = MathHelper.method_15374((double)(MathHelper.method_15355((float)var8_8) * 3.1415927f));
        var10_10 = MathHelper.method_15374((double)(var8_8 * var8_8 * 3.1415927f));
        var11_11 = MathHelper.method_15374((double)(var8_8 * 3.1415927f));
        v0 = SwingStyles$1.$SwitchMap$dev$quelchi$aurora$module$modules$HandTweaksModule$Style[var6_5.ordinal()];
        if (var5_6 != 0) return v0;
        switch (v0) {
            case 1: {
                if (var4_4) {
                    SwingStyles.hand(var0, var1_1);
                }
                var0.method_46416((float)var1_1 * -0.26f * var11_11, 0.1f * MathHelper.method_15374((double)(var8_8 * 6.2831855f)), -0.14f * var11_11);
                var0.method_22907((Quaternionfc)RotationAxis.field_40716.rotationDegrees((float)var1_1 * (45.0f - 12.0f * var11_11)));
                var0.method_22907((Quaternionfc)RotationAxis.field_40718.rotationDegrees((float)var1_1 * -14.0f * var11_11));
                var0.method_22907((Quaternionfc)RotationAxis.field_40714.rotationDegrees(-52.0f * var11_11));
                var0.method_22907((Quaternionfc)RotationAxis.field_40716.rotationDegrees((float)var1_1 * -45.0f));
                if (var5_6 == 0) return true;
            }
            case 2: {
                if (var4_4) {
                    SwingStyles.hand(var0, var1_1);
                }
                var0.method_46416((float)var1_1 * -0.5f * var9_9, 0.05f * MathHelper.method_15374((double)(MathHelper.method_15355((float)var8_8) * 6.2831855f)), -0.16f * var11_11);
                var0.method_22907((Quaternionfc)RotationAxis.field_40716.rotationDegrees((float)var1_1 * (45.0f - 85.0f * var9_9)));
                var0.method_22907((Quaternionfc)RotationAxis.field_40718.rotationDegrees((float)var1_1 * -45.0f * var9_9));
                var0.method_22907((Quaternionfc)RotationAxis.field_40714.rotationDegrees(-30.0f * var9_9 - 14.0f * var10_10));
                var0.method_22907((Quaternionfc)RotationAxis.field_40716.rotationDegrees((float)var1_1 * -45.0f));
                if (var5_6 == 0) return true;
            }
            case 3: {
                if (var4_4) {
                    SwingStyles.hand(var0, var1_1);
                }
                var0.method_46416((float)var1_1 * -0.06f * var9_9, -0.05f * var9_9, -0.45f * var9_9);
                var0.method_22907((Quaternionfc)RotationAxis.field_40716.rotationDegrees((float)var1_1 * -12.0f * var9_9));
                var0.method_22907((Quaternionfc)RotationAxis.field_40718.rotationDegrees((float)var1_1 * -8.0f * var9_9));
                var0.method_22907((Quaternionfc)RotationAxis.field_40714.rotationDegrees(-60.0f * var9_9));
                if (var5_6 == 0) return true;
            }
            case 4: {
                var12_12 = HandTweaksModule.customSwing();
                if (var12_12 == null) {
                    return false;
                }
                v1 = var4_4;
                if (var5_6 != 0) // goto
                if (v1) {
                    SwingStyles.hand(var0, var1_1);
                }
                var0.method_46416((float)var1_1 * (float)var12_12[0], (float)var12_12[1], (float)var12_12[2]);
                v2 = var12_12;
                if (var5_6 != 0) // goto
                cfr_temp_0 = v2[3] - 0.0;
                v1 = cfr_temp_0 == 0.0 ? 0 : (cfr_temp_0 > 0.0 ? 1 : -1);
lbl55:

                if (v1) {
                    var0.method_22907((Quaternionfc)RotationAxis.field_40714.rotationDegrees((float)var12_12[3]));
                }
                v2 = var12_12;
lbl58:

                if (var5_6 == 0) {
                    if (v2[4] != 0.0) {
                        var0.method_22907((Quaternionfc)RotationAxis.field_40716.rotationDegrees((float)var1_1 * (float)var12_12[4]));
                    }
                    v2 = var12_12;
                }
                v3 = 5;
                if (var5_6 == 0) {
                    if (v2[v3] != 0.0) {
                        var0.method_22907((Quaternionfc)RotationAxis.field_40718.rotationDegrees((float)var1_1 * (float)var12_12[5]));
                    }
                    v2 = var12_12;
                    v3 = 6;
                }
                if (var5_6 != 0) // goto
                if (v2[v3] != 0.0) {
                    var0.method_22907((Quaternionfc)RotationAxis.field_40714.rotationDegrees(var9_9 * (float)var12_12[6]));
                }
                v2 = var12_12;
                if (var5_6 != 0) // goto
                v3 = 7;
lbl74:

                if (v2[v3] != 0.0) {
                    var0.method_22907((Quaternionfc)RotationAxis.field_40716.rotationDegrees(var9_9 * (float)var1_1 * (float)var12_12[7]));
                }
                v2 = var12_12;
lbl77:

                if (v2[8] != 0.0) {
                    var0.method_22907((Quaternionfc)RotationAxis.field_40718.rotationDegrees(var9_9 * (float)var1_1 * (float)var12_12[8]));
                }
                if (var5_6 == 0) return true;
            }
        }
        return false;
    }
}
