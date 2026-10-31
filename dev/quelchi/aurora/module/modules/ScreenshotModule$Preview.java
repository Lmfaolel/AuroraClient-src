package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.gui.ShotThumbs;
import dev.quelchi.aurora.gui.ShotThumbs$Picture;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import java.nio.file.Path;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;

final class ScreenshotModule$Preview
implements Toast {
    private static final int W;
    private static final int H;
    private final Path shot;
    private Toast.Visibility vis = Toast.Visibility.field_2210;
    private static final String a;
    private static final long[] b;
    private static final Integer[] c;

    ScreenshotModule$Preview(Path path) {
        this.shot = path;
    }

    public Toast.Visibility method_61988() {
        return this.vis;
    }

    public void method_61989(ToastManager toastManager, long l) {
        ScreenshotModule$Preview screenshotModule$Preview = this;
        Toast.Visibility visibility = (double)l >= 4000.0 * toastManager.method_48221() ? Toast.Visibility.field_2209 : Toast.Visibility.field_2210;
        screenshotModule$Preview.vis = visibility;
    }

    public int method_29049() {
        return 160;
    }

    public int method_29050() {
        return 90;
    }

    public void method_1986(DrawContext drawContext, TextRenderer textRenderer, long l) {
        block4: {
            block5: {
                drawContext.method_25294(0, 0, 160, 90, ScreenshotModule$Preview.a(27656, 5512293533164307657L));
                boolean bl = AmbienceModule.b;
                ShotThumbs$Picture shotThumbs$Picture = ShotThumbs.thumbnail(this.shot);
                if (bl) break block4;
                if (shotThumbs$Picture == null) break block5;
                ShotThumbs.cover(drawContext, shotThumbs$Picture, 2, 2, 156, 86);
            }
            drawContext.method_25294(2, 74, 158, 88, ScreenshotModule$Preview.a(2703, 3197622968472134220L));
            drawContext.method_25303(textRenderer, a, 6, 77, -1);
        }
    }

    
    static {
        block18: {
            block17: {
                block19: {
                    break block19;
lbl1:

                    while (true) {
                        continue;
                        break;
                    }
                }
                v0 = "LHC%U\u007f\u0005wDE`Cp\u0000zO".toCharArray();
                v1 = v0;
                v2 = v0.length;
                var8 = 0;
                do {
                    v1 = v1;
                    v3 = var8;
                    v4 = v1[v3];
                    switch (var8 % 7) {
                        case 0: {
                            v5 = 31;
                            break;
                        }
                        case 1: {
                            v5 = 43;
                            break;
                        }
                        case 2: {
                            v5 = 49;
                            break;
                        }
                        case 3: {
                            v5 = 64;
                            break;
                        }
                        case 4: {
                            v5 = 48;
                            break;
                        }
                        case 5: {
                            v5 = 17;
                            break;
                        }
                        default: {
                            v5 = 118;
                        }
                    }
                    v1[v3] = (char)(v4 ^ v5);
                    ++var8;
lbl36:

                    v2 = v2;
                } while (v2 > var8);
                while (true)
                ScreenshotModule$Preview.a = new String(v1).intern();
                var0_1 = 2870798008862092015L;
                var6_2 = new long[4];
                var3_3 = 0;
                var4_4 = "\u009enQ|[\u0010\u00bd\u00de\u009d-\u00c7\u0004\u0081\u00e5\u00a24";
                var5_5 = "\u009enQ|[\u0010\u00bd\u00de\u009d-\u00c7\u0004\u0081\u00e5\u00a24".length();
                var2_6 = 0;
                while (true) {
                    var7_7 = var4_4.substring(var2_6, var2_6 += 8).getBytes("ISO-8859-1");
                    v6 = var6_2;
                    v7 = var3_3++;
                    v8 = ((long)var7_7[0] & 255L) << 56 | ((long)var7_7[1] & 255L) << 48 | ((long)var7_7[2] & 255L) << 40 | ((long)var7_7[3] & 255L) << 32 | ((long)var7_7[4] & 255L) << 24 | ((long)var7_7[5] & 255L) << 16 | ((long)var7_7[6] & 255L) << 8 | (long)var7_7[7] & 255L;
                    v9 = -1;
                    break block17;
                    break;
                }
lbl55:

                while (true) {
                    v6[v7] = v10;
                    if (var2_6 < var5_5) continue;
                    var4_4 = "\u00c0\u00d0\u00ed\u00c3\u001a4\u00b6\u0011K\u00b6\u00b3\u001f}v\u00c8\u00a3";
                    var5_5 = "\u00c0\u00d0\u00ed\u00c3\u001a4\u00b6\u0011K\u00b6\u00b3\u001f}v\u00c8\u00a3".length();
                    var2_6 = 0;
                    while (true) {
                        var7_7 = var4_4.substring(var2_6, var2_6 += 8).getBytes("ISO-8859-1");
                        v6 = var6_2;
                        v7 = var3_3++;
                        v8 = ((long)var7_7[0] & 255L) << 56 | ((long)var7_7[1] & 255L) << 48 | ((long)var7_7[2] & 255L) << 40 | ((long)var7_7[3] & 255L) << 32 | ((long)var7_7[4] & 255L) << 24 | ((long)var7_7[5] & 255L) << 16 | ((long)var7_7[6] & 255L) << 8 | (long)var7_7[7] & 255L;
                        v9 = 0;
                        break block17;
                        break;
                    }
                    break;
                }
lbl68:

                while (true) {
                    v6[v7] = v10;
                    if (var2_6 < var5_5) continue;
                    break block18;
                    break;
                }
            }
            v10 = v8 ^ var0_1;
            switch (v9) {
                default: {
                    continue;
                }
                case 0:
lbl79:

                continue;
            }
        }
        ScreenshotModule$Preview.b = var6_2;
        ScreenshotModule$Preview.c = new Integer[4];
        ScreenshotModule$Preview.H = ScreenshotModule$Preview.a(29798, 3161625293939743908L);
        ScreenshotModule$Preview.W = ScreenshotModule$Preview.a(32593, 6838218983755159441L);
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x68C0;
        if (c[n2] == null) {
            ScreenshotModule$Preview.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }
}
