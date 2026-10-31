package dev.quelchi.aurora.module.modules;

public final class TierTaggerModule$TierList
extends Enum<TierTaggerModule$TierList> {
    public static final /* enum */ TierTaggerModule$TierList MCTiers;
    public static final /* enum */ TierTaggerModule$TierList PvPHQ;
    public static final /* enum */ TierTaggerModule$TierList PvPTiers;
    public static final /* enum */ TierTaggerModule$TierList Subtiers;
    public final String host;
    private static final TierTaggerModule$TierList[] $VALUES;

    public static TierTaggerModule$TierList[] values() {
        return (TierTaggerModule$TierList[])$VALUES.clone();
    }

    public static TierTaggerModule$TierList valueOf(String string) {
        return Enum.valueOf(TierTaggerModule$TierList.class, string);
    }

    private TierTaggerModule$TierList(String string2) {
        this.host = string2;
    }

    private static TierTaggerModule$TierList[] $values() {
        return new TierTaggerModule$TierList[]{MCTiers, PvPHQ, PvPTiers, Subtiers};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[8];
                var4_1 = 0;
                var3_2 = "w%eM?\rQh>\u0005W%Em\u001f\bT&wQ'F@t\u0007J\u0010AL+QA\fw%eQ'F@t}vJ#\ft&wQ'F@t}{@:";
                var5_3 = "w%eM?\rQh>\u0005W%Em\u001f\bT&wQ'F@t\u0007J\u0010AL+QA\fw%eQ'F@t}vJ#\ft&wQ'F@t}{@:".length();
                var2_4 = 9;
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
                    var3_2 = "j0aL+QA)=pQ\bW%Eq'F@t";
                    var5_3 = "j0aL+QA)=pQ\bW%Eq'F@t".length();
                    var2_4 = 11;
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
                        v10 = 7;
                        break;
                    }
                    case 1: {
                        v10 = 83;
                        break;
                    }
                    case 2: {
                        v10 = 21;
                        break;
                    }
                    case 3: {
                        v10 = 37;
                        break;
                    }
                    case 4: {
                        v10 = 78;
                        break;
                    }
                    case 5: {
                        v10 = 35;
                        break;
                    }
                    default: {
                        v10 = 50;
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
        TierTaggerModule$TierList.MCTiers = new TierTaggerModule$TierList(var0[6]);
        TierTaggerModule$TierList.PvPHQ = new TierTaggerModule$TierList(var0[0]);
        TierTaggerModule$TierList.PvPTiers = new TierTaggerModule$TierList(var0[4]);
        TierTaggerModule$TierList.Subtiers = new TierTaggerModule$TierList(var0[5]);
        TierTaggerModule$TierList.$VALUES = TierTaggerModule$TierList.$values();
    }
}
