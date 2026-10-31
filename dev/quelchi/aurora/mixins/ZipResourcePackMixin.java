package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.perf.Tweaks;
import dev.quelchi.aurora.perf.ZipIndex;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourceType;
import net.minecraft.resource.ZipResourcePack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={ZipResourcePack.class})
public abstract class ZipResourcePackMixin {
    @Shadow
    private String method_52422(String string) {
        throw new AssertionError();
    }

    @Redirect(method={"method_14408"}, at={@At(value="INVOKE", target="Ljava/util/zip/ZipFile;entries()Ljava/util/Enumeration;", remap=false)})
    private Enumeration<? extends ZipEntry> aurora$matching(ZipFile zipFile, ResourceType resourceType, String string, String string2, ResourcePack.ResultConsumer resultConsumer) {
        if (!Tweaks.ZIP_INDEX) {
            return zipFile.entries();
        }
        return ZipIndex.matching(zipFile, this.method_52422(resourceType.method_14413() + "/" + string + "/") + string2 + "/");
    }
}
