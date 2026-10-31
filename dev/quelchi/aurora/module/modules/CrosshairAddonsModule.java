package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.crosshair.IconAnimation;
import dev.quelchi.aurora.crosshair.IconStore;
import dev.quelchi.aurora.gui.IconTex;
import dev.quelchi.aurora.gui.im.IconEditor;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.UnscaledHud;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.CrosshairAddonsModule$Indicator;
import dev.quelchi.aurora.module.modules.ShieldTweaksModule;
import dev.quelchi.aurora.module.setting.ActionSetting;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.ColorSetting;
import dev.quelchi.aurora.module.setting.EnumSetting;
import dev.quelchi.aurora.module.setting.NumberSetting;
import dev.quelchi.aurora.module.setting.StringSetting;
import dev.quelchi.aurora.shield.ShieldStates;
import java.util.Iterator;
import java.util.Locale;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.AttackIndicator;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;

public final class CrosshairAddonsModule
extends Module
implements UnscaledHud {
    public static final String CREDIT;
    private static CrosshairAddonsModule instance;
    private final BooleanSetting elytra;
    private final BooleanSetting entityIndicator;
    private final BooleanSetting shieldIndicator;
    private final BooleanSetting shieldBreak;
    private final BooleanSetting hitmarker;
    private final NumberSetting hitmarkerMs;
    private final BooleanSetting blend;
    private final BooleanSetting thirdPerson;
    private final BooleanSetting hitmarkerUntilEnd;
    private final BooleanSetting breakUntilEnd;
    private final NumberSetting breakMs;
    private final BooleanSetting hitmarkerOnMelee;
    private final BooleanSetting shieldFactorDelay;
    private final BooleanSetting entityShowAll;
    private final StringSetting entityList;
    private final EnumSetting<CrosshairAddonsModule$Indicator> indicator;
    private final BooleanSetting moveAttackIndicator;
    private final NumberSetting attackIndicatorGap;
    private final ActionSetting editIcons;
    private final ActionSetting elytraIcon;
    private final ActionSetting entityIcon;
    private final ActionSetting shieldIcon;
    private final ActionSetting shieldBreakIcon;
    private final ActionSetting hitmarkerIcon;
    private final ColorSetting elytraColor;
    private final ColorSetting entityColor;
    private final ColorSetting shieldColor;
    private final ColorSetting shieldBreakColor;
    private final ColorSetting hitmarkerColor;
    private long lastProjectileHit;
    private boolean breakPlaying;
    private boolean hitPlaying;
    private long lastShieldBreak;
    private long lastMeleeSwing;
    private static final long CONFIRM_MS;
    private long pendingAt;
    private static final int[][] ENTITY;
    private static final int[][] ELYTRA;
    private static final int[][] HITMARKER;
    private static final int[][] SHIELD_BREAK_0;
    private static final int[][] SHIELD_BREAK_1;
    private static final int[][] SHIELD_BREAK_2;
    private static final int[][] SHIELD_BREAK_3;
    private static final int[][] SHIELD_BREAK_4;
    private static final int[][] SHIELD;
    private static final int[][][] SHIELD_BREAK;
    private static final String[] b;
    private static final long[] d;
    private static final Integer[] e;
    private static final long[] f;
    private static final Long[] g;

    private static Runnable openEditor(String string) {
        return () -> CrosshairAddonsModule.lambda$openEditor$0(string);
    }

    public CrosshairAddonsModule() {
        block4: {
            String[] stringArray = b;
            super(stringArray[3], stringArray[36], Category.COMBAT, false);
            this.elytra = new BooleanSetting(stringArray[12], true);
            this.entityIndicator = new BooleanSetting(stringArray[19], false);
            this.shieldIndicator = new BooleanSetting(stringArray[21], false);
            this.shieldBreak = new BooleanSetting(stringArray[24], false);
            this.hitmarker = new BooleanSetting(stringArray[0], false);
            this.hitmarkerMs = new NumberSetting(stringArray[35], 50.0, 1000.0, 300.0, 50.0);
            this.blend = new BooleanSetting(stringArray[9], true);
            this.thirdPerson = new BooleanSetting(stringArray[1], false);
            this.hitmarkerUntilEnd = new BooleanSetting(stringArray[2], true);
            this.breakUntilEnd = new BooleanSetting(stringArray[31], true);
            this.breakMs = new NumberSetting(stringArray[33], 50.0, 2000.0, 750.0, 50.0);
            this.hitmarkerOnMelee = new BooleanSetting(stringArray[5], false);
            this.shieldFactorDelay = new BooleanSetting(stringArray[30], true);
            this.entityShowAll = new BooleanSetting(stringArray[16], false);
            this.entityList = new StringSetting(stringArray[17], stringArray[37]);
            this.indicator = new EnumSetting<CrosshairAddonsModule$Indicator>(stringArray[4], CrosshairAddonsModule$Indicator.Vanilla);
            this.moveAttackIndicator = new BooleanSetting(stringArray[26], true);
            this.attackIndicatorGap = new NumberSetting(stringArray[38], -5.0, 10.0, -2.0, 1.0);
            this.editIcons = new ActionSetting(stringArray[20], stringArray[25], CrosshairAddonsModule.openEditor(null));
            this.elytraIcon = new ActionSetting(stringArray[15], stringArray[22], CrosshairAddonsModule.openEditor(stringArray[13]));
            this.entityIcon = new ActionSetting(stringArray[34], stringArray[18], CrosshairAddonsModule.openEditor(stringArray[39]));
            this.shieldIcon = new ActionSetting(stringArray[34], stringArray[18], CrosshairAddonsModule.openEditor(stringArray[23]));
            this.shieldBreakIcon = new ActionSetting(stringArray[14], stringArray[18], CrosshairAddonsModule.openEditor(stringArray[24]));
            this.hitmarkerIcon = new ActionSetting(stringArray[34], stringArray[18], CrosshairAddonsModule.openEditor(stringArray[0]));
            this.elytraColor = new ColorSetting(stringArray[10], CrosshairAddonsModule.a(27552, 1571041986279349839L));
            this.entityColor = new ColorSetting(stringArray[40], CrosshairAddonsModule.a(16806, 5994779909730645064L));
            this.shieldColor = new ColorSetting(stringArray[40], CrosshairAddonsModule.a(16806, 5994779909730645064L));
            this.shieldBreakColor = new ColorSetting(stringArray[40], CrosshairAddonsModule.a(16806, 5994779909730645064L));
            this.hitmarkerColor = new ColorSetting(stringArray[40], CrosshairAddonsModule.a(16806, 5994779909730645064L));
            instance = this;
            boolean bl = AmbienceModule.b;
            this.addSettings(this.elytra, this.entityIndicator, this.shieldIndicator, this.shieldBreak, this.hitmarker, this.hitmarkerMs, this.blend, this.editIcons, this.elytraIcon, this.entityIcon, this.shieldIcon, this.shieldBreakIcon, this.hitmarkerIcon, this.elytraColor, this.entityColor, this.shieldColor, this.shieldBreakColor, this.hitmarkerColor, this.thirdPerson, this.hitmarkerUntilEnd, this.breakUntilEnd, this.breakMs, this.hitmarkerOnMelee, this.shieldFactorDelay, this.entityShowAll, this.entityList, this.indicator, this.moveAttackIndicator, this.attackIndicatorGap);
            IconStore.register(stringArray[28], 0, 0, 10, new int[][][]{ENTITY});
            IconStore.register(stringArray[32], 0, 11, 10, new int[][][]{ELYTRA});
            IconStore.register(stringArray[0], 0, 0, 10, new int[][][]{HITMARKER});
            IconStore.register(stringArray[11], 0, 8, 10, new int[][][]{SHIELD});
            IconStore.register(stringArray[24], 0, 8, 17, SHIELD_BREAK);
            this.hitmarker.children(this.hitmarkerIcon, this.hitmarkerColor, this.hitmarkerUntilEnd, this.hitmarkerMs, this.hitmarkerOnMelee);
            this.elytra.children(this.elytraIcon, this.elytraColor);
            this.entityIndicator.children(this.entityIcon, this.entityColor, this.entityShowAll, this.entityList);
            this.shieldIndicator.children(this.shieldIcon, this.shieldColor, this.shieldFactorDelay);
            this.shieldBreak.children(this.shieldBreakIcon, this.shieldBreakColor, this.breakUntilEnd, this.breakMs);
            this.moveAttackIndicator.children(this.attackIndicatorGap);
            this.moveAttackIndicator.visibleWhen(CrosshairAddonsModule::crosshairIndicatorOn);
            for (ColorSetting colorSetting : new ColorSetting[]{this.elytraColor, this.entityColor, this.shieldColor, this.shieldBreakColor, this.hitmarkerColor}) {
                colorSetting.visibleWhen(this::lambda$new$1);
                if (!bl) {
                    if (!bl) continue;
                    break;
                }
                break block4;
            }
            this.hitmarkerMs.visibleWhen(this::lambda$new$2);
            this.breakMs.visibleWhen(this::lambda$new$3);
            stringArray = b;
            this.elytra.tab(stringArray[7]);
            this.entityIndicator.tab(stringArray[8]);
            this.shieldIndicator.tab(stringArray[8]);
            this.shieldBreak.tab(stringArray[8]);
            this.hitmarker.tab(stringArray[8]);
            this.blend.tab(stringArray[8]);
            this.thirdPerson.tab(stringArray[8]);
            this.moveAttackIndicator.tab(stringArray[8]);
            this.indicator.tab(stringArray[8]);
            this.editIcons.tab(stringArray[8]);
        }
    }

    @Override
    public void onEnable() {
        this.applyIndicator();
    }

    @Override
    public void onSettingsChanged() {
        this.applyIndicator();
    }

    
    private void applyIndicator() {
        block21: {
            SimpleOption simpleOption;
            AttackIndicator attackIndicator;
            block20: {
                SimpleOption simpleOption2;
                MinecraftClient minecraftClient;
                boolean bl;
                block19: {
                    block18: {
                        MinecraftClient minecraftClient2;
                        block17: {
                            bl = AmbienceModule.b;
                            if (this.indicator.getValue() == CrosshairAddonsModule$Indicator.Vanilla) {
                                return;
                            }
                            minecraftClient2 = minecraftClient = MinecraftClient.method_1551();
                            if (bl) break block17;
                            if (minecraftClient2 == null) break block18;
                            minecraftClient2 = minecraftClient;
                        }
                        if (minecraftClient2.field_1690 != null) break block19;
                    }
                    return;
                }
                attackIndicator = switch (this.indicator.getValue().ordinal()) {
                    case 1 -> AttackIndicator.field_18152;
                    case 2 -> AttackIndicator.field_18153;
                    default -> AttackIndicator.field_18151;
                };
                simpleOption = simpleOption2 = minecraftClient.field_1690.method_42565();
                if (bl) break block20;
                if (simpleOption.method_41753() == attackIndicator) break block21;
                simpleOption = simpleOption2;
            }
            simpleOption.method_41748((Object)attackIndicator);
        }
    }

    private static boolean crosshairIndicatorOn() {
        boolean bl;
        block11: {
            block9: {
                Object object;
                block10: {
                    boolean bl2;
                    MinecraftClient minecraftClient;
                    block8: {
                        minecraftClient = MinecraftClient.method_1551();
                        bl2 = AmbienceModule.b;
                        object = minecraftClient;
                        if (bl2) break block8;
                        if (object == null) break block9;
                        object = minecraftClient;
                    }
                    if (bl2) break block10;
                    if (object.field_1690 == null) break block9;
                    object = minecraftClient.field_1690.method_42565().method_41753();
                }
                if (object != AttackIndicator.field_18152) break block9;
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
    public static void onProjectileHit() {
        CrosshairAddonsModule crosshairAddonsModule;
        block7: {
            CrosshairAddonsModule crosshairAddonsModule2;
            block8: {
                boolean bl;
                block6: {
                    crosshairAddonsModule2 = instance;
                    bl = AmbienceModule.b;
                    crosshairAddonsModule = crosshairAddonsModule2;
                    if (bl) break block6;
                    if (crosshairAddonsModule == null) return;
                    crosshairAddonsModule = crosshairAddonsModule2;
                }
                if (bl) break block7;
                if (crosshairAddonsModule.isEnabled()) break block8;
                return;
            }
            crosshairAddonsModule = crosshairAddonsModule2;
        }
        crosshairAddonsModule.showHitmarker();
    }

    private void showHitmarker() {
        this.lastProjectileHit = System.currentTimeMillis();
        this.hitPlaying = true;
        IconStore.get(b[0]).reset();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void onMeleeHit() {
        CrosshairAddonsModule crosshairAddonsModule;
        block7: {
            CrosshairAddonsModule crosshairAddonsModule2;
            block8: {
                boolean bl;
                block6: {
                    crosshairAddonsModule = instance;
                    bl = AmbienceModule.b;
                    crosshairAddonsModule2 = crosshairAddonsModule;
                    if (bl) break block6;
                    if (crosshairAddonsModule2 == null) return;
                    crosshairAddonsModule2 = crosshairAddonsModule;
                }
                if (bl) break block7;
                if (crosshairAddonsModule2.isEnabled()) break block8;
                return;
            }
            crosshairAddonsModule.lastMeleeSwing = System.currentTimeMillis();
            crosshairAddonsModule2 = crosshairAddonsModule;
        }
        crosshairAddonsModule2.pendingAt = crosshairAddonsModule.lastMeleeSwing;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void onDamageDealt() {
        CrosshairAddonsModule crosshairAddonsModule;
        block17: {
            long l;
            CrosshairAddonsModule crosshairAddonsModule2;
            block15: {
                boolean bl;
                block16: {
                    long l2;
                    block14: {
                        CrosshairAddonsModule crosshairAddonsModule3;
                        block13: {
                            crosshairAddonsModule2 = instance;
                            bl = AmbienceModule.b;
                            crosshairAddonsModule3 = crosshairAddonsModule2;
                            if (bl) break block13;
                            if (crosshairAddonsModule3 == null) return;
                            crosshairAddonsModule3 = crosshairAddonsModule2;
                        }
                        if (!crosshairAddonsModule3.isEnabled()) {
                            return;
                        }
                        long l3 = System.currentTimeMillis();
                        l2 = crosshairAddonsModule2.pendingAt;
                        if (bl) break block14;
                        if (l2 == 0L) return;
                        l2 = l3 - crosshairAddonsModule2.pendingAt;
                    }
                    long l4 = l2 - CrosshairAddonsModule.b(10418, 1207890149371724518L);
                    l = l4 == 0L ? 0 : (l4 < 0L ? -1 : 1);
                    if (bl) break block15;
                    if (l <= 0) break block16;
                    return;
                }
                crosshairAddonsModule = crosshairAddonsModule2;
                if (bl) break block17;
                crosshairAddonsModule.pendingAt = 0L;
                l = (long)crosshairAddonsModule2.hitmarkerOnMelee.getValue();
            }
            if (l == false) return;
            crosshairAddonsModule = crosshairAddonsModule2;
        }
        crosshairAddonsModule.showHitmarker();
    }

    
    @Override
    public void onHudRender(DrawContext var1_1) {
        block110: {
            block112: {
                block111: {
                    block108: {
                        block104: {
                            block105: {
                                block109: {
                                    block106: {
                                        block107: {
                                            block103: {
                                                block99: {
                                                    block101: {
                                                        block102: {
                                                            block100: {
                                                                block98: {
                                                                    block97: {
                                                                        block96: {
                                                                            block95: {
                                                                                block94: {
                                                                                    block92: {
                                                                                        block93: {
                                                                                            block91: {
                                                                                                block90: {
                                                                                                    block89: {
                                                                                                        block87: {
                                                                                                            block88: {
                                                                                                                block86: {
                                                                                                                    var3_2 = MinecraftClient.method_1551();
                                                                                                                    var2_3 = AmbienceModule.b;
                                                                                                                    v0 = var3_2;
                                                                                                                    if (var2_3) break block86;
                                                                                                                    if (v0.field_1724 != null) {
                                                                                                                    }
                                                                                                                    v0 = var3_2;
                                                                                                                }
                                                                                                                if (var2_3) break block87;
                                                                                                                if (!v0.field_1690.field_1842) break block88;
lbl24:

                                                                                                                return;
                                                                                                            }
                                                                                                            v0 = var3_2;
                                                                                                        }
                                                                                                        v5 = v0.field_1690;
                                                                                                        if (var2_3) break block89;
                                                                                                        if (v5.method_31044() == null) break block90;
                                                                                                        v5 = var3_2.field_1690;
                                                                                                    }
                                                                                                    v8 = (int)v5.method_31044().method_31034();
                                                                                                    if (var2_3) break block91;
                                                                                                    if (v8 != 0) break block90;
                                                                                                    v8 = (int)this.thirdPerson.getValue();
                                                                                                    if (var2_3) break block91;
                                                                                                    if (v8 != 0) break block90;
                                                                                                    return;
                                                                                                }
                                                                                                v8 = var1_1.method_51421();
                                                                                            }
                                                                                            var4_4 = v8;
                                                                                            var5_5 = var1_1.method_51443();
                                                                                            var6_6 = System.currentTimeMillis();
                                                                                            v12 = this.elytra;
                                                                                            if (var2_3) break block92;
                                                                                            if (!v12.getValue()) break block93;
                                                                                            v14 = var3_2.field_1724.method_6128();
                                                                                            if (var2_3) break block94;
                                                                                            if (!v14) break block93;
                                                                                            this.drawIcon(var1_1, IconStore.get(CrosshairAddonsModule.b[32]), var4_4, var5_5, this.elytraColor.getARGB());
                                                                                        }
                                                                                        v18 = this;
                                                                                        if (var2_3) break block95;
                                                                                        v12 = v18.entityIndicator;
                                                                                    }
                                                                                    v14 = v12.getValue();
                                                                                }
                                                                                if (!v14) break block97;
                                                                                v18 = this;
                                                                            }
                                                                            if (var2_3) break block96;
                                                                            if (!v18.entityMatches(var3_2)) break block97;
                                                                            v18 = this;
                                                                        }
                                                                        var10_7 = CrosshairAddonsModule.b;
                                                                        v18.drawIcon(var1_1, IconStore.get(var10_7[28]), var4_4, var5_5, this.entityColor.getARGB());
                                                                    }
                                                                    var10_7 = CrosshairAddonsModule.b;
                                                                    var8_8 = IconStore.get(var10_7[0]);
                                                                    v22 = this;
                                                                    if (var2_3) break block98;
                                                                    if (!v22.hitmarker.getValue()) break block99;
                                                                    v22 = this;
                                                                }
                                                                if (var2_3) break block100;
                                                                if (!v22.hitPlaying) break block99;
                                                                v22 = this;
                                                            }
                                                            v27 = v22.hitmarkerUntilEnd.getValue();
                                                            if (var2_3) break block101;
                                                            if (v27 == 0) break block102;
                                                            v29 = var8_8;
                                                            if (var2_3) break block103;
                                                            if (v29.hasPlayedOnce()) break block99;
                                                        }
                                                        cfr_temp_0 = var6_6 - this.lastProjectileHit - (long)this.hitmarkerMs.getValue();
                                                        v27 = cfr_temp_0 == 0L ? 0 : (cfr_temp_0 < 0L ? -1 : 1);
                                                    }
                                                    if (v27 >= 0) break block99;
lbl174:

                                                    this.drawIcon(var1_1, var8_8, var4_4, var5_5, this.hitmarkerColor.getARGB());
                                                }
                                                var10_7 = CrosshairAddonsModule.b;
                                                v29 = IconStore.get(var10_7[24]);
                                            }
                                            var9_9 = v29;
                                            v34 = this.shieldBreak;
                                            if (var2_3) break block104;
                                            if (v34.getValue()) {
                                            }
                                            v36 = this;
                                            if (var2_3) break block105;
                                            if (v36.breakPlaying) {
                                            }
                                            v39 = this.breakUntilEnd.getValue();
                                            if (var2_3) break block106;
                                            if (v39 == 0) break block107;
                                            v39 = var9_9.hasPlayedOnce();
                                            if (var2_3) break block108;
                                            if (v39 != 0) // goto
                                        }
                                        cfr_temp_1 = var6_6 - this.lastShieldBreak - (long)this.breakMs.getValue();
                                        v39 = cfr_temp_1 == 0L ? 0 : (cfr_temp_1 < 0L ? -1 : 1);
                                    }
                                    if (var2_3) break block108;
                                    if (v39 >= 0) break block109;
lbl237:

                                    this.drawIcon(var1_1, var9_9, var4_4, var5_5, this.shieldBreakColor.getARGB());
                                    if (!var2_3) break block110;
                                }
                                v36 = this;
                            }
                            if (var2_3) break block111;
                            v34 = v36.shieldIndicator;
                        }
                        v39 = v34.getValue();
                    }
                    if (v39 == 0) break block110;
                    v36 = this;
                }
                if (var2_3) break block112;
                if (!v36.targetBlocking(var3_2)) break block110;
                v36 = this;
            }
            v36.drawIcon(var1_1, IconStore.get(CrosshairAddonsModule.b[11]), var4_4, var5_5, this.shieldColor.getARGB());
        }
    }

    @Override
    public void onTick() {
        block35: {
            boolean bl;
            block34: {
                String string;
                block27: {
                    CrosshairAddonsModule crosshairAddonsModule;
                    boolean bl2;
                    block33: {
                        boolean bl3;
                        block32: {
                            block30: {
                                block31: {
                                    block26: {
                                        boolean bl4;
                                        block25: {
                                            bl2 = AmbienceModule.b;
                                            bl4 = ShieldTweaksModule.isOn();
                                            if (bl2) break block25;
                                            if (bl4) break block26;
                                            bl4 = false;
                                        }
                                        ShieldStates.update(bl4);
                                    }
                                    this.applyIndicator();
                                    Iterator<String> iterator = IconStore.keys().iterator();
                                    while (iterator.hasNext()) {
                                        block29: {
                                            IconAnimation iconAnimation;
                                            block28: {
                                                String string2;
                                                string = string2 = iterator.next();
                                                if (bl2) break block27;
                                                IconAnimation iconAnimation2 = IconStore.get(string);
                                                iconAnimation = iconAnimation2;
                                                if (bl2) break block28;
                                                if (iconAnimation == null) break block29;
                                                iconAnimation = iconAnimation2;
                                            }
                                            iconAnimation.tick();
                                        }
                                        if (!bl2) continue;
                                    }
                                    crosshairAddonsModule = this;
                                    if (bl2) break block30;
                                    if (!crosshairAddonsModule.breakPlaying) break block31;
                                    crosshairAddonsModule = this;
                                    if (bl2) break block30;
                                    if (!crosshairAddonsModule.breakUntilEnd.getValue()) break block31;
                                    bl3 = IconStore.get(b[29]).hasPlayedOnce();
                                    if (bl2) break block32;
                                    if (!bl3) break block31;
                                    this.breakPlaying = false;
                                }
                                crosshairAddonsModule = this;
                            }
                            if (bl2) break block33;
                            bl3 = crosshairAddonsModule.hitPlaying;
                        }
                        if (!bl3) break block35;
                        crosshairAddonsModule = this;
                    }
                    bl = crosshairAddonsModule.hitmarkerUntilEnd.getValue();
                    if (bl2) break block34;
                    if (!bl) break block35;
                    string = b[6];
                }
                bl = IconStore.get(string).hasPlayedOnce();
            }
            if (bl) {
                this.hitPlaying = false;
            }
        }
    }

    
    private boolean targetBlocking(MinecraftClient minecraftClient) {
        PlayerEntity playerEntity;
        boolean bl;
        boolean bl2;
        block21: {
            boolean bl3;
            PlayerEntity playerEntity2;
            block20: {
                block19: {
                    Entity entity;
                    Entity entity2;
                    Entity entity3;
                    block17: {
                        block18: {
                            entity3 = CrosshairAddonsModule.target(minecraftClient);
                            bl2 = AmbienceModule.b;
                            entity2 = entity3;
                            if (bl2) break block17;
                            if (!(entity2 instanceof PlayerEntity)) break block18;
                            playerEntity2 = (PlayerEntity)entity3;
                            if (!bl2) break block19;
                        }
                        entity2 = CrosshairAddonsModule.target(minecraftClient);
                    }
                    Entity entity4 = entity = entity2;
                    if (!bl2) {
                        if (!(entity4 instanceof LivingEntity)) return false;
                        entity4 = entity;
                    }
                    entity3 = (LivingEntity)entity4;
                    boolean bl4 = entity3.method_6039();
                    if (bl2) return bl4;
                    if (!bl4) return false;
                    return true;
                }
                bl3 = this.shieldFactorDelay.getValue();
                if (bl2) break block20;
                if (bl3) {
                    bl = ShieldStates.isBlocking(playerEntity2);
                    return bl;
                }
                playerEntity = playerEntity2;
                if (bl2) break block21;
                bl3 = ShieldStates.holdsUsableShield(playerEntity);
            }
            if (!bl3) return false;
            playerEntity = playerEntity2;
        }
        bl = playerEntity.method_6115();
        if (bl2) return bl;
        if (!bl) return false;
        return true;
    }

    private static Entity target(MinecraftClient minecraftClient) {
        Entity entity;
        block6: {
            block5: {
                HitResult hitResult;
                block4: {
                    HitResult hitResult2 = minecraftClient.field_1765;
                    boolean bl = AmbienceModule.b;
                    hitResult = hitResult2;
                    if (bl) break block4;
                    if (!(hitResult instanceof EntityHitResult)) break block5;
                    hitResult = hitResult2;
                }
                EntityHitResult entityHitResult = (EntityHitResult)hitResult;
                entity = entityHitResult.method_17782();
                break block6;
            }
            entity = null;
        }
        return entity;
    }

    private boolean entityMatches(MinecraftClient minecraftClient) {
        boolean bl;
        block37: {
            PlayerEntity playerEntity;
            PlayerEntity playerEntity2;
            boolean bl2;
            block34: {
                block35: {
                    Entity entity;
                    block33: {
                        boolean bl3;
                        block32: {
                            block30: {
                                block31: {
                                    Entity entity2;
                                    block28: {
                                        block29: {
                                            entity = CrosshairAddonsModule.target(minecraftClient);
                                            bl2 = AmbienceModule.b;
                                            entity2 = entity;
                                            if (bl2) break block28;
                                            if (entity2 != null) break block29;
                                            return false;
                                        }
                                        entity2 = entity;
                                    }
                                    bl3 = entity2 instanceof PlayerEntity;
                                    if (bl2) break block30;
                                    if (!bl3) break block31;
                                    playerEntity2 = (PlayerEntity)entity;
                                    bl3 = playerEntity2.method_7325();
                                    if (bl2) break block30;
                                    if (!bl3) break block31;
                                    return false;
                                }
                                bl3 = this.entityShowAll.getValue();
                            }
                            if (bl2) break block32;
                            if (!bl3) break block33;
                            bl3 = true;
                        }
                        return bl3;
                    }
                    playerEntity2 = Registries.field_41177.method_10221((Object)entity.method_5864());
                    playerEntity = playerEntity2;
                    if (bl2) break block34;
                    if (playerEntity != null) break block35;
                    return false;
                }
                playerEntity = playerEntity2;
            }
            String string = playerEntity.toString();
            String string2 = playerEntity2.method_12832();
            for (String string3 : this.entityList.getValue().split(",")) {
                boolean bl4;
                block38: {
                    block39: {
                        String string4;
                        String string5;
                        block36: {
                            string5 = string3.trim().toLowerCase(Locale.ROOT);
                            string4 = string5;
                            if (bl2) break block36;
                            bl = string4.isEmpty();
                            if (bl2) break block37;
                            if (bl) {
                                continue;
                            }
                            string4 = string5;
                        }
                        bl4 = string4.equals(string);
                        if (bl2) break block38;
                        if (bl4) break block39;
                        bl4 = string5.equals(string2);
                        if (bl2) break block38;
                        if (!bl4) continue;
                    }
                    bl4 = true;
                }
                return bl4;
            }
            bl = false;
        }
        return bl;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void onShieldBreak() {
        long l;
        block10: {
            CrosshairAddonsModule crosshairAddonsModule;
            boolean bl;
            CrosshairAddonsModule crosshairAddonsModule2;
            block9: {
                crosshairAddonsModule2 = instance;
                bl = AmbienceModule.b;
                crosshairAddonsModule = crosshairAddonsModule2;
                if (bl) break block9;
                if (crosshairAddonsModule == null) return;
                crosshairAddonsModule = crosshairAddonsModule2;
            }
            if (!crosshairAddonsModule.isEnabled()) {
                return;
            }
            l = System.currentTimeMillis();
            if (bl) return;
            if (l - crosshairAddonsModule2.lastMeleeSwing <= CrosshairAddonsModule.b(31861, 3664506940393068064L)) break block10;
            return;
        }
        IconStore.get(b[24]).reset();
        crosshairAddonsModule2.breakPlaying = true;
        crosshairAddonsModule2.lastShieldBreak = l;
    }

    public static boolean movesAttackIndicator() {
        boolean bl;
        block11: {
            block9: {
                CrosshairAddonsModule crosshairAddonsModule;
                boolean bl2;
                block10: {
                    CrosshairAddonsModule crosshairAddonsModule2;
                    block8: {
                        crosshairAddonsModule2 = instance;
                        bl2 = AmbienceModule.b;
                        crosshairAddonsModule = crosshairAddonsModule2;
                        if (bl2) break block8;
                        if (crosshairAddonsModule == null) break block9;
                        crosshairAddonsModule = crosshairAddonsModule2;
                    }
                    if (bl2) break block10;
                    if (!crosshairAddonsModule.isEnabled()) break block9;
                    crosshairAddonsModule = crosshairAddonsModule2;
                }
                bl = crosshairAddonsModule.moveAttackIndicator.getValue();
                if (bl2) break block11;
                if (!bl) break block9;
                bl = true;
                break block11;
            }
            bl = false;
        }
        return bl;
    }

    public static int attackIndicatorY(int n, int n2) {
        int n3;
        block35: {
            MinecraftClient minecraftClient;
            MinecraftClient minecraftClient2;
            boolean bl;
            CrosshairAddonsModule crosshairAddonsModule;
            block33: {
                block34: {
                    block32: {
                        int n4;
                        block31: {
                            crosshairAddonsModule = instance;
                            bl = AmbienceModule.b;
                            n4 = CrosshairAddonsModule.movesAttackIndicator();
                            if (bl) break block31;
                            if (n4 != 0) break block32;
                            n4 = n;
                        }
                        return n4;
                    }
                    minecraftClient2 = MinecraftClient.method_1551();
                    minecraftClient = minecraftClient2;
                    if (bl) break block33;
                    if (minecraftClient.field_1724 != null) break block34;
                    return n;
                }
                minecraftClient = minecraftClient2;
            }
            int n5 = minecraftClient.method_22683().method_4486();
            int n6 = minecraftClient2.method_22683().method_4502();
            int n7 = (int)crosshairAddonsModule.attackIndicatorGap.getValue();
            int n8 = n;
            int n9 = n + n2;
            for (String string : IconStore.keys()) {
                block43: {
                    int n10;
                    block42: {
                        int n11;
                        block40: {
                            int n12;
                            block39: {
                                int n13;
                                block37: {
                                    int n14;
                                    block36: {
                                        n3 = crosshairAddonsModule.iconVisible(string, minecraftClient2) ? 1 : 0;
                                        if (bl) break block35;
                                        if (n3 == 0) {
                                            continue;
                                        }
                                        IconAnimation iconAnimation = IconStore.get(string);
                                        if (iconAnimation == null) {
                                            continue;
                                        }
                                        int n15 = Math.round(15.0f * iconAnimation.getSize());
                                        n14 = (n6 - n15) / 2 + iconAnimation.getOffsetY();
                                        n12 = n14 + n15;
                                        int n16 = (n5 - n15) / 2 + iconAnimation.getOffsetX();
                                        int n17 = n16 + n15;
                                        n11 = n17;
                                        if (bl) break block36;
                                        if (n11 <= (n5 - 16) / 2) continue;
                                        n11 = n16;
                                    }
                                    n13 = n5;
                                    if (bl) break block37;
                                    if (n11 >= (n13 + 16) / 2) {
                                        continue;
                                    }
                                    n11 = n14;
                                    if (bl) break block39;
                                    n13 = n9;
                                }
                                if (n11 >= n13) continue;
                                n11 = n12;
                            }
                            if (bl) break block40;
                            if (n11 <= n8) {
                                continue;
                            }
                            n11 = n12 + n7;
                        }
                        int n18 = n11;
                        n10 = n18;
                        if (bl) break block42;
                        if (n10 <= n) break block43;
                        n10 = n18;
                    }
                    n = n10;
                }
                if (!bl) continue;
            }
            n3 = n;
        }
        return n3;
    }

    
    private boolean iconVisible(String var1_1, MinecraftClient var2_2) {
        block51: {
            var4_3 = var1_1;
            var5_4 = -1;
            var3_5 = AmbienceModule.b;
            v0 = var4_3.hashCode();
            if (var3_5) break block51;
            switch (v0) {
                case 2078871505: {
                    v0 = var4_3.equals(CrosshairAddonsModule.b[32]);
                    if (!var3_5) {
                        if (v0 == 0) break;
                        var5_4 = 0;
                        if (!var3_5) break;
                    }
                    break block51;
                }
                case 2080559107: {
                    v0 = var4_3.equals(CrosshairAddonsModule.b[28]);
                    if (!var3_5) {
                        if (v0 == 0) break;
                        var5_4 = 1;
                        if (!var3_5) break;
                    }
                    break block51;
                }
                case 108662125: {
                    v0 = var4_3.equals(CrosshairAddonsModule.b[0]);
                    if (!var3_5) {
                        if (v0 == 0) break;
                        var5_4 = 2;
                        if (!var3_5) break;
                    }
                    break block51;
                }
                case -1819473015: {
                    v0 = var4_3.equals(CrosshairAddonsModule.b[11]);
                    if (!var3_5) {
                        if (v0 == 0) break;
                        var5_4 = 3;
                        if (!var3_5) break;
                    }
                    break block51;
                }
                case 1707339176: {
                    v0 = var4_3.equals(CrosshairAddonsModule.b[24]);
                    if (var3_5) break block51;
                    if (v0 == 0) break;
                    var5_4 = 4;
                }
            }
            v0 = var5_4;
        }
        if (var3_5) // goto
        switch (v0) {
            case 0: {
                v0 = this.elytra.getValue();
lbl46:

                if (var3_5 != false) return (boolean)v0;
                if (v0 == 0) return (boolean)0;
                v0 = var2_2.field_1724.method_6128();
                if (var3_5 != false) return (boolean)v0;
                if (v0 == 0) return (boolean)0;
                return (boolean)1;
            }
            case 1: {
                v0 = this.entityIndicator.getValue();
                if (var3_5 != false) return (boolean)v0;
                if (v0 == 0) return (boolean)0;
                v0 = (int)this.entityMatches(var2_2);
                if (var3_5 != false) return (boolean)v0;
                if (v0 == 0) return (boolean)0;
                return (boolean)1;
            }
            case 2: {
                v0 = this.hitmarker.getValue();
                if (var3_5 != false) return (boolean)v0;
                if (v0 == 0) return (boolean)0;
                v0 = this.hitPlaying;
                if (var3_5 != false) return (boolean)v0;
                if (v0 == 0) return (boolean)0;
                return (boolean)1;
            }
            case 3: {
                v0 = this.shieldIndicator.getValue();
                if (var3_5 != false) return (boolean)v0;
                if (v0 == 0) return (boolean)0;
                v0 = (int)this.targetBlocking(var2_2);
                if (var3_5 != false) return (boolean)v0;
                if (v0 == 0) return (boolean)0;
                return (boolean)1;
            }
            case 4: {
                v0 = this.shieldBreak.getValue();
                if (var3_5 != false) return (boolean)v0;
                if (v0 == 0) return (boolean)0;
                v0 = this.breakPlaying;
                if (var3_5 != false) return (boolean)v0;
                if (v0 == 0) return (boolean)0;
                return (boolean)1;
            }
        }
        return (boolean)0;
    }

    private void drawIcon(DrawContext drawContext, IconAnimation iconAnimation, int n, int n2, int n3) {
        if (iconAnimation == null) {
            return;
        }
        IconTex.drawCentred(drawContext, iconAnimation.currentFrame(), n, n2, iconAnimation.getSize(), iconAnimation.getOffsetX(), iconAnimation.getOffsetY(), n3, this.blend.getValue());
    }

    private Boolean lambda$new$3() {
        boolean bl;
        block2: {
            block3: {
                boolean bl2 = AmbienceModule.b;
                bl = this.breakUntilEnd.getValue();
                if (bl2) break block2;
                if (bl) break block3;
                bl = true;
                break block2;
            }
            bl = false;
        }
        return bl;
    }

    private Boolean lambda$new$2() {
        boolean bl;
        block2: {
            block3: {
                boolean bl2 = AmbienceModule.b;
                bl = this.hitmarkerUntilEnd.getValue();
                if (bl2) break block2;
                if (bl) break block3;
                bl = true;
                break block2;
            }
            bl = false;
        }
        return bl;
    }

    private Boolean lambda$new$1() {
        boolean bl;
        block2: {
            block3: {
                boolean bl2 = AmbienceModule.b;
                bl = this.blend.getValue();
                if (bl2) break block2;
                if (bl) break block3;
                bl = true;
                break block2;
            }
            bl = false;
        }
        return bl;
    }

    private static void lambda$openEditor$0(String string) {
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        if (Im.available()) {
            minecraftClient.method_1507((Screen)new IconEditor(string));
        }
    }

    
    static {
        block27: {
            block26: {
                block25: {
                    block24: {
                        block23: {
                            block22: {
                                var21 = new String[41];
                                var19_1 = 0;
                                var18_2 = "j`Q*@Q=G{\u0014qaJ0\u0001J8\u0002}M.SGvRlW4NM\u0014reD>\u0001L8Al\tgUK3L)M.EF\u0010a{J4RK7K{\u0005\u0006EG9Lz\u0010c}Q&BHvKgA.BB\"M{\u0012ceV(\u0001L8\u0002d@+DFvJ`Q4\tj`Q*@Q=G{\nkgA.BB\"M{V\nkgA.BB\"M{V\u0011ggS.SL8OlK3\u0001A:GgA\u0005afI(S\u0006qaL\"MG\u0010ge\\3SBvKgA.BB\"M{\u0006ge\\3SB\u0005kjJ)R\u0004kjJ)\ncg\\gDM\"K}\\\bggQ.UJ3Q\u0004f{D0\u0010ggQ.UZvKgA.BB\"M{\u000egmL3\u0001B:N)L$NM%\u0010qaL\"MGvKgA.BB\"M{\u0004f{D0\u0006qaL\"MG\fqaL\"MGv@{@&J\u0004gmL3\u0015ofS\"\u0001B\"VhF,\u0001J8F`F&UL$\u0016cmA(OPv@p\u0005\u0010@O=Qp\u0005olj\u0002\u000b\u0006ggQ.UZ\fqaL\"MGv@{@&J\u0019uhL3\u0001E9P)Q/D\u00034NfF,\u0001W9\u0002hW*\u0014reD>\u0001L8Al\tgUK3L)M.EF\u0006ge\\3SB\u0011qaL\"MGv@{@&J\u0003\"Kd@\u0004kjJ)\u000ej`Q*@Q=G{\u00053HN3\u001akjJ)R\u00037PfP)E\u0003\"Jl\u0005$SL%QaD.S\u0006ReD>DQ\u0014c}Q&BHvKgA.BB\"M{\u0005 @S";
                                var20_3 = "j`Q*@Q=G{\u0014qaJ0\u0001J8\u0002}M.SGvRlW4NM\u0014reD>\u0001L8Al\tgUK3L)M.EF\u0010a{J4RK7K{\u0005\u0006EG9Lz\u0010c}Q&BHvKgA.BB\"M{\u0012ceV(\u0001L8\u0002d@+DFvJ`Q4\tj`Q*@Q=G{\nkgA.BB\"M{V\nkgA.BB\"M{V\u0011ggS.SL8OlK3\u0001A:GgA\u0005afI(S\u0006qaL\"MG\u0010ge\\3SBvKgA.BB\"M{\u0006ge\\3SB\u0005kjJ)R\u0004kjJ)\ncg\\gDM\"K}\\\bggQ.UJ3Q\u0004f{D0\u0010ggQ.UZvKgA.BB\"M{\u000egmL3\u0001B:N)L$NM%\u0010qaL\"MGvKgA.BB\"M{\u0004f{D0\u0006qaL\"MG\fqaL\"MGv@{@&J\u0004gmL3\u0015ofS\"\u0001B\"VhF,\u0001J8F`F&UL$\u0016cmA(OPv@p\u0005\u0010@O=Qp\u0005olj\u0002\u000b\u0006ggQ.UZ\fqaL\"MGv@{@&J\u0019uhL3\u0001E9P)Q/D\u00034NfF,\u0001W9\u0002hW*\u0014reD>\u0001L8Al\tgUK3L)M.EF\u0006ge\\3SB\u0011qaL\"MGv@{@&J\u0003\"Kd@\u0004kjJ)\u000ej`Q*@Q=G{\u00053HN3\u001akjJ)R\u00037PfP)E\u0003\"Jl\u0005$SL%QaD.S\u0006ReD>DQ\u0014c}Q&BHvKgA.BB\"M{\u0005 @S".length();
                                var17_4 = 9;
                                var16_5 = -1;
lbl7:

                                while (true) {
                                    v0 = ++var16_5;
                                    v1 = var18_2.substring(v0, v0 + var17_4);
                                    v2 = -1;
                                    break block22;
                                    break;
                                }
lbl12:

                                while (true) {
                                    var21[var19_1++] = v3.intern();
                                    if ((var16_5 += var17_4) < var20_3) {
                                        var17_4 = var18_2.charAt(var16_5);
                                        continue;
                                    }
                                    var18_2 = "ggQ.UZ\u0005afI(S";
                                    var20_3 = "ggQ.UZ\u0005afI(S".length();
                                    var17_4 = 6;
                                    var16_5 = -1;
lbl21:

                                    while (true) {
                                        v4 = ++var16_5;
                                        v1 = var18_2.substring(v4, v4 + var17_4);
                                        v2 = 0;
                                        break block22;
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
                                    break block23;
                                    break;
                                }
                            }
                            v5 = v1.toCharArray();
                            v6 = v5;
                            v7 = v5.length;
                            var22_6 = 0;
                            do {
                                v6 = v6;
                                v8 = var22_6;
                                v9 = v6[v8];
                                switch (var22_6 % 7) {
                                    case 0: {
                                        v10 = 34;
                                        break;
                                    }
                                    case 1: {
                                        v10 = 9;
                                        break;
                                    }
                                    case 2: {
                                        v10 = 37;
                                        break;
                                    }
                                    case 3: {
                                        v10 = 71;
                                        break;
                                    }
                                    case 4: {
                                        v10 = 33;
                                        break;
                                    }
                                    case 5: {
                                        v10 = 35;
                                        break;
                                    }
                                    default: {
                                        v10 = 86;
                                    }
                                }
                                v6[v8] = (char)(v9 ^ v10);
                                ++var22_6;
lbl65:

                                v7 = v7;
                            } while (v7 > var22_6);
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
                        CrosshairAddonsModule.b = var21;
                        CrosshairAddonsModule.CREDIT = CrosshairAddonsModule.b[27];
                        var8_7 = 750181290494948447L;
                        var14_8 = new long[2];
                        var11_9 = 0;
                        var12_10 = "b\u0099\u00bc\u00fe\u0099\u0092\u0005\u00e7\u00ec\u00ae\u00ff\u00bb\u00b4\u00d1/\u00e0";
                        var13_11 = "b\u0099\u00bc\u00fe\u0099\u0092\u0005\u00e7\u00ec\u00ae\u00ff\u00bb\u00b4\u00d1/\u00e0".length();
                        var10_12 = 0;
                        while (true) {
                            break block24;
                            break;
                        }
lbl86:

                        while (true) {
                            var14_8[v11] = (((long)var15_13[0] & 255L) << 56 | ((long)var15_13[1] & 255L) << 48 | ((long)var15_13[2] & 255L) << 40 | ((long)var15_13[3] & 255L) << 32 | ((long)var15_13[4] & 255L) << 24 | ((long)var15_13[5] & 255L) << 16 | ((long)var15_13[6] & 255L) << 8 | (long)var15_13[7] & 255L) ^ var8_7;
                            if (var10_12 < var13_11) continue;
                            break block25;
                            break;
                        }
                    }
                    var15_13 = var12_10.substring(var10_12, var10_12 += 8).getBytes("ISO-8859-1");
                    v11 = var11_9++;
                    while (true)
                }
                CrosshairAddonsModule.d = var14_8;
                CrosshairAddonsModule.e = new Integer[2];
                var0_14 = 3687399922361566612L;
                var6_15 = new long[3];
                var3_16 = 0;
                var4_17 = "\u0001\u00f6\u00b7\u0014\u00ce?\u0010\u0094#\u00ef\u000f\u0001\u00a5\u00d4\u00c6,1\u00e6\u000fG\u00b6\u00f8\u00c4z";
                var5_18 = "\u0001\u00f6\u00b7\u0014\u00ce?\u0010\u0094#\u00ef\u000f\u0001\u00a5\u00d4\u00c6,1\u00e6\u000fG\u00b6\u00f8\u00c4z".length();
                var2_19 = 0;
                while (true) {
                    break block26;
                    break;
                }
lbl108:

                while (true) {
                    var6_15[v12] = (((long)var7_20[0] & 255L) << 56 | ((long)var7_20[1] & 255L) << 48 | ((long)var7_20[2] & 255L) << 40 | ((long)var7_20[3] & 255L) << 32 | ((long)var7_20[4] & 255L) << 24 | ((long)var7_20[5] & 255L) << 16 | ((long)var7_20[6] & 255L) << 8 | (long)var7_20[7] & 255L) ^ var0_14;
                    if (var2_19 < var5_18) continue;
                    break block27;
                    break;
                }
            }
            var7_20 = var4_17.substring(var2_19, var2_19 += 8).getBytes("ISO-8859-1");
            v12 = var3_16++;
            while (true)
        }
        CrosshairAddonsModule.f = var6_15;
        CrosshairAddonsModule.g = new Long[3];
        CrosshairAddonsModule.CONFIRM_MS = CrosshairAddonsModule.b(10983, 201054458104430768L);
        CrosshairAddonsModule.ENTITY = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        CrosshairAddonsModule.ELYTRA = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0}, {0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0}, {0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0}, {0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0}, {0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0}, {0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0}, {0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0}, {0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        CrosshairAddonsModule.HITMARKER = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        CrosshairAddonsModule.SHIELD_BREAK_0 = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        CrosshairAddonsModule.SHIELD_BREAK_1 = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        CrosshairAddonsModule.SHIELD_BREAK_2 = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0}, {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        CrosshairAddonsModule.SHIELD_BREAK_3 = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}, {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        CrosshairAddonsModule.SHIELD_BREAK_4 = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        CrosshairAddonsModule.SHIELD = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        CrosshairAddonsModule.SHIELD_BREAK = new int[][][]{CrosshairAddonsModule.SHIELD_BREAK_0, CrosshairAddonsModule.SHIELD_BREAK_1, CrosshairAddonsModule.SHIELD_BREAK_2, CrosshairAddonsModule.SHIELD_BREAK_3, CrosshairAddonsModule.SHIELD_BREAK_4};
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x75EF;
        if (e[n2] == null) {
            CrosshairAddonsModule.e[n2] = (int)(d[n2] ^ l);
        }
        return e[n2];
    }

    private static long b(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x6A55) & Short.MAX_VALUE;
        if (g[n2] == null) {
            CrosshairAddonsModule.g[n2] = f[n2] ^ l;
        }
        return g[n2];
    }
}
