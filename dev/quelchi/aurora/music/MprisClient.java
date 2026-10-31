package dev.quelchi.aurora.music;

import dev.quelchi.aurora.music.Lyrics;
import dev.quelchi.aurora.music.NowPlaying;
import dev.quelchi.aurora.music.SmtcClient;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class MprisClient {
    private static final String FORMAT;
    private static final Pattern BUS;
    private static volatile String player;
    private static volatile boolean playerctl;
    private static final String[] a;
    private static final long[] b;
    private static final Long[] c;

    private MprisClient() {
    }

    static void start(int n) {
        Thread thread = new Thread(() -> MprisClient.lambda$start$0(n), a[5]);
        thread.setDaemon(true);
        thread.start();
    }

    
    private static void poll() {
        String string;
        Object object;
        String[] stringArray;
        block19: {
            stringArray = null;
            boolean bl = Lyrics.a;
            object = (playerctl ? MprisClient.viaPlayerctl() : MprisClient.viaGdbus()).iterator();
            while (object.hasNext()) {
                block21: {
                    String[] stringArray2;
                    String[] stringArray3;
                    block20: {
                        stringArray2 = stringArray3 = object.next();
                        if (!bl) {
                            string = stringArray2[1];
                            if (bl) break block19;
                            if (string.equals(a[17])) {
                                stringArray = stringArray3;
                                if (!bl) break;
                            }
                            stringArray2 = stringArray;
                        }
                        if (bl) break block20;
                        if (stringArray2 != null) break block21;
                        stringArray2 = stringArray3;
                    }
                    if (!bl && stringArray2[1].equals(a[37])) {
                        stringArray2 = stringArray = stringArray3;
                    }
                }
                if (!bl) continue;
            }
            String[] stringArray4 = stringArray;
            if (!bl) {
                if (stringArray4 == null) {
                    player = null;
                    SmtcClient.nothing();
                    return;
                }
                player = stringArray[0];
                stringArray4 = stringArray;
            }
            string = stringArray4[2];
        }
        object = SmtcClient.publish(string, (String)stringArray[3], (String)stringArray[4], MprisClient.num(stringArray[5]) / MprisClient.a(26047, 7650089655821636743L), MprisClient.num(stringArray[6]) / MprisClient.a(26047, 7650089655821636743L), stringArray[1].equals(a[32]));
        String string2 = stringArray[7];
        SmtcClient.art((NowPlaying)object, string2, () -> MprisClient.lambda$poll$1(string2));
    }

    private static List<String[]> viaPlayerctl() {
        String string;
        boolean bl;
        ArrayList<String[]> arrayList;
        block9: {
            String string2;
            block10: {
                arrayList = new ArrayList<String[]>();
                bl = Lyrics.a;
                String[] stringArray = new String[5];
                String[] stringArray2 = a;
                stringArray[0] = stringArray2[1];
                stringArray[1] = stringArray2[26];
                stringArray[2] = stringArray2[0];
                stringArray[3] = stringArray2[13];
                stringArray[4] = stringArray2[16];
                string2 = SmtcClient.exec(stringArray);
                string = string2;
                if (bl) break block9;
                if (string != null) break block10;
                return arrayList;
            }
            string = string2;
        }
        String[] stringArray = string.split("\n");
        int n = stringArray.length;
        int n2 = 0;
        while (n2 < n) {
            block11: {
                block12: {
                    String string3 = stringArray[n2];
                    String[] stringArray3 = string3.split("\t", -1);
                    if (bl) break block11;
                    if (stringArray3.length != 8) break block12;
                    arrayList.add(stringArray3);
                }
                ++n2;
            }
            if (!bl) continue;
        }
        return arrayList;
    }

    private static List<String[]> viaGdbus() {
        String string;
        String[] stringArray;
        boolean bl;
        ArrayList<String[]> arrayList;
        block11: {
            block10: {
                arrayList = new ArrayList<String[]>();
                bl = Lyrics.a;
                String[] stringArray2 = new String[9];
                stringArray = a;
                stringArray2[0] = stringArray[6];
                stringArray2[1] = stringArray[48];
                stringArray2[2] = stringArray[27];
                stringArray2[3] = stringArray[21];
                stringArray2[4] = stringArray[52];
                stringArray2[5] = stringArray[53];
                stringArray2[6] = stringArray[47];
                stringArray2[7] = stringArray[36];
                stringArray2[8] = stringArray[46];
                string = SmtcClient.exec(stringArray2);
                if (bl) break block10;
                if (string != null) break block11;
                SmtcClient.warnOnce(a[31]);
            }
            return arrayList;
        }
        Matcher matcher = BUS.matcher(string);
        while (matcher.find()) {
            block13: {
                String string2;
                String string3;
                String string4;
                block12: {
                    string4 = matcher.group(1);
                    String[] stringArray3 = new String[10];
                    stringArray = a;
                    stringArray3[0] = stringArray[6];
                    stringArray3[1] = stringArray[48];
                    stringArray3[2] = stringArray[27];
                    stringArray3[3] = stringArray[21];
                    stringArray3[4] = string4;
                    stringArray3[5] = stringArray[53];
                    stringArray3[6] = stringArray[15];
                    stringArray3[7] = stringArray[36];
                    stringArray3[8] = stringArray[41];
                    stringArray3[9] = stringArray[3];
                    string3 = SmtcClient.exec(stringArray3);
                    string2 = string3;
                    if (bl) break block12;
                    if (string2 == null) continue;
                    string2 = string3;
                }
                boolean bl2 = string2.isEmpty();
                if (bl) break block13;
                if (bl2) {
                    continue;
                }
                String[] stringArray4 = new String[8];
                stringArray4[0] = string4;
                stringArray = a;
                stringArray4[1] = MprisClient.text(string3, stringArray[11]);
                stringArray4[2] = MprisClient.text(string3, stringArray[50]);
                stringArray4[3] = MprisClient.text(string3, stringArray[29]);
                stringArray4[4] = MprisClient.text(string3, stringArray[18]);
                stringArray4[5] = MprisClient.number(string3, stringArray[40]);
                stringArray4[6] = MprisClient.number(string3, stringArray[30]);
                stringArray4[7] = MprisClient.text(string3, stringArray[43]);
                bl2 = arrayList.add(stringArray4);
            }
            if (!bl) continue;
        }
        return arrayList;
    }

    private static String text(String string, String string2) {
        String string3;
        String[] stringArray;
        block12: {
            Matcher matcher;
            block10: {
                Matcher matcher2;
                block11: {
                    boolean bl;
                    block8: {
                        block9: {
                            stringArray = a;
                            matcher2 = Pattern.compile("'" + Pattern.quote(string2) + stringArray[9]).matcher(string);
                            bl = Lyrics.a;
                            matcher = matcher2;
                            if (bl) break block8;
                            if (matcher.find()) break block9;
                            return "";
                        }
                        matcher = matcher2;
                    }
                    if (bl) break block10;
                    if (matcher.group(1) == null) break block11;
                    string3 = matcher2.group(1);
                    break block12;
                }
                matcher = matcher2;
            }
            string3 = matcher.group(2);
        }
        String string4 = string3;
        stringArray = a;
        return string4.replaceAll(stringArray[25], stringArray[39]);
    }

    private static String number(String string, String string2) {
        String string3;
        block6: {
            block5: {
                Matcher matcher;
                block4: {
                    Matcher matcher2 = Pattern.compile("'" + Pattern.quote(string2) + a[2]).matcher(string);
                    boolean bl = Lyrics.a;
                    matcher = matcher2;
                    if (bl) break block4;
                    if (!matcher.find()) break block5;
                    matcher = matcher2;
                }
                string3 = matcher.group(1);
                break block6;
            }
            string3 = "0";
        }
        return string3;
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
        String string2 = player;
        if (string2 == null) {
            return;
        }
        Thread thread = new Thread(() -> MprisClient.lambda$control$2(string, string2), a[33]);
        thread.setDaemon(true);
        thread.start();
    }

    
    private static void lambda$control$2(String string, String string2) {
        block32: {
            int n;
            String string3;
            String[] stringArray;
            block33: {
                int n2;
                String string4;
                boolean bl;
                block30: {
                    int n3;
                    block31: {
                        bl = Lyrics.a;
                        if (!playerctl) break block30;
                        string4 = string;
                        n2 = -1;
                        n3 = string4.hashCode();
                        if (bl) break block31;
                        switch (n3) {
                            case 3377907: {
                                n3 = string4.equals(a[23]) ? 1 : 0;
                                if (!bl) {
                                    if (n3 == 0) break;
                                    n2 = 0;
                                    if (!bl) break;
                                }
                                break block31;
                            }
                            case -1273775369: {
                                n3 = string4.equals(a[51]) ? 1 : 0;
                                if (bl) break block31;
                                if (n3 == 0) break;
                                n2 = 1;
                            }
                        }
                        n3 = n2;
                    }
                    string3 = switch (n3) {
                        case 0 -> a[8];
                        case 1 -> {
                            stringArray = a;
                            yield stringArray[24];
                        }
                        default -> {
                            stringArray = a;
                            yield stringArray[38];
                        }
                    };
                    String[] stringArray2 = new String[4];
                    stringArray = a;
                    stringArray2[0] = stringArray[19];
                    stringArray2[1] = stringArray[12];
                    stringArray2[2] = string2;
                    stringArray2[3] = string3;
                    SmtcClient.exec(stringArray2);
                    if (!bl) break block32;
                }
                string4 = string;
                n2 = -1;
                n = string4.hashCode();
                if (bl) break block33;
                switch (n) {
                    case 3377907: {
                        n = string4.equals(a[8]) ? 1 : 0;
                        if (!bl) {
                            if (n == 0) break;
                            n2 = 0;
                            if (!bl) break;
                        }
                        break block33;
                    }
                    case -1273775369: {
                        n = string4.equals(a[24]) ? 1 : 0;
                        if (bl) break block33;
                        if (n == 0) break;
                        n2 = 1;
                    }
                }
                n = n2;
            }
            string3 = switch (n) {
                case 0 -> a[54];
                case 1 -> {
                    stringArray = a;
                    yield stringArray[4];
                }
                default -> {
                    stringArray = a;
                    yield stringArray[14];
                }
            };
            String[] stringArray3 = new String[9];
            stringArray = a;
            stringArray3[0] = stringArray[28];
            stringArray3[1] = stringArray[10];
            stringArray3[2] = stringArray[35];
            stringArray3[3] = stringArray[7];
            stringArray3[4] = string2;
            stringArray3[5] = stringArray[42];
            stringArray3[6] = stringArray[22];
            stringArray3[7] = stringArray[34];
            stringArray3[8] = stringArray[20] + string3;
            SmtcClient.exec(stringArray3);
        }
    }

    private static byte[] lambda$poll$1(String string) throws Exception {
        return SmtcClient.load(string);
    }

    
    private static void lambda$start$0(int n) {
        boolean bl = Lyrics.a;
        int n2 = 2;
        if (!bl) {
            String[] stringArray = new String[n2];
            stringArray[0] = a[19];
            stringArray[1] = a[45];
            playerctl = SmtcClient.exec(stringArray) != null ? 1 : 0;
            n2 = playerctl ? 1 : 0;
        }
        while (SmtcClient.alive(n)) {
            MprisClient.poll();
            try {
                Thread.sleep(MprisClient.a(19346, 5243677964035111595L));
            }
            catch (InterruptedException interruptedException) {
                return;
            }
        }
        return;
    }

    
    static {
        block23: {
            block22: {
                block21: {
                    block20: {
                        var13 = new String[55];
                        var11_1 = 0;
                        var10_2 = "V\u0001CuQ\u0005=Z\tK\bVmP\u0016*O\b\u001a\u001c^\u0017(\u001d[sN[^zA8-\u0010D\u001e+\u001dIvg\u0000\u001c=\u000b\u001dT\u0016P:X\u0014;R\u0017\u0019YP\u0000 Z4[uL\u0001;\tJgxT\u001d,I\bk\u0016Rb\\\u000b<H\fz\u0011E{G\u0005dv4e]f\u0005\\\u0000UaF\u0006\u0016ISqF\u0010\u0004U\u0001O`2\u001c^\u0017(i?v\u0013[\r3\u001dLv\u0001?i3i8\u0014G8k:\u001cN`\u001c\u0018\u0015<\u001d[s`:\u0015Hi95g8\u0019=\u001fMk\u0012\u0004X\u0005[x\u000ek\bVmW\u0005*P7CuA\u0011:\u0002\u0016\u0014\b\u0016IQ{G\t(O\tk\bVme\u0005<H\u0001\u0017\u0014\u000bEs\u001a\t9I\rD;x\u0001-R\u0005gxT\u001d,IVg@\u001fGxT\u001d,I*VyP\u001942\u001fLgA\u0005=N\u0017Ji<\u001f2O\rCxP\u001942\u001fLuG\u0010 H\u0010Ji<\u001f2Z\bUaX\u001942\u001fLyE\u0016 H^[q[\u0003=S\u0019J\u001dN\u001f9T\u0017^`\\\u000b'F\u0019>oN\t9I\rD.T\u0016=n\u0016[iH\u0007k\bVm\\\n.\u000bC\u0001DuX^(W\u0006By\tK\bVmP\u0016*O\b\u001eT\u0016P:X\u0014;R\u0017\u0019YP\u0000 Z4[uL\u0001;\tJgxT\u001d,IJ\u0006\u0016ISqF\u0010\u0017\u0014\u000bEs\u001a\t9I\rD;x\u0001-R\u0005gxT\u001d,IV\u0004U\u0001O`\bK\u0016Rb\\\u000b<H\u0005g8\u001f:\u001c\u0002\u0016\u0005\t\u0016IDqF\u0017 T\n\u0005\\\u0000UaF\fC\u0001DuX^(I\u0010^gA\bk\u000bD}A\r&U'U\u000b\u0017dY\u00050^\u0016T`YD&IDPpW\u0011:\u0017DTu[C=\u001b\u0016RuQD\u0004k6~G\u0007k\bVm\\\n.\u0013z\u0011E{G\u0005dv\u0001S}T'&U\u0010E{Y\b\u0016IZqA\f&_\t\u0016IDqF\u0017 T\n\b\u0016IZqA\f&_\u0006k\u0005BgP\u0000\nK\bVm\u0018\u0014(N\u0017R\u0002\u001fU\fV\u0014E}F^%^\nP`]&T\u0016P:S\u0016,^\u0000Rg^\u0010&KJsV@\u0017gk\u0016XdP\u0016=R\u0001D:r\u0001=z\b[\r\u0016IXv_\u0001*OIGuA\f\fV\u0014E}F^(I\u0010bfY#\u001cLXfR8gV\u0014E}F8gv\u0001S}T4%Z\u001dRf\u00078g`:\u0010I\u001eMn\t\u0016IAqG\u0017 T\n\u001eT\u0016P:S\u0016,^\u0000Rg^\u0010&KJsV@\u0017gw\rD`{\u0005$^\u0017\u0015\u0014\u000bEs\u001a\u0002;^\u0001SqF\u000f=T\u0014\u0018Pw\u0011:\u0004X\u0005[xg@\u001fGxT\u001d,I*VyP\u001942\u001fLgA\u0005=N\u0017Ji<\u001f2O\rCxP\u001942\u001fLuG\u0010 H\u0010Ji<\u001f2Z\bUaX\u001942\u001fLyE\u0016 H^[q[\u0003=S\u0019J\u001dN\u001f9T\u0017^`\\\u000b'F\u0019>oN\t9I\rD.T\u0016=n\u0016[iH\u000bC\u0001DuX^=R\u0010[q\bK\u0016Rb\\\u000b<H\u0014T\u0016P:S\u0016,^\u0000Rg^\u0010&KJsV@\u0017";
                        var12_3 = "V\u0001CuQ\u0005=Z\tK\bVmP\u0016*O\b\u001a\u001c^\u0017(\u001d[sN[^zA8-\u0010D\u001e+\u001dIvg\u0000\u001c=\u000b\u001dT\u0016P:X\u0014;R\u0017\u0019YP\u0000 Z4[uL\u0001;\tJgxT\u001d,I\bk\u0016Rb\\\u000b<H\fz\u0011E{G\u0005dv4e]f\u0005\\\u0000UaF\u0006\u0016ISqF\u0010\u0004U\u0001O`2\u001c^\u0017(i?v\u0013[\r3\u001dLv\u0001?i3i8\u0014G8k:\u001cN`\u001c\u0018\u0015<\u001d[s`:\u0015Hi95g8\u0019=\u001fMk\u0012\u0004X\u0005[x\u000ek\bVmW\u0005*P7CuA\u0011:\u0002\u0016\u0014\b\u0016IQ{G\t(O\tk\bVme\u0005<H\u0001\u0017\u0014\u000bEs\u001a\t9I\rD;x\u0001-R\u0005gxT\u001d,IVg@\u001fGxT\u001d,I*VyP\u001942\u001fLgA\u0005=N\u0017Ji<\u001f2O\rCxP\u001942\u001fLuG\u0010 H\u0010Ji<\u001f2Z\bUaX\u001942\u001fLyE\u0016 H^[q[\u0003=S\u0019J\u001dN\u001f9T\u0017^`\\\u000b'F\u0019>oN\t9I\rD.T\u0016=n\u0016[iH\u0007k\bVm\\\n.\u000bC\u0001DuX^(W\u0006By\tK\bVmP\u0016*O\b\u001eT\u0016P:X\u0014;R\u0017\u0019YP\u0000 Z4[uL\u0001;\tJgxT\u001d,IJ\u0006\u0016ISqF\u0010\u0017\u0014\u000bEs\u001a\t9I\rD;x\u0001-R\u0005gxT\u001d,IV\u0004U\u0001O`\bK\u0016Rb\\\u000b<H\u0005g8\u001f:\u001c\u0002\u0016\u0005\t\u0016IDqF\u0017 T\n\u0005\\\u0000UaF\fC\u0001DuX^(I\u0010^gA\bk\u000bD}A\r&U'U\u000b\u0017dY\u00050^\u0016T`YD&IDPpW\u0011:\u0017DTu[C=\u001b\u0016RuQD\u0004k6~G\u0007k\bVm\\\n.\u0013z\u0011E{G\u0005dv\u0001S}T'&U\u0010E{Y\b\u0016IZqA\f&_\t\u0016IDqF\u0017 T\n\b\u0016IZqA\f&_\u0006k\u0005BgP\u0000\nK\bVm\u0018\u0014(N\u0017R\u0002\u001fU\fV\u0014E}F^%^\nP`]&T\u0016P:S\u0016,^\u0000Rg^\u0010&KJsV@\u0017gk\u0016XdP\u0016=R\u0001D:r\u0001=z\b[\r\u0016IXv_\u0001*OIGuA\f\fV\u0014E}F^(I\u0010bfY#\u001cLXfR8gV\u0014E}F8gv\u0001S}T4%Z\u001dRf\u00078g`:\u0010I\u001eMn\t\u0016IAqG\u0017 T\n\u001eT\u0016P:S\u0016,^\u0000Rg^\u0010&KJsV@\u0017gw\rD`{\u0005$^\u0017\u0015\u0014\u000bEs\u001a\u0002;^\u0001SqF\u000f=T\u0014\u0018Pw\u0011:\u0004X\u0005[xg@\u001fGxT\u001d,I*VyP\u001942\u001fLgA\u0005=N\u0017Ji<\u001f2O\rCxP\u001942\u001fLuG\u0010 H\u0010Ji<\u001f2Z\bUaX\u001942\u001fLyE\u0016 H^[q[\u0003=S\u0019J\u001dN\u001f9T\u0017^`\\\u000b'F\u0019>oN\t9I\rD.T\u0016=n\u0016[iH\u000bC\u0001DuX^=R\u0010[q\bK\u0016Rb\\\u000b<H\u0014T\u0016P:S\u0016,^\u0000Rg^\u0010&KJsV@\u0017".length();
                        var9_4 = 8;
                        var8_5 = -1;
lbl7:

                        while (true) {
                            v0 = ++var8_5;
                            v1 = var10_2.substring(v0, v0 + var9_4);
                            v2 = -1;
                            break block20;
                            break;
                        }
lbl12:

                        while (true) {
                            var13[var11_1++] = v3.intern();
                            if ((var8_5 += var9_4) < var12_3) {
                                var9_4 = var10_2.charAt(var8_5);
                                continue;
                            }
                            var10_2 = "\u0016IXv_\u0001*OIGuA\f\u0004u\u0001O`";
                            var12_3 = "\u0016IXv_\u0001*OIGuA\f\u0004u\u0001O`".length();
                            var9_4 = 13;
                            var8_5 = -1;
lbl21:

                            while (true) {
                                v4 = ++var8_5;
                                v1 = var10_2.substring(v4, v4 + var9_4);
                                v2 = 0;
                                break block20;
                                break;
                            }
                            break;
                        }
lbl26:

                        while (true) {
                            var13[var11_1++] = v3.intern();
                            if ((var8_5 += var9_4) < var12_3) {
                                var9_4 = var10_2.charAt(var8_5);
                                continue;
                            }
                            break block21;
                            break;
                        }
                    }
                    v5 = v1.toCharArray();
                    v6 = v5;
                    v7 = v5.length;
                    var14_6 = 0;
                    do {
                        v6 = v6;
                        v8 = var14_6;
                        v9 = v6[v8];
                        switch (var14_6 % 7) {
                            case 0: {
                                v10 = 59;
                                break;
                            }
                            case 1: {
                                v10 = 100;
                                break;
                            }
                            case 2: {
                                v10 = 55;
                                break;
                            }
                            case 3: {
                                v10 = 20;
                                break;
                            }
                            case 4: {
                                v10 = 53;
                                break;
                            }
                            case 5: {
                                v10 = 100;
                                break;
                            }
                            default: {
                                v10 = 73;
                            }
                        }
                        v6[v8] = (char)(v9 ^ v10);
                        ++var14_6;
lbl65:

                        v7 = v7;
                    } while (v7 > var14_6);
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
                MprisClient.a = var13;
                MprisClient.FORMAT = MprisClient.a[49];
                var0_7 = 3839729498515062077L;
                var6_8 = new long[2];
                var3_9 = 0;
                var4_10 = "_c\u00e1\u00a0\u000b\u00b7\u00aaR}\u008c<\u00f1\u00e2O\u0004~";
                var5_11 = "_c\u00e1\u00a0\u000b\u00b7\u00aaR}\u008c<\u00f1\u00e2O\u0004~".length();
                var2_12 = 0;
                while (true) {
                    break block22;
                    break;
                }
lbl86:

                while (true) {
                    var6_8[v11] = (((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L) ^ var0_7;
                    if (var2_12 < var5_11) continue;
                    break block23;
                    break;
                }
            }
            var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
            v11 = var3_9++;
            while (true)
        }
        MprisClient.b = var6_8;
        MprisClient.c = new Long[2];
        MprisClient.BUS = Pattern.compile(MprisClient.a[44]);
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x7138) & Short.MAX_VALUE;
        if (c[n2] == null) {
            MprisClient.c[n2] = b[n2] ^ l;
        }
        return c[n2];
    }
}
