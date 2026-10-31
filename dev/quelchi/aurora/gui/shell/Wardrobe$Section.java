package dev.quelchi.aurora.gui.shell;

import dev.quelchi.aurora.config.ConfigManager;
import dev.quelchi.aurora.cosmetics.backend.AccountCapes;
import dev.quelchi.aurora.cosmetics.content.Cosmetics;
import dev.quelchi.aurora.cosmetics.renderer.HitEffects;
import dev.quelchi.aurora.cosmetics.renderer.KillEffects;
import dev.quelchi.aurora.cosmetics.renderer.MaceEffects;
import dev.quelchi.aurora.gui.shell.SearchIndex;
import dev.quelchi.aurora.render.EffectEngine;
import dev.quelchi.aurora.render.EffectEngine$Effect;
import java.lang.invoke.LambdaMetafactory;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

final class Wardrobe$Section
extends Enum<Wardrobe$Section> {
    public static final /* enum */ Wardrobe$Section CAPES;
    public static final /* enum */ Wardrobe$Section WINGS;
    public static final /* enum */ Wardrobe$Section TRAILS;
    public static final /* enum */ Wardrobe$Section KILLFX;
    public static final /* enum */ Wardrobe$Section MACEFX;
    public static final /* enum */ Wardrobe$Section HITFX;
    public static final /* enum */ Wardrobe$Section EMOTES;
    public static final /* enum */ Wardrobe$Section BADGES;
    final String label;
    final String slot;
    final String icon;
    final String prefix;
    final String cat;
    private final Supplier<String> worn;
    final Consumer<String> equip;
    private static final Wardrobe$Section[] $VALUES;

    public static Wardrobe$Section[] values() {
        return (Wardrobe$Section[])$VALUES.clone();
    }

    public static Wardrobe$Section valueOf(String string) {
        return Enum.valueOf(Wardrobe$Section.class, string);
    }

    private Wardrobe$Section(String string2, String string3, String string4, String string5, String string6, Supplier<String> supplier, Consumer<String> consumer) {
        this.label = string2;
        this.slot = string3;
        this.icon = string4;
        this.prefix = string5;
        this.cat = string6;
        this.worn = supplier;
        this.equip = consumer;
    }

    String worn() {
        return this.worn.get();
    }

    private static void preview(EffectEngine$Effect effectEngine$Effect, float f) {
        ClientPlayerEntity clientPlayerEntity = MinecraftClient.method_1551().field_1724;
        if (clientPlayerEntity != null) {
            EffectEngine.play(effectEngine$Effect, clientPlayerEntity.method_73189(), f);
        }
    }

    private static String lambda$static$7() {
        return "";
    }

    private static void lambda$static$6(String string) {
        String string2;
        block4: {
            block5: {
                boolean bl = SearchIndex.a;
                string2 = string;
                if (bl) break block4;
                if (!string2.equals(Cosmetics.getOwnEmote())) break block5;
                string2 = "";
                break block4;
            }
            string2 = string;
        }
        Cosmetics.setOwnEmote(string2);
    }

    private static void lambda$static$5(String string) {
        Cosmetics.setOwnHitEffect(string);
        ConfigManager.save();
        Wardrobe$Section.preview(HitEffects.LIST.byId(string), 1.0f);
    }

    private static void lambda$static$4(String string) {
        Cosmetics.setOwnMaceEffect(string);
        ConfigManager.save();
        Wardrobe$Section.preview(MaceEffects.LIST.byId(string), 1.4f);
    }

    private static void lambda$static$3(String string) {
        Cosmetics.setOwnKillEffect(string);
        ConfigManager.save();
        Wardrobe$Section.preview(KillEffects.LIST.byId(string), 1.0f);
    }

    private static void lambda$static$2(String string) {
        Cosmetics.setOwnTrail(string);
        ConfigManager.save();
    }

    private static void lambda$static$1(String string) {
        Cosmetics.setOwnWings(string);
        ConfigManager.save();
    }

    private static String lambda$static$0() {
        String string;
        block4: {
            block5: {
                boolean bl = SearchIndex.a;
                string = Cosmetics.getOwnCape();
                if (bl) break block4;
                if (!string.isBlank()) break block5;
                string = AccountCapes.activeName();
                break block4;
            }
            string = Cosmetics.getOwnCape();
        }
        return string;
    }

    private static Wardrobe$Section[] $values() {
        return new Wardrobe$Section[]{CAPES, WINGS, TRAILS, KILLFX, MACEFX, HITFX, EMOTES, BADGES};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[47];
                var4_1 = 0;
                var3_2 = "bx.N#(EOt!Vp\nDp!GF+ELr6\u0006dP\u0001gE\u0015\bZ}-V\\%J]\u0005aX\u0016d[\tAx6ge+FJe\u0004jp2G\u0005~x,Ep\u0006l|-Vf>\u0006]c#Kow\u0006Kp&Efw\u000bbx.N#(EOt!V\nax6\u0002f+ELr6\u0005}c#Ko\u000bdp!G#(EOt!V\u0006kp&Ef>\nZ}-V\\:JGv1\u0006L|-Vfw\u0006kP\u0006eF\u001e\u0005kp&Ef\u0006bX\u000enE\u0015\u0005jp2Gp\u0005~X\feP\u0005jP\u0012gP\tZ}-V\\&JE}\u0005Kp&Ef\fdp!G#(EOt!Vp\u0004Ax6\u0018\tZ}-V\\.BYt\u0006}C\u0003kO\u001e\u0005Dp!G9\nZ}-V\\/BMv'\u0005~x,Ep\u0006}c#Ko>\u0005l|-Vf\nZ}-V\\(NFe'\u0005^x,E9\u0005Bx.N9\u0005]c#Ko\tZ}-V\\ BJt\u0004Jp2G\nZ}-V\\9QHx.\nBx.NF+ELr6\u0006l\\\rvF\u001e\u0005^x,Ep";
                var5_3 = "bx.N#(EOt!Vp\nDp!GF+ELr6\u0006dP\u0001gE\u0015\bZ}-V\\%J]\u0005aX\u0016d[\tAx6ge+FJe\u0004jp2G\u0005~x,Ep\u0006l|-Vf>\u0006]c#Kow\u0006Kp&Efw\u000bbx.N#(EOt!V\nax6\u0002f+ELr6\u0005}c#Ko\u000bdp!G#(EOt!V\u0006kp&Ef>\nZ}-V\\:JGv1\u0006L|-Vfw\u0006kP\u0006eF\u001e\u0005kp&Ef\u0006bX\u000enE\u0015\u0005jp2Gp\u0005~X\feP\u0005jP\u0012gP\tZ}-V\\&JE}\u0005Kp&Ef\fdp!G#(EOt!Vp\u0004Ax6\u0018\tZ}-V\\.BYt\u0006}C\u0003kO\u001e\u0005Dp!G9\nZ}-V\\/BMv'\u0005~x,Ep\u0006}c#Ko>\u0005l|-Vf\nZ}-V\\(NFe'\u0005^x,E9\u0005Bx.N9\u0005]c#Ko\tZ}-V\\ BJt\u0004Jp2G\nZ}-V\\9QHx.\nBx.NF+ELr6\u0006l\\\rvF\u001e\u0005^x,Ep".length();
                var2_4 = 12;
                var1_5 = -1;
lbl7:

                while (true) {
                    v0 = ++var1_5;
                    v1 = var3_2.substring(v0, v0 + var2_4);
                    v2 = -1;
                    break block18;
                    break;
                }
lbl12:

                while (true) {
                    var0[var4_1++] = v3.intern();
                    if ((var1_5 += var2_4) < var5_3) {
                        var2_4 = var3_2.charAt(var1_5);
                        continue;
                    }
                    var3_2 = "L|-Vf\u000bax6\u0002f+ELr6Q";
                    var5_3 = "L|-Vf\u000bax6\u0002f+ELr6Q".length();
                    var2_4 = 5;
                    var1_5 = -1;
lbl21:

                    while (true) {
                        v4 = ++var1_5;
                        v1 = var3_2.substring(v4, v4 + var2_4);
                        v2 = 0;
                        break block18;
                        break;
                    }
                    break;
                }
lbl26:

                while (true) {
                    var0[var4_1++] = v3.intern();
                    if ((var1_5 += var2_4) < var5_3) {
                        var2_4 = var3_2.charAt(var1_5);
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
                        v10 = 41;
                        break;
                    }
                    case 1: {
                        v10 = 17;
                        break;
                    }
                    case 2: {
                        v10 = 66;
                        break;
                    }
                    case 3: {
                        v10 = 34;
                        break;
                    }
                    case 4: {
                        v10 = 3;
                        break;
                    }
                    case 5: {
                        v10 = 77;
                        break;
                    }
                    default: {
                        v10 = 35;
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
        Wardrobe$Section.CAPES = new Wardrobe$Section(var0[21], var0[6], var0[28], "", var0[40], (Supplier<String>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, lambda$static$0(), ()Ljava/lang/String;)(), null);
        Wardrobe$Section.WINGS = new Wardrobe$Section(var0[32], var0[7], var0[16], var0[36], var0[44], (Supplier<String>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, getOwnWings(), ()Ljava/lang/String;)(), (Consumer<String>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)V, lambda$static$1(java.lang.String ), (Ljava/lang/String;)V)());
        Wardrobe$Section.TRAILS = new Wardrobe$Section(var0[33], var0[13], var0[41], var0[9], var0[38], (Supplier<String>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, getOwnTrail(), ()Ljava/lang/String;)(), (Consumer<String>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)V, lambda$static$2(java.lang.String ), (Ljava/lang/String;)V)());
        Wardrobe$Section.KILLFX = new Wardrobe$Section(var0[0], var0[11], var0[24], var0[37], var0[42], (Supplier<String>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, getOwnKillEffect(), ()Ljava/lang/String;)(), (Consumer<String>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)V, lambda$static$3(java.lang.String ), (Ljava/lang/String;)V)());
        Wardrobe$Section.MACEFX = new Wardrobe$Section(var0[26], var0[14], var0[39], var0[30], var0[1], (Supplier<String>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, getOwnMaceEffect(), ()Ljava/lang/String;)(), (Consumer<String>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)V, lambda$static$4(java.lang.String ), (Ljava/lang/String;)V)());
        Wardrobe$Section.HITFX = new Wardrobe$Section(var0[46], var0[12], var0[3], var0[27], var0[5], (Supplier<String>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, getOwnHitEffect(), ()Ljava/lang/String;)(), (Consumer<String>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)V, lambda$static$5(java.lang.String ), (Ljava/lang/String;)V)());
        Wardrobe$Section.EMOTES = new Wardrobe$Section(var0[8], var0[34], var0[35], var0[17], var0[45], (Supplier<String>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, getOwnEmote(), ()Ljava/lang/String;)(), (Consumer<String>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)V, lambda$static$6(java.lang.String ), (Ljava/lang/String;)V)());
        Wardrobe$Section.BADGES = new Wardrobe$Section(var0[15], var0[19], var0[31], var0[10], var0[25], (Supplier<String>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, lambda$static$7(), ()Ljava/lang/String;)(), null);
        Wardrobe$Section.$VALUES = Wardrobe$Section.$values();
    }
}
