package dev.quelchi.aurora.render;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.quelchi.aurora.cosmetics.content.Colorways;
import dev.quelchi.aurora.cosmetics.content.Colorways$Way;
import dev.quelchi.aurora.gui.PreviewScene$Geometry;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.render.EffectEngine$Def;
import dev.quelchi.aurora.render.EffectEngine$Effect;
import dev.quelchi.aurora.render.EffectEngine$Grad;
import dev.quelchi.aurora.render.EffectEngine$Inst;
import dev.quelchi.aurora.render.EffectEngine$Layer;
import dev.quelchi.aurora.render.EffectEngine$Out;
import dev.quelchi.aurora.render.EffectEngine$P;
import dev.quelchi.aurora.render.EffectEngine$Pool;
import dev.quelchi.aurora.render.EffectEngine$PreviewSink;
import dev.quelchi.aurora.render.EffectEngine$Prim;
import dev.quelchi.aurora.render.EffectEngine$Sound;
import dev.quelchi.aurora.render.EffectEngine$SpeckOut;
import dev.quelchi.aurora.render.EffectGpu;
import dev.quelchi.aurora.render.LocalSpeckSink;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.entity.Entity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public final class EffectEngine {
    static final int PARTICLES = 0;
    static final int LIGHT = 1;
    static final int SHOCKWAVE;
    static final int ARC;
    static final int BOLT;
    static final int RAYS;
    static final int OUTLINE;
    private static final String[] KINDS;
    private static final String[] SPRITES;
    static final int STREAK;
    static final int SMOKE;
    static final int RING;
    static final int BEAM;
    static final int SHARD;
    static final int HEART;
    static final int STRIP;
    static final int ALPHA;
    private static final int BILL = 0;
    private static final int GROUND;
    private static final int BEAMO;
    private static final int PLANE;
    private static final int TUMBLE;
    private static final int POINT = 0;
    private static final int SPHERE = 1;
    private static final int DISC;
    private static final int ERING;
    private static final int BOX;
    private static final int NONE = 0;
    private static final int DSPHERE = 1;
    private static final int HEMI;
    private static final int OUTD;
    private static final int UP;
    private static final int TOWARD;
    private static final int IN;
    private static final int HIT = 0;
    private static final int BODY = 1;
    private static final int FEET;
    private static final int GROUND_A;
    private static final int CHEST;
    private static final int MAX_PARTICLES;
    private static final float TAU = (float)Math.PI * 2;
    private static final float[] ARC_OUT;
    private static final float[] COL;
    private static final float[] COL2;
    private static final int RIB;
    private static final float[] RP;
    private static final float[] RW;
    private static final float[] RC;
    private static final float[] RS;
    private static int[] order;
    private static float[] keys;
    private static final float[] ARC_P;
    private static final float[] RAY_Q;
    static final EffectEngine$Pool WORLD;
    private static long lastNanos;
    private static final Map<Integer, long[]> HIDDEN;
    private static final float[] PLAIN;
    private static final float TILT = 0.45f;
    private static final Vector3f TILE_R;
    private static final Vector3f TILE_U;
    public static int a;
    private static final String[] b;
    private static final long[] c;
    private static final Integer[] d;
    private static final long e;

    private EffectEngine() {
    }

    
    public static EffectEngine$Effect effect(JsonObject var0) {
        block66: {
            block63: {
                block65: {
                    block64: {
                        block61: {
                            block62: {
                                block60: {
                                    block59: {
                                        block58: {
                                            var14_1 = EffectEngine.b;
                                            var2_2 = var0.get(var14_1[20]).getAsString();
                                            var3_3 = var0.getAsJsonObject(var14_1[50]);
                                            var1_4 = EffectEngine.a;
                                            v0 = var3_3;
                                            if (var1_4 == 0) {
                                                if (v0 == null) throw new IllegalArgumentException(EffectEngine.b[91]);
                                                v0 = var3_3;
                                            }
                                            if (v0.isEmpty()) {
                                                throw new IllegalArgumentException(EffectEngine.b[91]);
                                            }
                                            var4_5 = new ArrayList<Colorways$Way>();
                                            var5_6 = new float[var3_3.size()][][];
                                            var6_7 = 0;
                                            var7_8 = var3_3.entrySet().iterator();
                                            while (var7_8.hasNext()) {
                                                var8_9 = (Map.Entry)var7_8.next();
                                                var9_10 = ((JsonElement)var8_9.getValue()).getAsJsonObject();
                                                v1 = var9_10;
                                                if (var1_4 == 0) {
                                                    var14_1 = EffectEngine.b;
                                                    var10_11 = v1.get(var14_1[137]).getAsString();
                                                    var11_12 = var9_10.get(var14_1[34]).getAsString();
                                                    var12_14 = var9_10.get(var14_1[94]).getAsString();
                                                    var5_6[var6_7++] = new float[][]{EffectEngine.lin(var10_11), EffectEngine.lin((String)var11_12), EffectEngine.lin((String)var12_14)};
                                                    var4_5.add(Colorways.way(((String)var8_9.getKey()).toLowerCase(Locale.ROOT), (String)var8_9.getKey(), EffectEngine.rgb((String)var11_12), EffectEngine.rgb((String)var12_14)));
                                                    if (var1_4 == 0) continue;
                                                }
                                                break block58;
                                            }
                                            v1 = var0;
                                        }
                                        var14_1 = EffectEngine.b;
                                        v2 = var7_8 = v1.getAsJsonArray(var14_1[19]);
                                        if (var1_4 == 0) {
                                            if (v2 == null) throw new IllegalArgumentException(EffectEngine.b[64]);
                                            v2 = var7_8;
                                        }
                                        if (v2.isEmpty()) {
                                            throw new IllegalArgumentException(EffectEngine.b[64]);
                                        }
                                        var8_9 = new ArrayList<E>();
                                        for (String var10_11 : var7_8) {
                                            if (var1_4 == 0) {
                                                var11_12 = EffectEngine.layer(var10_11.getAsJsonObject());
                                                if (var11_12 != null) {
                                                    var8_9.add(var11_12);
                                                }
                                                if (var1_4 == 0) continue;
                                            }
                                            break block59;
                                        }
                                        v3 = var8_9;
                                        if (var1_4 == 0) {
                                            if (v3.isEmpty()) {
                                                throw new IllegalArgumentException(EffectEngine.b[99]);
                                            } else {
                                            }
                                        }
                                        break block60;
                                    }
                                    v3 = new ArrayList<E>();
                                }
                                var9_10 = v3;
                                var14_1 = EffectEngine.b;
                                v4 = var10_11 = var0.get(var14_1[132]);
                                if (var1_4 != 0) break block61;
                                if (v4 == null) break block62;
                                v4 = var10_11;
                                if (var1_4 != 0) break block61;
                                if (!v4.isJsonObject()) break block62;
                                var9_10.add(EffectEngine.sound(var10_11.getAsJsonObject()));
                                if (var1_4 == 0) break block63;
                            }
                            v4 = var10_11;
                        }
                        if (var1_4 != 0) break block64;
                        if (v4 == null) break block63;
                        v4 = var10_11;
                    }
                    if (var1_4 != 0) break block65;
                    if (!v4.isJsonArray()) break block63;
                    v4 = var10_11;
                }
                for (Object var12_14 : v4.getAsJsonArray()) {
                    var9_10.add(EffectEngine.sound(var12_14.getAsJsonObject()));
                    if (var1_4 == 0) continue;
                }
            }
            var14_1 = EffectEngine.b;
            var12_14 = EffectEngine.str(var0, var14_1[90], var14_1[51]);
            var13_15 = -1;
            v5 = var12_14.hashCode();
            if (var1_4 != 0) break block66;
            switch (v5) {
                case 103315: {
                    v5 = (int)var12_14.equals(EffectEngine.b[104]);
                    if (var1_4 == 0) {
                        if (v5 == 0) break;
                        var13_15 = 0;
                        if (var1_4 == 0) break;
                    }
                    break block66;
                }
                case 3029410: {
                    v5 = (int)var12_14.equals(EffectEngine.b[13]);
                    if (var1_4 == 0) {
                        if (v5 == 0) break;
                        var13_15 = 1;
                        if (var1_4 == 0) break;
                    }
                    break block66;
                }
                case -1237460601: {
                    v5 = (int)var12_14.equals(EffectEngine.b[81]);
                    if (var1_4 == 0) {
                        if (v5 == 0) break;
                        var13_15 = 2;
                        if (var1_4 == 0) break;
                    }
                    break block66;
                }
                case 94627585: {
                    v5 = (int)var12_14.equals(EffectEngine.b[26]);
                    if (var1_4 != 0) break block66;
                    if (v5 == 0) break;
                    var13_15 = 3;
                }
            }
            v5 = var13_15;
        }
        if (var1_4 == 0) {
            switch (v5) {
                case 0: {
                    v5 = 0;
                    break;
                }
                case 1: {
                    v5 = 1;
                    break;
                }
                case 2: {
                    v5 = 3;
                    break;
                }
                case 3: {
                    v5 = 4;
                    break;
                }
                default: {
                    v5 = 2;
                }
            }
        }
        var11_13 = v5;
        var14_1 = EffectEngine.b;
        var12_14 = new EffectEngine$Def(Math.max(0.1f, Math.min(5.0f, EffectEngine.flt(var0, var14_1[106], 1.0f))), EffectEngine.flt(var0, var14_1[9], 0.0f), var11_13, var8_9.toArray(new EffectEngine$Layer[0]), var5_6, var9_10.toArray(new EffectEngine$Sound[0]));
        var13_16 = (Colorways$Way)var4_5.get(0);
        v6 = new EffectEngine$Effect(var2_2, var0.get(var14_1[75]).getAsString(), EffectEngine.a(15931, 5073444859891720402L) | var13_16.inner(), var4_5, EffectEngine.a(23602, 593470712793449178L) | var13_16.outer(), (EffectEngine$Def)var12_14, 0);
        if (Module.a == false) return v6;
        EffectEngine.a = ++var1_4;
        return v6;
    }

    /*
     * Exception decompiling
     */
    private static EffectEngine$Layer layer(JsonObject var0) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [2[TRYBLOCK]], but top level block is 61[SWITCH]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
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

    private static EffectEngine$Sound sound(JsonObject jsonObject) {
        String[] stringArray = b;
        return new EffectEngine$Sound(SoundEvent.method_47908((Identifier)Identifier.method_60654((String)jsonObject.get(stringArray[36]).getAsString())), EffectEngine.flt(jsonObject, stringArray[112], 1.0f), EffectEngine.flt(jsonObject, stringArray[12], 1.0f));
    }

    private static int index(String[] stringArray, String string) {
        int n;
        block5: {
            int n2 = a;
            for (int i = 0; i < stringArray.length; ++i) {
                int n3;
                block6: {
                    n = string.equals(stringArray[i]) ? 1 : 0;
                    if (n2 != 0) break block5;
                    if (n2 != 0) break block6;
                    if (n == 0) continue;
                    n3 = i;
                }
                return n3;
            }
            n = -1;
        }
        return n;
    }

    
    private static int slot(String string) {
        int n;
        int n2;
        block20: {
            String string2 = string;
            n2 = a;
            int n3 = -1;
            n = string2.hashCode();
            if (n2 != 0) break block20;
            switch (n) {
                case 3059615: {
                    n = string2.equals(b[61]) ? 1 : 0;
                    if (n2 == 0) {
                        if (n == 0) break;
                        n3 = 0;
                        if (n2 == 0) break;
                    }
                    break block20;
                }
                case -1423461174: {
                    n = string2.equals(b[17]) ? 1 : 0;
                    if (n2 == 0) {
                        if (n == 0) break;
                        n3 = 1;
                        if (n2 == 0) break;
                    }
                    break block20;
                }
                case 3343801: {
                    n = string2.equals(b[11]) ? 1 : 0;
                    if (n2 != 0) break block20;
                    if (n == 0) break;
                    n3 = 2;
                }
            }
            n = n3;
        }
        if (n2 != 0) return n;
        switch (n) {
            case 0: {
                return 0;
            }
            case 1: {
                return 2;
            }
            case 2: {
                return 1;
            }
        }
        return -1;
    }

    private static EffectEngine$Grad grad1(String string) {
        EffectEngine$Grad effectEngine$Grad;
        EffectEngine$Grad effectEngine$Grad2 = effectEngine$Grad;
        EffectEngine$Grad effectEngine$Grad3 = effectEngine$Grad;
        float[] fArray = new float[1];
        float[] fArray2 = fArray;
        fArray[0] = 0.0f;
        int[] nArray = new int[1];
        int[] nArray2 = nArray;
        nArray[0] = EffectEngine.slot(string);
        float[] fArray3 = string.startsWith("#") ? EffectEngine.lin(string) : new float[3];
        effectEngine$Grad2(fArray2, nArray2, fArray3);
        return effectEngine$Grad3;
    }

    private static EffectEngine$Grad grad(JsonArray jsonArray) {
        JsonArray jsonArray2;
        int n;
        block9: {
            block10: {
                n = a;
                jsonArray2 = jsonArray;
                if (n != 0) break block9;
                if (!jsonArray2.isEmpty()) break block10;
                return EffectEngine.grad1(b[11]);
            }
            jsonArray2 = jsonArray;
        }
        float[] fArray = new float[jsonArray2.size()];
        float[] fArray2 = new float[jsonArray.size() * 3];
        int[] nArray = new int[jsonArray.size()];
        int n2 = 0;
        while (n2 < jsonArray.size()) {
            block11: {
                block12: {
                    JsonArray jsonArray3 = jsonArray.get(n2).getAsJsonArray();
                    fArray[n2] = jsonArray3.get(0).getAsFloat();
                    String string = jsonArray3.get(1).getAsString();
                    if (n != 0) break block11;
                    nArray[n2] = EffectEngine.slot(string);
                    if (!string.startsWith("#")) break block12;
                    System.arraycopy(EffectEngine.lin(string), 0, fArray2, n2 * 3, 3);
                }
                ++n2;
            }
            if (n == 0) continue;
        }
        return new EffectEngine$Grad(fArray, nArray, fArray2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static float[] curve(JsonObject jsonObject, String string) {
        float[] fArray;
        JsonArray jsonArray;
        JsonArray jsonArray2;
        int n;
        block18: {
            block19: {
                JsonObject jsonObject2;
                block17: {
                    JsonObject jsonObject3;
                    block16: {
                        n = a;
                        jsonObject3 = jsonObject;
                        if (n != 0) break block16;
                        if (!jsonObject3.has(string)) return null;
                        jsonObject2 = jsonObject;
                        if (n != 0) break block17;
                        jsonObject3 = jsonObject2.get(string);
                    }
                    if (!jsonObject3.isJsonArray()) {
                        return null;
                    }
                    jsonObject2 = jsonObject;
                }
                jsonArray2 = jsonObject2.getAsJsonArray(string);
                jsonArray = jsonArray2;
                if (n != 0) break block18;
                if (!jsonArray.isEmpty()) break block19;
                return null;
            }
            jsonArray = jsonArray2;
        }
        float[] fArray2 = new float[jsonArray.size() * 2];
        for (int i = 0; i < jsonArray2.size(); ++i) {
            JsonArray jsonArray3 = jsonArray2.get(i).getAsJsonArray();
            fArray = fArray2;
            if (n != 0) return fArray;
            fArray[i * 2] = jsonArray3.get(0).getAsFloat();
            fArray2[i * 2 + 1] = jsonArray3.get(1).getAsFloat();
            if (n == 0) continue;
            break;
        }
        fArray = fArray2;
        return fArray;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static float[] range(JsonObject jsonObject, String string, float f) {
        JsonElement jsonElement;
        block15: {
            JsonElement jsonElement2;
            block16: {
                float f2;
                float[] fArray;
                block17: {
                    float f3;
                    block18: {
                        int n;
                        block13: {
                            block14: {
                                block12: {
                                    jsonElement2 = jsonObject.get(string);
                                    n = a;
                                    jsonElement = jsonElement2;
                                    if (n != 0) break block12;
                                    if (jsonElement == null) return new float[]{f, f};
                                    jsonElement = jsonElement2;
                                }
                                if (n != 0) break block13;
                                if (!jsonElement.isJsonNull()) break block14;
                                return new float[]{f, f};
                            }
                            jsonElement = jsonElement2;
                        }
                        if (n != 0) break block15;
                        if (!jsonElement.isJsonArray()) break block16;
                        JsonArray jsonArray = jsonElement2.getAsJsonArray();
                        f3 = jsonArray.get(0).getAsFloat();
                        float[] fArray2 = new float[2];
                        float[] fArray3 = fArray2;
                        fArray = fArray2;
                        int n2 = 0;
                        f2 = f3;
                        if (n != 0) break block17;
                        fArray3[n2] = f2;
                        fArray3 = fArray;
                        fArray = fArray;
                        n2 = 1;
                        if (jsonArray.size() <= 1) break block18;
                        f2 = jsonArray.get(1).getAsFloat();
                        break block17;
                    }
                    f2 = f3;
                }
                fArray3[n2] = f2;
                return fArray;
            }
            jsonElement = jsonElement2;
        }
        float f4 = jsonElement.getAsFloat();
        return new float[]{f4, f4};
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static float[] vec(JsonObject jsonObject, String string, int n) {
        JsonObject jsonObject2;
        int n2;
        block13: {
            JsonObject jsonObject3;
            block12: {
                n2 = a;
                jsonObject3 = jsonObject;
                if (n2 != 0) break block12;
                if (!jsonObject3.has(string)) return null;
                jsonObject2 = jsonObject;
                if (n2 != 0) break block13;
                jsonObject3 = jsonObject2.get(string);
            }
            if (!jsonObject3.isJsonArray()) {
                return null;
            }
            jsonObject2 = jsonObject;
        }
        JsonArray jsonArray = jsonObject2.getAsJsonArray(string);
        float[] fArray = new float[n];
        for (int i = 0; i < n; ++i) {
            if (i >= jsonArray.size()) return fArray;
            fArray[i] = jsonArray.get(i).getAsFloat();
            if (n2 == 0) continue;
            return fArray;
        }
        return fArray;
    }

    private static float flt(JsonObject jsonObject, String string, float f) {
        float f2;
        block16: {
            block13: {
                String string2;
                JsonObject jsonObject2;
                block15: {
                    JsonElement jsonElement;
                    block14: {
                        String string3;
                        JsonObject jsonObject3;
                        int n;
                        block12: {
                            n = a;
                            jsonObject3 = jsonObject;
                            string3 = string;
                            if (n != 0) break block12;
                            if (!jsonObject3.has(string3)) break block13;
                            jsonObject3 = jsonObject;
                            string3 = string;
                        }
                        jsonElement = jsonObject3.get(string3);
                        if (n != 0) break block14;
                        if (!jsonElement.isJsonPrimitive()) break block13;
                        jsonObject2 = jsonObject;
                        string2 = string;
                        if (n != 0) break block15;
                        jsonElement = jsonObject2.get(string2);
                    }
                    if (!jsonElement.getAsJsonPrimitive().isNumber()) break block13;
                    jsonObject2 = jsonObject;
                    string2 = string;
                }
                f2 = jsonObject2.get(string2).getAsFloat();
                break block16;
            }
            f2 = f;
        }
        return f2;
    }

    private static String str(JsonObject jsonObject, String string, String string2) {
        String string3;
        block11: {
            block9: {
                String string4;
                JsonObject jsonObject2;
                block10: {
                    JsonObject jsonObject3;
                    block8: {
                        int n = a;
                        jsonObject3 = jsonObject;
                        if (n != 0) break block8;
                        if (!jsonObject3.has(string)) break block9;
                        jsonObject2 = jsonObject;
                        string4 = string;
                        if (n != 0) break block10;
                        jsonObject3 = jsonObject2.get(string4);
                    }
                    if (!jsonObject3.isJsonPrimitive()) break block9;
                    jsonObject2 = jsonObject;
                    string4 = string;
                }
                string3 = jsonObject2.get(string4).getAsString();
                break block11;
            }
            string3 = string2;
        }
        return string3;
    }

    private static int rgb(String string) {
        return Integer.parseInt(string.substring(1), 16) & EffectEngine.a(12246, 3080845511105818940L);
    }

    private static float[] lin(String string) {
        int n = EffectEngine.rgb(string);
        return new float[]{(float)Math.pow((double)(n >> 16 & 0xFF) / 255.0, 2.2), (float)Math.pow((double)(n >> 8 & 0xFF) / 255.0, 2.2), (float)Math.pow((double)(n & 0xFF) / 255.0, 2.2)};
    }

    static float pick(Random random, float[] fArray) {
        float f;
        block6: {
            float[] fArray2;
            float f2;
            block4: {
                block5: {
                    int n = a;
                    f2 = fArray[0];
                    fArray2 = fArray;
                    if (n != 0) break block4;
                    if (f2 != fArray2[1]) break block5;
                    f = fArray[0];
                    break block6;
                }
                f2 = fArray[0];
                fArray2 = fArray;
            }
            f = f2 + (fArray2[1] - fArray[0]) * random.nextFloat();
        }
        return f;
    }

    static float curveAt(float[] fArray, float f) {
        float f2;
        block16: {
            float f3;
            int n;
            block14: {
                float f4;
                block15: {
                    n = a;
                    if (fArray == null) {
                        return 1.0f;
                    }
                    float f5 = f - fArray[0];
                    f4 = f5 == 0.0f ? 0 : (f5 < 0.0f ? -1 : 1);
                    if (n != 0) break block14;
                    if (f4 > 0) break block15;
                    return fArray[1];
                }
                f4 = f3 = (float)2;
            }
            while (f3 < fArray.length) {
                block18: {
                    float f6;
                    float f7;
                    float[] fArray2;
                    block17: {
                        f2 = f;
                        if (n != 0) break block16;
                        fArray2 = fArray;
                        f7 = f3;
                        if (n != 0) break block17;
                        if (!(f2 <= fArray2[f7])) break block18;
                        f6 = fArray[f3 - true];
                        fArray2 = fArray;
                        f7 = f3 + true;
                    }
                    return f6 + (fArray2[f7] - fArray[f3 - true]) * (f - fArray[f3 - 2]) / Math.max(1.0E-6f, fArray[f3] - fArray[f3 - 2]);
                }
                f3 += 2;
                if (n == 0) continue;
            }
            f2 = fArray[fArray.length - 1];
        }
        return f2;
    }

    static void gradAt(EffectEngine$Grad effectEngine$Grad, float[][] fArray, float f, float[] fArray2) {
        block28: {
            float f2;
            int n;
            int n2;
            block26: {
                float f3;
                block27: {
                    block24: {
                        block25: {
                            n2 = a;
                            n = effectEngine$Grad.k.length;
                            if (n2 != 0) break block24;
                            if (n == 1) break block25;
                            float f4 = f - effectEngine$Grad.k[0];
                            f3 = f4 == 0.0f ? 0 : (f4 < 0.0f ? -1 : 1);
                            if (n2 != 0) break block26;
                            if (f3 > 0) break block27;
                        }
                        EffectEngine.stop(effectEngine$Grad, fArray, 0, fArray2, 0);
                    }
                    return;
                }
                f3 = f2 = 1.0f;
            }
            while (f2 < n) {
                block30: {
                    int n3;
                    int n4;
                    float[] fArray3;
                    float[] fArray4;
                    float f5;
                    block33: {
                        block34: {
                            int n5;
                            block31: {
                                block32: {
                                    float[] fArray5;
                                    float f6;
                                    block29: {
                                        if (n2 != 0) break block28;
                                        f6 = f;
                                        fArray5 = effectEngine$Grad.k;
                                        if (n2 != 0) break block29;
                                        if (!(f6 <= fArray5[f2])) break block30;
                                        f6 = f;
                                        fArray5 = effectEngine$Grad.k;
                                    }
                                    f5 = (f6 - fArray5[f2 - true]) / Math.max(1.0E-6f, effectEngine$Grad.k[f2] - effectEngine$Grad.k[f2 - true]);
                                    fArray4 = EffectEngine.stopRef(effectEngine$Grad, fArray, (int)(f2 - true));
                                    fArray3 = EffectEngine.stopRef(effectEngine$Grad, fArray, (int)f2);
                                    n5 = effectEngine$Grad.slot[f2 - true];
                                    if (n2 != 0) break block31;
                                    if (n5 >= 0) break block32;
                                    n5 = (int)((f2 - true) * 3);
                                    break block31;
                                }
                                n5 = 0;
                            }
                            n4 = n5;
                            n3 = effectEngine$Grad.slot[f2];
                            if (n2 != 0) break block33;
                            if (n3 >= 0) break block34;
                            n3 = (int)(f2 * 3);
                            break block33;
                        }
                        n3 = 0;
                    }
                    int n6 = n3;
                    for (int i = 0; i < 3; ++i) {
                        fArray2[i] = fArray4[n4 + i] + (fArray3[n6 + i] - fArray4[n4 + i]) * f5;
                        if (n2 == 0) continue;
                    }
                    return;
                }
                ++f2;
                if (n2 == 0) continue;
            }
            EffectEngine.stop(effectEngine$Grad, fArray, n - 1, fArray2, 0);
        }
    }

    private static float[] stopRef(EffectEngine$Grad effectEngine$Grad, float[][] fArray, int n) {
        float[] fArray2;
        block6: {
            block5: {
                EffectEngine$Grad effectEngine$Grad2;
                block4: {
                    int n2 = a;
                    effectEngine$Grad2 = effectEngine$Grad;
                    if (n2 != 0) break block4;
                    if (effectEngine$Grad2.slot[n] >= 0) break block5;
                    effectEngine$Grad2 = effectEngine$Grad;
                }
                fArray2 = effectEngine$Grad2.fixed;
                break block6;
            }
            fArray2 = fArray[effectEngine$Grad.slot[n]];
        }
        return fArray2;
    }

    private static void stop(EffectEngine$Grad effectEngine$Grad, float[][] fArray, int n, float[] fArray2, int n2) {
        int n3;
        float[] fArray3;
        block4: {
            block5: {
                fArray3 = EffectEngine.stopRef(effectEngine$Grad, fArray, n);
                int n4 = a;
                n3 = effectEngine$Grad.slot[n];
                if (n4 != 0) break block4;
                if (n3 >= 0) break block5;
                n3 = n * 3;
                break block4;
            }
            n3 = 0;
        }
        int n5 = n3;
        fArray2[n2] = fArray3[n5];
        fArray2[n2 + 1] = fArray3[n5 + 1];
        fArray2[n2 + 2] = fArray3[n5 + 2];
    }

    static float ease3(float f) {
        float f2 = 1.0f - Math.max(0.0f, Math.min(1.0f, f));
        return 1.0f - f2 * f2 * f2;
    }

    static float strikeVis(float f, int n) {
        float f2;
        float f3;
        float f4;
        block8: {
            float f5;
            float f6;
            block9: {
                float f7;
                block10: {
                    block11: {
                        float f8 = 0.07f;
                        f6 = 0.045f;
                        int n2 = a;
                        f5 = f8 + f6;
                        f4 = f;
                        f3 = n;
                        f2 = f5;
                        if (n2 != 0) break block8;
                        if (!(f4 < f3 * f2 - f6)) break block9;
                        f7 = f;
                        if (n2 != 0) break block10;
                        if (!(f7 % f5 < f8)) break block11;
                        f7 = 1.0f;
                        break block10;
                    }
                    f7 = 0.18f;
                }
                return f7;
            }
            f4 = 0.0f;
            f3 = 1.0f;
            f2 = (f - ((float)n * f5 - f6)) / 0.25f;
        }
        return Math.max(f4, f3 - f2);
    }

    private static void randDir(Random random, float[] fArray) {
        float f = random.nextFloat() * 2.0f - 1.0f;
        float f2 = random.nextFloat() * ((float)Math.PI * 2);
        float f3 = (float)Math.sqrt(1.0f - f * f);
        fArray[0] = f3 * (float)Math.cos(f2);
        fArray[1] = f;
        fArray[2] = f3 * (float)Math.sin(f2);
    }

    static float arcHead(float f) {
        return EffectEngine.ease3(f / 0.3f);
    }

    static float arcTail(float f) {
        return EffectEngine.ease3((f - 0.3f) / 0.6f) * 0.6f;
    }

    static void arcAt(EffectEngine$Inst effectEngine$Inst, EffectEngine$Prim effectEngine$Prim, float f, float f2, float[] fArray) {
        block8: {
            boolean bl;
            block10: {
                block9: {
                    float f3;
                    float f4;
                    float f5;
                    float f6;
                    float f7;
                    int n;
                    float f8;
                    float f9;
                    block6: {
                        float f10;
                        block7: {
                            f9 = effectEngine$Inst.fx;
                            f8 = effectEngine$Inst.fy;
                            n = a;
                            f6 = f7 = effectEngine$Inst.fz;
                            f5 = 0.0f;
                            f4 = -f9;
                            f10 = (float)Math.sqrt(f6 * f6 + f4 * f4);
                            f3 = f10;
                            if (n != 0) break block6;
                            if (!(f3 < 1.0E-4f)) break block7;
                            f6 = 1.0f;
                            f10 = 1.0f;
                        }
                        f6 /= f10;
                        f3 = f8 * (f4 /= f10) - f7 * f5;
                    }
                    float f11 = f3;
                    float f12 = f7 * f6 - f9 * f4;
                    float f13 = f9 * f5 - f8 * f6;
                    float f14 = (float)Math.cos(effectEngine$Prim.tilt);
                    float f15 = (float)Math.sin(effectEngine$Prim.tilt);
                    float f16 = f6 * f14 + f11 * f15;
                    float f17 = f5 * f14 + f12 * f15;
                    float f18 = f4 * f14 + f13 * f15;
                    float f19 = -f6 * f15 + f11 * f14;
                    float f20 = -f5 * f15 + f12 * f14;
                    float f21 = -f4 * f15 + f13 * f14;
                    float f22 = (float)Math.cos(effectEngine$Prim.lean);
                    float f23 = (float)Math.sin(effectEngine$Prim.lean);
                    f19 = f19 * f22 + f9 * f23;
                    f20 = f20 * f22 + f8 * f23;
                    f21 = f21 * f22 + f7 * f23;
                    EffectEngine$Layer effectEngine$Layer = effectEngine$Prim.l;
                    float f24 = effectEngine$Layer.radius[0] * effectEngine$Inst.sc;
                    float f25 = -f19 * f24 * 0.35f;
                    float f26 = effectEngine$Layer.y * effectEngine$Inst.sc - f20 * f24 * 0.35f;
                    float f27 = -f21 * f24 * 0.35f;
                    float f28 = (float)Math.toRadians(effectEngine$Layer.span);
                    float f29 = 1.5707964f + f28 / 2.0f - f28 * f;
                    float f30 = (float)Math.cos(f29);
                    float f31 = (float)Math.sin(f29);
                    fArray[0] = f25 + (f16 * f30 + f19 * f31) * f24 * f2;
                    fArray[1] = f26 + (f17 * f30 + f20 * f31) * f24 * f2;
                    fArray[2] = f27 + (f18 * f30 + f21 * f31) * f24 * f2;
                    fArray[3] = f16 * f31 - f19 * f30;
                    fArray[4] = f17 * f31 - f20 * f30;
                    fArray[5] = f18 * f31 - f21 * f30;
                    if (n == 0) break block8;
                    if (!Module.a) break block9;
                    bl = false;
                    break block10;
                }
                bl = true;
            }
            Module.a = bl;
        }
    }

    
    static void build(EffectEngine$Pool var0, EffectEngine$Out var1_1, double var2_2, double var4_3, double var6_4, float var8_5, float var9_6, float var10_7, Vector3f var11_8, Vector3f var12_9, boolean var13_10) {
        block44: {
            block43: {
                EffectEngine.collectLights(var0, var2_2, var4_3, var6_4);
                var15_11 = 0;
                var16_12 = 0;
                var14_14 = EffectEngine.a;
                while (var16_12 < var0.n) {
                    block45: {
                        block46: {
                            block42: {
                                block47: {
                                    var17_15 = var0.ps[var16_12];
                                    if (var14_14 != 0) break block45;
                                    v0 = var17_15.age;
                                    if (var14_14 != 0) // goto
                                    if (v0 < 0.0f) break block46;
                                    v1 = var17_15.l.alpha;
                                    if (var14_14 != 0) break block47;
                                    if (v1 == 0) break block46;
                                    v1 = var15_11;
                                }
                                if (v1 == EffectEngine.order.length) {
                                    EffectEngine.order = Arrays.copyOf(EffectEngine.order, var15_11 * 2);
                                    EffectEngine.keys = Arrays.copyOf(EffectEngine.keys, var15_11 * 2);
                                }
                                var18_16 = (float)(var17_15.e.ox - var2_2) + var17_15.x - var8_5;
                                var19_17 = (float)(var17_15.e.oy - var4_3) + var17_15.y - var9_6;
                                var20_18 = (float)(var17_15.e.oz - var6_4) + var17_15.z - var10_7;
                                var21_19 = var18_16 * var18_16 + var19_17 * var19_17 + var20_18 * var20_18;
                                for (var22_21 = var15_11++; var22_21 > 0; --var22_21) {
                                    v2 = EffectEngine.keys;
                                    if (var14_14 != 0) break block42;
                                    v0 = v2[var22_21 - 1];
                                    if (var14_14 == 0) {
                                        if (!(v0 < var21_19)) break;
                                        EffectEngine.keys[var22_21] = EffectEngine.keys[var22_21 - 1];
                                        EffectEngine.order[var22_21] = EffectEngine.order[var22_21 - 1];
                                        if (var14_14 == 0) continue;
                                    }
                                }
                                v2 = EffectEngine.keys;
                            }
                            v2[var22_21] = var21_19;
                            EffectEngine.order[var22_21] = var16_12;
                        }
                        ++var16_12;
                    }
                    if (var14_14 == 0) continue;
                }
                for (var16_12 = 0; var16_12 < var15_11; ++var16_12) {
                    EffectEngine.particle(var0, var0.ps[EffectEngine.order[var16_12]], var1_1, var2_2, var4_3, var6_4, var8_5, var9_6, var10_7, var11_8, var12_9);
                    if (var14_14 == 0) {
                        if (var14_14 == 0) continue;
                    }
                    break block43;
                }
                var16_12 = 0;
            }
            while (var16_12 < var0.n) {
                block48: {
                    v3 = var0;
                    if (var14_14 != 0) break block44;
                    var17_15 = v3.ps[var16_12];
                    if (var14_14 != 0) break block48;
                    v0 = var17_15.age;
lbl54:

                    if (v0 >= 0.0f && !var17_15.l.alpha) {
                        EffectEngine.particle(var0, (EffectEngine$P)var17_15, var1_1, var2_2, var4_3, var6_4, var8_5, var9_6, var10_7, var11_8, var12_9);
                    }
                    ++var16_12;
                }
                if (var14_14 == 0) continue;
            }
            v3 = var0;
        }
        block31: for (Object var17_15 : v3.insts) {
            var18_16 = (float)(var17_15.ox - var2_2);
            var19_17 = (float)(var17_15.oy - var4_3);
            var20_18 = (float)(var17_15.oz - var6_4);
            block32: for (EffectEngine$Prim var24_23 : var17_15.prims) {
                if (var14_14 != 0) continue block31;
                v4 = var24_23;
                if (var14_14 == 0) {
                    if (v4.age < 0.0f) continue;
                    v4 = var24_23;
                }
                switch (v4.l.kind) {
                    case 3: {
                        EffectEngine.arc((EffectEngine$Inst)var17_15, var24_23, var1_1, var18_16, var19_17, var20_18, var8_5, var9_6, var10_7);
                        if (var14_14 == 0) continue block32;
                    }
                    case 5: {
                        EffectEngine.rays((EffectEngine$Inst)var17_15, var24_23, var1_1, var18_16, var19_17, var20_18, var8_5, var9_6, var10_7);
                        if (var14_14 == 0) continue block32;
                    }
                    case 4: {
                        EffectEngine.bolt((EffectEngine$Inst)var17_15, var24_23, var1_1, var18_16, var19_17, var20_18, var8_5, var9_6, var10_7);
                        if (var14_14 == 0) continue block32;
                    }
                    case 6: {
                        if (var13_10 || var0 == EffectEngine.WORLD) continue block32;
                        EffectEngine.outlineGlow((EffectEngine$Inst)var17_15, var24_23, var1_1, var18_16, var19_17, var20_18, var11_8, var12_9);
                        continue block32;
                    }
                }
                if (var14_14 == 0) continue;
            }
            if (var14_14 == 0) continue;
        }
    }

    private static void outlineGlow(EffectEngine$Inst effectEngine$Inst, EffectEngine$Prim effectEngine$Prim, EffectEngine$Out effectEngine$Out, float f, float f2, float f3, Vector3f vector3f, Vector3f vector3f2) {
        float f4;
        float f5;
        EffectEngine$Layer effectEngine$Layer;
        block4: {
            block5: {
                effectEngine$Layer = effectEngine$Prim.l;
                int n = a;
                f5 = effectEngine$Prim.age / effectEngine$Layer.life[0];
                f4 = f5;
                if (n != 0) break block4;
                if (!(f4 > 1.0f)) break block5;
                return;
            }
            f4 = EffectEngine.curveAt(effectEngine$Layer.alphaCurve, f5) * effectEngine$Layer.intensity;
        }
        float f6 = f4;
        EffectEngine.gradAt(effectEngine$Layer.color, effectEngine$Inst.pal, f5, COL);
        float f7 = Math.max(1.0f, effectEngine$Layer.edge);
        float f8 = 1.0f * effectEngine$Inst.sc;
        float f9 = 0.5f * effectEngine$Inst.sc;
        float f10 = f2 + (effectEngine$Inst.floor - (float)effectEngine$Inst.oy) + f8 * 0.95f;
        float f11 = vector3f.x * f9;
        float f12 = vector3f.y * f9;
        float f13 = vector3f.z * f9;
        float f14 = vector3f2.x * f8;
        float f15 = vector3f2.y * f8;
        float f16 = vector3f2.z * f8;
        float f17 = COL[0] * f7;
        float f18 = COL[1] * f7;
        float f19 = COL[2] * f7;
        EffectEngine.corner(effectEngine$Out, f, f10, f3, f11, f12, f13, f14, f15, f16, -1.0f, -1.0f, 1.0f, f17, f18, f19, f6, 0, 0.0f, 0.0f);
        EffectEngine.corner(effectEngine$Out, f, f10, f3, f11, f12, f13, f14, f15, f16, 1.0f, -1.0f, 1.0f, f17, f18, f19, f6, 0, 0.0f, 0.0f);
        EffectEngine.corner(effectEngine$Out, f, f10, f3, f11, f12, f13, f14, f15, f16, 1.0f, 1.0f, 1.0f, f17, f18, f19, f6, 0, 0.0f, 0.0f);
        EffectEngine.corner(effectEngine$Out, f, f10, f3, f11, f12, f13, f14, f15, f16, -1.0f, 1.0f, 1.0f, f17, f18, f19, f6, 0, 0.0f, 0.0f);
    }

    private static void collectLights(EffectEngine$Pool effectEngine$Pool, double d, double d2, double d3) {
        effectEngine$Pool.lightN = 0;
        int n = a;
        for (Object object : effectEngine$Pool.insts) {
            block17: while (true) {
                EffectEngine$Inst effectEngine$Inst = object;
                EffectEngine$Prim[] effectEngine$PrimArray = effectEngine$Inst.prims;
                int n2 = effectEngine$PrimArray.length;
                int n3 = 0;
                while (n3 < n2) {
                    block19: {
                        block20: {
                            EffectEngine$Layer effectEngine$Layer;
                            float f;
                            EffectEngine$Layer effectEngine$Layer2;
                            block25: {
                                block26: {
                                    float f2;
                                    Object object2;
                                    block23: {
                                        float f3;
                                        block24: {
                                            block22: {
                                                float f4;
                                                block21: {
                                                    object = effectEngine$PrimArray[n3];
                                                    if (n != 0) continue block17;
                                                    object2 = object;
                                                    effectEngine$Layer2 = ((EffectEngine$Prim)object2).l;
                                                    if (n != 0) break block19;
                                                    if (effectEngine$Layer2.kind != 1) break block20;
                                                    float f4 = ((EffectEngine$Prim)object2).age - 0.0f;
                                                    f4 = f4 == 0.0f ? 0 : (f4 < 0.0f ? -1 : 1);
                                                    if (n != 0) break block21;
                                                    if (f4 < 0) break block20;
                                                    f4 = effectEngine$Pool.lightN;
                                                }
                                                if (f4 != 8) break block22;
                                                break block20;
                                            }
                                            f3 = ((EffectEngine$Prim)object2).age / effectEngine$Layer2.life[0];
                                            f2 = f3;
                                            if (n != 0) break block23;
                                            if (!(f2 > 1.0f)) break block24;
                                            break block20;
                                        }
                                        f2 = EffectEngine.curveAt(effectEngine$Layer2.curve, f3) * effectEngine$Layer2.intensity;
                                    }
                                    f = f2;
                                    effectEngine$Layer = effectEngine$Layer2;
                                    if (n != 0) break block25;
                                    if (effectEngine$Layer.strikes <= 0) break block26;
                                    f *= EffectEngine.strikeVis(((EffectEngine$Prim)object2).age, effectEngine$Layer2.strikes);
                                }
                                effectEngine$Layer = effectEngine$Layer2;
                            }
                            EffectEngine.gradAt(effectEngine$Layer.color, effectEngine$Inst.pal, 0.0f, COL);
                            int n4 = effectEngine$Pool.lightN++ * 7;
                            effectEngine$Pool.lights[n4] = (float)(effectEngine$Inst.ox - d);
                            effectEngine$Pool.lights[n4 + 1] = (float)(effectEngine$Inst.oy - d2) + effectEngine$Layer2.y * effectEngine$Inst.sc;
                            effectEngine$Pool.lights[n4 + 2] = (float)(effectEngine$Inst.oz - d3);
                            effectEngine$Pool.lights[n4 + 3] = effectEngine$Layer2.radius[0] * effectEngine$Inst.sc;
                            effectEngine$Pool.lights[n4 + 4] = COL[0] * f;
                            effectEngine$Pool.lights[n4 + 5] = COL[1] * f;
                            effectEngine$Pool.lights[n4 + 6] = COL[2] * f;
                        }
                        ++n3;
                    }
                    if (n == 0) continue;
                }
                break;
            }
            if (n == 0) continue;
        }
    }

    
    private static void particle(EffectEngine$Pool var0, EffectEngine$P var1_1, EffectEngine$Out var2_2, double var3_3, double var5_4, double var7_5, float var9_6, float var10_7, float var11_8, Vector3f var12_9, Vector3f var13_10) {
        block74: {
            block91: {
                block90: {
                    block79: {
                        block84: {
                            block87: {
                                block89: {
                                    block88: {
                                        block86: {
                                            block85: {
                                                block82: {
                                                    block83: {
                                                        block80: {
                                                            block81: {
                                                                block77: {
                                                                    block78: {
                                                                        block73: {
                                                                            block76: {
                                                                                block75: {
                                                                                    var15_11 = var1_1.l;
                                                                                    var16_12 = var1_1.e;
                                                                                    var14_13 = EffectEngine.a;
                                                                                    var17_14 = Math.min(var1_1.age / var1_1.life, 1.0f);
                                                                                    EffectEngine.gradAt(var15_11.color, var16_12.pal, var17_14, EffectEngine.COL);
                                                                                    var18_15 = EffectEngine.curveAt(var15_11.alphaCurve, var17_14);
                                                                                    v0 = var15_11.flicker;
                                                                                    v1 = 0.0f;
                                                                                    if (var14_13 != 0) break block75;
                                                                                    if (v0 != v1) {
                                                                                        var19_16 = var15_11.flicker;
                                                                                        var18_15 *= 1.0f - var19_16 + var19_16 * (0.55f + 0.45f * (float)Math.sin(var1_1.age * 31.0f + var1_1.seed * 7.0f) * (float)Math.sin(var1_1.age * 17.3f + var1_1.seed));
                                                                                    }
                                                                                    v0 = var18_15;
                                                                                    if (var14_13 != 0) break block76;
                                                                                    v1 = 0.002f;
                                                                                }
                                                                                if (v0 <= v1) {
                                                                                    return;
                                                                                }
                                                                                v0 = var15_11.intensity;
                                                                            }
                                                                            var19_16 = v0;
                                                                            var20_17 = (float)(var16_12.ox - var3_3) + var1_1.x;
                                                                            var21_18 = (float)(var16_12.oy - var5_4) + var1_1.y;
                                                                            var22_19 = (float)(var16_12.oz - var7_5) + var1_1.z;
                                                                            if (var15_11.lit) {
                                                                                var23_20 = 0.25f;
                                                                                var24_21 = 0.25f;
                                                                                var25_22 = 0.26f;
                                                                                for (var26_23 = 0; var26_23 < var0.lightN; var23_20 += var0.lights[var27_25 + 4] * var30_29, var25_22 += var0.lights[var27_25 + 6] * var30_29, ++var26_23) {
                                                                                    var27_25 = var26_23 * 7;
                                                                                    var28_26 = (float)Math.sqrt(EffectEngine.sq(var0.lights[var27_25] - var20_17) + EffectEngine.sq(var0.lights[var27_25 + 1] - var21_18) + EffectEngine.sq(var0.lights[var27_25 + 2] - var22_19));
                                                                                    var29_28 = Math.max(0.0f, 1.0f - (float)Math.pow(var28_26 / var0.lights[var27_25 + 3], 4.0));
                                                                                    var30_29 = var29_28 * var29_28 / (1.0f + var28_26 * var28_26) * 0.9f;
                                                                                    v2 = var24_21;
                                                                                    v3 = var0.lights[var27_25 + 5] * var30_29;
                                                                                    if (var14_13 == 0) {
                                                                                        var24_21 = v2 + v3;
                                                                                        if (var14_13 == 0) continue;
                                                                                    }
                                                                                    break block73;
                                                                                }
                                                                                EffectEngine.COL[0] = EffectEngine.COL[0] * (var23_20 * 4.0f);
                                                                                EffectEngine.COL[1] = EffectEngine.COL[1] * (var24_21 * 4.0f);
                                                                                EffectEngine.COL[2] = EffectEngine.COL[2] * (var25_22 * 4.0f);
                                                                            }
                                                                            var23_20 = EffectEngine.COL[0] * var19_16;
                                                                            var24_21 = EffectEngine.COL[1] * var19_16;
                                                                            var25_22 = EffectEngine.COL[2] * var19_16;
                                                                            v2 = var1_1.size;
                                                                            v3 = EffectEngine.curveAt(var15_11.sizeCurve, var17_14);
                                                                        }
                                                                        var26_24 = v2 * v3;
                                                                        var27_25 = var15_11.sprite;
                                                                        v4 = var15_11.alpha;
                                                                        if (var14_13 == 0) {
                                                                            v4 = v4 != 0 ? 32 : 0;
                                                                        }
                                                                        var28_27 = var27_25 | v4;
                                                                        switch (var27_25) {
                                                                            case 2: {
                                                                                v5 = var15_11.spikes;
                                                                                break;
                                                                            }
                                                                            case 5: {
                                                                                v5 = var15_11.ring;
                                                                                break;
                                                                            }
                                                                            case 6: {
                                                                                v5 = var0.time % 100.0f;
                                                                                break;
                                                                            }
                                                                            case 7: {
                                                                                v5 = (var1_1.rot * 1.3f + var1_1.seed) % 6.2831855f;
                                                                                break;
                                                                            }
                                                                            case 4: {
                                                                                v5 = var1_1.seed + var1_1.age * 0.25f;
                                                                                break;
                                                                            }
                                                                            case 12: {
                                                                                v5 = var15_11.half;
                                                                                break;
                                                                            }
                                                                            default: {
                                                                                v5 = 0.0f;
                                                                            }
                                                                        }
                                                                        var29_28 = v5;
                                                                        var30_29 = (float)Math.cos(var1_1.rot);
                                                                        var31_30 = (float)Math.sin(var1_1.rot);
                                                                        v6 = var27_25;
                                                                        if (var14_13 != 0) break block77;
                                                                        if (v6 != 3) break block78;
                                                                        var32_31 = var1_1.vx * var1_1.st;
                                                                        var33_33 = var1_1.vy * var1_1.st;
                                                                        var34_35 = var1_1.vz * var1_1.st;
                                                                        var35_37 = (float)Math.sqrt(var32_31 * var32_31 + var33_33 * var33_33 + var34_35 * var34_35);
                                                                        var36_38 = var13_10.x;
                                                                        var37_39 = var13_10.y;
                                                                        var38_40 = var13_10.z;
                                                                        v7 = var35_37;
                                                                        if (var14_13 == 0) {
                                                                            if (v7 > 1.0E-5f) {
                                                                                var36_38 = var32_31 / var35_37;
                                                                                var37_39 = var33_33 / var35_37;
                                                                                var38_40 = var34_35 / var35_37;
                                                                            }
                                                                            v7 = var20_17 - var32_31 * 0.5f;
                                                                        }
                                                                        var39_41 = v7;
                                                                        var40_42 = var21_18 - var33_33 * 0.5f;
                                                                        var41_43 = var22_19 - var34_35 * 0.5f;
                                                                        var42_44 = var9_6 - var39_41;
                                                                        var43_45 = var10_7 - var40_42;
                                                                        var44_46 = var11_8 - var41_43;
                                                                        var45_47 = var37_39 * var44_46 - var38_40 * var43_45;
                                                                        var46_48 = var38_40 * var42_44 - var36_38 * var44_46;
                                                                        var47_49 = var36_38 * var43_45 - var37_39 * var42_44;
                                                                        v8 = var48_50 = (float)Math.sqrt(var45_47 * var45_47 + var46_48 * var46_48 + var47_49 * var47_49);
                                                                        if (var14_13 == 0) {
                                                                            if (v8 < 1.0E-6f) {
                                                                                return;
                                                                            }
                                                                            var45_47 /= var48_50;
                                                                            var46_48 /= var48_50;
                                                                            var47_49 /= var48_50;
                                                                            v8 = var35_37 * 0.5f + var26_24;
                                                                        }
                                                                        var49_51 = v8;
                                                                        var50_52 = (var49_51 - var26_24) / var26_24;
                                                                        var51_53 = var49_51 / var26_24;
                                                                        EffectEngine.corner(var2_2, var39_41, var40_42, var41_43, var45_47 * var26_24, var46_48 * var26_24, var47_49 * var26_24, var36_38 * var49_51, var37_39 * var49_51, var38_40 * var49_51, -1.0f, -1.0f, var51_53, var23_20, var24_21, var25_22, var18_15, var28_27, var29_28, var50_52);
                                                                        EffectEngine.corner(var2_2, var39_41, var40_42, var41_43, var45_47 * var26_24, var46_48 * var26_24, var47_49 * var26_24, var36_38 * var49_51, var37_39 * var49_51, var38_40 * var49_51, 1.0f, -1.0f, var51_53, var23_20, var24_21, var25_22, var18_15, var28_27, var29_28, var50_52);
                                                                        EffectEngine.corner(var2_2, var39_41, var40_42, var41_43, var45_47 * var26_24, var46_48 * var26_24, var47_49 * var26_24, var36_38 * var49_51, var37_39 * var49_51, var38_40 * var49_51, 1.0f, 1.0f, var51_53, var23_20, var24_21, var25_22, var18_15, var28_27, var29_28, var50_52);
                                                                        EffectEngine.corner(var2_2, var39_41, var40_42, var41_43, var45_47 * var26_24, var46_48 * var26_24, var47_49 * var26_24, var36_38 * var49_51, var37_39 * var49_51, var38_40 * var49_51, -1.0f, 1.0f, var51_53, var23_20, var24_21, var25_22, var18_15, var28_27, var29_28, var50_52);
                                                                        if (var14_13 == 0) break block79;
                                                                    }
                                                                    v9 = var15_11;
                                                                    if (var14_13 != 0) break block80;
                                                                    v6 = v9.orient;
                                                                }
                                                                if (v6 != 3) break block81;
                                                                var32_31 = var9_6 - var20_17;
                                                                var34_35 = var33_33 = var11_8 - var22_19;
                                                                var35_37 = -var32_31;
                                                                var36_38 = (float)Math.sqrt(var34_35 * var34_35 + var35_37 * var35_37);
                                                                if (var14_13 == 0) {
                                                                    if (var36_38 < 1.0E-6f) {
                                                                        return;
                                                                    }
                                                                    var34_35 = var34_35 / var36_38 * var26_24;
                                                                    var35_37 = var35_37 / var36_38 * var26_24;
                                                                }
                                                                var37_39 = var26_24 * var15_11.aspect;
                                                                var2_2.vert(var20_17 - var34_35, var21_18, var22_19 - var35_37, -1.0f, 0.0f, var23_20, var24_21, var25_22, var18_15, var28_27, var29_28, 0.0f);
                                                                var2_2.vert(var20_17 + var34_35, var21_18, var22_19 + var35_37, 1.0f, 0.0f, var23_20, var24_21, var25_22, var18_15, var28_27, var29_28, 0.0f);
                                                                var2_2.vert(var20_17 + var34_35, var21_18 + var37_39, var22_19 + var35_37, 1.0f, 1.0f, var23_20, var24_21, var25_22, var18_15, var28_27, var29_28, 0.0f);
                                                                var2_2.vert(var20_17 - var34_35, var21_18 + var37_39, var22_19 - var35_37, -1.0f, 1.0f, var23_20, var24_21, var25_22, var18_15, var28_27, var29_28, 0.0f);
                                                                if (var14_13 == 0) break block79;
                                                            }
                                                            v9 = var15_11;
                                                        }
                                                        v10 = v9.orient;
                                                        if (var14_13 != 0) break block82;
                                                        if (v10 != 2) break block83;
                                                        var32_31 = 1.0f;
                                                        var33_33 = 0.0f;
                                                        var34_35 = 0.0f;
                                                        var35_37 = 0.0f;
                                                        var36_38 = 0.0f;
                                                        var37_39 = 1.0f;
                                                        if (var14_13 == 0) break block84;
                                                    }
                                                    v11 = var15_11;
                                                    if (var14_13 != 0) break block85;
                                                    v10 = v11.orient;
                                                }
                                                if (v10 == 4) break block86;
                                                v11 = var15_11;
                                            }
                                            if (v11.orient != 5) break block87;
                                        }
                                        var38_40 = var1_1.nx;
                                        var39_41 = var1_1.ny;
                                        var40_42 = var1_1.nz;
                                        v12 = var15_11.orient;
                                        if (var14_13 != 0) break block88;
                                        if (v12 == 5) {
                                            var41_43 = var1_1.rot * 0.8f + var1_1.seed;
                                            var42_44 = var1_1.seed * 3.7f + var1_1.age * (1.0f + var1_1.seed % 1.0f);
                                            var38_40 = (float)(Math.sin(var41_43) * Math.cos(var42_44));
                                            var39_41 = (float)Math.cos(var41_43);
                                            var40_42 = (float)(Math.sin(var41_43) * Math.sin(var42_44));
                                        }
                                        v13 = var39_41;
                                        if (var14_13 != 0) break block89;
                                        cfr_temp_0 = Math.abs(v13) - 0.95f;
                                        v12 = cfr_temp_0 == 0.0f ? 0 : (cfr_temp_0 < 0.0f ? -1 : 1);
                                    }
                                    v13 = v12 < 0 ? 0.0f : 1.0f;
                                }
                                var41_43 = v13;
                                v14 = var39_41;
                                if (var14_13 == 0) {
                                    v14 = Math.abs(v14) < 0.95f ? 1.0f : 0.0f;
                                }
                                var42_44 = v14;
                                var32_31 = var39_41 * 0.0f - var40_42 * var42_44;
                                var33_33 = var40_42 * var41_43 - var38_40 * 0.0f;
                                var34_35 = var38_40 * var42_44 - var39_41 * var41_43;
                                var43_45 = (float)Math.sqrt(var32_31 * var32_31 + var33_33 * var33_33 + var34_35 * var34_35);
                                if (var14_13 == 0) {
                                    if (var43_45 < 1.0E-6f) {
                                        return;
                                    }
                                    var32_31 /= var43_45;
                                    var33_33 /= var43_45;
                                    var34_35 /= var43_45;
                                }
                                var35_37 = var39_41 * var34_35 - var40_42 * var33_33;
                                var36_38 = var40_42 * var32_31 - var38_40 * var34_35;
                                var37_39 = var38_40 * var33_33 - var39_41 * var32_31;
                                if (var14_13 == 0) break block84;
                            }
                            var32_31 = var12_9.x;
                            var33_33 = var12_9.y;
                            var34_35 = var12_9.z;
                            var35_37 = var13_10.x;
                            var36_38 = var13_10.y;
                            var37_39 = var13_10.z;
                        }
                        var38_40 = (var32_31 * var30_29 + var35_37 * var31_30) * var26_24;
                        var39_41 = (var33_33 * var30_29 + var36_38 * var31_30) * var26_24;
                        var40_42 = (var34_35 * var30_29 + var37_39 * var31_30) * var26_24;
                        var41_43 = (-var32_31 * var31_30 + var35_37 * var30_29) * var26_24;
                        var42_44 = (-var33_33 * var31_30 + var36_38 * var30_29) * var26_24;
                        var43_45 = (-var34_35 * var31_30 + var37_39 * var30_29) * var26_24;
                        EffectEngine.corner(var2_2, var20_17, var21_18, var22_19, var38_40, var39_41, var40_42, var41_43, var42_44, var43_45, -1.0f, -1.0f, 1.0f, var23_20, var24_21, var25_22, var18_15, var28_27, var29_28, 0.0f);
                        EffectEngine.corner(var2_2, var20_17, var21_18, var22_19, var38_40, var39_41, var40_42, var41_43, var42_44, var43_45, 1.0f, -1.0f, 1.0f, var23_20, var24_21, var25_22, var18_15, var28_27, var29_28, 0.0f);
                        EffectEngine.corner(var2_2, var20_17, var21_18, var22_19, var38_40, var39_41, var40_42, var41_43, var42_44, var43_45, 1.0f, 1.0f, 1.0f, var23_20, var24_21, var25_22, var18_15, var28_27, var29_28, 0.0f);
                        EffectEngine.corner(var2_2, var20_17, var21_18, var22_19, var38_40, var39_41, var40_42, var41_43, var42_44, var43_45, -1.0f, 1.0f, 1.0f, var23_20, var24_21, var25_22, var18_15, var28_27, var29_28, 0.0f);
                    }
                    v15 = var15_11.trailLen;
                    if (var14_13 != 0) break block90;
                    if (v15 <= 0) break block74;
                    v15 = var1_1.tn;
                }
                if (var14_13 != 0) break block91;
                if (v15 <= 1) break block74;
                v15 = Math.min(var1_1.tn + 1, 128);
            }
            var32_32 = v15;
            for (var33_34 = 0; var33_34 < var32_32; ++var33_34) {
                if (var14_13 != 0) break block74;
                v16 = var33_34;
                v17 = var32_32;
                if (var14_13 != 0) // goto
                if (v16 < v17 - 1) {
                    v16 = var1_1.th + var33_34;
                    v17 = var15_11.trailLen;
lbl239:

                    var34_36 = v16 % v17;
                    EffectEngine.RP[var33_34 * 3] = (float)(var16_12.ox - var3_3) + var1_1.trail[var34_36 * 3];
                    EffectEngine.RP[var33_34 * 3 + 1] = (float)(var16_12.oy - var5_4) + var1_1.trail[var34_36 * 3 + 1];
                    EffectEngine.RP[var33_34 * 3 + 2] = (float)(var16_12.oz - var7_5) + var1_1.trail[var34_36 * 3 + 2];
                } else {
                    EffectEngine.RP[var33_34 * 3] = var20_17;
                    EffectEngine.RP[var33_34 * 3 + 1] = var21_18;
                    EffectEngine.RP[var33_34 * 3 + 2] = var22_19;
                }
                var34_35 = (float)var33_34 / (float)(var32_32 - 1);
                EffectEngine.RW[var33_34] = var15_11.trailWidth * var16_12.sc * var34_35 * Math.min(1.0f, var18_15 * 1.5f);
                EffectEngine.gradAt(var15_11.color, var16_12.pal, Math.min(1.0f, var17_14 + (1.0f - var34_35) * 0.3f), EffectEngine.COL2);
                var35_37 = var18_15 * var34_35 * var34_35 * var19_16 * 0.8f;
                EffectEngine.RC[var33_34 * 4] = EffectEngine.COL2[0] * var35_37;
                EffectEngine.RC[var33_34 * 4 + 1] = EffectEngine.COL2[1] * var35_37;
                EffectEngine.RC[var33_34 * 4 + 2] = EffectEngine.COL2[2] * var35_37;
                EffectEngine.RC[var33_34 * 4 + 3] = var35_37 * 0.3f;
                if (var14_13 == 0) continue;
            }
            EffectEngine.ribbon(var2_2, var32_32, var9_6, var10_7, var11_8);
        }
    }

    private static double sq(double d) {
        return d * d;
    }

    private static void corner(EffectEngine$Out effectEngine$Out, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, int n, float f17, float f18) {
        effectEngine$Out.vert(f + f4 * f10 + f7 * f11, f2 + f5 * f10 + f8 * f11, f3 + f6 * f10 + f9 * f11, f10, f11 * f12, f13, f14, f15, f16, n, f17, f18);
    }

    
    private static void ribbon(EffectEngine$Out var0, int var1_1, float var2_2, float var3_3, float var4_4) {
        block11: {
            block12: {
                var5_5 = EffectEngine.a;
                v0 = var1_1;
                if (var5_5 != 0) break block11;
                if (v0 >= 2) break block12;
                return;
            }
            v0 = var6_6 = 0;
        }
        while (var6_6 < var1_1) {
            var7_7 = Math.max(0, var6_6 - 1);
            var8_9 = Math.min(var1_1 - 1, var6_6 + 1);
            var9_11 = EffectEngine.RP[var8_9 * 3] - EffectEngine.RP[var7_7 * 3];
            var10_12 = EffectEngine.RP[var8_9 * 3 + 1] - EffectEngine.RP[var7_7 * 3 + 1];
            var11_13 = EffectEngine.RP[var8_9 * 3 + 2] - EffectEngine.RP[var7_7 * 3 + 2];
            var12_14 = var2_2 - EffectEngine.RP[var6_6 * 3];
            var13_15 = var3_3 - EffectEngine.RP[var6_6 * 3 + 1];
            var14_16 = var4_4 - EffectEngine.RP[var6_6 * 3 + 2];
            var15_17 = var10_12 * var14_16 - var11_13 * var13_15;
            v3 = var11_13 * var12_14;
            v4 = var9_11 * var14_16;
            if (var5_5 == 0) {
                block15: {
                    block13: {
                        block14: {
                            var16_18 = v3 - v4;
                            var17_19 = var9_11 * var13_15 - var10_12 * var12_14;
                            var18_20 = (float)Math.sqrt(var15_17 * var15_17 + var16_18 * var16_18 + var17_19 * var17_19);
                            v5 = var18_20;
                            if (var5_5 != 0) break block13;
                            if (!(v5 < 1.0E-9f)) break block14;
                            v7 = 0.0f;
                            break block15;
                        }
                        v5 = EffectEngine.RW[var6_6];
                    }
                    v7 = v5 / var18_20;
                }
                var19_21 = v7;
                EffectEngine.RS[var6_6 * 3] = var15_17 * var19_21;
                EffectEngine.RS[var6_6 * 3 + 1] = var16_18 * var19_21;
                EffectEngine.RS[var6_6 * 3 + 2] = var17_19 * var19_21;
                ++var6_6;
                if (var5_5 == 0) continue;
            }
        }
        for (var6_6 = 0; var6_6 < var1_1 - 1; ++var6_6) {
            v3 = var6_6;
            v4 = var1_1 - 1;
lbl59:

            var7_8 = v3 / v4;
            var8_10 = (float)(var6_6 + 1) / (float)(var1_1 - 1);
            EffectEngine.stripVert(var0, var6_6, 1.0f, var7_8);
            EffectEngine.stripVert(var0, var6_6, -1.0f, var7_8);
            EffectEngine.stripVert(var0, var6_6 + 1, -1.0f, var8_10);
            EffectEngine.stripVert(var0, var6_6 + 1, 1.0f, var8_10);
            if (var5_5 == 0) continue;
        }
    }

    private static void stripVert(EffectEngine$Out effectEngine$Out, int n, float f, float f2) {
        effectEngine$Out.vert(RP[n * 3] + RS[n * 3] * f, RP[n * 3 + 1] + RS[n * 3 + 1] * f, RP[n * 3 + 2] + RS[n * 3 + 2] * f, f2, f, RC[n * 4], RC[n * 4 + 1], RC[n * 4 + 2], 1.0f, 15, 0.0f, RC[n * 4 + 3]);
    }

    private static void arc(EffectEngine$Inst effectEngine$Inst, EffectEngine$Prim effectEngine$Prim, EffectEngine$Out effectEngine$Out, float f, float f2, float f3, float f4, float f5, float f6) {
        float f7;
        float f8;
        EffectEngine$Layer effectEngine$Layer;
        block4: {
            block5: {
                effectEngine$Layer = effectEngine$Prim.l;
                int n = a;
                f8 = effectEngine$Prim.age / effectEngine$Layer.life[0];
                f7 = f8;
                if (n != 0) break block4;
                if (!(f7 > 1.0f)) break block5;
                return;
            }
            f7 = EffectEngine.arcHead(f8);
        }
        float f9 = f7;
        float f10 = EffectEngine.arcTail(f8);
        float f11 = 1.0f - (float)Math.pow(Math.max(0.0f, (f8 - 0.4f) / 0.6f), 1.2);
        float f12 = effectEngine$Layer.radius[0] * effectEngine$Inst.sc;
        float f13 = effectEngine$Layer.width * effectEngine$Inst.sc;
        float f14 = effectEngine$Layer.intensity;
        float f15 = EffectEngine.ease3((f8 - 0.06f) / 0.94f) * 0.92f;
        float f16 = Math.max(0.0f, 1.0f - f8) * 0.2f * f14;
        EffectEngine.arcSeg(effectEngine$Inst, effectEngine$Prim, effectEngine$Out, f, f2, f3, f4, f5, f6, f15, f9, 0, f13, f12, f11, f16);
        EffectEngine.arcSeg(effectEngine$Inst, effectEngine$Prim, effectEngine$Out, f, f2, f3, f4, f5, f6, f10, f9, 1, f13, f12, f11, f14);
        EffectEngine.arcSeg(effectEngine$Inst, effectEngine$Prim, effectEngine$Out, f, f2, f3, f4, f5, f6, f10 + (f9 - f10) * 0.3f, f9, 2, f13, f12, f11, f14);
    }

    
    private static void arcSeg(EffectEngine$Inst effectEngine$Inst, EffectEngine$Prim effectEngine$Prim, EffectEngine$Out effectEngine$Out, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int n, float f9, float f10, float f11, float f12) {
        block9: {
            int n2 = a;
            float f13 = f8 - f7 - 0.004f;
            float f14 = f13 == 0.0f ? 0 : (f13 < 0.0f ? -1 : 1);
            if (n2 == 0) {
                if (f14 < 0) {
                    return;
                }
                f14 = 37;
            }
            float f15 = f14;
            for (int i = 0; i < f15; ++i) {
                float f16;
                float f17;
                float f18;
                block12: {
                    block13: {
                        float f19;
                        int n3;
                        block10: {
                            block11: {
                                if (n2 != 0) break block9;
                                f18 = (float)i / 36.0f;
                                n3 = n;
                                if (n2 != 0) break block10;
                                if (n3 != 0) break block11;
                                f17 = f9 * 2.6f * (float)Math.pow(Math.sin(Math.PI * (double)Math.min(f18 * 1.1f, 1.0f)), 0.9) * (0.5f + 0.5f * f11) + 0.001f;
                                f16 = 1.0f - f17 * 0.45f / f10;
                                EffectEngine.gradAt(effectEngine$Prim.l.color, effectEngine$Inst.pal, f18 * 0.7f, COL);
                                f19 = f12 * (float)Math.pow(f18, 1.3);
                                EffectEngine.RC[i * 4] = COL[0] * f19;
                                EffectEngine.RC[i * 4 + 1] = COL[1] * f19;
                                EffectEngine.RC[i * 4 + 2] = COL[2] * f19;
                                EffectEngine.RC[i * 4 + 3] = 0.0f;
                                if (n2 == 0) break block12;
                            }
                            n3 = n;
                        }
                        if (n3 != 1) break block13;
                        f17 = f9 * (float)Math.pow(Math.sin(Math.PI * Math.pow(f18, 1.8)), 0.7) * (0.5f + 0.5f * f11) + 0.001f;
                        f16 = 1.0f - f17 * 0.5f / f10;
                        EffectEngine.gradAt(effectEngine$Prim.l.color, effectEngine$Inst.pal, f18, COL);
                        f19 = (0.12f + 0.88f * f18 * f18) * f11 * f12;
                        EffectEngine.RC[i * 4] = COL[0] * f19;
                        EffectEngine.RC[i * 4 + 1] = COL[1] * f19;
                        EffectEngine.RC[i * 4 + 2] = COL[2] * f19;
                        EffectEngine.RC[i * 4 + 3] = f19 * 0.25f * f18;
                        if (n2 == 0) break block12;
                    }
                    f17 = 0.012f * effectEngine$Inst.sc * (0.3f + 0.7f * f18) + 0.001f;
                    f16 = 1.0f;
                    float[] fArray = effectEngine$Inst.pal[0];
                    float f20 = (float)Math.pow(f18, 1.6) * f11 * f12 * 1.3f;
                    EffectEngine.RC[i * 4] = fArray[0] * f20;
                    EffectEngine.RC[i * 4 + 1] = fArray[1] * f20;
                    EffectEngine.RC[i * 4 + 2] = fArray[2] * f20;
                    EffectEngine.RC[i * 4 + 3] = f20;
                }
                EffectEngine.arcAt(effectEngine$Inst, effectEngine$Prim, f7 + (f8 - f7) * f18, f16, ARC_P);
                EffectEngine.RP[i * 3] = f + ARC_P[0];
                EffectEngine.RP[i * 3 + 1] = f2 + ARC_P[1];
                EffectEngine.RP[i * 3 + 2] = f3 + ARC_P[2];
                EffectEngine.RW[i] = f17;
                if (n2 == 0) continue;
            }
            EffectEngine.ribbon(effectEngine$Out, (int)f15, f4, f5, f6);
        }
    }

    
    private static void rays(EffectEngine$Inst var0, EffectEngine$Prim var1_1, EffectEngine$Out var2_2, float var3_3, float var4_4, float var5_5, float var6_6, float var7_7, float var8_8) {
        var10_9 = var1_1.l;
        var11_10 = var0.sc;
        var9_11 = EffectEngine.a;
        block12: for (var12_12 = 0; var12_12 < var10_9.count; ++var12_12) {
            var13_13 = var12_12 * 11;
            var14_14 = var1_1.age - var1_1.rays[var13_13 + 5];
            v0 = var14_14;
            if (var9_11 == 0) {
                v1 = 0.0f;
            }
            block13: while (!(v0 < v1)) {
                block21: {
                    v0 = var14_14 / var1_1.rays[var13_13 + 6];
lbl17:

                    var15_15 = v0;
                    v3 = var15_15;
                    if (var9_11 != 0) break block21;
                    if (v3 > 1.0f) {
                        continue block12;
                    }
                    v3 = EffectEngine.curveAt(var10_9.alphaCurve, var15_15);
                }
                var16_16 = v3;
                var17_17 = EffectEngine.ease3(var15_15 / 0.3f);
                var18_18 = var1_1.rays[var13_13] + var10_9.raySpin * var1_1.age * var1_1.rays[var13_13 + 8];
                var19_19 = var1_1.rays[var13_13 + 1];
                var20_20 = var1_1.rays[var13_13 + 2] * (0.35f + 0.65f * var17_17) * var11_10;
                var21_21 = (float)(Math.sin(var19_19) * Math.cos(var18_18));
                var22_22 = (float)Math.cos(var19_19);
                var23_23 = (float)(Math.sin(var19_19) * Math.sin(var18_18));
                var24_24 = var3_3 + var1_1.rays[var13_13 + 9] * var11_10;
                var25_25 = var4_4 + 0.04f;
                var26_26 = var5_5 + var1_1.rays[var13_13 + 10] * var11_10;
                var27_27 = var1_1.rays[var13_13 + 4];
                var28_28 = var16_16 * var10_9.intensity * var1_1.rays[var13_13 + 7] * (0.8f + 0.2f * (float)Math.sin(var1_1.age * 6.0f + var27_27) * (float)Math.sin(var1_1.age * 2.3f + var27_27 * 2.0f));
                var29_29 = var10_9.width * var1_1.rays[var13_13 + 3] * var11_10;
                var30_30 = 0;
                do {
                    if (var10_9.haze > 0.0f) {
                        v6 = 2;
                        if (var9_11 != 0) // goto
                    } else {
                        v6 = 1;
                    }
lbl56:

                    if (var30_30 >= v6) continue block12;
                    for (var31_31 = 0; var31_31 < EffectEngine.RAY_Q.length; ++var31_31) {
                        block23: {
                            var32_32 = EffectEngine.RAY_Q[var31_31];
                            EffectEngine.RP[var31_31 * 3] = var24_24 + var21_21 * var20_20 * var32_32;
                            EffectEngine.RP[var31_31 * 3 + 1] = var25_25 + var22_22 * var20_20 * var32_32;
                            EffectEngine.RP[var31_31 * 3 + 2] = var26_26 + var23_23 * var20_20 * var32_32;
                            v8 = Math.min(1.0f, var32_32 / 0.06f);
                            v1 = 1.0f;
                            if (var9_11 != 0) continue block13;
                            var33_33 = v8 * (float)Math.pow(v1 - var32_32, 1.4);
                            var34_34 = (float)Math.pow(var32_32, 0.6);
                            if (var9_11 != 0) break block23;
                            if (var30_30 == 0) {
                            }
                            EffectEngine.RW[var31_31] = var29_29 * (0.12f + 0.88f * var34_34);
                            EffectEngine.gradAt(var10_9.color, var0.pal, var32_32, EffectEngine.COL);
                            var35_35 = var28_28 * var33_33;
                            EffectEngine.RC[var31_31 * 4] = EffectEngine.COL[0] * var35_35;
                            EffectEngine.RC[var31_31 * 4 + 1] = EffectEngine.COL[1] * var35_35;
                            EffectEngine.RC[var31_31 * 4 + 2] = EffectEngine.COL[2] * var35_35;
                            EffectEngine.RC[var31_31 * 4 + 3] = var35_35 * (1.0f - var32_32) * 0.35f;
                            if (var9_11 == 0) continue;
lbl83:

                            EffectEngine.RW[var31_31] = var29_29 * var10_9.haze * (0.2f + 0.8f * var34_34);
                            EffectEngine.gradAt(var10_9.color, var0.pal, 0.3f + var32_32 * 0.7f, EffectEngine.COL);
                        }
                        var35_35 = var28_28 * var33_33 * 0.14f;
                        EffectEngine.RC[var31_31 * 4] = EffectEngine.COL[0] * var35_35;
                        EffectEngine.RC[var31_31 * 4 + 1] = EffectEngine.COL[1] * var35_35;
                        EffectEngine.RC[var31_31 * 4 + 2] = EffectEngine.COL[2] * var35_35;
                        EffectEngine.RC[var31_31 * 4 + 3] = 0.0f;
                        if (var9_11 == 0) continue;
                    }
                    EffectEngine.ribbon(var2_2, EffectEngine.RAY_Q.length, var6_6, var7_7, var8_8);
                    ++var30_30;
                } while (var9_11 == 0);
            }
            if (var9_11 == 0) continue;
        }
    }

    private static void bolt(EffectEngine$Inst effectEngine$Inst, EffectEngine$Prim effectEngine$Prim, EffectEngine$Out effectEngine$Out, float f, float f2, float f3, float f4, float f5, float f6) {
        int n;
        float f7;
        EffectEngine$Layer effectEngine$Layer;
        block31: {
            block32: {
                float f8;
                block29: {
                    block30: {
                        effectEngine$Layer = effectEngine$Prim.l;
                        f7 = EffectEngine.strikeVis(effectEngine$Prim.age, Math.max(1, effectEngine$Layer.strikes));
                        n = a;
                        float f9 = f7 - 0.0f;
                        f8 = f9 == 0.0f ? 0 : (f9 < 0.0f ? -1 : 1);
                        if (n != 0) break block29;
                        if (f8 > 0) break block30;
                        return;
                    }
                    f8 = Math.min(Math.max(1, effectEngine$Layer.strikes) - 1, (int)(effectEngine$Prim.age / 0.115f));
                }
                float f10 = f8;
                if (n != 0) break block31;
                if (f10 == effectEngine$Prim.strike) break block32;
                effectEngine$Prim.strike = (int)f10;
                effectEngine$Prim.paths = EffectEngine.boltPaths(new Random(effectEngine$Prim.seed + (long)f10 * e), effectEngine$Layer, effectEngine$Inst.sc);
            }
            EffectEngine.gradAt(effectEngine$Layer.color, effectEngine$Inst.pal, 0.0f, COL);
        }
        float[] fArray = effectEngine$Layer.glowSlot >= 0 ? effectEngine$Inst.pal[effectEngine$Layer.glowSlot] : COL;
        float[] fArray2 = fArray;
        int n2 = 0;
        while (n2 < effectEngine$Prim.paths.length) {
            block33: {
                float[] fArray3 = effectEngine$Prim.paths[n2];
                int n3 = Math.min(128, fArray3.length / 3);
                float f11 = n2 > 0 ? 0.5f : 1.0f;
                float f12 = f11;
                float f13 = n2 > 0 ? 0.55f : 1.0f;
                float f14 = f13;
                float f15 = f7 * effectEngine$Layer.intensity * f14 * 0.35f;
                float f16 = f7 * effectEngine$Layer.intensity * f14;
                int n4 = 0;
                while (n4 < 2) {
                    block34: {
                        if (n != 0) break block33;
                        for (int i = 0; i < n3; ++i) {
                            float f17;
                            block39: {
                                int n5;
                                float[] fArray4;
                                block40: {
                                    float f18;
                                    float f19;
                                    float f20;
                                    block37: {
                                        block38: {
                                            float f21;
                                            block36: {
                                                block35: {
                                                    EffectEngine.RP[i * 3] = f + fArray3[i * 3];
                                                    if (n != 0) break block34;
                                                    EffectEngine.RP[i * 3 + 1] = f2 + fArray3[i * 3 + 1];
                                                    EffectEngine.RP[i * 3 + 2] = f3 + fArray3[i * 3 + 2];
                                                    if (n2 <= 0) break block35;
                                                    f21 = 1.0f - (float)i / (float)n3;
                                                    break block36;
                                                }
                                                f21 = 1.0f;
                                            }
                                            f20 = f21;
                                            fArray4 = RW;
                                            n5 = i;
                                            f19 = effectEngine$Layer.width;
                                            f18 = effectEngine$Inst.sc;
                                            if (n != 0) break block37;
                                            f19 = f19 * f18 * f12;
                                            if (n4 != 0) break block38;
                                            f18 = 3.5f;
                                            break block37;
                                        }
                                        f18 = 1.0f;
                                    }
                                    f17 = f19 * f18;
                                    if (n != 0) break block39;
                                    fArray4[n5] = f17 * f20;
                                    if (n4 != 0) break block40;
                                    EffectEngine.RC[i * 4] = fArray2[0] * f15;
                                    EffectEngine.RC[i * 4 + 1] = fArray2[1] * f15;
                                    EffectEngine.RC[i * 4 + 2] = fArray2[2] * f15;
                                    EffectEngine.RC[i * 4 + 3] = 0.0f;
                                    if (n == 0) continue;
                                }
                                EffectEngine.RC[i * 4] = COL[0] * f16;
                                EffectEngine.RC[i * 4 + 1] = COL[1] * f16;
                                EffectEngine.RC[i * 4 + 2] = COL[2] * f16;
                                fArray4 = RC;
                                n5 = i * 4 + 3;
                                f17 = f16 * 0.4f;
                            }
                            fArray4[n5] = f17;
                            if (n == 0) continue;
                        }
                        EffectEngine.ribbon(effectEngine$Out, n3, f4, f5, f6);
                        ++n4;
                    }
                    if (n == 0) continue;
                }
                ++n2;
            }
            if (n == 0) continue;
        }
    }

    private static float[][] boltPaths(Random random, EffectEngine$Layer effectEngine$Layer, float f) {
        ArrayList<float[]> arrayList;
        block4: {
            float[] fArray = new float[3];
            ArrayList<float[]> arrayList2 = new ArrayList<float[]>();
            float f2 = (random.nextFloat() - 0.5f) * 3.0f * f;
            float f3 = effectEngine$Layer.height * f;
            float f4 = (random.nextFloat() - 0.5f) * 3.0f * f;
            float[] fArray2 = new float[195];
            fArray2[0] = f2;
            fArray2[1] = f3;
            fArray2[2] = f4;
            int[] nArray = new int[]{1};
            int n = a;
            EffectEngine.boltPath(random, f2, f3, f4, 0.0f, 0.0f, 0.0f, 6, effectEngine$Layer.jag, fArray2, nArray, fArray);
            arrayList2.add(fArray2);
            for (int i = 0; i < effectEngine$Layer.branches; ++i) {
                int n2 = 8 + random.nextInt(36);
                float f5 = fArray2[n2 * 3];
                float f6 = fArray2[n2 * 3 + 1];
                float f7 = fArray2[n2 * 3 + 2];
                float f8 = random.nextFloat() - 0.5f;
                float f9 = -0.6f - random.nextFloat() * 0.5f;
                float f10 = random.nextFloat() - 0.5f;
                float f11 = (float)Math.sqrt(f8 * f8 + f9 * f9 + f10 * f10);
                float f12 = (1.5f + random.nextFloat() * 2.5f) * f / f11;
                float[] fArray3 = new float[51];
                fArray3[0] = f5;
                fArray3[1] = f6;
                fArray3[2] = f7;
                int[] nArray2 = new int[]{1};
                EffectEngine.boltPath(random, f5, f6, f7, f5 + f8 * f12, f6 + f9 * f12, f7 + f10 * f12, 4, effectEngine$Layer.jag * 1.6f, fArray3, nArray2, fArray);
                arrayList = arrayList2;
                if (n == 0) {
                    arrayList.add(fArray3);
                    if (n == 0) continue;
                    break;
                }
                break block4;
            }
            arrayList = arrayList2;
        }
        return (float[][])arrayList.toArray((T[])new float[0][]);
    }

    private static void boltPath(Random random, float f, float f2, float f3, float f4, float f5, float f6, int n, float f7, float[] fArray, int[] nArray, float[] fArray2) {
        if (n == 0) {
            fArray[nArray[0] * 3] = f4;
            fArray[nArray[0] * 3 + 1] = f5;
            fArray[nArray[0] * 3 + 2] = f6;
            nArray[0] = nArray[0] + 1;
            return;
        }
        float f8 = (float)Math.sqrt(EffectEngine.sq(f4 - f) + EffectEngine.sq(f5 - f2) + EffectEngine.sq(f6 - f3));
        EffectEngine.randDir(random, fArray2);
        float f9 = (f + f4) / 2.0f + fArray2[0] * f8 * f7 * 0.5f;
        float f10 = (f2 + f5) / 2.0f + fArray2[1] * f8 * f7 * 0.12f;
        float f11 = (f3 + f6) / 2.0f + fArray2[2] * f8 * f7 * 0.5f;
        EffectEngine.boltPath(random, f, f2, f3, f9, f10, f11, n - 1, f7, fArray, nArray, fArray2);
        EffectEngine.boltPath(random, f9, f10, f11, f4, f5, f6, n - 1, f7, fArray, nArray, fArray2);
    }

    public static void play(EffectEngine$Effect effectEngine$Effect, Vec3d vec3d, float f) {
        EffectEngine.play(effectEngine$Effect, null, vec3d, null, f, false);
    }

    
    public static void play(EffectEngine$Effect var0, Entity var1_1, Vec3d var2_2, Vec3d var3_3, float var4_4, boolean var5_5) {
        block59: {
            block60: {
                block53: {
                    block54: {
                        block65: {
                            block52: {
                                block51: {
                                    block48: {
                                        block64: {
                                            block50: {
                                                block49: {
                                                    block63: {
                                                        block62: {
                                                            block47: {
                                                                block61: {
                                                                    block46: {
                                                                        var6_6 = EffectEngine.a;
                                                                        v0 = var0;
                                                                        if (var6_6 != 0) break block46;
                                                                        if (v0 != null) {
                                                                        }
                                                                        v0 = var0;
                                                                    }
                                                                    if (v0.def() == null) // goto
                                                                    if (var2_2 != null) break block47;
                                                                    break block61;
                                                                    catch (IllegalArgumentException v2) {
                                                                        throw v2;
                                                                    }
                                                                }
                                                                return;
                                                            }
                                                            var7_7 = MinecraftClient.method_1551();
                                                            var8_8 = var0.def();
                                                            var9_9 = Math.max(0.2f, Math.min(3.0f, var4_4));
                                                            var10_10 = 1.0f + (var9_9 - 1.0f) * 0.25f;
                                                            if (var3_3 != null) break block48;
                                                            v4 = var7_7;
                                                            if (var6_6 != 0) break block49;
                                                            break block62;
                                                            catch (IllegalArgumentException v5) {
                                                                throw v5;
                                                            }
                                                        }
                                                        if (v4.field_1773 == null) break block50;
                                                        break block63;
                                                        catch (IllegalArgumentException v6) {
                                                            throw v6;
                                                        }
                                                    }
                                                    v4 = var7_7;
                                                }
                                                v8 = v4.field_1773.method_19418().method_71156();
                                                break block64;
                                            }
                                            v8 = var2_2.method_1031(0.0, 1.6, 3.0);
                                        }
                                        var3_3 = v8;
                                    }
                                    v9 = var1_1;
                                    if (var6_6 != 0) break block51;
                                    if (v9 == null) break block52;
                                    v9 = var1_1;
                                }
                                v11 = v9.method_17682();
                                break block65;
                            }
                            v11 = 1.8f;
                        }
                        var11_11 = v11;
                        var12_12 = (float)(var3_3.field_1352 - var2_2.field_1352);
                        var13_13 = (float)(var3_3.field_1350 - var2_2.field_1350);
                        var14_14 = (float)Math.sqrt(var12_12 * var12_12 + var13_13 * var13_13);
                        if (var6_6 != 0) break block53;
                        if (!(var14_14 < 0.001f)) break block54;
                        var12_12 = 0.0f;
                        var13_13 = 1.0f;
                        var14_14 = 1.0f;
                    }
                    var12_12 /= var14_14;
                    var13_13 /= var14_14;
                }
                var15_15 = var2_2.field_1352;
                var17_16 = var2_2.field_1351;
                var19_17 = var2_2.field_1350;
                var21_18 = var12_12;
                var22_19 = 0.0f;
                var23_20 = var13_13;
                if (var6_6 == 0) {
                }
                {
                    switch (var8_8.anchor)
                }
lbl_neg_1000:

                {
                    case 0: {
                        var21_18 = (float)(var3_3.field_1352 - (var15_15 += (double)(var12_12 * 0.4f)));
                        var22_19 = (float)(var3_3.field_1351 - (var17_16 += (double)Math.min(1.2f, var11_11 * 0.67f)));
                        var23_20 = (float)(var3_3.field_1350 - (var19_17 += (double)(var13_13 * 0.4f)));
lbl103:

                        if (var6_6 == 0) break;
                    }
lbl104:

                    case 1: {
                        var17_16 += (double)(var11_11 * 0.555f);
                        if (var6_6 == 0) break;
                    }
lbl107:

                    case 4: {
                        var17_16 += (double)(var11_11 * 0.64f);
                        break;
                    }
                }
lbl110:

                var24_21 = EffectEngine.WORLD;
                synchronized (var24_21) {
                    block57: {
                        block58: {
                            block67: {
                                block68: {
                                    block56: {
                                        block55: {
                                            block66: {
                                                while (EffectEngine.WORLD.insts.size() >= 24) {
                                                    EffectEngine.dropOldest();
                                                    if (var6_6 == 0) continue;
                                                }
                                                var25_24 = EffectEngine.WORLD.start(var8_8, var0.way(), var15_15, var17_16, var19_17, var21_18, var22_19, var23_20, (float)var2_2.field_1351, var10_10);
                                                if (var1_1 != var7_7.field_1724) break block55;
                                                if (var7_7.field_1690.method_31044() != Perspective.field_26664) break block55;
                                                break block66;
                                                catch (IllegalArgumentException v14) {
                                                    throw v14;
                                                }
                                            }
                                            v15 = 1;
                                            break block56;
                                        }
                                        v15 = 0;
                                    }
                                    var26_25 = v15;
                                    v17 = var25_24;
                                    if (var1_1 == null) break block67;
                                    v18 = var26_25;
                                    if (var6_6 != 0) break block57;
                                    break block68;
                                    catch (IllegalArgumentException v19) {
                                        throw v19;
                                    }
                                }
                                if (v18 == 0) break block58;
                            }
                            v18 = -1;
                            break block57;
                        }
                        v18 = var1_1.method_5628();
                    }
                    v17.victim = v18;
                }
                if (!var5_5) break block59;
                v21 = var1_1;
                if (var6_6 != 0) break block60;
                if (v21 == null) break block59;
                v21 = var1_1;
            }
            if (v21 != var7_7.field_1724) {
                var24_22 = System.currentTimeMillis();
                EffectEngine.HIDDEN.put(var1_1.method_5628(), new long[]{var24_22 + (long)(var8_8.hide * 1000.0f), var24_22 + (long)(var8_8.dur * 1000.0f)});
            }
        }
        var24_23 = var7_7.field_1687;
        if (var24_23 == null) {
            return;
        }
        for (EffectEngine$Sound var28_28 : var8_8.sounds) {
            var29_29 = Math.max(0.5f, Math.min(2.0f, var28_28.pitch() * (1.0f - (var9_9 - 1.0f) * 0.3f)));
            var24_23.method_8486(var2_2.field_1352, var2_2.field_1351, var2_2.field_1350, var28_28.event(), SoundCategory.field_15248, var28_28.volume(), var29_29, false);
            if (var6_6 == 0) continue;
        }
    }

    private static void dropOldest() {
        EffectEngine$Inst effectEngine$Inst = EffectEngine.WORLD.insts.remove(0);
        int n = a;
        for (int i = EffectEngine.WORLD.n - 1; i >= 0; --i) {
            EffectEngine$Pool effectEngine$Pool;
            block5: {
                effectEngine$Pool = WORLD;
                if (n != 0) break block5;
                if (effectEngine$Pool.ps[i].e != effectEngine$Inst) continue;
                effectEngine$Pool = WORLD;
            }
            effectEngine$Pool.remove(i);
            if (n == 0) continue;
        }
    }

    public static boolean hidden(Entity entity) {
        long l;
        block17: {
            block18: {
                long[] lArray;
                long l2;
                int n;
                block15: {
                    long l3;
                    long[] lArray2;
                    block16: {
                        Object object;
                        block13: {
                            block14: {
                                n = a;
                                object = HIDDEN;
                                if (n != 0) break block13;
                                if (!object.isEmpty()) break block14;
                                return false;
                            }
                            object = HIDDEN.get(entity.method_5628());
                        }
                        lArray2 = (long[])object;
                        if (lArray2 == null) {
                            return false;
                        }
                        l3 = System.currentTimeMillis();
                        l2 = l3;
                        lArray = lArray2;
                        if (n != 0) break block15;
                        if (l2 < lArray[1]) break block16;
                        HIDDEN.remove(entity.method_5628());
                        return false;
                    }
                    l2 = l3;
                    lArray = lArray2;
                }
                long l4 = l2 - lArray[0];
                l = l4 == 0L ? 0 : (l4 < 0L ? -1 : 1);
                if (n != 0) break block17;
                if (l < 0) break block18;
                l = 1;
                break block17;
            }
            l = 0;
        }
        return (boolean)l;
    }

    
    public static void clear() {
        EffectEngine$Pool effectEngine$Pool = WORLD;
        synchronized (effectEngine$Pool) {
            WORLD.clear();
        }
        HIDDEN.clear();
    }

    static void tick() {
        float f;
        block11: {
            block12: {
                long l = System.nanoTime();
                int n = a;
                float f2 = lastNanos == 0L ? 0.0f : Math.min(0.05f, (float)(l - lastNanos) / 1.0E9f);
                float f3 = f2;
                lastNanos = l;
                float f4 = f3 - 0.0f;
                f = f4 == 0.0f ? 0 : (f4 > 0.0f ? 1 : -1);
                if (n != 0) break block11;
                if (f <= 0) break block12;
                f = (float)WORLD.idle();
                if (n != 0) break block11;
                if (f != false) break block12;
                WORLD.step(f3);
            }
            f = (float)HIDDEN.isEmpty();
        }
        if (f == false) {
            long l = System.currentTimeMillis();
            HIDDEN.values().removeIf(arg_0 -> EffectEngine.lambda$tick$0(l, arg_0));
        }
    }

    
    public static void onWorldRender(WorldRenderContext worldRenderContext) {
        EffectEngine$Pool effectEngine$Pool;
        int n = a;
        EffectEngine$Pool effectEngine$Pool2 = effectEngine$Pool = WORLD;
        if (n != 0) return;
        synchronized (effectEngine$Pool2) {
            EffectEngine.tick();
            if (!WORLD.idle() && !EffectGpu.active()) {
                EffectGpu.drawDirect(WORLD, worldRenderContext.matrices().method_23760().method_23761());
                return;
            }
            effectEngine$Pool2 = effectEngine$Pool;
            return;
        }
    }

    
    public static void afterWorld(Matrix4f matrix4f, Matrix4f matrix4f2, Camera camera) {
        EffectEngine$Pool effectEngine$Pool = WORLD;
        synchronized (effectEngine$Pool) {
            int n = a;
            EffectEngine.tick();
            if (n != 0) return;
            if (WORLD.idle()) return;
            if (!EffectGpu.active()) {
                return;
            }
            EffectGpu.drawWorld(WORLD, matrix4f, matrix4f2, camera);
            return;
        }
    }

    
    public static int plainOutline(Entity entity) {
        EffectEngine$Pool effectEngine$Pool;
        int n = a;
        EffectEngine$Pool effectEngine$Pool2 = effectEngine$Pool = WORLD;
        if (n == 0) {
            // MONITORENTER : effectEngine$Pool2
            if (WORLD.idle()) {
                return 0;
            }
            effectEngine$Pool2 = WORLD;
        }
        block15: for (EffectEngine$Inst effectEngine$Inst : effectEngine$Pool2.insts) {
            if (n != 0) return 0;
            EffectEngine$Inst effectEngine$Inst2 = effectEngine$Inst;
            if (n == 0) {
                if (effectEngine$Inst2.victim != entity.method_5628()) continue;
                effectEngine$Inst2 = effectEngine$Inst;
            }
            EffectEngine$Prim[] effectEngine$PrimArray = effectEngine$Inst2.prims;
            int n2 = effectEngine$PrimArray.length;
            int n3 = 0;
            while (n3 < n2) {
                block28: {
                    block29: {
                        float f;
                        float f2;
                        block27: {
                            float f3;
                            float f4;
                            EffectEngine$Layer effectEngine$Layer;
                            block31: {
                                block30: {
                                    EffectEngine$Prim effectEngine$Prim = effectEngine$PrimArray[n3];
                                    effectEngine$Layer = effectEngine$Prim.l;
                                    if (n != 0) continue block15;
                                    if (n != 0) break block28;
                                    if (effectEngine$Layer.kind != 6) break block29;
                                    float f5 = effectEngine$Prim.age;
                                    if (n != 0) break block30;
                                    if (f5 < 0.0f) break block29;
                                    f5 = f4 = effectEngine$Prim.age / effectEngine$Layer.life[0];
                                }
                                if (n != 0) break block28;
                                if (f4 > 1.0f) break block29;
                                float f6 = EffectEngine.curveAt(effectEngine$Layer.alphaCurve, f4) * effectEngine$Layer.intensity - 0.3f;
                                f3 = f6 == 0.0f ? 0 : (f6 < 0.0f ? -1 : 1);
                                if (n != 0) break block31;
                                if (f3 < 0) break block29;
                                f3 = (float)EffectGpu.active();
                            }
                            if (n == 0) {
                                if (f3 != false) {
                                    return 0;
                                }
                                EffectEngine.gradAt(effectEngine$Layer.color, effectEngine$Inst.pal, f4, PLAIN);
                                f3 = 0.0f;
                            }
                            f2 = f3;
                            for (int i = 0; i < 3; ++i) {
                                f2 = f2 << 8 | Math.round(255.0f * (float)Math.pow(Math.min(1.0f, PLAIN[i]), 0.45454545454545453));
                                if (n == 0) {
                                    if (n == 0) continue;
                                }
                                break block27;
                            }
                            f = f2;
                            if (n != 0) return (int)f;
                            if (f == false) {
                                f = 1.0f;
                                return (int)f;
                            }
                        }
                        f = f2;
                        return (int)f;
                    }
                    ++n3;
                }
                if (n == 0) continue;
            }
            if (n == 0) continue;
        }
        return 0;
    }

    static boolean outline(EffectEngine$Pool effectEngine$Pool, float[] fArray, int[] nArray) {
        int n;
        block24: {
            Iterator<EffectEngine$Inst> iterator = effectEngine$Pool.insts.iterator();
            int n2 = a;
            block18: while (iterator.hasNext()) {
                EffectEngine$Inst effectEngine$Inst;
                EffectEngine$Inst effectEngine$Inst2;
                block23: {
                    effectEngine$Inst2 = iterator.next();
                    effectEngine$Inst = effectEngine$Inst2;
                    if (n2 != 0) break block23;
                    n = effectEngine$Inst.victim;
                    if (n2 != 0) break block24;
                    if (n < 0) {
                        continue;
                    }
                    effectEngine$Inst = effectEngine$Inst2;
                }
                EffectEngine$Prim[] effectEngine$PrimArray = effectEngine$Inst.prims;
                int n3 = effectEngine$PrimArray.length;
                int n4 = 0;
                while (n4 < n3) {
                    block25: {
                        block26: {
                            block31: {
                                float f;
                                float f2;
                                EffectEngine$Layer effectEngine$Layer;
                                block29: {
                                    block30: {
                                        EffectEngine$Prim effectEngine$Prim;
                                        block27: {
                                            EffectEngine$Prim effectEngine$Prim2;
                                            block28: {
                                                effectEngine$Prim2 = effectEngine$PrimArray[n4];
                                                effectEngine$Layer = effectEngine$Prim2.l;
                                                if (n2 != 0) break block25;
                                                if (n2 != 0) continue block18;
                                                if (effectEngine$Layer.kind != 6) break block26;
                                                effectEngine$Prim = effectEngine$Prim2;
                                                if (n2 != 0) break block27;
                                                if (!(effectEngine$Prim.age < 0.0f)) break block28;
                                                break block26;
                                            }
                                            effectEngine$Prim = effectEngine$Prim2;
                                        }
                                        f2 = effectEngine$Prim.age / effectEngine$Layer.life[0];
                                        f = f2;
                                        if (n2 != 0) break block29;
                                        if (!(f > 1.0f)) break block30;
                                        break block26;
                                    }
                                    f = EffectEngine.curveAt(effectEngine$Layer.alphaCurve, f2) * effectEngine$Layer.intensity;
                                }
                                float f3 = f;
                                EffectEngine.gradAt(effectEngine$Layer.color, effectEngine$Inst2.pal, f2, COL);
                                float[] fArray2 = effectEngine$Inst2.pal[1];
                                for (int i = 0; i < 3; ++i) {
                                    fArray[i] = COL[i] * f3 * effectEngine$Layer.edge;
                                    fArray[3 + i] = fArray2[i] * f3 * effectEngine$Layer.glow;
                                    if (n2 == 0) {
                                        fArray[6 + i] = COL[i] * f3 * effectEngine$Layer.inner;
                                        if (n2 == 0) continue;
                                        break;
                                    }
                                    break block31;
                                }
                                nArray[0] = effectEngine$Inst2.victim;
                            }
                            return true;
                        }
                        ++n4;
                    }
                    if (n2 == 0) continue;
                }
                if (n2 == 0) continue;
            }
            n = 0;
        }
        return n != 0;
    }

    static int shockwaves(EffectEngine$Pool effectEngine$Pool, float[] fArray, int n, double d, double d2, double d3) {
        int n2;
        block19: {
            int n3 = 0;
            Iterator<EffectEngine$Inst> iterator = effectEngine$Pool.insts.iterator();
            int n4 = a;
            while (iterator.hasNext()) {
                EffectEngine$Prim[] effectEngine$PrimArray;
                EffectEngine$Inst effectEngine$Inst = iterator.next();
                EffectEngine$Prim[] effectEngine$PrimArray2 = effectEngine$PrimArray = effectEngine$Inst.prims;
                block17: while (true) {
                    n2 = effectEngine$PrimArray2.length;
                    if (n4 != 0) break block19;
                    int n5 = n2;
                    int n6 = 0;
                    while (n6 < n5) {
                        block20: {
                            block21: {
                                float f;
                                block26: {
                                    block27: {
                                        float f2;
                                        float f3;
                                        EffectEngine$Layer effectEngine$Layer;
                                        block24: {
                                            block25: {
                                                EffectEngine$Prim effectEngine$Prim;
                                                block23: {
                                                    float f4;
                                                    block22: {
                                                        effectEngine$PrimArray2 = effectEngine$PrimArray;
                                                        if (n4 != 0) continue block17;
                                                        effectEngine$Prim = effectEngine$PrimArray2[n6];
                                                        effectEngine$Layer = effectEngine$Prim.l;
                                                        if (n4 != 0) break block20;
                                                        if (effectEngine$Layer.kind != 2) break block21;
                                                        float f5 = effectEngine$Prim.age - 0.0f;
                                                        f4 = f5 == 0.0f ? 0 : (f5 < 0.0f ? -1 : 1);
                                                        if (n4 != 0) break block22;
                                                        if (f4 < 0) break block21;
                                                        f4 = n3;
                                                    }
                                                    if (f4 != n) break block23;
                                                    break block21;
                                                }
                                                f3 = effectEngine$Prim.age / effectEngine$Layer.life[0];
                                                f2 = f3;
                                                if (n4 != 0) break block24;
                                                if (!(f2 > 1.0f)) break block25;
                                                break block21;
                                            }
                                            f2 = 1.0f - (float)Math.pow(1.0f - f3, 2.2);
                                        }
                                        float f6 = f2;
                                        int n7 = n3++ * 7;
                                        fArray[n7] = (float)(effectEngine$Inst.ox - d);
                                        fArray[n7 + 1] = (float)(effectEngine$Inst.oy - d2) + effectEngine$Layer.y * effectEngine$Inst.sc;
                                        fArray[n7 + 2] = (float)(effectEngine$Inst.oz - d3);
                                        fArray[n7 + 3] = (effectEngine$Layer.radius[0] + (effectEngine$Layer.radius[1] - effectEngine$Layer.radius[0]) * f6) * effectEngine$Inst.sc;
                                        float[] fArray2 = fArray;
                                        int n8 = n7 + 4;
                                        f = effectEngine$Layer.width;
                                        if (n4 != 0) break block26;
                                        fArray2[n8] = f * effectEngine$Inst.sc * (1.0f - f3 * 0.4f);
                                        fArray[n7 + 5] = effectEngine$Layer.strength * (1.0f - f3) * (1.0f - f3 * 0.3f);
                                        fArray2 = fArray;
                                        n8 = n7 + 6;
                                        if (!effectEngine$Layer.ground) break block27;
                                        f = 1.0f;
                                        break block26;
                                    }
                                    f = 0.0f;
                                }
                                fArray2[n8] = f;
                            }
                            ++n6;
                        }
                        if (n4 == 0) continue;
                    }
                    break;
                }
                if (n4 == 0) continue;
            }
            n2 = n3;
        }
        return n2;
    }

    
    private static void seek(EffectEngine$Pool var0, EffectEngine$Effect var1_1, float var2_2, float var3_3, String var4_4) {
        block28: {
            block27: {
                block23: {
                    block26: {
                        block24: {
                            block25: {
                                var6_5 = var1_1.def();
                                var5_6 = EffectEngine.a;
                                v0 = var3_3;
                                if (var5_6 != 0) break block24;
                                if (v0 < var0.time - 1.0E-4f) break block25;
                                v1 = var0;
                                if (var5_6 != 0) // goto
                                if (v1.time != 0.0f) break block26;
                                v1 = var0;
                                if (var5_6 != 0) // goto
                                if (!v1.insts.isEmpty()) break block26;
                            }
                            var0.clear();
                            var0.rnd = new Random((var1_1.id() + var4_4).hashCode());
                            var0.time = 0.0f;
                            v0 = 1.0f + (Math.max(0.2f, Math.min(3.0f, var2_2)) - 1.0f) * 0.25f;
                        }
                        var7_7 = v0;
                        switch (var6_5.anchor) {
                            case 0: {
                                v2 = 1.2f;
                                break;
                            }
                            case 1: {
                                v2 = 1.0f;
                                break;
                            }
                            case 4: {
                                v2 = 1.15f;
                                break;
                            }
                            default: {
                                v2 = 0.0f;
                            }
                        }
                        var8_8 = v2;
                        var9_9 = var6_5.anchor == 0 ? -0.4f : 0.0f;
                        var0.start(var6_5, var1_1.way(), 0.0, var8_8, var9_9, 0.0f, var6_5.anchor == 0 ? 0.3f : 0.0f, -1.0f, 0.0f, var7_7);
                    }
                    do {
                        v1 = var0;
lbl38:

                        if (!(v1.time < var3_3 - 1.0E-4f)) break;
                        v3 = var0;
                        if (var5_6 != 0) break block23;
                        v3.step(Math.min(0.016666668f, var3_3 - var0.time));
                    } while (var5_6 == 0);
                    v3 = var0;
                }
                if (var5_6 != 0) break block27;
                if (v3.insts.isEmpty()) break block28;
                v3 = var0.insts.get(0);
            }
            ((EffectEngine$Inst)v3).pal = var6_5.pals[Math.max(0, Math.min(var6_5.pals.length - 1, var1_1.way()))];
        }
    }

    private static EffectEngine$Pool tilePool(EffectEngine$Effect effectEngine$Effect) {
        EffectEngine$Def effectEngine$Def;
        block4: {
            EffectEngine$Def effectEngine$Def2;
            block5: {
                effectEngine$Def2 = effectEngine$Effect.def();
                int n = a;
                effectEngine$Def = effectEngine$Def2;
                if (n != 0) break block4;
                if (effectEngine$Def.tile != null) break block5;
                effectEngine$Def2.tile = new EffectEngine$Pool(EffectEngine.capacity(effectEngine$Def2), 0L);
                EffectEngine.fitTile(effectEngine$Effect);
            }
            effectEngine$Def = effectEngine$Def2;
        }
        return effectEngine$Def.tile;
    }

    private static int capacity(EffectEngine$Def effectEngine$Def) {
        int n;
        int n2;
        block5: {
            int n3 = 16;
            EffectEngine$Layer[] effectEngine$LayerArray = effectEngine$Def.layers;
            int n4 = effectEngine$LayerArray.length;
            int n5 = a;
            int n6 = 0;
            while (n6 < n4) {
                block4: {
                    EffectEngine$Layer effectEngine$Layer = effectEngine$LayerArray[n6];
                    if (n5 != 0) break block4;
                    n2 = n3;
                    n = effectEngine$Layer.count;
                    if (n5 != 0) break block5;
                    n3 = n2 + n;
                    if (effectEngine$Layer.shed != null) {
                        n3 += (int)(effectEngine$Layer.shed.rate * effectEngine$Layer.life[0]) + 4;
                    }
                    ++n6;
                }
                if (n5 == 0) continue;
            }
            n2 = 3000;
            n = n3;
        }
        return Math.min(n2, n);
    }

    private static void fitTile(EffectEngine$Effect effectEngine$Effect) {
        block2: {
            EffectEngine$Def effectEngine$Def = effectEngine$Effect.def();
            float f = 0.3f;
            float f2 = 0.0f;
            int n = a;
            float f3 = 0.3f;
            for (int i = 1; i <= 12; ++i) {
                EffectEngine.seek(effectEngine$Def.tile, effectEngine$Effect, 1.0f, effectEngine$Def.dur * (float)i / 12.0f * 0.7f, b[38]);
                if (n == 0) {
                    float[] fArray = new float[]{f, f2, f3};
                    EffectEngine.tileBuild(effectEngine$Def.tile, (arg_0, arg_1, arg_2, arg_3) -> EffectEngine.lambda$fitTile$1(fArray, arg_0, arg_1, arg_2, arg_3));
                    f = fArray[0];
                    f2 = fArray[1];
                    f3 = fArray[2];
                    if (n == 0) continue;
                }
                break block2;
            }
            effectEngine$Def.tileMid = (f2 + f3) / 2.0f;
            effectEngine$Def.tileScale = 1.0f / Math.max(f, Math.max(f3 - effectEngine$Def.tileMid, effectEngine$Def.tileMid - f2));
            effectEngine$Def.tile.clear();
            effectEngine$Def.tile.time = 0.0f;
        }
    }

    private static void tileBuild(EffectEngine$Pool effectEngine$Pool, EffectEngine$PreviewSink effectEngine$PreviewSink) {
        EffectEngine.build(effectEngine$Pool, new EffectEngine$SpeckOut((arg_0, arg_1, arg_2, arg_3, arg_4) -> EffectEngine.lambda$tileBuild$2(effectEngine$PreviewSink, arg_0, arg_1, arg_2, arg_3, arg_4)), 0.0, 0.0, 0.0, 0.0f, 0.6f, -1000.0f, TILE_R, TILE_U, false);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static float previewLife(EffectEngine$Effect effectEngine$Effect) {
        EffectEngine$Effect effectEngine$Effect2;
        block7: {
            int n;
            block6: {
                n = a;
                effectEngine$Effect2 = effectEngine$Effect;
                if (n != 0) break block6;
                if (effectEngine$Effect2 == null) return 1.0f;
                effectEngine$Effect2 = effectEngine$Effect;
            }
            if (n != 0) return effectEngine$Effect2.def().dur;
            if (effectEngine$Effect2.def() != null) break block7;
            return 1.0f;
        }
        effectEngine$Effect2 = effectEngine$Effect;
        return effectEngine$Effect2.def().dur;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static float hideAt(EffectEngine$Effect effectEngine$Effect) {
        EffectEngine$Effect effectEngine$Effect2;
        block7: {
            block8: {
                int n;
                block6: {
                    n = a;
                    effectEngine$Effect2 = effectEngine$Effect;
                    if (n != 0) break block6;
                    if (effectEngine$Effect2 == null) return Float.MAX_VALUE;
                    effectEngine$Effect2 = effectEngine$Effect;
                }
                if (n != 0) break block7;
                if (effectEngine$Effect2.def() != null) break block8;
                return Float.MAX_VALUE;
            }
            effectEngine$Effect2 = effectEngine$Effect;
        }
        float f = effectEngine$Effect2.def().hide;
        return f;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void preview(EffectEngine$Effect effectEngine$Effect, float f, EffectEngine$PreviewSink effectEngine$PreviewSink) {
        EffectEngine$Effect effectEngine$Effect2;
        block7: {
            block8: {
                int n;
                block6: {
                    n = a;
                    effectEngine$Effect2 = effectEngine$Effect;
                    if (n != 0) break block6;
                    if (effectEngine$Effect2 == null) return;
                    effectEngine$Effect2 = effectEngine$Effect;
                }
                if (n != 0) break block7;
                if (effectEngine$Effect2.def() != null) break block8;
                return;
            }
            effectEngine$Effect2 = effectEngine$Effect;
        }
        EffectEngine$Def effectEngine$Def = effectEngine$Effect2.def();
        EffectEngine$Pool effectEngine$Pool = EffectEngine.tilePool(effectEngine$Effect);
        EffectEngine.seek(effectEngine$Pool, effectEngine$Effect, 1.0f, Math.max(0.0f, f), b[120]);
        float f2 = effectEngine$Def.tileScale;
        float f3 = effectEngine$Def.tileMid;
        EffectEngine.tileBuild(effectEngine$Pool, (arg_0, arg_1, arg_2, arg_3) -> EffectEngine.lambda$preview$3(f2, f3, effectEngine$PreviewSink, arg_0, arg_1, arg_2, arg_3));
    }

    private static EffectEngine$Pool stagePool(EffectEngine$Effect effectEngine$Effect, float f) {
        EffectEngine$Def effectEngine$Def;
        block10: {
            EffectEngine$Def effectEngine$Def2;
            block11: {
                block8: {
                    block9: {
                        int n = a;
                        effectEngine$Def2 = effectEngine$Effect.def();
                        if (n != 0) break block8;
                        if (effectEngine$Def2.stage == null) break block9;
                        effectEngine$Def = effectEngine$Def2;
                        if (n != 0) break block10;
                        if (effectEngine$Def.stagePower == f) break block11;
                    }
                    effectEngine$Def2.stage = new EffectEngine$Pool(EffectEngine.capacity(effectEngine$Def2), 0L);
                }
                effectEngine$Def2.stagePower = f;
            }
            effectEngine$Def = effectEngine$Def2;
        }
        return effectEngine$Def.stage;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void worldPreview(EffectEngine$Effect effectEngine$Effect, float f, float f2, LocalSpeckSink localSpeckSink) {
        EffectEngine$Effect effectEngine$Effect2;
        block7: {
            block8: {
                int n;
                block6: {
                    n = a;
                    effectEngine$Effect2 = effectEngine$Effect;
                    if (n != 0) break block6;
                    if (effectEngine$Effect2 == null) return;
                    effectEngine$Effect2 = effectEngine$Effect;
                }
                if (n != 0) break block7;
                if (effectEngine$Effect2.def() != null) break block8;
                return;
            }
            effectEngine$Effect2 = effectEngine$Effect;
        }
        EffectEngine$Pool effectEngine$Pool = EffectEngine.stagePool(effectEngine$Effect2, f);
        EffectEngine.seek(effectEngine$Pool, effectEngine$Effect, f, Math.max(0.0f, f2), b[58]);
        EffectEngine.build(effectEngine$Pool, new EffectEngine$SpeckOut(localSpeckSink), 0.0, 0.0, 0.0, 0.0f, 1.0f, -1000.0f, TILE_R, TILE_U, false);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static PreviewScene$Geometry stage(EffectEngine$Effect effectEngine$Effect, float f, float f2) {
        EffectEngine$Effect effectEngine$Effect2;
        block6: {
            int n = a;
            effectEngine$Effect2 = effectEngine$Effect;
            if (n != 0) break block6;
            if (effectEngine$Effect2 == null) return null;
            effectEngine$Effect2 = effectEngine$Effect;
        }
        if (effectEngine$Effect2.def() != null && !(f2 < 0.0f)) return (arg_0, arg_1, arg_2) -> EffectEngine.lambda$stage$4(effectEngine$Effect, f, f2, arg_0, arg_1, arg_2);
        return null;
    }

    private static void lambda$stage$4(EffectEngine$Effect effectEngine$Effect, float f, float f2, VertexConsumer vertexConsumer, Matrix4f matrix4f, Vec3d vec3d) {
        EffectEngine$Pool effectEngine$Pool = EffectEngine.stagePool(effectEngine$Effect, f);
        EffectEngine.seek(effectEngine$Pool, effectEngine$Effect, f, f2, b[138]);
        EffectGpu.drawStage(effectEngine$Pool, matrix4f, vec3d);
    }

    private static void lambda$preview$3(float f, float f2, EffectEngine$PreviewSink effectEngine$PreviewSink, float f3, float f4, float f5, int n) {
        block8: {
            float f6;
            float f7;
            float f8;
            block7: {
                f8 = f3 * f;
                f7 = (f4 - f2) * f;
                int n2 = a;
                f6 = f8;
                if (n2 != 0) break block7;
                if (!(Math.abs(f6) <= 1.4f)) break block8;
                f6 = Math.abs(f7);
            }
            if (f6 <= 1.4f) {
                effectEngine$PreviewSink.speck(f8, f7, f5 * f, n);
            }
        }
    }

    private static void lambda$tileBuild$2(EffectEngine$PreviewSink effectEngine$PreviewSink, float f, float f2, float f3, float f4, int n) {
        effectEngine$PreviewSink.speck(f, f2 * 0.9f + f3 * 0.45f, f4, n);
    }

    private static void lambda$fitTile$1(float[] fArray, float f, float f2, float f3, int n) {
        if (n >>> 24 < 60) {
            return;
        }
        fArray[0] = Math.max(fArray[0], Math.abs(f));
        fArray[1] = Math.min(fArray[1], f2);
        fArray[2] = Math.max(fArray[2], f2);
    }

    private static boolean lambda$tick$0(long l, long[] lArray) {
        long l2;
        block2: {
            block3: {
                int n = a;
                long l3 = l - lArray[1];
                l2 = l3 == 0L ? 0 : (l3 < 0L ? -1 : 1);
                if (n != 0) break block2;
                if (l2 < 0) break block3;
                l2 = 1;
                break block2;
            }
            l2 = 0;
        }
        return (boolean)l2;
    }

    
    static {
        block30: {
            block29: {
                block28: {
                    block27: {
                        block26: {
                            var15 = new String[143];
                            var13_1 = 0;
                            var12_2 = "\u000fBWzvY\t\u0013FKikR\u0006\u0006T\u0005\u0013NAxn\u0005\u0014N]ij\u0006\u0004UVhlU\u0004\u0001NUq\u0006\u0011F]twB\u0007\u0010SXzeT\u0018\u0006\u000bBPzjE\u0004\u000bN]x\u0003\u0002C]\u0004\u000eFPs\u0005\u0013NM~j\u0004\u0001H]d\u0007\fRMqk_\u000f\u0003\u0001HA\u0005\u0013BM|n\u0006\u0002DZxlE\u0006\fUPxlE\u0006\u000fF@xpB\u0002\nC\u0005\u0010JVvg\b\u0014N]ijg\u000b\u0011\u0004\u0006^\\n\u0003\u0002UZ\u0004\u0010WPs\u0005\u0000O\\nv\u0004\u0007NJ~\u0004\u0011NWz\u0004\u000eHVs\u0003\u0001HA\u0005\u0017UXtn\u0005\u0000FKo{\u0006\u0010SKxcZ\u0004\u000eFPs\u0004\u000bFU{\u0002\nC\u0003\u000fNM\u0004\u0017NUx\u0007\u0005KP~iT\u0018\b\u0010SKxlV\u001e\u000b\u0004\u0006JPi\u0004\u0007UXz\t\u0013FKikR\u0006\u0006T\u0007\u0005KLivT\u0018\u0004\u000fBXs\u0004\u0010O\\y\u0003\u0013FN\u0006\u0010WKtvT\u0005\u0002KIuc\t\u0000HUrpF\u000b\u001aT\u0004\u0005B\\i\u0005\u0000HUrp\u0004\u0000HWx\u0005\u0000HLsv\u0004\u000bBTt\u0004\u0010O\\y\u0004\u0010WPs\u0005\u0010SXzg\u0005\u0010W\\xf\u0005\u0013HPsv\u0004\u0000HKx\u0005\u0000FKo{\u0006\u0010WPvgB\t\rH\u0019qcH\u000f\u0011T\u0004\u0010WXs\u0004\bNWy\u0006\fUPxlE\u0003\fRM\u0004\u0011F@n\u0003\u0011HM\u0004\u0010NCx\b\u0014N]ijg\u000b\u0011\u0005\u0007BU|{\u0004\u0001FJx\u0005\u000fF[xn\t\nIMxlB\u0003\u0017^\u0005\u000fN^uv\u0002\u0015^\u0005\u0000HLsv\u0002\nI\u0006\u0004UVhlU\u0005\u0013KXsg\u0005\nIWxp\u0006\u0010WQxpT\u0004\u0011HUq\u0003\u0000FM\u0002\u0016W\u0004\u0010H_i\u0004\u0006C^x\u0006\u0002IZumC\f\rH\u0019~m]\u0005\u0011PXdq\u0004\u0001BXp\u0006\tNMigC\u0006\u0002DZxlE\u0004\u0011FMx\u0006\u0010WQxpT\u0003\u000fBW\u0005\u0017UXtn\u001c\rHMuk_\rCSQtq\u0011\t\u000fN\\sv\u0011\t\u0002I\u0019ypP\u001d\u0005\u0000RKkg\t\u0010OV~iF\u000b\u0015B\u0004\u0001HUi\u0007\u0000HUqkU\u000f\u0003\u000bNM\u0006\u0017RT\u007fnT\u0003\u0007RK\u0005\u0010OXof\u0005\u0001K\\sf\u0005\u000bBXov\u0007\u0004UXkkE\u0013\u0005\u0005KXog\u0006\u0015HUhoT\t\u0010NCxAD\u0018\u0015B\u0003\u0007NK\u0006\u0002TIxaE\u0007\u0010SKxvR\u0002\u0006\u0017HN|pU\u0007\u0000HUqkU\u000f\u0005\u0002KIuc\u0004\u0017NUx\u0004\u000bFCx\u0004\u0011NWz\u0004\u0001BXp\u0004\u0001NUq\u0006\nDP~nT\u0005\u0014N]ij\u0007\u0001KVnq^\u0007\u0004\u0004KVj\u0003\u000fNM\u0003\u0015BU\u0004\fQ\\o\u0005\u0010HLsf\b\u0001UXsaY\u000f\u0010\u0004\u0011FMx\u0004\u000fN_x\u0004\u0004KVj\u0004\u0000HKx\u0005\u0010SXzg\u0005\u0010PPon\u0004\u0017NUi";
                            var14_3 = "\u000fBWzvY\t\u0013FKikR\u0006\u0006T\u0005\u0013NAxn\u0005\u0014N]ij\u0006\u0004UVhlU\u0004\u0001NUq\u0006\u0011F]twB\u0007\u0010SXzeT\u0018\u0006\u000bBPzjE\u0004\u000bN]x\u0003\u0002C]\u0004\u000eFPs\u0005\u0013NM~j\u0004\u0001H]d\u0007\fRMqk_\u000f\u0003\u0001HA\u0005\u0013BM|n\u0006\u0002DZxlE\u0006\fUPxlE\u0006\u000fF@xpB\u0002\nC\u0005\u0010JVvg\b\u0014N]ijg\u000b\u0011\u0004\u0006^\\n\u0003\u0002UZ\u0004\u0010WPs\u0005\u0000O\\nv\u0004\u0007NJ~\u0004\u0011NWz\u0004\u000eHVs\u0003\u0001HA\u0005\u0017UXtn\u0005\u0000FKo{\u0006\u0010SKxcZ\u0004\u000eFPs\u0004\u000bFU{\u0002\nC\u0003\u000fNM\u0004\u0017NUx\u0007\u0005KP~iT\u0018\b\u0010SKxlV\u001e\u000b\u0004\u0006JPi\u0004\u0007UXz\t\u0013FKikR\u0006\u0006T\u0007\u0005KLivT\u0018\u0004\u000fBXs\u0004\u0010O\\y\u0003\u0013FN\u0006\u0010WKtvT\u0005\u0002KIuc\t\u0000HUrpF\u000b\u001aT\u0004\u0005B\\i\u0005\u0000HUrp\u0004\u0000HWx\u0005\u0000HLsv\u0004\u000bBTt\u0004\u0010O\\y\u0004\u0010WPs\u0005\u0010SXzg\u0005\u0010W\\xf\u0005\u0013HPsv\u0004\u0000HKx\u0005\u0000FKo{\u0006\u0010WPvgB\t\rH\u0019qcH\u000f\u0011T\u0004\u0010WXs\u0004\bNWy\u0006\fUPxlE\u0003\fRM\u0004\u0011F@n\u0003\u0011HM\u0004\u0010NCx\b\u0014N]ijg\u000b\u0011\u0005\u0007BU|{\u0004\u0001FJx\u0005\u000fF[xn\t\nIMxlB\u0003\u0017^\u0005\u000fN^uv\u0002\u0015^\u0005\u0000HLsv\u0002\nI\u0006\u0004UVhlU\u0005\u0013KXsg\u0005\nIWxp\u0006\u0010WQxpT\u0004\u0011HUq\u0003\u0000FM\u0002\u0016W\u0004\u0010H_i\u0004\u0006C^x\u0006\u0002IZumC\f\rH\u0019~m]\u0005\u0011PXdq\u0004\u0001BXp\u0006\tNMigC\u0006\u0002DZxlE\u0004\u0011FMx\u0006\u0010WQxpT\u0003\u000fBW\u0005\u0017UXtn\u001c\rHMuk_\rCSQtq\u0011\t\u000fN\\sv\u0011\t\u0002I\u0019ypP\u001d\u0005\u0000RKkg\t\u0010OV~iF\u000b\u0015B\u0004\u0001HUi\u0007\u0000HUqkU\u000f\u0003\u000bNM\u0006\u0017RT\u007fnT\u0003\u0007RK\u0005\u0010OXof\u0005\u0001K\\sf\u0005\u000bBXov\u0007\u0004UXkkE\u0013\u0005\u0005KXog\u0006\u0015HUhoT\t\u0010NCxAD\u0018\u0015B\u0003\u0007NK\u0006\u0002TIxaE\u0007\u0010SKxvR\u0002\u0006\u0017HN|pU\u0007\u0000HUqkU\u000f\u0005\u0002KIuc\u0004\u0017NUx\u0004\u000bFCx\u0004\u0011NWz\u0004\u0001BXp\u0004\u0001NUq\u0006\nDP~nT\u0005\u0014N]ij\u0007\u0001KVnq^\u0007\u0004\u0004KVj\u0003\u000fNM\u0003\u0015BU\u0004\fQ\\o\u0005\u0010HLsf\b\u0001UXsaY\u000f\u0010\u0004\u0011FMx\u0004\u000fN_x\u0004\u0004KVj\u0004\u0000HKx\u0005\u0010SXzg\u0005\u0010PPon\u0004\u0017NUi".length();
                            var11_4 = 6;
                            var10_5 = -1;
lbl7:

                            while (true) {
                                v0 = ++var10_5;
                                v1 = var12_2.substring(v0, v0 + var11_4);
                                v2 = -1;
                                break block26;
                                break;
                            }
lbl12:

                            while (true) {
                                var15[var13_1++] = v3.intern();
                                if ((var10_5 += var11_4) < var14_3) {
                                    var11_4 = var12_2.charAt(var10_5);
                                    continue;
                                }
                                var12_2 = "\tF^\u0007\u0010SKtiT\u0019";
                                var14_3 = "\tF^\u0007\u0010SKtiT\u0019".length();
                                var11_4 = 3;
                                var10_5 = -1;
lbl21:

                                while (true) {
                                    v4 = ++var10_5;
                                    v1 = var12_2.substring(v4, v4 + var11_4);
                                    v2 = 0;
                                    break block26;
                                    break;
                                }
                                break;
                            }
lbl26:

                            while (true) {
                                var15[var13_1++] = v3.intern();
                                if ((var10_5 += var11_4) < var14_3) {
                                    var11_4 = var12_2.charAt(var10_5);
                                    continue;
                                }
                                break block27;
                                break;
                            }
                        }
                        v5 = v1.toCharArray();
                        v6 = v5;
                        v7 = v5.length;
                        var16_7 = 0;
                        do {
                            v6 = v6;
                            v8 = var16_7;
                            v9 = v6[v8];
                            switch (var16_7 % 7) {
                                case 0: {
                                    v10 = 99;
                                    break;
                                }
                                case 1: {
                                    v10 = 39;
                                    break;
                                }
                                case 2: {
                                    v10 = 57;
                                    break;
                                }
                                case 3: {
                                    v10 = 29;
                                    break;
                                }
                                case 4: {
                                    v10 = 2;
                                    break;
                                }
                                case 5: {
                                    v10 = 49;
                                    break;
                                }
                                default: {
                                    v10 = 106;
                                }
                            }
                            v6[v8] = (char)(v9 ^ v10);
                            ++var16_7;
lbl65:

                            v7 = v7;
                        } while (v7 > var16_7);
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
                    EffectEngine.b = var15;
                    var2_8 = 1656598860938895846L;
                    var8_9 = new long[14];
                    var5_10 = 0;
                    var6_11 = "\u00beu\u00e0\u00cbH:/%\u00a7\u00bf\u0091\u009f\u00ff2\u0099\u00b0\u008d\b\u00e2H\u00e22\u00a3<\u00a6\u00c1\u000b\n\u00ab\u00f4\u00c14\u00bc\u0019\u0019/\u00d6\u0013\u001e\u00da\u00f2\u0082\u001f\u00995\u009e\u00f21\u001c(\u00d8r\u00d1H\u00c7v\u009a\u001fw\u00dd\u0083\u00cfQD\u00aeW\u00c3\u0010@(/\u000fy\u001d\u00eay\u0001\u00b1R\u000eyv\u00b5\u00c7c\u00f7\u00d2\u007f\u00e05\u00d2\u0090^:&o";
                    var7_12 = "\u00beu\u00e0\u00cbH:/%\u00a7\u00bf\u0091\u009f\u00ff2\u0099\u00b0\u008d\b\u00e2H\u00e22\u00a3<\u00a6\u00c1\u000b\n\u00ab\u00f4\u00c14\u00bc\u0019\u0019/\u00d6\u0013\u001e\u00da\u00f2\u0082\u001f\u00995\u009e\u00f21\u001c(\u00d8r\u00d1H\u00c7v\u009a\u001fw\u00dd\u0083\u00cfQD\u00aeW\u00c3\u0010@(/\u000fy\u001d\u00eay\u0001\u00b1R\u000eyv\u00b5\u00c7c\u00f7\u00d2\u007f\u00e05\u00d2\u0090^:&o".length();
                    var4_13 = 0;
                    while (true) {
                        var9_14 = var6_11.substring(var4_13, var4_13 += 8).getBytes("ISO-8859-1");
                        v11 = var8_9;
                        v12 = var5_10++;
                        v13 = ((long)var9_14[0] & 255L) << 56 | ((long)var9_14[1] & 255L) << 48 | ((long)var9_14[2] & 255L) << 40 | ((long)var9_14[3] & 255L) << 32 | ((long)var9_14[4] & 255L) << 24 | ((long)var9_14[5] & 255L) << 16 | ((long)var9_14[6] & 255L) << 8 | (long)var9_14[7] & 255L;
                        v14 = -1;
                        break block28;
                        break;
                    }
lbl89:

                    while (true) {
                        v11[v12] = v15;
                        if (var4_13 < var7_12) continue;
                        var6_11 = "\u0005\u0001\u00cc\u001d4[\u001b\u00b1\u0095=\u00faI\u00dc\u00c3\u0007\u00ff";
                        var7_12 = "\u0005\u0001\u00cc\u001d4[\u001b\u00b1\u0095=\u00faI\u00dc\u00c3\u0007\u00ff".length();
                        var4_13 = 0;
                        while (true) {
                            var9_14 = var6_11.substring(var4_13, var4_13 += 8).getBytes("ISO-8859-1");
                            v11 = var8_9;
                            v12 = var5_10++;
                            v13 = ((long)var9_14[0] & 255L) << 56 | ((long)var9_14[1] & 255L) << 48 | ((long)var9_14[2] & 255L) << 40 | ((long)var9_14[3] & 255L) << 32 | ((long)var9_14[4] & 255L) << 24 | ((long)var9_14[5] & 255L) << 16 | ((long)var9_14[6] & 255L) << 8 | (long)var9_14[7] & 255L;
                            v14 = 0;
                            break block28;
                            break;
                        }
                        break;
                    }
lbl102:

                    while (true) {
                        v11[v12] = v15;
                        if (var4_13 < var7_12) continue;
                        break block29;
                        break;
                    }
                }
                v15 = v13 ^ var2_8;
                switch (v14) {
                    default: {
                        continue;
                    }
                    case 0:
lbl113:

                    continue;
                }
            }
            EffectEngine.c = var8_9;
            EffectEngine.d = new Integer[14];
            EffectEngine.BEAM = EffectEngine.a(20493, 1816606782001008367L);
            EffectEngine.DISC = EffectEngine.a(14462, 474547173084407442L);
            EffectEngine.RIB = EffectEngine.a(3512, 807578878750659415L);
            EffectEngine.TUMBLE = EffectEngine.a(30971, 7796656760098874908L);
            EffectEngine.GROUND = EffectEngine.a(14462, 474547173084407442L);
            EffectEngine.BOLT = EffectEngine.a(11851, 4517372451777884326L);
            EffectEngine.STRIP = EffectEngine.a(25053, 9131430938017234739L);
            EffectEngine.RING = EffectEngine.a(30971, 7796656760098874908L);
            EffectEngine.HEMI = EffectEngine.a(14462, 474547173084407442L);
            EffectEngine.HEART = EffectEngine.a(22884, 6977369816175626117L);
            EffectEngine.BOX = EffectEngine.a(11851, 4517372451777884326L);
            EffectEngine.UP = EffectEngine.a(11851, 4517372451777884326L);
            EffectEngine.SHOCKWAVE = EffectEngine.a(14462, 474547173084407442L);
            EffectEngine.ARC = EffectEngine.a(26302, 6514188867626006613L);
            EffectEngine.RAYS = EffectEngine.a(30971, 7796656760098874908L);
            EffectEngine.CHEST = EffectEngine.a(11851, 4517372451777884326L);
            EffectEngine.OUTLINE = EffectEngine.a(20493, 1816606782001008367L);
            EffectEngine.SHARD = EffectEngine.a(11646, 3416405456617452446L);
            EffectEngine.IN = EffectEngine.a(20493, 1816606782001008367L);
            EffectEngine.GROUND_A = EffectEngine.a(26302, 6514188867626006613L);
            EffectEngine.BEAMO = EffectEngine.a(26302, 6514188867626006613L);
            EffectEngine.OUTD = EffectEngine.a(26302, 6514188867626006613L);
            EffectEngine.TOWARD = EffectEngine.a(30971, 7796656760098874908L);
            EffectEngine.FEET = EffectEngine.a(14462, 474547173084407442L);
            EffectEngine.ALPHA = EffectEngine.a(25745, 7707819808356530807L);
            EffectEngine.PLANE = EffectEngine.a(11851, 4517372451777884326L);
            EffectEngine.STREAK = EffectEngine.a(26302, 6514188867626006613L);
            EffectEngine.SMOKE = EffectEngine.a(11851, 4517372451777884326L);
            EffectEngine.MAX_PARTICLES = EffectEngine.a(9907, 7886263918046558288L);
            EffectEngine.ERING = EffectEngine.a(26302, 6514188867626006613L);
            break block30;
lbl148:

            while (true) {
                continue;
                break;
            }
        }
        var0_15 = 3159524560827544084L;
        while (true)
        EffectEngine.e = 3159524560827542779L ^ var0_15;
        v16 = new String[7];
        var10_6 = EffectEngine.b;
        v16[0] = var10_6[43];
        v16[1] = var10_6[77];
        v16[2] = var10_6[101];
        v16[3] = var10_6[24];
        v16[4] = var10_6[102];
        v16[5] = var10_6[69];
        v16[6] = var10_6[14];
        EffectEngine.KINDS = v16;
        EffectEngine.SPRITES = new String[]{var10_6[136], var10_6[88], var10_6[111], var10_6[33], var10_6[21], var10_6[122], var10_6[92], var10_6[107], var10_6[16], var10_6[127], var10_6[47], var10_6[23], var10_6[109], var10_6[29], var10_6[2], null, var10_6[125], var10_6[86]};
        EffectEngine.ARC_OUT = new float[6];
        EffectEngine.COL = new float[3];
        EffectEngine.COL2 = new float[3];
        EffectEngine.RP = new float[384];
        EffectEngine.RW = new float[128];
        EffectEngine.RC = new float[512];
        EffectEngine.RS = new float[384];
        EffectEngine.order = new int[256];
        EffectEngine.keys = new float[256];
        EffectEngine.ARC_P = new float[6];
        EffectEngine.RAY_Q = new float[]{0.0f, 0.06f, 0.16f, 0.32f, 0.52f, 0.75f, 1.0f};
        EffectEngine.WORLD = new EffectEngine$Pool(3000, System.nanoTime());
        EffectEngine.HIDDEN = new ConcurrentHashMap<Integer, long[]>();
        EffectEngine.PLAIN = new float[3];
        EffectEngine.TILE_R = new Vector3f(1.0f, 0.0f, 0.0f);
        EffectEngine.TILE_U = new Vector3f(0.0f, 1.0f, 0.0f);
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1EEA;
        if (d[n2] == null) {
            EffectEngine.d[n2] = (int)(c[n2] ^ l);
        }
        return d[n2];
    }
}
