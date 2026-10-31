package dev.quelchi.aurora.module.modules;

import com.google.gson.JsonObject;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.SoundEditor;
import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.SoundControllerModule$Cue;
import dev.quelchi.aurora.module.setting.ActionSetting;
import dev.quelchi.aurora.module.setting.EnumSetting;
import dev.quelchi.aurora.module.setting.NumberSetting;
import dev.quelchi.aurora.sound.SoundOverrides;
import java.util.UUID;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;

public final class SoundControllerModule
extends Module {
    private static SoundControllerModule instance;
    private final ActionSetting sounds;
    private final EnumSetting<SoundControllerModule$Cue> killSound;
    private final NumberSetting killVolume;
    private final NumberSetting killPitch;
    private final EnumSetting<SoundControllerModule$Cue> popSound;
    private final NumberSetting popVolume;
    private final NumberSetting popPitch;
    private static final String[] b;

    public SoundControllerModule() {
        String[] stringArray = b;
        super(stringArray[8], stringArray[4], Category.MISC, false);
        this.sounds = new ActionSetting(stringArray[2], SoundControllerModule::lambda$new$0, SoundControllerModule::lambda$new$1);
        this.killSound = new EnumSetting<SoundControllerModule$Cue>(stringArray[9], SoundControllerModule$Cue.None);
        this.killVolume = new NumberSetting(stringArray[5], 0.1, 2.0, 1.0, 0.05);
        this.killPitch = new NumberSetting(stringArray[3], 0.5, 2.0, 1.0, 0.05);
        this.popSound = new EnumSetting<SoundControllerModule$Cue>(stringArray[0], SoundControllerModule$Cue.None);
        this.popVolume = new NumberSetting(stringArray[6], 0.1, 2.0, 1.0, 0.05);
        this.popPitch = new NumberSetting(stringArray[10], 0.5, 2.0, 1.0, 0.05);
        instance = this;
        this.addSettings(this.sounds, this.killSound, this.killVolume, this.killPitch, this.popSound, this.popVolume, this.popPitch);
        this.killSound.children(this.killVolume, this.killPitch);
        this.popSound.children(this.popVolume, this.popPitch);
    }

    public static boolean active() {
        boolean bl;
        block6: {
            block5: {
                SoundControllerModule soundControllerModule;
                boolean bl2;
                block4: {
                    SoundControllerModule soundControllerModule2 = instance;
                    bl2 = AmbienceModule.b;
                    soundControllerModule = soundControllerModule2;
                    if (bl2) break block4;
                    if (soundControllerModule == null) break block5;
                    soundControllerModule = soundControllerModule2;
                }
                bl = soundControllerModule.isEnabled();
                if (bl2) break block6;
                if (!bl) break block5;
                bl = true;
                break block6;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean replacesTotemSound() {
        boolean bl;
        block5: {
            block4: {
                if (!SoundControllerModule.active() || SoundControllerModule.instance.popSound.getValue() == SoundControllerModule$Cue.None) break block4;
                bl = true;
                break block5;
            }
            bl = false;
        }
        return bl;
    }

    public static void onOwnKill() {
        block4: {
            if (SoundControllerModule.active() && SoundControllerModule.instance.killSound.getValue() != SoundControllerModule$Cue.None) break block4;
            return;
        }
        MinecraftClient.method_1551().method_1483().method_4873((SoundInstance)PositionedSoundInstance.method_4757((SoundEvent)SoundControllerModule.instance.killSound.getValue().event, (float)SoundControllerModule.instance.killPitch.getValueFloat(), (float)SoundControllerModule.instance.killVolume.getValueFloat()));
    }

    public static void onPop(UUID uUID) {
        block14: {
            PlayerEntity playerEntity;
            MinecraftClient minecraftClient;
            block15: {
                PlayerEntity playerEntity2;
                boolean bl;
                block13: {
                    MinecraftClient minecraftClient2;
                    block11: {
                        block12: {
                            bl = AmbienceModule.b;
                            if (!SoundControllerModule.replacesTotemSound()) {
                                return;
                            }
                            minecraftClient = MinecraftClient.method_1551();
                            minecraftClient2 = minecraftClient;
                            if (bl) break block11;
                            if (minecraftClient2.field_1687 != null) break block12;
                            playerEntity2 = null;
                            break block13;
                        }
                        minecraftClient2 = minecraftClient;
                    }
                    playerEntity2 = minecraftClient2.field_1687.method_18470(uUID);
                }
                playerEntity = playerEntity2;
                if (bl) break block14;
                if (playerEntity != null) break block15;
                return;
            }
            minecraftClient.field_1687.method_8486(playerEntity.method_23317(), playerEntity.method_23318(), playerEntity.method_23321(), SoundControllerModule.instance.popSound.getValue().event, SoundCategory.field_15248, SoundControllerModule.instance.popVolume.getValueFloat(), SoundControllerModule.instance.popPitch.getValueFloat(), false);
        }
    }

    @Override
    public void writeExtra(JsonObject jsonObject) {
        SoundOverrides.write(jsonObject);
    }

    @Override
    public void readExtra(JsonObject jsonObject) {
        SoundOverrides.read(jsonObject);
    }

    private static void lambda$new$1() {
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        if (Im.available()) {
            minecraftClient.method_1507((Screen)new SoundEditor());
        }
    }

    private static String lambda$new$0() {
        Object object;
        block6: {
            int n;
            block4: {
                block5: {
                    boolean bl = AmbienceModule.b;
                    n = SoundOverrides.isEmpty();
                    if (bl) break block4;
                    if (n == 0) break block5;
                    object = b[1];
                    break block6;
                }
                n = SoundOverrides.all().size();
            }
            object = n + b[7];
        }
        return object;
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[11];
                var3_1 = 0;
                var2_2 = "[S @\u0015\"*`LtV\u0017w4k\u0005JQ$Q\u0001\u0006\\S!K\u001cq\u0005_U F\u00109]Y P\u0016gz`NtV\u0011n?a_1\u0005\u0019l#/O;P\u0016fv/]:AXr3lWtN\u0011n6/]:AXr5\u007f\u001c'J\rl>|\u0006YS8P\u0015g\u0006YS8P\u0015g\u0007/O;P\u0016f)\u0010\\S!K\u001c\"\u0019`R W\u0017n6jN";
                var4_3 = "[S @\u0015\"*`LtV\u0017w4k\u0005JQ$Q\u0001\u0006\\S!K\u001cq\u0005_U F\u00109]Y P\u0016gz`NtV\u0011n?a_1\u0005\u0019l#/O;P\u0016fv/]:AXr3lWtN\u0011n6/]:AXr5\u007f\u001c'J\rl>|\u0006YS8P\u0015g\u0006YS8P\u0015g\u0007/O;P\u0016f)\u0010\\S!K\u001c\"\u0019`R W\u0017n6jN".length();
                var1_4 = 15;
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
                    var2_2 = "DU8IXq5zR0\u0005_U F\u0010";
                    var4_3 = "DU8IXq5zR0\u0005_U F\u0010".length();
                    var1_4 = 10;
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
            var6_6 = 0;
            do {
                v6 = v6;
                v8 = var6_6;
                v9 = v6[v8];
                switch (var6_6 % 7) {
                    case 0: {
                        v10 = 15;
                        break;
                    }
                    case 1: {
                        v10 = 60;
                        break;
                    }
                    case 2: {
                        v10 = 84;
                        break;
                    }
                    case 3: {
                        v10 = 37;
                        break;
                    }
                    case 4: {
                        v10 = 120;
                        break;
                    }
                    case 5: {
                        v10 = 2;
                        break;
                    }
                    default: {
                        v10 = 90;
                    }
                }
                v6[v8] = (char)(v9 ^ v10);
                ++var6_6;
lbl65:

                v7 = v7;
            } while (v7 > var6_6);
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
        SoundControllerModule.b = var5;
    }
}
