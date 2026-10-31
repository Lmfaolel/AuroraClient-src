package dev.quelchi.aurora.gui;

import com.mojang.authlib.GameProfile;
import dev.quelchi.aurora.gui.PreviewPlayer$Guarded;
import dev.quelchi.aurora.gui.PreviewScene;
import dev.quelchi.aurora.util.SkinFetcher;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerSkinType;
import net.minecraft.entity.player.SkinTextures;
import net.minecraft.util.AssetInfo;

final class PreviewPlayer$Skinned
extends PreviewPlayer$Guarded {
    private PreviewPlayer$Skinned(ClientWorld clientWorld, GameProfile gameProfile) {
        super(clientWorld, gameProfile);
    }

    public SkinTextures method_52814() {
        SkinTextures skinTextures;
        block10: {
            SkinTextures skinTextures2;
            SkinTextures skinTextures3;
            block11: {
                int n;
                block8: {
                    block9: {
                        skinTextures3 = super.method_52814();
                        skinTextures2 = SkinFetcher.skinFor(this.method_5667());
                        n = PreviewScene.a;
                        skinTextures = skinTextures2;
                        if (n != 0) break block8;
                        if (skinTextures != null) break block9;
                        return skinTextures3;
                    }
                    skinTextures = skinTextures3;
                }
                if (n != 0) break block10;
                if (skinTextures != null) break block11;
                return skinTextures2;
            }
            skinTextures = SkinTextures.method_74884((AssetInfo.TextureAsset)skinTextures2.comp_1626(), (AssetInfo.TextureAsset)skinTextures3.comp_1627(), (AssetInfo.TextureAsset)skinTextures3.comp_1628(), (PlayerSkinType)skinTextures2.comp_1629());
        }
        return skinTextures;
    }
}
