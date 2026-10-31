package dev.quelchi.aurora.shield;

import net.minecraft.util.math.Vec3d;

record ShieldStates$Swing(Vec3d attacker, Vec3d target, long time, boolean wasBlocking) {
}
