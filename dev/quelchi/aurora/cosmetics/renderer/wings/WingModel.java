package dev.quelchi.aurora.cosmetics.renderer.wings;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.quelchi.aurora.cosmetics.renderer.wings.WingModel$Bone;
import dev.quelchi.aurora.cosmetics.renderer.wings.WingModel$Face;
import dev.quelchi.aurora.cosmetics.renderer.wings.WingModel$Motion;
import dev.quelchi.aurora.cosmetics.renderer.wings.WingModel$Pose;
import dev.quelchi.aurora.cosmetics.renderer.wings.WingModel$Quad;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.joml.Vector3fc;

public final class WingModel {
    final List<WingModel$Bone> roots;
    final WingModel$Motion motion;
    NativeImage image;
    Identifier texture;
    private static final String[] DIRS;
    private float[] bounds;
    private float front = Float.NaN;
    private static final int[] CCW;
    private static final Vector3f TURN;
    private static final Vector3f A;
    private static final Vector3f B;
    private static final Vector3f N;
    public static boolean a;
    private static final String[] b;

    private WingModel(List<WingModel$Bone> list, WingModel$Motion wingModel$Motion, NativeImage nativeImage) {
        this.roots = list;
        this.motion = wingModel$Motion;
        this.image = nativeImage;
    }

    public Identifier texture() {
        return this.texture;
    }

    
    static WingModel parse(String var0) throws Exception {
        block64: {
            block61: {
                block75: {
                    block60: {
                        block71: {
                            block72: {
                                block58: {
                                    block56: {
                                        block57: {
                                            block67: {
                                                block55: {
                                                    block53: {
                                                        block54: {
                                                            block66: {
                                                                block51: {
                                                                    block52: {
                                                                        block65: {
                                                                            block50: {
                                                                                var2_1 = JsonParser.parseString((String)var0).getAsJsonObject();
                                                                                var1_2 = WingModel.a;
                                                                                var17_3 = WingModel.b;
                                                                                var3_4 = var2_1.getAsJsonArray(var17_3[37]);
                                                                                v0 = var3_4;
                                                                                if (var1_2) break block50;
                                                                                if (v0 != null) {
                                                                                }
                                                                                v0 = var3_4;
                                                                            }
                                                                            if (var1_2) break block51;
                                                                            if (!v0.isEmpty()) break block52;
                                                                            break block65;
                                                                            catch (IllegalArgumentException v2) {
                                                                                throw v2;
                                                                            }
                                                                        }
                                                                        throw new IllegalArgumentException(WingModel.b[40]);
                                                                    }
                                                                    v0 = var3_4.get(0);
                                                                }
                                                                var4_5 = v0.getAsJsonObject();
                                                                var17_3 = WingModel.b;
                                                                var5_6 = var4_5.get(var17_3[6]).getAsString();
                                                                var6_7 = var5_6.indexOf(44);
                                                                v4 = Base64.getDecoder();
                                                                v5 = var6_7 < 0 ? var5_6 : var5_6.substring(var6_7 + 1);
                                                                var7_8 = NativeImage.method_49277((byte[])v4.decode(v5));
                                                                var17_3 = WingModel.b;
                                                                var8_9 = var2_1.getAsJsonObject(var17_3[44]);
                                                                v7 = var4_5;
                                                                if (var1_2) break block53;
                                                                if (!v7.has(var17_3[5])) break block54;
                                                                break block66;
                                                                catch (IllegalArgumentException v8) {
                                                                    throw v8;
                                                                }
                                                            }
                                                            v9 = var4_5.get(WingModel.b[34]).getAsFloat();
                                                            break block55;
                                                        }
                                                        var17_3 = WingModel.b;
                                                        v7 = var8_9.get(var17_3[0]);
                                                    }
                                                    v9 = v7.getAsFloat();
                                                }
                                                var9_10 = v9;
                                                v11 = var4_5;
                                                if (var1_2) break block56;
                                                if (!v11.has(WingModel.b[33])) break block57;
                                                break block67;
                                                catch (IllegalArgumentException v12) {
                                                    throw v12;
                                                }
                                            }
                                            v13 = var4_5.get(WingModel.b[1]).getAsFloat();
                                            break block58;
                                        }
                                        var17_3 = WingModel.b;
                                        v11 = var8_9.get(var17_3[46]);
                                    }
                                    v13 = v11.getAsFloat();
                                }
                                var10_11 = v13;
                                var11_12 = new HashMap<String, JsonObject>();
                                var17_3 = WingModel.b;
                                for (Object var13_14 : var2_1.getAsJsonArray(var17_3[16])) {
                                    block59: {
                                        block70: {
                                            block69: {
                                                block68: {
                                                    var14_15 /* !! */  = var13_14.getAsJsonObject();
                                                    v15 = var14_15 /* !! */ ;
                                                    if (var1_2) break block59;
                                                    if (!v15.has(WingModel.b[27])) // goto
                                                    break block68;
                                                    catch (IllegalArgumentException v16) {
                                                        throw v16;
                                                    }
                                                }
                                                v15 = WingModel.b[25];
                                                if (var1_2) break block59;
                                                break block69;
                                                catch (IllegalArgumentException v17) {
                                                    throw v17;
                                                }
                                            }
                                            if (!v15.equals(var14_15 /* !! */ .get(WingModel.b[31]).getAsString())) break block59;
                                            break block70;
                                            catch (IllegalArgumentException v18) {
                                                throw v18;
                                            }
                                        }
                                        v15 = var11_12.put(var14_15 /* !! */ .get(WingModel.b[11]).getAsString(), var14_15 /* !! */ );
                                    }
                                    if (!var1_2) continue;
                                }
                                var12_13 = new HashMap<K, V>();
                                v20 = var2_1;
                                if (var1_2) break block71;
                                if (!v20.has(WingModel.b[35])) break block60;
                                break block72;
                                catch (IllegalArgumentException v21) {
                                    throw v21;
                                }
                            }
                            v20 = var2_1;
                        }
                        var17_3 = WingModel.b;
                        for (JsonObject var14_15 : v20.getAsJsonArray(var17_3[13])) {
                            var15_16 /* !! */  = var14_15 /* !! */ .getAsJsonObject();
                            var17_3 = WingModel.b;
                            var12_13.put(var15_16 /* !! */ .get(var17_3[23]).getAsString(), var15_16 /* !! */ );
                            if (!var1_2) continue;
                        }
                    }
                    var13_14 = new ArrayList<E>();
                    var17_3 = WingModel.b;
                    var14_15 /* !! */  = new WingModel$Bone(var17_3[42], new Vector3f(), new Vector3f());
                    for (JsonElement var16_17 : var2_1.getAsJsonArray(var17_3[17])) {
                        block63: {
                            block62: {
                                block74: {
                                    block73: {
                                        if (var1_2) break block61;
                                        if (var1_2) break block62;
                                        break block73;
                                        catch (IllegalArgumentException v23) {
                                            throw v23;
                                        }
                                    }
                                    if (!var16_17.isJsonObject()) // goto
                                    break block74;
                                    catch (IllegalArgumentException v24) {
                                        throw v24;
                                    }
                                }
                                var13_14.add(WingModel.bone(var16_17.getAsJsonObject(), (Map<String, JsonObject>)var12_13, var11_12, var9_10, var10_11));
                            }
                            if (!var1_2) break block63;
lbl162:

                            WingModel.cube((WingModel$Bone)var14_15 /* !! */ , (JsonObject)var11_12.get(var16_17.getAsString()), var9_10, var10_11);
                        }
                        if (!var1_2) continue;
                    }
                    v27 = var14_15 /* !! */ .faces.isEmpty();
                    if (var1_2) break block61;
                    if (v27) break block61;
                    break block75;
                    catch (IllegalArgumentException v28) {
                        throw v28;
                    }
                }
                v27 = var13_14.add(var14_15 /* !! */ );
            }
            var15_16 /* !! */  = WingModel$Motion.FEATHER;
            v30 = var2_1;
            if (!var1_2) {
                if (!v30.has(WingModel.b[38])) break block64;
            }
            try {
                v30 = var2_1.get(WingModel.b[9]);
lbl193:

                var15_16 /* !! */  = WingModel$Motion.valueOf(v30.getAsString().toUpperCase(Locale.ROOT));
            }
            catch (IllegalArgumentException var16_18) {
            }
        }
        return new WingModel((List<WingModel$Bone>)var13_14, (WingModel$Motion)var15_16 /* !! */ , var7_8);
    }

    
    private static WingModel$Bone bone(JsonObject jsonObject, Map<String, JsonObject> map, Map<String, JsonObject> map2, float f, float f2) {
        boolean bl = a;
        JsonObject jsonObject2 = jsonObject;
        if (!bl) {
            jsonObject2 = jsonObject2.has(b[23]) ? map.getOrDefault(jsonObject.get(b[23]).getAsString(), jsonObject) : jsonObject;
        }
        JsonObject jsonObject3 = jsonObject2;
        String[] stringArray = b;
        WingModel$Bone wingModel$Bone = new WingModel$Bone(jsonObject3.has(b[3]) ? jsonObject3.get(b[7]).getAsString() : "", WingModel.vec(jsonObject3, stringArray[18]), WingModel.vec(jsonObject3, stringArray[12]));
        for (JsonElement jsonElement : jsonObject.getAsJsonArray(stringArray[15])) {
            block13: {
                block12: {
                    block11: {
                        if (bl) break block11;
                        if (!jsonElement.isJsonObject()) break block12;
                        wingModel$Bone.kids.add(WingModel.bone(jsonElement.getAsJsonObject(), map, map2, f, f2));
                    }
                    if (!bl) break block13;
                }
                WingModel.cube(wingModel$Bone, map2.get(jsonElement.getAsString()), f, f2);
            }
            if (!bl) continue;
        }
        return wingModel$Bone;
    }

    private static Vector3f vec(JsonObject jsonObject, String string) {
        JsonObject jsonObject2;
        block4: {
            block5: {
                boolean bl = a;
                jsonObject2 = jsonObject;
                if (bl) break block4;
                if (jsonObject2.has(string)) break block5;
                return new Vector3f();
            }
            jsonObject2 = jsonObject;
        }
        JsonArray jsonArray = jsonObject2.getAsJsonArray(string);
        return new Vector3f(jsonArray.get(0).getAsFloat(), jsonArray.get(1).getAsFloat(), jsonArray.get(2).getAsFloat());
    }

    
    private static void cube(WingModel$Bone var0, JsonObject var1_1, float var2_2, float var3_3) {
        block65: {
            block66: {
                block64: {
                    block63: {
                        var4_4 = WingModel.a;
                        v0 = var1_1;
                        if (var4_4) break block63;
                        if (v0 == null) break block64;
                        v0 = var1_1;
                    }
                    if (var4_4) break block65;
                    if (!v0.has(WingModel.b[26])) break block66;
                    v0 = var1_1;
                    if (var4_4) break block65;
                    if (v0.get(WingModel.b[2]).getAsBoolean()) break block66;
                }
                return;
            }
            v0 = var1_1;
        }
        var35_5 = WingModel.b;
        var5_6 = WingModel.vec(v0, var35_5[14]);
        var6_7 = WingModel.vec(var1_1, var35_5[49]);
        v1 = var1_1;
        if (var4_4) // goto
        if (v1.has(var35_5[29])) {
            v1 = var1_1.get(WingModel.b[48]);
lbl24:

            v2 = v1.getAsFloat();
        } else {
            v2 = 0.0f;
        }
        var7_8 = v2;
        var8_9 = var5_6.x - var7_8;
        var9_10 = var5_6.y - var7_8;
        var10_11 = var5_6.z - var7_8;
        var11_12 = var6_7.x + var7_8;
        var12_13 = var6_7.y + var7_8;
        var13_14 = var6_7.z + var7_8;
        var35_5 = WingModel.b;
        var14_15 = WingModel.turn(WingModel.vec(var1_1, var35_5[30]), WingModel.vec(var1_1, var35_5[36]));
        var15_16 = var1_1.getAsJsonObject(var35_5[41]);
        var16_17 = WingModel.DIRS;
        var17_18 = var16_17.length;
        var18_19 = 0;
        while (var18_19 < var17_18) {
            block62: {
                block68: {
                    block61: {
                        block72: {
                            block71: {
                                block70: {
                                    block69: {
                                        block67: {
                                            var19_20 = var16_17[var18_19];
                                            v3 = var15_16;
                                            if (var4_4) break block67;
                                            if (!v3.has(var19_20)) break block68;
                                            v3 = var20_21 = var15_16.getAsJsonObject(var19_20);
                                        }
                                        if (var4_4) break block62;
                                        if (!var20_21.has(WingModel.b[45])) break block68;
                                        v4 = var20_21;
                                        if (var4_4) break block69;
                                        if (v4.get(WingModel.b[21]).isJsonNull()) break block68;
                                        v4 = var20_21;
                                    }
                                    var35_5 = WingModel.b;
                                    var21_22 = v4.getAsJsonArray(var35_5[22]);
                                    var22_23 = var21_22.get(0).getAsFloat() / var2_2;
                                    var23_24 = var21_22.get(1).getAsFloat() / var3_3;
                                    var24_25 = var21_22.get(2).getAsFloat() / var2_2;
                                    var25_26 = var21_22.get(3).getAsFloat() / var3_3;
                                    var27_28 = var19_20;
                                    var28_29 = -1;
                                    v5 = var27_28.hashCode();
                                    if (var4_4) break block70;
                                    switch (v5) {
                                        case 105007365: {
                                            v5 = (int)var27_28.equals(WingModel.b[43]);
                                            if (!var4_4) {
                                                if (v5 == 0) break;
                                                var28_29 = 0;
                                                if (!var4_4) break;
                                            }
                                            break block70;
                                        }
                                        case 109627853: {
                                            v5 = (int)var27_28.equals(WingModel.b[8]);
                                            if (!var4_4) {
                                                if (v5 == 0) break;
                                                var28_29 = 1;
                                                if (!var4_4) break;
                                            }
                                            break block70;
                                        }
                                        case 3105789: {
                                            v5 = (int)var27_28.equals(WingModel.b[19]);
                                            if (!var4_4) {
                                                if (v5 == 0) break;
                                                var28_29 = 2;
                                                if (!var4_4) break;
                                            }
                                            break block70;
                                        }
                                        case 3645871: {
                                            v5 = (int)var27_28.equals(WingModel.b[32]);
                                            if (!var4_4) {
                                                if (v5 == 0) break;
                                                var28_29 = 3;
                                                if (!var4_4) break;
                                            }
                                            break block70;
                                        }
                                        case 3739: {
                                            v5 = (int)var27_28.equals(WingModel.b[39]);
                                            if (var4_4) break block70;
                                            if (v5 == 0) break;
                                            var28_29 = 4;
                                        }
                                    }
                                    v5 = var28_29;
                                }
                                if (var4_4) break block71;
                                switch (v5) {
                                    case 0: {
                                        v6 = new float[4][];
                                        v6[0] = new float[]{var11_12, var12_13, var10_11};
                                        v6[1] = new float[]{var8_9, var12_13, var10_11};
                                        v6[2] = new float[]{var8_9, var9_10, var10_11};
                                        v7 = v6;
                                        v6[3] = new float[]{var11_12, var9_10, var10_11};
                                        break block72;
                                    }
                                    case 1: {
                                        v8 = new float[4][];
                                        v8[0] = new float[]{var8_9, var12_13, var13_14};
                                        v8[1] = new float[]{var11_12, var12_13, var13_14};
                                        v8[2] = new float[]{var11_12, var9_10, var13_14};
                                        v7 = v8;
                                        v8[3] = new float[]{var8_9, var9_10, var13_14};
                                        break block72;
                                    }
                                    case 2: {
                                        v9 = new float[4][];
                                        v9[0] = new float[]{var11_12, var12_13, var13_14};
                                        v9[1] = new float[]{var11_12, var12_13, var10_11};
                                        v9[2] = new float[]{var11_12, var9_10, var10_11};
                                        v7 = v9;
                                        v9[3] = new float[]{var11_12, var9_10, var13_14};
                                        break block72;
                                    }
                                    case 3: {
                                        v10 = new float[4][];
                                        v10[0] = new float[]{var8_9, var12_13, var10_11};
                                        v10[1] = new float[]{var8_9, var12_13, var13_14};
                                        v10[2] = new float[]{var8_9, var9_10, var13_14};
                                        v7 = v10;
                                        v10[3] = new float[]{var8_9, var9_10, var10_11};
                                        break block72;
                                    }
                                    case 4: {
                                        v11 = new float[4][];
                                        v11[0] = new float[]{var8_9, var12_13, var10_11};
                                        v11[1] = new float[]{var11_12, var12_13, var10_11};
                                        v11[2] = new float[]{var11_12, var12_13, var13_14};
                                        v7 = v11;
                                        v11[3] = new float[]{var8_9, var12_13, var13_14};
                                        break block72;
                                    }
                                    default: {
                                        v5 = 4;
                                    }
                                }
                            }
                            v12 = new float[v5][];
                            v12[0] = new float[]{var8_9, var9_10, var13_14};
                            v12[1] = new float[]{var11_12, var9_10, var13_14};
                            v12[2] = new float[]{var11_12, var9_10, var10_11};
                            v7 = v12;
                            v12[3] = new float[]{var8_9, var9_10, var10_11};
                        }
                        var26_27 = v7;
                        var27_28 = new float[][]{{var22_23, var23_24}, {var24_25, var23_24}, {var24_25, var25_26}, {var22_23, var25_26}};
                        v13 = var20_21;
                        if (var4_4) // goto
                        if (v13.has(WingModel.b[12])) {
                            v13 = var20_21.get(WingModel.b[12]);
lbl158:

                            v14 = Math.floorMod(v13.getAsInt() / 90, 4);
                        } else {
                            v14 = 0;
                        }
                        var28_29 = v14;
                        var29_30 = new float[4][];
                        for (var30_32 = 0; var30_32 < 4; ++var30_32) {
                            v15 = var29_30;
                            if (!var4_4) {
                                v15[var30_32] = (float[])var27_28[(var30_32 - var28_29 + 4) % 4];
                                if (!var4_4) continue;
                            }
                            break block61;
                        }
                        v15 = var26_27;
                    }
                    for (float[] var33_35 : v15) {
                        var34_36 = var14_15.transformPosition(new Vector3f(var33_35[0], var33_35[1], var33_35[2]));
                        if (!var4_4) {
                            var33_35[0] = var34_36.x;
                            var33_35[1] = var34_36.y;
                            var33_35[2] = var34_36.z;
                            if (!var4_4) continue;
                        }
                        break block62;
                    }
                    var0.faces.add(new WingModel$Face(var26_27, var29_30));
                }
                ++var18_19;
            }
            if (!var4_4) continue;
        }
    }

    static Matrix4f turn(Vector3f vector3f, Vector3f vector3f2) {
        return new Matrix4f().translate((Vector3fc)vector3f).rotateZYX((float)Math.toRadians(vector3f2.z), (float)Math.toRadians(vector3f2.y), (float)Math.toRadians(vector3f2.x)).translate(-vector3f.x, -vector3f.y, -vector3f.z);
    }

    public float[] bounds() {
        WingModel wingModel;
        block2: {
            block3: {
                boolean bl = a;
                wingModel = this;
                if (bl) break block2;
                if (wingModel.bounds != null) break block3;
                float[] fArray = new float[]{Float.MAX_VALUE, Float.MAX_VALUE, -3.4028235E38f, -3.4028235E38f};
                this.faces(WingModel::lambda$bounds$0, (arg_0, arg_1) -> WingModel.lambda$bounds$1(fArray, arg_0, arg_1));
                this.bounds = fArray;
            }
            wingModel = this;
        }
        return wingModel.bounds;
    }

    public float front() {
        WingModel wingModel;
        block11: {
            block12: {
                float f;
                block16: {
                    float[] fArray;
                    block14: {
                        float[] fArray2;
                        block15: {
                            boolean bl = a;
                            wingModel = this;
                            if (bl) break block11;
                            if (!Float.isNaN(wingModel.front)) break block12;
                            fArray2 = new float[]{Float.MAX_VALUE};
                            this.faces(WingModel::lambda$front$2, (arg_0, arg_1) -> WingModel.lambda$front$3(fArray2, arg_0, arg_1));
                            for (WingModel$Bone wingModel$Bone : this.roots) {
                                block13: {
                                    if (bl) break block12;
                                    if (wingModel$Bone.side == 0) break block13;
                                    fArray2[0] = Math.min(fArray2[0], wingModel$Bone.pivot.z);
                                }
                                if (!bl) continue;
                            }
                            WingModel wingModel2 = this;
                            fArray = fArray2;
                            if (bl) break block14;
                            if (fArray[0] != Float.MAX_VALUE) break block15;
                            f = 2.0f;
                            break block16;
                        }
                        fArray = fArray2;
                    }
                    f = fArray[0];
                }
                wingModel2.front = f;
            }
            wingModel = this;
        }
        return wingModel.front;
    }

    public void faces(WingModel$Pose wingModel$Pose, WingModel$Quad wingModel$Quad) {
        Iterator<WingModel$Bone> iterator = this.roots.iterator();
        boolean bl = a;
        while (iterator.hasNext()) {
            WingModel$Bone wingModel$Bone = iterator.next();
            WingModel.faces(wingModel$Pose, wingModel$Quad, wingModel$Bone, new Matrix4f());
            if (!bl) continue;
        }
    }

    
    private static void faces(WingModel$Pose var0, WingModel$Quad var1_1, WingModel$Bone var2_2, Matrix4f var3_3) {
        WingModel.TURN.set(0.0f);
        var4_4 = WingModel.a;
        var0.add(var2_2, WingModel.TURN);
        var5_5 = new Matrix4f((Matrix4fc)var3_3).mul((Matrix4fc)WingModel.turn(var2_2.pivot, WingModel.TURN.add((Vector3fc)var2_2.rest)));
        var6_6 = var2_2.faces.iterator();
        while (var6_6.hasNext()) {
            v0 = var6_6.next();
            if (var4_4) // goto
            var7_7 = v0;
            var8_8 = new Vector3f[4];
            for (var9_9 = 0; var9_9 < 4; ++var9_9) {
                v0 = var8_8;
                if (!var4_4) {
                    v0[var9_9] = var5_5.transformPosition(new Vector3f(var7_7.pos()[var9_9][0], var7_7.pos()[var9_9][1], var7_7.pos()[var9_9][2]));
                    if (!var4_4) continue;
                    break;
                }
            }
            var1_1.add(var8_8, var7_7.uv());
            if (!var4_4) continue;
        }
        for (Vector3f[] v0 : var2_2.kids) lbl_neg_1000:

        {
            var7_7 = (WingModel$Bone)v0;
            WingModel.faces(var0, var1_1, (WingModel$Bone)var7_7, var5_5);
            if (!var4_4) continue;
        }
    }

    void emit(MatrixStack.Entry entry, VertexConsumer vertexConsumer, int n, WingModel$Pose wingModel$Pose) {
        this.faces(wingModel$Pose, (arg_0, arg_1) -> WingModel.lambda$emit$4(vertexConsumer, entry, n, arg_0, arg_1));
    }

    private static void lambda$emit$4(VertexConsumer vertexConsumer, MatrixStack.Entry entry, int n, Vector3f[] vector3fArray, float[][] fArray) {
        boolean bl;
        block7: {
            Vector3f vector3f;
            block8: {
                vector3fArray[1].sub((Vector3fc)vector3fArray[0], A);
                bl = a;
                vector3f = vector3fArray[3].sub((Vector3fc)vector3fArray[0], B);
                if (bl) break block7;
                B.cross((Vector3fc)A, N);
                if (!(N.lengthSquared() > 0.0f)) break block8;
                N.normalize();
                if (!bl) break block7;
            }
            vector3f = N.set(0.0f, 0.0f, 1.0f);
        }
        for (int n2 : CCW) {
            vertexConsumer.method_56824(entry, vector3fArray[n2].x, vector3fArray[n2].y, vector3fArray[n2].z).method_39415(-1).method_22913(fArray[n2][0], fArray[n2][1]).method_22922(OverlayTexture.field_21444).method_60803(n).method_60831(entry, WingModel.N.x, WingModel.N.y, WingModel.N.z);
            if (!bl) continue;
        }
    }

    private static void lambda$front$3(float[] fArray, Vector3f[] vector3fArray, float[][] fArray2) {
        Vector3f[] vector3fArray2 = vector3fArray;
        boolean bl = a;
        for (Vector3f vector3f : vector3fArray2) {
            fArray[0] = Math.min(fArray[0], vector3f.z);
            if (!bl) continue;
        }
    }

    private static void lambda$front$2(WingModel$Bone wingModel$Bone, Vector3f vector3f) {
    }

    private static void lambda$bounds$1(float[] fArray, Vector3f[] vector3fArray, float[][] fArray2) {
        Vector3f[] vector3fArray2 = vector3fArray;
        boolean bl = a;
        for (Vector3f vector3f : vector3fArray2) {
            fArray[0] = Math.min(fArray[0], vector3f.x);
            fArray[1] = Math.min(fArray[1], vector3f.y);
            fArray[2] = Math.max(fArray[2], vector3f.x);
            fArray[3] = Math.max(fArray[3], vector3f.y);
            if (!bl) continue;
        }
    }

    private static void lambda$bounds$0(WingModel$Bone wingModel$Bone, Vector3f vector3f) {
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[50];
                var3_1 = 0;
                var2_2 = "J\\b5X\tHCY)U\u0011\u0018UA\nK\\u(R\u0011\u0013TA\u007f\u0004STk$\u0004XTu5\bHCY6Y\u001c\u000bU\u0006NZs3S\u001d\u0004STk$\u0005NZs5X\r\\@t.B\u0019 PZr(_\u0016\u0005SZt5X\u0004H@o%\bOZr D\u0011\u0010S\u0006ZGi4@\u000b\u0004[Gi,\b^]o-T\n\u001aS\bXYc,U\u0016\u000bN\bR@r-Y\u0016\u001aO\u0006RGo&Y\u0016\u0004XTu5\u0005NZs5X\u0007IP~5E\n\u001a\u0002HC\u0004H@o%\u0004YZq/\u0004^@d$\nK\\u(R\u0011\u0013TA\u007f\u0004ILv$\u0002HE\u0007T[`-Q\f\u001a\u0006RGo&Y\u0016\u0004ILv$\u0004JPu5\tHCY)U\u0011\u0018UA\bHCY6Y\u001c\u000bU\u0006ZGi4@\u000b\bOZr D\u0011\u0010S\bIP~5E\n\u001aN\r\\@t.B\u0019 PZr(_\u0016\u0002HE\nSZ&5U\u0000\u000bHGc\u0005[Te$C\u0004OZi5\u0005SZt5X\nOPu.\\\r\u000bTZh\u0007IP~5E\n\u001a\u0006UPo&X\f\u0004JPu5";
                var4_3 = "J\\b5X\tHCY)U\u0011\u0018UA\nK\\u(R\u0011\u0013TA\u007f\u0004STk$\u0004XTu5\bHCY6Y\u001c\u000bU\u0006NZs3S\u001d\u0004STk$\u0005NZs5X\r\\@t.B\u0019 PZr(_\u0016\u0005SZt5X\u0004H@o%\bOZr D\u0011\u0010S\u0006ZGi4@\u000b\u0004[Gi,\b^]o-T\n\u001aS\bXYc,U\u0016\u000bN\bR@r-Y\u0016\u001aO\u0006RGo&Y\u0016\u0004XTu5\u0005NZs5X\u0007IP~5E\n\u001a\u0002HC\u0004H@o%\u0004YZq/\u0004^@d$\nK\\u(R\u0011\u0013TA\u007f\u0004ILv$\u0002HE\u0007T[`-Q\f\u001a\u0006RGo&Y\u0016\u0004ILv$\u0004JPu5\tHCY)U\u0011\u0018UA\bHCY6Y\u001c\u000bU\u0006ZGi4@\u000b\bOZr D\u0011\u0010S\bIP~5E\n\u001aN\r\\@t.B\u0019 PZr(_\u0016\u0002HE\nSZ&5U\u0000\u000bHGc\u0005[Te$C\u0004OZi5\u0005SZt5X\nOPu.\\\r\u000bTZh\u0007IP~5E\n\u001a\u0006UPo&X\f\u0004JPu5".length();
                var1_4 = 5;
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
                    var2_2 = "T[`-Q\f\u001a\u0002IZ";
                    var4_3 = "T[`-Q\f\u001a\u0002IZ".length();
                    var1_4 = 7;
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
                        v10 = 61;
                        break;
                    }
                    case 1: {
                        v10 = 53;
                        break;
                    }
                    case 2: {
                        v10 = 6;
                        break;
                    }
                    case 3: {
                        v10 = 65;
                        break;
                    }
                    case 4: {
                        v10 = 48;
                        break;
                    }
                    case 5: {
                        v10 = 120;
                        break;
                    }
                    default: {
                        v10 = 127;
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
        WingModel.b = var5;
        v11 = new String[6];
        var0_6 = WingModel.b;
        v11[0] = var0_6[10];
        v11[1] = var0_6[20];
        v11[2] = var0_6[4];
        v11[3] = var0_6[47];
        v11[4] = var0_6[28];
        v11[5] = var0_6[24];
        WingModel.DIRS = v11;
        WingModel.CCW = new int[]{0, 3, 2, 1};
        WingModel.TURN = new Vector3f();
        WingModel.A = new Vector3f();
        WingModel.B = new Vector3f();
        WingModel.N = new Vector3f();
    }
}
