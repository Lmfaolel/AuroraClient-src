package dev.quelchi.aurora.render;

import dev.quelchi.aurora.render.DamageTint;
import dev.quelchi.aurora.render.EffectEngine;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public final class GradientOverlayVertexConsumer
implements VertexConsumer {
    private static final float TOP_Y = -0.5f;
    private static final float BOTTOM_Y = 1.5f;
    private final VertexConsumer delegate;
    private final Matrix4f rootInverse;
    private final Vector3f tmp = new Vector3f();
    private float pendingX;
    private float pendingY;
    private float pendingZ;
    private static final long[] a;
    private static final Integer[] b;

    public GradientOverlayVertexConsumer(VertexConsumer vertexConsumer, Matrix4f matrix4f) {
        this.delegate = vertexConsumer;
        this.rootInverse = matrix4f;
    }

    private int gradientU(float f, float f2, float f3) {
        float f4;
        block6: {
            float f5;
            block7: {
                int n;
                block4: {
                    block5: {
                        this.tmp.set(f, f2, f3);
                        this.rootInverse.transformPosition(this.tmp);
                        f5 = (this.tmp.y - -0.5f) / 2.0f;
                        n = EffectEngine.a;
                        f4 = f5;
                        if (n != 0) break block4;
                        if (!(f4 < 0.0f)) break block5;
                        f5 = 0.0f;
                    }
                    f4 = f5;
                }
                if (n != 0) break block6;
                if (!(f4 > 1.0f)) break block7;
                f5 = 1.0f;
            }
            f4 = f5 * 15.0f;
        }
        return Math.round(f4);
    }

    public VertexConsumer method_22912(float f, float f2, float f3) {
        this.pendingX = f;
        this.pendingY = f2;
        this.pendingZ = f3;
        this.delegate.method_22912(f, f2, f3);
        return this;
    }

    public VertexConsumer method_1336(int n, int n2, int n3, int n4) {
        this.delegate.method_1336(n, n2, n3, n4);
        return this;
    }

    public VertexConsumer method_39415(int n) {
        this.delegate.method_39415(n);
        return this;
    }

    public VertexConsumer method_22913(float f, float f2) {
        this.delegate.method_22913(f, f2);
        return this;
    }

    public VertexConsumer method_60796(int n, int n2) {
        if (n2 != DamageTint.HURT_V) {
            this.delegate.method_60796(n, n2);
            return this;
        }
        this.delegate.method_60796(this.gradientU(this.pendingX, this.pendingY, this.pendingZ), 2);
        return this;
    }

    public VertexConsumer method_22922(int n) {
        return this.method_60796(n & GradientOverlayVertexConsumer.a(14350, 8339163518403280774L), n >> 16 & GradientOverlayVertexConsumer.a(13228, 4266901486227911717L));
    }

    public VertexConsumer method_22921(int n, int n2) {
        this.delegate.method_22921(n, n2);
        return this;
    }

    public VertexConsumer method_22914(float f, float f2, float f3) {
        this.delegate.method_22914(f, f2, f3);
        return this;
    }

    public VertexConsumer method_75298(float f) {
        this.delegate.method_75298(f);
        return this;
    }

    public void method_23919(float f, float f2, float f3, int n, float f4, float f5, int n2, int n3, float f6, float f7, float f8) {
        block2: {
            int n4;
            block3: {
                int n5 = EffectEngine.a;
                n4 = n2;
                if (n5 != 0) break block2;
                if ((n2 >> 16 & GradientOverlayVertexConsumer.a(13228, 4266901486227911717L)) != DamageTint.HURT_V) break block3;
                n4 = OverlayTexture.method_23625((int)this.gradientU(f, f2, f3), (int)2);
            }
            this.delegate.method_23919(f, f2, f3, n, f4, f5, n4, n3, f6, f7, f8);
        }
    }

    
    static {
        long l = 6681360947987281779L;
        long[] lArray = new long[2];
        int n = 0;
        String string = "\u0018\u00b1\u00a0~r\u00c1|\u00a9UDr\u00fe\u0086\u00a0\u00f7\n";
        int n2 = "\u0018\u00b1\u00a0~r\u00c1|\u00a9UDr\u00fe\u0086\u00a0\u00f7\n".length();
        int n3 = 0;
        do {
            byte[] byArray = string.substring(n3, n3 += 8).getBytes("ISO-8859-1");
            int n4 = n++;
            lArray[n4] = (((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL) ^ l;
        } while (n3 < n2);
        a = lArray;
        b = new Integer[2];
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4789;
        if (b[n2] == null) {
            GradientOverlayVertexConsumer.b[n2] = (int)(a[n2] ^ l);
        }
        return b[n2];
    }
}
