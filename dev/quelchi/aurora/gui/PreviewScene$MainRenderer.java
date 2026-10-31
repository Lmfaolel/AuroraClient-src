package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.gui.PreviewScene$Main;
import dev.quelchi.aurora.gui.PreviewScene$Renderer;
import net.minecraft.client.render.VertexConsumerProvider;

public final class PreviewScene$MainRenderer
extends PreviewScene$Renderer<PreviewScene.Main> {
    private static final String b;

    public PreviewScene$MainRenderer(VertexConsumerProvider.Immediate immediate) {
        super(immediate);
    }

    public Class<PreviewScene.Main> method_70903() {
        return PreviewScene.Main.class;
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
                char[] cArray2 = "\u0014\u0018/V2Qo\u0005\u001f8O)UG".toCharArray();
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
                    case 0 -> 117;
                    case 1 -> 109;
                    case 2 -> 93;
                    case 3 -> 57;
                    case 4 -> 64;
                    case 5 -> 48;
                    default -> 48;
                }));
                ++n;
                n2 = n2;
            } while (n2 > n);
        }
        b = new String(cArray).intern();
    }
}
