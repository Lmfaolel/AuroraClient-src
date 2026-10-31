package dev.quelchi.aurora.cosmetics.renderer;

import java.lang.invoke.MethodHandle;
import java.lang.runtime.ObjectMethods;
import net.minecraft.util.AssetInfo;
import net.minecraft.util.Identifier;

public final class CapeManager$CapeAsset
extends Record
implements AssetInfo.TextureAsset {
    private final Identifier id;

    public CapeManager$CapeAsset(Identifier identifier) {
        this.id = identifier;
    }

    public Identifier comp_3627() {
        return this.id;
    }

    @Override
    public final String toString() {
        return ObjectMethods.bootstrap("toString", new MethodHandle[]{CapeManager$CapeAsset.class, "id", "id"}, this);
    }

    @Override
    public final int hashCode() {
        return (int)ObjectMethods.bootstrap("hashCode", new MethodHandle[]{CapeManager$CapeAsset.class, "id", "id"}, this);
    }

    @Override
    public final boolean equals(Object object) {
        return (boolean)ObjectMethods.bootstrap("equals", new MethodHandle[]{CapeManager$CapeAsset.class, "id", "id"}, this, object);
    }

    public Identifier comp_3626() {
        return this.id;
    }
}
