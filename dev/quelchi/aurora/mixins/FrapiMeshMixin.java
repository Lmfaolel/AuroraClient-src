package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.perf.LeanMesh;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Pseudo
@Mixin(targets={"net/caffeinemc/mods/sodium/client/render/frapi/mesh/MutableMeshImpl", "net/fabricmc/fabric/impl/client/indigo/renderer/mesh/MutableMeshImpl"})
public abstract class FrapiMeshMixin {
    @ModifyConstant(method={"<init>"}, constant={@Constant(intValue=8)}, require=0)
    private int aurora$startWithOneQuad(int n) {
        return LeanMesh.BYPASS ? n : 1;
    }
}
