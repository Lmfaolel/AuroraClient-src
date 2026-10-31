package dev.quelchi.aurora.render;

import dev.quelchi.aurora.render.EffectEngine;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.MacWindowUtil;
import net.minecraft.util.Util;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

public final class WindowIcon {
    private static boolean done;
    private static final String[] a;

    private WindowIcon() {
    }

    
    public static void applyOnce() {
        MinecraftClient minecraftClient2;
        int n = EffectEngine.a;
        if (done) {
            return;
        }
        MinecraftClient minecraftClient = minecraftClient2 = MinecraftClient.method_1551();
        if (n == 0) {
            if (minecraftClient == null) return;
            minecraftClient = minecraftClient2;
        }
        if (n == 0) {
            if (minecraftClient.method_22683() == null) {
                return;
            }
            minecraftClient = minecraftClient2;
        }
        long l = minecraftClient.method_22683().method_4490();
        if (n == 0) {
            if (l == 0L) {
                return;
            }
            done = true;
            if (n != 0) return;
        }
        if (Util.method_668() == Util.OperatingSystem.field_1137) {
            try {
                MacWindowUtil.method_41718(WindowIcon::lambda$applyOnce$0);
                return;
            }
            catch (Throwable throwable) {
            }
            return;
        }
        try {
            MemoryStack memoryStack;
            block32: {
                ByteBuffer byteBuffer;
                IntBuffer intBuffer;
                IntBuffer intBuffer2;
                MemoryStack memoryStack2;
                block29: {
                    MemoryStack memoryStack3;
                    block30: {
                        ByteBuffer byteBuffer2 = WindowIcon.readResource(a[1]);
                        if (byteBuffer2 == null) {
                            return;
                        }
                        memoryStack2 = MemoryStack.stackPush();
                        try {
                            intBuffer2 = memoryStack2.mallocInt(1);
                            intBuffer = memoryStack2.mallocInt(1);
                            IntBuffer intBuffer3 = memoryStack2.mallocInt(1);
                            byteBuffer = STBImage.stbi_load_from_memory((ByteBuffer)byteBuffer2, (IntBuffer)intBuffer2, (IntBuffer)intBuffer, (IntBuffer)intBuffer3, (int)4);
                            if (byteBuffer != null) break block29;
                            memoryStack3 = memoryStack2;
                            if (n != 0) break block30;
                        }
                        catch (Throwable throwable) {
                            if (n != 0) throw throwable;
                            if (memoryStack2 == null) throw throwable;
                            try {
                                memoryStack2.close();
                                throw throwable;
                            }
                            catch (Throwable throwable2) {
                                throwable.addSuppressed(throwable2);
                            }
                            throw throwable;
                        }
                        if (memoryStack3 == null) return;
                        memoryStack3 = memoryStack2;
                    }
                    memoryStack3.close();
                    return;
                }
                try {
                    GLFWImage.Buffer buffer = GLFWImage.malloc((int)1, (MemoryStack)memoryStack2);
                    ((GLFWImage.Buffer)buffer.position(0)).width(intBuffer2.get(0)).height(intBuffer.get(0)).pixels(byteBuffer);
                    GLFW.glfwSetWindowIcon((long)l, (GLFWImage.Buffer)buffer);
                }
                finally {
                    STBImage.stbi_image_free((ByteBuffer)byteBuffer);
                }
                memoryStack = memoryStack2;
                if (n != 0) break block32;
                if (memoryStack == null) return;
                memoryStack = memoryStack2;
            }
            memoryStack.close();
            return;
        }
        catch (Throwable throwable) {
        }
    }

    
    private static ByteBuffer readResource(String var0) throws Exception {
        block18: {
            block17: {
                block14: {
                    block16: {
                        block15: {
                            var2_1 = WindowIcon.class.getResourceAsStream(var0);
                            var1_2 = EffectEngine.a;
                            try {
                                v0 = var2_1;
                                if (var1_2 == 0) {
                                    if (v0 != null) break block14;
                                }
                                var3_3 = null;
                            }
                            catch (Throwable var3_5) {
                                block19: {
                                    if (var1_2 != 0 || var2_1 == null) break block19;
                                    try {
                                        var2_1.close();
                                    }
                                    catch (Throwable var4_7) {
                                        var3_5.addSuppressed(var4_7);
                                    }
                                }
                                throw var3_5;
                            }
                            v3 = var2_1;
                            if (var1_2 != 0) break block15;
                            if (v3 == null) break block16;
                            v3 = var2_1;
                        }
                        v3.close();
                    }
                    return var3_3;
                }
                v0 = var2_1;
lbl41:

                var3_4 = v0.readAllBytes();
                var4_6 = BufferUtils.createByteBuffer((int)var3_4.length);
                var4_6.put(var3_4).flip();
                var5_8 = var4_6;
                v5 = var2_1;
                if (var1_2 != 0) break block17;
                if (v5 == null) break block18;
                v5 = var2_1;
            }
            v5.close();
        }
        return var5_8;
    }

    private static InputStream lambda$applyOnce$0() throws IOException {
        return WindowIcon.class.getResourceAsStream(a[0]);
    }

    
    static {
        block12: {
            var5 = new String[2];
            var3_1 = 0;
            var2_2 = "\u00005M%E\u0012#\u00005K$O\u00141\u0000=]9NH A3\u0017\u00005M%E\u0012#\u00005K$O\u00141\u0000=]9NH A3";
            var4_3 = "\u00005M%E\u0012#\u00005K$O\u00141\u0000=]9NH A3\u0017\u00005M%E\u0012#\u00005K$O\u00141\u0000=]9NH A3".length();
            var1_4 = 23;
            var0_5 = -1;
lbl7:

            while (true) {
                continue;
                break;
            }
lbl9:

            while (true) {
                var5[var3_1++] = new String(v0).intern();
                if ((var0_5 += var1_4) < var4_3) {
                    var1_4 = var2_2.charAt(var0_5);
                    continue;
                }
                break block12;
                break;
            }
            v1 = ++var0_5;
            v2 = var2_2.substring(v1, v1 + var1_4).toCharArray();
            v0 = v2;
            v3 = v2.length;
            var6_6 = 0;
            do {
                v0 = v0;
                v4 = var6_6;
                v5 = v0[v4];
                switch (var6_6 % 7) {
                    case 0: {
                        v6 = 47;
                        break;
                    }
                    case 1: {
                        v6 = 84;
                        break;
                    }
                    case 2: {
                        v6 = 62;
                        break;
                    }
                    case 3: {
                        v6 = 86;
                        break;
                    }
                    case 4: {
                        v6 = 32;
                        break;
                    }
                    case 5: {
                        v6 = 102;
                        break;
                    }
                    default: {
                        v6 = 80;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        WindowIcon.a = var5;
    }
}
