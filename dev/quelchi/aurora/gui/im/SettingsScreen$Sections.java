package dev.quelchi.aurora.gui.im;

import dev.quelchi.aurora.gui.im.ImScreen;

final class SettingsScreen$Sections {
    private SettingsScreen$Sections() {
    }

    static int at(float[] fArray, float f) {
        int n;
        block5: {
            int n2 = 0;
            boolean bl = ImScreen.a;
            for (int i = 1; i < fArray.length; ++i) {
                int n3;
                block6: {
                    float f2 = fArray[i] - (f + 1.0f);
                    n = f2 == 0.0f ? 0 : (f2 < 0.0f ? -1 : 1);
                    if (bl) break block5;
                    if (bl) break block6;
                    if (n > 0) continue;
                    n3 = i;
                }
                n2 = n3;
                if (!bl) continue;
            }
            n = n2;
        }
        return n;
    }
}
