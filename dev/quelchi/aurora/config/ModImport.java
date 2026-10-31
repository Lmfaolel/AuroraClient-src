package dev.quelchi.aurora.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import dev.quelchi.aurora.config.ConfigManager;
import dev.quelchi.aurora.module.Module;
import java.io.Reader;
import java.lang.invoke.LambdaMetafactory;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;

final class ModImport {
    private static final List<BiConsumer<Path, JsonObject>> MODS;
    private static final String[] a;
    private static final long b;

    private ModImport() {
    }

    static JsonObject read(Path path) {
        JsonObject jsonObject;
        block7: {
            boolean bl;
            block9: {
                block8: {
                    JsonObject jsonObject2 = new JsonObject();
                    boolean bl2 = ConfigManager.a;
                    for (BiConsumer<Path, JsonObject> biConsumer : MODS) {
                        try {
                            biConsumer.accept(path, jsonObject2);
                        }
                        catch (Exception exception) {
                        }
                        if (!bl2) continue;
                    }
                    jsonObject = jsonObject2;
                    if (!Module.a) break block7;
                    if (!bl2) break block8;
                    bl = false;
                    break block9;
                }
                bl = true;
            }
            ConfigManager.a = bl;
        }
        return jsonObject;
    }

    private static void shieldStatus(Path path, JsonObject jsonObject) {
        String[] stringArray = a;
        JsonElement jsonElement = ModImport.json(path, stringArray[74]);
        if (jsonElement == null) {
            return;
        }
        stringArray = a;
        JsonObject jsonObject2 = ModImport.module(jsonObject, stringArray[63], ModImport.on(ModImport.walksy(jsonElement, stringArray[28])));
        ModImport.put(jsonObject2, stringArray[86], ModImport.walksy(jsonElement, stringArray[10]));
        ModImport.put(jsonObject2, stringArray[5], ModImport.color(ModImport.walksy(jsonElement, stringArray[128])));
        ModImport.put(jsonObject2, stringArray[111], ModImport.walksy(jsonElement, stringArray[31]));
        ModImport.put(jsonObject2, stringArray[103], ModImport.color(ModImport.walksy(jsonElement, stringArray[114])));
        ModImport.put(jsonObject2, stringArray[81], ModImport.walksy(jsonElement, stringArray[25]));
        ModImport.put(jsonObject2, stringArray[82], ModImport.color(ModImport.walksy(jsonElement, stringArray[84])));
        ModImport.put(jsonObject2, stringArray[13], ModImport.walksy(jsonElement, stringArray[95]));
    }

    private static void crosshairAddons(Path path, JsonObject jsonObject) {
        String[] stringArray = a;
        JsonElement jsonElement = ModImport.json(path, stringArray[83]);
        if (jsonElement == null) {
            return;
        }
        stringArray = a;
        JsonObject jsonObject2 = ModImport.module(jsonObject, stringArray[93], ModImport.on(ModImport.walksy(jsonElement, stringArray[0])));
        ModImport.put(jsonObject2, stringArray[90], ModImport.walksy(jsonElement, stringArray[36]));
        ModImport.put(jsonObject2, stringArray[2], ModImport.walksy(jsonElement, stringArray[23]));
        ModImport.put(jsonObject2, stringArray[40], ModImport.walksy(jsonElement, stringArray[29]));
        ModImport.put(jsonObject2, stringArray[106], ModImport.walksy(jsonElement, stringArray[70]));
        ModImport.put(jsonObject2, stringArray[19], ModImport.walksy(jsonElement, stringArray[66]));
        ModImport.put(jsonObject2, stringArray[109], ModImport.walksy(jsonElement, stringArray[64]));
        ModImport.put(jsonObject2, stringArray[32], ModImport.walksy(jsonElement, stringArray[60]));
        ModImport.put(jsonObject2, stringArray[100], ModImport.walksy(jsonElement, stringArray[21]));
        ModImport.put(jsonObject2, stringArray[61], ModImport.walksy(jsonElement, stringArray[50]));
        ModImport.put(jsonObject2, stringArray[96], ModImport.ticksToMs(ModImport.walksy(jsonElement, stringArray[27])));
        ModImport.put(jsonObject2, stringArray[17], ModImport.walksy(jsonElement, stringArray[33]));
        ModImport.put(jsonObject2, stringArray[122], ModImport.walksy(jsonElement, stringArray[24]));
        ModImport.put(jsonObject2, stringArray[116], ModImport.walksy(jsonElement, stringArray[97]));
        ModImport.put(jsonObject2, stringArray[43], ModImport.walksy(jsonElement, stringArray[88]));
        ModImport.put(jsonObject2, stringArray[125], ModImport.ticksToMs(ModImport.walksy(jsonElement, stringArray[87])));
    }

    
    private static void tierTagger(Path var0, JsonObject var1_1) {
        block38: {
            block37: {
                block35: {
                    block36: {
                        block32: {
                            block34: {
                                block33: {
                                    block29: {
                                        block39: {
                                            var9_2 = ModImport.a;
                                            var3_3 = var0.resolve(var9_2[38]);
                                            var2_4 = ConfigManager.a;
                                            v0 = var3_3;
                                            if (var2_4) // goto
                                            if (Files.isRegularFile(v0, new LinkOption[0])) break block29;
                                            break block39;
                                            catch (Exception v1) {
                                                throw v1;
                                            }
                                        }
                                        return;
                                    }
                                    try {
                                        v0 = var3_3;
lbl19:

                                        var4_5 = Files.readAllLines(v0);
                                    }
                                    catch (Exception var5_6) {
                                        return;
                                    }
                                    var5_7 = new JsonObject();
                                    for (String var7_9 : var4_5) {
                                        block30: {
                                            v3 = var7_9;
                                            if (var2_4) break block30;
                                            if (v3.startsWith("[")) {
                                                break;
                                            }
                                            v3 = var7_9;
                                        }
                                        var8_10 = v3.indexOf(61);
                                        if (var8_10 > 0) {
                                            var5_7.addProperty(var7_9.substring(0, var8_10).trim(), var7_9.substring(var8_10 + 1).trim().replace("\"", ""));
                                        }
                                        if (!var2_4) continue;
                                    }
                                    v7 = var1_1;
                                    v8 = ModImport.a[124];
                                    v9 = var5_7.has(ModImport.a[16]);
                                    if (var2_4) break block32;
                                    if (!v9) break block33;
                                    v9 = ModImport.a[35].equals(var5_7.get(ModImport.a[16]).getAsString());
                                    if (var2_4) break block32;
                                    if (!v9) break block34;
                                }
                                v9 = true;
                                break block32;
                            }
                            v9 = false;
                        }
                        var6_8 = ModImport.module(v7, v8, v9);
                        v13 = var5_7;
                        if (var2_4) break block35;
                        if (!v13.has(ModImport.a[3])) break block36;
                        var6_8.addProperty(ModImport.a[7], Boolean.valueOf(ModImport.a[98].equals(var5_7.get(ModImport.a[115]).getAsString())));
                    }
                    v13 = var5_7;
                }
                if (var2_4) break block37;
                if (!v13.has(ModImport.a[65])) break block38;
                v13 = new JsonObject();
            }
            var7_9 = v13;
            var9_2 = ModImport.a;
            var7_9.add(var9_2[102], var5_7.get(var9_2[73]));
            var1_1.getAsJsonObject(var9_2[56]).add(var9_2[120], (JsonElement)var7_9);
        }
    }

    private static void blockOverlay(Path path, JsonObject jsonObject) {
        boolean bl;
        String string;
        JsonObject jsonObject2;
        JsonObject jsonObject3;
        String[] stringArray;
        block6: {
            block7: {
                JsonElement jsonElement;
                boolean bl2;
                block4: {
                    JsonElement jsonElement2;
                    block5: {
                        stringArray = a;
                        jsonElement2 = ModImport.json(path, stringArray[78]);
                        bl2 = ConfigManager.a;
                        jsonElement = jsonElement2;
                        if (bl2) break block4;
                        if (jsonElement != null) break block5;
                        return;
                    }
                    jsonElement = jsonElement2;
                }
                jsonObject3 = jsonElement.getAsJsonObject();
                jsonObject2 = jsonObject;
                string = a[42];
                bl = ModImport.on(jsonObject3.get(a[44]));
                if (bl2) break block6;
                if (bl) break block7;
                bl = true;
                break block6;
            }
            bl = false;
        }
        JsonObject jsonObject4 = ModImport.module(jsonObject2, string, bl);
        stringArray = a;
        ModImport.put(jsonObject4, stringArray[75], ModImport.not(jsonObject3.get(stringArray[119])));
        ModImport.put(jsonObject4, stringArray[77], ModImport.argb(jsonObject3.get(stringArray[4]), jsonObject3.get(stringArray[57])));
        ModImport.put(jsonObject4, stringArray[9], jsonObject3.get(stringArray[117]));
        ModImport.put(jsonObject4, stringArray[26], jsonObject3.get(stringArray[30]));
        ModImport.put(jsonObject4, stringArray[72], jsonObject3.get(stringArray[49]));
        ModImport.put(jsonObject4, stringArray[112], ModImport.not(jsonObject3.get(stringArray[80])));
        ModImport.put(jsonObject4, stringArray[1], ModImport.argb(jsonObject3.get(stringArray[39]), jsonObject3.get(stringArray[123])));
        ModImport.put(jsonObject4, stringArray[68], jsonObject3.get(stringArray[99]));
        ModImport.put(jsonObject4, stringArray[94], jsonObject3.get(stringArray[52]));
        ModImport.put(jsonObject4, stringArray[105], jsonObject3.get(stringArray[113]));
    }

    private static void totemTweaks(Path path, JsonObject jsonObject) {
        JsonElement jsonElement;
        String[] stringArray;
        block2: {
            JsonElement jsonElement2;
            block3: {
                stringArray = a;
                jsonElement2 = ModImport.json(path, stringArray[14]);
                boolean bl = ConfigManager.a;
                jsonElement = jsonElement2;
                if (bl) break block2;
                if (jsonElement != null) break block3;
                return;
            }
            jsonElement = jsonElement2;
        }
        JsonObject jsonObject2 = jsonElement.getAsJsonObject();
        stringArray = a;
        JsonObject jsonObject3 = ModImport.module(jsonObject, stringArray[85], true);
        ModImport.put(jsonObject3, stringArray[108], jsonObject2.get(stringArray[11]));
        ModImport.put(jsonObject3, stringArray[55], jsonObject2.get(stringArray[89]));
        ModImport.put(jsonObject3, stringArray[118], jsonObject2.get(stringArray[12]));
        ModImport.put(jsonObject3, stringArray[20], jsonObject2.get(stringArray[37]));
    }

    private static void optimizers(Path path, JsonObject jsonObject) {
        JsonElement jsonElement;
        JsonObject jsonObject2;
        JsonElement jsonElement2;
        block53: {
            block54: {
                boolean bl;
                JsonElement jsonElement3;
                block51: {
                    block52: {
                        JsonElement jsonElement4;
                        block46: {
                            block47: {
                                boolean bl2;
                                String string;
                                JsonObject jsonObject3;
                                block50: {
                                    block48: {
                                        JsonElement jsonElement5;
                                        block49: {
                                            boolean bl3;
                                            block44: {
                                                block45: {
                                                    boolean bl4;
                                                    block43: {
                                                        block42: {
                                                            JsonElement jsonElement6;
                                                            block41: {
                                                                String[] stringArray;
                                                                block39: {
                                                                    JsonElement jsonElement7;
                                                                    block40: {
                                                                        block38: {
                                                                            stringArray = a;
                                                                            jsonElement4 = ModImport.json(path, stringArray[76]);
                                                                            jsonElement3 = ModImport.json(path, stringArray[79]);
                                                                            jsonElement2 = ModImport.json(path, stringArray[69]);
                                                                            bl = ConfigManager.a;
                                                                            jsonElement7 = jsonElement4;
                                                                            if (bl) break block38;
                                                                            if (jsonElement7 != null) break block39;
                                                                            jsonElement7 = jsonElement3;
                                                                        }
                                                                        if (bl) break block40;
                                                                        if (jsonElement7 != null) break block39;
                                                                        jsonElement7 = jsonElement2;
                                                                    }
                                                                    if (jsonElement7 == null) {
                                                                        return;
                                                                    }
                                                                }
                                                                stringArray = a;
                                                                jsonObject2 = ModImport.module(jsonObject, stringArray[53], true);
                                                                jsonElement6 = jsonElement4;
                                                                if (bl) break block41;
                                                                if (jsonElement6 == null) break block42;
                                                                jsonElement6 = ModImport.walksy(jsonElement4, a[0]);
                                                            }
                                                            bl4 = ModImport.on(jsonElement6);
                                                            if (bl) break block43;
                                                            if (!bl4) break block42;
                                                            bl4 = true;
                                                            break block43;
                                                        }
                                                        bl4 = false;
                                                    }
                                                    bl3 = bl4;
                                                    if (bl) break block44;
                                                    if (jsonElement4 != null) break block45;
                                                    jsonElement = jsonElement3;
                                                    if (bl) break block46;
                                                    if (jsonElement == null) break block47;
                                                }
                                                jsonObject2.addProperty(a[41], Boolean.valueOf(bl3));
                                                jsonObject2.addProperty(a[8], Boolean.valueOf(false));
                                            }
                                            jsonObject3 = jsonObject2;
                                            string = a[6];
                                            if (bl3) break block48;
                                            jsonElement5 = jsonElement3;
                                            if (bl) break block49;
                                            if (jsonElement5 == null) break block48;
                                            jsonElement5 = jsonElement3.getAsJsonObject().get(a[91]);
                                        }
                                        bl2 = ModImport.on(jsonElement5);
                                        if (bl) break block50;
                                        if (!bl2) break block48;
                                        bl2 = true;
                                        break block50;
                                    }
                                    bl2 = false;
                                }
                                jsonObject3.addProperty(string, Boolean.valueOf(bl2));
                            }
                            jsonElement = jsonElement4;
                        }
                        if (bl) break block51;
                        if (jsonElement == null) break block52;
                        ModImport.put(jsonObject2, a[54], ModImport.walksy(jsonElement4, a[104]));
                    }
                    jsonElement = jsonElement3;
                }
                if (bl) break block53;
                if (jsonElement == null) break block54;
                ModImport.put(jsonObject2, a[107], jsonElement3.getAsJsonObject().get(a[92]));
            }
            jsonElement = jsonElement2;
        }
        if (jsonElement != null) {
            ModImport.put(jsonObject2, a[59], ModImport.walksy(jsonElement2, a[0]));
        }
    }

    private static void appleSkin(Path path, JsonObject jsonObject) {
        block4: {
            JsonElement jsonElement;
            String[] stringArray;
            block5: {
                boolean bl = ConfigManager.a;
                stringArray = a;
                jsonElement = ModImport.json(path, stringArray[101]);
                if (bl) break block4;
                if (jsonElement != null) break block5;
                return;
            }
            stringArray = a;
            ModImport.module(jsonObject, stringArray[58], ModImport.on(jsonElement.getAsJsonObject().get(stringArray[127])));
        }
    }

    private static void betterScreens(Path path, JsonObject jsonObject) {
        JsonElement jsonElement;
        String[] stringArray;
        block2: {
            JsonElement jsonElement2;
            block3: {
                stringArray = a;
                jsonElement2 = ModImport.json(path, stringArray[47]);
                boolean bl = ConfigManager.a;
                jsonElement = jsonElement2;
                if (bl) break block2;
                if (jsonElement != null) break block3;
                return;
            }
            jsonElement = jsonElement2;
        }
        JsonObject jsonObject2 = jsonElement.getAsJsonObject();
        stringArray = a;
        JsonObject jsonObject3 = ModImport.module(jsonObject, stringArray[46], true);
        jsonObject3.addProperty(stringArray[121], Boolean.valueOf(true));
        ModImport.put(jsonObject3, stringArray[48], jsonObject2.get(stringArray[51]));
        ModImport.put(jsonObject3, stringArray[15], ModImport.scaled(jsonObject2.get(stringArray[62]), 0.01));
        ModImport.put(jsonObject3, stringArray[34], ModImport.scaled(jsonObject2.get(stringArray[110]), 0.01));
    }

    
    private static JsonElement json(Path var0, String var1_1) {
        block25: {
            block24: {
                block20: {
                    block27: {
                        var3_2 = var0.resolve(var1_1);
                        var2_3 = ConfigManager.a;
                        v0 = var3_2;
                        if (var2_3) // goto
                        if (Files.isRegularFile(v0, new LinkOption[0])) break block20;
                        break block27;
                        catch (Throwable v1) {
                            throw v1;
                        }
                    }
                    return null;
                }
                v0 = var3_2;
lbl18:

                var4_4 = Files.newBufferedReader(v0);
                try {
                    block23: {
                        block22: {
                            block21: {
                                var5_6 = JsonParser.parseReader((Reader)var4_4);
                                v3 = var5_6;
                                if (var2_3) break block21;
                                if (v3 != null) {
                                }
                                v3 = var5_6;
                            }
                            if (!v3.isJsonNull()) break block22;
lbl33:

                            v5 = null;
                            break block23;
                        }
                        v5 = var5_6;
                    }
                    var6_8 = v5;
                }
                catch (Throwable var5_7) {
                    try {
                        block26: {
                            if (var2_3 || var4_4 == null) break block26;
                            try {
                                var4_4.close();
                            }
                            catch (Throwable var6_9) {
                                var5_7.addSuppressed(var6_9);
                            }
                        }
                        throw var5_7;
                    }
                    catch (Exception var4_5) {
                        return null;
                    }
                }
                v8 = var4_4;
                if (var2_3) break block24;
                if (v8 == null) break block25;
                v8 = var4_4;
            }
            v8.close();
        }
        return var6_8;
    }

    private static JsonElement walksy(JsonElement jsonElement, String string) {
        Iterator iterator = jsonElement.getAsJsonArray().iterator();
        boolean bl = ConfigManager.a;
        block4: while (iterator.hasNext()) {
            JsonArray jsonArray;
            String[] stringArray;
            block9: {
                JsonElement jsonElement2 = (JsonElement)iterator.next();
                stringArray = a;
                JsonArray jsonArray2 = jsonElement2.getAsJsonObject().getAsJsonArray(stringArray[18]);
                jsonArray = jsonArray2;
                if (bl) break block9;
                if (jsonArray == null) {
                    continue;
                }
                jsonArray = jsonArray2;
            }
            block5: for (JsonElement jsonElement3 : jsonArray) {
                if (bl) continue block4;
                stringArray = a;
                for (JsonElement jsonElement4 : jsonElement3.getAsJsonObject().getAsJsonArray(stringArray[22])) {
                    JsonObject jsonObject = jsonElement4.getAsJsonObject();
                    if (bl) continue block5;
                    if (string.equals(jsonObject.get(a[45]).getAsString())) {
                        return jsonObject.get(a[126]);
                    }
                    if (!bl) continue;
                }
                if (!bl) continue;
            }
            if (!bl) continue;
        }
        return null;
    }

    private static JsonObject module(JsonObject jsonObject, String string, boolean bl) {
        JsonObject jsonObject2 = new JsonObject();
        String[] stringArray = a;
        jsonObject2.addProperty(stringArray[16], Boolean.valueOf(bl));
        JsonObject jsonObject3 = new JsonObject();
        jsonObject2.add(stringArray[71], (JsonElement)jsonObject3);
        jsonObject.add(string, (JsonElement)jsonObject2);
        return jsonObject3;
    }

    private static void put(JsonObject jsonObject, String string, JsonElement jsonElement) {
        block6: {
            JsonElement jsonElement2;
            block5: {
                boolean bl = ConfigManager.a;
                jsonElement2 = jsonElement;
                if (bl) break block5;
                if (jsonElement2 == null) break block6;
                jsonElement2 = jsonElement;
            }
            if (!jsonElement2.isJsonNull()) {
                jsonObject.add(string, jsonElement);
            }
        }
    }

    private static boolean on(JsonElement jsonElement) {
        boolean bl;
        block11: {
            block9: {
                JsonElement jsonElement2;
                boolean bl2;
                block10: {
                    block8: {
                        bl2 = ConfigManager.a;
                        jsonElement2 = jsonElement;
                        if (bl2) break block8;
                        if (jsonElement2 == null) break block9;
                        jsonElement2 = jsonElement;
                    }
                    if (bl2) break block10;
                    if (!jsonElement2.isJsonPrimitive()) break block9;
                    jsonElement2 = jsonElement;
                }
                bl = jsonElement2.getAsBoolean();
                if (bl2) break block11;
                if (!bl) break block9;
                bl = true;
                break block11;
            }
            bl = false;
        }
        return bl;
    }

    private static JsonElement not(JsonElement jsonElement) {
        JsonPrimitive jsonPrimitive;
        block5: {
            JsonPrimitive jsonPrimitive2;
            block4: {
                if (jsonElement != null) break block4;
                jsonPrimitive = null;
                break block5;
            }
            JsonPrimitive jsonPrimitive3 = jsonPrimitive2;
            jsonPrimitive = jsonPrimitive2;
            boolean bl = !ModImport.on(jsonElement);
            jsonPrimitive3(Boolean.valueOf(bl));
        }
        return jsonPrimitive;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static JsonElement color(JsonElement jsonElement) {
        JsonElement jsonElement2;
        block7: {
            block8: {
                boolean bl;
                block6: {
                    bl = ConfigManager.a;
                    jsonElement2 = jsonElement;
                    if (bl) break block6;
                    if (jsonElement2 == null) return null;
                    jsonElement2 = jsonElement;
                }
                if (bl) break block7;
                if (jsonElement2.isJsonObject()) break block8;
                return null;
            }
            jsonElement2 = jsonElement;
        }
        JsonElement jsonElement3 = jsonElement2.getAsJsonObject().get(a[67]);
        return jsonElement3;
    }

    private static JsonElement argb(JsonElement jsonElement, JsonElement jsonElement2) {
        JsonElement jsonElement3;
        block6: {
            block7: {
                boolean bl = ConfigManager.a;
                jsonElement3 = jsonElement;
                if (bl) break block6;
                if (jsonElement3 != null) break block7;
                return null;
            }
            jsonElement3 = jsonElement2;
        }
        int n = jsonElement3 == null ? 255 : Math.max(0, Math.min(255, Math.round(jsonElement2.getAsFloat())));
        int n2 = n;
        return new JsonPrimitive((Number)(n2 << 24 | jsonElement.getAsInt() & (int)b));
    }

    private static JsonElement ticksToMs(JsonElement jsonElement) {
        JsonPrimitive jsonPrimitive = jsonElement == null ? null : new JsonPrimitive((Number)(jsonElement.getAsInt() * 50));
        return jsonPrimitive;
    }

    private static JsonElement scaled(JsonElement jsonElement, double d) {
        JsonPrimitive jsonPrimitive = jsonElement == null ? null : new JsonPrimitive((Number)(jsonElement.getAsDouble() * d));
        return jsonPrimitive;
    }

    
    static {
        block21: {
            block20: {
                block19: {
                    var7 = new String[129];
                    var5_1 = 0;
                    var4_2 = "Z\u001aAF\u00147iu\u0019@\u0002\u000bQ\u0014F\u0003\"vKx\u0019J\u0014\u0011R\u001bS\u000f#6fz\u0010K\u0012q;dr\u001bA\td\u001dJ\u0011\u0018:gy\u0006\nd\u001aI\u000f5\u001ag{\u001aW\u000bE\u0010D\u0002(vKx\u0019J\u0014\u0018T\u0000Q\u000336\u007f7\u0014K\u000596z7\u001aU\u001284am\u0010W\rP\u0014H\u0003<6lrUL\u0005>7\u0015_\u0010W\tq8ft\u001dJ\u0014q6xc\u001cH\u000f+<z\n[\u001cK\u0003q.as\u0001M\u001bT\u0000V\u0012>4(R\u001bD\u0004=<l7&M\u000f45l76J\n>+\tc\u001aQ\u0003<\nam\u0010\u000ev\u001bL\u000b0-ax\u001bv\u00164<l\u000fX\u0002KF\"1ar\u0019AF>7dn\u0010c\u001aQ\u0003<-\u007fr\u0014N\u0015\u007f3{x\u001b\u0016^\u001bS\u0003?-ge\f\u0005\u0012#8fd\u0005D\u001447kn\u0007r\u001bD\u0004=<l\u0010D\u001dL\u0003==(~\u001bA\u000f28|x\u0007\u0006p\u0007J\u0013!*\nV\u001b\\F47|~\u0001\\\u0017S\u001cV\u000735m7\u0010T\u00138)(v\u001bL\u000b0-ax\u001b\u001aC\u001aB\u0001=<(_\u001cQ\u000b0+cr\u0007\u0005)?yIc\u0001D\u0005:\u0007x\u0005Q\u000f>7{\u0011R\u001bS\u000f#6fz\u0010K\u0012q\u001bdr\u001bA\u001dD\u001dL\u0003==(^\u001bA\u000f28|x\u0007\u0005 0:|x\u0007\u0005\"45in\u001cT\u0000V\u0012>4(S\u001cV\u000735msUv\u000e8<dsUf\t=6z\u000eX\u0000Q\n87m86M\u0014>4i\u0012_\u001cQ\u000b0+cr\u0007\u0005\"$+ic\u001cJ\b\u000bZ\u001aAF\u00147iu\u0019@\u0002\u0014D\u001dJ\u0011q\u0010f7!M\u000f#=(G\u0010W\u0015>7\u0006t\u001dW\t<8\u0019T\u0000V\u0012>4(B\u0006L\b6y[\u007f\u001c@\n5yKx\u0019J\u0014\t_\u001cQ\u000b0+cr\u0007\u001eD\u001dL\u0003==(^\u001bA\u000f28|x\u0007\u0005'5=gyU`\b0;dr\u0011\u001cT\u001aK\u001200fr\u0007\u0005\u00040:cp\u0007J\u0013?=(x\u0005D\u00058-q\u0004c\u0007P\u0003\u001cA\u0014K\u000f=5i74Q\u00120:c7<K\u00028:ic\u001aWF\u00168x\u0015s\u001cV\u000735mR\u0004P\u000f!\u0018f~\u0018D\u001286f\u000fc\u001c@\u0014%8op\u0010WH%6e{\u0011d\u001aI\u000f5\u001ag{\u001aW)'<z{\u0014\\\u0014D\u001dJ\u0011q0f7\u0001M\u000f#=(g\u0010W\u0015>7\u0017@\u0014I\r\" (v\u001bF\u000e>+(x\u0005Q\u000f<0rr\u0007\rU\u0019J\u0005:yGa\u0010W\n0 !D\u001dL\u0003==(u\u0007@\u0007:vX{\u0014\\F>7krY\u0005\u00129<f7\u001dL\u00024\ns\u001cV\u000735mZ\u001aA\u0004y\u0014H\u0003\nP lF\u0005.mv\u001eV\u0012u\u0010Q\u00124+{t\u0007@\u0003?*&}\u0006J\b\u000f^\u001bS\u0003?-ge\f\u0005\u001528dr\u0005d\u0005@\u00035\u001f_\u001cQ\u000b0+cr\u0007\u00055%6x7:KF\u00107az\u0014Q\u000f>7(R\u001bA\u000et\u001aK\u001200fr\u0007v\u000505m\fd\u0005@\u00035\u0016~r\u0007I\u0007(\nX\u0005Q\u000f<0rr\u0007V\u0015T\u0019L\u0003?-(d\u001cA\u0003q<pg\u0019J\u001586f\bG\u001aUF\"0rr\nC\u001c@\u0014\u00058op\u0010W\u0005v\u0019U\u000e0\u0012D\u0014Q\u0013#8|~\u001aKF\u001e/me\u0019D\u001f\u0014T\u001aK\u0015$4iu\u0019@F>)|~\u0018L\u001c4+\u0017_\u001cQ\u000b0+cr\u0007\u0005'5=gyU`\b0;dr\u0011\u001e_\u001cQ\u000b0+cr\u0007\n6=8q7\u001aK\u00054u(c\u001d@\bq1as\u0010\u0017t\u001aK\u001200fr\u0007q\u0003)-}e\u0010j\u00160:ac\f\rD\u001dL\u0003==(C\u0002@\u0007:*\u0014R\u0019\\\u0012#8(V\u0011A\t?yMy\u0014G\n4=\bp\u0014H\u0003\u001c6lr\"R\u001bQ\u000f% (^\u001bA\u000f28|x\u0007\u0005596\u007f74I\nq\u001cfc\u001cQ\u000f4*\u0005a\u0014I\u00134\fQ\u0014F\u0003\"vK\u007f\u0007J\u000b0\u0018t\u001aK\u0015$4iu\u0019@\t!-az\u001c_\u0003#wbd\u001aK\u001eR\u001bQ\u000f% (^\u001bA\u000f28|x\u0007\u0005'5=gyU`\b0;dr\u0011\bd\u0010Q\u001287od\fT\u001dW\t<8'D\u0005@\u00035\bp\u0014H\u0003\u001c6lr\u0011d\u001dL\u0003=={c\u0014Q\u0013\"wbd\u001aK\u0007X\u0000Q\n87m\u0014v\u001bF\u000e>+gg\u0001L\u000b8#me[O\u0015>7\rX\u0000Q\n87m86J\n>+\u0017d\u001cH\u0016=<j{\u001aF\r>/me\u0019D\u001f\u007f3{x\u001b\u0018t\u0019L\u0003?-Wd\u001cA\u0003\u000e8ft\u001dJ\u0014\"wbd\u001aK\u000es\u001cV\u000735mX\u0003@\u0014=8q\bS\u001cV\u000735ms\u000eS\u001cV\u000735msZf\t=6z\u0014t\u0007J\u0015\"1i~\u0007D\u000256fd[O\u0015>7\u000eS\u001cV\u000735msUf\t=6z\fC\u001aQ\u0003<y\\`\u0010D\r\"\u0005E\u0010D\u0002(\u0015D\u001dL\u0003==(U\u0007@\u0007:yLb\u0007D\u001286f\"D\u001dL\u0003==(U\u0007@\u0007:y[c\u001aUF\u001e7(V\u001bL\u000b0-ax\u001b\u0005#?=\u0007g\u001aU58#m\u0014V\u0001Q\u000722(~\u001bA\u000f28|x\u0007\u0005\u00010)\u0007r\u001bD\u0004=<l\re\u0010H\t'<Gb\u0001I\u000f?<\u0010T\u0007J\u0015\"1i~\u0007\u0005'5=gy\u0006\u000eT\u001dW\t<8'D\u0005@\u00035z:\u000fD\u0010I\u0000q\n|v\u0001@F\u001e7dn\u000e_\u001cQ\u000b0+cr\u0007\u0005\u001284m\u001aD\u001dL\u0003==(U\u0007@\u0007:yIs\u0011J\bq\u001cfv\u0017I\u00035\u0004c\u0007P\u0003\rt\u001dW\t<8Ga\u0010W\n0 \u0012V\u0019V\tq6f7\u0018@\n4<(\u007f\u001cQ\u0015\u000fv\u0005U\n4*c~\u001b\u000b\f\"6f\"\u0004z\u001aA\u0003\u000eU\u0019J\u0005:0fpZf\t=6z\u0016T\u0019L\u0003?-(D\u001cA\u00035yMo\u0005I\t\"0gy\rC\u001dW\t$>`7\u0002D\n=*\u0010R\u001bQ\u000f% (~\u001bA\u000f28|x\u0007\u000eE\u0010H\t'<(x\u0000Q\n87m\nC\u001aQ\u0003<y{~\u000f@\u0010R\u0019\\\u0012#8(~\u001bA\u000f28|x\u0007\u001at\u001aK\u001200fr\u0007g\u000722oe\u001aP\b5\u0016xv\u0016L\u0012(\bU\u0019J\u0005:0fp\u0005Q\u0014F\u0003\"\fs\u001cV\u000735mS\u0010U\u00129\u000bB\u0006L\b6yKx\u0019J\u0014\td\u001dJ\u0011\u0018:gy\u0006\fD\u001dL\u0003==(u\u0007@\u0007:\t{\u001cK\u0003\u00060lc\u001d\fG\u001aUF5,zv\u0001L\t?\u000es\u001cV\u000735mX\u0000Q\n87m\u0005r\rQ\u00140\u0010^\u001bS\u0003?-ge\f\u00052&<i|\u0006\u0019@\u0014L\u0012q?geUQ\u000e4yj{\u001aF\rq-g7\u0014W\u000b\fv\u0019U\u000e0\u0016~r\u0007I\u0007(\nC\u001c@\u0014\u00058op\u0010W\u0011D\u001dL\u0003==(u\u0007@\u0007:y|~\u0018@\u0005a\u0014I\u00134";
                    var6_3 = "Z\u001aAF\u00147iu\u0019@\u0002\u000bQ\u0014F\u0003\"vKx\u0019J\u0014\u0011R\u001bS\u000f#6fz\u0010K\u0012q;dr\u001bA\td\u001dJ\u0011\u0018:gy\u0006\nd\u001aI\u000f5\u001ag{\u001aW\u000bE\u0010D\u0002(vKx\u0019J\u0014\u0018T\u0000Q\u000336\u007f7\u0014K\u000596z7\u001aU\u001284am\u0010W\rP\u0014H\u0003<6lrUL\u0005>7\u0015_\u0010W\tq8ft\u001dJ\u0014q6xc\u001cH\u000f+<z\n[\u001cK\u0003q.as\u0001M\u001bT\u0000V\u0012>4(R\u001bD\u0004=<l7&M\u000f45l76J\n>+\tc\u001aQ\u0003<\nam\u0010\u000ev\u001bL\u000b0-ax\u001bv\u00164<l\u000fX\u0002KF\"1ar\u0019AF>7dn\u0010c\u001aQ\u0003<-\u007fr\u0014N\u0015\u007f3{x\u001b\u0016^\u001bS\u0003?-ge\f\u0005\u0012#8fd\u0005D\u001447kn\u0007r\u001bD\u0004=<l\u0010D\u001dL\u0003==(~\u001bA\u000f28|x\u0007\u0006p\u0007J\u0013!*\nV\u001b\\F47|~\u0001\\\u0017S\u001cV\u000735m7\u0010T\u00138)(v\u001bL\u000b0-ax\u001b\u001aC\u001aB\u0001=<(_\u001cQ\u000b0+cr\u0007\u0005)?yIc\u0001D\u0005:\u0007x\u0005Q\u000f>7{\u0011R\u001bS\u000f#6fz\u0010K\u0012q\u001bdr\u001bA\u001dD\u001dL\u0003==(^\u001bA\u000f28|x\u0007\u0005 0:|x\u0007\u0005\"45in\u001cT\u0000V\u0012>4(S\u001cV\u000735msUv\u000e8<dsUf\t=6z\u000eX\u0000Q\n87m86M\u0014>4i\u0012_\u001cQ\u000b0+cr\u0007\u0005\"$+ic\u001cJ\b\u000bZ\u001aAF\u00147iu\u0019@\u0002\u0014D\u001dJ\u0011q\u0010f7!M\u000f#=(G\u0010W\u0015>7\u0006t\u001dW\t<8\u0019T\u0000V\u0012>4(B\u0006L\b6y[\u007f\u001c@\n5yKx\u0019J\u0014\t_\u001cQ\u000b0+cr\u0007\u001eD\u001dL\u0003==(^\u001bA\u000f28|x\u0007\u0005'5=gyU`\b0;dr\u0011\u001cT\u001aK\u001200fr\u0007\u0005\u00040:cp\u0007J\u0013?=(x\u0005D\u00058-q\u0004c\u0007P\u0003\u001cA\u0014K\u000f=5i74Q\u00120:c7<K\u00028:ic\u001aWF\u00168x\u0015s\u001cV\u000735mR\u0004P\u000f!\u0018f~\u0018D\u001286f\u000fc\u001c@\u0014%8op\u0010WH%6e{\u0011d\u001aI\u000f5\u001ag{\u001aW)'<z{\u0014\\\u0014D\u001dJ\u0011q0f7\u0001M\u000f#=(g\u0010W\u0015>7\u0017@\u0014I\r\" (v\u001bF\u000e>+(x\u0005Q\u000f<0rr\u0007\rU\u0019J\u0005:yGa\u0010W\n0 !D\u001dL\u0003==(u\u0007@\u0007:vX{\u0014\\F>7krY\u0005\u00129<f7\u001dL\u00024\ns\u001cV\u000735mZ\u001aA\u0004y\u0014H\u0003\nP lF\u0005.mv\u001eV\u0012u\u0010Q\u00124+{t\u0007@\u0003?*&}\u0006J\b\u000f^\u001bS\u0003?-ge\f\u0005\u001528dr\u0005d\u0005@\u00035\u001f_\u001cQ\u000b0+cr\u0007\u00055%6x7:KF\u00107az\u0014Q\u000f>7(R\u001bA\u000et\u001aK\u001200fr\u0007v\u000505m\fd\u0005@\u00035\u0016~r\u0007I\u0007(\nX\u0005Q\u000f<0rr\u0007V\u0015T\u0019L\u0003?-(d\u001cA\u0003q<pg\u0019J\u001586f\bG\u001aUF\"0rr\nC\u001c@\u0014\u00058op\u0010W\u0005v\u0019U\u000e0\u0012D\u0014Q\u0013#8|~\u001aKF\u001e/me\u0019D\u001f\u0014T\u001aK\u0015$4iu\u0019@F>)|~\u0018L\u001c4+\u0017_\u001cQ\u000b0+cr\u0007\u0005'5=gyU`\b0;dr\u0011\u001e_\u001cQ\u000b0+cr\u0007\n6=8q7\u001aK\u00054u(c\u001d@\bq1as\u0010\u0017t\u001aK\u001200fr\u0007q\u0003)-}e\u0010j\u00160:ac\f\rD\u001dL\u0003==(C\u0002@\u0007:*\u0014R\u0019\\\u0012#8(V\u0011A\t?yMy\u0014G\n4=\bp\u0014H\u0003\u001c6lr\"R\u001bQ\u000f% (^\u001bA\u000f28|x\u0007\u0005596\u007f74I\nq\u001cfc\u001cQ\u000f4*\u0005a\u0014I\u00134\fQ\u0014F\u0003\"vK\u007f\u0007J\u000b0\u0018t\u001aK\u0015$4iu\u0019@\t!-az\u001c_\u0003#wbd\u001aK\u001eR\u001bQ\u000f% (^\u001bA\u000f28|x\u0007\u0005'5=gyU`\b0;dr\u0011\bd\u0010Q\u001287od\fT\u001dW\t<8'D\u0005@\u00035\bp\u0014H\u0003\u001c6lr\u0011d\u001dL\u0003=={c\u0014Q\u0013\"wbd\u001aK\u0007X\u0000Q\n87m\u0014v\u001bF\u000e>+gg\u0001L\u000b8#me[O\u0015>7\rX\u0000Q\n87m86J\n>+\u0017d\u001cH\u0016=<j{\u001aF\r>/me\u0019D\u001f\u007f3{x\u001b\u0018t\u0019L\u0003?-Wd\u001cA\u0003\u000e8ft\u001dJ\u0014\"wbd\u001aK\u000es\u001cV\u000735mX\u0003@\u0014=8q\bS\u001cV\u000735ms\u000eS\u001cV\u000735msZf\t=6z\u0014t\u0007J\u0015\"1i~\u0007D\u000256fd[O\u0015>7\u000eS\u001cV\u000735msUf\t=6z\fC\u001aQ\u0003<y\\`\u0010D\r\"\u0005E\u0010D\u0002(\u0015D\u001dL\u0003==(U\u0007@\u0007:yLb\u0007D\u001286f\"D\u001dL\u0003==(U\u0007@\u0007:y[c\u001aUF\u001e7(V\u001bL\u000b0-ax\u001b\u0005#?=\u0007g\u001aU58#m\u0014V\u0001Q\u000722(~\u001bA\u000f28|x\u0007\u0005\u00010)\u0007r\u001bD\u0004=<l\re\u0010H\t'<Gb\u0001I\u000f?<\u0010T\u0007J\u0015\"1i~\u0007\u0005'5=gy\u0006\u000eT\u001dW\t<8'D\u0005@\u00035z:\u000fD\u0010I\u0000q\n|v\u0001@F\u001e7dn\u000e_\u001cQ\u000b0+cr\u0007\u0005\u001284m\u001aD\u001dL\u0003==(U\u0007@\u0007:yIs\u0011J\bq\u001cfv\u0017I\u00035\u0004c\u0007P\u0003\rt\u001dW\t<8Ga\u0010W\n0 \u0012V\u0019V\tq6f7\u0018@\n4<(\u007f\u001cQ\u0015\u000fv\u0005U\n4*c~\u001b\u000b\f\"6f\"\u0004z\u001aA\u0003\u000eU\u0019J\u0005:0fpZf\t=6z\u0016T\u0019L\u0003?-(D\u001cA\u00035yMo\u0005I\t\"0gy\rC\u001dW\t$>`7\u0002D\n=*\u0010R\u001bQ\u000f% (~\u001bA\u000f28|x\u0007\u000eE\u0010H\t'<(x\u0000Q\n87m\nC\u001aQ\u0003<y{~\u000f@\u0010R\u0019\\\u0012#8(~\u001bA\u000f28|x\u0007\u001at\u001aK\u001200fr\u0007g\u000722oe\u001aP\b5\u0016xv\u0016L\u0012(\bU\u0019J\u0005:0fp\u0005Q\u0014F\u0003\"\fs\u001cV\u000735mS\u0010U\u00129\u000bB\u0006L\b6yKx\u0019J\u0014\td\u001dJ\u0011\u0018:gy\u0006\fD\u001dL\u0003==(u\u0007@\u0007:\t{\u001cK\u0003\u00060lc\u001d\fG\u001aUF5,zv\u0001L\t?\u000es\u001cV\u000735mX\u0000Q\n87m\u0005r\rQ\u00140\u0010^\u001bS\u0003?-ge\f\u00052&<i|\u0006\u0019@\u0014L\u0012q?geUQ\u000e4yj{\u001aF\rq-g7\u0014W\u000b\fv\u0019U\u000e0\u0016~r\u0007I\u0007(\nC\u001c@\u0014\u00058op\u0010W\u0011D\u001dL\u0003==(u\u0007@\u0007:y|~\u0018@\u0005a\u0014I\u00134".length();
                    var3_4 = 11;
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
                        var4_2 = "d\u001dJ\u0011\u00028|b\u0007D\u001286f_\u0000A)'<z{\u0014\\\rR\u001bD\u0004=<l76J\n>+";
                        var6_3 = "d\u001dJ\u0011\u00028|b\u0007D\u001286f_\u0000A)'<z{\u0014\\\rR\u001bD\u0004=<l76J\n>+".length();
                        var3_4 = 24;
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
                            v10 = 23;
                            break;
                        }
                        case 1: {
                            v10 = 117;
                            break;
                        }
                        case 2: {
                            v10 = 37;
                            break;
                        }
                        case 3: {
                            v10 = 102;
                            break;
                        }
                        case 4: {
                            v10 = 81;
                            break;
                        }
                        case 5: {
                            v10 = 89;
                            break;
                        }
                        default: {
                            v10 = 8;
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
            ModImport.a = var7;
            break block21;
lbl77:

            while (true) {
                continue;
                break;
            }
        }
        var0_7 = 3055206154539862685L;
        while (true)
        ModImport.b = 7897393232253842786L ^ var0_7;
        ModImport.MODS = List.of((BiConsumer<Path, JsonObject>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;Ljava/lang/Object;)V, shieldStatus(java.nio.file.Path com.google.gson.JsonObject ), (Ljava/nio/file/Path;Lcom/google/gson/JsonObject;)V)(), (BiConsumer<Path, JsonObject>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;Ljava/lang/Object;)V, crosshairAddons(java.nio.file.Path com.google.gson.JsonObject ), (Ljava/nio/file/Path;Lcom/google/gson/JsonObject;)V)(), (BiConsumer<Path, JsonObject>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;Ljava/lang/Object;)V, tierTagger(java.nio.file.Path com.google.gson.JsonObject ), (Ljava/nio/file/Path;Lcom/google/gson/JsonObject;)V)(), (BiConsumer<Path, JsonObject>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;Ljava/lang/Object;)V, blockOverlay(java.nio.file.Path com.google.gson.JsonObject ), (Ljava/nio/file/Path;Lcom/google/gson/JsonObject;)V)(), (BiConsumer<Path, JsonObject>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;Ljava/lang/Object;)V, totemTweaks(java.nio.file.Path com.google.gson.JsonObject ), (Ljava/nio/file/Path;Lcom/google/gson/JsonObject;)V)(), (BiConsumer<Path, JsonObject>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;Ljava/lang/Object;)V, optimizers(java.nio.file.Path com.google.gson.JsonObject ), (Ljava/nio/file/Path;Lcom/google/gson/JsonObject;)V)(), (BiConsumer<Path, JsonObject>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;Ljava/lang/Object;)V, appleSkin(java.nio.file.Path com.google.gson.JsonObject ), (Ljava/nio/file/Path;Lcom/google/gson/JsonObject;)V)(), (BiConsumer<Path, JsonObject>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;Ljava/lang/Object;)V, betterScreens(java.nio.file.Path com.google.gson.JsonObject ), (Ljava/nio/file/Path;Lcom/google/gson/JsonObject;)V)());
    }
}
