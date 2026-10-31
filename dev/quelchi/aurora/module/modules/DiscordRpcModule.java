package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.AntiAltModule;
import dev.quelchi.aurora.module.setting.ActionSetting;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.rpc.DiscordIpc;
import dev.quelchi.aurora.util.Daemon;
import java.lang.invoke.LambdaMetafactory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.text.Text;

public final class DiscordRpcModule
extends Module {
    private static final String APP_ID;
    private static final String IMAGE;
    private final BooleanSetting showServer;
    private final BooleanSetting showElapsed;
    private final ActionSetting connection;
    private static final long INTERVAL_MS;
    private long lastPush;
    private long startedAt;
    private String lastPayload;
    private static final String[] b;
    private static final long[] d;
    private static final Long[] e;

    public DiscordRpcModule() {
        String[] stringArray = b;
        super(stringArray[4], stringArray[8], Category.MISC, true);
        this.showServer = new BooleanSetting(stringArray[0], true);
        this.showElapsed = new BooleanSetting(stringArray[14], true);
        this.connection = new ActionSetting(stringArray[2], DiscordRpcModule::lambda$new$0, this::reconnect);
        this.lastPayload = "";
        this.startedAt = System.currentTimeMillis() / DiscordRpcModule.a(25264, 6204000865193274119L);
        this.addSettings(this.connection, this.showServer, this.showElapsed);
    }

    @Override
    public void onEnable() {
        this.startedAt = System.currentTimeMillis() / DiscordRpcModule.a(30114, 6390006920512355351L);
        this.pushNow();
    }

    @Override
    public void onDisable() {
        Daemon.run(b[10], DiscordIpc::close);
    }

    @Override
    public void onSettingsChanged() {
        this.pushNow();
    }

    
    @Override
    public void onTick() {
        block17: {
            block19: {
                block18: {
                    block15: {
                        block16: {
                            var2_1 = System.currentTimeMillis();
                            var1_2 = AmbienceModule.b;
                            cfr_temp_0 = var2_1 - this.lastPush - DiscordRpcModule.a(3103, 6045723746004845993L);
                            v0 = cfr_temp_0 == 0L ? 0 : (cfr_temp_0 < 0L ? -1 : 1);
                            if (var1_2) break block15;
                            if (v0 >= 0) break block16;
                            return;
                        }
                        v2 = this;
                        if (!var1_2) {
                            v0 = (long)v2.showElapsed.getValue();
                        }
                    }
                    if (v0 != false) {
                        v2 = this;
lbl24:

                        v4 = v2.startedAt;
                    } else {
                        v4 = 0L;
                    }
                    var4_3 = v4;
                    var6_4 = this.detailsLine();
                    var7_5 = this.stateLine();
                    var8_6 = String.join((CharSequence)"|", new CharSequence[]{var6_4, var7_5, String.valueOf(var4_3)});
                    if (var1_2) break block17;
                    if (!DiscordIpc.isConnected()) break block18;
                    v6 = var8_6;
                    if (var1_2) break block19;
                    if (!v6.equals(this.lastPayload)) break block18;
                    this.lastPush = var2_1;
                    return;
                }
                this.lastPush = var2_1;
                this.lastPayload = var8_6;
                v6 = DiscordRpcModule.b[6];
            }
            Daemon.run(v6, (Runnable)LambdaMetafactory.metafactory(null, null, null, ()V, lambda$onTick$1(java.lang.String java.lang.String long ), ()V)((String)var6_4, (String)var7_5, (long)var4_3));
        }
    }

    private void reconnect() {
        this.pushNow();
        Daemon.run(b[1], DiscordRpcModule::lambda$reconnect$2);
    }

    private void pushNow() {
        this.lastPush = 0L;
        this.lastPayload = "";
    }

    private static String trim(String string) {
        Object object;
        block12: {
            String string2;
            block10: {
                block11: {
                    boolean bl;
                    block8: {
                        block9: {
                            bl = AmbienceModule.b;
                            string2 = string;
                            if (bl) break block8;
                            if (string2 != null) break block9;
                            return "";
                        }
                        string2 = string;
                    }
                    if (bl) break block10;
                    if (string2.length() > 30) break block11;
                    object = string;
                    break block12;
                }
                string2 = string.substring(0, 29);
            }
            object = string2 + "\u2026";
        }
        return object;
    }

    private String detailsLine() {
        String string;
        block14: {
            MinecraftClient minecraftClient;
            block13: {
                Text text;
                block12: {
                    Text text2;
                    boolean bl;
                    block11: {
                        AntiAltModule antiAltModule;
                        block9: {
                            AntiAltModule antiAltModule2;
                            block10: {
                                minecraftClient = MinecraftClient.method_1551();
                                bl = AmbienceModule.b;
                                if (minecraftClient.field_1724 == null) {
                                    return b[7];
                                }
                                antiAltModule2 = AntiAltModule.get();
                                antiAltModule = antiAltModule2;
                                if (bl) break block9;
                                if (antiAltModule != null) break block10;
                                text2 = null;
                                break block11;
                            }
                            antiAltModule = antiAltModule2;
                        }
                        text2 = antiAltModule.spoofedName();
                    }
                    Text text3 = text2;
                    text = text3;
                    if (bl) break block12;
                    if (text == null) break block13;
                    text = text3;
                }
                string = text.getString();
                break block14;
            }
            string = minecraftClient.field_1724.method_7334().name();
        }
        return b[16] + string;
    }

    private String stateLine() {
        Object object;
        block21: {
            block19: {
                ServerInfo serverInfo;
                block20: {
                    ServerInfo serverInfo2;
                    boolean bl;
                    block18: {
                        MinecraftClient minecraftClient;
                        block17: {
                            boolean bl2;
                            MinecraftClient minecraftClient2;
                            block15: {
                                block16: {
                                    minecraftClient2 = MinecraftClient.method_1551();
                                    bl = AmbienceModule.b;
                                    bl2 = this.showServer.getValue();
                                    if (bl) break block15;
                                    if (bl2) break block16;
                                    return b[13];
                                }
                                minecraftClient = minecraftClient2;
                                if (bl) break block17;
                                bl2 = minecraftClient.method_1542();
                            }
                            if (bl2) {
                                return b[17];
                            }
                            minecraftClient = minecraftClient2;
                        }
                        serverInfo2 = minecraftClient.method_1558();
                        serverInfo = serverInfo2;
                        if (bl) break block18;
                        if (serverInfo == null) break block19;
                        serverInfo = serverInfo2;
                    }
                    if (bl) break block20;
                    if (serverInfo.field_3761 == null) break block19;
                    serverInfo = serverInfo2;
                }
                String[] stringArray = b;
                object = stringArray[5] + serverInfo.field_3761;
                break block21;
            }
            object = b[15];
        }
        return object;
    }

    private static void lambda$reconnect$2() {
        DiscordIpc.close();
        DiscordIpc.resetBackoff();
    }

    private static void lambda$onTick$1(String string, String string2, long l) {
        String[] stringArray = b;
        DiscordIpc.update(stringArray[9], string, string2, stringArray[11], stringArray[3], l);
    }

    private static String lambda$new$0() {
        return DiscordRpcModule.trim(DiscordIpc.getStatus());
    }

    
    static {
        block28: {
            block27: {
                block26: {
                    block25: {
                        var13 = new String[19];
                        var11_1 = 0;
                        var10_2 = "u%\u0012\u0014R=MT;\u0018\u0011\u001bg8\u000f\f\u0000/\u0005b$\u000e\u0000\u001d<Lt=\u001eN\u0000+KI#\u0013\u0006\u0011:\ne\"\u0013\r\u0017-\\O\"\u0013\rg8\u000f\f\u0000/\be!\u0014\u0006\u001c:\u000bb$\u000e\u0000\u001d<L\u0006\u001f- \u0003I#]\u0011g8\u000f\f\u0000/\u0005b$\u000e\u0000\u001d<Lt=\u001e\fo#]\u0017\u001a+\bK(\u0013\u0016\u0001\u0017u%\u0012\u0014R=MU>\u0014\f\u001cnGHm9\n\u0001-GT)\u0013\u0017xNUCv\u0010\u0013tJZAx\u001b\u0014uJTG\u0017g8\u000f\f\u0000/\u0005b$\u000e\u0000\u001d<Lt=\u001eN\u0011\"GU(\u0006G8\u000f\f\u0000/\u0006G8\u000f\f\u0000/\rg8\u000f\f\u0000/\be!\u0014\u0006\u001c:\u0011u%\u0012\u0014R+DG=\u000e\u0006\u0016n\\O \u0018\u000bk8\u0011\u0017\u001b>DG4\u0018\u0011\u000bv!\u001c\u001a\u001b O\u0006,\u000eC";
                        var12_3 = "u%\u0012\u0014R=MT;\u0018\u0011\u001bg8\u000f\f\u0000/\u0005b$\u000e\u0000\u001d<Lt=\u001eN\u0000+KI#\u0013\u0006\u0011:\ne\"\u0013\r\u0017-\\O\"\u0013\rg8\u000f\f\u0000/\be!\u0014\u0006\u001c:\u000bb$\u000e\u0000\u001d<L\u0006\u001f- \u0003I#]\u0011g8\u000f\f\u0000/\u0005b$\u000e\u0000\u001d<Lt=\u001e\fo#]\u0017\u001a+\bK(\u0013\u0016\u0001\u0017u%\u0012\u0014R=MU>\u0014\f\u001cnGHm9\n\u0001-GT)\u0013\u0017xNUCv\u0010\u0013tJZAx\u001b\u0014uJTG\u0017g8\u000f\f\u0000/\u0005b$\u000e\u0000\u001d<Lt=\u001eN\u0011\"GU(\u0006G8\u000f\f\u0000/\u0006G8\u000f\f\u0000/\rg8\u000f\f\u0000/\be!\u0014\u0006\u001c:\u0011u%\u0012\u0014R+DG=\u000e\u0006\u0016n\\O \u0018\u000bk8\u0011\u0017\u001b>DG4\u0018\u0011\u000bv!\u001c\u001a\u001b O\u0006,\u000eC".length();
                        var9_4 = 11;
                        var8_5 = -1;
lbl7:

                        while (true) {
                            v0 = ++var8_5;
                            v1 = var10_2.substring(v0, v0 + var9_4);
                            v2 = -1;
                            break block25;
                            break;
                        }
lbl12:

                        while (true) {
                            var13[var11_1++] = v3.intern();
                            if ((var8_5 += var9_4) < var12_3) {
                                var9_4 = var10_2.charAt(var8_5);
                                continue;
                            }
                            var10_2 = "u$\u0013\u0004\u001e+XJ,\u0004\u0006\u0000\u0013\u0017xNUCv\u0010\u0013tJZAx\u001b\u0014uJTG";
                            var12_3 = "u$\u0013\u0004\u001e+XJ,\u0004\u0006\u0000\u0013\u0017xNUCv\u0010\u0013tJZAx\u001b\u0014uJTG".length();
                            var9_4 = 12;
                            var8_5 = -1;
lbl21:

                            while (true) {
                                v4 = ++var8_5;
                                v1 = var10_2.substring(v4, v4 + var9_4);
                                v2 = 0;
                                break block25;
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
                            break block26;
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
                                v10 = 38;
                                break;
                            }
                            case 1: {
                                v10 = 77;
                                break;
                            }
                            case 2: {
                                v10 = 125;
                                break;
                            }
                            case 3: {
                                v10 = 99;
                                break;
                            }
                            case 4: {
                                v10 = 114;
                                break;
                            }
                            case 5: {
                                v10 = 78;
                                break;
                            }
                            default: {
                                v10 = 40;
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
                DiscordRpcModule.b = var13;
                DiscordRpcModule.IMAGE = DiscordRpcModule.b[12];
                DiscordRpcModule.APP_ID = DiscordRpcModule.b[18];
                var0_7 = 8551402576306306695L;
                var6_8 = new long[4];
                var3_9 = 0;
                var4_10 = "%J\u0016\u00e2\u00de\u00db\u00a8\u00a6 \u00b5\u00a6\u00bfP\u0017Vh";
                var5_11 = "%J\u0016\u00e2\u00de\u00db\u00a8\u00a6 \u00b5\u00a6\u00bfP\u0017Vh".length();
                var2_12 = 0;
                while (true) {
                    var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
                    v11 = var6_8;
                    v12 = var3_9++;
                    v13 = ((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L;
                    v14 = -1;
                    break block27;
                    break;
                }
lbl92:

                while (true) {
                    v11[v12] = v15;
                    if (var2_12 < var5_11) continue;
                    var4_10 = "\u0015\u0019\u00f2\u008d: \u00f4\u00a9.\u0001r%\u00bdu\u00c1x";
                    var5_11 = "\u0015\u0019\u00f2\u008d: \u00f4\u00a9.\u0001r%\u00bdu\u00c1x".length();
                    var2_12 = 0;
                    while (true) {
                        var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
                        v11 = var6_8;
                        v12 = var3_9++;
                        v13 = ((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L;
                        v14 = 0;
                        break block27;
                        break;
                    }
                    break;
                }
lbl105:

                while (true) {
                    v11[v12] = v15;
                    if (var2_12 < var5_11) continue;
                    break block28;
                    break;
                }
            }
            v15 = v13 ^ var0_7;
            switch (v14) {
                default: {
                    continue;
                }
                case 0:
lbl116:

                continue;
            }
        }
        DiscordRpcModule.d = var6_8;
        DiscordRpcModule.e = new Long[4];
        DiscordRpcModule.INTERVAL_MS = DiscordRpcModule.a(20498, 7184751966159378854L);
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x49B6) & Short.MAX_VALUE;
        if (e[n2] == null) {
            DiscordRpcModule.e[n2] = d[n2] ^ l;
        }
        return e[n2];
    }
}
