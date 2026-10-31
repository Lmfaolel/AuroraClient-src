package dev.quelchi.aurora.mixins;

import com.mojang.blaze3d.textures.FilterMode;
import net.minecraft.client.font.TextDrawable;
import net.minecraft.client.gui.render.state.GlyphGuiElementRenderState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value={GlyphGuiElementRenderState.class})
public class GlyphSmoothMixin {
    @Shadow
    @Final
    private TextDrawable comp_4637;

    @ModifyArg(method={"comp_4056"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_12136;method_75294(Lcom/mojang/blaze3d/textures/FilterMode;)Lnet/minecraft/class_12137;")})
    private FilterMode aurora$smoothSora(FilterMode filterMode) {
        String string = this.comp_4637.comp_4629().texture().getLabel();
        return string != null && string.startsWith("aurora:sora") ? FilterMode.LINEAR : filterMode;
    }
}
