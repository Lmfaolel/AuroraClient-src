package dev.quelchi.aurora.glass;

import dev.quelchi.aurora.glass.hud.GlassHudElement;
import dev.quelchi.aurora.glass.hud.HudEditorScreen;
import dev.quelchi.aurora.glass.hud.HudLayoutStore;
import dev.quelchi.aurora.glass.hud.HudRegistry;
import dev.quelchi.aurora.glass.render.minecraft.GlassGpu;
import dev.quelchi.aurora.glass.style.LiquidGlassTheme;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import java.util.Iterator;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.InvalidateRenderStateCallback;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceReloader;
import net.minecraft.resource.ResourceType;
import net.minecraft.resource.SynchronousResourceReloader;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class GlassKit {
    public static final Logger LOG;
    private static final LiquidGlassTheme THEME;
    private static final HudLayoutStore HUD_LAYOUTS;
    private static final HudRegistry HUD;
    private static final String[] a;

    private GlassKit() {
    }

    public static void init() {
        block4: {
            boolean bl;
            block6: {
                block5: {
                    String[] stringArray = a;
                    HudElementRegistry.addLast((Identifier)GlassKit.id(stringArray[3]), HUD::render);
                    ClientTickEvents.END_CLIENT_TICK.register(GlassKit::lambda$init$0);
                    InvalidateRenderStateCallback.EVENT.register(GlassKit::lambda$init$1);
                    ResourceLoader.get((ResourceType)ResourceType.field_14188).registerReloader(GlassKit.id(stringArray[5]), (ResourceReloader)((SynchronousResourceReloader)GlassKit::lambda$init$2));
                    ClientLifecycleEvents.CLIENT_STOPPING.register(GlassKit::lambda$init$3);
                    int n = BooleanSetting.b;
                    if (n == 0) break block4;
                    if (!Module.a) break block5;
                    bl = false;
                    break block6;
                }
                bl = true;
            }
            Module.a = bl;
        }
    }

    public static Identifier id(String string) {
        String[] stringArray = a;
        return Identifier.method_60655((String)stringArray[0], (String)(stringArray[4] + string));
    }

    public static LiquidGlassTheme theme() {
        return THEME;
    }

    public static HudRegistry hud() {
        return HUD;
    }

    public static HudLayoutStore hudLayouts() {
        return HUD_LAYOUTS;
    }

    public static HudEditorScreen createHudEditor() {
        return new HudEditorScreen(HUD, HUD_LAYOUTS);
    }

    private static void shutdown() {
        block4: {
            Iterator<GlassHudElement> iterator = HUD.elements().iterator();
            int n = BooleanSetting.b;
            while (iterator.hasNext()) {
                GlassHudElement glassHudElement = iterator.next();
                if (n == 0) {
                    HUD_LAYOUTS.capture(glassHudElement);
                    if (n == 0) continue;
                    break;
                }
                break block4;
            }
            HUD_LAYOUTS.save();
            HUD.clear();
        }
    }

    private static void lambda$init$3(MinecraftClient minecraftClient) {
        try {
            GlassKit.shutdown();
        }
        finally {
            GlassGpu.close();
        }
    }

    private static void lambda$init$2(ResourceManager resourceManager) {
        GlassGpu.invalidate();
    }

    private static void lambda$init$1() {
        HUD.invalidateRenderState();
        GlassGpu.invalidate();
    }

    private static void lambda$init$0(MinecraftClient minecraftClient) {
        HUD.tick();
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[6];
                var3_1 = 0;
                var2_2 = "\u0002D\u001a)5h\u001b\u0002D\u001a)5h\t\u0004]\t54$L\u0016UF65fT\u0006C\u001c/\"z\f\u0002D\u001a)5h\t\u0004]\t54\u0003\u000bD\f";
                var4_3 = "\u0002D\u001a)5h\u001b\u0002D\u001a)5h\t\u0004]\t54$L\u0016UF65fT\u0006C\u001c/\"z\f\u0002D\u001a)5h\t\u0004]\t54\u0003\u000bD\f".length();
                var1_4 = 6;
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
                    var2_2 = "\u0004]\t54&\u0005\u0004]\t54";
                    var4_3 = "\u0004]\t54&\u0005\u0004]\t54".length();
                    var1_4 = 6;
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
                        v10 = 99;
                        break;
                    }
                    case 1: {
                        v10 = 49;
                        break;
                    }
                    case 2: {
                        v10 = 104;
                        break;
                    }
                    case 3: {
                        v10 = 70;
                        break;
                    }
                    case 4: {
                        v10 = 71;
                        break;
                    }
                    case 5: {
                        v10 = 9;
                        break;
                    }
                    default: {
                        v10 = 36;
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
        GlassKit.a = var5;
        var0_6 = GlassKit.a;
        GlassKit.LOG = LoggerFactory.getLogger((String)var0_6[2]);
        GlassKit.THEME = LiquidGlassTheme.dark();
        GlassKit.HUD_LAYOUTS = new HudLayoutStore(FabricLoader.getInstance().getConfigDir().resolve(var0_6[1]));
        GlassKit.HUD = new HudRegistry();
    }
}
