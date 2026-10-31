package dev.quelchi.aurora.prediction;

import dev.quelchi.aurora.prediction.BlockQuery;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

public final class ClientWorldQuery
implements BlockQuery {
    private final ClientWorld world;

    public ClientWorldQuery(ClientWorld clientWorld) {
        this.world = clientWorld;
    }

    @Override
    public float blastResistance(int n, int n2, int n3) {
        return this.world.method_8320(new BlockPos(n, n2, n3)).method_26204().method_9520();
    }

    @Override
    public boolean isAir(int n, int n2, int n3) {
        return this.world.method_8320(new BlockPos(n, n2, n3)).method_26215();
    }

    @Override
    public boolean topSturdy(int n, int n2, int n3) {
        BlockPos blockPos = new BlockPos(n, n2, n3);
        return this.world.method_8320(blockPos).method_26206((BlockView)this.world, blockPos, Direction.field_11036);
    }
}
