package dev.quelchi.aurora.module.modules;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.SkyModule;
import dev.quelchi.aurora.module.modules.SkyModule$Uniform;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.ColorSetting;
import dev.quelchi.aurora.module.setting.NumberSetting;
import dev.quelchi.aurora.module.setting.Setting;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

final class SkyModule$Look {
    final String id;
    final String first;
    final String savedKey;
    final String presetKey;
    final JsonObject defaults;
    final JsonObject presets;
    final List<SkyModule$Uniform> uniforms;
    final JsonArray sections;
    final JsonObject fog;
    final Map<String, Setting> bound;
    final Map<String, JsonObject> saved;
    JsonObject look;
    String loaded;
    int loadedSig;
    private static final String[] a;
    private static final long b;

    SkyModule$Look(String string, String string2, String string3, String string4) {
        String string5;
        block26: {
            String string6;
            block24: {
                block25: {
                    JsonObject jsonObject;
                    boolean bl;
                    block22: {
                        block23: {
                            JsonArray jsonArray;
                            JsonObject jsonObject2;
                            block27: {
                                block21: {
                                    JsonObject jsonObject3;
                                    String[] stringArray;
                                    block20: {
                                        this.uniforms = new ArrayList<SkyModule$Uniform>();
                                        this.bound = new LinkedHashMap<String, Setting>();
                                        this.saved = new LinkedHashMap<String, JsonObject>();
                                        this.id = string;
                                        this.savedKey = string3;
                                        bl = AmbienceModule.b;
                                        this.presetKey = string4;
                                        try {
                                            stringArray = a;
                                            try (InputStreamReader inputStreamReader = new InputStreamReader(SkyModule.class.getResourceAsStream(stringArray[0] + string + stringArray[8]), StandardCharsets.UTF_8);){
                                                jsonObject2 = JsonParser.parseReader((Reader)inputStreamReader).getAsJsonObject();
                                            }
                                        }
                                        catch (Exception exception) {
                                            String[] stringArray2 = a;
                                            throw new IllegalStateException(string + stringArray2[6], exception);
                                        }
                                        this.defaults = jsonObject2.getAsJsonObject(a[5]);
                                        SkyModule$Look skyModule$Look = this;
                                        jsonObject3 = jsonObject2.getAsJsonObject(a[4]);
                                        if (bl) break block20;
                                        skyModule$Look.presets = jsonObject3;
                                        skyModule$Look = this;
                                        if (!jsonObject2.has(a[1])) break block21;
                                        jsonObject3 = jsonObject2;
                                    }
                                    stringArray = a;
                                    jsonArray = jsonObject3.getAsJsonArray(stringArray[11]);
                                    break block27;
                                }
                                jsonArray = new JsonArray();
                            }
                            skyModule$Look.sections = jsonArray;
                            SkyModule$Look skyModule$Look = this;
                            jsonObject = jsonObject2;
                            if (bl) break block22;
                            if (!jsonObject.has(a[7])) break block23;
                            jsonObject = jsonObject2.getAsJsonObject(a[2]);
                            break block22;
                        }
                        jsonObject = new JsonObject();
                    }
                    skyModule$Look.fog = jsonObject;
                    SkyModule$Look skyModule$Look = this;
                    string6 = string2;
                    if (bl) break block24;
                    if (string6 == null) break block25;
                    string5 = string2;
                    break block26;
                }
                string6 = this.presets.keySet().iterator().next();
            }
            string5 = string6;
        }
        skyModule$Look.first = string5;
        this.look = this.defaults.deepCopy();
        this.loaded = this.first;
    }

    SkyModule$Look(String string) {
        String[] stringArray = a;
        this(string, null, string + stringArray[3], string + stringArray[10]);
    }

    
    void load(JsonObject jsonObject) {
        this.overlay(jsonObject);
        boolean bl = AmbienceModule.b;
        for (Map.Entry<String, Setting> entry : this.bound.entrySet()) {
            block9: {
                Setting setting;
                JsonPrimitive jsonPrimitive;
                block12: {
                    Setting setting2;
                    Setting setting3;
                    block10: {
                        block11: {
                            block7: {
                                block8: {
                                    jsonPrimitive = this.look.getAsJsonPrimitive(entry.getKey());
                                    setting3 = entry.getValue();
                                    if (bl) break block7;
                                    if (!(setting3 instanceof NumberSetting)) break block8;
                                    NumberSetting numberSetting = (NumberSetting)setting3;
                                    numberSetting.setExact(jsonPrimitive.getAsDouble());
                                    if (!bl) break block9;
                                }
                                setting3 = entry.getValue();
                            }
                            setting2 = setting3;
                            if (bl) break block10;
                            if (!(setting2 instanceof BooleanSetting)) break block11;
                            BooleanSetting booleanSetting = (BooleanSetting)setting3;
                            booleanSetting.setValue(jsonPrimitive.getAsBoolean());
                            if (!bl) break block9;
                        }
                        setting = entry.getValue();
                        if (bl) break block12;
                        setting2 = setting3 = setting;
                    }
                    if (!(setting2 instanceof ColorSetting)) break block9;
                    setting = setting3;
                }
                ColorSetting colorSetting = (ColorSetting)setting;
                colorSetting.setARGB((int)b | SkyModule.hex(jsonPrimitive.getAsString()));
            }
            if (!bl) continue;
        }
    }

    void overlay(JsonObject jsonObject) {
        this.look = this.defaults.deepCopy();
        Iterator iterator = this.defaults.entrySet().iterator();
        boolean bl = AmbienceModule.b;
        while (iterator.hasNext()) {
            boolean bl2;
            JsonPrimitive jsonPrimitive;
            Map.Entry entry;
            block31: {
                block36: {
                    JsonElement jsonElement;
                    block35: {
                        block32: {
                            block33: {
                                block34: {
                                    block29: {
                                        JsonElement jsonElement2;
                                        block30: {
                                            JsonElement jsonElement3;
                                            block28: {
                                                entry = (Map.Entry)iterator.next();
                                                jsonElement2 = jsonObject.get((String)entry.getKey());
                                                jsonElement3 = jsonElement2;
                                                if (bl) break block28;
                                                if (!(jsonElement3 instanceof JsonPrimitive)) continue;
                                                jsonElement3 = jsonElement2;
                                            }
                                            jsonPrimitive = (JsonPrimitive)jsonElement3;
                                            jsonElement2 = ((JsonElement)entry.getValue()).getAsJsonPrimitive();
                                            jsonElement = jsonElement2;
                                            if (bl) break block29;
                                            if (!jsonElement.isBoolean()) break block30;
                                            bl2 = jsonPrimitive.isBoolean();
                                            break block31;
                                        }
                                        jsonElement = jsonElement2;
                                    }
                                    if (bl) break block32;
                                    if (!jsonElement.isNumber()) break block33;
                                    bl2 = jsonPrimitive.isNumber();
                                    if (bl) break block31;
                                    if (!bl2) break block34;
                                    bl2 = Double.isFinite(jsonPrimitive.getAsDouble());
                                    if (bl) break block31;
                                    if (!bl2) break block34;
                                    bl2 = true;
                                    break block31;
                                }
                                bl2 = false;
                                break block31;
                            }
                            jsonElement = jsonPrimitive;
                        }
                        if (bl) break block35;
                        if (!jsonElement.isString()) break block36;
                        jsonElement = jsonPrimitive;
                    }
                    if (SkyModule.hex(jsonElement.getAsString()) == null) break block36;
                    bl2 = true;
                    break block31;
                }
                bl2 = false;
            }
            boolean bl3 = bl2;
            if (bl3) {
                this.look.add((String)entry.getKey(), (JsonElement)jsonPrimitive);
            }
            if (!bl) continue;
        }
    }

    
    JsonObject snapshot() {
        JsonObject jsonObject = this.look.deepCopy();
        Iterator<Map.Entry<String, Setting>> iterator = this.bound.entrySet().iterator();
        boolean bl = AmbienceModule.b;
        while (iterator.hasNext()) {
            block9: {
                Object object;
                Map.Entry<String, Setting> entry;
                block12: {
                    Object object2;
                    Setting setting;
                    block10: {
                        block11: {
                            block7: {
                                block8: {
                                    entry = iterator.next();
                                    setting = entry.getValue();
                                    if (bl) break block7;
                                    if (!(setting instanceof NumberSetting)) break block8;
                                    NumberSetting numberSetting = (NumberSetting)setting;
                                    jsonObject.addProperty(entry.getKey(), (Number)numberSetting.getValue());
                                    if (!bl) break block9;
                                }
                                object2 = entry;
                                if (bl) break block10;
                                setting = object2.getValue();
                            }
                            if (!(setting instanceof BooleanSetting)) break block11;
                            BooleanSetting booleanSetting = (BooleanSetting)setting;
                            jsonObject.addProperty(entry.getKey(), Boolean.valueOf(booleanSetting.getValue()));
                            if (!bl) break block9;
                        }
                        object = entry;
                        if (bl) break block12;
                        setting = object.getValue();
                        object2 = setting;
                    }
                    if (!(object2 instanceof ColorSetting)) break block9;
                    object = setting;
                }
                ColorSetting colorSetting = (ColorSetting)object;
                jsonObject.addProperty(entry.getKey(), String.format(a[9], colorSetting.getRGB()));
            }
            if (!bl) continue;
        }
        return jsonObject;
    }

    int sig() {
        return this.snapshot().hashCode();
    }

    
    static {
        block21: {
            block20: {
                block19: {
                    var7 = new String[12];
                    var5_1 = 0;
                    var4_2 = "52OGT\u0014\t52IF^\u0012\u001b5 WM\u001e\bi6_@X\u000f\u0014i\u0003|<[\u0005I2JQU\u0007j!YGT\u0014\t\b~6ZUD\f\u000ei\u001a49O[_@\u0017s O]_\u0007Z|!SY\u0011\u0014\u0012\u007fsVUC\u0003|<[\u000549O[_\u00059v\f\u0002I";
                    var6_3 = "52OGT\u0014\t52IF^\u0012\u001b5 WM\u001e\bi6_@X\u000f\u0014i\u0003|<[\u0005I2JQU\u0007j!YGT\u0014\t\b~6ZUD\f\u000ei\u001a49O[_@\u0017s O]_\u0007Z|!SY\u0011\u0014\u0012\u007fsVUC\u0003|<[\u000549O[_\u00059v\f\u0002I".length();
                    var3_4 = 19;
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
                        var4_2 = "J!YGT\u0014\bi6_@X\u000f\u0014i";
                        var6_3 = "J!YGT\u0014\bi6_@X\u000f\u0014i".length();
                        var3_4 = 6;
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
                            v10 = 26;
                            break;
                        }
                        case 1: {
                            v10 = 83;
                            break;
                        }
                        case 2: {
                            v10 = 60;
                            break;
                        }
                        case 3: {
                            v10 = 52;
                            break;
                        }
                        case 4: {
                            v10 = 49;
                            break;
                        }
                        case 5: {
                            v10 = 96;
                            break;
                        }
                        default: {
                            v10 = 122;
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
            SkyModule$Look.a = var7;
            break block21;
lbl77:

            while (true) {
                continue;
                break;
            }
        }
        var0_7 = 861148522454929201L;
        while (true)
        SkyModule$Look.b = -6243860442716352719L ^ var0_7;
    }
}
