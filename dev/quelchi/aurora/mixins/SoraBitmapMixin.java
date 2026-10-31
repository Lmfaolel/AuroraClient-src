package dev.quelchi.aurora.mixins;

import net.minecraft.client.texture.NativeImage;
import org.lwjgl.util.freetype.FT_Face;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value={NativeImage.class})
public class SoraBitmapMixin {
    @ModifyArg(method={"method_4316"}, at={@At(value="INVOKE", target="Lorg/lwjgl/util/freetype/FreeType;FT_Load_Glyph(Lorg/lwjgl/util/freetype/FT_Face;II)I", remap=false)}, index=2)
    private int aurora$unhinted(FT_Face fT_Face, int n, int n2) {
        String string = fT_Face.family_nameString();
        return string != null && string.startsWith("Sora") ? n2 | 2 : n2;
    }
}
