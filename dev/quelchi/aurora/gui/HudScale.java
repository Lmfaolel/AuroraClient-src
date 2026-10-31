package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.gui.GlassScreen;
import dev.quelchi.aurora.gui.PreviewScene;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;

public final class HudScale {
    public static final int REF;
    private static final int MENU_W;
    private static final int MENU_H;
    private static final float MENU_MIN = 0.4f;
    private static final float MENU_MAX = 2.5f;

    private HudScale() {
    }

    private static float ref(MinecraftClient minecraftClient) {
        int n = minecraftClient.method_22683().method_4480();
        float f = n <= 0 ? 2.0f : 2.0f * Math.max(1.0f, (float)minecraftClient.method_22683().method_4489() / (float)n);
        return f;
    }

    public static float menuFit() {
        int n;
        MinecraftClient minecraftClient;
        block10: {
            block11: {
                MinecraftClient minecraftClient2;
                int n2;
                block8: {
                    block9: {
                        minecraftClient = MinecraftClient.method_1551();
                        n2 = PreviewScene.a;
                        minecraftClient2 = minecraftClient;
                        if (n2 != 0) break block8;
                        if (minecraftClient2.method_22683() != null) break block9;
                        return 1.0f;
                    }
                    minecraftClient2 = minecraftClient;
                }
                Screen screen = minecraftClient2.field_1755;
                n = screen instanceof GlassScreen;
                if (n2 != 0) break block10;
                if (n != 0) break block11;
                return 1.0f;
            }
            n = Math.max(1, minecraftClient.method_22683().method_4495());
        }
        int n3 = n;
        return HudScale.menuFactor() * (float)n3 / HudScale.ref(minecraftClient);
    }

    public static float menuFactor() {
        MinecraftClient minecraftClient;
        MinecraftClient minecraftClient2;
        block4: {
            block5: {
                minecraftClient2 = MinecraftClient.method_1551();
                int n = PreviewScene.a;
                minecraftClient = minecraftClient2;
                if (n != 0) break block4;
                if (minecraftClient.method_22683() != null) break block5;
                return HudScale.factor();
            }
            minecraftClient = minecraftClient2;
        }
        float f = Math.min((float)minecraftClient.method_22683().method_4486() / 960.0f, (float)minecraftClient2.method_22683().method_4502() / 540.0f);
        return Math.max(0.4f, Math.min(2.5f, f));
    }

    public static float factor() {
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        if (minecraftClient.method_22683() == null) {
            return 1.0f;
        }
        int n = Math.max(1, minecraftClient.method_22683().method_4495());
        return HudScale.ref(minecraftClient) / (float)n * HudScale.menuFit();
    }

    public static int width() {
        MinecraftClient minecraftClient;
        MinecraftClient minecraftClient2;
        block4: {
            block5: {
                minecraftClient2 = MinecraftClient.method_1551();
                int n = PreviewScene.a;
                minecraftClient = minecraftClient2;
                if (n != 0) break block4;
                if (minecraftClient.method_22683() != null) break block5;
                return 0;
            }
            minecraftClient = minecraftClient2;
        }
        return Math.round((float)minecraftClient.method_22683().method_4489() / (HudScale.ref(minecraftClient2) * HudScale.menuFit()));
    }

    public static int height() {
        MinecraftClient minecraftClient;
        MinecraftClient minecraftClient2;
        block4: {
            block5: {
                minecraftClient2 = MinecraftClient.method_1551();
                int n = PreviewScene.a;
                minecraftClient = minecraftClient2;
                if (n != 0) break block4;
                if (minecraftClient.method_22683() != null) break block5;
                return 0;
            }
            minecraftClient = minecraftClient2;
        }
        return Math.round((float)minecraftClient.method_22683().method_4506() / (HudScale.ref(minecraftClient2) * HudScale.menuFit()));
    }

    public static void push(DrawContext drawContext) {
        float f = HudScale.factor();
        drawContext.method_51448().pushMatrix();
        drawContext.method_51448().scale(f, f);
    }

    public static void pop(DrawContext drawContext) {
        drawContext.method_51448().popMatrix();
    }

    public static double toVirtual(double d) {
        float f = HudScale.factor();
        double d2 = f == 0.0f ? d : d / (double)f;
        return d2;
    }

    
    static {
        long l = 8681584635175937519L;
        long[] lArray = new long[3];
        int n = 0;
        String string = "\u009d\u00ae\u00a6\u00d3\u008b&\u00a7\u00f31*.\u009e\u008b&\u00a6/\u0000\u00bc\"B\u008b&\u00a5\u00ed";
        int n2 = "\u009d\u00ae\u00a6\u00d3\u008b&\u00a7\u00f31*.\u009e\u008b&\u00a6/\u0000\u00bc\"B\u008b&\u00a5\u00ed".length();
        int n3 = 0;
        do {
            byte[] byArray = string.substring(n3, n3 += 8).getBytes("ISO-8859-1");
            int n4 = n++;
            lArray[n4] = (((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL) ^ l;
        } while (n3 < n2);
        REF = (int)lArray[2];
        MENU_H = (int)lArray[0];
        MENU_W = (int)lArray[1];
    }
}
