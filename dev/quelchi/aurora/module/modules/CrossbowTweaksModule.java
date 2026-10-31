package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.UnscaledHud;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.GuiTweaksModule;
import dev.quelchi.aurora.module.modules.GuiTweaksModule$Key;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.ColorSetting;
import dev.quelchi.aurora.module.setting.NumberSetting;
import java.util.List;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public final class CrossbowTweaksModule
extends Module
implements UnscaledHud {
    private static CrossbowTweaksModule instance;
    private final BooleanSetting inInventory;
    private final BooleanSetting inHotbar;
    private final NumberSetting opacity;
    private final ColorSetting plainColor;
    private static final String[] b;
    private static final long[] d;
    private static final Integer[] e;

    public CrossbowTweaksModule() {
        String[] stringArray = b;
        super(stringArray[4], stringArray[5], Category.COMBAT, false);
        this.inInventory = new BooleanSetting(stringArray[0], true);
        this.inHotbar = new BooleanSetting(stringArray[1], true);
        this.opacity = new NumberSetting(stringArray[2], 10.0, 100.0, 55.0, 5.0);
        this.plainColor = new ColorSetting(stringArray[3], CrossbowTweaksModule.a(13104, 3880211968165391772L), false);
        instance = this;
        this.addSettings(this.inInventory, this.inHotbar, this.opacity, this.plainColor);
        this.inInventory.children(this.opacity);
    }

    private static boolean on() {
        boolean bl;
        block6: {
            block5: {
                CrossbowTweaksModule crossbowTweaksModule;
                boolean bl2;
                block4: {
                    CrossbowTweaksModule crossbowTweaksModule2 = instance;
                    bl2 = AmbienceModule.b;
                    crossbowTweaksModule = crossbowTweaksModule2;
                    if (bl2) break block4;
                    if (crossbowTweaksModule == null) break block5;
                    crossbowTweaksModule = crossbowTweaksModule2;
                }
                bl = crossbowTweaksModule.isEnabled();
                if (bl2) break block6;
                if (!bl) break block5;
                bl = true;
                break block6;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean showsInInventory() {
        boolean bl;
        block8: {
            block7: {
                boolean bl2;
                block6: {
                    bl2 = AmbienceModule.b;
                    bl = CrossbowTweaksModule.on();
                    if (bl2) break block6;
                    if (!bl) break block7;
                    bl = CrossbowTweaksModule.instance.inInventory.getValue();
                }
                if (bl2) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean showsInHotbar() {
        boolean bl;
        block8: {
            block7: {
                boolean bl2;
                block6: {
                    bl2 = AmbienceModule.b;
                    bl = CrossbowTweaksModule.on();
                    if (bl2) break block6;
                    if (!bl) break block7;
                    bl = CrossbowTweaksModule.instance.inHotbar.getValue();
                }
                if (bl2) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int overlayFor(ItemStack itemStack) {
        int n;
        block34: {
            block33: {
                PotionContentsComponent potionContentsComponent;
                boolean bl;
                block32: {
                    List list;
                    block30: {
                        List list2;
                        block31: {
                            ChargedProjectilesComponent chargedProjectilesComponent;
                            block28: {
                                ChargedProjectilesComponent chargedProjectilesComponent2;
                                block29: {
                                    block27: {
                                        Object object;
                                        block25: {
                                            block26: {
                                                block24: {
                                                    bl = AmbienceModule.b;
                                                    int n2 = CrossbowTweaksModule.on();
                                                    if (bl) return n2;
                                                    if (n2 == 0) return 0;
                                                    object = itemStack;
                                                    if (bl) break block24;
                                                    if (object == null) return 0;
                                                    object = itemStack;
                                                }
                                                if (bl) break block25;
                                                if (object.method_31574(Items.field_8399)) break block26;
                                                return 0;
                                            }
                                            object = itemStack.method_58694(DataComponentTypes.field_49649);
                                        }
                                        chargedProjectilesComponent2 = (ChargedProjectilesComponent)object;
                                        chargedProjectilesComponent = chargedProjectilesComponent2;
                                        if (bl) break block27;
                                        if (chargedProjectilesComponent == null) return 0;
                                        chargedProjectilesComponent = chargedProjectilesComponent2;
                                    }
                                    if (bl) break block28;
                                    if (!chargedProjectilesComponent.method_57442()) break block29;
                                    return 0;
                                }
                                chargedProjectilesComponent = chargedProjectilesComponent2;
                            }
                            list2 = chargedProjectilesComponent.method_57437();
                            list = list2;
                            if (bl) break block30;
                            if (!list.isEmpty()) break block31;
                            return 0;
                        }
                        list = list2.get(0);
                    }
                    ItemStack itemStack2 = (ItemStack)list;
                    PotionContentsComponent potionContentsComponent2 = (PotionContentsComponent)itemStack2.method_58694(DataComponentTypes.field_49651);
                    potionContentsComponent = potionContentsComponent2;
                    if (bl) break block32;
                    if (potionContentsComponent == null) break block33;
                    potionContentsComponent = potionContentsComponent2;
                }
                n = potionContentsComponent.method_8064() & CrossbowTweaksModule.a(16622, 1678391457959829056L);
                if (!bl) break block34;
            }
            n = CrossbowTweaksModule.instance.plainColor.getRGB() & CrossbowTweaksModule.a(15747, 7845852168832000812L);
        }
        int n3 = Math.round((float)CrossbowTweaksModule.instance.opacity.getValue() * 255.0f / 100.0f);
        return Math.max(1, Math.min(255, n3)) << 24 | n;
    }

    public static void drawOverlay(DrawContext drawContext, int n, int n2, int n3) {
        if (n3 == 0) {
            return;
        }
        drawContext.method_25294(n, n2, n + 16, n2 + 16, n3);
    }

    
    @Override
    public void onHudRender(DrawContext drawContext) {
        MinecraftClient minecraftClient;
        boolean bl = AmbienceModule.b;
        if (!CrossbowTweaksModule.showsInHotbar()) {
            return;
        }
        MinecraftClient minecraftClient2 = minecraftClient = MinecraftClient.method_1551();
        if (!bl) {
            if (minecraftClient2.field_1724 == null) return;
            minecraftClient2 = minecraftClient;
        }
        int n = minecraftClient2.field_1690.field_1842;
        if (!bl) {
            if (n != 0) {
                return;
            }
            n = GuiTweaksModule.partHidden(GuiTweaksModule$Key.HOTBAR);
        }
        if (!bl) {
            if (n != 0) {
                return;
            }
            n = drawContext.method_51421() / 2 - 88;
        }
        int n2 = n;
        int n3 = drawContext.method_51443() - 19;
        GuiTweaksModule.pushOverlay(drawContext, GuiTweaksModule$Key.HOTBAR);
        try {
            int n4 = 0;
            do {
                if (n4 >= 9) return;
                int n5 = CrossbowTweaksModule.overlayFor(minecraftClient.field_1724.method_31548().method_5438(n4));
                if (bl) return;
                if (bl) continue;
                if (n5 != 0) {
                    CrossbowTweaksModule.drawOverlay(drawContext, n2 + n4 * 20, n3, n5);
                }
                ++n4;
            } while (!bl);
            return;
        }
        finally {
            GuiTweaksModule.popOverlay(drawContext);
        }
    }

    
    static {
        block23: {
            block22: {
                block21: {
                    block20: {
                        var13 = new String[6];
                        var11_1 = 0;
                        var10_2 = "Z\u0012\u0007\u0019Lls}\bH\u0002[\tZ\u0012\u0007\u0018Mntr\u000e\u0007\\\fF\u0013Kno\u0011C\u0010F\u0019L:wa\u000eH\u0007\u0002yy\u007f\u0013U";
                        var12_3 = "Z\u0012\u0007\u0019Lls}\bH\u0002[\tZ\u0012\u0007\u0018Mntr\u000e\u0007\\\fF\u0013Kno\u0011C\u0010F\u0019L:wa\u000eH\u0007\u0002yy\u007f\u0013U".length();
                        var9_4 = 12;
                        var8_5 = -1;
lbl7:

                        while (true) {
                            v0 = ++var8_5;
                            v1 = var10_2.substring(v0, v0 + var9_4);
                            v2 = -1;
                            break block20;
                            break;
                        }
lbl12:

                        while (true) {
                            var13[var11_1++] = v3.intern();
                            if ((var8_5 += var9_4) < var12_3) {
                                var9_4 = var10_2.charAt(var8_5);
                                continue;
                            }
                            var10_2 = "P\u000eH\u0003Qxyd\\s\u0007G{}`\u0016@\u0014H\u0007\u0002vyr\u0018B\u0014\u0002{da\u0013PPVcfv";
                            var12_3 = "P\u000eH\u0003Qxyd\\s\u0007G{}`\u0016@\u0014H\u0007\u0002vyr\u0018B\u0014\u0002{da\u0013PPVcfv".length();
                            var9_4 = 15;
                            var8_5 = -1;
lbl21:

                            while (true) {
                                v4 = ++var8_5;
                                v1 = var10_2.substring(v4, v4 + var9_4);
                                v2 = 0;
                                break block20;
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
                            break block21;
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
                                v10 = 19;
                                break;
                            }
                            case 1: {
                                v10 = 124;
                                break;
                            }
                            case 2: {
                                v10 = 39;
                                break;
                            }
                            case 3: {
                                v10 = 112;
                                break;
                            }
                            case 4: {
                                v10 = 34;
                                break;
                            }
                            case 5: {
                                v10 = 26;
                                break;
                            }
                            default: {
                                v10 = 22;
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
                CrossbowTweaksModule.b = var13;
                var0_7 = 6289669786853712564L;
                var6_8 = new long[3];
                var3_9 = 0;
                var4_10 = "v\u000b\u00eeA\u00a2\u00ad\u0003\u000br\u008d\fj\u00acf~g\u00d4Stj'\u00b6`\u00df";
                var5_11 = "v\u000b\u00eeA\u00a2\u00ad\u0003\u000br\u008d\fj\u00acf~g\u00d4Stj'\u00b6`\u00df".length();
                var2_12 = 0;
                while (true) {
                    break block22;
                    break;
                }
lbl84:

                while (true) {
                    var6_8[v11] = (((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L) ^ var0_7;
                    if (var2_12 < var5_11) continue;
                    break block23;
                    break;
                }
            }
            var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
            v11 = var3_9++;
            while (true)
        }
        CrossbowTweaksModule.d = var6_8;
        CrossbowTweaksModule.e = new Integer[3];
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1EAE;
        if (e[n2] == null) {
            CrossbowTweaksModule.e[n2] = (int)(d[n2] ^ l);
        }
        return e[n2];
    }
}
