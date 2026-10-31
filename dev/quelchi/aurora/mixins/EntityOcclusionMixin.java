package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.perf.Occlusion;
import dev.quelchi.aurora.perf.Tweaks;
import dev.quelchi.aurora.render.EffectEngine;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.EntityRenderManager;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.Leashable;
import net.minecraft.entity.PlayerLikeEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={EntityRenderer.class})
public abstract class EntityOcclusionMixin {
    @Shadow
    @Final
    protected EntityRenderManager field_4676;

    @Shadow
    protected abstract Box method_62358(Entity var1);

    @Shadow
    protected abstract boolean method_62406(Entity var1);

    @Shadow
    protected abstract boolean method_3921(Entity var1, double var2);

    @Inject(method={"method_3933"}, at={@At(value="RETURN")}, cancellable=true)
    private void aurora$cullOccluded(Entity entity, Frustum frustum, double d, double d2, double d3, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        Leashable leashable;
        if (((Boolean)callbackInfoReturnable.getReturnValue()).booleanValue() && EffectEngine.hidden(entity)) {
            callbackInfoReturnable.setReturnValue((Object)false);
            return;
        }
        if (!Tweaks.entityCull() || !((Boolean)callbackInfoReturnable.getReturnValue()).booleanValue()) {
            return;
        }
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        if (minecraftClient.field_1687 == null || entity.method_73183() != minecraftClient.field_1687) {
            return;
        }
        if (!this.method_62406(entity)) {
            return;
        }
        if (entity instanceof PlayerLikeEntity || entity instanceof EndCrystalEntity) {
            return;
        }
        if (entity instanceof Leashable && (leashable = (Leashable)entity).method_60952() != null) {
            return;
        }
        if (entity == minecraftClient.method_1560() || minecraftClient.method_27022(entity)) {
            return;
        }
        if (this.method_3921(entity, this.field_4676.method_23168(entity))) {
            return;
        }
        leashable = this.method_62358(entity);
        if (leashable.method_1013()) {
            return;
        }
        double d4 = entity.field_6014 - entity.method_23317();
        double d5 = entity.field_6036 - entity.method_23318();
        double d6 = entity.field_5969 - entity.method_23321();
        if (Occlusion.hidden(entity, (World)minecraftClient.field_1687, d, d2, d3, leashable.field_1323 - 1.0 + Math.min(d4, 0.0), leashable.field_1322 - 3.0 + Math.min(d5, 0.0), leashable.field_1321 - 1.0 + Math.min(d6, 0.0), leashable.field_1320 + 1.0 + Math.max(d4, 0.0), leashable.field_1325 + 1.0 + Math.max(d5, 0.0), leashable.field_1324 + 1.0 + Math.max(d6, 0.0))) {
            callbackInfoReturnable.setReturnValue((Object)false);
        }
    }
}
