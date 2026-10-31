package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.quelchi.aurora.mixins.SpriteContentsAccessor;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.client.render.item.model.BasicItemModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HeldItemContext;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value={BasicItemModel.class})
public abstract class HeldItemCutoutMixin {
    @Shadow
    @Final
    private List<BakedQuad> field_56959;
    @Unique
    private Boolean aurora$binaryAlpha;

    @WrapOperation(method={"method_65584(Lnet/minecraft/class_10444;Lnet/minecraft/class_1799;Lnet/minecraft/class_10442;Lnet/minecraft/class_811;Lnet/minecraft/class_638;Lnet/minecraft/class_11566;I)V"}, at={@At(value="INVOKE", target="Ljava/util/function/Function;apply(Ljava/lang/Object;)Ljava/lang/Object;")})
    private Object aurora$cutoutWhenBinary(Function<Object, Object> function, Object object, Operation<Object> operation, ItemRenderState itemRenderState, ItemStack itemStack, ItemModelManager itemModelManager, ItemDisplayContext itemDisplayContext, ClientWorld clientWorld, HeldItemContext heldItemContext, int n) {
        Object object2 = operation.call(new Object[]{function, object});
        if (!itemDisplayContext.method_29998() || object2 != TexturedRenderLayers.method_29382()) {
            return object2;
        }
        if (this.aurora$binaryAlpha == null) {
            this.aurora$binaryAlpha = HeldItemCutoutMixin.aurora$allBinary(this.field_56959);
        }
        return this.aurora$binaryAlpha != false ? RenderLayers.method_75990((Identifier)SpriteAtlasTexture.field_64467) : object2;
    }

    @Unique
    private static boolean aurora$allBinary(List<BakedQuad> list) {
        HashSet<Sprite> hashSet = new HashSet<Sprite>();
        for (BakedQuad bakedQuad : list) {
            if (!hashSet.add(bakedQuad.comp_3724())) continue;
            NativeImage[] nativeImageArray = ((SpriteContentsAccessor)bakedQuad.comp_3724().method_45851()).aurora$levels();
            if (nativeImageArray == null || nativeImageArray.length == 0 || nativeImageArray[0] == null) {
                return false;
            }
            NativeImage nativeImage = nativeImageArray[0];
            for (int i = 0; i < nativeImage.method_4323(); ++i) {
                for (int j = 0; j < nativeImage.method_4307(); ++j) {
                    int n = nativeImage.method_61940(j, i) >>> 24;
                    if (n == 0 || n == 255) continue;
                    return false;
                }
            }
        }
        return true;
    }
}
