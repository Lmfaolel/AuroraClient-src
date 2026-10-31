package dev.quelchi.aurora.module.modules;

import net.minecraft.util.Identifier;

public final class NametagsModule$Gamemode
extends Enum<NametagsModule$Gamemode> {
    public static final /* enum */ NametagsModule$Gamemode Vanilla;
    public static final /* enum */ NametagsModule$Gamemode UHC;
    public static final /* enum */ NametagsModule$Gamemode Pot;
    public static final /* enum */ NametagsModule$Gamemode NethPot;
    public static final /* enum */ NametagsModule$Gamemode SMP;
    public static final /* enum */ NametagsModule$Gamemode Sword;
    public static final /* enum */ NametagsModule$Gamemode Axe;
    public static final /* enum */ NametagsModule$Gamemode Mace;
    public static final /* enum */ NametagsModule$Gamemode DiaSMP;
    public static final /* enum */ NametagsModule$Gamemode Elytra;
    public final Identifier sprite;
    private static final NametagsModule$Gamemode[] $VALUES;
    private static final String[] a;

    public static NametagsModule$Gamemode[] values() {
        return (NametagsModule$Gamemode[])$VALUES.clone();
    }

    public static NametagsModule$Gamemode valueOf(String string) {
        return Enum.valueOf(NametagsModule$Gamemode.class, string);
    }

    private NametagsModule$Gamemode(String string2) {
        this.sprite = Identifier.method_60656((String)(a[5] + string2));
    }

    private static NametagsModule$Gamemode[] $values() {
        return new NametagsModule$Gamemode[]{Vanilla, UHC, Pot, NethPot, SMP, Sword, Axe, Mace, DiaSMP, Elytra};
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[21];
                var3_1 = 0;
                var2_2 = "\u0017.w\u0012 \nFng |\u001b\u0000Of{:h(\u0002Sf\u0006\u0001\u000f^wz/\u000f*\u0006Skm<q0\u0006xjf)w0\u0004\t\u0002Df\u0005-\u0017Bn'\u0007\u0012\u0002Ijd\"y\u000f*\u0006Skm<q0\u0006xp\u007f!j \u00100\fSfe\u0011w\"<Rml7q*\u0004\u000b!\rC\\k<a7\u0017Fo\u0006!\u000f^wz/\u0004)\u0002Df\u0003\u0005\u001bB\u0006\u0000\nFPE\u001e\u0007\n\u0006SkX!l\r*\u0006Skm<q0\u0006xbp+\r7\u0013Kb{&G4\fSjg \u0005\u0017\u0014Hql\f#\fKgm G%\u0013Wom";
                var4_3 = "\u0017.w\u0012 \nFng |\u001b\u0000Of{:h(\u0002Sf\u0006\u0001\u000f^wz/\u000f*\u0006Skm<q0\u0006xjf)w0\u0004\t\u0002Df\u0005-\u0017Bn'\u0007\u0012\u0002Ijd\"y\u000f*\u0006Skm<q0\u0006xp\u007f!j \u00100\fSfe\u0011w\"<Rml7q*\u0004\u000b!\rC\\k<a7\u0017Fo\u0006!\u000f^wz/\u0004)\u0002Df\u0003\u0005\u001bB\u0006\u0000\nFPE\u001e\u0007\n\u0006SkX!l\r*\u0006Skm<q0\u0006xbp+\r7\u0013Kb{&G4\fSjg \u0005\u0017\u0014Hql\f#\fKgm G%\u0013Wom".length();
                var1_4 = 3;
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
                    var2_2 = "\u0011+d\u0003\u0014\fS";
                    var4_3 = "\u0011+d\u0003\u0014\fS".length();
                    var1_4 = 3;
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
            var6_7 = 0;
            do {
                v6 = v6;
                v8 = var6_7;
                v9 = v6[v8];
                switch (var6_7 % 7) {
                    case 0: {
                        v10 = 68;
                        break;
                    }
                    case 1: {
                        v10 = 99;
                        break;
                    }
                    case 2: {
                        v10 = 39;
                        break;
                    }
                    case 3: {
                        v10 = 3;
                        break;
                    }
                    case 4: {
                        v10 = 8;
                        break;
                    }
                    case 5: {
                        v10 = 78;
                        break;
                    }
                    default: {
                        v10 = 24;
                    }
                }
                v6[v8] = (char)(v9 ^ v10);
                ++var6_7;
lbl65:

                v7 = v7;
            } while (v7 > var6_7);
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
        NametagsModule$Gamemode.a = var5;
        var0_6 = NametagsModule$Gamemode.a;
        NametagsModule$Gamemode.Vanilla = new NametagsModule$Gamemode(var0_6[9]);
        NametagsModule$Gamemode.UHC = new NametagsModule$Gamemode(var0_6[18]);
        NametagsModule$Gamemode.Pot = new NametagsModule$Gamemode(var0_6[16]);
        NametagsModule$Gamemode.NethPot = new NametagsModule$Gamemode(var0_6[3]);
        NametagsModule$Gamemode.SMP = new NametagsModule$Gamemode(var0_6[8]);
        NametagsModule$Gamemode.Sword = new NametagsModule$Gamemode(var0_6[7]);
        NametagsModule$Gamemode.Axe = new NametagsModule$Gamemode(var0_6[15]);
        NametagsModule$Gamemode.Mace = new NametagsModule$Gamemode(var0_6[11]);
        NametagsModule$Gamemode.DiaSMP = new NametagsModule$Gamemode(var0_6[1]);
        NametagsModule$Gamemode.Elytra = new NametagsModule$Gamemode(var0_6[10]);
        NametagsModule$Gamemode.$VALUES = NametagsModule$Gamemode.$values();
    }
}
