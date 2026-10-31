package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.perf.Occlusion;
import dev.quelchi.aurora.perf.Tweaks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.EndGatewayBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.entity.BlockEntityRenderManager;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.state.BlockEntityRenderState;
import net.minecraft.client.render.command.ModelCommandRenderer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={BlockEntityRenderManager.class})
public abstract class BlockEntityOcclusionMixin {
    @Shadow
    private Vec3d field_63069;

    @Shadow
    public abstract <E extends BlockEntity, S extends BlockEntityRenderState> BlockEntityRenderer<E, S> method_3550(E var1);

    @Inject(method={"method_74348"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$cullOccluded(BlockEntity blockEntity, float f, ModelCommandRenderer.CrumblingOverlayCommand crumblingOverlayCommand, CallbackInfoReturnable<BlockEntityRenderState> callbackInfoReturnable) {
        if (!Tweaks.blockEntityCull() || this.field_63069 == null) {
            return;
        }
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        if (minecraftClient.field_1687 == null || blockEntity.method_10997() != minecraftClient.field_1687) {
            return;
        }
        BlockEntityRenderer blockEntityRenderer = this.method_3550(blockEntity);
        if (blockEntityRenderer == null || blockEntityRenderer.method_3563() || blockEntity instanceof EndGatewayBlockEntity) {
            return;
        }
        BlockPos blockPos = blockEntity.method_11016();
        if (Occlusion.hidden(blockEntity, (World)minecraftClient.field_1687, this.field_63069.field_1352, this.field_63069.field_1351, this.field_63069.field_1350, blockPos.method_10263() - 1, blockPos.method_10264() - 1, blockPos.method_10260() - 1, blockPos.method_10263() + 2, blockPos.method_10264() + 2, blockPos.method_10260() + 2)) {
            callbackInfoReturnable.setReturnValue(null);
        }
    }
}
