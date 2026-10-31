package dev.quelchi.aurora.glass.hud;

import dev.quelchi.aurora.glass.GlassKit;
import dev.quelchi.aurora.glass.hud.GlassHudElement;
import dev.quelchi.aurora.glass.render.GlassRenderer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;

public final class HudRegistry {
    private final List<GlassHudElement> elements = new ArrayList<GlassHudElement>();
    private final List<GlassHudElement> view = Collections.unmodifiableList(this.elements);
    private final GlassRenderer renderer = new GlassRenderer();
    private static final String[] a;

    public GlassHudElement add(GlassHudElement glassHudElement) {
        HudRegistry hudRegistry;
        block11: {
            GlassHudElement glassHudElement2;
            block9: {
                int n;
                block10: {
                    n = GlassHudElement.a;
                    glassHudElement2 = glassHudElement;
                    if (n != 0) break block9;
                    if (glassHudElement2 != null) break block10;
                    throw new IllegalArgumentException(a[0]);
                }
                hudRegistry = this;
                if (n != 0) break block11;
                glassHudElement2 = hudRegistry.get(glassHudElement.id());
            }
            if (glassHudElement2 != null) {
                throw new IllegalArgumentException(a[1] + String.valueOf(glassHudElement.id()));
            }
            glassHudElement.rememberProfileDefaults();
            GlassKit.hudLayouts().apply(glassHudElement);
            hudRegistry = this;
        }
        hudRegistry.elements.add(glassHudElement);
        glassHudElement.mount();
        return glassHudElement;
    }

    public GlassHudElement get(Identifier identifier) {
        Iterator<GlassHudElement> iterator = this.elements.iterator();
        int n = GlassHudElement.a;
        while (iterator.hasNext()) {
            block6: {
                GlassHudElement glassHudElement;
                block5: {
                    GlassHudElement glassHudElement2 = iterator.next();
                    glassHudElement = glassHudElement2;
                    if (n != 0) break block5;
                    if (!glassHudElement.id().equals((Object)identifier)) break block6;
                    glassHudElement = glassHudElement2;
                }
                return glassHudElement;
            }
            if (n == 0) continue;
        }
        return null;
    }

    public List<GlassHudElement> elements() {
        return this.view;
    }

    public GlassRenderer renderer() {
        return this.renderer;
    }

    public void render(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        block10: {
            int n;
            block12: {
                HudRegistry hudRegistry;
                block11: {
                    block9: {
                        n = GlassHudElement.a;
                        hudRegistry = this;
                        if (n != 0) break block9;
                        if (hudRegistry.elements.isEmpty()) break block10;
                        hudRegistry = this;
                    }
                    if (n != 0) break block11;
                    if (hudRegistry.elements.size() <= 1) break block12;
                    hudRegistry = this;
                }
                hudRegistry.elements.sort(Comparator.comparingInt(GlassHudElement::zOrder));
            }
            MinecraftClient minecraftClient = MinecraftClient.method_1551();
            int n2 = (int)Math.round(minecraftClient.field_1729.method_68879(minecraftClient.method_22683()));
            int n3 = (int)Math.round(minecraftClient.field_1729.method_68883(minecraftClient.method_22683()));
            float f = renderTickCounter.method_60637(false);
            this.renderer.beginFrame(false, n2, n3);
            for (GlassHudElement glassHudElement : this.elements) {
                glassHudElement.render(drawContext, this.renderer, GlassKit.theme(), n2, n3, f);
                if (n == 0) continue;
            }
        }
    }

    public void tick() {
        Iterator<GlassHudElement> iterator = this.elements.iterator();
        int n = GlassHudElement.a;
        while (iterator.hasNext()) {
            GlassHudElement glassHudElement = iterator.next();
            glassHudElement.tick();
            if (n == 0) continue;
        }
    }

    public void invalidateRenderState() {
        this.renderer.invalidate();
        int n = GlassHudElement.a;
        for (GlassHudElement glassHudElement : this.elements) {
            glassHudElement.root().invalidateLayout();
            if (n == 0) continue;
        }
    }

    public void clear() {
        block4: {
            Iterator<GlassHudElement> iterator = this.elements.iterator();
            int n = GlassHudElement.a;
            while (iterator.hasNext()) {
                GlassHudElement glassHudElement = iterator.next();
                glassHudElement.unmount();
                if (n == 0) {
                    if (n == 0) continue;
                    break;
                }
                break block4;
            }
            this.elements.clear();
            this.renderer.invalidate();
        }
    }

    
    static {
        block12: {
            var5 = new String[2];
            var3_1 = 0;
            var2_2 = "r]\u0006i\fS2\u0012SD\u0013h\u0000^'cTCL<yf~UY$";
            var4_3 = "r]\u0006i\fS2\u0012SD\u0013h\u0000^'cTCL<yf~UY$".length();
            var1_4 = 7;
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
                        v6 = 23;
                        break;
                    }
                    case 1: {
                        v6 = 49;
                        break;
                    }
                    case 2: {
                        v6 = 99;
                        break;
                    }
                    case 3: {
                        v6 = 4;
                        break;
                    }
                    case 4: {
                        v6 = 105;
                        break;
                    }
                    case 5: {
                        v6 = 61;
                        break;
                    }
                    default: {
                        v6 = 70;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        HudRegistry.a = var5;
    }
}
