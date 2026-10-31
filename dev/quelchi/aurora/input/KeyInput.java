package dev.quelchi.aurora.input;

import dev.quelchi.aurora.input.FreeLook;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public final class KeyInput {
    private KeyInput() {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean isDown(MinecraftClient minecraftClient, KeyBinding keyBinding) {
        int n;
        block22: {
            InputUtil.Key key;
            int n2;
            long l;
            InputUtil.Key key2;
            block23: {
                block21: {
                    key2 = KeyBindingHelper.getBoundKeyOf((KeyBinding)keyBinding);
                    l = minecraftClient.method_22683().method_4490();
                    n2 = FreeLook.a;
                    key = key2;
                    if (n2 != 0) break block21;
                    if (key == null) break block22;
                    key = key2;
                }
                if (n2 != 0) break block23;
                if (key == InputUtil.field_16237) break block22;
                key = key2;
            }
            n = key.method_1444();
            if (n2 != 0) return n != 0;
            int n3 = n;
            if (n3 >= 0) {
                InputUtil.Type type;
                block24: {
                    InputUtil.Type type2;
                    block25: {
                        type2 = key2.method_1442();
                        type = type2;
                        if (n2 != 0) break block24;
                        if (type != InputUtil.Type.field_1672) break block25;
                        boolean bl = GLFW.glfwGetMouseButton((long)l, (int)n3);
                        if (n2 != 0) return bl;
                        if (!bl) return false;
                        return true;
                    }
                    type = type2;
                }
                if (type != InputUtil.Type.field_1668) break block22;
                boolean bl = GLFW.glfwGetKey((long)l, (int)n3);
                if (n2 != 0) return bl;
                if (!bl) return false;
                return true;
            }
        }
        n = keyBinding.method_1434();
        return n != 0;
    }

    public static boolean mouseDown(MinecraftClient minecraftClient, int n) {
        boolean bl;
        block4: {
            block5: {
                int n2 = FreeLook.a;
                bl = GLFW.glfwGetMouseButton((long)minecraftClient.method_22683().method_4490(), (int)n);
                if (n2 != 0) break block4;
                if (!bl) break block5;
                bl = true;
                break block4;
            }
            bl = false;
        }
        return bl;
    }

    public static String label(KeyBinding keyBinding) {
        return keyBinding.method_16007().getString();
    }
}
