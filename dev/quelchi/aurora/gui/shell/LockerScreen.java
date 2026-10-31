package dev.quelchi.aurora.gui.shell;

import dev.quelchi.aurora.config.ConfigManager;
import dev.quelchi.aurora.cosmetics.backend.AccountCapes;
import dev.quelchi.aurora.cosmetics.content.Colorways;
import dev.quelchi.aurora.cosmetics.content.Colorways$Way;
import dev.quelchi.aurora.cosmetics.content.Cosmetics;
import dev.quelchi.aurora.cosmetics.content.Emotes;
import dev.quelchi.aurora.cosmetics.content.Trails;
import dev.quelchi.aurora.cosmetics.content.Trails$Trail;
import dev.quelchi.aurora.cosmetics.content.Wings;
import dev.quelchi.aurora.cosmetics.content.Wings$Wing;
import dev.quelchi.aurora.cosmetics.renderer.CapeManager;
import dev.quelchi.aurora.cosmetics.renderer.EffectList;
import dev.quelchi.aurora.cosmetics.renderer.cape.CapeDefinition;
import dev.quelchi.aurora.cosmetics.renderer.cape.CapeLayer;
import dev.quelchi.aurora.cosmetics.renderer.cape.CapeLayer$Pass;
import dev.quelchi.aurora.cosmetics.renderer.cape.CapeTextureStore;
import dev.quelchi.aurora.cosmetics.renderer.cape.CosmeticRegistry;
import dev.quelchi.aurora.cosmetics.renderer.wings.WingFeature;
import dev.quelchi.aurora.cosmetics.renderer.wings.WingModel;
import dev.quelchi.aurora.cosmetics.renderer.wings.WingModel$Pose;
import dev.quelchi.aurora.cosmetics.renderer.wings.WingModels;
import dev.quelchi.aurora.gui.Anim;
import dev.quelchi.aurora.gui.HudScale;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.ImScreen;
import dev.quelchi.aurora.gui.im.Menu;
import dev.quelchi.aurora.gui.im.Motion;
import dev.quelchi.aurora.gui.im.PlusScreen;
import dev.quelchi.aurora.gui.im.Ui;
import dev.quelchi.aurora.gui.shell.ItemArt;
import dev.quelchi.aurora.gui.shell.LockerScreen$1;
import dev.quelchi.aurora.gui.shell.Rect;
import dev.quelchi.aurora.gui.shell.RewardsScreen;
import dev.quelchi.aurora.gui.shell.SearchIndex;
import dev.quelchi.aurora.gui.shell.Shop;
import dev.quelchi.aurora.gui.shell.StoreScreen;
import dev.quelchi.aurora.gui.shell.Turntable;
import dev.quelchi.aurora.gui.shell.Wardrobe;
import dev.quelchi.aurora.gui.shell.Wardrobe$Section;
import dev.quelchi.aurora.gui.shell.WardrobeBar;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.render.EffectEngine;
import dev.quelchi.aurora.render.EffectEngine$Effect;
import imgui.ImDrawList;
import imgui.ImFont;
import imgui.ImGui;
import imgui.type.ImString;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.session.Session;
import net.minecraft.util.Identifier;
import org.joml.Vector3f;
import org.joml.Vector3fc;

public final class LockerScreen
extends ImScreen {
    private static final float LEFT = 482.0f;
    private static final float PAD = 28.0f;
    private static final float SQ = 96.0f;
    private static final float GX = 14.0f;
    private static final float GY = 22.0f;
    private static final float LABEL = 24.0f;
    private static final float HEAD = 34.0f;
    private static final float WAY_H = 78.0f;
    private static final float EMO = 63.0f;
    private static final float EMO_GAP = 10.0f;
    private static final String[] TABS;
    private static final Wardrobe$Section[] SLOTS;
    private static final String SWAP;
    private final Turntable stage = new Turntable();
    private Wardrobe$Section slot;
    private String styling;
    private Wardrobe$Section framed = Wardrobe$Section.CAPES;
    private String note = "";
    private final ImString search = new ImString(32);
    private int wheel = -1;
    private String wasEmoting = "";
    private Wardrobe$Section fromSlot;
    private String fromStyling;
    private int dir = 1;
    private float holeX;
    private float holeY;
    private float holeR;
    private float holeB;
    private static final String[] b;
    private static final long[] c;
    private static final Integer[] d;
    private static final long g;

    public LockerScreen() {
        super(b[14]);
        AccountCapes.refresh();
        StoreScreen.refresh();
    }

    public LockerScreen(int n) {
        this();
        this.slot = Wardrobe$Section.EMOTES;
        this.framed = Wardrobe$Section.EMOTES;
        this.wheel = n;
        this.wasEmoting = Cosmetics.getOwnEmote();
    }

    public void method_25432() {
        LockerScreen lockerScreen;
        block4: {
            block5: {
                boolean bl = SearchIndex.a;
                lockerScreen = this;
                if (bl) break block4;
                if (lockerScreen.slot != Wardrobe$Section.EMOTES) break block5;
                Cosmetics.setOwnEmote(this.wasEmoting);
            }
            lockerScreen = this;
        }
        super.method_25432();
    }

    private static float gui(float f) {
        return f * Im.scale() / (float)MinecraftClient.method_1551().method_22683().method_4495();
    }

    public void method_25394(DrawContext drawContext, int n, int n2, float f) {
        Wardrobe$Section wardrobe$Section;
        int n3;
        int n4;
        int n5;
        int n6;
        block12: {
            LockerScreen lockerScreen;
            block10: {
                block11: {
                    LockerScreen lockerScreen2;
                    boolean bl;
                    block8: {
                        block9: {
                            drawContext.method_25294(0, 0, this.field_22789, this.field_22790, LockerScreen.a(28130, 3127451475607325775L));
                            bl = SearchIndex.a;
                            lockerScreen2 = this;
                            if (bl) break block8;
                            if (!(lockerScreen2.holeR <= this.holeX)) break block9;
                            return;
                        }
                        lockerScreen2 = this;
                    }
                    n6 = (int)Math.floor(LockerScreen.gui(lockerScreen2.holeX)) - 1;
                    n5 = (int)Math.floor(LockerScreen.gui(this.holeY)) - 1;
                    n4 = (int)Math.ceil(LockerScreen.gui(this.holeR)) + 1;
                    n3 = Math.round(LockerScreen.gui(this.holeB));
                    float[] fArray = Im.WINDOW;
                    int n7 = Math.round(Math.max(0.0f, Math.min(1.0f, fArray[3])) * Menu.fade * 255.0f);
                    drawContext.method_25294(n6, n5, n4, n3, n7 << 24 | Math.round(fArray[0] * 255.0f) << 16 | Math.round(fArray[1] * 255.0f) << 8 | Math.round(fArray[2] * 255.0f));
                    lockerScreen = this;
                    if (bl) break block10;
                    if (lockerScreen.slot == null) break block11;
                    wardrobe$Section = this.slot;
                    break block12;
                }
                lockerScreen = this;
            }
            wardrobe$Section = lockerScreen.framed;
        }
        Wardrobe$Section wardrobe$Section2 = wardrobe$Section;
        drawContext.method_44379(n6, n5, n4, n3);
        HudScale.push(drawContext);
        int n8 = (int)HudScale.toVirtual(n3 - n5);
        this.stage.layout(new Rect((int)HudScale.toVirtual(n6), (int)HudScale.toVirtual(n5) - n8 * 35 / 100, (int)HudScale.toVirtual(n4 - n6), n8 * 170 / 100));
        this.stage.render(drawContext, (int)HudScale.toVirtual(n), (int)HudScale.toVirtual(n2), wardrobe$Section2, Anim.seconds());
        HudScale.pop(drawContext);
        drawContext.method_44380();
    }

    private boolean onFigure(double d, double d2) {
        int n;
        block18: {
            block15: {
                float f;
                double d3;
                boolean bl;
                block17: {
                    block16: {
                        double d4;
                        block14: {
                            bl = SearchIndex.a;
                            d3 = d;
                            d4 = LockerScreen.gui(this.holeX);
                            if (bl) break block14;
                            if (!(d3 >= d4)) break block15;
                            d3 = d;
                            f = this.holeR;
                            if (bl) break block16;
                            d4 = LockerScreen.gui(f);
                        }
                        if (!(d3 <= d4)) break block15;
                        d3 = d2;
                        f = LockerScreen.gui(this.holeY);
                    }
                    if (bl) break block17;
                    if (!(d3 >= (double)f)) break block15;
                    d3 = d2;
                    f = LockerScreen.gui(this.holeB);
                }
                double d5 = d3 - (double)f;
                n = d5 == 0.0 ? 0 : (d5 < 0.0 ? -1 : 1);
                if (bl) break block18;
                if (n > 0) break block15;
                n = 1;
                break block18;
            }
            n = 0;
        }
        return n != 0;
    }

    @Override
    public boolean method_25402(Click click, boolean bl) {
        LockerScreen lockerScreen;
        block8: {
            block9: {
                boolean bl2 = SearchIndex.a;
                lockerScreen = this;
                if (bl2) break block8;
                if (!lockerScreen.onFigure(click.comp_4798(), click.comp_4799())) break block9;
                lockerScreen = this;
                if (bl2) break block8;
                if (!lockerScreen.stage.click(HudScale.toVirtual(click.comp_4798()), HudScale.toVirtual(click.comp_4799()), click.method_74245())) break block9;
                return true;
            }
            lockerScreen = this;
        }
        return super.method_25402(click, bl);
    }

    @Override
    public boolean method_25406(Click click) {
        this.stage.release(HudScale.toVirtual(click.comp_4798()), HudScale.toVirtual(click.comp_4799()), click.method_74245());
        return super.method_25406(click);
    }

    public boolean method_25403(Click click, double d, double d2) {
        return this.stage.drag(HudScale.toVirtual(click.comp_4798()), HudScale.toVirtual(click.comp_4799()));
    }

    @Override
    public boolean method_25401(double d, double d2, double d3, double d4) {
        double d5;
        double d6;
        LockerScreen lockerScreen;
        block8: {
            block10: {
                block9: {
                    boolean bl = SearchIndex.a;
                    lockerScreen = this;
                    d6 = d;
                    d5 = d2;
                    if (bl) break block8;
                    if (!lockerScreen.onFigure(d6, d5)) break block9;
                    lockerScreen = this;
                    if (bl) break block10;
                    if (!lockerScreen.stage.scroll(HudScale.toVirtual(d), HudScale.toVirtual(d2), d4)) break block9;
                    return true;
                }
                lockerScreen = this;
            }
            d6 = d;
            d5 = d2;
        }
        return super.method_25401(d6, d5, d3, d4);
    }

    @Override
    protected boolean key(int n) {
        Wardrobe$Section wardrobe$Section;
        LockerScreen lockerScreen;
        block17: {
            block16: {
                LockerScreen lockerScreen2;
                block15: {
                    boolean bl;
                    block14: {
                        int n2;
                        block12: {
                            block13: {
                                bl = SearchIndex.a;
                                n2 = n;
                                if (bl) break block12;
                                if (n2 != 256) break block13;
                                if (this.slot == null) break block13;
                                n2 = Im.typing() ? 1 : 0;
                                if (bl) break block12;
                                if (n2 == 0) break block14;
                            }
                            n2 = 0;
                        }
                        return n2 != 0;
                    }
                    lockerScreen = this;
                    lockerScreen2 = this;
                    if (bl) break block15;
                    if (lockerScreen2.styling == null) break block16;
                    lockerScreen2 = this;
                }
                wardrobe$Section = lockerScreen2.slot;
                break block17;
            }
            wardrobe$Section = null;
        }
        lockerScreen.back(wardrobe$Section);
        return true;
    }

    @Override
    public boolean shutsOnMenuKey() {
        return true;
    }

    
    @Override
    protected void draw() {
        float f;
        float f2;
        boolean bl;
        block21: {
            float f3;
            block19: {
                float f4;
                block20: {
                    float f5;
                    String[] stringArray;
                    block18: {
                        int n;
                        block16: {
                            int n2;
                            block17: {
                                stringArray = b;
                                n2 = Menu.top(stringArray[23], TABS, 0, 482.0f);
                                bl = SearchIndex.a;
                                n = n2;
                                if (bl) break block16;
                                if (n != 1) break block17;
                                this.field_22787.method_1507((Screen)new StoreScreen());
                                if (!bl) break block18;
                            }
                            n = n2;
                        }
                        if (n == 2) {
                            this.field_22787.method_1507((Screen)new RewardsScreen());
                        }
                    }
                    float f6 = Menu.x;
                    float f7 = Menu.y;
                    float f8 = Menu.h;
                    this.holeX = f6 + 482.0f;
                    this.holeY = f7 + 64.0f;
                    this.holeR = f6 + Menu.w - 14.0f;
                    this.holeB = f7 + f8;
                    ImGui.getWindowDrawList().addLine(this.holeX, this.holeY, this.holeX, this.holeB, Ui.col(Im.LINE));
                    ImGui.setCursorScreenPos((float)(f6 + 1.0f), (float)(f7 + 64.0f + 1.0f));
                    ImGui.pushStyleColor((int)3, (float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
                    ImGui.pushStyleVar((int)8, (float)0.0f);
                    stringArray = b;
                    ImGui.beginChild((String)stringArray[29], (float)480.0f, (float)(f8 - 64.0f - 2.0f), (boolean)false);
                    f2 = f6 + 28.0f;
                    f4 = ImGui.getCursorScreenPosY() + 21.0f;
                    f3 = f5 = Motion.since(stringArray[25]);
                    if (bl) break block19;
                    if (!(f3 < 0.22f)) break block20;
                    float f9 = 16.0f * (1.0f - Motion.out(f5 / 0.22f));
                    float f10 = Math.min(1.0f, f5 / 0.15f);
                    Wardrobe$Section wardrobe$Section = this.slot;
                    String string = this.styling;
                    this.slot = this.fromSlot;
                    this.styling = this.fromStyling;
                    ImGui.pushStyleVar((int)1, (float)1.0f);
                    ImGui.beginDisabled();
                    ImGui.pushStyleVar((int)0, (float)(1.0f - f10));
                    this.view(f2 - (float)this.dir * (16.0f - f9), f4);
                    ImGui.popStyleVar();
                    ImGui.endDisabled();
                    ImGui.popStyleVar();
                    this.slot = wardrobe$Section;
                    this.styling = string;
                    ImGui.pushStyleVar((int)0, (float)f10);
                    f = this.view(f2 + (float)this.dir * f9, f4);
                    ImGui.popStyleVar();
                    if (!bl) break block21;
                }
                f3 = this.view(f2, f4);
            }
            f = f3;
        }
        ImGui.setCursorScreenPos((float)f2, (float)(f + 28.0f - 1.0f));
        ImGui.dummy((float)1.0f, (float)1.0f);
        ImGui.endChild();
        ImGui.popStyleVar();
        ImGui.popStyleColor();
        Menu.end();
        if (Module.a) {
            SearchIndex.a = !bl;
        }
    }

    private float view(float f, float f2) {
        LockerScreen lockerScreen;
        block10: {
            block11: {
                boolean bl;
                block8: {
                    block9: {
                        bl = SearchIndex.a;
                        lockerScreen = this;
                        if (bl) break block8;
                        if (lockerScreen.slot != null) break block9;
                        return this.locker(f, f2);
                    }
                    lockerScreen = this;
                }
                if (bl) break block10;
                if (lockerScreen.styling == null) break block11;
                return this.styles(f, f2);
            }
            lockerScreen = this;
        }
        return lockerScreen.items(f, f2);
    }

    private static float width() {
        return 426.0f;
    }

    private static int cols(float f) {
        return Math.max(1, (int)((LockerScreen.width() + 14.0f) / (f + 14.0f)));
    }

    
    private static void tile(float f, float f2, float f3, float f4, boolean bl, float f5) {
        ImDrawList imDrawList;
        block8: {
            block7: {
                boolean bl2;
                block6: {
                    bl2 = SearchIndex.a;
                    imDrawList = ImGui.getWindowDrawList();
                    if (bl2) break block6;
                    if (!bl) break block7;
                    Ui.box(imDrawList, f, f2, f + f3, f2 + f4, Ui.tint(0.14f), new float[]{Menu.SOFT[0], Menu.SOFT[1], Menu.SOFT[2], 0.7f}, 12.0f, 1.5f);
                }
                if (!bl2) break block8;
            }
            Ui.box(imDrawList, f, f2, f + f3, f2 + f4, Im.SURFACE, Im.SURFACE_LINE, 12.0f, 1.0f);
        }
        if (!(f5 > 0.01f)) return;
        imDrawList.addRectFilled(f, f2, f + f3, f2 + f4, Ui.wash(0.04f * f5), 12.0f);
    }

    private static void label(String string, float f, float f2) {
        Ui.text(Im.sora13, f, Ui.mid(Im.sora13, f2, 16.0f), Im.INK, LockerScreen.fit(Im.sora13, string, 96.0f));
    }

    private void note(float f, float f2) {
        if (!this.note.isEmpty()) {
            Ui.text(Im.sub, f - Ui.width(Im.sub, this.note), Ui.mid(Im.sub, f2, 34.0f), Im.WARN, this.note);
        }
    }

    private static boolean backButton(float f, float f2) {
        boolean bl;
        block4: {
            float f3;
            block5: {
                ImGui.setCursorScreenPos((float)f, (float)f2);
                boolean bl2 = SearchIndex.a;
                String[] stringArray = b;
                bl = ImGui.invisibleButton((String)stringArray[1], (float)34.0f, (float)34.0f);
                f3 = Ui.hot();
                if (bl2) break block4;
                if (!(f3 > 0.01f)) break block5;
                ImGui.getWindowDrawList().addRectFilled(f, f2, f + 34.0f, f2 + 34.0f, Ui.wash(0.06f * f3), 9.0f);
            }
            Ui.icon(b[13], f + 9.5f, f2 + 9.5f, 15.0f, Ui.mix(Im.SUB, Im.INK, f3));
        }
        return bl;
    }

    private float locker(float f, float f2) {
        float f3;
        float f4;
        block28: {
            int n;
            String[] stringArray;
            boolean bl;
            block27: {
                block25: {
                    float f5;
                    block26: {
                        f5 = f + LockerScreen.width();
                        bl = SearchIndex.a;
                        Ui.text(Im.h3, f, Ui.mid(Im.h3, f2, 34.0f), Im.INK, b[21]);
                        if (bl) break block25;
                        if (!Menu.ghost(b[34], b[5], f5 + 6.0f, f2, 34.0f)) break block26;
                        this.reset();
                    }
                    stringArray = b;
                    this.note(f5 - Menu.ghostWidth(stringArray[12], stringArray[5]) - 4.0f, f2);
                    f2 += 52.0f;
                }
                int n2 = LockerScreen.cols(96.0f);
                for (n = 0; n < SLOTS.length; ++n) {
                    this.slotTile(SLOTS[n], f + (float)(n % n2) * 110.0f, f2 + (float)(n / n2) * 142.0f);
                    if (!bl) {
                        if (!bl) continue;
                        break;
                    }
                    break block27;
                }
                stringArray = b;
                Ui.text(Im.sora13, f, Ui.mid(Im.sora13, f2 += (float)((SLOTS.length + n2 - 1) / n2) * 142.0f, 16.0f), Im.INK, stringArray[4]);
                f2 += 24.0f;
                n = 6;
            }
            float f6 = Math.min(63.0f, (LockerScreen.width() - (float)(n - 1) * 10.0f) / (float)n);
            float[] fArray = new float[]{Im.MUTE[0], Im.MUTE[1], Im.MUTE[2], Im.MUTE[3] * 0.6f};
            for (int i = 0; i < n; ++i) {
                boolean bl2;
                block34: {
                    float f7;
                    float f8;
                    float f9;
                    String string;
                    block32: {
                        float f10;
                        block33: {
                            int n3;
                            block29: {
                                String string2;
                                block31: {
                                    block30: {
                                        f4 = f;
                                        f3 = (float)i * (f6 + 10.0f);
                                        if (bl) break block28;
                                        f10 = f4 + f3;
                                        string = Cosmetics.wheelSlot(i);
                                        ImGui.setCursorScreenPos((float)f10, (float)f2);
                                        stringArray = b;
                                        bl2 = ImGui.invisibleButton((String)(stringArray[31] + i), (float)f6, (float)f6);
                                        n3 = 1;
                                        if (bl) break block29;
                                        if (!ImGui.isItemClicked((int)n3)) break block30;
                                        string2 = string;
                                        if (bl) break block31;
                                        if (string2.isEmpty()) break block30;
                                        Cosmetics.setWheelSlot(i, "");
                                        ConfigManager.save();
                                    }
                                    f9 = f10;
                                    f8 = f2;
                                    f7 = f6;
                                    if (bl) break block32;
                                    LockerScreen.tile(f9, f8, f7, f6, false, Ui.hot());
                                    string2 = string;
                                }
                                n3 = string2.isEmpty() ? 1 : 0;
                            }
                            if (n3 == 0) break block33;
                            Ui.icon(b[41], f10 + f6 / 2.0f - 7.0f, f2 + f6 / 2.0f - 7.0f, 14.0f, fArray);
                            if (!bl) break block34;
                        }
                        f9 = f10 + f6 / 2.0f;
                        f8 = f2;
                        f7 = f6 * 0.35f;
                    }
                    LockerScreen.figure(f9, f8 + f7, string, Ui.col(Menu.SOFT), f6 / 120.0f);
                }
                if (!bl2) continue;
                this.wheel = i;
                this.framed = Wardrobe$Section.EMOTES;
                this.search.set("");
                this.go(Wardrobe$Section.EMOTES, null);
                continue;
            }
            f4 = f2;
            f3 = f6;
        }
        return f4 + f3;
    }

    private void slotTile(Wardrobe$Section wardrobe$Section, float f, float f2) {
        boolean bl;
        block76: {
            boolean bl2;
            block75: {
                boolean bl3;
                block73: {
                    boolean bl4;
                    block74: {
                        boolean bl5;
                        boolean bl6;
                        block72: {
                            block69: {
                                String string;
                                String string2;
                                float f3;
                                boolean bl7;
                                String string3;
                                block71: {
                                    block70: {
                                        boolean bl8;
                                        block67: {
                                            block68: {
                                                block66: {
                                                    String string4;
                                                    block65: {
                                                        block62: {
                                                            block64: {
                                                                String string5;
                                                                block63: {
                                                                    block61: {
                                                                        String string6;
                                                                        block60: {
                                                                            Wardrobe$Section wardrobe$Section2;
                                                                            block58: {
                                                                                block59: {
                                                                                    String string7;
                                                                                    block57: {
                                                                                        Wardrobe$Section wardrobe$Section3;
                                                                                        block55: {
                                                                                            block56: {
                                                                                                bl3 = SearchIndex.a;
                                                                                                wardrobe$Section3 = wardrobe$Section;
                                                                                                if (bl3) break block55;
                                                                                                if (wardrobe$Section3 != null) break block56;
                                                                                                string7 = b[16];
                                                                                                break block57;
                                                                                            }
                                                                                            wardrobe$Section3 = wardrobe$Section;
                                                                                        }
                                                                                        string7 = wardrobe$Section3.slot;
                                                                                    }
                                                                                    LockerScreen.label(string7, f, f2);
                                                                                    f2 += 24.0f;
                                                                                    ImGui.setCursorScreenPos((float)f, (float)f2);
                                                                                    wardrobe$Section2 = wardrobe$Section;
                                                                                    if (bl3) break block58;
                                                                                    if (wardrobe$Section2 != null) break block59;
                                                                                    string6 = b[18];
                                                                                    break block60;
                                                                                }
                                                                                wardrobe$Section2 = wardrobe$Section;
                                                                            }
                                                                            string6 = wardrobe$Section2.name();
                                                                        }
                                                                        String[] stringArray = b;
                                                                        bl2 = ImGui.invisibleButton((String)(stringArray[38] + string6), (float)96.0f, (float)96.0f);
                                                                        if (bl3) break block61;
                                                                        if (wardrobe$Section != null) break block62;
                                                                        LockerScreen.tile(f, f2, 96.0f, 96.0f, false, 0.0f);
                                                                        string5 = b[26];
                                                                        if (bl3) break block63;
                                                                        Ui.icon(string5, f + 48.0f - 15.0f, f2 + 48.0f - 15.0f, 30.0f, Im.MUTE);
                                                                    }
                                                                    if (!ImGui.isItemHovered()) break block64;
                                                                    string5 = b[20];
                                                                }
                                                                ImGui.setTooltip((String)string5);
                                                            }
                                                            return;
                                                        }
                                                        float f4 = f;
                                                        float f5 = f2;
                                                        float f6 = 96.0f;
                                                        float f7 = 96.0f;
                                                        boolean bl9 = wardrobe$Section == this.framed;
                                                        LockerScreen.tile(f4, f5, f6, f7, bl9, Ui.hot());
                                                        string3 = wardrobe$Section.worn();
                                                        string4 = string3;
                                                        if (bl3) break block65;
                                                        if (string4 == null) break block66;
                                                        string4 = string3;
                                                    }
                                                    bl8 = string4.isEmpty();
                                                    if (bl3) break block67;
                                                    if (bl8) break block66;
                                                    bl8 = string3.equals(b[27]);
                                                    if (bl3) break block67;
                                                    if (!bl8) break block68;
                                                }
                                                bl8 = true;
                                                break block67;
                                            }
                                            bl8 = false;
                                        }
                                        bl6 = bl8;
                                        boolean bl10 = wardrobe$Section == Wardrobe$Section.CAPES;
                                        bl7 = bl10;
                                        float f8 = bl7 ? 64.0f : 56.0f;
                                        f3 = f8;
                                        if (bl6) break block69;
                                        string2 = wardrobe$Section.cat;
                                        if (!bl7) break block70;
                                        string = string3;
                                        break block71;
                                    }
                                    string = Colorways.designOf(string3);
                                }
                                String string8 = bl7 ? null : Colorways.wayOf(string3);
                                bl5 = LockerScreen.art(string2, string, string8, f + 48.0f, f2 + (96.0f - f3) / 2.0f, f3);
                                if (bl3) break block72;
                                if (!bl5) break block69;
                                bl5 = true;
                                break block72;
                            }
                            bl5 = false;
                        }
                        bl4 = bl5;
                        bl = bl4;
                        if (bl3) break block73;
                        if (bl) break block74;
                        bl = bl6;
                        if (bl3) break block73;
                        if (!bl) break block74;
                        Ui.icon(wardrobe$Section.icon, f + 48.0f - 15.0f, f2 + 48.0f - 15.0f, 30.0f, Im.MUTE);
                        if (!bl3) break block75;
                    }
                    bl = bl4;
                }
                if (bl3) break block76;
                if (bl) break block75;
                Ui.icon(wardrobe$Section.icon, f + 48.0f - 15.0f, f2 + 48.0f - 15.0f, 30.0f, Menu.ICON_TOP, Menu.ICON_FOOT);
            }
            bl = bl2;
        }
        if (bl) {
            this.framed = wardrobe$Section;
            this.search.set("");
            this.back(wardrobe$Section);
        }
    }

    private void reset() {
        this.wear(Wardrobe$Section.CAPES, b[27]);
        Cosmetics.setOwnWings("");
        Cosmetics.setOwnTrail("");
        Cosmetics.setOwnKillEffect("");
        Cosmetics.setOwnHitEffect("");
        Cosmetics.setOwnMaceEffect("");
        ConfigManager.save();
    }

    
    private float items(float var1_1, float var2_2) {
        block150: {
            block144: {
                block146: {
                    block147: {
                        block149: {
                            block148: {
                                block145: {
                                    block143: {
                                        block140: {
                                            block142: {
                                                block141: {
                                                    block177: {
                                                        block139: {
                                                            block138: {
                                                                block137: {
                                                                    block135: {
                                                                        block136: {
                                                                            block134: {
                                                                                var4_3 = this.slot;
                                                                                var3_4 = SearchIndex.a;
                                                                                v0 = var4_3 == Wardrobe$Section.EMOTES ? 1 : 0;
                                                                                var5_5 = v0;
                                                                                var6_6 = var1_1 + LockerScreen.width();
                                                                                if (LockerScreen.backButton(var1_1, var2_2)) {
                                                                                    this.back(null);
                                                                                }
                                                                                v3 = Im.h3;
                                                                                v4 = var1_1 + 34.0f + 10.0f;
                                                                                v5 = Ui.mid(Im.h3, var2_2, 34.0f);
                                                                                v6 = Im.INK;
                                                                                v7 = var5_5;
                                                                                if (var3_4) break block134;
                                                                                if (v7 != 0) {
                                                                                }
                                                                                v9 = this;
                                                                                if (!var3_4) {
                                                                                }
                                                                                v7 = v9.wheel;
                                                                            }
                                                                            if (v7 >= 0) {
                                                                                v9 = this;
lbl42:

                                                                                var34_7 = LockerScreen.b;
                                                                                v12 = var34_7[0] + (v9.wheel + 1);
                                                                            } else lbl_neg_1000:

                                                                            {
                                                                                v12 = var4_3.label;
                                                                            }
                                                                            Ui.text(v3, v4, v5, v6, (String)v12);
                                                                            this.typeToSearch();
                                                                            var34_7 = LockerScreen.b;
                                                                            Menu.search(var34_7[37], var34_7[22] + var4_3.label.toLowerCase(Locale.ROOT), this.search, var6_6 - 180.0f, var2_2, 180.0f);
                                                                            this.note(var6_6 - 190.0f, var2_2);
                                                                            var2_2 += 52.0f;
                                                                            v13 = var4_3;
                                                                            if (var3_4) break block135;
                                                                            if (v13.worn() != null) break block136;
                                                                            v15 = "";
                                                                            break block137;
                                                                        }
                                                                        v13 = var4_3;
                                                                    }
                                                                    v15 = v13.worn();
                                                                }
                                                                var7_8 = v15;
                                                                v17 = var5_5;
                                                                if (var3_4) break block138;
                                                                if (v17 == 0) break block139;
                                                                v17 = this.wheel;
                                                            }
                                                            v20 = Cosmetics.wheelSlot(v17);
                                                            break block177;
                                                        }
                                                        v20 = var4_3 == Wardrobe$Section.CAPES ? var7_8 : Colorways.designOf(var7_8);
                                                    }
                                                    var8_9 = v20;
                                                    v22 = var8_9.isEmpty();
                                                    if (var3_4) break block140;
                                                    if (v22) break block141;
                                                    v22 = var8_9.equals(LockerScreen.b[45]);
                                                    if (var3_4) break block140;
                                                    if (!v22) break block142;
                                                }
                                                v22 = true;
                                                break block140;
                                            }
                                            v22 = false;
                                        }
                                        var9_10 = v22;
                                        var10_11 = this.search.get().trim().toLowerCase(Locale.ROOT);
                                        var11_12 = ImGui.getWindowDrawList();
                                        var12_13 = LockerScreen.cols(96.0f);
                                        var13_14 = 0;
                                        var14_15 = 142.0f;
                                        v26 = var10_11.isEmpty();
                                        if (var3_4) break block143;
                                        if (v26 == 0) break block144;
                                        v26 = var5_5;
                                    }
                                    if (var3_4) break block145;
                                    if (v26 != 0) break block144;
                                    LockerScreen.label(LockerScreen.b[11], var1_1, var2_2);
                                    ImGui.setCursorScreenPos((float)var1_1, (float)(var2_2 + 24.0f));
                                    if (var3_4) break block146;
                                    v26 = (int)ImGui.invisibleButton((String)LockerScreen.b[44], (float)96.0f, (float)96.0f);
                                }
                                if (v26 == 0) break block147;
                                v31 = this;
                                v32 = var4_3;
                                if (var4_3 != Wardrobe$Section.CAPES) break block148;
                                v34 = LockerScreen.b[27];
                                break block149;
                            }
                            v34 = "";
                        }
                        v31.wear(v32, v34);
                    }
                    LockerScreen.tile(var1_1, var2_2 + 24.0f, 96.0f, 96.0f, var9_10, Ui.hot());
                }
                var13_14 = 1;
            }
            for (String var16_17 : Wardrobe.catalogue(var4_3)) {
                block175: {
                    block176: {
                        block174: {
                            block171: {
                                block173: {
                                    block172: {
                                        block170: {
                                            block169: {
                                                block167: {
                                                    block168: {
                                                        block166: {
                                                            block163: {
                                                                block164: {
                                                                    block165: {
                                                                        block160: {
                                                                            block162: {
                                                                                block161: {
                                                                                    block159: {
                                                                                        block156: {
                                                                                            block158: {
                                                                                                block157: {
                                                                                                    block153: {
                                                                                                        block154: {
                                                                                                            block155: {
                                                                                                                block152: {
                                                                                                                    block151: {
                                                                                                                        if (var3_4) break block150;
                                                                                                                        var17_18 = Wardrobe.labelOf(var4_3, var16_17);
                                                                                                                        v36 = var10_11;
                                                                                                                        if (var3_4) break block151;
                                                                                                                        if (v36.isEmpty()) break block152;
                                                                                                                        v36 = var17_18.toLowerCase(Locale.ROOT);
                                                                                                                    }
                                                                                                                    if (!v36.contains(var10_11)) {
                                                                                                                        continue;
                                                                                                                    }
                                                                                                                }
                                                                                                                var18_19 = var1_1 + (float)(var13_14 % var12_13) * 110.0f;
                                                                                                                var19_20 = var2_2 + (float)(var13_14 / var12_13) * var14_15;
                                                                                                                var20_21 = var19_20 + 24.0f;
                                                                                                                ++var13_14;
                                                                                                                var21_22 = Wardrobe.lockedIn(var4_3, var16_17);
                                                                                                                var22_23 = var16_17.equals(var8_9);
                                                                                                                var23_24 = ImGui.isMouseHoveringRect((float)var18_19, (float)var20_21, (float)(var18_19 + 96.0f), (float)(var20_21 + 96.0f));
                                                                                                                v40 = 0;
                                                                                                                v41 = ImGui.getStyle().getAlpha();
                                                                                                                v42 = var21_22 != 0 ? 0.42f : 1.0f;
                                                                                                                ImGui.pushStyleVar((int)v40, (float)(v41 * v42));
                                                                                                                LockerScreen.label(var17_18, var18_19, var19_20);
                                                                                                                v44 = var18_19;
                                                                                                                if (var3_4) break block153;
                                                                                                                ImGui.setCursorScreenPos((float)v44, (float)var20_21);
                                                                                                                ImGui.setNextItemAllowOverlap();
                                                                                                                if (!ImGui.invisibleButton((String)(LockerScreen.b[10] + var16_17), (float)96.0f, (float)96.0f)) break block154;
                                                                                                                if (var5_5 == 0) break block155;
                                                                                                                this.perform(var16_17);
                                                                                                                if (!var3_4) break block154;
                                                                                                            }
                                                                                                            this.wear(var4_3, var16_17);
                                                                                                        }
                                                                                                        v44 = var18_19;
                                                                                                    }
                                                                                                    v49 = var20_21;
                                                                                                    v50 = 96.0f;
                                                                                                    v51 = 96.0f;
                                                                                                    v52 = var22_23;
                                                                                                    v53 = var23_24 != 0 ? 1.0f : 0.0f;
                                                                                                    LockerScreen.tile(v44, v49, v50, v51, v52, v53);
                                                                                                    if (var5_5 != 0) break block156;
                                                                                                    v55 = var4_3.cat;
                                                                                                    v56 = var16_17;
                                                                                                    if (!var22_23) break block157;
                                                                                                    v58 = Colorways.wayOf(var7_8);
                                                                                                    break block158;
                                                                                                }
                                                                                                v58 = null;
                                                                                            }
                                                                                            v60 = (int)LockerScreen.art(v55, v56, v58, var18_19 + 48.0f, var20_21 + 18.0f, 60.0f);
                                                                                            if (var3_4) break block159;
                                                                                            if (v60 == 0) break block156;
                                                                                            v60 = 1;
                                                                                            break block159;
                                                                                        }
                                                                                        v60 = 0;
                                                                                    }
                                                                                    var24_25 = v60;
                                                                                    v62 = var5_5;
                                                                                    if (var3_4) break block160;
                                                                                    if (v62 != 0) {
                                                                                    }
                                                                                    v64 = var18_19 + 48.0f;
                                                                                    v65 = var20_21 + 30.0f;
                                                                                    v66 = var16_17;
                                                                                    if (!var22_23) break block161;
                                                                                    v68 = Menu.SOFT;
                                                                                    break block162;
                                                                                }
                                                                                v68 = Im.SUB;
                                                                            }
                                                                            LockerScreen.figure(v64, v65, v66, Ui.col(v68), 0.75f);
                                                                            if (!var3_4) break block163;
lbl304:

                                                                            v62 = var24_25;
                                                                        }
                                                                        if (var3_4) break block164;
                                                                        if (v62 != 0) break block165;
                                                                        v62 = (int)var22_23;
                                                                        if (var3_4) break block164;
                                                                        if (v62 == 0) break block165;
                                                                        Ui.icon(var4_3.icon, var18_19 + 33.0f, var20_21 + 33.0f, 30.0f, Menu.ICON_TOP, Menu.ICON_FOOT);
                                                                        if (!var3_4) break block163;
                                                                    }
                                                                    v62 = var24_25;
                                                                }
                                                                if (var3_4) break block166;
                                                                if (v62 != 0) break block163;
                                                                Ui.icon(var4_3.icon, var18_19 + 33.0f, var20_21 + 33.0f, 30.0f, Im.SUB);
                                                            }
                                                            ImGui.popStyleVar();
                                                            v62 = var21_22;
                                                        }
                                                        if (v62 != 0) {
                                                            LockerScreen.padlock(var18_19 + 16.0f, var20_21 + 15.0f);
                                                        }
                                                        v79 = var4_3 == Wardrobe$Section.CAPES ? var16_17 : Colorways.designOf(var16_17);
                                                        var25_26 = v79;
                                                        v81 = var21_22;
                                                        if (var3_4) break block167;
                                                        if (v81 == 0) break block168;
                                                        v81 = Shop.onSale(var4_3.cat, var25_26);
                                                        if (var3_4) break block167;
                                                        if (v81 == 0) break block168;
                                                        WardrobeBar.amount(Shop.fmt(Cosmetics.price(var4_3.cat, var25_26)), var18_19 + 96.0f - 10.0f, var20_21 + 6.0f, 18.0f, Im.INK);
                                                    }
                                                    v81 = var21_22;
                                                }
                                                if (var3_4) break block169;
                                                if (v81 != 0) continue;
                                                v87 = var4_3;
                                                if (var3_4) break block170;
                                                v81 = LockerScreen.ways(v87, var16_17).size();
                                            }
                                            if (v81 < 2) {
                                                continue;
                                            }
                                            v87 = var4_3;
                                        }
                                        var30_32 = var16_17;
                                        var31_33 = String.valueOf((Object)v87);
                                        var34_7 = LockerScreen.b;
                                        var26_27 = Motion.to(var34_7[33] + var31_33 + var30_32, (boolean)var23_24, 0.09f);
                                        var32_34 = var16_17;
                                        var33_35 = String.valueOf((Object)var4_3);
                                        var27_28 = 9.6f + 2.4f * Motion.to(var34_7[3] + var33_35 + var32_34, (boolean)var23_24, 0.15f);
                                        v90 = var23_24;
                                        if (var3_4) break block171;
                                        if (v90 == 0) break block172;
                                        v92 = var18_19;
                                        if (var3_4) break block173;
                                        ImGui.setCursorScreenPos((float)(v92 + 96.0f - 32.0f), (float)(var20_21 + 8.0f));
                                        if (!ImGui.invisibleButton((String)(LockerScreen.b[36] + var16_17), (float)24.0f, (float)24.0f)) break block172;
                                        this.go(var4_3, var16_17);
                                    }
                                    v92 = var26_27;
                                }
                                v96 = 0.99f;
                                if (var3_4) break block174;
                                cfr_temp_0 = v92 - v96;
                                v90 = cfr_temp_0 == 0.0f ? 0 : (cfr_temp_0 < 0.0f ? -1 : 1);
                            }
                            if (v90 < 0) {
                                for (var28_29 = 0; var28_29 < 3; ++var28_29) {
                                    var11_12.addCircleFilled(var18_19 + 96.0f - 27.0f + (float)var28_29 * 6.5f, var20_21 + 17.0f, 1.8f, Ui.col(Im.SUB, 1.0f - var26_27));
                                    if (!var3_4) {
                                        if (!var3_4) continue;
                                        break;
                                    }
                                    break block175;
                                }
                            }
                            v92 = var26_27;
                            if (var3_4) break block176;
                            v96 = 0.01f;
                        }
                        if (!(v92 > v96)) break block175;
                        v92 = var18_19 + 96.0f - 20.0f;
                    }
                    var28_30 = v92;
                    var29_31 = var20_21 + 20.0f;
                    var11_12.addRectFilled(var28_30 - var27_28, var29_31 - var27_28, var28_30 + var27_28, var29_31 + var27_28, ImGui.getColorU32((float)1.0f, (float)1.0f, (float)1.0f, (float)var26_27), 7.0f);
                    var34_7 = LockerScreen.b;
                    Ui.icon(var34_7[40], var28_30 - 7.0f, var29_31 - 7.0f, 14.0f, new float[]{0.02f, 0.024f, 0.04f, var26_27});
                }
                if (!var3_4) continue;
            }
            if (var13_14 == 0) {
                Ui.text(Im.sub, var1_1, var2_2, Im.MUTE, LockerScreen.b[43]);
                return var2_2 + 20.0f;
            }
        }
        return var2_2 + (float)((var13_14 + var12_13 - 1) / var12_13) * var14_15 - 22.0f;
    }

    private float styles(float f, float f2) {
        block58: {
            boolean bl;
            List<Colorways$Way> list;
            Wardrobe$Section wardrobe$Section;
            block57: {
                String string;
                float f3;
                boolean bl2;
                block55: {
                    block56: {
                        int n;
                        float f4;
                        block45: {
                            String string2;
                            block44: {
                                Wardrobe$Section wardrobe$Section2;
                                block42: {
                                    block43: {
                                        float f5;
                                        block40: {
                                            block41: {
                                                wardrobe$Section = this.slot;
                                                bl2 = SearchIndex.a;
                                                f3 = f + LockerScreen.width();
                                                f5 = f;
                                                if (bl2) break block40;
                                                if (!LockerScreen.backButton(f5, f2)) break block41;
                                                this.go(wardrobe$Section, null);
                                            }
                                            Ui.text(Im.h3, f + 34.0f + 10.0f, Ui.mid(Im.h3, f2, 34.0f), Im.INK, LockerScreen.fit(Im.h3, Wardrobe.labelOf(wardrobe$Section, this.styling), LockerScreen.width() - 34.0f - 10.0f));
                                            f5 = f2 + 52.0f;
                                        }
                                        f2 = f5;
                                        list = LockerScreen.ways(wardrobe$Section, this.styling);
                                        wardrobe$Section2 = wardrobe$Section;
                                        if (bl2) break block42;
                                        if (wardrobe$Section2.worn() != null) break block43;
                                        string2 = "";
                                        break block44;
                                    }
                                    wardrobe$Section2 = wardrobe$Section;
                                }
                                string2 = wardrobe$Section2.worn();
                            }
                            String string3 = string2;
                            boolean bl3 = Colorways.designOf(string3).equals(this.styling);
                            String string4 = bl3 ? Colorways.wayOf(string3) : "";
                            String string5 = string4;
                            ImDrawList imDrawList = ImGui.getWindowDrawList();
                            int n2 = LockerScreen.cols(96.0f);
                            int n3 = 0;
                            for (Colorways$Way colorways$Way : list) {
                                block53: {
                                    boolean bl4;
                                    float f6;
                                    float f7;
                                    block54: {
                                        int n4;
                                        float f8;
                                        block48: {
                                            block47: {
                                                block49: {
                                                    block52: {
                                                        block51: {
                                                            String string6;
                                                            block50: {
                                                                block46: {
                                                                    f4 = f;
                                                                    n = n3;
                                                                    if (bl2) break block45;
                                                                    f7 = f4 + (float)(n % n2) * 110.0f;
                                                                    f8 = f2 + (float)(n3 / n2) * 124.0f;
                                                                    f6 = f8 + 24.0f;
                                                                    n4 = bl3;
                                                                    if (bl2) break block46;
                                                                    if (n4 == false) break block47;
                                                                    n4 = colorways$Way.id().equals(string5);
                                                                }
                                                                if (bl2) break block48;
                                                                if (n4 != false) break block49;
                                                                string6 = string5;
                                                                if (bl2) break block50;
                                                                if (string6 == null) break block51;
                                                                string6 = string5;
                                                            }
                                                            n4 = string6.isEmpty();
                                                            if (bl2) break block52;
                                                            if (n4 == 0) break block47;
                                                        }
                                                        n4 = n3;
                                                    }
                                                    if (bl2) break block48;
                                                    if (n4 != 0) break block47;
                                                }
                                                n4 = true;
                                                break block48;
                                            }
                                            n4 = false;
                                        }
                                        bl4 = n4;
                                        if (bl2) break block53;
                                        LockerScreen.label(colorways$Way.label(), f7, f8);
                                        ImGui.setCursorScreenPos((float)f7, (float)f6);
                                        if (!ImGui.invisibleButton((String)(b[8] + colorways$Way.id()), (float)96.0f, (float)78.0f)) break block54;
                                        this.wear(wardrobe$Section, Colorways.store(this.styling, colorways$Way.id(), list));
                                    }
                                    LockerScreen.tile(f7, f6, 96.0f, 78.0f, bl4, Ui.hot());
                                    imDrawList.addRectFilled(f7 + 14.0f, f6 + 14.0f, f7 + 38.0f, f6 + 38.0f, LockerScreen.solid(colorways$Way.inner()), 6.0f);
                                    imDrawList.addRectFilled(f7 + 44.0f, f6 + 14.0f, f7 + 68.0f, f6 + 38.0f, LockerScreen.solid(colorways$Way.outer()), 6.0f);
                                    ++n3;
                                }
                                if (!bl2) continue;
                            }
                            f4 = f2;
                            n = (n3 + n2 - 1) / n2;
                        }
                        f2 = f4 + (float)n * 124.0f;
                        string = b[7];
                        if (bl2) break block55;
                        if (!Menu.button(string, f3 - 72.0f, f2, 72.0f, 32.0f, true)) break block56;
                        this.go(wardrobe$Section, null);
                    }
                    String[] stringArray = b;
                    string = stringArray[19];
                }
                bl = Menu.ghost(string, b[9], f3 - 80.0f, f2, 32.0f);
                if (bl2) break block57;
                if (!bl) break block58;
                bl = list.isEmpty();
            }
            if (!bl) {
                this.wear(wardrobe$Section, Colorways.store(this.styling, list.get(0).id(), list));
            }
        }
        return f2 + 32.0f;
    }

    private void back(Wardrobe$Section wardrobe$Section) {
        this.go(wardrobe$Section, null);
    }

    private void go(Wardrobe$Section wardrobe$Section, String string) {
        block48: {
            LockerScreen lockerScreen;
            block47: {
                Wardrobe$Section wardrobe$Section2;
                block46: {
                    boolean bl;
                    block44: {
                        block45: {
                            block41: {
                                int n;
                                block42: {
                                    block43: {
                                        int n2;
                                        int n3;
                                        block40: {
                                            int n4;
                                            block39: {
                                                block38: {
                                                    int n5;
                                                    block37: {
                                                        LockerScreen lockerScreen2;
                                                        block35: {
                                                            block36: {
                                                                bl = SearchIndex.a;
                                                                lockerScreen2 = this;
                                                                if (bl) break block35;
                                                                if (lockerScreen2.slot != null) break block36;
                                                                n5 = 0;
                                                                break block37;
                                                            }
                                                            lockerScreen2 = this;
                                                        }
                                                        n5 = lockerScreen2.styling == null ? 1 : 2;
                                                    }
                                                    n3 = n5;
                                                    if (wardrobe$Section != null) break block38;
                                                    n4 = 0;
                                                    break block39;
                                                }
                                                n4 = string == null ? 1 : 2;
                                            }
                                            n2 = n4;
                                            if (bl) break block40;
                                            if (n3 == n2) break block41;
                                            this.fromSlot = this.slot;
                                            this.fromStyling = this.styling;
                                        }
                                        LockerScreen lockerScreen3 = this;
                                        n = n2;
                                        if (bl) break block42;
                                        if (n <= n3) break block43;
                                        n = 1;
                                        break block42;
                                    }
                                    n = -1;
                                }
                                lockerScreen3.dir = n;
                                Motion.restart(b[35]);
                            }
                            wardrobe$Section2 = this.slot;
                            if (bl) break block44;
                            if (wardrobe$Section2 != Wardrobe$Section.EMOTES) break block45;
                            wardrobe$Section2 = wardrobe$Section;
                            if (bl) break block44;
                            if (wardrobe$Section2 == Wardrobe$Section.EMOTES) break block45;
                            Cosmetics.setOwnEmote(this.wasEmoting);
                        }
                        wardrobe$Section2 = wardrobe$Section;
                    }
                    if (bl) break block46;
                    if (wardrobe$Section2 != Wardrobe$Section.EMOTES) break block47;
                    lockerScreen = this;
                    if (bl) break block48;
                    wardrobe$Section2 = lockerScreen.slot;
                }
                if (wardrobe$Section2 != Wardrobe$Section.EMOTES) {
                    this.wasEmoting = Cosmetics.getOwnEmote();
                }
            }
            this.slot = wardrobe$Section;
            this.styling = string;
            lockerScreen = this;
        }
        lockerScreen.note = "";
    }

    static String fit(String string, float f) {
        return LockerScreen.fit(Im.sora12, string, f);
    }

    
    static String fit(ImFont imFont, String string, float f) {
        boolean bl = SearchIndex.a;
        float f2 = Ui.width(imFont, string) - f;
        float f3 = f2 == 0.0f ? 0 : (f2 < 0.0f ? -1 : 1);
        if (bl) {
            return string + "\u2026";
        } else if (f3 <= 0) {
            return string;
        }
        do {
            f3 = string.length();
            if (f3 <= true || !(Ui.width(imFont, string + "\u2026") > f)) return string + "\u2026";
            string = string.substring(0, string.length() - 1);
        } while (!bl);
        return string + "\u2026";
    }

    
    private static List<Colorways$Way> ways(Wardrobe$Section wardrobe$Section, String string) {
        boolean bl = SearchIndex.a;
        switch (LockerScreen$1.$SwitchMap$dev$quelchi$aurora$gui$shell$Wardrobe$Section[wardrobe$Section.ordinal()]) {
            case 1: {
                Record record;
                Record record2 = record = Trails.byId(string);
                if (bl) return ((Trails$Trail)record2).ways();
                if (record2 != null) {
                    record2 = record;
                    return ((Trails$Trail)record2).ways();
                }
                if (!bl) return List.of();
            }
            case 2: {
                Record record;
                Record record3 = record = Wings.design(string);
                if (bl) return ((Wings$Wing)record3).ways();
                if (record3 != null) {
                    record3 = record;
                    return ((Wings$Wing)record3).ways();
                }
                if (!bl) return List.of();
            }
            case 3: 
            case 4: 
            case 5: {
                Record record;
                Record record4 = record = EffectList.of(wardrobe$Section.cat).byId(string);
                if (bl) return ((EffectEngine$Effect)record4).ways();
                if (record4 == null) return List.of();
                record4 = record;
                return ((EffectEngine$Effect)record4).ways();
            }
        }
        return List.of();
    }

    
    private void wear(Wardrobe$Section wardrobe$Section, String string) {
        block43: {
            Wardrobe$Section wardrobe$Section2;
            block45: {
                block44: {
                    block48: {
                        block49: {
                            Wardrobe$Section wardrobe$Section3;
                            String string2;
                            boolean bl;
                            block46: {
                                block47: {
                                    Wardrobe$Section wardrobe$Section4;
                                    block32: {
                                        block33: {
                                            block36: {
                                                String string3;
                                                block37: {
                                                    block38: {
                                                        block42: {
                                                            String string4;
                                                            String string5;
                                                            block40: {
                                                                block41: {
                                                                    boolean bl2;
                                                                    block39: {
                                                                        block34: {
                                                                            block35: {
                                                                                bl = SearchIndex.a;
                                                                                wardrobe$Section4 = wardrobe$Section;
                                                                                if (bl) break block32;
                                                                                if (wardrobe$Section4 != Wardrobe$Section.CAPES) break block33;
                                                                                string3 = string;
                                                                                if (bl) break block34;
                                                                                if (!AccountCapes.isAccount(string3)) break block35;
                                                                                this.note = b[42];
                                                                                AccountCapes.equip(string, this::lambda$wear$0);
                                                                                if (!bl) break block36;
                                                                            }
                                                                            string3 = string;
                                                                        }
                                                                        if (bl) break block37;
                                                                        if (string3.equals(b[27])) break block38;
                                                                        bl2 = Wardrobe.lockedIn(wardrobe$Section, string);
                                                                        if (bl) break block39;
                                                                        if (!bl2) break block38;
                                                                        string5 = b[32];
                                                                        string4 = string;
                                                                        if (bl) break block40;
                                                                        bl2 = Cosmetics.plusOnly(string5, string4);
                                                                    }
                                                                    if (!bl2) break block41;
                                                                    this.field_22787.method_1507((Screen)new PlusScreen());
                                                                    if (!bl) break block36;
                                                                }
                                                                string5 = b[17];
                                                                string4 = string;
                                                            }
                                                            if (!Shop.onSale(string5, string4)) break block42;
                                                            this.field_22787.method_1507((Screen)new StoreScreen(b[17], string));
                                                            if (!bl) break block36;
                                                        }
                                                        this.note = b[15];
                                                        if (!bl) break block36;
                                                    }
                                                    string3 = string;
                                                }
                                                LockerScreen.capeOn(string3);
                                            }
                                            return;
                                        }
                                        wardrobe$Section4 = wardrobe$Section;
                                    }
                                    if (wardrobe$Section4.equip == null) {
                                        return;
                                    }
                                    string2 = Colorways.designOf(string);
                                    if (bl) break block43;
                                    if (string2.isEmpty()) break block44;
                                    wardrobe$Section2 = wardrobe$Section;
                                    if (bl) break block45;
                                    if (!Cosmetics.locked(wardrobe$Section2.cat, string2)) break block44;
                                    wardrobe$Section3 = wardrobe$Section;
                                    if (bl) break block46;
                                    if (!Cosmetics.plusOnly(wardrobe$Section3.cat, string2)) break block47;
                                    this.field_22787.method_1507((Screen)new PlusScreen());
                                    if (!bl) break block48;
                                }
                                wardrobe$Section3 = wardrobe$Section;
                            }
                            if (!Shop.onSale(wardrobe$Section3.cat, string2)) break block49;
                            this.field_22787.method_1507((Screen)new StoreScreen(wardrobe$Section.cat, string2));
                            if (!bl) break block48;
                        }
                        this.note = b[15];
                    }
                    return;
                }
                wardrobe$Section2 = wardrobe$Section;
            }
            wardrobe$Section2.equip.accept(string);
            this.note = "";
        }
    }

    static boolean cape(String string, float f, float f2, float f3, float f4) {
        long l;
        Identifier identifier;
        boolean bl;
        block30: {
            long l2;
            block29: {
                UUID uUID;
                block28: {
                    Session session;
                    block26: {
                        Session session2;
                        block27: {
                            session2 = MinecraftClient.method_1551().method_1548();
                            bl = SearchIndex.a;
                            session = session2;
                            if (bl) break block26;
                            if (session != null) break block27;
                            uUID = null;
                            break block28;
                        }
                        session = session2;
                    }
                    uUID = session.method_44717();
                }
                identifier = CapeManager.texture(Cosmetics.variant(uUID, string, true));
                l = Ui.pixels(identifier);
                long l3 = l - 0L;
                l2 = l3 == 0L ? 0 : (l3 < 0L ? -1 : 1);
                if (bl) break block29;
                if (l2 != false) break block30;
                l2 = 0;
            }
            return (boolean)l2;
        }
        ImDrawList imDrawList = ImGui.getWindowDrawList();
        imDrawList.addImageRounded(l, f, f2, f + f3, f2 + f4, 0.015625f, 0.03125f, 0.171875f, 0.53125f, ImGui.getColorU32((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f), 3.0f);
        CapeDefinition capeDefinition = CosmeticRegistry.capeFor(identifier);
        if (capeDefinition == null) {
            return true;
        }
        int n = 1;
        while (n < capeDefinition.layers().size()) {
            block36: {
                block33: {
                    Identifier identifier2;
                    CapeLayer capeLayer;
                    block34: {
                        Identifier identifier3;
                        block35: {
                            CapeLayer capeLayer2;
                            block31: {
                                block32: {
                                    capeLayer = capeDefinition.layers().get(n);
                                    capeLayer2 = capeLayer;
                                    if (bl) break block31;
                                    if (capeLayer2.pass() == CapeLayer$Pass.OVERLAY) break block32;
                                    capeLayer2 = capeLayer;
                                    if (bl) break block31;
                                    if (capeLayer2.pass() == CapeLayer$Pass.GLOW) break block32;
                                    break block33;
                                }
                                capeLayer2 = capeLayer.frames().get(0);
                            }
                            identifier3 = (Identifier)capeLayer2;
                            identifier2 = identifier3;
                            if (bl) break block34;
                            if (CapeTextureStore.frame(identifier2)) break block35;
                            break block33;
                        }
                        identifier2 = identifier3;
                    }
                    long l4 = Ui.pixels(identifier2);
                    if (!bl) {
                        int n2 = capeLayer.colorAt(0L, 0);
                        if (l4 != 0L) {
                            imDrawList.addImageRounded(l4, f, f2, f + f3, f2 + f4, 0.015625f, 0.03125f, 0.171875f, 0.53125f, ImGui.getColorU32((float)((float)(n2 >> 16 & 0xFF) / 255.0f), (float)((float)(n2 >> 8 & 0xFF) / 255.0f), (float)((float)(n2 & 0xFF) / 255.0f), (float)((float)(n2 >>> 24) / 255.0f)), 3.0f);
                        }
                    }
                    break block36;
                }
                ++n;
            }
            if (!bl) continue;
        }
        return true;
    }

    private static int abgr(int n) {
        int n2 = Math.round((float)(n >>> 24) * ImGui.getStyle().getAlpha());
        return n2 << 24 | n & LockerScreen.a(2403, 6969423499590822095L) | (n & 0xFF) << 16 | n >> 16 & 0xFF;
    }

    static boolean art(String string, String string2, String string3, float f, float f2, float f3) {
        EffectEngine$Effect effectEngine$Effect;
        ImDrawList imDrawList;
        float f4;
        float f5;
        float f6;
        block30: {
            EffectEngine$Effect effectEngine$Effect2;
            block31: {
                EffectEngine$Effect effectEngine$Effect3;
                boolean bl;
                block29: {
                    EffectList effectList;
                    block27: {
                        EffectList effectList2;
                        block28: {
                            Trails$Trail trails$Trail;
                            block32: {
                                Trails$Trail trails$Trail2;
                                block26: {
                                    String string4;
                                    block25: {
                                        block24: {
                                            String string5;
                                            block23: {
                                                String string6;
                                                block21: {
                                                    block22: {
                                                        float f7 = f3 / 1.6f;
                                                        f6 = f2 + f3 / 2.0f;
                                                        f5 = f3 * 0.42f;
                                                        bl = SearchIndex.a;
                                                        f4 = f5 / 39.0f;
                                                        string5 = b[17];
                                                        string6 = string;
                                                        if (bl) break block21;
                                                        if (!string5.equals(string6)) break block22;
                                                        return LockerScreen.cape(string2, f - f7 / 2.0f, f2, f7, f3);
                                                    }
                                                    string5 = b[24];
                                                    if (bl) break block23;
                                                    string6 = string;
                                                }
                                                if (!string5.equals(string6)) break block24;
                                                string5 = string2;
                                            }
                                            return LockerScreen.wing(Wings.design(string5), string3, f, f6, f3);
                                        }
                                        imDrawList = ImGui.getWindowDrawList();
                                        string4 = b[6];
                                        if (bl) break block25;
                                        if (!string4.equals(string)) break block26;
                                        string4 = string2;
                                    }
                                    trails$Trail2 = Trails.design(string4);
                                    break block32;
                                }
                                trails$Trail2 = trails$Trail = null;
                            }
                            if (trails$Trail != null) {
                                float f8 = f3 / 66.0f;
                                ItemArt.trail((arg_0, arg_1, arg_2, arg_3, arg_4) -> LockerScreen.lambda$art$1(imDrawList, f, f8, f6, arg_0, arg_1, arg_2, arg_3, arg_4), 0, 0, 90, trails$Trail, trails$Trail.inWay(string3).argb());
                                return true;
                            }
                            effectList2 = EffectList.of(string);
                            effectList = effectList2;
                            if (bl) break block27;
                            if (effectList != null) break block28;
                            effectEngine$Effect3 = null;
                            break block29;
                        }
                        effectList = effectList2;
                    }
                    effectEngine$Effect3 = effectList.design(string2);
                }
                effectEngine$Effect2 = effectEngine$Effect3;
                effectEngine$Effect = effectEngine$Effect2;
                if (bl) break block30;
                if (effectEngine$Effect != null) break block31;
                return false;
            }
            effectEngine$Effect = effectEngine$Effect2.inWay(string3);
        }
        EffectEngine$Effect effectEngine$Effect4 = effectEngine$Effect;
        float f9 = EffectEngine.previewLife(effectEngine$Effect4);
        float f10 = Math.min(f9, (float)(System.currentTimeMillis() % (long)((f9 + 0.6f) * 1000.0f)) / 1000.0f);
        EffectEngine.preview(effectEngine$Effect4, f10, (arg_0, arg_1, arg_2, arg_3) -> LockerScreen.lambda$art$2(f, f5, f6, f4, imDrawList, arg_0, arg_1, arg_2, arg_3));
        return true;
    }

    private static boolean wing(Wings$Wing wings$Wing, String string, float f, float f2, float f3) {
        long l;
        WingModel wingModel;
        boolean bl;
        block18: {
            long l2;
            block17: {
                long l3;
                block16: {
                    WingModel wingModel2;
                    block14: {
                        block15: {
                            WingModel wingModel3;
                            block13: {
                                Wings$Wing wings$Wing2;
                                block11: {
                                    block12: {
                                        bl = SearchIndex.a;
                                        wings$Wing2 = wings$Wing;
                                        if (bl) break block11;
                                        if (wings$Wing2 != null) break block12;
                                        wingModel3 = null;
                                        break block13;
                                    }
                                    wings$Wing2 = wings$Wing;
                                }
                                wingModel3 = WingModels.get(wings$Wing2.model(string));
                            }
                            wingModel = wingModel3;
                            wingModel2 = wingModel;
                            if (bl) break block14;
                            if (wingModel2 != null) break block15;
                            l3 = 0L;
                            break block16;
                        }
                        wingModel2 = wingModel;
                    }
                    l3 = Ui.pixels(wingModel2.texture());
                }
                l = l3;
                long l4 = l - 0L;
                l2 = l4 == 0L ? 0 : (l4 < 0L ? -1 : 1);
                if (bl) break block17;
                if (l2 != false) break block18;
                l2 = 0;
            }
            return (boolean)l2;
        }
        float[] fArray = wingModel.bounds();
        float f4 = Math.min(f3 * 1.5f / Math.max(1.0f, fArray[2] - fArray[0]), f3 / Math.max(1.0f, fArray[3] - fArray[1])) * 0.8f;
        float f5 = (fArray[0] + fArray[2]) / 2.0f;
        float f6 = (fArray[1] + fArray[3]) / 2.0f;
        ArrayList arrayList = new ArrayList();
        WingModel$Pose wingModel$Pose = WingFeature.pose(wingModel, UUID.nameUUIDFromBytes((b[28] + wings$Wing.id()).getBytes()), 0.0f, false, false);
        wingModel.faces(wingModel$Pose, (arg_0, arg_1) -> LockerScreen.lambda$wing$3(f, f5, f4, f2, f6, arrayList, arg_0, arg_1));
        arrayList.sort(LockerScreen::lambda$wing$4);
        ImDrawList imDrawList = ImGui.getWindowDrawList();
        for (float[] fArray2 : arrayList) {
            imDrawList.addImageQuad(l, fArray2[0], fArray2[1], fArray2[4], fArray2[5], fArray2[8], fArray2[9], fArray2[12], fArray2[13], fArray2[2], fArray2[3], fArray2[6], fArray2[7], fArray2[10], fArray2[11], fArray2[14], fArray2[15], ImGui.getColorU32((float)fArray2[17], (float)fArray2[17], (float)fArray2[17], (float)1.0f));
            if (!bl) continue;
        }
        return true;
    }

    private static void padlock(float f, float f2) {
        ImDrawList imDrawList = ImGui.getWindowDrawList();
        int n = Ui.col(Im.SUB);
        imDrawList.addRectFilled(f - 5.0f, f2 - 1.0f, f + 5.0f, f2 + 6.0f, n, 2.0f);
        imDrawList.pathArcTo(f, f2 - 3.0f, 3.0f, (float)Math.PI, (float)Math.PI * 2);
        imDrawList.pathStroke(n, 0, 1.5f);
        imDrawList.addLine(f - 3.0f, f2 - 3.0f, f - 3.0f, f2 - 1.0f, n, 1.5f);
        imDrawList.addLine(f + 3.0f, f2 - 3.0f, f + 3.0f, f2 - 1.0f, n, 1.5f);
    }

    private static void capeOn(String string) {
        Cosmetics.setOwnCape(string);
        ConfigManager.save();
    }

    private static int solid(int n) {
        return ImGui.getColorU32((float)((float)(n >> 16 & 0xFF) / 255.0f), (float)((float)(n >> 8 & 0xFF) / 255.0f), (float)((float)(n & 0xFF) / 255.0f), (float)1.0f);
    }

    private void perform(String string) {
        LockerScreen lockerScreen;
        block9: {
            int n;
            block7: {
                boolean bl;
                block8: {
                    bl = SearchIndex.a;
                    n = Wardrobe.lockedIn(Wardrobe$Section.EMOTES, string);
                    if (bl) break block7;
                    if (n == 0) break block8;
                    this.note = b[46];
                    return;
                }
                lockerScreen = this;
                if (bl) break block9;
                lockerScreen.note = "";
                Cosmetics.setOwnEmote(string);
                n = this.wheel;
            }
            if (n < 0) {
                return;
            }
            lockerScreen = this;
        }
        Cosmetics.setWheelSlot(lockerScreen.wheel, string);
        ConfigManager.save();
    }

    static void figure(float f, float f2, String string, int n, float f3) {
        float[] fArray = new float[44];
        boolean bl = SearchIndex.a;
        Emotes.pose(string, (float)(System.currentTimeMillis() % g) / 1000.0f, fArray);
        for (int i = 0; i < fArray.length; ++i) {
            block14: {
                float[] fArray2 = fArray;
                if (bl) break block14;
                if (!Float.isNaN(fArray2[i])) continue;
                fArray2 = fArray;
            }
            fArray2[i] = 0.0f;
            if (!bl) continue;
        }
        ImDrawList imDrawList = ImGui.getWindowDrawList();
        float f4 = fArray[3] + fArray[18];
        float f5 = Math.max(1.2f, 1.8f * f3);
        float f6 = (f2 += fArray[19] * 1.1f * f3) + (2.0f + 16.0f * (float)Math.cos(f4)) * f3;
        imDrawList.addCircleFilled(f, f2 - (9.0f - 6.0f * (float)Math.sin(f4)) * f3, 4.0f * f3, n, 16);
        imDrawList.addLine(f, f2 - 2.0f * f3, f, f6, n, f5);
        for (int i = -1; i <= 1; i += 2) {
            double d;
            float f7;
            float f8;
            block21: {
                float f9;
                block19: {
                    block20: {
                        int n2;
                        int n3;
                        block17: {
                            block18: {
                                int n4;
                                block15: {
                                    block16: {
                                        n4 = i;
                                        if (bl) break block15;
                                        if (n4 >= 0) break block16;
                                        n4 = 6;
                                        break block15;
                                    }
                                    n4 = 9;
                                }
                                n3 = n4;
                                n2 = i;
                                if (bl) break block17;
                                if (n2 >= 0) break block18;
                                n2 = 12;
                                break block17;
                            }
                            n2 = 15;
                        }
                        int n5 = n2;
                        float f10 = f + (float)(i * 5) * f3;
                        float f11 = f10 + ((float)Math.sin(fArray[n3 + 2]) + (float)i * 0.28f) * 13.0f * f3;
                        float f12 = f2 + (float)(Math.cos(fArray[n3 + 2]) * Math.cos(fArray[n3])) * 13.0f * f3;
                        imDrawList.addLine(f10, f2, f11, f12, n, f5);
                        imDrawList.addCircleFilled(f11, f12, f5, n, 8);
                        f8 = fArray[n5] - fArray[20] * 0.9f;
                        f7 = f + (float)(i * 3) * f3;
                        f9 = f8;
                        if (bl) break block19;
                        if (!(Math.abs(f9) > 0.6f)) break block20;
                        d = (double)i * Math.abs(Math.sin(f8));
                        break block21;
                    }
                    f9 = f8;
                }
                d = Math.sin(f9);
            }
            float f13 = (float)d;
            imDrawList.addLine(f7, f6, f7 + f13 * 8.0f * f3 + (float)i, f6 + (float)Math.cos(f8) * 12.0f * f3, n, f5);
            if (!bl) continue;
        }
    }

    private static int lambda$wing$4(float[] fArray, float[] fArray2) {
        return Float.compare(fArray[16], fArray2[16]);
    }

    private static void lambda$wing$3(float f, float f2, float f3, float f4, float f5, List list, Vector3f[] vector3fArray, float[][] fArray) {
        float f6;
        float[] fArray2;
        block9: {
            block10: {
                float f7;
                boolean bl;
                block8: {
                    int n;
                    float[] fArray3;
                    fArray2 = new float[18];
                    bl = SearchIndex.a;
                    for (int i = 0; i < 4; ++i) {
                        fArray3 = fArray2;
                        n = i * 4;
                        f7 = f;
                        if (!bl) {
                            fArray3[n] = f7 + (vector3fArray[i].x - f2) * f3;
                            fArray2[i * 4 + 1] = f4 - (vector3fArray[i].y - f5) * f3;
                            fArray2[i * 4 + 2] = fArray[i][0];
                            fArray2[i * 4 + 3] = fArray[i][1];
                            if (!bl) continue;
                            break;
                        }
                        break block8;
                    }
                    fArray3 = fArray2;
                    n = 16;
                    f7 = vector3fArray[0].z + vector3fArray[1].z + vector3fArray[2].z + vector3fArray[3].z;
                }
                fArray3[n] = f7;
                Vector3f vector3f = new Vector3f((Vector3fc)vector3fArray[1]).sub((Vector3fc)vector3fArray[0]).cross((Vector3fc)new Vector3f((Vector3fc)vector3fArray[3]).sub((Vector3fc)vector3fArray[0]));
                float[] fArray4 = fArray2;
                int n = 17;
                f6 = vector3f.lengthSquared();
                if (bl) break block9;
                if (!(f6 > 0.0f)) break block10;
                f6 = 0.62f + 0.38f * Math.abs(vector3f.normalize().z);
                break block9;
            }
            f6 = 1.0f;
        }
        fArray4[n] = f6;
        list.add(fArray2);
    }

    private static void lambda$art$2(float f, float f2, float f3, float f4, ImDrawList imDrawList, float f5, float f6, float f7, int n) {
        float f8 = f + f5 * f2;
        float f9 = f3 - f6 * f2 * 0.66f;
        float f10 = Math.max(2.0f, Math.min(4.0f, f7 * 52.0f)) * f4;
        imDrawList.addRectFilled(f8, f9, f8 + f10, f9 + f10, LockerScreen.abgr(n));
    }

    private static void lambda$art$1(ImDrawList imDrawList, float f, float f2, float f3, int n, int n2, int n3, int n4, int n5) {
        imDrawList.addRectFilled(f + (float)(n - 45) * f2, f3 + (float)(n2 - 36) * f2, f + (float)(n3 - 45) * f2, f3 + (float)(n4 - 36) * f2, LockerScreen.abgr(n5));
    }

    private void lambda$wear$0(String string) {
        block7: {
            String string2;
            block6: {
                String string3;
                boolean bl;
                block4: {
                    block5: {
                        bl = SearchIndex.a;
                        LockerScreen lockerScreen = this;
                        string3 = string;
                        if (bl) break block4;
                        if (string3 != null) break block5;
                        string3 = "";
                        break block4;
                    }
                    string3 = string;
                }
                lockerScreen.note = string3;
                string2 = string;
                if (bl) break block6;
                if (string2 != null) break block7;
                string2 = "";
            }
            LockerScreen.capeOn(string2);
        }
    }

    
    static {
        block25: {
            block24: {
                block23: {
                    block22: {
                        block21: {
                            var15 = new String[47];
                            var13_1 = 0;
                            var12_2 = "f~<OXhHO|'\u001b\u0004Ar0P\u0005pg<IX\u0004Da<L\u0006f~<OX;\u0007Qv5IX;S\u0005Wa2RQ\u0004g|=^\u0003Tr*\u0007Qv5IX;S\u0004Jg6V\u0004m|=^\fqv ^IhTVg5RI\u0004Ar0P\btr!_O'YF\u0007o|0PX,\u0015\u0003kr'\u0004@r#^\u0003Kr'\u0005qv ^I\u000b`|>RS/\u001bP|<U\u0006o|0PX:\u0007pv2I^ \u001b\btr!_O'YF\u0005Tz=\\N\rTr!_O'YF= L\\8\bP\u007f<Ob ZW\u0006\u000e}<UXe\u0005Wz?^\u0007\u0005Wz?^N\u0006o|0PX:\u0005T{6^Q\u0004@r#^\u0003Sv=\fqv ^IhTVg5RI\rTr!_O'YF= L\\8\u0005Pg*WX\b\u00000 ^\\:XK\u0004P\u007f<O\u0007qv$ZO,H\u0004Fw:O\u0004S\u007f&H\u000epd:O^ RMtsX\\8^\u0010m|'ST&\\\u0003~2O^ ^P=\u0004M|=^";
                            var14_3 = "f~<OXhHO|'\u001b\u0004Ar0P\u0005pg<IX\u0004Da<L\u0006f~<OX;\u0007Qv5IX;S\u0005Wa2RQ\u0004g|=^\u0003Tr*\u0007Qv5IX;S\u0004Jg6V\u0004m|=^\fqv ^IhTVg5RI\u0004Ar0P\btr!_O'YF\u0007o|0PX,\u0015\u0003kr'\u0004@r#^\u0003Kr'\u0005qv ^I\u000b`|>RS/\u001bP|<U\u0006o|0PX:\u0007pv2I^ \u001b\btr!_O'YF\u0005Tz=\\N\rTr!_O'YF= L\\8\bP\u007f<Ob ZW\u0006\u000e}<UXe\u0005Wz?^\u0007\u0005Wz?^N\u0006o|0PX:\u0005T{6^Q\u0004@r#^\u0003Sv=\fqv ^IhTVg5RI\rTr!_O'YF= L\\8\u0005Pg*WX\b\u00000 ^\\:XK\u0004P\u007f<O\u0007qv$ZO,H\u0004Fw:O\u0004S\u007f&H\u000epd:O^ RMtsX\\8^\u0010m|'ST&\\\u0003~2O^ ^P=\u0004M|=^".length();
                            var11_4 = 11;
                            var10_5 = -1;
lbl7:

                            while (true) {
                                v0 = ++var10_5;
                                v1 = var12_2.substring(v0, v0 + var11_4);
                                v2 = -1;
                                break block21;
                                break;
                            }
lbl12:

                            while (true) {
                                var15[var13_1++] = v3.intern();
                                if ((var10_5 += var11_4) < var14_3) {
                                    var11_4 = var12_2.charAt(var10_5);
                                    continue;
                                }
                                var12_2 = "\u000e}<UXe\u0007o|0PX,\u0015";
                                var14_3 = "\u000e}<UXe\u0007o|0PX,\u0015".length();
                                var11_4 = 6;
                                var10_5 = -1;
lbl21:

                                while (true) {
                                    v4 = ++var10_5;
                                    v1 = var12_2.substring(v4, v4 + var11_4);
                                    v2 = 0;
                                    break block21;
                                    break;
                                }
                                break;
                            }
lbl26:

                            while (true) {
                                var15[var13_1++] = v3.intern();
                                if ((var10_5 += var11_4) < var14_3) {
                                    var11_4 = var12_2.charAt(var10_5);
                                    continue;
                                }
                                break block22;
                                break;
                            }
                        }
                        v5 = v1.toCharArray();
                        v6 = v5;
                        v7 = v5.length;
                        var16_7 = 0;
                        do {
                            v6 = v6;
                            v8 = var16_7;
                            v9 = v6[v8];
                            switch (var16_7 % 7) {
                                case 0: {
                                    v10 = 35;
                                    break;
                                }
                                case 1: {
                                    v10 = 19;
                                    break;
                                }
                                case 2: {
                                    v10 = 83;
                                    break;
                                }
                                case 3: {
                                    v10 = 59;
                                    break;
                                }
                                case 4: {
                                    v10 = 61;
                                    break;
                                }
                                case 5: {
                                    v10 = 72;
                                    break;
                                }
                                default: {
                                    v10 = 59;
                                }
                            }
                            v6[v8] = (char)(v9 ^ v10);
                            ++var16_7;
lbl65:

                            v7 = v7;
                        } while (v7 > var16_7);
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
                    LockerScreen.b = var15;
                    LockerScreen.SWAP = LockerScreen.b[35];
                    var2_8 = 4310733766917129384L;
                    var8_9 = new long[2];
                    var5_10 = 0;
                    var6_11 = "DVm@\u00dc>\u0093gM\u00e6Wx\u0003\u00bd\u000e\u00ed";
                    var7_12 = "DVm@\u00dc>\u0093gM\u00e6Wx\u0003\u00bd\u000e\u00ed".length();
                    var4_13 = 0;
                    while (true) {
                        break block23;
                        break;
                    }
lbl86:

                    while (true) {
                        var8_9[v11] = (((long)var9_14[0] & 255L) << 56 | ((long)var9_14[1] & 255L) << 48 | ((long)var9_14[2] & 255L) << 40 | ((long)var9_14[3] & 255L) << 32 | ((long)var9_14[4] & 255L) << 24 | ((long)var9_14[5] & 255L) << 16 | ((long)var9_14[6] & 255L) << 8 | (long)var9_14[7] & 255L) ^ var2_8;
                        if (var4_13 < var7_12) continue;
                        break block24;
                        break;
                    }
                }
                var9_14 = var6_11.substring(var4_13, var4_13 += 8).getBytes("ISO-8859-1");
                v11 = var5_10++;
                while (true)
            }
            LockerScreen.c = var8_9;
            LockerScreen.d = new Integer[2];
            break block25;
lbl101:

            while (true) {
                continue;
                break;
            }
        }
        var0_15 = 7195871877549352927L;
        while (true)
        LockerScreen.g = 7195871877548752927L ^ var0_15;
        v12 = new String[3];
        var10_6 = LockerScreen.b;
        v12[0] = var10_6[30];
        v12[1] = var10_6[2];
        v12[2] = var10_6[39];
        LockerScreen.TABS = v12;
        LockerScreen.SLOTS = new Wardrobe$Section[]{null, Wardrobe$Section.CAPES, Wardrobe$Section.WINGS, Wardrobe$Section.TRAILS, Wardrobe$Section.KILLFX, Wardrobe$Section.HITFX, Wardrobe$Section.MACEFX};
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x61AC;
        if (d[n2] == null) {
            LockerScreen.d[n2] = (int)(c[n2] ^ l);
        }
        return d[n2];
    }
}
