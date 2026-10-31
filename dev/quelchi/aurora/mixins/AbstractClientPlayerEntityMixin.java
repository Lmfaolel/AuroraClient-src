package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.cosmetics.backend.AccountCapes;
import dev.quelchi.aurora.cosmetics.content.Cosmetics;
import dev.quelchi.aurora.cosmetics.renderer.CapeManager;
import dev.quelchi.aurora.cosmetics.renderer.CapeManager$CapeAsset;
import dev.quelchi.aurora.module.modules.AntiAltModule;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerSkinType;
import net.minecraft.entity.player.SkinTextures;
import net.minecraft.util.AssetInfo;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={AbstractClientPlayerEntity.class})
public abstract class AbstractClientPlayerEntityMixin {
    private static final Map<Identifier, CapeManager$CapeAsset> CAPE_ASSETS = new ConcurrentHashMap<Identifier, CapeManager$CapeAsset>();

    @Inject(method={"method_52814"}, at={@At(value="HEAD")}, cancellable=true)
    private void divine$spoofSkin(CallbackInfoReturnable<SkinTextures> callbackInfoReturnable) {
        AntiAltModule antiAltModule = AntiAltModule.get();
        if (antiAltModule == null) {
            return;
        }
        SkinTextures skinTextures = antiAltModule.spoofedSkin();
        if (skinTextures == null) {
            return;
        }
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        if (this == minecraftClient.field_1724) {
            callbackInfoReturnable.setReturnValue((Object)skinTextures);
        }
    }

    @Inject(method={"method_52814"}, at={@At(value="RETURN")}, cancellable=true)
    private void aurora$customCape(CallbackInfoReturnable<SkinTextures> callbackInfoReturnable) {
        AbstractClientPlayerEntity abstractClientPlayerEntity = (AbstractClientPlayerEntity)this;
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        UUID uUID = minecraftClient.method_1548() == null ? null : minecraftClient.method_1548().method_44717();
        boolean bl = abstractClientPlayerEntity == minecraftClient.field_1724 || uUID != null && uUID.equals(abstractClientPlayerEntity.method_5667());
        String string = Cosmetics.capeFor(abstractClientPlayerEntity.method_5667(), bl);
        SkinTextures skinTextures = (SkinTextures)callbackInfoReturnable.getReturnValue();
        if (skinTextures == null) {
            return;
        }
        if (string == null || string.isBlank()) {
            Identifier identifier;
            Object object = identifier = bl ? AccountCapes.shownTexture() : null;
            if (identifier != null) {
                callbackInfoReturnable.setReturnValue((Object)SkinTextures.method_74884((AssetInfo.TextureAsset)skinTextures.comp_1626(), (AssetInfo.TextureAsset)CAPE_ASSETS.computeIfAbsent(identifier, CapeManager$CapeAsset::new), null, (PlayerSkinType)skinTextures.comp_1629()));
            }
            return;
        }
        if ("-none-".equals(string)) {
            callbackInfoReturnable.setReturnValue((Object)SkinTextures.method_74884((AssetInfo.TextureAsset)skinTextures.comp_1626(), null, (AssetInfo.TextureAsset)skinTextures.comp_1628(), (PlayerSkinType)skinTextures.comp_1629()));
            return;
        }
        Identifier identifier = CapeManager.texture(CapeManager.key(string));
        if (identifier == null) {
            return;
        }
        CapeManager$CapeAsset capeManager$CapeAsset = CAPE_ASSETS.computeIfAbsent(identifier, CapeManager$CapeAsset::new);
        callbackInfoReturnable.setReturnValue((Object)SkinTextures.method_74884((AssetInfo.TextureAsset)skinTextures.comp_1626(), (AssetInfo.TextureAsset)capeManager$CapeAsset, null, (PlayerSkinType)skinTextures.comp_1629()));
    }
}
