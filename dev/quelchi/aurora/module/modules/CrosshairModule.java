package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.crosshair.CrosshairShape;
import dev.quelchi.aurora.crosshair.IconAnimation;
import dev.quelchi.aurora.crosshair.IconStore;
import dev.quelchi.aurora.gui.IconTex;
import dev.quelchi.aurora.gui.im.IconEditor;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.setting.ActionSetting;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.ColorSetting;
import dev.quelchi.aurora.module.setting.NumberSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;

public final class CrosshairModule
extends Module {
    public static final String CUSTOM_KEY;
    private static CrosshairModule instance;
    private final ActionSetting draw;
    private final BooleanSetting hover;
    private final ColorSetting hoverColor;
    private final NumberSetting hoverRange;
    private static final String[] b;
    private static final long d;

    public static CrosshairModule get() {
        return instance;
    }

    public CrosshairModule() {
        String[] stringArray = b;
        super(stringArray[2], stringArray[1], Category.COMBAT, false);
        this.draw = new ActionSetting(stringArray[4], stringArray[6], CrosshairModule::openEditor);
        this.hover = new BooleanSetting(stringArray[7], false);
        this.hoverColor = new ColorSetting(stringArray[3], (int)d);
        this.hoverRange = new NumberSetting(stringArray[0], 3.0, 64.0, 6.0, 1.0);
        instance = this;
        this.addSettings(this.draw, this.hover, this.hoverColor, this.hoverRange);
        IconStore.registerHidden(stringArray[5], 0, 0, 10, new int[][][]{CrosshairShape.shipped()});
        this.hover.children(this.hoverColor, this.hoverRange);
    }

    private static void openEditor() {
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        if (Im.available()) {
            minecraftClient.method_1507((Screen)new IconEditor(b[5]));
        }
    }

    
    public void render(DrawContext drawContext) {
        int n;
        int n2;
        int[][] nArray;
        IconAnimation iconAnimation;
        block12: {
            CrosshairModule crosshairModule;
            boolean bl;
            block11: {
                iconAnimation = IconStore.get(b[5]);
                bl = AmbienceModule.b;
                IconAnimation iconAnimation2 = iconAnimation;
                if (!bl) {
                    if (iconAnimation2 == null) {
                        return;
                    }
                    iconAnimation2 = iconAnimation;
                }
                nArray = iconAnimation2.previewFrame(System.currentTimeMillis());
                n2 = drawContext.method_51421();
                n = drawContext.method_51443();
                crosshairModule = this;
                if (bl) break block11;
                if (!crosshairModule.hover.getValue()) break block12;
                crosshairModule = this;
            }
            if (CrosshairModule.hoveringPlayer(crosshairModule.hoverRange.getValue())) {
                IconTex.drawCentredSolid(drawContext, nArray, n2, n, iconAnimation.getSize(), iconAnimation.getOffsetX(), iconAnimation.getOffsetY(), this.hoverColor.getARGB());
                if (!bl) return;
            }
        }
        IconTex.drawCentred(drawContext, nArray, n2, n, iconAnimation.getSize(), iconAnimation.getOffsetX(), iconAnimation.getOffsetY(), this.hoverColor.getARGB(), true);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static boolean hoveringPlayer(double d) {
        double d2;
        Vec3d vec3d;
        Vec3d vec3d2;
        Vec3d vec3d3;
        Entity entity;
        MinecraftClient minecraftClient;
        block25: {
            BlockHitResult blockHitResult;
            block26: {
                BlockHitResult blockHitResult2;
                boolean bl;
                block24: {
                    MinecraftClient minecraftClient2;
                    block22: {
                        block23: {
                            block21: {
                                block20: {
                                    minecraftClient = MinecraftClient.method_1551();
                                    bl = AmbienceModule.b;
                                    minecraftClient2 = minecraftClient;
                                    if (bl) break block20;
                                    if (minecraftClient2.field_1687 == null) return false;
                                    minecraftClient2 = minecraftClient;
                                }
                                if (bl) break block21;
                                if (minecraftClient2.field_1724 == null) return false;
                                minecraftClient2 = minecraftClient;
                            }
                            if (bl) break block22;
                            if (minecraftClient2.method_1560() != null) break block23;
                            return false;
                        }
                        minecraftClient2 = minecraftClient;
                    }
                    entity = minecraftClient2.method_1560();
                    vec3d3 = entity.method_5836(1.0f);
                    vec3d2 = entity.method_5828(1.0f);
                    vec3d = vec3d3.method_1019(vec3d2.method_1021(d));
                    d2 = d * d;
                    blockHitResult2 = minecraftClient.field_1687.method_17742(new RaycastContext(vec3d3, vec3d, RaycastContext.ShapeType.field_17558, RaycastContext.FluidHandling.field_1348, entity));
                    blockHitResult = blockHitResult2;
                    if (bl) break block24;
                    if (blockHitResult == null) break block25;
                    blockHitResult = blockHitResult2;
                }
                if (bl) break block26;
                if (blockHitResult.method_17783() == HitResult.Type.field_1333) break block25;
                blockHitResult = blockHitResult2;
            }
            d2 = blockHitResult.method_17784().method_1025(vec3d3);
        }
        Box box = entity.method_5829().method_18804(vec3d2.method_1021(d)).method_1014(1.0);
        EntityHitResult entityHitResult = ProjectileUtil.method_18075((Entity)entity, (Vec3d)vec3d3, (Vec3d)vec3d, (Box)box, arg_0 -> CrosshairModule.lambda$hoveringPlayer$0(minecraftClient, arg_0), (double)d2);
        if (entityHitResult == null) return false;
        return true;
    }

    private static boolean lambda$hoveringPlayer$0(MinecraftClient minecraftClient, Entity entity) {
        boolean bl;
        block13: {
            block11: {
                Entity entity2;
                boolean bl2;
                block12: {
                    block10: {
                        bl2 = AmbienceModule.b;
                        entity2 = entity;
                        if (bl2) break block10;
                        if (!(entity2 instanceof PlayerEntity)) break block11;
                        entity2 = entity;
                    }
                    if (bl2) break block12;
                    if (entity2 == minecraftClient.field_1724) break block11;
                    entity2 = entity;
                }
                bl = entity2.method_7325();
                if (bl2) break block13;
                if (bl) break block11;
                bl = true;
                break block13;
            }
            bl = false;
        }
        return bl;
    }

    
    static {
        block21: {
            block20: {
                block19: {
                    var7 = new String[8];
                    var5_1 = 0;
                    var4_2 = "G\u000bk.Ibbn\nz.\u0017K\u0016|<\u001b;_z\u0016=$L,\u0010l\u0016r8H*Qf\u0016\tL\u0016r8H*Qf\u0016\u000bG\u000bk.Ibs`\br9\u0007K\u0016|<R,W\tL\u0016r8H*Qf\u0016";
                    var6_3 = "G\u000bk.Ibbn\nz.\u0017K\u0016|<\u001b;_z\u0016=$L,\u0010l\u0016r8H*Qf\u0016\tL\u0016r8H*Qf\u0016\u000bG\u000bk.Ibs`\br9\u0007K\u0016|<R,W\tL\u0016r8H*Qf\u0016".length();
                    var3_4 = 11;
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
                        var4_2 = "J\u0000t?\f_\b|2^0\u0010G\u000bk.I";
                        var6_3 = "J\u0000t?\f_\b|2^0\u0010G\u000bk.I".length();
                        var3_4 = 4;
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
                            v10 = 15;
                            break;
                        }
                        case 1: {
                            v10 = 100;
                            break;
                        }
                        case 2: {
                            v10 = 29;
                            break;
                        }
                        case 3: {
                            v10 = 75;
                            break;
                        }
                        case 4: {
                            v10 = 59;
                            break;
                        }
                        case 5: {
                            v10 = 66;
                            break;
                        }
                        default: {
                            v10 = 48;
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
            CrosshairModule.b = var7;
            CrosshairModule.CUSTOM_KEY = CrosshairModule.b[5];
            break block21;
lbl79:

            while (true) {
                continue;
                break;
            }
        }
        var0_7 = 321116824284119186L;
        while (true)
        CrosshairModule.d = -4736457201594134334L ^ var0_7;
    }
}
