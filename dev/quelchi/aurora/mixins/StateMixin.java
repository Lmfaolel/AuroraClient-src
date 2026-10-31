package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.perf.Tweaks;
import java.util.Map;
import net.minecraft.state.State;
import net.minecraft.state.property.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={State.class})
public abstract class StateMixin {
    @Shadow
    private Map<Property<?>, Object[]> field_24741;

    @Inject(method={"method_28496"}, at={@At(value="TAIL")})
    private void aurora$shareRows(Map<Map<Property<?>, Comparable<?>>, ?> map, CallbackInfo callbackInfo) {
        if (!Tweaks.STATE_TABLES) {
            return;
        }
        for (Property property : this.field_24741.keySet().toArray(new Property[0])) {
            Object[] objectArray;
            Map<Property<?>, Object[]> map2;
            Object[] objectArray2 = this.field_24741.get(property);
            if (objectArray2.length == 0 || objectArray2[0] == this || !((map2 = objectArray2[0]) instanceof State)) continue;
            State state = (State)map2;
            map2 = ((StateMixin)state).field_24741;
            if (map2 == null || (objectArray = map2.get(property)) == null || objectArray.length != objectArray2.length) continue;
            this.field_24741.put(property, objectArray);
        }
    }
}
