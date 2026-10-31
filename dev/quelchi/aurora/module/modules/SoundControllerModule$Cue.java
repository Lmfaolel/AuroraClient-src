package dev.quelchi.aurora.module.modules;

import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public final class SoundControllerModule$Cue
extends Enum<SoundControllerModule$Cue> {
    public static final /* enum */ SoundControllerModule$Cue None;
    public static final /* enum */ SoundControllerModule$Cue Orb;
    public static final /* enum */ SoundControllerModule$Cue LevelUp;
    public static final /* enum */ SoundControllerModule$Cue Pling;
    public static final /* enum */ SoundControllerModule$Cue Bell;
    public static final /* enum */ SoundControllerModule$Cue Chime;
    public static final /* enum */ SoundControllerModule$Cue Anvil;
    public static final /* enum */ SoundControllerModule$Cue Glass;
    public static final /* enum */ SoundControllerModule$Cue Firework;
    public static final /* enum */ SoundControllerModule$Cue Twinkle;
    public static final /* enum */ SoundControllerModule$Cue Thunder;
    public static final /* enum */ SoundControllerModule$Cue Beacon;
    public static final /* enum */ SoundControllerModule$Cue Mace;
    public static final /* enum */ SoundControllerModule$Cue Wither;
    final SoundEvent event;
    private static final SoundControllerModule$Cue[] $VALUES;

    public static SoundControllerModule$Cue[] values() {
        return (SoundControllerModule$Cue[])$VALUES.clone();
    }

    public static SoundControllerModule$Cue valueOf(String string) {
        return Enum.valueOf(SoundControllerModule$Cue.class, string);
    }

    private SoundControllerModule$Cue(SoundEvent soundEvent) {
        this.event = soundEvent;
    }

    private static SoundControllerModule$Cue[] $values() {
        return new SoundControllerModule$Cue[]{None, Orb, LevelUp, Pling, Bell, Chime, Anvil, Glass, Firework, Twinkle, Thunder, Beacon, Mace, Wither};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[14];
                var4_1 = 0;
                var3_2 = "YP$\u00046\u0016\u0007OB,\t2\u0014#\u0005\\Y$\u0014*\u0005Z[3\u000e5\u0007O]0\t=\u001d4\u0003TG'\u0007WP3\u00025-6\u0004YP)\u000b\u0004UZ+\u0002\u0004VT&\u0002\u0005KY,\t>\b]\\7\u0002.\u00174p";
                var5_3 = "YP$\u00046\u0016\u0007OB,\t2\u0014#\u0005\\Y$\u0014*\u0005Z[3\u000e5\u0007O]0\t=\u001d4\u0003TG'\u0007WP3\u00025-6\u0004YP)\u000b\u0004UZ+\u0002\u0004VT&\u0002\u0005KY,\t>\b]\\7\u0002.\u00174p".length();
                var2_4 = 6;
                var1_5 = -1;
lbl7:

                while (true) {
                    v0 = ++var1_5;
                    v1 = var3_2.substring(v0, v0 + var2_4);
                    v2 = -1;
                    break block18;
                    break;
                }
lbl12:

                while (true) {
                    var0[var4_1++] = v3.intern();
                    if ((var1_5 += var2_4) < var5_3) {
                        var2_4 = var3_2.charAt(var1_5);
                        continue;
                    }
                    var3_2 = "L\\1\u000f<\n\u0005X],\n<";
                    var5_3 = "L\\1\u000f<\n\u0005X],\n<".length();
                    var2_4 = 6;
                    var1_5 = -1;
lbl21:

                    while (true) {
                        v4 = ++var1_5;
                        v1 = var3_2.substring(v4, v4 + var2_4);
                        v2 = 0;
                        break block18;
                        break;
                    }
                    break;
                }
lbl26:

                while (true) {
                    var0[var4_1++] = v3.intern();
                    if ((var1_5 += var2_4) < var5_3) {
                        var2_4 = var3_2.charAt(var1_5);
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
                        v10 = 27;
                        break;
                    }
                    case 1: {
                        v10 = 53;
                        break;
                    }
                    case 2: {
                        v10 = 69;
                        break;
                    }
                    case 3: {
                        v10 = 103;
                        break;
                    }
                    case 4: {
                        v10 = 89;
                        break;
                    }
                    case 5: {
                        v10 = 120;
                        break;
                    }
                    default: {
                        v10 = 70;
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
        SoundControllerModule$Cue.None = new SoundControllerModule$Cue(null);
        SoundControllerModule$Cue.Orb = new SoundControllerModule$Cue(SoundEvents.field_14627);
        SoundControllerModule$Cue.LevelUp = new SoundControllerModule$Cue(SoundEvents.field_14709);
        SoundControllerModule$Cue.Pling = new SoundControllerModule$Cue((SoundEvent)SoundEvents.field_14622.comp_349());
        SoundControllerModule$Cue.Bell = new SoundControllerModule$Cue((SoundEvent)SoundEvents.field_14793.comp_349());
        SoundControllerModule$Cue.Chime = new SoundControllerModule$Cue(SoundEvents.field_26980);
        SoundControllerModule$Cue.Anvil = new SoundControllerModule$Cue(SoundEvents.field_14833);
        SoundControllerModule$Cue.Glass = new SoundControllerModule$Cue(SoundEvents.field_15081);
        SoundControllerModule$Cue.Firework = new SoundControllerModule$Cue(SoundEvents.field_14917);
        SoundControllerModule$Cue.Twinkle = new SoundControllerModule$Cue(SoundEvents.field_14800);
        SoundControllerModule$Cue.Thunder = new SoundControllerModule$Cue(SoundEvents.field_14865);
        SoundControllerModule$Cue.Beacon = new SoundControllerModule$Cue(SoundEvents.field_14703);
        SoundControllerModule$Cue.Mace = new SoundControllerModule$Cue(SoundEvents.field_49924);
        SoundControllerModule$Cue.Wither = new SoundControllerModule$Cue(SoundEvents.field_14792);
        SoundControllerModule$Cue.$VALUES = SoundControllerModule$Cue.$values();
    }
}
