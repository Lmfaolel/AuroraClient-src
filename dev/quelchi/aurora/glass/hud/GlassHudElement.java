package dev.quelchi.aurora.glass.hud;

import dev.quelchi.aurora.glass.hud.GlassHudElement$PlacementSnapshot;
import dev.quelchi.aurora.glass.hud.HudPlacement;
import dev.quelchi.aurora.glass.render.GlassRenderer;
import dev.quelchi.aurora.glass.style.LiquidGlassTheme;
import dev.quelchi.aurora.glass.ui.UiBounds;
import dev.quelchi.aurora.glass.ui.UiComponent;
import dev.quelchi.aurora.glass.ui.UiContext;
import java.util.function.BooleanSupplier;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.joml.Matrix3x2fStack;

public final class GlassHudElement {
    private final Identifier id;
    private final UiComponent root;
    private final HudPlacement placement;
    private int width;
    private int height;
    private int zOrder;
    private boolean locked;
    private BooleanSupplier visible;
    private GlassHudElement$PlacementSnapshot defaults;
    public static int a;
    private static final String b;

    public GlassHudElement(Identifier identifier, UiComponent uiComponent, int n, int n2) {
        block7: {
            block6: {
                int n3 = a;
                this.visible = GlassHudElement::lambda$new$0;
                if (identifier == null || uiComponent == null) break block6;
                this.id = identifier;
                this.root = uiComponent;
                this.width = Math.max(1, n);
                this.height = Math.max(1, n2);
                this.placement = new HudPlacement();
                if (n3 == 0) break block7;
            }
            throw new IllegalArgumentException(b);
        }
    }

    public Identifier id() {
        return this.id;
    }

    public UiComponent root() {
        return this.root;
    }

    public HudPlacement placement() {
        return this.placement;
    }

    public int width() {
        return this.width;
    }

    public int height() {
        return this.height;
    }

    public GlassHudElement size(int n, int n2) {
        this.width = Math.max(1, n);
        this.height = Math.max(1, n2);
        this.root.invalidateLayout();
        return this;
    }

    public int zOrder() {
        return this.zOrder;
    }

    public boolean locked() {
        return this.locked;
    }

    public GlassHudElement locked(boolean bl) {
        this.locked = bl;
        return this;
    }

    public GlassHudElement visibleWhen(BooleanSupplier booleanSupplier) {
        BooleanSupplier booleanSupplier2;
        block2: {
            block3: {
                int n = a;
                GlassHudElement glassHudElement = this;
                booleanSupplier2 = booleanSupplier;
                if (n != 0) break block2;
                if (booleanSupplier2 != null) break block3;
                booleanSupplier2 = GlassHudElement::lambda$visibleWhen$1;
                break block2;
            }
            booleanSupplier2 = booleanSupplier;
        }
        glassHudElement.visible = booleanSupplier2;
        return this;
    }

    boolean visible() {
        return this.visible.getAsBoolean();
    }

    void rememberProfileDefaults() {
        this.defaults = new GlassHudElement$PlacementSnapshot(this.placement.anchor(), this.placement.percentX(), this.placement.percentY(), this.placement.offsetX(), this.placement.offsetY(), this.placement.scale(), this.placement.rotation(), this.width, this.height, this.locked);
    }

    void render(DrawContext drawContext, GlassRenderer glassRenderer, LiquidGlassTheme liquidGlassTheme, int n, int n2, float f) {
        block13: {
            Matrix3x2fStack matrix3x2fStack;
            block16: {
                int n3;
                int n4;
                block17: {
                    float f2;
                    UiBounds uiBounds;
                    int n5;
                    block15: {
                        GlassHudElement glassHudElement;
                        block14: {
                            GlassHudElement glassHudElement2;
                            block12: {
                                n5 = a;
                                glassHudElement2 = this;
                                if (n5 != 0) break block12;
                                if (!glassHudElement2.visible()) break block13;
                                glassHudElement2 = this;
                            }
                            uiBounds = glassHudElement2.placement.bounds(drawContext.method_51421(), drawContext.method_51443(), this.width, this.height);
                            f2 = this.placement.scale();
                            n4 = Math.round((float)(n - uiBounds.x()) / f2);
                            n3 = Math.round((float)(n2 - uiBounds.y()) / f2);
                            glassHudElement = this;
                            if (n5 != 0) break block14;
                            if (!glassHudElement.root.layoutDirty()) break block15;
                            glassHudElement = this;
                        }
                        glassHudElement.root.layout(new UiBounds(0, 0, this.width, this.height));
                    }
                    matrix3x2fStack = drawContext.method_51448();
                    matrix3x2fStack.pushMatrix();
                    matrix3x2fStack.translate((float)uiBounds.x(), (float)uiBounds.y());
                    matrix3x2fStack.scale(f2, f2);
                    float f3 = this.placement.rotation();
                    if (n5 != 0) break block16;
                    if (f3 == 0.0f) break block17;
                    matrix3x2fStack.translate((float)this.width / 2.0f, (float)this.height / 2.0f);
                    matrix3x2fStack.rotate((float)Math.toRadians(f3));
                    matrix3x2fStack.translate((float)(-this.width) / 2.0f, (float)(-this.height) / 2.0f);
                }
                this.root.render(new UiContext(drawContext, glassRenderer, liquidGlassTheme, n4, n3, f, Util.method_658()));
            }
            matrix3x2fStack.popMatrix();
        }
    }

    void tick() {
        block5: {
            GlassHudElement glassHudElement;
            block4: {
                int n = a;
                glassHudElement = this;
                if (n != 0) break block4;
                if (!glassHudElement.visible()) break block5;
                glassHudElement = this;
            }
            glassHudElement.root.tick();
        }
    }

    void mount() {
        this.root.mount();
    }

    void unmount() {
        this.root.unmount();
    }

    private static boolean lambda$visibleWhen$1() {
        return true;
    }

    private static boolean lambda$new$0() {
        return true;
    }

    
    static {
        char[] cArray;
        block11: {
            int n;
            int n2;
            block10: {
                char[] cArray2 = "A4p!'OK".toCharArray();
                cArray = cArray2;
                n2 = cArray2.length;
                n = 0;
                if (!true) break block10;
                n2 = n2;
                if (n2 <= n) break block11;
            }
            do {
                cArray = cArray;
                int n3 = n;
                char c = cArray[n3];
                cArray[n3] = (char)(c ^ (switch (n % 7) {
                    case 0 -> 40;
                    case 1 -> 80;
                    case 2 -> 95;
                    case 3 -> 83;
                    case 4 -> 72;
                    case 5 -> 32;
                    default -> 63;
                }));
                ++n;
                n2 = n2;
            } while (n2 > n);
        }
        b = new String(cArray).intern();
    }
}
