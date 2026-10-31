package dev.quelchi.aurora.crosshair;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.quelchi.aurora.crosshair.IconAnimation;
import dev.quelchi.aurora.gui.IconTex;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public final class IconStore {
    public static final int SIZE;
    private static final Map<String, IconAnimation> ICONS;
    private static final Map<String, IconAnimation> DEFAULTS;
    private static final Set<String> HIDDEN;
    public static boolean a;
    private static final String[] b;

    private IconStore() {
    }

    public static void register(String string, int n, int n2, int n3, int[][] ... nArray) {
        IconAnimation iconAnimation = new IconAnimation(n, n2, nArray);
        iconAnimation.setSpeed(n3);
        ICONS.put(string, iconAnimation);
        DEFAULTS.put(string, iconAnimation.copyOf());
    }

    public static void registerHidden(String string, int n, int n2, int n3, int[][] ... nArray) {
        IconStore.register(string, n, n2, n3, nArray);
        HIDDEN.add(string);
    }

    public static IconAnimation get(String string) {
        return ICONS.get(string);
    }

    public static Iterable<String> keys() {
        ArrayList<String> arrayList = new ArrayList<String>();
        Iterator<String> iterator = ICONS.keySet().iterator();
        boolean bl = a;
        while (iterator.hasNext()) {
            block5: {
                String string = iterator.next();
                boolean bl2 = HIDDEN.contains(string);
                if (bl || bl2) break block5;
                bl2 = arrayList.add(string);
            }
            if (!bl) continue;
        }
        return arrayList;
    }

    public static boolean isDefault(String string) {
        boolean bl;
        block9: {
            block7: {
                IconAnimation iconAnimation;
                IconAnimation iconAnimation2;
                boolean bl2;
                block8: {
                    IconAnimation iconAnimation3;
                    block6: {
                        iconAnimation3 = ICONS.get(string);
                        bl2 = a;
                        iconAnimation2 = DEFAULTS.get(string);
                        iconAnimation = iconAnimation3;
                        if (bl2) break block6;
                        if (iconAnimation == null) break block7;
                        iconAnimation = iconAnimation2;
                    }
                    if (bl2) break block8;
                    if (iconAnimation == null) break block7;
                    iconAnimation = iconAnimation3;
                }
                bl = iconAnimation.matches(iconAnimation2);
                if (bl2) break block9;
                if (!bl) break block7;
                bl = true;
                break block9;
            }
            bl = false;
        }
        return bl;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void set(String string, int n, int n2, int n3, int n4) {
        int[][] nArray;
        block28: {
            int n5;
            int[][] nArray2;
            int n6;
            block26: {
                boolean bl;
                block27: {
                    block25: {
                        block24: {
                            block23: {
                                IconAnimation iconAnimation;
                                block21: {
                                    IconAnimation iconAnimation2;
                                    block22: {
                                        iconAnimation2 = ICONS.get(string);
                                        bl = a;
                                        iconAnimation = iconAnimation2;
                                        if (bl) break block21;
                                        if (iconAnimation != null) break block22;
                                        return;
                                    }
                                    iconAnimation = iconAnimation2;
                                }
                                nArray = iconAnimation.frame(n);
                                if (nArray == null) return;
                                n6 = n3;
                                if (bl) break block23;
                                if (n6 < 0) return;
                                n6 = n3;
                            }
                            if (bl) break block24;
                            if (n6 >= nArray.length) return;
                            n6 = n2;
                        }
                        if (bl) break block25;
                        if (n6 < 0) return;
                        n6 = n2;
                    }
                    if (bl) break block26;
                    if (n6 < nArray[n3].length) break block27;
                    return;
                }
                nArray2 = nArray;
                n5 = n3;
                if (bl) break block28;
                n6 = nArray2[n5][n2];
            }
            if (n6 == n4) {
                return;
            }
            nArray2 = nArray;
            n5 = n3;
        }
        nArray2[n5][n2] = n4;
        IconTex.invalidate(nArray);
    }

    public static void replace(String string, int n, int[][] nArray) {
        block22: {
            int[][] nArray2;
            boolean bl;
            block20: {
                IconAnimation iconAnimation;
                block21: {
                    block19: {
                        iconAnimation = ICONS.get(string);
                        bl = a;
                        if (iconAnimation == null) break block19;
                        nArray2 = nArray;
                        if (bl) break block20;
                        if (nArray2 != null) break block21;
                    }
                    return;
                }
                nArray2 = iconAnimation.frame(n);
            }
            int[][] nArray3 = nArray2;
            if (nArray3 == null) {
                return;
            }
            int n2 = 0;
            while (n2 < nArray3.length) {
                block23: {
                    if (bl) break block22;
                    for (int i = 0; i < nArray3[n2].length; ++i) {
                        int n3;
                        block26: {
                            block25: {
                                int[][] nArray4;
                                block24: {
                                    if (bl) break block23;
                                    int[] nArray5 = nArray3[n2];
                                    int n4 = i;
                                    n3 = n2;
                                    nArray4 = nArray;
                                    if (bl) break block24;
                                    if (n3 >= nArray4.length) break block25;
                                    n3 = i;
                                    if (bl) break block26;
                                    nArray4 = nArray;
                                }
                                if (n3 >= nArray4[n2].length) break block25;
                                n3 = nArray[n2][i];
                                break block26;
                            }
                            n3 = 0;
                        }
                        nArray5[n4] = n3;
                        if (!bl) continue;
                    }
                    ++n2;
                }
                if (!bl) continue;
            }
            IconTex.invalidate(nArray3);
        }
    }

    public static void clear(String string, int n) {
        block12: {
            int[][] nArray;
            int[][] nArray2;
            boolean bl;
            block10: {
                block11: {
                    IconAnimation iconAnimation;
                    block8: {
                        IconAnimation iconAnimation2;
                        block9: {
                            iconAnimation2 = ICONS.get(string);
                            bl = a;
                            iconAnimation = iconAnimation2;
                            if (bl) break block8;
                            if (iconAnimation != null) break block9;
                            return;
                        }
                        iconAnimation = iconAnimation2;
                    }
                    nArray2 = iconAnimation.frame(n);
                    nArray = nArray2;
                    if (bl) break block10;
                    if (nArray != null) break block11;
                    return;
                }
                nArray = nArray2;
            }
            for (int[] nArray3 : nArray) {
                if (!bl) {
                    Arrays.fill(nArray3, 0);
                    if (!bl) continue;
                    break;
                }
                break block12;
            }
            IconTex.invalidate(nArray2);
        }
    }

    public static void reset(String string) {
        IconAnimation iconAnimation;
        IconAnimation iconAnimation2;
        block6: {
            IconAnimation iconAnimation3;
            block7: {
                block5: {
                    boolean bl;
                    block4: {
                        iconAnimation3 = ICONS.get(string);
                        iconAnimation2 = DEFAULTS.get(string);
                        bl = a;
                        iconAnimation = iconAnimation3;
                        if (bl) break block4;
                        if (iconAnimation == null) break block5;
                        iconAnimation = iconAnimation2;
                    }
                    if (bl) break block6;
                    if (iconAnimation != null) break block7;
                }
                return;
            }
            iconAnimation = iconAnimation3;
        }
        iconAnimation.copyFrom(iconAnimation2);
    }

    public static void write(JsonObject jsonObject) {
        block19: {
            JsonObject jsonObject2;
            JsonObject jsonObject3;
            block24: {
                jsonObject3 = new JsonObject();
                boolean bl = a;
                block14: for (Map.Entry<String, IconAnimation> entry : ICONS.entrySet()) {
                    block22: {
                        JsonObject jsonObject4;
                        IconAnimation iconAnimation;
                        block23: {
                            boolean bl2;
                            JsonArray jsonArray;
                            JsonArray jsonArray2;
                            if (bl) break block19;
                            Object object = entry.getKey();
                            block15: while (true) {
                                if (!bl) {
                                    if (IconStore.isDefault((String)object)) {
                                        continue block14;
                                    }
                                    object = entry.getValue();
                                }
                                iconAnimation = (IconAnimation)object;
                                jsonArray2 = new JsonArray();
                                jsonArray = new JsonArray();
                                bl2 = false;
                                for (int i = 0; i < iconAnimation.frameCount(); ++i) {
                                    boolean bl3;
                                    boolean bl4;
                                    JsonArray jsonArray3;
                                    block20: {
                                        block21: {
                                            JsonArray jsonArray4 = jsonArray2;
                                            if (bl) continue block15;
                                            jsonArray4.add(IconStore.encode(iconAnimation.frame(i)));
                                            jsonArray3 = IconStore.colors(iconAnimation.frame(i));
                                            bl4 = bl2;
                                            bl3 = jsonArray3.isEmpty();
                                            if (bl) break block20;
                                            if (bl3) break block21;
                                            bl3 = true;
                                            break block20;
                                        }
                                        bl3 = false;
                                    }
                                    bl2 = bl4 | bl3;
                                    jsonArray.add((JsonElement)jsonArray3);
                                    if (!bl) continue;
                                }
                                break;
                            }
                            jsonObject4 = new JsonObject();
                            if (bl) break block22;
                            jsonObject4.add(b[2], (JsonElement)jsonArray2);
                            if (!bl2) break block23;
                            jsonObject4.add(b[12], (JsonElement)jsonArray);
                        }
                        String[] stringArray = b;
                        jsonObject4.addProperty(stringArray[11], (Number)iconAnimation.getSpeed());
                        jsonObject4.addProperty(stringArray[5], (Number)Float.valueOf(iconAnimation.getSize()));
                        jsonObject4.addProperty(stringArray[3], (Number)iconAnimation.getOffsetX());
                        jsonObject4.addProperty(stringArray[4], (Number)iconAnimation.getOffsetY());
                        jsonObject3.add(entry.getKey(), (JsonElement)jsonObject4);
                    }
                    if (!bl) continue;
                }
                jsonObject2 = jsonObject3;
                if (bl) break block24;
                if (jsonObject2.isEmpty()) break block19;
                jsonObject2 = jsonObject;
            }
            jsonObject2.add(b[1], (JsonElement)jsonObject3);
        }
    }

    
    public static void read(JsonObject var0) {
        block68: {
            block69: {
                var1_1 = IconStore.a;
                v0 = var0;
                if (var1_1) break block68;
                if (!v0.has(IconStore.b[6])) break block69;
                v0 = var0.getAsJsonObject(IconStore.b[6]);
                break block68;
            }
            v0 = new JsonObject();
        }
        var2_2 = v0;
        for (String var4_4 : IconStore.ICONS.keySet()) {
            block93: {
                block92: {
                    block90: {
                        block91: {
                            block89: {
                                block87: {
                                    block84: {
                                        block86: {
                                            block85: {
                                                block83: {
                                                    block79: {
                                                        block77: {
                                                            block78: {
                                                                block80: {
                                                                    block81: {
                                                                        block75: {
                                                                            block72: {
                                                                                block73: {
                                                                                    block74: {
                                                                                        block70: {
                                                                                            block71: {
                                                                                                v3 /* !! */  = var2_2;
                                                                                                if (var1_1) break block70;
                                                                                                if (v3 /* !! */ .has(var4_4)) break block71;
                                                                                                IconStore.reset(var4_4);
                                                                                                if (!var1_1) continue;
                                                                                            }
                                                                                            v3 /* !! */  = IconStore.ICONS.get(var4_4);
                                                                                        }
                                                                                        var5_5 = (IconAnimation)v3 /* !! */ ;
                                                                                        var6_6 = var2_2.get(var4_4);
                                                                                        v6 = var6_6;
                                                                                        if (var1_1) break block72;
                                                                                        if (!v6.isJsonPrimitive()) break block73;
                                                                                        var7_7 /* !! */  = (JsonObject)IconStore.decode(var6_6.getAsString(), var5_5.dim());
                                                                                        if (var1_1) break block74;
                                                                                        if (var7_7 /* !! */  == null) continue;
                                                                                        IconStore.applyFrames(var5_5, new int[][][]{(int[][])var7_7 /* !! */ });
                                                                                    }
                                                                                    if (!var1_1) continue;
                                                                                }
                                                                                v6 = var6_6;
                                                                            }
                                                                            if (var1_1) break block75;
                                                                            if (!v6.isJsonObject()) {
                                                                                continue;
                                                                            }
                                                                            v6 = var6_6;
                                                                        }
                                                                        var7_7 /* !! */  = v6.getAsJsonObject();
                                                                        v11 /* !! */  = var7_7 /* !! */ ;
                                                                        if (var1_1) break block77;
                                                                        if (!v11 /* !! */ .has(IconStore.b[0])) break block78;
                                                                        v11 /* !! */  = var7_7 /* !! */ ;
                                                                        v13 = IconStore.b[0];
                                                                        if (var1_1) break block79;
                                                                        if (!v11 /* !! */ .get(v13).isJsonArray()) break block78;
                                                                        var13_14 = IconStore.b;
                                                                        var8_8 = var7_7 /* !! */ .getAsJsonArray(var13_14[0]);
                                                                        var9_9 = new int[var8_8.size()][][];
                                                                        v16 = var8_8.size();
                                                                        if (var1_1) break block80;
                                                                        if (v16 <= 0) break block81;
                                                                        v16 = 1;
                                                                        break block80;
                                                                    }
                                                                    v16 = 0;
                                                                }
                                                                var10_11 = v16;
                                                                var11_12 = 0;
                                                                while (var11_12 < var8_8.size()) {
                                                                    block82: {
                                                                        if (var1_1) break block78;
                                                                        var12_13 = IconStore.decode(var8_8.get(var11_12).getAsString(), var5_5.dim());
                                                                        if (var1_1) break block82;
                                                                        if (var12_13 == null) {
                                                                        }
                                                                        var10_11 = 0;
                                                                        if (!var1_1) break;
lbl120:

                                                                        var9_9[var11_12] = var12_13;
                                                                        ++var11_12;
                                                                    }
                                                                    if (!var1_1) continue;
                                                                }
                                                                v20 = var10_11;
                                                                if (var1_1) break block83;
                                                                if (v20 == 0) break block78;
                                                                IconStore.applyFrames(var5_5, var9_9);
                                                            }
                                                            v11 /* !! */  = var7_7 /* !! */ ;
                                                        }
                                                        var13_14 = IconStore.b;
                                                        v13 = var13_14[13];
                                                    }
                                                    if (var1_1) break block84;
                                                    v20 = v11 /* !! */ .has(v13);
                                                }
                                                if (v20 == 0) break block85;
                                                v11 /* !! */  = var7_7 /* !! */ ;
                                                if (var1_1) break block86;
                                                if (!v11 /* !! */ .get(IconStore.b[13]).isJsonArray()) break block85;
                                                var13_14 = IconStore.b;
                                                var8_8 = var7_7 /* !! */ .getAsJsonArray(var13_14[13]);
                                                for (var9_10 = 0; var9_10 < var8_8.size(); ++var9_10) {
                                                    v26 = var9_10;
                                                    if (var1_1 || var1_1) break block87;
                                                    if (v26 >= var5_5.frameCount()) break;
                                                    if (!var8_8.get(var9_10).isJsonArray()) {
                                                        continue;
                                                    }
                                                    IconStore.applyColors(var5_5.frame(var9_10), var8_8.get(var9_10).getAsJsonArray());
                                                    if (!var1_1) continue;
                                                }
                                            }
                                            v11 /* !! */  = var7_7 /* !! */ ;
                                        }
                                        var13_14 = IconStore.b;
                                        v13 = var13_14[10];
                                    }
                                    if (var1_1) break block89;
                                    v26 = v11 /* !! */ .has(v13);
                                }
                                if (v26 != 0) {
                                    var5_5.setSpeed(var7_7 /* !! */ .get(IconStore.b[10]).getAsInt());
                                }
                                v11 /* !! */  = var7_7 /* !! */ ;
                                var13_14 = IconStore.b;
                                v13 = var13_14[7];
                            }
                            if (var1_1) break block90;
                            if (!v11 /* !! */ .has(v13)) break block91;
                            var5_5.setSize(var7_7 /* !! */ .get(IconStore.b[7]).getAsFloat());
                        }
                        v11 /* !! */  = var7_7 /* !! */ ;
                        if (var1_1) break block92;
                        v13 = IconStore.b[8];
                    }
                    if (!v11 /* !! */ .has(v13)) break block93;
                    v11 /* !! */  = var7_7 /* !! */ ;
                }
                if (v11 /* !! */ .has(IconStore.b[9])) {
                    var5_5.setOffset(var7_7 /* !! */ .get(IconStore.b[8]).getAsInt(), var7_7 /* !! */ .get(IconStore.b[9]).getAsInt());
                }
            }
            if (!var1_1) continue;
        }
    }

    private static void applyFrames(IconAnimation iconAnimation, int[][][] nArray) {
        IconAnimation iconAnimation2 = new IconAnimation(iconAnimation.getOffsetX(), iconAnimation.getOffsetY(), nArray);
        iconAnimation2.setSpeed(iconAnimation.getSpeed());
        iconAnimation2.setSize(iconAnimation.getSize());
        iconAnimation.copyFrom(iconAnimation2);
    }

    private static JsonArray colors(int[][] nArray) {
        JsonArray jsonArray = new JsonArray();
        boolean bl = a;
        int n = 0;
        while (n < nArray.length) {
            block10: {
                int n2 = 0;
                while (n2 < nArray[n].length) {
                    block11: {
                        block12: {
                            int n3;
                            block13: {
                                n3 = nArray[n][n2];
                                if (bl) break block10;
                                if (bl) break block11;
                                if (n3 == 0) break block12;
                                if (n3 != 1) break block13;
                                break block12;
                            }
                            jsonArray.add(n2 + "," + n + "," + Integer.toHexString(n3));
                        }
                        ++n2;
                    }
                    if (!bl) continue;
                }
                ++n;
            }
            if (!bl) continue;
        }
        return jsonArray;
    }

    
    private static void applyColors(int[][] nArray, JsonArray jsonArray) {
        boolean bl = a;
        if (nArray == null) {
            return;
        }
        for (JsonElement jsonElement : jsonArray) {
            try {
                String[] stringArray;
                block25: {
                    int[] nArray2;
                    int n;
                    int n2;
                    block23: {
                        int n3;
                        block24: {
                            block20: {
                                block22: {
                                    block21: {
                                        block19: {
                                            String[] stringArray2 = stringArray = jsonElement.getAsString().split(",");
                                            if (!bl) {
                                                if (stringArray2.length != 3 && !bl) continue;
                                                stringArray2 = stringArray;
                                            }
                                            n3 = Integer.parseInt(stringArray2[0]);
                                            n = n2 = Integer.parseInt(stringArray[1]);
                                            if (bl) break block19;
                                            if (n < 0) break block20;
                                            n = n2;
                                        }
                                        if (bl) break block21;
                                        if (n >= nArray.length) break block20;
                                        n = n3;
                                    }
                                    if (bl) break block22;
                                    if (n < 0) break block20;
                                    n = n3;
                                }
                                if (bl) break block23;
                                if (n < nArray[n2].length) break block24;
                            }
                            if (!bl) continue;
                        }
                        nArray2 = nArray[n2];
                        if (bl) break block25;
                        n = nArray2[n3];
                    }
                    if (n == 0 && !bl) continue;
                    nArray2 = nArray[n2];
                }
                nArray2[n3] = (int)Long.parseLong(stringArray[2], 16);
            }
            catch (Exception exception) {
            }
            if (!bl) continue;
        }
    }

    private static String encode(int[][] nArray) {
        StringBuilder stringBuilder;
        block11: {
            StringBuilder stringBuilder2 = new StringBuilder();
            int[][] nArray2 = nArray;
            int n = nArray2.length;
            int n2 = 0;
            boolean bl = a;
            block8: while (true) {
                int n32 = n2;
                block9: while (n32 < n) {
                    int[] nArray3;
                    block12: {
                        nArray3 = nArray2[n2];
                        stringBuilder = stringBuilder2;
                        if (bl) break block11;
                        if (bl) break block12;
                        if (stringBuilder.length() <= 0) break block12;
                        stringBuilder2.append('/');
                    }
                    for (int n32 : nArray3) {
                        int n4;
                        StringBuilder stringBuilder3;
                        block13: {
                            block14: {
                                if (bl) continue block9;
                                int n5 = n32;
                                stringBuilder3 = stringBuilder2;
                                n4 = n5;
                                if (bl) break block13;
                                if (n4 != 0) break block14;
                                n4 = 48;
                                break block13;
                            }
                            n4 = 49;
                        }
                        stringBuilder3.append((char)n4);
                        if (!bl) continue;
                    }
                    ++n2;
                    if (!bl) continue block8;
                }
                break;
            }
            stringBuilder = stringBuilder2;
        }
        return stringBuilder.toString();
    }

    private static int[][] decode(String string, int n) {
        int[][] nArray;
        block26: {
            int n2;
            String[] stringArray;
            boolean bl;
            block22: {
                block23: {
                    String string2;
                    block20: {
                        block21: {
                            bl = a;
                            string2 = string;
                            if (bl) break block20;
                            if (string2 != null) break block21;
                            return null;
                        }
                        string2 = string;
                    }
                    stringArray = string2.split("/");
                    n2 = stringArray.length;
                    if (bl) break block22;
                    if (n2 == n) break block23;
                    return null;
                }
                n2 = n;
            }
            int[][] nArray2 = new int[n2][n];
            for (int i = 0; i < n; ++i) {
                int n3;
                block24: {
                    int n4;
                    block25: {
                        n4 = stringArray[i].length();
                        if (bl) break block24;
                        if (n4 == n) break block25;
                        return null;
                    }
                    n4 = n3 = 0;
                }
                while (n3 < n) {
                    char c;
                    block27: {
                        block28: {
                            nArray = nArray2;
                            if (bl) break block26;
                            int[] nArray3 = nArray[i];
                            int n5 = n3;
                            c = stringArray[i].charAt(n3);
                            if (bl) break block27;
                            if (c != '1') break block28;
                            c = '\u0001';
                            break block27;
                        }
                        c = '\u0000';
                    }
                    nArray3[n5] = c;
                    ++n3;
                    if (!bl) continue;
                }
                if (!bl) continue;
            }
            nArray = nArray2;
        }
        return nArray;
    }

    
    static {
        var9 = new String[14];
        var7_1 = 0;
        var6_2 = ",\fG3;/\u000e)\fI--4@#\fo=12R\u0006,\fG3;/\u0007%\u0018@-;(y\u0007%\u0018@-;(x\u00049\u0017\\;\u000e)\fI--4@#\fo=12R\u00049\u0017\\;\u0007%\u0018@-;(y\u0007%\u0018@-;(x\u00059\u000eC;:\u00059\u000eC;:";
        var8_3 = ",\fG3;/\u000e)\fI--4@#\fo=12R\u0006,\fG3;/\u0007%\u0018@-;(y\u0007%\u0018@-;(x\u00049\u0017\\;\u000e)\fI--4@#\fo=12R\u00049\u0017\\;\u0007%\u0018@-;(y\u0007%\u0018@-;(x\u00059\u000eC;:\u00059\u000eC;:".length();
        var5_4 = 6;
        var4_5 = -1;
        block11: while (true) {
            v0 = ++var4_5;
            v1 = var6_2.substring(v0, v0 + var5_4);
            v2 = -1;
            block12: while (true) {
                var6_2 = ":\u001fO0*\u0005:\u001fO0*";
                var8_3 = ":\u001fO0*\u0005:\u001fO0*".length();
                var5_4 = 5;
                var4_5 = -1;
                while (true) {
                    v3 = ++var4_5;
                    v1 = var6_2.substring(v3, v3 + var5_4);
                    v2 = 0;
lbl21:

                    v4 = v1.toCharArray();
                    v5 = v4;
                    v6 = v4.length;
                    var10_6 = 0;
                    do {
                        v5 = v5;
                        v7 = var10_6;
                        v8 = v5[v7];
                        switch (var10_6 % 7) {
                            case 0: {
                                v9 = 74;
                                break;
                            }
                            case 1: {
                                v9 = 126;
                                break;
                            }
                            case 2: {
                                v9 = 38;
                                break;
                            }
                            case 3: {
                                v9 = 94;
                                break;
                            }
                            case 4: {
                                v9 = 94;
                                break;
                            }
                            case 5: {
                                v9 = 92;
                                break;
                            }
                            default: {
                                v9 = 33;
                            }
                        }
                        v5[v7] = (char)(v8 ^ v9);
                        ++var10_6;
lbl53:

                        v6 = v6;
                    } while (v6 > var10_6);
                    v10 = new String(v5);
                    switch (v2) {
                        default: {
                            var9[var7_1++] = v10.intern();
                            if ((var4_5 += var5_4) >= var8_3) continue block12;
                            var5_4 = var6_2.charAt(var4_5);
                            continue block11;
                        }
                        case 0: 
                    }
                    var9[var7_1++] = v10.intern();
                    if ((var4_5 += var5_4) >= var8_3) {
                        IconStore.b = var9;
                        var2_7 = 8301569646038010756L;
                        var0_8 = 7348592879243283339L ^ var2_7;
                        IconStore.SIZE = (int)var0_8;
                        IconStore.ICONS = new LinkedHashMap<String, IconAnimation>();
                        IconStore.DEFAULTS = new LinkedHashMap<String, IconAnimation>();
                        IconStore.HIDDEN = new HashSet<String>();
                        return;
                    }
                    var5_4 = var6_2.charAt(var4_5);
                }
                break;
            }
            break;
        }
    }
}
