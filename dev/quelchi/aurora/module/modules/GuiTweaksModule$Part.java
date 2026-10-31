package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.module.DraggableHud;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.GuiTweaksModule;
import dev.quelchi.aurora.module.modules.GuiTweaksModule$Key;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import net.minecraft.client.gui.DrawContext;

public final class GuiTweaksModule$Part
implements DraggableHud {
    public final GuiTweaksModule$Key key;
    private int dx;
    private int dy;
    final BooleanSetting shown;
    private int anchorX;
    private int anchorY;
    private int width;
    private int height;
    private boolean nested;
    private boolean drawnThisSession;
    final GuiTweaksModule this$0;
    private static final String a;

    GuiTweaksModule$Part(GuiTweaksModule guiTweaksModule, GuiTweaksModule$Key guiTweaksModule$Key) {
        this.this$0 = guiTweaksModule;
        this.key = guiTweaksModule$Key;
        this.width = guiTweaksModule$Key.defaultWidth;
        this.height = guiTweaksModule$Key.defaultHeight;
        this.shown = new BooleanSetting(guiTweaksModule$Key.label, true);
    }

    public void reportAnchor(int n, int n2, int n3, int n4) {
        int n5;
        block7: {
            block8: {
                this.anchorX = n;
                this.anchorY = n2;
                boolean bl = AmbienceModule.b;
                n5 = n3;
                if (bl) break block7;
                if (n5 <= 0) break block8;
                this.width = n3;
            }
            n5 = n4;
        }
        if (n5 > 0) {
            this.height = n4;
        }
        this.drawnThisSession = true;
    }

    public boolean hidden() {
        boolean bl;
        block2: {
            block3: {
                boolean bl2 = AmbienceModule.b;
                bl = this.shown.getValue();
                if (bl2) break block2;
                if (bl) break block3;
                bl = true;
                break block2;
            }
            bl = false;
        }
        return bl;
    }

    public void setHidden(boolean bl) {
        boolean bl2;
        BooleanSetting booleanSetting;
        block2: {
            block3: {
                boolean bl3 = AmbienceModule.b;
                booleanSetting = this.shown;
                bl2 = bl;
                if (bl3) break block2;
                if (bl2) break block3;
                bl2 = true;
                break block2;
            }
            bl2 = false;
        }
        booleanSetting.setValue(bl2);
    }

    public boolean isDefault() {
        boolean bl;
        block13: {
            block11: {
                GuiTweaksModule$Part guiTweaksModule$Part;
                boolean bl2;
                block12: {
                    block10: {
                        bl2 = AmbienceModule.b;
                        guiTweaksModule$Part = this;
                        if (bl2) break block10;
                        if (guiTweaksModule$Part.dx != 0) break block11;
                        guiTweaksModule$Part = this;
                    }
                    if (bl2) break block12;
                    if (guiTweaksModule$Part.dy != 0) break block11;
                    guiTweaksModule$Part = this;
                }
                float f = guiTweaksModule$Part.getHudScale() - 1.0f;
                bl = f == 0.0f ? 0 : (f > 0.0f ? 1 : -1);
                if (bl2) break block13;
                if (bl) break block11;
                bl = true;
                break block13;
            }
            bl = false;
        }
        return bl;
    }

    public void applyTransform(DrawContext drawContext) {
        float f = this.getHudScale();
        drawContext.method_51448().translate((float)(this.anchorX + this.dx), (float)(this.anchorY + this.dy));
        drawContext.method_51448().scale(f, f);
        drawContext.method_51448().translate((float)(-this.anchorX), (float)(-this.anchorY));
    }

    public int toLocalX(int n) {
        return (int)Math.floor((float)this.anchorX + (float)(n - this.anchorX - this.dx) / this.getHudScale());
    }

    public int toLocalY(int n) {
        return (int)Math.floor((float)this.anchorY + (float)(n - this.anchorY - this.dy) / this.getHudScale());
    }

    private GuiTweaksModule$Part cluster() {
        GuiTweaksModule$Part guiTweaksModule$Part;
        block4: {
            block5: {
                boolean bl = AmbienceModule.b;
                guiTweaksModule$Part = this;
                if (bl) break block4;
                if (!guiTweaksModule$Part.nested) break block5;
                guiTweaksModule$Part = GuiTweaksModule.partOrNull(GuiTweaksModule$Key.SURVIVAL);
                break block4;
            }
            guiTweaksModule$Part = null;
        }
        return guiTweaksModule$Part;
    }

    private float clusterScale() {
        float f;
        block6: {
            GuiTweaksModule$Part guiTweaksModule$Part;
            block4: {
                GuiTweaksModule$Part guiTweaksModule$Part2;
                block5: {
                    guiTweaksModule$Part2 = this.cluster();
                    boolean bl = AmbienceModule.b;
                    guiTweaksModule$Part = guiTweaksModule$Part2;
                    if (bl) break block4;
                    if (guiTweaksModule$Part != null) break block5;
                    f = 1.0f;
                    break block6;
                }
                guiTweaksModule$Part = guiTweaksModule$Part2;
            }
            f = guiTweaksModule$Part.getHudScale();
        }
        return f;
    }

    @Override
    public float getHudScale() {
        float f;
        block6: {
            GuiTweaksModule$Part guiTweaksModule$Part;
            block4: {
                block5: {
                    boolean bl = AmbienceModule.b;
                    guiTweaksModule$Part = this;
                    if (bl) break block4;
                    if (guiTweaksModule$Part.key != GuiTweaksModule$Key.SCOREBOARD) break block5;
                    f = this.this$0.scoreboardScale.getValueFloat();
                    break block6;
                }
                guiTweaksModule$Part = this;
            }
            f = DraggableHud.super.getHudScale();
        }
        return f;
    }

    @Override
    public void setHudScale(float f) {
        block8: {
            GuiTweaksModule$Part guiTweaksModule$Part;
            block6: {
                block7: {
                    boolean bl = AmbienceModule.b;
                    guiTweaksModule$Part = this;
                    if (bl) break block6;
                    if (guiTweaksModule$Part.key != GuiTweaksModule$Key.SCOREBOARD) break block7;
                    this.this$0.scoreboardScale.setValue(f);
                    if (!bl) break block8;
                }
                guiTweaksModule$Part = this;
            }
            DraggableHud.super.setHudScale(f);
        }
    }

    @Override
    public boolean hudHidden() {
        return this.hidden();
    }

    @Override
    public void setHudHidden(boolean bl) {
        this.setHidden(bl);
    }

    @Override
    public boolean hudInGuiSpace() {
        return true;
    }

    @Override
    public void resetHudPos() {
        this.dx = 0;
        this.dy = 0;
        this.setHudScale(1.0f);
        this.setHudHidden(false);
    }

    @Override
    public int getHudX() {
        int n;
        block6: {
            GuiTweaksModule$Part guiTweaksModule$Part;
            GuiTweaksModule$Part guiTweaksModule$Part2;
            block4: {
                block5: {
                    guiTweaksModule$Part2 = this.cluster();
                    boolean bl = AmbienceModule.b;
                    guiTweaksModule$Part = guiTweaksModule$Part2;
                    if (bl) break block4;
                    if (guiTweaksModule$Part != null) break block5;
                    n = this.anchorX + this.dx;
                    break block6;
                }
                guiTweaksModule$Part = guiTweaksModule$Part2;
            }
            n = guiTweaksModule$Part.getHudX() + Math.round((float)(this.anchorX + this.dx - guiTweaksModule$Part2.anchorX) * guiTweaksModule$Part2.getHudScale());
        }
        return n;
    }

    @Override
    public int getHudY() {
        int n;
        block6: {
            GuiTweaksModule$Part guiTweaksModule$Part;
            GuiTweaksModule$Part guiTweaksModule$Part2;
            block4: {
                block5: {
                    guiTweaksModule$Part2 = this.cluster();
                    boolean bl = AmbienceModule.b;
                    guiTweaksModule$Part = guiTweaksModule$Part2;
                    if (bl) break block4;
                    if (guiTweaksModule$Part != null) break block5;
                    n = this.anchorY + this.dy;
                    break block6;
                }
                guiTweaksModule$Part = guiTweaksModule$Part2;
            }
            n = guiTweaksModule$Part.getHudY() + Math.round((float)(this.anchorY + this.dy - guiTweaksModule$Part2.anchorY) * guiTweaksModule$Part2.getHudScale());
        }
        return n;
    }

    @Override
    public void setHudPos(int n, int n2) {
        GuiTweaksModule$Part guiTweaksModule$Part;
        block6: {
            block5: {
                boolean bl = AmbienceModule.b;
                if (!GuiTweaksModule.CLUSTER_CHILDREN.contains((Object)this.key)) break block5;
                guiTweaksModule$Part = GuiTweaksModule.partOrNull(GuiTweaksModule$Key.SURVIVAL);
                if (bl) break block6;
                GuiTweaksModule$Part guiTweaksModule$Part2 = guiTweaksModule$Part;
                if (guiTweaksModule$Part2 != null) {
                    guiTweaksModule$Part2.dx += n - this.getHudX();
                    guiTweaksModule$Part2.dy += n2 - this.getHudY();
                    return;
                }
            }
            guiTweaksModule$Part = this;
        }
        float f = guiTweaksModule$Part.clusterScale();
        this.dx += Math.round((float)(n - this.getHudX()) / f);
        this.dy += Math.round((float)(n2 - this.getHudY()) / f);
    }

    @Override
    public int[] getLastBounds() {
        float f = this.clusterScale();
        return new int[]{this.getHudX(), this.getHudY(), Math.round((float)this.width * f), Math.round((float)this.height * f)};
    }

    @Override
    public String hudKey() {
        return a + this.key.name();
    }

    @Override
    public boolean scalesItself() {
        return true;
    }

    
    static {
        char[] cArray;
        block11: {
            int n;
            int n2;
            block10: {
                char[] cArray2 = "\u0011)Ss\u0016\u0019I=/\u0014".toCharArray();
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
                    case 0 -> 86;
                    case 1 -> 92;
                    case 2 -> 58;
                    case 3 -> 39;
                    case 4 -> 97;
                    case 5 -> 124;
                    default -> 40;
                }));
                ++n;
                n2 = n2;
            } while (n2 > n);
        }
        a = new String(cArray).intern();
    }
}
