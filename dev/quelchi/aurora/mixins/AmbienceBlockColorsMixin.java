package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.ambience.AmbienceColors;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import java.util.function.ToIntFunction;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.biome.GrassColors;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={BlockColors.class})
public abstract class AmbienceBlockColorsMixin {
    @Unique
    private static BlockColorProvider aurora$tint(ToIntFunction<AmbienceModule> toIntFunction, BlockColorProvider blockColorProvider) {
        return (arg_0, arg_1, arg_2, arg_3) -> AmbienceBlockColorsMixin.lambda$aurora$tint$0(toIntFunction, blockColorProvider, arg_0, arg_1, arg_2, arg_3);
    }

    @Inject(method={"method_1689"}, at={@At(value="RETURN")})
    private static void aurora$registerColorProviders(CallbackInfoReturnable<BlockColors> callbackInfoReturnable) {
        BlockColors blockColors = (BlockColors)callbackInfoReturnable.getReturnValue();
        BlockColorProvider blockColorProvider = AmbienceBlockColorsMixin::lambda$aurora$registerColorProviders$1;
        BlockColorProvider blockColorProvider2 = AmbienceBlockColorsMixin::lambda$aurora$registerColorProviders$2;
        BlockColorProvider blockColorProvider3 = AmbienceBlockColorsMixin::lambda$aurora$registerColorProviders$3;
        BlockColorProvider blockColorProvider4 = AmbienceBlockColorsMixin::lambda$aurora$registerColorProviders$4;
        BlockColorProvider blockColorProvider5 = (arg_0, arg_1, arg_2, arg_3) -> AmbienceBlockColorsMixin.lambda$aurora$registerColorProviders$5(blockColorProvider, arg_0, arg_1, arg_2, arg_3);
        BlockColorProvider blockColorProvider6 = AmbienceBlockColorsMixin::lambda$aurora$registerColorProviders$6;
        blockColors.method_1690(AmbienceBlockColorsMixin.aurora$tint(AmbienceBlockColorsMixin::lambda$aurora$registerColorProviders$7, blockColorProvider), new Block[]{Blocks.field_10219});
        blockColors.method_1690(AmbienceBlockColorsMixin.aurora$tint(AmbienceBlockColorsMixin::lambda$aurora$registerColorProviders$8, blockColorProvider), AmbienceColors.SHORT_GRASS_TARGETS);
        blockColors.method_1690(AmbienceBlockColorsMixin.aurora$tint(AmbienceBlockColorsMixin::lambda$aurora$registerColorProviders$9, blockColorProvider2), new Block[]{Blocks.field_10424});
        blockColors.method_1690(AmbienceBlockColorsMixin.aurora$tint(AmbienceBlockColorsMixin::lambda$aurora$registerColorProviders$10, blockColorProvider6), AmbienceColors.DRY_SHORT_GRASS_TARGETS);
        blockColors.method_1690(AmbienceBlockColorsMixin.aurora$tint(AmbienceBlockColorsMixin::lambda$aurora$registerColorProviders$11, blockColorProvider3), AmbienceColors.TALL_GRASS_TARGETS);
        blockColors.method_1690(AmbienceBlockColorsMixin.aurora$tint(AmbienceBlockColorsMixin::lambda$aurora$registerColorProviders$12, blockColorProvider6), AmbienceColors.DRY_TALL_GRASS_TARGETS);
        blockColors.method_1690(AmbienceBlockColorsMixin.aurora$tint(AmbienceBlockColorsMixin::lambda$aurora$registerColorProviders$13, blockColorProvider4), AmbienceColors.BIOME_LEAF_TARGETS);
        blockColors.method_1690(AmbienceBlockColorsMixin.aurora$tint(AmbienceBlockColorsMixin::lambda$aurora$registerColorProviders$14, AmbienceBlockColorsMixin::lambda$aurora$registerColorProviders$15), new Block[]{Blocks.field_9988});
        blockColors.method_1690(AmbienceBlockColorsMixin.aurora$tint(AmbienceBlockColorsMixin::lambda$aurora$registerColorProviders$16, AmbienceBlockColorsMixin::lambda$aurora$registerColorProviders$17), new Block[]{Blocks.field_10539});
        blockColors.method_1690(AmbienceBlockColorsMixin.aurora$tint(AmbienceBlockColorsMixin::lambda$aurora$registerColorProviders$18, blockColorProvider5), new Block[]{Blocks.field_42750});
    }

    private static int lambda$aurora$registerColorProviders$18(AmbienceModule ambienceModule) {
        return ambienceModule.leaves() ? ambienceModule.leafRgb() : -1;
    }

    private static int lambda$aurora$registerColorProviders$17(BlockState blockState, BlockRenderView blockRenderView, BlockPos blockPos, int n) {
        return -8345771;
    }

    private static int lambda$aurora$registerColorProviders$16(AmbienceModule ambienceModule) {
        return ambienceModule.leaves() ? ambienceModule.leafRgb() : -1;
    }

    private static int lambda$aurora$registerColorProviders$15(BlockState blockState, BlockRenderView blockRenderView, BlockPos blockPos, int n) {
        return -10380959;
    }

    private static int lambda$aurora$registerColorProviders$14(AmbienceModule ambienceModule) {
        return ambienceModule.leaves() ? ambienceModule.leafRgb() : -1;
    }

    private static int lambda$aurora$registerColorProviders$13(AmbienceModule ambienceModule) {
        return ambienceModule.leaves() ? ambienceModule.leafRgb() : -1;
    }

    private static int lambda$aurora$registerColorProviders$12(AmbienceModule ambienceModule) {
        return ambienceModule.tallGrass() ? ambienceModule.tallGrassRgb() : -1;
    }

    private static int lambda$aurora$registerColorProviders$11(AmbienceModule ambienceModule) {
        return ambienceModule.tallGrass() ? ambienceModule.tallGrassRgb() : -1;
    }

    private static int lambda$aurora$registerColorProviders$10(AmbienceModule ambienceModule) {
        return ambienceModule.shortGrass() ? ambienceModule.shortGrassRgb() : -1;
    }

    private static int lambda$aurora$registerColorProviders$9(AmbienceModule ambienceModule) {
        return ambienceModule.shortGrass() ? ambienceModule.shortGrassRgb() : -1;
    }

    private static int lambda$aurora$registerColorProviders$8(AmbienceModule ambienceModule) {
        return ambienceModule.shortGrass() ? ambienceModule.shortGrassRgb() : -1;
    }

    private static int lambda$aurora$registerColorProviders$7(AmbienceModule ambienceModule) {
        return ambienceModule.grassBlocks() ? ambienceModule.grassBlockRgb() : -1;
    }

    private static int lambda$aurora$registerColorProviders$6(BlockState blockState, BlockRenderView blockRenderView, BlockPos blockPos, int n) {
        return -1;
    }

    private static int lambda$aurora$registerColorProviders$5(BlockColorProvider blockColorProvider, BlockState blockState, BlockRenderView blockRenderView, BlockPos blockPos, int n) {
        return n != 0 ? -1 : blockColorProvider.getColor(blockState, blockRenderView, blockPos, n);
    }

    private static int lambda$aurora$registerColorProviders$4(BlockState blockState, BlockRenderView blockRenderView, BlockPos blockPos, int n) {
        return blockRenderView != null && blockPos != null ? BiomeColors.method_4966((BlockRenderView)blockRenderView, (BlockPos)blockPos) : -12012264;
    }

    private static int lambda$aurora$registerColorProviders$3(BlockState blockState, BlockRenderView blockRenderView, BlockPos blockPos, int n) {
        if (blockRenderView == null || blockPos == null) {
            return GrassColors.method_49724();
        }
        return BiomeColors.method_4962((BlockRenderView)blockRenderView, (BlockPos)(blockState.method_11654((Property)TallPlantBlock.field_10929) == DoubleBlockHalf.field_12609 ? blockPos.method_10074() : blockPos));
    }

    private static int lambda$aurora$registerColorProviders$2(BlockState blockState, BlockRenderView blockRenderView, BlockPos blockPos, int n) {
        return blockRenderView != null && blockPos != null ? BiomeColors.method_4962((BlockRenderView)blockRenderView, (BlockPos)blockPos) : -1;
    }

    private static int lambda$aurora$registerColorProviders$1(BlockState blockState, BlockRenderView blockRenderView, BlockPos blockPos, int n) {
        return blockRenderView != null && blockPos != null ? BiomeColors.method_4962((BlockRenderView)blockRenderView, (BlockPos)blockPos) : GrassColors.method_49724();
    }

    private static int lambda$aurora$tint$0(ToIntFunction toIntFunction, BlockColorProvider blockColorProvider, BlockState blockState, BlockRenderView blockRenderView, BlockPos blockPos, int n) {
        int n2;
        AmbienceModule ambienceModule = AmbienceModule.get();
        if (ambienceModule != null && ambienceModule.isEnabled() && (n2 = toIntFunction.applyAsInt(ambienceModule)) != -1) {
            return n2;
        }
        return blockColorProvider.getColor(blockState, blockRenderView, blockPos, n);
    }
}
