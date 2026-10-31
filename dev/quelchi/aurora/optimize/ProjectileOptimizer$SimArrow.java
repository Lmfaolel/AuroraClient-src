package dev.quelchi.aurora.optimize;

import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.math.Vec3d;

final class ProjectileOptimizer$SimArrow {
    final EntitySpawnS2CPacket packet;
    Vec3d pos;
    Vec3d vel;
    int age;

    ProjectileOptimizer$SimArrow(EntitySpawnS2CPacket entitySpawnS2CPacket) {
        this.packet = entitySpawnS2CPacket;
        this.pos = new Vec3d(entitySpawnS2CPacket.method_11175(), entitySpawnS2CPacket.method_11174(), entitySpawnS2CPacket.method_11176());
        this.vel = entitySpawnS2CPacket.method_73084();
    }
}
