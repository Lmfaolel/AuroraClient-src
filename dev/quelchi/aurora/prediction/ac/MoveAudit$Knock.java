package dev.quelchi.aurora.prediction.ac;

import net.minecraft.util.math.Vec3d;

final class MoveAudit$Knock {
    final Vec3d v;
    final boolean add;
    final long at;
    double bestKept;

    MoveAudit$Knock(Vec3d vec3d, boolean bl, long l) {
        this.v = vec3d;
        this.add = bl;
        this.at = l;
    }
}
