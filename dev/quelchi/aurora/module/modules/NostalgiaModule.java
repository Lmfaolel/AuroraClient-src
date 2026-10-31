package dev.quelchi.aurora.module.modules;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.VertexFormat;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.UniformType;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.texture.ReloadableTexture;
import net.minecraft.client.texture.ResourceTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.joml.Vector3f;
import org.joml.Vector3fc;

public final class NostalgiaModule
extends Module {
    private static final Identifier OLD_GLINT_ITEM;
    private static final Identifier OLD_GLINT_ARMOR;
    private static NostalgiaModule instance;
    private final BooleanSetting oldCrystals;
    private final BooleanSetting oldLighting;
    private final BooleanSetting oldGlint;
    private final BooleanSetting oldHurtCam;
    private final BooleanSetting potionGlint;
    private final BooleanSetting oldPotions;
    private final BooleanSetting oldWalk;
    private boolean glintApplied;
    private static RenderPipeline oldLightmap;
    private static boolean oldLightmapBroken;
    private static final String[] b;

    public NostalgiaModule() {
        String[] stringArray = b;
        super(stringArray[3], stringArray[9], Category.RENDER, false);
        this.oldCrystals = new BooleanSetting(stringArray[0], false);
        this.oldLighting = new BooleanSetting(stringArray[2], false);
        this.oldGlint = new BooleanSetting(stringArray[7], false);
        this.oldHurtCam = new BooleanSetting(stringArray[11], false);
        this.potionGlint = new BooleanSetting(stringArray[4], false);
        this.oldPotions = new BooleanSetting(stringArray[12], false);
        this.oldWalk = new BooleanSetting(stringArray[15], false);
        instance = this;
        this.addSettings(this.oldCrystals, this.oldLighting, this.oldGlint, this.oldHurtCam, this.potionGlint, this.oldPotions, this.oldWalk);
    }

    public static boolean oldCrystals() {
        boolean bl;
        block11: {
            block9: {
                NostalgiaModule nostalgiaModule;
                boolean bl2;
                block10: {
                    NostalgiaModule nostalgiaModule2;
                    block8: {
                        nostalgiaModule2 = instance;
                        bl2 = AmbienceModule.b;
                        nostalgiaModule = nostalgiaModule2;
                        if (bl2) break block8;
                        if (nostalgiaModule == null) break block9;
                        nostalgiaModule = nostalgiaModule2;
                    }
                    if (bl2) break block10;
                    if (!nostalgiaModule.isEnabled()) break block9;
                    nostalgiaModule = nostalgiaModule2;
                }
                bl = nostalgiaModule.oldCrystals.getValue();
                if (bl2) break block11;
                if (!bl) break block9;
                bl = true;
                break block11;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean oldHurtCam() {
        boolean bl;
        block11: {
            block9: {
                NostalgiaModule nostalgiaModule;
                boolean bl2;
                block10: {
                    NostalgiaModule nostalgiaModule2;
                    block8: {
                        nostalgiaModule2 = instance;
                        bl2 = AmbienceModule.b;
                        nostalgiaModule = nostalgiaModule2;
                        if (bl2) break block8;
                        if (nostalgiaModule == null) break block9;
                        nostalgiaModule = nostalgiaModule2;
                    }
                    if (bl2) break block10;
                    if (!nostalgiaModule.isEnabled()) break block9;
                    nostalgiaModule = nostalgiaModule2;
                }
                bl = nostalgiaModule.oldHurtCam.getValue();
                if (bl2) break block11;
                if (!bl) break block9;
                bl = true;
                break block11;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean oldPotions() {
        boolean bl;
        block11: {
            block9: {
                NostalgiaModule nostalgiaModule;
                boolean bl2;
                block10: {
                    NostalgiaModule nostalgiaModule2;
                    block8: {
                        nostalgiaModule2 = instance;
                        bl2 = AmbienceModule.b;
                        nostalgiaModule = nostalgiaModule2;
                        if (bl2) break block8;
                        if (nostalgiaModule == null) break block9;
                        nostalgiaModule = nostalgiaModule2;
                    }
                    if (bl2) break block10;
                    if (!nostalgiaModule.isEnabled()) break block9;
                    nostalgiaModule = nostalgiaModule2;
                }
                bl = nostalgiaModule.oldPotions.getValue();
                if (bl2) break block11;
                if (!bl) break block9;
                bl = true;
                break block11;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean potionGlint() {
        boolean bl;
        block11: {
            block9: {
                NostalgiaModule nostalgiaModule;
                boolean bl2;
                block10: {
                    NostalgiaModule nostalgiaModule2;
                    block8: {
                        nostalgiaModule2 = instance;
                        bl2 = AmbienceModule.b;
                        nostalgiaModule = nostalgiaModule2;
                        if (bl2) break block8;
                        if (nostalgiaModule == null) break block9;
                        nostalgiaModule = nostalgiaModule2;
                    }
                    if (bl2) break block10;
                    if (!nostalgiaModule.isEnabled()) break block9;
                    nostalgiaModule = nostalgiaModule2;
                }
                bl = nostalgiaModule.potionGlint.getValue();
                if (bl2) break block11;
                if (!bl) break block9;
                bl = true;
                break block11;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean oldWalkAnimation() {
        boolean bl;
        block11: {
            block9: {
                NostalgiaModule nostalgiaModule;
                boolean bl2;
                block10: {
                    NostalgiaModule nostalgiaModule2;
                    block8: {
                        nostalgiaModule2 = instance;
                        bl2 = AmbienceModule.b;
                        nostalgiaModule = nostalgiaModule2;
                        if (bl2) break block8;
                        if (nostalgiaModule == null) break block9;
                        nostalgiaModule = nostalgiaModule2;
                    }
                    if (bl2) break block10;
                    if (!nostalgiaModule.isEnabled()) break block9;
                    nostalgiaModule = nostalgiaModule2;
                }
                bl = nostalgiaModule.oldWalk.getValue();
                if (bl2) break block11;
                if (!bl) break block9;
                bl = true;
                break block11;
            }
            bl = false;
        }
        return bl;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static RenderPipeline lightmapPipeline(RenderPipeline renderPipeline) {
        RenderPipeline renderPipeline2;
        block20: {
            RenderPipeline renderPipeline3;
            block21: {
                boolean bl;
                boolean bl2;
                block19: {
                    NostalgiaModule nostalgiaModule;
                    block18: {
                        NostalgiaModule nostalgiaModule2;
                        block17: {
                            nostalgiaModule2 = instance;
                            bl2 = AmbienceModule.b;
                            nostalgiaModule = nostalgiaModule2;
                            if (bl2) break block17;
                            if (nostalgiaModule == null) return renderPipeline;
                            nostalgiaModule = nostalgiaModule2;
                        }
                        if (bl2) break block18;
                        if (!nostalgiaModule.isEnabled()) return renderPipeline;
                        nostalgiaModule = nostalgiaModule2;
                    }
                    bl = nostalgiaModule.oldLighting.getValue();
                    if (bl2) break block19;
                    if (!bl) return renderPipeline;
                    bl = oldLightmapBroken;
                }
                if (bl) {
                    return renderPipeline;
                }
                renderPipeline2 = oldLightmap;
                if (bl2) return renderPipeline2;
                if (renderPipeline2 != null) break block20;
                String[] stringArray = b;
                renderPipeline3 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(Identifier.method_60655((String)stringArray[14], (String)stringArray[6])).withVertexShader(stringArray[17]).withFragmentShader(Identifier.method_60655((String)stringArray[14], (String)stringArray[1])).withUniform(stringArray[16], UniformType.field_60031).withVertexFormat(VertexFormats.field_60033, VertexFormat.DrawMode.field_27379).withDepthWrite(false).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).build();
                if (bl2) return renderPipeline;
                if (RenderSystem.getDevice().precompilePipeline(renderPipeline3, null).isValid()) break block21;
                oldLightmapBroken = true;
                Aurora.LOG.warn(b[5]);
                return renderPipeline;
            }
            oldLightmap = renderPipeline3;
        }
        renderPipeline2 = oldLightmap;
        return renderPipeline2;
    }

    private static float oldSkyBrightness(ClientWorld clientWorld) {
        double d = MathHelper.method_15385((double)((double)clientWorld.method_8532() / 24000.0 - 0.25));
        double d2 = 0.5 - Math.cos(d * Math.PI) / 2.0;
        float f = (float)(d * 2.0 + d2) / 3.0f;
        float f2 = 1.0f - (MathHelper.method_15362((double)(f * ((float)Math.PI * 2))) * 2.0f + 0.2f);
        f2 = MathHelper.method_15363((float)f2, (float)0.0f, (float)1.0f);
        f2 = 1.0f - f2;
        f2 *= 1.0f - clientWorld.method_8430(1.0f) * 5.0f / 16.0f;
        return (f2 *= 1.0f - clientWorld.method_8478(1.0f) * 5.0f / 16.0f) * 0.8f + 0.2f;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static float oldSkyLightFactor(ClientWorld clientWorld, boolean bl, float f) {
        boolean bl2;
        block18: {
            block19: {
                boolean bl3;
                block17: {
                    NostalgiaModule nostalgiaModule;
                    block16: {
                        NostalgiaModule nostalgiaModule2;
                        block15: {
                            nostalgiaModule2 = instance;
                            bl3 = AmbienceModule.b;
                            nostalgiaModule = nostalgiaModule2;
                            if (bl3) break block15;
                            if (nostalgiaModule == null) return f;
                            nostalgiaModule = nostalgiaModule2;
                        }
                        if (bl3) break block16;
                        if (!nostalgiaModule.isEnabled()) return f;
                        nostalgiaModule = nostalgiaModule2;
                    }
                    bl2 = nostalgiaModule.oldLighting.getValue();
                    if (bl3) break block17;
                    if (!bl2) return f;
                    bl2 = oldLightmapBroken;
                }
                if (bl3) break block18;
                if (!bl2) break block19;
                return f;
            }
            bl2 = bl;
        }
        if (bl2) {
            return 1.0f;
        }
        float f2 = NostalgiaModule.oldSkyBrightness(clientWorld) * 0.95f + 0.05f;
        return f2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Vector3f oldSkyLightColor(ClientWorld clientWorld, Vector3f vector3f) {
        boolean bl;
        block13: {
            NostalgiaModule nostalgiaModule;
            boolean bl2;
            block12: {
                NostalgiaModule nostalgiaModule2;
                block11: {
                    nostalgiaModule2 = instance;
                    bl2 = AmbienceModule.b;
                    nostalgiaModule = nostalgiaModule2;
                    if (bl2) break block11;
                    if (nostalgiaModule == null) return vector3f;
                    nostalgiaModule = nostalgiaModule2;
                }
                if (bl2) break block12;
                if (!nostalgiaModule.isEnabled()) return vector3f;
                nostalgiaModule = nostalgiaModule2;
            }
            bl = nostalgiaModule.oldLighting.getValue();
            if (bl2) break block13;
            if (!bl) return vector3f;
            bl = oldLightmapBroken;
        }
        if (bl) {
            return vector3f;
        }
        float f = NostalgiaModule.oldSkyBrightness(clientWorld);
        return new Vector3f(f, f, 1.0f).lerp((Vector3fc)new Vector3f(1.0f, 1.0f, 1.0f), 0.35f);
    }

    public static boolean oldGlint() {
        boolean bl;
        block11: {
            block9: {
                NostalgiaModule nostalgiaModule;
                boolean bl2;
                block10: {
                    NostalgiaModule nostalgiaModule2;
                    block8: {
                        nostalgiaModule2 = instance;
                        bl2 = AmbienceModule.b;
                        nostalgiaModule = nostalgiaModule2;
                        if (bl2) break block8;
                        if (nostalgiaModule == null) break block9;
                        nostalgiaModule = nostalgiaModule2;
                    }
                    if (bl2) break block10;
                    if (!nostalgiaModule.isEnabled()) break block9;
                    nostalgiaModule = nostalgiaModule2;
                }
                bl = nostalgiaModule.oldGlint.getValue();
                if (bl2) break block11;
                if (!bl) break block9;
                bl = true;
                break block11;
            }
            bl = false;
        }
        return bl;
    }

    public static void reconcile() {
        block3: {
            NostalgiaModule nostalgiaModule;
            block2: {
                NostalgiaModule nostalgiaModule2 = instance;
                boolean bl = AmbienceModule.b;
                nostalgiaModule = nostalgiaModule2;
                if (bl) break block2;
                if (nostalgiaModule == null) break block3;
                nostalgiaModule = nostalgiaModule2;
            }
            nostalgiaModule.apply();
        }
    }

    @Override
    public void onEnable() {
        this.apply();
    }

    @Override
    public void onDisable() {
        this.apply();
    }

    @Override
    public void onSettingsChanged() {
        this.apply();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void apply() {
        ResourceTexture resourceTexture;
        boolean bl;
        MinecraftClient minecraftClient;
        block24: {
            block25: {
                boolean bl2;
                boolean bl3;
                block23: {
                    block22: {
                        NostalgiaModule nostalgiaModule;
                        block21: {
                            MinecraftClient minecraftClient2;
                            block20: {
                                bl3 = AmbienceModule.b;
                                if (!Aurora.booted) {
                                    return;
                                }
                                minecraftClient = MinecraftClient.method_1551();
                                minecraftClient2 = minecraftClient;
                                if (bl3) break block20;
                                if (minecraftClient2 == null) return;
                                minecraftClient2 = minecraftClient;
                            }
                            if (minecraftClient2.method_1531() == null) {
                                return;
                            }
                            nostalgiaModule = this;
                            if (bl3) break block21;
                            if (!nostalgiaModule.isEnabled()) break block22;
                            nostalgiaModule = this;
                        }
                        bl2 = nostalgiaModule.oldGlint.getValue();
                        if (bl3) break block23;
                        if (!bl2) break block22;
                        bl2 = true;
                        break block23;
                    }
                    bl2 = false;
                }
                bl = bl2;
                if (bl3) break block24;
                if (bl != this.glintApplied) break block25;
                return;
            }
            this.glintApplied = bl;
        }
        TextureManager textureManager = minecraftClient.method_1531();
        TextureManager textureManager2 = textureManager;
        Identifier identifier = ItemRenderer.field_43087;
        ResourceTexture resourceTexture2 = resourceTexture;
        ResourceTexture resourceTexture3 = resourceTexture;
        Identifier identifier2 = bl ? OLD_GLINT_ITEM : ItemRenderer.field_43087;
        resourceTexture2(identifier2);
        textureManager2.method_65876(identifier, (ReloadableTexture)resourceTexture3);
        textureManager.method_65876(ItemRenderer.field_43086, (ReloadableTexture)new ResourceTexture(bl ? OLD_GLINT_ARMOR : ItemRenderer.field_43086));
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[18];
                var3_1 = 0;
                var2_2 = "\u001a\u0019W1%\u0000O&\u0001R}\u0015\u00116\u001aAtI\u001e_2\u001dG|\u0007\u0002i:\u0019W\f\u001a\u0019W1*\u001bQ=\u0001Z\u007f\u0001\t\u001b\u001a@e\u0007\u001eQ<\u0014\f\u0005\u001aGx\t\u001c\u0016\u0012\u0019Z\u007f\u0012G\u001b\u001a@e\u0007\u001eQ<\u0014\t1\u0012\u001aSu\u001a_uF\u001e_2\u001dG|\u0007\u0002\u0016&\u001dRu\u0003\u0000\u00161\u001cW1\b\u001dBu\u0016\\|\u0016\u001bZ0Y\u0013^\n\u0016\u0016\u0019\u001cTy\u0012\u001bX2UZbF\u001dP3\u0015%\u001cCt\n\u001bX0Z_x\u0001\u001aB8\u0014CN\t\u001eR\t\u001a\u0019W1!\u001e_;\u0001\u00064\u0000A~\u0014\u00131\u0016\u0007Jb\u0012\u0013Z&Y\u0013}\u000f\u0015^!\u001c]vF\u0013X1UT}\u000f\u001cBu\u0014@1\u0012\u001aS,UFb\u0003\u0016\u0016!\u001a\u0013}\t\u001d] !\u0010Ke\u0013\u0000S&Z^x\u0015\u0011\u0019:\u0019WN\u0001\u001e_;\u0001lx\u0012\u0017[{\u0005]v\u000f\u001a\u0019W1.\u0007D!Upp\u000b\u0017D4\u000b\u001a\u0019W16\u001dB<\u001a]b!!\u0010Ke\u0013\u0000S&Z^x\u0015\u0011\u0019:\u0019WN\u0001\u001e_;\u0001lp\u0014\u001fY'[C\u007f\u0001\u00064\u0000A~\u0014\u0013\u0012\u001a\u0019W11\u0013Z>Ur\u007f\u000f\u001fW!\u001c\\\u007f";
                var4_3 = "\u001a\u0019W1%\u0000O&\u0001R}\u0015\u00116\u001aAtI\u001e_2\u001dG|\u0007\u0002i:\u0019W\f\u001a\u0019W1*\u001bQ=\u0001Z\u007f\u0001\t\u001b\u001a@e\u0007\u001eQ<\u0014\f\u0005\u001aGx\t\u001c\u0016\u0012\u0019Z\u007f\u0012G\u001b\u001a@e\u0007\u001eQ<\u0014\t1\u0012\u001aSu\u001a_uF\u001e_2\u001dG|\u0007\u0002\u0016&\u001dRu\u0003\u0000\u00161\u001cW1\b\u001dBu\u0016\\|\u0016\u001bZ0Y\u0013^\n\u0016\u0016\u0019\u001cTy\u0012\u001bX2UZbF\u001dP3\u0015%\u001cCt\n\u001bX0Z_x\u0001\u001aB8\u0014CN\t\u001eR\t\u001a\u0019W1!\u001e_;\u0001\u00064\u0000A~\u0014\u00131\u0016\u0007Jb\u0012\u0013Z&Y\u0013}\u000f\u0015^!\u001c]vF\u0013X1UT}\u000f\u001cBu\u0014@1\u0012\u001aS,UFb\u0003\u0016\u0016!\u001a\u0013}\t\u001d] !\u0010Ke\u0013\u0000S&Z^x\u0015\u0011\u0019:\u0019WN\u0001\u001e_;\u0001lx\u0012\u0017[{\u0005]v\u000f\u001a\u0019W1.\u0007D!Upp\u000b\u0017D4\u000b\u001a\u0019W16\u001dB<\u001a]b!!\u0010Ke\u0013\u0000S&Z^x\u0015\u0011\u0019:\u0019WN\u0001\u001e_;\u0001lp\u0014\u001fY'[C\u007f\u0001\u00064\u0000A~\u0014\u0013\u0012\u001a\u0019W11\u0013Z>Ur\u007f\u000f\u001fW!\u001c\\\u007f".length();
                var1_4 = 12;
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
                    var2_2 = "\u0019\u001cTy\u0012\u001fW%<]w\t\u000f6\u001aAtI\u0001U'\u0010V\u007f\u0017\u0007W1";
                    var4_3 = "\u0019\u001cTy\u0012\u001fW%<]w\t\u000f6\u001aAtI\u0001U'\u0010V\u007f\u0017\u0007W1".length();
                    var1_4 = 12;
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
                        v10 = 85;
                        break;
                    }
                    case 1: {
                        v10 = 117;
                        break;
                    }
                    case 2: {
                        v10 = 51;
                        break;
                    }
                    case 3: {
                        v10 = 17;
                        break;
                    }
                    case 4: {
                        v10 = 102;
                        break;
                    }
                    case 5: {
                        v10 = 114;
                        break;
                    }
                    default: {
                        v10 = 54;
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
        NostalgiaModule.b = var5;
        var0_6 = NostalgiaModule.b;
        NostalgiaModule.OLD_GLINT_ITEM = Identifier.method_60655((String)var0_6[8], (String)var0_6[10]);
        NostalgiaModule.OLD_GLINT_ARMOR = Identifier.method_60655((String)var0_6[14], (String)var0_6[13]);
    }
}
