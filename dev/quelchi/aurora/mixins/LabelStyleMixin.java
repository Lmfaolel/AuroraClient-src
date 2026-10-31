package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.quelchi.aurora.module.modules.NametagsModule;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.command.LabelCommandRenderer;
import net.minecraft.text.Text;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value={LabelCommandRenderer.class})
public abstract class LabelStyleMixin {
    @WrapOperation(method={"method_73014"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_327;method_27522(Lnet/minecraft/class_2561;FFIZLorg/joml/Matrix4f;Lnet/minecraft/class_4597;Lnet/minecraft/class_327$class_6415;II)V")})
    private void aurora$styleLabel(TextRenderer textRenderer, Text text, float f, float f2, int n, boolean bl, Matrix4f matrix4f, VertexConsumerProvider vertexConsumerProvider, TextRenderer.TextLayerType textLayerType, int n2, int n3, Operation<Void> operation) {
        NametagsModule nametagsModule = NametagsModule.get();
        if (nametagsModule == null || !nametagsModule.isEnabled()) {
            operation.call(new Object[]{textRenderer, text, Float.valueOf(f), Float.valueOf(f2), n, bl, matrix4f, vertexConsumerProvider, textLayerType, n2, n3});
            return;
        }
        if (textLayerType == TextRenderer.TextLayerType.field_33994 || n != -1) {
            if (nametagsModule.plainBackground()) {
                n2 = nametagsModule.backgroundColor();
            } else {
                nametagsModule.drawBackground(textRenderer, text, f, f2, matrix4f, vertexConsumerProvider, textLayerType, n3);
                n2 = 0;
            }
        }
        operation.call(new Object[]{textRenderer, text, Float.valueOf(f), Float.valueOf(f2), n, nametagsModule.textShadow(), matrix4f, vertexConsumerProvider, textLayerType, n2, n3});
    }
}
