package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.quelchi.aurora.duck.KeptCrystal;
import dev.quelchi.aurora.prediction.PredictionHandshake;
import net.minecraft.entity.decoration.EndCrystalEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value={EndCrystalEntity.class})
public abstract class EndCrystalEntityMixin
implements KeptCrystal {
    @Unique
    private boolean aurora$kept;
    @Unique
    private long aurora$keptAt;

    @Override
    public void aurora$keep(long l) {
        this.aurora$kept = true;
        this.aurora$keptAt = l;
    }

    @Override
    public boolean aurora$isKept() {
        return this.aurora$kept;
    }

    @Override
    public long aurora$keptAt() {
        return this.aurora$keptAt;
    }

    @ModifyReturnValue(method={"method_5863()Z"}, at={@At(value="RETURN")})
    private boolean aurora$ghostNotHittable(boolean bl) {
        return bl && (((EndCrystalEntity)this).method_5628() >= 0 || PredictionHandshake.isActive());
    }
}
