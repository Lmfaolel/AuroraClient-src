package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.gui.PreviewScene$Grid;
import dev.quelchi.aurora.gui.PreviewScene$Renderer;
import net.minecraft.client.render.VertexConsumerProvider;

public final class PreviewScene$GridRenderer
extends PreviewScene$Renderer<PreviewScene$Grid> {
    private static final String b;

    public PreviewScene$GridRenderer(VertexConsumerProvider.Immediate immediate) {
        super(immediate);
    }

    public Class<PreviewScene$Grid> method_70903() {
        return PreviewScene$Grid.class;
    }

    protected String method_70906() {
        return b;
    }

    
    static {
        char[] cArray;
        block11: {
            int n;
            int n2;
            block10: {
                char[] cArray2 = "GB\u007f&\"\"2VEh?9&\u001ayP\u007f 4".toCharArray();
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
                    case 0 -> 38;
                    case 1 -> 55;
                    case 2 -> 13;
                    case 3 -> 73;
                    case 4 -> 80;
                    case 5 -> 67;
                    default -> 109;
                }));
                ++n;
                n2 = n2;
            } while (n2 > n);
        }
        b = new String(cArray).intern();
    }
}
