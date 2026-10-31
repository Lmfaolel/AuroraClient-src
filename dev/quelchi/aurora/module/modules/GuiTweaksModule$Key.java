package dev.quelchi.aurora.module.modules;

public final class GuiTweaksModule$Key
extends Enum<GuiTweaksModule$Key> {
    public static final /* enum */ GuiTweaksModule$Key SURVIVAL;
    public static final /* enum */ GuiTweaksModule$Key HOTBAR;
    public static final /* enum */ GuiTweaksModule$Key HEALTH;
    public static final /* enum */ GuiTweaksModule$Key ARMOR;
    public static final /* enum */ GuiTweaksModule$Key HUNGER;
    public static final /* enum */ GuiTweaksModule$Key SCOREBOARD;
    public static final /* enum */ GuiTweaksModule$Key CHAT;
    public static final /* enum */ GuiTweaksModule$Key BOSSBAR;
    public static final /* enum */ GuiTweaksModule$Key EFFECTS;
    public static final /* enum */ GuiTweaksModule$Key EXPERIENCE;
    public static final /* enum */ GuiTweaksModule$Key AIR;
    public static final /* enum */ GuiTweaksModule$Key MOUNT;
    public final String label;
    public final int defaultWidth;
    public final int defaultHeight;
    private static final GuiTweaksModule$Key[] $VALUES;

    public static GuiTweaksModule$Key[] values() {
        return (GuiTweaksModule$Key[])$VALUES.clone();
    }

    public static GuiTweaksModule$Key valueOf(String string) {
        return Enum.valueOf(GuiTweaksModule$Key.class, string);
    }

    private GuiTweaksModule$Key(String string2, int n2, int n3) {
        this.label = string2;
        this.defaultWidth = n2;
        this.defaultHeight = n3;
    }

    private static GuiTweaksModule$Key[] $values() {
        return new GuiTweaksModule$Key[]{SURVIVAL, HOTBAR, HEALTH, ARMOR, HUNGER, SCOREBOARD, CHAT, BOSSBAR, EFFECTS, EXPERIENCE, AIR, MOUNT};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[24];
                var4_1 = 0;
                var3_2 = "SV@Ir\u0005_kxhT\u0007[_SBet1\nMzzuCB\r\u007fkq\u0004]qts\n[AEBti'PZP\u0010MlgqOV\u0003r9vkSS\u0016{k\u0006Vl{`CR\u0006VvaeGR\u0006VVAEgr\u000e[aebTI\u0007pzp'DA\u0010\fSv`iR\u0000\n{xysN\u0006V\\TKrh\u000b_pg'DU\u0000|upt\nMZZUcb-_KQ\u0003_PG\u0005_KXHt\u0004]QTS\u0006V|tkRH\u0006VL[@cr\bMLGQov#R\b\\vft\u0006B\u0003l";
                var5_3 = "SV@Ir\u0005_kxhT\u0007[_SBet1\nMzzuCB\r\u007fkq\u0004]qts\n[AEBti'PZP\u0010MlgqOV\u0003r9vkSS\u0016{k\u0006Vl{`CR\u0006VvaeGR\u0006VVAEgr\u000e[aebTI\u0007pzp'DA\u0010\fSv`iR\u0000\n{xysN\u0006V\\TKrh\u000b_pg'DU\u0000|upt\nMZZUcb-_KQ\u0003_PG\u0005_KXHt\u0004]QTS\u0006V|tkRH\u0006VL[@cr\bMLGQov#R\b\\vft\u0006B\u0003l".length();
                var2_4 = 5;
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
                    var3_2 = "\\VFTda0\u000eMmtsSSB{\u007fsbET\u0011";
                    var5_3 = "\\VFTda0\u000eMmtsSSB{\u007fsbET\u0011".length();
                    var2_4 = 7;
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
                        v10 = 30;
                        break;
                    }
                    case 1: {
                        v10 = 25;
                        break;
                    }
                    case 2: {
                        v10 = 21;
                        break;
                    }
                    case 3: {
                        v10 = 7;
                        break;
                    }
                    case 4: {
                        v10 = 38;
                        break;
                    }
                    case 5: {
                        v10 = 32;
                        break;
                    }
                    default: {
                        v10 = 98;
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
        GuiTweaksModule$Key.SURVIVAL = new GuiTweaksModule$Key(var0[6], 182, 40);
        GuiTweaksModule$Key.HOTBAR = new GuiTweaksModule$Key(var0[8], 182, 22);
        GuiTweaksModule$Key.HEALTH = new GuiTweaksModule$Key(var0[18], 81, 10);
        GuiTweaksModule$Key.ARMOR = new GuiTweaksModule$Key(var0[1], 81, 10);
        GuiTweaksModule$Key.HUNGER = new GuiTweaksModule$Key(var0[7], 81, 10);
        GuiTweaksModule$Key.SCOREBOARD = new GuiTweaksModule$Key(var0[3], 100, 60);
        GuiTweaksModule$Key.CHAT = new GuiTweaksModule$Key(var0[4], 320, 90);
        GuiTweaksModule$Key.BOSSBAR = new GuiTweaksModule$Key(var0[21], 182, 19);
        GuiTweaksModule$Key.EFFECTS = new GuiTweaksModule$Key(var0[23], 25, 25);
        GuiTweaksModule$Key.EXPERIENCE = new GuiTweaksModule$Key(var0[10], 182, 5);
        GuiTweaksModule$Key.AIR = new GuiTweaksModule$Key(var0[13], 81, 10);
        GuiTweaksModule$Key.MOUNT = new GuiTweaksModule$Key(var0[11], 81, 10);
        GuiTweaksModule$Key.$VALUES = GuiTweaksModule$Key.$values();
    }
}
