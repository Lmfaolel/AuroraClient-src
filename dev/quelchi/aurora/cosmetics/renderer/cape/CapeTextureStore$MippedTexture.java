package dev.quelchi.aurora.cosmetics.renderer.cape;

import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.AddressMode;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.TextureFormat;
import dev.quelchi.aurora.cosmetics.renderer.cape.CapeLayer;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.util.Identifier;

final class CapeTextureStore$MippedTexture
extends AbstractTexture {
    private static final String a;

    private CapeTextureStore$MippedTexture(Identifier identifier, NativeImage[] nativeImageArray) {
        block4: {
            boolean bl = CapeLayer.a;
            GpuDevice gpuDevice = RenderSystem.getDevice();
            this.field_56974 = gpuDevice.createTexture(() -> CapeTextureStore$MippedTexture.lambda$new$0(identifier), 5, TextureFormat.RGBA8, nativeImageArray[0].method_4307(), nativeImageArray[0].method_4323(), 1, nativeImageArray.length);
            this.field_60597 = gpuDevice.createTextureView(this.field_56974);
            CommandEncoder commandEncoder = gpuDevice.createCommandEncoder();
            for (int i = 0; i < nativeImageArray.length; ++i) {
                if (!bl) {
                    commandEncoder.writeToTexture(this.field_56974, nativeImageArray[i], i, 0, 0, 0, nativeImageArray[i].method_4307(), nativeImageArray[i].method_4323(), 0, 0);
                    if (!bl) continue;
                    break;
                }
                break block4;
            }
            this.field_63613 = RenderSystem.getSamplerCache().method_75293(AddressMode.REPEAT, AddressMode.REPEAT, FilterMode.LINEAR, FilterMode.NEAREST, true);
        }
    }

    private static String lambda$new$0(Identifier identifier) {
        return a + identifier.method_12832();
    }

    
    static {
        char[] cArray;
        block11: {
            int n;
            int n2;
            block10: {
                char[] cArray2 = "!w`4j \b".toCharArray();
                cArray = cArray2;
                n2 = cArray2.length;
                n = 0;
                if (!true) break block10;
                n2 = n2;
                if (n2 <= n) break block11;
            }
            do {
                cArray = cArray;
                int n3 = n;
                char c = cArray[n3];
                cArray[n3] = (char)(c ^ (switch (n % 7) {
                    case 0 -> 64;
                    case 1 -> 2;
                    case 2 -> 18;
                    case 3 -> 91;
                    case 4 -> 24;
                    case 5 -> 65;
                    default -> 39;
                }));
                ++n;
                n2 = n2;
            } while (n2 > n);
        }
        a = new String(cArray).intern();
    }
}
