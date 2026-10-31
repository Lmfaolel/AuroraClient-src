package dev.quelchi.aurora.chat;

import dev.quelchi.aurora.chat.Emoji;
import dev.quelchi.aurora.gui.Round;
import java.lang.invoke.LambdaMetafactory;
import java.util.List;
import java.util.function.IntPredicate;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

public final class EmojiSuggest {
    private static List<String> matches;
    private static int index;
    private static int start;
    private static int end;
    private static int caret;
    private static String dismissed;
    private static final int ROW;
    private static final int PAD;
    private static final int ROWS;
    private static final String[] a;
    private static final long[] b;
    private static final Integer[] c;

    private EmojiSuggest() {
    }

    public static boolean open() {
        boolean bl;
        block2: {
            block3: {
                boolean bl2 = Emoji.a;
                bl = matches.isEmpty();
                if (bl2) break block2;
                if (bl) break block3;
                bl = true;
                break block2;
            }
            bl = false;
        }
        return bl;
    }

    public static int caret() {
        return caret;
    }

    public static void close() {
        matches = List.of();
        index = 0;
        end = -1;
        start = -1;
        dismissed = null;
    }

    public static void dismiss() {
        dismissed = EmojiSuggest.key();
        matches = List.of();
        index = 0;
    }

    public static void move(int n) {
        List<String> list;
        block4: {
            block5: {
                boolean bl = Emoji.a;
                list = matches;
                if (bl) break block4;
                if (!list.isEmpty()) break block5;
                return;
            }
            list = matches;
        }
        int n2 = list.size();
        index = ((index + n) % n2 + n2) % n2;
    }

    private static String key() {
        String string;
        block6: {
            int n;
            block4: {
                block5: {
                    boolean bl = Emoji.a;
                    n = start;
                    if (bl) break block4;
                    if (n >= 0) break block5;
                    string = null;
                    break block6;
                }
                n = start;
            }
            string = n + ":" + end;
        }
        return string;
    }

    
    public static void update(String var0, int var1_1) {
        block57: {
            block58: {
                block55: {
                    block56: {
                        block54: {
                            block52: {
                                block53: {
                                    block50: {
                                        block51: {
                                            block48: {
                                                block49: {
                                                    block46: {
                                                        block44: {
                                                            block45: {
                                                                block43: {
                                                                    var2_2 = Emoji.a;
                                                                    if (var0 != null) {
                                                                        v0 = var1_1;
                                                                        if (var2_2) break block43;
                                                                    }
                                                                    if (v0 >= 0) {
                                                                    }
                                                                    v0 = var1_1;
                                                                }
                                                                if (var2_2) break block44;
                                                                if (v0 <= var0.length()) break block45;
lbl25:

                                                                EmojiSuggest.close();
                                                                return;
                                                            }
                                                            v0 = -1;
                                                        }
                                                        var3_3 = v0;
                                                        for (var4_4 = var1_1 - 1; var4_4 >= 0; --var4_4) {
                                                            block47: {
                                                                v5 = var0.charAt(var4_4);
                                                                if (var2_2) break block46;
                                                                var5_6 = v5;
                                                                v6 = var5_6;
                                                                if (var2_2) break block47;
                                                                if (v6 == 58) {
                                                                }
                                                                var3_3 = var4_4;
                                                                if (!var2_2) break;
lbl48:

                                                                v6 = (int)Character.isWhitespace((char)var5_6);
                                                            }
                                                            if (v6 != 0) break;
                                                            if (!var2_2) continue;
                                                        }
                                                        v5 = var3_3;
                                                    }
                                                    if (v5 < 0) {
                                                        EmojiSuggest.close();
                                                        return;
                                                    }
                                                    var4_5 = var0.substring(var3_3 + 1, var1_1);
                                                    v10 = var4_5;
                                                    if (var2_2) break block48;
                                                    if (!v10.isEmpty()) break block49;
                                                    EmojiSuggest.close();
                                                    return;
                                                }
                                                v10 = var4_5;
                                            }
                                            if (var2_2) break block50;
                                            if (v10.chars().allMatch((IntPredicate)LambdaMetafactory.metafactory(null, null, null, (I)Z, lambda$update$0(int ), (I)Z)())) break block51;
                                            EmojiSuggest.close();
                                            return;
                                        }
                                        EmojiSuggest.start = var3_3;
                                        EmojiSuggest.end = var1_1;
                                        v10 = EmojiSuggest.key();
                                    }
                                    var5_7 = v10;
                                    if (var2_2) break block52;
                                    if (var5_7 == null) break block53;
                                    v16 = var5_7;
                                    if (var2_2) break block54;
                                    if (!v16.equals(EmojiSuggest.dismissed)) break block53;
                                    EmojiSuggest.matches = List.of();
                                    return;
                                }
                                EmojiSuggest.dismissed = null;
                            }
                            v16 = var4_5;
                        }
                        var6_8 = Emoji.matching(v16);
                        v20 = var6_8;
                        if (var2_2) break block55;
                        if (!v20.isEmpty()) break block56;
                        EmojiSuggest.matches = List.of();
                        return;
                    }
                    v20 = var6_8;
                }
                if (var2_2) break block57;
                if (v20.equals(EmojiSuggest.matches)) break block58;
                EmojiSuggest.index = 0;
            }
            v20 = var6_8;
        }
        EmojiSuggest.matches = v20;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String complete(String string, int n) {
        block10: {
            int n2;
            block9: {
                boolean bl = Emoji.a;
                n2 = matches.isEmpty();
                if (bl) break block9;
                if (n2 != 0) return null;
                n2 = start;
            }
            if (n2 >= 0 && string != null) break block10;
            return null;
        }
        if (n > string.length()) {
            return null;
        }
        String string2 = matches.get(Math.min(index, matches.size() - 1));
        String string3 = string.substring(0, start);
        String string4 = string.substring(Math.min(n, string.length()));
        String string5 = Emoji.charsOf(string2);
        caret = string3.length() + string5.length();
        return string3 + string5 + string4;
    }

    public static void render(DrawContext drawContext, int n) {
        int n2;
        int n3;
        int n4;
        int n5;
        int n6;
        int n7;
        TextRenderer textRenderer;
        boolean bl;
        block25: {
            MinecraftClient minecraftClient;
            block23: {
                MinecraftClient minecraftClient2;
                block24: {
                    bl = Emoji.a;
                    if (matches.isEmpty()) {
                        return;
                    }
                    minecraftClient2 = MinecraftClient.method_1551();
                    minecraftClient = minecraftClient2;
                    if (bl) break block23;
                    if (minecraftClient != null) break block24;
                    return;
                }
                minecraftClient = minecraftClient2;
            }
            textRenderer = minecraftClient.field_1772;
            n7 = Math.min(matches.size(), 6);
            n6 = Math.max(0, Math.min(index - n7 / 2, matches.size() - n7));
            int n8 = 0;
            String[] stringArray = a;
            n5 = textRenderer.method_1727(stringArray[1]) + 8;
            for (n4 = n6; n4 < n6 + n7; ++n4) {
                n3 = n8;
                n2 = textRenderer.method_1727(":" + matches.get(n4) + ":");
                if (!bl) {
                    n8 = Math.max(n3, n2);
                    if (!bl) continue;
                }
                break block25;
            }
            n4 = 27 + n8 + 10 + n5 + 6;
            n3 = 6 + n7 * 21;
            n2 = (n7 - 1) * 2;
        }
        int n9 = n3 + n2;
        int n10 = 2;
        int n11 = n - 14 - 4 - n9;
        Round.rect(drawContext, n10, n11, n4, n9, 7, EmojiSuggest.a(7091, 4617025081150670235L));
        Round.outline(drawContext, n10, n11, n4, n9, 7, EmojiSuggest.a(16396, 8171244841719520802L));
        for (int i = 0; i < n7; ++i) {
            int n12;
            int n13;
            block31: {
                int n14;
                int n15;
                int n16;
                Object object;
                TextRenderer textRenderer2;
                DrawContext drawContext2;
                int n17;
                block30: {
                    int n18;
                    String string;
                    block28: {
                        block29: {
                            int n19;
                            block26: {
                                block27: {
                                    string = matches.get(n6 + i);
                                    n19 = n6;
                                    if (bl) break block26;
                                    if (n19 + i != index) break block27;
                                    n19 = 1;
                                    break block26;
                                }
                                n19 = 0;
                            }
                            n17 = n19;
                            n13 = n11 + 3 + i * 23;
                            n18 = n13 + 6;
                            if (bl) break block28;
                            if (n17 == 0) break block29;
                            Round.rect(drawContext, n10 + 3, n13, n4 - 6, 21, 4, EmojiSuggest.a(5143, 3161745044149733948L));
                        }
                        drawContext2 = drawContext;
                        textRenderer2 = textRenderer;
                        object = Emoji.charsOf(string);
                        n16 = n10 + 3 + 6;
                        n15 = n18;
                        n14 = -1;
                        if (bl) break block30;
                        drawContext2.method_51433(textRenderer2, (String)object, n16, n15, n14, false);
                    }
                    drawContext2 = drawContext;
                    textRenderer2 = textRenderer;
                    object = ":" + string + ":";
                    n16 = n10 + 3 + 21;
                    n15 = n18;
                    n14 = n17 != 0 ? -1 : EmojiSuggest.a(15315, 2008483600070944250L);
                }
                drawContext2.method_51433(textRenderer2, (String)object, n16, n15, n14, false);
                n12 = n17;
                if (bl) break block31;
                if (n12 == 0) {
                    continue;
                }
                n12 = n10 + n4 - 3 - 6 - n5;
            }
            int n20 = n12;
            int n21 = n13 + 3;
            Round.outline(drawContext, n20, n21, n5, 14, 3, EmojiSuggest.a(24259, 1274046532368351468L));
            drawContext.method_51433(textRenderer, a[0], n20 + 4, n21 + 3, EmojiSuggest.a(18669, 3077918586582554304L), false);
            if (!bl) continue;
        }
    }

    private static boolean lambda$update$0(int n) {
        int n2;
        block8: {
            block10: {
                block9: {
                    boolean bl = Emoji.a;
                    n2 = n;
                    if (bl) break block8;
                    if (Character.isLetterOrDigit(n2)) break block9;
                    n2 = n;
                    if (bl) break block8;
                    if (n2 != 95) break block10;
                }
                n2 = 1;
                break block8;
            }
            n2 = 0;
        }
        return n2 != 0;
    }

    
    static {
        block21: {
            block20: {
                block19: {
                    var13 = new String[2];
                    var11_1 = 0;
                    var10_2 = "[TQ\u0003[TQ";
                    var12_3 = "[TQ\u0003[TQ".length();
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
                        break block19;
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
                                v6 = 15;
                                break;
                            }
                            case 1: {
                                v6 = 53;
                                break;
                            }
                            case 2: {
                                v6 = 51;
                                break;
                            }
                            case 3: {
                                v6 = 59;
                                break;
                            }
                            case 4: {
                                v6 = 100;
                                break;
                            }
                            case 5: {
                                v6 = 28;
                                break;
                            }
                            default: {
                                v6 = 52;
                            }
                        }
                        v0[v4] = (char)(v5 ^ v6);
                        ++var14_6;
lbl48:

                        v3 = v3;
                    } while (v3 > var14_6);
                    while (true)
                }
                EmojiSuggest.a = var13;
                var0_7 = 1260870120801028491L;
                var6_8 = new long[9];
                var3_9 = 0;
                var4_10 = "\u00cb\u000b\u00b9\u00cc\u0095y\u0003\u00e1D\u0099S&22\u009b\u00ca\u00f1\u00c89U\u00ce\u00e2v\u009847!\u00dd\u0010\u00afhV\u00a5\u00b8\u00e3\u00f5j\u0092*\u00a3\u00c85\u001d\u0099\\!\u00de\n@\f\u00ea\u0014b%\u00bcH";
                var5_11 = "\u00cb\u000b\u00b9\u00cc\u0095y\u0003\u00e1D\u0099S&22\u009b\u00ca\u00f1\u00c89U\u00ce\u00e2v\u009847!\u00dd\u0010\u00afhV\u00a5\u00b8\u00e3\u00f5j\u0092*\u00a3\u00c85\u001d\u0099\\!\u00de\n@\f\u00ea\u0014b%\u00bcH".length();
                var2_12 = 0;
                while (true) {
                    var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
                    v7 = var6_8;
                    v8 = var3_9++;
                    v9 = ((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L;
                    v10 = -1;
                    break block20;
                    break;
                }
lbl68:

                while (true) {
                    v7[v8] = v11;
                    if (var2_12 < var5_11) continue;
                    var4_10 = "\u00be\u0012\u00ecsr\u00dct\u008b\u00f1\"\b\u00aff\"\u00f0\u00d9";
                    var5_11 = "\u00be\u0012\u00ecsr\u00dct\u008b\u00f1\"\b\u00aff\"\u00f0\u00d9".length();
                    var2_12 = 0;
                    while (true) {
                        var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
                        v7 = var6_8;
                        v8 = var3_9++;
                        v9 = ((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L;
                        v10 = 0;
                        break block20;
                        break;
                    }
                    break;
                }
lbl81:

                while (true) {
                    v7[v8] = v11;
                    if (var2_12 < var5_11) continue;
                    break block21;
                    break;
                }
            }
            v11 = v9 ^ var0_7;
            switch (v10) {
                default: {
                    continue;
                }
                case 0:
lbl92:

                continue;
            }
        }
        EmojiSuggest.b = var6_8;
        EmojiSuggest.c = new Integer[9];
        EmojiSuggest.ROWS = EmojiSuggest.a(9004, 3599543632100834566L);
        EmojiSuggest.ROW = EmojiSuggest.a(19576, 5695363115261973076L);
        EmojiSuggest.PAD = EmojiSuggest.a(10100, 4977142013982751057L);
        EmojiSuggest.matches = List.of();
        EmojiSuggest.start = -1;
        EmojiSuggest.end = -1;
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1E2D;
        if (c[n2] == null) {
            EmojiSuggest.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }
}
