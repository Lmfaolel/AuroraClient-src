package dev.quelchi.aurora.render;

import dev.quelchi.aurora.render.EffectEngine;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.util.Identifier;

public final class TotemFade {
    private static float alpha;
    private static final long a;

    private TotemFade() {
    }

    public static void begin(float f) {
        alpha = Math.max(0.0f, Math.min(1.0f, f));
    }

    public static void end() {
        alpha = 1.0f;
    }

    public static boolean active() {
        float f;
        block2: {
            block3: {
                int n = EffectEngine.a;
                float f2 = alpha - 0.999f;
                f = f2 == 0.0f ? 0 : (f2 < 0.0f ? -1 : 1);
                if (n != 0) break block2;
                if (f >= 0) break block3;
                f = 1.0f;
                break block2;
            }
            f = 0.0f;
        }
        return (boolean)f;
    }

    public static int[] tints() {
        return new int[]{Math.round(alpha * 255.0f) << 24 | (int)a};
    }

    public static List<BakedQuad> tinted(List<BakedQuad> list) {
        ArrayList<BakedQuad> arrayList;
        block7: {
            ArrayList<BakedQuad> arrayList2 = new ArrayList<BakedQuad>(list.size());
            Iterator<BakedQuad> iterator = list.iterator();
            int n = EffectEngine.a;
            while (iterator.hasNext()) {
                BakedQuad bakedQuad;
                block8: {
                    BakedQuad bakedQuad2;
                    block9: {
                        bakedQuad2 = iterator.next();
                        arrayList = arrayList2;
                        if (n != 0) break block7;
                        bakedQuad = bakedQuad2;
                        if (n != 0) break block8;
                        if (!bakedQuad.method_3360()) break block9;
                        bakedQuad = bakedQuad2;
                        break block8;
                    }
                    bakedQuad = new BakedQuad(bakedQuad2.comp_5238(), bakedQuad2.comp_5239(), bakedQuad2.comp_5240(), bakedQuad2.comp_5241(), bakedQuad2.comp_5242(), bakedQuad2.comp_5243(), bakedQuad2.comp_5244(), bakedQuad2.comp_5245(), 0, bakedQuad2.comp_3723(), bakedQuad2.comp_3724(), bakedQuad2.comp_3725(), bakedQuad2.comp_3726());
                }
                arrayList.add(bakedQuad);
                if (n == 0) continue;
            }
            arrayList = arrayList2;
        }
        return arrayList;
    }

    public static RenderLayer layerFor(List<BakedQuad> list, RenderLayer renderLayer) {
        Iterator<BakedQuad> iterator = list.iterator();
        int n = EffectEngine.a;
        while (iterator.hasNext()) {
            block6: {
                BakedQuad bakedQuad;
                block5: {
                    BakedQuad bakedQuad2 = iterator.next();
                    bakedQuad = bakedQuad2;
                    if (n != 0) break block5;
                    if (bakedQuad.comp_3724() == null) break block6;
                    bakedQuad = bakedQuad2;
                }
                return RenderLayers.method_75998((Identifier)bakedQuad.comp_3724().method_45852());
            }
            if (n == 0) continue;
        }
        return renderLayer;
    }

    
    static {
        long l = 183622305381449194L;
        a = 0x36158AF28DE68215L ^ l;
        alpha = 1.0f;
    }
}
