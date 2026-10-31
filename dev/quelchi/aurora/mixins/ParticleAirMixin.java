package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import java.util.List;
import net.minecraft.block.AbstractBlock;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value={Particle.class})
public abstract class ParticleAirMixin {
    @WrapOperation(method={"method_3069(DDD)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_1297;method_20736(Lnet/minecraft/class_1297;Lnet/minecraft/class_243;Lnet/minecraft/class_238;Lnet/minecraft/class_1937;Ljava/util/List;)Lnet/minecraft/class_243;")})
    private Vec3d aurora$skipInAir(Entity entity, Vec3d vec3d, Box box, World world, List<VoxelShape> list, Operation<Vec3d> operation) {
        if (list.isEmpty() && ParticleAirMixin.aurora$clear(world, box.method_18804(vec3d))) {
            return vec3d;
        }
        return (Vec3d)operation.call(new Object[]{entity, vec3d, box, world, list});
    }

    @Unique
    private static boolean aurora$clear(World world, Box box) {
        int n;
        int n2;
        ChunkSection[] chunkSectionArray;
        WorldChunk worldChunk;
        int n3;
        int n4;
        int n5 = MathHelper.method_15357((double)(box.field_1323 - 1.0E-7));
        int n6 = MathHelper.method_15357((double)(box.field_1320 + 1.0E-7));
        int n7 = MathHelper.method_15357((double)(box.field_1322 - 1.0E-7));
        int n8 = MathHelper.method_15357((double)(box.field_1325 + 1.0E-7));
        int n9 = MathHelper.method_15357((double)(box.field_1321 - 1.0E-7));
        int n10 = MathHelper.method_15357((double)(box.field_1324 + 1.0E-7));
        if ((n6 - n5 + 1) * (n8 - n7 + 1) * (n10 - n9 + 1) > 8) {
            return false;
        }
        int n11 = n5 - 1 >> 4;
        int n12 = n6 + 1 >> 4;
        int n13 = n7 - 1 >> 4;
        int n14 = n8 + 1 >> 4;
        int n15 = n9 - 1 >> 4;
        int n16 = n10 + 1 >> 4;
        for (n4 = n11; n4 <= n12; ++n4) {
            for (n3 = n15; n3 <= n16; ++n3) {
                worldChunk = (WorldChunk)world.method_8398().method_12121(n4, n3, ChunkStatus.field_12803, false);
                if (worldChunk == null) {
                    return false;
                }
                chunkSectionArray = worldChunk.method_12006();
                for (n2 = n13; n2 <= n14; ++n2) {
                    n = world.method_31603(n2);
                    if (n < 0 || n >= chunkSectionArray.length || chunkSectionArray[n].method_38292() || !chunkSectionArray[n].method_19523(AbstractBlock.AbstractBlockState::method_26209)) continue;
                    return false;
                }
            }
        }
        for (n4 = n5; n4 <= n6; ++n4) {
            for (n3 = n9; n3 <= n10; ++n3) {
                worldChunk = (WorldChunk)world.method_8398().method_12121(n4 >> 4, n3 >> 4, ChunkStatus.field_12803, false);
                chunkSectionArray = worldChunk.method_12006();
                for (n2 = n7; n2 <= n8; ++n2) {
                    n = world.method_31603(n2 >> 4);
                    if (n < 0 || n >= chunkSectionArray.length || chunkSectionArray[n].method_38292() || chunkSectionArray[n].method_12254(n4 & 0xF, n2 & 0xF, n3 & 0xF).method_26215()) continue;
                    return false;
                }
            }
        }
        return true;
    }
}
