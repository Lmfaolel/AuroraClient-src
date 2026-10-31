package dev.quelchi.aurora.gui.im;

import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.ImScreen;
import dev.quelchi.aurora.gui.im.Mc;
import dev.quelchi.aurora.gui.im.Menu;
import dev.quelchi.aurora.gui.im.Ui;
import imgui.ImDrawList;
import imgui.ImGui;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.ParentElement;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.AbstractTextWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.ElementListWidget;
import net.minecraft.client.gui.widget.EntryListWidget;
import net.minecraft.client.gui.widget.LockButtonWidget;
import net.minecraft.client.gui.widget.OptionListWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.client.input.AbstractInput;
import net.minecraft.client.option.SimpleOption;

public final class McOptions
extends Mc {
    private static final Field VALUE;
    private static final String c;

    public McOptions(Screen screen) {
        super(screen);
    }

    private static Field value() {
        for (Field field : SliderWidget.class.getDeclaredFields()) {
            if (field.getType() != Double.TYPE || Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            field.setAccessible(true);
            return field;
        }
        throw new IllegalStateException(c);
    }

    
    static boolean fits(Screen screen) {
        Iterator iterator = screen.method_25396().iterator();
        boolean bl = ImScreen.a;
        block4: do {
            Element element;
            if (!iterator.hasNext()) return true;
            Element element2 = element = (Element)iterator.next();
            block5: while (!bl) {
                boolean bl2 = element2 instanceof OptionListWidget;
                if (bl) return bl2;
                if (bl2) {
                    OptionListWidget optionListWidget = (OptionListWidget)element;
                    block6: for (Element element3 : optionListWidget.method_25396()) {
                        ParentElement parentElement = (ParentElement)element3;
                        if (bl) continue block5;
                        for (Element element4 : parentElement.method_25396()) {
                            if (bl) continue block6;
                            boolean bl3 = McOptions.fits(element4);
                            if (bl) return bl3;
                            if (!bl3) {
                                return false;
                            }
                            if (!bl) continue;
                        }
                        if (!bl) continue;
                    }
                    if (!bl) continue block4;
                }
                element2 = element;
                break;
            }
            boolean bl4 = McOptions.fits(element2);
            if (bl) return bl4;
            if (bl4) continue;
            return false;
        } while (!bl);
        return true;
    }

    private static boolean fits(Element element) {
        boolean bl;
        block10: {
            block13: {
                block11: {
                    Element element2;
                    boolean bl2;
                    block12: {
                        bl2 = ImScreen.a;
                        bl = element instanceof AbstractTextWidget;
                        if (bl2) break block10;
                        if (bl) break block11;
                        element2 = element;
                        if (bl2) break block12;
                        if (!McOptions.known(element2)) break block13;
                        element2 = element;
                    }
                    bl = McOptions.latin(((ClickableWidget)element2).method_25369().getString());
                    if (bl2) break block10;
                    if (!bl) break block13;
                }
                bl = true;
                break block10;
            }
            bl = false;
        }
        return bl;
    }

    @Override
    protected void draw() {
        block4: {
            block5: {
                this.begin();
                this.title();
                boolean bl = ImScreen.a;
                OptionListWidget optionListWidget = this.child(OptionListWidget.class);
                if (bl) break block4;
                if (optionListWidget == null) break block5;
                this.rows(optionListWidget);
            }
            this.outside();
            ImGui.end();
        }
    }

    
    private void rows(OptionListWidget optionListWidget) {
        block8: {
            float f;
            float f2 = f = this.band(this.vanilla.method_25440().getString(), (EntryListWidget<?>)optionListWidget);
            boolean bl = ImScreen.a;
            int n = this.vanilla.field_22789 / 2 - 155;
            int n2 = 0;
            for (Object object2 : optionListWidget.method_25396()) {
                ElementListWidget.Entry entry;
                block4: while (true) {
                    entry = (ElementListWidget.Entry)object2;
                    if (bl) break block8;
                    int n3 = 0;
                    for (Object object2 : entry.method_25396()) {
                        block11: {
                            int n4;
                            ClickableWidget clickableWidget;
                            block10: {
                                block9: {
                                    if (bl) continue block4;
                                    Object object3 = object2;
                                    clickableWidget = (ClickableWidget)object3;
                                    n4 = n + n3 * 160;
                                    if (bl) break block9;
                                    if (!(clickableWidget instanceof AbstractTextWidget)) break block10;
                                    Ui.mcText(McOptions.x(n) + 2.0f, f + (float)(entry.method_46427() + entry.method_25364() - optionListWidget.method_46427()) * 1.7f - 20.0f, Ui.col(Im.MUTE), clickableWidget.method_25369().getString(), false);
                                }
                                if (!bl) break block11;
                            }
                            this.control("o" + n2 + "_" + n3, clickableWidget, McOptions.x(n4), f + McOptions.y(entry.method_73382() - optionListWidget.method_46427()), McOptions.x(clickableWidget.method_25368()));
                        }
                        ++n3;
                        if (!bl) continue;
                    }
                    break;
                }
                f2 = f + (float)(entry.method_46427() + entry.method_25364() - optionListWidget.method_46427()) * 1.7f;
                ++n2;
                if (!bl) continue;
            }
            this.endList(f2 + 6.0f);
        }
    }

    
    @Override
    protected void control(String string, ClickableWidget clickableWidget, float f, float f2, float f3) {
        block9: {
            McOptions mcOptions;
            block12: {
                boolean bl;
                block10: {
                    boolean bl2;
                    block11: {
                        ClickableWidget clickableWidget2;
                        block7: {
                            block8: {
                                bl2 = ImScreen.a;
                                clickableWidget2 = clickableWidget;
                                if (bl2) break block7;
                                if (!(clickableWidget2 instanceof LockButtonWidget)) break block8;
                                LockButtonWidget lockButtonWidget = (LockButtonWidget)clickableWidget;
                                this.lock(string, lockButtonWidget, f, f2);
                                if (!bl2) break block9;
                            }
                            clickableWidget2 = clickableWidget;
                        }
                        bl = clickableWidget2 instanceof SliderWidget;
                        if (bl2) break block10;
                        if (!bl) break block11;
                        SliderWidget sliderWidget = (SliderWidget)clickableWidget;
                        this.drag(string, sliderWidget, f, f2, f3);
                        if (!bl2) break block9;
                    }
                    mcOptions = this;
                    if (bl2) break block12;
                    bl = mcOptions.press(string, clickableWidget, f, f2, f3);
                }
                if (!bl) break block9;
                mcOptions = this;
            }
            mcOptions.refresh();
        }
    }

    private void drag(String string, SliderWidget sliderWidget, float f, float f2, float f3) {
        block17: {
            boolean bl;
            block15: {
                boolean bl2;
                block16: {
                    block13: {
                        block14: {
                            float f4;
                            block11: {
                                block12: {
                                    float f5;
                                    bl2 = ImScreen.a;
                                    try {
                                        f5 = (float)VALUE.getDouble(sliderWidget);
                                    }
                                    catch (IllegalAccessException illegalAccessException) {
                                        throw new IllegalStateException(illegalAccessException);
                                    }
                                    f4 = McOptions.slider(string, sliderWidget.method_25369().getString(), f5, f, f2, f3, sliderWidget.field_22763);
                                    bl = sliderWidget.field_22763;
                                    if (bl2) break block11;
                                    if (bl) break block12;
                                    return;
                                }
                                bl = ImGui.isItemActive();
                            }
                            if (bl2) break block13;
                            if (!bl) break block14;
                            int n = sliderWidget.method_46426();
                            int n2 = sliderWidget.method_46427();
                            int n3 = sliderWidget.method_25368();
                            int n4 = sliderWidget.method_25364();
                            sliderWidget.method_55444(1008, 20, 0, 0);
                            sliderWidget.method_25348(McOptions.click(4.0 + 1000.0 * (double)f4), false);
                            sliderWidget.method_55444(n3, n4, n, n2);
                        }
                        bl = ImGui.isItemDeactivated();
                    }
                    if (bl2) break block15;
                    if (bl) break block16;
                    return;
                }
                sliderWidget.method_25357(McOptions.click(0.0));
                if (bl2) break block17;
                bl = sliderWidget instanceof SimpleOption.OptionSliderWidgetImpl;
            }
            if (bl) {
                SimpleOption.OptionSliderWidgetImpl optionSliderWidgetImpl = (SimpleOption.OptionSliderWidgetImpl)sliderWidget;
                optionSliderWidgetImpl.method_59868();
                optionSliderWidgetImpl.method_75314();
            }
            this.refresh();
        }
    }

    private void refresh() {
        block5: {
            Screen screen;
            block4: {
                Screen screen2 = this.vanilla;
                boolean bl = ImScreen.a;
                screen = screen2;
                if (bl) break block4;
                if (!(screen instanceof GameOptionsScreen)) break block5;
                screen = screen2;
            }
            GameOptionsScreen gameOptionsScreen = (GameOptionsScreen)screen;
            gameOptionsScreen.method_75370(this.field_22787.field_1690.method_75329());
        }
    }

    private void lock(String string, LockButtonWidget lockButtonWidget, float f, float f2) {
        boolean bl;
        block28: {
            boolean bl2;
            block29: {
                int n;
                ImDrawList imDrawList;
                boolean bl3;
                block26: {
                    float f3;
                    float f4;
                    block27: {
                        boolean bl4;
                        block25: {
                            block24: {
                                block23: {
                                    ImGui.setCursorScreenPos((float)f, (float)f2);
                                    bl3 = ImScreen.a;
                                    bl4 = ImGui.invisibleButton((String)string, (float)34.0f, (float)34.0f);
                                    if (bl3) break block23;
                                    if (!bl4) break block24;
                                    bl4 = lockButtonWidget.field_22763;
                                }
                                if (bl3) break block25;
                                if (!bl4) break block24;
                                bl4 = true;
                                break block25;
                            }
                            bl4 = false;
                        }
                        bl2 = bl4;
                        float f5 = lockButtonWidget.field_22763 ? Ui.hot() : 0.0f;
                        f4 = f5;
                        float f6 = lockButtonWidget.field_22763 ? 1.0f : 0.4f;
                        f3 = f6;
                        imDrawList = ImGui.getWindowDrawList();
                        Ui.fill(imDrawList, f, f2, f + 34.0f, f2 + 34.0f, Ui.col(Ui.mix(Im.QUIET, Im.QUIET_HI, f4), f3), 8.0f);
                        Ui.rect(imDrawList, f, f2, f + 34.0f, f2 + 34.0f, Ui.wash(0.16f * f3), 8.0f);
                        n = lockButtonWidget.method_1896();
                        if (bl3) break block26;
                        if (n == 0) break block27;
                        n = Ui.col(Menu.ACCENT);
                        break block26;
                    }
                    n = Ui.col(Ui.mix(Im.SUB, Im.INK, f4), f3);
                }
                int n2 = n;
                float f7 = f + 17.0f;
                Ui.fill(imDrawList, f7 - 6.0f, f2 + 16.0f, f7 + 6.0f, f2 + 25.0f, n2, 2.0f);
                float f8 = lockButtonWidget.method_1896() ? f7 : f7 + 5.0f;
                float f9 = f8;
                imDrawList.pathArcTo(f9, f2 + 14.0f, 3.5f, (float)Math.PI, (float)Math.PI * 2);
                imDrawList.pathStroke(n2, 0, 1.6f);
                imDrawList.addLine(f9 - 3.5f, f2 + 14.0f, f9 - 3.5f, f2 + 16.0f, n2, 1.6f);
                bl = lockButtonWidget.method_1896();
                if (bl3) break block28;
                if (!bl) break block29;
                imDrawList.addLine(f9 + 3.5f, f2 + 14.0f, f9 + 3.5f, f2 + 16.0f, n2, 1.6f);
            }
            bl = bl2;
        }
        if (bl) {
            lockButtonWidget.method_25306((AbstractInput)McOptions.click(0.0));
        }
    }

    
    static {
        char[] cArray;
        block11: {
            int n;
            int n2;
            block10: {
                char[] cArray2 = "N%$91]It-*8 \u000fv|:m3;\u000fh|%88".toCharArray();
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
                    case 0 -> 29;
                    case 1 -> 73;
                    case 2 -> 77;
                    case 3 -> 93;
                    case 4 -> 84;
                    case 5 -> 47;
                    default -> 30;
                }));
                ++n;
                n2 = n2;
            } while (n2 > n);
        }
        c = new String(cArray).intern();
        VALUE = McOptions.value();
    }
}
