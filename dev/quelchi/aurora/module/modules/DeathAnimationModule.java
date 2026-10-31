package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.setting.NumberSetting;
import dev.quelchi.aurora.module.setting.StringSetting;
import java.util.Locale;
import net.minecraft.entity.Entity;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public final class DeathAnimationModule
extends Module {
    private static DeathAnimationModule instance;
    private final NumberSetting deathTime;
    private final NumberSetting overlayTime;
    private final NumberSetting opacity;
    private final StringSetting entities;
    private static final String[] b;
    private static final long d;

    public DeathAnimationModule() {
        String[] stringArray = b;
        super(stringArray[3], stringArray[4], Category.RENDER, false);
        this.deathTime = new NumberSetting(stringArray[0], 0.0, 100.0, 22.0, 1.0);
        this.overlayTime = new NumberSetting(stringArray[2], 0.0, 100.0, 22.0, 1.0);
        this.opacity = new NumberSetting(stringArray[7], 5.0, 100.0, 100.0, 5.0);
        this.entities = new StringSetting(stringArray[1], stringArray[5], 128);
        instance = this;
        this.addSettings(this.entities, this.deathTime, this.overlayTime, this.opacity);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean affects(Entity entity) {
        boolean bl;
        DeathAnimationModule deathAnimationModule;
        block27: {
            DeathAnimationModule deathAnimationModule2;
            block26: {
                deathAnimationModule = instance;
                bl = AmbienceModule.b;
                deathAnimationModule2 = deathAnimationModule;
                if (bl) break block26;
                if (deathAnimationModule2 == null) return false;
                deathAnimationModule2 = deathAnimationModule;
            }
            boolean bl2 = deathAnimationModule2.isEnabled();
            if (bl) return bl2;
            if (!bl2) return false;
            if (entity != null) break block27;
            return false;
        }
        Identifier identifier = Registries.field_41177.method_10221((Object)entity.method_5864());
        if (identifier == null) {
            return false;
        }
        String[] stringArray = deathAnimationModule.entities.getValue().split(",");
        int n = stringArray.length;
        int n2 = 0;
        do {
            block30: {
                Identifier identifier2;
                block33: {
                    Object object;
                    block31: {
                        String string;
                        block32: {
                            block28: {
                                block29: {
                                    if (n2 >= n) return false;
                                    String string2 = stringArray[n2];
                                    string = string2.trim().toLowerCase(Locale.ROOT).replace(' ', '_');
                                    object = string;
                                    if (bl) break block28;
                                    boolean bl3 = ((String)object).isEmpty();
                                    if (bl) return bl3;
                                    if (!bl3) break block29;
                                    break block30;
                                }
                                object = string;
                            }
                            if (bl) break block31;
                            if (!((String)object).contains(":")) break block32;
                            identifier2 = Identifier.method_12829((String)string);
                            break block33;
                        }
                        object = b[6] + string;
                    }
                    identifier2 = Identifier.method_12829((String)object);
                }
                Identifier identifier3 = identifier2;
                if (bl) continue;
                if (identifier3 == null) break block30;
                boolean bl4 = identifier3.equals((Object)identifier);
                if (bl) return bl4;
                if (!bl4) break block30;
                return true;
            }
            ++n2;
        } while (!bl);
        return false;
    }

    public static int deathTime() {
        int n;
        block6: {
            DeathAnimationModule deathAnimationModule;
            block4: {
                DeathAnimationModule deathAnimationModule2;
                block5: {
                    deathAnimationModule2 = instance;
                    boolean bl = AmbienceModule.b;
                    deathAnimationModule = deathAnimationModule2;
                    if (bl) break block4;
                    if (deathAnimationModule != null) break block5;
                    n = 20;
                    break block6;
                }
                deathAnimationModule = deathAnimationModule2;
            }
            n = deathAnimationModule.deathTime.getValueInt();
        }
        return n;
    }

    public static int overlayTime() {
        int n;
        block6: {
            DeathAnimationModule deathAnimationModule;
            block4: {
                DeathAnimationModule deathAnimationModule2;
                block5: {
                    deathAnimationModule2 = instance;
                    boolean bl = AmbienceModule.b;
                    deathAnimationModule = deathAnimationModule2;
                    if (bl) break block4;
                    if (deathAnimationModule != null) break block5;
                    n = 20;
                    break block6;
                }
                deathAnimationModule = deathAnimationModule2;
            }
            n = deathAnimationModule.overlayTime.getValueInt();
        }
        return n;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int fadeTint(float f) {
        double d;
        double d2;
        block13: {
            DeathAnimationModule deathAnimationModule;
            block14: {
                DeathAnimationModule deathAnimationModule2;
                boolean bl;
                block11: {
                    block12: {
                        block10: {
                            deathAnimationModule = instance;
                            bl = AmbienceModule.b;
                            deathAnimationModule2 = deathAnimationModule;
                            if (bl) break block10;
                            if (deathAnimationModule2 == null) return -1;
                            deathAnimationModule2 = deathAnimationModule;
                        }
                        if (bl) break block11;
                        if (deathAnimationModule2.isEnabled()) break block12;
                        return -1;
                    }
                    deathAnimationModule2 = deathAnimationModule;
                }
                d2 = deathAnimationModule2.opacity.getValue();
                double d3 = d2 - 99.5;
                d = d3 == 0.0 ? 0 : (d3 > 0.0 ? 1 : -1);
                if (bl) break block13;
                if (d < 0) break block14;
                return -1;
            }
            d = Math.max(1, deathAnimationModule.deathTime.getValueInt());
        }
        double d4 = d;
        float f2 = Math.max(0.0f, 1.0f - f / (float)d4);
        int n = Math.round(255.0f * (float)(d2 / 100.0) * f2);
        return Math.max(0, Math.min(255, n)) << 24 | (int)DeathAnimationModule.d;
    }

    
    static {
        block21: {
            block20: {
                block19: {
                    var7 = new String[8];
                    var5_1 = 0;
                    var4_2 = "$`\u0011{e\u0019i\th\u0015\b%k\u0004fyPx\u0013\f/s\u0015}aXd@q\u0019bh\u000f$`\u0011{e\u0019\\\u000el\u001dnyPr\u000e\u00152`\u001d`{\\=\u0014m\u0015/i\\|\u0014mPiaVm\u0006\u0010i\u0011vhK";
                    var6_3 = "$`\u0011{e\u0019i\th\u0015\b%k\u0004fyPx\u0013\f/s\u0015}aXd@q\u0019bh\u000f$`\u0011{e\u0019\\\u000el\u001dnyPr\u000e\u00152`\u001d`{\\=\u0014m\u0015/i\\|\u0014mPiaVm\u0006\u0010i\u0011vhK".length();
                    var3_4 = 10;
                    var2_5 = -1;
lbl7:

                    while (true) {
                        v0 = ++var2_5;
                        v1 = var4_2.substring(v0, v0 + var3_4);
                        v2 = -1;
                        break block19;
                        break;
                    }
lbl12:

                    while (true) {
                        var7[var5_1++] = v3.intern();
                        if ((var2_5 += var3_4) < var6_3) {
                            var3_4 = var4_2.charAt(var2_5);
                            continue;
                        }
                        var4_2 = "\rl\u001ejnK|\u0006qJ\u0007/u\u0011ldMd";
                        var6_3 = "\rl\u001ejnK|\u0006qJ\u0007/u\u0011ldMd".length();
                        var3_4 = 10;
                        var2_5 = -1;
lbl21:

                        while (true) {
                            v4 = ++var2_5;
                            v1 = var4_2.substring(v4, v4 + var3_4);
                            v2 = 0;
                            break block19;
                            break;
                        }
                        break;
                    }
lbl26:

                    while (true) {
                        var7[var5_1++] = v3.intern();
                        if ((var2_5 += var3_4) < var6_3) {
                            var3_4 = var4_2.charAt(var2_5);
                            continue;
                        }
                        break block20;
                        break;
                    }
                }
                v5 = v1.toCharArray();
                v6 = v5;
                v7 = v5.length;
                var8_6 = 0;
                do {
                    v6 = v6;
                    v8 = var8_6;
                    v9 = v6[v8];
                    switch (var8_6 % 7) {
                        case 0: {
                            v10 = 96;
                            break;
                        }
                        case 1: {
                            v10 = 5;
                            break;
                        }
                        case 2: {
                            v10 = 112;
                            break;
                        }
                        case 3: {
                            v10 = 15;
                            break;
                        }
                        case 4: {
                            v10 = 13;
                            break;
                        }
                        case 5: {
                            v10 = 57;
                            break;
                        }
                        default: {
                            v10 = 29;
                        }
                    }
                    v6[v8] = (char)(v9 ^ v10);
                    ++var8_6;
lbl65:

                    v7 = v7;
                } while (v7 > var8_6);
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
            DeathAnimationModule.b = var7;
            break block21;
lbl77:

            while (true) {
                continue;
                break;
            }
        }
        var0_7 = 1925446574769926361L;
        while (true)
        DeathAnimationModule.d = 6087101371563675430L ^ var0_7;
    }
}
