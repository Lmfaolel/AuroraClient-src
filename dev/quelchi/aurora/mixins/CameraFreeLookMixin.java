package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.quelchi.aurora.input.FreeLook;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value={Camera.class})
public abstract class CameraFreeLookMixin {
    @WrapOperation(method={"method_19321(Lnet/minecraft/class_1937;Lnet/minecraft/class_1297;ZZF)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_1297;method_5705(F)F")})
    private float aurora$yaw(Entity entity, float f, Operation<Float> operation) {
        return FreeLook.owns(entity) ? FreeLook.yaw() : ((Float)operation.call(new Object[]{entity, Float.valueOf(f)})).floatValue();
    }

    @WrapOperation(method={"method_19321(Lnet/minecraft/class_1937;Lnet/minecraft/class_1297;ZZF)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_1297;method_5695(F)F")})
    private float aurora$pitch(Entity entity, float f, Operation<Float> operation) {
        return FreeLook.owns(entity) ? FreeLook.pitch() : ((Float)operation.call(new Object[]{entity, Float.valueOf(f)})).floatValue();
    }
}
