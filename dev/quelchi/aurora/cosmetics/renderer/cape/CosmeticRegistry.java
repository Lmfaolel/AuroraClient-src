package dev.quelchi.aurora.cosmetics.renderer.cape;

import dev.quelchi.aurora.cosmetics.renderer.cape.CapeDefinition;
import dev.quelchi.aurora.cosmetics.renderer.cape.CapeLayer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.util.Identifier;

public final class CosmeticRegistry {
    private static final Map<String, CapeDefinition> CAPES;
    private static final Map<Identifier, CapeDefinition> BY_TEXTURE;
    private static final String[] a;

    private CosmeticRegistry() {
    }

    public static void registerCape(CapeDefinition capeDefinition) {
        CAPES.put(capeDefinition.id(), capeDefinition);
        String[] stringArray = a;
        BY_TEXTURE.remove(Identifier.method_60655((String)stringArray[1], (String)(stringArray[3] + capeDefinition.id())));
    }

    public static boolean contains(String string) {
        return CAPES.containsKey(string);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static CapeDefinition capeFor(Identifier identifier) {
        CapeDefinition capeDefinition;
        CapeDefinition capeDefinition2;
        block28: {
            CapeDefinition capeDefinition3;
            CapeDefinition capeDefinition4;
            boolean bl;
            block26: {
                block27: {
                    String string;
                    block24: {
                        String string2;
                        block25: {
                            block23: {
                                block22: {
                                    Object object;
                                    block20: {
                                        block21: {
                                            bl = CapeLayer.a;
                                            if (identifier == null) return null;
                                            object = a[0];
                                            if (bl) break block20;
                                            if (((String)object).equals(identifier.method_12836())) break block21;
                                            return null;
                                        }
                                        object = BY_TEXTURE.get(identifier);
                                    }
                                    CapeDefinition capeDefinition5 = (CapeDefinition)object;
                                    CapeDefinition capeDefinition6 = capeDefinition5;
                                    if (bl) return capeDefinition6;
                                    if (capeDefinition6 == null) break block22;
                                    capeDefinition6 = capeDefinition5;
                                    return capeDefinition6;
                                }
                                string2 = identifier.method_12832();
                                string = string2;
                                if (bl) break block23;
                                if (!string.startsWith(a[2])) return null;
                                string = string2;
                            }
                            if (bl) break block24;
                            if (string.length() != 5) break block25;
                            return null;
                        }
                        string = string2.substring(5);
                    }
                    String string3 = string;
                    capeDefinition4 = CAPES.get(string3);
                    capeDefinition3 = capeDefinition4;
                    if (bl) break block26;
                    if (capeDefinition3 != null) break block27;
                    capeDefinition4 = CapeDefinition.single(string3, identifier);
                }
                capeDefinition3 = BY_TEXTURE.putIfAbsent(identifier, capeDefinition4);
            }
            capeDefinition2 = capeDefinition3;
            capeDefinition = capeDefinition2;
            if (bl) return capeDefinition;
            if (capeDefinition != null) break block28;
            capeDefinition = capeDefinition4;
            return capeDefinition;
        }
        capeDefinition = capeDefinition2;
        return capeDefinition;
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[4];
                var3_1 = 0;
                var2_2 = ">+F=D4\u0006>+F=D4";
                var4_3 = ">+F=D4\u0006>+F=D4".length();
                var1_4 = 6;
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
                    var2_2 = "<?D7\u0019\u0005<?D7\u0019";
                    var4_3 = "<?D7\u0019\u0005<?D7\u0019".length();
                    var1_4 = 5;
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
            var6_6 = 0;
            do {
                v6 = v6;
                v8 = var6_6;
                v9 = v6[v8];
                switch (var6_6 % 7) {
                    case 0: {
                        v10 = 95;
                        break;
                    }
                    case 1: {
                        v10 = 94;
                        break;
                    }
                    case 2: {
                        v10 = 52;
                        break;
                    }
                    case 3: {
                        v10 = 82;
                        break;
                    }
                    case 4: {
                        v10 = 54;
                        break;
                    }
                    case 5: {
                        v10 = 85;
                        break;
                    }
                    default: {
                        v10 = 38;
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
        CosmeticRegistry.a = var5;
        CosmeticRegistry.CAPES = new ConcurrentHashMap<String, CapeDefinition>();
        CosmeticRegistry.BY_TEXTURE = new ConcurrentHashMap<Identifier, CapeDefinition>();
    }
}
