package dev.quelchi.aurora.nostalgia;

import dev.quelchi.aurora.module.Module;
import java.util.HashMap;
import java.util.Map;
import java.util.OptionalInt;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.ColorHelper;

public final class OldPotions {
    private static final Map<RegistryKey<StatusEffect>, Integer> PALETTE;
    public static boolean a;

    private OldPotions() {
    }

    private static void put(RegistryEntry<StatusEffect> registryEntry, int n) {
        registryEntry.method_40230().ifPresent(arg_0 -> OldPotions.lambda$put$0(n, arg_0));
    }

    private static int colorOf(RegistryEntry<StatusEffect> registryEntry) {
        int n;
        block4: {
            StatusEffect statusEffect;
            block3: {
                Integer n2;
                block2: {
                    statusEffect = (StatusEffect)registryEntry.comp_349();
                    Integer n3 = registryEntry.method_40230().map(PALETTE::get).orElse(null);
                    boolean bl = a;
                    n2 = n3;
                    if (bl) break block2;
                    if (n2 == null) break block3;
                    n2 = n3;
                }
                n = n2;
                break block4;
            }
            n = statusEffect.method_5556();
        }
        return n;
    }

    public static OptionalInt mix(Iterable<StatusEffectInstance> iterable) {
        OptionalInt optionalInt;
        block18: {
            int n;
            block17: {
                int n2;
                int n3;
                int n4;
                int n5;
                block14: {
                    n5 = 0;
                    n4 = 0;
                    n3 = 0;
                    boolean bl = a;
                    n2 = 0;
                    for (StatusEffectInstance statusEffectInstance : iterable) {
                        StatusEffectInstance statusEffectInstance2;
                        block15: {
                            if (bl) break block14;
                            statusEffectInstance2 = statusEffectInstance;
                            if (bl) break block15;
                            if (!statusEffectInstance2.method_5581()) {
                                continue;
                            }
                            statusEffectInstance2 = statusEffectInstance;
                        }
                        int n6 = OldPotions.colorOf((RegistryEntry<StatusEffect>)statusEffectInstance2.method_5579());
                        int n7 = statusEffectInstance.method_5578() + 1;
                        n5 += n7 * ColorHelper.method_61327((int)n6);
                        n4 += n7 * ColorHelper.method_61329((int)n6);
                        n3 += n7 * ColorHelper.method_61331((int)n6);
                        n2 += n7;
                        if (!bl) continue;
                        boolean bl2 = !Module.a;
                        Module.a = bl2;
                        break;
                    }
                    n = n2;
                    if (bl) break block17;
                    if (n != 0) break block14;
                    optionalInt = OptionalInt.empty();
                    break block18;
                }
                n = ColorHelper.method_61323((int)(n5 / n2), (int)(n4 / n2), (int)(n3 / n2));
            }
            optionalInt = OptionalInt.of(n);
        }
        return optionalInt;
    }

    private static void lambda$put$0(int n, RegistryKey registryKey) {
        PALETTE.put((RegistryKey<StatusEffect>)registryKey, n);
    }

    
    static {
        block8: {
            block7: {
                var1 = 3439700681365621970L;
                var0_1 = new long[23];
                var4_2 = 0;
                var5_3 = "\u00a5\u00a0Vh\u009e\u008f\u00f1\u00f1\u00c4x\\\u00ad\u009eh\u0093s\u00b5\u00f3\u00e3\u007f\u009e\u00e4\u00a8\u00f1\u00b0~\u007f^\u009e?\u00c1\u009a\u00ae\u0006\u0096d\u009e/\u00fa\u0081V\rvO\u009e\u008f\u00a8\u00f1+.\u008a\u0091\u009e=\u00ce\u00c5&\u00ce*W\u009eB\u00a6\u00f5\u0086\u00c5Et\u009e\u000b#\u0014\u0018\u0005yi\u009e\u0093\u0016\u00e8\u00bdI\u00c0\u00c5\u009e\u008f\u00a8\u00f1'5W\n\u009e-\u00e0S\u00ce,#i\u009e\u00aeL\u0091In\u00f5v\u009eY\u00deK\u00ea_&f\u009e\u00ee\u00c9\u00e8\u00b5A]G\u009eR\u00dew(OQB\u009eUs\u009e\u00eb\u0017\u00ac\u00f6\u009e\"\u0091\u0098V\u00fdl\u00b0\u009eh\u0093\u00f1J\u0012\u001e\u0093\u009e4\u0086\u00db\u00e4]\u00e2]\u009e9\u001f\u00e3";
                var6_4 = "\u00a5\u00a0Vh\u009e\u008f\u00f1\u00f1\u00c4x\\\u00ad\u009eh\u0093s\u00b5\u00f3\u00e3\u007f\u009e\u00e4\u00a8\u00f1\u00b0~\u007f^\u009e?\u00c1\u009a\u00ae\u0006\u0096d\u009e/\u00fa\u0081V\rvO\u009e\u008f\u00a8\u00f1+.\u008a\u0091\u009e=\u00ce\u00c5&\u00ce*W\u009eB\u00a6\u00f5\u0086\u00c5Et\u009e\u000b#\u0014\u0018\u0005yi\u009e\u0093\u0016\u00e8\u00bdI\u00c0\u00c5\u009e\u008f\u00a8\u00f1'5W\n\u009e-\u00e0S\u00ce,#i\u009e\u00aeL\u0091In\u00f5v\u009eY\u00deK\u00ea_&f\u009e\u00ee\u00c9\u00e8\u00b5A]G\u009eR\u00dew(OQB\u009eUs\u009e\u00eb\u0017\u00ac\u00f6\u009e\"\u0091\u0098V\u00fdl\u00b0\u009eh\u0093\u00f1J\u0012\u001e\u0093\u009e4\u0086\u00db\u00e4]\u00e2]\u009e9\u001f\u00e3".length();
                var3_5 = 0;
                while (true) {
                    var7_6 = var5_3.substring(var3_5, var3_5 += 8).getBytes("ISO-8859-1");
                    v0 = var0_1;
                    v1 = var4_2++;
                    v2 = ((long)var7_6[0] & 255L) << 56 | ((long)var7_6[1] & 255L) << 48 | ((long)var7_6[2] & 255L) << 40 | ((long)var7_6[3] & 255L) << 32 | ((long)var7_6[4] & 255L) << 24 | ((long)var7_6[5] & 255L) << 16 | ((long)var7_6[6] & 255L) << 8 | (long)var7_6[7] & 255L;
                    v3 = -1;
                    break block7;
                    break;
                }
lbl14:

                while (true) {
                    v0[v1] = v4;
                    if (var3_5 < var6_4) continue;
                    var5_3 = ">6\u000f{\u009e\u00ba\u00d0y\u00bb\u0012\u00f5\u00ec\u009e\b\u000f@";
                    var6_4 = ">6\u000f{\u009e\u00ba\u00d0y\u00bb\u0012\u00f5\u00ec\u009e\b\u000f@".length();
                    var3_5 = 0;
                    while (true) {
                        var7_6 = var5_3.substring(var3_5, var3_5 += 8).getBytes("ISO-8859-1");
                        v0 = var0_1;
                        v1 = var4_2++;
                        v2 = ((long)var7_6[0] & 255L) << 56 | ((long)var7_6[1] & 255L) << 48 | ((long)var7_6[2] & 255L) << 40 | ((long)var7_6[3] & 255L) << 32 | ((long)var7_6[4] & 255L) << 24 | ((long)var7_6[5] & 255L) << 16 | ((long)var7_6[6] & 255L) << 8 | (long)var7_6[7] & 255L;
                        v3 = 0;
                        break block7;
                        break;
                    }
                    break;
                }
lbl27:

                while (true) {
                    v0[v1] = v4;
                    if (var3_5 < var6_4) continue;
                    break block8;
                    break;
                }
            }
            v4 = v2 ^ var1;
            switch (v3) {
                default: {
                    continue;
                }
                case 0:
lbl38:

                continue;
            }
        }
        OldPotions.PALETTE = new HashMap<RegistryKey<StatusEffect>, Integer>();
        OldPotions.put((RegistryEntry<StatusEffect>)StatusEffects.field_5904, (int)var0_1[8]);
        OldPotions.put((RegistryEntry<StatusEffect>)StatusEffects.field_5909, (int)var0_1[11]);
        OldPotions.put((RegistryEntry<StatusEffect>)StatusEffects.field_5917, (int)var0_1[12]);
        OldPotions.put((RegistryEntry<StatusEffect>)StatusEffects.field_5901, (int)var0_1[6]);
        OldPotions.put((RegistryEntry<StatusEffect>)StatusEffects.field_5910, (int)var0_1[2]);
        OldPotions.put((RegistryEntry<StatusEffect>)StatusEffects.field_5915, (int)var0_1[5]);
        OldPotions.put((RegistryEntry<StatusEffect>)StatusEffects.field_5921, (int)var0_1[19]);
        OldPotions.put((RegistryEntry<StatusEffect>)StatusEffects.field_5913, (int)var0_1[16]);
        OldPotions.put((RegistryEntry<StatusEffect>)StatusEffects.field_5916, (int)var0_1[17]);
        OldPotions.put((RegistryEntry<StatusEffect>)StatusEffects.field_5924, (int)var0_1[21]);
        OldPotions.put((RegistryEntry<StatusEffect>)StatusEffects.field_5907, (int)var0_1[14]);
        OldPotions.put((RegistryEntry<StatusEffect>)StatusEffects.field_5918, (int)var0_1[9]);
        OldPotions.put((RegistryEntry<StatusEffect>)StatusEffects.field_5923, (int)var0_1[13]);
        OldPotions.put((RegistryEntry<StatusEffect>)StatusEffects.field_5905, (int)var0_1[22]);
        OldPotions.put((RegistryEntry<StatusEffect>)StatusEffects.field_5919, (int)var0_1[18]);
        OldPotions.put((RegistryEntry<StatusEffect>)StatusEffects.field_5925, (int)var0_1[1]);
        OldPotions.put((RegistryEntry<StatusEffect>)StatusEffects.field_5903, (int)var0_1[4]);
        OldPotions.put((RegistryEntry<StatusEffect>)StatusEffects.field_5911, (int)var0_1[3]);
        OldPotions.put((RegistryEntry<StatusEffect>)StatusEffects.field_5899, (int)var0_1[20]);
        OldPotions.put((RegistryEntry<StatusEffect>)StatusEffects.field_5920, (int)var0_1[7]);
        OldPotions.put((RegistryEntry<StatusEffect>)StatusEffects.field_5914, (int)var0_1[0]);
        OldPotions.put((RegistryEntry<StatusEffect>)StatusEffects.field_5898, (int)var0_1[15]);
        OldPotions.put((RegistryEntry<StatusEffect>)StatusEffects.field_5922, (int)var0_1[10]);
    }
}
