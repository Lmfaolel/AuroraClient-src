package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.duck.GlassContext;
import dev.quelchi.aurora.gui.PreviewScene$Figure;
import dev.quelchi.aurora.gui.PreviewScene$Grid;
import dev.quelchi.aurora.gui.PreviewScene$Main;
import dev.quelchi.aurora.gui.PreviewScene$Spec;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.ScreenRect;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.gui.render.state.special.SpecialGuiElementRenderState;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fc;
import org.joml.Vector2f;

public final class PreviewScene {
    private static volatile boolean broken;
    public static int a;

    private PreviewScene() {
    }

    public static boolean usable() {
        boolean bl;
        block2: {
            block3: {
                int n = a;
                bl = broken;
                if (n != 0) break block2;
                if (bl) break block3;
                bl = true;
                break block2;
            }
            bl = false;
        }
        return bl;
    }

    public static void main(DrawContext drawContext, List<PreviewScene$Spec> list, int n, int n2, int n3, int n4) {
        PreviewScene.submit(drawContext, list, n, n2, n3, n4, false);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void submit(DrawContext drawContext, List<PreviewScene$Spec> list, int n, int n2, int n3, int n4, boolean bl) {
        PreviewScene$Spec previewScene$Spec2;
        int n5;
        int n6;
        int n7;
        int n8;
        int n9;
        Matrix3x2f matrix3x2f;
        GlassContext glassContext;
        int n10;
        block21: {
            DrawContext drawContext2;
            block20: {
                boolean bl2;
                block19: {
                    n10 = a;
                    bl2 = list.isEmpty();
                    if (n10 != 0) break block19;
                    if (bl2) return;
                    drawContext2 = drawContext;
                    if (n10 != 0) break block20;
                    bl2 = drawContext2 instanceof GlassContext;
                }
                if (!bl2) return;
                drawContext2 = drawContext;
            }
            glassContext = (GlassContext)drawContext2;
            if (n10 != 0) {
                return;
            }
            matrix3x2f = new Matrix3x2f((Matrix3x2fc)drawContext.method_51448());
            Vector2f vector2f = matrix3x2f.transformPosition(new Vector2f((float)n, (float)n2));
            Vector2f vector2f2 = matrix3x2f.transformPosition(new Vector2f((float)n3, (float)n4));
            n9 = Math.round(Math.min(vector2f.x, vector2f2.x));
            n8 = Math.round(Math.min(vector2f.y, vector2f2.y));
            n7 = Math.round(Math.max(vector2f.x, vector2f2.x));
            n6 = Math.round(Math.max(vector2f.y, vector2f2.y));
            n5 = n7;
            if (n10 != 0) break block21;
            if (n5 <= n9) return;
            n5 = n6;
        }
        if (n5 <= n8) {
            return;
        }
        float f = Math.abs(matrix3x2f.m00());
        ArrayList<PreviewScene$Figure> arrayList = new ArrayList<PreviewScene$Figure>(list.size());
        for (PreviewScene$Spec previewScene$Spec2 : list) {
            Vector2f vector2f = matrix3x2f.transformPosition(new Vector2f(previewScene$Spec2.cx(), previewScene$Spec2.cy()));
            arrayList.add(new PreviewScene$Figure(previewScene$Spec2.entity(), previewScene$Spec2.offset(), vector2f.x, vector2f.y, previewScene$Spec2.size() * f, previewScene$Spec2.translation(), previewScene$Spec2.rotation(), previewScene$Spec2.cameraAngle(), previewScene$Spec2.yawDeg(), previewScene$Spec2.specks(), previewScene$Spec2.colors(), previewScene$Spec2.count(), previewScene$Spec2.geometry(), previewScene$Spec2.actors()));
            if (n10 == 0) continue;
        }
        ScreenRect screenRect = glassContext.aurora$scissor();
        previewScene$Spec2 = SpecialGuiElementRenderState.method_71535((int)n9, (int)n8, (int)n7, (int)n6, (ScreenRect)screenRect);
        GuiRenderState guiRenderState = glassContext.aurora$guiState();
        Record record = bl ? new PreviewScene$Grid(List.copyOf(arrayList), n9, n8, n7, n6, 1.0f, screenRect, (ScreenRect)previewScene$Spec2) : new PreviewScene$Main(List.copyOf(arrayList), n9, n8, n7, n6, 1.0f, screenRect, (ScreenRect)previewScene$Spec2);
        guiRenderState.method_70922((SpecialGuiElementRenderState)record);
    }
}
