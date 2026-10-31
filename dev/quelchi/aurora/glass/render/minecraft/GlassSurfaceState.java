package dev.quelchi.aurora.glass.render.minecraft;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import dev.quelchi.aurora.glass.render.GlassMath;
import dev.quelchi.aurora.glass.render.GlassQuality;
import dev.quelchi.aurora.glass.render.RenderStats;
import dev.quelchi.aurora.glass.render.minecraft.GlassPipelines;
import dev.quelchi.aurora.glass.style.GlassStyle;
import dev.quelchi.aurora.glass.ui.UiBounds;
import dev.quelchi.aurora.module.Module;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.ScreenRect;
import net.minecraft.client.gui.render.state.SimpleGuiElementRenderState;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.texture.TextureSetup;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fc;

public final class GlassSurfaceState
implements SimpleGuiElementRenderState {
    final UiBounds box;
    final GlassStyle style;
    final GlassQuality requested;
    final boolean backdrop;
    final float visibility;
    final float mouseX;
    final float mouseY;
    final float pixelScale;
    final RenderStats stats;
    private final Matrix3x2f pose;
    private final ScreenRect scissor;
    private final ScreenRect bounds;
    private final int padding;
    private TextureSetup textures;
    private int row;
    public static boolean a;

    
    public GlassSurfaceState(DrawContext var1_1, UiBounds var2_2, GlassStyle var3_3, GlassQuality var4_4, boolean var5_5, float var6_6, float var7_7, float var8_8, RenderStats var9_9) {
        block27: {
            block29: {
                block28: {
                    block26: {
                        block25: {
                            block24: {
                                block22: {
                                    block23: {
                                        block20: {
                                            block21: {
                                                super();
                                                var10_10 = GlassSurfaceState.a;
                                                this.textures = TextureSetup.method_70899();
                                                this.row = -1;
                                                this.box = var2_2;
                                                this.style = var3_3;
                                                this.requested = var4_4;
                                                v0 = this;
                                                v1 = var5_5;
                                                if (var10_10) break block20;
                                                v0.backdrop = v1;
                                                this.visibility = var8_8;
                                                this.stats = var9_9;
                                                this.pose = new Matrix3x2f((Matrix3x2fc)var1_1.method_51448());
                                                this.scissor = var1_1.field_44659.method_70863();
                                                v0 = this;
                                                if (var3_3.outerGlow() >>> 24 != 0) break block21;
                                                v1 = 3;
                                                break block20;
                                            }
                                            v1 = 12;
                                        }
                                        v0.padding = Math.max(v1, var3_3.shadowSize() * 2 + 4);
                                        var11_11 = new ScreenRect(var2_2.x() - this.padding, var2_2.y() - this.padding, var2_2.width() + this.padding * 2, var2_2.height() + this.padding * 2).method_71523((Matrix3x2fc)this.pose);
                                        v4 = this;
                                        v5 = this.scissor;
                                        if (var10_10) break block22;
                                        if (v5 != null) break block23;
                                        v7 = var11_11;
                                        break block24;
                                    }
                                    v5 = var11_11;
                                }
                                v7 = v5.method_49701(this.scissor);
                            }
                            v4.bounds = v7;
                            var12_12 = this.pose.m00() * this.pose.m11() - this.pose.m01() * this.pose.m10();
                            v9 = var12_12;
                            if (var10_10) break block25;
                            if (Math.abs(v9) > 1.0E-6f) {
                            }
                            v9 = var6_6 - this.pose.m20();
                        }
                        var13_13 = v9;
                        var14_14 = var7_7 - this.pose.m21();
                        this.mouseX = (this.pose.m11() * var13_13 - this.pose.m10() * var14_14) / var12_12 - (float)var2_2.x();
                        this.mouseY = (-this.pose.m01() * var13_13 + this.pose.m00() * var14_14) / var12_12 - (float)var2_2.y();
                        if (!var10_10) break block26;
lbl72:

                        this.mouseY = -100000.0f;
                        this.mouseX = -100000.0f;
                    }
                    this.pixelScale = (float)Math.sqrt(Math.abs(var12_12));
                    if (!Module.a) break block27;
                    if (!var10_10) break block28;
                    v14 = false;
                    break block29;
                }
                v14 = true;
            }
            GlassSurfaceState.a = v14;
        }
    }

    void prepare(int n, TextureSetup textureSetup) {
        this.row = n;
        this.textures = textureSetup;
    }

    public RenderPipeline comp_4055() {
        RenderPipeline renderPipeline = this.row >= 0 ? GlassPipelines.SURFACE : RenderPipelines.field_56879;
        return renderPipeline;
    }

    public TextureSetup comp_4056() {
        return this.textures;
    }

    public ScreenRect comp_4069() {
        return this.scissor;
    }

    public ScreenRect comp_4274() {
        return this.bounds;
    }

    public void method_70917(VertexConsumer vertexConsumer) {
        block8: {
            GlassSurfaceState glassSurfaceState;
            block6: {
                block7: {
                    boolean bl = a;
                    glassSurfaceState = this;
                    if (bl) break block6;
                    if (glassSurfaceState.row >= 0) break block7;
                    this.fallback(vertexConsumer);
                    if (!bl) break block8;
                }
                this.vertex(vertexConsumer, -this.padding, -this.padding);
                this.vertex(vertexConsumer, -this.padding, this.box.height() + this.padding);
                this.vertex(vertexConsumer, this.box.width() + this.padding, this.box.height() + this.padding);
                glassSurfaceState = this;
            }
            glassSurfaceState.vertex(vertexConsumer, this.box.width() + this.padding, -this.padding);
        }
    }

    private void vertex(VertexConsumer vertexConsumer, float f, float f2) {
        this.position(vertexConsumer, f + (float)this.box.x(), f2 + (float)this.box.y());
        vertexConsumer.method_39415(this.style.tint()).method_22913(f, f2).method_22921(this.row, 0);
    }

    private void position(VertexConsumer vertexConsumer, float f, float f2) {
        vertexConsumer.method_22912(this.pose.m00() * f + this.pose.m10() * f2 + this.pose.m20(), this.pose.m01() * f + this.pose.m11() * f2 + this.pose.m21(), 0.0f);
    }

    private void quad(VertexConsumer vertexConsumer, int n, int n2, int n3, int n4, int n5) {
        block12: {
            int n6;
            block13: {
                boolean bl;
                block11: {
                    bl = a;
                    n6 = n;
                    if (bl) break block11;
                    if (n6 >= n3) break block12;
                    n6 = n2;
                }
                if (bl) break block13;
                if (n6 >= n4) break block12;
                n6 = n5 >>> 24;
            }
            if (n6 != 0) {
                this.position(vertexConsumer, n, n2);
                vertexConsumer.method_39415(n5);
                this.position(vertexConsumer, n, n4);
                vertexConsumer.method_39415(n5);
                this.position(vertexConsumer, n3, n4);
                vertexConsumer.method_39415(n5);
                this.position(vertexConsumer, n3, n2);
                vertexConsumer.method_39415(n5);
            }
        }
    }

    
    private void fallback(VertexConsumer vertexConsumer) {
        int n;
        int n2;
        int n3;
        int n4;
        int n5;
        int n6;
        boolean bl;
        block22: {
            int n7;
            int n8;
            float f;
            block21: {
                int n9;
                block20: {
                    int n10 = this.box.width();
                    bl = a;
                    n6 = this.box.height();
                    n5 = Math.round(GlassMath.radius(this.style.radius(), n10, n6));
                    n4 = Math.round(GlassMath.radius(this.style.topRightRadius(), n10, n6));
                    n3 = Math.round(GlassMath.radius(this.style.bottomRightRadius(), n10, n6));
                    n2 = Math.round(GlassMath.radius(this.style.bottomLeftRadius(), n10, n6));
                    f = 1.0f;
                    n9 = n5;
                    if (!bl) {
                        if (n9 + n4 > n10) {
                            f = Math.min(f, (float)n10 / (float)(n5 + n4));
                        }
                        n9 = n2 + n3;
                    }
                    if (bl) break block20;
                    if (n9 > n10) {
                        f = Math.min(f, (float)n10 / (float)(n2 + n3));
                    }
                    n8 = n5;
                    n7 = n2;
                    if (bl) break block21;
                    n9 = n8 + n7;
                }
                if (n9 > n6) {
                    f = Math.min(f, (float)n6 / (float)(n5 + n2));
                }
                n = n4;
                if (bl) break block22;
                n8 = n + n3;
                n7 = n6;
            }
            if (n8 > n7) {
                f = Math.min(f, (float)n6 / (float)(n4 + n3));
            }
            n5 = Math.round((float)n5 * f);
            n4 = Math.round((float)n4 * f);
            n3 = Math.round((float)n3 * f);
            n2 = Math.round((float)n2 * f);
            n = GlassMath.alpha(this.style.tint(), this.style.opacity() * this.visibility);
        }
        int n11 = n;
        int n12 = GlassMath.alpha(this.style.borderColor(), this.visibility);
        for (int i = 0; i < n6; ++i) {
            int n13;
            int n14;
            int n15;
            block24: {
                int n16;
                int n17;
                int n18;
                block25: {
                    int n19;
                    block23: {
                        n19 = GlassMath.leftInset(i, n6, n5, n2);
                        n18 = GlassMath.rightInset(i, n6, n4, n3);
                        n15 = this.box.x() + n19;
                        n14 = this.box.right() - n18;
                        n13 = this.box.y() + i;
                        n17 = i;
                        if (bl) break block23;
                        if (n17 == 0) break block24;
                        n17 = i;
                    }
                    n16 = n6;
                    if (bl) break block25;
                    if (n17 == n16 - 1) break block24;
                    n17 = n19;
                    n16 = 1 + GlassMath.leftInset(i - 1, Math.max(0, n6 - 2), Math.max(0, n5 - 1), Math.max(0, n2 - 1));
                }
                int n20 = Math.max(n17, n16);
                int n21 = Math.max(n18, 1 + GlassMath.rightInset(i - 1, Math.max(0, n6 - 2), Math.max(0, n4 - 1), Math.max(0, n3 - 1)));
                this.quad(vertexConsumer, n15, n13, this.box.x() + n20, n13 + 1, n12);
                this.quad(vertexConsumer, this.box.x() + n20, n13, this.box.right() - n21, n13 + 1, n11);
                this.quad(vertexConsumer, this.box.right() - n21, n13, n14, n13 + 1, n12);
                if (!bl) continue;
            }
            this.quad(vertexConsumer, n15, n13, n14, n13 + 1, n12);
            if (!bl) continue;
        }
    }
}
