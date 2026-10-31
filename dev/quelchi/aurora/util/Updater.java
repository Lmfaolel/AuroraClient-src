package dev.quelchi.aurora.util;

import com.google.gson.JsonParser;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.cosmetics.backend.CosmeticsSync;
import dev.quelchi.aurora.util.BuildInfo;
import dev.quelchi.aurora.util.Daemon;
import dev.quelchi.aurora.util.KeyUtil;
import dev.quelchi.aurora.util.LoaderPayload;
import dev.quelchi.aurora.util.MachineId;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.time.Duration;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

public final class Updater {
    private static final HttpClient HTTP;
    public static volatile String ready;
    private static volatile boolean checked;
    public static volatile boolean promptShown;
    private static int waited;
    private static final int RECHECK_TICKS;
    private static int sinceCheck;
    private static volatile boolean checking;
    private static volatile String stagedSha;
    private static volatile String selfSha;
    private static final String[] a;
    private static final long[] b;
    private static final Integer[] c;
    private static final long[] d;
    private static final Long[] e;

    private Updater() {
    }

    public static void checkOnce() {
        block30: {
            int n;
            block31: {
                int n2;
                block29: {
                    int n3;
                    block26: {
                        block28: {
                            block27: {
                                block24: {
                                    block25: {
                                        n2 = KeyUtil.a;
                                        n3 = checked;
                                        if (n2 != 0) break block24;
                                        if (n3 != 0) break block25;
                                        if (CosmeticsSync.token() != null) break block25;
                                        n3 = waited;
                                        if (n2 != 0) break block24;
                                        waited = n3 + 1;
                                        if (waited >= 600) break block25;
                                        return;
                                    }
                                    n3 = checked;
                                }
                                if (n2 != 0) break block26;
                                if (n3 == 0) break block27;
                                n3 = sinceCheck + 1;
                                if (n2 != 0) break block26;
                                sinceCheck = n3;
                                if (n3 < 600) break block28;
                            }
                            n3 = 1;
                            break block26;
                        }
                        n3 = 0;
                    }
                    int n4 = n3;
                    n = n4;
                    if (n2 != 0) break block29;
                    if (n == 0) break block30;
                    n = checking ? 1 : 0;
                }
                if (n2 != 0) break block31;
                if (n != 0) break block30;
                checked = true;
                checking = true;
                n = 0;
            }
            sinceCheck = n;
            Daemon.run(a[11], Updater::lambda$checkOnce$0);
        }
    }

    
    private static void check() {
        var0 = KeyUtil.a;
        try {
            block148: {
                block147: {
                    block169: {
                        block145: {
                            block146: {
                                block144: {
                                    block168: {
                                        block167: {
                                            block166: {
                                                block162: {
                                                    block143: {
                                                        block141: {
                                                            block142: {
                                                                block165: {
                                                                    block164: {
                                                                        block163: {
                                                                            block161: {
                                                                                block140: {
                                                                                    block139: {
                                                                                        block137: {
                                                                                            block133: {
                                                                                                block138: {
                                                                                                    block134: {
                                                                                                        block135: {
                                                                                                            block136: {
                                                                                                                block160: {
                                                                                                                    block159: {
                                                                                                                        block127: {
                                                                                                                            block128: {
                                                                                                                                block130: {
                                                                                                                                    block132: {
                                                                                                                                        block131: {
                                                                                                                                            block129: {
                                                                                                                                                block158: {
                                                                                                                                                    block157: {
                                                                                                                                                        block125: {
                                                                                                                                                            block126: {
                                                                                                                                                                block124: {
                                                                                                                                                                    block123: {
                                                                                                                                                                        block156: {
                                                                                                                                                                            block121: {
                                                                                                                                                                                block122: {
                                                                                                                                                                                    block120: {
                                                                                                                                                                                        block119: {
                                                                                                                                                                                            block118: {
                                                                                                                                                                                                block155: {
                                                                                                                                                                                                    block154: {
                                                                                                                                                                                                        block153: {
                                                                                                                                                                                                            block117: {
                                                                                                                                                                                                                block116: {
                                                                                                                                                                                                                    block114: {
                                                                                                                                                                                                                        block115: {
                                                                                                                                                                                                                            block112: {
                                                                                                                                                                                                                                block113: {
                                                                                                                                                                                                                                    block110: {
                                                                                                                                                                                                                                        block111: {
                                                                                                                                                                                                                                            block107: {
                                                                                                                                                                                                                                                block109: {
                                                                                                                                                                                                                                                    block108: {
                                                                                                                                                                                                                                                        block152: {
                                                                                                                                                                                                                                                            block106: {
                                                                                                                                                                                                                                                                block151: {
                                                                                                                                                                                                                                                                    block102: {
                                                                                                                                                                                                                                                                        block103: {
                                                                                                                                                                                                                                                                            block105: {
                                                                                                                                                                                                                                                                                block104: {
                                                                                                                                                                                                                                                                                    block150: {
                                                                                                                                                                                                                                                                                        block149: {
                                                                                                                                                                                                                                                                                            var1_1 = LoaderPayload.inMemory();
                                                                                                                                                                                                                                                                                            v0 = var1_1 != false ? null : Updater.ownJar();
                                                                                                                                                                                                                                                                                            var2_3 = v0;
                                                                                                                                                                                                                                                                                            v2 = var1_1;
                                                                                                                                                                                                                                                                                            if (var0 != 0) break block102;
                                                                                                                                                                                                                                                                                            if (v2) break block103;
                                                                                                                                                                                                                                                                                            break block149;
                                                                                                                                                                                                                                                                                            catch (Exception v3) {
                                                                                                                                                                                                                                                                                                throw v3;
                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                        v4 = var2_3;
                                                                                                                                                                                                                                                                                        if (var0 != 0) break block104;
                                                                                                                                                                                                                                                                                        break block150;
                                                                                                                                                                                                                                                                                        catch (Exception v5) {
                                                                                                                                                                                                                                                                                            throw v5;
                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                    if (v4 == null) break block105;
                                                                                                                                                                                                                                                                                    v4 = var2_3;
                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                v2 = Files.isRegularFile(v4, new LinkOption[0]);
                                                                                                                                                                                                                                                                                if (var0 != 0) break block102;
                                                                                                                                                                                                                                                                                if (v2) break block103;
                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                            return;
                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                        v2 = var1_1;
                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                    if (var0 != 0) break block106;
                                                                                                                                                                                                                                                                    if (v2) break block107;
                                                                                                                                                                                                                                                                    break block151;
                                                                                                                                                                                                                                                                    catch (Exception v8) {
                                                                                                                                                                                                                                                                        throw v8;
                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                v2 = Boolean.getBoolean(Updater.a[34]);
                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                            if (var0 != 0) break block108;
                                                                                                                                                                                                                                                            if (v2) break block109;
                                                                                                                                                                                                                                                            break block152;
                                                                                                                                                                                                                                                            catch (Exception v10) {
                                                                                                                                                                                                                                                                throw v10;
                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                        v2 = Files.exists(var2_3.resolveSibling(Updater.a[19]), new LinkOption[0]);
                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                    if (!v2) break block107;
                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                return;
                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                            var3_4 = CosmeticsSync.baseUrl();
                                                                                                                                                                                                                                            v12 = var3_4;
                                                                                                                                                                                                                                            if (var0 != 0) break block110;
                                                                                                                                                                                                                                            if (!v12.isEmpty()) break block111;
                                                                                                                                                                                                                                            return;
                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                        v12 = CosmeticsSync.token();
                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                    v14 = v12 != null;
                                                                                                                                                                                                                                    var4_5 = v14;
                                                                                                                                                                                                                                    var15_6 = Updater.a;
                                                                                                                                                                                                                                    var5_7 = Updater.HTTP.send(CosmeticsSync.bearer(HttpRequest.newBuilder().uri(URI.create(var3_4 + var15_6[5] + Updater.auth())).timeout(Duration.ofSeconds(Updater.b(14392, 5148255070059670661L))).GET()).build(), HttpResponse.BodyHandlers.ofString());
                                                                                                                                                                                                                                    v16 = var5_7;
                                                                                                                                                                                                                                    if (var0 != 0) break block112;
                                                                                                                                                                                                                                    if (v16.statusCode() / 100 == 2) break block113;
                                                                                                                                                                                                                                    return;
                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                v16 = var5_7.body();
                                                                                                                                                                                                                            }
                                                                                                                                                                                                                            var6_8 = JsonParser.parseString((String)((String)v16)).getAsJsonObject();
                                                                                                                                                                                                                            v18 = var6_8;
                                                                                                                                                                                                                            v19 = Updater.a[20];
                                                                                                                                                                                                                            if (var0 != 0) break block114;
                                                                                                                                                                                                                            if (v18.has(v19)) break block115;
                                                                                                                                                                                                                            return;
                                                                                                                                                                                                                        }
                                                                                                                                                                                                                        v18 = var6_8;
                                                                                                                                                                                                                        if (var0 != 0) break block116;
                                                                                                                                                                                                                        v19 = Updater.a[32];
                                                                                                                                                                                                                    }
                                                                                                                                                                                                                    if (!v18.has(v19)) break block117;
                                                                                                                                                                                                                    v18 = var6_8.get(Updater.a[3]);
                                                                                                                                                                                                                }
                                                                                                                                                                                                                v23 = v18.getAsString();
                                                                                                                                                                                                                break block153;
                                                                                                                                                                                                            }
                                                                                                                                                                                                            v23 = "";
                                                                                                                                                                                                        }
                                                                                                                                                                                                        var7_9 = v23;
                                                                                                                                                                                                        v24 = var4_5;
                                                                                                                                                                                                        if (var0 != 0) break block118;
                                                                                                                                                                                                        if (!v24) break block119;
                                                                                                                                                                                                        break block154;
                                                                                                                                                                                                        catch (Exception v25) {
                                                                                                                                                                                                            throw v25;
                                                                                                                                                                                                        }
                                                                                                                                                                                                    }
                                                                                                                                                                                                    v26 = var7_9;
                                                                                                                                                                                                    if (var0 != 0) break block120;
                                                                                                                                                                                                    break block155;
                                                                                                                                                                                                    catch (Exception v27) {
                                                                                                                                                                                                        throw v27;
                                                                                                                                                                                                    }
                                                                                                                                                                                                }
                                                                                                                                                                                                v24 = v26.isEmpty();
                                                                                                                                                                                            }
                                                                                                                                                                                            if (!v24) {
                                                                                                                                                                                                Updater.rememberChannel(var7_9);
                                                                                                                                                                                            }
                                                                                                                                                                                        }
                                                                                                                                                                                        var15_6 = Updater.a;
                                                                                                                                                                                        v26 = var6_8.get(var15_6[23]).getAsString();
                                                                                                                                                                                    }
                                                                                                                                                                                    var8_10 = v26;
                                                                                                                                                                                    v30 = var1_1 != false ? LoaderPayload.sha256() : Updater.sha256(var2_3);
                                                                                                                                                                                    var9_11 = v30;
                                                                                                                                                                                    v32 = var9_11;
                                                                                                                                                                                    if (var0 != 0) break block121;
                                                                                                                                                                                    if (v32 != null) break block122;
                                                                                                                                                                                    return;
                                                                                                                                                                                }
                                                                                                                                                                                v32 = var8_10;
                                                                                                                                                                            }
                                                                                                                                                                            if (var0 != 0) break block123;
                                                                                                                                                                            if (v32.isEmpty()) break block124;
                                                                                                                                                                            break block156;
                                                                                                                                                                            catch (Exception v34) {
                                                                                                                                                                                throw v34;
                                                                                                                                                                            }
                                                                                                                                                                        }
                                                                                                                                                                        v32 = var8_10;
                                                                                                                                                                    }
                                                                                                                                                                    v36 = Updater.stagedSha;
                                                                                                                                                                    if (var0 != 0) break block125;
                                                                                                                                                                    if (!v32.equals(v36)) break block126;
                                                                                                                                                                }
                                                                                                                                                                return;
                                                                                                                                                            }
                                                                                                                                                            v32 = var8_10;
                                                                                                                                                            v36 = var9_11;
                                                                                                                                                        }
                                                                                                                                                        v38 = v32.equals(v36);
                                                                                                                                                        if (var0 != 0) break block127;
                                                                                                                                                        if (!v38) break block128;
                                                                                                                                                        break block157;
                                                                                                                                                        catch (Exception v39) {
                                                                                                                                                            throw v39;
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                    v40 = Updater.stagedSha.isEmpty();
                                                                                                                                                    if (var0 != 0) break block129;
                                                                                                                                                    break block158;
                                                                                                                                                    catch (Exception v41) {
                                                                                                                                                        throw v41;
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                                if (v40) break block130;
                                                                                                                                                v40 = var1_1;
                                                                                                                                            }
                                                                                                                                            if (!v40) break block131;
                                                                                                                                            LoaderPayload.unstage();
                                                                                                                                            if (var0 == 0) break block132;
                                                                                                                                        }
                                                                                                                                        var15_6 = Updater.a;
                                                                                                                                        var10_12 = var2_3.resolveSibling(String.valueOf(var2_3.getFileName()) + var15_6[21]);
                                                                                                                                        Files.deleteIfExists(var10_12);
                                                                                                                                        Files.deleteIfExists(var10_12.resolveSibling(String.valueOf(var10_12.getFileName()) + var15_6[22]));
                                                                                                                                    }
                                                                                                                                    Updater.stagedSha = "";
                                                                                                                                    Updater.ready = "";
                                                                                                                                }
                                                                                                                                return;
                                                                                                                            }
                                                                                                                            v44 = var6_8;
                                                                                                                            v45 = Updater.a[27];
                                                                                                                            if (var0 != 0) break block133;
                                                                                                                            v38 = v44.has(v45);
                                                                                                                        }
                                                                                                                        if (!v38) break block138;
                                                                                                                        var15_6 = Updater.a;
                                                                                                                        var10_13 = var6_8.get(var15_6[31]).getAsLong();
                                                                                                                        var12_15 = BuildInfo.builtMillis() / Updater.b(23814, 1011460824704829885L);
                                                                                                                        v47 = var12_15;
                                                                                                                        if (var0 != 0) break block134;
                                                                                                                        if (v47 != 0L) break block135;
                                                                                                                        break block159;
                                                                                                                        catch (Exception v48) {
                                                                                                                            throw v48;
                                                                                                                        }
                                                                                                                    }
                                                                                                                    v49 = var2_3;
                                                                                                                    if (var0 != 0) break block136;
                                                                                                                    break block160;
                                                                                                                    catch (Exception v50) {
                                                                                                                        throw v50;
                                                                                                                    }
                                                                                                                }
                                                                                                                if (v49 == null) break block135;
                                                                                                                v49 = var2_3;
                                                                                                            }
                                                                                                            var12_15 = Files.getLastModifiedTime(v49, new LinkOption[0]).toMillis() / Updater.b(31904, 8216119231158642716L);
                                                                                                        }
                                                                                                        v47 = var10_13 + Updater.b(21853, 7490042790863225314L);
                                                                                                    }
                                                                                                    cfr_temp_0 = v47 - var12_15;
                                                                                                    v52 = cfr_temp_0 == 0L ? 0 : (cfr_temp_0 < 0L ? -1 : 1);
                                                                                                    if (var0 != 0) break block137;
                                                                                                    if (v52 >= 0) break block138;
                                                                                                    var15_6 = Updater.a;
                                                                                                    Aurora.LOG.info(var15_6[9]);
                                                                                                    return;
                                                                                                }
                                                                                                v44 = var6_8;
                                                                                                if (var0 != 0) break block139;
                                                                                                v45 = Updater.a[10];
                                                                                            }
                                                                                            v52 = v44.has(v45);
                                                                                        }
                                                                                        if (v52 == 0) break block140;
                                                                                        v44 = var6_8.get(Updater.a[25]);
                                                                                    }
                                                                                    v56 = v44.getAsString();
                                                                                    break block161;
                                                                                }
                                                                                v56 = var10_14 = "";
                                                                            }
                                                                            if (!var1_1) break block162;
                                                                            var15_6 = Updater.a;
                                                                            var11_17 = Updater.HTTP.send(CosmeticsSync.bearer(HttpRequest.newBuilder().uri(URI.create(var3_4 + var15_6[26] + Updater.auth())).timeout(Duration.ofSeconds(Updater.b(14845, 1173729462300552517L))).GET()).build(), HttpResponse.BodyHandlers.ofByteArray());
                                                                            if (var0 != 0) break block141;
                                                                            if (var11_17.statusCode() / 100 != 2) // goto
                                                                            break block163;
                                                                            catch (Exception v57) {
                                                                                throw v57;
                                                                            }
                                                                        }
                                                                        v58 = var8_10;
                                                                        if (var0 != 0) break block142;
                                                                        break block164;
                                                                        catch (Exception v59) {
                                                                            throw v59;
                                                                        }
                                                                    }
                                                                    if (!v58.equals(Updater.sha256(var11_17.body()))) // goto
                                                                    break block165;
                                                                    catch (Exception v60) {
                                                                        throw v60;
                                                                    }
                                                                }
                                                                v58 = var11_17.body();
                                                            }
                                                            if (LoaderPayload.stage((byte[])v58, var10_14)) break block143;
lbl355:

                                                            Aurora.LOG.warn(Updater.a[35]);
                                                        }
                                                        return;
                                                    }
                                                    Updater.staged(var8_10, var7_9, var9_11);
                                                    return;
                                                }
                                                var15_6 = Updater.a;
                                                var11_18 = var2_3.resolveSibling(String.valueOf(var2_3.getFileName()) + var15_6[12]);
                                                var12_16 = var2_3.resolveSibling(String.valueOf(var2_3.getFileName()) + var15_6[8]);
                                                var13_19 = var12_16.resolveSibling(String.valueOf(var12_16.getFileName()) + var15_6[14]);
                                                Files.deleteIfExists(var11_18);
                                                var14_20 = Updater.HTTP.send(CosmeticsSync.bearer(HttpRequest.newBuilder().uri(URI.create(var3_4 + var15_6[6] + Updater.auth())).timeout(Duration.ofSeconds(Updater.b(19573, 3670713852721060043L))).GET()).build(), HttpResponse.BodyHandlers.ofFile(var11_18));
                                                v63 = var14_20.statusCode();
                                                if (var0 != 0) break block144;
                                                if (v63 / 100 != 2) // goto
                                                break block166;
                                                catch (Exception v64) {
                                                    throw v64;
                                                }
                                            }
                                            v65 = var8_10;
                                            if (var0 != 0) break block145;
                                            break block167;
                                            catch (Exception v66) {
                                                throw v66;
                                            }
                                        }
                                        if (v65.equals(Updater.sha256(var11_18))) break block146;
                                        break block168;
                                        catch (Exception v67) {
                                            throw v67;
                                        }
                                    }
                                    v63 = (int)Files.deleteIfExists(var11_18);
                                }
                                var15_6 = Updater.a;
                                Aurora.LOG.warn(var15_6[33]);
                                return;
                            }
                            v65 = var10_14;
                        }
                        v69 = v65.isEmpty();
                        if (var0 != 0) break block147;
                        if (!v69) // goto
                        break block169;
                        catch (Exception v70) {
                            throw v70;
                        }
                    }
                    v69 = Files.deleteIfExists(var13_19);
                }
                if (var0 == 0) break block148;
lbl421:

                Files.writeString(var13_19, (CharSequence)var10_14, new OpenOption[0]);
            }
            Files.move(var11_18, var12_16, new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});
            Updater.staged(var8_10, var7_9, var9_11);
        }
        catch (Exception var1_2) {
            Aurora.LOG.debug(Updater.a[18], (Throwable)var1_2);
        }
    }

    private static void staged(String string, String string2, String string3) {
        String string4;
        block4: {
            block5: {
                int n = KeyUtil.a;
                string4 = string;
                if (n != 0) break block4;
                stagedSha = string4;
                if (!a[15].equals(string2)) break block5;
                string4 = a[16];
                break block4;
            }
            String[] stringArray = a;
            string4 = stringArray[13];
        }
        ready = string4;
        Aurora.LOG.info(a[7], (Object)string3.substring(0, 8), (Object)string.substring(0, 8));
    }

    private static String sha256(byte[] byArray) throws Exception {
        StringBuilder stringBuilder;
        block4: {
            StringBuilder stringBuilder2 = new StringBuilder();
            String[] stringArray = a;
            byte[] byArray2 = MessageDigest.getInstance(stringArray[2]).digest(byArray);
            int n = byArray2.length;
            int n2 = KeyUtil.a;
            for (int i = 0; i < n; ++i) {
                byte by = byArray2[i];
                stringBuilder = stringBuilder2;
                if (n2 == 0) {
                    stringBuilder.append(String.format(a[0], by));
                    if (n2 == 0) continue;
                    break;
                }
                break block4;
            }
            stringBuilder = stringBuilder2;
        }
        return stringBuilder.toString();
    }

    public static Path channelFile() {
        return FabricLoader.getInstance().getConfigDir().resolve(a[28]);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void rememberChannel(String string) {
        String string2;
        block7: {
            int n = KeyUtil.a;
            string2 = string;
            if (n != 0) break block7;
            if (string2 == null) return;
            string2 = string;
        }
        if (string2.isEmpty()) {
            return;
        }
        try {
            Files.writeString(Updater.channelFile(), (CharSequence)string.trim(), new OpenOption[0]);
            return;
        }
        catch (Exception exception) {
            Aurora.LOG.debug(a[17], (Throwable)exception);
        }
    }

    private static String auth() {
        Object object;
        block4: {
            block5: {
                String string = CosmeticsSync.token();
                int n = KeyUtil.a;
                object = string;
                if (n != 0) break block4;
                if (object != null) break block5;
                return "";
            }
            String[] stringArray = a;
            object = stringArray[30] + MachineId.get() + stringArray[24];
        }
        return object;
    }

    private static Path ownJar() {
        return FabricLoader.getInstance().getModContainer(a[1]).map(Updater::lambda$ownJar$1).orElse(null);
    }

    
    public static String selfSha() {
        block25: {
            block18: {
                block17: {
                    var1 = Updater.selfSha;
                    var0_1 = KeyUtil.a;
                    v0 = var1;
                    if (var0_1 != 0) break block17;
                    if (v0 == null) break block18;
                    v0 = var1;
                }
                return v0;
            }
            try {
                block24: {
                    block20: {
                        block28: {
                            block22: {
                                block23: {
                                    block27: {
                                        block21: {
                                            block26: {
                                                block19: {
                                                    var2_2 = Updater.ownJar();
                                                    if (var0_1 != 0) break block19;
                                                    if (LoaderPayload.inMemory()) {
                                                    }
                                                    var1 = LoaderPayload.sha256();
                                                }
                                                if (var0_1 == 0) break block20;
lbl28:

                                                v3 = var2_2;
                                                if (var0_1 != 0) break block21;
                                                break block26;
                                                catch (Exception v4) {
                                                    throw v4;
                                                }
                                            }
                                            if (v3 == null) break block22;
                                            v3 = var2_2;
                                        }
                                        if (var0_1 != 0) break block23;
                                        if (!Files.isRegularFile(v3, new LinkOption[0])) break block22;
                                        break block27;
                                        catch (Exception v6) {
                                            throw v6;
                                        }
                                    }
                                    v3 = var2_2;
                                }
                                v8 = Updater.sha256(v3);
                                break block28;
                            }
                            v8 = "";
                        }
                        var1 = v8;
                    }
                    v9 = var1;
                    if (var0_1 != 0) break block24;
                    if (v9 != null) break block25;
                    v9 = "";
                }
                var1 = v9;
            }
            catch (Exception var2_3) {
                var1 = "";
            }
        }
        Updater.selfSha = var1;
        return var1;
    }

    
    private static String sha256(Path path) throws Exception {
        Object object;
        int n;
        Object object2;
        MessageDigest messageDigest;
        block15: {
            InputStream inputStream;
            block16: {
                String[] stringArray = a;
                messageDigest = MessageDigest.getInstance(stringArray[4]);
                object2 = Files.newInputStream(path, new OpenOption[0]);
                n = KeyUtil.a;
                try {
                    int n2;
                    byte[] byArray = new byte[Updater.a(11843, 1705631808935675754L)];
                    while ((n2 = ((InputStream)object2).read(byArray)) > 0) {
                        messageDigest.update(byArray, 0, n2);
                        if (n == 0) {
                            if (n == 0) continue;
                        }
                        break block15;
                    }
                    inputStream = object2;
                    if (n != 0) break block16;
                }
                catch (Throwable throwable) {
                    if (n != 0) throw throwable;
                    if (object2 == null) throw throwable;
                    try {
                        ((InputStream)object2).close();
                        throw throwable;
                    }
                    catch (Throwable throwable2) {
                        throwable.addSuppressed(throwable2);
                    }
                    throw throwable;
                }
                if (inputStream == null) break block15;
                inputStream = object2;
            }
            inputStream.close();
        }
        object2 = new StringBuilder();
        for (byte by : messageDigest.digest()) {
            object = object2;
            if (n != 0) return ((StringBuilder)object).toString();
            ((StringBuilder)object).append(String.format(a[29], by));
            if (n == 0) continue;
        }
        object = object2;
        return ((StringBuilder)object).toString();
    }

    private static Path lambda$ownJar$1(ModContainer modContainer) {
        Path path;
        block6: {
            Object object;
            block4: {
                block5: {
                    int n = KeyUtil.a;
                    object = modContainer.getOrigin();
                    if (n != 0) break block4;
                    if (!object.getPaths().isEmpty()) break block5;
                    path = null;
                    break block6;
                }
                object = modContainer.getOrigin().getPaths().get(0);
            }
            path = (Path)object;
        }
        return path;
    }

    private static void lambda$checkOnce$0() {
        try {
            Updater.check();
        }
        finally {
            checking = false;
        }
    }

    
    static {
        block32: {
            block31: {
                block30: {
                    block29: {
                        block28: {
                            block27: {
                                var21 = new String[36];
                                var19_1 = 0;
                                var18_2 = "h\t,\u001e\u0006,Ll\t\u001a+\u0007\u001eq_KZ\u007f\u0006\u0007.Q\u007f\b\u0006/\\\u0007\u001eq_KZ\u007f\u0006\u001bbXn\u000fG)\\$\\p\u0012G:Q4Uq\u0007\feF(Km\u000f\u0007$\u0013bXn\u000fG)\\$\\p\u0012G:Q4Uq\u0007\f\u001e\fLl\t\u001a+\u00108Iz\u0007\u001c/\u0010>M\u007f\u0001\r.\nmBcFEt\u00106D\u0007cJj\u0007\u000f/T<\fLl\t\u001a+\u00108Iz\u0007\u001c/\u0010>Rw\u0016\u0018/Tw\u0019r\t\u000b+\\m[k\u000f\u0004.\u0010$J>\b\r=U?\u0019j\u000e\t$\u00109Q{F\u000b\"Q#W{\n\u0003>Py\u000e,Ll\t\u001a+\u001d8Iz\u0007\u001c/B\u0005cI\u007f\u0014\u001c%\fLl\t\u001a+\u00108Iz\u0007\u001c/\u0010?\\\u007f\u0002\u0011f\u0010?\\m\u0012\t8DmMqF\t:@!@\u0004cJw\u0001\u0006!Xj\u0003\u001b>(\u0019\\m\u0012\r8\u0010/Lw\n\fjQ;Xw\n\t(\\(\u0015>\u0014\r9D,KjF\u001c%\u0010,In\n\u0011!\u000eVk\n\fj^\"M>\u0014\r'U [{\u0014H?@)Xj\u0003H)X,Wp\u0003\u0004\u0013\u0018Iz\u0007\u001c/\u0010.Q{\u0005\u0003jV,Pr\u0003\f\u000e=Xg\n\u0007+TcIw\b\u0006/T\u0006>Q\u007fT]|\u0007cJj\u0007\u000f/T\u0004cJw\u0001\u0006>Q\u007fT]|\nkLn\u0002\t>U?\u0004,\u0003>Py\u0013bXn\u000fG)\\$\\p\u0012G:Q4Uq\u0007\f\u0005/Lw\n\u001c\u0012,Ll\t\u001a+\u001d.Q\u007f\b\u0006/\\cMf\u0012\u0004h\t,\u001e\trT\u007f\u0005\u0000#^(\u0004\u0005/Lw\n\u001c\u0007.Q\u007f\b\u0006/\\*\fLl\t\u001a+\u00108Iz\u0007\u001c/\u0010)Vi\b\u0004%Q)\u0019x\u0007\u0001&U)\u0019h\u0003\u001a#V$Z\u007f\u0012\u0001%^";
                                var20_3 = "h\t,\u001e\u0006,Ll\t\u001a+\u0007\u001eq_KZ\u007f\u0006\u0007.Q\u007f\b\u0006/\\\u0007\u001eq_KZ\u007f\u0006\u001bbXn\u000fG)\\$\\p\u0012G:Q4Uq\u0007\feF(Km\u000f\u0007$\u0013bXn\u000fG)\\$\\p\u0012G:Q4Uq\u0007\f\u001e\fLl\t\u001a+\u00108Iz\u0007\u001c/\u0010>M\u007f\u0001\r.\nmBcFEt\u00106D\u0007cJj\u0007\u000f/T<\fLl\t\u001a+\u00108Iz\u0007\u001c/\u0010>Rw\u0016\u0018/Tw\u0019r\t\u000b+\\m[k\u000f\u0004.\u0010$J>\b\r=U?\u0019j\u000e\t$\u00109Q{F\u000b\"Q#W{\n\u0003>Py\u000e,Ll\t\u001a+\u001d8Iz\u0007\u001c/B\u0005cI\u007f\u0014\u001c%\fLl\t\u001a+\u00108Iz\u0007\u001c/\u0010?\\\u007f\u0002\u0011f\u0010?\\m\u0012\t8DmMqF\t:@!@\u0004cJw\u0001\u0006!Xj\u0003\u001b>(\u0019\\m\u0012\r8\u0010/Lw\n\fjQ;Xw\n\t(\\(\u0015>\u0014\r9D,KjF\u001c%\u0010,In\n\u0011!\u000eVk\n\fj^\"M>\u0014\r'U [{\u0014H?@)Xj\u0003H)X,Wp\u0003\u0004\u0013\u0018Iz\u0007\u001c/\u0010.Q{\u0005\u0003jV,Pr\u0003\f\u000e=Xg\n\u0007+TcIw\b\u0006/T\u0006>Q\u007fT]|\u0007cJj\u0007\u000f/T\u0004cJw\u0001\u0006>Q\u007fT]|\nkLn\u0002\t>U?\u0004,\u0003>Py\u0013bXn\u000fG)\\$\\p\u0012G:Q4Uq\u0007\f\u0005/Lw\n\u001c\u0012,Ll\t\u001a+\u001d.Q\u007f\b\u0006/\\cMf\u0012\u0004h\t,\u001e\trT\u007f\u0005\u0000#^(\u0004\u0005/Lw\n\u001c\u0007.Q\u007f\b\u0006/\\*\fLl\t\u001a+\u00108Iz\u0007\u001c/\u0010)Vi\b\u0004%Q)\u0019x\u0007\u0001&U)\u0019h\u0003\u001a#V$Z\u007f\u0012\u0001%^".length();
                                var17_4 = 4;
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
                                    var18_2 = ",Ll\t\u001a+\u001e!V\u007f\u0002\r8\u001e#Vk\u0016\f+D(*\fLl\t\u001a+\u00108Iz\u0007\u001c/\u0010)Vi\b\u0004%Q)\u0019x\u0007\u0001&U)\u0019h\u0003\u001a#V$Z\u007f\u0012\u0001%^";
                                    var20_3 = ",Ll\t\u001a+\u001e!V\u007f\u0002\r8\u001e#Vk\u0016\f+D(*\fLl\t\u001a+\u00108Iz\u0007\u001c/\u0010)Vi\b\u0004%Q)\u0019x\u0007\u0001&U)\u0019h\u0003\u001a#V$Z\u007f\u0012\u0001%^".length();
                                    var17_4 = 22;
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
                            var22_6 = 0;
                            do {
                                v6 = v6;
                                v8 = var22_6;
                                v9 = v6[v8];
                                switch (var22_6 % 7) {
                                    case 0: {
                                        v10 = 77;
                                        break;
                                    }
                                    case 1: {
                                        v10 = 57;
                                        break;
                                    }
                                    case 2: {
                                        v10 = 30;
                                        break;
                                    }
                                    case 3: {
                                        v10 = 102;
                                        break;
                                    }
                                    case 4: {
                                        v10 = 104;
                                        break;
                                    }
                                    case 5: {
                                        v10 = 74;
                                        break;
                                    }
                                    default: {
                                        v10 = 48;
                                    }
                                }
                                v6[v8] = (char)(v9 ^ v10);
                                ++var22_6;
lbl65:

                                v7 = v7;
                            } while (v7 > var22_6);
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
                        Updater.a = var21;
                        var8_7 = 6254841895934926683L;
                        var14_8 = new long[2];
                        var11_9 = 0;
                        var12_10 = "\u00bb#\u00fdW|XX1\u00ad?\u00e4&6\u008e:1";
                        var13_11 = "\u00bb#\u00fdW|XX1\u00ad?\u00e4&6\u008e:1".length();
                        var10_12 = 0;
                        while (true) {
                            break block29;
                            break;
                        }
lbl84:

                        while (true) {
                            var14_8[v11] = (((long)var15_13[0] & 255L) << 56 | ((long)var15_13[1] & 255L) << 48 | ((long)var15_13[2] & 255L) << 40 | ((long)var15_13[3] & 255L) << 32 | ((long)var15_13[4] & 255L) << 24 | ((long)var15_13[5] & 255L) << 16 | ((long)var15_13[6] & 255L) << 8 | (long)var15_13[7] & 255L) ^ var8_7;
                            if (var10_12 < var13_11) continue;
                            break block30;
                            break;
                        }
                    }
                    var15_13 = var12_10.substring(var10_12, var10_12 += 8).getBytes("ISO-8859-1");
                    v11 = var11_9++;
                    while (true)
                }
                Updater.b = var14_8;
                Updater.c = new Integer[2];
                Updater.RECHECK_TICKS = Updater.a(19994, 5464247178440517426L);
                var0_14 = 1190051011890123625L;
                var6_15 = new long[7];
                var3_16 = 0;
                var4_17 = "\"s\u0017\u00db\u00eb\u00c5K\u00dawr\u0013,\u00e9\u00ae\u00d2\u00b7b\u0086m\u00ff\u00f99x\u009dW\u00f1\u00af\u00a2\u00bcL\u00bf\u00e6v\u00e5\u009b\u00cf~\u008aR\u009b";
                var5_18 = "\"s\u0017\u00db\u00eb\u00c5K\u00dawr\u0013,\u00e9\u00ae\u00d2\u00b7b\u0086m\u00ff\u00f99x\u009dW\u00f1\u00af\u00a2\u00bcL\u00bf\u00e6v\u00e5\u009b\u00cf~\u008aR\u009b".length();
                var2_19 = 0;
                while (true) {
                    var7_20 = var4_17.substring(var2_19, var2_19 += 8).getBytes("ISO-8859-1");
                    v12 = var6_15;
                    v13 = var3_16++;
                    v14 = ((long)var7_20[0] & 255L) << 56 | ((long)var7_20[1] & 255L) << 48 | ((long)var7_20[2] & 255L) << 40 | ((long)var7_20[3] & 255L) << 32 | ((long)var7_20[4] & 255L) << 24 | ((long)var7_20[5] & 255L) << 16 | ((long)var7_20[6] & 255L) << 8 | (long)var7_20[7] & 255L;
                    v15 = -1;
                    break block31;
                    break;
                }
lbl112:

                while (true) {
                    v12[v13] = v16;
                    if (var2_19 < var5_18) continue;
                    var4_17 = "\u001e\u008a\u0087U\u00cc\u008fY<\u0000\u00ca\u0005\u00ad:\u009b\u00beT";
                    var5_18 = "\u001e\u008a\u0087U\u00cc\u008fY<\u0000\u00ca\u0005\u00ad:\u009b\u00beT".length();
                    var2_19 = 0;
                    while (true) {
                        var7_20 = var4_17.substring(var2_19, var2_19 += 8).getBytes("ISO-8859-1");
                        v12 = var6_15;
                        v13 = var3_16++;
                        v14 = ((long)var7_20[0] & 255L) << 56 | ((long)var7_20[1] & 255L) << 48 | ((long)var7_20[2] & 255L) << 40 | ((long)var7_20[3] & 255L) << 32 | ((long)var7_20[4] & 255L) << 24 | ((long)var7_20[5] & 255L) << 16 | ((long)var7_20[6] & 255L) << 8 | (long)var7_20[7] & 255L;
                        v15 = 0;
                        break block31;
                        break;
                    }
                    break;
                }
lbl125:

                while (true) {
                    v12[v13] = v16;
                    if (var2_19 < var5_18) continue;
                    break block32;
                    break;
                }
            }
            v16 = v14 ^ var0_14;
            switch (v15) {
                default: {
                    continue;
                }
                case 0:
lbl136:

                continue;
            }
        }
        Updater.d = var6_15;
        Updater.e = new Long[7];
        Updater.HTTP = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(Updater.b(21826, 7378711313322885624L))).build();
        Updater.ready = "";
        Updater.stagedSha = "";
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6929;
        if (c[n2] == null) {
            Updater.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }

    private static long b(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x14BE) & Short.MAX_VALUE;
        if (e[n2] == null) {
            Updater.e[n2] = d[n2] ^ l;
        }
        return e[n2];
    }
}
