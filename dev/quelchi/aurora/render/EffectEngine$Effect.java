package dev.quelchi.aurora.render;

import dev.quelchi.aurora.cosmetics.content.Colorways;
import dev.quelchi.aurora.cosmetics.content.Colorways$Way;
import dev.quelchi.aurora.render.EffectEngine;
import dev.quelchi.aurora.render.EffectEngine$Def;
import java.util.List;

public record EffectEngine$Effect(String id, String label, int argb, List<Colorways$Way> ways, int accent, EffectEngine$Def def, int way) {
    private static final long[] a;
    private static final Integer[] b;

    public EffectEngine$Effect inWay(String string) {
        EffectEngine$Effect effectEngine$Effect;
        block4: {
            block5: {
                int n = EffectEngine.a;
                effectEngine$Effect = this;
                if (n != 0) break block4;
                if (!effectEngine$Effect.ways.isEmpty()) break block5;
                return this;
            }
            effectEngine$Effect = this;
        }
        Colorways$Way colorways$Way = Colorways.pick(effectEngine$Effect.ways, string);
        return new EffectEngine$Effect(this.id, this.label, EffectEngine$Effect.a(25391, 7676160207711538689L) | colorways$Way.inner(), this.ways, EffectEngine$Effect.a(13967, 1857083965413579680L) | colorways$Way.outer(), this.def, this.ways.indexOf(colorways$Way));
    }

    
    static {
        long l = 5610197652282368754L;
        long[] lArray = new long[2];
        int n = 0;
        String string = "\u0018Wo\u00a7k\u0089\u00b9Rn\u00b4L\u00b4d\u00a7\u00ec\u00f3";
        int n2 = "\u0018Wo\u00a7k\u0089\u00b9Rn\u00b4L\u00b4d\u00a7\u00ec\u00f3".length();
        int n3 = 0;
        do {
            byte[] byArray = string.substring(n3, n3 += 8).getBytes("ISO-8859-1");
            int n4 = n++;
            lArray[n4] = (((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL) ^ l;
        } while (n3 < n2);
        a = lArray;
        b = new Integer[2];
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6D2F;
        if (b[n2] == null) {
            EffectEngine$Effect.b[n2] = (int)(a[n2] ^ l);
        }
        return b[n2];
    }
}
