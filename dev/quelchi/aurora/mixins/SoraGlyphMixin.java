package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.font.TrueTypeFont;
import org.lwjgl.util.freetype.FT_Face;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value={TrueTypeFont.class})
public class SoraGlyphMixin {
    private static final int NO_HINTING = 2;

    @ModifyArg(method={"method_61902"}, at={@At(value="INVOKE", target="Lorg/lwjgl/util/freetype/FreeType;FT_Load_Glyph(Lorg/lwjgl/util/freetype/FT_Face;II)I", remap=false)}, index=2)
    private int aurora$unhinted(FT_Face fT_Face, int n, int n2) {
        return SoraGlyphMixin.sora(fT_Face) ? n2 | 2 : n2;
    }

    @ModifyExpressionValue(method={"method_61902"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_9111;method_56146(Lorg/lwjgl/util/freetype/FT_Vector;)F")})
    private float aurora$wholeAdvance(float f, int n, FT_Face fT_Face, int n2) {
        return SoraGlyphMixin.sora(fT_Face) ? (float)Math.round(f) : f;
    }

    @Unique
    private static boolean sora(FT_Face fT_Face) {
        String string = fT_Face.family_nameString();
        return string != null && string.startsWith("Sora");
    }
}
