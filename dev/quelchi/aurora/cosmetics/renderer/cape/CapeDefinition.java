package dev.quelchi.aurora.cosmetics.renderer.cape;

import dev.quelchi.aurora.cosmetics.renderer.cape.CapeLayer;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.Identifier;

public final class CapeDefinition {
    private final String id;
    private final List<CapeLayer> layers = new ArrayList<CapeLayer>();

    public CapeDefinition(String string) {
        this.id = string;
    }

    public static CapeDefinition single(String string, Identifier identifier) {
        return new CapeDefinition(string).layer(CapeLayer.base(identifier));
    }

    public CapeDefinition layer(CapeLayer capeLayer) {
        this.layers.add(capeLayer);
        return this;
    }

    public String id() {
        return this.id;
    }

    public List<CapeLayer> layers() {
        return this.layers;
    }
}
