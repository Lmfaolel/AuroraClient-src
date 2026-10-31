package dev.quelchi.aurora.optimize;

import net.minecraft.entity.EntityType;

record ProjectileOptimizer$SpawnEntry(int id, EntityType<?> type, double x, double y, double z, int tick) {
}
