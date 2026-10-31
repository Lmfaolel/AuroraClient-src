package dev.quelchi.aurora.gui.shell;

import dev.quelchi.aurora.cosmetics.content.Cosmetics;
import dev.quelchi.aurora.cosmetics.content.Trails;
import dev.quelchi.aurora.cosmetics.content.Trails$Trail;
import dev.quelchi.aurora.cosmetics.renderer.EmoteEngine;
import dev.quelchi.aurora.cosmetics.renderer.HitEffects;
import dev.quelchi.aurora.cosmetics.renderer.KillEffects;
import dev.quelchi.aurora.cosmetics.renderer.MaceEffects;
import dev.quelchi.aurora.gui.Mannequin;
import dev.quelchi.aurora.gui.Mannequin$Act;
import dev.quelchi.aurora.gui.PreviewPlayer;
import dev.quelchi.aurora.gui.VectorIcons;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.Menu;
import dev.quelchi.aurora.gui.shell.Rect;
import dev.quelchi.aurora.gui.shell.SearchIndex;
import dev.quelchi.aurora.gui.shell.Turntable$1;
import dev.quelchi.aurora.gui.shell.Turntable$2;
import dev.quelchi.aurora.gui.shell.Turntable$3;
import dev.quelchi.aurora.gui.shell.Turntable$4;
import dev.quelchi.aurora.gui.shell.Turntable$5;
import dev.quelchi.aurora.gui.shell.Wardrobe$Section;
import dev.quelchi.aurora.render.EffectEngine;
import dev.quelchi.aurora.render.EffectEngine$Effect;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.joml.Matrix3x2fStack;

final class Turntable {
    private static final float[] FRONT;
    private static final float[] BACK;
    private static final float[] BACK_ANGLE;
    private static final float[] ROOM;
    private static final float[] HIT;
    private static final float[] STREAM;
    private static final float DEG_PER_PX = 1.0f;
    private static final float PITCH_LIMIT = 32.0f;
    private static final float ZOOM_MIN = 0.6f;
    private static final float ZOOM_MAX = 3.0f;
    private Rect r = Rect.NONE;
    private float yaw;
    private float pitch;
    private float zoom = 1.0f;
    private float lift;
    private float yawTo;
    private float pitchTo;
    private float zoomTo = 1.0f;
    private float liftTo;
    private static final float TURN_STIFFNESS = 42.0f;
    private static final float ZOOM_STIFFNESS = 70.0f;
    private float yawV;
    private float pitchV;
    private float zoomV;
    private float liftV;
    private long lastNanos;
    private boolean held;
    private double grabX;
    private double grabY;
    private float grabYaw;
    private float grabPitch;
    private Wardrobe$Section framed;
    private static final int RING_PX;
    private static final float STAND = 0.7f;
    private static final float FALL = 1.1f;
    private static final float REST = 0.8f;
    private static final float SMASH = 0.55f;
    private static final float ATT_X = 0.55f;
    private static final float ATT_Z = -1.25f;
    private static final float FALL_FROM = 4.5f;
    private String showing = "";
    private float loopFrom;
    private static final float STEP = 0.004166667f;
    private static final String[] a;

    Turntable() {
    }

    void layout(Rect rect) {
        this.r = rect;
    }

    
    private void frameFor(Wardrobe$Section var1_1) {
        var2_2 = SearchIndex.a;
        if (var1_1 == this.framed) {
            return;
        }
        this.framed = var1_1;
        switch (Turntable$5.$SwitchMap$dev$quelchi$aurora$gui$shell$Wardrobe$Section[var1_1.ordinal()]) {
            case 1: {
                v0 = Turntable.BACK;
                break;
            }
            case 2: {
                v0 = Turntable.BACK_ANGLE;
                break;
            }
            case 3: {
                v0 = Turntable.STREAM;
                break;
            }
            case 4: 
            case 5: {
                v0 = Turntable.ROOM;
                break;
            }
            case 6: {
                v0 = Turntable.HIT;
                break;
            }
            default: {
                v0 = Turntable.FRONT;
            }
        }
        var3_3 = v0;
        this.yawTo = var3_3[0];
        this.pitchTo = var3_3[1];
        this.zoomTo = var3_3[2];
        this.liftTo = var3_3[3];
        while (this.yawTo - this.yaw > 180.0f) {
            v1 = this;
            if (!var2_2) {
                v1.yawTo -= 360.0f;
                if (!var2_2) continue;
            }
        }
        do {
            v1 = this;
lbl36:

            if (!(v1.yaw - this.yawTo > 180.0f)) return;
            this.yawTo += 360.0f;
        } while (!var2_2);
    }

    static boolean wearable(Wardrobe$Section wardrobe$Section) {
        boolean bl = wardrobe$Section != Wardrobe$Section.BADGES;
        return bl;
    }

    void render(DrawContext drawContext, int n, int n2, Wardrobe$Section wardrobe$Section, float f) {
        block66: {
            String string;
            OtherClientPlayerEntity otherClientPlayerEntity;
            block68: {
                block67: {
                    block64: {
                        block65: {
                            Turntable turntable;
                            float f2;
                            block63: {
                                float f3;
                                float f4;
                                boolean bl;
                                block62: {
                                    Turntable turntable2;
                                    block60: {
                                        block61: {
                                            float f5;
                                            block59: {
                                                Turntable turntable3;
                                                block55: {
                                                    block52: {
                                                        float f6;
                                                        float f7;
                                                        block56: {
                                                            Turntable turntable4;
                                                            block58: {
                                                                block57: {
                                                                    this.frameFor(wardrobe$Section);
                                                                    bl = SearchIndex.a;
                                                                    if (!Turntable.wearable(wardrobe$Section)) {
                                                                        return;
                                                                    }
                                                                    long l = System.nanoTime();
                                                                    float f8 = this.lastNanos == 0L ? 0.0f : Math.min(0.1f, (float)(l - this.lastNanos) / 1.0E9f);
                                                                    float f9 = f8;
                                                                    this.lastNanos = l;
                                                                    float f10 = f9;
                                                                    while (f10 > 0.0f) {
                                                                        block53: {
                                                                            float f11;
                                                                            block54: {
                                                                                f11 = Math.min(0.004166667f, f10);
                                                                                if (bl) break block52;
                                                                                if (bl) break block53;
                                                                                if (this.held) break block54;
                                                                                this.yawV += Turntable.spring(this.yaw, this.yawTo, this.yawV, 42.0f) * f11;
                                                                                this.yaw += this.yawV * f11;
                                                                                this.pitchV += Turntable.spring(this.pitch, this.pitchTo, this.pitchV, 42.0f) * f11;
                                                                                this.pitch += this.pitchV * f11;
                                                                            }
                                                                            this.zoomV += Turntable.spring(this.zoom, this.zoomTo, this.zoomV, 70.0f) * f11;
                                                                            this.zoom += this.zoomV * f11;
                                                                            this.liftV += Turntable.spring(this.lift, this.liftTo, this.liftV, 70.0f) * f11;
                                                                            this.lift += this.liftV * f11;
                                                                            f10 -= 0.004166667f;
                                                                        }
                                                                        if (!bl) continue;
                                                                    }
                                                                    turntable3 = this;
                                                                    if (bl) break block55;
                                                                    if (turntable3.held) break block52;
                                                                    f7 = this.yawTo;
                                                                    f6 = this.yaw;
                                                                    if (bl) break block56;
                                                                    if (!(Math.abs(f7 - f6) < 0.05f)) break block57;
                                                                    turntable4 = this;
                                                                    if (bl) break block58;
                                                                    if (!(Math.abs(turntable4.yawV) < 0.5f)) break block57;
                                                                    this.yaw = this.yawTo;
                                                                    this.yawV = 0.0f;
                                                                }
                                                                turntable4 = this;
                                                            }
                                                            f5 = turntable4.pitchTo - this.pitch;
                                                            if (bl) break block59;
                                                            f7 = Math.abs(f5);
                                                            f6 = 0.05f;
                                                        }
                                                        if (!(f7 < f6)) break block52;
                                                        f5 = this.pitchV;
                                                        if (bl) break block59;
                                                        if (!(Math.abs(f5) < 0.5f)) break block52;
                                                        this.pitch = this.pitchTo;
                                                        this.pitchV = 0.0f;
                                                    }
                                                    turntable3 = this;
                                                }
                                                f2 = turntable3.zoomTo;
                                                turntable2 = this;
                                                if (bl) break block60;
                                                f5 = Math.abs(f2 - turntable2.zoom);
                                            }
                                            if (!(f5 < 0.001f)) break block61;
                                            f4 = Math.abs(this.zoomV);
                                            f3 = 0.01f;
                                            if (bl) break block62;
                                            if (!(f4 < f3)) break block61;
                                            this.zoom = this.zoomTo;
                                            this.zoomV = 0.0f;
                                        }
                                        f2 = this.liftTo;
                                        if (bl) break block63;
                                        turntable2 = this;
                                    }
                                    f4 = Math.abs(f2 - turntable2.lift);
                                    f3 = 0.001f;
                                }
                                if (!(f4 < f3)) break block64;
                                turntable = this;
                                if (bl) break block65;
                                f2 = Math.abs(turntable.liftV);
                            }
                            if (!(f2 < 0.01f)) break block64;
                            this.lift = this.liftTo;
                            turntable = this;
                        }
                        turntable.liftV = 0.0f;
                    }
                    if (MinecraftClient.method_1551().field_1724 != null) break block66;
                    otherClientPlayerEntity = PreviewPlayer.get();
                    if (wardrobe$Section != Wardrobe$Section.EMOTES) break block67;
                    string = Cosmetics.getOwnEmote();
                    break block68;
                }
                string = "";
            }
            EmoteEngine.preview((AbstractClientPlayerEntity)otherClientPlayerEntity, string, f);
        }
        int n3 = 6;
        this.ring(drawContext, this.r.x() + n3, this.r.y() + n3, this.r.w() - n3 * 2, this.r.h() - n3 * 2);
        Mannequin.draw(drawContext, this.r.x() + n3, this.r.y() + n3, this.r.w() - n3 * 2, this.r.h() - n3 * 2, f, this.yaw, this.pitch, this.zoom, this.lift, this.act(wardrobe$Section, f));
    }

    private void ring(DrawContext drawContext, int n, int n2, int n3, int n4) {
        block5: {
            Identifier identifier = VectorIcons.texture(a[1], 990);
            boolean bl = SearchIndex.a;
            if (identifier == null) {
                return;
            }
            float f = Math.min((float)(n4 - 12) / 2.4f, (float)n3 / 2.7f) * this.zoom;
            float f2 = (float)n2 + (float)n4 / 2.0f + (0.9f + this.lift) * f * (float)Math.cos(Math.toRadians(this.pitch));
            float f3 = 0.9f * f / 172.0f;
            float[] fArray = Im.INK;
            int n5 = Math.round(fArray[0] * 255.0f) << 16 | Math.round(fArray[1] * 255.0f) << 8 | Math.round(fArray[2] * 255.0f);
            Matrix3x2fStack matrix3x2fStack = drawContext.method_51448();
            matrix3x2fStack.pushMatrix();
            matrix3x2fStack.translate((float)n + (float)n3 / 2.0f - 165.0f * f3, f2 - 34.0f * f3);
            matrix3x2fStack.scale(f3, f3);
            for (int i = 0; i < 70; i += 7) {
                float f4 = Math.max(0.0f, Math.min(1.0f, ((float)i + 3.5f - 17.0f) / 34.0f));
                if (!bl) {
                    int n6 = Math.round((0.12f + 0.48f * f4) * Menu.fade * 255.0f);
                    drawContext.method_25293(RenderPipelines.field_56883, identifier, 0, i, 0.0f, (float)i * 3.0f, 330, 7, 990, 21, 990, 990, n6 << 24 | n5);
                    if (!bl) continue;
                }
                break block5;
            }
            matrix3x2fStack.popMatrix();
        }
    }

    
    private Mannequin$Act act(Wardrobe$Section wardrobe$Section, float f) {
        block12: {
            block11: {
                boolean bl = SearchIndex.a;
                String string = wardrobe$Section.name() + ":" + wardrobe$Section.worn();
                if (bl) break block11;
                if (string.equals(this.showing)) break block12;
                this.showing = string;
            }
            this.loopFrom = f;
        }
        float f2 = f - this.loopFrom;
        switch (Turntable$5.$SwitchMap$dev$quelchi$aurora$gui$shell$Wardrobe$Section[wardrobe$Section.ordinal()]) {
            case 3: {
                Mannequin$Act mannequin$Act = this.walking(f);
                return mannequin$Act;
            }
            case 4: {
                Mannequin$Act mannequin$Act = this.dying(f2);
                return mannequin$Act;
            }
            case 6: {
                Mannequin$Act mannequin$Act = this.smashed(f2);
                return mannequin$Act;
            }
            case 5: {
                Mannequin$Act mannequin$Act = this.struck(f2);
                return mannequin$Act;
            }
        }
        return null;
    }

    private Mannequin$Act walking(float f) {
        Trails$Trail trails$Trail = Trails.byId(Cosmetics.shownTrail());
        if (trails$Trail == null) {
            return null;
        }
        return new Turntable$1(this, f, trails$Trail);
    }

    private Mannequin$Act dying(float f) {
        EffectEngine$Effect effectEngine$Effect = KillEffects.LIST.byId(Cosmetics.getOwnKillEffect());
        if (effectEngine$Effect == null) {
            return null;
        }
        float f2 = Math.max(0.5f, EffectEngine.previewLife(effectEngine$Effect));
        float f3 = 0.7f + Math.max(f2, 1.1f) + 0.8f;
        float f4 = f % f3 - 0.7f;
        return new Turntable$2(this, f4, effectEngine$Effect);
    }

    private Mannequin$Act smashed(float f) {
        float f2;
        OtherClientPlayerEntity otherClientPlayerEntity;
        float f3;
        float f4;
        EffectEngine$Effect effectEngine$Effect;
        block16: {
            block17: {
                boolean bl;
                block14: {
                    OtherClientPlayerEntity otherClientPlayerEntity2;
                    block15: {
                        block13: {
                            effectEngine$Effect = MaceEffects.LIST.byId(Cosmetics.getOwnMaceEffect());
                            bl = SearchIndex.a;
                            if (effectEngine$Effect == null) {
                                return null;
                            }
                            float f5 = Math.max(0.5f, EffectEngine.previewLife(effectEngine$Effect));
                            float f6 = 0.55f + Math.max(f5, 1.3f) + 0.7f;
                            f4 = f % f6;
                            f3 = f4 - 0.55f;
                            otherClientPlayerEntity = PreviewPlayer.actor(a[0], true);
                            otherClientPlayerEntity2 = otherClientPlayerEntity;
                            if (bl) break block13;
                            if (otherClientPlayerEntity2 == null) break block14;
                            otherClientPlayerEntity2 = otherClientPlayerEntity;
                        }
                        if (bl) break block15;
                        if (otherClientPlayerEntity2.method_6047().method_31574(Items.field_49814)) break block14;
                        otherClientPlayerEntity2 = otherClientPlayerEntity;
                    }
                    otherClientPlayerEntity2.method_6122(Hand.field_5808, new ItemStack((ItemConvertible)Items.field_49814));
                }
                float f7 = Math.max(0.0f, Math.min(1.0f, f4 / 0.55f));
                f2 = f3;
                if (bl) break block16;
                if (!(f2 < 0.0f)) break block17;
                f2 = 4.5f * (1.0f - f7) * (1.0f - f7);
                break block16;
            }
            f2 = 0.0f;
        }
        float f8 = f2;
        float f9 = Math.max(0.0f, Math.min(1.0f, (f4 - 0.41000003f) / 0.28f));
        double d = Math.sqrt(1.865f);
        float f10 = (float)((double)-0.55f / d);
        float f11 = (float)(1.25 / d);
        return new Turntable$3(this, f3, effectEngine$Effect, otherClientPlayerEntity, f10, f11, f9, f8);
    }

    private Mannequin$Act struck(float f) {
        EffectEngine$Effect effectEngine$Effect = HitEffects.LIST.byId(Cosmetics.getOwnHitEffect());
        if (effectEngine$Effect == null) {
            return null;
        }
        float f2 = 0.4f + Math.max(EffectEngine.previewLife(effectEngine$Effect), 0.5f) + 0.3f;
        float f3 = f % f2 - 0.4f;
        return new Turntable$4(this, f3, effectEngine$Effect);
    }

    private static float spring(float f, float f2, float f3, float f4) {
        return -f4 * (f - f2) - 2.0f * (float)Math.sqrt(f4) * f3;
    }

    boolean click(double d, double d2, int n) {
        block10: {
            Turntable turntable;
            block11: {
                boolean bl;
                block8: {
                    block9: {
                        boolean bl2 = SearchIndex.a;
                        bl = n;
                        if (bl2) break block8;
                        if (bl) break block9;
                        turntable = this;
                        if (bl2) break block10;
                        if (turntable.r.has(d, d2)) break block11;
                    }
                    bl = false;
                }
                return bl;
            }
            this.held = true;
            this.grabX = d;
            this.grabY = d2;
            this.grabYaw = this.yaw;
            this.grabPitch = this.pitch;
            turntable = this;
        }
        this.pitchV = 0.0f;
        turntable.yawV = 0.0f;
        return true;
    }

    boolean release(double d, double d2, int n) {
        block4: {
            Turntable turntable;
            block5: {
                boolean bl = SearchIndex.a;
                turntable = this;
                if (bl) break block4;
                if (turntable.held) break block5;
                return false;
            }
            this.held = false;
            this.yawTo = this.yaw;
            this.pitchTo = this.pitch;
            turntable = this;
        }
        this.pitchV = 0.0f;
        turntable.yawV = 0.0f;
        return true;
    }

    boolean drag(double d, double d2) {
        block4: {
            Turntable turntable;
            block5: {
                boolean bl = SearchIndex.a;
                turntable = this;
                if (bl) break block4;
                if (turntable.held) break block5;
                return false;
            }
            this.yaw = this.grabYaw - (float)(d - this.grabX) * 1.0f;
            turntable = this;
        }
        turntable.pitch = MathHelper.method_15363((float)(this.grabPitch + (float)(d2 - this.grabY) * 0.4f), (float)-32.0f, (float)32.0f);
        return true;
    }

    boolean scroll(double d, double d2, double d3) {
        block4: {
            Turntable turntable;
            block5: {
                boolean bl = SearchIndex.a;
                turntable = this;
                if (bl) break block4;
                if (turntable.r.has(d, d2)) break block5;
                return false;
            }
            turntable = this;
        }
        turntable.zoomTo = MathHelper.method_15363((float)(this.zoomTo + (float)d3 * 0.12f), (float)0.6f, (float)3.0f);
        return true;
    }

    
    static {
        var9 = new String[2];
        var7_1 = 0;
        var6_2 = "16\u000f_\t(\"\u001eT\u0018_T20";
        var8_3 = "16\u000f_\t(\"\u001eT\u0018_T20".length();
        var5_4 = 4;
        var4_5 = -1;
lbl7:

        while (true) {
            v0 = ++var4_5;
            v1 = var6_2.substring(v0, v0 + var5_4).toCharArray();
            v2 = v1;
            v3 = v1.length;
            var10_6 = 0;
            break;
        }
lbl14:

        while (true) {
            var5_4 = var6_2.charAt(var4_5);
            continue;
            break;
        }
        do {
            v2 = v2;
            v4 = var10_6;
            v5 = v2[v4];
            switch (var10_6 % 7) {
                case 0: {
                    v6 = 92;
                    break;
                }
                case 1: {
                    v6 = 87;
                    break;
                }
                case 2: {
                    v6 = 108;
                    break;
                }
                case 3: {
                    v6 = 58;
                    break;
                }
                case 4: {
                    v6 = 71;
                    break;
                }
                case 5: {
                    v6 = 45;
                    break;
                }
                default: {
                    v6 = 61;
                }
            }
            v2[v4] = (char)(v5 ^ v6);
            ++var10_6;
lbl44:

            v3 = v3;
        } while (v3 > var10_6);
        var9[var7_1++] = new String(v2).intern();
        while ((var4_5 += var5_4) < var8_3)
lbl50:

        Turntable.a = var9;
        var2_7 = 6362980872887847959L;
        var0_8 = 8644741845854048201L ^ var2_7;
        Turntable.RING_PX = (int)var0_8;
        Turntable.FRONT = new float[]{0.0f, 0.0f, 1.0f, 0.0f};
        Turntable.BACK = new float[]{180.0f, 0.0f, 1.05f, 0.0f};
        Turntable.BACK_ANGLE = new float[]{205.0f, 6.0f, 0.94f, 0.0f};
        Turntable.ROOM = new float[]{0.0f, 6.0f, 0.7f, 0.15f};
        Turntable.HIT = new float[]{-55.0f, -22.0f, 0.62f, 0.25f};
        Turntable.STREAM = new float[]{-40.0f, 10.0f, 0.72f, 0.1f};
    }
}
