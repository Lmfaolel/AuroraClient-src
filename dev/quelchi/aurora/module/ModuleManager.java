package dev.quelchi.aurora.module;

import dev.quelchi.aurora.config.ConfigManager;
import dev.quelchi.aurora.gui.HudScale;
import dev.quelchi.aurora.gui.ToggleDisplay;
import dev.quelchi.aurora.gui.lq.HudBridge;
import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.DraggableHud;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.UnscaledHud;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.AntiAltModule;
import dev.quelchi.aurora.module.modules.ArmorHudModule;
import dev.quelchi.aurora.module.modules.AutoDeafenModule;
import dev.quelchi.aurora.module.modules.AutoTextModule;
import dev.quelchi.aurora.module.modules.BlockOverlayModule;
import dev.quelchi.aurora.module.modules.ChatModule;
import dev.quelchi.aurora.module.modules.CleanViewModule;
import dev.quelchi.aurora.module.modules.CobwebModule;
import dev.quelchi.aurora.module.modules.CoordsHudModule;
import dev.quelchi.aurora.module.modules.CrossbowTweaksModule;
import dev.quelchi.aurora.module.modules.CrosshairAddonsModule;
import dev.quelchi.aurora.module.modules.CrosshairModule;
import dev.quelchi.aurora.module.modules.CustomCrystalsModule;
import dev.quelchi.aurora.module.modules.DamageTintModule;
import dev.quelchi.aurora.module.modules.DeathAnimationModule;
import dev.quelchi.aurora.module.modules.DiscordRpcModule;
import dev.quelchi.aurora.module.modules.FpsHudModule;
import dev.quelchi.aurora.module.modules.FullBrightModule;
import dev.quelchi.aurora.module.modules.GuiTweaksModule;
import dev.quelchi.aurora.module.modules.HandTweaksModule;
import dev.quelchi.aurora.module.modules.HideArmorModule;
import dev.quelchi.aurora.module.modules.HidePlayerModelModule;
import dev.quelchi.aurora.module.modules.HitboxesModule;
import dev.quelchi.aurora.module.modules.ItemHighlighterModule;
import dev.quelchi.aurora.module.modules.KeystrokesModule;
import dev.quelchi.aurora.module.modules.MotionBlurModule;
import dev.quelchi.aurora.module.modules.MusicPlayerModule;
import dev.quelchi.aurora.module.modules.NametagsModule;
import dev.quelchi.aurora.module.modules.NostalgiaModule;
import dev.quelchi.aurora.module.modules.OptimizersModule;
import dev.quelchi.aurora.module.modules.PingHudModule;
import dev.quelchi.aurora.module.modules.PopChamsModule;
import dev.quelchi.aurora.module.modules.PotionHudModule;
import dev.quelchi.aurora.module.modules.RendersModule;
import dev.quelchi.aurora.module.modules.SaturationOverlayModule;
import dev.quelchi.aurora.module.modules.ScreenshotModule;
import dev.quelchi.aurora.module.modules.ShieldTweaksModule;
import dev.quelchi.aurora.module.modules.ShulkerTooltipsModule;
import dev.quelchi.aurora.module.modules.SoundControllerModule;
import dev.quelchi.aurora.module.modules.SprintModule;
import dev.quelchi.aurora.module.modules.TierTaggerModule;
import dev.quelchi.aurora.module.modules.TimeChangerModule;
import dev.quelchi.aurora.module.modules.TotemCounterModule;
import dev.quelchi.aurora.module.modules.TotemTweaksModule;
import dev.quelchi.aurora.module.modules.TransparentPlayersModule;
import dev.quelchi.aurora.module.modules.WatermarkModule;
import dev.quelchi.aurora.module.modules.WeatherChangerModule;
import dev.quelchi.aurora.module.modules.ZoomModule;
import dev.quelchi.aurora.util.KeyUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Util;

public final class ModuleManager {
    private final List<Module> modules;
    private final Set<Integer> heldToggleKeys;

    public ModuleManager() {
        int n;
        block11: {
            boolean bl;
            block12: {
                this.modules = new ArrayList<Module>();
                this.heldToggleKeys = new HashSet<Integer>();
                this.modules.add(new DamageTintModule());
                this.modules.add(new HideArmorModule());
                this.modules.add(new CleanViewModule());
                this.modules.add(new CrosshairModule());
                this.modules.add(new CrosshairAddonsModule());
                this.modules.add(new CrossbowTweaksModule());
                this.modules.add(new TransparentPlayersModule());
                this.modules.add(new RendersModule());
                this.modules.add(new PopChamsModule());
                this.modules.add(new HandTweaksModule());
                this.modules.add(new NametagsModule());
                this.modules.add(new TierTaggerModule());
                this.modules.add(new CobwebModule());
                this.modules.add(new HitboxesModule());
                this.modules.add(new BlockOverlayModule());
                n = Category.a;
                this.modules.add(new KeystrokesModule());
                this.modules.add(new TotemCounterModule());
                this.modules.add(new FpsHudModule());
                this.modules.add(new SprintModule());
                this.modules.add(new CoordsHudModule());
                this.modules.add(new PingHudModule());
                this.modules.add(new PotionHudModule());
                this.modules.add(new FullBrightModule());
                this.modules.add(new TimeChangerModule());
                this.modules.add(new WeatherChangerModule());
                bl = this.modules.add(new AmbienceModule());
                if (n != 0) break block11;
                this.modules.add(new ScreenshotModule());
                this.modules.add(new TotemTweaksModule());
                this.modules.add(new OptimizersModule());
                this.modules.add(new DiscordRpcModule());
                this.modules.add(new DeathAnimationModule());
                this.modules.add(new MotionBlurModule());
                this.modules.add(new NostalgiaModule());
                this.modules.add(new CustomCrystalsModule());
                this.modules.add(new SoundControllerModule());
                this.modules.add(new ArmorHudModule());
                this.modules.add(new GuiTweaksModule());
                this.modules.add(new ItemHighlighterModule());
                this.modules.add(new AntiAltModule());
                this.modules.add(new ChatModule());
                this.modules.add(new HidePlayerModelModule());
                this.modules.add(new AutoTextModule());
                if (Util.method_668() != Util.OperatingSystem.field_1133) break block12;
                this.modules.add(new AutoDeafenModule());
            }
            this.modules.add(new ZoomModule());
            this.modules.add(new WatermarkModule());
            this.modules.add(new MusicPlayerModule());
            this.modules.add(new ShieldTweaksModule());
            this.modules.add(new SaturationOverlayModule());
            bl = this.modules.add(new ShulkerTooltipsModule());
        }
        for (Module module : this.modules) {
            block14: {
                Module module2;
                block13: {
                    module2 = module;
                    if (n != 0) break block13;
                    if (!(module2 instanceof DraggableHud)) break block14;
                    module2 = module;
                }
                DraggableHud draggableHud = (DraggableHud)((Object)module2);
                draggableHud.rememberHome();
            }
            if (n == 0) continue;
            boolean bl = !Module.a;
            Module.a = bl;
            break;
        }
    }

    public void onTick() {
        this.handleToggleKeys();
        int n = Category.a;
        for (Module module : this.modules) {
            block6: {
                Module module2;
                block5: {
                    module2 = module;
                    if (n != 0) break block5;
                    if (!module2.isEnabled()) break block6;
                    module2 = module;
                }
                module2.onTick();
            }
            if (n == 0) continue;
        }
    }

    private void handleToggleKeys() {
        boolean bl;
        block37: {
            int n;
            int n2;
            HashSet<Integer> hashSet;
            block36: {
                block32: {
                    hashSet = new HashSet<Integer>();
                    n2 = Category.a;
                    for (Module module : this.modules) {
                        block35: {
                            int n3;
                            int n4;
                            int n5;
                            block33: {
                                n5 = module.getToggleKey().getKey();
                                if (n2 != 0) break block32;
                                n4 = n5;
                                if (n2 != 0) break block33;
                                if (n4 == -1) {
                                    continue;
                                }
                                n3 = n5;
                                if (n2 != 0) break block35;
                                n4 = KeyUtil.isKeyPressed(n3) ? 1 : 0;
                            }
                            if (n4 != 0) {
                                n3 = hashSet.add(n5) ? 1 : 0;
                            }
                        }
                        if (n2 == 0) continue;
                    }
                    if (MinecraftClient.method_1551().field_1755 == null) break block32;
                    n = 1;
                    break block36;
                }
                n = 0;
            }
            int n6 = n;
            bl = false;
            for (Module module : this.modules) {
                block42: {
                    boolean bl2;
                    block41: {
                        Integer n7;
                        Set<Integer> set;
                        block40: {
                            int n8;
                            int n9;
                            block39: {
                                int n10;
                                block38: {
                                    if (n2 != 0) break block37;
                                    n10 = n6;
                                    if (n2 != 0) break block38;
                                    if (n10 != 0) {
                                        break;
                                    }
                                    n10 = module.getToggleKey().getKey();
                                }
                                n9 = n10;
                                n8 = n9;
                                if (n2 != 0) break block39;
                                if (n8 == -1) continue;
                                set = hashSet;
                                n7 = n9;
                                if (n2 != 0) break block40;
                                n8 = set.contains(n7) ? 1 : 0;
                            }
                            if (n8 == 0) continue;
                            set = this.heldToggleKeys;
                            n7 = n9;
                        }
                        bl2 = set.contains(n7);
                        if (n2 != 0) break block41;
                        if (bl2) break block42;
                        module.toggle();
                        ToggleDisplay.push(module.getName(), module.isEnabled());
                        bl2 = true;
                    }
                    bl = bl2;
                }
                if (n2 == 0) continue;
            }
            this.heldToggleKeys.retainAll(hashSet);
            this.heldToggleKeys.addAll(hashSet);
        }
        if (bl) {
            ConfigManager.save();
        }
    }

    
    public void onHudRender(DrawContext drawContext) {
        HudScale.push(drawContext);
        int n = Category.a;
        try {
            Iterator<Module> iterator = this.modules.iterator();
            while (iterator.hasNext()) {
                Module module = iterator.next();
                if (n != 0) return;
                if (n == 0) {
                    if (!module.isEnabled()) continue;
                    this.renderOne(module, drawContext);
                }
                if (n != 0) return;
            }
            return;
        }
        finally {
            HudScale.pop(drawContext);
        }
    }

    
    public void renderOne(Module var1_1, DrawContext var2_2) {
        block38: {
            block39: {
                block41: {
                    block40: {
                        block47: {
                            block46: {
                                block45: {
                                    block36: {
                                        block35: {
                                            block33: {
                                                block34: {
                                                    block44: {
                                                        block43: {
                                                            var3_3 = Category.a;
                                                            v0 = var1_1;
                                                            if (var3_3 != 0) break block33;
                                                            if (!(v0 instanceof DraggableHud)) break block34;
                                                            break block43;
                                                            catch (MatchException v1) {
                                                                throw v1;
                                                            }
                                                        }
                                                        v0 = var1_1;
                                                        if (var3_3 != 0) break block33;
                                                        var4_4 = (DraggableHud)v0;
                                                        if (!var4_4.hudHidden()) break block34;
                                                        v3 = ModuleManager.editing();
                                                        if (var3_3 != 0) break block35;
                                                        break block44;
                                                        catch (MatchException v4) {
                                                            throw v4;
                                                        }
                                                    }
                                                    if (v3) break block34;
                                                    return;
                                                }
                                                v0 = var1_1;
                                            }
                                            if (var3_3 != 0) break block36;
                                            v3 = v0 instanceof UnscaledHud;
                                        }
                                        if (!v3) // goto
                                        HudScale.pop(var2_2);
                                        try {
                                            var1_1.onHudRender(var2_2);
                                        }
                                        finally {
                                            HudScale.push(var2_2);
                                        }
                                        if (var3_3 == 0) break block38;
lbl52:

                                        v0 = var1_1;
                                    }
                                    if (var3_3 != 0) break block39;
                                    if (!(v0 instanceof DraggableHud)) // goto
                                    break block45;
                                    catch (MatchException v8) {
                                        throw v8;
                                    }
                                }
                                v0 = var1_1;
                                if (var3_3 != 0) break block39;
                                var4_4 = (DraggableHud)v0;
                                if (!var4_4.hudScalable()) // goto
                                v10 = var4_4;
                                if (var3_3 != 0) break block40;
                                break block46;
                                catch (MatchException v11) {
                                    throw v11;
                                }
                            }
                            if (v10.scalesItself()) // goto
                            break block47;
                            catch (MatchException v12) {
                                throw v12;
                            }
                        }
                        v10 = var4_4;
                        if (var3_3 != 0) break block41;
                    }
                    if (v10.getHudScale() != 1.0f) {
                        v10 = var4_4;
                    }
                }
                var5_6 = v10.getHudScale();
                var6_7 = var4_4.getHudX();
                var7_8 = var4_4.getHudY();
                var2_2.method_51448().pushMatrix();
                var2_2.method_51448().translate(var6_7, var7_8);
                var2_2.method_51448().scale(var5_6, var5_6);
                var2_2.method_51448().translate(-var6_7, -var7_8);
                try {
                    var1_1.onHudRender(var2_2);
                }
                finally {
                    var2_2.method_51448().popMatrix();
                }
                if (var3_3 == 0) break block38;
lbl121:

                v0 = var1_1;
            }
            v0.onHudRender(var2_2);
        }
    }

    public static boolean editing() {
        return HudBridge.editing();
    }

    public List<Module> getModules() {
        return this.modules;
    }

    public List<Module> getModules(Category category) {
        ArrayList<Module> arrayList = new ArrayList<Module>();
        int n = Category.a;
        for (Module module : this.modules) {
            if (module.getCategory() == category) {
                arrayList.add(module);
            }
            if (n == 0) continue;
        }
        return arrayList;
    }
}
