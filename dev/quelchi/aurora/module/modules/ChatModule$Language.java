package dev.quelchi.aurora.module.modules;

public final class ChatModule$Language
extends Enum<ChatModule$Language> {
    public static final /* enum */ ChatModule$Language English;
    public static final /* enum */ ChatModule$Language Spanish;
    public static final /* enum */ ChatModule$Language Portuguese;
    public static final /* enum */ ChatModule$Language French;
    public static final /* enum */ ChatModule$Language German;
    public static final /* enum */ ChatModule$Language Italian;
    public static final /* enum */ ChatModule$Language Dutch;
    public static final /* enum */ ChatModule$Language Polish;
    public static final /* enum */ ChatModule$Language Russian;
    public static final /* enum */ ChatModule$Language Ukrainian;
    public static final /* enum */ ChatModule$Language Serbian;
    public static final /* enum */ ChatModule$Language Croatian;
    public static final /* enum */ ChatModule$Language Turkish;
    public static final /* enum */ ChatModule$Language Arabic;
    public static final /* enum */ ChatModule$Language Chinese;
    public static final /* enum */ ChatModule$Language Japanese;
    public static final /* enum */ ChatModule$Language Korean;
    public static final /* enum */ ChatModule$Language Hindi;
    public static final /* enum */ ChatModule$Language Indonesian;
    public static final /* enum */ ChatModule$Language Vietnamese;
    public static final /* enum */ ChatModule$Language Swedish;
    public static final /* enum */ ChatModule$Language Czech;
    public static final /* enum */ ChatModule$Language Romanian;
    public static final /* enum */ ChatModule$Language Hungarian;
    public static final /* enum */ ChatModule$Language Greek;
    final String code;
    private static final ChatModule$Language[] $VALUES;

    public static ChatModule$Language[] values() {
        return (ChatModule$Language[])$VALUES.clone();
    }

    public static ChatModule$Language valueOf(String string) {
        return Enum.valueOf(ChatModule$Language.class, string);
    }

    private ChatModule$Language(String string2) {
        this.code = string2;
    }

    private static ChatModule$Language[] $values() {
        return new ChatModule$Language[]{English, Spanish, Portuguese, French, German, Italian, Dutch, Polish, Russian, Ukrainian, Serbian, Croatian, Turkish, Arabic, Chinese, Japanese, Korean, Hindi, Indonesian, Vietnamese, Swedish, Czech, Romanian, Hungarian, Greek};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[50];
                var4_1 = 0;
                var3_2 = "c\u001c\u0005V\u001br\u0005D\u0002t\u001a\u0007B\fe\u0002F\u007f}\u0002x\u001d\u0002e\u001b\u0002r\u001a\u0006W\u001br\u000eLv\u0002d\u0002\b[\bg\u0001A{`t\bR\u001bx\u0001[wr\u007f\nG\u0000r\u0014A\u007f~t\u001ar\u0002y\u001c\u0002{\b\u0006A\u0006{\t\\v\bC\u0006z\u0001Awr\u007f\u0007E\u001ce\u000bFm{\u0002b\u001b\u0006P\u001bv\u0002F}\u0002x\r\u0007R\u0001~\u000eJmv\u0007B\u0019v\u000eFm{\u0005R\u0013r\u0003G\nX\u0007s\u000fA{`x\by\nA\u0006e\u0014Zyft\u001ar\u0002b\u001f\u0002c\u0006\u0002t\u0007\u0006V\fe\rNp\u0007T\u0007p\fFm{\u0002y\u001b\u0005Y\u0000y\u0004F\u0005k\u0001:#a\u0007X\u001dv\fF\u007f}\u0002a\u0005\u0005U\u001cc\u0003G\u0007B\u001er\u0004Fm{\u0002\u007f\u0005\u0002z\u0006\u0002a\u001d\u0006Z\u0006e\u0005Np\u0002t\u0005\u0002y\u0000\u0002p\u001b\u0002g\u0000\u0007C\u001cd\u0013F\u007f}\tY\u001cy\u0007Nlzp\u0007\tD\u0002e\u0001Fpzp\u0007";
                var5_3 = "c\u001c\u0005V\u001br\u0005D\u0002t\u001a\u0007B\fe\u0002F\u007f}\u0002x\u001d\u0002e\u001b\u0002r\u001a\u0006W\u001br\u000eLv\u0002d\u0002\b[\bg\u0001A{`t\bR\u001bx\u0001[wr\u007f\nG\u0000r\u0014A\u007f~t\u001ar\u0002y\u001c\u0002{\b\u0006A\u0006{\t\\v\bC\u0006z\u0001Awr\u007f\u0007E\u001ce\u000bFm{\u0002b\u001b\u0006P\u001bv\u0002F}\u0002x\r\u0007R\u0001~\u000eJmv\u0007B\u0019v\u000eFm{\u0005R\u0013r\u0003G\nX\u0007s\u000fA{`x\by\nA\u0006e\u0014Zyft\u001ar\u0002b\u001f\u0002c\u0006\u0002t\u0007\u0006V\fe\rNp\u0007T\u0007p\fFm{\u0002y\u001b\u0005Y\u0000y\u0004F\u0005k\u0001:#a\u0007X\u001dv\fF\u007f}\u0002a\u0005\u0005U\u001cc\u0003G\u0007B\u001er\u0004Fm{\u0002\u007f\u0005\u0002z\u0006\u0002a\u001d\u0006Z\u0006e\u0005Np\u0002t\u0005\u0002y\u0000\u0002p\u001b\u0002g\u0000\u0007C\u001cd\u0013F\u007f}\tY\u001cy\u0007Nlzp\u0007\tD\u0002e\u0001Fpzp\u0007".length();
                var2_4 = 2;
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
                    var3_2 = "w\u001b\u0002u\f";
                    var5_3 = "w\u001b\u0002u\f".length();
                    var2_4 = 2;
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
                        v10 = 17;
                        break;
                    }
                    case 1: {
                        v10 = 105;
                        break;
                    }
                    case 2: {
                        v10 = 23;
                        break;
                    }
                    case 3: {
                        v10 = 96;
                        break;
                    }
                    case 4: {
                        v10 = 47;
                        break;
                    }
                    case 5: {
                        v10 = 30;
                        break;
                    }
                    default: {
                        v10 = 19;
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
        ChatModule$Language.English = new ChatModule$Language(var0[27]);
        ChatModule$Language.Spanish = new ChatModule$Language(var0[2]);
        ChatModule$Language.Portuguese = new ChatModule$Language(var0[39]);
        ChatModule$Language.French = new ChatModule$Language(var0[48]);
        ChatModule$Language.German = new ChatModule$Language(var0[49]);
        ChatModule$Language.Italian = new ChatModule$Language(var0[4]);
        ChatModule$Language.Dutch = new ChatModule$Language(var0[37]);
        ChatModule$Language.Polish = new ChatModule$Language(var0[34]);
        ChatModule$Language.Russian = new ChatModule$Language(var0[0]);
        ChatModule$Language.Ukrainian = new ChatModule$Language(var0[8]);
        ChatModule$Language.Serbian = new ChatModule$Language(var0[17]);
        ChatModule$Language.Croatian = new ChatModule$Language(var0[30]);
        ChatModule$Language.Turkish = new ChatModule$Language(var0[5]);
        ChatModule$Language.Arabic = new ChatModule$Language(var0[43]);
        ChatModule$Language.Chinese = new ChatModule$Language(var0[32]);
        ChatModule$Language.Japanese = new ChatModule$Language(var0[13]);
        ChatModule$Language.Korean = new ChatModule$Language(var0[38]);
        ChatModule$Language.Hindi = new ChatModule$Language(var0[42]);
        ChatModule$Language.Indonesian = new ChatModule$Language(var0[19]);
        ChatModule$Language.Vietnamese = new ChatModule$Language(var0[44]);
        ChatModule$Language.Swedish = new ChatModule$Language(var0[25]);
        ChatModule$Language.Czech = new ChatModule$Language(var0[6]);
        ChatModule$Language.Romanian = new ChatModule$Language(var0[26]);
        ChatModule$Language.Hungarian = new ChatModule$Language(var0[12]);
        ChatModule$Language.Greek = new ChatModule$Language(var0[41]);
        ChatModule$Language.$VALUES = ChatModule$Language.$values();
    }
}
