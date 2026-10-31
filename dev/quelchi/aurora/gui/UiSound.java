package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.gui.PreviewScene;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public final class UiSound {
    public static final SoundEvent ON;
    public static final SoundEvent OFF;
    public static final SoundEvent TICK;
    public static final SoundEvent SELECT;
    public static final SoundEvent OPEN;
    public static final SoundEvent CLOSE;
    private static long lastTick;
    private static final String[] a;
    private static final long b;

    private UiSound() {
    }

    private static SoundEvent of(String string) {
        String[] stringArray = a;
        return SoundEvent.method_47908((Identifier)Identifier.method_60655((String)stringArray[2], (String)(stringArray[7] + string)));
    }

    public static void play(SoundEvent soundEvent, float f) {
        MinecraftClient.method_1551().method_1483().method_4873((SoundInstance)PositionedSoundInstance.method_4757((SoundEvent)soundEvent, (float)0.72f, (float)(f * 0.8f)));
    }

    public static void tick() {
        block4: {
            long l;
            block5: {
                int n = PreviewScene.a;
                l = System.currentTimeMillis();
                if (n != 0) break block4;
                if (l - lastTick >= b) break block5;
                return;
            }
            lastTick = l;
            UiSound.play(TICK, 0.3f);
        }
    }

    
    static {
        block21: {
            block20: {
                block19: {
                    var7 = new String[8];
                    var5_1 = 0;
                    var4_2 = "\f{\u001f%\n\u0017d\u001d,\u0002\u0018|\fm\u001c\u0006\u0002~\b$\u001c\u001c\u0006\u0010n\u0016.\r\t\t\u0017d\u001d,\u0002\u0018|\fe\u0005\u0000g\u00158\u000b";
                    var6_3 = "\f{\u001f%\n\u0017d\u001d,\u0002\u0018|\fm\u001c\u0006\u0002~\b$\u001c\u001c\u0006\u0010n\u0016.\r\t\t\u0017d\u001d,\u0002\u0018|\fe\u0005\u0000g\u00158\u000b".length();
                    var3_4 = 4;
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
                        var4_2 = "\u0017b\u0019 \u0003\u0016bT";
                        var6_3 = "\u0017b\u0019 \u0003\u0016bT".length();
                        var3_4 = 4;
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
                var8_7 = 0;
                do {
                    v6 = v6;
                    v8 = var8_7;
                    v9 = v6[v8];
                    switch (var8_7 % 7) {
                        case 0: {
                            v10 = 99;
                            break;
                        }
                        case 1: {
                            v10 = 11;
                            break;
                        }
                        case 2: {
                            v10 = 122;
                            break;
                        }
                        case 3: {
                            v10 = 75;
                            break;
                        }
                        case 4: {
                            v10 = 110;
                            break;
                        }
                        case 5: {
                            v10 = 125;
                            break;
                        }
                        default: {
                            v10 = 35;
                        }
                    }
                    v6[v8] = (char)(v9 ^ v10);
                    ++var8_7;
lbl65:

                    v7 = v7;
                } while (v7 > var8_7);
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
            UiSound.a = var7;
            break block21;
lbl77:

            while (true) {
                continue;
                break;
            }
        }
        var0_8 = 3873839327754790339L;
        while (true)
        UiSound.b = 3873839327754790382L ^ var0_8;
        var2_6 = UiSound.a;
        UiSound.ON = UiSound.of(var2_6[4]);
        UiSound.OFF = UiSound.of(var2_6[1]);
        UiSound.TICK = UiSound.of(var2_6[6]);
        UiSound.SELECT = UiSound.of(var2_6[3]);
        UiSound.OPEN = UiSound.of(var2_6[0]);
        UiSound.CLOSE = UiSound.of(var2_6[5]);
    }
}
