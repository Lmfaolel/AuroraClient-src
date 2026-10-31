package dev.quelchi.aurora.rpc;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.rpc.DiscordIpc$Frame;
import dev.quelchi.aurora.rpc.DiscordIpc$Pipe;
import java.io.IOException;
import java.lang.invoke.LambdaMetafactory;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public final class DiscordIpc {
    private static final int OP_HANDSHAKE = 0;
    private static final int OP_FRAME = 1;
    private static final int OP_CLOSE;
    private static final List<DiscordIpc$Pipe> pipes;
    private static volatile boolean connected;
    private static volatile String status;
    private static final long PID;
    private static final long RETRY_MS;
    private static final long READ_TIMEOUT_MS;
    private static volatile long retryAfter;
    private static volatile String rejectedId;
    private static final boolean WINDOWS;
    public static boolean a;
    private static final String[] b;
    private static final long[] c;
    private static final Integer[] d;
    private static final long[] e;
    private static final Long[] f;

    private DiscordIpc() {
    }

    public static boolean isConnected() {
        return connected;
    }

    public static String getStatus() {
        return status;
    }

    public static synchronized void resetBackoff() {
        retryAfter = 0L;
        rejectedId = null;
    }

    
    public static synchronized boolean connect(String var0) {
        block65: {
            block66: {
                block81: {
                    block70: {
                        block69: {
                            block67: {
                                block68: {
                                    block59: {
                                        block57: {
                                            block58: {
                                                block75: {
                                                    block55: {
                                                        block56: {
                                                            block74: {
                                                                block53: {
                                                                    block54: {
                                                                        block52: {
                                                                            block73: {
                                                                                block72: {
                                                                                    block71: {
                                                                                        block51: {
                                                                                            block50: {
                                                                                                var1_1 = DiscordIpc.a;
                                                                                                v0 = DiscordIpc.connected;
                                                                                                if (var1_1) break block50;
                                                                                                if (!v0) break block51;
                                                                                                v0 = true;
                                                                                            }
                                                                                            return v0;
                                                                                        }
                                                                                        v2 = var0;
                                                                                        if (var1_1) break block52;
                                                                                        if (v2 == null) // goto
                                                                                        break block71;
                                                                                        catch (Exception v3) {
                                                                                            throw v3;
                                                                                        }
                                                                                    }
                                                                                    v4 = var0;
                                                                                    if (var1_1) break block53;
                                                                                    break block72;
                                                                                    catch (Exception v5) {
                                                                                        throw v5;
                                                                                    }
                                                                                }
                                                                                if (!v4.isBlank()) break block54;
                                                                                break block73;
                                                                                catch (Exception v6) {
                                                                                    throw v6;
                                                                                }
                                                                            }
                                                                            v2 = DiscordIpc.b[23];
                                                                        }
                                                                        DiscordIpc.status = v2;
                                                                        return false;
                                                                    }
                                                                    v4 = var0.trim();
                                                                }
                                                                var2_2 = v4;
                                                                v8 = var2_2.equals(DiscordIpc.rejectedId);
                                                                if (var1_1) break block55;
                                                                if (v8 == 0) break block56;
                                                                break block74;
                                                                catch (Exception v9) {
                                                                    throw v9;
                                                                }
                                                            }
                                                            return false;
                                                        }
                                                        cfr_temp_0 = System.currentTimeMillis() - DiscordIpc.retryAfter;
                                                        v8 = cfr_temp_0 == 0L ? 0 : (cfr_temp_0 < 0L ? -1 : 1);
                                                    }
                                                    if (var1_1) break block57;
                                                    if (v8 >= 0) break block58;
                                                    break block75;
                                                    catch (Exception v11) {
                                                        throw v11;
                                                    }
                                                }
                                                return false;
                                            }
                                            v8 = 0;
                                        }
                                        var3_3 = v8;
                                        var4_4 = null;
                                        var5_5 = 0;
                                        for (var6_6 = 0; var6_6 < 10; ++var6_6) {
                                            try {
                                                v13 = var6_6;
                                                if (var1_1) break block59;
                                                var7_7 = DiscordIpc$Pipe.open(v13);
                                            }
                                            catch (Exception var8_9) {
                                                continue;
                                            }
                                            var3_3 = 1;
                                            try {
                                                block60: {
                                                    block61: {
                                                        block80: {
                                                            block63: {
                                                                block64: {
                                                                    block79: {
                                                                        block62: {
                                                                            block78: {
                                                                                block77: {
                                                                                    block76: {
                                                                                        var8_8 = new JsonObject();
                                                                                        var8_8.addProperty("v", (Number)1);
                                                                                        var13_12 = DiscordIpc.b;
                                                                                        var8_8.addProperty(var13_12[11], var2_2);
                                                                                        DiscordIpc.write(var7_7, 0, var8_8.toString());
                                                                                        var9_11 = DiscordIpc.read(var7_7);
                                                                                        if (var1_1) break block60;
                                                                                        if (var9_11.opcode != 2) break block61;
                                                                                        break block76;
                                                                                        catch (Exception v14) {
                                                                                            throw v14;
                                                                                        }
                                                                                    }
                                                                                    v15 = var9_11;
                                                                                    if (var1_1) break block62;
                                                                                    break block77;
                                                                                    catch (Exception v16) {
                                                                                        throw v16;
                                                                                    }
                                                                                }
                                                                                if (v15.json == null) break block63;
                                                                                break block78;
                                                                                catch (Exception v17) {
                                                                                    throw v17;
                                                                                }
                                                                            }
                                                                            v15 = var9_11;
                                                                        }
                                                                        v19 = v15.json;
                                                                        if (var1_1) break block64;
                                                                        if (!v19.has(DiscordIpc.b[56])) break block63;
                                                                        break block79;
                                                                        catch (Exception v20) {
                                                                            throw v20;
                                                                        }
                                                                    }
                                                                    v19 = var9_11.json.get(DiscordIpc.b[41]);
                                                                }
                                                                v22 = v19.getAsString();
                                                                break block80;
                                                            }
                                                            var13_12 = DiscordIpc.b;
                                                            v22 = var13_12[28];
                                                        }
                                                        DiscordIpc.status = var10_13 = v22;
                                                        DiscordIpc.rejectedId = var2_2;
                                                        DiscordIpc.closeQuietly(var7_7);
                                                        return false;
                                                    }
                                                    DiscordIpc.pipes.add(var7_7);
                                                    if (var1_1) continue;
                                                    DiscordIpc.connected = true;
                                                }
                                                if (var4_4 == null) {
                                                    var4_4 = DiscordIpc.userSuffix(var9_11);
                                                }
                                                ++var5_5;
                                                continue;
                                            }
                                            catch (Exception var8_10) {
                                                DiscordIpc.closeQuietly(var7_7);
                                            }
                                            if (!var1_1) continue;
                                        }
                                        v13 = var5_5;
                                    }
                                    if (var1_1) break block65;
                                    if (v13 <= 0) break block66;
                                    v25 = var4_4;
                                    if (var1_1) break block67;
                                    if (v25 != null) break block68;
                                    v25 = "";
                                    break block67;
                                }
                                v25 = var4_4;
                            }
                            v28 = var5_5;
                            if (var1_1) break block69;
                            if (v28 <= 1) break block70;
                            v28 = var5_5;
                        }
                        var13_12 = DiscordIpc.b;
                        v31 = var13_12[13] + v28 + var13_12[60];
                        break block81;
                    }
                    v31 = "";
                }
                var11_14 = v31;
                var12_15 = v25;
                var13_12 = DiscordIpc.b;
                DiscordIpc.status = var13_12[30] + var12_15 + var11_14;
                Aurora.LOG.info(var13_12[22], (Object)DiscordIpc.status);
                return true;
            }
            v13 = var3_3;
        }
        v32 = v13 != 0 ? DiscordIpc.b[35] : DiscordIpc.b[6];
        DiscordIpc.status = v32;
        DiscordIpc.retryAfter = System.currentTimeMillis() + DiscordIpc.b(2252, 8385134724702523020L);
        return false;
    }

    
    public static synchronized void update(String var0, String var1_1, String var2_2, String var3_3, String var4_4, long var5_5) {
        block53: {
            block52: {
                block72: {
                    var7_6 = DiscordIpc.a;
                    v0 = DiscordIpc.connected;
                    if (var7_6) break block52;
                    if (v0) break block53;
                    break block72;
                    catch (Exception v1) {
                        throw v1;
                    }
                }
                v0 = DiscordIpc.connect(var0);
            }
            if (!v0) {
                return;
            }
        }
        try {
            block71: {
                block69: {
                    block70: {
                        block87: {
                            block65: {
                                block66: {
                                    block86: {
                                        block68: {
                                            block84: {
                                                block85: {
                                                    block67: {
                                                        block83: {
                                                            block82: {
                                                                block81: {
                                                                    block61: {
                                                                        block64: {
                                                                            block75: {
                                                                                block76: {
                                                                                    block60: {
                                                                                        block77: {
                                                                                            block58: {
                                                                                                block59: {
                                                                                                    block56: {
                                                                                                        block57: {
                                                                                                            block74: {
                                                                                                                block54: {
                                                                                                                    block55: {
                                                                                                                        block73: {
                                                                                                                            var8_7 = new JsonObject();
                                                                                                                            v3 = var1_1;
                                                                                                                            if (var7_6) break block54;
                                                                                                                            if (!DiscordIpc.notBlank(v3)) break block55;
                                                                                                                            break block73;
                                                                                                                            catch (Exception v4) {
                                                                                                                                throw v4;
                                                                                                                            }
                                                                                                                        }
                                                                                                                        var8_7.addProperty(DiscordIpc.b[53], var1_1);
                                                                                                                    }
                                                                                                                    v3 = var2_2;
                                                                                                                }
                                                                                                                v6 = DiscordIpc.notBlank(v3);
                                                                                                                if (var7_6) break block56;
                                                                                                                if (v6 == 0) break block57;
                                                                                                                break block74;
                                                                                                                catch (Exception v7) {
                                                                                                                    throw v7;
                                                                                                                }
                                                                                                            }
                                                                                                            var8_7.addProperty(DiscordIpc.b[5], var2_2);
                                                                                                        }
                                                                                                        v6 = (cfr_temp_0 = var5_5 - 0L) == 0L ? 0 : (cfr_temp_0 < 0L ? -1 : 1);
                                                                                                    }
                                                                                                    if (var7_6) break block58;
                                                                                                    if (v6 <= 0) break block59;
                                                                                                    var9_9 = new JsonObject();
                                                                                                    var15_10 = DiscordIpc.b;
                                                                                                    var9_9.addProperty(var15_10[32], (Number)var5_5);
                                                                                                    var8_7.add(var15_10[34], (JsonElement)var9_9);
                                                                                                }
                                                                                                v6 = (int)DiscordIpc.notBlank(var3_3);
                                                                                            }
                                                                                            if (v6 == 0) break block75;
                                                                                            var9_9 = new JsonObject();
                                                                                            v10 = var9_9;
                                                                                            if (var7_6) break block76;
                                                                                            v10.addProperty(DiscordIpc.b[54], var3_3);
                                                                                            if (!DiscordIpc.notBlank(var4_4)) break block60;
                                                                                            break block77;
                                                                                            catch (Exception v11) {
                                                                                                throw v11;
                                                                                            }
                                                                                        }
                                                                                        var9_9.addProperty(DiscordIpc.b[44], var4_4);
                                                                                    }
                                                                                    v10 = var8_7;
                                                                                }
                                                                                var15_10 = DiscordIpc.b;
                                                                                v10.add(var15_10[50], (JsonElement)var9_9);
                                                                            }
                                                                            var15_10 = DiscordIpc.b;
                                                                            var8_7.addProperty(var15_10[17], (Number)0);
                                                                            var8_7.addProperty(var15_10[3], Boolean.valueOf(false));
                                                                            var9_9 = new JsonObject();
                                                                            var9_9.addProperty(var15_10[39], (Number)DiscordIpc.PID);
                                                                            var9_9.add(var15_10[45], (JsonElement)var8_7);
                                                                            var10_11 = new JsonObject();
                                                                            var10_11.addProperty(var15_10[57], var15_10[12]);
                                                                            var10_11.add(var15_10[46], (JsonElement)var9_9);
                                                                            var10_11.addProperty(var15_10[49], UUID.randomUUID().toString());
                                                                            var11_12 = null;
                                                                            for (DiscordIpc$Pipe var13_14 : DiscordIpc.pipes) {
                                                                                block63: {
                                                                                    if (var7_6) break block61;
                                                                                    try {
                                                                                        block62: {
                                                                                            block80: {
                                                                                                block79: {
                                                                                                    block78: {
                                                                                                        DiscordIpc.write(var13_14, 1, var10_11.toString());
                                                                                                        var14_15 = DiscordIpc.read(var13_14);
                                                                                                        v13 = var11_12;
                                                                                                        if (var7_6) break block62;
                                                                                                        if (v13 == null) // goto
                                                                                                        break block78;
                                                                                                        catch (Exception v14) {
                                                                                                            throw v14;
                                                                                                        }
                                                                                                    }
                                                                                                    v13 = var14_15;
                                                                                                    if (var7_6) break block62;
                                                                                                    break block79;
                                                                                                    catch (Exception v15) {
                                                                                                        throw v15;
                                                                                                    }
                                                                                                }
                                                                                                if (v13.opcode != 2) break block63;
                                                                                                break block80;
                                                                                                catch (Exception v16) {
                                                                                                    throw v16;
                                                                                                }
                                                                                            }
                                                                                            v13 = var14_15;
                                                                                        }
                                                                                        var11_12 = v13;
                                                                                    }
                                                                                    catch (Exception var14_16) {
                                                                                        DiscordIpc.pipes.remove(var13_14);
                                                                                        DiscordIpc.closeQuietly(var13_14);
                                                                                    }
                                                                                }
                                                                                if (!var7_6) continue;
                                                                            }
                                                                            v18 = DiscordIpc.pipes.isEmpty();
                                                                            if (var7_6) break block64;
                                                                            if (!v18) break block61;
                                                                            v18 = false;
                                                                        }
                                                                        DiscordIpc.connected = v18;
                                                                        var15_10 = DiscordIpc.b;
                                                                        DiscordIpc.status = var15_10[31];
                                                                        return;
                                                                    }
                                                                    v20 = var11_12;
                                                                    if (!var7_6) {
                                                                        if (v20 == null) {
                                                                            return;
                                                                        }
                                                                        v20 = var11_12;
                                                                    }
                                                                    if (var7_6) break block65;
                                                                    if (v20.opcode != 2) break block66;
                                                                    break block81;
                                                                    catch (Exception v21) {
                                                                        throw v21;
                                                                    }
                                                                }
                                                                v22 = var11_12;
                                                                if (var7_6) break block67;
                                                                break block82;
                                                                catch (Exception v23) {
                                                                    throw v23;
                                                                }
                                                            }
                                                            if (v22.json == null) break block68;
                                                            break block83;
                                                            catch (Exception v24) {
                                                                throw v24;
                                                            }
                                                        }
                                                        v22 = var11_12;
                                                    }
                                                    if (var7_6) break block84;
                                                    if (!v22.json.has(DiscordIpc.b[41])) break block68;
                                                    break block85;
                                                    catch (Exception v26) {
                                                        throw v26;
                                                    }
                                                }
                                                v22 = var11_12;
                                            }
                                            var15_10 = DiscordIpc.b;
                                            v28 = v22.json.get(var15_10[41]).getAsString();
                                            break block86;
                                        }
                                        var15_10 = DiscordIpc.b;
                                        v28 = var15_10[1];
                                    }
                                    var12_13 = v28;
                                    DiscordIpc.close();
                                    DiscordIpc.status = var12_13;
                                    return;
                                }
                                v20 = var11_12;
                            }
                            var12_13 = DiscordIpc.errorMessage(v20);
                            v29 = var12_13;
                            if (var7_6) break block69;
                            if (v29 == null) break block70;
                            break block87;
                            catch (Exception v30) {
                                throw v30;
                            }
                        }
                        v31 = var12_13;
                        break block71;
                    }
                    v29 = DiscordIpc.imageWarning(var11_12, var3_3);
                }
                var15_10 = DiscordIpc.b;
                v31 = var15_10[59] + (String)v29;
            }
            DiscordIpc.status = v31;
        }
        catch (Exception var8_8) {
            DiscordIpc.close();
            DiscordIpc.status = DiscordIpc.b[26];
        }
    }

    public static synchronized void close() {
        block5: {
            connected = false;
            boolean bl = a;
            for (DiscordIpc$Pipe discordIpc$Pipe : pipes) {
                try {
                    String[] stringArray = b;
                    DiscordIpc.write(discordIpc$Pipe, 2, stringArray[37]);
                    if (bl) break block5;
                }
                catch (Exception exception) {
                }
                DiscordIpc.closeQuietly(discordIpc$Pipe);
                if (!bl) continue;
                boolean bl2 = !Module.a;
                Module.a = bl2;
                break;
            }
            pipes.clear();
            status = b[15];
        }
    }

    private static void write(DiscordIpc$Pipe discordIpc$Pipe, int n, String string) throws Exception {
        byte[] byArray = string.getBytes(StandardCharsets.UTF_8);
        ByteBuffer byteBuffer = ByteBuffer.allocate(8 + byArray.length).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.putInt(n);
        byteBuffer.putInt(byArray.length);
        byteBuffer.put(byArray);
        discordIpc$Pipe.write(byteBuffer.array());
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static DiscordIpc$Frame read(DiscordIpc$Pipe discordIpc$Pipe) throws Exception {
        int n;
        int n2;
        int n3;
        block7: {
            byte[] byArray = DiscordIpc.readBytes(discordIpc$Pipe, 8);
            ByteBuffer byteBuffer = ByteBuffer.wrap(byArray).order(ByteOrder.LITTLE_ENDIAN);
            n3 = byteBuffer.getInt();
            boolean bl = a;
            n2 = byteBuffer.getInt();
            n = n2;
            if (bl) break block7;
            if (n < 0) throw new IllegalStateException(b[21] + n2);
            n = n2;
        }
        if (n > DiscordIpc.a(31764, 1742890809474884594L)) {
            throw new IllegalStateException(b[21] + n2);
        }
        byte[] byArray = DiscordIpc.readBytes(discordIpc$Pipe, n2);
        JsonObject jsonObject = null;
        try {
            jsonObject = JsonParser.parseString((String)new String(byArray, StandardCharsets.UTF_8)).getAsJsonObject();
            return new DiscordIpc$Frame(n3, jsonObject);
        }
        catch (Exception exception) {
        }
        return new DiscordIpc$Frame(n3, jsonObject);
    }

    private static byte[] readBytes(DiscordIpc$Pipe discordIpc$Pipe, int n) throws Exception {
        int n2;
        boolean bl;
        block17: {
            block18: {
                bl = a;
                n2 = n;
                if (bl) break block17;
                if (n2 != 0) break block18;
                return new byte[0];
            }
            n2 = n;
        }
        byte[] byArray = new byte[n2];
        int n3 = 0;
        long l = System.currentTimeMillis() + DiscordIpc.b(5198, 7506729167304741385L);
        while (n3 < n) {
            block21: {
                int n4;
                int n5;
                block22: {
                    long l2;
                    block23: {
                        block24: {
                            block19: {
                                block20: {
                                    n5 = discordIpc$Pipe.readSome(byArray, n3, n - n3);
                                    n4 = n5;
                                    if (bl) break block19;
                                    if (n4 >= 0) break block20;
                                    throw new IOException(b[40]);
                                }
                                n4 = n5;
                            }
                            if (bl) break block21;
                            if (n4 != 0) break block22;
                            l2 = System.currentTimeMillis();
                            if (bl) break block23;
                            if (l2 <= l) break block24;
                            throw new IOException(b[52]);
                        }
                        l2 = DiscordIpc.b(5714, 20561465504813072L);
                    }
                    Thread.sleep(l2);
                    if (!bl) continue;
                }
                n4 = n3 = n3 + n5;
            }
            if (!bl) continue;
        }
        return byArray;
    }

    
    private static String errorMessage(DiscordIpc$Frame discordIpc$Frame) {
        boolean bl = a;
        try {
            String string;
            block19: {
                JsonObject jsonObject;
                block20: {
                    JsonObject jsonObject2;
                    block18: {
                        String[] stringArray;
                        DiscordIpc$Frame discordIpc$Frame2 = discordIpc$Frame;
                        if (!bl) {
                            if (discordIpc$Frame2.json == null) {
                                return null;
                            }
                            discordIpc$Frame2 = discordIpc$Frame;
                        }
                        if (!bl) {
                            if (!discordIpc$Frame2.json.has(b[19])) return null;
                            discordIpc$Frame2 = discordIpc$Frame;
                        }
                        boolean bl2 = discordIpc$Frame2.json.get(b[9]).isJsonNull();
                        if (!bl) {
                            if (bl2) {
                                return null;
                            }
                            stringArray = b;
                            bl2 = stringArray[24].equals(discordIpc$Frame.json.get(stringArray[9]).getAsString());
                        }
                        if (!bl2) {
                            return null;
                        }
                        stringArray = b;
                        jsonObject = jsonObject2 = discordIpc$Frame.json.getAsJsonObject(stringArray[27]);
                        if (bl) break block18;
                        if (jsonObject == null) break block19;
                        jsonObject = jsonObject2;
                    }
                    if (bl) break block20;
                    if (!jsonObject.has(b[41])) break block19;
                    jsonObject = jsonObject2.get(b[41]);
                }
                string = jsonObject.getAsString();
                return string;
            }
            string = b[51];
            return string;
        }
        catch (Exception exception) {
            return null;
        }
    }

    
    private static String userSuffix(DiscordIpc$Frame discordIpc$Frame) {
        boolean bl = a;
        try {
            Object object;
            block18: {
                block19: {
                    String string;
                    block23: {
                        String string2;
                        block17: {
                            JsonObject jsonObject;
                            block14: {
                                String string3;
                                JsonObject jsonObject2;
                                block15: {
                                    JsonObject jsonObject3;
                                    String[] stringArray;
                                    block16: {
                                        block22: {
                                            block21: {
                                                block20: {
                                                    stringArray = b;
                                                    jsonObject3 = discordIpc$Frame.json.getAsJsonObject(stringArray[27]).getAsJsonObject(stringArray[7]);
                                                    jsonObject = jsonObject3;
                                                    if (bl) break block14;
                                                    if (!jsonObject.has(stringArray[10])) break block16;
                                                    break block20;
                                                    catch (Exception exception) {
                                                        throw exception;
                                                    }
                                                }
                                                jsonObject2 = jsonObject3;
                                                string3 = b[0];
                                                if (bl) break block15;
                                                break block21;
                                                catch (Exception exception) {
                                                    throw exception;
                                                }
                                            }
                                            if (jsonObject2.get(string3).isJsonNull()) break block16;
                                            break block22;
                                            catch (Exception exception) {
                                                throw exception;
                                            }
                                        }
                                        string2 = jsonObject3.get(b[0]).getAsString();
                                        break block17;
                                    }
                                    jsonObject2 = jsonObject3;
                                    stringArray = b;
                                    string3 = stringArray[48];
                                }
                                jsonObject = jsonObject2.get(string3);
                            }
                            string2 = jsonObject.getAsString();
                        }
                        string = string2;
                        object = string;
                        if (bl) break block18;
                        if (!DiscordIpc.notBlank((String)object)) break block19;
                        break block23;
                        catch (Exception exception) {
                            throw exception;
                        }
                    }
                    object = b[55] + string;
                    break block18;
                }
                object = "";
            }
            return object;
        }
        catch (Exception exception) {
            return "";
        }
    }

    
    private static String imageWarning(DiscordIpc$Frame discordIpc$Frame, String string) {
        boolean bl;
        block17: {
            String string2;
            block16: {
                block25: {
                    bl = a;
                    string2 = string;
                    if (bl) break block16;
                    if (DiscordIpc.notBlank(string2)) break block17;
                    break block25;
                    catch (Exception exception) {
                        throw exception;
                    }
                }
                string2 = "";
            }
            return string2;
        }
        try {
            Object object;
            block24: {
                String[] stringArray;
                block23: {
                    boolean bl2;
                    block22: {
                        block21: {
                            JsonObject jsonObject;
                            block20: {
                                JsonObject jsonObject2;
                                block18: {
                                    block19: {
                                        JsonObject jsonObject3;
                                        block26: {
                                            stringArray = b;
                                            jsonObject3 = discordIpc$Frame.json.getAsJsonObject(stringArray[14]);
                                            jsonObject2 = jsonObject3;
                                            if (bl) break block18;
                                            if (!jsonObject2.has(stringArray[20])) break block19;
                                            break block26;
                                            catch (Exception exception) {
                                                throw exception;
                                            }
                                        }
                                        jsonObject2 = jsonObject3.getAsJsonObject(b[50]);
                                        break block18;
                                    }
                                    jsonObject2 = null;
                                }
                                JsonObject jsonObject4 = jsonObject2;
                                jsonObject = jsonObject4;
                                if (bl) break block20;
                                if (jsonObject == null) break block21;
                                jsonObject = jsonObject4;
                            }
                            bl2 = jsonObject.has(b[29]);
                            if (bl) break block22;
                            if (!bl2) break block21;
                            bl2 = true;
                            break block22;
                        }
                        bl2 = false;
                    }
                    boolean bl3 = bl2;
                    if (!bl3) break block23;
                    object = "";
                    break block24;
                }
                stringArray = b;
                object = stringArray[8] + string + stringArray[43];
            }
            return object;
        }
        catch (Exception exception) {
            return "";
        }
    }

    
    private static void closeQuietly(DiscordIpc$Pipe discordIpc$Pipe) {
        boolean bl = a;
        try {
            DiscordIpc$Pipe discordIpc$Pipe2 = discordIpc$Pipe;
            if (!bl) {
                if (discordIpc$Pipe2.file != null) {
                    discordIpc$Pipe.file.close();
                    if (!bl) return;
                }
                discordIpc$Pipe2 = discordIpc$Pipe;
            }
            discordIpc$Pipe2.sock.close();
            return;
        }
        catch (Exception exception) {
        }
    }

    private static String pipePath(int n) {
        String string;
        block10: {
            String string2;
            block11: {
                String[] stringArray;
                boolean bl;
                block8: {
                    block9: {
                        block7: {
                            int n2;
                            block6: {
                                bl = a;
                                n2 = WINDOWS;
                                if (bl) break block6;
                                if (n2 == 0) break block7;
                                n2 = n;
                            }
                            String[] stringArray2 = b;
                            return stringArray2[42] + n2;
                        }
                        stringArray = b;
                        string2 = System.getenv(stringArray[58]);
                        string = string2;
                        if (bl) break block8;
                        if (string != null) break block9;
                        stringArray = b;
                        string2 = System.getenv(stringArray[25]);
                    }
                    string = string2;
                }
                if (bl) break block10;
                if (string != null) break block11;
                stringArray = b;
                string2 = stringArray[18];
            }
            string = Path.of(string2, b[4] + n).toString();
        }
        return string;
    }

    private static boolean notBlank(String string) {
        boolean bl;
        block6: {
            block5: {
                String string2;
                boolean bl2;
                block4: {
                    bl2 = a;
                    string2 = string;
                    if (bl2) break block4;
                    if (string2 == null) break block5;
                    string2 = string;
                }
                bl = string2.isBlank();
                if (bl2) break block6;
                if (bl) break block5;
                bl = true;
                break block6;
            }
            bl = false;
        }
        return bl;
    }

    private static void lambda$static$1() {
        Thread thread = new Thread(DiscordIpc::lambda$static$0, b[36]);
        thread.setDaemon(true);
        thread.start();
        try {
            thread.join(DiscordIpc.b(25947, 6337779959018103581L));
        }
        catch (InterruptedException interruptedException) {
        }
    }

    private static void lambda$static$0() {
        try {
            DiscordIpc.close();
        }
        catch (Throwable throwable) {
        }
    }

    
    static {
        block32: {
            block31: {
                block30: {
                    block29: {
                        block28: {
                            block27: {
                                var21 = new String[61];
                                var19_1 = 0;
                                var18_2 = "*Kq\u0018p'*#Fs\u001f\b\u001fBt\u001fr?\u0010)\r\u0003HjZr$\u001b#B}\u000et/\b$Im\u000ep%\u0016(\f)Nm\u0019~9\u0011`Nn\u0019<\u0005>S\u007f\u000et\u0011\tNm\u0019~9\u0011mIq\u000e1-\u001a8Iz\u00048T{\b\fm\u000fp\u00151\"\u0018,@{Z3\u0003(Qj\u000b*Kq\u0018p'*#Fs\u001f\t.Kw\u001f\u007f?*$C\f\u001ebJ%P\b!\u0004qW.H\u0002m\u000f\u0004)Fj\u001b\r\u0003HjZr$\u001b#B}\u000et/\u001a\fRl\u0015c*X\tNm\u0019~9\u0011\u0004W}Wb#\u00009Cq\r\u007f\u00049^n\u001f\u0004bSs\n\u0003(Qj\u0006,Tm\u001fe8\u0011/FzZw9\u0014 B>\u0016t%\u00129O>\u000e\tNm\u0019~9\u0011muN910\b\u000f\u001eBjZpk6!N{\u0014ek<\t\u0005\buL5C\u0006\u0019jN>X\u0019\r\u001fB}\u0015\u007f%\u0010.Sw\u0014v\u206d\u0004)Fj\u001b\u0012\u001fBx\u000fb.\u0011mEgZU\"\u0006.Hl\u001e\u000b!Fl\u001dt\u0014\u001c Fy\u001f\t\u000eHp\u0014t(\u0001(C\r\u001fB}\u0015\u007f%\u0010.Sw\u0014v\u206d\u0005>S\u007f\be\u0003:Np\n9Ns\u001fb?\u0014 Wm\u0018\tNm\u0019~9\u0011mO\u007f\u0014u8\u001d,L{Zw*\u001c!Bz\u0017\fRl\u0015c*X\tNm\u0019~9\u0011\u0004W}Wr'\u001a>B\u00026Z\u0007\"T0\u0014p&\u0010\u0003=Nz\u000b=Nn\u001f1(\u0019\"T{\u001e\u0007 Bm\tp,\u0010\u0015\u0011{0&a\"\u0005({z\u0013b(\u001a?C3\u0013a(X\u0002o\u000e\n!Fl\u001dt\u0014\u0001(_j\b,Dj\u0013g\"\u00014\u0004,Uy\t\u0011\tNm\u0019~9\u0011mnN919\u0010,Cg\b8T{\b\u007f*\u0018(\u0005#Hp\u0019t\u0006,Tm\u001fe8\u0013\u001fBt\u001fr?\u0010)\u0007|\u00031\u000f\u001c>Dq\bu#\tNm\u0019~9\u0011mCw\u001e1%\u001a9\u0007l\u001fa'\fmPw\u000ey\"\u001bm\u0015.J!&\u0006\u0007)Bj\u001bx'\u0006\u000b!Fl\u001dt\u0014\u001c Fy\u001f\u0004mFmZ\u0007 Bm\tp,\u0010\u0003.Jz\u000f\u0015cY%C\u001e;\u0019nS?N\u000f<\u001f";
                                var20_3 = "*Kq\u0018p'*#Fs\u001f\b\u001fBt\u001fr?\u0010)\r\u0003HjZr$\u001b#B}\u000et/\b$Im\u000ep%\u0016(\f)Nm\u0019~9\u0011`Nn\u0019<\u0005>S\u007f\u000et\u0011\tNm\u0019~9\u0011mIq\u000e1-\u001a8Iz\u00048T{\b\fm\u000fp\u00151\"\u0018,@{Z3\u0003(Qj\u000b*Kq\u0018p'*#Fs\u001f\t.Kw\u001f\u007f?*$C\f\u001ebJ%P\b!\u0004qW.H\u0002m\u000f\u0004)Fj\u001b\r\u0003HjZr$\u001b#B}\u000et/\u001a\fRl\u0015c*X\tNm\u0019~9\u0011\u0004W}Wb#\u00009Cq\r\u007f\u00049^n\u001f\u0004bSs\n\u0003(Qj\u0006,Tm\u001fe8\u0011/FzZw9\u0014 B>\u0016t%\u00129O>\u000e\tNm\u0019~9\u0011muN910\b\u000f\u001eBjZpk6!N{\u0014ek<\t\u0005\buL5C\u0006\u0019jN>X\u0019\r\u001fB}\u0015\u007f%\u0010.Sw\u0014v\u206d\u0004)Fj\u001b\u0012\u001fBx\u000fb.\u0011mEgZU\"\u0006.Hl\u001e\u000b!Fl\u001dt\u0014\u001c Fy\u001f\t\u000eHp\u0014t(\u0001(C\r\u001fB}\u0015\u007f%\u0010.Sw\u0014v\u206d\u0005>S\u007f\be\u0003:Np\n9Ns\u001fb?\u0014 Wm\u0018\tNm\u0019~9\u0011mO\u007f\u0014u8\u001d,L{Zw*\u001c!Bz\u0017\fRl\u0015c*X\tNm\u0019~9\u0011\u0004W}Wr'\u001a>B\u00026Z\u0007\"T0\u0014p&\u0010\u0003=Nz\u000b=Nn\u001f1(\u0019\"T{\u001e\u0007 Bm\tp,\u0010\u0015\u0011{0&a\"\u0005({z\u0013b(\u001a?C3\u0013a(X\u0002o\u000e\n!Fl\u001dt\u0014\u0001(_j\b,Dj\u0013g\"\u00014\u0004,Uy\t\u0011\tNm\u0019~9\u0011mnN919\u0010,Cg\b8T{\b\u007f*\u0018(\u0005#Hp\u0019t\u0006,Tm\u001fe8\u0013\u001fBt\u001fr?\u0010)\u0007|\u00031\u000f\u001c>Dq\bu#\tNm\u0019~9\u0011mCw\u001e1%\u001a9\u0007l\u001fa'\fmPw\u000ey\"\u001bm\u0015.J!&\u0006\u0007)Bj\u001bx'\u0006\u000b!Fl\u001dt\u0014\u001c Fy\u001f\u0004mFmZ\u0007 Bm\tp,\u0010\u0003.Jz\u000f\u0015cY%C\u001e;\u0019nS?N\u000f<\u001f".length();
                                var17_4 = 11;
                                var16_5 = -1;
lbl7:

                                while (true) {
                                    v0 = ++var16_5;
                                    v1 = var18_2.substring(v0, v0 + var17_4);
                                    v2 = -1;
                                    break block27;
                                    break;
                                }
lbl12:

                                while (true) {
                                    var21[var19_1++] = v3.intern();
                                    if ((var16_5 += var17_4) < var20_3) {
                                        var17_4 = var18_2.charAt(var16_5);
                                        continue;
                                    }
                                    var18_2 = "\u000eHp\u0014t(\u0001(C\tmDr\u0013t%\u0001>\u000e";
                                    var20_3 = "\u000eHp\u0014t(\u0001(C\tmDr\u0013t%\u0001>\u000e".length();
                                    var17_4 = 9;
                                    var16_5 = -1;
lbl21:

                                    while (true) {
                                        v4 = ++var16_5;
                                        v1 = var18_2.substring(v4, v4 + var17_4);
                                        v2 = 0;
                                        break block27;
                                        break;
                                    }
                                    break;
                                }
lbl26:

                                while (true) {
                                    var21[var19_1++] = v3.intern();
                                    if ((var16_5 += var17_4) < var20_3) {
                                        var17_4 = var18_2.charAt(var16_5);
                                        continue;
                                    }
                                    break block28;
                                    break;
                                }
                            }
                            v5 = v1.toCharArray();
                            v6 = v5;
                            v7 = v5.length;
                            var22_7 = 0;
                            do {
                                v6 = v6;
                                v8 = var22_7;
                                v9 = v6[v8];
                                switch (var22_7 % 7) {
                                    case 0: {
                                        v10 = 77;
                                        break;
                                    }
                                    case 1: {
                                        v10 = 39;
                                        break;
                                    }
                                    case 2: {
                                        v10 = 30;
                                        break;
                                    }
                                    case 3: {
                                        v10 = 122;
                                        break;
                                    }
                                    case 4: {
                                        v10 = 17;
                                        break;
                                    }
                                    case 5: {
                                        v10 = 75;
                                        break;
                                    }
                                    default: {
                                        v10 = 117;
                                    }
                                }
                                v6[v8] = (char)(v9 ^ v10);
                                ++var22_7;
lbl65:

                                v7 = v7;
                            } while (v7 > var22_7);
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
                        DiscordIpc.b = var21;
                        var8_8 = 398948336781027563L;
                        var14_9 = new long[2];
                        var11_10 = 0;
                        var12_11 = "X|\u00b3(/\u00bc\u00d8\u00ecZ>`t\u00f4\u008b\u00cb\u0019";
                        var13_12 = "X|\u00b3(/\u00bc\u00d8\u00ecZ>`t\u00f4\u008b\u00cb\u0019".length();
                        var10_13 = 0;
                        while (true) {
                            break block29;
                            break;
                        }
lbl84:

                        while (true) {
                            var14_9[v11] = (((long)var15_14[0] & 255L) << 56 | ((long)var15_14[1] & 255L) << 48 | ((long)var15_14[2] & 255L) << 40 | ((long)var15_14[3] & 255L) << 32 | ((long)var15_14[4] & 255L) << 24 | ((long)var15_14[5] & 255L) << 16 | ((long)var15_14[6] & 255L) << 8 | (long)var15_14[7] & 255L) ^ var8_8;
                            if (var10_13 < var13_12) continue;
                            break block30;
                            break;
                        }
                    }
                    var15_14 = var12_11.substring(var10_13, var10_13 += 8).getBytes("ISO-8859-1");
                    v11 = var11_10++;
                    while (true)
                }
                DiscordIpc.c = var14_9;
                DiscordIpc.d = new Integer[2];
                DiscordIpc.OP_CLOSE = DiscordIpc.a(28642, 2545021573835534341L);
                var0_15 = 387508867592842757L;
                var6_16 = new long[6];
                var3_17 = 0;
                var4_18 = "\u0005)\u00b9\u00c8/V\u00be\u0010\u001cY\u00c6\u009a\u00a2o\u001f\u00feq=N\u00d8\u0003\u0080\u00d5\u00b9n\u00a1BX6|\u00a8B";
                var5_19 = "\u0005)\u00b9\u00c8/V\u00be\u0010\u001cY\u00c6\u009a\u00a2o\u001f\u00feq=N\u00d8\u0003\u0080\u00d5\u00b9n\u00a1BX6|\u00a8B".length();
                var2_20 = 0;
                while (true) {
                    var7_21 = var4_18.substring(var2_20, var2_20 += 8).getBytes("ISO-8859-1");
                    v12 = var6_16;
                    v13 = var3_17++;
                    v14 = ((long)var7_21[0] & 255L) << 56 | ((long)var7_21[1] & 255L) << 48 | ((long)var7_21[2] & 255L) << 40 | ((long)var7_21[3] & 255L) << 32 | ((long)var7_21[4] & 255L) << 24 | ((long)var7_21[5] & 255L) << 16 | ((long)var7_21[6] & 255L) << 8 | (long)var7_21[7] & 255L;
                    v15 = -1;
                    break block31;
                    break;
                }
lbl112:

                while (true) {
                    v12[v13] = v16;
                    if (var2_20 < var5_19) continue;
                    var4_18 = "R\u0094\u00e4\b!\u00f2L\u00ecmM\u00f7$\u00d2l;\u00dc";
                    var5_19 = "R\u0094\u00e4\b!\u00f2L\u00ecmM\u00f7$\u00d2l;\u00dc".length();
                    var2_20 = 0;
                    while (true) {
                        var7_21 = var4_18.substring(var2_20, var2_20 += 8).getBytes("ISO-8859-1");
                        v12 = var6_16;
                        v13 = var3_17++;
                        v14 = ((long)var7_21[0] & 255L) << 56 | ((long)var7_21[1] & 255L) << 48 | ((long)var7_21[2] & 255L) << 40 | ((long)var7_21[3] & 255L) << 32 | ((long)var7_21[4] & 255L) << 24 | ((long)var7_21[5] & 255L) << 16 | ((long)var7_21[6] & 255L) << 8 | (long)var7_21[7] & 255L;
                        v15 = 0;
                        break block31;
                        break;
                    }
                    break;
                }
lbl125:

                while (true) {
                    v12[v13] = v16;
                    if (var2_20 < var5_19) continue;
                    break block32;
                    break;
                }
            }
            v16 = v14 ^ var0_15;
            switch (v15) {
                default: {
                    continue;
                }
                case 0:
lbl136:

                continue;
            }
        }
        DiscordIpc.e = var6_16;
        DiscordIpc.f = new Long[6];
        DiscordIpc.RETRY_MS = DiscordIpc.b(30006, 7764758928542643063L);
        DiscordIpc.READ_TIMEOUT_MS = DiscordIpc.b(12392, 1817611303456372267L);
        DiscordIpc.pipes = new CopyOnWriteArrayList<DiscordIpc$Pipe>();
        var16_6 = DiscordIpc.b;
        DiscordIpc.status = var16_6[2];
        DiscordIpc.PID = ProcessHandle.current().pid();
        DiscordIpc.WINDOWS = System.getProperty(var16_6[38], "").toLowerCase().contains(var16_6[33]);
        Runtime.getRuntime().addShutdownHook(new Thread((Runnable)LambdaMetafactory.metafactory(null, null, null, ()V, lambda$static$1(), ()V)(), var16_6[16]));
        Aurora.LOG.debug(var16_6[47]);
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5FE7;
        if (d[n2] == null) {
            DiscordIpc.d[n2] = (int)(c[n2] ^ l);
        }
        return d[n2];
    }

    private static long b(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x4642) & Short.MAX_VALUE;
        if (f[n2] == null) {
            DiscordIpc.f[n2] = e[n2] ^ l;
        }
        return f[n2];
    }
}
