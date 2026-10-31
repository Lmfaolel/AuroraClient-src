package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.gui.Anim;
import dev.quelchi.aurora.gui.HudScale;
import dev.quelchi.aurora.gui.PreviewScene;
import dev.quelchi.aurora.gui.Round;
import dev.quelchi.aurora.gui.SocialToast$1;
import dev.quelchi.aurora.gui.SocialToast$Hit;
import dev.quelchi.aurora.gui.SocialToast$Toast;
import dev.quelchi.aurora.gui.Theme;
import dev.quelchi.aurora.gui.UiSound;
import dev.quelchi.aurora.gui.VectorIcons;
import dev.quelchi.aurora.social.SocialEvents$Kind;
import dev.quelchi.aurora.social.WorldShare;
import dev.quelchi.aurora.social.WorldShare$Invite;
import dev.quelchi.aurora.util.SkinFetcher;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.UUID;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.PlayerSkinDrawer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.SkinTextures;
import net.minecraft.text.MutableText;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

public final class SocialToast {
    private static final Deque<SocialToast$Toast> QUEUE;
    private static final List<SocialToast$Hit> HITS;
    private static final long LIFE_MS;
    private static final long INVITE_MS;
    private static final int MAX_ON_SCREEN;
    private static boolean enabled;
    private static boolean sound;
    private static boolean requests;
    private static boolean online;
    private static final int W;
    private static final int H;
    private static final int IH;
    private static final int R;
    private static final int FACE;
    private static final int EDGE;
    private static final int SLIDE;
    private static final int BTN;
    private static final long IN_MS;
    private static final long OUT_MS;
    private static boolean fromScreen;
    private static Screen drawnOn;
    private static final String[] a;
    private static final long[] b;
    private static final Integer[] c;
    private static final long[] d;
    private static final Long[] e;

    private SocialToast() {
    }

    public static void push(String string, String string2, UUID uUID) {
        if (enabled) {
            SocialToast.add(new SocialToast$Toast(string, string2, uUID, Anim.millis(), null));
        }
    }

    
    public static void event(SocialEvents$Kind socialEvents$Kind, String string, String string2, UUID uUID) {
        boolean bl = switch (SocialToast$1.$SwitchMap$dev$quelchi$aurora$social$SocialEvents$Kind[socialEvents$Kind.ordinal()]) {
            default -> throw new MatchException(null, null);
            case 1 -> requests;
            case 2 -> online;
        };
        if (!bl) return;
        SocialToast.add(new SocialToast$Toast(string, string2, uUID, Anim.millis(), null));
    }

    public static void invite(WorldShare$Invite worldShare$Invite) {
        String[] stringArray = a;
        SocialToast.add(new SocialToast$Toast(worldShare$Invite.name() + stringArray[5], stringArray[4] + worldShare$Invite.world(), worldShare$Invite.who(), Anim.millis(), worldShare$Invite));
    }

    
    private static void add(SocialToast$Toast socialToast$Toast) {
        MinecraftClient minecraftClient = QUEUE;
        synchronized (minecraftClient) {
            block16: {
                MinecraftClient minecraftClient2;
                block18: {
                    int n;
                    block17: {
                        block15: {
                            Deque<SocialToast$Toast> deque;
                            n = PreviewScene.a;
                            while (QUEUE.size() >= 3) {
                                deque = QUEUE;
                                if (n == 0) {
                                    SocialToast.drop(deque.pollFirst());
                                    if (n == 0) continue;
                                    break;
                                }
                                break block15;
                            }
                            QUEUE.addLast(socialToast$Toast);
                            deque = minecraftClient;
                        }
                        minecraftClient = MinecraftClient.method_1551();
                        if (!sound) break block16;
                        minecraftClient2 = minecraftClient;
                        if (n != 0) break block17;
                        if (minecraftClient2 == null) break block16;
                        minecraftClient2 = minecraftClient;
                    }
                    if (n != 0) break block18;
                    if (minecraftClient2.method_1483() == null) break block16;
                    minecraftClient2 = minecraftClient;
                }
                minecraftClient2.execute(SocialToast::lambda$add$0);
            }
            return;
        }
    }

    
    public static void system(String string, String string2) {
        Deque<SocialToast$Toast> deque = QUEUE;
        synchronized (deque) {
            block7: {
                Deque<SocialToast$Toast> deque2;
                int n = PreviewScene.a;
                while (QUEUE.size() >= 3) {
                    deque2 = QUEUE;
                    if (n == 0) {
                        SocialToast.drop(deque2.pollFirst());
                        if (n == 0) continue;
                        break;
                    }
                    break block7;
                }
                QUEUE.addLast(new SocialToast$Toast(string, string2, null, Anim.millis(), null));
                deque2 = deque;
            }
            return;
        }
    }

    
    public static void clear() {
        Deque<SocialToast$Toast> deque = QUEUE;
        synchronized (deque) {
            QUEUE.forEach(SocialToast::drop);
            QUEUE.clear();
            HITS.clear();
            drawnOn = null;
        }
    }

    private static void drop(SocialToast$Toast socialToast$Toast) {
        block3: {
            MinecraftClient minecraftClient;
            block2: {
                MinecraftClient minecraftClient2 = MinecraftClient.method_1551();
                int n = PreviewScene.a;
                minecraftClient = minecraftClient2;
                if (n != 0) break block2;
                if (minecraftClient == null) break block3;
                minecraftClient = minecraftClient2;
            }
            minecraftClient.execute(() -> SocialToast.lambda$drop$1(socialToast$Toast));
        }
    }

    public static boolean enabled() {
        return enabled;
    }

    public static void setEnabled(boolean bl) {
        enabled = bl;
    }

    public static boolean sound() {
        return sound;
    }

    public static void setSound(boolean bl) {
        sound = bl;
    }

    public static boolean requests() {
        return requests;
    }

    public static void setRequests(boolean bl) {
        requests = bl;
    }

    public static boolean online() {
        return online;
    }

    public static void setOnline(boolean bl) {
        online = bl;
    }

    public static void renderOverScreen(DrawContext drawContext) {
        fromScreen = true;
        try {
            SocialToast.render(drawContext);
        }
        finally {
            fromScreen = false;
        }
    }

    
    public static void render(DrawContext drawContext) {
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        int n = PreviewScene.a;
        if (minecraftClient == null) return;
        if (QUEUE.isEmpty()) {
            return;
        }
        if (minecraftClient.field_1755 != null && !fromScreen) {
            return;
        }
        long l = Anim.millis();
        Deque<SocialToast$Toast> deque = QUEUE;
        synchronized (deque) {
            QUEUE.removeIf(arg_0 -> SocialToast.lambda$render$2(l, arg_0));
            HITS.clear();
            Screen screen = drawnOn = QUEUE.isEmpty() ? null : minecraftClient.field_1755;
            if (n == 0) {
                if (QUEUE.isEmpty()) {
                    return;
                }
                HudScale.push(drawContext);
            }
            int n2 = HudScale.width();
            int n3 = 15;
            for (SocialToast$Toast socialToast$Toast : QUEUE) {
                block21: {
                    MinecraftClient minecraftClient2;
                    DrawContext drawContext2;
                    int n4;
                    int n5;
                    float f;
                    long l2;
                    block20: {
                        if (n != 0) return;
                        l2 = l - socialToast$Toast.shown();
                        float f2 = Math.max(0.0f, (float)(l2 - (socialToast$Toast.life() - SocialToast.b(18433, 6967697528677889335L))) / 150.0f);
                        f = Math.min(1.0f, (float)l2 / 150.0f) * (1.0f - f2);
                        int n6 = n3;
                        int n8 = socialToast$Toast.invite() != null ? 64 : 40;
                        int n7 = n3 + (n8 + 3);
                        if (n == 0) {
                            n3 = n7;
                            if (f <= 0.01f) continue;
                            n7 = Math.round(Anim.px(socialToast$Toast, n6, 16.0f));
                        }
                        n5 = n7;
                        n4 = n2 - 188 - 15 + Math.round((1.0f - Anim.outCubic((float)l2 / 220.0f) + Anim.outCubic(f2)) * 18.0f);
                        drawContext2 = drawContext;
                        minecraftClient2 = minecraftClient;
                        if (n != 0) break block20;
                        SocialToast.card(drawContext2, minecraftClient2, socialToast$Toast, n4, n5, n8, f);
                        if (socialToast$Toast.invite() == null) break block21;
                        drawContext2 = drawContext;
                        minecraftClient2 = minecraftClient;
                    }
                    SocialToast.invite(drawContext2, minecraftClient2, socialToast$Toast, n4, n5, f, 1.0f - (float)l2 / (float)socialToast$Toast.life());
                }
                if (n == 0) continue;
            }
            HudScale.pop(drawContext);
            return;
        }
    }

    private static void card(DrawContext drawContext, MinecraftClient minecraftClient, SocialToast$Toast socialToast$Toast, int n, int n2, int n3, float f) {
        block14: {
            int n4;
            int n5;
            int n6;
            block13: {
                int n7;
                int n8;
                block12: {
                    int n9;
                    block10: {
                        block11: {
                            n6 = Math.round(255.0f * f);
                            n8 = PreviewScene.a;
                            Round.rect(drawContext, n, n2, 188, n3, 8, Theme.alpha(SocialToast.a(4836, 4476358205376250005L), n6));
                            Round.outline(drawContext, n, n2, 188, n3, 8, Theme.alpha(SocialToast.a(2739, 3676076175303694537L), Math.round(41.0f * f)));
                            n7 = n + 9;
                            n9 = n2 + 8;
                            if (n8 != 0) break block10;
                            if (socialToast$Toast.from() == null) break block11;
                            PlayerSkinDrawer.method_44443((DrawContext)drawContext, (SkinTextures)SkinFetcher.skinFor(socialToast$Toast.from()), (int)n7, (int)n9, (int)24, (int)Theme.alpha(SocialToast.a(12094, 8160360497307516236L), n6));
                            if (n8 == 0) break block12;
                        }
                        Round.rect(drawContext, n7, n9, 24, 24, 5, Theme.alpha(SocialToast.a(12094, 8160360497307516236L), Math.round(34.0f * f)));
                    }
                    VectorIcons.draw(drawContext, a[2], n7 + 5, n9 + 5, 14, Theme.alpha(SocialToast.a(3693, 5426960536659516438L), n6));
                }
                n5 = n7 + 24 + 8;
                n4 = n + 188 - 9 - n5;
                if (n8 != 0) break block13;
                if (!(f > 0.05f)) break block14;
                drawContext.method_51439(minecraftClient.field_1772, SocialToast.fit(minecraftClient, socialToast$Toast.who(), n4, true), n5, n2 + 10, Theme.alpha(SocialToast.a(6429, 2519089610580151140L), n6), false);
            }
            drawContext.method_51439(minecraftClient.field_1772, SocialToast.fit(minecraftClient, socialToast$Toast.body(), n4, false), n5, n2 + 22, Theme.alpha(SocialToast.a(20565, 6175779431312589346L), n6), false);
        }
    }

    private static void invite(DrawContext drawContext, MinecraftClient minecraftClient, SocialToast$Toast socialToast$Toast, int n, int n2, float f, float f2) {
        int n3;
        int n4;
        block14: {
            int n5;
            block13: {
                int n6;
                block12: {
                    float f3;
                    block10: {
                        block11: {
                            n6 = PreviewScene.a;
                            f3 = f;
                            if (n6 != 0) break block10;
                            if (!(f3 <= 0.05f)) break block11;
                            return;
                        }
                        f3 = 255.0f * f;
                    }
                    n4 = Math.round(f3);
                    n5 = n2 + 40 - 2;
                    if (n6 != 0) break block12;
                    if (minecraftClient.field_1755 != null) break block13;
                    drawContext.method_51439(minecraftClient.field_1772, (Text)Text.method_43470((String)a[1]), n + 9, n5 + 5, Theme.alpha(SocialToast.a(7474, 146433134096863054L), n4), false);
                }
                if (n6 == 0) break block14;
            }
            n3 = 83;
            Round.rect(drawContext, n + 9, n5, n3, 18, 6, Theme.alpha(SocialToast.a(19749, 3307386855811522389L), n4));
            String[] stringArray = a;
            SocialToast.center(drawContext, minecraftClient, stringArray[0], n + 9, n5, n3, Theme.alpha(SocialToast.a(30980, 7119205625037335418L), n4), true);
            Round.rect(drawContext, n + 13 + n3, n5, n3, 18, 6, Theme.alpha(SocialToast.a(12094, 8160360497307516236L), Math.round(18.0f * f)));
            Round.outline(drawContext, n + 13 + n3, n5, n3, 18, 6, Theme.alpha(SocialToast.a(12094, 8160360497307516236L), Math.round(41.0f * f)));
            SocialToast.center(drawContext, minecraftClient, stringArray[3], n + 13 + n3, n5, n3, Theme.alpha(SocialToast.a(7474, 146433134096863054L), n4), false);
            WorldShare$Invite worldShare$Invite = socialToast$Toast.invite();
            HITS.add(new SocialToast$Hit(n + 9, n5, n3, 18, () -> SocialToast.lambda$invite$3(worldShare$Invite)));
            HITS.add(new SocialToast$Hit(n + 13 + n3, n5, n3, 18, () -> SocialToast.lambda$invite$4(worldShare$Invite)));
        }
        n3 = Math.round(172.0f * Math.max(0.0f, f2));
        drawContext.method_25294(n + 8, n2 + 64 - 2, n + 8 + n3, n2 + 64 - 1, Theme.alpha(SocialToast.a(12697, 7754785278077068266L), n4));
    }

    private static void center(DrawContext drawContext, MinecraftClient minecraftClient, String string, int n, int n2, int n3, int n4, boolean bl) {
        MutableText mutableText = Text.method_43470((String)string).method_10862(Style.field_24360.method_10982(Boolean.valueOf(bl)));
        drawContext.method_51439(minecraftClient.field_1772, (Text)mutableText, n + (n3 - minecraftClient.field_1772.method_27525((StringVisitable)mutableText)) / 2, n2 + 5, n4, false);
    }

    
    public static boolean click(double d, double d2) {
        int n;
        block26: {
            block25: {
                Screen screen;
                block24: {
                    Screen screen2 = MinecraftClient.method_1551().field_1755;
                    n = PreviewScene.a;
                    screen = screen2;
                    if (n != 0) break block24;
                    if (screen == null) break block25;
                    screen = screen2;
                }
                if (screen == drawnOn) break block26;
            }
            return false;
        }
        double d3 = HudScale.toVirtual(d);
        double d4 = HudScale.toVirtual(d2);
        Runnable runnable = null;
        Deque<SocialToast$Toast> deque = QUEUE;
        synchronized (deque) {
            block23: {
                for (SocialToast$Hit socialToast$Hit : HITS) {
                    block28: {
                        double d5;
                        double d6;
                        block30: {
                            block29: {
                                block27: {
                                    if (n != 0) break block23;
                                    d6 = d3;
                                    d5 = socialToast$Hit.x();
                                    if (n != 0) break block27;
                                    if (!(d6 >= d5)) break block28;
                                    d6 = d3;
                                    d5 = socialToast$Hit.x() + socialToast$Hit.w();
                                }
                                if (n != 0) break block29;
                                if (!(d6 <= d5)) break block28;
                                d6 = d4;
                                d5 = socialToast$Hit.y();
                            }
                            if (n != 0) break block30;
                            if (!(d6 >= d5)) break block28;
                            d6 = d4;
                            d5 = socialToast$Hit.y() + socialToast$Hit.h();
                        }
                        if (d6 <= d5) {
                            runnable = socialToast$Hit.act();
                        }
                    }
                    if (n == 0) continue;
                }
            }
            Runnable runnable2 = runnable;
            if (n == 0) {
                if (runnable2 == null) {
                    return false;
                }
                runnable2 = runnable;
            }
            runnable2.run();
            return true;
        }
    }

    
    private static Text fit(MinecraftClient var0, String var1_1, int var2_2, boolean var3_3) {
        block8: {
            block9: {
                block7: {
                    var5_4 = Style.field_24360.method_10982(Boolean.valueOf(var3_3));
                    var4_5 = PreviewScene.a;
                    v0 = var0.field_1772.method_27525((StringVisitable)Text.method_43470((String)var1_1).method_10862(var5_4));
                    if (var4_5 != 0) break block7;
                    if (v0 <= var2_2) break block8;
lbl11:

                    while (true) {
                        v0 = var1_1.length();
                        break;
                    }
                }
                if (v0 <= 1 || var0.field_1772.method_27525((StringVisitable)Text.method_43470((String)((String)var1_1 + "\u2026")).method_10862(var5_4)) <= var2_2) break block9;
                var1_1 = var1_1.substring(0, var1_1.length() - 1);
                while (var4_5 == 0)
            }
            var1_1 = (String)var1_1 + "\u2026";
        }
        return Text.method_43470((String)var1_1).method_10862(var5_4);
    }

    private static void lambda$invite$4(WorldShare$Invite worldShare$Invite) {
        WorldShare.dismiss(worldShare$Invite.who());
    }

    private static void lambda$invite$3(WorldShare$Invite worldShare$Invite) {
        WorldShare.accept(worldShare$Invite);
    }

    private static boolean lambda$render$2(long l, SocialToast$Toast socialToast$Toast) {
        int n;
        block16: {
            block14: {
                block15: {
                    boolean bl;
                    int n2;
                    block13: {
                        block12: {
                            n2 = PreviewScene.a;
                            if (socialToast$Toast.invite() == null) break block12;
                            bl = WorldShare.invites().contains(socialToast$Toast.invite());
                            if (n2 != 0) break block13;
                            if (bl) break block12;
                            bl = true;
                            break block13;
                        }
                        bl = false;
                    }
                    boolean bl2 = bl;
                    if (n2 != 0) break block14;
                    if (bl2) break block15;
                    long l2 = l - socialToast$Toast.shown() - socialToast$Toast.life();
                    n = l2 == 0L ? 0 : (l2 < 0L ? -1 : 1);
                    if (n2 != 0) break block16;
                    if (n > 0) break block15;
                    return false;
                }
                Anim.reset(socialToast$Toast);
            }
            n = 1;
        }
        return n != 0;
    }

    private static void lambda$drop$1(SocialToast$Toast socialToast$Toast) {
        Anim.reset(socialToast$Toast);
    }

    private static void lambda$add$0() {
        UiSound.play(UiSound.OPEN, 0.35f);
    }

    
    static {
        block37: {
            block36: {
                block35: {
                    block34: {
                        block33: {
                            block32: {
                                var21 = new String[6];
                                var19_1 = 0;
                                var18_2 = "F5\u0015\u000f\u001bC*\u0019\u000fdN\biz\f\u00001I\u0005,7\u0019\u000f1\u001a\u0014cz\u0016\u000e-T\u000bb;\n>\"H\ti4\u0018\u0012\u0005@;\b\u00046";
                                var20_3 = "F5\u0015\u000f\u001bC*\u0019\u000fdN\biz\f\u00001I\u0005,7\u0019\u000f1\u001a\u0014cz\u0016\u000e-T\u000bb;\n>\"H\ti4\u0018\u0012\u0005@;\b\u00046".length();
                                var17_4 = 4;
                                var16_5 = -1;
lbl7:

                                while (true) {
                                    v0 = ++var16_5;
                                    v1 = var18_2.substring(v0, v0 + var17_4);
                                    v2 = -1;
                                    break block32;
                                    break;
                                }
lbl12:

                                while (true) {
                                    var21[var19_1++] = v3.intern();
                                    if ((var16_5 += var17_4) < var20_3) {
                                        var17_4 = var18_2.charAt(var16_5);
                                        continue;
                                    }
                                    var18_2 = "x5\\\f,3\u0012\u0017-N\u0005hz\u0005\u000e1";
                                    var20_3 = "x5\\\f,3\u0012\u0017-N\u0005hz\u0005\u000e1".length();
                                    var17_4 = 3;
                                    var16_5 = -1;
lbl21:

                                    while (true) {
                                        v4 = ++var16_5;
                                        v1 = var18_2.substring(v4, v4 + var17_4);
                                        v2 = 0;
                                        break block32;
                                        break;
                                    }
                                    break;
                                }
lbl26:

                                while (true) {
                                    var21[var19_1++] = v3.intern();
                                    if ((var16_5 += var17_4) < var20_3) {
                                        var17_4 = var18_2.charAt(var16_5);
                                        continue;
                                    }
                                    break block33;
                                    break;
                                }
                            }
                            v5 = v1.toCharArray();
                            v6 = v5;
                            v7 = v5.length;
                            var22_6 = 0;
                            do {
                                v6 = v6;
                                v8 = var22_6;
                                v9 = v6[v8];
                                switch (var22_6 % 7) {
                                    case 0: {
                                        v10 = 12;
                                        break;
                                    }
                                    case 1: {
                                        v10 = 90;
                                        break;
                                    }
                                    case 2: {
                                        v10 = 124;
                                        break;
                                    }
                                    case 3: {
                                        v10 = 97;
                                        break;
                                    }
                                    case 4: {
                                        v10 = 68;
                                        break;
                                    }
                                    case 5: {
                                        v10 = 58;
                                        break;
                                    }
                                    default: {
                                        v10 = 96;
                                    }
                                }
                                v6[v8] = (char)(v9 ^ v10);
                                ++var22_6;
lbl65:

                                v7 = v7;
                            } while (v7 > var22_6);
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
                        SocialToast.a = var21;
                        var8_7 = 1067521921920476801L;
                        var14_8 = new long[18];
                        var11_9 = 0;
                        var12_10 = "c\u00ba\u00eb\u00e3\u0006\u009d\u0007\u00ec%QTpa\"\u00ae\u00b3L\u00a2\u0083S\u00b4\u0094\u008c\u00afP\u008b\u00e0\u001c\u00c3\u001b\u00b8\t\u00c7\u009b\u00f1\u000bA\u0083\u00ca#\u00ad5\u00f6d\u001c\u00c8t\u000e\n\u00a7\u00f1\u00df;\u00d8$2\u0099\u0086\u00afY\u0083a\u0093\u00cb\r\u00f0\u00f6\u008e\u0010\u00a6uei\u00be(\u00f6\u00d2\u00ad\u00dcS\u000e\u00c4\u0001\u0085\u00c9X\u0000\u00ef\u00fc\u0088?]\t\u0013l\u0007\u00aa\u00c0gE\u0000\u0081B\u0096<q\u000b\u00f7\u00b9\u00f3\u001e\u0012.`6\u00d36\u00bc\u0001\u00b7\u00ea\u00de\u00c1\u0003\u0018U\u0089`";
                        var13_11 = "c\u00ba\u00eb\u00e3\u0006\u009d\u0007\u00ec%QTpa\"\u00ae\u00b3L\u00a2\u0083S\u00b4\u0094\u008c\u00afP\u008b\u00e0\u001c\u00c3\u001b\u00b8\t\u00c7\u009b\u00f1\u000bA\u0083\u00ca#\u00ad5\u00f6d\u001c\u00c8t\u000e\n\u00a7\u00f1\u00df;\u00d8$2\u0099\u0086\u00afY\u0083a\u0093\u00cb\r\u00f0\u00f6\u008e\u0010\u00a6uei\u00be(\u00f6\u00d2\u00ad\u00dcS\u000e\u00c4\u0001\u0085\u00c9X\u0000\u00ef\u00fc\u0088?]\t\u0013l\u0007\u00aa\u00c0gE\u0000\u0081B\u0096<q\u000b\u00f7\u00b9\u00f3\u001e\u0012.`6\u00d36\u00bc\u0001\u00b7\u00ea\u00de\u00c1\u0003\u0018U\u0089`".length();
                        var10_12 = 0;
                        while (true) {
                            var15_13 = var12_10.substring(var10_12, var10_12 += 8).getBytes("ISO-8859-1");
                            v11 = var14_8;
                            v12 = var11_9++;
                            v13 = ((long)var15_13[0] & 255L) << 56 | ((long)var15_13[1] & 255L) << 48 | ((long)var15_13[2] & 255L) << 40 | ((long)var15_13[3] & 255L) << 32 | ((long)var15_13[4] & 255L) << 24 | ((long)var15_13[5] & 255L) << 16 | ((long)var15_13[6] & 255L) << 8 | (long)var15_13[7] & 255L;
                            v14 = -1;
                            break block34;
                            break;
                        }
lbl89:

                        while (true) {
                            v11[v12] = v15;
                            if (var10_12 < var13_11) continue;
                            var12_10 = "\u00fdg\u0099\u00c8\u0088F\u0082L\u00bf\u00f2\u000b\u008a\u00eet\u00a4l";
                            var13_11 = "\u00fdg\u0099\u00c8\u0088F\u0082L\u00bf\u00f2\u000b\u008a\u00eet\u00a4l".length();
                            var10_12 = 0;
                            while (true) {
                                var15_13 = var12_10.substring(var10_12, var10_12 += 8).getBytes("ISO-8859-1");
                                v11 = var14_8;
                                v12 = var11_9++;
                                v13 = ((long)var15_13[0] & 255L) << 56 | ((long)var15_13[1] & 255L) << 48 | ((long)var15_13[2] & 255L) << 40 | ((long)var15_13[3] & 255L) << 32 | ((long)var15_13[4] & 255L) << 24 | ((long)var15_13[5] & 255L) << 16 | ((long)var15_13[6] & 255L) << 8 | (long)var15_13[7] & 255L;
                                v14 = 0;
                                break block34;
                                break;
                            }
                            break;
                        }
lbl102:

                        while (true) {
                            v11[v12] = v15;
                            if (var10_12 < var13_11) continue;
                            break block35;
                            break;
                        }
                    }
                    v15 = v13 ^ var8_7;
                    switch (v14) {
                        default: {
                            continue;
                        }
                        case 0:
lbl113:

                        continue;
                    }
                }
                SocialToast.b = var14_8;
                SocialToast.c = new Integer[18];
                SocialToast.SLIDE = SocialToast.a(10429, 6333805579603999424L);
                SocialToast.MAX_ON_SCREEN = SocialToast.a(30378, 1218416426101351630L);
                SocialToast.FACE = SocialToast.a(13943, 5251106542551458831L);
                SocialToast.W = SocialToast.a(29605, 3104292226585207249L);
                SocialToast.EDGE = SocialToast.a(23112, 8381461497936709693L);
                SocialToast.H = SocialToast.a(20640, 7271985032942027461L);
                SocialToast.IH = SocialToast.a(30744, 91661016740899438L);
                SocialToast.R = SocialToast.a(6385, 4699599928338434702L);
                SocialToast.BTN = SocialToast.a(10429, 6333805579603999424L);
                var0_14 = 621204814305892533L;
                var6_15 = new long[5];
                var3_16 = 0;
                var4_17 = "& v\u00f56\u008f\u0087\u008d\u0015\u00a3\u00155\u00f6\u00a1\u00a2\u007fh,\u00cd\u0080\u00a2Am\u0014";
                var5_18 = "& v\u00f56\u008f\u0087\u008d\u0015\u00a3\u00155\u00f6\u00a1\u00a2\u007fh,\u00cd\u0080\u00a2Am\u0014".length();
                var2_19 = 0;
                while (true) {
                    var7_20 = var4_17.substring(var2_19, var2_19 += 8).getBytes("ISO-8859-1");
                    v16 = var6_15;
                    v17 = var3_16++;
                    v18 = ((long)var7_20[0] & 255L) << 56 | ((long)var7_20[1] & 255L) << 48 | ((long)var7_20[2] & 255L) << 40 | ((long)var7_20[3] & 255L) << 32 | ((long)var7_20[4] & 255L) << 24 | ((long)var7_20[5] & 255L) << 16 | ((long)var7_20[6] & 255L) << 8 | (long)var7_20[7] & 255L;
                    v19 = -1;
                    break block36;
                    break;
                }
lbl139:

                while (true) {
                    v16[v17] = v20;
                    if (var2_19 < var5_18) continue;
                    var4_17 = "Ee\u008f\u00d5\f\u00cf\u00e7\u00fej\"\u00e1\n\u00e2\u00beY\u00ee";
                    var5_18 = "Ee\u008f\u00d5\f\u00cf\u00e7\u00fej\"\u00e1\n\u00e2\u00beY\u00ee".length();
                    var2_19 = 0;
                    while (true) {
                        var7_20 = var4_17.substring(var2_19, var2_19 += 8).getBytes("ISO-8859-1");
                        v16 = var6_15;
                        v17 = var3_16++;
                        v18 = ((long)var7_20[0] & 255L) << 56 | ((long)var7_20[1] & 255L) << 48 | ((long)var7_20[2] & 255L) << 40 | ((long)var7_20[3] & 255L) << 32 | ((long)var7_20[4] & 255L) << 24 | ((long)var7_20[5] & 255L) << 16 | ((long)var7_20[6] & 255L) << 8 | (long)var7_20[7] & 255L;
                        v19 = 0;
                        break block36;
                        break;
                    }
                    break;
                }
lbl152:

                while (true) {
                    v16[v17] = v20;
                    if (var2_19 < var5_18) continue;
                    break block37;
                    break;
                }
            }
            v20 = v18 ^ var0_14;
            switch (v19) {
                default: {
                    continue;
                }
                case 0:
lbl163:

                continue;
            }
        }
        SocialToast.d = var6_15;
        SocialToast.e = new Long[5];
        SocialToast.IN_MS = SocialToast.b(1827, 2107090104288197142L);
        SocialToast.OUT_MS = SocialToast.b(31997, 7114587307512512973L);
        SocialToast.INVITE_MS = SocialToast.b(27692, 3368270901378863384L);
        SocialToast.LIFE_MS = SocialToast.b(20980, 5619218730282708163L);
        SocialToast.QUEUE = new ArrayDeque<SocialToast$Toast>();
        SocialToast.HITS = new ArrayList<SocialToast$Hit>();
        SocialToast.enabled = true;
        SocialToast.sound = true;
        SocialToast.requests = true;
        SocialToast.online = false;
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5274;
        if (c[n2] == null) {
            SocialToast.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }

    private static long b(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x3D34) & Short.MAX_VALUE;
        if (e[n2] == null) {
            SocialToast.e[n2] = d[n2] ^ l;
        }
        return e[n2];
    }
}
