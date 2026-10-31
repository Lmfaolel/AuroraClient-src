package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.TextHud;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public final class PotionHudModule
extends TextHud {
    private static PotionHudModule instance;
    private static final String[] ROMAN;
    private final BooleanSetting showIcons;
    private final BooleanSetting showName;
    private final BooleanSetting showLevel;
    private final BooleanSetting showTimer;
    private final BooleanSetting hideVanilla;
    private final List<Identifier> icons;
    private static final String[] d;

    public PotionHudModule() {
        String[] stringArray = d;
        super(stringArray[4], stringArray[8], stringArray[11], 4, 44);
        this.showIcons = new BooleanSetting(stringArray[10], true);
        this.showName = new BooleanSetting(stringArray[2], true);
        this.showLevel = new BooleanSetting(stringArray[15], true);
        this.showTimer = new BooleanSetting(stringArray[3], true);
        this.hideVanilla = new BooleanSetting(stringArray[14], true);
        this.icons = new ArrayList<Identifier>();
        instance = this;
        this.addSettings(this.showIcons, this.showName, this.showLevel, this.showTimer, this.hideVanilla);
    }

    public static PotionHudModule get() {
        return instance;
    }

    public boolean hidesVanilla() {
        return this.hideVanilla.getValue();
    }

    @Override
    protected List<String> lines() {
        this.icons.clear();
        boolean bl = AmbienceModule.b;
        ClientPlayerEntity clientPlayerEntity = MinecraftClient.method_1551().field_1724;
        if (clientPlayerEntity == null) {
            return List.of();
        }
        ArrayList<String> arrayList = new ArrayList<String>();
        for (StatusEffectInstance statusEffectInstance : clientPlayerEntity.method_6026()) {
            block13: {
                PotionHudModule potionHudModule;
                block15: {
                    String string;
                    block14: {
                        string = this.format(statusEffectInstance);
                        if (bl) break block13;
                        if (!string.isEmpty()) break block14;
                        potionHudModule = this;
                        if (bl) break block15;
                        if (!potionHudModule.showIcons.getValue()) {
                            continue;
                        }
                    }
                    arrayList.add(string);
                    potionHudModule = this;
                }
                potionHudModule.icons.add(InGameHud.method_71644((RegistryEntry)statusEffectInstance.method_5579()));
            }
            if (!bl) continue;
        }
        return arrayList;
    }

    private static String level(int n) {
        Object object;
        block6: {
            int n2;
            block4: {
                block5: {
                    boolean bl = AmbienceModule.b;
                    n2 = n;
                    if (bl) break block4;
                    if (n2 >= ROMAN.length) break block5;
                    object = ROMAN[n];
                    break block6;
                }
                n2 = n + 1;
            }
            object = " " + n2;
        }
        return object;
    }

    private String format(StatusEffectInstance statusEffectInstance) {
        StringBuilder stringBuilder;
        block41: {
            String string;
            StringBuilder stringBuilder2;
            block45: {
                StatusEffectInstance statusEffectInstance2;
                block43: {
                    block44: {
                        boolean bl;
                        block42: {
                            int n;
                            block40: {
                                block39: {
                                    PotionHudModule potionHudModule;
                                    block37: {
                                        block38: {
                                            BooleanSetting booleanSetting;
                                            int n2;
                                            block35: {
                                                block36: {
                                                    stringBuilder = new StringBuilder();
                                                    n2 = statusEffectInstance.method_5578();
                                                    bl = AmbienceModule.b;
                                                    booleanSetting = this.showName;
                                                    if (bl) break block35;
                                                    if (!booleanSetting.getValue()) break block36;
                                                    stringBuilder.append(((StatusEffect)statusEffectInstance.method_5579().comp_349()).method_5560().getString());
                                                    potionHudModule = this;
                                                    if (bl) break block37;
                                                    if (!potionHudModule.showLevel.getValue()) break block38;
                                                    n = n2;
                                                    if (bl) break block39;
                                                    if (n <= 0) break block38;
                                                    stringBuilder.append(PotionHudModule.level(n2));
                                                    if (!bl) break block38;
                                                }
                                                potionHudModule = this;
                                                if (bl) break block37;
                                                booleanSetting = potionHudModule.showLevel;
                                            }
                                            if (!booleanSetting.getValue()) break block38;
                                            n = n2;
                                            if (bl) break block39;
                                            if (n <= 0) break block38;
                                            stringBuilder.append(PotionHudModule.level(n2).trim());
                                        }
                                        potionHudModule = this;
                                    }
                                    n = potionHudModule.showTimer.getValue() ? 1 : 0;
                                }
                                if (bl) break block40;
                                if (n == 0) break block41;
                                stringBuilder2 = stringBuilder;
                                if (bl) break block42;
                                n = stringBuilder2.length();
                            }
                            if (n > 0) {
                                stringBuilder.append(d[12]);
                            }
                            stringBuilder2 = stringBuilder;
                        }
                        statusEffectInstance2 = statusEffectInstance;
                        if (bl) break block43;
                        if (!statusEffectInstance2.method_48559()) break block44;
                        string = "\u221e";
                        break block45;
                    }
                    statusEffectInstance2 = statusEffectInstance;
                }
                string = PotionHudModule.time(statusEffectInstance2.method_5584());
            }
            stringBuilder2.append(string);
        }
        return stringBuilder.toString();
    }

    @Override
    protected List<String> placeholder() {
        String[] stringArray = d;
        return List.of(stringArray[5], stringArray[6]);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    protected Identifier icon(int n) {
        int n2;
        block4: {
            boolean bl = AmbienceModule.b;
            n2 = this.showIcons.getValue();
            if (bl) break block4;
            if (n2 == 0) return null;
            n2 = n;
        }
        if (n2 < this.icons.size()) return this.icons.get(n);
        return null;
    }

    @Override
    protected int iconWidth() {
        int n;
        block2: {
            block3: {
                boolean bl = AmbienceModule.b;
                n = this.showIcons.getValue();
                if (bl) break block2;
                if (n == 0) break block3;
                n = 15;
                break block2;
            }
            n = 0;
        }
        return n;
    }

    private static String time(int n) {
        int n2 = n / 20;
        return n2 / 60 + ":" + String.format(d[9], n2 % 60);
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[17];
                var3_1 = 0;
                var2_2 = "]G2\u0003]G-\u00043o\u0016\f\u0005)g\u0016\fe\n-a\u000f\u0000xiF5[?\u000e.~\u001e\fs'/4.[Y-4V\u000e.z\t\fy`\u0012\u0015.[X-3S\u0004]G2 \u0014<m\u000f\u0000abF\u0018h\u001d\ftsF\tg\u0016\fet\u0004X>I\r\u00054m\u0014\u0007d\u0006\ra\u000f\u0000xi\u0002].\u0002]X\u00125g\u001f\f7q\u0007\u0013g\u0017\u0005v'\u000f\u001ea\u0015\u001a";
                var4_3 = "]G2\u0003]G-\u00043o\u0016\f\u0005)g\u0016\fe\n-a\u000f\u0000xiF5[?\u000e.~\u001e\fs'/4.[Y-4V\u000e.z\t\fy`\u0012\u0015.[X-3S\u0004]G2 \u0014<m\u000f\u0000abF\u0018h\u001d\ftsF\tg\u0016\fet\u0004X>I\r\u00054m\u0014\u0007d\u0006\ra\u000f\u0000xi\u0002].\u0002]X\u00125g\u001f\f7q\u0007\u0013g\u0017\u0005v'\u000f\u001ea\u0015\u001a".length();
                var1_4 = 3;
                var0_5 = -1;
lbl7:

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block18;
                    break;
                }
lbl12:

                while (true) {
                    var5[var3_1++] = v3.intern();
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        continue;
                    }
                    var2_2 = "1k\r\f{\u0003]X2";
                    var4_3 = "1k\r\f{\u0003]X2".length();
                    var1_4 = 5;
                    var0_5 = -1;
lbl21:

                    while (true) {
                        v4 = ++var0_5;
                        v1 = var2_2.substring(v4, v4 + var1_4);
                        v2 = 0;
                        break block18;
                        break;
                    }
                    break;
                }
lbl26:

                while (true) {
                    var5[var3_1++] = v3.intern();
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        continue;
                    }
                    break block19;
                    break;
                }
            }
            v5 = v1.toCharArray();
            v6 = v5;
            v7 = v5.length;
            var6_7 = 0;
            do {
                v6 = v6;
                v8 = var6_7;
                v9 = v6[v8];
                switch (var6_7 % 7) {
                    case 0: {
                        v10 = 125;
                        break;
                    }
                    case 1: {
                        v10 = 14;
                        break;
                    }
                    case 2: {
                        v10 = 123;
                        break;
                    }
                    case 3: {
                        v10 = 105;
                        break;
                    }
                    case 4: {
                        v10 = 23;
                        break;
                    }
                    case 5: {
                        v10 = 7;
                        break;
                    }
                    default: {
                        v10 = 102;
                    }
                }
                v6[v8] = (char)(v9 ^ v10);
                ++var6_7;
lbl65:

                v7 = v7;
            } while (v7 > var6_7);
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
        PotionHudModule.d = var5;
        v11 = new String[6];
        v11[0] = "";
        var0_6 = PotionHudModule.d;
        v11[1] = var0_6[0];
        v11[2] = var0_6[7];
        v11[3] = var0_6[1];
        v11[4] = var0_6[13];
        v11[5] = var0_6[16];
        PotionHudModule.ROMAN = v11;
    }
}
