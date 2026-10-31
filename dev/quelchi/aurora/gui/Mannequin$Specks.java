package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.gui.PreviewScene;
import dev.quelchi.aurora.render.LocalSpeckSink;
import java.util.Arrays;

final class Mannequin$Specks
implements LocalSpeckSink {
    float[] xyz = new float[2048];
    int[] colors = new int[512];
    int count;

    private Mannequin$Specks() {
    }

    @Override
    public void speck(float f, float f2, float f3, float f4, int n) {
        Mannequin$Specks mannequin$Specks;
        block4: {
            block5: {
                int n2 = PreviewScene.a;
                mannequin$Specks = this;
                if (n2 != 0) break block4;
                if (mannequin$Specks.count != this.colors.length) break block5;
                this.xyz = Arrays.copyOf(this.xyz, this.xyz.length * 2);
                this.colors = Arrays.copyOf(this.colors, this.colors.length * 2);
            }
            mannequin$Specks = this;
        }
        int n3 = mannequin$Specks.count * 4;
        this.xyz[n3] = f;
        this.xyz[n3 + 1] = f2;
        this.xyz[n3 + 2] = f3;
        this.xyz[n3 + 3] = f4;
        this.colors[this.count++] = n;
    }
}
