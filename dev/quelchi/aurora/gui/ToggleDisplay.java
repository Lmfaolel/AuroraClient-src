package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.gui.Anim;
import dev.quelchi.aurora.gui.PreviewScene;
import dev.quelchi.aurora.gui.Round;
import dev.quelchi.aurora.gui.Theme;
import dev.quelchi.aurora.gui.ToggleDisplay$Entry;
import java.util.ArrayDeque;
import java.util.Deque;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public final class ToggleDisplay {
    private static final Deque<ToggleDisplay$Entry> QUEUE;
    private static final long LIFE_MS;
    private static final long FADE_MS;
    private static final int MAX_ON_SCREEN;
    private static final int H;
    private static final int SW;
    private static final int SH;
    private static final int KNOB;
    private static final int TRACK_OFF;
    private static final int TRACK_ON;
    private static final int KNOB_OFF;
    private static final long[] a;
    private static final Integer[] b;
    private static final long[] c;
    private static final Long[] d;

    private ToggleDisplay() {
    }

    private static float ease(float f) {
        float f2 = 1.0f - f;
        return 1.0f - f2 * f2 * f2;
    }

    
    public static void push(String string, boolean bl) {
        Deque<ToggleDisplay$Entry> deque = QUEUE;
        synchronized (deque) {
            block7: {
                Deque<ToggleDisplay$Entry> deque2;
                int n = PreviewScene.a;
                QUEUE.removeIf(arg_0 -> ToggleDisplay.lambda$push$0(string, arg_0));
                QUEUE.addLast(new ToggleDisplay$Entry(string, bl, Anim.millis()));
                while (QUEUE.size() > 4) {
                    deque2 = QUEUE;
                    if (n == 0) {
                        Anim.reset(deque2.removeFirst());
                        if (n == 0) continue;
                        break;
                    }
                    break block7;
                }
                deque2 = deque;
            }
            return;
        }
    }

    
    public static void clear() {
        Deque<ToggleDisplay$Entry> deque = QUEUE;
        synchronized (deque) {
            QUEUE.forEach(Anim::reset);
            QUEUE.clear();
        }
    }

    
    public static void render(DrawContext drawContext) {
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        int n = PreviewScene.a;
        if (minecraftClient == null) return;
        if (QUEUE.isEmpty()) {
            return;
        }
        long l = Anim.millis();
        Deque<ToggleDisplay$Entry> deque = QUEUE;
        synchronized (deque) {
            int n2 = QUEUE.removeIf(arg_0 -> ToggleDisplay.lambda$render$1(l, arg_0));
            if (n == 0) {
                if (QUEUE.isEmpty()) {
                    return;
                }
                n2 = drawContext.method_51421();
            }
            int n3 = n2;
            int n4 = drawContext.method_51443();
            int n5 = n4 - 80;
            ToggleDisplay$Entry[] toggleDisplay$EntryArray = QUEUE.toArray(new ToggleDisplay$Entry[0]);
            int i = toggleDisplay$EntryArray.length - 1;
            do {
                block18: {
                    float f;
                    int n9;
                    float f4;
                    float f5;
                    long l2;
                    ToggleDisplay$Entry toggleDisplay$Entry;
                    block17: {
                        if (i < 0) return;
                        toggleDisplay$Entry = toggleDisplay$EntryArray[i];
                        l2 = l - toggleDisplay$Entry.shown();
                        f5 = Math.min(1.0f, (float)l2 / 200.0f);
                        float f6 = Math.min(1.0f, (float)(ToggleDisplay.b(14582, 420704056176473148L) - l2) / 200.0f);
                        if (n != 0) return;
                        f4 = Math.max(0.0f, Math.min(f5, f6));
                        n9 = n5;
                        n5 -= 28;
                        float f2 = f4 - 0.01f;
                        f = f2 == 0.0f ? 0 : (f2 < 0.0f ? -1 : 1);
                        if (n != 0) break block17;
                        if (f <= 0) break block18;
                        f = 10 + minecraftClient.field_1772.method_1727(toggleDisplay$Entry.name()) + 9 + 24 + 9;
                    }
                    float f2 = f;
                    int n8 = (n3 - f2) / 2;
                    int n7 = Math.round(Anim.px(toggleDisplay$Entry, n9, 16.0f)) + Math.round((1.0f - ToggleDisplay.ease(f5)) * 5.0f);
                    int n6 = Math.round(255.0f * f4);
                    Round.rect(drawContext, n8, n7, (int)f2, 24, 12, Theme.alpha(ToggleDisplay.a(27223, 7064435383132078489L), n6));
                    Round.outline(drawContext, n8, n7, (int)f2, 24, 12, Theme.alpha(ToggleDisplay.a(31898, 5434447340970235739L), Math.round(41.0f * f4)));
                    float f3 = f4;
                    if (n == 0) {
                        if (f3 > 0.05f) {
                            drawContext.method_51433(minecraftClient.field_1772, toggleDisplay$Entry.name(), n8 + 10, n7 + 8, Theme.alpha(ToggleDisplay.a(15867, 3599304939363669566L), n6), false);
                        }
                        f3 = Anim.outCubic(Math.max(0.0f, (float)(l2 - ToggleDisplay.b(13695, 35563015506524593L)) / 150.0f));
                    }
                    float f8 = f3;
                    float f10 = toggleDisplay$Entry.on() ? f8 : 1.0f - f8;
                    int n10 = n8 + f2 - 9 - 24;
                    int n11 = n7 + 6;
                    Round.rect(drawContext, n10, n11, 24, 12, 6, Theme.alpha(Theme.mix(ToggleDisplay.a(10170, 4182103287888874618L), ToggleDisplay.a(28069, 932002031955035759L), f10), n6));
                    Round.circle(drawContext, n10 + 6 + Math.round(f10 * 12.0f), n11 + 6, 8, Theme.alpha(Theme.mix(ToggleDisplay.a(16256, 7832230343870747714L), ToggleDisplay.a(16424, 4327737821769165806L), f10), n6));
                }
                --i;
            } while (n == 0);
            return;
        }
    }

    private static boolean lambda$render$1(long l, ToggleDisplay$Entry toggleDisplay$Entry) {
        long l2;
        block4: {
            block5: {
                int n = PreviewScene.a;
                long l3 = l - toggleDisplay$Entry.shown() - ToggleDisplay.b(10144, 2626973068955118447L);
                l2 = l3 == 0L ? 0 : (l3 < 0L ? -1 : 1);
                if (n != 0) break block4;
                if (l2 > 0) break block5;
                return false;
            }
            Anim.reset(toggleDisplay$Entry);
            l2 = 1;
        }
        return (boolean)l2;
    }

    private static boolean lambda$push$0(String string, ToggleDisplay$Entry toggleDisplay$Entry) {
        ToggleDisplay$Entry toggleDisplay$Entry2;
        block4: {
            block5: {
                int n = PreviewScene.a;
                toggleDisplay$Entry2 = toggleDisplay$Entry;
                if (n != 0) break block4;
                if (toggleDisplay$Entry2.name().equals(string)) break block5;
                return false;
            }
            toggleDisplay$Entry2 = toggleDisplay$Entry;
        }
        Anim.reset(toggleDisplay$Entry2);
        return true;
    }

    
    static {
        block17: {
            block16: {
                block15: {
                    block14: {
                        var8 = 6697725656424698975L;
                        var14_1 = new long[14];
                        var11_2 = 0;
                        var12_3 = "\u00fc`A(\u00af,4\u009a\u0098\u00be\u00ac\u00ec\u00d5\u009d\u009etq\u0004\u00b1@\u00c2\u008c\u00f5\u001f\u00e4\u0098%\u00da\u00d7\u00d1\u00fc\u00fbBz@\u00cc\u0001[\u00c0N\u0014\u00a5\u0012\u00dd\u0097\u00aa\u00f7\u00bee\u0005d\u001d\u00df\"\u00ca\u00ef\"\u00fe\u00f98\u00d8\u00d3\u00b1\u0096\u00aa\u001a{\u00b6f\u00f0\u00d0\u00ef\u00c7I$\u00a2\u00c1#\u00f3I\u00ea\u0085do\u0083AZ\u00f5R\u00e63\u00ec\u008e\u0013\u00a4\u00de";
                        var13_4 = "\u00fc`A(\u00af,4\u009a\u0098\u00be\u00ac\u00ec\u00d5\u009d\u009etq\u0004\u00b1@\u00c2\u008c\u00f5\u001f\u00e4\u0098%\u00da\u00d7\u00d1\u00fc\u00fbBz@\u00cc\u0001[\u00c0N\u0014\u00a5\u0012\u00dd\u0097\u00aa\u00f7\u00bee\u0005d\u001d\u00df\"\u00ca\u00ef\"\u00fe\u00f98\u00d8\u00d3\u00b1\u0096\u00aa\u001a{\u00b6f\u00f0\u00d0\u00ef\u00c7I$\u00a2\u00c1#\u00f3I\u00ea\u0085do\u0083AZ\u00f5R\u00e63\u00ec\u008e\u0013\u00a4\u00de".length();
                        var10_5 = 0;
                        while (true) {
                            var15_6 = var12_3.substring(var10_5, var10_5 += 8).getBytes("ISO-8859-1");
                            v0 = var14_1;
                            v1 = var11_2++;
                            v2 = ((long)var15_6[0] & 255L) << 56 | ((long)var15_6[1] & 255L) << 48 | ((long)var15_6[2] & 255L) << 40 | ((long)var15_6[3] & 255L) << 32 | ((long)var15_6[4] & 255L) << 24 | ((long)var15_6[5] & 255L) << 16 | ((long)var15_6[6] & 255L) << 8 | (long)var15_6[7] & 255L;
                            v3 = -1;
                            break block14;
                            break;
                        }
lbl14:

                        while (true) {
                            v0[v1] = v4;
                            if (var10_5 < var13_4) continue;
                            var12_3 = "\u00fd\u0017\r\u00c1\u00d4o\u0007\u00dc\u0099\u00c6\u00e9\u0018\u00c6}[\u00af";
                            var13_4 = "\u00fd\u0017\r\u00c1\u00d4o\u0007\u00dc\u0099\u00c6\u00e9\u0018\u00c6}[\u00af".length();
                            var10_5 = 0;
                            while (true) {
                                var15_6 = var12_3.substring(var10_5, var10_5 += 8).getBytes("ISO-8859-1");
                                v0 = var14_1;
                                v1 = var11_2++;
                                v2 = ((long)var15_6[0] & 255L) << 56 | ((long)var15_6[1] & 255L) << 48 | ((long)var15_6[2] & 255L) << 40 | ((long)var15_6[3] & 255L) << 32 | ((long)var15_6[4] & 255L) << 24 | ((long)var15_6[5] & 255L) << 16 | ((long)var15_6[6] & 255L) << 8 | (long)var15_6[7] & 255L;
                                v3 = 0;
                                break block14;
                                break;
                            }
                            break;
                        }
lbl27:

                        while (true) {
                            v0[v1] = v4;
                            if (var10_5 < var13_4) continue;
                            break block15;
                            break;
                        }
                    }
                    v4 = v2 ^ var8;
                    switch (v3) {
                        default: {
                            continue;
                        }
                        case 0:
lbl38:

                        continue;
                    }
                }
                ToggleDisplay.a = var14_1;
                ToggleDisplay.b = new Integer[14];
                ToggleDisplay.SW = ToggleDisplay.a(25072, 8063653155133147699L);
                ToggleDisplay.TRACK_ON = ToggleDisplay.a(30635, 6965198510044830831L);
                ToggleDisplay.KNOB = ToggleDisplay.a(9578, 8868589321812676258L);
                ToggleDisplay.H = ToggleDisplay.a(25072, 8063653155133147699L);
                ToggleDisplay.KNOB_OFF = ToggleDisplay.a(30810, 2081980149820838801L);
                ToggleDisplay.MAX_ON_SCREEN = ToggleDisplay.a(9275, 9073511996561526772L);
                ToggleDisplay.SH = ToggleDisplay.a(23364, 2193741511267807373L);
                ToggleDisplay.TRACK_OFF = ToggleDisplay.a(9500, 8672941728648007387L);
                var0_7 = 3880542082484529131L;
                var6_8 = new long[5];
                var3_9 = 0;
                var4_10 = "5\u00a4,)\u0000\u0019\u009e\u0000\u0011\u00ae\u0096`K\u00e7\u0089Xv5\u00eda\u00c6\u00d4\u00c2\u00f1";
                var5_11 = "5\u00a4,)\u0000\u0019\u009e\u0000\u0011\u00ae\u0096`K\u00e7\u0089Xv5\u00eda\u00c6\u00d4\u00c2\u00f1".length();
                var2_12 = 0;
                while (true) {
                    var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
                    v5 = var6_8;
                    v6 = var3_9++;
                    v7 = ((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L;
                    v8 = -1;
                    break block16;
                    break;
                }
lbl63:

                while (true) {
                    v5[v6] = v9;
                    if (var2_12 < var5_11) continue;
                    var4_10 = "y\u00ea\u009c\u00fd\u00a7}\u0096j0\f\u00d0j\u00e5\u0087\u0016\u000b";
                    var5_11 = "y\u00ea\u009c\u00fd\u00a7}\u0096j0\f\u00d0j\u00e5\u0087\u0016\u000b".length();
                    var2_12 = 0;
                    while (true) {
                        var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
                        v5 = var6_8;
                        v6 = var3_9++;
                        v7 = ((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L;
                        v8 = 0;
                        break block16;
                        break;
                    }
                    break;
                }
lbl76:

                while (true) {
                    v5[v6] = v9;
                    if (var2_12 < var5_11) continue;
                    break block17;
                    break;
                }
            }
            v9 = v7 ^ var0_7;
            switch (v8) {
                default: {
                    continue;
                }
                case 0:
lbl87:

                continue;
            }
        }
        ToggleDisplay.c = var6_8;
        ToggleDisplay.d = new Long[5];
        ToggleDisplay.FADE_MS = ToggleDisplay.b(26910, 4895299644250465746L);
        ToggleDisplay.LIFE_MS = ToggleDisplay.b(14480, 5490143631292265565L);
        ToggleDisplay.QUEUE = new ArrayDeque<ToggleDisplay$Entry>();
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2BC2;
        if (b[n2] == null) {
            ToggleDisplay.b[n2] = (int)(a[n2] ^ l);
        }
        return b[n2];
    }

    private static long b(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x4CCE) & Short.MAX_VALUE;
        if (d[n2] == null) {
            ToggleDisplay.d[n2] = c[n2] ^ l;
        }
        return d[n2];
    }
}
