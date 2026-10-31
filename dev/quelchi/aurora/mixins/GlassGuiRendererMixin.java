package dev.quelchi.aurora.mixins;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import dev.quelchi.aurora.glass.render.minecraft.GlassGpu;
import net.minecraft.client.gui.render.GuiRenderer;
import net.minecraft.client.gui.render.state.GuiRenderState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={GuiRenderer.class})
abstract class GlassGuiRendererMixin {
    @Shadow
    @Final
    private GuiRenderState field_59914;

    GlassGuiRendererMixin() {
    }

    @Inject(method={"method_70890(Lcom/mojang/blaze3d/buffers/GpuBufferSlice;)V"}, at={@At(value="HEAD")})
    private void aurora$prepareGlass(GpuBufferSlice gpuBufferSlice, CallbackInfo callbackInfo) {
        GlassGpu.prepare(this.field_59914);
    }

    @Inject(method={"close"}, at={@At(value="HEAD")})
    private void aurora$closeGlass(CallbackInfo callbackInfo) {
        GlassGpu.close();
    }
}
