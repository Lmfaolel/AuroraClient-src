package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.cosmetics.backend.CosmeticsSync;
import dev.quelchi.aurora.gui.PreviewScene;
import dev.quelchi.aurora.util.BuildInfo;
import dev.quelchi.aurora.util.Daemon;
import java.lang.invoke.LambdaMetafactory;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Locale;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.SharedConstants;

public final class TitleHome {
    private static final String[] a;

    private TitleHome() {
    }

    
    public static String versionLine() {
        Object object;
        String[] stringArray;
        Object object2;
        int n;
        Object object3;
        block17: {
            block15: {
                boolean bl;
                String string;
                block16: {
                    block13: {
                        block14: {
                            object3 = CosmeticsSync.myVersion();
                            n = PreviewScene.a;
                            string = BuildInfo.dev();
                            bl = ((String)object3).isEmpty();
                            if (n != 0) break block13;
                            if (bl) break block14;
                            object3 = ((String)object3).toLowerCase(Locale.ROOT).replace(' ', '-');
                            if (n == 0) break block15;
                        }
                        bl = CosmeticsSync.releases().isEmpty();
                    }
                    if (n != 0) break block16;
                    if (bl) break block15;
                    object2 = string;
                    if (n != 0) break block17;
                    bl = ((String)object2).isEmpty();
                }
                if (!bl) {
                    stringArray = a;
                    object3 = stringArray[3] + string;
                }
            }
            stringArray = a;
            object2 = stringArray[2] + SharedConstants.method_16673().comp_4025();
        }
        String string = object2;
        Object object4 = object3;
        if (n == 0) {
            if (((String)object4).isEmpty()) {
                object = string;
                return object;
            }
            object4 = object3;
        }
        stringArray = a;
        object = stringArray[4] + (String)object4 + stringArray[8] + string;
        return object;
    }

    public static void removeOldFiles() {
        try {
            String[] stringArray = a;
            Files.deleteIfExists(FabricLoader.getInstance().getConfigDir().resolve(stringArray[0]));
        }
        catch (Exception exception) {
            String[] stringArray = a;
            Aurora.LOG.debug(stringArray[7], (Throwable)exception);
        }
        Daemon.run(a[1], TitleHome::lambda$removeOldFiles$0);
    }

    
    private static void lambda$removeOldFiles$0() {
        block21: {
            block19: {
                block23: {
                    var5 = TitleHome.a;
                    var1_1 = FabricLoader.getInstance().getGameDir().resolve(var5[6]);
                    var0_2 = PreviewScene.a;
                    v0 = var1_1;
                    if (var0_2 != 0) // goto
                    if (Files.isDirectory(v0, new LinkOption[]{LinkOption.NOFOLLOW_LINKS})) break block19;
                    break block23;
                    catch (Throwable v1) {
                        throw v1;
                    }
                }
                return;
            }
            try {
                block20: {
                    v0 = var1_1;
lbl19:

                    var2_3 = Files.walk(v0, new FileVisitOption[0]);
                    try {
                        for (Path var4_7 : (Iterable<T>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/util/Iterator;, iterator(), ()Ljava/util/Iterator;)(var2_3.sorted(Comparator.reverseOrder()))) {
                            if (var0_2 == 0) {
                                Files.deleteIfExists(var4_7);
                                if (var0_2 == 0) continue;
                                break;
                            }
                            break block20;
                        }
                        var5 = TitleHome.a;
                        Aurora.LOG.info(var5[9]);
                        if (var0_2 != 0) break block21;
                    }
                    catch (Throwable var3_6) {
                        block22: {
                            if (var0_2 != 0 || var2_3 == null) break block22;
                            try {
                                var2_3.close();
                            }
                            catch (Throwable var4_8) {
                                var3_6.addSuppressed(var4_8);
                            }
                        }
                        throw var3_6;
                    }
                }
                if (var2_3 != null) {
                    var2_3.close();
                }
            }
            catch (Exception var2_4) {
                Aurora.LOG.debug(TitleHome.a[5], (Throwable)var2_4);
            }
        }
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[10];
                var3_1 = 0;
                var2_2 = "\u001e\u0018s\u0019\b\u0002*\u0013\fr\u0002W\u0010b\r\u001bd\u0004T\u0017\u007f\u000b\u000f\u001e\u0018s\u0019\b\u0002*\u0010\u0001e\u0015\u001b\u0000o\u001a\n2\u0004o\u0013\u0019\u0011f\u0019\u0019!\u0004;\bw[\u0007>\u0018s\u0019\b\u0002'&<\u0002t\u001a\u001eCi\u0010\u0019!\u0004\u001f\u000eh\t\b!\u0002\u0012\u0006'\u0010\u0001eV\t\u0017h\r\u0004d\u0005Z\u0000f\u001c\u0005d\u0014\u001e\u0018s\u0019\b\u0002(\u001c\fb\u001e\u001fLt\u000b\u0002s\u001f\u001f\u0010)<\u0002t\u001a\u001eCi\u0010\u0019!\u0004\u001f\u000eh\t\b!\u0002\u0012\u0006'\u0010\u0001eV\u0016\u0002t\u000bMr\u0013\b\u0015b\rMg\u001f\u0016\u0006";
                var4_3 = "\u001e\u0018s\u0019\b\u0002*\u0013\fr\u0002W\u0010b\r\u001bd\u0004T\u0017\u007f\u000b\u000f\u001e\u0018s\u0019\b\u0002*\u0010\u0001e\u0015\u001b\u0000o\u001a\n2\u0004o\u0013\u0019\u0011f\u0019\u0019!\u0004;\bw[\u0007>\u0018s\u0019\b\u0002'&<\u0002t\u001a\u001eCi\u0010\u0019!\u0004\u001f\u000eh\t\b!\u0002\u0012\u0006'\u0010\u0001eV\t\u0017h\r\u0004d\u0005Z\u0000f\u001c\u0005d\u0014\u001e\u0018s\u0019\b\u0002(\u001c\fb\u001e\u001fLt\u000b\u0002s\u001f\u001f\u0010)<\u0002t\u001a\u001eCi\u0010\u0019!\u0004\u001f\u000eh\t\b!\u0002\u0012\u0006'\u0010\u0001eV\u0016\u0002t\u000bMr\u0013\b\u0015b\rMg\u001f\u0016\u0006".length();
                var1_4 = 22;
                var0_5 = -1;
lbl7:

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block18;
                    break;
                }
lbl12:

                while (true) {
                    var5[var3_1++] = v3.intern();
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        continue;
                    }
                    var2_2 = "_M\u001d-\bl\u0019\f\u0006c_\u0019i\u0013Z\fk\u001bMr\u0002\u0015\u0011n\u001a\u001e!\u0015\u001b\u0000o\u001a";
                    var4_3 = "_M\u001d-\bl\u0019\f\u0006c_\u0019i\u0013Z\fk\u001bMr\u0002\u0015\u0011n\u001a\u001e!\u0015\u001b\u0000o\u001a".length();
                    var1_4 = 2;
                    var0_5 = -1;
lbl21:

                    while (true) {
                        v4 = ++var0_5;
                        v1 = var2_2.substring(v4, v4 + var1_4);
                        v2 = 0;
                        break block18;
                        break;
                    }
                    break;
                }
lbl26:

                while (true) {
                    var5[var3_1++] = v3.intern();
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        continue;
                    }
                    break block19;
                    break;
                }
            }
            v5 = v1.toCharArray();
            v6 = v5;
            v7 = v5.length;
            var6_6 = 0;
            do {
                v6 = v6;
                v8 = var6_6;
                v9 = v6[v8];
                switch (var6_6 % 7) {
                    case 0: {
                        v10 = 127;
                        break;
                    }
                    case 1: {
                        v10 = 109;
                        break;
                    }
                    case 2: {
                        v10 = 1;
                        break;
                    }
                    case 3: {
                        v10 = 118;
                        break;
                    }
                    case 4: {
                        v10 = 122;
                        break;
                    }
                    case 5: {
                        v10 = 99;
                        break;
                    }
                    default: {
                        v10 = 7;
                    }
                }
                v6[v8] = (char)(v9 ^ v10);
                ++var6_6;
lbl65:

                v7 = v7;
            } while (v7 > var6_6);
            v3 = new String(v6);
            switch (v2) {
                default: {
                    continue;
                }
                case 0:
lbl73:

                continue;
            }
        }
        TitleHome.a = var5;
    }
}
