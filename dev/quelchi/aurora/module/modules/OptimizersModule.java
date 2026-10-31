package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.setting.ActionSetting;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.NumberSetting;
import dev.quelchi.aurora.optimize.AnchorOptimizer;
import dev.quelchi.aurora.optimize.AnchorPrediction;
import dev.quelchi.aurora.optimize.Blast;
import dev.quelchi.aurora.optimize.ConsumableOptimizer;
import dev.quelchi.aurora.optimize.CrossbowOptimizer;
import dev.quelchi.aurora.optimize.CrystalOptimizer;
import dev.quelchi.aurora.optimize.CrystalPrediction;
import dev.quelchi.aurora.optimize.GhostAnchor;
import dev.quelchi.aurora.optimize.Ledger;
import dev.quelchi.aurora.optimize.MarlowProtocol;
import dev.quelchi.aurora.prediction.Prediction;
import java.util.Locale;
import net.minecraft.client.network.ServerInfo;

public final class OptimizersModule
extends Module {
    private static OptimizersModule instance;
    private final ActionSetting serverState;
    private final BooleanSetting crystals;
    private final BooleanSetting keepVisible;
    private final BooleanSetting anchors;
    private final BooleanSetting anchorExplosion;
    private final BooleanSetting heroAnchor;
    private final BooleanSetting cutebowAnchor;
    private final BooleanSetting cutebowOutline;
    private final BooleanSetting clientCrystals;
    private final NumberSetting crystalOpacity;
    private final BooleanSetting consumables;
    private final BooleanSetting crossbow;
    private final BooleanSetting elytra;
    private final BooleanSetting lowLatency;
    private final BooleanSetting framePacing;
    private final BooleanSetting backgroundFps;
    private final BooleanSetting entityCull;
    private final BooleanSetting blockEntityCull;
    private final BooleanSetting particleCull;
    private final BooleanSetting farDetail;
    private static final String[] NO_ELYTRA;
    private static final String[] b;

    public static OptimizersModule get() {
        return instance;
    }

    public OptimizersModule() {
        block4: {
            String[] stringArray = b;
            super(stringArray[15], stringArray[26], Category.COMBAT, false);
            this.serverState = new ActionSetting(stringArray[5], OptimizersModule::lambda$new$0, OptimizersModule::lambda$new$1);
            this.crystals = new BooleanSetting(stringArray[13], false);
            this.keepVisible = new BooleanSetting(stringArray[9], false);
            this.anchors = new BooleanSetting(stringArray[11], false);
            this.anchorExplosion = new BooleanSetting(stringArray[20], true);
            boolean bl = AmbienceModule.b;
            this.heroAnchor = new BooleanSetting(stringArray[23], false);
            this.cutebowAnchor = new BooleanSetting(stringArray[4], false);
            this.cutebowOutline = new BooleanSetting(stringArray[2], false);
            this.clientCrystals = new BooleanSetting(stringArray[3], false);
            this.crystalOpacity = new NumberSetting(stringArray[1], 0.2, 1.0, 1.0, 0.05);
            this.consumables = new BooleanSetting(stringArray[25], false);
            this.crossbow = new BooleanSetting(stringArray[6], false);
            this.elytra = new BooleanSetting(stringArray[14], false);
            this.lowLatency = new BooleanSetting(stringArray[10], true);
            this.framePacing = new BooleanSetting(stringArray[8], true);
            this.backgroundFps = new BooleanSetting(stringArray[29], true);
            this.entityCull = new BooleanSetting(stringArray[19], true);
            this.blockEntityCull = new BooleanSetting(stringArray[22], true);
            this.particleCull = new BooleanSetting(stringArray[17], true);
            this.farDetail = new BooleanSetting(stringArray[28], true);
            instance = this;
            this.addSettings(this.serverState, this.crystals, this.keepVisible, this.anchors, this.anchorExplosion, this.heroAnchor, this.cutebowAnchor, this.cutebowOutline, this.clientCrystals, this.crystalOpacity, this.consumables, this.crossbow, this.elytra, this.lowLatency, this.framePacing, this.backgroundFps, this.entityCull, this.blockEntityCull, this.particleCull, this.farDetail);
            for (BooleanSetting booleanSetting : new BooleanSetting[]{this.lowLatency, this.framePacing, this.backgroundFps, this.entityCull, this.blockEntityCull, this.particleCull, this.farDetail}) {
                booleanSetting.tab(b[12]);
                if (!bl) {
                    if (!bl) continue;
                    break;
                }
                break block4;
            }
            this.crystals.children(this.keepVisible);
            this.anchors.children(this.anchorExplosion);
            this.cutebowAnchor.children(this.cutebowOutline);
            this.clientCrystals.children(this.crystalOpacity);
        }
    }

    private static boolean superseded() {
        return Prediction.active();
    }

    public boolean crystals() {
        boolean bl;
        block8: {
            block7: {
                OptimizersModule optimizersModule;
                boolean bl2;
                block6: {
                    bl2 = AmbienceModule.b;
                    optimizersModule = this;
                    if (bl2) break block6;
                    if (!optimizersModule.isEnabled()) break block7;
                    optimizersModule = this;
                }
                bl = optimizersModule.crystals.getValue();
                if (bl2) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    public boolean keepVisible() {
        return this.keepVisible.getValue();
    }

    public boolean lowLatency() {
        return this.lowLatency.getValue();
    }

    public boolean framePacing() {
        return this.framePacing.getValue();
    }

    public boolean backgroundFps() {
        return this.backgroundFps.getValue();
    }

    public boolean entityCull() {
        return this.entityCull.getValue();
    }

    public boolean blockEntityCull() {
        return this.blockEntityCull.getValue();
    }

    public boolean particleCull() {
        return this.particleCull.getValue();
    }

    public boolean farDetail() {
        return this.farDetail.getValue();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean anchors() {
        OptimizersModule optimizersModule;
        boolean bl;
        block9: {
            boolean bl2;
            block8: {
                bl = AmbienceModule.b;
                bl2 = OptimizersModule.superseded();
                if (bl) break block8;
                if (bl2) return false;
                optimizersModule = this;
                if (bl) break block9;
                bl2 = optimizersModule.isEnabled();
            }
            if (!bl2) return false;
            optimizersModule = this;
        }
        boolean bl3 = optimizersModule.anchors.getValue();
        if (bl) return bl3;
        if (!bl3) return false;
        return true;
    }

    public boolean anchorClientExplosion() {
        return this.anchorExplosion.getValue();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean heroAnchors() {
        OptimizersModule optimizersModule;
        boolean bl;
        block14: {
            block13: {
                boolean bl2;
                block12: {
                    bl = AmbienceModule.b;
                    bl2 = OptimizersModule.superseded();
                    if (bl) break block12;
                    if (bl2) return false;
                    optimizersModule = this;
                    if (bl) break block13;
                    bl2 = optimizersModule.isEnabled();
                }
                if (!bl2) return false;
                optimizersModule = this;
            }
            if (bl) break block14;
            if (!optimizersModule.heroAnchor.getValue()) return false;
            optimizersModule = this;
        }
        boolean bl3 = optimizersModule.anchors.getValue();
        if (bl) return bl3;
        if (bl3) return false;
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean cutebowAnchors() {
        OptimizersModule optimizersModule;
        boolean bl;
        block19: {
            block18: {
                block17: {
                    boolean bl2;
                    block16: {
                        bl = AmbienceModule.b;
                        bl2 = OptimizersModule.superseded();
                        if (bl) break block16;
                        if (bl2) return false;
                        optimizersModule = this;
                        if (bl) break block17;
                        bl2 = optimizersModule.isEnabled();
                    }
                    if (!bl2) return false;
                    optimizersModule = this;
                }
                if (bl) break block18;
                if (!optimizersModule.cutebowAnchor.getValue()) return false;
                optimizersModule = this;
            }
            if (bl) break block19;
            if (optimizersModule.anchors.getValue()) return false;
            optimizersModule = this;
        }
        boolean bl3 = optimizersModule.heroAnchor.getValue();
        if (bl) return bl3;
        if (bl3) return false;
        return true;
    }

    public boolean cutebowRemoveOutline() {
        return this.cutebowOutline.getValue();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean clientCrystals() {
        OptimizersModule optimizersModule;
        boolean bl;
        block9: {
            boolean bl2;
            block8: {
                bl = AmbienceModule.b;
                bl2 = OptimizersModule.superseded();
                if (bl) break block8;
                if (bl2) return false;
                optimizersModule = this;
                if (bl) break block9;
                bl2 = optimizersModule.isEnabled();
            }
            if (!bl2) return false;
            optimizersModule = this;
        }
        boolean bl3 = optimizersModule.clientCrystals.getValue();
        if (bl) return bl3;
        if (!bl3) return false;
        return true;
    }

    public float crystalPreviewOpacity() {
        float f = OptimizersModule.superseded() ? 1.0f : this.crystalOpacity.getValueFloat();
        return f;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean consumables() {
        OptimizersModule optimizersModule;
        boolean bl;
        block9: {
            boolean bl2;
            block8: {
                bl = AmbienceModule.b;
                bl2 = OptimizersModule.superseded();
                if (bl) break block8;
                if (bl2) return false;
                optimizersModule = this;
                if (bl) break block9;
                bl2 = optimizersModule.isEnabled();
            }
            if (!bl2) return false;
            optimizersModule = this;
        }
        boolean bl3 = optimizersModule.consumables.getValue();
        if (bl) return bl3;
        if (!bl3) return false;
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean crossbow() {
        OptimizersModule optimizersModule;
        boolean bl;
        block9: {
            boolean bl2;
            block8: {
                bl = AmbienceModule.b;
                bl2 = OptimizersModule.superseded();
                if (bl) break block8;
                if (bl2) return false;
                optimizersModule = this;
                if (bl) break block9;
                bl2 = optimizersModule.isEnabled();
            }
            if (!bl2) return false;
            optimizersModule = this;
        }
        boolean bl3 = optimizersModule.crossbow.getValue();
        if (bl) return bl3;
        if (!bl3) return false;
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean elytra() {
        OptimizersModule optimizersModule;
        boolean bl;
        block14: {
            block13: {
                boolean bl2;
                block12: {
                    bl = AmbienceModule.b;
                    bl2 = OptimizersModule.superseded();
                    if (bl) break block12;
                    if (bl2) return false;
                    optimizersModule = this;
                    if (bl) break block13;
                    bl2 = optimizersModule.isEnabled();
                }
                if (!bl2) return false;
                optimizersModule = this;
            }
            if (bl) break block14;
            if (!optimizersModule.elytra.getValue()) return false;
            optimizersModule = this;
        }
        boolean bl3 = optimizersModule.elytraBlockedHere();
        if (bl) return bl3;
        if (bl3) return false;
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean elytraBlockedHere() {
        String string;
        boolean bl;
        block27: {
            String string2;
            block26: {
                int n;
                block24: {
                    block25: {
                        ServerInfo serverInfo;
                        block22: {
                            ServerInfo serverInfo2;
                            block23: {
                                block21: {
                                    serverInfo2 = this.mc.method_1558();
                                    bl = AmbienceModule.b;
                                    serverInfo = serverInfo2;
                                    if (bl) break block21;
                                    if (serverInfo == null) return false;
                                    serverInfo = serverInfo2;
                                }
                                if (bl) break block22;
                                if (serverInfo.field_3761 != null) break block23;
                                return false;
                            }
                            serverInfo = serverInfo2;
                        }
                        string = serverInfo.field_3761.trim().toLowerCase(Locale.ROOT);
                        int n2 = string.lastIndexOf(58);
                        n = n2;
                        if (bl) break block24;
                        if (n <= 0) break block25;
                        string = string.substring(0, n2);
                    }
                    string2 = string;
                    if (bl) break block26;
                    n = string2.endsWith(".") ? 1 : 0;
                }
                if (n == 0) break block27;
                string2 = string.substring(0, string.length() - 1);
            }
            string = string2;
        }
        String[] stringArray = NO_ELYTRA;
        int n = stringArray.length;
        int n3 = 0;
        do {
            block28: {
                boolean bl2;
                if (n3 >= n) return false;
                String string3 = stringArray[n3];
                boolean bl3 = string.equals(string3);
                if (bl) return bl3;
                if (bl) return bl2;
                if (bl3) return true;
                bl2 = string.endsWith("." + string3);
                if (bl) return bl2;
                if (!bl2) break block28;
                return true;
            }
            ++n3;
        } while (!bl);
        return false;
    }

    @Override
    public void onTick() {
        Ledger.tick();
        AnchorPrediction.tick();
        GhostAnchor.tick();
        CrossbowOptimizer.tick();
        Blast.tick();
    }

    
    @Override
    public void onSettingsChanged() {
        block24: {
            BooleanSetting booleanSetting;
            block25: {
                OptimizersModule optimizersModule;
                boolean bl;
                block23: {
                    BooleanSetting booleanSetting2;
                    block21: {
                        block22: {
                            block20: {
                                OptimizersModule optimizersModule2;
                                block19: {
                                    bl = AmbienceModule.b;
                                    optimizersModule2 = this;
                                    if (!bl) {
                                        if (!optimizersModule2.anchors.getValue()) {
                                            AnchorOptimizer.clearAll();
                                        }
                                        optimizersModule2 = this;
                                    }
                                    if (bl) break block19;
                                    if (!optimizersModule2.heroAnchor.getValue()) break block20;
                                    optimizersModule2 = this;
                                }
                                booleanSetting2 = optimizersModule2.anchors;
                                if (bl) break block21;
                                if (!booleanSetting2.getValue()) break block22;
                            }
                            GhostAnchor.restoreAll();
                        }
                        optimizersModule = this;
                        if (bl) break block23;
                        booleanSetting2 = optimizersModule.cutebowAnchor;
                    }
                    if (!booleanSetting2.getValue()) break block24;
                    optimizersModule = this;
                }
                booleanSetting = optimizersModule.anchors;
                if (bl) break block25;
                if (booleanSetting.getValue()) break block24;
                booleanSetting = this.heroAnchor;
            }
            if (!booleanSetting.getValue()) return;
        }
        AnchorPrediction.restoreAll();
    }

    @Override
    public void onDisable() {
        AnchorOptimizer.clearAll();
        GhostAnchor.restoreAll();
        AnchorPrediction.restoreAll();
        ConsumableOptimizer.reset();
        CrossbowOptimizer.reset();
        CrystalPrediction.clearAll();
        CrystalOptimizer.clearAll();
        Blast.clearAll();
    }

    private static void lambda$new$1() {
    }

    private static String lambda$new$0() {
        boolean bl;
        block17: {
            boolean bl2;
            block18: {
                boolean bl3;
                block15: {
                    boolean bl4;
                    block16: {
                        bl4 = MarlowProtocol.optedOut();
                        bl2 = ConsumableOptimizer.refused();
                        bl3 = AmbienceModule.b;
                        bl = bl4;
                        if (bl3) break block15;
                        if (!bl) break block16;
                        bl = bl2;
                        if (bl3) break block15;
                        if (!bl) break block16;
                        return b[18];
                    }
                    bl = bl4;
                }
                if (bl3) break block17;
                if (!bl) break block18;
                return b[24];
            }
            bl = bl2;
        }
        if (bl) {
            return b[21];
        }
        return b[0];
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[30];
                var3_1 = 0;
                var2_2 = "$TW\r\u0000SFEP^\u0010\u0012\u000f5J^\u0014\u001eSUEWK\u0003\u0014_V\u001c\u000e7]V\r\u0001S\u0002\nMO\u000e\u001eXG\u0014&TR\u0007\u0019B\u0002\u0016Q_\u0007WUP\u001cKO\u0003\u001bE\u0018&MO\u0007\u0015YUEYU\u0001\u001fYPEWK\u0016\u001e[K\u001f]I\u00066]I\u0014\u0012D\u0012&JT\u0011\u0004TM\u0012\u0018T\u0012\u0003_O\fB^\u0010\u000b\u0013JT\u001b\u0016ZGKV^\u0016!#JZ\u000f\u0012\u0016R\u0004[R\f\u0010\f\u0002\u0000N^\fWpr6\u0018X\u0003\u0007\u0016V\fUR\f\u0010\u0013.]^\u0012WSL\u0011QO\u001bW@K\u0016QY\u000e\u0012\")WLB\u001bWV\u0000VX\u001bM\u0016\u0013E^I\u0003\u001aS\u0002\u0014M^\u0017\u0012R\u0002\nV\u001b%'c\u00172YW\t\u0004O\u0002\u0004VX\n\u0018D\u0002\nHO\u000b\u001a_X\u0000J\u000b5]I\u0004\u0018DO\u0004VX\u0007\u0010(YI\u000e\u0018A\u0002\nHO\u000b\u001a_X\u0000J\u0010 TB\u0016\u0005W\u0002\nHO\u000b\u001a_X\u0000J\n*HO\u000b\u001a_X\u0000JH\u0011\u0013YU\u000b\u001bZC\u0017WB\u0003\u001bS\f\u000b]O\u001b6SR\u0012WFC\u0017LR\u0001\u001bSQEZ^\n\u001eXFEOZ\u000e\u001bE\u001b'WO\nWYD\u0003\u0002\u001b\u0016\u001f_QEK^\u0010\u0001SPEYH\t\u0012R\"6SR\u0012WRP\u0004OR\f\u0010\u0016G\u000bLR\u0016\u001eSQEZ^\n\u001eXFEOZ\u000e\u001bE\u0015&TR\u0007\u0019B\u0002\u0016Q_\u0007WSZ\u0015TT\u0011\u001eYL\"&WU\u0011\u0002[C\u0007T^\u0011WYD\u0003\u0002\u001b\u0016\u001f_QEK^\u0010\u0001SPEYH\t\u0012R 6SR\u0012WTN\n[PB\u0012XV\fLR\u0007\u0004\u0016@\u0000PR\f\u0013\u0016U\u0004TW\u0011\u0015-]I\rWWL\u0006PT\u0010WYR\u0011QV\u000b\rSP\u001d(YI\u000e\u0018A\u0002\n^]XWBJ\fK\u001b\u0011\u0012DT\u0000J\u001b\u0003\u0004]G\u0001\u0014&WU\u0011\u0002[C\u0007T^B\u0018FV\fUR\u0018\u0012D)&TR\u0007\u0019B\u000f\u0016Q_\u0007WUP\u001cKO\u0003\u001b\u0016C\u000b\\\u001b\u0003\u0019UJ\nJ\u001b\u0012\u0005SF\f[O\u000b\u0018X\f\u000bYI\r\u000eWN\u0000\u0016U\u0007\u0003";
                var4_3 = "$TW\r\u0000SFEP^\u0010\u0012\u000f5J^\u0014\u001eSUEWK\u0003\u0014_V\u001c\u000e7]V\r\u0001S\u0002\nMO\u000e\u001eXG\u0014&TR\u0007\u0019B\u0002\u0016Q_\u0007WUP\u001cKO\u0003\u001bE\u0018&MO\u0007\u0015YUEYU\u0001\u001fYPEWK\u0016\u001e[K\u001f]I\u00066]I\u0014\u0012D\u0012&JT\u0011\u0004TM\u0012\u0018T\u0012\u0003_O\fB^\u0010\u000b\u0013JT\u001b\u0016ZGKV^\u0016!#JZ\u000f\u0012\u0016R\u0004[R\f\u0010\f\u0002\u0000N^\fWpr6\u0018X\u0003\u0007\u0016V\fUR\f\u0010\u0013.]^\u0012WSL\u0011QO\u001bW@K\u0016QY\u000e\u0012\")WLB\u001bWV\u0000VX\u001bM\u0016\u0013E^I\u0003\u001aS\u0002\u0014M^\u0017\u0012R\u0002\nV\u001b%'c\u00172YW\t\u0004O\u0002\u0004VX\n\u0018D\u0002\nHO\u000b\u001a_X\u0000J\u000b5]I\u0004\u0018DO\u0004VX\u0007\u0010(YI\u000e\u0018A\u0002\nHO\u000b\u001a_X\u0000J\u0010 TB\u0016\u0005W\u0002\nHO\u000b\u001a_X\u0000J\n*HO\u000b\u001a_X\u0000JH\u0011\u0013YU\u000b\u001bZC\u0017WB\u0003\u001bS\f\u000b]O\u001b6SR\u0012WFC\u0017LR\u0001\u001bSQEZ^\n\u001eXFEOZ\u000e\u001bE\u001b'WO\nWYD\u0003\u0002\u001b\u0016\u001f_QEK^\u0010\u0001SPEYH\t\u0012R\"6SR\u0012WRP\u0004OR\f\u0010\u0016G\u000bLR\u0016\u001eSQEZ^\n\u001eXFEOZ\u000e\u001bE\u0015&TR\u0007\u0019B\u0002\u0016Q_\u0007WSZ\u0015TT\u0011\u001eYL\"&WU\u0011\u0002[C\u0007T^\u0011WYD\u0003\u0002\u001b\u0016\u001f_QEK^\u0010\u0001SPEYH\t\u0012R 6SR\u0012WTN\n[PB\u0012XV\fLR\u0007\u0004\u0016@\u0000PR\f\u0013\u0016U\u0004TW\u0011\u0015-]I\rWWL\u0006PT\u0010WYR\u0011QV\u000b\rSP\u001d(YI\u000e\u0018A\u0002\n^]XWBJ\fK\u001b\u0011\u0012DT\u0000J\u001b\u0003\u0004]G\u0001\u0014&WU\u0011\u0002[C\u0007T^B\u0018FV\fUR\u0018\u0012D)&TR\u0007\u0019B\u000f\u0016Q_\u0007WUP\u001cKO\u0003\u001b\u0016C\u000b\\\u001b\u0003\u0019UJ\nJ\u001b\u0012\u0005SF\f[O\u000b\u0018X\f\u000bYI\r\u000eWN\u0000\u0016U\u0007\u0003".length();
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
                    var2_2 = "6SR\u0012WPC\u0017\u0018S\u0007\u001bR\u0002\fL^\u000f\u0004\u001a\u0002\u0006YK\u0007\u0004\u001a\u0002\u0016Q\\\f\u0004$V\b\u001b$'e\u0002\u0012PR\u000e\u0012\u0016V\r]\u001b\u0015\u001eXF\nO\u001b\u000b\u0004\u0016W\u000b^T\u0001\u0002EG\u0001";
                    var4_3 = "6SR\u0012WPC\u0017\u0018S\u0007\u001bR\u0002\fL^\u000f\u0004\u001a\u0002\u0006YK\u0007\u0004\u001a\u0002\u0016Q\\\f\u0004$V\b\u001b$'e\u0002\u0012PR\u000e\u0012\u0016V\r]\u001b\u0015\u001eXF\nO\u001b\u000b\u0004\u0016W\u000b^T\u0001\u0002EG\u0001".length();
                    var1_4 = 33;
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
                        v10 = 101;
                        break;
                    }
                    case 1: {
                        v10 = 56;
                        break;
                    }
                    case 2: {
                        v10 = 59;
                        break;
                    }
                    case 3: {
                        v10 = 98;
                        break;
                    }
                    case 4: {
                        v10 = 119;
                        break;
                    }
                    case 5: {
                        v10 = 54;
                        break;
                    }
                    default: {
                        v10 = 34;
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
        OptimizersModule.b = var5;
        v11 = new String[3];
        var0_6 = OptimizersModule.b;
        v11[0] = var0_6[16];
        v11[1] = var0_6[7];
        v11[2] = var0_6[27];
        OptimizersModule.NO_ELYTRA = v11;
    }
}
