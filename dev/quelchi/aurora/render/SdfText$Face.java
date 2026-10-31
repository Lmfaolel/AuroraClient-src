package dev.quelchi.aurora.render;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.render.EffectEngine;
import dev.quelchi.aurora.render.SdfText;
import dev.quelchi.aurora.render.SdfText$Glyph;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.io.InputStream;
import java.nio.ByteBuffer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.util.Identifier;
import org.lwjgl.stb.STBTTFontinfo;
import org.lwjgl.stb.STBTruetype;
import org.lwjgl.system.MemoryUtil;

public final class SdfText$Face {
    private final String file;
    private final Int2ObjectOpenHashMap<SdfText$Glyph> glyphs = new Int2ObjectOpenHashMap();
    private STBTTFontinfo info;
    private float scale;
    private float perUnit;
    private boolean failed;
    private static final String[] a;
    private static final long b;

    private SdfText$Face(String string) {
        this.file = string;
    }

    
    private boolean load() {
        int n = EffectEngine.a;
        SdfText$Face sdfText$Face = this;
        if (n == 0) {
            if (sdfText$Face.info != null) {
                return true;
            }
            sdfText$Face = this;
        }
        boolean bl = sdfText$Face.failed;
        if (n != 0) return bl;
        if (bl) {
            return false;
        }
        try {
            InputStream inputStream;
            boolean bl2;
            block18: {
                String[] stringArray = a;
                InputStream inputStream2 = MinecraftClient.method_1551().method_1478().getResourceOrThrow(Identifier.method_60655((String)stringArray[2], (String)(stringArray[0] + this.file))).method_14482();
                try {
                    byte[] byArray = inputStream2.readAllBytes();
                    ByteBuffer byteBuffer = MemoryUtil.memAlloc((int)byArray.length).put(byArray).flip();
                    STBTTFontinfo sTBTTFontinfo = STBTTFontinfo.create();
                    if (n == 0) {
                        if (!STBTruetype.stbtt_InitFont((STBTTFontinfo)sTBTTFontinfo, (ByteBuffer)byteBuffer)) {
                            throw new IllegalStateException(a[4] + this.file);
                        }
                        this.scale = STBTruetype.stbtt_ScaleForMappingEmToPixels((STBTTFontinfo)sTBTTFontinfo, (float)48.0f);
                        this.perUnit = STBTruetype.stbtt_ScaleForMappingEmToPixels((STBTTFontinfo)sTBTTFontinfo, (float)1.0f);
                        this.info = sTBTTFontinfo;
                    }
                    bl2 = true;
                    inputStream = inputStream2;
                    if (n != 0) break block18;
                }
                catch (Throwable throwable) {
                    if (n != 0) throw throwable;
                    if (inputStream2 == null) throw throwable;
                    try {
                        inputStream2.close();
                        throw throwable;
                    }
                    catch (Throwable throwable2) {
                        throwable.addSuppressed(throwable2);
                    }
                    throw throwable;
                }
                if (inputStream == null) return bl2;
                inputStream = inputStream2;
            }
            inputStream.close();
            return bl2;
        }
        catch (Exception | LinkageError throwable3) {
            this.failed = true;
            Aurora.LOG.warn(a[3], (Object)this.file, (Object)throwable3);
            return false;
        }
    }

    private SdfText$Glyph glyph(int n) {
        SdfText$Glyph sdfText$Glyph;
        block4: {
            SdfText$Glyph sdfText$Glyph2;
            block5: {
                sdfText$Glyph2 = (SdfText$Glyph)this.glyphs.get(n);
                int n2 = EffectEngine.a;
                sdfText$Glyph = sdfText$Glyph2;
                if (n2 != 0) break block4;
                if (sdfText$Glyph != null) break block5;
                try {
                    sdfText$Glyph2 = this.make(n);
                }
                catch (Exception | LinkageError throwable) {
                    SdfText.broken = true;
                    Aurora.LOG.warn(a[5], throwable);
                    sdfText$Glyph2 = SdfText.MISSING;
                }
                this.glyphs.put(n, (Object)sdfText$Glyph2);
            }
            sdfText$Glyph = sdfText$Glyph2;
        }
        return sdfText$Glyph;
    }

    
    private SdfText$Glyph make(int n) {
        int n3 = EffectEngine.a;
        int n2 = STBTruetype.stbtt_FindGlyphIndex((STBTTFontinfo)this.info, (int)n);
        if (n3 == 0) {
            if (n2 == 0) {
                return SdfText.MISSING;
            }
            n2 = 1;
        }
        int[] nArray = new int[n2];
        int[] nArray2 = new int[1];
        STBTruetype.stbtt_GetCodepointHMetrics((STBTTFontinfo)this.info, (int)n, (int[])nArray, (int[])nArray2);
        float f = (float)nArray[0] * this.perUnit;
        int[] nArray3 = new int[1];
        int[] nArray4 = new int[1];
        int[] nArray5 = new int[1];
        int[] nArray6 = new int[1];
        ByteBuffer byteBuffer = STBTruetype.stbtt_GetCodepointSDF((STBTTFontinfo)this.info, (float)this.scale, (int)n, (int)12, (byte)-128, (float)10.666667f, (int[])nArray3, (int[])nArray4, (int[])nArray5, (int[])nArray6);
        if (byteBuffer == null) {
            return new SdfText$Glyph(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, f);
        }
        try {
            Object object;
            int n4;
            int n5;
            block31: {
                block35: {
                    int n6;
                    block34: {
                        int n7;
                        block33: {
                            n5 = nArray3[0];
                            n4 = nArray4[0];
                            n7 = SdfText.penX;
                            if (n3 != 0) break block33;
                            if (n7 + n5 > 1024) {
                                SdfText.penX = 0;
                                SdfText.penY += SdfText.rowH + 1;
                                SdfText.rowH = 0;
                            }
                            n6 = SdfText.penY;
                            if (n3 != 0) break block34;
                            n7 = n6 + n4;
                        }
                        if (n7 <= 2048) break block35;
                        n6 = SdfText.full;
                    }
                    if (n3 == 0) {
                        if (n6 == 0) {
                            Aurora.LOG.warn(a[1]);
                        }
                        n6 = 1;
                    }
                    SdfText.full = n6;
                    SdfText$Glyph sdfText$Glyph = SdfText.MISSING;
                    return sdfText$Glyph;
                }
                object = new NativeImage(NativeImage.Format.field_4997, n5, n4, false);
                try {
                    int n8 = 0;
                    while (n8 < n4) {
                        block32: {
                            if (n3 != 0) break block31;
                            for (int i = 0; i < n5; ++i) {
                                object.method_61941(i, n8, (byteBuffer.get(n8 * n5 + i) & 0xFF) * (int)b);
                                if (n3 == 0) {
                                    if (n3 == 0) continue;
                                }
                                break block32;
                            }
                            ++n8;
                        }
                        if (n3 == 0) continue;
                    }
                    RenderSystem.getDevice().createCommandEncoder().writeToTexture(SdfText.atlas, object, 0, 0, SdfText.penX, SdfText.penY, n5, n4, 0, 0);
                }
                catch (Throwable throwable) {
                    try {
                        object.close();
                        throw throwable;
                    }
                    catch (Throwable throwable2) {
                        throwable.addSuppressed(throwable2);
                    }
                    throw throwable;
                }
                object.close();
            }
            object = new SdfText$Glyph((float)nArray5[0] / 48.0f, (float)nArray6[0] / 48.0f, (float)(nArray5[0] + n5) / 48.0f, (float)(nArray6[0] + n4) / 48.0f, (float)SdfText.penX / 1024.0f, (float)SdfText.penY / 2048.0f, (float)(SdfText.penX + n5) / 1024.0f, (float)(SdfText.penY + n4) / 2048.0f, f);
            SdfText.penX += n5 + 1;
            SdfText.rowH = Math.max(SdfText.rowH, n4);
            Object object2 = object;
            return object2;
        }
        finally {
            STBTruetype.stbtt_FreeSDF((ByteBuffer)byteBuffer);
        }
    }

    
    static {
        block21: {
            block20: {
                block19: {
                    var7 = new String[6];
                    var5_1 = 0;
                    var4_2 = "HQi\f\t=}zAXR#dZ\u001ef\fJ'o\u000eWtX@3pB\u0012'\u0016C1<MVf\nG%hKLtXB4}Y\u001en\u0016\u00060}@Wk\u0014Gao\u000eXh\u0016R\u0006OKu\u0017T'<mQr\u0014B(;Z\u001ek\u0017G\"<UC'\u001eI4<}zAXR#dZ\u0012'\u0011RfzORk\u000b\u0006$}MU'\fIfjOPn\u0014J'<Z[\u007f\f";
                    var6_3 = "HQi\f\t=}zAXR#dZ\u001ef\fJ'o\u000eWtX@3pB\u0012'\u0016C1<MVf\nG%hKLtXB4}Y\u001en\u0016\u00060}@Wk\u0014Gao\u000eXh\u0016R\u0006OKu\u0017T'<mQr\u0014B(;Z\u001ek\u0017G\"<UC'\u001eI4<}zAXR#dZ\u0012'\u0011RfzORk\u000b\u0006$}MU'\fIfjOPn\u0014J'<Z[\u007f\f".length();
                    var3_4 = 5;
                    var2_5 = -1;
lbl7:

                    while (true) {
                        v0 = ++var2_5;
                        v1 = var4_2.substring(v0, v0 + var3_4);
                        v2 = -1;
                        break block19;
                        break;
                    }
lbl12:

                    while (true) {
                        var7[var5_1++] = v3.intern();
                        if ((var2_5 += var3_4) < var6_3) {
                            var3_4 = var4_2.charAt(var2_5);
                            continue;
                        }
                        var4_2 = "]JeXE)iBZi_RfnK_cX-}zAXR#dZ\u001es\rT(yJ\u001eh\u001e@j<O\u001e`\u0014_6t\u000e]h\rJ\"r\tJ'\u001aCfqOZb";
                        var6_3 = "]JeXE)iBZi_RfnK_cX-}zAXR#dZ\u001es\rT(yJ\u001eh\u001e@j<O\u001e`\u0014_6t\u000e]h\rJ\"r\tJ'\u001aCfqOZb".length();
                        var3_4 = 18;
                        var2_5 = -1;
lbl21:

                        while (true) {
                            v4 = ++var2_5;
                            v1 = var4_2.substring(v4, v4 + var3_4);
                            v2 = 0;
                            break block19;
                            break;
                        }
                        break;
                    }
lbl26:

                    while (true) {
                        var7[var5_1++] = v3.intern();
                        if ((var2_5 += var3_4) < var6_3) {
                            var3_4 = var4_2.charAt(var2_5);
                            continue;
                        }
                        break block20;
                        break;
                    }
                }
                v5 = v1.toCharArray();
                v6 = v5;
                v7 = v5.length;
                var8_6 = 0;
                do {
                    v6 = v6;
                    v8 = var8_6;
                    v9 = v6[v8];
                    switch (var8_6 % 7) {
                        case 0: {
                            v10 = 46;
                            break;
                        }
                        case 1: {
                            v10 = 62;
                            break;
                        }
                        case 2: {
                            v10 = 7;
                            break;
                        }
                        case 3: {
                            v10 = 120;
                            break;
                        }
                        case 4: {
                            v10 = 38;
                            break;
                        }
                        case 5: {
                            v10 = 70;
                            break;
                        }
                        default: {
                            v10 = 28;
                        }
                    }
                    v6[v8] = (char)(v9 ^ v10);
                    ++var8_6;
lbl65:

                    v7 = v7;
                } while (v7 > var8_6);
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
            SdfText$Face.a = var7;
            break block21;
lbl77:

            while (true) {
                continue;
                break;
            }
        }
        var0_7 = 8604395879218323736L;
        while (true)
        SdfText$Face.b = -2469274302430207975L ^ var0_7;
    }
}
