package dev.quelchi.aurora.cosmetics.renderer.wings;

import dev.quelchi.aurora.cosmetics.renderer.wings.WingModel;
import dev.quelchi.aurora.cosmetics.renderer.wings.WingModel$Face;
import dev.quelchi.aurora.module.Module;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.joml.Vector3f;

final class WingModel$Bone {
    final String name;
    final Vector3f pivot;
    final Vector3f rest;
    final List<WingModel.Face> faces;
    final List<WingModel$Bone> kids;
    final int side;
    final boolean tip;
    private static final String[] a;

    WingModel$Bone(String string, Vector3f vector3f, Vector3f vector3f2) {
        block14: {
            boolean bl;
            block16: {
                block15: {
                    int n;
                    boolean bl2;
                    String string2;
                    block12: {
                        block13: {
                            String string3;
                            String string4;
                            block10: {
                                block11: {
                                    this.faces = new ArrayList<WingModel.Face>();
                                    this.kids = new ArrayList<WingModel$Bone>();
                                    this.name = string;
                                    this.pivot = vector3f;
                                    this.rest = vector3f2;
                                    string2 = string.toLowerCase(Locale.ROOT);
                                    bl2 = WingModel.a;
                                    WingModel$Bone wingModel$Bone = this;
                                    string4 = string2;
                                    string3 = a[0];
                                    if (bl2) break block10;
                                    if (!string4.contains(string3)) break block11;
                                    n = 1;
                                    break block12;
                                }
                                string4 = string2;
                                String[] stringArray = a;
                                string3 = stringArray[2];
                            }
                            n = string4.contains(string3);
                            if (bl2) break block12;
                            if (n == 0) break block13;
                            n = -1;
                            break block12;
                        }
                        n = 0;
                    }
                    wingModel$Bone.side = n;
                    this.tip = string2.endsWith(a[1]);
                    if (!bl2) break block14;
                    if (!Module.a) break block15;
                    bl = false;
                    break block16;
                }
                bl = true;
            }
            Module.a = bl;
        }
    }

    
    static {
        block12: {
            var5 = new String[3];
            var3_1 = 0;
            var2_2 = "\"j pC\u0004\u000fw.h\u0004<f!l";
            var4_3 = "\"j pC\u0004\u000fw.h\u0004<f!l".length();
            var1_4 = 5;
            var0_5 = -1;
lbl7:

            while (true) {
                continue;
                break;
            }
lbl9:

            while (true) {
                var5[var3_1++] = new String(v0).intern();
                if ((var0_5 += var1_4) < var4_3) {
                    var1_4 = var2_2.charAt(var0_5);
                    continue;
                }
                break block12;
                break;
            }
            v1 = ++var0_5;
            v2 = var2_2.substring(v1, v1 + var1_4).toCharArray();
            v0 = v2;
            v3 = v2.length;
            var6_6 = 0;
            do {
                v0 = v0;
                v4 = var6_6;
                v5 = v0[v4];
                switch (var6_6 % 7) {
                    case 0: {
                        v6 = 80;
                        break;
                    }
                    case 1: {
                        v6 = 3;
                        break;
                    }
                    case 2: {
                        v6 = 71;
                        break;
                    }
                    case 3: {
                        v6 = 24;
                        break;
                    }
                    case 4: {
                        v6 = 55;
                        break;
                    }
                    case 5: {
                        v6 = 71;
                        break;
                    }
                    default: {
                        v6 = 90;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        WingModel$Bone.a = var5;
    }
}
