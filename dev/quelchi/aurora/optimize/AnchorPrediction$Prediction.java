package dev.quelchi.aurora.optimize;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

final class AnchorPrediction$Prediction {
    BlockPos pos;
    BlockState before;
    BlockState after;
    BlockState lastServerState;
    int createdTick;

    private AnchorPrediction$Prediction() {
    }
}
