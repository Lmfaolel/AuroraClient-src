package dev.quelchi.aurora.sound;

import dev.quelchi.aurora.sound.SoundOverrides;

public record SoundOverrides$Override(float volume, float pitch, boolean muted) {
    public static final SoundOverrides$Override DEFAULT = new SoundOverrides$Override(1.0f, 1.0f, false);

    public boolean isDefault() {
        boolean bl;
        block13: {
            block11: {
                float f;
                float f2;
                boolean bl2;
                block12: {
                    SoundOverrides$Override soundOverrides$Override;
                    block10: {
                        bl2 = SoundOverrides.a;
                        soundOverrides$Override = this;
                        if (bl2) break block10;
                        if (soundOverrides$Override.muted) break block11;
                        soundOverrides$Override = this;
                    }
                    float f = soundOverrides$Override.volume;
                    f = 1.0f;
                    if (bl2) break block12;
                    if (f2 != f) break block11;
                    float f = this.pitch;
                    f = 1.0f;
                }
                float f3 = f2 - f;
                bl = f3 == 0.0f ? 0 : (f3 > 0.0f ? 1 : -1);
                if (bl2) break block13;
                if (bl) break block11;
                bl = true;
                break block13;
            }
            bl = false;
        }
        return bl;
    }
}
