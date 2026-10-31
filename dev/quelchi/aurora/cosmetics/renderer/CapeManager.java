package dev.quelchi.aurora.cosmetics.renderer;

import dev.quelchi.aurora.cosmetics.backend.AccountCapes;
import dev.quelchi.aurora.cosmetics.renderer.EffectList;
import dev.quelchi.aurora.cosmetics.renderer.cape.CapeTextureStore;
import dev.quelchi.aurora.module.Module;
import java.util.Locale;
import net.minecraft.util.Identifier;

public final class CapeManager {
    private static final String a;

    private CapeManager() {
    }

    public static Identifier texture(String string) {
        String string2;
        block4: {
            block5: {
                int n = EffectList.a;
                string2 = string;
                if (n != 0) break block4;
                if (!AccountCapes.isAccount(string2)) break block5;
                return AccountCapes.texture(string);
            }
            string2 = string;
        }
        return CapeTextureStore.get(string2);
    }

    
    public static String key(String var0) {
        block48: {
            block47: {
                block39: {
                    var2_1 = new StringBuilder(var0.length());
                    var1_2 = EffectList.a;
                    for (int var6_6 : var0.toLowerCase(Locale.ROOT).toCharArray()) {
                        block42: {
                            block46: {
                                block45: {
                                    block43: {
                                        block44: {
                                            block40: {
                                                block41: {
                                                    if (var1_2 != 0) break block39;
                                                    v0 = var2_1;
                                                    v1 = var6_6;
                                                    if (var1_2 != 0) break block40;
                                                    if (v1 < 97) break block41;
                                                    v1 = var6_6;
                                                    if (var1_2 != 0) break block42;
                                                    if (v1 > 122) {
                                                    }
                                                }
                                                v1 = var6_6;
                                            }
                                            if (var1_2 != 0) break block43;
                                            if (v1 < 48) break block44;
                                            v1 = var6_6;
                                            if (var1_2 != 0) break block42;
                                            if (v1 > 57) {
                                            }
                                        }
                                        v1 = var6_6;
                                    }
                                    if (var1_2 != 0) break block42;
                                    if (v1 == 95) break block45;
                                    v1 = var6_6;
                                    if (var1_2 != 0) break block42;
                                    if (v1 == 46) break block45;
                                    v1 = var6_6;
                                    if (var1_2 != 0) break block42;
                                    if (v1 != 45) break block46;
                                }
                                v1 = var6_6;
                                break block42;
                            }
                            v1 = 95;
                        }
                        v0.append((char)v1);
                        if (var1_2 == 0) continue;
                        v17 = Module.a == false;
                        Module.a = v17;
                        break;
                    }
                    v19 = var2_1;
                    if (var1_2 != 0) break block47;
                    if (!v19.isEmpty()) break block39;
                    v21 = CapeManager.a;
                    break block48;
                }
                v19 = var2_1;
            }
            v21 = v19.toString();
        }
        return v21;
    }

    
    static {
        char[] cArray;
        block11: {
            int n;
            int n2;
            block10: {
                char[] cArray2 = "m>\u0012p".toCharArray();
                cArray = cArray2;
                n2 = cArray2.length;
                n = 0;
                if (!true) break block10;
                n2 = n2;
                if (n2 <= n) break block11;
            }
            do {
                cArray = cArray;
                int n3 = n;
                char c = cArray[n3];
                cArray[n3] = (char)(c ^ (switch (n % 7) {
                    case 0 -> 14;
                    case 1 -> 95;
                    case 2 -> 98;
                    case 3 -> 21;
                    case 4 -> 77;
                    case 5 -> 16;
                    default -> 33;
                }));
                ++n;
                n2 = n2;
            } while (n2 > n);
        }
        a = new String(cArray).intern();
    }
}
