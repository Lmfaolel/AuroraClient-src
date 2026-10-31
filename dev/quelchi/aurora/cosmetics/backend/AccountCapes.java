package dev.quelchi.aurora.cosmetics.backend;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.cosmetics.backend.AccountCapes$Cape;
import dev.quelchi.aurora.cosmetics.backend.CosmeticsSync;
import dev.quelchi.aurora.util.Daemon;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.session.Session;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.util.Identifier;

public final class AccountCapes {
    public static final String PREFIX;
    private static final String PROFILE;
    private static final HttpClient HTTP;
    private static volatile List<AccountCapes$Cape> owned;
    private static volatile String active;
    private static volatile boolean busy;
    private static final String[] a;
    private static final long[] b;
    private static final Long[] c;

    private AccountCapes() {
    }

    public static List<AccountCapes$Cape> owned() {
        return owned;
    }

    public static boolean isAccount(String string) {
        boolean bl;
        block6: {
            block5: {
                String string2;
                int n;
                block4: {
                    n = CosmeticsSync.a;
                    string2 = string;
                    if (n != 0) break block4;
                    if (string2 == null) break block5;
                    string2 = string;
                }
                bl = string2.startsWith(a[22]);
                if (n != 0) break block6;
                if (!bl) break block5;
                bl = true;
                break block6;
            }
            bl = false;
        }
        return bl;
    }

    public static String activeName() {
        Object object;
        block6: {
            String string;
            block4: {
                block5: {
                    int n = CosmeticsSync.a;
                    string = active;
                    if (n != 0) break block4;
                    if (!string.isEmpty()) break block5;
                    object = "";
                    break block6;
                }
                string = active;
            }
            object = a[22] + string;
        }
        return object;
    }

    public static AccountCapes$Cape find(String string) {
        int n = CosmeticsSync.a;
        if (!AccountCapes.isAccount(string)) {
            return null;
        }
        for (AccountCapes$Cape accountCapes$Cape : owned) {
            block9: {
                AccountCapes$Cape accountCapes$Cape2;
                block8: {
                    accountCapes$Cape2 = accountCapes$Cape;
                    if (n != 0) break block8;
                    if (!(a[5] + accountCapes$Cape2.id()).equals(string)) break block9;
                    accountCapes$Cape2 = accountCapes$Cape;
                }
                return accountCapes$Cape2;
            }
            if (n == 0) continue;
        }
        return null;
    }

    public static Identifier texture(String string) {
        Identifier identifier;
        block6: {
            AccountCapes$Cape accountCapes$Cape;
            block4: {
                AccountCapes$Cape accountCapes$Cape2;
                block5: {
                    accountCapes$Cape2 = AccountCapes.find(string);
                    int n = CosmeticsSync.a;
                    accountCapes$Cape = accountCapes$Cape2;
                    if (n != 0) break block4;
                    if (accountCapes$Cape != null) break block5;
                    identifier = null;
                    break block6;
                }
                accountCapes$Cape = accountCapes$Cape2;
            }
            identifier = accountCapes$Cape.tex();
        }
        return identifier;
    }

    public static String alias(String string) {
        String string2;
        block6: {
            AccountCapes$Cape accountCapes$Cape;
            block4: {
                AccountCapes$Cape accountCapes$Cape2;
                block5: {
                    accountCapes$Cape2 = AccountCapes.find(string);
                    int n = CosmeticsSync.a;
                    accountCapes$Cape = accountCapes$Cape2;
                    if (n != 0) break block4;
                    if (accountCapes$Cape != null) break block5;
                    string2 = a[29];
                    break block6;
                }
                accountCapes$Cape = accountCapes$Cape2;
            }
            string2 = accountCapes$Cape.alias();
        }
        return string2;
    }

    public static Identifier shownTexture() {
        Identifier identifier;
        block6: {
            Object object;
            block4: {
                block5: {
                    int n = CosmeticsSync.a;
                    object = active;
                    if (n != 0) break block4;
                    if (!((String)object).isEmpty()) break block5;
                    identifier = null;
                    break block6;
                }
                object = a[22] + active;
            }
            identifier = AccountCapes.texture((String)object);
        }
        return identifier;
    }

    public static void reset() {
        owned = List.of();
        active = "";
        AccountCapes.refresh();
    }

    public static void refresh() {
        boolean bl;
        String string;
        block5: {
            block6: {
                block4: {
                    string = AccountCapes.token();
                    int n = CosmeticsSync.a;
                    if (string == null) break block4;
                    bl = busy;
                    if (n != 0) break block5;
                    if (!bl) break block6;
                }
                return;
            }
            bl = true;
        }
        busy = bl;
        Daemon.run(a[21], () -> AccountCapes.lambda$refresh$1(string));
    }

    
    private static void upload(List<String[]> list, List<byte[]> list2, String string) {
        block4: {
            TextureManager textureManager = MinecraftClient.method_1551().method_1531();
            ArrayList<AccountCapes$Cape> arrayList = new ArrayList<AccountCapes$Cape>();
            int n = CosmeticsSync.a;
            for (int i = 0; i < list.size(); ++i) {
                if (n != 0) break block4;
                String[] stringArray = a;
                Identifier identifier = Identifier.method_60655((String)stringArray[27], (String)(stringArray[3] + list.get(i)[0]));
                try {
                    textureManager.method_4616(identifier, (AbstractTexture)new NativeImageBackedTexture(() -> ((Identifier)identifier).toString(), NativeImage.method_49277((byte[])list2.get(i))));
                }
                catch (Exception exception) {
                    if (n == 0) continue;
                }
                arrayList.add(new AccountCapes$Cape(list.get(i)[0], list.get(i)[1], identifier));
                if (n == 0) continue;
            }
            owned = List.copyOf(arrayList);
            active = string;
        }
    }

    public static void equip(String string, Consumer<String> consumer) {
        String string2;
        String string3;
        AccountCapes$Cape accountCapes$Cape;
        block10: {
            block11: {
                block8: {
                    block9: {
                        accountCapes$Cape = AccountCapes.find(string);
                        int n = CosmeticsSync.a;
                        string3 = AccountCapes.token();
                        if (n != 0) break block8;
                        if (accountCapes$Cape == null) break block9;
                        string2 = string3;
                        if (n != 0) break block10;
                        if (string2 != null) break block11;
                    }
                    consumer.accept(a[19]);
                }
                return;
            }
            string2 = a[16];
        }
        Daemon.run(string2, () -> AccountCapes.lambda$equip$3(string3, accountCapes$Cape, consumer));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static String token() {
        Session session;
        Session session2;
        block12: {
            int n;
            block11: {
                block10: {
                    session2 = MinecraftClient.method_1551().method_1548();
                    n = CosmeticsSync.a;
                    session = session2;
                    if (n != 0) break block10;
                    if (session == null) return null;
                    session = session2;
                }
                if (n != 0) break block11;
                if (session.method_1674() == null) return null;
                session = session2;
            }
            if (n != 0) return session.method_1674();
            if (session.method_1674().length() >= 20) break block12;
            return null;
        }
        session = session2;
        return session.method_1674();
    }

    
    private static byte[] download(String string) {
        int n = CosmeticsSync.a;
        try {
            String[] stringArray = a;
            HttpResponse<byte[]> httpResponse = HTTP.send(HttpRequest.newBuilder(URI.create(string.replace(stringArray[12], stringArray[14]))).timeout(Duration.ofSeconds(AccountCapes.a(8735, 8767741079647657896L))).GET().build(), HttpResponse.BodyHandlers.ofByteArray());
            Object object = httpResponse;
            if (n == 0) {
                if (object.statusCode() != 200) {
                    return null;
                }
                object = httpResponse.body();
            }
            byte[] byArray = (byte[])object;
            return byArray;
        }
        catch (Exception exception) {
            return null;
        }
    }

    
    private static void lambda$equip$3(String string, AccountCapes$Cape accountCapes$Cape, Consumer consumer) {
        Object object;
        Object object2;
        block9: {
            object2 = null;
            int n = CosmeticsSync.a;
            try {
                String[] stringArray = a;
                Object object3 = object = HTTP.send(HttpRequest.newBuilder(URI.create(stringArray[33])).header(stringArray[20], stringArray[4] + string).header(stringArray[28], stringArray[15]).timeout(Duration.ofSeconds(AccountCapes.a(1534, 7115714940499343432L))).PUT(HttpRequest.BodyPublishers.ofString(stringArray[2] + accountCapes$Cape.id() + stringArray[30])).build(), HttpResponse.BodyHandlers.ofString());
                if (n == 0) {
                    if (object3.statusCode() == 429) {
                        stringArray = a;
                        object2 = stringArray[24];
                        break block9;
                    }
                    object3 = object;
                }
                int n2 = object3.statusCode();
                if (n == 0) {
                    if (n2 / 100 == 2) break block9;
                    n2 = object.statusCode();
                }
                stringArray = a;
                object2 = stringArray[8] + n2 + ")";
            }
            catch (Exception exception) {
                object2 = a[10];
            }
        }
        object = object2;
        MinecraftClient.method_1551().execute(() -> AccountCapes.lambda$equip$2((String)object, string, accountCapes$Cape, consumer));
    }

    private static void lambda$equip$2(String string, String string2, AccountCapes$Cape accountCapes$Cape, Consumer consumer) {
        block7: {
            String string3;
            block8: {
                int n;
                block6: {
                    n = CosmeticsSync.a;
                    string3 = string;
                    if (n != 0) break block6;
                    if (string3 != null) break block7;
                    string3 = string2;
                }
                if (n != 0) break block8;
                if (!string3.equals(AccountCapes.token())) break block7;
                string3 = accountCapes$Cape.id();
            }
            active = string3;
        }
        consumer.accept(string);
    }

    
    private static void lambda$refresh$1(String string) {
        int n = CosmeticsSync.a;
        try {
            String[] stringArray = a;
            HttpResponse<String> httpResponse = HTTP.send(HttpRequest.newBuilder(URI.create(stringArray[25])).header(stringArray[17], stringArray[26] + string).timeout(Duration.ofSeconds(AccountCapes.a(8735, 8767741079647657896L))).GET().build(), HttpResponse.BodyHandlers.ofString());
            Object object = httpResponse;
            if (n == 0) {
                if (object.statusCode() != 200) {
                    return;
                }
                object = httpResponse.body();
            }
            JsonObject jsonObject = JsonParser.parseString((String)((String)object)).getAsJsonObject();
            if (n != 0) return;
            if (!jsonObject.has(a[7])) {
                return;
            }
            ArrayList<String[]> arrayList = new ArrayList<String[]>();
            ArrayList<byte[]> arrayList2 = new ArrayList<byte[]>();
            String string2 = "";
            stringArray = a;
            for (JsonElement jsonElement : jsonObject.getAsJsonArray(stringArray[32])) {
                if (n != 0) return;
                JsonObject jsonObject2 = jsonElement.getAsJsonObject();
                stringArray = a;
                String string4 = jsonObject2.get(stringArray[6]).getAsString().toLowerCase(Locale.ROOT);
                byte[] byArray = AccountCapes.download(jsonObject2.get(stringArray[9]).getAsString());
                if (byArray == null) continue;
                String[] stringArray2 = new String[2];
                String[] stringArray3 = stringArray2;
                String[] stringArray4 = stringArray2;
                int n2 = 0;
                String string3 = string4;
                if (n == 0) {
                    stringArray3[n2] = string3;
                    stringArray3 = stringArray4;
                    stringArray4 = stringArray4;
                    n2 = 1;
                    if (jsonObject2.has(a[0])) {
                        string3 = jsonObject2.get(a[31]).getAsString();
                    } else {
                        stringArray = a;
                        string3 = stringArray[18];
                    }
                }
                stringArray3[n2] = string3;
                arrayList.add(stringArray4);
                arrayList2.add(byArray);
                String string5 = a[13];
                if (n == 0 && string5.equals(jsonObject2.get(a[11]).getAsString())) {
                    string5 = string2 = string4;
                }
                if (n == 0) continue;
            }
            String string6 = string2;
            MinecraftClient.method_1551().execute(() -> AccountCapes.lambda$refresh$0(string, arrayList, arrayList2, string6));
            return;
        }
        catch (Exception exception) {
            Aurora.LOG.debug(a[1], (Throwable)exception);
            return;
        }
        finally {
            busy = false;
        }
    }

    private static void lambda$refresh$0(String string, List list, List list2, String string2) {
        if (string.equals(AccountCapes.token())) {
            AccountCapes.upload(list, list2, string2);
        }
    }

    
    static {
        block23: {
            block22: {
                block21: {
                    block20: {
                        var13 = new String[34];
                        var11_1 = 0;
                        var10_2 = "Z\u0012I48\u001ci\u001bA1\"\u00104\u001b\u001fC6$\u000b=O^C4;\u001b \u001b\u0018A<'\u001b7\u000b@\\C4;\u001b\u001a_\\\u001aw\rZ\u001dC:>\u0010'd\u001dA%.Q\u0007y\u001bA'.\fs\u0003V\u001d\u007f\u0002R\u001a\u0005X\u001fP08\u0019v\u0011J4%\u0019s_\u0017D;l\nsO\u001fK0k\n;Z\n\u0000}\u0003N\fL\u0015x\u0011U9/\u0010tO^R0*\u001d;\u001b3O?*\u00104\u0005H\nA!.\u0007S\nT%qQ|\u0006z=t\u001c\u001d;\bS\nT%8D|\u0014\u0010Z\u000eP9\"\u001d2O\u0017O;d\u0014 T\u0010\u0014Z\u000bR:9\u001f~Z\u001dC:>\u0010'\u0016\u001dA%.\r\rz\u000bT=$\f:A\u001fT<$\u0010\u000ev\u0017N0(\f2]\n\u00006*\u000e63h\u0017G;k\u0017=\u001b\tI!#^2\u001b3I69\u0011 T\u0018Tu*\u001d0T\u000bN!k\n<\u001b\tE49^*T\u000bRu(\u001f#^\r\rz\u000bT=$\f:A\u001fT<$\u0010\u0014Z\u000bR:9\u001f~Z\u001dC:>\u0010'\u0016\u001dA%.\r\u0003V\u001d\u007f3S\nT%8D|\u0014\u001fP<e\u0013:U\u001bC'*\u0018'H\u001bR#\"\u001d6HPC:&Q>R\u0010E69\u001f5OQP'$\u0018:W\u001b,v\u0011J4%\u0019sH\u001fY&k\r?T\t\u00001$\t=\u0017^T'2^2\\\u001fI;k\u0017=\u001b\u001f\u00008\"\u0010&O\u001b3S\nT%8D|\u0014\u001fP<e\u0013:U\u001bC'*\u0018'H\u001bR#\"\u001d6HPC:&Q>R\u0010E69\u001f5OQP'$\u0018:W\u001b\u0007y\u001bA'.\fs\u0006Z\u000bR:9\u001f\fx\u0011N!.\u0010'\u0016*Y%.\u000ev\u0017N0(\f2]\n\u00006*\u000e6\u0002\u0019\u0003\u0005Z\u0012I48";
                        var12_3 = "Z\u0012I48\u001ci\u001bA1\"\u00104\u001b\u001fC6$\u000b=O^C4;\u001b \u001b\u0018A<'\u001b7\u000b@\\C4;\u001b\u001a_\\\u001aw\rZ\u001dC:>\u0010'd\u001dA%.Q\u0007y\u001bA'.\fs\u0003V\u001d\u007f\u0002R\u001a\u0005X\u001fP08\u0019v\u0011J4%\u0019s_\u0017D;l\nsO\u001fK0k\n;Z\n\u0000}\u0003N\fL\u0015x\u0011U9/\u0010tO^R0*\u001d;\u001b3O?*\u00104\u0005H\nA!.\u0007S\nT%qQ|\u0006z=t\u001c\u001d;\bS\nT%8D|\u0014\u0010Z\u000eP9\"\u001d2O\u0017O;d\u0014 T\u0010\u0014Z\u000bR:9\u001f~Z\u001dC:>\u0010'\u0016\u001dA%.\r\rz\u000bT=$\f:A\u001fT<$\u0010\u000ev\u0017N0(\f2]\n\u00006*\u000e63h\u0017G;k\u0017=\u001b\tI!#^2\u001b3I69\u0011 T\u0018Tu*\u001d0T\u000bN!k\n<\u001b\tE49^*T\u000bRu(\u001f#^\r\rz\u000bT=$\f:A\u001fT<$\u0010\u0014Z\u000bR:9\u001f~Z\u001dC:>\u0010'\u0016\u001dA%.\r\u0003V\u001d\u007f3S\nT%8D|\u0014\u001fP<e\u0013:U\u001bC'*\u0018'H\u001bR#\"\u001d6HPC:&Q>R\u0010E69\u001f5OQP'$\u0018:W\u001b,v\u0011J4%\u0019sH\u001fY&k\r?T\t\u00001$\t=\u0017^T'2^2\\\u001fI;k\u0017=\u001b\u001f\u00008\"\u0010&O\u001b3S\nT%8D|\u0014\u001fP<e\u0013:U\u001bC'*\u0018'H\u001bR#\"\u001d6HPC:&Q>R\u0010E69\u001f5OQP'$\u0018:W\u001b\u0007y\u001bA'.\fs\u0006Z\u000bR:9\u001f\fx\u0011N!.\u0010'\u0016*Y%.\u000ev\u0017N0(\f2]\n\u00006*\u000e6\u0002\u0019\u0003\u0005Z\u0012I48".length();
                        var9_4 = 5;
                        var8_5 = -1;
lbl7:

                        while (true) {
                            v0 = ++var8_5;
                            v1 = var10_2.substring(v0, v0 + var9_4);
                            v2 = -1;
                            break block20;
                            break;
                        }
lbl12:

                        while (true) {
                            var13[var11_1++] = v3.intern();
                            if ((var8_5 += var9_4) < var12_3) {
                                var9_4 = var10_2.charAt(var8_5);
                                continue;
                            }
                            var10_2 = "X\u001fP08@S\nT%8D|\u0014\u001fP<e\u0013:U\u001bC'*\u0018'H\u001bR#\"\u001d6HPC:&Q>R\u0010E69\u001f5OQP'$\u0018:W\u001b\u000f6*\u000e6HQA6?\u0017%^";
                            var12_3 = "X\u001fP08@S\nT%8D|\u0014\u001fP<e\u0013:U\u001bC'*\u0018'H\u001bR#\"\u001d6HPC:&Q>R\u0010E69\u001f5OQP'$\u0018:W\u001b\u000f6*\u000e6HQA6?\u0017%^".length();
                            var9_4 = 5;
                            var8_5 = -1;
lbl21:

                            while (true) {
                                v4 = ++var8_5;
                                v1 = var10_2.substring(v4, v4 + var9_4);
                                v2 = 0;
                                break block20;
                                break;
                            }
                            break;
                        }
lbl26:

                        while (true) {
                            var13[var11_1++] = v3.intern();
                            if ((var8_5 += var9_4) < var12_3) {
                                var9_4 = var10_2.charAt(var8_5);
                                continue;
                            }
                            break block21;
                            break;
                        }
                    }
                    v5 = v1.toCharArray();
                    v6 = v5;
                    v7 = v5.length;
                    var14_6 = 0;
                    do {
                        v6 = v6;
                        v8 = var14_6;
                        v9 = v6[v8];
                        switch (var14_6 % 7) {
                            case 0: {
                                v10 = 59;
                                break;
                            }
                            case 1: {
                                v10 = 126;
                                break;
                            }
                            case 2: {
                                v10 = 32;
                                break;
                            }
                            case 3: {
                                v10 = 85;
                                break;
                            }
                            case 4: {
                                v10 = 75;
                                break;
                            }
                            case 5: {
                                v10 = 126;
                                break;
                            }
                            default: {
                                v10 = 83;
                            }
                        }
                        v6[v8] = (char)(v9 ^ v10);
                        ++var14_6;
lbl65:

                        v7 = v7;
                    } while (v7 > var14_6);
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
                AccountCapes.a = var13;
                AccountCapes.PREFIX = AccountCapes.a[22];
                AccountCapes.PROFILE = AccountCapes.a[23];
                var0_7 = 6172483503131418890L;
                var6_8 = new long[3];
                var3_9 = 0;
                var4_10 = ",\u0004W\u0004\f\u00e4\u00fe\u00ad7i\b\u0003]|YM,V \u00fa=\u00f8\u00e1\u00c3";
                var5_11 = ",\u0004W\u0004\f\u00e4\u00fe\u00ad7i\b\u0003]|YM,V \u00fa=\u00f8\u00e1\u00c3".length();
                var2_12 = 0;
                while (true) {
                    break block22;
                    break;
                }
lbl87:

                while (true) {
                    var6_8[v11] = (((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L) ^ var0_7;
                    if (var2_12 < var5_11) continue;
                    break block23;
                    break;
                }
            }
            var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
            v11 = var3_9++;
            while (true)
        }
        AccountCapes.b = var6_8;
        AccountCapes.c = new Long[3];
        AccountCapes.HTTP = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(AccountCapes.a(15734, 8790799907865985219L))).build();
        AccountCapes.owned = List.of();
        AccountCapes.active = "";
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x3DB7) & Short.MAX_VALUE;
        if (c[n2] == null) {
            AccountCapes.c[n2] = b[n2] ^ l;
        }
        return c[n2];
    }
}
