package dev.quelchi.aurora.cobweb;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.quelchi.aurora.cobweb.CobwebTint;
import dev.quelchi.aurora.module.Module;
import java.io.Reader;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CobwebTextureResolver {
    private static final Logger LOGGER;
    private static final Identifier VANILLA_COBWEB;
    private static final int MAX_MODEL_DEPTH;
    private static volatile ResourceManager manager;
    private static volatile Set<Identifier> targets;
    private static final String[] a;

    private CobwebTextureResolver() {
    }

    public static void beginReload(ResourceManager resourceManager) {
        manager = resourceManager;
        targets = null;
        CobwebTint.reset();
    }

    
    public static boolean isTarget(Identifier identifier) {
        boolean bl;
        block24: {
            boolean bl2;
            block26: {
                block25: {
                    Set<Identifier> set;
                    boolean bl3;
                    block16: {
                        Set<Identifier> set2;
                        block17: {
                            Class<CobwebTextureResolver> clazz;
                            set2 = targets;
                            bl3 = CobwebTint.a;
                            set = set2;
                            if (bl3) break block16;
                            if (set != null) break block17;
                            Class<CobwebTextureResolver> clazz2 = clazz = CobwebTextureResolver.class;
                            if (bl3) break block17;
                            synchronized (clazz2) {
                                set2 = targets;
                                if (set2 == null) {
                                    ResourceManager resourceManager;
                                    block18: {
                                        ResourceManager resourceManager2;
                                        block19: {
                                            MinecraftClient minecraftClient;
                                            block22: {
                                                MinecraftClient minecraftClient2;
                                                block23: {
                                                    block21: {
                                                        block20: {
                                                            resourceManager2 = manager;
                                                            resourceManager = resourceManager2;
                                                            if (bl3) break block18;
                                                            if (resourceManager != null) break block19;
                                                            minecraftClient2 = MinecraftClient.method_1551();
                                                            minecraftClient = minecraftClient2;
                                                            if (bl3) break block20;
                                                            if (minecraftClient == null) break block21;
                                                            minecraftClient = minecraftClient2;
                                                        }
                                                        if (bl3) break block22;
                                                        if (minecraftClient.method_1478() != null) break block23;
                                                    }
                                                    return VANILLA_COBWEB.equals((Object)identifier);
                                                }
                                                minecraftClient = minecraftClient2;
                                            }
                                            resourceManager2 = minecraftClient.method_1478();
                                        }
                                        resourceManager = resourceManager2;
                                    }
                                    targets = set2 = CobwebTextureResolver.resolve(resourceManager);
                                }
                                clazz2 = clazz;
                            }
                        }
                        set = set2;
                    }
                    bl = set.contains(identifier);
                    if (!Module.a) break block24;
                    if (!bl3) break block25;
                    bl2 = false;
                    break block26;
                }
                bl2 = true;
            }
            CobwebTint.a = bl2;
        }
        return bl;
    }

    private static Set<Identifier> resolve(ResourceManager resourceManager) {
        LinkedHashSet<Identifier> linkedHashSet;
        block5: {
            linkedHashSet = new LinkedHashSet<Identifier>();
            boolean bl = CobwebTint.a;
            try {
                LinkedHashSet<Identifier> linkedHashSet2 = new LinkedHashSet<Identifier>();
                String[] stringArray = a;
                linkedHashSet2.add(Identifier.method_60656((String)stringArray[10]));
                CobwebTextureResolver.collectModelRefs(resourceManager, Identifier.method_60656((String)stringArray[9]), linkedHashSet2);
                CobwebTextureResolver.collectModelRefs(resourceManager, Identifier.method_60656((String)stringArray[7]), linkedHashSet2);
                HashSet<Identifier> hashSet = new HashSet<Identifier>();
                for (Identifier identifier : linkedHashSet2) {
                    if (bl) break block5;
                    CobwebTextureResolver.walkModel(resourceManager, identifier, hashSet, linkedHashSet, 0);
                    if (!bl) continue;
                    break;
                }
            }
            catch (Exception exception) {
                String[] stringArray = a;
                LOGGER.warn(stringArray[12], (Throwable)exception);
            }
            linkedHashSet.add(VANILLA_COBWEB);
            LOGGER.info(a[15], linkedHashSet);
        }
        return linkedHashSet;
    }

    private static void collectModelRefs(ResourceManager resourceManager, Identifier identifier, Set<Identifier> set) {
        block3: {
            JsonElement jsonElement;
            block2: {
                JsonElement jsonElement2 = CobwebTextureResolver.readJson(resourceManager, identifier);
                boolean bl = CobwebTint.a;
                jsonElement = jsonElement2;
                if (bl) break block2;
                if (jsonElement == null) break block3;
                jsonElement = jsonElement2;
            }
            CobwebTextureResolver.collectModelRefs(jsonElement, set);
        }
    }

    
    private static void collectModelRefs(JsonElement jsonElement, Set<Identifier> set) {
        block20: {
            JsonElement jsonElement2;
            boolean bl;
            block26: {
                block21: {
                    block22: {
                        bl = CobwebTint.a;
                        jsonElement2 = jsonElement;
                        if (bl) break block21;
                        if (!jsonElement2.isJsonObject()) break block22;
                        for (Object object : jsonElement.getAsJsonObject().entrySet()) {
                            block23: {
                                Object object2;
                                block25: {
                                    block24: {
                                        if (bl) break block20;
                                        if (bl) break block23;
                                        if (!a[2].equals(object.getKey())) break block24;
                                        object2 = object.getValue();
                                        if (bl) break block25;
                                        if (!((JsonElement)object2).isJsonPrimitive()) break block24;
                                        object2 = object;
                                        if (bl) break block25;
                                        if (((JsonElement)object2.getValue()).getAsJsonPrimitive().isString()) {
                                            set.add(Identifier.method_60654((String)((JsonElement)object.getValue()).getAsString()));
                                        }
                                    }
                                    object2 = object.getValue();
                                }
                                CobwebTextureResolver.collectModelRefs((JsonElement)object2, set);
                            }
                            if (!bl) continue;
                        }
                        if (!bl) break block20;
                    }
                    jsonElement2 = jsonElement;
                }
                if (bl) break block26;
                if (!jsonElement2.isJsonArray()) break block20;
                jsonElement2 = jsonElement;
            }
            for (Object object : jsonElement2.getAsJsonArray()) {
                CobwebTextureResolver.collectModelRefs((JsonElement)object, set);
                if (!bl) continue;
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void walkModel(ResourceManager resourceManager, Identifier identifier, Set<Identifier> set, Set<Identifier> set2, int n) {
        JsonObject jsonObject;
        JsonObject jsonObject2;
        block55: {
            String string;
            block50: {
                boolean bl;
                block51: {
                    JsonElement jsonElement;
                    String[] stringArray;
                    block48: {
                        JsonElement jsonElement2;
                        block49: {
                            block47: {
                                String string2;
                                block46: {
                                    int n2;
                                    block45: {
                                        block44: {
                                            bl = CobwebTint.a;
                                            n2 = n;
                                            if (bl) break block44;
                                            if (n2 > 24) return;
                                            n2 = set.add(identifier) ? 1 : 0;
                                        }
                                        if (bl) break block45;
                                        if (n2 == 0) return;
                                        string2 = identifier.method_12832();
                                        if (bl) break block46;
                                        n2 = string2.startsWith(a[8]) ? 1 : 0;
                                    }
                                    if (n2 != 0) {
                                        return;
                                    }
                                    string2 = identifier.method_12836();
                                }
                                stringArray = a;
                                Identifier identifier2 = Identifier.method_60655((String)string2, (String)(stringArray[11] + identifier.method_12832() + stringArray[0]));
                                jsonElement2 = CobwebTextureResolver.readJson(resourceManager, identifier2);
                                jsonElement = jsonElement2;
                                if (bl) break block47;
                                if (jsonElement == null) return;
                                jsonElement = jsonElement2;
                            }
                            if (bl) break block48;
                            if (jsonElement.isJsonObject()) break block49;
                            return;
                        }
                        jsonElement = jsonElement2;
                    }
                    jsonObject2 = jsonElement.getAsJsonObject();
                    jsonObject = jsonObject2;
                    string = a[1];
                    if (bl) break block50;
                    if (!jsonObject.has(string)) break block51;
                    jsonObject = jsonObject2;
                    string = a[14];
                    if (bl) break block50;
                    if (!jsonObject.get(string).isJsonObject()) break block51;
                    stringArray = a;
                    for (Map.Entry entry : jsonObject2.get(stringArray[14]).getAsJsonObject().entrySet()) {
                        block53: {
                            JsonElement jsonElement3;
                            block54: {
                                Map.Entry entry2;
                                block52: {
                                    if (bl) return;
                                    entry2 = entry;
                                    if (bl) break block52;
                                    if (!((JsonElement)entry2.getValue()).isJsonPrimitive()) break block53;
                                    entry2 = entry.getValue();
                                }
                                jsonElement3 = (JsonElement)entry2;
                                if (bl) break block54;
                                if (!jsonElement3.getAsJsonPrimitive().isString()) break block53;
                                jsonElement3 = (JsonElement)entry.getValue();
                            }
                            String string3 = jsonElement3.getAsString();
                            boolean bl2 = string3.startsWith("#");
                            if (bl || bl2) break block53;
                            bl2 = set2.add(Identifier.method_60654((String)string3));
                        }
                        if (!bl) continue;
                    }
                }
                jsonObject = jsonObject2;
                if (bl) break block55;
                string = a[6];
            }
            if (!jsonObject.has(string)) return;
            jsonObject = jsonObject2.get(a[3]);
        }
        if (!jsonObject.isJsonPrimitive()) return;
        CobwebTextureResolver.walkModel(resourceManager, Identifier.method_60654((String)jsonObject2.get(a[3]).getAsString()), set, set2, n + 1);
        return;
    }

    
    private static JsonElement readJson(ResourceManager var0, Identifier var1_1) {
        block17: {
            block16: {
                block15: {
                    block19: {
                        var3_2 = var0.method_14486(var1_1);
                        var2_3 = CobwebTint.a;
                        v0 /* !! */  = var3_2;
                        if (var2_3) // goto
                        if (!v0 /* !! */ .isEmpty()) break block15;
                        break block19;
                        catch (Throwable v1) {
                            throw v1;
                        }
                    }
                    return null;
                }
                v0 /* !! */  = var3_2.get();
lbl18:

                var4_4 = ((Resource)v0 /* !! */ ).method_43039();
                try {
                    var5_6 = JsonParser.parseReader((Reader)var4_4);
                }
                catch (Throwable var5_7) {
                    try {
                        block18: {
                            if (var2_3 || var4_4 == null) break block18;
                            try {
                                var4_4.close();
                            }
                            catch (Throwable var6_8) {
                                var5_7.addSuppressed(var6_8);
                            }
                        }
                        throw var5_7;
                    }
                    catch (Exception var4_5) {
                        CobwebTextureResolver.LOGGER.warn(CobwebTextureResolver.a[13], (Object)var1_1, (Object)var4_5);
                        return null;
                    }
                }
                v4 = var4_4;
                if (var2_3) break block16;
                if (v4 == null) break block17;
                v4 = var4_4;
            }
            v4.close();
        }
        return var5_6;
    }

    
    static {
        var9 = new String[16];
        var7_1 = 0;
        var6_2 = "5)\u001fVp\bo&\u0014Mk\u00008h\u0005v,\b\\r\u0006k\"\u001e\\p\u0006\fy/\u0003Zu]>t!\u001b\\|\rZ6\u001eVl\u0013rX,\u000eN{\u0010\u0006k\"\u001e\\p\u0006\u0011r7\tTm]>t!\u001b\\|\\7h,\u0002\by6\u0005Uj\u001b34\u0017y/\u0003Zu\u0001)z7\tJ1\u00112y4\t[0\u0018.t-\fy/\u0003Zu]>t!\u001b\\|\u0007v,\b\\r\u0001r6@ \u0003[i\u0017?Fc\nXw\u001e8\u007fc\u0018V>\u00008h,\u0000O{R>t!\u001b\\|R)~;\u0018Ll\u0017.;%\u001eVsR0t'\tUm\u001b@ \u0003[i\u0017?Fc\u000fVk\u001e9;-\u0003M>\u0002<i0\t\u0019e\u000f";
        var8_3 = "5)\u001fVp\bo&\u0014Mk\u00008h\u0005v,\b\\r\u0006k\"\u001e\\p\u0006\fy/\u0003Zu]>t!\u001b\\|\rZ6\u001eVl\u0013rX,\u000eN{\u0010\u0006k\"\u001e\\p\u0006\u0011r7\tTm]>t!\u001b\\|\\7h,\u0002\by6\u0005Uj\u001b34\u0017y/\u0003Zu\u0001)z7\tJ1\u00112y4\t[0\u0018.t-\fy/\u0003Zu]>t!\u001b\\|\u0007v,\b\\r\u0001r6@ \u0003[i\u0017?Fc\nXw\u001e8\u007fc\u0018V>\u00008h,\u0000O{R>t!\u001b\\|R)~;\u0018Ll\u0017.;%\u001eVsR0t'\tUm\u001b@ \u0003[i\u0017?Fc\u000fVk\u001e9;-\u0003M>\u0002<i0\t\u0019e\u000f".length();
        var5_4 = 5;
        var4_5 = -1;
        block11: while (true) {
            v0 = ++var4_5;
            v1 = var6_2.substring(v0, v0 + var5_4);
            v2 = -1;
            block12: while (true) {
                var6_2 = "o&\u0014Mk\u00008h\"@ \u0003[i\u0017?Fc\nXz\u0017}o\"\u001e^{\u0006.;1\tJq\u001e+~'V\u0019e\u000f";
                var8_3 = "o&\u0014Mk\u00008h\"@ \u0003[i\u0017?Fc\nXz\u0017}o\"\u001e^{\u0006.;1\tJq\u001e+~'V\u0019e\u000f".length();
                var5_4 = 8;
                var4_5 = -1;
                while (true) {
                    v3 = ++var4_5;
                    v1 = var6_2.substring(v3, v3 + var5_4);
                    v2 = 0;
lbl21:

                    v4 = v1.toCharArray();
                    v5 = v4;
                    v6 = v4.length;
                    var10_7 = 0;
                    do {
                        v5 = v5;
                        v7 = var10_7;
                        v8 = v5[v7];
                        switch (var10_7 % 7) {
                            case 0: {
                                v9 = 27;
                                break;
                            }
                            case 1: {
                                v9 = 67;
                                break;
                            }
                            case 2: {
                                v9 = 108;
                                break;
                            }
                            case 3: {
                                v9 = 57;
                                break;
                            }
                            case 4: {
                                v9 = 30;
                                break;
                            }
                            case 5: {
                                v9 = 114;
                                break;
                            }
                            default: {
                                v9 = 93;
                            }
                        }
                        v5[v7] = (char)(v8 ^ v9);
                        ++var10_7;
lbl53:

                        v6 = v6;
                    } while (v6 > var10_7);
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
                        CobwebTextureResolver.a = var9;
                        var2_8 = 7336360034334701925L;
                        var0_9 = 4207979652056381821L ^ var2_8;
                        CobwebTextureResolver.MAX_MODEL_DEPTH = (int)var0_9;
                        var4_6 = CobwebTextureResolver.a;
                        CobwebTextureResolver.LOGGER = LoggerFactory.getLogger((String)var4_6[5]);
                        CobwebTextureResolver.VANILLA_COBWEB = Identifier.method_60656((String)var4_6[4]);
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
