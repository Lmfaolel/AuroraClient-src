package dev.quelchi.aurora.gui;

public final class Glass$Kind
extends Enum<Glass$Kind> {
    public static final /* enum */ Glass$Kind WINDOW;
    public static final /* enum */ Glass$Kind CARD;
    public static final /* enum */ Glass$Kind CARD_ON;
    public static final /* enum */ Glass$Kind CARD_OFF;
    public static final /* enum */ Glass$Kind WELL;
    public static final /* enum */ Glass$Kind LIVE;
    public static final /* enum */ Glass$Kind POPOVER;
    private static final Glass$Kind[] $VALUES;

    public static Glass$Kind[] values() {
        return (Glass$Kind[])$VALUES.clone();
    }

    public static Glass$Kind valueOf(String string) {
        return Enum.valueOf(Glass$Kind.class, string);
    }

    private static Glass$Kind[] $values() {
        return new Glass$Kind[]{WINDOW, CARD, CARD_ON, CARD_OFF, WELL, LIVE, POPOVER};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[7];
                var4_1 = 0;
                var3_2 = "\u001fHF\u0013c/.\u001a\u0004\u0010@B\u0012\u0006\u000b@Z\u0013s7\u0004\u000bLX\u001b\u0007\u001fHF\u0013c/&";
                var5_3 = "\u001fHF\u0013c/.\u001a\u0004\u0010@B\u0012\u0006\u000b@Z\u0013s7\u0004\u000bLX\u001b\u0007\u001fHF\u0013c/&".length();
                var2_4 = 8;
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
                    var3_2 = "\u001fHF\u0013\u0007\fFD\u0018j%:";
                    var5_3 = "\u001fHF\u0013\u0007\fFD\u0018j%:".length();
                    var2_4 = 4;
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
                        v10 = 92;
                        break;
                    }
                    case 1: {
                        v10 = 9;
                        break;
                    }
                    case 2: {
                        v10 = 20;
                        break;
                    }
                    case 3: {
                        v10 = 87;
                        break;
                    }
                    case 4: {
                        v10 = 60;
                        break;
                    }
                    case 5: {
                        v10 = 96;
                        break;
                    }
                    default: {
                        v10 = 104;
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
        Glass$Kind.WINDOW = new Glass$Kind();
        Glass$Kind.CARD = new Glass$Kind();
        Glass$Kind.CARD_ON = new Glass$Kind();
        Glass$Kind.CARD_OFF = new Glass$Kind();
        Glass$Kind.WELL = new Glass$Kind();
        Glass$Kind.LIVE = new Glass$Kind();
        Glass$Kind.POPOVER = new Glass$Kind();
        Glass$Kind.$VALUES = Glass$Kind.$values();
    }
}
