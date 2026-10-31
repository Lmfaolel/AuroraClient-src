package dev.quelchi.aurora.chat;

import dev.quelchi.aurora.chat.Emoji$Entry;
import dev.quelchi.aurora.module.Module;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public final class Emoji {
    private static final String[] DATA;
    public static final List<Emoji$Entry> ALL;
    private static final Map<String, String> BY_NAME;
    public static boolean a;

    private Emoji() {
    }

    private static List<Emoji$Entry> build() {
        ArrayList<Emoji$Entry> arrayList = new ArrayList<Emoji$Entry>(DATA.length / 2);
        int n = 0;
        while (n + 1 < DATA.length) {
            arrayList.add(new Emoji$Entry(DATA[n], new String(Character.toChars(Integer.parseInt(DATA[n + 1], 16)))));
            n += 2;
        }
        return List.copyOf(arrayList);
    }

    private static Map<String, String> index() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        for (Emoji$Entry emoji$Entry : ALL) {
            linkedHashMap.put(emoji$Entry.name(), emoji$Entry.chars());
        }
        return Map.copyOf(linkedHashMap);
    }

    public static String charsOf(String string) {
        String string2;
        block4: {
            block5: {
                boolean bl = a;
                string2 = string;
                if (bl) break block4;
                if (string2 != null) break block5;
                return "";
            }
            string2 = BY_NAME.getOrDefault(string.toLowerCase(Locale.ROOT), "");
        }
        return string2;
    }

    public static List<String> matching(String string) {
        ArrayList<String> arrayList;
        block22: {
            String string2;
            boolean bl;
            block21: {
                String string3;
                block19: {
                    block20: {
                        bl = a;
                        string3 = string;
                        if (bl) break block19;
                        if (string3 != null) break block20;
                        string2 = "";
                        break block21;
                    }
                    string3 = string;
                }
                string2 = string3.toLowerCase(Locale.ROOT);
            }
            String string4 = string2;
            arrayList = new ArrayList<String>();
            ArrayList<String> arrayList2 = new ArrayList<String>();
            for (Emoji$Entry emoji$Entry : ALL) {
                block25: {
                    Emoji$Entry emoji$Entry2;
                    block23: {
                        block24: {
                            boolean bl2;
                            block27: {
                                block26: {
                                    if (bl) break block22;
                                    emoji$Entry2 = emoji$Entry;
                                    if (bl) break block23;
                                    if (!emoji$Entry2.name().startsWith(string4)) break block24;
                                    arrayList.add(emoji$Entry.name());
                                    if (!bl) break block25;
                                    if (!Module.a) break block26;
                                    bl2 = false;
                                    break block27;
                                }
                                bl2 = true;
                            }
                            Module.a = bl2;
                        }
                        emoji$Entry2 = emoji$Entry;
                    }
                    boolean bl3 = emoji$Entry2.name().contains(string4);
                    if (bl || !bl3) break block25;
                    bl3 = arrayList2.add(emoji$Entry.name());
                }
                if (!bl) continue;
            }
            arrayList.addAll(arrayList2);
        }
        return arrayList;
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[276];
                var4_1 = 0;
                var3_2 = ".\u001d[\u00135\u0005.\u001d\\\u0015H\u0005r.\u001cM\u0012\u0003r2\f\u0005.\u001d[\u001dG\u0005.\u001d[eB\u0006k)\u0000T\u0019u\u0004-lV\u0012\u0004x2\tP\u0005.\u001d[eD\u0007j5\u0006G\u001e~3\u0005~5\bV\b\u0005.\u001d[\u0011@\u0005.\u001dV\u0016@\u0004}4\u0000I\u0005o4\u0006J\u0005\u0007l+\nE\u001ai/\u0004h:\u0019A\u0005.\u001d[e0\u0004s4\fO\u0005.\u001dV\u0015E\u0005l,\u0000V\u0015\u0004-l_\u0011\u0007{)\u0000T\u001di)\u0005.\u001dZ\u00140\u0005.\u001d[eF\u0004}>\nV\u0004-\u0019_\u0012\nw>\u000e@\u0001d2q>\u001c\u0005.\u001d[\u0017G\tw:\u0001@\u0002d<t>\u0005.\u001dZ\u0016I\u0005.\u001dZ\u0016F\u0005.\u001d\\gG\u0005.\u001d[\u00103\u0005.\u001dY\u0014D\u0006{)\u000eC\u001eb\u0005.\u001dZ\u0015E\u0005l5\u000eO\u0014\u0005l3\u0000G\u001a\u0004-mV\u0010\u0005.\u001d[g4\u0006j5\u0003K\u0012g\u0004}>\u000eV\u0005.\u001dY\u0014F\u0005.\u001d[\u0010H\u0005.\u001dY\u0016@\bl+\u000eV\u001a`8l\u0004-j\\\u001d\u000b})\u0000O\u0014b5z:\u001dP\t}.\u001bP\u0014~;s\"\u0005.\u001d[\u0017@\u0005.\u001d[\u00173\u0003}4\u0018\u0005.\u001dV\u00155\u0005|3\nG\u001a\u0007m:\u0006J\u0013c*\bl8\u0006W\u0002c/l\u0005|7\u0000Q\u0015\u0003l:\u000b\u0005.\u001dZ\u00143\u0006k:\u001dC\u0014x\u0004x)\u0006J\u0006r4\u0001O\u0014u\u0004r4\u0000J\u0005r>\u000bE\u001d\u0005.\u001d\\gD\u0004-lZ\u0017\u0004r.\u001bA\u0005.\u001dY\u00145\u0005.\u001d\\fC\u0004y)\u0000C\u0005o:\u0001@\u0010\u0005.\u001d[\u00164\u0005.\u001dZ\u0013F\u0003}>\n\u0005.\u001d[fD\u0005.\u001dVbC\u0004-l]\u001c\u0005.\u001d[\u0017D\u0004l5\u0000S\u0007h:\u001dJ\u0018b:\u0002p0\u0006l>\u000eV\u0012d\u0005|7\u0000S\u001f\u0004-l_\u0016\u0005.\u001d\\e7\u0005.\u001dZ\u0015C\u0006l3\u0006A\u001dh\u0005.\u001d\\bH\u0005.\u001dY\u001cA\u0005r2\u0001Q\u0002\u0004-mXa\u0005.\u001dZ\u0015@\u0003u4\u0016\u0005m4\rK\u0005\u0006y2\u0001M\u0002d\u0005.\u001d[\u0017B\u0004h2\u0001O\u0005.\u001dVfE\u0005|)\u0000S\u001f\bk3\u001aI\u0013\u007f(o\u0005.\u001d\\\u00130\u0005.\u001d[\u00172\u0005m2\bL\u0005\u0003{4\b\u0005.\u001d[\u0014H\u0005.\u001d[e@\u0007q>\u001aP\u0003m1\u0005.\u001d[\u0017I\u0005.\u001d[`G\u0005.\u001dV\u001cE\u0005.\u001d[\u00155\u000el+\u000eV\u001a`4q<\u0007A\u0010~)\u0005.\u001dZ\u0011A\u0005o:\u001dP\b\bo7\nE\u0015e3x\u0005.\u001dV\u0015G\u0006o>\u0001G\u0018`\u0005.\u001d[\u0017F\u0004-lZ\u0013\u0005.\u001d\\fG\u0004m:\bA\u0005.\u001dY\u0014I\u0004s>\tP\u0004r:\u0006H\u0007m>\f]\u0012`8\u0005k3\u0006J\u001a\u0005.\u001dY\u0014H\u0006k4\u0001C\u0004i\u0006{4\u0003H\u0010~\u0005~7\u0006A\u001f\u0005.\u001dZ\u00164\u0006w:\u0002I\u0014~\u0003y4\u0017\u0007}:\u001bP\u0014~$\u0006m4\fO\u0014x\u0005.\u001d[\u00100\u0004s4\u0019A\u0005r4\u0001A\b\u0004{4\u0018J\u0005.\u001dYa@\u0005.\u001dZ\u0015B\u0005l7\nA\u0001\u0004-lV\u0011\u0003|)\u0016\u0005.\u001d[\u00133\u0004{2\fA\u0004-l.\u0015\u0005~+\u001fH\u0014\u0004y2\u001cP\u0006r.\u001cG\u001di\u0004-m.\u0014\u0004-h^f\u0004y2\u001cL\u0005.\u001d\\\u00104\u0004v5\tK\u0004}4\u0002F\u0005.\u001dY\u0017E\u0006h)\nJ\u0012d\u0005.\u001d[\u001cA\u0005o2\u0015^\u0010\u0005.\u001d[\u00107\u0005.\u001dZ\u0014F\u0005.\u001d[\u00105\u0005.\u001dY\u00144\u0005.\u001dY\u00165\u0005|)\u0000W\u0002\u0005.\u001dY\u0014C\u0005.\u001d\\\u0011D\u0004x>\u000eV\u0005.\u001d\\fI\u0004-lY\u0010\u0005.\u001dV\u0015B\u0004o)\u000e]\u0003e:\u001f\u0004}.\u0003F\u0006r:\bJ\u0014x\u0004l/\u000eV\u0005.\u001dY\u0010@\tw4\u001aV\u0016`<l(\u0005.\u001d\\\u0014I\u0005.\u001d[gI\u0005.\u001d\\fD\u0005|7\u0000G\u001a\to:\u001fA\u0003o1v+\u0005l0\u001aH\u001d\u0005l,\nE\u0005\u0007|3\u0006G\u001ai3\u0005x3\u0000W\u0005\u000b|)\u0016W\u0005m1}:\u0003H\u0004o7\u001aW\u0007o>\u0001C\u0004e3\u0004-mV\u001d\u0005w>\u000eV\u0005\nk3\u001aI\u0013\u007f9p,\u0001\u0004}4\u0001A\u0005.\u001dY\u00164\u0004z\"\nW\u0005.\u001d\\g@\u0004-l_\u001d\u0005.\u001d[b@\u0005.\u001dY\u0010C\u0004y2\u001dA\u0005.\u001d[fA\u0003o2\b\u0005.\u001dY\u00107\u0005.\u001dZ\u0016D\u0005.\u001d\\eE\u0005.\u001dV\u0013B\u0005.\u001d[\u0015E\u0003l4\r\u0004-mXf\u0004-l[\u0010\u0004y7\u000eC\u0006x.\u0006P\u0010~\u0005.\u001d[\u0016F\u0005.\u001d[\u001c4\u0004-\u0019_\u0013\u0003|:\u001b\u0004-\u0019Z\u0014\u0005.\u001dY\u0016C\u0005.\u001dY\u0016A\u0005{>\u0019M\u001d\u0005|3\u000eV\u0005\u0003|4\u0018\u0005.\u001d\\\u001cC\u0005.\u001dV\u001c0\bv5\tM\u001fe)f\u0007z#\fH\u0010e0\u0004}4\u0000O\u0005.\u001dVaA\u0006l+\u0006@\u0014~\u0005o3\u0000J\u0014\u0003l.\u0001\u0005.\u001d\\\u001c@\u0005.\u001d[\u00104\u0004-m_\u0014\u0005.\u001dY\u0015A\u0004-m.\u0015\u0005})\u000eM\u001f\u0004-l[g\u0005.\u001d[\u0016G\u0005l6\u0006H\u0014\u0005.\u001d[\u00157\u0005q4\u001bA\u0002\u0004-m^\u0011\u0004-l_b\bn.\nW\u0005e2q\u0005.\u001dYeH\u0005.\u001dZ\u00145\u0005.\u001dV\u001c3\u0003x>\u0002\u0004-m_\u0015\u0005h3\u000eH\u0014\u0005~5\bA\u001d\u0005.\u001d[\u00102\u0005.\u001dY\u0014A\u0005.\u001dV\u00130\u0004q>\u001d@\u0004}2\u001d@\u0004|:\u0004A\u0004|4\u0000H\u0005.\u001dY\u00153\u0005.\u001d[\u00145\u0005.\u001d[\u0010A\u0003t>\u0016\u0004|7\u000eT\u0004-\u0019_\u0011\u0004}>\u0003H\b|4\u0002T\u0004x8m\u0005.\u001d\\eF\u0002j+\u0005.\u001d[\u001dE";
                var5_3 = ".\u001d[\u00135\u0005.\u001d\\\u0015H\u0005r.\u001cM\u0012\u0003r2\f\u0005.\u001d[\u001dG\u0005.\u001d[eB\u0006k)\u0000T\u0019u\u0004-lV\u0012\u0004x2\tP\u0005.\u001d[eD\u0007j5\u0006G\u001e~3\u0005~5\bV\b\u0005.\u001d[\u0011@\u0005.\u001dV\u0016@\u0004}4\u0000I\u0005o4\u0006J\u0005\u0007l+\nE\u001ai/\u0004h:\u0019A\u0005.\u001d[e0\u0004s4\fO\u0005.\u001dV\u0015E\u0005l,\u0000V\u0015\u0004-l_\u0011\u0007{)\u0000T\u001di)\u0005.\u001dZ\u00140\u0005.\u001d[eF\u0004}>\nV\u0004-\u0019_\u0012\nw>\u000e@\u0001d2q>\u001c\u0005.\u001d[\u0017G\tw:\u0001@\u0002d<t>\u0005.\u001dZ\u0016I\u0005.\u001dZ\u0016F\u0005.\u001d\\gG\u0005.\u001d[\u00103\u0005.\u001dY\u0014D\u0006{)\u000eC\u001eb\u0005.\u001dZ\u0015E\u0005l5\u000eO\u0014\u0005l3\u0000G\u001a\u0004-mV\u0010\u0005.\u001d[g4\u0006j5\u0003K\u0012g\u0004}>\u000eV\u0005.\u001dY\u0014F\u0005.\u001d[\u0010H\u0005.\u001dY\u0016@\bl+\u000eV\u001a`8l\u0004-j\\\u001d\u000b})\u0000O\u0014b5z:\u001dP\t}.\u001bP\u0014~;s\"\u0005.\u001d[\u0017@\u0005.\u001d[\u00173\u0003}4\u0018\u0005.\u001dV\u00155\u0005|3\nG\u001a\u0007m:\u0006J\u0013c*\bl8\u0006W\u0002c/l\u0005|7\u0000Q\u0015\u0003l:\u000b\u0005.\u001dZ\u00143\u0006k:\u001dC\u0014x\u0004x)\u0006J\u0006r4\u0001O\u0014u\u0004r4\u0000J\u0005r>\u000bE\u001d\u0005.\u001d\\gD\u0004-lZ\u0017\u0004r.\u001bA\u0005.\u001dY\u00145\u0005.\u001d\\fC\u0004y)\u0000C\u0005o:\u0001@\u0010\u0005.\u001d[\u00164\u0005.\u001dZ\u0013F\u0003}>\n\u0005.\u001d[fD\u0005.\u001dVbC\u0004-l]\u001c\u0005.\u001d[\u0017D\u0004l5\u0000S\u0007h:\u001dJ\u0018b:\u0002p0\u0006l>\u000eV\u0012d\u0005|7\u0000S\u001f\u0004-l_\u0016\u0005.\u001d\\e7\u0005.\u001dZ\u0015C\u0006l3\u0006A\u001dh\u0005.\u001d\\bH\u0005.\u001dY\u001cA\u0005r2\u0001Q\u0002\u0004-mXa\u0005.\u001dZ\u0015@\u0003u4\u0016\u0005m4\rK\u0005\u0006y2\u0001M\u0002d\u0005.\u001d[\u0017B\u0004h2\u0001O\u0005.\u001dVfE\u0005|)\u0000S\u001f\bk3\u001aI\u0013\u007f(o\u0005.\u001d\\\u00130\u0005.\u001d[\u00172\u0005m2\bL\u0005\u0003{4\b\u0005.\u001d[\u0014H\u0005.\u001d[e@\u0007q>\u001aP\u0003m1\u0005.\u001d[\u0017I\u0005.\u001d[`G\u0005.\u001dV\u001cE\u0005.\u001d[\u00155\u000el+\u000eV\u001a`4q<\u0007A\u0010~)\u0005.\u001dZ\u0011A\u0005o:\u001dP\b\bo7\nE\u0015e3x\u0005.\u001dV\u0015G\u0006o>\u0001G\u0018`\u0005.\u001d[\u0017F\u0004-lZ\u0013\u0005.\u001d\\fG\u0004m:\bA\u0005.\u001dY\u0014I\u0004s>\tP\u0004r:\u0006H\u0007m>\f]\u0012`8\u0005k3\u0006J\u001a\u0005.\u001dY\u0014H\u0006k4\u0001C\u0004i\u0006{4\u0003H\u0010~\u0005~7\u0006A\u001f\u0005.\u001dZ\u00164\u0006w:\u0002I\u0014~\u0003y4\u0017\u0007}:\u001bP\u0014~$\u0006m4\fO\u0014x\u0005.\u001d[\u00100\u0004s4\u0019A\u0005r4\u0001A\b\u0004{4\u0018J\u0005.\u001dYa@\u0005.\u001dZ\u0015B\u0005l7\nA\u0001\u0004-lV\u0011\u0003|)\u0016\u0005.\u001d[\u00133\u0004{2\fA\u0004-l.\u0015\u0005~+\u001fH\u0014\u0004y2\u001cP\u0006r.\u001cG\u001di\u0004-m.\u0014\u0004-h^f\u0004y2\u001cL\u0005.\u001d\\\u00104\u0004v5\tK\u0004}4\u0002F\u0005.\u001dY\u0017E\u0006h)\nJ\u0012d\u0005.\u001d[\u001cA\u0005o2\u0015^\u0010\u0005.\u001d[\u00107\u0005.\u001dZ\u0014F\u0005.\u001d[\u00105\u0005.\u001dY\u00144\u0005.\u001dY\u00165\u0005|)\u0000W\u0002\u0005.\u001dY\u0014C\u0005.\u001d\\\u0011D\u0004x>\u000eV\u0005.\u001d\\fI\u0004-lY\u0010\u0005.\u001dV\u0015B\u0004o)\u000e]\u0003e:\u001f\u0004}.\u0003F\u0006r:\bJ\u0014x\u0004l/\u000eV\u0005.\u001dY\u0010@\tw4\u001aV\u0016`<l(\u0005.\u001d\\\u0014I\u0005.\u001d[gI\u0005.\u001d\\fD\u0005|7\u0000G\u001a\to:\u001fA\u0003o1v+\u0005l0\u001aH\u001d\u0005l,\nE\u0005\u0007|3\u0006G\u001ai3\u0005x3\u0000W\u0005\u000b|)\u0016W\u0005m1}:\u0003H\u0004o7\u001aW\u0007o>\u0001C\u0004e3\u0004-mV\u001d\u0005w>\u000eV\u0005\nk3\u001aI\u0013\u007f9p,\u0001\u0004}4\u0001A\u0005.\u001dY\u00164\u0004z\"\nW\u0005.\u001d\\g@\u0004-l_\u001d\u0005.\u001d[b@\u0005.\u001dY\u0010C\u0004y2\u001dA\u0005.\u001d[fA\u0003o2\b\u0005.\u001dY\u00107\u0005.\u001dZ\u0016D\u0005.\u001d\\eE\u0005.\u001dV\u0013B\u0005.\u001d[\u0015E\u0003l4\r\u0004-mXf\u0004-l[\u0010\u0004y7\u000eC\u0006x.\u0006P\u0010~\u0005.\u001d[\u0016F\u0005.\u001d[\u001c4\u0004-\u0019_\u0013\u0003|:\u001b\u0004-\u0019Z\u0014\u0005.\u001dY\u0016C\u0005.\u001dY\u0016A\u0005{>\u0019M\u001d\u0005|3\u000eV\u0005\u0003|4\u0018\u0005.\u001d\\\u001cC\u0005.\u001dV\u001c0\bv5\tM\u001fe)f\u0007z#\fH\u0010e0\u0004}4\u0000O\u0005.\u001dVaA\u0006l+\u0006@\u0014~\u0005o3\u0000J\u0014\u0003l.\u0001\u0005.\u001d\\\u001c@\u0005.\u001d[\u00104\u0004-m_\u0014\u0005.\u001dY\u0015A\u0004-m.\u0015\u0005})\u000eM\u001f\u0004-l[g\u0005.\u001d[\u0016G\u0005l6\u0006H\u0014\u0005.\u001d[\u00157\u0005q4\u001bA\u0002\u0004-m^\u0011\u0004-l_b\bn.\nW\u0005e2q\u0005.\u001dYeH\u0005.\u001dZ\u00145\u0005.\u001dV\u001c3\u0003x>\u0002\u0004-m_\u0015\u0005h3\u000eH\u0014\u0005~5\bA\u001d\u0005.\u001d[\u00102\u0005.\u001dY\u0014A\u0005.\u001dV\u00130\u0004q>\u001d@\u0004}2\u001d@\u0004|:\u0004A\u0004|4\u0000H\u0005.\u001dY\u00153\u0005.\u001d[\u00145\u0005.\u001d[\u0010A\u0003t>\u0016\u0004|7\u000eT\u0004-\u0019_\u0011\u0004}>\u0003H\b|4\u0002T\u0004x8m\u0005.\u001d\\eF\u0002j+\u0005.\u001d[\u001dE".length();
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
                    var3_2 = ".\u001d[f3\u0006|4\tB\u0014i";
                    var5_3 = ".\u001d[f3\u0006|4\tB\u0014i".length();
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
                        v10 = 31;
                        break;
                    }
                    case 1: {
                        v10 = 91;
                        break;
                    }
                    case 2: {
                        v10 = 111;
                        break;
                    }
                    case 3: {
                        v10 = 36;
                        break;
                    }
                    case 4: {
                        v10 = 113;
                        break;
                    }
                    case 5: {
                        v10 = 12;
                        break;
                    }
                    default: {
                        v10 = 93;
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
        Emoji.DATA = new String[]{var0[243], var0[202], var0[62], var0[257], var0[94], var0[168], var0[98], var0[128], var0[262], var0[165], var0[59], var0[179], var0[145], var0[221], var0[211], var0[166], var0[11], var0[222], var0[122], var0[46], var0[127], var0[20], var0[39], var0[197], var0[143], var0[158], var0[138], var0[69], var0[129], var0[263], var0[108], var0[238], var0[187], var0[35], var0[259], var0[173], var0[115], var0[209], var0[116], var0[258], var0[223], var0[123], var0[255], var0[44], var0[84], var0[13], var0[186], var0[160], var0[189], var0[146], var0[131], var0[0], var0[95], var0[117], var0[240], var0[231], var0[198], var0[265], var0[196], var0[99], var0[101], var0[164], var0[195], var0[236], var0[82], var0[256], var0[17], var0[34], var0[267], var0[162], var0[174], var0[206], var0[151], var0[18], var0[15], var0[45], var0[150], var0[137], var0[30], var0[54], var0[194], var0[172], var0[49], var0[273], var0[113], var0[4], var0[203], var0[207], var0[178], var0[220], var0[47], var0[78], var0[14], var0[9], var0[175], var0[239], var0[80], var0[213], var0[234], var0[237], var0[64], var0[1], var0[58], var0[253], var0[56], var0[181], var0[23], var0[25], var0[21], var0[40], var0[88], var0[141], var0[53], var0[89], var0[157], var0[5], var0[252], var0[217], var0[100], var0[12], var0[6], var0[33], var0[65], var0[66], var0[61], var0[86], var0[147], var0[70], var0[190], var0[132], var0[55], var0[22], var0[167], var0[241], var0[81], var0[152], var0[248], var0[67], var0[229], var0[120], var0[156], var0[48], var0[191], var0[144], var0[91], var0[7], var0[272], var0[27], var0[140], var0[218], var0[124], var0[268], var0[104], var0[148], var0[126], var0[212], var0[228], var0[92], var0[266], var0[93], var0[19], var0[87], var0[42], var0[142], var0[269], var0[37], var0[8], var0[235], var0[261], var0[226], var0[161], var0[169], var0[149], var0[155], var0[275], var0[246], var0[26], var0[102], var0[180], var0[153], var0[184], var0[114], var0[133], var0[31], var0[159], var0[32], var0[170], var0[193], var0[177], var0[77], var0[83], var0[250], var0[176], var0[107], var0[135], var0[60], var0[270], var0[274], var0[233], var0[201], var0[125], var0[200], var0[230], var0[110], var0[118], var0[247], var0[57], var0[85], var0[185], var0[41], var0[224], var0[182], var0[139], var0[204], var0[130], var0[76], var0[136], var0[90], var0[214], var0[249], var0[96], var0[199], var0[219], var0[51], var0[105], var0[29], var0[134], var0[227], var0[43], var0[52], var0[72], var0[103], var0[71], var0[109], var0[63], var0[79], var0[205], var0[119], var0[225], var0[73], var0[188], var0[210], var0[192], var0[216], var0[260], var0[242], var0[154], var0[244], var0[254], var0[97], var0[36], var0[106], var0[38], var0[264], var0[232], var0[74], var0[75], var0[112], var0[50], var0[251], var0[10], var0[111], var0[2], var0[183], var0[245], var0[121], var0[215], var0[171], var0[28], var0[271], var0[3], var0[208], var0[16], var0[24], var0[68], var0[163]};
        Emoji.ALL = Emoji.build();
        Emoji.BY_NAME = Emoji.index();
    }
}
