package dev.quelchi.aurora.prediction;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

final class HolePredictor$Hole {
    final Vec3d center;
    final long created;
    long confirmedAt = a;
    final Map<BlockPos, BlockState> originals = new HashMap<BlockPos, BlockState>();
    final Map<BlockPos, BlockState> predicted = new HashMap<BlockPos, BlockState>();
    final Map<BlockPos, BlockState> held = new HashMap<BlockPos, BlockState>();
    private static final long a;

    HolePredictor$Hole(Vec3d vec3d, long l) {
        this.center = vec3d;
        this.created = l;
    }

    
    static {
        long l = 1340423021526154625L;
        a = 0xED65DC5D212D827EL ^ l;
    }
}
