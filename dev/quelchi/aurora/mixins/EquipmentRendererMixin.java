package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.quelchi.aurora.module.modules.DamageTintModule;
import dev.quelchi.aurora.module.modules.RendersModule;
import dev.quelchi.aurora.module.modules.ShieldTweaksModule;
import dev.quelchi.aurora.module.modules.TransparentPlayersModule;
import dev.quelchi.aurora.render.ArmorRenderContext;
import dev.quelchi.aurora.render.ArmorRenderContext$State;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.entity.equipment.EquipmentRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value={EquipmentRenderer.class})
public abstract class EquipmentRendererMixin {
    private static final String RENDER = "render(Lnet/minecraft/client/render/entity/equipment/EquipmentModel$LayerType;Lnet/minecraft/registry/RegistryKey;Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;ILnet/minecraft/util/Identifier;II)V";
    private static final String SUBMIT_MODEL = "Lnet/minecraft/client/render/command/RenderCommandQueue;submitModel(Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/RenderLayer;IIILnet/minecraft/client/texture/Sprite;ILnet/minecraft/client/render/command/ModelCommandRenderer$CrumblingOverlayCommand;)V";

    private static boolean divine$hurtFlash() {
        ArmorRenderContext$State armorRenderContext$State = ArmorRenderContext.get();
        DamageTintModule damageTintModule = DamageTintModule.get();
        return armorRenderContext$State != null && armorRenderContext$State.hurt() && damageTintModule != null && damageTintModule.tintArmor();
    }

    private static int aurora$hurtUv() {
        return OverlayTexture.method_23625((int)OverlayTexture.method_23210((float)0.0f), (int)OverlayTexture.method_23212((boolean)true));
    }

    @WrapOperation(method={"method_64078(Lnet/minecraft/class_10186$class_10190;Lnet/minecraft/class_5321;Lnet/minecraft/class_3879;Ljava/lang/Object;Lnet/minecraft/class_1799;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;ILnet/minecraft/class_2960;II)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_12249;method_75966(Lnet/minecraft/class_2960;)Lnet/minecraft/class_1921;")})
    private RenderLayer divine$armorLayer(Identifier identifier, Operation<RenderLayer> operation) {
        boolean bl = TransparentPlayersModule.fadesArmor();
        boolean bl2 = EquipmentRendererMixin.divine$hurtFlash();
        if (bl && bl2) {
            return RenderLayers.method_76000((Identifier)identifier);
        }
        if (bl) {
            return RenderLayers.method_75978((Identifier)identifier);
        }
        if (bl2) {
            return RenderLayers.method_75996((Identifier)identifier);
        }
        return RenderLayers.method_75966((Identifier)identifier);
    }

    @WrapOperation(method={"method_64078(Lnet/minecraft/class_10186$class_10190;Lnet/minecraft/class_5321;Lnet/minecraft/class_3879;Ljava/lang/Object;Lnet/minecraft/class_1799;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;ILnet/minecraft/class_2960;II)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_4722;method_48480(Z)Lnet/minecraft/class_1921;")})
    private RenderLayer aurora$trimLayer(boolean bl, Operation<RenderLayer> operation) {
        boolean bl2 = TransparentPlayersModule.fadesArmor();
        boolean bl3 = EquipmentRendererMixin.divine$hurtFlash();
        if (!bl2 && !bl3) {
            return (RenderLayer)operation.call(new Object[]{bl});
        }
        Identifier identifier = TexturedRenderLayers.field_42071;
        if (bl2) {
            return RenderLayers.method_76000((Identifier)identifier);
        }
        return RenderLayers.method_75996((Identifier)identifier);
    }

    @WrapOperation(method={"method_64078(Lnet/minecraft/class_10186$class_10190;Lnet/minecraft/class_5321;Lnet/minecraft/class_3879;Ljava/lang/Object;Lnet/minecraft/class_1799;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;ILnet/minecraft/class_2960;II)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_1799;method_7958()Z")})
    private boolean aurora$armorGlint(ItemStack itemStack, Operation<Boolean> operation) {
        if (RendersModule.noArmorGlint()) {
            return false;
        }
        return (Boolean)operation.call(new Object[]{itemStack});
    }

    @ModifyArg(method={"method_64078(Lnet/minecraft/class_10186$class_10190;Lnet/minecraft/class_5321;Lnet/minecraft/class_3879;Ljava/lang/Object;Lnet/minecraft/class_1799;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;ILnet/minecraft/class_2960;II)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_11785;method_73490(Lnet/minecraft/class_3879;Ljava/lang/Object;Lnet/minecraft/class_4587;Lnet/minecraft/class_1921;IIILnet/minecraft/class_1058;ILnet/minecraft/class_11683$class_11792;)V")}, index=6)
    private int aurora$armorAlpha(int n) {
        return TransparentPlayersModule.fadesArmor() ? TransparentPlayersModule.withArmorAlpha(n) : n;
    }

    @ModifyArg(method={"method_64078(Lnet/minecraft/class_10186$class_10190;Lnet/minecraft/class_5321;Lnet/minecraft/class_3879;Ljava/lang/Object;Lnet/minecraft/class_1799;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;ILnet/minecraft/class_2960;II)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_11785;method_73490(Lnet/minecraft/class_3879;Ljava/lang/Object;Lnet/minecraft/class_4587;Lnet/minecraft/class_1921;IIILnet/minecraft/class_1058;ILnet/minecraft/class_11683$class_11792;)V")}, index=5)
    private int divine$armorHurtOverlay(int n) {
        return EquipmentRendererMixin.divine$hurtFlash() ? EquipmentRendererMixin.aurora$hurtUv() : n;
    }

    @ModifyArg(method={"method_64078(Lnet/minecraft/class_10186$class_10190;Lnet/minecraft/class_5321;Lnet/minecraft/class_3879;Ljava/lang/Object;Lnet/minecraft/class_1799;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;ILnet/minecraft/class_2960;II)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_11785;method_73490(Lnet/minecraft/class_3879;Ljava/lang/Object;Lnet/minecraft/class_4587;Lnet/minecraft/class_1921;IIILnet/minecraft/class_1058;ILnet/minecraft/class_11683$class_11792;)V")}, index=6)
    private int divine$armorTint(int n) {
        ArmorRenderContext$State armorRenderContext$State = ArmorRenderContext.get();
        if (armorRenderContext$State == null) {
            return n;
        }
        if (armorRenderContext$State.blocking() && ShieldTweaksModule.armorTintEnabled()) {
            n = ShieldTweaksModule.blendArmor(n);
        }
        return n;
    }
}
