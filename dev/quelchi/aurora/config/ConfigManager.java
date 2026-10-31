package dev.quelchi.aurora.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.config.ModImport;
import dev.quelchi.aurora.cosmetics.content.Cosmetics;
import dev.quelchi.aurora.crosshair.IconStore;
import dev.quelchi.aurora.gui.AuroraBackdrop;
import dev.quelchi.aurora.gui.SocialToast;
import dev.quelchi.aurora.gui.im.Themes;
import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.HudScales;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.ColorSetting;
import dev.quelchi.aurora.module.setting.EnumSetting;
import dev.quelchi.aurora.module.setting.KeybindSetting;
import dev.quelchi.aurora.module.setting.NumberSetting;
import dev.quelchi.aurora.module.setting.Setting;
import dev.quelchi.aurora.module.setting.StringSetting;
import dev.quelchi.aurora.social.Badges;
import dev.quelchi.aurora.social.Social;
import dev.quelchi.aurora.util.BorderlessFit;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.UUID;
import net.fabricmc.loader.api.FabricLoader;

public final class ConfigManager {
    private static final Gson GSON;
    private static final Path PATH;
    private static final Map<Category, int[]> windowPos;
    private static final Map<String, String> LEGACY_NAMES;
    private static final Map<String, String> LEGACY_SETTING_KEYS;
    public static boolean a;
    private static final String[] b;
    private static final long c;
    private static final long d;

    private ConfigManager() {
    }

    public static Path path() {
        return PATH;
    }

    public static void save() {
        boolean bl = a;
        try {
            String[] stringArray;
            JsonObject jsonObject;
            block26: {
                JsonObject jsonObject2;
                block25: {
                    block24: {
                        JsonObject jsonObject3;
                        JsonObject jsonObject4;
                        block23: {
                            block22: {
                                jsonObject = new JsonObject();
                                stringArray = b;
                                jsonObject.addProperty(stringArray[131], (Number)Aurora.INSTANCE.getOpenGuiKey());
                                jsonObject.addProperty(stringArray[86], (Number)AuroraBackdrop.current());
                                JsonObject jsonObject5 = new JsonObject();
                                for (Module object22 : Aurora.INSTANCE.getModuleManager().getModules()) {
                                    jsonObject5.add(object22.getName(), (JsonElement)ConfigManager.writeModule(object22));
                                    if (!bl) {
                                        if (!bl) continue;
                                        break;
                                    }
                                    break block22;
                                }
                                stringArray = b;
                                jsonObject.add(stringArray[65], (JsonElement)jsonObject5);
                            }
                            JsonObject jsonObject6 = new JsonObject();
                            for (Map.Entry<Category, int[]> entry : windowPos.entrySet()) {
                                jsonObject4 = new JsonObject();
                                if (!bl) {
                                    jsonObject3 = jsonObject4;
                                    jsonObject3.addProperty("x", (Number)entry.getValue()[0]);
                                    jsonObject3.addProperty("y", (Number)entry.getValue()[1]);
                                    jsonObject6.add(entry.getKey().name(), (JsonElement)jsonObject3);
                                    if (!bl) continue;
                                }
                                break block23;
                            }
                            stringArray = b;
                            jsonObject.add(stringArray[130], (JsonElement)jsonObject6);
                            IconStore.write(jsonObject);
                            HudScales.write(jsonObject);
                            jsonObject4 = new JsonObject();
                        }
                        JsonObject jsonObject5 = jsonObject4;
                        stringArray = b;
                        jsonObject5.addProperty(stringArray[67], Cosmetics.getOwnCape());
                        jsonObject5.addProperty(stringArray[46], Cosmetics.getOwnCapeUrl());
                        jsonObject5.addProperty(stringArray[104], Cosmetics.getChosenBetaCape());
                        JsonObject jsonObject6 = new JsonObject();
                        Cosmetics.capeIds().forEach((arg_0, arg_1) -> ((JsonObject)jsonObject6).addProperty(arg_0, arg_1));
                        jsonObject5.add(stringArray[85], (JsonElement)jsonObject6);
                        jsonObject5.addProperty(stringArray[50], Cosmetics.getOwnWings());
                        jsonObject5.addProperty(stringArray[133], Cosmetics.getOwnTrail());
                        jsonObject5.addProperty(stringArray[148], Cosmetics.getOwnKillEffect());
                        jsonObject5.addProperty(stringArray[212], Cosmetics.getOwnMaceEffect());
                        jsonObject5.addProperty(stringArray[36], Cosmetics.getOwnHitEffect());
                        jsonObject5.addProperty(stringArray[199], Boolean.valueOf(Cosmetics.showOthers()));
                        jsonObject3 = new JsonArray();
                        for (int i = 0; i < 8; ++i) {
                            jsonObject3.add(Cosmetics.wheelSlot(i));
                            if (!bl) {
                                if (!bl) continue;
                                break;
                            }
                            break block24;
                        }
                        stringArray = b;
                        jsonObject5.add(stringArray[101], (JsonElement)jsonObject3);
                        jsonObject.add(stringArray[38], (JsonElement)jsonObject5);
                        Themes.write(jsonObject);
                        BorderlessFit.write(jsonObject);
                    }
                    jsonObject2 = new JsonObject();
                    stringArray = b;
                    jsonObject2.addProperty(stringArray[211], Boolean.valueOf(SocialToast.enabled()));
                    jsonObject2.addProperty(stringArray[30], Boolean.valueOf(SocialToast.sound()));
                    jsonObject2.addProperty(stringArray[84], Boolean.valueOf(SocialToast.requests()));
                    jsonObject2.addProperty(stringArray[87], Boolean.valueOf(SocialToast.online()));
                    JsonArray jsonArray = new JsonArray();
                    for (UUID uUID : Social.mutedList()) {
                        if (!bl) {
                            jsonArray.add(uUID.toString());
                            if (!bl) continue;
                            break;
                        }
                        break block25;
                    }
                    stringArray = b;
                    jsonObject2.add(stringArray[7], (JsonElement)jsonArray);
                }
                JsonArray jsonArray = new JsonArray();
                for (String string : Badges.seenList()) {
                    if (!bl) {
                        jsonArray.add(string);
                        if (!bl) continue;
                        break;
                    }
                    break block26;
                }
                stringArray = b;
                jsonObject2.add(stringArray[201], (JsonElement)jsonArray);
                jsonObject.add(stringArray[107], (JsonElement)jsonObject2);
                Files.createDirectories(PATH.getParent(), new FileAttribute[0]);
            }
            stringArray = b;
            Path path = PATH.resolveSibling(String.valueOf(PATH.getFileName()) + stringArray[100]);
            Files.writeString(path, (CharSequence)GSON.toJson((JsonElement)jsonObject), new OpenOption[0]);
            try {
                Files.move(path, PATH, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
            }
            catch (AtomicMoveNotSupportedException atomicMoveNotSupportedException) {
                Files.move(path, PATH, StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (Exception exception) {
            Aurora.LOG.warn(b[108], (Throwable)exception);
        }
    }

    public static JsonObject shareSettings(Module module) {
        JsonObject jsonObject = ConfigManager.writeModule(module).getAsJsonObject(b[179]);
        ConfigManager.dropKeys(module, jsonObject);
        return jsonObject;
    }

    public static void applyShared(Module module, JsonObject jsonObject) {
        block4: {
            boolean bl;
            block6: {
                block5: {
                    JsonObject jsonObject2 = jsonObject.deepCopy();
                    ConfigManager.dropKeys(module, jsonObject2);
                    JsonObject jsonObject3 = new JsonObject();
                    jsonObject3.add(b[179], (JsonElement)jsonObject2);
                    boolean bl2 = a;
                    ConfigManager.readModule(module, jsonObject3);
                    ConfigManager.save();
                    if (!bl2) break block4;
                    if (!Module.a) break block5;
                    bl = false;
                    break block6;
                }
                bl = true;
            }
            Module.a = bl;
        }
    }

    private static void dropKeys(Module module, JsonObject jsonObject) {
        Map<Setting, String> map = ConfigManager.configKeys(module);
        boolean bl = a;
        for (Setting setting : module.getSettings()) {
            if (setting instanceof KeybindSetting) {
                jsonObject.remove(map.get(setting));
            }
            if (!bl) continue;
        }
    }

    
    private static JsonObject writeModule(Module module) {
        boolean bl;
        JsonObject jsonObject;
        block20: {
            jsonObject = new JsonObject();
            String[] stringArray = b;
            jsonObject.addProperty(stringArray[113], Boolean.valueOf(module.isEnabled()));
            JsonObject jsonObject2 = new JsonObject();
            bl = a;
            Map<Setting, String> map = ConfigManager.configKeys(module);
            for (Setting setting : module.getSettings()) {
                block23: {
                    Setting setting2;
                    String string;
                    block32: {
                        block30: {
                            block31: {
                                block28: {
                                    block29: {
                                        block26: {
                                            block27: {
                                                block24: {
                                                    block25: {
                                                        block21: {
                                                            block22: {
                                                                if (bl) break block20;
                                                                string = map.get(setting);
                                                                setting2 = setting;
                                                                if (bl) break block21;
                                                                if (!(setting2 instanceof BooleanSetting)) break block22;
                                                                BooleanSetting booleanSetting = (BooleanSetting)setting;
                                                                jsonObject2.addProperty(string, Boolean.valueOf(booleanSetting.getValue()));
                                                                if (!bl) break block23;
                                                            }
                                                            setting2 = setting;
                                                        }
                                                        if (bl) break block24;
                                                        if (!(setting2 instanceof NumberSetting)) break block25;
                                                        NumberSetting numberSetting = (NumberSetting)setting;
                                                        jsonObject2.addProperty(string, (Number)numberSetting.getValue());
                                                        if (!bl) break block23;
                                                    }
                                                    setting2 = setting;
                                                }
                                                if (bl) break block26;
                                                if (!(setting2 instanceof ColorSetting)) break block27;
                                                ColorSetting colorSetting = (ColorSetting)setting;
                                                jsonObject2.addProperty(string, (Number)colorSetting.getARGB());
                                                if (!bl) break block23;
                                            }
                                            setting2 = setting;
                                        }
                                        if (bl) break block28;
                                        if (!(setting2 instanceof KeybindSetting)) break block29;
                                        KeybindSetting keybindSetting = (KeybindSetting)setting;
                                        jsonObject2.addProperty(string, (Number)keybindSetting.getKey());
                                        if (!bl) break block23;
                                    }
                                    setting2 = setting;
                                }
                                if (bl) break block30;
                                if (!(setting2 instanceof EnumSetting)) break block31;
                                EnumSetting enumSetting = (EnumSetting)setting;
                                jsonObject2.addProperty(string, enumSetting.getValueName());
                                if (!bl) break block23;
                            }
                            setting2 = setting;
                        }
                        if (bl) break block32;
                        if (!(setting2 instanceof StringSetting)) break block23;
                        setting2 = setting;
                    }
                    StringSetting stringSetting = (StringSetting)setting2;
                    jsonObject2.addProperty(string, stringSetting.getValue());
                }
                if (!bl) continue;
            }
            stringArray = b;
            jsonObject.add(stringArray[179], (JsonElement)jsonObject2);
        }
        JsonObject jsonObject3 = new JsonObject();
        module.writeExtra(jsonObject3);
        JsonObject jsonObject4 = jsonObject3;
        if (!bl) {
            if (!jsonObject4.isEmpty()) {
                jsonObject.add(b[25], (JsonElement)jsonObject3);
            }
            jsonObject4 = jsonObject;
        }
        return jsonObject4;
    }

    private static Path backupPath() {
        return PATH.resolveSibling(String.valueOf(PATH.getFileName()) + b[125]);
    }

    
    private static void writeBackup() {
        boolean bl = a;
        try {
            Path path = PATH;
            if (bl) return;
            if (!Files.exists(path, new LinkOption[0])) return;
            path = Files.copy(PATH, ConfigManager.backupPath(), StandardCopyOption.REPLACE_EXISTING);
            return;
        }
        catch (Exception exception) {
            Aurora.LOG.debug(b[59], (Throwable)exception);
        }
    }

    private static boolean restoreBackup() {
        boolean bl = a;
        try {
            block4: {
                Path path;
                block5: {
                    path = ConfigManager.backupPath();
                    if (bl) break block4;
                    if (Files.exists(path, new LinkOption[0])) break block5;
                    return false;
                }
                Files.copy(path, PATH, StandardCopyOption.REPLACE_EXISTING);
            }
            return true;
        }
        catch (Exception exception) {
            Aurora.LOG.warn(b[70], (Throwable)exception);
            return false;
        }
    }

    private static void importReplacedMods() {
        block8: {
            JsonObject jsonObject = ModImport.read(PATH.getParent());
            boolean bl = a;
            if (jsonObject.isEmpty()) {
                return;
            }
            for (Module module : Aurora.INSTANCE.getModuleManager().getModules()) {
                block9: {
                    if (bl) break block8;
                    if (!jsonObject.has(module.getName())) break block9;
                    ConfigManager.readModule(module, jsonObject.getAsJsonObject(module.getName()));
                }
                if (!bl) continue;
            }
            Aurora.LOG.info(b[122], (Object)jsonObject.keySet());
        }
    }

    
    public static void load() {
        block10: {
            boolean bl = a;
            try {
                if (!ConfigManager.loadOnce()) {
                    return;
                }
            }
            catch (Exception exception) {
                Aurora.LOG.warn(b[156], (Throwable)exception);
                ConfigManager.moveAside();
                if (bl) break block10;
                if (!ConfigManager.restoreBackup()) {
                    Aurora.LOG.warn(b[213]);
                    if (!Files.exists(PATH, new LinkOption[0])) {
                        ConfigManager.save();
                    }
                    return;
                }
                String[] stringArray = b;
                Aurora.LOG.warn(stringArray[173]);
                try {
                    ConfigManager.loadOnce();
                }
                catch (Exception exception4) {
                    Aurora.LOG.warn(b[128], (Throwable)exception4);
                    return;
                }
            }
        }
        ConfigManager.writeBackup();
    }

    
    private static void moveAside() {
        boolean bl = a;
        try {
            Path path;
            Path path2;
            block14: {
                String[] stringArray;
                Path path3;
                block12: {
                    block13: {
                        Path path4;
                        block16: {
                            block15: {
                                Path path5 = PATH;
                                if (!bl) {
                                    if (!Files.exists(path5, new LinkOption[0])) {
                                        return;
                                    }
                                    path5 = ConfigManager.backupPath();
                                }
                                path4 = path5;
                                path3 = path4;
                                if (bl) break block12;
                                if (!Files.exists(path3, new LinkOption[0])) break block13;
                                break block15;
                                catch (Exception exception) {
                                    throw exception;
                                }
                            }
                            path3 = PATH;
                            if (bl) break block12;
                            break block16;
                            catch (Exception exception) {
                                throw exception;
                            }
                        }
                        if (Files.mismatch(path3, path4) != d) break block13;
                        return;
                    }
                    stringArray = b;
                    path3 = PATH.resolveSibling(String.valueOf(PATH.getFileName()) + stringArray[4]);
                }
                Path path6 = path3;
                int n = 2;
                while (Files.exists(path6, new LinkOption[0])) {
                    path2 = PATH;
                    path = PATH.getFileName();
                    if (!bl) {
                        stringArray = b;
                        path6 = path2.resolveSibling(String.valueOf(path) + stringArray[24] + n);
                        ++n;
                        if (!bl) continue;
                    }
                    break block14;
                }
                path2 = PATH;
                path = path6;
            }
            Files.move(path2, path, new CopyOption[0]);
        }
        catch (Exception exception) {
            Aurora.LOG.warn(b[43], (Throwable)exception);
        }
    }

    
    private static boolean loadOnce() throws Exception {
        boolean bl;
        block228: {
            block223: {
                Object object;
                Object object2;
                JsonObject jsonObject;
                JsonObject jsonObject2;
                JsonObject jsonObject3;
                boolean bl2;
                block221: {
                    block222: {
                        String string;
                        String[] stringArray;
                        block219: {
                            block220: {
                                block217: {
                                    block218: {
                                        block196: {
                                            block194: {
                                                block215: {
                                                    block216: {
                                                        block214: {
                                                            String string2;
                                                            block212: {
                                                                block213: {
                                                                    block211: {
                                                                        int n;
                                                                        block210: {
                                                                            block208: {
                                                                                block209: {
                                                                                    block207: {
                                                                                        boolean bl3;
                                                                                        block205: {
                                                                                            block206: {
                                                                                                block203: {
                                                                                                    block204: {
                                                                                                        String string3;
                                                                                                        block201: {
                                                                                                            block202: {
                                                                                                                block200: {
                                                                                                                    block199: {
                                                                                                                        block197: {
                                                                                                                            block198: {
                                                                                                                                String string4;
                                                                                                                                block183: {
                                                                                                                                    block181: {
                                                                                                                                        block182: {
                                                                                                                                            block242: {
                                                                                                                                                block241: {
                                                                                                                                                    block192: {
                                                                                                                                                        block193: {
                                                                                                                                                            block240: {
                                                                                                                                                                String string5;
                                                                                                                                                                block190: {
                                                                                                                                                                    block191: {
                                                                                                                                                                        block239: {
                                                                                                                                                                            block188: {
                                                                                                                                                                                block189: {
                                                                                                                                                                                    block238: {
                                                                                                                                                                                        block186: {
                                                                                                                                                                                            block187: {
                                                                                                                                                                                                block237: {
                                                                                                                                                                                                    block184: {
                                                                                                                                                                                                        block185: {
                                                                                                                                                                                                            block236: {
                                                                                                                                                                                                                block235: {
                                                                                                                                                                                                                    block234: {
                                                                                                                                                                                                                        block179: {
                                                                                                                                                                                                                            block180: {
                                                                                                                                                                                                                                block233: {
                                                                                                                                                                                                                                    Object object3;
                                                                                                                                                                                                                                    block177: {
                                                                                                                                                                                                                                        block178: {
                                                                                                                                                                                                                                            block232: {
                                                                                                                                                                                                                                                bl2 = a;
                                                                                                                                                                                                                                                object3 = PATH;
                                                                                                                                                                                                                                                if (bl2) break block177;
                                                                                                                                                                                                                                                if (Files.exists((Path)object3, new LinkOption[0])) break block178;
                                                                                                                                                                                                                                                break block232;
                                                                                                                                                                                                                                                catch (IllegalArgumentException illegalArgumentException) {
                                                                                                                                                                                                                                                    throw illegalArgumentException;
                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                            ConfigManager.importReplacedMods();
                                                                                                                                                                                                                                            ConfigManager.save();
                                                                                                                                                                                                                                            return false;
                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                        object3 = GSON.fromJson(Files.readString(PATH), JsonObject.class);
                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                    jsonObject3 = (JsonObject)object3;
                                                                                                                                                                                                                                    if (bl2) break block179;
                                                                                                                                                                                                                                    if (jsonObject3 != null) break block180;
                                                                                                                                                                                                                                    break block233;
                                                                                                                                                                                                                                    catch (IllegalArgumentException illegalArgumentException) {
                                                                                                                                                                                                                                        throw illegalArgumentException;
                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                throw new IllegalStateException(b[15]);
                                                                                                                                                                                                                            }
                                                                                                                                                                                                                            IconStore.read(jsonObject3);
                                                                                                                                                                                                                            HudScales.read(jsonObject3);
                                                                                                                                                                                                                            Themes.read(jsonObject3);
                                                                                                                                                                                                                            BorderlessFit.read(jsonObject3);
                                                                                                                                                                                                                        }
                                                                                                                                                                                                                        jsonObject2 = jsonObject3;
                                                                                                                                                                                                                        if (bl2) break block181;
                                                                                                                                                                                                                        if (!jsonObject2.has(b[219])) break block182;
                                                                                                                                                                                                                        break block234;
                                                                                                                                                                                                                        catch (IllegalArgumentException illegalArgumentException) {
                                                                                                                                                                                                                            throw illegalArgumentException;
                                                                                                                                                                                                                        }
                                                                                                                                                                                                                    }
                                                                                                                                                                                                                    jsonObject2 = jsonObject3;
                                                                                                                                                                                                                    string4 = b[107];
                                                                                                                                                                                                                    if (bl2) break block183;
                                                                                                                                                                                                                    break block235;
                                                                                                                                                                                                                    catch (IllegalArgumentException illegalArgumentException) {
                                                                                                                                                                                                                        throw illegalArgumentException;
                                                                                                                                                                                                                    }
                                                                                                                                                                                                                }
                                                                                                                                                                                                                if (!jsonObject2.get(string4).isJsonObject()) break block182;
                                                                                                                                                                                                                stringArray = b;
                                                                                                                                                                                                                jsonObject = jsonObject3.getAsJsonObject(stringArray[107]);
                                                                                                                                                                                                                jsonObject2 = jsonObject;
                                                                                                                                                                                                                if (bl2) break block184;
                                                                                                                                                                                                                if (!jsonObject2.has(stringArray[141])) break block185;
                                                                                                                                                                                                                break block236;
                                                                                                                                                                                                                catch (IllegalArgumentException illegalArgumentException) {
                                                                                                                                                                                                                    throw illegalArgumentException;
                                                                                                                                                                                                                }
                                                                                                                                                                                                            }
                                                                                                                                                                                                            SocialToast.setEnabled(jsonObject.get(b[211]).getAsBoolean());
                                                                                                                                                                                                        }
                                                                                                                                                                                                        jsonObject2 = jsonObject;
                                                                                                                                                                                                    }
                                                                                                                                                                                                    if (bl2) break block186;
                                                                                                                                                                                                    if (!jsonObject2.has(b[110])) break block187;
                                                                                                                                                                                                    break block237;
                                                                                                                                                                                                    catch (IllegalArgumentException illegalArgumentException) {
                                                                                                                                                                                                        throw illegalArgumentException;
                                                                                                                                                                                                    }
                                                                                                                                                                                                }
                                                                                                                                                                                                SocialToast.setSound(jsonObject.get(b[30]).getAsBoolean());
                                                                                                                                                                                            }
                                                                                                                                                                                            jsonObject2 = jsonObject;
                                                                                                                                                                                        }
                                                                                                                                                                                        string5 = b[210];
                                                                                                                                                                                        if (bl2) break block188;
                                                                                                                                                                                        if (!jsonObject2.has(string5)) break block189;
                                                                                                                                                                                        break block238;
                                                                                                                                                                                        catch (IllegalArgumentException illegalArgumentException) {
                                                                                                                                                                                            throw illegalArgumentException;
                                                                                                                                                                                        }
                                                                                                                                                                                    }
                                                                                                                                                                                    SocialToast.setRequests(jsonObject.get(b[84]).getAsBoolean());
                                                                                                                                                                                }
                                                                                                                                                                                jsonObject2 = jsonObject;
                                                                                                                                                                                stringArray = b;
                                                                                                                                                                                string5 = stringArray[97];
                                                                                                                                                                            }
                                                                                                                                                                            if (bl2) break block190;
                                                                                                                                                                            if (!jsonObject2.has(string5)) break block191;
                                                                                                                                                                            break block239;
                                                                                                                                                                            catch (IllegalArgumentException illegalArgumentException) {
                                                                                                                                                                                throw illegalArgumentException;
                                                                                                                                                                            }
                                                                                                                                                                        }
                                                                                                                                                                        SocialToast.setOnline(jsonObject.get(b[87]).getAsBoolean());
                                                                                                                                                                    }
                                                                                                                                                                    jsonObject2 = jsonObject;
                                                                                                                                                                    if (bl2) break block192;
                                                                                                                                                                    string5 = b[53];
                                                                                                                                                                }
                                                                                                                                                                if (!jsonObject2.has(string5)) break block193;
                                                                                                                                                                jsonObject2 = jsonObject;
                                                                                                                                                                if (bl2) break block192;
                                                                                                                                                                break block240;
                                                                                                                                                                catch (IllegalArgumentException illegalArgumentException) {
                                                                                                                                                                    throw illegalArgumentException;
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                            if (!jsonObject2.get(b[201]).isJsonArray()) break block193;
                                                                                                                                                            object2 = new ArrayList();
                                                                                                                                                            stringArray = b;
                                                                                                                                                            for (Object object4 : jsonObject.getAsJsonArray(stringArray[201])) {
                                                                                                                                                                object2.add(object4.getAsString());
                                                                                                                                                                if (!bl2) {
                                                                                                                                                                    if (!bl2) continue;
                                                                                                                                                                    break;
                                                                                                                                                                }
                                                                                                                                                                break block193;
                                                                                                                                                            }
                                                                                                                                                            Badges.restoreSeen((Collection<String>)object2);
                                                                                                                                                        }
                                                                                                                                                        jsonObject2 = jsonObject;
                                                                                                                                                    }
                                                                                                                                                    string4 = b[75];
                                                                                                                                                    if (bl2) break block183;
                                                                                                                                                    if (!jsonObject2.has(string4)) break block182;
                                                                                                                                                    break block241;
                                                                                                                                                    catch (IllegalArgumentException illegalArgumentException) {
                                                                                                                                                        throw illegalArgumentException;
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                                jsonObject2 = jsonObject;
                                                                                                                                                string4 = b[7];
                                                                                                                                                if (bl2) break block183;
                                                                                                                                                break block242;
                                                                                                                                                catch (IllegalArgumentException illegalArgumentException) {
                                                                                                                                                    throw illegalArgumentException;
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                            if (!jsonObject2.get(string4).isJsonArray()) break block182;
                                                                                                                                            stringArray = b;
                                                                                                                                            for (Object object5 : jsonObject.getAsJsonArray(stringArray[7])) {
                                                                                                                                                block195: {
                                                                                                                                                    if (bl2) break block194;
                                                                                                                                                    Social.setMuted(UUID.fromString(object5.getAsString()), true);
                                                                                                                                                    break block195;
                                                                                                                                                    {
                                                                                                                                                        catch (IllegalArgumentException illegalArgumentException) {
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                                if (!bl2) continue;
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        jsonObject2 = jsonObject3;
                                                                                                                                    }
                                                                                                                                    if (bl2) break block196;
                                                                                                                                    string4 = b[80];
                                                                                                                                }
                                                                                                                                if (!jsonObject2.has(string4)) break block194;
                                                                                                                                jsonObject2 = jsonObject3;
                                                                                                                                if (bl2) break block196;
                                                                                                                                if (!jsonObject2.get(b[38]).isJsonObject()) break block194;
                                                                                                                                stringArray = b;
                                                                                                                                jsonObject = jsonObject3.getAsJsonObject(stringArray[38]);
                                                                                                                                jsonObject2 = jsonObject;
                                                                                                                                if (bl2) break block197;
                                                                                                                                if (!jsonObject2.has(stringArray[21])) break block198;
                                                                                                                                jsonObject2 = jsonObject;
                                                                                                                                if (bl2) break block197;
                                                                                                                                if (!jsonObject2.get(b[85]).isJsonObject()) break block198;
                                                                                                                                stringArray = b;
                                                                                                                                object2 = jsonObject.getAsJsonObject(stringArray[85]);
                                                                                                                                for (Object object4 : object2.keySet()) {
                                                                                                                                    if (!bl2) {
                                                                                                                                        Cosmetics.setCapeId((String)object4, object2.get((String)object4).getAsString());
                                                                                                                                        if (!bl2) continue;
                                                                                                                                        break;
                                                                                                                                    }
                                                                                                                                    break block199;
                                                                                                                                }
                                                                                                                            }
                                                                                                                            jsonObject2 = jsonObject;
                                                                                                                        }
                                                                                                                        if (bl2) break block200;
                                                                                                                        if (!jsonObject2.has(b[88])) break block199;
                                                                                                                        Cosmetics.setOwnCape(jsonObject.get(b[67]).getAsString());
                                                                                                                    }
                                                                                                                    jsonObject2 = jsonObject;
                                                                                                                }
                                                                                                                string3 = b[0];
                                                                                                                if (bl2) break block201;
                                                                                                                if (!jsonObject2.has(string3)) break block202;
                                                                                                                Cosmetics.setChosenBetaCape(jsonObject.get(b[104]).getAsString());
                                                                                                            }
                                                                                                            jsonObject2 = jsonObject;
                                                                                                            if (bl2) break block203;
                                                                                                            string3 = b[82];
                                                                                                        }
                                                                                                        if (!jsonObject2.has(string3)) break block204;
                                                                                                        bl3 = Cosmetics.getOwnCapeUrl().isBlank();
                                                                                                        if (bl2) break block205;
                                                                                                        if (!bl3) break block204;
                                                                                                        jsonObject2 = jsonObject;
                                                                                                        string2 = b[46];
                                                                                                        if (bl2) break block206;
                                                                                                        object2 = jsonObject2.get(string2).getAsString();
                                                                                                        if (!((String)object2).contains(b[185])) {
                                                                                                            Cosmetics.setOwnCapeUrl((String)object2);
                                                                                                        }
                                                                                                    }
                                                                                                    jsonObject2 = jsonObject;
                                                                                                }
                                                                                                stringArray = b;
                                                                                                string2 = stringArray[72];
                                                                                            }
                                                                                            if (bl2) break block207;
                                                                                            bl3 = jsonObject2.has(string2);
                                                                                        }
                                                                                        if (bl3) {
                                                                                            Cosmetics.setOwnWings(jsonObject.get(b[50]).getAsString());
                                                                                        }
                                                                                        jsonObject2 = jsonObject;
                                                                                        stringArray = b;
                                                                                        string2 = stringArray[23];
                                                                                    }
                                                                                    if (bl2) break block208;
                                                                                    if (!jsonObject2.has(string2)) break block209;
                                                                                    stringArray = b;
                                                                                    object2 = jsonObject.getAsJsonArray(stringArray[101]);
                                                                                    for (int i = 0; i < object2.size(); ++i) {
                                                                                        n = i;
                                                                                        if (bl2 || bl2) break block210;
                                                                                        if (n >= 8) break;
                                                                                        Cosmetics.setWheelSlot(i, object2.get(i).getAsString());
                                                                                        if (!bl2) continue;
                                                                                        break;
                                                                                    }
                                                                                }
                                                                                jsonObject2 = jsonObject;
                                                                                stringArray = b;
                                                                                string2 = stringArray[135];
                                                                            }
                                                                            if (bl2) break block211;
                                                                            n = jsonObject2.has(string2);
                                                                        }
                                                                        if (n != 0) {
                                                                            Cosmetics.setOwnTrail(jsonObject.get(b[133]).getAsString());
                                                                        }
                                                                        jsonObject2 = jsonObject;
                                                                        stringArray = b;
                                                                        string2 = stringArray[56];
                                                                    }
                                                                    if (bl2) break block212;
                                                                    if (!jsonObject2.has(string2)) break block213;
                                                                    Cosmetics.setOwnMaceEffect(jsonObject.get(b[212]).getAsString());
                                                                }
                                                                jsonObject2 = jsonObject;
                                                                if (bl2) break block214;
                                                                string2 = b[60];
                                                            }
                                                            if (jsonObject2.has(string2)) {
                                                                Cosmetics.setOwnHitEffect(jsonObject.get(b[36]).getAsString());
                                                            }
                                                            jsonObject2 = jsonObject;
                                                        }
                                                        if (bl2) break block215;
                                                        if (!jsonObject2.has(b[111])) break block216;
                                                        Cosmetics.setOwnKillEffect(jsonObject.get(b[148]).getAsString());
                                                    }
                                                    jsonObject2 = jsonObject;
                                                }
                                                if (bl2) break block196;
                                                if (!jsonObject2.has(b[180])) break block194;
                                                Cosmetics.setShowOthers(jsonObject.get(b[199]).getAsBoolean());
                                            }
                                            jsonObject2 = jsonObject3;
                                        }
                                        if (bl2) break block217;
                                        if (!jsonObject2.has(b[10])) break block218;
                                        Aurora.INSTANCE.setOpenGuiKey(jsonObject3.get(b[131]).getAsInt());
                                    }
                                    jsonObject2 = jsonObject3;
                                }
                                string = b[42];
                                if (bl2) break block219;
                                if (!jsonObject2.has(string)) break block220;
                                AuroraBackdrop.setCurrent(jsonObject3.get(b[86]).getAsInt());
                            }
                            jsonObject2 = jsonObject3;
                            if (bl2) break block221;
                            string = b[182];
                        }
                        if (!jsonObject2.has(string)) break block222;
                        jsonObject2 = jsonObject3;
                        if (bl2) break block221;
                        if (!jsonObject2.get(b[65]).isJsonObject()) break block222;
                        stringArray = b;
                        jsonObject = jsonObject3.getAsJsonObject(stringArray[65]);
                        ConfigManager.migrateGuiTweaks(jsonObject);
                        ConfigManager.migrateCpsIntoKeystrokes(jsonObject);
                        ConfigManager.migrateNostalgia(jsonObject);
                        ConfigManager.migrateChatTiers(jsonObject);
                        ConfigManager.migrateCustomCrystals(jsonObject);
                        ConfigManager.migrateLyrics(jsonObject);
                        ConfigManager.migrateLyricsLook(jsonObject);
                        for (Object object5 : Aurora.INSTANCE.getModuleManager().getModules()) {
                            block227: {
                                JsonObject jsonObject4;
                                Object object4;
                                block226: {
                                    block224: {
                                        block225: {
                                            object4 = ((Module)object5).getName();
                                            if (bl2) break block223;
                                            jsonObject4 = jsonObject;
                                            if (bl2) break block224;
                                            if (jsonObject4.has((String)object4)) break block225;
                                            object = LEGACY_NAMES.get(object4);
                                            if (object == null) break block225;
                                            jsonObject4 = jsonObject;
                                            if (bl2) break block224;
                                            if (!jsonObject4.has((String)object)) break block225;
                                            object4 = object;
                                        }
                                        jsonObject4 = jsonObject;
                                    }
                                    if (bl2) break block226;
                                    if (!jsonObject4.has((String)object4)) break block227;
                                    jsonObject4 = jsonObject.get((String)object4);
                                }
                                if (jsonObject4.isJsonObject()) {
                                    ConfigManager.readModule((Module)object5, jsonObject.getAsJsonObject((String)object4));
                                }
                            }
                            if (!bl2) continue;
                        }
                    }
                    jsonObject2 = jsonObject3;
                }
                bl = jsonObject2.has(b[69]);
                if (bl2) break block228;
                if (!bl) break block223;
                bl = jsonObject3.get(b[130]).isJsonObject();
                if (bl2) break block228;
                if (!bl) break block223;
                jsonObject = jsonObject3.getAsJsonObject(b[130]);
                object2 = Category.values();
                int n2 = ((Category[])object2).length;
                int n2 = 0;
                while (n2 < n2) {
                    block229: {
                        block230: {
                            JsonObject jsonObject5;
                            block231: {
                                object = object2[n2];
                                if (bl2) break block229;
                                bl = jsonObject.has(((Enum)object).name());
                                if (bl2) break block228;
                                if (!bl) break block230;
                                jsonObject5 = jsonObject;
                                if (bl2) break block231;
                                if (!jsonObject5.get(((Enum)object).name()).isJsonObject()) break block230;
                                jsonObject5 = jsonObject.getAsJsonObject(((Enum)object).name());
                            }
                            JsonObject jsonObject6 = jsonObject5;
                            if (bl2) break block229;
                            if (!jsonObject6.has("x")) break block230;
                            Object object6 = jsonObject6;
                            if (bl2) break block230;
                            if (!object6.has("y")) break block230;
                            object6 = windowPos.put((Category)((Object)object), new int[]{jsonObject6.get("x").getAsInt(), jsonObject6.get("y").getAsInt()});
                        }
                        ++n2;
                    }
                    if (!bl2) continue;
                }
            }
            bl = true;
        }
        return bl;
    }

    private static Map<Setting, String> configKeys(Module module) {
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        boolean bl = a;
        for (Setting identityHashMap2 : module.getSettings()) {
            hashMap.merge(identityHashMap2.getName(), 1, Integer::sum);
            if (!bl) continue;
        }
        IdentityHashMap identityHashMap3 = new IdentityHashMap();
        block9: for (Setting setting : module.getSettings()) {
            for (Setting setting2 : setting.getChildren()) {
                identityHashMap3.put(setting2, setting);
                if (bl) continue block9;
                if (!bl) continue;
            }
            if (!bl) continue;
        }
        IdentityHashMap<Setting, String> identityHashMap = new IdentityHashMap<Setting, String>();
        HashMap<String, Integer> hashMap2 = new HashMap<String, Integer>();
        for (Setting setting2 : module.getSettings()) {
            Object object;
            block16: {
                String string;
                block15: {
                    Setting setting;
                    block14: {
                        HashMap<String, Integer> hashMap3;
                        block12: {
                            block13: {
                                string = setting2.getName();
                                hashMap3 = hashMap;
                                if (bl) break block12;
                                if (hashMap3.getOrDefault(string, 0) > 1) break block13;
                                identityHashMap.put(setting2, string);
                                if (!bl) continue;
                            }
                            hashMap3 = identityHashMap3.get(setting2);
                        }
                        Setting setting3 = (Setting)((Object)hashMap3);
                        setting = setting3;
                        if (bl) break block14;
                        if (setting == null) break block15;
                        setting = setting3;
                    }
                    object = setting.getName() + "/" + string;
                    break block16;
                }
                object = string;
            }
            String string = object;
            int n = hashMap2.merge(string, 1, Integer::sum);
            IdentityHashMap<Setting, String> identityHashMap2 = identityHashMap;
            Setting setting = setting2;
            Object object2 = n == 1 ? string : string + "#" + n;
            identityHashMap2.put(setting, (String)object2);
            if (!bl) continue;
        }
        return identityHashMap;
    }

    
    private static void migrateGuiTweaks(JsonObject var0) {
        block108: {
            block106: {
                block107: {
                    block104: {
                        block105: {
                            block102: {
                                block103: {
                                    block92: {
                                        block96: {
                                            block101: {
                                                block99: {
                                                    block100: {
                                                        block97: {
                                                            block98: {
                                                                block117: {
                                                                    block95: {
                                                                        block94: {
                                                                            block93: {
                                                                                block116: {
                                                                                    block115: {
                                                                                        block91: {
                                                                                            block89: {
                                                                                                block90: {
                                                                                                    block87: {
                                                                                                        block88: {
                                                                                                            block85: {
                                                                                                                block84: {
                                                                                                                    block80: {
                                                                                                                        block81: {
                                                                                                                            block113: {
                                                                                                                                block112: {
                                                                                                                                    block83: {
                                                                                                                                        block82: {
                                                                                                                                            block110: {
                                                                                                                                                block111: {
                                                                                                                                                    block78: {
                                                                                                                                                        block79: {
                                                                                                                                                            block76: {
                                                                                                                                                                block77: {
                                                                                                                                                                    block109: {
                                                                                                                                                                        var1_1 = ConfigManager.a;
                                                                                                                                                                        v0 = var0;
                                                                                                                                                                        if (var1_1) break block76;
                                                                                                                                                                        if (!v0.has(ConfigManager.b[35])) break block77;
                                                                                                                                                                        break block109;
                                                                                                                                                                        catch (Exception v1) {
                                                                                                                                                                            throw v1;
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                    return;
                                                                                                                                                                }
                                                                                                                                                                v0 = new JsonObject();
                                                                                                                                                            }
                                                                                                                                                            var2_2 = v0;
                                                                                                                                                            var3_3 = new JsonObject();
                                                                                                                                                            var4_4 = new JsonObject();
                                                                                                                                                            var5_5 = false;
                                                                                                                                                            var6_6 = false;
                                                                                                                                                            var7_7 = 0;
                                                                                                                                                            var17_8 = ConfigManager.b;
                                                                                                                                                            var8_9 = ConfigManager.legacyModule(var0, var17_8[139]);
                                                                                                                                                            v3 = var8_9;
                                                                                                                                                            if (var1_1) break block78;
                                                                                                                                                            if (v3 == null) break block79;
                                                                                                                                                            var5_5 = true;
                                                                                                                                                            var6_6 |= ConfigManager.wasEnabled(var8_9);
                                                                                                                                                            var17_8 = ConfigManager.b;
                                                                                                                                                            ConfigManager.copySetting(var8_9, var2_2, var17_8[74]);
                                                                                                                                                            ConfigManager.copySetting(var8_9, var2_2, var17_8[118]);
                                                                                                                                                        }
                                                                                                                                                        var17_8 = ConfigManager.b;
                                                                                                                                                        v3 = ConfigManager.legacyModule(var0, var17_8[159]);
                                                                                                                                                    }
                                                                                                                                                    var9_10 = v3;
                                                                                                                                                    v5 = var9_10;
                                                                                                                                                    if (var1_1) break block80;
                                                                                                                                                    if (v5 == null) break block81;
                                                                                                                                                    var5_5 = true;
                                                                                                                                                    v7 = var6_6;
                                                                                                                                                    if (var1_1) break block110;
                                                                                                                                                    var6_6 = v7 | ConfigManager.wasEnabled(var9_10);
                                                                                                                                                    ConfigManager.copySetting(var9_10, var2_2, ConfigManager.b[40]);
                                                                                                                                                    if (!var9_10.has(ConfigManager.b[179])) break block82;
                                                                                                                                                    v8 = var9_10;
                                                                                                                                                    if (var1_1) break block83;
                                                                                                                                                    break block111;
                                                                                                                                                    catch (Exception v9) {
                                                                                                                                                        throw v9;
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                                v7 = v8.get(ConfigManager.b[179]).isJsonObject();
                                                                                                                                            }
                                                                                                                                            if (!v7) break block82;
                                                                                                                                            v8 = var9_10.getAsJsonObject(ConfigManager.b[179]);
                                                                                                                                            break block83;
                                                                                                                                        }
                                                                                                                                        v8 = null;
                                                                                                                                    }
                                                                                                                                    var10_11 = v8;
                                                                                                                                    if (var1_1) break block84;
                                                                                                                                    if (var10_11 == null) break block81;
                                                                                                                                    break block112;
                                                                                                                                    catch (Exception v12) {
                                                                                                                                        throw v12;
                                                                                                                                    }
                                                                                                                                }
                                                                                                                                v13 = var10_11;
                                                                                                                                if (var1_1) break block85;
                                                                                                                                break block113;
                                                                                                                                catch (Exception v14) {
                                                                                                                                    throw v14;
                                                                                                                                }
                                                                                                                            }
                                                                                                                            if (!v13.has(ConfigManager.b[102])) break block81;
                                                                                                                            try {
                                                                                                                                block86: {
                                                                                                                                    block114: {
                                                                                                                                        v16 = var10_11;
                                                                                                                                        if (var1_1) break block86;
                                                                                                                                        if (!v16.get(ConfigManager.b[57]).getAsBoolean()) break block81;
                                                                                                                                        break block114;
                                                                                                                                        catch (Exception v17) {
                                                                                                                                            throw v17;
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                    v16 = new JsonObject();
                                                                                                                                }
                                                                                                                                var11_12 = v16;
                                                                                                                                var17_8 = ConfigManager.b;
                                                                                                                                var11_12.addProperty(var17_8[71], Boolean.valueOf(true));
                                                                                                                                var3_3.add(var17_8[123], (JsonElement)var11_12);
                                                                                                                            }
                                                                                                                            catch (Exception var11_13) {
                                                                                                                            }
                                                                                                                        }
                                                                                                                        var17_8 = ConfigManager.b;
                                                                                                                        v5 = ConfigManager.legacyModule(var0, var17_8[142]);
                                                                                                                    }
                                                                                                                    var10_11 = v5;
                                                                                                                }
                                                                                                                v13 = var10_11;
                                                                                                            }
                                                                                                            if (var1_1) break block87;
                                                                                                            if (v13 == null) break block88;
                                                                                                            var5_5 = true;
                                                                                                            var11_14 = ConfigManager.wasEnabled(var10_11);
                                                                                                            var6_6 |= var11_14;
                                                                                                            var17_8 = ConfigManager.b;
                                                                                                            var2_2.addProperty(var17_8[12], Boolean.valueOf(var11_14));
                                                                                                        }
                                                                                                        var17_8 = ConfigManager.b;
                                                                                                        v13 = ConfigManager.legacyModule(var0, var17_8[190]);
                                                                                                    }
                                                                                                    var11_12 = v13;
                                                                                                    v20 = var11_12;
                                                                                                    if (var1_1) break block89;
                                                                                                    if (v20 == null) break block90;
                                                                                                    var5_5 = true;
                                                                                                    var6_6 |= ConfigManager.wasEnabled(var11_12);
                                                                                                    var17_8 = ConfigManager.b;
                                                                                                    ConfigManager.copySetting(var11_12, var2_2, var17_8[161]);
                                                                                                }
                                                                                                var17_8 = ConfigManager.b;
                                                                                                v20 = ConfigManager.legacyModule(var0, var17_8[8]);
                                                                                            }
                                                                                            var12_15 = v20;
                                                                                            if (var1_1) break block91;
                                                                                            if (var12_15 == null) break block92;
                                                                                            var5_5 = true;
                                                                                        }
                                                                                        var13_16 = ConfigManager.wasEnabled(var12_15);
                                                                                        var6_6 |= var13_16;
                                                                                        v23 = var12_15;
                                                                                        v24 = ConfigManager.b[179];
                                                                                        if (var1_1) break block93;
                                                                                        if (!v23.has(v24)) break block94;
                                                                                        break block115;
                                                                                        catch (Exception v25) {
                                                                                            throw v25;
                                                                                        }
                                                                                    }
                                                                                    v23 = var12_15;
                                                                                    if (var1_1) break block95;
                                                                                    break block116;
                                                                                    catch (Exception v26) {
                                                                                        throw v26;
                                                                                    }
                                                                                }
                                                                                v24 = ConfigManager.b[179];
                                                                            }
                                                                            if (!v23.get(v24).isJsonObject()) break block94;
                                                                            v23 = var12_15.getAsJsonObject(ConfigManager.b[179]);
                                                                            break block95;
                                                                        }
                                                                        v23 = new JsonObject();
                                                                    }
                                                                    var14_18 = v23;
                                                                    v29 = var13_16;
                                                                    if (var1_1) break block96;
                                                                    if (v29 != 0) {
                                                                    }
                                                                    var15_19 = new JsonObject();
                                                                    v31 = var14_18;
                                                                    if (var1_1) break block97;
                                                                    if (!v31.has(ConfigManager.b[11])) break block98;
                                                                    break block117;
                                                                    catch (Exception v32) {
                                                                        throw v32;
                                                                    }
                                                                }
                                                                var15_19.add(ConfigManager.b[207], var14_18.get(ConfigManager.b[41]));
                                                            }
                                                            v31 = var14_18;
                                                        }
                                                        v34 = ConfigManager.b[189];
                                                        if (var1_1) break block99;
                                                        if (!v31.has(v34)) break block100;
                                                        try {
                                                            var17_8 = ConfigManager.b;
                                                            var15_19.addProperty(var17_8[44], (Number)(var14_18.get(var17_8[189]).getAsInt() & (int)ConfigManager.c));
                                                        }
                                                        catch (Exception var16_20) {
                                                        }
                                                    }
                                                    v31 = var14_18;
                                                    if (var1_1) break block101;
                                                    v34 = ConfigManager.b[9];
                                                }
                                                if (v31.has(v34)) {
                                                    var15_19.add(ConfigManager.b[144], var14_18.get(ConfigManager.b[29]));
                                                }
                                                v31 = var4_4;
                                            }
                                            v31.add(ConfigManager.b[28], (JsonElement)var15_19);
                                            if (!var1_1) break block92;
lbl259:

                                            v29 = 1;
                                        }
                                        var7_7 = v29;
                                    }
                                    if (!var5_5) {
                                        return;
                                    }
                                    var13_17 = new JsonObject();
                                    var17_8 = ConfigManager.b;
                                    var13_17.addProperty(var17_8[113], Boolean.valueOf(var6_6));
                                    var13_17.add(var17_8[179], (JsonElement)var2_2);
                                    var14_18 = new JsonObject();
                                    v40 = var3_3;
                                    if (var1_1) break block102;
                                    if (v40.size() == 0) break block103;
                                    var14_18.add(ConfigManager.b[163], (JsonElement)var3_3);
                                }
                                v40 = var4_4;
                            }
                            if (var1_1) break block104;
                            if (v40.size() != 0) break block105;
                            v44 = var7_7;
                            if (var1_1) break block106;
                            if (v44 == 0) break block107;
                        }
                        v40 = var14_18;
                    }
                    var17_8 = ConfigManager.b;
                    v40.add(var17_8[137], (JsonElement)var4_4);
                }
                v47 = var14_18;
                if (var1_1) break block108;
                v44 = v47.size();
            }
            if (v44 != 0) {
                var13_17.add(ConfigManager.b[16], (JsonElement)var14_18);
            }
            v47 = var0;
        }
        v47.add(ConfigManager.b[114], (JsonElement)var13_17);
    }

    private static void migrateCpsIntoKeystrokes(JsonObject jsonObject) {
        block70: {
            block61: {
                JsonObject jsonObject2;
                JsonObject jsonObject3;
                String[] stringArray;
                block68: {
                    JsonObject jsonObject4;
                    block69: {
                        JsonObject jsonObject5;
                        boolean bl;
                        block66: {
                            block67: {
                                JsonObject jsonObject6;
                                block65: {
                                    block64: {
                                        String string;
                                        block63: {
                                            JsonObject jsonObject7;
                                            block62: {
                                                String string2;
                                                JsonObject jsonObject8;
                                                JsonObject jsonObject9;
                                                block60: {
                                                    block58: {
                                                        JsonObject jsonObject10;
                                                        block59: {
                                                            JsonObject jsonObject11;
                                                            block56: {
                                                                block57: {
                                                                    JsonObject jsonObject12;
                                                                    block55: {
                                                                        block54: {
                                                                            JsonObject jsonObject13;
                                                                            block53: {
                                                                                block51: {
                                                                                    block52: {
                                                                                        JsonObject jsonObject14;
                                                                                        block49: {
                                                                                            block50: {
                                                                                                stringArray = b;
                                                                                                jsonObject9 = ConfigManager.legacyModule(jsonObject, stringArray[127]);
                                                                                                bl = a;
                                                                                                jsonObject14 = jsonObject9;
                                                                                                if (bl) break block49;
                                                                                                if (jsonObject14 != null) break block50;
                                                                                                return;
                                                                                            }
                                                                                            stringArray = b;
                                                                                            jsonObject14 = ConfigManager.legacyModule(jsonObject, stringArray[117]);
                                                                                        }
                                                                                        jsonObject4 = jsonObject14;
                                                                                        jsonObject13 = jsonObject4;
                                                                                        if (bl) break block51;
                                                                                        if (jsonObject13 != null) break block52;
                                                                                        jsonObject4 = new JsonObject();
                                                                                        stringArray = b;
                                                                                        jsonObject.add(stringArray[34], (JsonElement)jsonObject4);
                                                                                    }
                                                                                    jsonObject13 = jsonObject4;
                                                                                }
                                                                                if (bl) break block53;
                                                                                if (!jsonObject13.has(b[179])) break block54;
                                                                                jsonObject12 = jsonObject4;
                                                                                if (bl) break block55;
                                                                                jsonObject13 = jsonObject12.get(b[179]);
                                                                            }
                                                                            if (!jsonObject13.isJsonObject()) break block54;
                                                                            jsonObject12 = jsonObject4.getAsJsonObject(b[179]);
                                                                            break block55;
                                                                        }
                                                                        jsonObject12 = new JsonObject();
                                                                    }
                                                                    jsonObject10 = jsonObject12;
                                                                    jsonObject11 = jsonObject10;
                                                                    if (bl) break block56;
                                                                    if (!jsonObject11.has(b[81])) break block57;
                                                                    return;
                                                                }
                                                                jsonObject10.addProperty(b[162], Boolean.valueOf(ConfigManager.wasEnabled(jsonObject9)));
                                                                ConfigManager.copySetting(jsonObject9, jsonObject10, b[150]);
                                                                ConfigManager.copySetting(jsonObject9, jsonObject10, b[90]);
                                                                ConfigManager.copySetting(jsonObject9, jsonObject10, b[83]);
                                                                ConfigManager.copySetting(jsonObject9, jsonObject10, b[13]);
                                                                if (bl) break block58;
                                                                jsonObject11 = jsonObject9;
                                                            }
                                                            if (!jsonObject11.has(b[179])) break block59;
                                                            jsonObject8 = jsonObject9;
                                                            string2 = b[179];
                                                            if (bl) break block60;
                                                            if (!jsonObject8.get(string2).isJsonObject()) break block59;
                                                            stringArray = b;
                                                            jsonObject5 = jsonObject9.getAsJsonObject(stringArray[179]);
                                                            if (bl) break block58;
                                                            if (!jsonObject5.has(stringArray[112])) break block59;
                                                            jsonObject10.add(b[93], jsonObject5.get(b[49]));
                                                        }
                                                        stringArray = b;
                                                        jsonObject4.add(stringArray[179], (JsonElement)jsonObject10);
                                                        if (bl) break block70;
                                                    }
                                                    jsonObject8 = jsonObject9;
                                                    stringArray = b;
                                                    string2 = stringArray[25];
                                                }
                                                if (!jsonObject8.has(string2)) break block61;
                                                jsonObject7 = jsonObject9;
                                                if (bl) break block62;
                                                if (!jsonObject7.get(b[25]).isJsonObject()) break block61;
                                                jsonObject7 = jsonObject9.getAsJsonObject(b[25]);
                                            }
                                            jsonObject5 = jsonObject7;
                                            jsonObject6 = jsonObject4;
                                            string = b[25];
                                            if (bl) break block63;
                                            if (!jsonObject6.has(string)) break block64;
                                            jsonObject6 = jsonObject4;
                                            if (bl) break block65;
                                            string = b[25];
                                        }
                                        if (!jsonObject6.get(string).isJsonObject()) break block64;
                                        jsonObject6 = jsonObject4.getAsJsonObject(b[25]);
                                        break block65;
                                    }
                                    jsonObject6 = new JsonObject();
                                }
                                jsonObject3 = jsonObject6;
                                jsonObject2 = jsonObject5;
                                if (bl) break block66;
                                if (!jsonObject2.has(b[79])) break block67;
                                jsonObject3.add(b[175], jsonObject5.get(b[175]));
                            }
                            jsonObject2 = jsonObject5;
                        }
                        if (bl) break block68;
                        if (!jsonObject2.has(b[184])) break block69;
                        jsonObject3.add(b[58], jsonObject5.get(b[58]));
                    }
                    jsonObject2 = jsonObject4;
                }
                stringArray = b;
                jsonObject2.add(stringArray[25], (JsonElement)jsonObject3);
            }
            Aurora.LOG.info(b[200]);
        }
    }

    
    private static void migrateNostalgia(JsonObject jsonObject) {
        boolean bl;
        String[] stringArray;
        boolean bl2;
        JsonObject jsonObject2;
        block33: {
            boolean bl3;
            block32: {
                JsonObject jsonObject3;
                boolean bl4;
                block41: {
                    block40: {
                        block39: {
                            block38: {
                                JsonObject jsonObject4;
                                block31: {
                                    JsonObject jsonObject5;
                                    block29: {
                                        block30: {
                                            JsonObject jsonObject6;
                                            block27: {
                                                block28: {
                                                    block37: {
                                                        bl4 = a;
                                                        jsonObject6 = jsonObject;
                                                        if (bl4) break block27;
                                                        if (!jsonObject6.has(b[109])) break block28;
                                                        break block37;
                                                        catch (Exception exception) {
                                                            throw exception;
                                                        }
                                                    }
                                                    return;
                                                }
                                                jsonObject6 = new JsonObject();
                                            }
                                            jsonObject2 = jsonObject6;
                                            bl3 = false;
                                            bl2 = false;
                                            stringArray = b;
                                            JsonObject jsonObject7 = ConfigManager.legacyModule(jsonObject, stringArray[63]);
                                            jsonObject5 = jsonObject7;
                                            if (bl4) break block29;
                                            if (jsonObject5 == null) break block30;
                                            bl3 = true;
                                            boolean bl5 = ConfigManager.wasEnabled(jsonObject7);
                                            bl2 |= bl5;
                                            stringArray = b;
                                            jsonObject2.addProperty(stringArray[152], Boolean.valueOf(bl5));
                                        }
                                        stringArray = b;
                                        jsonObject5 = ConfigManager.legacyModule(jsonObject, stringArray[151]);
                                    }
                                    jsonObject3 = jsonObject5;
                                    jsonObject4 = jsonObject3;
                                    if (bl4) break block31;
                                    if (jsonObject4 == null) break block32;
                                    jsonObject4 = jsonObject3;
                                }
                                bl = jsonObject4.has(b[179]);
                                if (bl4) break block33;
                                if (!bl) break block32;
                                break block38;
                                catch (Exception exception) {
                                    throw exception;
                                }
                            }
                            bl = jsonObject3.get(b[179]).isJsonObject();
                            if (bl4) break block33;
                            break block39;
                            catch (Exception exception) {
                                throw exception;
                            }
                        }
                        if (!bl) break block32;
                        break block40;
                        catch (Exception exception) {
                            throw exception;
                        }
                    }
                    bl = jsonObject3.getAsJsonObject(b[179]).has(b[66]);
                    if (bl4) break block33;
                    break block41;
                    catch (Exception exception) {
                        throw exception;
                    }
                }
                if (!bl) break block32;
                bl3 = true;
                boolean bl6 = false;
                try {
                    boolean bl7;
                    block36: {
                        block35: {
                            JsonObject jsonObject8;
                            block34: {
                                block42: {
                                    jsonObject8 = jsonObject3;
                                    if (bl4) break block34;
                                    if (!ConfigManager.wasEnabled(jsonObject8)) break block35;
                                    break block42;
                                    catch (Exception exception) {
                                        throw exception;
                                    }
                                }
                                jsonObject8 = jsonObject3.getAsJsonObject(b[179]).get(b[3]);
                            }
                            bl7 = jsonObject8.getAsBoolean();
                            if (bl4) break block36;
                            if (!bl7) break block35;
                            bl7 = true;
                            break block36;
                        }
                        bl7 = false;
                    }
                    bl6 = bl7;
                }
                catch (Exception exception) {
                }
                bl2 |= bl6;
                stringArray = b;
                jsonObject2.addProperty(stringArray[3], Boolean.valueOf(bl6));
            }
            bl = bl3;
        }
        if (!bl) {
            return;
        }
        JsonObject jsonObject9 = new JsonObject();
        stringArray = b;
        jsonObject9.addProperty(stringArray[126], Boolean.valueOf(bl2));
        jsonObject9.add(stringArray[179], (JsonElement)jsonObject2);
        jsonObject.add(stringArray[220], (JsonElement)jsonObject9);
        Aurora.LOG.info(stringArray[121]);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void migrateChatTiers(JsonObject jsonObject) {
        JsonObject jsonObject2;
        JsonObject jsonObject3;
        JsonObject jsonObject4;
        String[] stringArray;
        block39: {
            JsonObject jsonObject5;
            boolean bl;
            block38: {
                block37: {
                    JsonObject jsonObject6;
                    block36: {
                        block34: {
                            block35: {
                                JsonObject jsonObject7;
                                block32: {
                                    block33: {
                                        JsonObject jsonObject8;
                                        block31: {
                                            String string;
                                            JsonObject jsonObject9;
                                            block30: {
                                                block29: {
                                                    stringArray = b;
                                                    jsonObject9 = ConfigManager.legacyModule(jsonObject, stringArray[105]);
                                                    bl = a;
                                                    jsonObject8 = jsonObject9;
                                                    if (bl) break block29;
                                                    if (jsonObject8 == null) return;
                                                    jsonObject8 = jsonObject9;
                                                }
                                                string = b[52];
                                                if (bl) break block30;
                                                if (!jsonObject8.has(string)) return;
                                                jsonObject8 = jsonObject9;
                                                if (bl) break block31;
                                                string = b[179];
                                            }
                                            if (!jsonObject8.get(string).isJsonObject()) {
                                                return;
                                            }
                                            stringArray = b;
                                            jsonObject8 = jsonObject9.getAsJsonObject(stringArray[179]);
                                        }
                                        jsonObject4 = jsonObject8;
                                        jsonObject7 = jsonObject4;
                                        if (bl) break block32;
                                        if (jsonObject7.has(b[172])) break block33;
                                        return;
                                    }
                                    stringArray = b;
                                    jsonObject7 = ConfigManager.legacyModule(jsonObject, stringArray[31]);
                                }
                                jsonObject3 = jsonObject7;
                                jsonObject6 = jsonObject3;
                                if (bl) break block34;
                                if (jsonObject6 != null) break block35;
                                jsonObject3 = new JsonObject();
                                stringArray = b;
                                jsonObject.add(stringArray[2], (JsonElement)jsonObject3);
                            }
                            jsonObject6 = jsonObject3;
                        }
                        if (bl) break block36;
                        if (!jsonObject6.has(b[179])) break block37;
                        jsonObject5 = jsonObject3;
                        if (bl) break block38;
                        jsonObject6 = jsonObject5.get(b[179]);
                    }
                    if (!jsonObject6.isJsonObject()) break block37;
                    jsonObject5 = jsonObject3.getAsJsonObject(b[179]);
                    break block38;
                }
                jsonObject5 = new JsonObject();
            }
            jsonObject2 = jsonObject5;
            if (bl) return;
            if (jsonObject2.has(b[157])) break block39;
            jsonObject2.add(b[73], jsonObject4.get(b[96]));
        }
        stringArray = b;
        jsonObject3.add(stringArray[179], (JsonElement)jsonObject2);
        jsonObject4.remove(stringArray[96]);
    }

    
    static void migrateLyrics(JsonObject var0) {
        block90: {
            block91: {
                block87: {
                    block89: {
                        block88: {
                            block84: {
                                block86: {
                                    block85: {
                                        block82: {
                                            block83: {
                                                block79: {
                                                    block81: {
                                                        block80: {
                                                            block78: {
                                                                block96: {
                                                                    block70: {
                                                                        block74: {
                                                                            block73: {
                                                                                block71: {
                                                                                    block72: {
                                                                                        block95: {
                                                                                            block69: {
                                                                                                block68: {
                                                                                                    block67: {
                                                                                                        block65: {
                                                                                                            block66: {
                                                                                                                block63: {
                                                                                                                    block64: {
                                                                                                                        block94: {
                                                                                                                            block93: {
                                                                                                                                block61: {
                                                                                                                                    block62: {
                                                                                                                                        block92: {
                                                                                                                                            var1_1 = ConfigManager.a;
                                                                                                                                            v0 = var0;
                                                                                                                                            if (var1_1) break block61;
                                                                                                                                            if (!v0.has(ConfigManager.b[94])) break block62;
                                                                                                                                            break block92;
                                                                                                                                            catch (Exception v1) {
                                                                                                                                                throw v1;
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        return;
                                                                                                                                    }
                                                                                                                                    var11_2 = ConfigManager.b;
                                                                                                                                    v0 = ConfigManager.legacyModule(var0, var11_2[119]);
                                                                                                                                }
                                                                                                                                var2_3 = v0;
                                                                                                                                var11_2 = ConfigManager.b;
                                                                                                                                var3_4 = ConfigManager.legacyModule(var0, var11_2[195]);
                                                                                                                                v3 = var2_3;
                                                                                                                                if (var1_1) break block63;
                                                                                                                                if (v3 != null) break block64;
                                                                                                                                break block93;
                                                                                                                                catch (Exception v4) {
                                                                                                                                    throw v4;
                                                                                                                                }
                                                                                                                            }
                                                                                                                            v3 = var3_4;
                                                                                                                            if (var1_1) break block63;
                                                                                                                            break block94;
                                                                                                                            catch (Exception v5) {
                                                                                                                                throw v5;
                                                                                                                            }
                                                                                                                        }
                                                                                                                        if (v3 != null) break block64;
                                                                                                                        return;
                                                                                                                    }
                                                                                                                    v3 = var2_3;
                                                                                                                }
                                                                                                                if (var1_1) break block65;
                                                                                                                if (v3 == null) break block66;
                                                                                                                v3 = var2_3;
                                                                                                                break block65;
                                                                                                            }
                                                                                                            v3 = new JsonObject();
                                                                                                        }
                                                                                                        var4_5 = v3;
                                                                                                        var11_2 = ConfigManager.b;
                                                                                                        var5_6 = ConfigManager.child(var4_5, var11_2[179]);
                                                                                                        var6_7 = ConfigManager.child(var4_5, var11_2[25]);
                                                                                                        v8 = var2_3;
                                                                                                        if (var1_1) break block67;
                                                                                                        if (v8 == null) break block68;
                                                                                                        v8 = var2_3;
                                                                                                    }
                                                                                                    v10 = ConfigManager.wasEnabled(v8);
                                                                                                    if (var1_1) break block69;
                                                                                                    if (!v10) break block68;
                                                                                                    v10 = true;
                                                                                                    break block69;
                                                                                                }
                                                                                                v10 = false;
                                                                                            }
                                                                                            var7_8 = v10;
                                                                                            var8_9 = false;
                                                                                            var9_10 = var5_6;
                                                                                            v12 = var3_4;
                                                                                            if (!var1_1) {
                                                                                                if (v12 == null) break block70;
                                                                                            }
                                                                                            var8_9 = ConfigManager.wasEnabled(var3_4);
                                                                                            var11_2 = ConfigManager.b;
                                                                                            var9_10 = ConfigManager.child(var3_4, var11_2[179]);
                                                                                            var10_11 = ConfigManager.child(var3_4, var11_2[25]);
                                                                                            v14 = var10_11;
                                                                                            v15 = var11_2[196];
                                                                                            if (var1_1) break block71;
                                                                                            if (!v14.has(v15)) break block72;
                                                                                            break block95;
                                                                                            catch (Exception v16) {
                                                                                                throw v16;
                                                                                            }
                                                                                        }
                                                                                        var6_7.add(ConfigManager.b[164], var10_11.get(ConfigManager.b[164]));
                                                                                    }
                                                                                    v14 = var10_11;
                                                                                    if (var1_1) break block73;
                                                                                    v15 = ConfigManager.b[143];
                                                                                }
                                                                                if (!v14.has(v15)) break block74;
                                                                                v14 = var6_7;
                                                                            }
                                                                            var11_2 = ConfigManager.b;
                                                                            v14.add(var11_2[120], var10_11.get(var11_2[120]));
                                                                        }
                                                                        if (!var1_1) break block96;
                                                                    }
                                                                    try {
                                                                        block77: {
                                                                            block76: {
                                                                                block75: {
                                                                                    block97: {
                                                                                        v12 = var5_6;
lbl134:

                                                                                        v20 = ConfigManager.b[176];
                                                                                        if (var1_1) break block75;
                                                                                        if (!v12.has(v20)) break block76;
                                                                                        break block97;
                                                                                        catch (Exception v21) {
                                                                                            throw v21;
                                                                                        }
                                                                                    }
                                                                                    v12 = var5_6;
                                                                                    v20 = ConfigManager.b[176];
                                                                                }
                                                                                v23 = v12.get(v20).getAsBoolean();
                                                                                if (var1_1) break block77;
                                                                                if (!v23) break block76;
                                                                                v23 = true;
                                                                                break block77;
                                                                            }
                                                                            v23 = false;
                                                                        }
                                                                        var8_9 = v23;
                                                                    }
                                                                    catch (Exception var10_12) {
                                                                    }
                                                                }
                                                                if (var3_4 == null) break block78;
                                                                v25 = var8_9;
                                                                break block79;
                                                            }
                                                            v25 = var7_8;
                                                            if (var1_1) break block80;
                                                            if (!v25) break block81;
                                                            v25 = var8_9;
                                                        }
                                                        if (var1_1) break block79;
                                                        if (!v25) break block81;
                                                        v25 = true;
                                                        break block79;
                                                    }
                                                    v25 = false;
                                                }
                                                var10_13 = v25;
                                                v29 = var9_10;
                                                if (var1_1) break block82;
                                                if (!v29.has(ConfigManager.b[83])) break block83;
                                                var5_6.add(ConfigManager.b[115], var9_10.get(ConfigManager.b[83]));
                                            }
                                            var5_6.remove(ConfigManager.b[176]);
                                            v29 = var5_6;
                                            v32 = ConfigManager.b[95];
                                            v33 = var8_9;
                                            if (var1_1) break block84;
                                            v29.addProperty(v32, Boolean.valueOf(v33));
                                            v29 = var5_6;
                                        }
                                        v32 = ConfigManager.b[6];
                                        if (var7_8) break block85;
                                        v33 = var10_13;
                                        if (var1_1) break block84;
                                        if (v33) break block86;
                                    }
                                    v33 = true;
                                    break block84;
                                }
                                v33 = false;
                            }
                            v29.addProperty(v32, Boolean.valueOf(v33));
                            v37 = var4_5;
                            v38 = ConfigManager.b[113];
                            v39 = var7_8;
                            if (var1_1) break block87;
                            if (v39) break block88;
                            v39 = var10_13;
                            if (var1_1) break block87;
                            if (!v39) break block89;
                        }
                        v39 = true;
                        break block87;
                    }
                    v39 = false;
                }
                v37.addProperty(v38, Boolean.valueOf(v39));
                if (var1_1) break block90;
                if (!var6_7.isEmpty()) break block91;
                var4_5.remove(ConfigManager.b[25]);
            }
            var11_2 = ConfigManager.b;
            var0.remove(var11_2[168]);
            var0.remove(var11_2[176]);
            var0.add(var11_2[94], (JsonElement)var4_5);
            Aurora.LOG.info(var11_2[68]);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    static void migrateLyricsLook(JsonObject jsonObject) {
        JsonObject jsonObject2;
        String[] stringArray;
        block32: {
            boolean bl;
            block30: {
                JsonElement jsonElement;
                block31: {
                    JsonElement jsonElement2;
                    boolean bl2;
                    block28: {
                        block29: {
                            block27: {
                                JsonObject jsonObject3;
                                block26: {
                                    JsonObject jsonObject4;
                                    JsonObject jsonObject5;
                                    block25: {
                                        block24: {
                                            stringArray = b;
                                            jsonObject5 = ConfigManager.legacyModule(jsonObject, stringArray[124]);
                                            bl2 = a;
                                            jsonObject4 = jsonObject5;
                                            if (bl2) break block24;
                                            if (jsonObject4 == null) return;
                                            jsonObject4 = jsonObject5;
                                        }
                                        if (bl2) break block25;
                                        if (!jsonObject4.has(b[179])) return;
                                        jsonObject3 = jsonObject5;
                                        if (bl2) break block26;
                                        jsonObject4 = jsonObject3.get(b[179]);
                                    }
                                    if (!jsonObject4.isJsonObject()) {
                                        return;
                                    }
                                    stringArray = b;
                                    jsonObject3 = jsonObject5.getAsJsonObject(stringArray[179]);
                                }
                                jsonObject2 = jsonObject3;
                                stringArray = b;
                                jsonElement = jsonObject2.get(stringArray[153]);
                                jsonElement2 = jsonElement;
                                if (bl2) break block27;
                                if (jsonElement2 == null) return;
                                jsonElement2 = jsonElement;
                            }
                            if (bl2) break block28;
                            if (jsonElement2.isJsonPrimitive()) break block29;
                            return;
                        }
                        jsonElement2 = jsonElement;
                    }
                    bl = jsonElement2.getAsJsonPrimitive().isBoolean();
                    if (bl2) break block30;
                    if (!bl) break block31;
                    jsonObject2.remove(b[194]);
                    jsonObject2.remove(b[181]);
                    jsonObject2.remove(b[76]);
                    jsonObject2.remove(b[205]);
                    if (!bl2) break block32;
                }
                bl = b[45].equals(jsonElement.getAsString());
            }
            if (!bl) {
                return;
            }
        }
        stringArray = b;
        jsonObject2.addProperty(stringArray[115], stringArray[160]);
    }

    private static JsonObject child(JsonObject jsonObject, String string) {
        JsonObject jsonObject2;
        block10: {
            block11: {
                JsonObject jsonObject3;
                block8: {
                    block9: {
                        boolean bl = a;
                        jsonObject3 = jsonObject;
                        if (bl) break block8;
                        if (!jsonObject3.has(string)) break block9;
                        jsonObject2 = jsonObject;
                        if (bl) break block10;
                        if (jsonObject2.get(string).isJsonObject()) break block11;
                    }
                    jsonObject3 = jsonObject;
                }
                jsonObject3.add(string, (JsonElement)new JsonObject());
            }
            jsonObject2 = jsonObject.getAsJsonObject(string);
        }
        return jsonObject2;
    }

    
    private static void migrateCustomCrystals(JsonObject var0) {
        block58: {
            block72: {
                block57: {
                    block71: {
                        block56: {
                            block70: {
                                block54: {
                                    block55: {
                                        block53: {
                                            block69: {
                                                block68: {
                                                    block52: {
                                                        var5_1 = ConfigManager.b;
                                                        var2_2 = ConfigManager.legacyModule(var0, var5_1[214]);
                                                        var1_3 = ConfigManager.a;
                                                        v0 = var2_2;
                                                        if (var1_3) break block52;
                                                        if (v0 != null) {
                                                        }
                                                        v0 = var2_2;
                                                    }
                                                    v2 = ConfigManager.b[179];
                                                    if (var1_3) break block53;
                                                    if (!v0.has(v2)) // goto
                                                    break block68;
                                                    catch (Exception v3) {
                                                        throw v3;
                                                    }
                                                }
                                                v0 = var2_2;
                                                if (var1_3) break block54;
                                                break block69;
                                                catch (Exception v4) {
                                                    throw v4;
                                                }
                                            }
                                            v2 = ConfigManager.b[179];
                                        }
                                        if (v0.get(v2).isJsonObject()) break block55;
lbl37:

                                        return;
                                    }
                                    var5_1 = ConfigManager.b;
                                    v0 = var2_2.getAsJsonObject(var5_1[179]);
                                }
                                var3_4 = v0;
                                v7 = var3_4;
                                if (var1_3) break block56;
                                if (v7.has(ConfigManager.b[197])) // goto
                                break block70;
                                catch (Exception v8) {
                                    throw v8;
                                }
                            }
                            v7 = var3_4;
                        }
                        if (var1_3) break block57;
                        if (v7.has(ConfigManager.b[92])) // goto
                        break block71;
                        catch (Exception v10) {
                            throw v10;
                        }
                    }
                    v7 = var3_4;
                }
                v12 = ConfigManager.b[206];
                if (var1_3) // goto
                if (!v7.has(v12)) break block58;
                break block72;
                catch (Exception v13) {
                    throw v13;
                }
            }
            return;
        }
        try {
            block65: {
                block67: {
                    block66: {
                        block77: {
                            block76: {
                                block64: {
                                    block75: {
                                        block62: {
                                            block63: {
                                                block74: {
                                                    block61: {
                                                        block59: {
                                                            block60: {
                                                                block73: {
                                                                    v7 = var3_4;
                                                                    var5_1 = ConfigManager.b;
                                                                    v12 = var5_1[193];
lbl90:

                                                                    if (var1_3) break block59;
                                                                    if (!v7.has(v12)) break block60;
                                                                    break block73;
                                                                    catch (Exception v15) {
                                                                        throw v15;
                                                                    }
                                                                }
                                                                var3_4.addProperty(ConfigManager.b[91], (Number)(var3_4.get(ConfigManager.b[158]).getAsDouble() / 100.0));
                                                            }
                                                            v7 = var3_4;
                                                            if (var1_3) break block61;
                                                            v12 = ConfigManager.b[136];
                                                        }
                                                        if (v7.has(v12)) {
                                                            var3_4.addProperty(ConfigManager.b[14], (Number)Math.max(1.0, var3_4.get(ConfigManager.b[203]).getAsDouble() / 100.0));
                                                        }
                                                        v7 = var3_4;
                                                    }
                                                    v19 = ConfigManager.b[106];
                                                    if (var1_3) break block62;
                                                    if (!v7.has(v19)) break block63;
                                                    break block74;
                                                    catch (Exception v20) {
                                                        throw v20;
                                                    }
                                                }
                                                var3_4.addProperty(ConfigManager.b[166], (Number)(var3_4.get(ConfigManager.b[218]).getAsDouble() / 100.0));
                                            }
                                            v7 = var3_4;
                                            var5_1 = ConfigManager.b;
                                            v19 = var5_1[47];
                                        }
                                        if (var1_3) break block64;
                                        if (!v7.has(v19)) break block65;
                                        break block75;
                                        catch (Exception v22) {
                                            throw v22;
                                        }
                                    }
                                    v7 = var3_4;
                                    v19 = ConfigManager.b[188];
                                }
                                if (var1_3) break block66;
                                if (!v7.get(v19).getAsBoolean()) break block65;
                                break block76;
                                catch (Exception v24) {
                                    throw v24;
                                }
                            }
                            v7 = var3_4;
                            if (var1_3) break block67;
                            break block77;
                            catch (Exception v25) {
                                throw v25;
                            }
                        }
                        v19 = ConfigManager.b[134];
                    }
                    if (!v7.has(v19)) break block65;
                    v7 = var3_4.get(ConfigManager.b[189]);
                }
                var4_5 = v7.getAsInt();
                var5_1 = ConfigManager.b;
                var3_4.addProperty(var5_1[129], (Number)var4_5);
                var3_4.addProperty(var5_1[186], (Number)var4_5);
                var3_4.addProperty(var5_1[33], (Number)var4_5);
            }
            var5_1 = ConfigManager.b;
            Aurora.LOG.info(var5_1[202]);
        }
        catch (Exception var4_6) {
            Aurora.LOG.warn(ConfigManager.b[204], (Throwable)var4_6);
        }
    }

    private static JsonObject legacyModule(JsonObject jsonObject, String string) {
        JsonObject jsonObject2;
        block10: {
            block9: {
                String string2;
                block8: {
                    boolean bl = a;
                    jsonObject2 = jsonObject;
                    string2 = string;
                    if (bl) break block8;
                    if (!jsonObject2.has(string2)) break block9;
                    jsonObject2 = jsonObject;
                    if (bl) break block10;
                    string2 = string;
                }
                if (!jsonObject2.get(string2).isJsonObject()) break block9;
                jsonObject2 = jsonObject.getAsJsonObject(string);
                break block10;
            }
            jsonObject2 = null;
        }
        return jsonObject2;
    }

    
    private static boolean wasEnabled(JsonObject jsonObject) {
        boolean bl = a;
        try {
            JsonObject jsonObject2 = jsonObject;
            String string = b[113];
            if (!bl) {
                if (!jsonObject2.has(string)) return false;
                jsonObject2 = jsonObject;
                string = b[113];
            }
            boolean bl2 = jsonObject2.get(string).getAsBoolean();
            if (bl) return bl2;
            if (!bl2) return false;
            return true;
        }
        catch (Exception exception) {
            return false;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void copySetting(JsonObject jsonObject, JsonObject jsonObject2, String string) {
        JsonObject jsonObject3;
        JsonObject jsonObject4;
        block15: {
            JsonObject jsonObject5;
            boolean bl;
            block14: {
                String string2;
                block13: {
                    bl = a;
                    jsonObject5 = jsonObject;
                    string2 = b[179];
                    if (bl) break block13;
                    if (!jsonObject5.has(string2)) return;
                    jsonObject5 = jsonObject;
                    if (bl) break block14;
                    string2 = b[179];
                }
                if (!jsonObject5.get(string2).isJsonObject()) {
                    return;
                }
                jsonObject5 = jsonObject.getAsJsonObject(b[179]);
            }
            jsonObject4 = jsonObject5;
            jsonObject3 = jsonObject4;
            if (bl) break block15;
            if (!jsonObject3.has(string)) return;
            jsonObject3 = jsonObject2;
        }
        jsonObject3.add(string, jsonObject4.get(string));
    }

    
    private static void readModule(Module var0, JsonObject var1_1) {
        block179: {
            block178: {
                block162: {
                    block177: {
                        block176: {
                            block143: {
                                block144: {
                                    block159: {
                                        block158: {
                                            block203: {
                                                block202: {
                                                    block148: {
                                                        block183: {
                                                            block182: {
                                                                block181: {
                                                                    block142: {
                                                                        block141: {
                                                                            block180: {
                                                                                var2_2 = ConfigManager.a;
                                                                                v0 = var1_1;
                                                                                v1 = ConfigManager.b[113];
                                                                                if (var2_2) break block141;
                                                                                if (!v0.has(v1)) break block142;
                                                                                break block180;
                                                                                catch (Exception v2) {
                                                                                    throw v2;
                                                                                }
                                                                            }
                                                                            v0 = var1_1;
                                                                            v1 = ConfigManager.b[113];
                                                                        }
                                                                        v4 = v0.get(v1).getAsBoolean();
                                                                        break block181;
                                                                    }
                                                                    v4 = null;
                                                                }
                                                                var3_3 = v4;
                                                                v5 = var1_1;
                                                                v6 = ConfigManager.b[179];
                                                                if (var2_2) break block143;
                                                                if (!v5.has(v6)) break block144;
                                                                break block182;
                                                                catch (Exception v7) {
                                                                    throw v7;
                                                                }
                                                            }
                                                            v5 = var1_1;
                                                            v6 = ConfigManager.b[179];
                                                            if (var2_2) break block143;
                                                            break block183;
                                                            catch (Exception v8) {
                                                                throw v8;
                                                            }
                                                        }
                                                        if (!v5.get(v6).isJsonObject()) break block144;
                                                        var17_4 = ConfigManager.b;
                                                        var4_5 = var1_1.getAsJsonObject(var17_4[179]);
                                                        try {
                                                            block156: {
                                                                block157: {
                                                                    block199: {
                                                                        block201: {
                                                                            block200: {
                                                                                block154: {
                                                                                    block155: {
                                                                                        block196: {
                                                                                            block198: {
                                                                                                block197: {
                                                                                                    block153: {
                                                                                                        block152: {
                                                                                                            block151: {
                                                                                                                block195: {
                                                                                                                    block150: {
                                                                                                                        block191: {
                                                                                                                            block194: {
                                                                                                                                block193: {
                                                                                                                                    block192: {
                                                                                                                                        block149: {
                                                                                                                                            block145: {
                                                                                                                                                block190: {
                                                                                                                                                    block146: {
                                                                                                                                                        block147: {
                                                                                                                                                            block189: {
                                                                                                                                                                block188: {
                                                                                                                                                                    block187: {
                                                                                                                                                                        block186: {
                                                                                                                                                                            block185: {
                                                                                                                                                                                block184: {
                                                                                                                                                                                    v10 = var4_5;
                                                                                                                                                                                    v11 = var17_4[170];
                                                                                                                                                                                    if (var2_2) break block145;
                                                                                                                                                                                    if (!v10.has(v11)) // goto
                                                                                                                                                                                    break block184;
                                                                                                                                                                                    catch (Exception v12) {
                                                                                                                                                                                        throw v12;
                                                                                                                                                                                    }
                                                                                                                                                                                }
                                                                                                                                                                                v10 = var4_5;
                                                                                                                                                                                v11 = ConfigManager.b[170];
                                                                                                                                                                                if (var2_2) break block145;
                                                                                                                                                                                break block185;
                                                                                                                                                                                catch (Exception v13) {
                                                                                                                                                                                    throw v13;
                                                                                                                                                                                }
                                                                                                                                                                            }
                                                                                                                                                                            if (!v10.get(v11).isJsonPrimitive()) // goto
                                                                                                                                                                            break block186;
                                                                                                                                                                            catch (Exception v14) {
                                                                                                                                                                                throw v14;
                                                                                                                                                                            }
                                                                                                                                                                        }
                                                                                                                                                                        v10 = var4_5;
                                                                                                                                                                        v11 = ConfigManager.b[170];
                                                                                                                                                                        if (var2_2) break block145;
                                                                                                                                                                        break block187;
                                                                                                                                                                        catch (Exception v15) {
                                                                                                                                                                            throw v15;
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                    if (!v10.getAsJsonPrimitive(v11).isString()) // goto
                                                                                                                                                                    break block188;
                                                                                                                                                                    catch (Exception v16) {
                                                                                                                                                                        throw v16;
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                                v17 = var4_5;
                                                                                                                                                                v18 = ConfigManager.b[170];
                                                                                                                                                                v19 = ConfigManager.b[138].equals(var4_5.get(ConfigManager.b[170]).getAsString());
                                                                                                                                                                if (var2_2) break block146;
                                                                                                                                                                break block189;
                                                                                                                                                                catch (Exception v20) {
                                                                                                                                                                    throw v20;
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                            if (v19) break block147;
                                                                                                                                                            v19 = true;
                                                                                                                                                            break block146;
                                                                                                                                                        }
                                                                                                                                                        v19 = false;
                                                                                                                                                    }
                                                                                                                                                    v17.addProperty(v18, Boolean.valueOf(v19));
                                                                                                                                                    if (!var2_2) break block148;
lbl109:

                                                                                                                                                    v10 = var4_5;
                                                                                                                                                    if (var2_2) break block149;
                                                                                                                                                    break block190;
                                                                                                                                                    catch (Exception v22) {
                                                                                                                                                        throw v22;
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                                v11 = ConfigManager.b[170];
                                                                                                                                            }
                                                                                                                                            if (v10.has(v11)) break block148;
                                                                                                                                            v10 = var4_5;
                                                                                                                                        }
                                                                                                                                        if (var2_2) break block191;
                                                                                                                                        if (v10.has(ConfigManager.b[209])) // goto
                                                                                                                                        break block192;
                                                                                                                                        catch (Exception v25) {
                                                                                                                                            throw v25;
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                    v10 = var4_5;
                                                                                                                                    v26 = ConfigManager.b[162];
                                                                                                                                    if (var2_2) break block150;
                                                                                                                                    break block193;
                                                                                                                                    catch (Exception v27) {
                                                                                                                                        throw v27;
                                                                                                                                    }
                                                                                                                                }
                                                                                                                                if (!v10.has(v26)) break block148;
                                                                                                                                break block194;
                                                                                                                                catch (Exception v28) {
                                                                                                                                    throw v28;
                                                                                                                                }
                                                                                                                            }
                                                                                                                            v10 = var4_5;
                                                                                                                        }
                                                                                                                        var17_4 = ConfigManager.b;
                                                                                                                        v26 = var17_4[162];
                                                                                                                    }
                                                                                                                    if (var2_2) break block151;
                                                                                                                    if (!v10.has(v26)) break block152;
                                                                                                                    break block195;
                                                                                                                    catch (Exception v30) {
                                                                                                                        throw v30;
                                                                                                                    }
                                                                                                                }
                                                                                                                v10 = var4_5;
                                                                                                                v26 = ConfigManager.b[162];
                                                                                                            }
                                                                                                            v32 = v10.get(v26).getAsBoolean();
                                                                                                            if (var2_2) break block153;
                                                                                                            if (!v32) break block152;
                                                                                                            v32 = true;
                                                                                                            break block153;
                                                                                                        }
                                                                                                        v32 = false;
                                                                                                    }
                                                                                                    var5_6 = v32;
                                                                                                    v34 = var4_5.has(ConfigManager.b[155]);
                                                                                                    if (var2_2) break block154;
                                                                                                    if (!v34) break block196;
                                                                                                    break block197;
                                                                                                    catch (Exception v35) {
                                                                                                        throw v35;
                                                                                                    }
                                                                                                }
                                                                                                v34 = var4_5.get(ConfigManager.b[155]).getAsBoolean();
                                                                                                if (var2_2) break block154;
                                                                                                break block198;
                                                                                                catch (Exception v36) {
                                                                                                    throw v36;
                                                                                                }
                                                                                            }
                                                                                            if (!v34) break block155;
                                                                                        }
                                                                                        v34 = true;
                                                                                        break block154;
                                                                                    }
                                                                                    v34 = false;
                                                                                }
                                                                                var6_10 = v34;
                                                                                v38 = ConfigManager.b[170];
                                                                                v39 = var5_6;
                                                                                if (var2_2) break block156;
                                                                                if (v39) break block199;
                                                                                break block200;
                                                                                catch (Exception v40) {
                                                                                    throw v40;
                                                                                }
                                                                            }
                                                                            v39 = var6_10;
                                                                            if (var2_2) break block156;
                                                                            break block201;
                                                                            catch (Exception v41) {
                                                                                throw v41;
                                                                            }
                                                                        }
                                                                        if (!v39) break block157;
                                                                    }
                                                                    v39 = true;
                                                                    break block156;
                                                                }
                                                                v39 = false;
                                                            }
                                                            var4_5.addProperty(v38, Boolean.valueOf(v39));
                                                        }
                                                        catch (Exception var5_7) {
                                                        }
                                                    }
                                                    v43 = var4_5;
                                                    v44 = ConfigManager.b[83];
                                                    if (var2_2) break block158;
                                                    if (!v43.has(v44)) break block159;
                                                    break block202;
                                                    catch (Exception v45) {
                                                        throw v45;
                                                    }
                                                }
                                                v43 = var4_5;
                                                if (var2_2) // goto
                                                break block203;
                                                catch (Exception v46) {
                                                    throw v46;
                                                }
                                            }
                                            v44 = ConfigManager.b[18];
                                        }
                                        if (v43.has(v44)) break block159;
                                        try {
                                            block161: {
                                                block160: {
                                                    v43 = var4_5;
lbl269:

                                                    v48 = ConfigManager.b[51];
                                                    if (!var4_5.get(ConfigManager.b[83]).getAsBoolean()) break block160;
                                                    v49 = ConfigManager.b[19];
                                                    break block161;
                                                }
                                                var17_4 = ConfigManager.b;
                                                v49 = var17_4[132];
                                            }
                                            v43.addProperty(v48, v49);
                                        }
                                        catch (Exception var5_8) {
                                        }
                                    }
                                    var5_9 = ConfigManager.configKeys(var0);
                                    for (Setting var7_12 : var0.getSettings()) {
                                        block168: {
                                            block166: {
                                                block165: {
                                                    block163: {
                                                        block164: {
                                                            block205: {
                                                                block204: {
                                                                    var8_13 = var5_9.get(var7_12);
                                                                    if (var2_2) break block162;
                                                                    v51 = var4_5;
                                                                    if (var2_2) break block163;
                                                                    break block204;
                                                                    catch (Exception v52) {
                                                                        throw v52;
                                                                    }
                                                                }
                                                                if (v51.has(var8_13)) break block164;
                                                                var9_14 /* !! */  = ConfigManager.LEGACY_SETTING_KEYS.get(var0.getName() + "/" + var8_13);
                                                                if (var9_14 /* !! */  == null) break block164;
                                                                v51 = var4_5;
                                                                v54 = var9_14 /* !! */ ;
                                                                if (var2_2) break block165;
                                                                break block205;
                                                                catch (Exception v55) {
                                                                    throw v55;
                                                                }
                                                            }
                                                            if (!v51.has(v54)) break block164;
                                                            var8_13 = var9_14 /* !! */ ;
                                                        }
                                                        v51 = var4_5;
                                                    }
                                                    if (var2_2) break block166;
                                                    v54 = var8_13;
                                                }
                                                if (!v51.has(v54)) {
                                                    var17_4 = ConfigManager.b;
                                                    var9_14 /* !! */  = var8_13.replace(var17_4[189], var17_4[208]).replace(var17_4[154], var17_4[103]);
                                                    if (!var4_5.has((String)var9_14 /* !! */ )) {
                                                        continue;
                                                    }
                                                    var8_13 = var9_14 /* !! */ ;
                                                }
                                                v51 = var4_5.get(var8_13);
                                            }
                                            var9_14 /* !! */  = v51;
                                            try {
                                                block175: {
                                                    block211: {
                                                        block174: {
                                                            block173: {
                                                                block172: {
                                                                    block210: {
                                                                        block209: {
                                                                            block208: {
                                                                                block207: {
                                                                                    block206: {
                                                                                        block171: {
                                                                                            block170: {
                                                                                                block169: {
                                                                                                    block167: {
                                                                                                        v59 = var7_12;
                                                                                                        if (var2_2) break block167;
                                                                                                        if (v59 instanceof BooleanSetting) {
                                                                                                        }
                                                                                                        var10_15 = (BooleanSetting)var7_12;
                                                                                                        var10_15.setValue(ConfigManager.asBoolean((JsonElement)var9_14 /* !! */ ));
                                                                                                        if (!var2_2) break block168;
lbl352:

                                                                                                        v59 = var7_12;
                                                                                                    }
                                                                                                    if (var2_2) break block169;
                                                                                                    if (v59 instanceof NumberSetting) {
                                                                                                    }
                                                                                                    var11_17 = (NumberSetting)var7_12;
                                                                                                    var11_17.setExact(var9_14 /* !! */ .getAsDouble());
                                                                                                    if (!var2_2) break block168;
lbl368:

                                                                                                    v59 = var7_12;
                                                                                                }
                                                                                                if (var2_2) break block170;
                                                                                                if (v59 instanceof ColorSetting) {
                                                                                                }
                                                                                                var12_18 = (ColorSetting)var7_12;
                                                                                                var12_18.setARGB(var9_14 /* !! */ .getAsInt());
                                                                                                if (!var2_2) break block168;
lbl384:

                                                                                                v59 = var7_12;
                                                                                            }
                                                                                            if (var2_2) break block171;
                                                                                            if (v59 instanceof KeybindSetting) {
                                                                                            }
                                                                                            var13_19 = (KeybindSetting)var7_12;
                                                                                            var13_19.setKey(var9_14 /* !! */ .getAsInt());
                                                                                            if (!var2_2) break block168;
lbl400:

                                                                                            v59 = var7_12;
                                                                                        }
                                                                                        if (var2_2) break block172;
                                                                                        if (!(v59 instanceof EnumSetting)) // goto
                                                                                        break block206;
                                                                                        catch (Exception v68) {
                                                                                            throw v68;
                                                                                        }
                                                                                    }
                                                                                    v59 = var7_12;
                                                                                    if (var2_2) break block172;
                                                                                    var14_20 = (EnumSetting)v59;
                                                                                    if (!var9_14 /* !! */ .isJsonPrimitive()) // goto
                                                                                    v70 = var9_14 /* !! */ .getAsJsonPrimitive().isBoolean();
                                                                                    if (var2_2) break block173;
                                                                                    break block207;
                                                                                    catch (Exception v71) {
                                                                                        throw v71;
                                                                                    }
                                                                                }
                                                                                if (!v70) // goto
                                                                                break block208;
                                                                                catch (Exception v72) {
                                                                                    throw v72;
                                                                                }
                                                                            }
                                                                            if (var9_14 /* !! */ .getAsBoolean()) break block168;
                                                                            break block209;
                                                                            catch (Exception v73) {
                                                                                throw v73;
                                                                            }
                                                                        }
                                                                        var14_20.setByName(ConfigManager.b[132]);
                                                                        if (!var2_2) break block168;
                                                                        break block210;
                                                                        catch (Exception v74) {
                                                                            throw v74;
                                                                        }
                                                                    }
                                                                    v59 = var7_12;
                                                                }
                                                                if (var2_2) break block174;
                                                                v70 = v59 instanceof EnumSetting;
                                                            }
                                                            if (!v70) // goto
                                                            var15_21 = (EnumSetting)var7_12;
                                                            var15_21.setByName(var9_14 /* !! */ .getAsString());
                                                            if (!var2_2) break block168;
lbl464:

                                                            v59 = var7_12;
                                                        }
                                                        if (var2_2) break block175;
                                                        if (!(v59 instanceof StringSetting)) break block168;
                                                        break block211;
                                                        catch (Exception v78) {
                                                            throw v78;
                                                        }
                                                    }
                                                    v59 = var7_12;
                                                }
                                                var16_22 = (StringSetting)v59;
                                                var16_22.setValue(var9_14 /* !! */ .getAsString());
                                            }
                                            catch (Exception var10_16) {
                                            }
                                        }
                                        if (!var2_2) continue;
                                    }
                                }
                                v5 = var1_1;
                                if (var2_2) break block176;
                                v6 = ConfigManager.b[25];
                            }
                            if (!v5.has(v6)) break block177;
                            v5 = var1_1.get(ConfigManager.b[25]);
                        }
                        if (v5.isJsonObject()) {
                            var0.readExtra(var1_1.getAsJsonObject(ConfigManager.b[25]));
                        }
                    }
                    v83 = var0;
                    if (var2_2) break block178;
                    v83.onSettingsChanged();
                }
                if (var3_3 == null) break block179;
                v83 = var0;
            }
            v83.setEnabled(var3_3);
        }
    }

    private static boolean asBoolean(JsonElement jsonElement) {
        JsonElement jsonElement2;
        block20: {
            block21: {
                boolean bl;
                block26: {
                    block23: {
                        String string;
                        boolean bl2;
                        block25: {
                            String string2;
                            block24: {
                                block22: {
                                    bl2 = a;
                                    jsonElement2 = jsonElement;
                                    if (bl2) break block20;
                                    if (!jsonElement2.isJsonPrimitive()) break block21;
                                    jsonElement2 = jsonElement.getAsJsonPrimitive();
                                    if (bl2) break block20;
                                    if (!jsonElement2.isString()) break block21;
                                    string2 = jsonElement.getAsString().trim();
                                    string = string2;
                                    if (bl2) break block22;
                                    if (string.isEmpty()) break block23;
                                    string = string2;
                                }
                                if (bl2) break block24;
                                if (string.equalsIgnoreCase(b[216])) break block23;
                                string = string2;
                            }
                            if (bl2) break block25;
                            if (string.equalsIgnoreCase(b[217])) break block23;
                            string = string2;
                        }
                        bl = string.equalsIgnoreCase(b[165]);
                        if (bl2) break block26;
                        if (bl) break block23;
                        bl = true;
                        break block26;
                    }
                    bl = false;
                }
                return bl;
            }
            jsonElement2 = jsonElement;
        }
        return jsonElement2.getAsBoolean();
    }

    
    static {
        block23: {
            block22: {
                block21: {
                    block20: {
                        var9 = new String[221];
                        var7_1 = 0;
                        var6_2 = "f=CqS\n2`!MAW\u0014\u0015\u0011F'UqB\u0005\u001c%:\\v_\t\u0019\u007f0^\nQ<Ipb\u0005\u0017b0^\fJ9H\"u\u0016\tv!MnE\u0007+7^m]\u0001\u001e\u001bJ%Xk[\r\n`'_-{\u0005\u0002i:[\"Y\u0014\u0004l8ExS\u0016\u000bV=Cu\u0016\u0014\u001cd,Ip\u0005h XgR\tM0Mf\u0016#\u001cj\"\u000bU @qSD#u0If\u0007j%Il}\u0001\t\u0005V!UnS\u000fM<Hg\u0016%\u0013q<Cl\u0016&\u0011w\u0006V=MfY\u0013\u000eW:XcB\r\u001fku_rS\u0001\u0014\u0018f:Bd_\u0003Pu4^qS\u0000Pq:\flY\u0010\u0018l;K\u0005`-XpW#W0BfS\u0016\u0003*\u001dMlRD\u0003m4HgDK7i4_q\u0016\u0000\u0019w0Ov_\u000b\u001e\u000bU4BgZD\u0003q,@g\u0005B9MqE\u000fB9MqED\u0013m'CoW\u0010\u0019f\u0007f4\\g\u007f\u0000\u0003\u0015B9MqED\u0012d6GeD\u000b\u0005k1\f`Z\u0011\u0002\n`8CvS3\u0018`0@\b+7^m]\u0001\u001e(\u0005`-XpW\nM<Hg\u0016%\u0002h:^\fK:\fKB\u0001\u001d%\u001bMoS\u0015h<BgU\u0016\u0011c!\u0016rZ\u0005\t`'sjS\u0005\u0014\u000bU @qSD#u0If\u000bk:XkP\u001d#j Bf\nQ<Ipb\u0005\u0017b0^$W0BfS\u0016\u0003*\u001dMlRD\u0003m4HgDK7i4_q\u0016\u0006\u0002l2DvX\u0001\u0003v\u0011J XgDD\u0016w4Ag\u0019'\u001fi:^\nN0UqB\u0016\u001fn0_\nB\u0000e\"b\u0013\u0015d>_\tm<XGP\u0002\u0015f!\tD'AmDD8P\u0011\tf:_oS\u0010\u0019f&\u000bd ^mD\u0005^o&Cl\nG4OiQ\u0016\u001fp;H\u0005V!UnS\bg4OiR\u0016\u001fu&F:YnRD\u001ej!\foY\u0012\u0015%!Dg\u0016\u0006\u0002j>Il\u0016\u0007\u001fk3Ee\u0016\u0005\u0003l1I\u0003w2N\u0005D9Nw[\u0007f4\\gc\u0016\u001c\u0004Q<Bv\u001eB\u0000e\"b\u0013\u0015d>_-~\r\u0014`uDgZ\u0000Pl!Io\u0016\n\u0011h0\u0006D6OgX\u0010\u0005r<BeE\u000bU4BgZD\u0003q,@g\bv0Xv_\n\u0017v\ng4HeS\u0017#`0B\u0015W0BfS\u0016\u0003*\u0005@cO\u0001\u0002vzomZ\u000b\u0002$W0BfS\u0016\u0003*\u001dMlRD\u0003m4HgDK7i4_q\u0016\u0000\u0019v!CpB\r\u001fk\nh4Ogs\u0002\u0016`6X\u000fM<Hg\u0016\u0017\u0013j'I`Y\u0005\u0002a\u0004f%_[\u001cF:YnRD\u001ej!\f`W\u0007\u001b% \\\"B\f\u0015%6ClP\r\u0017\tm<XGP\u0002\u0015f!\u000bM4Bf\u00160\u0007`4Gq#W0BfS\u0016\u0003*\u001dMlRD\u0003m4HgDK7i4_q\u0016\u0007\u0018w:AcB\r\u0013\fJ9H\"z\r\u0017m!ElQ\u0010D;OjY\u0016Pj%Xk[\r\n`'\u0007h:HwZ\u0001\u0003\fJ9H\"u\u0016\tv!MnE\u0004f4\\g0H:ZgRD=p&Ea\u00164\u001cd,Ip\u0016\u0005\u001eauXjSD\u001c|'EaED\u0012d'\fkX\u0010\u001f%\u0018Yq_\u0007\u0007r<BfY\u0013\u0003#F:YnRD\u001ej!\fpS\u0017\u0004j'I\"B\f\u0015%6ClP\r\u0017%7Ma]\u0011\u0000\u0006m<HfS\n\u0005r<BeE\fV=Cu\u0016\r\u001e%6DcB\fK:\fKB\u0001\u001d%\u001bMoS\u0005h XgR\tD9EeX\t\u0015k!\u0010B9MqED\u0014l&XmD\u0010\u0019j;\u000fB9MqED\u0014l'IaB\r\u001fk\u0004f%_Z\tf:_oS\u0010\u0019f&\tF\u0005\u007f\"F\u0005\u001e`9\u0007f4\\gc\u0016\u001c\nG4OiQ\u0016\u001fp;H\u000ek:XkP\u001d\"`$YgE\u0010\u0003\u0007f4\\g\u007f\u0000\u0003\bg4OiR\u0016\u001fu\fk:XkP\u001d?k9ElS\u0004f4\\g\rM<Hg\u00167\u0015i0OvY\u0016\fW<KjBD\u0012p!XmX\u0005V6MnS\u000eW:XcB\r\u001fku_rS\u0001\u0014\u0010F\u0005\u007f\"F\u0005\u001e`9\u0003CU\u0007\u0015k!\u0005H _kU\u000bV=Cu\u0016\b\tw<Oq\tQ<Ip\u0016\u0010\u0011b&\fk:XkP\u001d?k9ElS\nM4Bf\u00167\u0007l;K\u0011M<Hg\u00164\u001cd,Ip\u0016)\u001fa0@\u0004+!Ar\n`8CvS3\u0018`0@\u000fM<Hg\u0016\u0017\u0013j'I`Y\u0005\u0002a\u0006f:@mC\u0016\u000ef=CqS\n2`!MAW\u0014\u0015\u0004F=Mv\nG:N\"^\u0001\u0019b=X\u0006v:OkW\b\u0015C4EnS\u0000Pq:\fqW\u0012\u0015%6ClP\r\u0017\tK:_vW\b\u0017l4\u000bk:XkP\u001d#j Bf\nn<@ns\u0002\u0016`6X\u0006D6OgX\u0010\u0007`;M`Z\u0001\u0014\nB\u0000e\"b\u0013\u0015d>_\u0016V=Cu\u0016\b\tw<Oq\u0019&\u0011f>KpY\u0011\u001ea\nC @nT\u0016\u0019b=X\nN0UqB\u0016\u001fn0_\rM<Hg\u00167\u0015i0OvY\u0016\fH _kUD i4UgD\u0007i,^kU\u0017)2H:ZgRD?i1\fN_\u0003\u0018q<Be\u0016\u0005\u001eaucnRD3w,_vW\b\u0003%<BvYD>j&XcZ\u0003\u0019d0D ^mD\u0005Pq:Ci\u0016\u000b\u0006`'\fqS\u0010\u0004l;Kq\u0016\u0002\u0002j8\fmB\f\u0015wuAmR\u0017Pc:^\"M\u0019\nV\u0016cPs&?D\u0007h\u0005H _kU\u0004+7Mi\u0007`;M`Z\u0001\u0014\u0003F\u0005\u007f.Q=I\"T\u0005\u0013n \\\"R\r\u0014%;Cv\u0016\b\u001fd1\fg_\u0010\u0018`'\u0000\"C\u0017\u0019k2\ffS\u0002\u0011p9Xq\nF:^g\u0019'\u001fi:^\u0007r<BfY\u0013\u0003\u0007j%Il}\u0001\t\u0004K:Bg\u0005q'MkZ\u0005F:@mD\u0005q'MkZ\nV%El\u0016\u0017\u0000`0H\nm<KjZ\r\u0017m!_\u0003J3J\u0006M:X`W\u0016\"J%Xk[\r\n`'_-a\u0005\u001cn&U\"W\n\u0013m:^\"Y\u0014\u0004l8ExS\u0016\rk:XkP\r\u0013d!EmX\u0017\u000fM<Hg\u0016%\u0013q<Cl\u0016&\u0011w\u0007i,^kU\u0017)\u0005u @qS\u0010B9MqED\u0002`3^cU\u0010\u0019j;\u0010B9MqED\u0012w<KjB\n\u0015v&\u000fB9MqED\u0012i ^\"E\r\n`\nn<@ns\u0002\u0016`6X$W0BfS\u0016\u0003*\u001dMlRD\u0003m4HgDK7i4_q\u0016\u0016\u0015c'MaB\r\u001fk\u000bI0Jv\u0016\u0006\u0005q!Cl\nF9IcXD&l0[\fJ9H\"z\r\u0017m!ElQ\u0016V=Cu\u0016\b\tw<Oq\u0019&\u0011f>KpY\u0011\u001ea\u0005f:@mD\bV=Cu\u0016' V\u0015C4EnS\u0000Pq:\fnY\u0005\u0014%6ClP\r\u0017\fV=Cu\u0016\r\u001e%6DcB\u0004V<Vg\nV6CpS\u0006\u001fd'H\u0004K:Bg\u000fL;ZgX\u0010\u001fw,\fqU\u0005\u001c`\tF\u0005\u007f\"F\u0005\u001e`9\u0005u4^vE\bi,^kU\u00174]\u0003j3J\rG:YlU\u0001Pm0Ee^\u0010\nD'AmC\u0016PM\u0000h\fH _kUD i4UgD\rB9MqED\u0001p4@kB\u001d\u0003F\u0005\u007f\bK4AgB\u0005\u0017v\tQ<Ip\u0016\u0010\u0011b&$W0_vY\u0016\u0015auXjSD\u0013j;JkQD\u0012d6GwFHPw0XpO\r\u001eb\nK4AgF\b\u0011q0_\u0004f%_Z\u0006I,^kU\u0017 B\u0000e\"b\u0013\u0015d>_-e\u0007\u001fw0NmW\u0016\u0014%7Ma]\u0003\u0002j Bf)W0BfS\u0016\u0003*\u001dMlRD\u0003m4HgDK7i4_q\u0016\u0006\u0011f>KpY\u0011\u001eauNnC\u0016\bv0Xv_\n\u0017v\nv=Cuy\u0010\u0018`'_\u000bI<BgED\u0003m:[l\u0007h:HwZ\u0001\u0003\u0014M<Hg\u0016-\u001es0BvY\u0016\t%\u0018CfS\b\u0004f%_[\r*6Cq[\u0001\u0004l6_-CK\u0011L;BgDD\u0016w4Ag\u0019'\u001fi:^!W0BfS\u0016\u0003*\u001dMlRD\u0003m4HgDK7i4_q\u0016\u0015\u0005d9EvO\u0004Q<Bv\u0005F:@mD\u000fL;ZgX\u0010\u001fw,\fQU\u0005\u001c`\u000bM<Hg\u0016%\u0002h:Yp\nC @nt\u0016\u0019b=X\u0004V<Vg\tM<KjZ\r\u0017m!\u0006I,^kU\u0017\bi,^kU\u00174]\u0005V6MnS#W0BfS\u0016\u0003*\u001dMlRD\u0003m4HgDK7i4_q\u0016\u0006\u001cp'\fq_\u001e\u0015\nv=Cuy\u0010\u0018`'_%C:@fS\u0000Pq=I\"u4#%8CfC\b\u0015%<BvYD;`,_vD\u000b\u001b`&\ng4HeS\u0017#`0B*H:ZgRD3p&Xm[D5k1\fAD\u001d\u0003q4@q\u0016\u000b\u001eq:\foC\b\u0004l%@kS\u0016\u0003\nV%El\u0016\u0017\u0000`0H8F _vY\tP@;H\"u\u0016\tv!MnED\u0003`!XkX\u0003\u0003%6CwZ\u0000Pk:X\"T\u0001Pf4^p_\u0001\u0014%4OpY\u0017\u0003\u0012V=Cu\u0016\b\tw<Oq\u0019%\u0013f0Bv\rG:YlU\u0001Pm0Ee^\u0010\u0005v!UnS\u0006F:@mC\u0016\bV=Cu\u0016' V\u000ek:XkP\u001d\"`$YgE\u0010\u0003\rk:XkP\r\u0013d!EmX\u0017\nh4Ogs\u0002\u0016`6X\u000eP&ElQD\u0014`3MwZ\u0010\u0003\u0013F _vY\tP@;H\"u\u0016\tv!MnE\u001fB\u0000e\"b\u0013\u0015d>_-~\r\u0014`uDmB\u0006\u0011wu_gZ\u0001\u0013q:^\u0005c4@qS\u0004k:Bg\nG:N\"^\u0001\u0019b=X";
                        var8_3 = "f=CqS\n2`!MAW\u0014\u0015\u0011F'UqB\u0005\u001c%:\\v_\t\u0019\u007f0^\nQ<Ipb\u0005\u0017b0^\fJ9H\"u\u0016\tv!MnE\u0007+7^m]\u0001\u001e\u001bJ%Xk[\r\n`'_-{\u0005\u0002i:[\"Y\u0014\u0004l8ExS\u0016\u000bV=Cu\u0016\u0014\u001cd,Ip\u0005h XgR\tM0Mf\u0016#\u001cj\"\u000bU @qSD#u0If\u0007j%Il}\u0001\t\u0005V!UnS\u000fM<Hg\u0016%\u0013q<Cl\u0016&\u0011w\u0006V=MfY\u0013\u000eW:XcB\r\u001fku_rS\u0001\u0014\u0018f:Bd_\u0003Pu4^qS\u0000Pq:\flY\u0010\u0018l;K\u0005`-XpW#W0BfS\u0016\u0003*\u001dMlRD\u0003m4HgDK7i4_q\u0016\u0000\u0019w0Ov_\u000b\u001e\u000bU4BgZD\u0003q,@g\u0005B9MqE\u000fB9MqED\u0013m'CoW\u0010\u0019f\u0007f4\\g\u007f\u0000\u0003\u0015B9MqED\u0012d6GeD\u000b\u0005k1\f`Z\u0011\u0002\n`8CvS3\u0018`0@\b+7^m]\u0001\u001e(\u0005`-XpW\nM<Hg\u0016%\u0002h:^\fK:\fKB\u0001\u001d%\u001bMoS\u0015h<BgU\u0016\u0011c!\u0016rZ\u0005\t`'sjS\u0005\u0014\u000bU @qSD#u0If\u000bk:XkP\u001d#j Bf\nQ<Ipb\u0005\u0017b0^$W0BfS\u0016\u0003*\u001dMlRD\u0003m4HgDK7i4_q\u0016\u0006\u0002l2DvX\u0001\u0003v\u0011J XgDD\u0016w4Ag\u0019'\u001fi:^\nN0UqB\u0016\u001fn0_\nB\u0000e\"b\u0013\u0015d>_\tm<XGP\u0002\u0015f!\tD'AmDD8P\u0011\tf:_oS\u0010\u0019f&\u000bd ^mD\u0005^o&Cl\nG4OiQ\u0016\u001fp;H\u0005V!UnS\bg4OiR\u0016\u001fu&F:YnRD\u001ej!\foY\u0012\u0015%!Dg\u0016\u0006\u0002j>Il\u0016\u0007\u001fk3Ee\u0016\u0005\u0003l1I\u0003w2N\u0005D9Nw[\u0007f4\\gc\u0016\u001c\u0004Q<Bv\u001eB\u0000e\"b\u0013\u0015d>_-~\r\u0014`uDgZ\u0000Pl!Io\u0016\n\u0011h0\u0006D6OgX\u0010\u0005r<BeE\u000bU4BgZD\u0003q,@g\bv0Xv_\n\u0017v\ng4HeS\u0017#`0B\u0015W0BfS\u0016\u0003*\u0005@cO\u0001\u0002vzomZ\u000b\u0002$W0BfS\u0016\u0003*\u001dMlRD\u0003m4HgDK7i4_q\u0016\u0000\u0019v!CpB\r\u001fk\nh4Ogs\u0002\u0016`6X\u000fM<Hg\u0016\u0017\u0013j'I`Y\u0005\u0002a\u0004f%_[\u001cF:YnRD\u001ej!\f`W\u0007\u001b% \\\"B\f\u0015%6ClP\r\u0017\tm<XGP\u0002\u0015f!\u000bM4Bf\u00160\u0007`4Gq#W0BfS\u0016\u0003*\u001dMlRD\u0003m4HgDK7i4_q\u0016\u0007\u0018w:AcB\r\u0013\fJ9H\"z\r\u0017m!ElQ\u0010D;OjY\u0016Pj%Xk[\r\n`'\u0007h:HwZ\u0001\u0003\fJ9H\"u\u0016\tv!MnE\u0004f4\\g0H:ZgRD=p&Ea\u00164\u001cd,Ip\u0016\u0005\u001eauXjSD\u001c|'EaED\u0012d'\fkX\u0010\u001f%\u0018Yq_\u0007\u0007r<BfY\u0013\u0003#F:YnRD\u001ej!\fpS\u0017\u0004j'I\"B\f\u0015%6ClP\r\u0017%7Ma]\u0011\u0000\u0006m<HfS\n\u0005r<BeE\fV=Cu\u0016\r\u001e%6DcB\fK:\fKB\u0001\u001d%\u001bMoS\u0005h XgR\tD9EeX\t\u0015k!\u0010B9MqED\u0014l&XmD\u0010\u0019j;\u000fB9MqED\u0014l'IaB\r\u001fk\u0004f%_Z\tf:_oS\u0010\u0019f&\tF\u0005\u007f\"F\u0005\u001e`9\u0007f4\\gc\u0016\u001c\nG4OiQ\u0016\u001fp;H\u000ek:XkP\u001d\"`$YgE\u0010\u0003\u0007f4\\g\u007f\u0000\u0003\bg4OiR\u0016\u001fu\fk:XkP\u001d?k9ElS\u0004f4\\g\rM<Hg\u00167\u0015i0OvY\u0016\fW<KjBD\u0012p!XmX\u0005V6MnS\u000eW:XcB\r\u001fku_rS\u0001\u0014\u0010F\u0005\u007f\"F\u0005\u001e`9\u0003CU\u0007\u0015k!\u0005H _kU\u000bV=Cu\u0016\b\tw<Oq\tQ<Ip\u0016\u0010\u0011b&\fk:XkP\u001d?k9ElS\nM4Bf\u00167\u0007l;K\u0011M<Hg\u00164\u001cd,Ip\u0016)\u001fa0@\u0004+!Ar\n`8CvS3\u0018`0@\u000fM<Hg\u0016\u0017\u0013j'I`Y\u0005\u0002a\u0006f:@mC\u0016\u000ef=CqS\n2`!MAW\u0014\u0015\u0004F=Mv\nG:N\"^\u0001\u0019b=X\u0006v:OkW\b\u0015C4EnS\u0000Pq:\fqW\u0012\u0015%6ClP\r\u0017\tK:_vW\b\u0017l4\u000bk:XkP\u001d#j Bf\nn<@ns\u0002\u0016`6X\u0006D6OgX\u0010\u0007`;M`Z\u0001\u0014\nB\u0000e\"b\u0013\u0015d>_\u0016V=Cu\u0016\b\tw<Oq\u0019&\u0011f>KpY\u0011\u001ea\nC @nT\u0016\u0019b=X\nN0UqB\u0016\u001fn0_\rM<Hg\u00167\u0015i0OvY\u0016\fH _kUD i4UgD\u0007i,^kU\u0017)2H:ZgRD?i1\fN_\u0003\u0018q<Be\u0016\u0005\u001eaucnRD3w,_vW\b\u0003%<BvYD>j&XcZ\u0003\u0019d0D ^mD\u0005Pq:Ci\u0016\u000b\u0006`'\fqS\u0010\u0004l;Kq\u0016\u0002\u0002j8\fmB\f\u0015wuAmR\u0017Pc:^\"M\u0019\nV\u0016cPs&?D\u0007h\u0005H _kU\u0004+7Mi\u0007`;M`Z\u0001\u0014\u0003F\u0005\u007f.Q=I\"T\u0005\u0013n \\\"R\r\u0014%;Cv\u0016\b\u001fd1\fg_\u0010\u0018`'\u0000\"C\u0017\u0019k2\ffS\u0002\u0011p9Xq\nF:^g\u0019'\u001fi:^\u0007r<BfY\u0013\u0003\u0007j%Il}\u0001\t\u0004K:Bg\u0005q'MkZ\u0005F:@mD\u0005q'MkZ\nV%El\u0016\u0017\u0000`0H\nm<KjZ\r\u0017m!_\u0003J3J\u0006M:X`W\u0016\"J%Xk[\r\n`'_-a\u0005\u001cn&U\"W\n\u0013m:^\"Y\u0014\u0004l8ExS\u0016\rk:XkP\r\u0013d!EmX\u0017\u000fM<Hg\u0016%\u0013q<Cl\u0016&\u0011w\u0007i,^kU\u0017)\u0005u @qS\u0010B9MqED\u0002`3^cU\u0010\u0019j;\u0010B9MqED\u0012w<KjB\n\u0015v&\u000fB9MqED\u0012i ^\"E\r\n`\nn<@ns\u0002\u0016`6X$W0BfS\u0016\u0003*\u001dMlRD\u0003m4HgDK7i4_q\u0016\u0016\u0015c'MaB\r\u001fk\u000bI0Jv\u0016\u0006\u0005q!Cl\nF9IcXD&l0[\fJ9H\"z\r\u0017m!ElQ\u0016V=Cu\u0016\b\tw<Oq\u0019&\u0011f>KpY\u0011\u001ea\u0005f:@mD\bV=Cu\u0016' V\u0015C4EnS\u0000Pq:\fnY\u0005\u0014%6ClP\r\u0017\fV=Cu\u0016\r\u001e%6DcB\u0004V<Vg\nV6CpS\u0006\u001fd'H\u0004K:Bg\u000fL;ZgX\u0010\u001fw,\fqU\u0005\u001c`\tF\u0005\u007f\"F\u0005\u001e`9\u0005u4^vE\bi,^kU\u00174]\u0003j3J\rG:YlU\u0001Pm0Ee^\u0010\nD'AmC\u0016PM\u0000h\fH _kUD i4UgD\rB9MqED\u0001p4@kB\u001d\u0003F\u0005\u007f\bK4AgB\u0005\u0017v\tQ<Ip\u0016\u0010\u0011b&$W0_vY\u0016\u0015auXjSD\u0013j;JkQD\u0012d6GwFHPw0XpO\r\u001eb\nK4AgF\b\u0011q0_\u0004f%_Z\u0006I,^kU\u0017 B\u0000e\"b\u0013\u0015d>_-e\u0007\u001fw0NmW\u0016\u0014%7Ma]\u0003\u0002j Bf)W0BfS\u0016\u0003*\u001dMlRD\u0003m4HgDK7i4_q\u0016\u0006\u0011f>KpY\u0011\u001eauNnC\u0016\bv0Xv_\n\u0017v\nv=Cuy\u0010\u0018`'_\u000bI<BgED\u0003m:[l\u0007h:HwZ\u0001\u0003\u0014M<Hg\u0016-\u001es0BvY\u0016\t%\u0018CfS\b\u0004f%_[\r*6Cq[\u0001\u0004l6_-CK\u0011L;BgDD\u0016w4Ag\u0019'\u001fi:^!W0BfS\u0016\u0003*\u001dMlRD\u0003m4HgDK7i4_q\u0016\u0015\u0005d9EvO\u0004Q<Bv\u0005F:@mD\u000fL;ZgX\u0010\u001fw,\fQU\u0005\u001c`\u000bM<Hg\u0016%\u0002h:Yp\nC @nt\u0016\u0019b=X\u0004V<Vg\tM<KjZ\r\u0017m!\u0006I,^kU\u0017\bi,^kU\u00174]\u0005V6MnS#W0BfS\u0016\u0003*\u001dMlRD\u0003m4HgDK7i4_q\u0016\u0006\u001cp'\fq_\u001e\u0015\nv=Cuy\u0010\u0018`'_%C:@fS\u0000Pq=I\"u4#%8CfC\b\u0015%<BvYD;`,_vD\u000b\u001b`&\ng4HeS\u0017#`0B*H:ZgRD3p&Xm[D5k1\fAD\u001d\u0003q4@q\u0016\u000b\u001eq:\foC\b\u0004l%@kS\u0016\u0003\nV%El\u0016\u0017\u0000`0H8F _vY\tP@;H\"u\u0016\tv!MnED\u0003`!XkX\u0003\u0003%6CwZ\u0000Pk:X\"T\u0001Pf4^p_\u0001\u0014%4OpY\u0017\u0003\u0012V=Cu\u0016\b\tw<Oq\u0019%\u0013f0Bv\rG:YlU\u0001Pm0Ee^\u0010\u0005v!UnS\u0006F:@mC\u0016\bV=Cu\u0016' V\u000ek:XkP\u001d\"`$YgE\u0010\u0003\rk:XkP\r\u0013d!EmX\u0017\nh4Ogs\u0002\u0016`6X\u000eP&ElQD\u0014`3MwZ\u0010\u0003\u0013F _vY\tP@;H\"u\u0016\tv!MnE\u001fB\u0000e\"b\u0013\u0015d>_-~\r\u0014`uDmB\u0006\u0011wu_gZ\u0001\u0013q:^\u0005c4@qS\u0004k:Bg\nG:N\"^\u0001\u0019b=X".length();
                        var5_4 = 14;
                        var4_5 = -1;
lbl7:

                        while (true) {
                            v0 = ++var4_5;
                            v1 = var6_2.substring(v0, v0 + var5_4);
                            v2 = -1;
                            break block20;
                            break;
                        }
lbl12:

                        while (true) {
                            var9[var7_1++] = v3.intern();
                            if ((var4_5 += var5_4) < var8_3) {
                                var5_4 = var6_2.charAt(var4_5);
                                continue;
                            }
                            var6_2 = "v:OkW\b\tK:_vW\b\u0017l4";
                            var8_3 = "v:OkW\b\tK:_vW\b\u0017l4".length();
                            var5_4 = 6;
                            var4_5 = -1;
lbl21:

                            while (true) {
                                v4 = ++var4_5;
                                v1 = var6_2.substring(v4, v4 + var5_4);
                                v2 = 0;
                                break block20;
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
                            break block21;
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
                                v10 = 5;
                                break;
                            }
                            case 1: {
                                v10 = 85;
                                break;
                            }
                            case 2: {
                                v10 = 44;
                                break;
                            }
                            case 3: {
                                v10 = 2;
                                break;
                            }
                            case 4: {
                                v10 = 54;
                                break;
                            }
                            case 5: {
                                v10 = 100;
                                break;
                            }
                            default: {
                                v10 = 112;
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
                ConfigManager.b = var9;
                break block22;
lbl77:

                while (true) {
                    continue;
                    break;
                }
            }
            var2_8 = 750569524441925198L;
            while (true)
            ConfigManager.c = -8906333169789716047L ^ var2_8;
            break block23;
lbl87:

            while (true) {
                continue;
                break;
            }
        }
        var0_9 = 3164103855255122673L;
        while (true)
        ConfigManager.d = -3164103855255122674L ^ var0_9;
        ConfigManager.GSON = new GsonBuilder().setPrettyPrinting().create();
        var4_6 = ConfigManager.b;
        ConfigManager.PATH = FabricLoader.getInstance().getConfigDir().resolve(var4_6[39]);
        ConfigManager.windowPos = new EnumMap<Category, int[]>(Category.class);
        ConfigManager.LEGACY_NAMES = Map.of(var4_6[26], var4_6[191], var4_6[37], var4_6[167], var4_6[116], var4_6[192], var4_6[183], var4_6[99], var4_6[61], var4_6[98], var4_6[171], var4_6[174], var4_6[94], var4_6[168]);
        ConfigManager.LEGACY_SETTING_KEYS = Map.ofEntries(new Map.Entry[]{Map.entry(var4_6[54], var4_6[189]), Map.entry(var4_6[5], var4_6[1]), Map.entry(var4_6[140], var4_6[64]), Map.entry(var4_6[48], var4_6[27]), Map.entry(var4_6[215], var4_6[89]), Map.entry(var4_6[177], var4_6[83]), Map.entry(var4_6[198], var4_6[147]), Map.entry(var4_6[187], var4_6[169]), Map.entry(var4_6[17], var4_6[78]), Map.entry(var4_6[149], var4_6[145]), Map.entry(var4_6[32], var4_6[146]), Map.entry(var4_6[62], var4_6[20]), Map.entry(var4_6[55], var4_6[77]), Map.entry(var4_6[178], var4_6[22])});
    }
}
