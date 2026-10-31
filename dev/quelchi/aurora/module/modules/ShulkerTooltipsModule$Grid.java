package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.module.modules.AmbienceModule;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

record ShulkerTooltipsModule$Grid(DefaultedList<ItemStack> items, int tint) {
    private static final long[] a;
    private static final Integer[] b;

    public int method_32661(TextRenderer textRenderer) {
        return 58;
    }

    public int method_32664(TextRenderer textRenderer) {
        return 164;
    }

    public void method_32666(TextRenderer textRenderer, int n, int n2, int n3, int n4, DrawContext drawContext) {
        drawContext.method_25294(n, n2, n + 162 + 2, n2 + 54 + 2, ShulkerTooltipsModule$Grid.a(15175, 1763395365144647124L) | this.tint);
        boolean bl = AmbienceModule.b;
        for (int i = 0; i < 27; ++i) {
            ItemStack itemStack;
            int n5;
            int n6;
            block11: {
                int n7;
                block10: {
                    n7 = i;
                    if (bl) break block10;
                    if (n7 >= this.items.size()) break;
                    n7 = n + 1 + i % 9 * 18;
                }
                n6 = n7;
                n5 = n2 + 1 + i / 9 * 18;
                drawContext.method_25294(n6 + 1, n5 + 1, n6 + 17, n5 + 17, ShulkerTooltipsModule$Grid.a(17502, 2832633760559508172L));
                itemStack = (ItemStack)this.items.get(i);
                if (bl) break block11;
                if (itemStack.method_7960()) {
                    continue;
                }
                drawContext.method_51427(itemStack, n6 + 1, n5 + 1);
            }
            drawContext.method_51431(textRenderer, itemStack, n6 + 1, n5 + 1);
            if (!bl) continue;
        }
    }

    
    static {
        long l = 7612347799659195260L;
        long[] lArray = new long[2];
        int n = 0;
        String string = "[L\u0081L{m\u00fe\u00a8\u00be\u00c8\u009er\u001d\u0084\u0001\u00b0";
        int n2 = "[L\u0081L{m\u00fe\u00a8\u00be\u00c8\u009er\u001d\u0084\u0001\u00b0".length();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6E93;
        if (b[n2] == null) {
            ShulkerTooltipsModule$Grid.b[n2] = (int)(a[n2] ^ l);
        }
        return b[n2];
    }
}
