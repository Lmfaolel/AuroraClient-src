package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.mixins.RangedWeaponItemInvoker;
import dev.quelchi.aurora.optimize.CrossbowOptimizer;
import dev.quelchi.aurora.optimize.ProjectileOptimizer;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import java.util.List;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={CrossbowItem.class})
public abstract class CrossbowItemMixin {
    @Shadow
    private boolean field_7937;
    @Shadow
    private boolean field_7936;
    @Unique
    private ChargedProjectilesComponent aurora$fired;

    @Shadow
    abstract CrossbowItem.LoadingSounds method_59976(ItemStack var1);

    @Shadow
    protected abstract void method_7763(LivingEntity var1, ProjectileEntity var2, int var3, float var4, float var5, float var6, LivingEntity var7);

    @Inject(method={"method_7836"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_1764;method_7777(Lnet/minecraft/class_1937;Lnet/minecraft/class_1309;Lnet/minecraft/class_1268;Lnet/minecraft/class_1799;FFLnet/minecraft/class_1309;)V")})
    private void aurora$onShoot(World world, PlayerEntity playerEntity, Hand hand, CallbackInfoReturnable<ActionResult> callbackInfoReturnable) {
        if (!CrossbowOptimizer.on()) {
            return;
        }
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        if (minecraftClient.field_1687 == null || minecraftClient.field_1724 == null) {
            return;
        }
        ItemStack itemStack = playerEntity.method_5998(hand);
        this.aurora$fired = (ChargedProjectilesComponent)itemStack.method_58695(DataComponentTypes.field_49649, (Object)ChargedProjectilesComponent.field_49298);
        int n = CrossbowItemMixin.aurora$multishot(itemStack) ? 3 : 1;
        for (int i = 0; i < n; ++i) {
            minecraftClient.field_1687.method_43128((Entity)minecraftClient.field_1724, playerEntity.method_23317(), playerEntity.method_23318(), playerEntity.method_23321(), SoundEvents.field_15187, SoundCategory.field_15248, 0.5f, CrossbowItemMixin.aurora$shotPitch(minecraftClient.field_1724.method_59922(), i));
            CrossbowOptimizer.played(SoundEvents.field_15187);
        }
        itemStack.method_57379(DataComponentTypes.field_49649, (Object)ChargedProjectilesComponent.field_49298);
        CrossbowOptimizer.onShoot();
    }

    @Inject(method={"method_7777(Lnet/minecraft/class_1937;Lnet/minecraft/class_1309;Lnet/minecraft/class_1268;Lnet/minecraft/class_1799;FFLnet/minecraft/class_1309;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$clientBolts(World world, LivingEntity livingEntity, Hand hand, ItemStack itemStack, float f, float f2, LivingEntity livingEntity2, CallbackInfo callbackInfo) {
        ClientWorld clientWorld;
        block5: {
            block4: {
                if (!(world instanceof ClientWorld)) break block4;
                clientWorld = (ClientWorld)world;
                if (ProjectileOptimizer.on()) break block5;
            }
            return;
        }
        ChargedProjectilesComponent chargedProjectilesComponent = this.aurora$fired != null ? this.aurora$fired : (ChargedProjectilesComponent)itemStack.method_58695(DataComponentTypes.field_49649, (Object)ChargedProjectilesComponent.field_49298);
        this.aurora$fired = null;
        if (chargedProjectilesComponent != null && !chargedProjectilesComponent.method_57437().isEmpty()) {
            ProjectileOptimizer.shootAll(clientWorld, livingEntity, hand, itemStack, chargedProjectilesComponent.method_57437(), f, f2, livingEntity instanceof PlayerEntity, livingEntity2, this::method_7763);
        }
        callbackInfo.cancel();
    }

    @Inject(method={"method_7852"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$draw(World world, LivingEntity livingEntity, ItemStack itemStack, int n, CallbackInfo callbackInfo) {
        if (!CrossbowOptimizer.on()) {
            return;
        }
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        ClientWorld clientWorld = minecraftClient.field_1687;
        ClientPlayerEntity clientPlayerEntity = minecraftClient.field_1724;
        if (clientWorld == null || clientPlayerEntity == null) {
            return;
        }
        CrossbowItem.LoadingSounds loadingSounds = this.method_59976(itemStack);
        float f = (float)(itemStack.method_7935(livingEntity) - n) / (float)CrossbowItem.method_7775((ItemStack)itemStack, (LivingEntity)livingEntity);
        if (f < 0.2f) {
            this.field_7937 = false;
            this.field_7936 = false;
        }
        if (f >= 0.2f && !this.field_7937) {
            this.field_7937 = true;
            loadingSounds.comp_2673().ifPresent(arg_0 -> CrossbowItemMixin.lambda$aurora$draw$0(clientWorld, clientPlayerEntity, livingEntity, arg_0));
        }
        if (f >= 0.5f && !this.field_7936) {
            this.field_7936 = true;
            loadingSounds.comp_2674().ifPresent(arg_0 -> CrossbowItemMixin.lambda$aurora$draw$1(clientWorld, clientPlayerEntity, livingEntity, arg_0));
        }
        if (f >= 1.0f && !CrossbowItem.method_7781((ItemStack)itemStack) && CrossbowItemMixin.aurora$load(livingEntity, itemStack)) {
            loadingSounds.comp_2675().ifPresent(arg_0 -> CrossbowItemMixin.lambda$aurora$draw$2(clientWorld, clientPlayerEntity, livingEntity, arg_0));
        }
        callbackInfo.cancel();
    }

    @Unique
    private static void aurora$play(ClientWorld clientWorld, ClientPlayerEntity clientPlayerEntity, LivingEntity livingEntity, SoundEvent soundEvent, float f, float f2) {
        clientWorld.method_43128((Entity)clientPlayerEntity, livingEntity.method_23317(), livingEntity.method_23318(), livingEntity.method_23321(), soundEvent, SoundCategory.field_15248, f, f2);
        CrossbowOptimizer.played(soundEvent);
    }

    @Unique
    private static boolean aurora$load(LivingEntity livingEntity, ItemStack itemStack) {
        List<ItemStack> list = RangedWeaponItemInvoker.aurora$load(itemStack, livingEntity.method_18808(itemStack), livingEntity);
        if (list.isEmpty()) {
            return false;
        }
        itemStack.method_57379(DataComponentTypes.field_49649, (Object)ChargedProjectilesComponent.method_57441(list));
        return true;
    }

    @Unique
    private static boolean aurora$multishot(ItemStack itemStack) {
        if (itemStack.method_7960()) {
            return false;
        }
        for (Object2IntMap.Entry entry : itemStack.method_58657().method_57539()) {
            if (!((RegistryEntry)entry.getKey()).method_40225(Enchantments.field_9108)) continue;
            return entry.getIntValue() > 0;
        }
        return false;
    }

    @Unique
    private static float aurora$shotPitch(Random random, int n) {
        if (n == 0) {
            return 1.0f;
        }
        float f = (n & 1) == 1 ? 0.63f : 0.43f;
        return 1.0f / (random.method_43057() * 0.5f + 1.8f) + f;
    }

    private static void lambda$aurora$draw$2(ClientWorld clientWorld, ClientPlayerEntity clientPlayerEntity, LivingEntity livingEntity, RegistryEntry registryEntry) {
        CrossbowItemMixin.aurora$play(clientWorld, clientPlayerEntity, livingEntity, (SoundEvent)registryEntry.comp_349(), 1.0f, 1.0f / (clientWorld.method_8409().method_43057() * 0.5f + 1.0f) + 0.2f);
    }

    private static void lambda$aurora$draw$1(ClientWorld clientWorld, ClientPlayerEntity clientPlayerEntity, LivingEntity livingEntity, RegistryEntry registryEntry) {
        CrossbowItemMixin.aurora$play(clientWorld, clientPlayerEntity, livingEntity, (SoundEvent)registryEntry.comp_349(), 0.5f, 1.0f);
    }

    private static void lambda$aurora$draw$0(ClientWorld clientWorld, ClientPlayerEntity clientPlayerEntity, LivingEntity livingEntity, RegistryEntry registryEntry) {
        CrossbowItemMixin.aurora$play(clientWorld, clientPlayerEntity, livingEntity, (SoundEvent)registryEntry.comp_349(), 0.5f, 1.0f);
    }
}
