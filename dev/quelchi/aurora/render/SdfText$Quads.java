package dev.quelchi.aurora.render;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import dev.quelchi.aurora.render.EffectEngine;
import java.lang.invoke.MethodHandle;
import java.lang.runtime.ObjectMethods;
import net.minecraft.client.gui.ScreenRect;
import net.minecraft.client.gui.render.state.SimpleGuiElementRenderState;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.texture.TextureSetup;

final class SdfText$Quads
extends Record
implements SimpleGuiElementRenderState {
    private final float[] q;
    private final RenderPipeline pipeline;
    private final TextureSetup textureSetup;
    private final ScreenRect scissorArea;
    private final ScreenRect bounds;

    private SdfText$Quads(float[] fArray, RenderPipeline renderPipeline, TextureSetup textureSetup, ScreenRect screenRect, ScreenRect screenRect2) {
        this.q = fArray;
        this.pipeline = renderPipeline;
        this.textureSetup = textureSetup;
        this.scissorArea = screenRect;
        this.bounds = screenRect2;
    }

    public void method_70917(VertexConsumer vertexConsumer) {
        float[] fArray = this.q;
        int n = EffectEngine.a;
        for (int i = 0; i < fArray.length; i += 15) {
            int n2 = Float.floatToRawIntBits(fArray[i + 12]);
            int n3 = (int)fArray[i + 13];
            int n4 = (int)fArray[i + 14];
            vertexConsumer.method_22912(fArray[i], fArray[i + 1], 0.0f).method_39415(n2).method_22913(fArray[i + 8], fArray[i + 9]).method_22921(n3, n4);
            vertexConsumer.method_22912(fArray[i + 2], fArray[i + 3], 0.0f).method_39415(n2).method_22913(fArray[i + 8], fArray[i + 11]).method_22921(n3, n4);
            vertexConsumer.method_22912(fArray[i + 4], fArray[i + 5], 0.0f).method_39415(n2).method_22913(fArray[i + 10], fArray[i + 11]).method_22921(n3, n4);
            vertexConsumer.method_22912(fArray[i + 6], fArray[i + 7], 0.0f).method_39415(n2).method_22913(fArray[i + 10], fArray[i + 9]).method_22921(n3, n4);
            if (n == 0) continue;
        }
    }

    @Override
    public final String toString() {
        return ObjectMethods.bootstrap("toString", new MethodHandle[]{SdfText$Quads.class, "q;pipeline;textureSetup;scissorArea;bounds", "q", "pipeline", "textureSetup", "scissorArea", "bounds"}, this);
    }

    @Override
    public final int hashCode() {
        return (int)ObjectMethods.bootstrap("hashCode", new MethodHandle[]{SdfText$Quads.class, "q;pipeline;textureSetup;scissorArea;bounds", "q", "pipeline", "textureSetup", "scissorArea", "bounds"}, this);
    }

    @Override
    public final boolean equals(Object object) {
        return (boolean)ObjectMethods.bootstrap("equals", new MethodHandle[]{SdfText$Quads.class, "q;pipeline;textureSetup;scissorArea;bounds", "q", "pipeline", "textureSetup", "scissorArea", "bounds"}, this, object);
    }

    public float[] q() {
        return this.q;
    }

    public RenderPipeline comp_4055() {
        return this.pipeline;
    }

    public TextureSetup comp_4056() {
        return this.textureSetup;
    }

    public ScreenRect comp_4069() {
        return this.scissorArea;
    }

    public ScreenRect comp_4274() {
        return this.bounds;
    }
}
