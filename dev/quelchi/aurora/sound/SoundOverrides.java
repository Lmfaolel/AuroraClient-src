package dev.quelchi.aurora.sound;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.sound.SoundOverrides$Override;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import net.minecraft.util.Identifier;

public final class SoundOverrides {
    private static final int HEARD_CAP;
    private static final Map<Identifier, SoundOverrides$Override> ENTRIES;
    private static final LinkedHashSet<Identifier> HEARD;
    public static boolean a;
    private static final String[] b;

    private SoundOverrides() {
    }

    public static Map<Identifier, SoundOverrides$Override> all() {
        return ENTRIES;
    }

    public static boolean isEmpty() {
        return ENTRIES.isEmpty();
    }

    public static SoundOverrides$Override forSound(Identifier identifier) {
        SoundOverrides$Override soundOverrides$Override;
        block6: {
            Object object;
            block4: {
                block5: {
                    boolean bl = a;
                    object = identifier;
                    if (bl) break block4;
                    if (object != null) break block5;
                    soundOverrides$Override = null;
                    break block6;
                }
                object = ENTRIES.get(identifier);
            }
            soundOverrides$Override = (SoundOverrides$Override)object;
        }
        return soundOverrides$Override;
    }

    public static SoundOverrides$Override get(Identifier identifier) {
        return ENTRIES.getOrDefault(identifier, SoundOverrides$Override.DEFAULT);
    }

    public static void set(Identifier identifier, SoundOverrides$Override soundOverrides$Override) {
        block6: {
            boolean bl = a;
            if (identifier == null) break block6;
            SoundOverrides$Override soundOverrides$Override2 = soundOverrides$Override;
            if (bl) break block6;
            if (soundOverrides$Override2 == null) break block6;
            soundOverrides$Override2 = ENTRIES.put(identifier, soundOverrides$Override);
        }
    }

    public static Identifier add(Identifier identifier) {
        Identifier identifier2;
        block4: {
            block5: {
                boolean bl = a;
                identifier2 = identifier;
                if (bl) break block4;
                if (identifier2 == null) break block5;
                ENTRIES.putIfAbsent(identifier, SoundOverrides$Override.DEFAULT);
            }
            identifier2 = identifier;
        }
        return identifier2;
    }

    public static void remove(Identifier identifier) {
        ENTRIES.remove(identifier);
    }

    
    public static void noteHeard(Identifier identifier) {
        boolean bl = a;
        if (identifier == null) {
            return;
        }
        LinkedHashSet<Identifier> linkedHashSet = HEARD;
        synchronized (linkedHashSet) {
            block10: {
                LinkedHashSet<Identifier> linkedHashSet2;
                HEARD.remove(identifier);
                HEARD.add(identifier);
                while (HEARD.size() > 64) {
                    linkedHashSet2 = HEARD;
                    if (!bl) {
                        linkedHashSet2.remove(HEARD.iterator().next());
                        if (!bl) continue;
                        break;
                    }
                    break block10;
                }
                linkedHashSet2 = linkedHashSet;
            }
            return;
        }
    }

    
    public static List<Identifier> heard() {
        LinkedHashSet<Identifier> linkedHashSet = HEARD;
        // MONITORENTER : linkedHashSet
        boolean bl = a;
        ArrayList<Identifier> arrayList2 = new ArrayList<Identifier>(HEARD);
        Collections.reverse(arrayList2);
        if (!Module.a) return arrayList2;
        a = !bl;
        return arrayList2;
    }

    public static void write(JsonObject jsonObject) {
        block7: {
            JsonObject jsonObject2 = new JsonObject();
            Iterator<Map.Entry<Identifier, SoundOverrides$Override>> iterator = ENTRIES.entrySet().iterator();
            boolean bl = a;
            while (iterator.hasNext()) {
                block8: {
                    JsonObject jsonObject3;
                    Map.Entry<Identifier, SoundOverrides$Override> entry;
                    block9: {
                        entry = iterator.next();
                        SoundOverrides$Override soundOverrides$Override = entry.getValue();
                        if (bl) break block7;
                        jsonObject3 = new JsonObject();
                        if (bl) break block8;
                        jsonObject3.addProperty(b[0], (Number)Float.valueOf(soundOverrides$Override.volume()));
                        jsonObject3.addProperty(b[4], (Number)Float.valueOf(soundOverrides$Override.pitch()));
                        if (!soundOverrides$Override.muted()) break block9;
                        jsonObject3.addProperty(b[1], Boolean.valueOf(true));
                    }
                    jsonObject2.add(entry.getKey().toString(), (JsonElement)jsonObject3);
                }
                if (!bl) continue;
                boolean bl2 = !Module.a;
                Module.a = bl2;
                break;
            }
            jsonObject.add(b[2], (JsonElement)jsonObject2);
        }
    }

    
    public static void read(JsonObject jsonObject) {
        String[] stringArray;
        JsonObject jsonObject2;
        boolean bl;
        block14: {
            block15: {
                block12: {
                    String string;
                    block13: {
                        block11: {
                            bl = a;
                            jsonObject2 = jsonObject;
                            if (bl) break block11;
                            if (jsonObject2 == null) break block12;
                            jsonObject2 = jsonObject;
                        }
                        string = b[6];
                        if (bl) break block13;
                        if (!jsonObject2.has(string)) break block12;
                        jsonObject2 = jsonObject;
                        if (bl) break block14;
                        string = b[6];
                    }
                    if (jsonObject2.get(string).isJsonObject()) break block15;
                }
                return;
            }
            ENTRIES.clear();
            stringArray = b;
            jsonObject2 = jsonObject.getAsJsonObject(stringArray[6]);
        }
        JsonObject jsonObject3 = jsonObject2;
        for (String string : jsonObject3.keySet()) {
            try {
                JsonObject jsonObject4;
                Identifier identifier = Identifier.method_12829((String)string);
                if (identifier == null && !bl) continue;
                stringArray = b;
                float f = jsonObject4.has(stringArray[3]) ? jsonObject4.get(stringArray[3]).getAsFloat() : 1.0f;
                stringArray = b;
                jsonObject4 = jsonObject3.getAsJsonObject(string);
                ENTRIES.put(identifier, new SoundOverrides$Override(jsonObject4.has(b[7]) ? jsonObject4.get(b[7]).getAsFloat() : 1.0f, f, jsonObject4.has(stringArray[5]) && jsonObject4.get(stringArray[5]).getAsBoolean()));
            }
            catch (Exception exception) {
            }
            if (!bl) continue;
        }
    }

    
    static {
        var9 = new String[8];
        var7_1 = 0;
        var6_2 = "0U704\u000f\u0005+O/ =\u00065U.+=\u0019\u00056S/&1\u00056S/&1\u0005+O/ =";
        var8_3 = "0U704\u000f\u0005+O/ =\u00065U.+=\u0019\u00056S/&1\u00056S/&1\u0005+O/ =".length();
        var5_4 = 6;
        var4_5 = -1;
        block11: while (true) {
            v0 = ++var4_5;
            v1 = var6_2.substring(v0, v0 + var5_4);
            v2 = -1;
            block12: while (true) {
                var6_2 = "5U.+=\u0019\u00060U704\u000f";
                var8_3 = "5U.+=\u0019\u00060U704\u000f".length();
                var5_4 = 6;
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
                                v9 = 70;
                                break;
                            }
                            case 1: {
                                v9 = 58;
                                break;
                            }
                            case 2: {
                                v9 = 91;
                                break;
                            }
                            case 3: {
                                v9 = 69;
                                break;
                            }
                            case 4: {
                                v9 = 89;
                                break;
                            }
                            case 5: {
                                v9 = 106;
                                break;
                            }
                            default: {
                                v9 = 24;
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
                        SoundOverrides.b = var9;
                        var2_7 = 4647759879127319586L;
                        var0_8 = -2764267768597298078L ^ var2_7;
                        SoundOverrides.HEARD_CAP = (int)var0_8;
                        SoundOverrides.ENTRIES = new LinkedHashMap<Identifier, SoundOverrides$Override>();
                        SoundOverrides.HEARD = new LinkedHashSet<E>();
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
