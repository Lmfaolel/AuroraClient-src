package dev.quelchi.aurora.profile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.config.ConfigManager;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.profile.Profile;
import java.io.IOException;
import java.lang.invoke.LambdaMetafactory;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.ToLongFunction;
import net.fabricmc.loader.api.FabricLoader;

public final class ProfileManager {
    private static final Gson GSON;
    private static final Set<String> PERSONAL;
    private static final String AUTO_TEXT;
    private static boolean imported;
    private static final String[] a;

    private ProfileManager() {
    }

    public static Path dir() {
        return FabricLoader.getInstance().getConfigDir().resolve(a[11]);
    }

    private static Path configFile() {
        return FabricLoader.getInstance().getConfigDir().resolve(a[21]);
    }

    
    public static List<Profile> list() {
        block14: {
            block13: {
                block16: {
                    ProfileManager.importLegacy();
                    var1 = new ArrayList<Profile>();
                    var0_1 = Profile.a;
                    var2_2 = ProfileManager.dir();
                    v0 = var2_2;
                    if (var0_1 != 0) // goto
                    if (Files.isDirectory(v0, new LinkOption[0])) break block13;
                    break block16;
                    catch (Throwable v1) {
                        throw v1;
                    }
                }
                return var1;
            }
            try {
                v0 = var2_2;
lbl20:

                var3_3 = Files.list(v0);
                if (var0_1 != 0) break block14;
                try {
                    var3_3.filter((Predicate<Path>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Z, lambda$list$0(java.nio.file.Path ), (Ljava/nio/file/Path;)Z)()).forEach((Consumer<Path>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)V, lambda$list$1(java.util.List java.nio.file.Path ), (Ljava/nio/file/Path;)V)(var1));
                    if (var3_3 == null) goto lbl_neg_1000
                }
                catch (Throwable var4_5) {
                    block15: {
                        if (var0_1 != 0 || var3_3 == null) break block15;
                        try {
                            var3_3.close();
                        }
                        catch (Throwable var5_6) {
                            var4_5.addSuppressed(var5_6);
                        }
                    }
                    throw var4_5;
                }
lbl_neg_1000:

                {
                    var3_3.close();
                }
lbl_neg_1000:

                {
                }
            }
            catch (IOException var3_4) {
                Aurora.LOG.warn(ProfileManager.a[41], (Throwable)var3_4);
            }
        }
        var1.sort(Comparator.comparingLong((ToLongFunction<Profile>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)J, updatedAt(), (Ldev/quelchi/aurora/profile/Profile;)J)()).reversed());
        return var1;
    }

    private static Profile read(Path path) {
        int n = Profile.a;
        try {
            Profile profile;
            String[] stringArray;
            JsonObject jsonObject;
            block10: {
                block9: {
                    JsonObject jsonObject2;
                    block8: {
                        jsonObject = (JsonObject)GSON.fromJson(Files.readString(path), JsonObject.class);
                        jsonObject2 = jsonObject;
                        if (n != 0) break block8;
                        if (jsonObject2 == null) break block9;
                        jsonObject2 = jsonObject;
                    }
                    if (jsonObject2.has((stringArray = a)[38])) break block10;
                }
                return null;
            }
            Profile profile2 = profile;
            Profile profile3 = profile;
            String string = jsonObject.has(a[42]) ? jsonObject.get(a[42]).getAsString() : path.getFileName().toString();
            stringArray = a;
            long l = jsonObject.has(stringArray[10]) ? jsonObject.get(stringArray[10]).getAsLong() : 0L;
            stringArray = a;
            long l2 = jsonObject.has(stringArray[32]) ? jsonObject.get(stringArray[32]).getAsLong() : 0L;
            stringArray = a;
            String string2 = jsonObject.has(stringArray[31]) ? jsonObject.get(stringArray[31]).getAsString() : "";
            stringArray = a;
            String string3 = jsonObject.has(stringArray[5]) ? jsonObject.get(stringArray[5]).getAsString() : "";
            stringArray = a;
            profile2(string, l, l2, string2, string3, path, jsonObject.getAsJsonObject(stringArray[38]), jsonObject.has(stringArray[3]) ? jsonObject.get(stringArray[3]).getAsString() : "");
            return profile3;
        }
        catch (Exception exception) {
            Aurora.LOG.warn(a[48], (Object)path, (Object)exception);
            return null;
        }
    }

    
    public static JsonObject capture() {
        ConfigManager.save();
        int n = Profile.a;
        try {
            JsonObject jsonObject;
            JsonObject jsonObject2;
            JsonObject jsonObject3 = jsonObject2 = (JsonObject)GSON.fromJson(Files.readString(ProfileManager.configFile()), JsonObject.class);
            if (n != 0) return jsonObject3;
            if (jsonObject3 == null) {
                jsonObject3 = new JsonObject();
                return jsonObject3;
            }
            for (String string : PERSONAL) {
                jsonObject = jsonObject2;
                if (n != 0) return jsonObject;
                jsonObject.remove(string);
                if (n == 0) continue;
            }
            jsonObject = jsonObject2;
            return jsonObject;
        }
        catch (Exception exception) {
            Aurora.LOG.warn(a[7], (Throwable)exception);
            return new JsonObject();
        }
    }

    public static String summarise(JsonObject jsonObject) {
        Object object;
        block51: {
            StringBuilder stringBuilder;
            block52: {
                String string;
                StringBuilder stringBuilder2;
                block53: {
                    block54: {
                        int n;
                        int n2;
                        int n3;
                        int n4;
                        block55: {
                            block49: {
                                String string2;
                                JsonObject jsonObject2;
                                block50: {
                                    block48: {
                                        block42: {
                                            String[] stringArray;
                                            block40: {
                                                block41: {
                                                    n4 = 0;
                                                    n3 = Profile.a;
                                                    n2 = 0;
                                                    jsonObject2 = jsonObject;
                                                    if (n3 != 0) break block40;
                                                    if (!jsonObject2.has(a[25])) break block41;
                                                    jsonObject2 = jsonObject;
                                                    string2 = a[25];
                                                    if (n3 != 0) break block42;
                                                    if (!jsonObject2.get(string2).isJsonObject()) break block41;
                                                    stringArray = a;
                                                    JsonObject jsonObject3 = jsonObject.getAsJsonObject(stringArray[25]);
                                                    n4 = jsonObject3.size();
                                                    for (String string3 : jsonObject3.keySet()) {
                                                        block46: {
                                                            String string4;
                                                            JsonObject jsonObject4;
                                                            block47: {
                                                                JsonObject jsonObject5;
                                                                block45: {
                                                                    JsonObject jsonObject6;
                                                                    block43: {
                                                                        block44: {
                                                                            jsonObject6 = jsonObject3;
                                                                            if (n3 != 0) break block43;
                                                                            string2 = string3;
                                                                            if (n3 != 0) break block42;
                                                                            if (!jsonObject6.get(string2).isJsonObject()) break block44;
                                                                            jsonObject6 = jsonObject3.getAsJsonObject(string3);
                                                                            break block43;
                                                                        }
                                                                        jsonObject6 = null;
                                                                    }
                                                                    jsonObject5 = jsonObject6;
                                                                    jsonObject4 = jsonObject5;
                                                                    if (n3 != 0) break block45;
                                                                    if (jsonObject4 == null) break block46;
                                                                    jsonObject4 = jsonObject5;
                                                                }
                                                                string4 = a[29];
                                                                if (n3 != 0) break block47;
                                                                if (!jsonObject4.has(string4)) break block46;
                                                                jsonObject4 = jsonObject5;
                                                                string4 = a[16];
                                                            }
                                                            if (jsonObject4.get(string4).getAsBoolean()) {
                                                                ++n2;
                                                            }
                                                        }
                                                        if (n3 == 0) continue;
                                                        boolean bl = !Module.a;
                                                        Module.a = bl;
                                                        break;
                                                    }
                                                }
                                                jsonObject2 = jsonObject;
                                            }
                                            stringArray = a;
                                            string2 = stringArray[26];
                                        }
                                        if (n3 != 0) break block48;
                                        if (!jsonObject2.has(string2)) break block49;
                                        jsonObject2 = jsonObject;
                                        string2 = a[44];
                                    }
                                    if (n3 != 0) break block50;
                                    if (!jsonObject2.get(string2).isJsonObject()) break block49;
                                    jsonObject2 = jsonObject;
                                    string2 = a[44];
                                }
                                n = jsonObject2.getAsJsonObject(string2).size();
                                break block55;
                            }
                            n = 0;
                        }
                        int n5 = n;
                        stringBuilder = new StringBuilder();
                        object = stringBuilder.append(n2);
                        if (n3 != 0) break block51;
                        ((StringBuilder)object).append(a[14]).append(n4).append(a[45]);
                        if (n5 <= 0) break block52;
                        stringBuilder2 = stringBuilder;
                        string = a[39];
                        if (n3 != 0) break block53;
                        stringBuilder2 = stringBuilder2.append(string).append(n5);
                        if (n5 != 1) break block54;
                        string = a[27];
                        break block53;
                    }
                    string = a[36];
                }
                stringBuilder2.append(string);
            }
            object = stringBuilder;
        }
        return ((StringBuilder)object).toString();
    }

    public static Profile save(String string) throws IOException {
        return ProfileManager.write(string, ProfileManager.capture(), "", 0L, null, "");
    }

    public static Profile keep(String string, JsonObject jsonObject, String string2) throws IOException {
        return ProfileManager.write(string, ProfileManager.shareable(jsonObject), "", 0L, null, string2);
    }

    public static Profile update(Profile profile) throws IOException {
        return ProfileManager.writeTo(profile.file(), profile.name(), ProfileManager.capture(), profile.code(), profile.createdAt(), profile.from());
    }

    private static Profile write(String string, JsonObject jsonObject, String string2, long l, Path path, String string3) throws IOException {
        String string4;
        Path path2;
        block12: {
            block11: {
                Object object;
                int n;
                block9: {
                    block10: {
                        n = Profile.a;
                        object = ProfileManager.slug(string);
                        if (n != 0) break block9;
                        if (!((String)object).isEmpty()) break block10;
                        throw new IOException(a[2]);
                    }
                    Files.createDirectories(ProfileManager.dir(), new FileAttribute[0]);
                }
                String string5 = object;
                int n2 = 2;
                do {
                    String[] stringArray = a;
                    if (!Files.exists(ProfileManager.dir().resolve((String)object + stringArray[43]), new LinkOption[0])) break;
                    path2 = ProfileManager.dir();
                    if (n != 0) break block11;
                    string4 = object;
                    if (n != 0) break block12;
                    if (path2.resolve(string4 + a[43]).equals(path)) break;
                    object = string5 + "-" + n2;
                    ++n2;
                } while (n == 0);
                path2 = ProfileManager.dir().resolve((String)object + a[43]);
            }
            string4 = string;
        }
        return ProfileManager.writeTo(path2, string4, jsonObject, string2, l, string3);
    }

    private static Profile writeTo(Path path, String string, JsonObject jsonObject, String string2, long l, String string3) throws IOException {
        Profile profile;
        String string4;
        String string5;
        JsonObject jsonObject2;
        String[] stringArray;
        JsonObject jsonObject3;
        long l2;
        block14: {
            block15: {
                String string6;
                String string7;
                JsonObject jsonObject4;
                int n;
                block12: {
                    block13: {
                        long l3;
                        String string8;
                        JsonObject jsonObject5;
                        block10: {
                            block11: {
                                l2 = System.currentTimeMillis();
                                jsonObject3 = new JsonObject();
                                stringArray = a;
                                jsonObject3.addProperty(stringArray[47], string);
                                n = Profile.a;
                                jsonObject5 = jsonObject3;
                                string8 = stringArray[0];
                                l3 = l;
                                if (n != 0) break block10;
                                if (l3 != 0L) break block11;
                                l3 = l2;
                                break block10;
                            }
                            l3 = l;
                        }
                        jsonObject5.addProperty(string8, (Number)l3);
                        jsonObject3.addProperty(a[9], (Number)l2);
                        jsonObject4 = jsonObject3;
                        string7 = a[4];
                        string6 = string2;
                        if (n != 0) break block12;
                        if (string6 != null) break block13;
                        string6 = "";
                        break block12;
                    }
                    string6 = string2;
                }
                jsonObject4.addProperty(string7, string6);
                jsonObject2 = jsonObject3;
                string5 = a[28];
                string4 = string3;
                if (n != 0) break block14;
                if (string4 != null) break block15;
                string4 = "";
                break block14;
            }
            string4 = string3;
        }
        jsonObject2.addProperty(string5, string4);
        String string9 = ProfileManager.summarise(jsonObject);
        stringArray = a;
        jsonObject3.addProperty(stringArray[37], string9);
        jsonObject3.add(stringArray[46], (JsonElement)jsonObject);
        Path path2 = path.resolveSibling(String.valueOf(path.getFileName()) + stringArray[17]);
        Files.writeString(path2, (CharSequence)GSON.toJson((JsonElement)jsonObject3), new OpenOption[0]);
        Files.move(path2, path, StandardCopyOption.REPLACE_EXISTING);
        Profile profile2 = profile;
        Profile profile3 = profile;
        String string10 = string;
        long l4 = l == 0L ? l2 : l;
        profile2(string10, l4, l2, string2 == null ? "" : string2, string9, path, jsonObject, string3 == null ? "" : string3);
        return profile3;
    }

    public static Profile withCode(Profile profile, String string) {
        try {
            JsonObject jsonObject = (JsonObject)GSON.fromJson(Files.readString(profile.file()), JsonObject.class);
            String[] stringArray = a;
            jsonObject.addProperty(stringArray[31], string);
            Files.writeString(profile.file(), (CharSequence)GSON.toJson((JsonElement)jsonObject), new OpenOption[0]);
        }
        catch (Exception exception) {
            Aurora.LOG.warn(a[19], (Object)profile.file(), (Object)exception);
        }
        return new Profile(profile.name(), profile.createdAt(), profile.updatedAt(), string, profile.summary(), profile.file(), profile.data(), profile.from());
    }

    public static void delete(Profile profile) {
        try {
            Files.deleteIfExists(profile.file());
        }
        catch (IOException iOException) {
            Aurora.LOG.warn(a[20], (Object)profile.file(), (Object)iOException);
        }
    }

    public static Profile rename(Profile profile, String string) throws IOException {
        Profile profile2;
        block4: {
            Profile profile3;
            block5: {
                profile3 = ProfileManager.write(string, profile.data(), profile.code(), profile.createdAt(), profile.file(), profile.from());
                int n = Profile.a;
                profile2 = profile3;
                if (n != 0) break block4;
                if (profile2.file().equals(profile.file())) break block5;
                ProfileManager.delete(profile);
            }
            profile2 = profile3;
        }
        return profile2;
    }

    public static String apply(JsonObject jsonObject) {
        return ProfileManager.apply(jsonObject, false);
    }

    public static String applyForeign(JsonObject jsonObject) {
        return ProfileManager.apply(jsonObject, true);
    }

    
    private static String apply(JsonObject jsonObject, boolean bl) {
        int n = Profile.a;
        try {
            block23: {
                JsonObject jsonObject2;
                block24: {
                    JsonObject jsonObject3;
                    JsonObject jsonObject4;
                    block27: {
                        JsonObject jsonObject42;
                        block26: {
                            block25: {
                                JsonObject jsonObject5;
                                JsonObject jsonObject6;
                                block19: {
                                    block20: {
                                        jsonObject6 = (JsonObject)GSON.fromJson(Files.readString(ProfileManager.configFile()), JsonObject.class);
                                        jsonObject2 = bl ? ProfileManager.shareable(jsonObject) : jsonObject.deepCopy();
                                        jsonObject5 = jsonObject6;
                                        if (n != 0) break block19;
                                        if (jsonObject5 == null) break block20;
                                        for (String object2 : PERSONAL) {
                                            block22: {
                                                JsonObject jsonObject7;
                                                block21: {
                                                    jsonObject5 = jsonObject6;
                                                    if (n != 0) break block19;
                                                    if (n != 0) break block21;
                                                    if (!jsonObject5.has(object2)) break block22;
                                                    jsonObject7 = jsonObject2;
                                                }
                                                jsonObject7.add(object2, jsonObject6.get(object2));
                                            }
                                            if (n == 0) continue;
                                        }
                                    }
                                    jsonObject5 = ProfileManager.modules(jsonObject2);
                                }
                                jsonObject42 = jsonObject5;
                                jsonObject4 = ProfileManager.modules(jsonObject6);
                                if (n != 0) break block23;
                                if (jsonObject42 == null) break block24;
                                jsonObject3 = jsonObject42;
                                if (n != 0) break block25;
                                if (jsonObject3.has(a[35])) break block24;
                                jsonObject3 = jsonObject4;
                            }
                            if (n != 0) break block26;
                            if (jsonObject3 == null) break block24;
                            jsonObject3 = jsonObject4;
                        }
                        if (n != 0) break block27;
                        if (!jsonObject3.has(a[18])) break block24;
                        jsonObject3 = jsonObject42;
                    }
                    String[] stringArray = a;
                    jsonObject3.add(stringArray[18], jsonObject4.get(stringArray[18]));
                }
                Files.writeString(ProfileManager.configFile(), (CharSequence)GSON.toJson((JsonElement)jsonObject2), new OpenOption[0]);
                ConfigManager.load();
            }
            return "";
        }
        catch (Exception exception) {
            String[] stringArray = a;
            Aurora.LOG.warn(stringArray[6], (Throwable)exception);
            return stringArray[40];
        }
    }

    public static JsonObject shareable(JsonObject jsonObject) {
        JsonObject jsonObject2;
        block4: {
            JsonObject jsonObject3;
            block5: {
                jsonObject3 = jsonObject.deepCopy();
                JsonObject jsonObject4 = ProfileManager.modules(jsonObject3);
                int n = Profile.a;
                jsonObject2 = jsonObject4;
                if (n != 0) break block4;
                if (jsonObject2 == null) break block5;
                jsonObject4.remove(a[18]);
            }
            jsonObject2 = jsonObject3;
        }
        return jsonObject2;
    }

    private static JsonObject modules(JsonObject jsonObject) {
        JsonObject jsonObject2;
        block13: {
            block11: {
                String string;
                block12: {
                    int n;
                    block10: {
                        n = Profile.a;
                        jsonObject2 = jsonObject;
                        if (n != 0) break block10;
                        if (jsonObject2 == null) break block11;
                        jsonObject2 = jsonObject;
                    }
                    string = a[12];
                    if (n != 0) break block12;
                    if (!jsonObject2.has(string)) break block11;
                    jsonObject2 = jsonObject;
                    if (n != 0) break block13;
                    string = a[25];
                }
                if (!jsonObject2.get(string).isJsonObject()) break block11;
                jsonObject2 = jsonObject.getAsJsonObject(a[25]);
                break block13;
            }
            jsonObject2 = null;
        }
        return jsonObject2;
    }

    
    private static void importLegacy() {
        block18: {
            block17: {
                block20: {
                    block15: {
                        block16: {
                            var0 = Profile.a;
                            v0 = ProfileManager.imported;
                            if (var0 != 0) break block15;
                            if (!v0) break block16;
                            return;
                        }
                        v0 = true;
                    }
                    ProfileManager.imported = v0;
                    var1_1 = ProfileManager.dir();
                    v2 = var1_1;
                    if (var0 != 0) // goto
                    if (Files.isDirectory(v2, new LinkOption[0])) break block17;
                    break block20;
                    catch (Throwable v3) {
                        throw v3;
                    }
                }
                return;
            }
            try {
                v2 = var1_1;
lbl30:

                var2_2 = Files.list(v2);
                if (var0 != 0) break block18;
                try {
                    var2_2.filter((Predicate<Path>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Z, lambda$importLegacy$2(java.nio.file.Path ), (Ljava/nio/file/Path;)Z)()).forEach((Consumer<Path>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)V, lambda$importLegacy$3(java.nio.file.Path java.nio.file.Path ), (Ljava/nio/file/Path;)V)((Path)var1_1));
                    if (var2_2 == null) goto lbl_neg_1000
                }
                catch (Throwable var3_4) {
                    block19: {
                        if (var0 != 0 || var2_2 == null) break block19;
                        try {
                            var2_2.close();
                        }
                        catch (Throwable var4_5) {
                            var3_4.addSuppressed(var4_5);
                        }
                    }
                    throw var3_4;
                }
lbl_neg_1000:

                {
                    var2_2.close();
                }
lbl_neg_1000:

                {
                }
            }
            catch (IOException var2_3) {
                Aurora.LOG.warn(ProfileManager.a[13], (Throwable)var2_3);
            }
        }
    }

    private static String slug(String string) {
        String string2;
        block23: {
            Object object;
            block24: {
                int n;
                block21: {
                    block22: {
                        String string3;
                        block20: {
                            String string4;
                            block18: {
                                block19: {
                                    n = Profile.a;
                                    string4 = string;
                                    if (n != 0) break block18;
                                    if (string4 != null) break block19;
                                    string3 = "";
                                    break block20;
                                }
                                String[] stringArray = a;
                                string4 = string.trim().toLowerCase(Locale.ROOT).replaceAll(stringArray[24], "");
                            }
                            string3 = string4.replace(' ', '-');
                        }
                        object = string3;
                        string2 = object;
                        if (n != 0) break block21;
                        if (!string2.isEmpty()) break block22;
                        string2 = string;
                        if (n != 0) break block21;
                        if (string2 == null) break block22;
                        string2 = string;
                        if (n != 0) break block21;
                        if (string2.isBlank()) break block22;
                        object = a[34] + Integer.toHexString(string.trim().hashCode());
                    }
                    string2 = object;
                }
                if (n != 0) break block23;
                if (string2.length() <= 40) break block24;
                string2 = ((String)object).substring(0, 40);
                break block23;
            }
            string2 = object;
        }
        return string2;
    }

    
    private static void lambda$importLegacy$3(Path var0, Path var1_1) {
        block19: {
            block18: {
                block17: {
                    block21: {
                        block20: {
                            var9_2 = ProfileManager.a;
                            var3_3 = var1_1.resolve(var9_2[8]).resolve(var9_2[30]);
                            var4_4 = var1_1.getFileName().toString();
                            var2_5 = Profile.a;
                            var5_6 = var0.resolve(ProfileManager.slug(var4_4) + var9_2[22]);
                            v0 = var3_3;
                            v1 = new LinkOption[]{};
                            if (var2_5 != 0) break block17;
                            if (!Files.exists((Path)v0, v1)) // goto
                            break block20;
                            catch (Exception v2) {
                                throw v2;
                            }
                        }
                        v0 = var5_6;
                        if (var2_5 != 0) // goto
                        break block21;
                        catch (Exception v3) {
                            throw v3;
                        }
                    }
                    v1 = new LinkOption[]{};
                }
                if (!Files.exists((Path)v0, v1)) break block18;
lbl30:

                return;
            }
            try {
                v0 = ProfileManager.GSON.fromJson(Files.readString(var3_3), JsonObject.class);
lbl36:

                if ((var6_7 = (JsonObject)v0) == null) {
                    return;
                }
                for (String var8_10 : ProfileManager.PERSONAL) {
                    if (var2_5 == 0) {
                        var6_7.remove(var8_10);
                        if (var2_5 == 0) continue;
                        break;
                    }
                    break block19;
                }
                ProfileManager.write(var4_4, var6_7, "", Files.getLastModifiedTime(var1_1, new LinkOption[0]).toMillis(), null, "");
                var9_2 = ProfileManager.a;
                Aurora.LOG.info(var9_2[33], (Object)var4_4);
            }
            catch (Exception var6_8) {
                Aurora.LOG.warn(ProfileManager.a[1], (Object)var4_4, (Object)var6_8);
            }
        }
    }

    private static boolean lambda$importLegacy$2(Path path) {
        return Files.isDirectory(path, new LinkOption[0]);
    }

    private static void lambda$list$1(List list, Path path) {
        Profile profile = ProfileManager.read(path);
        if (profile != null) {
            list.add(profile);
        }
    }

    private static boolean lambda$list$0(Path path) {
        return path.getFileName().toString().endsWith(a[43]);
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[49];
                var3_1 = 0;
                var2_2 = "8N\u0018Ga/q\u001aH\"\u0018S\bJqj{4H]Dg#{<\u001c\tNpje)Y\u000eCajn&\u001c\u0012Pp8\u0014\u001cU\u000bC5#a{]]Ht'p{Z\u0014Tf>\u0004=N\u0012K\u00048S\u0019C\u0007(I\u0010Kt8l\u0019\u0018S\bJqj{4H]Ge:y\"\u001c\u001c\u0006e8z=U\u0011C+\u0018S\bJqj{4H]Tp+q{H\u0015C5)z5Z\u0014A5>z{Q\u001cMpjt{L\u000fIs#y>\u00068S\u0013@|-\t.L\u0019Ga/q\u001aH\t8N\u0018Ga/q\u001aH\u000f:I\u000fIg+8+N\u0012@|&p(\u00076S\u0019Sy/f\u001e\u0018S\bJqj{4H]Jz%~{Z\u0012T5%y?\u001c\rTp9p/O\u0004{S\u001b\u0006\t8S\u000eKp>|8O\u0007>R\u001cDy/q\u0004uR\u0018Q\t\u001aI\tI5\u001ep#H\u001e\u0018S\bJqj{4H]Ua%g>\u001c\tNpjv4X\u0018\u0006z$5 A\u0013\u0018S\bJqj{4H]Bp&p/Y]]h\u000b:I\u000fIg+;1O\u0012H\u0005uV\u000eI{\u0006(S\u001eOt&\f\u0000b\u001c\u000boz8b\u001c\"\u000bH\u00076S\u0019Sy/f\t3I\u0019uv+y>O\u000b{T\bB5:y:_\u0018B\u0004=N\u0012K\u0007>R\u001cDy/q\u000b:I\u000fIg+;1O\u0012H\u00048S\u0019C\t.L\u0019Ga/q\u001aH1\u001aI\u000fIg+/{^\u000fI`-}/\u001c\tNpje)Y\u000eCaj2 AZ\u0006z<p)\u001c\u001cU5+5+N\u0012@|&p\b+N\u0012@|&pv\t\u001aI\tI5\u001ep#H\f{T\bBfje7]\u001eCq\u0007(I\u0010Kt8l\u0004?]\tG\u0002w\u001c\u001c\u0018S\bJqj{4H]Ge:y\"\u001c\tNt>5+N\u0012@|&p\u0017\u0018S\bJqj{4H]J|9a{L\u000fIs#y>O\u00045]\u0010C\u0005uV\u000eI{\t3I\u0019uv+y>O\u000b{Q\u0012B`&p(\u001c\u0012H\u0004?]\tG";
                var4_3 = "8N\u0018Ga/q\u001aH\"\u0018S\bJqj{4H]Dg#{<\u001c\tNpje)Y\u000eCajn&\u001c\u0012Pp8\u0014\u001cU\u000bC5#a{]]Ht'p{Z\u0014Tf>\u0004=N\u0012K\u00048S\u0019C\u0007(I\u0010Kt8l\u0019\u0018S\bJqj{4H]Ge:y\"\u001c\u001c\u0006e8z=U\u0011C+\u0018S\bJqj{4H]Tp+q{H\u0015C5)z5Z\u0014A5>z{Q\u001cMpjt{L\u000fIs#y>\u00068S\u0013@|-\t.L\u0019Ga/q\u001aH\t8N\u0018Ga/q\u001aH\u000f:I\u000fIg+8+N\u0012@|&p(\u00076S\u0019Sy/f\u001e\u0018S\bJqj{4H]Jz%~{Z\u0012T5%y?\u001c\rTp9p/O\u0004{S\u001b\u0006\t8S\u000eKp>|8O\u0007>R\u001cDy/q\u0004uR\u0018Q\t\u001aI\tI5\u001ep#H\u001e\u0018S\bJqj{4H]Ua%g>\u001c\tNpjv4X\u0018\u0006z$5 A\u0013\u0018S\bJqj{4H]Bp&p/Y]]h\u000b:I\u000fIg+;1O\u0012H\u0005uV\u000eI{\u0006(S\u001eOt&\f\u0000b\u001c\u000boz8b\u001c\"\u000bH\u00076S\u0019Sy/f\t3I\u0019uv+y>O\u000b{T\bB5:y:_\u0018B\u0004=N\u0012K\u0007>R\u001cDy/q\u000b:I\u000fIg+;1O\u0012H\u00048S\u0019C\t.L\u0019Ga/q\u001aH1\u001aI\u000fIg+/{^\u000fI`-}/\u001c\tNpje)Y\u000eCaj2 AZ\u0006z<p)\u001c\u001cU5+5+N\u0012@|&p\b+N\u0012@|&pv\t\u001aI\tI5\u001ep#H\f{T\bBfje7]\u001eCq\u0007(I\u0010Kt8l\u0004?]\tG\u0002w\u001c\u001c\u0018S\bJqj{4H]Ge:y\"\u001c\tNt>5+N\u0012@|&p\u0017\u0018S\bJqj{4H]J|9a{L\u000fIs#y>O\u00045]\u0010C\u0005uV\u000eI{\t3I\u0019uv+y>O\u000b{Q\u0012B`&p(\u001c\u0012H\u0004?]\tG".length();
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
                    var2_2 = "5]\u0010C\u001d\u0018S\bJqj{4H]Tp+q{H\u0015C5:g4Z\u0014Jpjn&";
                    var4_3 = "5]\u0010C\u001d\u0018S\bJqj{4H]Tp+q{H\u0015C5:g4Z\u0014Jpjn&".length();
                    var1_4 = 4;
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
            var6_7 = 0;
            do {
                v6 = v6;
                v8 = var6_7;
                v9 = v6[v8];
                switch (var6_7 % 7) {
                    case 0: {
                        v10 = 91;
                        break;
                    }
                    case 1: {
                        v10 = 60;
                        break;
                    }
                    case 2: {
                        v10 = 125;
                        break;
                    }
                    case 3: {
                        v10 = 38;
                        break;
                    }
                    case 4: {
                        v10 = 21;
                        break;
                    }
                    case 5: {
                        v10 = 74;
                        break;
                    }
                    default: {
                        v10 = 21;
                    }
                }
                v6[v8] = (char)(v9 ^ v10);
                ++var6_7;
lbl65:

                v7 = v7;
            } while (v7 > var6_7);
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
        ProfileManager.a = var5;
        ProfileManager.AUTO_TEXT = ProfileManager.a[18];
        ProfileManager.GSON = new GsonBuilder().setPrettyPrinting().create();
        var0_6 = ProfileManager.a;
        ProfileManager.PERSONAL = Set.of(var0_6[15], var0_6[23]);
    }
}
