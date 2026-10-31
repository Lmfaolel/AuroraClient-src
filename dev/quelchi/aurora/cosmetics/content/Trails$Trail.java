package dev.quelchi.aurora.cosmetics.content;

import dev.quelchi.aurora.cosmetics.content.Colorways;
import dev.quelchi.aurora.cosmetics.content.Colorways$Way;
import dev.quelchi.aurora.cosmetics.content.Cosmetics;
import dev.quelchi.aurora.cosmetics.content.Trails$Origin;
import dev.quelchi.aurora.cosmetics.content.Trails$Style;
import dev.quelchi.aurora.module.Module;
import java.util.List;

public record Trails$Trail(String id, String label, int argb, int width, int length, Trails$Style style, List<Colorways$Way> ways, Trails$Origin origin) {
    private static final long[] a;
    private static final Integer[] b;

    public Trails$Trail {
        this.id = string;
        this.label = string2;
        this.argb = n;
        this.width = n2;
        this.length = n3;
        int n4 = Cosmetics.a;
        this.style = trails$Style;
        this.ways = list;
        this.origin = trails$Origin;
        if (Module.a) {
            Cosmetics.a = ++n4;
        }
    }

    public Trails$Trail inWay(String string) {
        Trails$Trail trails$Trail;
        block4: {
            block5: {
                int n = Cosmetics.a;
                trails$Trail = this;
                if (n != 0) break block4;
                if (!trails$Trail.ways.isEmpty()) break block5;
                return this;
            }
            trails$Trail = this;
        }
        Colorways$Way colorways$Way = Colorways.pick(trails$Trail.ways, string);
        return new Trails$Trail(this.id, this.label, this.argb & Trails$Trail.a(28861, 4919845443337660798L) | colorways$Way.inner() & Trails$Trail.a(25552, 5193670202811496978L), this.width, this.length, this.style, this.ways, this.origin);
    }

    
    static {
        long l = 1311896636523390248L;
        long[] lArray = new long[2];
        int n = 0;
        String string = "\u0012\u001f%\u007fu\u00e2`\u00c5\\[/\u00cc\u008a\u00f6\u008cV";
        int n2 = "\u0012\u001f%\u007fu\u00e2`\u00c5\\[/\u00cc\u008a\u00f6\u008cV".length();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x59C2;
        if (b[n2] == null) {
            Trails$Trail.b[n2] = (int)(a[n2] ^ l);
        }
        return b[n2];
    }
}
