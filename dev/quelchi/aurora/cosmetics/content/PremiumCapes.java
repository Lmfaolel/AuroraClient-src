package dev.quelchi.aurora.cosmetics.content;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.cosmetics.backend.CapePacks;
import dev.quelchi.aurora.cosmetics.content.Cosmetics;
import dev.quelchi.aurora.cosmetics.renderer.cape.CapeDefinition;
import dev.quelchi.aurora.cosmetics.renderer.cape.CapeLayer;
import dev.quelchi.aurora.cosmetics.renderer.cape.CapeLayer$Animation;
import dev.quelchi.aurora.cosmetics.renderer.cape.CapeLayer$Pass;
import dev.quelchi.aurora.cosmetics.renderer.cape.CapeTextureStore;
import dev.quelchi.aurora.cosmetics.renderer.cape.CosmeticRegistry;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.util.Identifier;

public final class PremiumCapes {
    private static volatile List<String> ids;
    private static volatile Set<String> beta;
    private static final Map<String, String> LABELS;
    private static final Map<String, Identifier> BASES;
    private static final String[] a;
    private static final long b;

    private PremiumCapes() {
    }

    public static void registerRemote(JsonArray jsonArray) {
        PremiumCapes.apply(jsonArray);
    }

    
    private static void apply(JsonArray jsonArray) {
        block19: {
            ArrayList<String> arrayList = new ArrayList<String>();
            int n = Cosmetics.a;
            HashSet<String> hashSet = new HashSet<String>();
            for (JsonElement jsonElement : jsonArray) {
                block20: {
                    try {
                        String string;
                        JsonObject jsonObject = jsonElement.getAsJsonObject();
                        if (n != 0) break block19;
                        String[] stringArray = a;
                        String string2 = jsonObject.get(stringArray[29]).getAsString();
                        LABELS.put(string2, jsonObject.get(stringArray[19]).getAsString());
                        JsonObject jsonObject2 = jsonObject;
                        if (n == 0) {
                            if (!PremiumCapes.flag(jsonObject2, stringArray[5])) {
                                arrayList.add(string2);
                            }
                            jsonObject2 = jsonObject;
                        }
                        if (n == 0) {
                            if (PremiumCapes.flag(jsonObject2, a[8])) {
                                hashSet.add(string2);
                            }
                            stringArray = a;
                            jsonObject2 = jsonObject.get(stringArray[25]);
                        }
                        String string3 = string = jsonObject2.getAsString();
                        if (n == 0) {
                            if (!string3.matches(a[2]) && n == 0) continue;
                            string3 = string;
                        }
                        if (n == 0) {
                            if (CapePacks.has(string3)) {
                                PremiumCapes.define(jsonObject, string2, a[11] + string + "/");
                                if (n == 0) break block20;
                            }
                            string3 = string;
                        }
                        CapePacks.fetch(string3, () -> PremiumCapes.lambda$apply$0(jsonObject, string2, string));
                    }
                    catch (Exception exception) {
                        Aurora.LOG.warn(a[12], (Object)exception.toString());
                    }
                }
                if (n == 0) continue;
            }
            ids = List.copyOf(arrayList);
            beta = hashSet;
        }
    }

    
    private static void define(JsonObject var0, String var1_1, String var2_2) {
        block36: {
            var3_3 = Cosmetics.a;
            try {
                block45: {
                    block55: {
                        block35: {
                            block46: {
                                block47: {
                                    var12_4 = PremiumCapes.a;
                                    var4_5 = new CapeDefinition(var1_1).layer(CapeLayer.base(Identifier.method_60655((String)var12_4[30], (String)(var12_4[27] + var1_1))));
                                    v0 = var0;
                                    if (var3_3 != 0) break block46;
                                    if (!v0.has(var12_4[18])) break block35;
                                    break block47;
                                    catch (Exception v1) {
                                        throw v1;
                                    }
                                }
                                v0 = var0;
                            }
                            var12_4 = PremiumCapes.a;
                            for (JsonElement var6_8 : v0.getAsJsonArray(var12_4[6])) {
                                block54: {
                                    block44: {
                                        block43: {
                                            block53: {
                                                block52: {
                                                    block42: {
                                                        block41: {
                                                            block51: {
                                                                block39: {
                                                                    block40: {
                                                                        block50: {
                                                                            block37: {
                                                                                block38: {
                                                                                    block49: {
                                                                                        block48: {
                                                                                            var7_9 = var6_8.getAsJsonObject();
                                                                                            if (var3_3 != 0) break block36;
                                                                                            var8_10 = new ArrayList<Identifier>();
                                                                                            v3 = var7_9;
                                                                                            if (var3_3 != 0) // goto
                                                                                            if (!v3.has(PremiumCapes.a[16])) // goto
                                                                                            break block48;
                                                                                            catch (Exception v4) {
                                                                                                throw v4;
                                                                                            }
                                                                                        }
                                                                                        v5 = var8_10;
                                                                                        v6 = Identifier.method_60655((String)PremiumCapes.a[15], (String)(var2_2 + var7_9.get(PremiumCapes.a[14]).getAsString() + PremiumCapes.a[26]));
                                                                                        block33: while (true) {
                                                                                            v5.add(v6);
                                                                                            if (var3_3 == 0) break;
lbl42:

                                                                                            var12_4 = PremiumCapes.a;
                                                                                            v3 = var7_9.get(var12_4[24]);
lbl44:

                                                                                            var9_12 = v3.getAsString();
                                                                                            var12_4 = PremiumCapes.a;
                                                                                            var10_13 = var7_9.get(var12_4[10]).getAsInt();
                                                                                            for (var11_14 = 0; var11_14 < var10_13; ++var11_14) {
                                                                                                v5 = var8_10;
                                                                                                var12_4 = PremiumCapes.a;
                                                                                                v6 = Identifier.method_60655((String)var12_4[15], (String)String.format(var12_4[7], new Object[]{var2_2, var9_12, var11_14}));
                                                                                                if (var3_3 != 0) continue block33;
                                                                                                v5.add(v6);
                                                                                                if (var3_3 == 0) continue;
                                                                                            }
                                                                                            break;
                                                                                        }
                                                                                        v8 = var8_10.size();
                                                                                        if (var3_3 != 0) break block37;
                                                                                        if (v8 <= 1) break block38;
                                                                                        break block49;
                                                                                        catch (Exception v9) {
                                                                                            throw v9;
                                                                                        }
                                                                                    }
                                                                                    CapeTextureStore.animated(var8_10);
                                                                                }
                                                                                var12_4 = PremiumCapes.a;
                                                                                v8 = (int)var7_9.has(var12_4[13]);
                                                                            }
                                                                            if (var3_3 != 0) break block39;
                                                                            if (v8 == 0) break block40;
                                                                            break block50;
                                                                            catch (Exception v11) {
                                                                                throw v11;
                                                                            }
                                                                        }
                                                                        v8 = (int)PremiumCapes.b | Integer.parseInt(var7_9.get(PremiumCapes.a[3]).getAsString(), 16);
                                                                        break block39;
                                                                    }
                                                                    v8 = -1;
                                                                }
                                                                var9_11 = v8;
                                                                v13 = var4_5;
                                                                v14 = CapeLayer$Pass.valueOf(var7_9.get(PremiumCapes.a[21]).getAsString());
                                                                v15 = var8_10;
                                                                v16 = var8_10.size() > 1 ? CapeLayer$Animation.LOOP : CapeLayer$Animation.STATIC;
                                                                v18 = var7_9;
                                                                if (var3_3 != 0) break block41;
                                                                if (!v18.has(PremiumCapes.a[22])) break block42;
                                                                break block51;
                                                                catch (Exception v19) {
                                                                    throw v19;
                                                                }
                                                            }
                                                            v18 = var7_9.get(PremiumCapes.a[1]);
                                                        }
                                                        v21 = v18.getAsFloat();
                                                        break block52;
                                                    }
                                                    v21 = 0.0f;
                                                }
                                                v22 = var9_11;
                                                v23 = var7_9;
                                                if (var3_3 != 0) break block43;
                                                if (!v23.has(PremiumCapes.a[0])) break block44;
                                                break block53;
                                                catch (Exception v24) {
                                                    throw v24;
                                                }
                                            }
                                            v23 = var7_9.get(PremiumCapes.a[20]);
                                        }
                                        v26 = v23.getAsFloat();
                                        break block54;
                                    }
                                    v26 = 1.0f;
                                }
                                var12_4 = PremiumCapes.a;
                                v13.layer(CapeLayer.of(v14, v15, v16, v21, v22, v26, 0.0f, 0.0f, PremiumCapes.flag(var7_9, var12_4[4])));
                                if (var3_3 == 0) continue;
                            }
                        }
                        var12_4 = PremiumCapes.a;
                        var5_7 = Identifier.method_60655((String)var12_4[15], (String)(var2_2 + var12_4[28]));
                        if (var3_3 != 0) break block36;
                        if (var5_7.equals(PremiumCapes.BASES.put(var1_1, (Identifier)var5_7))) break block45;
                        break block55;
                        catch (Exception v27) {
                            throw v27;
                        }
                    }
                    CapeTextureStore.forget(Identifier.method_60655((String)PremiumCapes.a[15], (String)(PremiumCapes.a[9] + var1_1)));
                }
                CosmeticRegistry.registerCape(var4_5);
            }
            catch (Exception var4_6) {
                Aurora.LOG.warn(PremiumCapes.a[17], (Object)var1_1, (Object)var4_6.toString());
            }
        }
    }

    private static boolean flag(JsonObject jsonObject, String string) {
        boolean bl;
        block8: {
            block7: {
                JsonObject jsonObject2;
                int n;
                block6: {
                    n = Cosmetics.a;
                    jsonObject2 = jsonObject;
                    if (n != 0) break block6;
                    if (!jsonObject2.has(string)) break block7;
                    jsonObject2 = jsonObject.get(string);
                }
                bl = jsonObject2.getAsBoolean();
                if (n != 0) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    public static Identifier base(String string) {
        return BASES.get(string);
    }

    public static List<String> ids() {
        return ids;
    }

    public static boolean isBetaCape(String string) {
        boolean bl;
        block5: {
            block4: {
                int n = Cosmetics.a;
                if (string == null) break block4;
                bl = beta.contains(string);
                if (n != 0) break block5;
                if (!bl) break block4;
                bl = true;
                break block5;
            }
            bl = false;
        }
        return bl;
    }

    public static String displayName(String string) {
        Object object;
        block18: {
            String string2;
            block16: {
                block17: {
                    String string3;
                    int n;
                    block14: {
                        block15: {
                            String string4;
                            block12: {
                                block13: {
                                    n = Cosmetics.a;
                                    string4 = string;
                                    if (n != 0) break block12;
                                    if (string4 == null) break block13;
                                    string3 = string;
                                    if (n != 0) break block14;
                                    if (!string3.isEmpty()) break block15;
                                }
                                string4 = "";
                            }
                            return string4;
                        }
                        string3 = LABELS.get(string);
                    }
                    String string5 = string3;
                    string2 = string5;
                    if (n != 0) break block16;
                    if (string2 == null) break block17;
                    object = string5;
                    break block18;
                }
                string2 = string;
            }
            object = Character.toUpperCase(string2.charAt(0)) + string.substring(1);
        }
        return object;
    }

    private static void lambda$apply$0(JsonObject jsonObject, String string, String string2) {
        PremiumCapes.define(jsonObject, string, a[23] + string2 + "/");
    }

    
    static {
        block21: {
            block20: {
                block19: {
                    var7 = new String[31];
                    var5_1 = 0;
                    var4_2 = "i\nYO\f\u0003n\u0016Z\fSV\u0004\u001e\f\n{U\u001d\u001f\u0013\u0010\u0004|\u000fGS\u0005j\nLI\t\u0006`\u000fMC\bI\u0006d\u0007PB\u001fT\r-\u0015\fT2\u0002-:\u0002\u0007W\u0003@\u0004j\u0003]F\u0005k\u0007YBB\u0005k\t\\I\u0019\tk\u0007YB\u001dF~cI\u0017[\u0003[Q\bC=k\u0007YBMTva\u0016YB\t\u001d=s\u001b\u0004|\u000fGS\u0004n\u000fEB\u0006i\u0013[H\u001fF\u0004n\u000fEB\u0015K\u0007YBM\u0000fuA\tT\u0006Nmx\u0003M\u001dM\\`\u0006d\u0007PB\u001fT\u0005d\u0007KB\u0001\u0005i\nYO\f\u0004x\u0007ZT\u0003n\u0016Z\tk\u0007YB\u001dF~cI\u0006n\u0014HJ\bT\u0004x\u0007JL\u0004&\u0016G@\u0005k\u0007YBB\bj\u0007ZBCWso";
                    var6_3 = "i\nYO\f\u0003n\u0016Z\fSV\u0004\u001e\f\n{U\u001d\u001f\u0013\u0010\u0004|\u000fGS\u0005j\nLI\t\u0006`\u000fMC\bI\u0006d\u0007PB\u001fT\r-\u0015\fT2\u0002-:\u0002\u0007W\u0003@\u0004j\u0003]F\u0005k\u0007YBB\u0005k\t\\I\u0019\tk\u0007YB\u001dF~cI\u0017[\u0003[Q\bC=k\u0007YBMTva\u0016YB\t\u001d=s\u001b\u0004|\u000fGS\u0004n\u000fEB\u0006i\u0013[H\u001fF\u0004n\u000fEB\u0015K\u0007YBM\u0000fuA\tT\u0006Nmx\u0003M\u001dM\\`\u0006d\u0007PB\u001fT\u0005d\u0007KB\u0001\u0005i\nYO\f\u0004x\u0007ZT\u0003n\u0016Z\tk\u0007YB\u001dF~cI\u0006n\u0014HJ\bT\u0004x\u0007JL\u0004&\u0016G@\u0005k\u0007YBB\bj\u0007ZBCWso".length();
                    var3_4 = 5;
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
                        var4_2 = "a\u0002\u0006i\u0013[H\u001fF";
                        var6_3 = "a\u0002\u0006i\u0013[H\u001fF".length();
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
                            v10 = 8;
                            break;
                        }
                        case 1: {
                            v10 = 102;
                            break;
                        }
                        case 2: {
                            v10 = 41;
                            break;
                        }
                        case 3: {
                            v10 = 39;
                            break;
                        }
                        case 4: {
                            v10 = 109;
                            break;
                        }
                        case 5: {
                            v10 = 39;
                            break;
                        }
                        default: {
                            v10 = 29;
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
            PremiumCapes.a = var7;
            break block21;
lbl77:

            while (true) {
                continue;
                break;
            }
        }
        var0_7 = 1168841733475787139L;
        while (true)
        PremiumCapes.b = -6859676342729186941L ^ var0_7;
        PremiumCapes.ids = List.of();
        PremiumCapes.beta = Set.of();
        PremiumCapes.LABELS = new ConcurrentHashMap<String, String>();
        PremiumCapes.BASES = new ConcurrentHashMap<String, Identifier>();
    }
}
