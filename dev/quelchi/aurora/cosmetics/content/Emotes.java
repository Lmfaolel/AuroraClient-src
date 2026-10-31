package dev.quelchi.aurora.cosmetics.content;

import dev.quelchi.aurora.cosmetics.backend.EmoteFile;
import dev.quelchi.aurora.cosmetics.backend.EmoteImport;
import dev.quelchi.aurora.cosmetics.backend.EmoteImport$Clip;
import dev.quelchi.aurora.cosmetics.content.Cosmetics;
import dev.quelchi.aurora.cosmetics.content.Emotes$Emote;
import dev.quelchi.aurora.cosmetics.renderer.EmoteEngine;
import dev.quelchi.aurora.input.FreeLook;
import dev.quelchi.aurora.module.Module;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.option.Perspective;
import net.minecraft.util.math.MathHelper;

public final class Emotes {
    private static final Map<String, Emotes$Emote> BY_ID;
    private static final Map<String, float[][]> REMOTE_POSES;
    private static final Set<String> REMOTE_IDS;
    private static final Map<String, float[][]> LOCAL_POSES;
    private static volatile String editorId;
    private static final Set<String> IMPORTED_IDS;
    private static final Set<String> SERVED_IDS;
    public static final int HEAD = 0;
    public static final int BODY;
    public static final int R_ARM;
    public static final int L_ARM;
    public static final int R_LEG;
    public static final int L_LEG;
    public static final int HIP_BEND;
    public static final int SINK;
    public static final int KNEE;
    public static final int R_ELBOW;
    public static final int L_ELBOW;
    public static final int R_SHIN;
    public static final int L_SHIN;
    public static final int SPINE;
    public static final int OFF_HEAD;
    public static final int OFF_BODY;
    public static final int OFF_R_ARM;
    public static final int OFF_L_ARM;
    public static final int OFF_R_LEG;
    public static final int OFF_L_LEG;
    public static final int SLOTS;
    private static final Map<UUID, long[]> CLOCKS;
    private static final float[] SAMPLE;
    private static Perspective savedView;

    private Emotes() {
    }

    
    public static void registerImported(List<Emotes$Emote> var0) {
        var2_1 = Emotes.IMPORTED_IDS.iterator();
        var1_2 = Cosmetics.a;
        while (var2_1.hasNext()) {
            v0 = var2_1.next();
            if (var1_2 == 0) {
                var3_3 = v0;
                Emotes.BY_ID.remove(var3_3);
                if (var1_2 == 0) continue;
            }
        }
        Emotes.IMPORTED_IDS.clear();
        for (Object v0 : var0) lbl_neg_1000:

        {
            var3_3 = (Emotes$Emote)v0;
            Emotes.BY_ID.put(var3_3.id(), (Emotes$Emote)var3_3);
            Emotes.IMPORTED_IDS.add(var3_3.id());
            if (var1_2 == 0) continue;
        }
    }

    public static void registerServedClips(List<Emotes$Emote> list) {
        int n;
        block5: {
            Iterator<Object> iterator = SERVED_IDS.iterator();
            n = Cosmetics.a;
            while (iterator.hasNext()) {
                String object = iterator.next();
                BY_ID.remove(object);
                if (n == 0) {
                    if (n == 0) continue;
                    break;
                }
                break block5;
            }
            SERVED_IDS.clear();
        }
        for (Emotes$Emote emotes$Emote : list) {
            BY_ID.put(emotes$Emote.id(), emotes$Emote);
            SERVED_IDS.add(emotes$Emote.id());
            if (n == 0) continue;
        }
    }

    public static void setLocalPoses(Map<String, float[][]> map) {
        LOCAL_POSES.clear();
        LOCAL_POSES.putAll(map);
    }

    public static void registerRemote(List<Emotes$Emote> list, Map<String, float[][]> map) {
        Map<String, Object> map2;
        block10: {
            int n;
            block9: {
                Iterator<Object> iterator = REMOTE_IDS.iterator();
                n = Cosmetics.a;
                while (iterator.hasNext()) {
                    String object = iterator.next();
                    if (n != 0) break block9;
                    BY_ID.remove(object);
                    if (n == 0) continue;
                    boolean bl = !Module.a;
                    Module.a = bl;
                    break;
                }
                REMOTE_IDS.clear();
                REMOTE_POSES.clear();
            }
            for (Emotes$Emote emotes$Emote : list) {
                map2 = BY_ID;
                if (n == 0) {
                    map2.put(emotes$Emote.id(), (float[][])emotes$Emote);
                    REMOTE_IDS.add(emotes$Emote.id());
                    if (n == 0) continue;
                    break;
                }
                break block10;
            }
            map2 = REMOTE_POSES;
        }
        map2.putAll(map);
    }

    public static Iterable<Emotes$Emote> all() {
        return BY_ID.values();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String activeId(AbstractClientPlayerEntity abstractClientPlayerEntity) {
        int n;
        String string;
        block15: {
            block16: {
                String string2;
                int n2;
                block14: {
                    block13: {
                        string = Cosmetics.emoteFor(abstractClientPlayerEntity.method_5667());
                        n2 = Cosmetics.a;
                        string2 = string;
                        if (n2 != 0) break block13;
                        if (string2 == null) return null;
                        string2 = string;
                    }
                    if (n2 != 0) break block14;
                    if (string2.isEmpty()) return null;
                    string2 = string;
                }
                n = EmoteEngine.has(string2);
                if (n2 != 0) break block15;
                if (n != 0) break block16;
                return null;
            }
            n = abstractClientPlayerEntity.field_6213;
        }
        if (n > 0) {
            return null;
        }
        String string3 = string;
        return string3;
    }

    public static float secondsIn(UUID uUID) {
        long[] lArray = CLOCKS.get(uUID);
        float f = lArray == null ? 0.0f : (float)(System.nanoTime() - lArray[0]) / 1.0E9f;
        return f;
    }

    public static void pose(String string, float f, float[] fArray) {
        block15: {
            float[][] fArray2;
            block14: {
                Object object;
                int n;
                block12: {
                    block13: {
                        block11: {
                            block10: {
                                Arrays.fill(fArray, Float.NaN);
                                n = Cosmetics.a;
                                float[][] fArray3 = LOCAL_POSES.get(string);
                                if (n != 0) break block10;
                                if (fArray3 == null) break block11;
                                Emotes.channels(fArray3, f, fArray);
                            }
                            return;
                        }
                        EmoteImport$Clip emoteImport$Clip = EmoteImport.clip(string);
                        object = emoteImport$Clip;
                        if (n != 0) break block12;
                        if (object == null) break block13;
                        EmoteImport.sample(emoteImport$Clip, f, fArray);
                        return;
                    }
                    object = REMOTE_POSES.get(string);
                }
                float[][] fArray4 = (float[][])object;
                fArray2 = fArray4;
                if (n != 0) break block14;
                if (fArray2 == null) break block15;
                fArray2 = fArray4;
            }
            Emotes.channels(fArray2, f, fArray);
        }
    }

    
    private static void channels(float[][] var0, float var1_1, float[] var2_2) {
        var4_3 = var0;
        var3_4 = Cosmetics.a;
        var5_5 = var4_3.length;
        var6_6 = 0;
        while (var6_6 < var5_5) {
            block25: {
                block26: {
                    block35: {
                        block34: {
                            block33: {
                                block32: {
                                    block30: {
                                        block31: {
                                            block29: {
                                                block27: {
                                                    block28: {
                                                        var7_7 = var4_3[var6_6];
                                                        var8_8 = (int)var7_7[0];
                                                        if (var3_4 != 0) break block25;
                                                        if (var8_8 < 0) break block26;
                                                        v1 = var8_8;
                                                        if (var3_4 != 0) break block27;
                                                        if (v1 < 44) break block28;
                                                        break block26;
                                                    }
                                                    v5 = var7_7[6];
                                                    if (var3_4 != 0) break block29;
                                                    cfr_temp_0 = v5 - 0.0f;
                                                    v1 = cfr_temp_0 == 0.0f ? 0 : (cfr_temp_0 > 0.0f ? 1 : -1);
                                                }
                                                v5 = v1 > 0 ? Math.min(var1_1 * var7_7[6], 1.0f) : 1.0f;
                                            }
                                            var9_9 = v5;
                                            v8 = var7_7[3];
                                            if (var3_4 != 0) break block30;
                                            if (v8 != 0.0f) break block31;
                                            v10 = 0.0f;
                                            break block32;
                                        }
                                        v8 = var1_1 * var7_7[3] + var7_7[4];
                                    }
                                    v10 = MathHelper.method_15374((double)v8);
                                }
                                var10_10 = v10;
                                v12 = var7_7;
                                v13 = 5;
                                if (var3_4 != 0) break block33;
                                if (v12[v13] == 1.0f) {
                                }
                                var10_10 = Math.abs(var10_10);
                                if (var3_4 == 0) break block34;
lbl75:

                                v12 = var7_7;
                                if (var3_4 != 0) break block35;
                                v13 = 5;
                            }
                            if (v12[v13] == 2.0f) {
                                var10_10 = Math.max(0.0f, var10_10);
                            }
                        }
                        v12 = var2_2;
                    }
                    v12[var8_8] = (var7_7[1] + var7_7[2] * var10_10) * var9_9;
                }
                ++var6_6;
            }
            if (var3_4 == 0) continue;
        }
    }

    
    private static void camera(MinecraftClient minecraftClient, boolean bl) {
        block17: {
            block21: {
                Perspective perspective;
                block20: {
                    int n;
                    block19: {
                        block15: {
                            MinecraftClient minecraftClient2;
                            block18: {
                                Perspective perspective2;
                                block16: {
                                    n = Cosmetics.a;
                                    if (!bl) break block15;
                                    perspective2 = savedView;
                                    if (n != 0) break block16;
                                    if (perspective2 != null) break block17;
                                    minecraftClient2 = minecraftClient;
                                    if (n != 0) break block18;
                                    perspective2 = savedView = minecraftClient2.field_1690.method_31044();
                                }
                                if (!perspective2.method_31034()) break block17;
                                minecraftClient2 = minecraftClient;
                            }
                            minecraftClient2.field_1690.method_31043(Perspective.field_26665);
                            if (n == 0) break block17;
                        }
                        perspective = savedView;
                        if (n != 0) break block19;
                        if (perspective == null) break block17;
                        perspective = minecraftClient.field_1690.method_31044();
                    }
                    if (n != 0) break block20;
                    if (perspective != Perspective.field_26665) break block21;
                    perspective = savedView;
                }
                if (perspective.method_31034()) {
                    minecraftClient.field_1690.method_31043(savedView);
                }
            }
            savedView = null;
        }
    }

    
    public static void tick(MinecraftClient var0) {
        block98: {
            block97: {
                block95: {
                    block96: {
                        block88: {
                            block94: {
                                block93: {
                                    block92: {
                                        block91: {
                                            block90: {
                                                block89: {
                                                    block87: {
                                                        block86: {
                                                            block85: {
                                                                block82: {
                                                                    block84: {
                                                                        block83: {
                                                                            block80: {
                                                                                block81: {
                                                                                    block78: {
                                                                                        block79: {
                                                                                            EmoteFile.poll();
                                                                                            EmoteImport.poll();
                                                                                            var1_1 = Cosmetics.a;
                                                                                            var2_2 = var0.field_1724;
                                                                                            if (var2_2 == null) {
                                                                                                return;
                                                                                            }
                                                                                            var3_3 = Cosmetics.getOwnEmote();
                                                                                            v1 = var0;
                                                                                            v2 = var3_3.isEmpty();
                                                                                            if (var1_1 != 0) break block78;
                                                                                            if (v2) break block79;
                                                                                            v2 = true;
                                                                                            break block78;
                                                                                        }
                                                                                        v2 = false;
                                                                                    }
                                                                                    Emotes.camera(v1, v2);
                                                                                    FreeLook.update(var0);
                                                                                    v4 = var3_3;
                                                                                    if (var1_1 != 0) break block80;
                                                                                    if (!v4.isEmpty()) break block81;
                                                                                    return;
                                                                                }
                                                                                v4 = Emotes.editorId;
                                                                            }
                                                                            if (v4 != null) {
                                                                                return;
                                                                            }
                                                                            v7 = var0.field_1690.field_1894.method_1434();
                                                                            if (var1_1 != 0) break block82;
                                                                            if (v7) break block83;
                                                                            v7 = var0.field_1690.field_1881.method_1434();
                                                                            if (var1_1 != 0) break block82;
                                                                            if (v7) break block83;
                                                                            v7 = var0.field_1690.field_1913.method_1434();
                                                                            if (var1_1 != 0) break block82;
                                                                            if (v7) break block83;
                                                                            v7 = var0.field_1690.field_1849.method_1434();
                                                                            if (var1_1 != 0) break block82;
                                                                            if (v7) break block83;
                                                                            v7 = var0.field_1690.field_1903.method_1434();
                                                                            if (var1_1 != 0) break block82;
                                                                            if (!v7) break block84;
                                                                        }
                                                                        v7 = true;
                                                                        break block82;
                                                                    }
                                                                    v7 = false;
                                                                }
                                                                var4_4 = v7;
                                                                v17 = var4_4;
                                                                if (var1_1 != 0) break block85;
                                                                if (!v17) break block86;
                                                                v17 = EmoteEngine.isTraversal(var3_3);
                                                            }
                                                            if (var1_1 != 0) break block87;
                                                            if (v17) break block86;
                                                            v17 = true;
                                                            break block87;
                                                        }
                                                        v17 = false;
                                                    }
                                                    var5_5 = v17;
                                                    if (var1_1 != 0) break block88;
                                                    if (!var5_5) {
                                                    }
                                                    v22 = var2_2;
                                                    if (var1_1 != 0) break block89;
                                                    if (!v22.method_5715()) {
                                                    }
                                                    v22 = var2_2;
                                                }
                                                if (var1_1 != 0) break block90;
                                                if (!v22.field_6252) {
                                                }
                                                v22 = var2_2;
                                            }
                                            if (var1_1 != 0) break block91;
                                            if (!v22.method_6115()) {
                                            }
                                            v22 = var2_2;
                                        }
                                        if (var1_1 != 0) break block92;
                                        if (v22.field_6235 <= 0) {
                                        }
                                        v22 = var2_2;
                                        if (var1_1 != 0) break block93;
                                    }
                                    if (!v22.method_6128()) {
                                        v22 = var2_2;
                                    }
                                }
                                if (var1_1 != 0) break block94;
                                if (!v22.method_5681()) {
                                }
                                v22 = var2_2;
                            }
                            v35 = v22.field_6213;
                            if (var1_1 != 0) break block95;
                            if (v35 <= 0) break block96;
lbl220:

                            Cosmetics.setOwnEmote("");
                        }
                        return;
                    }
                    v35 = (int)EmoteEngine.loops(var3_3);
                }
                if (var1_1 != 0) break block97;
                if (v35 != 0) break block98;
                cfr_temp_0 = Emotes.secondsIn(var2_2.method_5667()) - (EmoteEngine.lengthSeconds(var3_3) + 0.4f);
                v35 = cfr_temp_0 == 0.0f ? 0 : (cfr_temp_0 > 0.0f ? 1 : -1);
            }
            if (v35 > 0) {
                Cosmetics.setOwnEmote("");
            }
        }
    }

    
    static {
        block8: {
            block7: {
                var1 = 3112316668243305029L;
                var0_1 = new long[20];
                var4_2 = 0;
                var5_3 = "Cn\u00ad\u0013\u001f+b\\\u00e2+%\u00d6\u001f+bC\u00b3\u00efZ,\u001f+bQ\u00a3\u008dB\u00ce\u001f+bf\u00ac\u0001\u00c5O\u001f+bV\u0004<\u00c2\u008a\u001f+bF \u00f8dR\u001f+bJ\u00d6\u00ef\f\u00aa\u001f+bc\u00a0\u00e5\u0095\u00c5\u001f+bW\u009e\u00f8Z\u00db\u001f+biC\u007f\u0098\\\u001f+b_[\u009a)\u0090\u001f+b]\u0099\u007fT\u00d7\u001f+bX\u009a~z\u0002\u001f+bS\u00a6\u0080\u00d0\u000b\u001f+bI\u00f8\u0088\u00f0\u00a7\u001f+bR\u009b\u00e6kK\u001f+be\r]j5\u001f+bL";
                var6_4 = "Cn\u00ad\u0013\u001f+b\\\u00e2+%\u00d6\u001f+bC\u00b3\u00efZ,\u001f+bQ\u00a3\u008dB\u00ce\u001f+bf\u00ac\u0001\u00c5O\u001f+bV\u0004<\u00c2\u008a\u001f+bF \u00f8dR\u001f+bJ\u00d6\u00ef\f\u00aa\u001f+bc\u00a0\u00e5\u0095\u00c5\u001f+bW\u009e\u00f8Z\u00db\u001f+biC\u007f\u0098\\\u001f+b_[\u009a)\u0090\u001f+b]\u0099\u007fT\u00d7\u001f+bX\u009a~z\u0002\u001f+bS\u00a6\u0080\u00d0\u000b\u001f+bI\u00f8\u0088\u00f0\u00a7\u001f+bR\u009b\u00e6kK\u001f+be\r]j5\u001f+bL".length();
                var3_5 = 0;
                while (true) {
                    var7_6 = var5_3.substring(var3_5, var3_5 += 8).getBytes("ISO-8859-1");
                    v0 = var0_1;
                    v1 = var4_2++;
                    v2 = ((long)var7_6[0] & 255L) << 56 | ((long)var7_6[1] & 255L) << 48 | ((long)var7_6[2] & 255L) << 40 | ((long)var7_6[3] & 255L) << 32 | ((long)var7_6[4] & 255L) << 24 | ((long)var7_6[5] & 255L) << 16 | ((long)var7_6[6] & 255L) << 8 | (long)var7_6[7] & 255L;
                    v3 = -1;
                    break block7;
                    break;
                }
lbl14:

                while (true) {
                    v0[v1] = v4;
                    if (var3_5 < var6_4) continue;
                    var5_3 = "wC%\u00b3\u001f+bl\u00e8sQ\u00a7\u001f+bP";
                    var6_4 = "wC%\u00b3\u001f+bl\u00e8sQ\u00a7\u001f+bP".length();
                    var3_5 = 0;
                    while (true) {
                        var7_6 = var5_3.substring(var3_5, var3_5 += 8).getBytes("ISO-8859-1");
                        v0 = var0_1;
                        v1 = var4_2++;
                        v2 = ((long)var7_6[0] & 255L) << 56 | ((long)var7_6[1] & 255L) << 48 | ((long)var7_6[2] & 255L) << 40 | ((long)var7_6[3] & 255L) << 32 | ((long)var7_6[4] & 255L) << 24 | ((long)var7_6[5] & 255L) << 16 | ((long)var7_6[6] & 255L) << 8 | (long)var7_6[7] & 255L;
                        v3 = 0;
                        break block7;
                        break;
                    }
                    break;
                }
lbl27:

                while (true) {
                    v0[v1] = v4;
                    if (var3_5 < var6_4) continue;
                    break block8;
                    break;
                }
            }
            v4 = v2 ^ var1;
            switch (v3) {
                default: {
                    continue;
                }
                case 0:
lbl38:

                continue;
            }
        }
        Emotes.R_ARM = (int)var0_1[1];
        Emotes.OFF_L_ARM = (int)var0_1[3];
        Emotes.OFF_BODY = (int)var0_1[12];
        Emotes.L_ELBOW = (int)var0_1[13];
        Emotes.SLOTS = (int)var0_1[9];
        Emotes.KNEE = (int)var0_1[2];
        Emotes.OFF_R_ARM = (int)var0_1[16];
        Emotes.L_SHIN = (int)var0_1[11];
        Emotes.R_ELBOW = (int)var0_1[19];
        Emotes.BODY = (int)var0_1[5];
        Emotes.OFF_HEAD = (int)var0_1[10];
        Emotes.SPINE = (int)var0_1[0];
        Emotes.L_ARM = (int)var0_1[17];
        Emotes.SINK = (int)var0_1[4];
        Emotes.L_LEG = (int)var0_1[6];
        Emotes.R_SHIN = (int)var0_1[15];
        Emotes.R_LEG = (int)var0_1[14];
        Emotes.HIP_BEND = (int)var0_1[8];
        Emotes.OFF_R_LEG = (int)var0_1[7];
        Emotes.OFF_L_LEG = (int)var0_1[18];
        Emotes.BY_ID = new LinkedHashMap<String, Emotes$Emote>();
        Emotes.REMOTE_POSES = new ConcurrentHashMap<String, float[][]>();
        Emotes.REMOTE_IDS = ConcurrentHashMap.newKeySet();
        Emotes.LOCAL_POSES = new ConcurrentHashMap<String, float[][]>();
        Emotes.IMPORTED_IDS = ConcurrentHashMap.newKeySet();
        Emotes.SERVED_IDS = ConcurrentHashMap.newKeySet();
        Emotes.CLOCKS = new HashMap<UUID, long[]>();
        Emotes.SAMPLE = new float[44];
    }
}
