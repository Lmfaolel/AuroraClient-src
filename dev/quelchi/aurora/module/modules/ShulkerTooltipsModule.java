package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.ShulkerTooltipsModule$Grid;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipData;
import net.minecraft.util.DyeColor;
import net.minecraft.util.collection.DefaultedList;

public final class ShulkerTooltipsModule
extends Module {
    private static ShulkerTooltipsModule instance;
    private static final boolean OTHER_MOD;
    private static final String[] b;
    private static final long[] d;
    private static final Integer[] e;

    public ShulkerTooltipsModule() {
        String[] stringArray = b;
        super(stringArray[2], stringArray[0], Category.HUD, false);
        instance = this;
        TooltipComponentCallback.EVENT.register(ShulkerTooltipsModule::lambda$new$0);
    }

    public static boolean active(ItemStack itemStack) {
        boolean bl;
        block14: {
            block11: {
                boolean bl2;
                block13: {
                    block12: {
                        ShulkerTooltipsModule shulkerTooltipsModule;
                        block10: {
                            ShulkerTooltipsModule shulkerTooltipsModule2 = instance;
                            bl2 = AmbienceModule.b;
                            shulkerTooltipsModule = shulkerTooltipsModule2;
                            if (bl2) break block10;
                            if (shulkerTooltipsModule == null) break block11;
                            shulkerTooltipsModule = shulkerTooltipsModule2;
                        }
                        bl = shulkerTooltipsModule.isEnabled();
                        if (bl2) break block12;
                        if (!bl) break block11;
                        bl = OTHER_MOD;
                    }
                    if (bl2) break block13;
                    if (bl) break block11;
                    bl = Block.method_9503((Item)itemStack.method_7909()) instanceof ShulkerBoxBlock;
                }
                if (bl2) break block14;
                if (!bl) break block11;
                bl = true;
                break block14;
            }
            bl = false;
        }
        return bl;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static TooltipData grid(ItemStack itemStack) {
        int n;
        DefaultedList defaultedList;
        block27: {
            DyeColor dyeColor;
            block25: {
                DyeColor dyeColor2;
                block26: {
                    int n2;
                    ContainerComponent containerComponent;
                    boolean bl;
                    block23: {
                        block24: {
                            Object object;
                            block21: {
                                block22: {
                                    ContainerComponent containerComponent2;
                                    block20: {
                                        Object object2;
                                        block18: {
                                            block19: {
                                                bl = AmbienceModule.b;
                                                object2 = itemStack;
                                                if (bl) break block18;
                                                if (ShulkerTooltipsModule.active(object2)) break block19;
                                                return null;
                                            }
                                            object2 = itemStack.method_58694(DataComponentTypes.field_49622);
                                        }
                                        containerComponent = (ContainerComponent)object2;
                                        containerComponent2 = containerComponent;
                                        if (bl) break block20;
                                        if (containerComponent2 == null) return null;
                                        containerComponent2 = containerComponent;
                                    }
                                    object = containerComponent2.method_59714().iterator();
                                    if (bl) break block21;
                                    if (object.hasNext()) break block22;
                                    return null;
                                }
                                object = itemStack.method_58695(DataComponentTypes.field_56400, (Object)TooltipDisplayComponent.field_56318);
                            }
                            n2 = ((TooltipDisplayComponent)object).method_67214(DataComponentTypes.field_49622);
                            if (bl) break block23;
                            if (n2 != 0) break block24;
                            return null;
                        }
                        n2 = 27;
                    }
                    defaultedList = DefaultedList.method_10213((int)n2, (Object)ItemStack.field_8037);
                    containerComponent.method_57492(defaultedList);
                    dyeColor2 = ((ShulkerBoxBlock)Block.method_9503((Item)itemStack.method_7909())).method_10528();
                    dyeColor = dyeColor2;
                    if (bl) break block25;
                    if (dyeColor != null) break block26;
                    n = ShulkerTooltipsModule.a(4839, 2808704173456902325L);
                    break block27;
                }
                dyeColor = dyeColor2;
            }
            n = dyeColor.method_7787() & ShulkerTooltipsModule.a(14800, 9093827096957313923L);
        }
        int n3 = n;
        return new ShulkerTooltipsModule$Grid((DefaultedList<ItemStack>)defaultedList, n3);
    }

    private static TooltipComponent lambda$new$0(TooltipData tooltipData) {
        ShulkerTooltipsModule$Grid shulkerTooltipsModule$Grid;
        block6: {
            block5: {
                ShulkerTooltipsModule$Grid shulkerTooltipsModule$Grid2;
                TooltipData tooltipData2;
                block4: {
                    boolean bl = AmbienceModule.b;
                    tooltipData2 = tooltipData;
                    if (bl) break block4;
                    if (!(tooltipData2 instanceof ShulkerTooltipsModule$Grid)) break block5;
                    tooltipData2 = tooltipData;
                }
                shulkerTooltipsModule$Grid = shulkerTooltipsModule$Grid2 = (ShulkerTooltipsModule$Grid)tooltipData2;
                break block6;
            }
            shulkerTooltipsModule$Grid = null;
        }
        return shulkerTooltipsModule$Grid;
    }

    
    static {
        block16: {
            block15: {
                block14: {
                    var13 = new String[3];
                    var11_1 = 0;
                    var10_2 = "=\u0015rIOB\u001b\u001b\u0011v[\u001d\u0011\u0011\u0001\u0005=]\u0000_\u0007\u000b\u0013iMOP\u0000N\u001c=Y\u001dX\u0017\u0011\u001d\u0015hR\u0004T\u0001\f\u0012eJ\u0000^\u001f\u001a\u0014m\u0010=\u0015hR\u0004T\u0001N)rQ\u0003E\u001a\u001e\u000e";
                    var12_3 = "=\u0015rIOB\u001b\u001b\u0011v[\u001d\u0011\u0011\u0001\u0005=]\u0000_\u0007\u000b\u0013iMOP\u0000N\u001c=Y\u001dX\u0017\u0011\u001d\u0015hR\u0004T\u0001\f\u0012eJ\u0000^\u001f\u001a\u0014m\u0010=\u0015hR\u0004T\u0001N)rQ\u0003E\u001a\u001e\u000e".length();
                    var9_4 = 35;
                    var8_5 = -1;
lbl7:

                    while (true) {
                        continue;
                        break;
                    }
lbl9:

                    while (true) {
                        var13[var11_1++] = new String(v0).intern();
                        if ((var8_5 += var9_4) < var12_3) {
                            var9_4 = var10_2.charAt(var8_5);
                            continue;
                        }
                        break block14;
                        break;
                    }
                    v1 = ++var8_5;
                    v2 = var10_2.substring(v1, v1 + var9_4).toCharArray();
                    v0 = v2;
                    v3 = v2.length;
                    var14_6 = 0;
                    do {
                        v0 = v0;
                        v4 = var14_6;
                        v5 = v0[v4];
                        switch (var14_6 % 7) {
                            case 0: {
                                v6 = 110;
                                break;
                            }
                            case 1: {
                                v6 = 125;
                                break;
                            }
                            case 2: {
                                v6 = 29;
                                break;
                            }
                            case 3: {
                                v6 = 62;
                                break;
                            }
                            case 4: {
                                v6 = 111;
                                break;
                            }
                            case 5: {
                                v6 = 49;
                                break;
                            }
                            default: {
                                v6 = 115;
                            }
                        }
                        v0[v4] = (char)(v5 ^ v6);
                        ++var14_6;
lbl48:

                        v3 = v3;
                    } while (v3 > var14_6);
                    while (true)
                }
                ShulkerTooltipsModule.b = var13;
                var0_7 = 8245385977265109466L;
                var6_8 = new long[2];
                var3_9 = 0;
                var4_10 = "\u00eb!\u000e\u00c0\u0000\u00d6%\u00a6\u00e6\u0082\u00cb\u0083\u00c8Z\u00ae\u00e4";
                var5_11 = "\u00eb!\u000e\u00c0\u0000\u00d6%\u00a6\u00e6\u0082\u00cb\u0083\u00c8Z\u00ae\u00e4".length();
                var2_12 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl63:

                while (true) {
                    var6_8[v7] = (((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L) ^ var0_7;
                    if (var2_12 < var5_11) continue;
                    break block16;
                    break;
                }
            }
            var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
            v7 = var3_9++;
            while (true)
        }
        ShulkerTooltipsModule.d = var6_8;
        ShulkerTooltipsModule.e = new Integer[2];
        ShulkerTooltipsModule.OTHER_MOD = FabricLoader.getInstance().isModLoaded(ShulkerTooltipsModule.b[1]);
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5A53;
        if (e[n2] == null) {
            ShulkerTooltipsModule.e[n2] = (int)(d[n2] ^ l);
        }
        return e[n2];
    }
}
