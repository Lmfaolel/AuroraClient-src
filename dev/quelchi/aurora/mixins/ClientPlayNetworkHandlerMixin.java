package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.quelchi.aurora.cosmetics.renderer.HitEffects;
import dev.quelchi.aurora.cosmetics.renderer.KillEffects;
import dev.quelchi.aurora.cosmetics.renderer.MaceEffects;
import dev.quelchi.aurora.module.modules.AutoDeafenModule;
import dev.quelchi.aurora.module.modules.AutoTextModule;
import dev.quelchi.aurora.module.modules.CrosshairAddonsModule;
import dev.quelchi.aurora.module.modules.PingHudModule;
import dev.quelchi.aurora.module.modules.SoundControllerModule;
import dev.quelchi.aurora.module.modules.TimeChangerModule;
import dev.quelchi.aurora.module.modules.TotemTweaksModule;
import dev.quelchi.aurora.optimize.AnchorOptimizer;
import dev.quelchi.aurora.optimize.AnchorPrediction;
import dev.quelchi.aurora.optimize.Blast;
import dev.quelchi.aurora.optimize.ConsumableOptimizer;
import dev.quelchi.aurora.optimize.CrossbowOptimizer;
import dev.quelchi.aurora.optimize.ProjectileOptimizer;
import dev.quelchi.aurora.popchams.PositionHandler;
import dev.quelchi.aurora.prediction.HolePredictor;
import dev.quelchi.aurora.prediction.InventoryHold;
import dev.quelchi.aurora.prediction.MeleePrediction;
import dev.quelchi.aurora.prediction.OffhandSwap;
import dev.quelchi.aurora.prediction.PearlPlaceHold;
import dev.quelchi.aurora.prediction.Prediction;
import dev.quelchi.aurora.prediction.SelfDamagePrediction;
import dev.quelchi.aurora.prediction.ac.AntiCheat;
import dev.quelchi.aurora.prediction.ac.MoveAudit;
import dev.quelchi.aurora.prediction.ac.PacketAudit;
import dev.quelchi.aurora.shield.ShieldStates;
import dev.quelchi.aurora.util.TotemPops;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.BlockUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ChunkDeltaUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.CooldownUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityDamageS2CPacket;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityTrackerUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import net.minecraft.network.packet.s2c.play.InventoryS2CPacket;
import net.minecraft.network.packet.s2c.play.PlaySoundFromEntityS2CPacket;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.SetPlayerInventoryS2CPacket;
import net.minecraft.network.packet.s2c.play.UpdateSelectedSlotS2CPacket;
import net.minecraft.network.packet.s2c.play.WorldEventS2CPacket;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;
import net.minecraft.network.packet.s2c.query.PingResultS2CPacket;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value={ClientPlayNetworkHandler.class})
public abstract class ClientPlayNetworkHandlerMixin {
    @Shadow
    private ClientWorld field_3699;
    @Unique
    private boolean aurora$squelchExplosionParticle;
    @Unique
    private double[] aurora$pearlKeep;
    @Unique
    private boolean aurora$pearlConfirmed;

    @Inject(method={"method_11087"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_2656;comp_2199()I", ordinal=0)}, cancellable=true)
    private void aurora$keepLocalCooldown(CooldownUpdateS2CPacket cooldownUpdateS2CPacket, CallbackInfo callbackInfo) {
        if (ProjectileOptimizer.ignoresServerCooldown(cooldownUpdateS2CPacket.comp_3082())) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"method_12666"}, at={@At(value="HEAD")})
    private void aurora$onPingResult(PingResultS2CPacket pingResultS2CPacket, CallbackInfo callbackInfo) {
        PingHudModule.onPingResult(pingResultS2CPacket.comp_2201());
    }

    @Inject(method={"method_11135(Lnet/minecraft/class_2735;)V"}, at={@At(value="HEAD")})
    private void aurora$serverSetSlot(UpdateSelectedSlotS2CPacket updateSelectedSlotS2CPacket, CallbackInfo callbackInfo) {
        if (!MinecraftClient.method_1551().method_18854()) {
            return;
        }
        AntiCheat.onServerSlot();
    }

    @Inject(method={"method_11085"}, at={@At(value="HEAD")})
    private void aurora$projectileHitPlayer(GameStateChangeS2CPacket gameStateChangeS2CPacket, CallbackInfo callbackInfo) {
        if (!MinecraftClient.method_1551().method_18854()) {
            return;
        }
        if (gameStateChangeS2CPacket.method_11491() == GameStateChangeS2CPacket.field_25651) {
            CrosshairAddonsModule.onProjectileHit();
        }
    }

    @Inject(method={"method_11146"}, at={@At(value="HEAD")})
    private void aurora$projectileHit(PlaySoundS2CPacket playSoundS2CPacket, CallbackInfo callbackInfo) {
        if (!MinecraftClient.method_1551().method_18854()) {
            return;
        }
        RegistryEntry registryEntry = playSoundS2CPacket.method_11894();
        ClientPlayNetworkHandlerMixin.aurora$onSound((RegistryEntry<SoundEvent>)registryEntry);
        ClientPlayNetworkHandlerMixin.aurora$shieldBreakAt((RegistryEntry<SoundEvent>)registryEntry, playSoundS2CPacket.method_11890(), playSoundS2CPacket.method_11889(), playSoundS2CPacket.method_11893());
        ClientPlayNetworkHandlerMixin.aurora$maceSmashAt((RegistryEntry<SoundEvent>)registryEntry, playSoundS2CPacket.method_11890(), playSoundS2CPacket.method_11889(), playSoundS2CPacket.method_11893());
    }

    private static void aurora$shieldBreakAt(RegistryEntry<SoundEvent> registryEntry, double d, double d2, double d3) {
        if (registryEntry == null) {
            return;
        }
        if (!MinecraftClient.method_1551().method_18854()) {
            return;
        }
        try {
            if ("item.shield.break".equals(((SoundEvent)registryEntry.comp_349()).comp_3319().method_12832())) {
                ShieldStates.onShieldBreakSound(d, d2, d3);
            }
        }
        catch (Exception exception) {
        }
    }

    @Inject(method={"method_49034"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$damageBlame(EntityDamageS2CPacket entityDamageS2CPacket, CallbackInfo callbackInfo) {
        if (!MinecraftClient.method_1551().method_18854()) {
            return;
        }
        KillEffects.onDamage(entityDamageS2CPacket.comp_1267(), entityDamageS2CPacket.comp_1269());
        MaceEffects.onDamage(entityDamageS2CPacket.comp_1267(), entityDamageS2CPacket.comp_1269());
        HitEffects.onDamage(entityDamageS2CPacket);
        AutoDeafenModule.onDamage(entityDamageS2CPacket.comp_1267(), entityDamageS2CPacket.comp_1269());
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        if (minecraftClient.field_1724 != null && entityDamageS2CPacket.comp_1269() == minecraftClient.field_1724.method_5628() && entityDamageS2CPacket.comp_1267() != minecraftClient.field_1724.method_5628()) {
            CrosshairAddonsModule.onDamageDealt();
        }
        if (MeleePrediction.swallowsDamage(entityDamageS2CPacket)) {
            callbackInfo.cancel();
        }
    }

    private static void aurora$maceSmashAt(RegistryEntry<SoundEvent> registryEntry, double d, double d2, double d3) {
        if (registryEntry == null) {
            return;
        }
        if (!MinecraftClient.method_1551().method_18854()) {
            return;
        }
        try {
            String string = ((SoundEvent)registryEntry.comp_349()).comp_3319().method_12832();
            boolean bl = "item.mace.smash_ground_heavy".equals(string);
            if (!(bl || "item.mace.smash_ground".equals(string) || "item.mace.smash_air".equals(string))) {
                return;
            }
            MaceEffects.onSmash(new Vec3d(d, d2, d3), bl);
        }
        catch (Exception exception) {
        }
    }

    @Inject(method={"method_11125"}, at={@At(value="HEAD")})
    private void aurora$projectileHitFromEntity(PlaySoundFromEntityS2CPacket playSoundFromEntityS2CPacket, CallbackInfo callbackInfo) {
        if (!MinecraftClient.method_1551().method_18854()) {
            return;
        }
        ClientPlayNetworkHandlerMixin.aurora$onSound((RegistryEntry<SoundEvent>)playSoundFromEntityS2CPacket.method_11882());
    }

    private static void aurora$onSound(RegistryEntry<SoundEvent> registryEntry) {
        Identifier identifier;
        if (registryEntry == null) {
            return;
        }
        try {
            identifier = ((SoundEvent)registryEntry.comp_349()).comp_3319();
        }
        catch (Exception exception) {
            return;
        }
        if (identifier == null) {
            return;
        }
        String string = identifier.method_12832();
        if (string.equals("item.shield.break")) {
            CrosshairAddonsModule.onShieldBreak();
        }
    }

    @Inject(method={"method_11079"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$holdTime(WorldTimeUpdateS2CPacket worldTimeUpdateS2CPacket, CallbackInfo callbackInfo) {
        TimeChangerModule timeChangerModule = TimeChangerModule.get();
        if (timeChangerModule != null && timeChangerModule.isEnabled()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"method_11148"}, at={@At(value="HEAD")}, cancellable=true)
    private void divine$onEntityStatus(EntityStatusS2CPacket entityStatusS2CPacket, CallbackInfo callbackInfo) {
        Entity entity;
        if (!MinecraftClient.method_1551().method_18854()) {
            return;
        }
        if (this.field_3699 == null) {
            return;
        }
        byte by = entityStatusS2CPacket.method_11470();
        if (by == 35) {
            if (SelfDamagePrediction.swallowsPop(entityStatusS2CPacket.method_11469((World)this.field_3699))) {
                callbackInfo.cancel();
                return;
            }
            Entity entity2 = entityStatusS2CPacket.method_11469((World)this.field_3699);
            if (entity2 instanceof PlayerEntity) {
                PlayerEntity playerEntity = (PlayerEntity)entity2;
                TotemPops.onPop(playerEntity.method_5667());
                PositionHandler.onTotemStatus(playerEntity);
            }
        } else if (by == 3) {
            Object object = entityStatusS2CPacket.method_11469((World)this.field_3699);
            if (object instanceof PlayerEntity) {
                PlayerEntity playerEntity = (PlayerEntity)object;
                object = AutoTextModule.get();
                if (object != null) {
                    ((AutoTextModule)object).onPlayerDeath(playerEntity);
                }
                if (KillEffects.onDeath(playerEntity)) {
                    SoundControllerModule.onOwnKill();
                }
            }
        } else if (by == 30 && (entity = entityStatusS2CPacket.method_11469((World)this.field_3699)) instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity)entity;
            ShieldStates.onShieldBreakStatus(playerEntity);
        }
    }

    @Inject(method={"method_11146"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$consumableSound(PlaySoundS2CPacket playSoundS2CPacket, CallbackInfo callbackInfo) {
        if (!MinecraftClient.method_1551().method_18854()) {
            return;
        }
        try {
            SoundEvent soundEvent = (SoundEvent)playSoundS2CPacket.method_11894().comp_349();
            if (ConsumableOptimizer.suppressesSound(soundEvent) || CrossbowOptimizer.suppressesSound(soundEvent) || ProjectileOptimizer.suppressesSound((RegistryEntry<SoundEvent>)playSoundS2CPacket.method_11894()) || AnchorOptimizer.suppressesSound(soundEvent, new Vec3d(playSoundS2CPacket.method_11890(), playSoundS2CPacket.method_11889(), playSoundS2CPacket.method_11893()))) {
                callbackInfo.cancel();
            }
        }
        catch (Exception exception) {
        }
    }

    @Inject(method={"method_11109"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$holdOffhandSwapSlot(ScreenHandlerSlotUpdateS2CPacket screenHandlerSlotUpdateS2CPacket, CallbackInfo callbackInfo) {
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        if (!minecraftClient.method_18854()) {
            return;
        }
        if (minecraftClient.field_1724 == null) {
            return;
        }
        int n = screenHandlerSlotUpdateS2CPacket.method_11450();
        int n2 = -1;
        if (screenHandlerSlotUpdateS2CPacket.method_11452() == 0) {
            if (n == 45) {
                n2 = 40;
            } else if (n >= 36 && n < 45) {
                n2 = n - 36;
            }
        } else if (screenHandlerSlotUpdateS2CPacket.method_11452() == minecraftClient.field_1724.field_7512.field_7763 && n >= 0 && n < minecraftClient.field_1724.field_7512.field_7761.size()) {
            Slot slot = (Slot)minecraftClient.field_1724.field_7512.field_7761.get(n);
            if (slot.field_7871 == minecraftClient.field_1724.method_31548()) {
                n2 = slot.method_34266();
            }
        }
        if (n2 < 0) {
            return;
        }
        if (OffhandSwap.refuse(n2, screenHandlerSlotUpdateS2CPacket.method_11449()) | InventoryHold.refuse(n2, screenHandlerSlotUpdateS2CPacket.method_11449())) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"method_61188"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$holdOffhandSwapInventory(SetPlayerInventoryS2CPacket setPlayerInventoryS2CPacket, CallbackInfo callbackInfo) {
        if (!MinecraftClient.method_1551().method_18854()) {
            return;
        }
        if (OffhandSwap.refuse(setPlayerInventoryS2CPacket.comp_2891(), setPlayerInventoryS2CPacket.comp_2892()) | InventoryHold.refuse(setPlayerInventoryS2CPacket.comp_2891(), setPlayerInventoryS2CPacket.comp_2892())) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"method_11153"}, at={@At(value="HEAD")})
    private void aurora$fullInventorySync(InventoryS2CPacket inventoryS2CPacket, CallbackInfo callbackInfo) {
        if (!MinecraftClient.method_1551().method_18854()) {
            return;
        }
        InventoryHold.onFullSync();
    }

    @Inject(method={"method_11109"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$crossbowSlot(ScreenHandlerSlotUpdateS2CPacket screenHandlerSlotUpdateS2CPacket, CallbackInfo callbackInfo) {
        if (!MinecraftClient.method_1551().method_18854()) {
            return;
        }
        if (CrossbowOptimizer.filtersSlotUpdate(screenHandlerSlotUpdateS2CPacket)) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"method_11093"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$consumableTracker(EntityTrackerUpdateS2CPacket entityTrackerUpdateS2CPacket, CallbackInfo callbackInfo) {
        if (!MinecraftClient.method_1551().method_18854()) {
            return;
        }
        if (ConsumableOptimizer.filtersTracker(entityTrackerUpdateS2CPacket) || CrossbowOptimizer.filtersTracker(entityTrackerUpdateS2CPacket)) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"method_11148"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$consumableStopUsing(EntityStatusS2CPacket entityStatusS2CPacket, CallbackInfo callbackInfo) {
        if (!MinecraftClient.method_1551().method_18854()) {
            return;
        }
        if (this.field_3699 == null) {
            return;
        }
        if (entityStatusS2CPacket.method_11469((World)this.field_3699) != MinecraftClient.method_1551().field_1724) {
            return;
        }
        if (ConsumableOptimizer.swallowsStopUsing(entityStatusS2CPacket.method_11470())) {
            callbackInfo.cancel();
        }
    }

    @ModifyArgs(method={"method_11124"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_638;method_8486(DDDLnet/minecraft/class_3414;Lnet/minecraft/class_3419;FFZ)V")})
    private void aurora$silenceDuplicateBlast(Args args) {
        Vec3d vec3d = new Vec3d(((Double)args.get(0)).doubleValue(), ((Double)args.get(1)).doubleValue(), ((Double)args.get(2)).doubleValue());
        HolePredictor.confirm(vec3d);
        int n = Blast.alreadyPlayed(vec3d);
        if (n != 0) {
            args.set(5, (Object)Float.valueOf(0.0f));
            if (n == 2) {
                this.aurora$squelchExplosionParticle = true;
            }
        } else if (ProjectileOptimizer.explosionFromServer(vec3d.field_1352, vec3d.field_1351, vec3d.field_1350)) {
            args.set(5, (Object)Float.valueOf(0.0f));
            this.aurora$squelchExplosionParticle = true;
        }
    }

    @WrapOperation(method={"method_11124"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_638;method_8406(Lnet/minecraft/class_2394;DDDDDD)V")})
    private void aurora$squelchDuplicateGust(ClientWorld clientWorld, ParticleEffect particleEffect, double d, double d2, double d3, double d4, double d5, double d6, Operation<Void> operation) {
        if (this.aurora$squelchExplosionParticle) {
            this.aurora$squelchExplosionParticle = false;
            return;
        }
        operation.call(new Object[]{clientWorld, particleEffect, d, d2, d3, d4, d5, d6});
    }

    @Inject(method={"method_11112"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$projectileSpawn(EntitySpawnS2CPacket entitySpawnS2CPacket, CallbackInfo callbackInfo) {
        if (!MinecraftClient.method_1551().method_18854()) {
            return;
        }
        if (ProjectileOptimizer.swallowsSpawn(entitySpawnS2CPacket)) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"method_11098"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$projectileWorldEvent(WorldEventS2CPacket worldEventS2CPacket, CallbackInfo callbackInfo) {
        if (!MinecraftClient.method_1551().method_18854()) {
            return;
        }
        if (ProjectileOptimizer.swallowsWorldEvent(worldEventS2CPacket.method_11532())) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"method_11136"}, at={@At(value="HEAD")})
    private void aurora$anchorOptimizerBlockUpdate(BlockUpdateS2CPacket blockUpdateS2CPacket, CallbackInfo callbackInfo) {
        if (!MinecraftClient.method_1551().method_18854()) {
            return;
        }
        AnchorOptimizer.onServerBlockUpdate(blockUpdateS2CPacket.method_11309());
    }

    @Inject(method={"method_11100"}, at={@At(value="HEAD")})
    private void aurora$anchorOptimizerDeltaUpdate(ChunkDeltaUpdateS2CPacket chunkDeltaUpdateS2CPacket, CallbackInfo callbackInfo) {
        if (!MinecraftClient.method_1551().method_18854()) {
            return;
        }
        chunkDeltaUpdateS2CPacket.method_30621(ClientPlayNetworkHandlerMixin::lambda$aurora$anchorOptimizerDeltaUpdate$0);
    }

    @Inject(method={"method_11136"}, at={@At(value="TAIL")})
    private void aurora$anchorBlockUpdate(BlockUpdateS2CPacket blockUpdateS2CPacket, CallbackInfo callbackInfo) {
        if (!MinecraftClient.method_1551().method_18854()) {
            return;
        }
        ClientWorld clientWorld = this.field_3699;
        if (clientWorld != null) {
            AnchorPrediction.onServerBlockUpdate(clientWorld, blockUpdateS2CPacket.method_11309(), blockUpdateS2CPacket.method_11308());
        }
    }

    @Inject(method={"method_11100"}, at={@At(value="TAIL")})
    private void aurora$anchorDeltaUpdate(ChunkDeltaUpdateS2CPacket chunkDeltaUpdateS2CPacket, CallbackInfo callbackInfo) {
        if (!MinecraftClient.method_1551().method_18854()) {
            return;
        }
        ClientWorld clientWorld = this.field_3699;
        if (clientWorld == null) {
            return;
        }
        chunkDeltaUpdateS2CPacket.method_30621((arg_0, arg_1) -> ClientPlayNetworkHandlerMixin.lambda$aurora$anchorDeltaUpdate$1(clientWorld, arg_0, arg_1));
    }

    @Inject(method={"method_11132"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_2743;method_11818()I", ordinal=0)}, cancellable=true)
    private void aurora$dropPearlDamageVelocity(EntityVelocityUpdateS2CPacket entityVelocityUpdateS2CPacket, CallbackInfo callbackInfo) {
        if (Prediction.swallowsPearlDamageVelocity(entityVelocityUpdateS2CPacket)) {
            MoveAudit.onOwnDrop(entityVelocityUpdateS2CPacket.method_73085());
            callbackInfo.cancel();
        }
    }

    @Inject(method={"method_11157"}, at={@At(value="TAIL")})
    private void aurora$releaseHeldPlacements(PlayerPositionLookS2CPacket playerPositionLookS2CPacket, CallbackInfo callbackInfo) {
        PacketAudit.onTeleport();
        MoveAudit.onServerTeleport();
        if (!this.aurora$pearlConfirmed) {
            return;
        }
        this.aurora$pearlConfirmed = false;
        PearlPlaceHold.release();
    }

    @Inject(method={"method_11157"}, at={@At(value="HEAD")})
    private void aurora$keepPearlPosition(PlayerPositionLookS2CPacket playerPositionLookS2CPacket, CallbackInfo callbackInfo) {
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        if (!minecraftClient.method_18854()) {
            return;
        }
        this.aurora$pearlKeep = null;
        if (minecraftClient.field_1724 == null || minecraftClient.field_1724.method_5765()) {
            return;
        }
        if (!Prediction.isOwnPearlTeleport(playerPositionLookS2CPacket)) {
            Prediction.onServerCorrection(playerPositionLookS2CPacket);
            return;
        }
        this.aurora$pearlConfirmed = true;
        ClientPlayerEntity clientPlayerEntity = minecraftClient.field_1724;
        this.aurora$pearlKeep = new double[]{clientPlayerEntity.method_23317(), clientPlayerEntity.method_23318(), clientPlayerEntity.method_23321(), clientPlayerEntity.field_6014, clientPlayerEntity.field_6036, clientPlayerEntity.field_5969, clientPlayerEntity.field_6038, clientPlayerEntity.field_5971, clientPlayerEntity.field_5989, clientPlayerEntity.method_18798().field_1352, clientPlayerEntity.method_18798().field_1351, clientPlayerEntity.method_18798().field_1350, clientPlayerEntity.method_36454(), clientPlayerEntity.method_36455(), clientPlayerEntity.field_5982, clientPlayerEntity.field_6004};
    }

    @Inject(method={"method_11157"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_634;method_64897(Lnet/minecraft/class_10182;Ljava/util/Set;Lnet/minecraft/class_1297;Z)Z", shift=At.Shift.AFTER)})
    private void aurora$restorePearlPosition(PlayerPositionLookS2CPacket playerPositionLookS2CPacket, CallbackInfo callbackInfo) {
        double[] dArray = this.aurora$pearlKeep;
        this.aurora$pearlKeep = null;
        ClientPlayerEntity clientPlayerEntity = MinecraftClient.method_1551().field_1724;
        if (dArray == null || clientPlayerEntity == null) {
            return;
        }
        clientPlayerEntity.method_5814(dArray[0], dArray[1], dArray[2]);
        clientPlayerEntity.field_6014 = dArray[3];
        clientPlayerEntity.field_6036 = dArray[4];
        clientPlayerEntity.field_5969 = dArray[5];
        clientPlayerEntity.field_6038 = dArray[6];
        clientPlayerEntity.field_5971 = dArray[7];
        clientPlayerEntity.field_5989 = dArray[8];
        clientPlayerEntity.method_18800(dArray[9], dArray[10], dArray[11]);
        clientPlayerEntity.method_36456((float)dArray[12]);
        clientPlayerEntity.method_36457((float)dArray[13]);
        clientPlayerEntity.field_5982 = (float)dArray[14];
        clientPlayerEntity.field_6004 = (float)dArray[15];
    }

    @Inject(method={"method_43596"}, at={@At(value="HEAD")})
    private void divine$autoTextKill(GameMessageS2CPacket gameMessageS2CPacket, CallbackInfo callbackInfo) {
        if (!MinecraftClient.method_1551().method_18854()) {
            return;
        }
        if (gameMessageS2CPacket.comp_906()) {
            return;
        }
        AutoTextModule autoTextModule = AutoTextModule.get();
        if (autoTextModule != null && autoTextModule.isEnabled()) {
            autoTextModule.onSystemMessage(gameMessageS2CPacket.comp_763().getString());
        }
    }

    @WrapOperation(method={"method_11148"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_702;method_3051(Lnet/minecraft/class_1297;Lnet/minecraft/class_2394;I)V")})
    private void aurora$hideOwnPopParticles(ParticleManager particleManager, Entity entity, ParticleEffect particleEffect, int n, Operation<Void> operation) {
        if (entity == MinecraftClient.method_1551().field_1724 && TotemTweaksModule.hideOwnParticles()) {
            return;
        }
        operation.call(new Object[]{particleManager, entity, particleEffect, n});
    }

    private static void lambda$aurora$anchorDeltaUpdate$1(ClientWorld clientWorld, BlockPos blockPos, BlockState blockState) {
        AnchorPrediction.onServerBlockUpdate(clientWorld, blockPos, blockState);
    }

    private static void lambda$aurora$anchorOptimizerDeltaUpdate$0(BlockPos blockPos, BlockState blockState) {
        AnchorOptimizer.onServerBlockUpdate(blockPos);
    }
}
