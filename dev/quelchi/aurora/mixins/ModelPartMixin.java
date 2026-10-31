package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.perf.FastModel;
import java.util.List;
import java.util.RandomAccess;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ModelPart.class})
public abstract class ModelPartMixin {
    @Shadow
    @Final
    private List<ModelPart.Cuboid> field_3663;

    @Inject(method={"method_22702"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$indexCuboids(MatrixStack.Entry entry, VertexConsumer vertexConsumer, int n, int n2, int n3, CallbackInfo callbackInfo) {
        if (!FastModel.active()) {
            return;
        }
        List<ModelPart.Cuboid> list = this.field_3663;
        if (!(list instanceof RandomAccess)) {
            return;
        }
        int n4 = list.size();
        for (int i = 0; i < n4; ++i) {
            list.get(i).method_32089(entry, vertexConsumer, n, n2, n3);
        }
        callbackInfo.cancel();
    }
}
