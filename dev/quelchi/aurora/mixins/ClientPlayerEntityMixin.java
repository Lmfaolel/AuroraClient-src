package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.quelchi.aurora.optimize.AnchorOptimizer;
import dev.quelchi.aurora.optimize.ConsumableOptimizer;
import dev.quelchi.aurora.optimize.ProjectileOptimizer;
import dev.quelchi.aurora.popchams.PositionHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ClientPlayerEntity.class})
public abstract class ClientPlayerEntityMixin {
    @Shadow
    private double field_3926;
    @Shadow
    private double field_3940;
    @Shadow
    private double field_3924;
    @Unique
    private double aurora$sentX;
    @Unique
    private double aurora$sentY;
    @Unique
    private double aurora$sentZ;

    @Inject(method={"method_3136"}, at={@At(value="HEAD")})
    private void aurora$beforeMovementSent(CallbackInfo callbackInfo) {
        this.aurora$sentX = this.field_3926;
        this.aurora$sentY = this.field_3940;
        this.aurora$sentZ = this.field_3924;
    }

    @Inject(method={"method_3136"}, at={@At(value="TAIL")})
    private void aurora$afterMovementSent(CallbackInfo callbackInfo) {
        ProjectileOptimizer.onMovementSent(new Vec3d(this.field_3926 - this.aurora$sentX, this.field_3940 - this.aurora$sentY, this.field_3924 - this.aurora$sentZ));
    }

    @Inject(method={"method_5773()V"}, at={@At(value="HEAD")})
    private void aurora$agePopChams(CallbackInfo callbackInfo) {
        PositionHandler.tick();
    }

    @ModifyExpressionValue(method={"method_67270"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_746;method_6115()Z")})
    private boolean aurora$holdUseSlowdown(boolean bl) {
        return bl || ConsumableOptimizer.holdsUseSlowdown();
    }

    @Inject(method={"method_76762(FLnet/minecraft/class_1297;)Lnet/minecraft/class_239;"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$anchorPhantomTarget(float f, Entity entity, CallbackInfoReturnable<HitResult> callbackInfoReturnable) {
        double d = ((ClientPlayerEntity)this).method_55754();
        HitResult hitResult = AnchorOptimizer.phantom(entity, d, f);
        if (hitResult != null) {
            callbackInfoReturnable.setReturnValue((Object)hitResult);
        }
    }
}
