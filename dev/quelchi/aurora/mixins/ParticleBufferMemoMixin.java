package dev.quelchi.aurora.mixins;

import java.util.Map;
import java.util.function.Function;
import net.minecraft.client.particle.BillboardParticleSubmittable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={BillboardParticleSubmittable.class})
public abstract class ParticleBufferMemoMixin {
    @Unique
    private Object aurora$lastType;
    @Unique
    private Object aurora$lastBuffer;

    @Redirect(method={"method_74323(Lnet/minecraft/class_3940$class_11941;FFFFFFFFFFFFII)V"}, at={@At(value="INVOKE", target="Ljava/util/Map;computeIfAbsent(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;")})
    private Object aurora$sameType(Map<Object, Object> map, Object object, Function<Object, Object> function) {
        if (object == this.aurora$lastType) {
            return this.aurora$lastBuffer;
        }
        Object object2 = map.computeIfAbsent(object, function);
        this.aurora$lastType = object;
        this.aurora$lastBuffer = object2;
        return object2;
    }
}
