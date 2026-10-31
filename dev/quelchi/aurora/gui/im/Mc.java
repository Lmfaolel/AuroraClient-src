package dev.quelchi.aurora.gui.im;

import dev.quelchi.aurora.gui.UpdatePrompt;
import dev.quelchi.aurora.gui.im.Edit;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.ImScreen;
import dev.quelchi.aurora.gui.im.McKeys;
import dev.quelchi.aurora.gui.im.McOptions;
import dev.quelchi.aurora.gui.im.Menu;
import dev.quelchi.aurora.gui.im.TitleMenu;
import dev.quelchi.aurora.gui.im.Ui;
import imgui.ImDrawList;
import imgui.ImGui;
import java.util.Iterator;
import java.util.function.Predicate;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.screen.option.KeybindsScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.widget.AbstractTextWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.EntryListWidget;
import net.minecraft.client.gui.widget.LockButtonWidget;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.client.input.KeyInput;
import net.minecraft.client.input.MouseInput;

public abstract class Mc
extends ImScreen {
    static final float K = 1.7f;
    static final float H = 34.0f;
    private static final TitleMenu BACK;
    protected final Screen vanilla;
    protected float sw;
    protected float sh;
    private static final String SMALL;
    private static final String[] b;
    private static final long d;

    protected Mc(Screen screen) {
        super(screen.method_25440().getString());
        this.vanilla = screen;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Screen own(Screen screen) {
        block33: {
            Screen screen2;
            block32: {
                boolean bl;
                block30: {
                    block31: {
                        block29: {
                            block27: {
                                block28: {
                                    block25: {
                                        block26: {
                                            bl = ImScreen.a;
                                            screen2 = screen;
                                            if (bl) break block25;
                                            if (Mc.latin(screen2.method_25440().getString())) break block26;
                                            return null;
                                        }
                                        screen2 = screen;
                                    }
                                    if (bl) break block27;
                                    if (!(screen2 instanceof KeybindsScreen)) break block28;
                                    return new McKeys(screen);
                                }
                                screen2 = screen;
                            }
                            if (bl) break block29;
                            if (screen2 instanceof SelectWorldScreen) return null;
                            screen2 = screen;
                        }
                        if (bl) break block30;
                        if (!(screen2 instanceof MultiplayerScreen)) break block31;
                        return null;
                    }
                    screen2 = screen;
                }
                if (bl) break block32;
                if (screen2 instanceof OptionsScreen) break block33;
                screen2 = screen;
            }
            if (!(screen2 instanceof GameOptionsScreen)) {
                return null;
            }
        }
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        screen.method_25423(minecraftClient.method_22683().method_4486(), minecraftClient.method_22683().method_4502());
        if (!McOptions.fits(screen)) return null;
        McOptions mcOptions = new McOptions(screen);
        return mcOptions;
    }

    static boolean latin(String string) {
        int c;
        block7: {
            boolean bl = ImScreen.a;
            for (int i = 0; i < string.length(); ++i) {
                boolean bl2;
                block8: {
                    c = string.charAt(i);
                    if (bl) break block7;
                    if (bl) break block8;
                    if (c <= 255) continue;
                    bl2 = false;
                }
                return bl2;
            }
            c = 1;
        }
        return c != 0;
    }

    static boolean known(Element element) {
        boolean bl;
        block6: {
            block8: {
                block7: {
                    boolean bl2 = ImScreen.a;
                    bl = element instanceof PressableWidget;
                    if (bl2) break block6;
                    if (bl) break block7;
                    bl = element instanceof SliderWidget;
                    if (bl2) break block6;
                    if (!bl) break block8;
                }
                bl = true;
                break block6;
            }
            bl = false;
        }
        return bl;
    }

    static boolean self(ClickableWidget clickableWidget) {
        int n;
        block12: {
            block11: {
                block13: {
                    ClickableWidget clickableWidget2;
                    boolean bl;
                    block10: {
                        bl = ImScreen.a;
                        clickableWidget2 = clickableWidget;
                        if (bl) break block10;
                        if (clickableWidget2 instanceof LockButtonWidget) break block11;
                        clickableWidget2 = clickableWidget;
                    }
                    n = clickableWidget2.method_25368();
                    if (bl) break block12;
                    if (n <= 24) break block13;
                    n = clickableWidget.method_25369().getString().isEmpty() ? 1 : 0;
                    if (bl) break block12;
                    if (n == 0) break block11;
                }
                n = 1;
                break block12;
            }
            n = 0;
        }
        return n != 0;
    }

    private boolean selves(Predicate<ClickableWidget> predicate) {
        boolean bl;
        block16: {
            Iterator iterator = this.vanilla.method_25396().iterator();
            boolean bl2 = ImScreen.a;
            while (iterator.hasNext()) {
                block17: {
                    boolean bl3;
                    block20: {
                        block19: {
                            PressableWidget pressableWidget;
                            PressableWidget pressableWidget2;
                            block18: {
                                Element element;
                                block15: {
                                    Element element2 = (Element)iterator.next();
                                    element = element2;
                                    if (bl2) break block15;
                                    bl = element instanceof PressableWidget;
                                    if (bl2) break block16;
                                    if (!bl) break block17;
                                    element = element2;
                                }
                                pressableWidget2 = (PressableWidget)element;
                                pressableWidget = pressableWidget2;
                                if (bl2) break block18;
                                if (!pressableWidget.field_22764) break block17;
                                pressableWidget = pressableWidget2;
                            }
                            bl3 = Mc.self((ClickableWidget)pressableWidget);
                            if (bl2) break block19;
                            if (!bl3) break block17;
                            bl3 = predicate.test((ClickableWidget)pressableWidget2);
                        }
                        if (bl2) break block20;
                        if (!bl3) break block17;
                        bl3 = true;
                    }
                    return bl3;
                }
                if (!bl2) continue;
            }
            bl = false;
        }
        return bl;
    }

    @Override
    public boolean method_25402(Click click, boolean bl) {
        boolean bl2;
        block6: {
            block8: {
                block7: {
                    boolean bl3 = ImScreen.a;
                    bl2 = this.selves(arg_0 -> Mc.lambda$mouseClicked$0(click, bl, arg_0));
                    if (bl3) break block6;
                    if (bl2) break block7;
                    bl2 = super.method_25402(click, bl);
                    if (bl3) break block6;
                    if (!bl2) break block8;
                }
                bl2 = true;
                break block6;
            }
            bl2 = false;
        }
        return bl2;
    }

    protected void method_25426() {
        this.vanilla.method_25423(this.field_22789, this.field_22790);
    }

    @Override
    protected boolean vanillaSize() {
        return true;
    }

    public void method_25393() {
        this.vanilla.method_25393();
    }

    public void method_25432() {
        this.vanilla.method_25432();
    }

    @Override
    public void method_25419() {
        this.vanilla.method_25419();
    }

    @Override
    public boolean method_25421() {
        return this.vanilla.method_25421();
    }

    @Override
    public boolean method_25404(KeyInput keyInput) {
        int n;
        block8: {
            block9: {
                boolean bl = ImScreen.a;
                n = keyInput.comp_4795();
                if (bl) break block8;
                if (n != 294) break block9;
                n = Im.typing() ? 1 : 0;
                if (bl) break block8;
                if (n != 0) break block9;
                return this.vanilla.method_25404(keyInput);
            }
            n = super.method_25404(keyInput) ? 1 : 0;
        }
        return n != 0;
    }

    public void method_25394(DrawContext drawContext, int n, int n2, float f) {
        block14: {
            DrawContext drawContext2;
            block12: {
                block13: {
                    Mc mc;
                    boolean bl;
                    block10: {
                        block11: {
                            bl = ImScreen.a;
                            mc = this;
                            if (bl) break block10;
                            if (mc.field_22787.field_1687 != null) break block11;
                            Mc.BACK.field_22789 = this.field_22789;
                            Mc.BACK.field_22790 = this.field_22790;
                            BACK.method_25394(drawContext, n, n2, f);
                        }
                        drawContext2 = drawContext;
                        if (bl) break block12;
                        drawContext2.method_25294(0, 0, this.field_22789, this.field_22790, (int)d);
                        mc = this;
                    }
                    if (mc.field_22787.field_1687 == null) break block13;
                    super.method_25394(drawContext, n, n2, f);
                    if (!bl) break block14;
                }
                drawContext2 = drawContext;
            }
            UpdatePrompt.renderOverScreen(drawContext2, this.field_22789);
        }
        this.selves(arg_0 -> Mc.lambda$render$1(drawContext, n, n2, f, arg_0));
    }

    protected <T> T child(Class<T> clazz) {
        Iterator iterator = this.vanilla.method_25396().iterator();
        boolean bl = ImScreen.a;
        while (iterator.hasNext()) {
            block6: {
                Class<T> clazz2;
                block5: {
                    Element element = (Element)iterator.next();
                    clazz2 = clazz;
                    if (bl) break block5;
                    if (!clazz2.isInstance(element)) break block6;
                    clazz2 = clazz.cast(element);
                }
                return (T)clazz2;
            }
            if (!bl) continue;
        }
        return null;
    }

    protected static float x(int n) {
        return (float)n * 1.7f;
    }

    protected static float y(int n) {
        return (float)n * 1.7f;
    }

    static float labelX(float f, float f2) {
        return 1.7f * (float)(Math.round(f / 1.7f) / 2 - Math.round(f2 / 1.7f) / 2);
    }

    static float labelY(float f) {
        return 1.7f * (float)((Math.round(f / 1.7f) - 9) / 2 + 1);
    }

    static Click click(double d) {
        MouseInput mouseInput;
        Click click;
        Click click2 = click;
        Click click3 = click;
        double d2 = d;
        double d3 = 0.0;
        MouseInput mouseInput2 = mouseInput;
        MouseInput mouseInput3 = mouseInput;
        int n = 0;
        int n2 = ImGui.getIO().getKeyShift() ? 1 : 0;
        mouseInput2(n, n2);
        click2(d2, d3, mouseInput3);
        return click3;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected boolean press(String string, ClickableWidget clickableWidget, float f, float f2, float f3) {
        boolean bl = ImScreen.a;
        boolean bl2 = Mc.button(string, clickableWidget.method_25369().getString(), f, f2, f3, clickableWidget.field_22763);
        if (bl) return bl2;
        if (!bl2) return false;
        bl2 = clickableWidget instanceof PressableWidget;
        if (bl) return bl2;
        if (!bl2) return false;
        PressableWidget pressableWidget = (PressableWidget)clickableWidget;
        if (bl) {
            return false;
        }
        this.press(pressableWidget);
        return true;
    }

    protected void control(String string, ClickableWidget clickableWidget, float f, float f2, float f3) {
        this.press(string, clickableWidget, f, f2, f3);
    }

    protected void outside() {
        int n = 0;
        Iterator iterator = this.vanilla.method_25396().iterator();
        boolean bl = ImScreen.a;
        while (iterator.hasNext()) {
            block14: {
                block15: {
                    Element element;
                    block12: {
                        Element element2 = (Element)iterator.next();
                        element = element2;
                        if (bl) break block12;
                        if (!Mc.known(element)) {
                            continue;
                        }
                        element = element2;
                    }
                    ClickableWidget clickableWidget = (ClickableWidget)element;
                    if (bl) break block14;
                    if (!clickableWidget.field_22764) break block15;
                    if (Mc.self(clickableWidget)) break block15;
                    this.control("w" + n, clickableWidget, Mc.x(clickableWidget.method_46426()), Mc.y(clickableWidget.method_46427()), Mc.x(clickableWidget.method_25368()));
                }
                ++n;
            }
            if (!bl) continue;
        }
    }

    protected void begin() {
        Mc mc;
        block10: {
            block11: {
                Mc mc2;
                block8: {
                    block9: {
                        this.sw = ImGui.getIO().getDisplaySizeX();
                        boolean bl = ImScreen.a;
                        this.sh = ImGui.getIO().getDisplaySizeY();
                        mc2 = this;
                        if (bl) break block8;
                        if (mc2.vanilla.field_22789 != this.field_22789) break block9;
                        mc = this;
                        if (bl) break block10;
                        if (mc.vanilla.field_22790 == this.field_22790) break block11;
                    }
                    mc2 = this.vanilla;
                }
                mc2.method_25410(this.field_22789, this.field_22790);
            }
            ImGui.setNextWindowPos((float)0.0f, (float)0.0f);
            mc = this;
        }
        ImGui.setNextWindowSize((float)mc.sw, (float)this.sh);
        ImGui.pushStyleVar((int)2, (float)0.0f, (float)0.0f);
        ImGui.begin((String)b[3], (int)8367);
        ImGui.popStyleVar();
    }

    protected void title() {
        float f;
        float f2;
        String string;
        block13: {
            AbstractTextWidget abstractTextWidget;
            block11: {
                AbstractTextWidget abstractTextWidget2;
                block12: {
                    float f3;
                    boolean bl;
                    block10: {
                        AbstractTextWidget abstractTextWidget3;
                        float f4;
                        block8: {
                            block9: {
                                abstractTextWidget2 = this.child(AbstractTextWidget.class);
                                string = this.vanilla.method_25440().getString();
                                f4 = Ui.width(Im.mc, string);
                                bl = ImScreen.a;
                                abstractTextWidget3 = abstractTextWidget2;
                                if (bl) break block8;
                                if (abstractTextWidget3 != null) break block9;
                                f3 = Mc.x(this.field_22789 / 2) + Mc.labelX(0.0f, f4);
                                break block10;
                            }
                            abstractTextWidget3 = abstractTextWidget2;
                        }
                        f3 = Mc.x(abstractTextWidget3.method_46426()) + Mc.labelX(Mc.x(abstractTextWidget2.method_25368()), f4);
                    }
                    float f5 = f3;
                    f2 = f5;
                    abstractTextWidget = abstractTextWidget2;
                    if (bl) break block11;
                    if (abstractTextWidget != null) break block12;
                    f = Mc.y(12);
                    break block13;
                }
                abstractTextWidget = abstractTextWidget2;
            }
            f = Mc.y(abstractTextWidget.method_46427());
        }
        Ui.mcText(f2, f, Ui.col(Im.INK), string, true);
    }

    protected float band(String string, EntryListWidget<?> entryListWidget) {
        float f = Mc.y(entryListWidget.method_46427());
        float f2 = Mc.y(entryListWidget.method_55443());
        ImDrawList imDrawList = ImGui.getWindowDrawList();
        imDrawList.addLine(0.0f, f, this.sw, f, Ui.col(Im.RIM));
        imDrawList.addLine(0.0f, f2, this.sw, f2, Ui.col(Im.RIM));
        ImGui.pushStyleVar((int)18, (float)4.0f);
        ImGui.pushStyleColor((int)15, (float)1.0f, (float)1.0f, (float)1.0f, (float)0.26f);
        Edit.list(string, 0.0f, f + 1.0f, Mc.x(this.vanilla.field_22789 / 2 + entryListWidget.method_25322() / 2 + 10) + 4.0f, f2 - f - 1.0f);
        return ImGui.getCursorScreenPosY() - 1.0f;
    }

    protected void endList(float f) {
        Edit.endList(0.0f, f - 1.0f);
        ImGui.popStyleColor();
        ImGui.popStyleVar();
    }

    static boolean button(String string, String string2, float f, float f2, float f3, boolean bl) {
        boolean bl2;
        block14: {
            block13: {
                boolean bl3;
                block12: {
                    ImGui.setCursorScreenPos((float)f, (float)f2);
                    bl3 = ImScreen.a;
                    bl2 = ImGui.invisibleButton((String)string, (float)f3, (float)34.0f);
                    if (bl3) break block12;
                    if (!bl2) break block13;
                    bl2 = bl;
                }
                if (bl3) break block14;
                if (!bl2) break block13;
                bl2 = true;
                break block14;
            }
            bl2 = false;
        }
        boolean bl4 = bl2;
        float f4 = bl ? Ui.hot() : 0.0f;
        float f5 = f4;
        float f6 = bl ? 1.0f : 0.4f;
        float f7 = f6;
        ImDrawList imDrawList = ImGui.getWindowDrawList();
        Ui.fill(imDrawList, f, f2, f + f3, f2 + 34.0f, Ui.col(Ui.mix(Im.QUIET, Im.QUIET_HI, f5), f7), 8.0f);
        Ui.rect(imDrawList, f, f2, f + f3, f2 + 34.0f, Ui.wash(0.16f * f7), 8.0f);
        int n = string2.indexOf(b[0]);
        String string3 = n < 0 ? string2 : string2.substring(0, n + 2);
        String string4 = string3;
        String string5 = n < 0 ? "" : string2.substring(n + 2);
        String string6 = string5;
        float f8 = Ui.width(Im.mc, string4);
        float f9 = f8 + Ui.width(Im.mc, string6);
        float f10 = f + Math.max(8.0f, Mc.labelX(f3, f9));
        float f11 = f2 + Mc.labelY(34.0f);
        imDrawList.pushClipRect(f + 6.0f, f2, f + f3 - 6.0f, f2 + 34.0f, true);
        Ui.mcText(f10, f11, Ui.col(Ui.mix(Im.SUB, Im.INK, f5), f7), string4, true);
        Ui.mcText(f10 + f8, f11, ImGui.getColorU32((float)1.0f, (float)1.0f, (float)1.0f, (float)f7), string6, true);
        imDrawList.popClipRect();
        return bl4;
    }

    static float slider(String string, String string2, float f, float f2, float f3, float f4, boolean bl) {
        float f5;
        int n;
        float f6;
        float f7;
        ImDrawList imDrawList;
        float f8;
        float f9;
        block19: {
            block20: {
                float f10;
                boolean bl2;
                block18: {
                    block17: {
                        float f11;
                        float f12;
                        block16: {
                            float f13;
                            block14: {
                                block15: {
                                    bl2 = ImScreen.a;
                                    f13 = f2;
                                    if (bl2) break block14;
                                    ImGui.setCursorScreenPos((float)f13, (float)f3);
                                    ImGui.invisibleButton((String)string, (float)f4, (float)34.0f);
                                    if (!bl) break block15;
                                    f13 = 1.0f;
                                    break block14;
                                }
                                f13 = 0.4f;
                            }
                            f9 = f13;
                            f8 = f2 + Math.max(3.0f, Math.min(f4 - 3.0f, f4 * f));
                            imDrawList = ImGui.getWindowDrawList();
                            Ui.fill(imDrawList, f2, f3, f2 + f4, f3 + 34.0f, Ui.col(Im.QUIET, f9), 8.0f);
                            imDrawList.pushClipRect(f2, f3, f8, f3 + 34.0f, true);
                            Ui.accent(imDrawList, f2, f3, f2 + f4, f3 + 34.0f, Ui.col(Menu.FILL, 0.45f * f9), 8.0f);
                            imDrawList.popClipRect();
                            Ui.rect(imDrawList, f2, f3, f2 + f4, f3 + 34.0f, Ui.wash(0.16f * f9), 8.0f);
                            float f14 = Ui.width(Im.mc, string2);
                            f7 = f2 + Math.max(8.0f, Mc.labelX(f4, f14));
                            f6 = f3 + Mc.labelY(34.0f);
                            f12 = f8;
                            f11 = f7;
                            if (bl2) break block16;
                            if (!(f12 > f11 - 3.0f)) break block17;
                            f12 = f8;
                            f11 = f7 + f14 + 3.0f;
                        }
                        float f15 = f12 - f11;
                        f10 = f15 == 0.0f ? 0 : (f15 < 0.0f ? -1 : 1);
                        if (bl2) break block18;
                        if (f10 >= 0) break block17;
                        f10 = 1.0f;
                        break block18;
                    }
                    f10 = 0.0f;
                }
                float f16 = f10;
                n = ImGui.getColorU32((float)1.0f, (float)1.0f, (float)1.0f, (float)f9);
                f5 = f16;
                if (bl2) break block19;
                if (f5 == false) break block20;
                f5 = Ui.wash(0.25f * f9);
                break block19;
            }
            f5 = n;
        }
        float f17 = f5;
        float f18 = f3 + 17.0f;
        imDrawList.addRectFilledMultiColor(f8 - 1.5f, f3 + 5.0f, f8 + 1.5f, f18 - 3.0f, n, n, (int)f17, (int)f17);
        Ui.fill(imDrawList, f8 - 1.5f, f18 - 3.0f, f8 + 1.5f, f18 + 3.0f, (int)f17);
        imDrawList.addRectFilledMultiColor(f8 - 1.5f, f18 + 3.0f, f8 + 1.5f, f3 + 34.0f - 5.0f, (int)f17, (int)f17, n, n);
        imDrawList.pushClipRect(f2 + 6.0f, f3, f2 + f4 - 6.0f, f3 + 34.0f, true);
        Ui.mcText(f7, f6, Ui.col(Im.INK, f9), string2, true);
        imDrawList.popClipRect();
        return Math.max(0.0f, Math.min(1.0f, (ImGui.getMousePosX() - f2) / f4));
    }

    static void line(float f, float f2, float f3, float[] fArray, String string) {
        ImDrawList imDrawList = ImGui.getWindowDrawList();
        imDrawList.pushClipRect(f, f2 - 6.0f, f3, f2 + 22.0f, true);
        Ui.mcText(f, f2, Ui.col(fArray), Mc.plain(string), false);
        imDrawList.popClipRect();
    }

    static String plain(String string) {
        String string2;
        block5: {
            StringBuilder stringBuilder = new StringBuilder(string.length());
            boolean bl = ImScreen.a;
            int n = 0;
            while (n < string.length()) {
                block6: {
                    block7: {
                        string2 = string;
                        if (bl) break block5;
                        int n2 = Mc.fold(string2.charAt(n));
                        if (bl) break block6;
                        if (n2 < 0) break block7;
                        stringBuilder.append((char)n2);
                    }
                    ++n;
                }
                if (!bl) continue;
            }
            string2 = stringBuilder.toString().strip().replaceAll(b[1], " ");
        }
        return string2;
    }

    static int fold(int n) {
        int n2;
        block16: {
            block17: {
                boolean bl;
                block14: {
                    block15: {
                        int n3;
                        block12: {
                            block13: {
                                bl = ImScreen.a;
                                n3 = n;
                                if (bl) break block12;
                                if (n3 != 32) break block13;
                                n3 = -1;
                                break block12;
                            }
                            n3 = b[2].indexOf(n);
                        }
                        int n4 = n3;
                        n2 = n4;
                        if (bl) break block14;
                        if (n2 < 0) break block15;
                        return 65 + n4;
                    }
                    n2 = n;
                }
                if (bl) break block16;
                if (n2 > 255) break block17;
                n2 = n;
                break block16;
            }
            n2 = -1;
        }
        return n2;
    }

    private static boolean lambda$render$1(DrawContext drawContext, int n, int n2, float f, ClickableWidget clickableWidget) {
        clickableWidget.method_25394(drawContext, n, n2, f);
        return false;
    }

    private static boolean lambda$mouseClicked$0(Click click, boolean bl, ClickableWidget clickableWidget) {
        return clickableWidget.method_25402(click, bl);
    }

    
    static {
        block21: {
            block20: {
                block19: {
                    var7 = new String[5];
                    var5_1 = 0;
                    var4_2 = ".\r\u00054Vq$w\u001a\u1d14\u02b4\u1d47\u1d0d\u1d0d\ua75a\u0257\u0288\u0247\u1d49\u1d03\u0295\u1d67\u0241\u1d1b\u1d35\u01a8\u0288\ua73b\u1d71\u1d29\u1d34\u1d0cc\u0287\u1d28";
                    var6_3 = ".\r\u00054Vq$w\u001a\u1d14\u02b4\u1d47\u1d0d\u1d0d\ua75a\u0257\u0288\u0247\u1d49\u1d03\u0295\u1d67\u0241\u1d1b\u1d35\u01a8\u0288\ua73b\u1d71\u1d29\u1d34\u1d0cc\u0287\u1d28".length();
                    var3_4 = 2;
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
                        var4_2 = "yN\u001a\u1d14\u02b4\u1d47\u1d0d\u1d0d\ua75a\u0257\u0288\u0247\u1d49\u1d03\u0295\u1d67\u0241\u1d1b\u1d35\u01a8\u0288\ua73b\u1d71\u1d29\u1d34\u1d0cc\u0287\u1d28";
                        var6_3 = "yN\u001a\u1d14\u02b4\u1d47\u1d0d\u1d0d\ua75a\u0257\u0288\u0247\u1d49\u1d03\u0295\u1d67\u0241\u1d1b\u1d35\u01a8\u0288\ua73b\u1d71\u1d29\u1d34\u1d0cc\u0287\u1d28".length();
                        var3_4 = 2;
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
                            v10 = 20;
                            break;
                        }
                        case 1: {
                            v10 = 45;
                            break;
                        }
                        case 2: {
                            v10 = 67;
                            break;
                        }
                        case 3: {
                            v10 = 8;
                            break;
                        }
                        case 4: {
                            v10 = 10;
                            break;
                        }
                        case 5: {
                            v10 = 106;
                            break;
                        }
                        default: {
                            v10 = 53;
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
            Mc.b = var7;
            Mc.SMALL = Mc.b[4];
            break block21;
lbl79:

            while (true) {
                continue;
                break;
            }
        }
        var0_7 = 4506565328399352418L;
        while (true)
        Mc.d = -2356448219127298968L ^ var0_7;
        Mc.BACK = new TitleMenu();
    }
}
