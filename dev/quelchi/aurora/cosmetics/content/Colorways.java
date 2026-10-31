package dev.quelchi.aurora.cosmetics.content;

import dev.quelchi.aurora.cosmetics.content.Colorways$Way;
import dev.quelchi.aurora.cosmetics.content.Cosmetics;
import java.util.Iterator;
import java.util.List;

public final class Colorways {
    public static final char SEP;

    private Colorways() {
    }

    public static Colorways$Way way(String string, String string2, int n, int n2) {
        return new Colorways$Way(string, string2, n, n2);
    }

    public static String designOf(String string) {
        String string2;
        block12: {
            block13: {
                String string3;
                block10: {
                    block11: {
                        int n = Cosmetics.a;
                        string3 = string;
                        if (n != 0) break block10;
                        if (string3 == null) break block11;
                        string2 = string;
                        if (n != 0) break block12;
                        if (!string2.isEmpty()) break block13;
                    }
                    string3 = "";
                }
                return string3;
            }
            string2 = string;
        }
        int n = Colorways.split(string2);
        String string4 = n < 0 ? string : string.substring(0, n);
        return string4;
    }

    public static String wayOf(String string) {
        String string2;
        block6: {
            block7: {
                int n = Cosmetics.a;
                string2 = string;
                if (n != 0) break block6;
                if (string2 != null) break block7;
                return null;
            }
            string2 = string;
        }
        int n = Colorways.split(string2);
        String string3 = n < 0 ? null : string.substring(n + 1);
        return string3;
    }

    public static String store(String string, String string2, List<Colorways$Way> list) {
        Object object;
        block21: {
            String string3;
            block23: {
                block24: {
                    block22: {
                        int n;
                        block20: {
                            String string4;
                            block18: {
                                block19: {
                                    n = Cosmetics.a;
                                    string4 = string;
                                    if (n != 0) break block18;
                                    if (string4 == null) break block19;
                                    string4 = string;
                                    if (n != 0) break block18;
                                    if (string4.isEmpty()) break block19;
                                    if (!list.isEmpty()) break block20;
                                }
                                string4 = "";
                            }
                            return string4;
                        }
                        object = string2;
                        if (n != 0) break block21;
                        if (object == null) break block22;
                        string3 = string2;
                        if (n != 0) break block23;
                        if (!string3.equals(list.get(0).id())) break block24;
                    }
                    object = string;
                    break block21;
                }
                string3 = string;
            }
            object = string3 + "." + string2;
        }
        return object;
    }

    public static Colorways$Way pick(List<Colorways$Way> list, String string) {
        Iterator<Colorways$Way> iterator;
        block7: {
            block8: {
                int n = Cosmetics.a;
                iterator = string;
                if (n != 0) break block7;
                if (iterator == null) break block8;
                Iterator<Colorways$Way> iterator2 = list.iterator();
                while (iterator2.hasNext()) {
                    block10: {
                        Colorways$Way colorways$Way;
                        block9: {
                            iterator = iterator2;
                            if (n != 0) break block7;
                            Colorways$Way colorways$Way2 = iterator.next();
                            colorways$Way = colorways$Way2;
                            if (n != 0) break block9;
                            if (!colorways$Way.id().equals(string)) break block10;
                            colorways$Way = colorways$Way2;
                        }
                        return colorways$Way;
                    }
                    if (n == 0) continue;
                }
            }
            iterator = list.get(0);
        }
        return (Colorways$Way)((Object)iterator);
    }

    private static int split(String string) {
        int n;
        block4: {
            int n2;
            block5: {
                n2 = string.indexOf(46);
                int n3 = Cosmetics.a;
                n = n2;
                if (n3 != 0) break block4;
                if (n >= 0) break block5;
                n = string.indexOf(58);
                break block4;
            }
            n = n2;
        }
        return n;
    }

    
    static {
        long l = 1261200777034682164L;
        long l2 = 0x409C707AB1994F1AL ^ l;
        SEP = (char)l2;
    }
}
