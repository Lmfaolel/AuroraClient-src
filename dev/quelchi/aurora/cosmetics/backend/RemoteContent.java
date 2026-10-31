package dev.quelchi.aurora.cosmetics.backend;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.cosmetics.backend.CapePacks;
import dev.quelchi.aurora.cosmetics.backend.CosmeticsSync;
import dev.quelchi.aurora.cosmetics.backend.EmoteImport;
import dev.quelchi.aurora.cosmetics.backend.EmoteImport$Clip;
import dev.quelchi.aurora.cosmetics.content.Colorways;
import dev.quelchi.aurora.cosmetics.content.Colorways$Way;
import dev.quelchi.aurora.cosmetics.content.Emotes;
import dev.quelchi.aurora.cosmetics.content.Emotes$Emote;
import dev.quelchi.aurora.cosmetics.content.PremiumCapes;
import dev.quelchi.aurora.cosmetics.content.Trails;
import dev.quelchi.aurora.cosmetics.content.Trails$Origin;
import dev.quelchi.aurora.cosmetics.content.Trails$Style;
import dev.quelchi.aurora.cosmetics.content.Trails$Trail;
import dev.quelchi.aurora.cosmetics.content.Wings;
import dev.quelchi.aurora.cosmetics.content.Wings$Wing;
import dev.quelchi.aurora.cosmetics.renderer.EffectList;
import dev.quelchi.aurora.cosmetics.renderer.EmoteEngine;
import dev.quelchi.aurora.cosmetics.renderer.HitEffects;
import dev.quelchi.aurora.cosmetics.renderer.KillEffects;
import dev.quelchi.aurora.cosmetics.renderer.MaceEffects;
import dev.quelchi.aurora.cosmetics.renderer.wings.WingModels;
import dev.quelchi.aurora.render.EffectEngine;
import dev.quelchi.aurora.render.EffectEngine$Effect;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class RemoteContent {
    private static volatile boolean loaded;
    private static volatile boolean applied;
    private static final String[] a;
    private static final long[] b;
    private static final Integer[] c;
    private static final long d;

    private RemoteContent() {
    }

    public static boolean applied() {
        return applied;
    }

    public static boolean loaded() {
        return loaded;
    }

    public static void markUnloaded() {
        loaded = false;
    }

    public static void markLoaded() {
        loaded = true;
    }

    public static void apply(JsonObject jsonObject) {
        if (jsonObject == null) {
            return;
        }
        loaded = true;
        applied = true;
        String[] stringArray = a;
        RemoteContent.category(stringArray[42], () -> RemoteContent.lambda$apply$0(jsonObject));
        RemoteContent.category(stringArray[11], () -> RemoteContent.lambda$apply$1(jsonObject));
        RemoteContent.category(stringArray[53], () -> RemoteContent.lambda$apply$2(jsonObject));
        RemoteContent.category(stringArray[0], () -> RemoteContent.lambda$apply$3(jsonObject));
        RemoteContent.category(stringArray[27], () -> RemoteContent.lambda$apply$4(jsonObject));
        RemoteContent.category(stringArray[7], () -> RemoteContent.lambda$apply$5(jsonObject));
        RemoteContent.category(stringArray[51], () -> RemoteContent.lambda$apply$6(jsonObject));
        RemoteContent.category(stringArray[12], () -> RemoteContent.lambda$apply$7(jsonObject));
        RemoteContent.category(stringArray[50], () -> RemoteContent.lambda$apply$8(jsonObject));
    }

    private static void category(String string, Runnable runnable) {
        try {
            runnable.run();
        }
        catch (Exception exception) {
            Aurora.LOG.warn(a[10], (Object)string, (Object)exception.toString());
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static Set<String> shas(JsonObject jsonObject, String string, String string2) {
        JsonObject jsonObject2;
        int n;
        HashSet<String> hashSet;
        block36: {
            JsonObject jsonObject3;
            block35: {
                hashSet = new HashSet<String>();
                n = CosmeticsSync.a;
                jsonObject3 = jsonObject;
                if (n != 0) break block35;
                if (!jsonObject3.has(string)) return hashSet;
                jsonObject2 = jsonObject;
                if (n != 0) break block36;
                jsonObject3 = jsonObject2.get(string);
            }
            if (!jsonObject3.isJsonArray()) {
                return hashSet;
            }
            jsonObject2 = jsonObject;
        }
        block32: for (JsonElement jsonElement : jsonObject2.getAsJsonArray(string)) {
            block41: {
                JsonObject jsonObject4;
                block42: {
                    JsonObject jsonObject5;
                    JsonObject jsonObject6;
                    block40: {
                        String string3;
                        block37: {
                            block39: {
                                block38: {
                                    jsonObject6 = jsonElement.getAsJsonObject();
                                    jsonObject5 = jsonObject6;
                                    string3 = string2;
                                    if (n != 0) break block37;
                                    if (!jsonObject5.has(string3)) break block38;
                                    jsonObject5 = jsonObject6;
                                    if (n != 0) break block39;
                                    if (jsonObject5.get(string2).isJsonNull()) break block38;
                                    hashSet.add(jsonObject6.get(string2).getAsString());
                                }
                                jsonObject5 = jsonObject6;
                            }
                            if (n != 0) break block40;
                            string3 = a[25];
                        }
                        if (!jsonObject5.has(string3)) break block41;
                        jsonObject4 = jsonObject6;
                        if (n != 0) break block42;
                        jsonObject5 = jsonObject4.get(a[25]);
                    }
                    if (!jsonObject5.isJsonArray()) break block41;
                    jsonObject4 = jsonObject6;
                }
                for (JsonElement jsonElement2 : jsonObject4.getAsJsonArray(a[25])) {
                    block44: {
                        String string4;
                        JsonObject jsonObject7;
                        JsonObject jsonObject8;
                        block43: {
                            jsonObject8 = jsonElement2.getAsJsonObject();
                            if (n != 0) continue block32;
                            jsonObject7 = jsonObject8;
                            string4 = string2;
                            if (n != 0) break block43;
                            if (!jsonObject7.has(string4)) break block44;
                            jsonObject7 = jsonObject8;
                            string4 = string2;
                        }
                        boolean bl = jsonObject7.get(string4).isJsonNull();
                        if (n != 0 || bl) break block44;
                        bl = hashSet.add(jsonObject8.get(string2).getAsString());
                    }
                    if (n == 0) continue;
                }
            }
            if (n == 0) continue;
        }
        return hashSet;
    }

    
    private static void emotes(JsonArray jsonArray) {
        block33: {
            int n = CosmeticsSync.a;
            if (jsonArray == null) {
                return;
            }
            ArrayList<Emotes$Emote> arrayList = new ArrayList<Emotes$Emote>();
            HashMap<String, float[][]> hashMap = new HashMap<String, float[][]>();
            for (JsonElement jsonElement : jsonArray) {
                block34: {
                    try {
                        Object object;
                        JsonObject jsonObject = jsonElement.getAsJsonObject();
                        String[] stringArray = a;
                        String string = jsonObject.get(stringArray[59]).getAsString();
                        if (n != 0) break block33;
                        JsonArray jsonArray2 = jsonObject.getAsJsonArray(stringArray[15]);
                        float[][] fArray = new float[jsonArray2.size()][7];
                        for (int i = 0; i < jsonArray2.size(); ++i) {
                            float f;
                            block58: {
                                block47: {
                                    JsonObject jsonObject2;
                                    block46: {
                                        JsonObject jsonObject3;
                                        block57: {
                                            float f2;
                                            block45: {
                                                String string2;
                                                JsonObject jsonObject4;
                                                String string3;
                                                block43: {
                                                    block44: {
                                                        block56: {
                                                            float f3;
                                                            block55: {
                                                                block42: {
                                                                    JsonObject jsonObject5;
                                                                    block41: {
                                                                        block54: {
                                                                            float f4;
                                                                            block53: {
                                                                                block40: {
                                                                                    JsonObject jsonObject6;
                                                                                    block39: {
                                                                                        block52: {
                                                                                            float f5;
                                                                                            block51: {
                                                                                                block38: {
                                                                                                    JsonObject jsonObject7;
                                                                                                    block37: {
                                                                                                        block50: {
                                                                                                            float f6;
                                                                                                            block49: {
                                                                                                                block36: {
                                                                                                                    JsonElement jsonElement2;
                                                                                                                    block35: {
                                                                                                                        block48: {
                                                                                                                            object = jsonArray2.get(i);
                                                                                                                            if (n != 0) break block34;
                                                                                                                            jsonObject3 = object.getAsJsonObject();
                                                                                                                            float[] fArray2 = fArray[i];
                                                                                                                            int n2 = 0;
                                                                                                                            jsonElement2 = jsonObject3.get(a[46]);
                                                                                                                            if (n != 0) break block35;
                                                                                                                            fArray2[n2] = jsonElement2.getAsFloat();
                                                                                                                            fArray2 = fArray[i];
                                                                                                                            n2 = 1;
                                                                                                                            if (!jsonObject3.has(a[3])) break block36;
                                                                                                                            break block48;
                                                                                                                            catch (Exception exception) {
                                                                                                                                throw exception;
                                                                                                                            }
                                                                                                                        }
                                                                                                                        jsonElement2 = jsonObject3.get(a[18]);
                                                                                                                    }
                                                                                                                    f6 = jsonElement2.getAsFloat();
                                                                                                                    break block49;
                                                                                                                }
                                                                                                                f6 = 0.0f;
                                                                                                            }
                                                                                                            fArray2[n2] = f6;
                                                                                                            float[] fArray3 = fArray[i];
                                                                                                            int n3 = 2;
                                                                                                            jsonObject7 = jsonObject3;
                                                                                                            if (n != 0) break block37;
                                                                                                            if (!jsonObject7.has(a[21])) break block38;
                                                                                                            break block50;
                                                                                                            catch (Exception exception) {
                                                                                                                throw exception;
                                                                                                            }
                                                                                                        }
                                                                                                        jsonObject7 = jsonObject3.get(a[40]);
                                                                                                    }
                                                                                                    f5 = jsonObject7.getAsFloat();
                                                                                                    break block51;
                                                                                                }
                                                                                                f5 = 0.0f;
                                                                                            }
                                                                                            fArray3[n3] = f5;
                                                                                            float[] fArray4 = fArray[i];
                                                                                            int n4 = 3;
                                                                                            jsonObject6 = jsonObject3;
                                                                                            if (n != 0) break block39;
                                                                                            if (!jsonObject6.has(a[52])) break block40;
                                                                                            break block52;
                                                                                            catch (Exception exception) {
                                                                                                throw exception;
                                                                                            }
                                                                                        }
                                                                                        jsonObject6 = jsonObject3.get(a[32]);
                                                                                    }
                                                                                    f4 = jsonObject6.getAsFloat();
                                                                                    break block53;
                                                                                }
                                                                                f4 = 0.0f;
                                                                            }
                                                                            fArray4[n4] = f4;
                                                                            float[] fArray5 = fArray[i];
                                                                            int n5 = 4;
                                                                            jsonObject5 = jsonObject3;
                                                                            if (n != 0) break block41;
                                                                            if (!jsonObject5.has(a[5])) break block42;
                                                                            break block54;
                                                                            catch (Exception exception) {
                                                                                throw exception;
                                                                            }
                                                                        }
                                                                        jsonObject5 = jsonObject3.get(a[58]);
                                                                    }
                                                                    f3 = jsonObject5.getAsFloat();
                                                                    break block55;
                                                                }
                                                                f3 = 0.0f;
                                                            }
                                                            fArray5[n5] = f3;
                                                            float[] fArray6 = fArray[i];
                                                            int n6 = 5;
                                                            string3 = a[56];
                                                            jsonObject4 = jsonObject3;
                                                            string2 = a[57];
                                                            if (n != 0) break block43;
                                                            if (!string3.equals(RemoteContent.opt(jsonObject4, string2))) break block44;
                                                            break block56;
                                                            catch (Exception exception) {
                                                                throw exception;
                                                            }
                                                        }
                                                        f2 = 1.0f;
                                                        break block45;
                                                    }
                                                    stringArray = a;
                                                    string3 = stringArray[39];
                                                    jsonObject4 = jsonObject3;
                                                    string2 = stringArray[14];
                                                }
                                                f2 = string3.equals(RemoteContent.opt(jsonObject4, string2)) ? 2.0f : 0.0f;
                                            }
                                            fArray6[n6] = f2;
                                            float[] fArray7 = fArray[i];
                                            int n7 = 6;
                                            jsonObject2 = jsonObject3;
                                            if (n != 0) break block46;
                                            if (!jsonObject2.has(a[43])) break block47;
                                            break block57;
                                            catch (Exception exception) {
                                                throw exception;
                                            }
                                        }
                                        jsonObject2 = jsonObject3.get(a[48]);
                                    }
                                    f = jsonObject2.getAsFloat();
                                    break block58;
                                }
                                f = 0.0f;
                            }
                            fArray7[n7] = f;
                            if (n == 0) continue;
                        }
                        stringArray = a;
                        arrayList.add(new Emotes$Emote(string, jsonObject.get(stringArray[9]).getAsString()));
                        object = hashMap.put(string, fArray);
                    }
                    catch (Exception exception) {
                        Aurora.LOG.warn(a[44], (Object)exception.toString());
                    }
                }
                if (n == 0) continue;
            }
            Emotes.registerRemote(arrayList, hashMap);
        }
    }

    
    private static void emoteClips(JsonArray jsonArray) {
        block13: {
            int n = CosmeticsSync.a;
            if (jsonArray == null) {
                return;
            }
            HashMap<String, JsonObject> hashMap = new HashMap<String, JsonObject>();
            HashMap<String, EmoteImport$Clip> hashMap2 = new HashMap<String, EmoteImport$Clip>();
            ArrayList<String> arrayList = new ArrayList<String>();
            ArrayList<Emotes$Emote> arrayList2 = new ArrayList<Emotes$Emote>();
            for (JsonElement jsonElement : jsonArray) {
                block14: {
                    String[] stringArray;
                    try {
                        boolean bl;
                        JsonObject jsonObject = jsonElement.getAsJsonObject();
                        stringArray = a;
                        String string = jsonObject.get(stringArray[37]).getAsString();
                        hashMap.put(string, jsonObject);
                        if (n != 0) break block13;
                        hashMap2.put(string, EmoteImport.parse(jsonObject.getAsJsonObject(stringArray[23])));
                        if (n != 0) break block14;
                        if (jsonObject.has(a[8])) {
                            bl = jsonObject.get(a[24]).getAsBoolean();
                            if (n != 0) break block14;
                            if (bl) {
                                arrayList.add(string);
                            }
                        }
                        stringArray = a;
                        bl = arrayList2.add(new Emotes$Emote(string, jsonObject.get(stringArray[49]).getAsString()));
                    }
                    catch (Exception exception) {
                        stringArray = a;
                        Aurora.LOG.warn(stringArray[55], (Object)exception.toString());
                    }
                }
                if (n == 0) continue;
            }
            EmoteEngine.setServed(hashMap);
            EmoteEngine.setTraversal(arrayList);
            EmoteImport.setServed(hashMap2);
            Emotes.registerServedClips(arrayList2);
            Aurora.LOG.info(a[26], (Object)arrayList2.size());
        }
    }

    
    private static void wings(JsonArray jsonArray) {
        block14: {
            int n = CosmeticsSync.a;
            if (jsonArray == null) {
                return;
            }
            ArrayList<Wings$Wing> arrayList = new ArrayList<Wings$Wing>();
            for (JsonElement jsonElement : jsonArray) {
                try {
                    HashMap<String, String> hashMap;
                    ArrayList<Colorways$Way> arrayList2;
                    String string;
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                    String[] stringArray = a;
                    JsonElement jsonElement2 = jsonObject.get(stringArray[37]);
                    block9: while (true) {
                        string = jsonElement2.getAsString();
                        if (n != 0) break block14;
                        arrayList2 = new ArrayList<Colorways$Way>();
                        hashMap = new HashMap<String, String>();
                        JsonObject jsonObject2 = jsonObject;
                        if (n == 0) {
                            if (!jsonObject2.has(a[25])) break;
                            jsonObject2 = jsonObject;
                        }
                        stringArray = a;
                        Iterator iterator = jsonObject2.getAsJsonArray(stringArray[25]).iterator();
                        while (iterator.hasNext()) {
                            JsonObject jsonObject4;
                            JsonElement jsonElement3;
                            jsonElement2 = jsonElement3 = (JsonElement)iterator.next();
                            if (n != 0) continue block9;
                            JsonObject jsonObject3 = jsonObject4 = jsonElement2.getAsJsonObject();
                            if (n == 0) {
                                if (!jsonObject3.has(a[31])) continue;
                                stringArray = a;
                                jsonObject3 = jsonObject4.get(stringArray[37]);
                            }
                            String string2 = jsonObject3.getAsString();
                            stringArray = a;
                            arrayList2.add(Colorways.way(string2, jsonObject4.get(stringArray[49]).getAsString(), RemoteContent.argb(jsonObject4.get(stringArray[62]).getAsString()) & RemoteContent.a(15655, 6452265568009034670L), RemoteContent.argb(jsonObject4.get(stringArray[29]).getAsString()) & RemoteContent.a(15655, 6452265568009034670L)));
                            hashMap.put(string2, jsonObject4.get(stringArray[31]).getAsString());
                            if (n == 0) continue;
                        }
                        break;
                    }
                    arrayList.add(new Wings$Wing(string, jsonObject.get(a[49]).getAsString(), jsonObject.has(a[31]) ? jsonObject.get(a[31]).getAsString() : null, arrayList2, hashMap));
                }
                catch (Exception exception) {
                    Aurora.LOG.warn(a[61], (Object)exception.toString());
                }
                if (n == 0) continue;
            }
            Wings.registerRemote(arrayList);
        }
    }

    
    private static void trails(JsonArray jsonArray) {
        ArrayList<Trails$Trail> arrayList;
        block7: {
            int n = CosmeticsSync.a;
            if (jsonArray == null) {
                return;
            }
            ArrayList<Trails$Trail> arrayList2 = new ArrayList<Trails$Trail>();
            for (JsonElement jsonElement : jsonArray) {
                try {
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                    arrayList = arrayList2;
                    if (n != 0) break block7;
                    String[] stringArray = a;
                    int n2 = jsonObject.has(stringArray[19]) ? jsonObject.get(stringArray[2]).getAsInt() : 40;
                    stringArray = a;
                    arrayList.add(new Trails$Trail(jsonObject.get(a[37]).getAsString(), jsonObject.get(a[49]).getAsString(), RemoteContent.argb(jsonObject.get(a[1]).getAsString()), jsonObject.has(a[63]) ? jsonObject.get(a[41]).getAsInt() : 3, n2, Trails$Style.valueOf(jsonObject.get(stringArray[13]).getAsString()), RemoteContent.ways(jsonObject), jsonObject.has(stringArray[60]) ? Trails$Origin.valueOf(jsonObject.get(stringArray[16]).getAsString()) : Trails$Origin.BODY));
                }
                catch (Exception exception) {
                    Aurora.LOG.warn(a[30], (Object)exception.toString());
                }
                if (n == 0) continue;
            }
            arrayList = arrayList2;
        }
        Trails.registerRemote(arrayList);
    }

    private static void effects(JsonArray jsonArray, EffectList effectList) {
        block6: {
            int n = CosmeticsSync.a;
            if (jsonArray == null) {
                return;
            }
            ArrayList<EffectEngine$Effect> arrayList = new ArrayList<EffectEngine$Effect>();
            for (JsonElement jsonElement : jsonArray) {
                try {
                    arrayList.add(EffectEngine.effect(jsonElement.getAsJsonObject()));
                    if (n != 0) break block6;
                }
                catch (Exception exception) {
                    Aurora.LOG.warn(a[6], (Object)exception.toString());
                }
                if (n == 0) continue;
            }
            effectList.set(arrayList);
        }
    }

    private static String opt(JsonObject jsonObject, String string) {
        String string2;
        block6: {
            block5: {
                JsonObject jsonObject2;
                block4: {
                    int n = CosmeticsSync.a;
                    jsonObject2 = jsonObject;
                    if (n != 0) break block4;
                    if (!jsonObject2.has(string)) break block5;
                    jsonObject2 = jsonObject.get(string);
                }
                string2 = jsonObject2.getAsString();
                break block6;
            }
            string2 = "";
        }
        return string2;
    }

    private static int argb(String string) {
        int n;
        block2: {
            long l;
            block3: {
                l = Long.parseLong(string, 16);
                int n2 = CosmeticsSync.a;
                n = string.length();
                if (n2 != 0) break block2;
                if (n > 6) break block3;
                l |= d;
            }
            n = (int)l;
        }
        return n;
    }

    private static List<Colorways$Way> ways(JsonObject jsonObject) {
        ArrayList<Colorways$Way> arrayList;
        block10: {
            ArrayList<Colorways$Way> arrayList2;
            block9: {
                JsonObject jsonObject2;
                int n;
                block8: {
                    arrayList2 = new ArrayList<Colorways$Way>();
                    n = CosmeticsSync.a;
                    jsonObject2 = jsonObject;
                    if (n != 0) break block8;
                    if (!jsonObject2.has(a[45])) break block9;
                    jsonObject2 = jsonObject;
                }
                String[] stringArray = a;
                for (JsonElement jsonElement : jsonObject2.getAsJsonArray(stringArray[25])) {
                    JsonObject jsonObject3 = jsonElement.getAsJsonObject();
                    arrayList = arrayList2;
                    if (n == 0) {
                        arrayList.add(Colorways.way(jsonObject3.get(a[37]).getAsString(), jsonObject3.get(a[49]).getAsString(), RemoteContent.argb(jsonObject3.get(a[36]).getAsString()) & RemoteContent.a(31166, 4558916348199329590L), RemoteContent.argb(jsonObject3.get(a[34]).getAsString()) & RemoteContent.a(15655, 6452265568009034670L)));
                        if (n == 0) continue;
                        break;
                    }
                    break block10;
                }
            }
            arrayList = arrayList2;
        }
        return arrayList;
    }

    private static void lambda$apply$8(JsonObject jsonObject) {
        RemoteContent.effects(jsonObject.getAsJsonArray(a[20]), HitEffects.LIST);
    }

    private static void lambda$apply$7(JsonObject jsonObject) {
        RemoteContent.effects(jsonObject.getAsJsonArray(a[17]), MaceEffects.LIST);
    }

    private static void lambda$apply$6(JsonObject jsonObject) {
        RemoteContent.effects(jsonObject.getAsJsonArray(a[28]), KillEffects.LIST);
    }

    private static void lambda$apply$5(JsonObject jsonObject) {
        RemoteContent.trails(jsonObject.getAsJsonArray(a[33]));
    }

    private static void lambda$apply$4(JsonObject jsonObject) {
        RemoteContent.wings(jsonObject.getAsJsonArray(a[22]));
    }

    private static void lambda$apply$3(JsonObject jsonObject) {
        RemoteContent.emoteClips(jsonObject.getAsJsonArray(a[35]));
    }

    private static void lambda$apply$2(JsonObject jsonObject) {
        RemoteContent.emotes(jsonObject.getAsJsonArray(a[38]));
    }

    private static void lambda$apply$1(JsonObject jsonObject) {
        block5: {
            JsonObject jsonObject2;
            block4: {
                int n = CosmeticsSync.a;
                jsonObject2 = jsonObject;
                if (n != 0) break block4;
                if (!jsonObject2.has(a[54])) break block5;
                jsonObject2 = jsonObject;
            }
            PremiumCapes.registerRemote(jsonObject2.getAsJsonArray(a[54]));
        }
    }

    private static void lambda$apply$0(JsonObject jsonObject) {
        block11: {
            Set<String> set;
            block10: {
                Set<String> set2;
                int n;
                block8: {
                    block9: {
                        String[] stringArray = a;
                        Set<String> set3 = RemoteContent.shas(jsonObject, stringArray[54], stringArray[47]);
                        n = CosmeticsSync.a;
                        set2 = RemoteContent.shas(jsonObject, stringArray[22], stringArray[4]);
                        set = set3;
                        if (n != 0) break block8;
                        if (set.isEmpty()) break block9;
                        CapePacks.prune(set3);
                    }
                    set = set2;
                }
                if (n != 0) break block10;
                if (set.isEmpty()) break block11;
                set = set2;
            }
            WingModels.prune(set);
        }
    }

    
    static {
        block25: {
            block24: {
                block23: {
                    block22: {
                        block21: {
                            var15 = new String[64];
                            var13_1 = 0;
                            var12_2 = ")4.^7 S%)2\u0006/6-E'\u0011\u0006 </M&\u000b\u0004.82O\u0005!6%O>\u0005<1 Y7\u0019\u001f<3\\7\u0007\u001f)?'O1\u0017\u001f?2(Z\"\u0006[vy:W\u00068+ C>\u0010\t8+ \\7\u0011L-5\u0005 8#O>\u001d\u001f2(Z\"\nQ+y5B7CD1y\"K&\u0002S#>4OhCD1\u0005/81O!\u000b!8\"O\u0017\u0005Y):5Y\u0005?-8F7\u0004;87O\b/1 D<\u0006S?\u0006#+(M;\r\u000b!8\"O\u0017\u0005Y):5Y\u0004.82O\u0006 </M&\u000b\n$05o4\u0005Z/-2\u0003-41\u0005;0/M!\u0005)4.^7\t8+ \\7\u0011L-5\u0004;88Y\u001f\r,3E \u0002\u0005l\"<\n!\u0006M:<%\n7\u000eP8<aI>\nOd*h\u0005;0/M!\u000b'0-F\u0017\u0005Y):5Y\u0005#,5O \u0018\u001f<3\\7\u0007\u001f8+ C>CL'01Z7\u0007\u0005l\"<\u0005!6%O>\u0004*+$[\u00068+ C>\u0010\u0005#,5O \n)4.^7 S%)2\u0005%7/O \u0002%=\u0006)4.^7\u0010\u0003<62\u0003-41\u0005;0%^:\u0005/8\"B7\u0004)82O\u0018\u001f<3\\7\u0007\u001f)4.^7CL'01Z7\u0007\u0005l\"<\u0004;88Y\u0004?5.^\u0004<8\"A\u0004)82O\u0005 8#O>\n$05o4\u0005Z/-2\u000b'0-F\u0017\u0005Y):5Y\u0004*+$[\u0006)4.^7\u0010\u0005/81O!\u001d\u001f<3\\7\u0007\u001f)4.^7C\\ 01\n!\bV<)$NhCD1\u0003-;2\u0004;87O\u0005<1 Y7\u0002%=\u0006#+(M;\r\u0017\u001f<3\\7\u0007\u001f;0/Mr\u0010T%)1O6Y\u001f7$";
                            var14_3 = ")4.^7 S%)2\u0006/6-E'\u0011\u0006 </M&\u000b\u0004.82O\u0005!6%O>\u0005<1 Y7\u0019\u001f<3\\7\u0007\u001f)?'O1\u0017\u001f?2(Z\"\u0006[vy:W\u00068+ C>\u0010\t8+ \\7\u0011L-5\u0005 8#O>\u001d\u001f2(Z\"\nQ+y5B7CD1y\"K&\u0002S#>4OhCD1\u0005/81O!\u000b!8\"O\u0017\u0005Y):5Y\u0005?-8F7\u0004;87O\b/1 D<\u0006S?\u0006#+(M;\r\u000b!8\"O\u0017\u0005Y):5Y\u0004.82O\u0006 </M&\u000b\n$05o4\u0005Z/-2\u0003-41\u0005;0/M!\u0005)4.^7\t8+ \\7\u0011L-5\u0004;88Y\u001f\r,3E \u0002\u0005l\"<\n!\u0006M:<%\n7\u000eP8<aI>\nOd*h\u0005;0/M!\u000b'0-F\u0017\u0005Y):5Y\u0005#,5O \u0018\u001f<3\\7\u0007\u001f8+ C>CL'01Z7\u0007\u0005l\"<\u0005!6%O>\u0004*+$[\u00068+ C>\u0010\u0005#,5O \n)4.^7 S%)2\u0005%7/O \u0002%=\u0006)4.^7\u0010\u0003<62\u0003-41\u0005;0%^:\u0005/8\"B7\u0004)82O\u0018\u001f<3\\7\u0007\u001f)4.^7CL'01Z7\u0007\u0005l\"<\u0004;88Y\u0004?5.^\u0004<8\"A\u0004)82O\u0005 8#O>\n$05o4\u0005Z/-2\u000b'0-F\u0017\u0005Y):5Y\u0004*+$[\u0006)4.^7\u0010\u0005/81O!\u001d\u001f<3\\7\u0007\u001f)4.^7C\\ 01\n!\bV<)$NhCD1\u0003-;2\u0004;87O\u0005<1 Y7\u0002%=\u0006#+(M;\r\u0017\u001f<3\\7\u0007\u001f;0/Mr\u0010T%)1O6Y\u001f7$".length();
                            var11_4 = 10;
                            var10_5 = -1;
lbl7:

                            while (true) {
                                v0 = ++var10_5;
                                v1 = var12_2.substring(v0, v0 + var11_4);
                                v2 = -1;
                                break block21;
                                break;
                            }
lbl12:

                            while (true) {
                                var15[var13_1++] = v3.intern();
                                if ((var10_5 += var11_4) < var14_3) {
                                    var11_4 = var12_2.charAt(var10_5);
                                    continue;
                                }
                                var12_2 = "%7/O \u0005;0%^:";
                                var14_3 = "%7/O \u0005;0%^:".length();
                                var11_4 = 5;
                                var10_5 = -1;
lbl21:

                                while (true) {
                                    v4 = ++var10_5;
                                    v1 = var12_2.substring(v4, v4 + var11_4);
                                    v2 = 0;
                                    break block21;
                                    break;
                                }
                                break;
                            }
lbl26:

                            while (true) {
                                var15[var13_1++] = v3.intern();
                                if ((var10_5 += var11_4) < var14_3) {
                                    var11_4 = var12_2.charAt(var10_5);
                                    continue;
                                }
                                break block22;
                                break;
                            }
                        }
                        v5 = v1.toCharArray();
                        v6 = v5;
                        v7 = v5.length;
                        var16_6 = 0;
                        do {
                            v6 = v6;
                            v8 = var16_6;
                            v9 = v6[v8];
                            switch (var16_6 % 7) {
                                case 0: {
                                    v10 = 76;
                                    break;
                                }
                                case 1: {
                                    v10 = 89;
                                    break;
                                }
                                case 2: {
                                    v10 = 65;
                                    break;
                                }
                                case 3: {
                                    v10 = 42;
                                    break;
                                }
                                case 4: {
                                    v10 = 82;
                                    break;
                                }
                                case 5: {
                                    v10 = 99;
                                    break;
                                }
                                default: {
                                    v10 = 63;
                                }
                            }
                            v6[v8] = (char)(v9 ^ v10);
                            ++var16_6;
lbl65:

                            v7 = v7;
                        } while (v7 > var16_6);
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
                    RemoteContent.a = var15;
                    var2_7 = 4653731134234931354L;
                    var8_8 = new long[2];
                    var5_9 = 0;
                    var6_10 = "`\u001d\u008c\\\u00d7\u00e4\u0010SH\u00ec\u00cf\u009dYi\u00d4\u00cb";
                    var7_11 = "`\u001d\u008c\\\u00d7\u00e4\u0010SH\u00ec\u00cf\u009dYi\u00d4\u00cb".length();
                    var4_12 = 0;
                    while (true) {
                        break block23;
                        break;
                    }
lbl84:

                    while (true) {
                        var8_8[v11] = (((long)var9_13[0] & 255L) << 56 | ((long)var9_13[1] & 255L) << 48 | ((long)var9_13[2] & 255L) << 40 | ((long)var9_13[3] & 255L) << 32 | ((long)var9_13[4] & 255L) << 24 | ((long)var9_13[5] & 255L) << 16 | ((long)var9_13[6] & 255L) << 8 | (long)var9_13[7] & 255L) ^ var2_7;
                        if (var4_12 < var7_11) continue;
                        break block24;
                        break;
                    }
                }
                var9_13 = var6_10.substring(var4_12, var4_12 += 8).getBytes("ISO-8859-1");
                v11 = var5_9++;
                while (true)
            }
            RemoteContent.b = var8_8;
            RemoteContent.c = new Integer[2];
            break block25;
lbl99:

            while (true) {
                continue;
                break;
            }
        }
        var0_14 = 8122270597224676962L;
        while (true)
        RemoteContent.d = 8122270600798223970L ^ var0_14;
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2688;
        if (c[n2] == null) {
            RemoteContent.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }
}
