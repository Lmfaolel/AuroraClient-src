package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.gui.PackPreview$Face;
import dev.quelchi.aurora.gui.PackPreview$Slot;
import dev.quelchi.aurora.gui.PackTextures;
import dev.quelchi.aurora.gui.PackTextures$Sprite;
import dev.quelchi.aurora.gui.PackTextures$Stats;
import dev.quelchi.aurora.gui.PreviewScene;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;
import org.joml.Matrix3x2fc;

public final class PackPreview {
    private static final PackPreview$Slot[] TOP;
    private static final String[] MATERIALS;
    private static final Item[][] ARMOUR;
    private static final String[] PIECES;
    private static final PackPreview$Slot[] HEARTS;
    private static final long CYCLE_MS;
    private static final int ICON;
    private static final int CELL;
    private static final String[] a;
    private static final long[] b;
    private static final Integer[] c;
    private static final long[] d;
    private static final Long[] e;

    private PackPreview() {
    }

    public static List<String> wantedTextures() {
        ArrayList<String> arrayList;
        block23: {
            Object object;
            Object[] objectArray;
            int n;
            int n2;
            int n3;
            Object[] objectArray2;
            ArrayList<String> arrayList2;
            block18: {
                arrayList2 = new ArrayList<String>();
                objectArray2 = TOP;
                n3 = objectArray2.length;
                n2 = 0;
                n = PreviewScene.a;
                while (n2 < n3) {
                    block19: {
                        block20: {
                            ArrayList<String> arrayList3;
                            block21: {
                                objectArray = objectArray2;
                                if (n != 0) break block18;
                                object = objectArray[n2];
                                if (n != 0) break block19;
                                arrayList2.add(((PackPreview$Slot)object).texture());
                                if (((PackPreview$Slot)object).side() == null) break block20;
                                arrayList3 = arrayList2;
                                if (n != 0) break block21;
                                if (arrayList3.contains(((PackPreview$Slot)object).side())) break block20;
                                arrayList3 = arrayList2;
                            }
                            arrayList3.add(((PackPreview$Slot)object).side());
                        }
                        ++n2;
                    }
                    if (n == 0) continue;
                }
                objectArray2 = MATERIALS;
                n3 = objectArray2.length;
                n2 = 0;
                while (n2 < n3) {
                    block22: {
                        object = objectArray2[n2];
                        for (String string : PIECES) {
                            arrayList2.add(a[14] + (String)object + "_" + string);
                            if (n == 0) {
                                if (n == 0) continue;
                                break;
                            }
                            break block22;
                        }
                        ++n2;
                    }
                    if (n == 0) continue;
                }
                objectArray2 = HEARTS;
                objectArray = objectArray2;
            }
            n3 = objectArray.length;
            for (n2 = 0; n2 < n3; ++n2) {
                object = objectArray2[n2];
                arrayList = arrayList2;
                if (n == 0) {
                    arrayList.add(((PackPreview$Slot)object).texture());
                    if (n == 0) continue;
                    break;
                }
                break block23;
            }
            arrayList = arrayList2;
        }
        return arrayList;
    }

    public static boolean ready(String string) {
        return PackTextures.ready(string);
    }

    public static void render(DrawContext drawContext, String string, int n, int n2, int n3) {
        int n4;
        int n5;
        int n6;
        block16: {
            int n7;
            block15: {
                int n8;
                block13: {
                    block14: {
                        n6 = PreviewScene.a;
                        n8 = PackTextures.ready(string);
                        if (n6 != 0) break block13;
                        if (n8 != 0) break block14;
                        return;
                    }
                    n8 = (int)(System.currentTimeMillis() / PackPreview.b(5056, 2860402359775738760L) % (long)MATERIALS.length);
                }
                n7 = n8;
                for (n5 = 0; n5 < TOP.length; ++n5) {
                    if (n6 == 0) {
                        PackPreview.draw(drawContext, TOP[n5], n + n5 * 17, n2);
                        if (n6 == 0) continue;
                        break;
                    }
                    break block15;
                }
                n5 = n2 + 16;
            }
            for (n4 = 0; n4 < PIECES.length; ++n4) {
                if (n6 == 0) {
                    PackPreview.draw(drawContext, PackPreview$Slot.of(a[16] + MATERIALS[n7] + "_" + PIECES[n4], ARMOUR[n7][n4]), n + n4 * 17, n5);
                    if (n6 == 0) continue;
                    break;
                }
                break block16;
            }
            n4 = 0;
        }
        while (n4 < HEARTS.length) {
            PackPreview.draw(drawContext, HEARTS[n4], n + (PIECES.length + n4) * 17, n5);
            ++n4;
            if (n6 == 0) continue;
            break;
        }
    }

    private static void draw(DrawContext drawContext, PackPreview$Slot packPreview$Slot, int n, int n2) {
        block33: {
            PackPreview$Slot packPreview$Slot2;
            block31: {
                block32: {
                    PackTextures$Sprite packTextures$Sprite;
                    int n3;
                    PackTextures$Sprite packTextures$Sprite2;
                    block30: {
                        block25: {
                            PackTextures$Sprite packTextures$Sprite3;
                            block28: {
                                block29: {
                                    block26: {
                                        block27: {
                                            PackPreview$Slot packPreview$Slot3;
                                            block24: {
                                                packTextures$Sprite2 = PackTextures.sprite(packPreview$Slot.texture());
                                                n3 = PreviewScene.a;
                                                packPreview$Slot3 = packPreview$Slot;
                                                if (n3 != 0) break block24;
                                                if (packPreview$Slot3.side() == null) break block25;
                                                packPreview$Slot3 = packPreview$Slot;
                                            }
                                            packTextures$Sprite3 = PackTextures.sprite(packPreview$Slot3.side());
                                            packTextures$Sprite = packTextures$Sprite2;
                                            if (n3 != 0) break block26;
                                            if (packTextures$Sprite != null) break block27;
                                            packTextures$Sprite2 = packTextures$Sprite3;
                                        }
                                        packTextures$Sprite = packTextures$Sprite3;
                                    }
                                    if (n3 != 0) break block28;
                                    if (packTextures$Sprite != null) break block29;
                                    packTextures$Sprite3 = packTextures$Sprite2;
                                }
                                packTextures$Sprite = packTextures$Sprite2;
                            }
                            if (n3 != 0) break block30;
                            if (packTextures$Sprite == null) break block25;
                            PackPreview.cube(drawContext, PackPreview$Face.whole(packTextures$Sprite2), PackPreview$Face.whole(packTextures$Sprite3), PackPreview$Face.whole(packTextures$Sprite3), (float)n + 8.0f, n2, 16.0f);
                            return;
                        }
                        packTextures$Sprite = packTextures$Sprite2;
                    }
                    if (packTextures$Sprite != null) {
                        drawContext.method_25302(RenderPipelines.field_56883, packTextures$Sprite2.id(), n, n2, 0.0f, 0.0f, 16, 16, packTextures$Sprite2.frameWidth(), packTextures$Sprite2.frameHeight(), packTextures$Sprite2.width(), packTextures$Sprite2.height());
                        return;
                    }
                    packPreview$Slot2 = packPreview$Slot;
                    if (n3 != 0) break block31;
                    if (packPreview$Slot2.sprite() == null) break block32;
                    drawContext.method_52706(RenderPipelines.field_56883, packPreview$Slot.sprite(), n + 3, n2 + 3, 9, 9);
                    if (n3 == 0) break block33;
                }
                packPreview$Slot2 = packPreview$Slot;
            }
            if (packPreview$Slot2.fallback() != null) {
                drawContext.method_51427(new ItemStack((ItemConvertible)packPreview$Slot.fallback()), n, n2);
            }
        }
    }

    public static List<Text> stats(String string) {
        Object object;
        MutableText mutableText;
        ArrayList<Text> arrayList;
        ArrayList<Text> arrayList2;
        PackTextures$Stats packTextures$Stats;
        block11: {
            block10: {
                PackTextures$Stats packTextures$Stats2;
                block9: {
                    int n;
                    block8: {
                        packTextures$Stats = PackTextures.stats();
                        n = PreviewScene.a;
                        if (PackTextures.ready(string) && packTextures$Stats != null) break block8;
                        return List.of();
                    }
                    arrayList2 = new ArrayList<Text>(4);
                    arrayList = arrayList2;
                    mutableText = Text.method_43470((String)a[8]);
                    packTextures$Stats2 = packTextures$Stats;
                    if (n != 0) break block9;
                    if (packTextures$Stats2.resolution() <= 0) break block10;
                    packTextures$Stats2 = packTextures$Stats;
                }
                object = packTextures$Stats2.resolution() + "x";
                break block11;
            }
            object = "?";
        }
        arrayList.add((Text)mutableText.method_10852((Text)Text.method_43470((String)object).method_27692(Formatting.field_1068)).method_27692(Formatting.field_1080));
        String[] stringArray = a;
        arrayList2.add(PackPreview.mark(stringArray[9], packTextures$Stats.animations()));
        arrayList2.add(PackPreview.mark(stringArray[3], packTextures$Stats.lightmap()));
        arrayList2.add(PackPreview.mark(stringArray[10], packTextures$Stats.sounds()));
        return arrayList2;
    }

    private static Text mark(String string, boolean bl) {
        MutableText mutableText = Text.method_43470((String)(string + a[6])).method_27692(Formatting.field_1080);
        String string2 = bl ? "\u2713" : "\u2717";
        MutableText mutableText2 = Text.method_43470((String)string2);
        Formatting formatting = bl ? Formatting.field_1060 : Formatting.field_1061;
        return mutableText.method_10852((Text)mutableText2.method_27692(formatting));
    }

    public static int width() {
        return TOP.length * 17;
    }

    public static void cube(DrawContext drawContext, PackPreview$Face packPreview$Face, PackPreview$Face packPreview$Face2, PackPreview$Face packPreview$Face3, float f, float f2, float f3) {
        float f4 = f3 / 2.0f;
        float f5 = f4 * 1.7320508f;
        PackPreview.face(drawContext, packPreview$Face, f - f5 / 2.0f, f2 + f4 / 2.0f, f5 / 2.0f, -f4 / 2.0f, f5 / 2.0f, f4 / 2.0f, -1);
        PackPreview.face(drawContext, packPreview$Face2, f - f5 / 2.0f, f2 + f4 / 2.0f, f5 / 2.0f, f4 / 2.0f, 0.0f, f4, PackPreview.a(14669, 5091458260422077788L));
        PackPreview.face(drawContext, packPreview$Face3, f, f2 + f4, f5 / 2.0f, -f4 / 2.0f, 0.0f, f4, PackPreview.a(14315, 6090286057812632568L));
    }

    public static void face(DrawContext drawContext, PackPreview$Face packPreview$Face, float f, float f2, float f3, float f4, float f5, float f6, int n) {
        Matrix3x2fStack matrix3x2fStack = drawContext.method_51448();
        matrix3x2fStack.pushMatrix();
        matrix3x2fStack.mul((Matrix3x2fc)new Matrix3x2f(f3 / 16.0f, f4 / 16.0f, f5 / 16.0f, f6 / 16.0f, f, f2));
        drawContext.method_25293(RenderPipelines.field_56883, packPreview$Face.s().id(), 0, 0, (float)packPreview$Face.u(), (float)packPreview$Face.v(), 16, 16, packPreview$Face.w(), packPreview$Face.h(), packPreview$Face.s().width(), packPreview$Face.s().height(), n);
        matrix3x2fStack.popMatrix();
    }

    
    static {
        block32: {
            block31: {
                block30: {
                    block29: {
                        block28: {
                            block27: {
                                var21 = new String[33];
                                var19_1 = 0;
                                var18_2 = "i\u0019O4]<\bn\u0006P6A}%j\u001bC?Ya%\u007f\u001aP\bYu\u001c\u0010b\u0001E:\u0019w\u0013j\u0018O9RL\u001bs\u0010\u000ei\u0019O4]<\u0015i\u0006I3_r\u0014\bG\u001cG?B~\u001b{\th\u001aN#Wz\u0014n\u0007\u0015b\u0001E:\u0019g\u0015\u007f\u0010M\bYu%~\u001bD._}\u001d\u00021U\nh\u001dE$Bc\u0016j\u0001E\fY\u0010S8Zf\u000eb\u001aNm\u0016\nJ\u001bI:Wg\u0013d\u001bS\u0006X\u001aU9R`\u001ai\u0019O4]<\bn\u0006P6A}%j\u001bC?Ya%x\u001cD2\u0006\u0010b\u0001E:\u0019v\u0014o\u0010R\bFv\u001by\u0019\u0004b\u0007O9\u0005b\u0001E:\u0019\u0007g\u0010A#^v\b\u0005b\u0001E:\u0019\u0006l\u001aL3S}\te\u0010T?Sa\u0013\u007f\u0010\u000ei\u0019O4]<\u0015i\u0006I3_r\u0014\u0004m\u0000L;\u000fi\u0019O4]<\u001dg\u001aW$B|\u0014n\u0006c\u0010L:Sg\u0005i\u001aO#E\u000ej\u0017S8Dq\u0013e\u0012\u007f1C\u007f\u0016\u0007o\u001cA:Y}\u001e\u0004c\u0014L1\u0012b\u0001E:\u0019w\u0013j\u0018O9RL\t|\u001aR3\u0010b\u0001E:\u0019v\u0014o*C%O`\u000ej\u0019\th\u001dA>X~\u001bb\u0019\bg\u0010G0_}\u001dx";
                                var20_3 = "i\u0019O4]<\bn\u0006P6A}%j\u001bC?Ya%\u007f\u001aP\bYu\u001c\u0010b\u0001E:\u0019w\u0013j\u0018O9RL\u001bs\u0010\u000ei\u0019O4]<\u0015i\u0006I3_r\u0014\bG\u001cG?B~\u001b{\th\u001aN#Wz\u0014n\u0007\u0015b\u0001E:\u0019g\u0015\u007f\u0010M\bYu%~\u001bD._}\u001d\u00021U\nh\u001dE$Bc\u0016j\u0001E\fY\u0010S8Zf\u000eb\u001aNm\u0016\nJ\u001bI:Wg\u0013d\u001bS\u0006X\u001aU9R`\u001ai\u0019O4]<\bn\u0006P6A}%j\u001bC?Ya%x\u001cD2\u0006\u0010b\u0001E:\u0019v\u0014o\u0010R\bFv\u001by\u0019\u0004b\u0007O9\u0005b\u0001E:\u0019\u0007g\u0010A#^v\b\u0005b\u0001E:\u0019\u0006l\u001aL3S}\te\u0010T?Sa\u0013\u007f\u0010\u000ei\u0019O4]<\u0015i\u0006I3_r\u0014\u0004m\u0000L;\u000fi\u0019O4]<\u001dg\u001aW$B|\u0014n\u0006c\u0010L:Sg\u0005i\u001aO#E\u000ej\u0017S8Dq\u0013e\u0012\u007f1C\u007f\u0016\u0007o\u001cA:Y}\u001e\u0004c\u0014L1\u0012b\u0001E:\u0019w\u0013j\u0018O9RL\t|\u001aR3\u0010b\u0001E:\u0019v\u0014o*C%O`\u000ej\u0019\th\u001dA>X~\u001bb\u0019\bg\u0010G0_}\u001dx".length();
                                var17_4 = 28;
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
                                    var18_2 = "i\u0019O4]<\u001dg\u001aW$B|\u0014n\u0011b\u0001E:\u0019t\u0015g\u0011E9ir\n{\u0019E";
                                    var20_3 = "i\u0019O4]<\u001dg\u001aW$B|\u0014n\u0011b\u0001E:\u0019t\u0015g\u0011E9ir\n{\u0019E".length();
                                    var17_4 = 15;
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
                                        v10 = 11;
                                        break;
                                    }
                                    case 1: {
                                        v10 = 117;
                                        break;
                                    }
                                    case 2: {
                                        v10 = 32;
                                        break;
                                    }
                                    case 3: {
                                        v10 = 87;
                                        break;
                                    }
                                    case 4: {
                                        v10 = 54;
                                        break;
                                    }
                                    case 5: {
                                        v10 = 19;
                                        break;
                                    }
                                    default: {
                                        v10 = 122;
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
                        PackPreview.a = var21;
                        var8_8 = 854085600546233571L;
                        var14_9 = new long[4];
                        var11_10 = 0;
                        var12_11 = "5.\u0017l\u00c1\u0012`\u009f\u00cf\u00a8\u008d\u00dc\n\u00b6\u00fds";
                        var13_12 = "5.\u0017l\u00c1\u0012`\u009f\u00cf\u00a8\u008d\u00dc\n\u00b6\u00fds".length();
                        var10_13 = 0;
                        while (true) {
                            var15_14 = var12_11.substring(var10_13, var10_13 += 8).getBytes("ISO-8859-1");
                            v11 = var14_9;
                            v12 = var11_10++;
                            v13 = ((long)var15_14[0] & 255L) << 56 | ((long)var15_14[1] & 255L) << 48 | ((long)var15_14[2] & 255L) << 40 | ((long)var15_14[3] & 255L) << 32 | ((long)var15_14[4] & 255L) << 24 | ((long)var15_14[5] & 255L) << 16 | ((long)var15_14[6] & 255L) << 8 | (long)var15_14[7] & 255L;
                            v14 = -1;
                            break block29;
                            break;
                        }
lbl89:

                        while (true) {
                            v11[v12] = v15;
                            if (var10_13 < var13_12) continue;
                            var12_11 = "0\u00044:\u00f8}%\u00da8\u00a6\u0084o\u0003\u00a3\u00a6\u0082";
                            var13_12 = "0\u00044:\u00f8}%\u00da8\u00a6\u0084o\u0003\u00a3\u00a6\u0082".length();
                            var10_13 = 0;
                            while (true) {
                                var15_14 = var12_11.substring(var10_13, var10_13 += 8).getBytes("ISO-8859-1");
                                v11 = var14_9;
                                v12 = var11_10++;
                                v13 = ((long)var15_14[0] & 255L) << 56 | ((long)var15_14[1] & 255L) << 48 | ((long)var15_14[2] & 255L) << 40 | ((long)var15_14[3] & 255L) << 32 | ((long)var15_14[4] & 255L) << 24 | ((long)var15_14[5] & 255L) << 16 | ((long)var15_14[6] & 255L) << 8 | (long)var15_14[7] & 255L;
                                v14 = 0;
                                break block29;
                                break;
                            }
                            break;
                        }
lbl102:

                        while (true) {
                            v11[v12] = v15;
                            if (var10_13 < var13_12) continue;
                            break block30;
                            break;
                        }
                    }
                    v15 = v13 ^ var8_8;
                    switch (v14) {
                        default: {
                            continue;
                        }
                        case 0:
lbl113:

                        continue;
                    }
                }
                PackPreview.b = var14_9;
                PackPreview.c = new Integer[4];
                PackPreview.CELL = PackPreview.a(11578, 7728042634998495528L);
                PackPreview.ICON = PackPreview.a(26748, 6386660877315603564L);
                var0_15 = 7961166002976216673L;
                var6_16 = new long[2];
                var3_17 = 0;
                var4_18 = "I\u00c9\u008e\u0086<On\u0001T\u0016M(w\u00a2?0";
                var5_19 = "I\u00c9\u008e\u0086<On\u0001T\u0016M(w\u00a2?0".length();
                var2_20 = 0;
                while (true) {
                    break block31;
                    break;
                }
lbl127:

                while (true) {
                    var6_16[v16] = (((long)var7_21[0] & 255L) << 56 | ((long)var7_21[1] & 255L) << 48 | ((long)var7_21[2] & 255L) << 40 | ((long)var7_21[3] & 255L) << 32 | ((long)var7_21[4] & 255L) << 24 | ((long)var7_21[5] & 255L) << 16 | ((long)var7_21[6] & 255L) << 8 | (long)var7_21[7] & 255L) ^ var0_15;
                    if (var2_20 < var5_19) continue;
                    break block32;
                    break;
                }
            }
            var7_21 = var4_18.substring(var2_20, var2_20 += 8).getBytes("ISO-8859-1");
            v16 = var3_17++;
            while (true)
        }
        PackPreview.d = var6_16;
        PackPreview.e = new Long[2];
        PackPreview.CYCLE_MS = PackPreview.b(17136, 4210288840754227897L);
        v17 = new PackPreview$Slot[9];
        var16_6 = PackPreview.a;
        v17[0] = PackPreview$Slot.of(var16_6[27], Items.field_8802);
        v17[1] = PackPreview$Slot.of(var16_6[12], Items.field_8634);
        v17[2] = PackPreview$Slot.block(var16_6[2], var16_6[19], Items.field_8281);
        v17[3] = PackPreview$Slot.of(var16_6[28], Items.field_8301);
        v17[4] = PackPreview$Slot.block(var16_6[0], var16_6[11], Items.field_23141);
        v17[5] = PackPreview$Slot.block(var16_6[31], var16_6[21], Items.field_8801);
        v17[6] = PackPreview$Slot.of(var16_6[32], Items.field_8463);
        v17[7] = PackPreview$Slot.of(var16_6[5], Items.field_8288);
        v17[8] = PackPreview$Slot.of(var16_6[1], Items.field_8556);
        PackPreview.TOP = v17;
        PackPreview.MATERIALS = new String[]{var16_6[15], var16_6[29], var16_6[13], var16_6[17], var16_6[25], var16_6[18]};
        PackPreview.ARMOUR = new Item[][]{{Items.field_8267, Items.field_8577, Items.field_8570, Items.field_8370}, {Items.field_8283, Items.field_8873, Items.field_8218, Items.field_8313}, {Items.field_8743, Items.field_8523, Items.field_8396, Items.field_8660}, {Items.field_8862, Items.field_8678, Items.field_8416, Items.field_8753}, {Items.field_8805, Items.field_8058, Items.field_8348, Items.field_8285}, {Items.field_22027, Items.field_22028, Items.field_22029, Items.field_22030}};
        PackPreview.PIECES = new String[]{var16_6[22], var16_6[7], var16_6[30], var16_6[23]};
        PackPreview.HEARTS = new PackPreview$Slot[]{PackPreview$Slot.hud(var16_6[20]), PackPreview$Slot.hud(var16_6[26]), PackPreview$Slot.hud(var16_6[4]), PackPreview$Slot.hud(var16_6[24])};
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1410;
        if (c[n2] == null) {
            PackPreview.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }

    private static long b(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x2848) & Short.MAX_VALUE;
        if (e[n2] == null) {
            PackPreview.e[n2] = d[n2] ^ l;
        }
        return e[n2];
    }
}
