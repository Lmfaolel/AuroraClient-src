package dev.quelchi.aurora.module;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.quelchi.aurora.module.Category;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class HudScales {
    private static final Map<String, Float> SCALES;
    private static final Map<String, int[]> HOME;
    public static final float MIN = 0.25f;
    public static final float MAX = 6.0f;
    private static final Set<String> HIDDEN;
    private static final String[] a;

    private HudScales() {
    }

    public static void rememberHome(String string, int n, int n2) {
        block4: {
            int n3 = Category.a;
            Object object = string;
            if (n3 != 0 || object == null) break block4;
            object = HOME.putIfAbsent(string, new int[]{n, n2});
        }
    }

    public static int[] home(String string) {
        int[] nArray;
        block6: {
            Object object;
            block4: {
                block5: {
                    int n = Category.a;
                    object = string;
                    if (n != 0) break block4;
                    if (object != null) break block5;
                    nArray = null;
                    break block6;
                }
                object = HOME.get(string);
            }
            nArray = (int[])object;
        }
        return nArray;
    }

    public static float get(String string) {
        float f;
        block6: {
            Float f2;
            block4: {
                Float f3;
                block5: {
                    f3 = SCALES.get(string);
                    int n = Category.a;
                    f2 = f3;
                    if (n != 0) break block4;
                    if (f2 != null) break block5;
                    f = 1.0f;
                    break block6;
                }
                f2 = f3;
            }
            f = f2.floatValue();
        }
        return f;
    }

    public static boolean hidden(String string) {
        boolean bl;
        block5: {
            block4: {
                int n = Category.a;
                if (string == null) break block4;
                bl = HIDDEN.contains(string);
                if (n != 0) break block5;
                if (!bl) break block4;
                bl = true;
                break block5;
            }
            bl = false;
        }
        return bl;
    }

    public static void setHidden(String string, boolean bl) {
        block9: {
            boolean bl2;
            block10: {
                int n = Category.a;
                if (string == null) {
                    return;
                }
                bl2 = bl;
                if (n != 0) break block9;
                if (!bl2) break block10;
                HIDDEN.add(string);
                if (n == 0) break block9;
            }
            bl2 = HIDDEN.remove(string);
        }
    }

    
    public static void set(String string, float f) {
        float f2;
        block6: {
            int n;
            block5: {
                n = Category.a;
                f2 = Math.max(0.25f, Math.min(6.0f, f));
                if (n != 0) break block5;
                if (f2 != 1.0f) break block6;
                SCALES.remove(string);
            }
            if (n == 0) return;
        }
        SCALES.put(string, Float.valueOf(f2));
    }

    public static void write(JsonObject jsonObject) {
        block14: {
            JsonArray jsonArray;
            boolean bl;
            int n;
            block11: {
                block12: {
                    String string;
                    JsonObject jsonObject2;
                    block13: {
                        n = Category.a;
                        bl = SCALES.isEmpty();
                        if (n != 0) break block11;
                        if (bl) break block12;
                        jsonArray = new JsonObject();
                        for (Map.Entry object : SCALES.entrySet()) {
                            jsonObject2 = jsonArray;
                            string = (String)object.getKey();
                            if (n == 0) {
                                jsonObject2.addProperty(string, (Number)object.getValue());
                                if (n == 0) continue;
                                break;
                            }
                            break block13;
                        }
                        jsonObject2 = jsonObject;
                        String[] stringArray = a;
                        string = stringArray[2];
                    }
                    jsonObject2.add(string, (JsonElement)jsonArray);
                }
                bl = HIDDEN.isEmpty();
            }
            if (!bl) {
                jsonArray = new JsonArray();
                for (String string : HIDDEN) {
                    if (n == 0) {
                        jsonArray.add(string);
                        if (n == 0) continue;
                        break;
                    }
                    break block14;
                }
                jsonObject.add(a[1], (JsonElement)jsonArray);
            }
        }
    }

    
    public static void read(JsonObject jsonObject) {
        block26: {
            JsonObject jsonObject2;
            int n;
            block28: {
                JsonObject jsonObject3;
                block27: {
                    block31: {
                        String string;
                        block24: {
                            block25: {
                                block30: {
                                    block29: {
                                        block22: {
                                            block23: {
                                                SCALES.clear();
                                                HIDDEN.clear();
                                                n = Category.a;
                                                jsonObject3 = jsonObject;
                                                if (n != 0) break block22;
                                                if (jsonObject3 != null) break block23;
                                                return;
                                            }
                                            jsonObject3 = jsonObject;
                                        }
                                        string = a[3];
                                        if (n != 0) break block24;
                                        if (!jsonObject3.has(string)) break block25;
                                        break block29;
                                        catch (Exception exception) {
                                            throw exception;
                                        }
                                    }
                                    jsonObject3 = jsonObject;
                                    string = a[3];
                                    if (n != 0) break block24;
                                    break block30;
                                    catch (Exception exception) {
                                        throw exception;
                                    }
                                }
                                if (!jsonObject3.get(string).isJsonObject()) break block25;
                                String[] stringArray = a;
                                Iterator iterator = jsonObject.getAsJsonObject(stringArray[3]);
                                for (String string2 : iterator.keySet()) {
                                    try {
                                        if (n != 0) break block26;
                                        HudScales.set(string2, iterator.get(string2).getAsFloat());
                                    }
                                    catch (Exception exception) {
                                    }
                                    if (n == 0) continue;
                                }
                            }
                            jsonObject3 = jsonObject;
                            if (n != 0) break block27;
                            string = a[0];
                        }
                        if (!jsonObject3.has(string)) break block26;
                        jsonObject2 = jsonObject;
                        if (n != 0) break block28;
                        break block31;
                        catch (Exception exception) {
                            throw exception;
                        }
                    }
                    jsonObject3 = jsonObject2.get(a[0]);
                }
                if (!jsonObject3.isJsonArray()) break block26;
                jsonObject2 = jsonObject;
            }
            for (Object object : jsonObject2.getAsJsonArray(a[0])) {
                try {
                    HIDDEN.add(object.getAsString());
                }
                catch (Exception exception) {
                }
                if (n == 0) continue;
            }
        }
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[4];
                var3_1 = 0;
                var2_2 = "u)Y\u00006s0x2\tu)Y\u00006s0x2";
                var4_3 = "u)Y\u00006s0x2\tu)Y\u00006s0x2".length();
                var1_4 = 9;
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
                    var2_2 = "u)Y\u001b<v8x/\tu)Y\u001b<v8x/";
                    var4_3 = "u)Y\u001b<v8x/\tu)Y\u001b<v8x/".length();
                    var1_4 = 9;
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
                        v10 = 29;
                        break;
                    }
                    case 1: {
                        v10 = 92;
                        break;
                    }
                    case 2: {
                        v10 = 61;
                        break;
                    }
                    case 3: {
                        v10 = 72;
                        break;
                    }
                    case 4: {
                        v10 = 95;
                        break;
                    }
                    case 5: {
                        v10 = 23;
                        break;
                    }
                    default: {
                        v10 = 84;
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
        HudScales.a = var5;
        HudScales.SCALES = new ConcurrentHashMap<String, Float>();
        HudScales.HOME = new ConcurrentHashMap<String, int[]>();
        HudScales.HIDDEN = ConcurrentHashMap.newKeySet();
    }
}
