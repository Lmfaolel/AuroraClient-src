package dev.quelchi.aurora.input;

import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.shell.EmoteWheel;
import dev.quelchi.aurora.input.AuroraKeys;
import dev.quelchi.aurora.input.FreeLook;
import dev.quelchi.aurora.util.KeyUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

public final class EmoteKey {
    private EmoteKey() {
    }

    public static void tick(MinecraftClient minecraftClient) {
        int n;
        int n2;
        block19: {
            block20: {
                block18: {
                    int n3;
                    block17: {
                        n2 = AuroraKeys.emoteCode();
                        int n4 = FreeLook.a;
                        n3 = n2;
                        if (n4 != 0) break block17;
                        if (n3 == -1) break block18;
                        n = n2;
                        if (n4 != 0) break block19;
                        n3 = KeyUtil.isKeyPressedInGame(n) ? 1 : 0;
                    }
                    if (n3 != 0) break block20;
                }
                return;
            }
            n = KeyUtil.isKeyPressed(292);
        }
        if (n != 0) {
            return;
        }
        if (minecraftClient.field_1724 == null) {
            return;
        }
        if (Im.available()) {
            minecraftClient.method_1507((Screen)new EmoteWheel(n2));
        }
    }
}
