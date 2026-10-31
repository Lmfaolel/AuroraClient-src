package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.duck.GuiLayerMask;
import java.util.Arrays;
import net.minecraft.client.gui.ScreenRect;
import net.minecraft.client.gui.render.state.GuiElementRenderState;
import net.minecraft.client.gui.render.state.ItemGuiElementRenderState;
import net.minecraft.client.gui.render.state.SimpleGuiElementRenderState;
import net.minecraft.client.gui.render.state.TextGuiElementRenderState;
import net.minecraft.client.gui.render.state.special.SpecialGuiElementRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets={"net/minecraft/class_11246$class_11276"})
public abstract class GuiLayerMaskMixin
implements GuiLayerMask {
    @Unique
    private long aurora$mask;
    @Unique
    private int[] aurora$rects = new int[32];
    @Unique
    private Object[] aurora$elems = new Object[8];
    @Unique
    private int aurora$n;

    @Override
    public long aurora$mask() {
        return this.aurora$mask;
    }

    @Override
    public Object aurora$hit(ScreenRect screenRect) {
        int n = screenRect.method_49620();
        int n2 = screenRect.method_49618();
        int n3 = screenRect.method_49621();
        int n4 = screenRect.method_49619();
        int[] nArray = this.aurora$rects;
        int n5 = 0;
        int n6 = 0;
        while (n5 < this.aurora$n) {
            if (nArray[n6] < n3 && nArray[n6 + 2] > n && nArray[n6 + 1] < n4 && nArray[n6 + 3] > n2) {
                return this.aurora$elems[n5];
            }
            ++n5;
            n6 += 4;
        }
        return null;
    }

    @Unique
    private void aurora$add(GuiElementRenderState guiElementRenderState) {
        ScreenRect screenRect = guiElementRenderState.comp_4274();
        if (screenRect == null) {
            return;
        }
        this.aurora$mask |= GuiLayerMask.of(screenRect);
        if (this.aurora$n == this.aurora$elems.length) {
            this.aurora$elems = Arrays.copyOf(this.aurora$elems, this.aurora$n * 2);
            this.aurora$rects = Arrays.copyOf(this.aurora$rects, this.aurora$n * 8);
        }
        int n = this.aurora$n * 4;
        this.aurora$rects[n] = screenRect.method_49620();
        this.aurora$rects[n + 1] = screenRect.method_49618();
        this.aurora$rects[n + 2] = screenRect.method_49621();
        this.aurora$rects[n + 3] = screenRect.method_49619();
        this.aurora$elems[this.aurora$n++] = guiElementRenderState;
    }

    @Inject(method={"method_71080(Lnet/minecraft/class_11245;)V"}, at={@At(value="HEAD")})
    private void aurora$item(ItemGuiElementRenderState itemGuiElementRenderState, CallbackInfo callbackInfo) {
        this.aurora$add((GuiElementRenderState)itemGuiElementRenderState);
    }

    @Inject(method={"method_71081(Lnet/minecraft/class_11247;)V"}, at={@At(value="HEAD")})
    private void aurora$text(TextGuiElementRenderState textGuiElementRenderState, CallbackInfo callbackInfo) {
        this.aurora$add((GuiElementRenderState)textGuiElementRenderState);
    }

    @Inject(method={"method_71082(Lnet/minecraft/class_11256;)V"}, at={@At(value="HEAD")})
    private void aurora$special(SpecialGuiElementRenderState specialGuiElementRenderState, CallbackInfo callbackInfo) {
        this.aurora$add((GuiElementRenderState)specialGuiElementRenderState);
    }

    @Inject(method={"method_71079(Lnet/minecraft/class_11244;)V"}, at={@At(value="HEAD")})
    private void aurora$simple(SimpleGuiElementRenderState simpleGuiElementRenderState, CallbackInfo callbackInfo) {
        this.aurora$add((GuiElementRenderState)simpleGuiElementRenderState);
    }
}
