package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.gui.PreviewScene;
import dev.quelchi.aurora.gui.Round$Size;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;

class Round$1
extends LinkedHashMap<Round$Size, Identifier> {
    Round$1(int n, float f, boolean bl) {
        super(n, f, bl);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Round$Size, Identifier> entry) {
        int n;
        block4: {
            block5: {
                int n2 = PreviewScene.a;
                n = this.size();
                if (n2 != 0) break block4;
                if (n > 192) break block5;
                return false;
            }
            MinecraftClient.method_1551().method_1531().method_4615(entry.getValue());
            n = 1;
        }
        return n != 0;
    }
}
