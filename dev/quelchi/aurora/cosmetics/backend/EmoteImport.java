package dev.quelchi.aurora.cosmetics.backend;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.cosmetics.backend.CosmeticsSync;
import dev.quelchi.aurora.cosmetics.backend.EmoteImport$Clip;
import dev.quelchi.aurora.cosmetics.backend.EmoteImport$Key;
import dev.quelchi.aurora.cosmetics.content.Emotes;
import dev.quelchi.aurora.cosmetics.content.Emotes$Emote;
import dev.quelchi.aurora.cosmetics.renderer.EmoteEngine;
import dev.quelchi.aurora.module.Module;
import java.lang.invoke.CallSite;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import net.fabricmc.loader.api.FabricLoader;

public final class EmoteImport {
    private static final Map<String, EmoteImport$Clip> CLIPS;
    private static final Map<String, EmoteImport$Clip> SERVED;
    private static Path dir;
    private static long stamp;
    private static final Map<String, int[]> PARTS;
    private static final String[] a;

    private EmoteImport() {
    }

    public static void setServed(Map<String, EmoteImport$Clip> map) {
        SERVED.clear();
        SERVED.putAll(map);
    }

    public static EmoteImport$Clip clip(String string) {
        EmoteImport$Clip emoteImport$Clip;
        block6: {
            EmoteImport$Clip emoteImport$Clip2;
            block4: {
                block5: {
                    EmoteImport$Clip emoteImport$Clip3 = SERVED.get(string);
                    int n = CosmeticsSync.a;
                    emoteImport$Clip2 = emoteImport$Clip3;
                    if (n != 0) break block4;
                    if (emoteImport$Clip2 == null) break block5;
                    emoteImport$Clip = emoteImport$Clip3;
                    break block6;
                }
                emoteImport$Clip2 = CLIPS.get(string);
            }
            emoteImport$Clip = emoteImport$Clip2;
        }
        return emoteImport$Clip;
    }

    
    public static void poll() {
        int n = CosmeticsSync.a;
        try {
            long l2;
            block26: {
                Stream<Path> stream;
                block27: {
                    block29: {
                        Path path2;
                        Path path;
                        block28: {
                            path = dir;
                            if (n != 0) break block28;
                            if (path != null) break block29;
                            dir = FabricLoader.getInstance().getConfigDir().resolve(a[22]);
                            Files.createDirectories(dir, new FileAttribute[0]);
                            path = dir.resolve(a[15]);
                        }
                        Path path3 = path2 = path;
                        if (n == 0 && !Files.exists(path3, new LinkOption[0])) {
                            path3 = Files.writeString(path2, (CharSequence)a[35], new OpenOption[0]);
                        }
                    }
                    l2 = 0L;
                    Stream<Path> stream2 = Files.list(dir);
                    try {
                        for (Path path : stream2.toList()) {
                            if (n == 0) {
                                l2 += Files.getLastModifiedTime(path, new LinkOption[0]).toMillis() + (long)path.getFileName().hashCode();
                                if (n == 0) continue;
                            }
                            break block26;
                        }
                        stream = stream2;
                        if (n != 0) break block27;
                    }
                    catch (Throwable throwable) {
                        if (n != 0) throw throwable;
                        if (stream2 == null) throw throwable;
                        try {
                            stream2.close();
                            throw throwable;
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                        throw throwable;
                    }
                    if (stream == null) break block26;
                    stream = stream2;
                }
                stream.close();
            }
            long l = l2;
            if (n == 0) {
                if (l == stamp) {
                    return;
                }
                l = l2;
            }
            stamp = l;
            EmoteImport.scan();
            return;
        }
        catch (Exception exception) {
            Aurora.LOG.debug(a[25], (Throwable)exception);
        }
    }

    
    private static void scan() throws Exception {
        block20: {
            block25: {
                block24: {
                    var1 = new HashMap<CallSite, EmoteImport$Clip>();
                    var2_1 = new HashMap<String, JsonObject>();
                    var3_2 = new ArrayList<Emotes$Emote>();
                    var4_3 = Files.list(EmoteImport.dir);
                    var0_4 = CosmeticsSync.a;
                    try {
                        for (Path var6_7 : var4_3.toList()) {
                            block21: {
                                if (var0_4 != 0) break block20;
                                var7_9 = var6_7.getFileName().toString();
                                v0 = var7_9;
                                if (var0_4 != 0) // goto
                                if (!v0.endsWith(EmoteImport.a[26])) {
                                    continue;
                                }
                                break block21;
                                catch (Exception v1) {
                                    throw v1;
                                }
                            }
                            try {
                                block28: {
                                    block23: {
                                        block22: {
                                            block27: {
                                                v0 = Files.readString(var6_7);
lbl24:

                                                var8_10 = JsonParser.parseString((String)v0).getAsJsonObject();
                                                v3 = var8_10;
                                                if (var0_4 != 0) break block22;
                                                if (!v3.has(EmoteImport.a[19])) break block23;
                                                break block27;
                                                catch (Exception v4) {
                                                    throw v4;
                                                }
                                            }
                                            v3 = var8_10.get(EmoteImport.a[12]);
                                        }
                                        v6 = v3.getAsString();
                                        break block28;
                                    }
                                    v6 = var7_9.substring(0, var7_9.length() - 5);
                                }
                                var9_12 = v6;
                                var11_14 = EmoteImport.a;
                                var10_13 = var11_14[2] + var9_12.toLowerCase().replaceAll(var11_14[40], "");
                                var1.put((CallSite)var10_13, EmoteImport.parse(var8_10.getAsJsonObject(var11_14[18])));
                                var2_1.put(var10_13, var8_10);
                                var3_2.add(new Emotes$Emote(var10_13, var9_12));
                            }
                            catch (Exception var8_11) {
                                var11_14 = EmoteImport.a;
                                Aurora.LOG.warn(var11_14[27], (Object)var7_9, (Object)var8_11.toString());
                            }
                            if (var0_4 == 0) continue;
                        }
                    }
                    catch (Throwable var5_6) {
                        block26: {
                            if (var0_4 != 0 || var4_3 == null) break block26;
                            try {
                                var4_3.close();
                            }
                            catch (Throwable var6_8) {
                                var5_6.addSuppressed(var6_8);
                            }
                        }
                        throw var5_6;
                    }
                    v8 = var4_3;
                    if (var0_4 != 0) break block24;
                    if (v8 == null) break block25;
                    v8 = var4_3;
                }
                v8.close();
            }
            EmoteImport.CLIPS.clear();
            EmoteImport.CLIPS.putAll(var1);
            EmoteEngine.setLocal(var2_1);
            Emotes.registerImported(var3_2);
            Aurora.LOG.info(EmoteImport.a[38], (Object)var3_2.size());
        }
    }

    public static EmoteImport$Clip parse(JsonObject jsonObject) {
        EmoteImport$Clip emoteImport$Clip;
        JsonObject jsonObject2;
        boolean bl;
        float f;
        float f2;
        block62: {
            boolean bl2;
            float f3;
            int n;
            block61: {
                block60: {
                    JsonObject jsonObject3;
                    block59: {
                        float f4;
                        block73: {
                            block58: {
                                JsonObject jsonObject4;
                                block57: {
                                    float f5;
                                    block72: {
                                        block56: {
                                            JsonObject jsonObject5;
                                            block55: {
                                                boolean bl3;
                                                block54: {
                                                    block53: {
                                                        String string;
                                                        JsonObject jsonObject6;
                                                        block52: {
                                                            n = CosmeticsSync.a;
                                                            jsonObject6 = jsonObject;
                                                            string = a[14];
                                                            if (n != 0) break block52;
                                                            if (!jsonObject6.has(string)) break block53;
                                                            jsonObject6 = jsonObject;
                                                            string = a[13];
                                                        }
                                                        bl3 = jsonObject6.get(string).getAsBoolean();
                                                        if (n != 0) break block54;
                                                        if (!bl3) break block53;
                                                        bl3 = true;
                                                        break block54;
                                                    }
                                                    bl3 = false;
                                                }
                                                boolean bl4 = bl3;
                                                float f6 = bl4 ? (float)Math.PI / 180 : 1.0f;
                                                f3 = f6;
                                                jsonObject5 = jsonObject;
                                                if (n != 0) break block55;
                                                if (!jsonObject5.has(a[17])) break block56;
                                                jsonObject5 = jsonObject.get(a[9]);
                                            }
                                            f5 = jsonObject5.getAsFloat();
                                            break block72;
                                        }
                                        f5 = 20.0f;
                                    }
                                    f2 = f5;
                                    jsonObject4 = jsonObject;
                                    if (n != 0) break block57;
                                    if (!jsonObject4.has(a[16])) break block58;
                                    jsonObject4 = jsonObject.get(a[6]);
                                }
                                f4 = jsonObject4.getAsFloat();
                                break block73;
                            }
                            f4 = 0.0f;
                        }
                        f = f4;
                        jsonObject3 = jsonObject;
                        if (n != 0) break block59;
                        if (!jsonObject3.has(a[34])) break block60;
                        jsonObject3 = jsonObject.get(a[39]);
                    }
                    bl2 = jsonObject3.getAsBoolean();
                    if (n != 0) break block61;
                    if (!bl2) break block60;
                    bl2 = true;
                    break block61;
                }
                bl2 = false;
            }
            bl = bl2;
            jsonObject2 = new HashMap();
            String[] stringArray = a;
            for (Object object : jsonObject.getAsJsonArray(stringArray[24])) {
                block65: {
                    block49: while (true) {
                        String string;
                        float f7;
                        JsonObject jsonObject7;
                        block74: {
                            block64: {
                                JsonObject jsonObject8;
                                block63: {
                                    JsonElement jsonElement = (JsonElement)object;
                                    jsonObject7 = jsonElement.getAsJsonObject();
                                    stringArray = a;
                                    f7 = jsonObject7.get(stringArray[0]).getAsFloat();
                                    if (n != 0) break block62;
                                    jsonObject8 = jsonObject7;
                                    if (n != 0) break block63;
                                    if (!jsonObject8.has(stringArray[7])) break block64;
                                    jsonObject8 = jsonObject7.get(a[5]);
                                }
                                string = jsonObject8.getAsString();
                                break block74;
                            }
                            stringArray = a;
                            string = stringArray[1];
                        }
                        String string2 = string;
                        Iterator<Map.Entry<String, int[]>> iterator = PARTS.entrySet().iterator();
                        while (true) {
                            String string3;
                            JsonObject jsonObject9;
                            int n2;
                            JsonObject jsonObject10;
                            block70: {
                                int n3;
                                JsonObject jsonObject11;
                                block71: {
                                    Object object2;
                                    Object object3;
                                    block68: {
                                        block69: {
                                            Object k;
                                            JsonObject jsonObject12;
                                            block66: {
                                                if (!iterator.hasNext()) break block65;
                                                object = iterator.next();
                                                if (n != 0) continue block49;
                                                object3 = object;
                                                jsonObject12 = jsonObject7;
                                                k = object3.getKey();
                                                if (n != 0) break block66;
                                                if (!jsonObject12.has((String)k)) {
                                                    continue;
                                                }
                                                jsonObject12 = jsonObject7;
                                                k = object3.getKey();
                                            }
                                            jsonObject11 = jsonObject12.getAsJsonObject((String)k);
                                            int n4 = ((int[])object3.getValue())[0];
                                            int n5 = ((int[])object3.getValue())[1];
                                            EmoteImport.add(jsonObject2, n4, jsonObject11, a[31], f7, f3, string2);
                                            object2 = jsonObject2;
                                            if (n != 0) break block68;
                                            EmoteImport.add(object2, n4 + 1, jsonObject11, a[29], f7, f3, string2);
                                            EmoteImport.add(jsonObject2, n4 + 2, jsonObject11, a[11], f7, f3, string2);
                                            if (n5 < 0) break block69;
                                            object2 = jsonObject11;
                                            if (n != 0) break block68;
                                            if (!object2.has(a[23])) break block69;
                                            EmoteImport.put((Map<Integer, List<EmoteImport$Key>>)jsonObject2, n5, new EmoteImport$Key(f7, jsonObject11.get(a[30]).getAsFloat() * f3, string2));
                                        }
                                        object2 = object3.getValue();
                                    }
                                    n3 = ((int[])object2)[2];
                                    int n6 = ((int[])object3.getValue())[3];
                                    jsonObject10 = jsonObject2;
                                    n2 = n3;
                                    jsonObject9 = jsonObject11;
                                    string3 = "x";
                                    if (n != 0) break block70;
                                    EmoteImport.add(jsonObject10, n2, jsonObject9, string3, f7, 1.0f, string2);
                                    if (!jsonObject11.has("y")) break block71;
                                    EmoteImport.put((Map<Integer, List<EmoteImport$Key>>)jsonObject2, n3 + 1, new EmoteImport$Key(f7, 2.0f * (float)n6 - jsonObject11.get("y").getAsFloat(), string2));
                                }
                                jsonObject10 = jsonObject2;
                                n2 = n3 + 2;
                                jsonObject9 = jsonObject11;
                                string3 = "z";
                            }
                            EmoteImport.add(jsonObject10, n2, jsonObject9, string3, f7, 1.0f, string2);
                            if (n != 0) break block49;
                        }
                        break;
                    }
                    boolean bl5 = Module.a = !Module.a;
                }
                if (n == 0) continue;
            }
            Aurora.LOG.debug(a[36], new Object[]{jsonObject2.size(), jsonObject2.containsKey(21), jsonObject2.containsKey(22), jsonObject2.containsKey(23), jsonObject2.containsKey(24), jsonObject2.containsKey(25)});
        }
        EmoteImport$Clip emoteImport$Clip2 = emoteImport$Clip;
        EmoteImport$Clip emoteImport$Clip3 = emoteImport$Clip;
        JsonObject jsonObject13 = jsonObject2;
        float f8 = Math.max(1.0f, f2);
        float f9 = bl ? f : 0.0f;
        emoteImport$Clip2((Map<Integer, List<EmoteImport$Key>>)jsonObject13, f8, f9, bl);
        return emoteImport$Clip3;
    }

    private static void add(Map<Integer, List<EmoteImport$Key>> map, int n, JsonObject jsonObject, String string, float f, float f2, String string2) {
        if (jsonObject.has(string)) {
            EmoteImport.put(map, n, new EmoteImport$Key(f, jsonObject.get(string).getAsFloat() * f2, string2));
        }
    }

    private static void put(Map<Integer, List<EmoteImport$Key>> map, int n, EmoteImport$Key emoteImport$Key) {
        map.computeIfAbsent(n, EmoteImport::lambda$put$0).add(emoteImport$Key);
    }

    
    public static void sample(EmoteImport$Clip var0, float var1_1, float[] var2_2) {
        block62: {
            block63: {
                block60: {
                    block61: {
                        Arrays.fill(var2_2, NaNf);
                        var4_3 = var1_1 * 20.0f;
                        var3_4 = CosmeticsSync.a;
                        v0 = var0.loop();
                        if (var3_4 != 0) break block60;
                        if (v0 != 0) {
                        }
                        v2 = var4_3;
                        v3 = var0.length();
                        if (var3_4 != 0) break block61;
                        if (v2 > v3) {
                        }
                        var5_5 = Math.max(1.0f, var0.length() - var0.loopStart());
                        var4_3 = var0.loopStart() + (var4_3 - var0.loopStart()) % var5_5;
                        if (var3_4 == 0) break block62;
lbl30:

                        v2 = var4_3;
                        if (var3_4 != 0) break block63;
                        v3 = var0.length();
                    }
                    v0 = (cfr_temp_0 = v2 - v3) == 0.0f ? 0 : (cfr_temp_0 > 0.0f ? 1 : -1);
                }
                if (v0 <= 0) break block62;
                v2 = var0.length();
            }
            var4_3 = v2;
        }
        var5_6 = var0.channels().entrySet().iterator();
        block54: while (true) {
            v9 = var5_6;
            block55: while (v9.hasNext()) {
                block81: {
                    block79: {
                        block80: {
                            block77: {
                                block78: {
                                    block75: {
                                        block76: {
                                            block73: {
                                                block74: {
                                                    var6_7 = var5_6.next();
                                                    var7_8 = var6_7.getValue();
                                                    var8_9 = null;
                                                    var9_10 = null;
                                                    var10_11 = var7_8.iterator();
                                                    while (var10_11.hasNext()) {
                                                        block70: {
                                                            block71: {
                                                                block72: {
                                                                    block69: {
                                                                        block64: {
                                                                            block68: {
                                                                                block65: {
                                                                                    block66: {
                                                                                        block67: {
                                                                                            v9 = var10_11;
                                                                                            if (var3_4 != 0) continue block55;
                                                                                            var11_13 = (EmoteImport$Key)v9.next();
                                                                                            v10 = var11_13.tick();
                                                                                            if (var3_4 != 0) break block64;
                                                                                            if (!(v10 <= var4_3)) break block65;
                                                                                            v12 = var8_9;
                                                                                            if (var3_4 != 0) break block66;
                                                                                            if (v12 == null) break block67;
                                                                                            v15 = var11_13;
                                                                                            if (var3_4 != 0) break block68;
                                                                                            if (!(v15.tick() >= var8_9.tick())) break block65;
                                                                                        }
                                                                                        v12 = var11_13;
                                                                                    }
                                                                                    var8_9 = v12;
                                                                                }
                                                                                v15 = var11_13;
                                                                            }
                                                                            if (var3_4 != 0) break block69;
                                                                            v10 = v15.tick();
                                                                        }
                                                                        if (!(v10 > var4_3)) break block70;
                                                                        v15 = var9_10;
                                                                    }
                                                                    if (var3_4 != 0) break block71;
                                                                    if (v15 == null) break block72;
                                                                    v15 = var11_13;
                                                                    if (var3_4 != 0) break block71;
                                                                    if (!(v15.tick() < var9_10.tick())) break block70;
                                                                }
                                                                v15 = var11_13;
                                                            }
                                                            var9_10 = v15;
                                                        }
                                                        if (var3_4 == 0) continue;
                                                    }
                                                    v25 = var8_9;
                                                    if (var3_4 != 0) break block73;
                                                    if (v25 != null) break block74;
                                                    v25 = var9_10;
                                                    if (var3_4 != 0) break block73;
                                                    if (v25 == null) {
                                                        continue block54;
                                                    }
                                                }
                                                v25 = var8_9;
                                            }
                                            if (var3_4 != 0) break block75;
                                            if (v25 != null) break block76;
                                            var2_2[var6_7.getKey().intValue()] = var9_10.value();
                                            if (var3_4 == 0) continue block54;
                                        }
                                        v25 = var9_10;
                                    }
                                    if (var3_4 != 0) break block77;
                                    if (v25 != null) break block78;
                                    var2_2[var6_7.getKey().intValue()] = var8_9.value();
                                    if (var3_4 == 0) continue block54;
                                }
                                v25 = var9_10;
                            }
                            var10_12 = v25.tick() - var8_9.tick();
                            v33 = var10_12;
                            if (var3_4 != 0) break block79;
                            if (!(v33 <= 0.0f)) break block80;
                            v35 = 1.0f;
                            break block81;
                        }
                        v33 = var4_3 - var8_9.tick();
                    }
                    v35 = v33 / var10_12;
                }
                var11_14 = v35;
                var2_2[var6_7.getKey().intValue()] = var8_9.value() + (var9_10.value() - var8_9.value()) * EmoteImport.ease(var9_10.easing(), var11_14);
                if (var3_4 != 0) break block54;
                continue block54;
            }
            break;
        }
    }

    private static float ease(String string, float f) {
        String string2;
        block34: {
            String string3;
            String string4;
            block29: {
                int n;
                block30: {
                    float f2;
                    block33: {
                        float f3;
                        block31: {
                            block32: {
                                block27: {
                                    block28: {
                                        String string5;
                                        block26: {
                                            String string6;
                                            block24: {
                                                block25: {
                                                    n = CosmeticsSync.a;
                                                    string6 = string;
                                                    if (n != 0) break block24;
                                                    if (string6 != null) break block25;
                                                    string5 = "";
                                                    break block26;
                                                }
                                                string6 = string;
                                            }
                                            string5 = string6.toUpperCase();
                                        }
                                        string4 = string5;
                                        string2 = string4;
                                        if (n != 0) break block27;
                                        if (!string2.startsWith(a[28])) break block28;
                                        return 0.0f;
                                    }
                                    string2 = string4;
                                }
                                string3 = a[20];
                                if (n != 0) break block29;
                                if (!string2.contains(string3)) break block30;
                                f3 = f;
                                if (n != 0) break block31;
                                if (!(f3 < 0.5f)) break block32;
                                f2 = 2.0f * f * f;
                                break block33;
                            }
                            f3 = 1.0f;
                        }
                        f2 = f3 - 2.0f * (1.0f - f) * (1.0f - f);
                    }
                    return f2;
                }
                string2 = string4;
                if (n != 0) break block34;
                string3 = a[21];
            }
            if (string2.startsWith(string3)) {
                return f * f;
            }
            string2 = string4;
        }
        if (string2.startsWith(a[8])) {
            return 1.0f - (1.0f - f) * (1.0f - f);
        }
        return f;
    }

    private static List lambda$put$0(Integer n) {
        return new ArrayList();
    }

    
    static {
        block21: {
            block20: {
                block19: {
                    var9 = new String[41];
                    var7_1 = 0;
                    var6_2 = "o|Kf\u0006W\\fHU\u0003\u0003rxw\bi|Oe`\u001dM|\bi|Oe`\u0010Zv\u0006~t[dz6\nip\\xf?|rvC\u0006~t[dz6\u0007^T{H[\u0004|\u0007~{LY}2C\u0004spIi\u0004izDa\u0004utEh\u0007\u007fpO\u007fq4[\u0007\u007fpO\u007fq4[\nIPiIY\u0014\u0006om\\\nip\\xf?|rvC\u0007~{LY}2C\u0005~xGyq\u0004utEh\u0005R[gX@\u0006^T{H]\u001f\rz`Zbf0\u0005~xGyq\"\u0004ypFi\u0005vz^hg\u0018^xGyqqAveG\u007f`qXtyD-r0AwpL\u00055\u007f[bz\u001b^xGyqqNryM-3*U<5[f}!X~q\u0012-o,\bXZf^@\u0010fO\u0003bt_\u0004ypFi\u0005k|\\n|\u0007wpNyU#E\u0007wpNyX4O\u0006rfdb{!\u018d_gG}4\u0014EtaMnf0No5M`{%M;sAaq\"\b3;B~{?\u0001;|F-`9Ah5Nbx5Mi;\"\u0007@9Mb5Dbu5\bt{\by|4\bupPy42@z{Oh4%G;a@dgqNtyLhfqIuq\bld!Mzg\bdz[\\sp\bhy>\\~5_eq4D;wQ-`9Mrg\bbc?\butEh:[\"O}Mt40Z~5Xau(M\u007f5Jt4\u0001DzlM\u007f4\u0010FrxIy}>F;YAof0Zb9\by|4\bhtEh4>F~\u001fm`{%MxgIk`q]hp[!4\"G;tFt`9Aur\by|0\\;bG\u007f\u007f\"\bo}M\u007fqq_tgC~49Mip\u0004\u0007}?Kw`Ldz6\bypFig\u007f\bZ`\\e{#\br{\bOx>KpwMcw9\b}zZ-Q<GopK\u007fu7\\5\u001f\"Hy>\\~f\blf4\bbz]\u007fgk\buz\\e}?O;}M\u007fqq[s|X~4&Ao}\bLa#Git\blz5\buz\\e}?O\u0011|[-f4Lrf\\\u007f}3]opL#\u001e<Z`Zbf0\bxyA}.qSf5Keu?F~y[!43Muq[-f*U;ySp4\"@r{[-o,\u0007`h\b~d8F~5Sp\u0005ozZ~{\u001cZ`Zbf0\u0012;nU-}<Xtg\\hpqMvz\\h<\"\u0001";
                    var8_3 = "o|Kf\u0006W\\fHU\u0003\u0003rxw\bi|Oe`\u001dM|\bi|Oe`\u0010Zv\u0006~t[dz6\nip\\xf?|rvC\u0006~t[dz6\u0007^T{H[\u0004|\u0007~{LY}2C\u0004spIi\u0004izDa\u0004utEh\u0007\u007fpO\u007fq4[\u0007\u007fpO\u007fq4[\nIPiIY\u0014\u0006om\\\nip\\xf?|rvC\u0007~{LY}2C\u0005~xGyq\u0004utEh\u0005R[gX@\u0006^T{H]\u001f\rz`Zbf0\u0005~xGyq\"\u0004ypFi\u0005vz^hg\u0018^xGyqqAveG\u007f`qXtyD-r0AwpL\u00055\u007f[bz\u001b^xGyqqNryM-3*U<5[f}!X~q\u0012-o,\bXZf^@\u0010fO\u0003bt_\u0004ypFi\u0005k|\\n|\u0007wpNyU#E\u0007wpNyX4O\u0006rfdb{!\u018d_gG}4\u0014EtaMnf0No5M`{%M;sAaq\"\b3;B~{?\u0001;|F-`9Ah5Nbx5Mi;\"\u0007@9Mb5Dbu5\bt{\by|4\bupPy42@z{Oh4%G;a@dgqNtyLhfqIuq\bld!Mzg\bdz[\\sp\bhy>\\~5_eq4D;wQ-`9Mrg\bbc?\butEh:[\"O}Mt40Z~5Xau(M\u007f5Jt4\u0001DzlM\u007f4\u0010FrxIy}>F;YAof0Zb9\by|4\bhtEh4>F~\u001fm`{%MxgIk`q]hp[!4\"G;tFt`9Aur\by|0\\;bG\u007f\u007f\"\bo}M\u007fqq_tgC~49Mip\u0004\u0007}?Kw`Ldz6\bypFig\u007f\bZ`\\e{#\br{\bOx>KpwMcw9\b}zZ-Q<GopK\u007fu7\\5\u001f\"Hy>\\~f\blf4\bbz]\u007fgk\buz\\e}?O;}M\u007fqq[s|X~4&Ao}\bLa#Git\blz5\buz\\e}?O\u0011|[-f4Lrf\\\u007f}3]opL#\u001e<Z`Zbf0\bxyA}.qSf5Keu?F~y[!43Muq[-f*U;ySp4\"@r{[-o,\u0007`h\b~d8F~5Sp\u0005ozZ~{\u001cZ`Zbf0\u0012;nU-}<Xtg\\hpqMvz\\h<\"\u0001".length();
                    var5_4 = 4;
                    var4_5 = -1;
lbl7:

                    while (true) {
                        v0 = ++var4_5;
                        v1 = var6_2.substring(v0, v0 + var5_4);
                        v2 = -1;
                        break block19;
                        break;
                    }
lbl12:

                    while (true) {
                        var9[var7_1++] = v3.intern();
                        if ((var4_5 += var5_4) < var8_3) {
                            var5_4 = var6_2.charAt(var4_5);
                            continue;
                        }
                        var6_2 = "rfdb{!\t@KI na\u0005\"H";
                        var8_3 = "rfdb{!\t@KI na\u0005\"H".length();
                        var5_4 = 6;
                        var4_5 = -1;
lbl21:

                        while (true) {
                            v4 = ++var4_5;
                            v1 = var6_2.substring(v4, v4 + var5_4);
                            v2 = 0;
                            break block19;
                            break;
                        }
                        break;
                    }
lbl26:

                    while (true) {
                        var9[var7_1++] = v3.intern();
                        if ((var4_5 += var5_4) < var8_3) {
                            var5_4 = var6_2.charAt(var4_5);
                            continue;
                        }
                        break block20;
                        break;
                    }
                }
                v5 = v1.toCharArray();
                v6 = v5;
                v7 = v5.length;
                var10_7 = 0;
                do {
                    v6 = v6;
                    v8 = var10_7;
                    v9 = v6[v8];
                    switch (var10_7 % 7) {
                        case 0: {
                            v10 = 27;
                            break;
                        }
                        case 1: {
                            v10 = 21;
                            break;
                        }
                        case 2: {
                            v10 = 40;
                            break;
                        }
                        case 3: {
                            v10 = 13;
                            break;
                        }
                        case 4: {
                            v10 = 20;
                            break;
                        }
                        case 5: {
                            v10 = 81;
                            break;
                        }
                        default: {
                            v10 = 40;
                        }
                    }
                    v6[v8] = (char)(v9 ^ v10);
                    ++var10_7;
lbl65:

                    v7 = v7;
                } while (v7 > var10_7);
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
            EmoteImport.a = var9;
            break block21;
lbl77:

            while (true) {
                continue;
                break;
            }
        }
        var2_8 = 3168236472696797339L;
        while (true)
        var0_9 = -3168236472696797340L ^ var2_8;
        EmoteImport.CLIPS = new ConcurrentHashMap<String, EmoteImport$Clip>();
        EmoteImport.SERVED = new ConcurrentHashMap<String, EmoteImport$Clip>();
        EmoteImport.stamp = var0_9;
        var4_6 = EmoteImport.a;
        EmoteImport.PARTS = Map.of(var4_6[10], new int[]{0, -1, 26, 0}, var4_6[37], new int[]{3, 25, 29, 0}, var4_6[4], new int[]{6, 21, 32, 2}, var4_6[32], new int[]{9, 22, 35, 2}, var4_6[3], new int[]{12, 23, 38, 12}, var4_6[33], new int[]{15, 24, 41, 12});
    }
}
