package dev.quelchi.aurora.gui.shell;

import dev.quelchi.aurora.auth.Accounts;
import dev.quelchi.aurora.auth.Accounts$Saved;
import dev.quelchi.aurora.auth.BrowserAuth;
import dev.quelchi.aurora.auth.MsAuth;
import dev.quelchi.aurora.auth.MsAuth$Account;
import dev.quelchi.aurora.auth.MsAuth$AuthException;
import dev.quelchi.aurora.auth.MsAuth$DeviceCode;
import dev.quelchi.aurora.gui.shell.SearchIndex;
import java.util.Optional;
import java.util.UUID;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.text.Text;
import net.minecraft.util.Util;

public final class AccountFlow {
    private static volatile MsAuth$DeviceCode pending;
    private static volatile boolean working;
    private static long nextPoll;
    private static final String[] a;
    private static final long[] b;
    private static final Long[] c;

    private AccountFlow() {
    }

    public static boolean working() {
        return working;
    }

    public static boolean waiting() {
        boolean bl = pending != null;
        return bl;
    }

    public static void reopen() {
        MsAuth$DeviceCode msAuth$DeviceCode = pending;
        if (msAuth$DeviceCode != null) {
            Util.method_668().method_670(msAuth$DeviceCode.signInUrl());
        }
    }

    public static void cancel() {
        if (pending == null) {
            return;
        }
        pending = null;
        Accounts.setStatus("");
    }

    public static void beginAdd() {
        AccountFlow.beginAdd("");
    }

    private static void beginAdd(String string) {
        block15: {
            String string2;
            block13: {
                block14: {
                    boolean bl;
                    boolean bl2;
                    block11: {
                        block12: {
                            bl2 = SearchIndex.a;
                            bl = working;
                            if (bl2) break block11;
                            if (!bl) break block12;
                            return;
                        }
                        working = true;
                        string2 = string;
                        if (bl2) break block13;
                        bl = string2.isEmpty();
                    }
                    if (!bl) break block14;
                    Accounts.setStatus("");
                    if (!bl2) break block15;
                }
                string2 = string;
            }
            Accounts.setError(string2);
        }
        if (BrowserAuth.configured()) {
            AccountFlow.run(AccountFlow::lambda$beginAdd$0);
            return;
        }
        AccountFlow.run(() -> AccountFlow.lambda$beginAdd$1(string));
    }

    public static void switchTo(Accounts$Saved accounts$Saved) {
        Accounts$Saved accounts$Saved2;
        block19: {
            block20: {
                boolean bl;
                block17: {
                    block18: {
                        Accounts$Saved accounts$Saved3;
                        block15: {
                            block16: {
                                bl = SearchIndex.a;
                                if (working) {
                                    return;
                                }
                                accounts$Saved3 = accounts$Saved;
                                if (bl) break block15;
                                if (accounts$Saved3.refreshToken() == null) break block16;
                                accounts$Saved2 = accounts$Saved;
                                if (bl) break block17;
                                if (!accounts$Saved2.refreshToken().isBlank()) break block18;
                            }
                            accounts$Saved3 = accounts$Saved;
                        }
                        Accounts.setStatus(a[2] + accounts$Saved3.name());
                        AccountFlow.beginAdd();
                        return;
                    }
                    accounts$Saved2 = accounts$Saved;
                }
                if (bl) break block19;
                if (!accounts$Saved2.sessionUsable()) break block20;
                Accounts.apply(new MsAuth$Account(accounts$Saved.uuid(), accounts$Saved.name(), accounts$Saved.sessionToken(), accounts$Saved.refreshToken(), accounts$Saved.xuid()));
                return;
            }
            accounts$Saved2 = accounts$Saved;
        }
        AccountFlow.refresh(accounts$Saved2, false);
    }

    
    public static void renewSession() {
        Accounts$Saved accounts$Saved;
        block20: {
            Accounts$Saved accounts$Saved2;
            block21: {
                String string;
                block19: {
                    block18: {
                        Accounts$Saved accounts$Saved32;
                        boolean bl;
                        block17: {
                            block16: {
                                bl = SearchIndex.a;
                                if (working) {
                                    return;
                                }
                                UUID uUID = Accounts.current();
                                accounts$Saved2 = null;
                                for (Accounts$Saved accounts$Saved32 : Accounts.all()) {
                                    if (!bl) {
                                        Accounts$Saved accounts$Saved4;
                                        Accounts$Saved accounts$Saved5 = accounts$Saved4 = accounts$Saved32;
                                        if (!bl && accounts$Saved5.uuid().equals(uUID)) {
                                            accounts$Saved5 = accounts$Saved2 = accounts$Saved4;
                                        }
                                        if (!bl) continue;
                                    }
                                    break block16;
                                }
                                accounts$Saved32 = accounts$Saved2;
                            }
                            if (bl) break block17;
                            if (accounts$Saved32 == null) break block18;
                            accounts$Saved32 = accounts$Saved2;
                        }
                        string = accounts$Saved32.refreshToken();
                        if (bl) break block19;
                        if (string == null) break block18;
                        accounts$Saved = accounts$Saved2;
                        if (bl) break block20;
                        if (!accounts$Saved.refreshToken().isBlank()) break block21;
                    }
                    string = a[10];
                }
                AccountFlow.toast(string, a[6]);
                return;
            }
            accounts$Saved = accounts$Saved2;
        }
        AccountFlow.refresh(accounts$Saved, true);
    }

    private static void toast(String string, String string2) {
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        minecraftClient.execute(() -> AccountFlow.lambda$toast$2(minecraftClient, string, string2));
    }

    private static void refresh(Accounts$Saved accounts$Saved, boolean bl) {
        working = true;
        Accounts.setStatus(a[9] + accounts$Saved.name() + "\u2026");
        AccountFlow.run(() -> AccountFlow.lambda$refresh$4(accounts$Saved, bl));
    }

    public static void poll() {
        MsAuth$DeviceCode msAuth$DeviceCode;
        block14: {
            long l;
            long l2;
            MsAuth$DeviceCode msAuth$DeviceCode2;
            block12: {
                boolean bl;
                block13: {
                    block11: {
                        msAuth$DeviceCode2 = pending;
                        bl = SearchIndex.a;
                        if (msAuth$DeviceCode2 != null && !working) break block11;
                        return;
                    }
                    l2 = System.currentTimeMillis();
                    long l3 = l2 - nextPoll;
                    l = l3 == 0L ? 0 : (l3 < 0L ? -1 : 1);
                    if (bl) break block12;
                    if (l >= 0) break block13;
                    return;
                }
                msAuth$DeviceCode = msAuth$DeviceCode2;
                if (bl) break block14;
                l = (long)msAuth$DeviceCode.expired();
            }
            if (l != false) {
                pending = null;
                Accounts.setError(a[7]);
                return;
            }
            nextPoll = l2 + (long)Math.max(2, msAuth$DeviceCode2.interval()) * AccountFlow.a(8530, 942374540964108961L);
            working = true;
            msAuth$DeviceCode = msAuth$DeviceCode2;
        }
        AccountFlow.run(() -> AccountFlow.lambda$poll$5(msAuth$DeviceCode));
    }

    private static void run(Runnable runnable) {
        Thread thread = new Thread(runnable, a[3]);
        thread.setDaemon(true);
        thread.start();
    }

    
    private static void lambda$poll$5(MsAuth$DeviceCode msAuth$DeviceCode) {
        boolean bl = SearchIndex.a;
        try {
            Optional<MsAuth$Account> optional = MsAuth.tryComplete(msAuth$DeviceCode);
            if (bl) return;
            if (!optional.isPresent()) return;
            Accounts.put(optional.get());
            Accounts.apply(optional.get());
            pending = null;
            return;
        }
        catch (MsAuth$AuthException msAuth$AuthException) {
            pending = null;
            Accounts.setError(msAuth$AuthException.getMessage());
            return;
        }
        catch (Exception exception) {
            Accounts.setError(a[5]);
            return;
        }
        finally {
            working = false;
        }
    }

    /*
     * Exception decompiling
     */
    private static void lambda$refresh$4(Accounts$Saved var0, boolean var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Started 2 blocks at once
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:412)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:487)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:845)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1042)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:929)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:73)
         *     at org.benf.cfr.reader.Main.main(Main.java:49)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    private static void lambda$refresh$3(Accounts$Saved accounts$Saved, String string) {
        Accounts.put(new Accounts$Saved(accounts$Saved.uuid(), accounts$Saved.name(), string));
    }

    private static void lambda$toast$2(MinecraftClient minecraftClient, String string, String string2) {
        SystemToast.method_1990((ToastManager)minecraftClient.method_1566(), (SystemToast.Type)SystemToast.Type.field_47588, (Text)Text.method_43470((String)string), (Text)Text.method_43470((String)string2));
    }

    
    private static void lambda$beginAdd$1(String string) {
        boolean bl = SearchIndex.a;
        try {
            MsAuth$DeviceCode msAuth$DeviceCode;
            pending = msAuth$DeviceCode = MsAuth.begin();
            nextPoll = System.currentTimeMillis() + (long)msAuth$DeviceCode.interval() * AccountFlow.a(2833, 8594226652131045603L);
            if (bl) return;
            if (string.isEmpty()) {
                Accounts.setStatus(a[12]);
            }
            Util.method_668().method_670(msAuth$DeviceCode.signInUrl());
            return;
        }
        catch (MsAuth$AuthException msAuth$AuthException) {
            Accounts.setError(msAuth$AuthException.getMessage());
            return;
        }
        catch (Exception exception) {
            Accounts.setError(a[4] + exception.getClass().getSimpleName());
            return;
        }
        finally {
            working = false;
        }
    }

    private static void lambda$beginAdd$0() {
        try {
            MsAuth$Account msAuth$Account = BrowserAuth.signIn(Accounts::setStatus);
            Accounts.put(msAuth$Account);
            Accounts.apply(msAuth$Account);
        }
        catch (MsAuth$AuthException msAuth$AuthException) {
            Accounts.setError(msAuth$AuthException.getMessage());
        }
        catch (Exception exception) {
            Accounts.setError(a[8] + exception.getClass().getSimpleName());
        }
        finally {
            working = false;
        }
    }

    
    static {
        block23: {
            block22: {
                block21: {
                    block20: {
                        var13 = new String[15];
                        var11_1 = 0;
                        var10_2 = "+hR\u00030gJ\u0004'H\bbeG\u0013'Z\nqzL52n\\\u00030zLAaI\u0002}3V\tb\u001b\fspM\u0014iOMcdK\u0015dS\bb3M\u000f'O\u0005u3V\bsW\b0`A\u0013b^\u0003\u000b2n\\\u00030zLAfHM\u000f rI\u0002br\u000f dX\u0002e}V\u0012\u001b\"hN\u0001t3L\u000es\u001b\u001furA\t'v\u0004saM\u0012h]\u0019*3\u001a2n\\\u0003=zLAaZ\u0004|vFO'o\u001fi3C\u0006fR\u0003>;2n\\\u00030zLAf\\\fy}\u0002\u0007uT\u00000gJ\u0004'Z\u000es|W\u000fs\u001b\u001egzV\u0002o^\u001f0|LAsS\b0gK\u0015k^McpP\u0004bU\u00125oZ\u00190pM\u0005b\u001b\bhcK\u0013b_C\u00102n\\\u0003=zLAaZ\u0004|vF['\u000e2n\\\u0003y}EAnUMq`\u0002\u00148hN\u001f0`G\u0012tR\u0002~3P\u0000i\u001b\u0002eg\u000f2n\\\u0003uw\u0002\bi\u001b\fwrK\u000f\u001b wK\u001f\u007feGAnOMy}\u0002\u0018hN\u001f0qP\u000epH\bb=";
                        var12_3 = "+hR\u00030gJ\u0004'H\bbeG\u0013'Z\nqzL52n\\\u00030zLAaI\u0002}3V\tb\u001b\fspM\u0014iOMcdK\u0015dS\bb3M\u000f'O\u0005u3V\bsW\b0`A\u0013b^\u0003\u000b2n\\\u00030zLAfHM\u000f rI\u0002br\u000f dX\u0002e}V\u0012\u001b\"hN\u0001t3L\u000es\u001b\u001furA\t'v\u0004saM\u0012h]\u0019*3\u001a2n\\\u0003=zLAaZ\u0004|vFO'o\u001fi3C\u0006fR\u0003>;2n\\\u00030zLAf\\\fy}\u0002\u0007uT\u00000gJ\u0004'Z\u000es|W\u000fs\u001b\u001egzV\u0002o^\u001f0|LAsS\b0gK\u0015k^McpP\u0004bU\u00125oZ\u00190pM\u0005b\u001b\bhcK\u0013b_C\u00102n\\\u0003=zLAaZ\u0004|vF['\u000e2n\\\u0003y}EAnUMq`\u0002\u00148hN\u001f0`G\u0012tR\u0002~3P\u0000i\u001b\u0002eg\u000f2n\\\u0003uw\u0002\bi\u001b\fwrK\u000f\u001b wK\u001f\u007feGAnOMy}\u0002\u0018hN\u001f0qP\u000epH\bb=".length();
                        var9_4 = 21;
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
                            var10_2 = "\"hN\u0001t3L\u000es\u001b\u001eytLAnUMq`\u0002\u0016\"hN\u0001t}\u0005\u0015'H\u0004w}\u0002\bi\u001b\fwrK\u000f";
                            var12_3 = "\"hN\u0001t3L\u000es\u001b\u001eytLAnUMq`\u0002\u0016\"hN\u0001t}\u0005\u0015'H\u0004w}\u0002\bi\u001b\fwrK\u000f".length();
                            var9_4 = 21;
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
                                v10 = 97;
                                break;
                            }
                            case 1: {
                                v10 = 7;
                                break;
                            }
                            case 2: {
                                v10 = 59;
                                break;
                            }
                            case 3: {
                                v10 = 109;
                                break;
                            }
                            case 4: {
                                v10 = 16;
                                break;
                            }
                            case 5: {
                                v10 = 19;
                                break;
                            }
                            default: {
                                v10 = 34;
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
                AccountFlow.a = var13;
                var0_7 = 3211845182867261760L;
                var6_8 = new long[2];
                var3_9 = 0;
                var4_10 = "!\u00818\u008b\u00d1\u00d3\u00d0\t[\u00d6\u0017\u00b6!-zK";
                var5_11 = "!\u00818\u008b\u00d1\u00d3\u00d0\t[\u00d6\u0017\u00b6!-zK".length();
                var2_12 = 0;
                while (true) {
                    break block22;
                    break;
                }
lbl84:

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
        AccountFlow.b = var6_8;
        AccountFlow.c = new Long[2];
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x3BF3) & Short.MAX_VALUE;
        if (c[n2] == null) {
            AccountFlow.c[n2] = b[n2] ^ l;
        }
        return c[n2];
    }
}
