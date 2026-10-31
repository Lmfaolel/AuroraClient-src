package dev.quelchi.aurora.module.modules;

import com.google.gson.JsonObject;
import dev.quelchi.aurora.gui.HudStyle;
import dev.quelchi.aurora.gui.HudStyle$Look;
import dev.quelchi.aurora.gui.Round;
import dev.quelchi.aurora.hud.KeystrokesHud;
import dev.quelchi.aurora.input.RawMouse;
import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.DraggableHud;
import dev.quelchi.aurora.module.ExtraHuds;
import dev.quelchi.aurora.module.HudScales;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.KeystrokesModule$Cursor;
import dev.quelchi.aurora.module.modules.KeystrokesModule$Point;
import dev.quelchi.aurora.module.modules.KeystrokesModule$Tail;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.ColorSetting;
import dev.quelchi.aurora.module.setting.EnumSetting;
import dev.quelchi.aurora.module.setting.NumberSetting;
import dev.quelchi.aurora.render.HudElement;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.MathHelper;

public final class KeystrokesModule
extends Module
implements DraggableHud,
ExtraHuds {
    private static KeystrokesModule instance;
    private final BooleanSetting showKeys;
    private final BooleanSetting showMouse;
    private final BooleanSetting cps;
    private final BooleanSetting highlightSelected;
    private final NumberSetting scale;
    private final NumberSetting keySize;
    private final NumberSetting gap;
    private final NumberSetting mouseHeight;
    private final ColorSetting idleBg;
    private final ColorSetting pressedBg;
    private final ColorSetting idleText;
    private final ColorSetting pressedText;
    private final ColorSetting border;
    private final ColorSetting accent;
    private final ColorSetting cpsText;
    private final EnumSetting<HudStyle$Look> panelStyle;
    private final BooleanSetting tracker;
    private final NumberSetting padWidth;
    private final NumberSetting padHeight;
    private static final float GAIN = 0.0012f;
    private static final float HOMING = 0.8f;
    private static final float IDLE = 0.06f;
    private final NumberSetting trailLength;
    private final EnumSetting<KeystrokesModule$Cursor> cursorShape;
    private final NumberSetting cursorSize;
    private final EnumSetting<KeystrokesModule$Tail> trailStyle;
    private final EnumSetting<HudStyle$Look> padStyle;
    private final ColorSetting trailColor;
    private final Deque<KeystrokesModule$Point> points;
    private float posX;
    private float posY;
    private long trackerNanos;
    private float sinceSample;
    private float still;
    private final HudElement trackerElement;
    private int hudX;
    private int hudY;
    private int[] lastBounds;
    private static final String[] b;
    private static final long[] d;
    private static final Integer[] e;

    public static KeystrokesModule get() {
        return instance;
    }

    public KeystrokesModule() {
        String[] stringArray = b;
        super(stringArray[1], stringArray[7], Category.HUD, true);
        this.showKeys = new BooleanSetting(stringArray[5], true);
        this.showMouse = new BooleanSetting(stringArray[28], true);
        this.cps = new BooleanSetting(stringArray[27], true);
        this.highlightSelected = new BooleanSetting(stringArray[34], false);
        this.scale = new NumberSetting(stringArray[11], 0.5, 3.0, 0.9, 0.05);
        this.keySize = new NumberSetting(stringArray[33], 8.0, 40.0, 20.0, 1.0);
        this.gap = new NumberSetting(stringArray[8], 0.0, 10.0, 0.0, 1.0);
        this.mouseHeight = new NumberSetting(stringArray[9], 8.0, 40.0, 20.0, 1.0);
        this.idleBg = new ColorSetting(stringArray[31], KeystrokesModule.a(3296, 8244220944795462611L));
        this.pressedBg = new ColorSetting(stringArray[3], KeystrokesModule.a(16910, 5060699208842290492L));
        this.idleText = new ColorSetting(stringArray[13], -1);
        this.pressedText = new ColorSetting(stringArray[0], -1);
        this.border = new ColorSetting(stringArray[23], 0);
        this.accent = new ColorSetting(stringArray[15], KeystrokesModule.a(17548, 8067756682282574781L));
        this.cpsText = new ColorSetting(stringArray[12], KeystrokesModule.a(29279, 3216159244366615915L));
        this.panelStyle = new EnumSetting<HudStyle$Look>(stringArray[37], HudStyle$Look.Glass);
        this.tracker = new BooleanSetting(stringArray[14], false);
        this.padWidth = new NumberSetting(stringArray[10], 40.0, 300.0, 110.0, 2.0);
        this.padHeight = new NumberSetting(stringArray[18], 30.0, 220.0, 62.0, 2.0);
        this.trailLength = new NumberSetting(stringArray[29], 0.0, 160.0, 60.0, 1.0);
        this.cursorShape = new EnumSetting<KeystrokesModule$Cursor>(stringArray[36], KeystrokesModule$Cursor.Dot);
        this.cursorSize = new NumberSetting(stringArray[35], 2.0, 14.0, 4.0, 1.0);
        this.trailStyle = new EnumSetting<KeystrokesModule$Tail>(stringArray[20], KeystrokesModule$Tail.Line);
        this.padStyle = new EnumSetting<HudStyle$Look>(stringArray[25], HudStyle$Look.Glass);
        this.trailColor = new ColorSetting(stringArray[17], KeystrokesModule.a(23190, 3386829870034828710L));
        this.points = new ArrayDeque<KeystrokesModule$Point>();
        this.trackerElement = new HudElement(stringArray[21], 4, 60);
        this.hudX = KeystrokesModule.a(26146, 7459565864397177109L);
        this.hudY = KeystrokesModule.a(7716, 8177188523156764946L);
        this.lastBounds = new int[]{8, 8, 0, 0};
        instance = this;
        this.addSettings(this.showKeys, this.keySize, this.gap, this.highlightSelected, this.showMouse, this.mouseHeight, this.cps, this.cpsText, this.tracker, this.padWidth, this.padHeight, this.padStyle, this.trailLength, this.trailStyle, this.cursorShape, this.cursorSize, this.trailColor, this.scale, this.panelStyle, this.idleBg, this.pressedBg, this.idleText, this.pressedText, this.border, this.accent);
        this.showKeys.children(this.keySize, this.highlightSelected);
        this.showMouse.children(this.mouseHeight);
        this.cps.children(this.cpsText);
        this.tracker.children(this.padWidth, this.padHeight, this.padStyle, this.trailLength, this.trailStyle, this.cursorShape, this.cursorSize, this.trailColor);
        this.showKeys.tab(stringArray[22]);
        this.gap.tab(stringArray[6]);
        this.showMouse.tab(stringArray[26]);
        this.cps.tab(stringArray[26]);
        this.tracker.tab(stringArray[30]);
        this.padWidth.tab(stringArray[2]);
        this.padHeight.tab(stringArray[2]);
        this.padStyle.tab(stringArray[2]);
        this.trailLength.tab(stringArray[2]);
        this.trailStyle.tab(stringArray[2]);
        this.cursorShape.tab(stringArray[2]);
        this.cursorSize.tab(stringArray[2]);
        this.trailColor.tab(stringArray[2]);
        this.scale.tab(stringArray[24]);
        this.panelStyle.tab(stringArray[32]);
        this.idleBg.tab(stringArray[32]);
        this.pressedBg.tab(stringArray[32]);
        this.idleText.tab(stringArray[32]);
        this.pressedText.tab(stringArray[32]);
        this.border.tab(stringArray[32]);
        this.accent.tab(stringArray[32]);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public void onHudRender(DrawContext drawContext) {
        MinecraftClient minecraftClient;
        MinecraftClient minecraftClient2;
        block7: {
            minecraftClient2 = MinecraftClient.method_1551();
            boolean bl = AmbienceModule.b;
            minecraftClient = minecraftClient2;
            if (bl) break block7;
            if (minecraftClient.field_1724 == null) return;
            minecraftClient = minecraftClient2;
        }
        if (minecraftClient.field_1690.field_1842) {
            return;
        }
        this.lastBounds = KeystrokesHud.draw(drawContext, minecraftClient2, this.hudX, this.hudY);
        this.drawTracker(drawContext);
    }

    private void drawTracker(DrawContext drawContext) {
        block17: {
            KeystrokesModule keystrokesModule;
            boolean bl;
            block15: {
                block16: {
                    bl = AmbienceModule.b;
                    keystrokesModule = this;
                    if (bl) break block15;
                    if (keystrokesModule.tracker.getValue()) break block16;
                    this.trackerElement.clearBounds();
                    return;
                }
                keystrokesModule = this;
            }
            int n = (int)keystrokesModule.padWidth.getValue();
            int n2 = (int)this.padHeight.getValue();
            int n3 = this.trackerElement.getHudX();
            int n4 = this.trackerElement.getHudY();
            this.trackerElement.setBounds(n, n2);
            float f = this.trackerElement.getHudScale();
            drawContext.method_51448().pushMatrix();
            drawContext.method_51448().translate((float)n3, (float)n4);
            drawContext.method_51448().scale(f, f);
            drawContext.method_51448().translate((float)(-n3), (float)(-n4));
            int n5 = this.trailColor.getARGB();
            HudStyle.panel(drawContext, n3, n4, n, n2, n5, this.padStyle.getValue());
            this.advanceTracker();
            int n6 = n3 + n / 2;
            int n7 = n4 + n2 / 2;
            int n8 = Math.max(1, n / 2 - 3);
            int n9 = Math.max(1, n2 / 2 - 3);
            int n10 = (int)this.trailLength.getValue();
            int n11 = (int)this.cursorSize.getValue();
            drawContext.method_44379(n3, n4, n3 + n, n4 + n2);
            int n12 = this.points.size();
            int n13 = 0;
            float f2 = Float.NaN;
            float f3 = Float.NaN;
            int n14 = this.trailStyle.getValue() == KeystrokesModule$Tail.Dots ? 3 : 1;
            int n15 = n14;
            for (KeystrokesModule$Point keystrokesModule$Point : this.points) {
                block20: {
                    float f4;
                    float f5;
                    block21: {
                        int n16;
                        int n17;
                        block19: {
                            int n18;
                            block18: {
                                if (bl) break block17;
                                n17 = n12 - ++n13;
                                n18 = n17;
                                if (bl) break block18;
                                if (n18 >= n10) continue;
                                n16 = n17;
                                if (bl) break block19;
                                n18 = n16 % n15;
                            }
                            if (n18 != 0) continue;
                            n16 = n6;
                        }
                        f5 = (float)n16 + keystrokesModule$Point.x() * (float)n8;
                        f4 = (float)n7 + keystrokesModule$Point.y() * (float)n9;
                        float f6 = (float)n17 / (float)Math.max(1, n10);
                        if (bl) break block20;
                        if (Float.isNaN(f2)) break block21;
                        this.trail(drawContext, f2, f3, f5, f4, f6, keystrokesModule$Point.seed(), n5, n11);
                    }
                    f2 = f5;
                    f3 = f4;
                }
                if (!bl) continue;
            }
            drawContext.method_44380();
            this.cursor(drawContext, Math.round((float)n6 + this.posX * (float)n8), Math.round((float)n7 + this.posY * (float)n9), n11, n5);
            drawContext.method_51448().popMatrix();
        }
    }

    
    private void trail(DrawContext var1_1, float var2_2, float var3_3, float var4_4, float var5_5, float var6_6, float var7_7, int var8_8, int var9_9) {
        var11_10 = (int)((float)(var8_8 >>> 24 & 255) * (1.0f - var6_6));
        var10_11 = AmbienceModule.b;
        v0 = var11_10;
        if (!var10_11) {
            if (v0 <= 3) {
                return;
            }
            v0 = var12_12 = var11_10 << 24 | var8_8 & KeystrokesModule.a(19889, 6884716235643038340L);
        }
        if (var10_11) // goto
        switch (this.trailStyle.getValue().ordinal()) {
            case 0: {
                Round.stroke(var1_1, var2_2, var3_3, var4_4, var5_5, 1, var12_12);
lbl12:

                if (!var10_11) return;
            }
            case 1: {
                Round.stroke(var1_1, var2_2, var3_3, var4_4, var5_5, Math.max(1, Math.round((float)var9_9 * 0.5f * (1.0f - var6_6))), var12_12);
                if (!var10_11) return;
            }
            case 2: {
                var13_13 = Math.max(1, Math.round((float)var9_9 * 0.4f * (1.0f - var6_6)));
                var1_1.method_25294(Math.round(var4_4) - var13_13, Math.round(var5_5) - var13_13, Math.round(var4_4) + var13_13, Math.round(var5_5) + var13_13, var12_12);
                if (!var10_11) return;
            }
            case 3: {
                var13_14 = var7_7 * 3.1415927f * 2.0f;
                var14_15 = var6_6 * (float)var9_9 * 1.6f;
                var15_16 = var4_4 + MathHelper.method_15362((double)var13_14) * var14_15;
                var16_17 = var5_5 + MathHelper.method_15374((double)var13_14) * var14_15;
                Round.stroke(var1_1, var4_4, var5_5, var15_16, var16_17, 1, var12_12);
                return;
            }
        }
    }

    
    private void cursor(DrawContext drawContext, int n, int n2, int n3, int n4) {
        boolean bl = AmbienceModule.b;
        switch (this.cursorShape.getValue().ordinal()) {
            case 0: {
                Round.rect(drawContext, n - n3 / 2, n2 - n3 / 2, n3, n3, n3 / 2, n4);
                if (!bl) return;
            }
            case 1: {
                Round.outline(drawContext, n - n3, n2 - n3, n3 * 2, n3 * 2, n3, n4);
                if (!bl) return;
            }
            case 2: {
                drawContext.method_25294(n - n3, n2, n + n3 + 1, n2 + 1, n4);
                drawContext.method_25294(n, n2 - n3, n + 1, n2 + n3 + 1, n4);
                if (!bl) return;
            }
            case 3: {
                Round.outline(drawContext, n - n3, n2 - n3, n3 * 2, n3 * 2, 1, n4);
                return;
            }
        }
    }

    private void advanceTracker() {
        block24: {
            KeystrokesModule keystrokesModule;
            boolean bl;
            block23: {
                float f;
                block21: {
                    KeystrokesModule keystrokesModule2;
                    block22: {
                        block19: {
                            float f2;
                            block20: {
                                float f3;
                                block25: {
                                    block18: {
                                        float f4;
                                        float f5;
                                        block17: {
                                            long l = System.nanoTime();
                                            bl = AmbienceModule.b;
                                            float f6 = this.trackerNanos == 0L ? 0.0f : Math.min(0.1f, (float)(l - this.trackerNanos) / 1.0E9f);
                                            f2 = f6;
                                            this.trackerNanos = l;
                                            double d = RawMouse.takeX();
                                            double d2 = RawMouse.takeY();
                                            KeystrokesModule keystrokesModule3 = this;
                                            f5 = this.posX;
                                            f4 = (float)d;
                                            if (bl) break block17;
                                            keystrokesModule3.posX = MathHelper.method_15363((float)(f5 + f4 * 0.0012f), (float)-1.0f, (float)1.0f);
                                            this.posY = MathHelper.method_15363((float)(this.posY + (float)d2 * 0.0012f), (float)-1.0f, (float)1.0f);
                                            keystrokesModule3 = this;
                                            if (!(Math.abs(d) + Math.abs(d2) < 0.5)) break block18;
                                            f5 = this.still;
                                            f4 = f2;
                                        }
                                        f3 = f5 + f4;
                                        break block25;
                                    }
                                    f3 = 0.0f;
                                }
                                keystrokesModule3.still = f3;
                                if (bl) break block19;
                                if (!(this.still >= 0.06f)) break block20;
                                float f7 = (float)Math.exp(-0.8f * f2);
                                this.posX *= f7;
                                this.posY *= f7;
                            }
                            KeystrokesModule keystrokesModule4 = this;
                            keystrokesModule2 = keystrokesModule4;
                            f = keystrokesModule4.sinceSample;
                            if (bl) break block21;
                            keystrokesModule2.sinceSample = f + f2;
                        }
                        if (this.sinceSample >= 0.012f) break block22;
                        keystrokesModule = this;
                        if (bl) break block23;
                        if (!keystrokesModule.points.isEmpty()) break block24;
                    }
                    keystrokesModule2 = this;
                    f = 0.0f;
                }
                keystrokesModule2.sinceSample = f;
                keystrokesModule = this;
            }
            keystrokesModule.points.addLast(new KeystrokesModule$Point(this.posX, this.posY, (float)Math.random()));
            while (this.points.size() > 160) {
                this.points.removeFirst();
                if (!bl) continue;
            }
        }
    }

    @Override
    public List<DraggableHud> extraHuds() {
        List<DraggableHud> list;
        block6: {
            block5: {
                KeystrokesModule keystrokesModule;
                block4: {
                    boolean bl = AmbienceModule.b;
                    keystrokesModule = this;
                    if (bl) break block4;
                    if (!keystrokesModule.tracker.getValue()) break block5;
                    keystrokesModule = this;
                }
                list = List.of(keystrokesModule.trackerElement);
                break block6;
            }
            list = List.of();
        }
        return list;
    }

    @Override
    public void writeExtra(JsonObject jsonObject) {
        String[] stringArray = b;
        jsonObject.addProperty(stringArray[19], (Number)this.hudX);
        jsonObject.addProperty(stringArray[16], (Number)this.hudY);
        this.trackerElement.write(jsonObject);
    }

    @Override
    public void readExtra(JsonObject jsonObject) {
        JsonObject jsonObject2;
        block7: {
            block8: {
                boolean bl = AmbienceModule.b;
                jsonObject2 = jsonObject;
                if (bl) break block7;
                if (!jsonObject2.has(b[4])) break block8;
                this.hudX = jsonObject.get(b[19]).getAsInt();
            }
            jsonObject2 = jsonObject;
        }
        if (jsonObject2.has(b[38])) {
            this.hudY = jsonObject.get(b[16]).getAsInt();
        }
        this.trackerElement.read(jsonObject);
    }

    @Override
    public int getHudX() {
        return this.hudX;
    }

    @Override
    public int getHudY() {
        return this.hudY;
    }

    @Override
    public void setHudPos(int n, int n2) {
        this.hudX = n;
        this.hudY = n2;
    }

    @Override
    public int[] getLastBounds() {
        return this.lastBounds;
    }

    public boolean showKeys() {
        return this.showKeys.getValue();
    }

    public boolean showMouse() {
        return this.showMouse.getValue();
    }

    public boolean showCps() {
        return this.cps.getValue();
    }

    public boolean highlightSelected() {
        return this.highlightSelected.getValue();
    }

    public double scale() {
        return this.scale.getValue();
    }

    @Override
    public float getHudScale() {
        return (float)this.scale.getValue();
    }

    @Override
    public void setHudScale(float f) {
        this.scale.setValue(f);
    }

    @Override
    public void resetHudPos() {
        block4: {
            block5: {
                boolean bl = AmbienceModule.b;
                int[] nArray = HudScales.home(this.hudKey());
                if (bl) break block4;
                if (nArray == null) break block5;
                this.setHudPos(nArray[0], nArray[1]);
            }
            this.scale.reset();
            this.setHudHidden(false);
        }
    }

    @Override
    public boolean scalesItself() {
        return true;
    }

    public int keySize() {
        return this.keySize.getValueInt();
    }

    public int gap() {
        return this.gap.getValueInt();
    }

    public int mouseHeight() {
        return this.mouseHeight.getValueInt();
    }

    public int idleBg() {
        return this.idleBg.getARGB();
    }

    public int pressedBg() {
        return this.pressedBg.getARGB();
    }

    public int idleText() {
        return this.idleText.getARGB();
    }

    public int pressedText() {
        return this.pressedText.getARGB();
    }

    public int border() {
        return this.border.getARGB();
    }

    public int accent() {
        return this.accent.getARGB();
    }

    public int cpsText() {
        return this.cpsText.getARGB();
    }

    public HudStyle$Look panelLook() {
        return this.panelStyle.getValue();
    }

    
    static {
        block28: {
            block27: {
                block26: {
                    block25: {
                        var13 = new String[39];
                        var11_1 = 0;
                        var10_2 = "y\u001a\t0Q\u0002\u0003\t\u001c\t;V\nb\r\u00150V\u0015\bB\r\u001f\u0005d\u0007\u00190G\ry\u001a\t0Q\u0002\u0003\t\u000b\u0003/M\u0015\u0004A\u001d\b\u001b\tz\u0000\u00034\u0002,\u0002P\u001b\u0004b\r\u00150\u0015b\r\u0015cC\t\u0003\t\u000b\u0000*A\fGM\u0001\u001f3N\u0006\u001e\u0003n\t\u001c\fd\u0007\u00190GG/L\u0001\u000b+V\ty\t\bcU\u000e\u0003]\u0000\u0005z\u000b\r/G\bj8?cv\u0002\u001f]\n}\r\u00147\u0002\u0004\bE\u0007\u001e\rd\u0007\u00190GG\u0013[\t\u000f(G\u0015\u0006h\u000b\u000f&L\u0013\u0004A\u001d\b\u001a\f}\u001a\r*NG\u0004F\u0004\u00036P\ny\t\bcJ\u0002\u000eN\u0000\u0018\u0004A\u001d\b\u001b\u000b}\u001a\r*NG\u0014]\u0011\u0000&\fD\u0007\u00190G\u0013\u0015H\u000b\u0007&P\u0004b\r\u00150\u0006k\u0007\u001e'G\u0015\u0005z\u001c\u0015/G\ty\t\bcQ\u0013\u001eE\r\u0003j8?\u0003j8?\nz\u0000\u00034\u0002*\b\\\u001b\t\u0005}\u001a\r*N\u0005d\u0007\u00190G\tb\r\u0015cA\b\u000bF\u001a\u0005z\u001c\u0015/G\bb\r\u0015cq\u000e\u001dL\u000ea\u0001\u000b+N\u000e\u0000A\u001cL\u0010N\b\u0013\u000bj\u001d\u001e0M\u0015GZ\u0001\u0016&\u0006j\u001d\u001e0M\u0015";
                        var12_3 = "y\u001a\t0Q\u0002\u0003\t\u001c\t;V\nb\r\u00150V\u0015\bB\r\u001f\u0005d\u0007\u00190G\ry\u001a\t0Q\u0002\u0003\t\u000b\u0003/M\u0015\u0004A\u001d\b\u001b\tz\u0000\u00034\u0002,\u0002P\u001b\u0004b\r\u00150\u0015b\r\u0015cC\t\u0003\t\u000b\u0000*A\fGM\u0001\u001f3N\u0006\u001e\u0003n\t\u001c\fd\u0007\u00190GG/L\u0001\u000b+V\ty\t\bcU\u000e\u0003]\u0000\u0005z\u000b\r/G\bj8?cv\u0002\u001f]\n}\r\u00147\u0002\u0004\bE\u0007\u001e\rd\u0007\u00190GG\u0013[\t\u000f(G\u0015\u0006h\u000b\u000f&L\u0013\u0004A\u001d\b\u001a\f}\u001a\r*NG\u0004F\u0004\u00036P\ny\t\bcJ\u0002\u000eN\u0000\u0018\u0004A\u001d\b\u001b\u000b}\u001a\r*NG\u0014]\u0011\u0000&\fD\u0007\u00190G\u0013\u0015H\u000b\u0007&P\u0004b\r\u00150\u0006k\u0007\u001e'G\u0015\u0005z\u001c\u0015/G\ty\t\bcQ\u0013\u001eE\r\u0003j8?\u0003j8?\nz\u0000\u00034\u0002*\b\\\u001b\t\u0005}\u001a\r*N\u0005d\u0007\u00190G\tb\r\u0015cA\b\u000bF\u001a\u0005z\u001c\u0015/G\bb\r\u0015cq\u000e\u001dL\u000ea\u0001\u000b+N\u000e\u0000A\u001cL\u0010N\b\u0013\u000bj\u001d\u001e0M\u0015GZ\u0001\u0016&\u0006j\u001d\u001e0M\u0015".length();
                        var9_4 = 12;
                        var8_5 = -1;
lbl7:

                        while (true) {
                            v0 = ++var8_5;
                            v1 = var10_2.substring(v0, v0 + var9_4);
                            v2 = -1;
                            break block25;
                            break;
                        }
lbl12:

                        while (true) {
                            var13[var11_1++] = v3.intern();
                            if ((var8_5 += var9_4) < var12_3) {
                                var9_4 = var10_2.charAt(var8_5);
                                continue;
                            }
                            var10_2 = "y\t\u0002&NG\u0014]\u0011\u0000&\u0004A\u001d\b\u001a";
                            var12_3 = "y\t\u0002&NG\u0014]\u0011\u0000&\u0004A\u001d\b\u001a".length();
                            var9_4 = 11;
                            var8_5 = -1;
lbl21:

                            while (true) {
                                v4 = ++var8_5;
                                v1 = var10_2.substring(v4, v4 + var9_4);
                                v2 = 0;
                                break block25;
                                break;
                            }
                            break;
                        }
lbl26:

                        while (true) {
                            var13[var11_1++] = v3.intern();
                            if ((var8_5 += var9_4) < var12_3) {
                                var9_4 = var10_2.charAt(var8_5);
                                continue;
                            }
                            break block26;
                            break;
                        }
                    }
                    v5 = v1.toCharArray();
                    v6 = v5;
                    v7 = v5.length;
                    var14_6 = 0;
                    do {
                        v6 = v6;
                        v8 = var14_6;
                        v9 = v6[v8];
                        switch (var14_6 % 7) {
                            case 0: {
                                v10 = 41;
                                break;
                            }
                            case 1: {
                                v10 = 104;
                                break;
                            }
                            case 2: {
                                v10 = 108;
                                break;
                            }
                            case 3: {
                                v10 = 67;
                                break;
                            }
                            case 4: {
                                v10 = 34;
                                break;
                            }
                            case 5: {
                                v10 = 103;
                                break;
                            }
                            default: {
                                v10 = 103;
                            }
                        }
                        v6[v8] = (char)(v9 ^ v10);
                        ++var14_6;
lbl65:

                        v7 = v7;
                    } while (v7 > var14_6);
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
                KeystrokesModule.b = var13;
                var0_7 = 8624312167647849465L;
                var6_8 = new long[8];
                var3_9 = 0;
                var4_10 = "\u00d3\u00e1_\u00cc\u0094\u009b~\u0082\u00e0'&\u00dd\u001a\u0081\u008a&\u00adG>C\u0086\u00bc\u00aa\u00e4}\u0016\u009e\u00e60\u0092R\u00e3!K*f\u00c2Q\n\u0003\u00114\u008c\u001fyh\u00b8\u00a0";
                var5_11 = "\u00d3\u00e1_\u00cc\u0094\u009b~\u0082\u00e0'&\u00dd\u001a\u0081\u008a&\u00adG>C\u0086\u00bc\u00aa\u00e4}\u0016\u009e\u00e60\u0092R\u00e3!K*f\u00c2Q\n\u0003\u00114\u008c\u001fyh\u00b8\u00a0".length();
                var2_12 = 0;
                while (true) {
                    var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
                    v11 = var6_8;
                    v12 = var3_9++;
                    v13 = ((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L;
                    v14 = -1;
                    break block27;
                    break;
                }
lbl89:

                while (true) {
                    v11[v12] = v15;
                    if (var2_12 < var5_11) continue;
                    var4_10 = "\u0001yUg\u0010\u001b?\u00d5\u00e8\u008c\u0080\u0094lI\u00e1\f";
                    var5_11 = "\u0001yUg\u0010\u001b?\u00d5\u00e8\u008c\u0080\u0094lI\u00e1\f".length();
                    var2_12 = 0;
                    while (true) {
                        var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
                        v11 = var6_8;
                        v12 = var3_9++;
                        v13 = ((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L;
                        v14 = 0;
                        break block27;
                        break;
                    }
                    break;
                }
lbl102:

                while (true) {
                    v11[v12] = v15;
                    if (var2_12 < var5_11) continue;
                    break block28;
                    break;
                }
            }
            v15 = v13 ^ var0_7;
            switch (v14) {
                default: {
                    continue;
                }
                case 0:
lbl113:

                continue;
            }
        }
        KeystrokesModule.d = var6_8;
        KeystrokesModule.e = new Integer[8];
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6735;
        if (e[n2] == null) {
            KeystrokesModule.e[n2] = (int)(d[n2] ^ l);
        }
        return e[n2];
    }
}
