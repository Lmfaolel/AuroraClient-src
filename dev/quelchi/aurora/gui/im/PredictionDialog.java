package dev.quelchi.aurora.gui.im;

import dev.quelchi.aurora.gui.PredictionFx;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.ImScreen;
import dev.quelchi.aurora.gui.im.Menu;
import dev.quelchi.aurora.gui.im.Motion;
import dev.quelchi.aurora.gui.im.Ui;
import dev.quelchi.aurora.prediction.PredictionHandshake;
import imgui.ImDrawList;
import imgui.ImGui;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.input.KeyInput;

public final class PredictionDialog
extends ImScreen {
    private static final float W = 360.0f;
    private static final float H = 158.0f;
    private static final float PAD = 20.0f;
    private static final float HOLD = 1.3f;
    private static final String SHOT;
    private int answer;
    private final Set<Integer> stale = new HashSet<Integer>();
    private static final String[] b;

    public PredictionDialog() {
        super(b[3]);
    }

    private void answer(boolean n) {
        block14: {
            block15: {
                int n2;
                boolean bl;
                block12: {
                    block13: {
                        block10: {
                            PredictionDialog predictionDialog;
                            block11: {
                                bl = ImScreen.a;
                                predictionDialog = this;
                                if (bl) break block10;
                                if (predictionDialog.answer == 0) break block11;
                                return;
                            }
                            predictionDialog = this;
                        }
                        n2 = n;
                        if (bl) break block12;
                        if (n2 == 0) break block13;
                        n2 = 1;
                        break block12;
                    }
                    n2 = -1;
                }
                predictionDialog.answer = n2;
                PredictionHandshake.answered();
                if (bl) break block14;
                Motion.restart(b[7]);
                if (n != 0) break block15;
                return;
            }
            PredictionFx.skipNext();
            PredictionHandshake.accept();
        }
    }

    @Override
    protected boolean key(int n) {
        int n2;
        block20: {
            block21: {
                PredictionDialog predictionDialog;
                boolean bl;
                block16: {
                    block17: {
                        boolean bl2;
                        block18: {
                            block19: {
                                bl = ImScreen.a;
                                predictionDialog = this;
                                if (bl) break block16;
                                if (predictionDialog.answer == 0) break block17;
                                bl2 = this.stale.contains(n);
                                if (bl) break block18;
                                if (bl2) break block19;
                                MinecraftClient.method_1551().method_1507(null);
                            }
                            bl2 = true;
                        }
                        return bl2;
                    }
                    predictionDialog = this;
                }
                n2 = predictionDialog.stale.add(n);
                if (bl) break block20;
                if (n == 257) break block21;
                n2 = n;
                if (bl) break block20;
                if (n2 == 335) break block21;
                return false;
            }
            this.answer(true);
            n2 = 1;
        }
        return n2 != 0;
    }

    @Override
    public boolean method_16803(KeyInput keyInput) {
        this.stale.remove(keyInput.comp_4795());
        return super.method_16803(keyInput);
    }

    @Override
    public void method_25419() {
        PredictionHandshake.answered();
        super.method_25419();
    }

    @Override
    public boolean method_25402(Click click, boolean bl) {
        boolean bl2;
        block4: {
            block5: {
                boolean bl3 = ImScreen.a;
                bl2 = this.answer;
                if (bl3) break block4;
                if (bl2) break block5;
                return super.method_25402(click, bl);
            }
            MinecraftClient.method_1551().method_1507(null);
            bl2 = true;
        }
        return bl2;
    }

    @Override
    public void method_25420(DrawContext drawContext, int n, int n2, float f) {
    }

    @Override
    protected void draw() {
        block32: {
            PredictionDialog predictionDialog;
            ImDrawList imDrawList;
            float f;
            float f2;
            float f3;
            block31: {
                float f4;
                block27: {
                    boolean bl;
                    block28: {
                        float f5;
                        String string;
                        float f6;
                        float f7;
                        block29: {
                            block30: {
                                float f8;
                                String[] stringArray;
                                block25: {
                                    block26: {
                                        float f9;
                                        block24: {
                                            block23: {
                                                bl = ImScreen.a;
                                                if (this.answer != 0) break block23;
                                                f9 = 0.0f;
                                                break block24;
                                            }
                                            stringArray = b;
                                            f9 = Motion.since(stringArray[6]);
                                        }
                                        f3 = f9;
                                        f8 = f3;
                                        if (bl) break block25;
                                        if (!(f8 >= 1.52f)) break block26;
                                        MinecraftClient.method_1551().method_1507(null);
                                        return;
                                    }
                                    f8 = Motion.out((f3 - 1.3f) / 0.22f);
                                }
                                float f10 = f8;
                                float f11 = ImGui.getIO().getDisplaySizeX();
                                float f12 = ImGui.getIO().getDisplaySizeY();
                                ImGui.getBackgroundDrawList().addRectFilled(0.0f, 0.0f, f11, f12, Ui.col(Im.DIM, 0.66f * (1.0f - f10)));
                                ImGui.setNextWindowPos((float)0.0f, (float)0.0f);
                                ImGui.setNextWindowSize((float)f11, (float)f12);
                                ImGui.pushStyleVar((int)2, (float)0.0f, (float)0.0f);
                                stringArray = b;
                                ImGui.begin((String)stringArray[6], (int)175);
                                ImGui.popStyleVar();
                                ImGui.pushStyleVar((int)0, (float)Math.max(0.02f, 1.0f - f10));
                                f2 = Math.round((f11 - 360.0f) / 2.0f);
                                f = (float)Math.round((f12 - 158.0f) / 2.0f) + 8.0f * f10;
                                imDrawList = ImGui.getWindowDrawList();
                                Ui.fill(imDrawList, f2, f, f2 + 360.0f, f + 158.0f, Ui.col(Im.POP), 14.0f);
                                Ui.rect(imDrawList, f2, f, f2 + 360.0f, f + 158.0f, Ui.wash(0.16f), 14.0f);
                                float f13 = this.answer == 0 ? 1.0f : 1.0f - Math.min(1.0f, f3 / 0.09f);
                                float f14 = f13;
                                float f15 = f14 - 0.0f;
                                f4 = f15 == 0.0f ? 0 : (f15 > 0.0f ? 1 : -1);
                                if (bl) break block27;
                                if (f4 <= 0) break block28;
                                ImGui.pushStyleVar((int)0, (float)(ImGui.getStyle().getAlpha() * Math.max(0.02f, f14)));
                                stringArray = b;
                                Ui.text(Im.bold, f2 + 20.0f, f + 20.0f, Im.INK, stringArray[2]);
                                Ui.text(Im.sub, f2 + 20.0f, f + 48.0f, Im.SUB, stringArray[5]);
                                Ui.text(Im.sub, f2 + 20.0f, f + 67.0f, Im.SUB, stringArray[0]);
                                f7 = 156.0f;
                                f6 = f + 158.0f - 20.0f - 34.0f;
                                string = stringArray[1];
                                f5 = f2;
                                if (bl) break block29;
                                if (!Menu.button(string, f5 + 20.0f, f6, f7, 34.0f, false)) break block30;
                                this.answer(false);
                            }
                            string = b[9];
                            f5 = f2 + 20.0f + f7 + 8.0f;
                        }
                        if (Menu.button(string, f5, f6, f7, 34.0f, true)) {
                            this.answer(true);
                        }
                        ImGui.popStyleVar();
                    }
                    predictionDialog = this;
                    if (bl) break block31;
                    f4 = predictionDialog.answer;
                }
                if (f4 == false) break block32;
                predictionDialog = this;
            }
            predictionDialog.answered(imDrawList, f2 + 180.0f, f + 79.0f, f3);
        }
        ImGui.popStyleVar();
        ImGui.end();
    }

    
    private void answered(ImDrawList imDrawList, float f, float f2, float f3) {
        float f4;
        int n;
        block15: {
            int n2;
            float f5;
            float f6;
            block13: {
                block14: {
                    boolean bl = ImScreen.a;
                    int n3 = this.answer;
                    if (!bl) {
                        n3 = n3 > 0 ? 1 : 0;
                    }
                    n = n3;
                    float f7 = Math.max(0.0f, Math.min(1.0f, (f3 - 0.09f) / 0.15f));
                    float f8 = Math.max(0.0f, Math.min(1.0f, (f3 - 0.09f) / 0.26f)) - 1.0f;
                    float f9 = 1.0f + 2.4f * f8 * f8 * f8 + 1.4f * f8 * f8;
                    f4 = f2 - 12.0f;
                    f6 = 0.6666667f * f9;
                    f5 = Motion.out((f3 - 0.3f) / 0.32f);
                    ImGui.pushStyleVar((int)0, (float)(ImGui.getStyle().getAlpha() * Math.max(0.02f, f7)));
                    int n4 = n;
                    if (!bl) {
                        n4 = n4 != 0 ? Ui.col(Im.GREEN, 0.16f) : Ui.col(Im.QUIET);
                    }
                    imDrawList.addCircleFilled(f, f4, 17.0f * f9, n4, 32);
                    n2 = Ui.col(n != 0 ? Im.GREEN : Im.MUTE);
                    if (bl) break block13;
                    if (n == 0) break block14;
                    float f10 = f5 * 19.5f;
                    PredictionDialog.line(imDrawList, f, f4, f6, 5.0f, 12.5f, 9.5f, 17.0f, Math.min(1.0f, f10 / 6.4f), n2);
                    PredictionDialog.line(imDrawList, f, f4, f6, 9.5f, 17.0f, 19.0f, 7.5f, (f10 - 6.4f) / 13.1f, n2);
                    if (!bl) break block15;
                }
                PredictionDialog.line(imDrawList, f, f4, f6, 7.0f, 7.0f, 17.0f, 17.0f, Math.min(1.0f, f5 * 2.0f), n2);
            }
            PredictionDialog.line(imDrawList, f, f4, f6, 17.0f, 7.0f, 7.0f, 17.0f, f5 * 2.0f - 1.0f, n2);
        }
        String string = n != 0 ? b[4] : b[8];
        Ui.text(Im.sora13, f - (float)Math.floor(Ui.width(Im.sora13, string) / 2.0f), f4 + 25.0f, n != 0 ? Im.INK : Im.MUTE, string);
        ImGui.popStyleVar();
    }

    private static void line(ImDrawList imDrawList, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int n) {
        if (f8 <= 0.0f) {
            return;
        }
        imDrawList.addLine(f + (f4 - 12.0f) * f3, f2 + (f5 - 12.0f) * f3, f + (f4 + (f6 - f4) * f8 - 12.0f) * f3, f2 + (f5 + (f7 - f5) * f8 - 12.0f) * f3, n, 1.6f);
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[10];
                var3_1 = 0;
                var2_2 = "\u0003pni+?73.7{1=3/pd:>0?`rr{-2(`pr{<*{)ldn>0/,{9\u0007\u0004gtv60>\u0011\u0003n~\u007f1*{0pr~6=/)my\n\u0010pr~6=/)my\r\u0010pr~6=/)my:00'\u0014j~i\u007f->2trh\u007f-.0rxh+-{\u0001weu-?{0pr~6=/)my4\n0pr~6=/)my\n0pr~6=/)my";
                var4_3 = "\u0003pni+?73.7{1=3/pd:>0?`rr{-2(`pr{<*{)ldn>0/,{9\u0007\u0004gtv60>\u0011\u0003n~\u007f1*{0pr~6=/)my\n\u0010pr~6=/)my\r\u0010pr~6=/)my:00'\u0014j~i\u007f->2trh\u007f-.0rxh+-{\u0001weu-?{0pr~6=/)my4\n0pr~6=/)my\n0pr~6=/)my".length();
                var1_4 = 45;
                var0_5 = -1;
lbl7:

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block18;
                    break;
                }
lbl12:

                while (true) {
                    var5[var3_1++] = v3.intern();
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        continue;
                    }
                    var2_2 = "\u0010pr~6=/)my:08=\u0006\u0001at\u007f/*";
                    var4_3 = "\u0010pr~6=/)my:08=\u0006\u0001at\u007f/*".length();
                    var1_4 = 14;
                    var0_5 = -1;
lbl21:

                    while (true) {
                        v4 = ++var0_5;
                        v1 = var2_2.substring(v4, v4 + var1_4);
                        v2 = 0;
                        break block18;
                        break;
                    }
                    break;
                }
lbl26:

                while (true) {
                    var5[var3_1++] = v3.intern();
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        continue;
                    }
                    break block19;
                    break;
                }
            }
            v5 = v1.toCharArray();
            v6 = v5;
            v7 = v5.length;
            var6_6 = 0;
            do {
                v6 = v6;
                v8 = var6_6;
                v9 = v6[v8];
                switch (var6_6 % 7) {
                    case 0: {
                        v10 = 64;
                        break;
                    }
                    case 1: {
                        v10 = 2;
                        break;
                    }
                    case 2: {
                        v10 = 23;
                        break;
                    }
                    case 3: {
                        v10 = 26;
                        break;
                    }
                    case 4: {
                        v10 = 95;
                        break;
                    }
                    case 5: {
                        v10 = 94;
                        break;
                    }
                    default: {
                        v10 = 91;
                    }
                }
                v6[v8] = (char)(v9 ^ v10);
                ++var6_6;
lbl65:

                v7 = v7;
            } while (v7 > var6_6);
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
        PredictionDialog.b = var5;
        PredictionDialog.SHOT = PredictionDialog.b[6];
    }
}
