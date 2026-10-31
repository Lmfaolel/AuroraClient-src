package dev.quelchi.aurora.glass.hud;

import dev.quelchi.aurora.glass.hud.Anchor;
import dev.quelchi.aurora.glass.hud.GlassHudElement;
import dev.quelchi.aurora.glass.hud.HudPlacement;
import dev.quelchi.aurora.module.Module;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.Properties;
import net.minecraft.util.Identifier;

public final class HudLayoutStore {
    private final Path file;
    private final Properties values = new Properties();
    private String profile = a[12];
    private static final String[] a;

    public HudLayoutStore(Path path) {
        this.file = path;
        this.load();
    }

    public void apply(GlassHudElement glassHudElement) {
        String string = this.key(glassHudElement.id());
        int n = GlassHudElement.a;
        HudPlacement hudPlacement = glassHudElement.placement();
        hudPlacement.anchor(this.parseAnchor(this.values.getProperty(string + a[0]), hudPlacement.anchor()));
        hudPlacement.percent(this.readFloat(string + a[28], hudPlacement.percentX()), this.readFloat(string + a[16], hudPlacement.percentY()));
        hudPlacement.offset(this.readInt(string + a[7], hudPlacement.offsetX()), this.readInt(string + a[24], hudPlacement.offsetY()));
        hudPlacement.scale(this.readFloat(string + a[10], hudPlacement.scale()));
        hudPlacement.rotation(this.readFloat(string + a[23], hudPlacement.rotation()));
        glassHudElement.size(this.readInt(string + a[11], glassHudElement.width()), this.readInt(string + a[17], glassHudElement.height()));
        glassHudElement.locked(Boolean.parseBoolean(this.values.getProperty(string + a[27], Boolean.toString(glassHudElement.locked()))));
        if (Module.a) {
            GlassHudElement.a = ++n;
        }
    }

    public void capture(GlassHudElement glassHudElement) {
        block4: {
            boolean bl;
            block6: {
                block5: {
                    String string = this.key(glassHudElement.id());
                    HudPlacement hudPlacement = glassHudElement.placement();
                    String[] stringArray = a;
                    this.values.setProperty(string + stringArray[6], hudPlacement.anchor().name());
                    this.values.setProperty(string + stringArray[3], Float.toString(hudPlacement.percentX()));
                    this.values.setProperty(string + stringArray[14], Float.toString(hudPlacement.percentY()));
                    this.values.setProperty(string + stringArray[19], Integer.toString(hudPlacement.offsetX()));
                    this.values.setProperty(string + stringArray[21], Integer.toString(hudPlacement.offsetY()));
                    this.values.setProperty(string + stringArray[13], Float.toString(hudPlacement.scale()));
                    this.values.setProperty(string + stringArray[15], Float.toString(hudPlacement.rotation()));
                    this.values.setProperty(string + stringArray[26], Integer.toString(glassHudElement.width()));
                    this.values.setProperty(string + stringArray[1], Integer.toString(glassHudElement.height()));
                    this.values.setProperty(string + stringArray[9], Boolean.toString(glassHudElement.locked()));
                    int n = GlassHudElement.a;
                    if (n == 0) break block4;
                    if (!Module.a) break block5;
                    bl = false;
                    break block6;
                }
                bl = true;
            }
            Module.a = bl;
        }
    }

    
    public void save() {
        String[] stringArray = a;
        this.values.setProperty(stringArray[20], this.profile);
        int n = GlassHudElement.a;
        try {
            Path path2;
            Path path = path2 = this.file.getParent();
            if (n == 0) {
                if (path != null) {
                    Files.createDirectories(path2, new FileAttribute[0]);
                }
                path = this.file;
            }
            OutputStream outputStream = Files.newOutputStream(path, new OpenOption[0]);
            try {
                stringArray = a;
                this.values.store(outputStream, stringArray[25]);
                if (n != 0) return;
                if (outputStream == null) return;
            }
            catch (Throwable throwable) {
                if (n != 0) throw throwable;
                if (outputStream == null) throw throwable;
                try {
                    outputStream.close();
                    throw throwable;
                }
                catch (Throwable throwable2) {
                    throwable.addSuppressed(throwable2);
                }
                throw throwable;
            }
            outputStream.close();
            return;
        }
        catch (IOException iOException) {
            throw new IllegalStateException(a[4] + String.valueOf(this.file), iOException);
        }
    }

    
    public void load() {
        this.values.clear();
        String[] stringArray = a;
        this.profile = stringArray[8];
        if (!Files.isRegularFile(this.file, new LinkOption[0])) return;
        try (InputStream inputStream = Files.newInputStream(this.file, new OpenOption[0]);){
            this.values.load(inputStream);
            String string = this.values.getProperty(stringArray[22]);
            if (string == null) return;
            if (!string.matches(stringArray[2])) return;
            this.profile = string;
            return;
        }
        catch (IOException iOException) {
            throw new IllegalStateException(a[18] + String.valueOf(this.file), iOException);
        }
    }

    public void reset(Identifier identifier) {
        String string = this.key(identifier) + ".";
        this.values.keySet().removeIf(arg_0 -> HudLayoutStore.lambda$reset$0(string, arg_0));
    }

    private String key(Identifier identifier) {
        String string;
        block6: {
            HudLayoutStore hudLayoutStore;
            String string2;
            block4: {
                block5: {
                    string2 = identifier.method_12836() + "." + identifier.method_12832().replace('/', '.');
                    int n = GlassHudElement.a;
                    hudLayoutStore = this;
                    if (n != 0) break block4;
                    if (!hudLayoutStore.profile.equals(a[12])) break block5;
                    string = string2;
                    break block6;
                }
                hudLayoutStore = this;
            }
            string = a[5] + hudLayoutStore.profile + "." + string2;
        }
        return string;
    }

    private int readInt(String string, int n) {
        try {
            return Integer.parseInt(this.values.getProperty(string));
        }
        catch (RuntimeException runtimeException) {
            return n;
        }
    }

    private float readFloat(String string, float f) {
        try {
            return Float.parseFloat(this.values.getProperty(string));
        }
        catch (RuntimeException runtimeException) {
            return f;
        }
    }

    
    private Anchor parseAnchor(String string, Anchor anchor) {
        int n = GlassHudElement.a;
        String string2 = string;
        if (n != 0) return Anchor.valueOf(string2);
        if (string2 == null) {
            return anchor;
        }
        try {
            string2 = string;
            return Anchor.valueOf(string2);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            return anchor;
        }
    }

    private static boolean lambda$reset$0(String string, Object object) {
        return object.toString().startsWith(string);
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[29];
                var3_1 = 0;
                var2_2 = "<<X\bA\u0018'\u0007<5S\u0002N\u001f!\u0013I<\u001b\u0011hZ\u000f\"p\u000f4\u0004*.#q\u0005YT\t<-S\u0019J\u0012;f\u0005\u001dQ2C\u0007MW;})\u0016\u0018H\u000102\u0015c/\t\u001b4k2C\u001f\t\u0003:2\tb/Y\r@\u001b0as\u0007<<X\bA\u0018'\b<2P\rZ\u0012!J\u0007v8P\n\\\u001b!\u0007<1Y\bB\u00121\u0006<.U\nE\u0012\u0006<*_\u000f]\u001f\u0007v8P\n\\\u001b!\u0006<.U\nE\u0012\t<-S\u0019J\u0012;f\u0004\t</Y\u001fH\u0003<}3\t<-S\u0019J\u0012;f\u0004\u0007<5S\u0002N\u001f!\u001fQ2C\u0007MW;})\u0016\u0007F\u001612\u0015c/\t\u001b4k2C\u001f\t\u0011'}0\u0016\b<2P\rZ\u0012!J\u0012\u007f8B\n\u0007\u00166f4@\u000ey\u0005:t4Z\u000e\b<2P\rZ\u0012!K\u0012\u007f8B\n\u0007\u00166f4@\u000ey\u0005:t4Z\u000e\t</Y\u001fH\u0003<}3\b<2P\rZ\u0012!K\u0013U1W\u0018Z<<f}~>mW9s$Y\u001e]\u0006<*_\u000f]\u001f";
                var4_3 = "<<X\bA\u0018'\u0007<5S\u0002N\u001f!\u0013I<\u001b\u0011hZ\u000f\"p\u000f4\u0004*.#q\u0005YT\t<-S\u0019J\u0012;f\u0005\u001dQ2C\u0007MW;})\u0016\u0018H\u000102\u0015c/\t\u001b4k2C\u001f\t\u0003:2\tb/Y\r@\u001b0as\u0007<<X\bA\u0018'\b<2P\rZ\u0012!J\u0007v8P\n\\\u001b!\u0007<1Y\bB\u00121\u0006<.U\nE\u0012\u0006<*_\u000f]\u001f\u0007v8P\n\\\u001b!\u0006<.U\nE\u0012\t<-S\u0019J\u0012;f\u0004\t</Y\u001fH\u0003<}3\t<-S\u0019J\u0012;f\u0004\u0007<5S\u0002N\u001f!\u001fQ2C\u0007MW;})\u0016\u0007F\u001612\u0015c/\t\u001b4k2C\u001f\t\u0011'}0\u0016\b<2P\rZ\u0012!J\u0012\u007f8B\n\u0007\u00166f4@\u000ey\u0005:t4Z\u000e\b<2P\rZ\u0012!K\u0012\u007f8B\n\u0007\u00166f4@\u000ey\u0005:t4Z\u000e\t</Y\u001fH\u0003<}3\b<2P\rZ\u0012!K\u0013U1W\u0018Z<<f}~>mW9s$Y\u001e]\u0006<*_\u000f]\u001f".length();
                var1_4 = 7;
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
                    var2_2 = "<1Y\bB\u00121\t<-S\u0019J\u0012;f\u0005";
                    var4_3 = "<1Y\bB\u00121\t<-S\u0019J\u0012;f\u0005".length();
                    var1_4 = 7;
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
                        v10 = 18;
                        break;
                    }
                    case 1: {
                        v10 = 93;
                        break;
                    }
                    case 2: {
                        v10 = 54;
                        break;
                    }
                    case 3: {
                        v10 = 107;
                        break;
                    }
                    case 4: {
                        v10 = 41;
                        break;
                    }
                    case 5: {
                        v10 = 119;
                        break;
                    }
                    default: {
                        v10 = 85;
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
        HudLayoutStore.a = var5;
    }
}
