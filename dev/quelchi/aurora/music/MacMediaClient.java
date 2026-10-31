package dev.quelchi.aurora.music;

import dev.quelchi.aurora.music.Lyrics;
import dev.quelchi.aurora.music.NowPlaying;
import dev.quelchi.aurora.music.SmtcClient;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;

final class MacMediaClient {
    private static final String SPOTIFY;
    private static final String MUSIC;
    private static final String MUSIC_ART;
    private static volatile String app;
    private static final String[] a;
    private static final long b;

    private MacMediaClient() {
    }

    static void start(int n) {
        Thread thread = new Thread(() -> MacMediaClient.lambda$start$0(n), a[3]);
        thread.setDaemon(true);
        thread.start();
    }

    private static void poll() {
        block39: {
            String string;
            Object object;
            block37: {
                String[] stringArray;
                block38: {
                    String[] stringArray2;
                    String string2;
                    boolean bl;
                    String[] stringArray3;
                    block35: {
                        block36: {
                            block31: {
                                block32: {
                                    String[] stringArray4;
                                    block33: {
                                        block34: {
                                            String[] stringArray5;
                                            block29: {
                                                block30: {
                                                    stringArray3 = a;
                                                    stringArray = stringArray5 = MacMediaClient.query(stringArray3[23]);
                                                    bl = Lyrics.a;
                                                    string2 = stringArray3[12];
                                                    stringArray2 = stringArray5;
                                                    if (bl) break block29;
                                                    if (stringArray2 == null) break block30;
                                                    stringArray2 = stringArray5;
                                                    if (bl) break block31;
                                                    if (stringArray2[0].equals(a[1])) break block32;
                                                }
                                                stringArray2 = MacMediaClient.query(a[5]);
                                            }
                                            if (bl) break block31;
                                            object = stringArray2;
                                            if (object == null) break block32;
                                            stringArray4 = stringArray5;
                                            if (bl) break block33;
                                            if (stringArray4 == null) break block34;
                                            stringArray2 = object;
                                            if (bl) break block31;
                                            if (!stringArray2[0].equals(a[13])) break block32;
                                        }
                                        stringArray4 = object;
                                    }
                                    stringArray = stringArray4;
                                    stringArray3 = a;
                                    string2 = stringArray3[16];
                                }
                                stringArray2 = stringArray;
                            }
                            if (bl) break block35;
                            if (stringArray2 != null) break block36;
                            app = null;
                            SmtcClient.nothing();
                            return;
                        }
                        app = string2;
                        stringArray2 = stringArray;
                    }
                    stringArray3 = a;
                    object = SmtcClient.publish(stringArray2[1], stringArray[2], stringArray[3], MacMediaClient.num(stringArray[4]), MacMediaClient.num(stringArray[5]), stringArray[0].equals(stringArray3[13]));
                    string = string2;
                    if (bl) break block37;
                    if (!string.equals(stringArray3[20])) break block38;
                    SmtcClient.art((NowPlaying)object, a[15], MacMediaClient::musicArt);
                    if (!bl) break block39;
                }
                string = stringArray[6];
            }
            String string3 = string;
            SmtcClient.art((NowPlaying)object, string3, () -> MacMediaClient.lambda$poll$1(string3));
        }
    }

    private static String[] query(String string) {
        String[] stringArray;
        block10: {
            block11: {
                String string2;
                boolean bl;
                block8: {
                    String string3;
                    block9: {
                        String[] stringArray2 = new String[3];
                        String[] stringArray3 = a;
                        stringArray2[0] = stringArray3[24];
                        stringArray2[1] = stringArray3[26];
                        stringArray2[2] = string;
                        string3 = SmtcClient.exec(stringArray2);
                        bl = Lyrics.a;
                        string2 = string3;
                        if (bl) break block8;
                        if (string2 != null) break block9;
                        SmtcClient.warnOnce(a[10]);
                        return null;
                    }
                    string2 = string3.strip();
                }
                String[] stringArray4 = string2.split("\t", -1);
                stringArray = stringArray4;
                if (bl) break block10;
                if (stringArray.length != 7) break block11;
                stringArray = stringArray4;
                break block10;
            }
            stringArray = null;
        }
        return stringArray;
    }

    
    private static byte[] musicArt() throws Exception {
        String[] stringArray = a;
        Path path = Files.createTempFile(stringArray[6], stringArray[11], new FileAttribute[0]);
        boolean bl = Lyrics.a;
        try {
            byte[] byArray;
            block10: {
                block8: {
                    String string;
                    block7: {
                        block9: {
                            string = SmtcClient.exec(stringArray[2], stringArray[18], stringArray[25], path.toString());
                            if (bl) break block7;
                            if (Files.size(path) <= 0L) break block8;
                            break block9;
                            catch (Exception exception) {
                                throw exception;
                            }
                        }
                        string = path.toUri().toString();
                    }
                    byArray = SmtcClient.load(string);
                    break block10;
                }
                byArray = null;
            }
            byte[] byArray2 = byArray;
            return byArray2;
        }
        finally {
            Files.deleteIfExists(path);
        }
    }

    private static long num(String string) {
        try {
            return Math.max(0L, Long.parseLong(string.trim()));
        }
        catch (NumberFormatException numberFormatException) {
            return 0L;
        }
    }

    
    static void control(String string) {
        int n;
        Object object;
        String string2;
        block18: {
            string2 = app;
            boolean bl = Lyrics.a;
            String string3 = string2;
            if (!bl) {
                if (string3 == null) {
                    return;
                }
                string3 = string;
            }
            object = string3;
            int n2 = -1;
            n = ((String)object).hashCode();
            if (bl) break block18;
            switch (n) {
                case 3377907: {
                    n = ((String)object).equals(a[0]) ? 1 : 0;
                    if (!bl) {
                        if (n == 0) break;
                        n2 = 0;
                        if (!bl) break;
                    }
                    break block18;
                }
                case -1273775369: {
                    n = ((String)object).equals(a[7]) ? 1 : 0;
                    if (bl) break block18;
                    if (n == 0) break;
                    n2 = 1;
                }
            }
            n = n2;
        }
        String string4 = switch (n) {
            case 0 -> a[21];
            case 1 -> {
                String[] var6_5 = a;
                yield var6_5[19];
            }
            default -> {
                String[] var6_5 = a;
                yield var6_5[8];
            }
        };
        object = new Thread(() -> MacMediaClient.lambda$control$2(string2, string4), a[27]);
        ((Thread)object).setDaemon(true);
        ((Thread)object).start();
    }

    private static void lambda$control$2(String string, String string2) {
        String[] stringArray = new String[3];
        String[] stringArray2 = a;
        stringArray[0] = stringArray2[2];
        stringArray[1] = stringArray2[18];
        stringArray[2] = stringArray2[22] + string + stringArray2[14] + string2;
        SmtcClient.exec(stringArray);
    }

    private static byte[] lambda$poll$1(String string) throws Exception {
        return SmtcClient.load(string);
    }

    
    private static void lambda$start$0(int n) {
        while (SmtcClient.alive(n)) {
            MacMediaClient.poll();
            try {
                Thread.sleep(b);
            }
            catch (InterruptedException interruptedException) {
                return;
            }
        }
        return;
    }

    
    static {
        block21: {
            block20: {
                block19: {
                    var7 = new String[28];
                    var5_1 = 0;
                    var4_2 = "a\bN\r\u0007\u007f\u0001W\u0000ZN\u0002\t`\u001eW\nPR\f\u007f\u0019\u000fN\u0018D\u0016AAHB\fU4VD\fn\u01a0f\u000b\u0016\u0018CP\tf\u000eW\rZO\u000b/Oe\t\\T\fi\u0014\u0014YZSEa\u0002BYAU\u000ba\u0004X\u001e\u0013T\rj\u0003\u0016\u000bVT\u0010}\u0003\u0016[\u0011*\u0011j\u0001ZYRP\u0015c\u0004U\u0018GI\naM\u0014*CO\u0011f\u000bO[9\u0000E/M_\u001f\u0013P\tn\u0014S\u000b\u0013S\u0011n\u0019SYZSE|\u0019Y\tCE\u0001/\u0019^\u001c]\u0000\u0017j\u0019C\u000b]\u0000G-g\u0016Y\u0013\u0000\u0016j\u0019\u0016\r\u0013T\n/\u000eC\u000bAE\u000b{MB\u000bRC\u000e\u0005M\u0016Y\u0013S\u0000{MW\u000bG\u0000\u0011`M\u0014[9\u0000E/MB\u000bJ*E/M\u0016Y\u0013\u0000E|\bBYRR\u0011/\u0019YYRR\u0011x\u0002D\u0012\u0013U\u0017cMY\u001f\u0013To/M\u0016YVN\u0001/\u0019D\u00009\u0000E/MD\u001cGU\u0017aM\u001e\t_A\u001cj\u001f\u0016\nGA\u0011jMW\n\u0013S\u0011}\u0004X\u001e\u001a\u0000C/\u0019W\u001b\u0013\u0006E'\u0003W\u0014V\u0000\niMBP\u0013\u0006E{\fTY\u0015\u0000Mn\u001fB\u0010@TE`\u000b\u0016\r\u001a\u0000C/\u0019W\u001b\u0013\u0006E'\fZ\u001bFME`\u000b\u0016\r\u001a\u0000C/\u0019W\u001b\u0013\u0006E'\tC\u000bRT\f`\u0003\u0016\u0016U\u0000\u0011&M\u0010YGA\u0007/K\u0016QAO\u0010a\t\u0016Q\u001bP\tn\u0014S\u000b\u0013P\n|\u0004B\u0010\\NL/G\u0016H\u0003\u0010U&D\u0016_\u0013T\u0004mM\u0010YRR\u0011\u0005\bX\u001d\u0013T\u0000c\u0001<\u0162f\u000b\u0016\u0018CP\tf\u000eW\rZO\u000b/O{\f@I\u0006-M_\n\u0013N\n{MD\f]N\fa\n\u0016\r[E\u000b/\u001fS\rFR\u000b/O\u0014sGE\tcMW\tCL\fl\fB\u0010\\NE- C\nZCG\u0005M\u0016Y\u0013I\u0003/\u001dZ\u0018JE\u0017/\u001eB\u0018GEEf\u001e\u0016\nGO\u0015\u007f\bRYGH\u0000aMD\u001cGU\u0017aM\u0014[9\u0000E/ME\u001cG\u0000\u0011/\u0019YYPU\u0017}\bX\r\u0013T\u0017n\u000e]s\u0013\u0000E/\u001fS\rFR\u000b/EF\u0015RY\u0000}ME\rRT\u0000/\fEY@T\u0017f\u0003QP\u0013\u0006E{\fTY\u0015\u0000Ma\f[\u001c\u0013O\u0003/\u0019\u001fY\u0015\u0000\u0011n\u000f\u0016_\u0013\b\u0004}\u0019_\nG\u0000\niMBP\u0013\u0006E{\fTY\u0015\u0000Mn\u0001T\f^\u0000\niMBP\u0013\u0006E{\fTY\u0015\u0000M}\u0002C\u0017W\u0000M'\tC\u000bRT\f`\u0003\u0016\u0016U\u0000\u0011&M\u001cY\u0002\u0010U?D\u001fY\u0015\u0000\u0011n\u000f\u0016_\u0013\b\u0017`\u0018X\u001d\u0013\bM\u007f\u0001W\u0000VRE\u007f\u0002E\u0010GI\naD\u0016S\u0013\u0011U?]\u001fP\u0013\u0006E{\fTY\u0015\u0000G-gS\u0017W\u0000\u0011j\u0001Zs\u000bn\u0018D\u0016AAHn\u001fBT\b\u007f\u001fS\u000fZO\u0010|\t\u007f\u0001W\u0000CA\u0010|\b\u00fe`\u0003\u0016\u000bFNEn\u001fQ\u000f9\u0000E/MB\u001c_LEn\u001dF\u0015ZC\u0004{\u0004Y\u0017\u0013\u0002(z\u001e_\u001a\u0011*E/M\u0016Y\u0013\u0000E|\bBYW\u0000\u0011`MD\u0018D\u0000\u0001n\u0019WY\\FEn\u001fB\u000e\\R\u000e/\\\u0016\u0016U\u0000\u0006z\u001fD\u001c]TE{\u001fW\u001aX*E/M\u0016\u001c]DE{\bZ\u00159\u0000E/ME\u001cG\u0000\u0003/\u0019YY\\P\u0000aMP\u0016A\u0000\u0004l\u000eS\n@\u0000M_\"e0k\u0000\u0003f\u0001SY\u001bI\u0011j\u0000\u0016H\u0013O\u0003/\fD\u001eE\tL/\u001a_\r[\u0000\u0012}\u0004B\u001c\u0013P\u0000}\u0000_\n@I\nag\u0016Y\u0013\u0000\u0016j\u0019\u0016\u001c\\FEiMB\u0016\u0013\u0010o/M\u0016YDR\f{\b\u0016\u001d\u0013T\n/\u000b<Y\u0013\u0000El\u0001Y\nV\u0000\u0004l\u000eS\n@\u0000\u0003\u0005\bX\u001d\u0013R\u0010ag\u0013`\u001eW\nPR\f\u007f\u0019\u0016\u0017\\TEi\u0002C\u0017W\u0004!\u0004[\u001e\u0007\\\u001dY\rZF\u001c\u0007\u007f\u0001W\u0000ZN\u0002\u0005-MB\u0016\u0013\u0005b\u0018E\u0010P\u0005B\u0018E\u0010P\u0162f\u000b\u0016\u0018CP\tf\u000eW\rZO\u000b/O{\f@I\u0006-M_\n\u0013N\n{MD\f]N\fa\n\u0016\r[E\u000b/\u001fS\rFR\u000b/O\u0014sGE\tcMW\tCL\fl\fB\u0010\\NE- C\nZCG\u0005M\u0016Y\u0013I\u0003/\u001dZ\u0018JE\u0017/\u001eB\u0018GEEf\u001e\u0016\nGO\u0015\u007f\bRYGH\u0000aMD\u001cGU\u0017aM\u0014[9\u0000E/ME\u001cG\u0000\u0011/\u0019YYPU\u0017}\bX\r\u0013T\u0017n\u000e]s\u0013\u0000E/\u001fS\rFR\u000b/EF\u0015RY\u0000}ME\rRT\u0000/\fEY@T\u0017f\u0003QP\u0013\u0006E{\fTY\u0015\u0000Ma\f[\u001c\u0013O\u0003/\u0019\u001fY\u0015\u0000\u0011n\u000f\u0016_\u0013\b\u0004}\u0019_\nG\u0000\niMBP\u0013\u0006E{\fTY\u0015\u0000Mn\u0001T\f^\u0000\niMBP\u0013\u0006E{\fTY\u0015\u0000M}\u0002C\u0017W\u0000M'\tC\u000bRT\f`\u0003\u0016\u0016U\u0000\u0011&M\u001cY\u0002\u0010U?D\u001fY\u0015\u0000\u0011n\u000f\u0016_\u0013\b\u0017`\u0018X\u001d\u0013\bM\u007f\u0001W\u0000VRE\u007f\u0002E\u0010GI\naD\u0016S\u0013\u0011U?]\u001fP\u0013\u0006E{\fTY\u0015\u0000G-gS\u0017W\u0000\u0011j\u0001Zs\u0002\"\b\u000e\u007f\u001fS\u000fZO\u0010|MB\u000bRC\u000e\u0005B\u0018E\u0010P\na\bN\r\u0013T\u0017n\u000e]\u0012{\bZ\u0015\u0013A\u0015\u007f\u0001_\u001aRT\f`\u0003\u0016[\u01a0f\u000b\u0016\u0018CP\tf\u000eW\rZO\u000b/Oe\t\\T\fi\u0014\u0014YZSEa\u0002BYAU\u000ba\u0004X\u001e\u0013T\rj\u0003\u0016\u000bVT\u0010}\u0003\u0016[\u0011*\u0011j\u0001ZYRP\u0015c\u0004U\u0018GI\naM\u0014*CO\u0011f\u000bO[9\u0000E/M_\u001f\u0013P\tn\u0014S\u000b\u0013S\u0011n\u0019SYZSE|\u0019Y\tCE\u0001/\u0019^\u001c]\u0000\u0017j\u0019C\u000b]\u0000G-g\u0016Y\u0013\u0000\u0016j\u0019\u0016\r\u0013T\n/\u000eC\u000bAE\u000b{MB\u000bRC\u000e\u0005M\u0016Y\u0013S\u0000{MW\u000bG\u0000\u0011`M\u0014[9\u0000E/MB\u000bJ*E/M\u0016Y\u0013\u0000E|\bBYRR\u0011/\u0019YYRR\u0011x\u0002D\u0012\u0013U\u0017cMY\u001f\u0013To/M\u0016YVN\u0001/\u0019D\u00009\u0000E/MD\u001cGU\u0017aM\u001e\t_A\u001cj\u001f\u0016\nGA\u0011jMW\n\u0013S\u0011}\u0004X\u001e\u001a\u0000C/\u0019W\u001b\u0013\u0006E'\u0003W\u0014V\u0000\niMBP\u0013\u0006E{\fTY\u0015\u0000Mn\u001fB\u0010@TE`\u000b\u0016\r\u001a\u0000C/\u0019W\u001b\u0013\u0006E'\fZ\u001bFME`\u000b\u0016\r\u001a\u0000C/\u0019W\u001b\u0013\u0006E'\tC\u000bRT\f`\u0003\u0016\u0016U\u0000\u0011&M\u0010YGA\u0007/K\u0016QAO\u0010a\t\u0016Q\u001bP\tn\u0014S\u000b\u0013P\n|\u0004B\u0010\\NL/G\u0016H\u0003\u0010U&D\u0016_\u0013T\u0004mM\u0010YRR\u0011\u0005\bX\u001d\u0013T\u0000c\u0001<\t`\u001eW\nPR\f\u007f\u0019\u00fe`\u0003\u0016\u000bFNEn\u001fQ\u000f9\u0000E/MB\u001c_LEn\u001dF\u0015ZC\u0004{\u0004Y\u0017\u0013\u0002(z\u001e_\u001a\u0011*E/M\u0016Y\u0013\u0000E|\bBYW\u0000\u0011`MD\u0018D\u0000\u0001n\u0019WY\\FEn\u001fB\u000e\\R\u000e/\\\u0016\u0016U\u0000\u0006z\u001fD\u001c]TE{\u001fW\u001aX*E/M\u0016\u001c]DE{\bZ\u00159\u0000E/ME\u001cG\u0000\u0003/\u0019YY\\P\u0000aMP\u0016A\u0000\u0004l\u000eS\n@\u0000M_\"e0k\u0000\u0003f\u0001SY\u001bI\u0011j\u0000\u0016H\u0013O\u0003/\fD\u001eE\tL/\u001a_\r[\u0000\u0012}\u0004B\u001c\u0013P\u0000}\u0000_\n@I\nag\u0016Y\u0013\u0000\u0016j\u0019\u0016\u001c\\FEiMB\u0016\u0013\u0010o/M\u0016YDR\f{\b\u0016\u001d\u0013T\n/\u000b<Y\u0013\u0000El\u0001Y\nV\u0000\u0004l\u000eS\n@\u0000\u0003\u0005\bX\u001d\u0013R\u0010ag";
                    var6_3 = "a\bN\r\u0007\u007f\u0001W\u0000ZN\u0002\t`\u001eW\nPR\f\u007f\u0019\u000fN\u0018D\u0016AAHB\fU4VD\fn\u01a0f\u000b\u0016\u0018CP\tf\u000eW\rZO\u000b/Oe\t\\T\fi\u0014\u0014YZSEa\u0002BYAU\u000ba\u0004X\u001e\u0013T\rj\u0003\u0016\u000bVT\u0010}\u0003\u0016[\u0011*\u0011j\u0001ZYRP\u0015c\u0004U\u0018GI\naM\u0014*CO\u0011f\u000bO[9\u0000E/M_\u001f\u0013P\tn\u0014S\u000b\u0013S\u0011n\u0019SYZSE|\u0019Y\tCE\u0001/\u0019^\u001c]\u0000\u0017j\u0019C\u000b]\u0000G-g\u0016Y\u0013\u0000\u0016j\u0019\u0016\r\u0013T\n/\u000eC\u000bAE\u000b{MB\u000bRC\u000e\u0005M\u0016Y\u0013S\u0000{MW\u000bG\u0000\u0011`M\u0014[9\u0000E/MB\u000bJ*E/M\u0016Y\u0013\u0000E|\bBYRR\u0011/\u0019YYRR\u0011x\u0002D\u0012\u0013U\u0017cMY\u001f\u0013To/M\u0016YVN\u0001/\u0019D\u00009\u0000E/MD\u001cGU\u0017aM\u001e\t_A\u001cj\u001f\u0016\nGA\u0011jMW\n\u0013S\u0011}\u0004X\u001e\u001a\u0000C/\u0019W\u001b\u0013\u0006E'\u0003W\u0014V\u0000\niMBP\u0013\u0006E{\fTY\u0015\u0000Mn\u001fB\u0010@TE`\u000b\u0016\r\u001a\u0000C/\u0019W\u001b\u0013\u0006E'\fZ\u001bFME`\u000b\u0016\r\u001a\u0000C/\u0019W\u001b\u0013\u0006E'\tC\u000bRT\f`\u0003\u0016\u0016U\u0000\u0011&M\u0010YGA\u0007/K\u0016QAO\u0010a\t\u0016Q\u001bP\tn\u0014S\u000b\u0013P\n|\u0004B\u0010\\NL/G\u0016H\u0003\u0010U&D\u0016_\u0013T\u0004mM\u0010YRR\u0011\u0005\bX\u001d\u0013T\u0000c\u0001<\u0162f\u000b\u0016\u0018CP\tf\u000eW\rZO\u000b/O{\f@I\u0006-M_\n\u0013N\n{MD\f]N\fa\n\u0016\r[E\u000b/\u001fS\rFR\u000b/O\u0014sGE\tcMW\tCL\fl\fB\u0010\\NE- C\nZCG\u0005M\u0016Y\u0013I\u0003/\u001dZ\u0018JE\u0017/\u001eB\u0018GEEf\u001e\u0016\nGO\u0015\u007f\bRYGH\u0000aMD\u001cGU\u0017aM\u0014[9\u0000E/ME\u001cG\u0000\u0011/\u0019YYPU\u0017}\bX\r\u0013T\u0017n\u000e]s\u0013\u0000E/\u001fS\rFR\u000b/EF\u0015RY\u0000}ME\rRT\u0000/\fEY@T\u0017f\u0003QP\u0013\u0006E{\fTY\u0015\u0000Ma\f[\u001c\u0013O\u0003/\u0019\u001fY\u0015\u0000\u0011n\u000f\u0016_\u0013\b\u0004}\u0019_\nG\u0000\niMBP\u0013\u0006E{\fTY\u0015\u0000Mn\u0001T\f^\u0000\niMBP\u0013\u0006E{\fTY\u0015\u0000M}\u0002C\u0017W\u0000M'\tC\u000bRT\f`\u0003\u0016\u0016U\u0000\u0011&M\u001cY\u0002\u0010U?D\u001fY\u0015\u0000\u0011n\u000f\u0016_\u0013\b\u0017`\u0018X\u001d\u0013\bM\u007f\u0001W\u0000VRE\u007f\u0002E\u0010GI\naD\u0016S\u0013\u0011U?]\u001fP\u0013\u0006E{\fTY\u0015\u0000G-gS\u0017W\u0000\u0011j\u0001Zs\u000bn\u0018D\u0016AAHn\u001fBT\b\u007f\u001fS\u000fZO\u0010|\t\u007f\u0001W\u0000CA\u0010|\b\u00fe`\u0003\u0016\u000bFNEn\u001fQ\u000f9\u0000E/MB\u001c_LEn\u001dF\u0015ZC\u0004{\u0004Y\u0017\u0013\u0002(z\u001e_\u001a\u0011*E/M\u0016Y\u0013\u0000E|\bBYW\u0000\u0011`MD\u0018D\u0000\u0001n\u0019WY\\FEn\u001fB\u000e\\R\u000e/\\\u0016\u0016U\u0000\u0006z\u001fD\u001c]TE{\u001fW\u001aX*E/M\u0016\u001c]DE{\bZ\u00159\u0000E/ME\u001cG\u0000\u0003/\u0019YY\\P\u0000aMP\u0016A\u0000\u0004l\u000eS\n@\u0000M_\"e0k\u0000\u0003f\u0001SY\u001bI\u0011j\u0000\u0016H\u0013O\u0003/\fD\u001eE\tL/\u001a_\r[\u0000\u0012}\u0004B\u001c\u0013P\u0000}\u0000_\n@I\nag\u0016Y\u0013\u0000\u0016j\u0019\u0016\u001c\\FEiMB\u0016\u0013\u0010o/M\u0016YDR\f{\b\u0016\u001d\u0013T\n/\u000b<Y\u0013\u0000El\u0001Y\nV\u0000\u0004l\u000eS\n@\u0000\u0003\u0005\bX\u001d\u0013R\u0010ag\u0013`\u001eW\nPR\f\u007f\u0019\u0016\u0017\\TEi\u0002C\u0017W\u0004!\u0004[\u001e\u0007\\\u001dY\rZF\u001c\u0007\u007f\u0001W\u0000ZN\u0002\u0005-MB\u0016\u0013\u0005b\u0018E\u0010P\u0005B\u0018E\u0010P\u0162f\u000b\u0016\u0018CP\tf\u000eW\rZO\u000b/O{\f@I\u0006-M_\n\u0013N\n{MD\f]N\fa\n\u0016\r[E\u000b/\u001fS\rFR\u000b/O\u0014sGE\tcMW\tCL\fl\fB\u0010\\NE- C\nZCG\u0005M\u0016Y\u0013I\u0003/\u001dZ\u0018JE\u0017/\u001eB\u0018GEEf\u001e\u0016\nGO\u0015\u007f\bRYGH\u0000aMD\u001cGU\u0017aM\u0014[9\u0000E/ME\u001cG\u0000\u0011/\u0019YYPU\u0017}\bX\r\u0013T\u0017n\u000e]s\u0013\u0000E/\u001fS\rFR\u000b/EF\u0015RY\u0000}ME\rRT\u0000/\fEY@T\u0017f\u0003QP\u0013\u0006E{\fTY\u0015\u0000Ma\f[\u001c\u0013O\u0003/\u0019\u001fY\u0015\u0000\u0011n\u000f\u0016_\u0013\b\u0004}\u0019_\nG\u0000\niMBP\u0013\u0006E{\fTY\u0015\u0000Mn\u0001T\f^\u0000\niMBP\u0013\u0006E{\fTY\u0015\u0000M}\u0002C\u0017W\u0000M'\tC\u000bRT\f`\u0003\u0016\u0016U\u0000\u0011&M\u001cY\u0002\u0010U?D\u001fY\u0015\u0000\u0011n\u000f\u0016_\u0013\b\u0017`\u0018X\u001d\u0013\bM\u007f\u0001W\u0000VRE\u007f\u0002E\u0010GI\naD\u0016S\u0013\u0011U?]\u001fP\u0013\u0006E{\fTY\u0015\u0000G-gS\u0017W\u0000\u0011j\u0001Zs\u0002\"\b\u000e\u007f\u001fS\u000fZO\u0010|MB\u000bRC\u000e\u0005B\u0018E\u0010P\na\bN\r\u0013T\u0017n\u000e]\u0012{\bZ\u0015\u0013A\u0015\u007f\u0001_\u001aRT\f`\u0003\u0016[\u01a0f\u000b\u0016\u0018CP\tf\u000eW\rZO\u000b/Oe\t\\T\fi\u0014\u0014YZSEa\u0002BYAU\u000ba\u0004X\u001e\u0013T\rj\u0003\u0016\u000bVT\u0010}\u0003\u0016[\u0011*\u0011j\u0001ZYRP\u0015c\u0004U\u0018GI\naM\u0014*CO\u0011f\u000bO[9\u0000E/M_\u001f\u0013P\tn\u0014S\u000b\u0013S\u0011n\u0019SYZSE|\u0019Y\tCE\u0001/\u0019^\u001c]\u0000\u0017j\u0019C\u000b]\u0000G-g\u0016Y\u0013\u0000\u0016j\u0019\u0016\r\u0013T\n/\u000eC\u000bAE\u000b{MB\u000bRC\u000e\u0005M\u0016Y\u0013S\u0000{MW\u000bG\u0000\u0011`M\u0014[9\u0000E/MB\u000bJ*E/M\u0016Y\u0013\u0000E|\bBYRR\u0011/\u0019YYRR\u0011x\u0002D\u0012\u0013U\u0017cMY\u001f\u0013To/M\u0016YVN\u0001/\u0019D\u00009\u0000E/MD\u001cGU\u0017aM\u001e\t_A\u001cj\u001f\u0016\nGA\u0011jMW\n\u0013S\u0011}\u0004X\u001e\u001a\u0000C/\u0019W\u001b\u0013\u0006E'\u0003W\u0014V\u0000\niMBP\u0013\u0006E{\fTY\u0015\u0000Mn\u001fB\u0010@TE`\u000b\u0016\r\u001a\u0000C/\u0019W\u001b\u0013\u0006E'\fZ\u001bFME`\u000b\u0016\r\u001a\u0000C/\u0019W\u001b\u0013\u0006E'\tC\u000bRT\f`\u0003\u0016\u0016U\u0000\u0011&M\u0010YGA\u0007/K\u0016QAO\u0010a\t\u0016Q\u001bP\tn\u0014S\u000b\u0013P\n|\u0004B\u0010\\NL/G\u0016H\u0003\u0010U&D\u0016_\u0013T\u0004mM\u0010YRR\u0011\u0005\bX\u001d\u0013T\u0000c\u0001<\t`\u001eW\nPR\f\u007f\u0019\u00fe`\u0003\u0016\u000bFNEn\u001fQ\u000f9\u0000E/MB\u001c_LEn\u001dF\u0015ZC\u0004{\u0004Y\u0017\u0013\u0002(z\u001e_\u001a\u0011*E/M\u0016Y\u0013\u0000E|\bBYW\u0000\u0011`MD\u0018D\u0000\u0001n\u0019WY\\FEn\u001fB\u000e\\R\u000e/\\\u0016\u0016U\u0000\u0006z\u001fD\u001c]TE{\u001fW\u001aX*E/M\u0016\u001c]DE{\bZ\u00159\u0000E/ME\u001cG\u0000\u0003/\u0019YY\\P\u0000aMP\u0016A\u0000\u0004l\u000eS\n@\u0000M_\"e0k\u0000\u0003f\u0001SY\u001bI\u0011j\u0000\u0016H\u0013O\u0003/\fD\u001eE\tL/\u001a_\r[\u0000\u0012}\u0004B\u001c\u0013P\u0000}\u0000_\n@I\nag\u0016Y\u0013\u0000\u0016j\u0019\u0016\u001c\\FEiMB\u0016\u0013\u0010o/M\u0016YDR\f{\b\u0016\u001d\u0013T\n/\u000b<Y\u0013\u0000El\u0001Y\nV\u0000\u0004l\u000eS\n@\u0000\u0003\u0005\bX\u001d\u0013R\u0010ag".length();
                    var3_4 = 4;
                    var2_5 = -1;
lbl7:

                    while (true) {
                        v0 = ++var2_5;
                        v1 = var4_2.substring(v0, v0 + var3_4);
                        v2 = -1;
                        break block19;
                        break;
                    }
lbl12:

                    while (true) {
                        var7[var5_1++] = v3.intern();
                        if ((var2_5 += var3_4) < var6_3) {
                            var3_4 = var4_2.charAt(var2_5);
                            continue;
                        }
                        var4_2 = "\"\b\u0013N\u0018D\u0016AAHB\bR\u0010Rc\na\u0019D\u0016_";
                        var6_3 = "\"\b\u0013N\u0018D\u0016AAHB\bR\u0010Rc\na\u0019D\u0016_".length();
                        var3_4 = 2;
                        var2_5 = -1;
lbl21:

                        while (true) {
                            v4 = ++var2_5;
                            v1 = var4_2.substring(v4, v4 + var3_4);
                            v2 = 0;
                            break block19;
                            break;
                        }
                        break;
                    }
lbl26:

                    while (true) {
                        var7[var5_1++] = v3.intern();
                        if ((var2_5 += var3_4) < var6_3) {
                            var3_4 = var4_2.charAt(var2_5);
                            continue;
                        }
                        break block20;
                        break;
                    }
                }
                v5 = v1.toCharArray();
                v6 = v5;
                v7 = v5.length;
                var8_6 = 0;
                do {
                    v6 = v6;
                    v8 = var8_6;
                    v9 = v6[v8];
                    switch (var8_6 % 7) {
                        case 0: {
                            v10 = 15;
                            break;
                        }
                        case 1: {
                            v10 = 109;
                            break;
                        }
                        case 2: {
                            v10 = 54;
                            break;
                        }
                        case 3: {
                            v10 = 121;
                            break;
                        }
                        case 4: {
                            v10 = 51;
                            break;
                        }
                        case 5: {
                            v10 = 32;
                            break;
                        }
                        default: {
                            v10 = 101;
                        }
                    }
                    v6[v8] = (char)(v9 ^ v10);
                    ++var8_6;
lbl65:

                    v7 = v7;
                } while (v7 > var8_6);
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
            MacMediaClient.a = var7;
            MacMediaClient.MUSIC_ART = MacMediaClient.a[9];
            MacMediaClient.SPOTIFY = MacMediaClient.a[4];
            MacMediaClient.MUSIC = MacMediaClient.a[17];
            break block21;
lbl81:

            while (true) {
                continue;
                break;
            }
        }
        var0_7 = 978239802987117001L;
        while (true)
        MacMediaClient.b = 978239802987117089L ^ var0_7;
    }
}
