package dev.quelchi.aurora.gui.shell;

import dev.quelchi.aurora.cosmetics.backend.AccountCapes;
import dev.quelchi.aurora.cosmetics.content.Colorways;
import dev.quelchi.aurora.cosmetics.content.Cosmetics;
import dev.quelchi.aurora.cosmetics.content.Emotes;
import dev.quelchi.aurora.cosmetics.content.Emotes$Emote;
import dev.quelchi.aurora.cosmetics.content.PremiumCapes;
import dev.quelchi.aurora.cosmetics.content.Trails;
import dev.quelchi.aurora.cosmetics.content.Trails$Trail;
import dev.quelchi.aurora.cosmetics.content.Wings;
import dev.quelchi.aurora.cosmetics.content.Wings$Wing;
import dev.quelchi.aurora.cosmetics.renderer.EffectList;
import dev.quelchi.aurora.gui.shell.SearchIndex;
import dev.quelchi.aurora.gui.shell.Wardrobe$Section;
import dev.quelchi.aurora.render.EffectEngine$Effect;
import java.lang.invoke.LambdaMetafactory;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import net.minecraft.client.MinecraftClient;

final class Wardrobe {
    private static final String a;

    private Wardrobe() {
    }

    
    static List<String> catalogue(Wardrobe$Section var0) {
        block32: {
            block31: {
                var2_1 = new ArrayList<String>();
                var1_2 = SearchIndex.a;
                v0 = var0;
                if (var1_2) break block31;
                {
                    switch (v0.ordinal())
                }
lbl_neg_1000:

                {
                    case 0: {
                        for (Record var4_4 : AccountCapes.owned()) {
                            v2 = var2_1.add(Wardrobe.a + var4_4.id());
                            if (!var1_2) {
                                if (!var1_2) continue;
                                break;
                            }
                        }
                        v2 = var2_1.addAll(PremiumCapes.ids());
lbl21:

                        if (!var1_2) // goto
                    }
lbl22:

                    case 1: {
                        for (Record var4_4 : Wings.all()) {
                            v4 = var2_1;
                            if (!var1_2) {
                                v4.add(var4_4.id());
                                if (!var1_2) continue;
                                break;
                            }
                            break block32;
                        }
                        if (!var1_2) // goto
                    }
lbl35:

                    case 2: {
                        for (Record var4_4 : Trails.all()) {
                            var2_1.add(var4_4.id());
                            if (!var1_2) {
                                if (!var1_2) continue;
                                break;
                            }
                        }
                        if (!var1_2) // goto
                    }
lbl47:

                    case 3: 
                    case 4: 
                    case 5: {
                        for (Record var4_4 : EffectList.of(var0.cat).all()) {
                            var2_1.add(var4_4.id());
                            if (!var1_2) {
                                if (!var1_2) continue;
                                break;
                            }
                        }
                        if (!var1_2) // goto
                    }
lbl59:

                    case 6: {
                        for (Record var4_4 : Emotes.all()) {
                            if (var1_2) break;
                            var2_1.add(var4_4.id());
                            if (!var1_2) continue;
                            break;
                        }
                    }
lbl69:

                    default: {
                        v0 = var0;
                    }
                }
            }
            if (v0 != Wardrobe$Section.CAPES) {
                var2_1.removeIf((Predicate<String>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Z, lambda$catalogue$0(dev.quelchi.aurora.gui.shell.Wardrobe$Section java.lang.String ), (Ljava/lang/String;)Z)((Wardrobe$Section)var0));
            }
            v4 = var2_1;
        }
        return v4;
    }

    static boolean lockedIn(Wardrobe$Section wardrobe$Section, String string) {
        Wardrobe$Section wardrobe$Section2;
        block6: {
            block7: {
                UUID uUID;
                block10: {
                    MinecraftClient minecraftClient;
                    block8: {
                        MinecraftClient minecraftClient2;
                        block9: {
                            boolean bl = SearchIndex.a;
                            wardrobe$Section2 = wardrobe$Section;
                            if (bl) break block6;
                            if (wardrobe$Section2 != Wardrobe$Section.CAPES) break block7;
                            minecraftClient2 = MinecraftClient.method_1551();
                            minecraftClient = minecraftClient2;
                            if (bl) break block8;
                            if (minecraftClient.method_1548() != null) break block9;
                            uUID = null;
                            break block10;
                        }
                        minecraftClient = minecraftClient2;
                    }
                    uUID = minecraftClient.method_1548().method_44717();
                }
                return Cosmetics.isCapeLocked(uUID, string, true);
            }
            wardrobe$Section2 = wardrobe$Section;
        }
        return Cosmetics.locked(wardrobe$Section2.cat, string);
    }

    
    static String labelOf(Wardrobe$Section wardrobe$Section, String string) {
        boolean bl = SearchIndex.a;
        if (wardrobe$Section == Wardrobe$Section.CAPES) {
            String string2;
            String string3 = string;
            if (!bl) {
                if (AccountCapes.isAccount(string3)) {
                    string2 = AccountCapes.alias(string);
                    return string2;
                }
                string3 = string;
            }
            string2 = PremiumCapes.displayName(string3);
            return string2;
        }
        String string4 = Colorways.designOf(string);
        switch (wardrobe$Section.ordinal()) {
            case 1: {
                for (Wings$Wing wings$Wing : Wings.all()) {
                    if (bl) return string4;
                    Wings$Wing wings$Wing2 = wings$Wing;
                    if (bl) return wings$Wing2.label();
                    if (wings$Wing2.id().equals(string4)) {
                        wings$Wing2 = wings$Wing;
                        return wings$Wing2.label();
                    }
                    if (!bl) continue;
                }
                if (!bl) return string4;
            }
            case 6: {
                for (Emotes$Emote emotes$Emote : Emotes.all()) {
                    if (bl) return string4;
                    Emotes$Emote emotes$Emote2 = emotes$Emote;
                    if (bl) return emotes$Emote2.label();
                    if (emotes$Emote2.id().equals(string4)) {
                        emotes$Emote2 = emotes$Emote;
                        return emotes$Emote2.label();
                    }
                    if (!bl) continue;
                }
                if (!bl) return string4;
            }
            case 2: {
                Object object = Trails.all().iterator();
                while (object.hasNext()) {
                    Trails$Trail trails$Trail = (Trails$Trail)object.next();
                    if (bl) return string4;
                    Trails$Trail trails$Trail2 = trails$Trail;
                    if (bl) return trails$Trail2.label();
                    if (trails$Trail2.id().equals(string4)) {
                        trails$Trail2 = trails$Trail;
                        return trails$Trail2.label();
                    }
                    if (!bl) continue;
                }
                if (!bl) return string4;
            }
            case 3: 
            case 4: 
            case 5: {
                Object object;
                Object object2 = object = EffectList.of(wardrobe$Section.cat).design(string4);
                if (bl) return ((EffectEngine$Effect)object2).label();
                if (object2 == null) return string4;
                object2 = object;
                return ((EffectEngine$Effect)object2).label();
            }
        }
        return string4;
    }

    private static boolean lambda$catalogue$0(Wardrobe$Section wardrobe$Section, String string) {
        boolean bl;
        block2: {
            block3: {
                boolean bl2 = SearchIndex.a;
                bl = Cosmetics.offered(wardrobe$Section.cat, string);
                if (bl2) break block2;
                if (bl) break block3;
                bl = true;
                break block2;
            }
            bl = false;
        }
        return bl;
    }

    
    static {
        char[] cArray;
        block11: {
            int n;
            int n2;
            block10: {
                char[] cArray2 = "\u001a\u0014\u0001".toCharArray();
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
                    case 0 -> 119;
                    case 1 -> 119;
                    case 2 -> 94;
                    case 3 -> 91;
                    case 4 -> 4;
                    case 5 -> 125;
                    default -> 80;
                }));
                ++n;
                n2 = n2;
            } while (n2 > n);
        }
        a = new String(cArray).intern();
    }
}
