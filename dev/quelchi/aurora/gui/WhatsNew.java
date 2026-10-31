package dev.quelchi.aurora.gui;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.config.ConfigManager;
import dev.quelchi.aurora.gui.PreviewScene;
import dev.quelchi.aurora.gui.WhatsNew$Feature;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.Identifier;

public final class WhatsNew {
    private static final String FILE;
    private static boolean checked;
    private static final String[] a;

    private WhatsNew() {
    }

    private static Path seenFile() {
        return ConfigManager.path().resolveSibling(a[8]);
    }

    
    public static List<WhatsNew$Feature> fresh() {
        ArrayList<WhatsNew$Feature> arrayList;
        block47: {
            String string;
            String[] stringArray;
            int n;
            block45: {
                Object object;
                block43: {
                    block40: {
                        ArrayList<WhatsNew$Feature> arrayList2;
                        block42: {
                            InputStream inputStream;
                            block41: {
                                boolean bl;
                                block38: {
                                    block39: {
                                        block52: {
                                            n = PreviewScene.a;
                                            bl = checked;
                                            if (n != 0) break block38;
                                            if (!bl) break block39;
                                            break block52;
                                            catch (Throwable throwable) {
                                                throw throwable;
                                            }
                                        }
                                        return List.of();
                                    }
                                    bl = true;
                                }
                                checked = bl;
                                arrayList = new ArrayList<WhatsNew$Feature>();
                                stringArray = a;
                                object = WhatsNew.class.getResourceAsStream(stringArray[6]);
                                if (object != null) break block40;
                                arrayList2 = arrayList;
                                inputStream = object;
                                if (n != 0) break block41;
                                if (inputStream == null) break block42;
                                inputStream = object;
                            }
                            inputStream.close();
                        }
                        return arrayList2;
                    }
                    try {
                        string = new String(((InputStream)object).readAllBytes(), StandardCharsets.UTF_8);
                        if (n != 0) break block43;
                        {
                            catch (Throwable throwable) {
                                block44: {
                                    if (n != 0 || object == null) break block44;
                                    try {
                                        ((InputStream)object).close();
                                    }
                                    catch (Throwable throwable2) {
                                        throwable.addSuppressed(throwable2);
                                    }
                                }
                                throw throwable;
                            }
                        }
                        if (object != null) {
                            ((InputStream)object).close();
                        }
                    }
                    catch (Exception exception) {
                        return arrayList;
                    }
                }
                object = Integer.toHexString(string.hashCode());
                try {
                    Path path;
                    Path path2;
                    block46: {
                        block54: {
                            block53: {
                                path2 = WhatsNew.seenFile();
                                if (n != 0) break block45;
                                if (!Files.exists(path2, new LinkOption[0])) break block46;
                                break block53;
                                catch (Throwable throwable) {
                                    throw throwable;
                                }
                            }
                            path = path2;
                            if (n != 0) break block45;
                            break block54;
                            catch (Throwable throwable) {
                                throw throwable;
                            }
                        }
                        if (!Files.readString(path).trim().equals(object)) break block46;
                        return arrayList;
                    }
                    path = Files.writeString(path2, (CharSequence)object, new OpenOption[0]);
                }
                catch (Exception exception) {
                    stringArray = a;
                    Aurora.LOG.debug(stringArray[4], (Throwable)exception);
                }
            }
            try {
                stringArray = a;
                for (JsonElement jsonElement : JsonParser.parseString((String)string).getAsJsonObject().getAsJsonArray(stringArray[2])) {
                    Identifier identifier;
                    String string2;
                    String string3;
                    String string4;
                    WhatsNew$Feature whatsNew$Feature;
                    WhatsNew$Feature whatsNew$Feature2;
                    ArrayList<WhatsNew$Feature> arrayList3;
                    block51: {
                        String string5;
                        block50: {
                            WhatsNew$Feature whatsNew$Feature3;
                            String string6;
                            JsonObject jsonObject;
                            block57: {
                                block49: {
                                    JsonObject jsonObject2;
                                    block48: {
                                        block56: {
                                            block55: {
                                                jsonObject = jsonElement.getAsJsonObject();
                                                if (n != 0) break block47;
                                                jsonObject2 = jsonObject;
                                                if (n != 0) break block48;
                                                break block55;
                                                catch (Throwable throwable) {
                                                    throw throwable;
                                                }
                                            }
                                            if (!jsonObject2.has(a[3])) break block49;
                                            break block56;
                                            catch (Throwable throwable) {
                                                throw throwable;
                                            }
                                        }
                                        jsonObject2 = jsonObject.get(a[1]);
                                    }
                                    string6 = jsonObject2.getAsString();
                                    break block57;
                                }
                                string6 = null;
                            }
                            string5 = string6;
                            arrayList3 = arrayList;
                            whatsNew$Feature2 = whatsNew$Feature3;
                            whatsNew$Feature = whatsNew$Feature3;
                            string4 = jsonObject.get(a[0]).getAsString();
                            string3 = jsonObject.get(a[12]).getAsString();
                            string2 = jsonObject.get(a[11]).getAsString();
                            if (string5 != null) break block50;
                            identifier = null;
                            break block51;
                        }
                        stringArray = a;
                        identifier = Identifier.method_60655((String)stringArray[5], (String)(stringArray[9] + string5));
                    }
                    whatsNew$Feature2(string4, string3, string2, identifier);
                    arrayList3.add(whatsNew$Feature);
                    if (n == 0) continue;
                    break;
                }
            }
            catch (Exception exception) {
                Aurora.LOG.warn(a[7], (Throwable)exception);
                arrayList.clear();
            }
        }
        return arrayList;
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[13];
                var3_1 = 0;
                var2_2 = "\r\u001bu\u0005\u0010\u0017s\u0004I\b\u001f\u001fs\u0017Y{+\n\u0005\u0010\u0017s\u0004I\u0018.\u0012s\u0017\u000bzn\u0017\u001feCAh<\u0012\u001f`CJh'\u0015\u001fv\u0006\u0018\u000f`\f^h%V\u001ba\u0010I}=V\u001bg\u0011C{/V\rz\u0002Xz \u001c\r=\u0014Dh:\n\u0014w\u0014\u0002c=\u0016\u0014\u0011;\u001bvC[a/\r\t|\u0006['$\n\u0015|\u0013\u0018\u000f`\f^hc\u000e\u0012s\u0017_g+\u000eTf\u001bX\t\u000e\u0012s\u0017_g+\u000eU%V\u001ba\u0010I}=V\u001bg\u0011C{/V\rz\u0002Xz \u001c\r=\u0014Dh:\n\u0014w\u0014\u0002c=\u0016\u0014";
                var4_3 = "\r\u001bu\u0005\u0010\u0017s\u0004I\b\u001f\u001fs\u0017Y{+\n\u0005\u0010\u0017s\u0004I\u0018.\u0012s\u0017\u000bzn\u0017\u001feCAh<\u0012\u001f`CJh'\u0015\u001fv\u0006\u0018\u000f`\f^h%V\u001ba\u0010I}=V\u001bg\u0011C{/V\rz\u0002Xz \u001c\r=\u0014Dh:\n\u0014w\u0014\u0002c=\u0016\u0014\u0011;\u001bvC[a/\r\t|\u0006['$\n\u0015|\u0013\u0018\u000f`\f^hc\u000e\u0012s\u0017_g+\u000eTf\u001bX\t\u000e\u0012s\u0017_g+\u000eU%V\u001ba\u0010I}=V\u001bg\u0011C{/V\rz\u0002Xz \u001c\r=\u0014Dh:\n\u0014w\u0014\u0002c=\u0016\u0014".length();
                var1_4 = 3;
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
                    var2_2 = "\r\u001fj\u0017\u0005\r\u0013f\u000fI";
                    var4_3 = "\r\u001fj\u0017\u0005\r\u0013f\u000fI".length();
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
            var6_6 = 0;
            do {
                v6 = v6;
                v8 = var6_6;
                v9 = v6[v8];
                switch (var6_6 % 7) {
                    case 0: {
                        v10 = 121;
                        break;
                    }
                    case 1: {
                        v10 = 122;
                        break;
                    }
                    case 2: {
                        v10 = 18;
                        break;
                    }
                    case 3: {
                        v10 = 99;
                        break;
                    }
                    case 4: {
                        v10 = 44;
                        break;
                    }
                    case 5: {
                        v10 = 9;
                        break;
                    }
                    default: {
                        v10 = 78;
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
        WhatsNew.a = var5;
        WhatsNew.FILE = WhatsNew.a[10];
    }
}
