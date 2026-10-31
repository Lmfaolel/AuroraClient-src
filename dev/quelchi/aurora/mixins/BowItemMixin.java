package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.mixins.RangedWeaponItemInvoker;
import dev.quelchi.aurora.optimize.ProjectileOptimizer;
import java.util.List;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={BowItem.class})
public abstract class BowItemMixin {
    @Shadow
    protected abstract void method_7763(LivingEntity var1, ProjectileEntity var2, int var3, float var4, float var5, float var6, LivingEntity var7);

    @Inject(method={"method_7840"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_1753;method_57390(Lnet/minecraft/class_1799;Lnet/minecraft/class_1799;Lnet/minecraft/class_1309;)Ljava/util/List;")})
    private void aurora$clientArrows(ItemStack itemStack, World world, LivingEntity livingEntity, int n, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        ClientWorld clientWorld;
        block6: {
            block5: {
                if (!(world instanceof ClientWorld)) break block5;
                clientWorld = (ClientWorld)world;
                if (ProjectileOptimizer.on()) break block6;
            }
            return;
        }
        if (!(livingEntity instanceof PlayerEntity)) {
            return;
        }
        PlayerEntity playerEntity = (PlayerEntity)livingEntity;
        List<ItemStack> list = RangedWeaponItemInvoker.aurora$load(itemStack, playerEntity.method_18808(itemStack), (LivingEntity)playerEntity);
        if (list.isEmpty()) {
            return;
        }
        int n2 = ((BowItem)this).method_7881(itemStack, livingEntity) - n;
        float f = BowItem.method_7722((int)n2);
        ProjectileOptimizer.shootAll(clientWorld, (LivingEntity)playerEntity, playerEntity.method_6058(), itemStack, list, f * 3.0f, 1.0f, f == 1.0f, null, this::method_7763);
    }
}
