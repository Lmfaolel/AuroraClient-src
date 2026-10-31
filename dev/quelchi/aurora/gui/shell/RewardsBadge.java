package dev.quelchi.aurora.gui.shell;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.quelchi.aurora.cosmetics.backend.CosmeticsSync;
import dev.quelchi.aurora.gui.SocialToast;
import dev.quelchi.aurora.gui.shell.SearchIndex;
import net.minecraft.client.MinecraftClient;

public final class RewardsBadge {
    private static final long EVERY_MS;
    private static volatile int ready;
    private static long fetchedAt;
    private static boolean busy;
    private static boolean told;
    private static final String[] a;
    private static final long[] b;
    private static final Long[] c;

    private RewardsBadge() {
    }

    public static int ready() {
        return ready;
    }

    public static void tick() {
        long l;
        block28: {
            block29: {
                block27: {
                    int n;
                    boolean bl;
                    block22: {
                        block23: {
                            String[] stringArray;
                            Object object;
                            block26: {
                                int n2;
                                block24: {
                                    block25: {
                                        MinecraftClient minecraftClient = MinecraftClient.method_1551();
                                        bl = SearchIndex.a;
                                        n = told;
                                        if (bl) break block22;
                                        if (n != 0) break block23;
                                        n = ready;
                                        if (bl) break block22;
                                        if (n <= 0) break block23;
                                        if (minecraftClient.field_1687 == null) break block23;
                                        n2 = 1;
                                        if (bl) break block24;
                                        told = n2;
                                        if (ready != 1) break block25;
                                        object = a[3];
                                        break block26;
                                    }
                                    n2 = ready;
                                }
                                stringArray = a;
                                object = n2 + stringArray[0];
                            }
                            stringArray = a;
                            SocialToast.system((String)object, stringArray[2]);
                        }
                        n = busy;
                    }
                    if (n != 0 || CosmeticsSync.token() == null) break block27;
                    l = System.currentTimeMillis() - fetchedAt;
                    if (bl) break block28;
                    if (l >= RewardsBadge.a(21512, 6242683587855511848L)) break block29;
                }
                return;
            }
            l = System.currentTimeMillis();
        }
        fetchedAt = l;
        busy = true;
        CosmeticsSync.fetch(a[4], RewardsBadge::lambda$tick$0);
    }

    static void seen(JsonObject jsonObject) {
        block25: {
            int n;
            block23: {
                int n2;
                block24: {
                    String[] stringArray;
                    String string;
                    JsonObject jsonObject2;
                    boolean bl;
                    block22: {
                        block20: {
                            block21: {
                                n2 = 0;
                                bl = SearchIndex.a;
                                jsonObject2 = jsonObject;
                                if (bl) break block20;
                                if (!jsonObject2.has(a[6])) break block21;
                                jsonObject2 = jsonObject;
                                string = a[9];
                                if (bl) break block22;
                                if (jsonObject2.getAsJsonObject(string).get(a[1]).getAsBoolean()) break block21;
                                ++n2;
                            }
                            jsonObject2 = jsonObject;
                        }
                        stringArray = a;
                        string = stringArray[8];
                    }
                    n = jsonObject2.has(string);
                    if (bl) break block23;
                    if (n == 0) break block24;
                    stringArray = a;
                    for (JsonElement jsonElement : jsonObject.getAsJsonArray(stringArray[10])) {
                        block27: {
                            JsonObject jsonObject3;
                            JsonObject jsonObject4;
                            block26: {
                                jsonObject4 = jsonElement.getAsJsonObject();
                                if (bl) break block25;
                                jsonObject3 = jsonObject4;
                                if (bl) break block26;
                                if (jsonObject3.get(a[5]).getAsBoolean()) break block27;
                                jsonObject3 = jsonObject4.get(a[11]);
                            }
                            if (jsonObject3.getAsLong() >= jsonObject4.get(a[7]).getAsLong()) {
                                ++n2;
                            }
                        }
                        if (!bl) continue;
                    }
                }
                n = n2;
            }
            ready = n;
        }
    }

    private static void lambda$tick$0(JsonObject jsonObject) {
        block3: {
            JsonObject jsonObject2;
            block2: {
                busy = false;
                boolean bl = SearchIndex.a;
                jsonObject2 = jsonObject;
                if (bl) break block2;
                if (jsonObject2 == null) break block3;
                jsonObject2 = jsonObject;
            }
            RewardsBadge.seen(jsonObject2);
        }
    }

    
    static {
        block23: {
            block22: {
                block21: {
                    block20: {
                        var13 = new String[12];
                        var11_1 = 0;
                        var10_2 = "Ah\u000f\u0012]10\u0012:\u0018\u0000]'-\f\u0002v\u000b\fQ&05u\u000e\u0004E\u001a\"v\u000b\fQc=\u000f:=\u0004N'&\u000ex\u000fI\u001c\u00111\u0016{\u0018\u0001O\u0011 :\u0018\u0000K\"&\u0005:\u0003\u0016\u001c11\u0000~\u0013\fN{\u001a\f\u001311\u0016{\u0018\u0001O\u0007\u0002v\u000b\fQ&0\u0006\u0012n\u0018\u0000](\u0004\u000f\u007f\u000f\u0001\n\fs\u0006\u0000O7;\u000f\u007f\u0019\u0006\u0012n\u0018\u0000](";
                        var12_3 = "Ah\u000f\u0012]10\u0012:\u0018\u0000]'-\f\u0002v\u000b\fQ&05u\u000e\u0004E\u001a\"v\u000b\fQc=\u000f:=\u0004N'&\u000ex\u000fI\u001c\u00111\u0016{\u0018\u0001O\u0011 :\u0018\u0000K\"&\u0005:\u0003\u0016\u001c11\u0000~\u0013\fN{\u001a\f\u001311\u0016{\u0018\u0001O\u0007\u0002v\u000b\fQ&0\u0006\u0012n\u0018\u0000](\u0004\u000f\u007f\u000f\u0001\n\fs\u0006\u0000O7;\u000f\u007f\u0019\u0006\u0012n\u0018\u0000](".length();
                        var9_4 = 14;
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
                            var10_2 = "\fs\u0006\u0000O7;\u000f\u007f\u0019\u0004\t{\u001c\u0000";
                            var12_3 = "\fs\u0006\u0000O7;\u000f\u007f\u0019\u0004\t{\u001c\u0000".length();
                            var9_4 = 10;
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
                                v10 = 26;
                                break;
                            }
                            case 2: {
                                v10 = 106;
                                break;
                            }
                            case 3: {
                                v10 = 101;
                                break;
                            }
                            case 4: {
                                v10 = 60;
                                break;
                            }
                            case 5: {
                                v10 = 67;
                                break;
                            }
                            default: {
                                v10 = 84;
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
                RewardsBadge.a = var13;
                var0_7 = 4719393406292537683L;
                var6_8 = new long[2];
                var3_9 = 0;
                var4_10 = "\u0017\u00dc\u00d0\u00e9\u0092.S\u009bdx\u008a16\u0092\u0010\u00b7";
                var5_11 = "\u0017\u00dc\u00d0\u00e9\u0092.S\u009bdx\u008a16\u0092\u0010\u00b7".length();
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
        RewardsBadge.b = var6_8;
        RewardsBadge.c = new Long[2];
        RewardsBadge.EVERY_MS = RewardsBadge.a(5925, 2667869845365043716L);
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x2520) & Short.MAX_VALUE;
        if (c[n2] == null) {
            RewardsBadge.c[n2] = b[n2] ^ l;
        }
        return c[n2];
    }
}
