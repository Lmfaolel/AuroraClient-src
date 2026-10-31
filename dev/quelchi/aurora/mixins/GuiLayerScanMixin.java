package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.duck.GuiLayerMask;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.gui.ScreenRect;
import net.minecraft.client.gui.render.state.GuiRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={GuiRenderState.class})
public abstract class GuiLayerScanMixin {
    @Unique
    private static final Object[] aurora$hit = new Object[1];
    @Unique
    private static final List<Object> aurora$hitList = Arrays.asList(aurora$hit);

    @Redirect(method={"method_71532(Lnet/minecraft/class_8030;)V"}, at={@At(value="FIELD", opcode=180, target="Lnet/minecraft/class_11246$class_11276;field_60050:Ljava/util/List;")})
    private List<?> aurora$simple(@Coerce Object object, ScreenRect screenRect) {
        GuiLayerMask guiLayerMask = (GuiLayerMask)object;
        if ((guiLayerMask.aurora$mask() & GuiLayerMask.of(screenRect)) == 0L) {
            return null;
        }
        Object object2 = guiLayerMask.aurora$hit(screenRect);
        if (object2 == null) {
            return null;
        }
        GuiLayerScanMixin.aurora$hit[0] = object2;
        return aurora$hitList;
    }

    @Redirect(method={"method_71532(Lnet/minecraft/class_8030;)V"}, at={@At(value="FIELD", opcode=180, target="Lnet/minecraft/class_11246$class_11276;field_60051:Ljava/util/List;")})
    private List<?> aurora$item(@Coerce Object object, ScreenRect screenRect) {
        return null;
    }

    @Redirect(method={"method_71532(Lnet/minecraft/class_8030;)V"}, at={@At(value="FIELD", opcode=180, target="Lnet/minecraft/class_11246$class_11276;field_60052:Ljava/util/List;")})
    private List<?> aurora$text(@Coerce Object object, ScreenRect screenRect) {
        return null;
    }

    @Redirect(method={"method_71532(Lnet/minecraft/class_8030;)V"}, at={@At(value="FIELD", opcode=180, target="Lnet/minecraft/class_11246$class_11276;field_60053:Ljava/util/List;")})
    private List<?> aurora$special(@Coerce Object object, ScreenRect screenRect) {
        return null;
    }
}
