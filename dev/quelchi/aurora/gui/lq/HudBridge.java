package dev.quelchi.aurora.gui.lq;

import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.config.ConfigManager;
import dev.quelchi.aurora.glass.GlassKit;
import dev.quelchi.aurora.glass.hud.Anchor;
import dev.quelchi.aurora.glass.hud.GlassHudElement;
import dev.quelchi.aurora.glass.hud.HudPlacement;
import dev.quelchi.aurora.gui.HudScale;
import dev.quelchi.aurora.gui.lq.HudBridge$Blank;
import dev.quelchi.aurora.gui.lq.HudEditor;
import dev.quelchi.aurora.module.DraggableHud;
import dev.quelchi.aurora.module.ExtraHuds;
import dev.quelchi.aurora.module.Module;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.Identifier;

public final class HudBridge {
    private static final Map<DraggableHud, GlassHudElement> ELEMENTS;
    private static final Set<DraggableHud> LIVE;
    private static final Map<DraggableHud, int[]> PUSHED;
    private static final Map<String, DraggableHud> BY_ID;
    private static final Map<String, Module> OWNERS;
    private static final Map<String, String> NAMES;
    private static boolean wasEditing;
    private static final float MARK = 360.0f;
    public static boolean a;
    private static final String[] b;

    private HudBridge() {
    }

    static Collection<GlassHudElement> pieces() {
        return ELEMENTS.values();
    }

    static DraggableHud hud(GlassHudElement glassHudElement) {
        return BY_ID.get(glassHudElement.id().method_12832());
    }

    static int[] box(GlassHudElement glassHudElement) {
        int[] nArray;
        block16: {
            int[] nArray2;
            block14: {
                int[] nArray3;
                block15: {
                    int[] nArray4;
                    boolean bl;
                    block13: {
                        Object object;
                        block11: {
                            DraggableHud draggableHud;
                            block12: {
                                block10: {
                                    draggableHud = HudBridge.hud(glassHudElement);
                                    bl = a;
                                    if (draggableHud == null) break block10;
                                    object = LIVE;
                                    if (bl) break block11;
                                    if (object.contains(draggableHud)) break block12;
                                }
                                nArray4 = null;
                                break block13;
                            }
                            object = PUSHED.get(draggableHud);
                        }
                        nArray4 = (int[])object;
                    }
                    nArray3 = nArray4;
                    nArray2 = nArray3;
                    if (bl) break block14;
                    if (nArray2 != null) break block15;
                    nArray = null;
                    break block16;
                }
                int[] nArray5 = new int[4];
                nArray5[0] = nArray3[0];
                nArray5[1] = nArray3[1];
                nArray2 = nArray5;
                nArray5[2] = glassHudElement.width();
            }
            nArray = nArray2;
            nArray2[3] = glassHudElement.height();
        }
        return nArray;
    }

    static void remove(GlassHudElement glassHudElement) {
        block3: {
            Module module;
            block2: {
                Module module2 = HudBridge.owner(glassHudElement);
                boolean bl = a;
                module = module2;
                if (bl) break block2;
                if (module == null) break block3;
                module = module2;
            }
            module.setEnabled(false);
        }
    }

    static Module owner(GlassHudElement glassHudElement) {
        return OWNERS.get(glassHudElement.id().method_12832());
    }

    public static void openEditor() {
        HudBridge.openEditor(null);
    }

    public static void openEditor(Screen screen) {
        MinecraftClient.method_1551().method_1507((Screen)new HudEditor(screen));
    }

    public static boolean editing() {
        return MinecraftClient.method_1551().field_1755 instanceof HudEditor;
    }

    public static void apply() {
        boolean bl = a;
        if (!HudBridge.editing()) {
            return;
        }
        Iterator<DraggableHud> iterator = LIVE.iterator();
        block26: while (true) {
            Iterator<DraggableHud> iterator2 = iterator;
            while (iterator2.hasNext()) {
                block39: {
                    DraggableHud draggableHud;
                    HudPlacement hudPlacement;
                    DraggableHud draggableHud2;
                    block40: {
                        int n;
                        block38: {
                            block36: {
                                float f;
                                block37: {
                                    int n2;
                                    float f2;
                                    block35: {
                                        block34: {
                                            block32: {
                                                block33: {
                                                    float f3;
                                                    int[] nArray;
                                                    GlassHudElement glassHudElement;
                                                    block30: {
                                                        block31: {
                                                            iterator2 = iterator;
                                                            if (bl) continue;
                                                            draggableHud2 = iterator2.next();
                                                            glassHudElement = ELEMENTS.get(draggableHud2);
                                                            nArray = PUSHED.get(draggableHud2);
                                                            if (glassHudElement == null) continue block26;
                                                            if (nArray == null) {
                                                                continue block26;
                                                            }
                                                            hudPlacement = glassHudElement.placement();
                                                            float f4 = hudPlacement.rotation() - 360.0f;
                                                            f3 = f4 == 0.0f ? 0 : (f4 > 0.0f ? 1 : -1);
                                                            if (bl) break block30;
                                                            if (f3 == false) break block31;
                                                            draggableHud2.resetHudPos();
                                                            if (!bl) continue block26;
                                                        }
                                                        f3 = Math.max(0, Math.min(hudPlacement.offsetX(), HudScale.width() - glassHudElement.width()));
                                                    }
                                                    float f5 = f3;
                                                    int n3 = Math.max(0, Math.min(hudPlacement.offsetY(), HudScale.height() - glassHudElement.height()));
                                                    f2 = f5 - nArray[0];
                                                    n2 = n3 - nArray[1];
                                                    n = hudPlacement.offsetX();
                                                    if (bl) break block32;
                                                    if (n != nArray[0]) break block33;
                                                    n = hudPlacement.offsetY();
                                                    if (bl) break block32;
                                                    if (n != nArray[1]) break block33;
                                                    n2 = 0;
                                                    f2 = 0;
                                                }
                                                n = (int)f2;
                                            }
                                            if (bl) break block34;
                                            if (n != 0) break block35;
                                            n = n2;
                                        }
                                        if (bl) break block36;
                                        if (n == 0) break block37;
                                    }
                                    float f6 = HudBridge.unit(draggableHud2);
                                    draggableHud2.setHudPos(draggableHud2.getHudX() + Math.round((float)f2 * f6), draggableHud2.getHudY() + Math.round((float)n2 * f6));
                                }
                                n = (f = hudPlacement.scale() - 1.0f) == 0.0f ? 0 : (f > 0.0f ? 1 : -1);
                            }
                            if (bl) break block38;
                            if (n == 0) break block39;
                            draggableHud = draggableHud2;
                            if (bl) break block40;
                            n = draggableHud.hudScalable() ? 1 : 0;
                        }
                        if (n == 0) break block39;
                        draggableHud2.setHudHidden(false);
                        draggableHud = draggableHud2;
                    }
                    draggableHud.setHudScale(Math.max(0.25f, Math.min(6.0f, draggableHud2.getHudScale() * hudPlacement.scale())));
                }
                if (bl) break block26;
                continue block26;
            }
            break;
        }
    }

    public static void sync() {
        block22: {
            boolean bl;
            block29: {
                boolean bl2;
                block30: {
                    bl2 = HudBridge.editing();
                    boolean bl3 = a;
                    LIVE.clear();
                    Iterator<Module> iterator = Aurora.INSTANCE.getModuleManager().getModules().iterator();
                    block18: while (true) {
                        Iterator<Object> iterator2 = iterator;
                        block19: while (iterator2.hasNext()) {
                            block28: {
                                Object object;
                                Module module;
                                Module module2;
                                block27: {
                                    block25: {
                                        block26: {
                                            block23: {
                                                module2 = iterator.next();
                                                if (bl3) break block22;
                                                module = module2;
                                                if (bl3) break block23;
                                                if (!module.isEnabled()) {
                                                    continue block18;
                                                }
                                                module = module2;
                                            }
                                            if (bl3) break block25;
                                            if (!(module instanceof DraggableHud)) break block26;
                                            object = (DraggableHud)((Object)module2);
                                            HudBridge.mirror((DraggableHud)object, module2);
                                        }
                                        module = module2;
                                    }
                                    if (bl3) break block27;
                                    if (!(module instanceof ExtraHuds)) break block28;
                                    module = module2;
                                }
                                object = (ExtraHuds)((Object)module);
                                Iterator<DraggableHud> iterator3 = object.extraHuds().iterator();
                                while (iterator3.hasNext()) {
                                    iterator2 = iterator3;
                                    if (bl3) continue block19;
                                    DraggableHud draggableHud = (DraggableHud)iterator2.next();
                                    HudBridge.mirror(draggableHud, module2);
                                    if (!bl3) continue;
                                }
                            }
                            if (bl3) break block18;
                            continue block18;
                        }
                        break;
                    }
                    bl = wasEditing;
                    if (bl3) break block29;
                    if (!bl) break block30;
                    bl = bl2;
                    if (bl3) break block29;
                    if (bl) break block30;
                    ConfigManager.save();
                }
                bl = bl2;
            }
            wasEditing = bl;
        }
    }

    private static float unit(DraggableHud draggableHud) {
        float f = draggableHud.hudInGuiSpace() ? HudScale.factor() : 1.0f;
        return f;
    }

    private static void mirror(DraggableHud draggableHud, Module module) {
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        GlassHudElement glassHudElement;
        block19: {
            block20: {
                Object object;
                boolean bl;
                block12: {
                    String string;
                    String string2;
                    Map<String, Object> map;
                    block18: {
                        DraggableHud draggableHud2;
                        block16: {
                            block17: {
                                Object object2;
                                String string3;
                                block15: {
                                    DraggableHud draggableHud3;
                                    block13: {
                                        block14: {
                                            LIVE.add(draggableHud);
                                            bl = a;
                                            glassHudElement = ELEMENTS.get(draggableHud);
                                            if (glassHudElement != null) break block12;
                                            string3 = module.getName();
                                            draggableHud3 = draggableHud;
                                            if (bl) break block13;
                                            if (draggableHud3 != module) break block14;
                                            object2 = "";
                                            break block15;
                                        }
                                        draggableHud3 = draggableHud;
                                    }
                                    object2 = "/" + draggableHud3.hudKey().substring(draggableHud.hudKey().lastIndexOf(46) + 1);
                                }
                                object = string3 + (String)object2;
                                String[] stringArray = b;
                                String string4 = ((String)object).toLowerCase(Locale.ROOT).replaceAll(stringArray[0], "_");
                                glassHudElement = GlassKit.hud().add(new GlassHudElement(Identifier.method_60655((String)stringArray[1], (String)string4), new HudBridge$Blank(), 1, 1).visibleWhen(() -> HudBridge.lambda$mirror$0(draggableHud)));
                                ELEMENTS.put(draggableHud, glassHudElement);
                                map = BY_ID;
                                string2 = string4;
                                draggableHud2 = draggableHud;
                                if (bl) break block16;
                                map.put(string2, draggableHud2);
                                OWNERS.put(string4, module);
                                map = NAMES;
                                string2 = string4;
                                if (draggableHud != module) break block17;
                                string = module.getName();
                                break block18;
                            }
                            draggableHud2 = draggableHud;
                        }
                        string = HudBridge.words(draggableHud2.hudKey().substring(draggableHud.hudKey().lastIndexOf(46) + 1));
                    }
                    map.put(string2, (DraggableHud)((Object)string));
                }
                object = draggableHud.getLastBounds();
                float f7 = draggableHud.getHudScale();
                f6 = object[0];
                f5 = object[1];
                f4 = (float)object[2];
                f3 = (float)object[3];
                f2 = f7;
                if (bl) break block19;
                if (f2 == 1.0f) break block20;
                f = draggableHud.getHudX();
                float f8 = draggableHud.getHudY();
                f6 = f + (f6 - f) * f7;
                f5 = f8 + (f5 - f8) * f7;
                f4 *= f7;
                f3 *= f7;
            }
            f2 = HudBridge.unit(draggableHud);
        }
        f = f2;
        int n = Math.round(f6 / f);
        int n2 = Math.round(f5 / f);
        glassHudElement.locked(false);
        glassHudElement.size(Math.max(1, Math.round(f4 / f)), Math.max(1, Math.round(f3 / f)));
        glassHudElement.placement().anchor(Anchor.TOP_LEFT).percent(0.0f, 0.0f).offset(n, n2).scale(1.0f).rotation(360.0f);
        PUSHED.put(draggableHud, new int[]{n, n2});
    }

    private static String words(String string) {
        String string2;
        block18: {
            boolean bl;
            block17: {
                String string3;
                block16: {
                    bl = a;
                    string3 = string;
                    if (bl) break block16;
                    if (!string3.equals(string.toUpperCase(Locale.ROOT))) break block17;
                    string3 = string.toLowerCase(Locale.ROOT);
                }
                string = string3;
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < string.length(); ++i) {
                int n;
                StringBuilder stringBuilder2;
                block21: {
                    char c;
                    block22: {
                        block20: {
                            int n2;
                            block19: {
                                string2 = string;
                                if (bl) break block18;
                                c = string2.charAt(i);
                                n2 = i;
                                if (bl) break block19;
                                if (n2 <= 0) break block20;
                                n2 = Character.isUpperCase(c) ? 1 : 0;
                            }
                            if (n2 != 0) {
                                stringBuilder.append(' ');
                            }
                        }
                        stringBuilder2 = stringBuilder;
                        n = i;
                        if (bl) break block21;
                        if (n != 0) break block22;
                        n = Character.toUpperCase(c);
                        break block21;
                    }
                    n = c;
                }
                stringBuilder2.append((char)n);
                if (!bl) continue;
            }
            string2 = stringBuilder.toString();
        }
        return string2;
    }

    private static boolean lambda$mirror$0(DraggableHud draggableHud) {
        return LIVE.contains(draggableHud);
    }

    
    static {
        block12: {
            var5 = new String[2];
            var3_1 = 0;
            var2_2 = "g\\Vmi\u0019\u0005\u0005-\u0019\u001f>t\u0006]wE/aH";
            var4_3 = "g\\Vmi\u0019\u0005\u0005-\u0019\u001f>t\u0006]wE/aH".length();
            var1_4 = 13;
            var0_5 = -1;
lbl7:

            while (true) {
                continue;
                break;
            }
lbl9:

            while (true) {
                var5[var3_1++] = new String(v0).intern();
                if ((var0_5 += var1_4) < var4_3) {
                    var1_4 = var2_2.charAt(var0_5);
                    continue;
                }
                break block12;
                break;
            }
            v1 = ++var0_5;
            v2 = var2_2.substring(v1, v1 + var1_4).toCharArray();
            v0 = v2;
            v3 = v2.length;
            var6_6 = 0;
            do {
                v0 = v0;
                v4 = var6_6;
                v5 = v0[v4];
                switch (var6_6 % 7) {
                    case 0: {
                        v6 = 60;
                        break;
                    }
                    case 1: {
                        v6 = 2;
                        break;
                    }
                    case 2: {
                        v6 = 55;
                        break;
                    }
                    case 3: {
                        v6 = 64;
                        break;
                    }
                    case 4: {
                        v6 = 19;
                        break;
                    }
                    case 5: {
                        v6 = 41;
                        break;
                    }
                    default: {
                        v6 = 40;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        HudBridge.b = var5;
        HudBridge.ELEMENTS = new IdentityHashMap<DraggableHud, GlassHudElement>();
        HudBridge.LIVE = Collections.newSetFromMap(new IdentityHashMap<K, V>());
        HudBridge.PUSHED = new IdentityHashMap<DraggableHud, int[]>();
        HudBridge.BY_ID = new HashMap<String, DraggableHud>();
        HudBridge.OWNERS = new HashMap<String, Module>();
        HudBridge.NAMES = new HashMap<String, String>();
    }
}
