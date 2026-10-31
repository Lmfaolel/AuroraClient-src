package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.setting.StringSetting;
import dev.quelchi.aurora.util.SkinFetcher;
import dev.quelchi.aurora.util.TextReplacer;
import java.util.Optional;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.SkinTextures;
import net.minecraft.text.Text;

public final class AntiAltModule
extends Module {
    private static AntiAltModule instance;
    private final StringSetting name;
    private final StringSetting skinName;
    private volatile SkinTextures skinTextures;
    private volatile String fetchedFor;
    private volatile boolean fetchSucceeded;
    private static final String[] b;

    public static AntiAltModule get() {
        return instance;
    }

    public AntiAltModule() {
        String[] stringArray = b;
        super(stringArray[0], stringArray[1], Category.PLAYER, false);
        this.name = new StringSetting(stringArray[3], "", 16);
        this.skinName = new StringSetting(stringArray[2], "", 16);
        this.fetchedFor = "";
        instance = this;
        this.addSettings(this.name, this.skinName);
    }

    @Override
    public void onEnable() {
        this.refetchSkinIfNeeded();
    }

    @Override
    public void onSettingsChanged() {
        this.refetchSkinIfNeeded();
    }

    private void refetchSkinIfNeeded() {
        block16: {
            String string;
            block13: {
                block15: {
                    AntiAltModule antiAltModule;
                    boolean bl;
                    block14: {
                        bl = AmbienceModule.b;
                        string = this.skinName.getValue().trim();
                        if (bl) break block13;
                        if (!string.equals(this.fetchedFor)) break block14;
                        antiAltModule = this;
                        if (bl) break block15;
                        if (!antiAltModule.fetchSucceeded) break block14;
                        return;
                    }
                    this.fetchedFor = string;
                    this.fetchSucceeded = false;
                    if (bl) break block16;
                    antiAltModule = this;
                }
                antiAltModule.skinTextures = null;
            }
            if (string.isEmpty()) {
                return;
            }
            SkinFetcher.fetch(string).thenAccept(arg_0 -> this.lambda$refetchSkinIfNeeded$0(string, arg_0));
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public Text spoofedName() {
        AntiAltModule antiAltModule;
        block9: {
            boolean bl;
            block8: {
                bl = AmbienceModule.b;
                antiAltModule = this;
                if (bl) break block8;
                if (!antiAltModule.isEnabled()) return null;
                antiAltModule = this;
            }
            if (bl) return Text.method_43470((String)antiAltModule.name.getValue());
            if (!antiAltModule.name.isEmpty()) break block9;
            return null;
        }
        antiAltModule = this;
        return Text.method_43470((String)antiAltModule.name.getValue());
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public Text replaceInChat(Text text) {
        String string;
        String string2;
        block15: {
            boolean bl;
            block14: {
                Text text2;
                block13: {
                    AntiAltModule antiAltModule;
                    block12: {
                        bl = AmbienceModule.b;
                        antiAltModule = this;
                        if (bl) break block12;
                        if (!antiAltModule.isEnabled()) break block13;
                        antiAltModule = this;
                    }
                    if (antiAltModule.name.isEmpty()) break block13;
                    text2 = text;
                    if (bl) return text2;
                    if (text2 != null) break block14;
                }
                text2 = text;
                return text2;
            }
            string2 = MinecraftClient.method_1551().method_1548().method_1676();
            string = string2;
            if (bl) break block15;
            if (string == null) return text;
            string = string2;
        }
        if (!string.isEmpty()) return TextReplacer.replace(text, string2, this.name.getValue());
        return text;
    }

    public SkinTextures spoofedSkin() {
        SkinTextures skinTextures;
        block6: {
            block5: {
                AntiAltModule antiAltModule;
                block4: {
                    boolean bl = AmbienceModule.b;
                    antiAltModule = this;
                    if (bl) break block4;
                    if (!antiAltModule.isEnabled()) break block5;
                    antiAltModule = this;
                }
                skinTextures = antiAltModule.skinTextures;
                break block6;
            }
            skinTextures = null;
        }
        return skinTextures;
    }

    private void lambda$refetchSkinIfNeeded$0(String string, Optional optional) {
        block7: {
            AntiAltModule antiAltModule;
            block8: {
                boolean bl = AmbienceModule.b;
                antiAltModule = this;
                if (bl) break block7;
                if (antiAltModule.fetchedFor.equals(string)) break block8;
                return;
            }
            this.skinTextures = optional.orElse(null);
            antiAltModule = this;
        }
        antiAltModule.fetchSucceeded = optional.isPresent();
        if (optional.isEmpty()) {
            Aurora.LOG.warn(b[4], (Object)string);
        }
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[5];
                var3_1 = 0;
                var2_2 = "J5c(,& \u007f\u0013X+x.jG\"j6ram\t(+(|(b\rX0~/,2?n)y a\u0002";
                var4_3 = "J5c(,& \u007f\u0013X+x.jG\"j6ram\t(+(|(b\rX0~/,2?n)y a\u0002".length();
                var1_4 = 8;
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
                    var2_2 = "E:z$'J5c(,& \u007fa7\"c\u0012 o{y.xG*n/t),\u0014'b57'c\u0015l, jf";
                    var4_3 = "E:z$'J5c(,& \u007fa7\"c\u0012 o{y.xG*n/t),\u0014'b57'c\u0015l, jf".length();
                    var1_4 = 4;
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
                        v10 = 11;
                        break;
                    }
                    case 1: {
                        v10 = 91;
                        break;
                    }
                    case 2: {
                        v10 = 23;
                        break;
                    }
                    case 3: {
                        v10 = 65;
                        break;
                    }
                    case 4: {
                        v10 = 12;
                        break;
                    }
                    case 5: {
                        v10 = 103;
                        break;
                    }
                    default: {
                        v10 = 76;
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
        AntiAltModule.b = var5;
    }
}
