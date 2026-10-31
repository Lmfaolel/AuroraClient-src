package dev.quelchi.aurora.gui.im;

import dev.quelchi.aurora.Aurora;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.Arrays;
import net.fabricmc.loader.api.FabricLoader;

final class Im$Lib {
    static final boolean THERE;
    private static final String[] a;

    private Im$Lib() {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static boolean there() {
        String[] stringArray;
        try {
            stringArray = a;
            Class.forName(stringArray[5], false, Im$Lib.class.getClassLoader());
        }
        catch (Throwable throwable) {
            return false;
        }
        try {
            boolean bl = Im$Lib.unpack();
            try {
                stringArray = a;
                Class.forName(stringArray[1], true, Im$Lib.class.getClassLoader());
                if (!bl) return true;
            }
            catch (Throwable throwable) {
                if (!bl) throw throwable;
                System.clearProperty(a[3]);
                throw throwable;
            }
            System.clearProperty(stringArray[3]);
            return true;
        }
        catch (Throwable throwable) {
            Aurora.LOG.warn(a[0], throwable);
            return false;
        }
    }

    
    private static boolean unpack() throws IOException {
        if (System.getProperty(a[10]) != null) return false;
        if (!System.getProperty(a[2]).toLowerCase().contains(a[7])) {
            return false;
        }
        String[] stringArray = a;
        try (InputStream inputStream = Im$Lib.class.getClassLoader().getResourceAsStream(stringArray[9]);){
            if (inputStream == null) {
                boolean bl = false;
                return bl;
            }
            byte[] byArray = inputStream.readAllBytes();
            stringArray = a;
            Path path = FabricLoader.getInstance().getGameDir().resolve(stringArray[4]).resolve(stringArray[8]);
            Path path2 = path.resolve(stringArray[6]);
            if (!Files.exists(path2, new LinkOption[0]) || !Arrays.equals(Files.readAllBytes(path2), byArray)) {
                Files.createDirectories(path, new FileAttribute[0]);
                Files.write(path2, byArray, new OpenOption[0]);
            }
            System.setProperty(a[3], path.toString());
            boolean bl = true;
            return bl;
        }
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[11];
                var3_1 = 0;
                var2_2 = "Y(\u0005Z\u0010PpY!BA\u0016\u00044\\*\u0003KUPUE7\r]\u0018Wg\u0010(\u0007A\f\u00034Q7\u0007\u000f\f\u001euF$\u000bC\u0018\u0012xUe\u0016G\u0010\u00034\\$\u0017A\u001a\u0018\u000bY(\u0005Z\u0010^]]\u0002\u0017F\u0007_6LA\u0018\u001dq\u0012Y(\u0005Z\u0010^xY'\u0010N\u000b\t:@$\u0016G\u0006Q0\u0010@\u000b\u0011\u000bY(\u0005Z\u0010^]]\u0002\u0017F\u0010Y(\u0005Z\u0010]~Q3\u0003\u0019M^p\\)\u0003G,\f\u0007^$\u0016F\u000f\u0015g";
                var4_3 = "Y(\u0005Z\u0010PpY!BA\u0016\u00044\\*\u0003KUPUE7\r]\u0018Wg\u0010(\u0007A\f\u00034Q7\u0007\u000f\f\u001euF$\u000bC\u0018\u0012xUe\u0016G\u0010\u00034\\$\u0017A\u001a\u0018\u000bY(\u0005Z\u0010^]]\u0002\u0017F\u0007_6LA\u0018\u001dq\u0012Y(\u0005Z\u0010^xY'\u0010N\u000b\t:@$\u0016G\u0006Q0\u0010@\u000b\u0011\u000bY(\u0005Z\u0010^]]\u0002\u0017F\u0010Y(\u0005Z\u0010]~Q3\u0003\u0019M^p\\)\u0003G,\f\u0007^$\u0016F\u000f\u0015g".length();
                var1_4 = 62;
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
                    var2_2 = "Y*MF\u0014\u0017aYj\bN\u000f\u0011;^$\u0016F\u000f\u00159R,\f\u0000\u0010\u001dsE,OE\u0018\u0006u\u0006qLK\u0015\u001c\u0012Y(\u0005Z\u0010^xY'\u0010N\u000b\t:@$\u0016G";
                    var4_3 = "Y*MF\u0014\u0017aYj\bN\u000f\u0011;^$\u0016F\u000f\u00159R,\f\u0000\u0010\u001dsE,OE\u0018\u0006u\u0006qLK\u0015\u001c\u0012Y(\u0005Z\u0010^xY'\u0010N\u000b\t:@$\u0016G".length();
                    var1_4 = 41;
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
                        v10 = 48;
                        break;
                    }
                    case 1: {
                        v10 = 69;
                        break;
                    }
                    case 2: {
                        v10 = 98;
                        break;
                    }
                    case 3: {
                        v10 = 47;
                        break;
                    }
                    case 4: {
                        v10 = 121;
                        break;
                    }
                    case 5: {
                        v10 = 112;
                        break;
                    }
                    default: {
                        v10 = 20;
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
        Im$Lib.a = var5;
        Im$Lib.THERE = Im$Lib.there();
    }
}
