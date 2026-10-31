package dev.quelchi.aurora.mixins;

import com.mojang.blaze3d.opengl.GlStateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={GlStateManager.class})
public interface GlStateManagerAccessor {
    @Accessor(value="readFbo")
    public static int getReadFbo() {
        throw new AssertionError();
    }

    @Accessor(value="readFbo")
    public static void setReadFbo(int n) {
        throw new AssertionError();
    }

    @Accessor(value="writeFbo")
    public static int getWriteFbo() {
        throw new AssertionError();
    }

    @Accessor(value="writeFbo")
    public static void setWriteFbo(int n) {
        throw new AssertionError();
    }
}
