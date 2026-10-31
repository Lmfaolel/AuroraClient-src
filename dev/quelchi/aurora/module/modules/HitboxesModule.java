package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.TeamEditor;
import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.HitboxesModule$Group;
import dev.quelchi.aurora.module.modules.HitboxesModule$Kind;
import dev.quelchi.aurora.module.setting.ActionSetting;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.ColorSetting;
import dev.quelchi.aurora.module.setting.Setting;
import dev.quelchi.aurora.module.setting.StringSetting;
import java.util.Locale;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.debug.DebugHudEntries;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractWindChargeEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;

public final class HitboxesModule
extends Module {
    private static HitboxesModule instance;
    private final BooleanSetting hideModel;
    private final BooleanSetting heldItemOnBox;
    private final HitboxesModule$Group players;
    private final HitboxesModule$Group crystals;
    private final HitboxesModule$Group pearls;
    private final HitboxesModule$Group arrows;
    private final HitboxesModule$Group entities;
    private final HitboxesModule$Group misc;
    private final BooleanSetting teams;
    private final StringSetting teammates;
    private final ActionSetting editTeam;
    private final ColorSetting teamFill;
    private final ColorSetting teamLine;
    private final BooleanSetting showInvisible;
    private final BooleanSetting target;
    private final ColorSetting targetLine;
    private final ColorSetting targetFill;
    private String[] team;
    private static final String[] b;
    private static final long[] d;
    private static final Integer[] e;

    public static HitboxesModule get() {
        return instance;
    }

    public HitboxesModule() {
        block4: {
            String[] stringArray = b;
            super(stringArray[18], stringArray[0], Category.RENDER, false);
            this.hideModel = new BooleanSetting(stringArray[8], false);
            this.heldItemOnBox = new BooleanSetting(stringArray[7], true);
            this.players = new HitboxesModule$Group(stringArray[11], true, this.hideModel);
            this.crystals = new HitboxesModule$Group(stringArray[12], true, new Setting[0]);
            this.pearls = new HitboxesModule$Group(stringArray[13], true, new Setting[0]);
            this.arrows = new HitboxesModule$Group(stringArray[9], false, new Setting[0]);
            this.entities = new HitboxesModule$Group(stringArray[1], false, new Setting[0]);
            this.misc = new HitboxesModule$Group(stringArray[6], false, new Setting[0]);
            this.teams = new BooleanSetting(stringArray[10], false);
            this.teammates = new StringSetting(stringArray[15], "", 4096);
            this.editTeam = new ActionSetting(stringArray[17], stringArray[2], this::lambda$new$0);
            this.teamFill = new ColorSetting(stringArray[19], HitboxesModule.a(32361, 2730683111109092579L), true);
            this.teamLine = new ColorSetting(stringArray[3], HitboxesModule.a(20431, 886780500917769542L), false);
            this.showInvisible = new BooleanSetting(stringArray[4], false);
            this.target = new BooleanSetting(stringArray[14], false);
            this.targetLine = new ColorSetting(stringArray[5], HitboxesModule.a(3008, 4844683145793671499L), false);
            this.targetFill = new ColorSetting(stringArray[16], HitboxesModule.a(11003, 1566677194445303923L), true);
            boolean bl = AmbienceModule.b;
            this.team = new String[0];
            instance = this;
            for (HitboxesModule$Group hitboxesModule$Group : new HitboxesModule$Group[]{this.players, this.crystals, this.pearls, this.arrows, this.entities, this.misc}) {
                if (!bl) {
                    this.addSettings(hitboxesModule$Group.all());
                    if (!bl) continue;
                    break;
                }
                break block4;
            }
            this.addSettings(this.hideModel, this.heldItemOnBox, this.teams, this.teammates, this.editTeam, this.teamFill, this.teamLine, this.showInvisible, this.target, this.targetLine, this.targetFill);
            this.target.children(this.targetLine, this.targetFill);
            this.hideModel.children(this.heldItemOnBox, this.showInvisible);
            this.showInvisible.visibleWhen(this.hideModel::getValue);
            this.teammates.visibleWhen(HitboxesModule::lambda$new$1);
            this.teams.children(this.teamFill, this.teamLine);
            this.onSettingsChanged();
        }
    }

    @Override
    public void onSettingsChanged() {
        block19: {
            String string;
            boolean bl;
            block17: {
                String string2;
                block18: {
                    block15: {
                        block16: {
                            bl = AmbienceModule.b;
                            string2 = this.teammates.getValue();
                            if (bl) break block15;
                            if (string2 == null) break block16;
                            string = string2;
                            if (bl) break block17;
                            if (!string.isBlank()) break block18;
                        }
                        this.team = new String[0];
                    }
                    return;
                }
                string = string2;
            }
            String[] stringArray = string.split(",");
            String[] stringArray2 = new String[stringArray.length];
            int n = 0;
            String[] stringArray3 = stringArray;
            int n2 = stringArray3.length;
            int n3 = 0;
            while (n3 < n2) {
                block20: {
                    block21: {
                        String string3 = stringArray3[n3];
                        String string4 = string3.trim().toLowerCase(Locale.ROOT);
                        if (bl) break block19;
                        if (bl) break block20;
                        if (string4.isEmpty()) break block21;
                        stringArray2[n++] = string4;
                    }
                    ++n3;
                }
                if (!bl) continue;
            }
            this.team = new String[n];
            System.arraycopy(stringArray2, 0, this.team, 0, n);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static HitboxesModule$Kind kindOf(Entity entity) {
        Entity entity2;
        block29: {
            block30: {
                boolean bl;
                block27: {
                    block28: {
                        block26: {
                            block24: {
                                block25: {
                                    block22: {
                                        block23: {
                                            bl = AmbienceModule.b;
                                            entity2 = entity;
                                            if (bl) break block22;
                                            if (!(entity2 instanceof PlayerEntity)) break block23;
                                            return HitboxesModule$Kind.PLAYERS;
                                        }
                                        entity2 = entity;
                                    }
                                    if (bl) break block24;
                                    if (!(entity2 instanceof EndCrystalEntity)) break block25;
                                    return HitboxesModule$Kind.CRYSTALS;
                                }
                                entity2 = entity;
                            }
                            if (bl) break block26;
                            if (entity2 instanceof EnderPearlEntity) return HitboxesModule$Kind.PEARLS;
                            entity2 = entity;
                        }
                        if (bl) break block27;
                        if (!(entity2 instanceof AbstractWindChargeEntity)) break block28;
                        return HitboxesModule$Kind.PEARLS;
                    }
                    entity2 = entity;
                }
                if (bl) break block29;
                if (!(entity2 instanceof PersistentProjectileEntity)) break block30;
                return HitboxesModule$Kind.ARROWS;
            }
            entity2 = entity;
        }
        if (!(entity2 instanceof LivingEntity)) return HitboxesModule$Kind.MISC;
        return HitboxesModule$Kind.ENTITIES;
    }

    
    private HitboxesModule$Group group(HitboxesModule$Kind hitboxesModule$Kind) {
        HitboxesModule$Group hitboxesModule$Group;
        switch (hitboxesModule$Kind.ordinal()) {
            default: {
                throw new MatchException(null, null);
            }
            case 0: {
                hitboxesModule$Group = this.players;
                return hitboxesModule$Group;
            }
            case 1: {
                hitboxesModule$Group = this.crystals;
                return hitboxesModule$Group;
            }
            case 2: {
                hitboxesModule$Group = this.pearls;
                return hitboxesModule$Group;
            }
            case 3: {
                hitboxesModule$Group = this.arrows;
                return hitboxesModule$Group;
            }
            case 4: {
                hitboxesModule$Group = this.entities;
                return hitboxesModule$Group;
            }
            case 5: 
        }
        hitboxesModule$Group = this.misc;
        return hitboxesModule$Group;
    }

    public boolean draws(HitboxesModule$Kind hitboxesModule$Kind) {
        boolean bl;
        block8: {
            block7: {
                HitboxesModule hitboxesModule;
                boolean bl2;
                block6: {
                    bl2 = AmbienceModule.b;
                    hitboxesModule = this;
                    if (bl2) break block6;
                    if (!hitboxesModule.isEnabled()) break block7;
                    hitboxesModule = this;
                }
                bl = hitboxesModule.group((HitboxesModule$Kind)hitboxesModule$Kind).on.getValue();
                if (bl2) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    public boolean fills(HitboxesModule$Kind hitboxesModule$Kind) {
        return this.group((HitboxesModule$Kind)hitboxesModule$Kind).fill.getValue();
    }

    public boolean outlines(HitboxesModule$Kind hitboxesModule$Kind) {
        return this.group((HitboxesModule$Kind)hitboxesModule$Kind).lines.getValue();
    }

    public float lineWidth(HitboxesModule$Kind hitboxesModule$Kind) {
        return this.group((HitboxesModule$Kind)hitboxesModule$Kind).width.getValueFloat();
    }

    public double scale(HitboxesModule$Kind hitboxesModule$Kind) {
        return this.group((HitboxesModule$Kind)hitboxesModule$Kind).scale.getValue();
    }

    public int fillArgb(HitboxesModule$Kind hitboxesModule$Kind, Entity entity) {
        HitboxesModule hitboxesModule;
        block10: {
            block11: {
                boolean bl;
                block8: {
                    block9: {
                        bl = AmbienceModule.b;
                        hitboxesModule = this;
                        if (bl) break block8;
                        if (!hitboxesModule.isTeammate(entity)) break block9;
                        return this.teamFill.getARGB();
                    }
                    hitboxesModule = this;
                }
                if (bl) break block10;
                if (!hitboxesModule.isTarget(entity)) break block11;
                return this.targetFill.getARGB();
            }
            hitboxesModule = this;
        }
        return hitboxesModule.group((HitboxesModule$Kind)hitboxesModule$Kind).fillColor.getARGB();
    }

    public int lineArgb(HitboxesModule$Kind hitboxesModule$Kind, Entity entity) {
        HitboxesModule hitboxesModule;
        block11: {
            Entity entity2;
            block9: {
                boolean bl;
                block10: {
                    bl = AmbienceModule.b;
                    hitboxesModule = this;
                    entity2 = entity;
                    if (bl) break block9;
                    if (!hitboxesModule.isTeammate(entity2)) break block10;
                    return this.teamLine.getARGB();
                }
                hitboxesModule = this;
                if (bl) break block11;
                entity2 = entity;
            }
            if (hitboxesModule.isTarget(entity2)) {
                return this.targetLine.getARGB();
            }
            hitboxesModule = this;
        }
        return hitboxesModule.group((HitboxesModule$Kind)hitboxesModule$Kind).lineColor.getARGB();
    }

    private boolean isTarget(Entity entity) {
        boolean bl;
        block8: {
            block6: {
                Entity entity2;
                block7: {
                    boolean bl2 = AmbienceModule.b;
                    if (!this.target.getValue()) break block6;
                    entity2 = entity;
                    if (bl2) break block7;
                    if (entity2 == null) break block6;
                    entity2 = entity;
                }
                if (entity2 != this.mc.field_1692) break block6;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean showsInvisible(Entity entity) {
        boolean bl;
        boolean bl2;
        block18: {
            block19: {
                bl2 = AmbienceModule.b;
                boolean bl3 = this.showInvisible.getValue();
                if (bl2) return bl3;
                if (!bl3) return false;
                bl = this.hidesModel();
                if (bl2) break block18;
                if (bl) break block19;
                return false;
            }
            bl = entity instanceof LivingEntity;
        }
        if (bl2) return bl;
        if (!bl) return false;
        LivingEntity livingEntity = (LivingEntity)entity;
        if (bl2) {
            return false;
        }
        EquipmentSlot[] equipmentSlotArray = new EquipmentSlot[]{EquipmentSlot.field_6169, EquipmentSlot.field_6174, EquipmentSlot.field_6172, EquipmentSlot.field_6166};
        int n = equipmentSlotArray.length;
        int n2 = 0;
        do {
            block20: {
                if (n2 >= n) return false;
                EquipmentSlot equipmentSlot = equipmentSlotArray[n2];
                if (bl2) continue;
                boolean bl4 = livingEntity.method_6118(equipmentSlot).method_7960();
                if (bl2) return bl4;
                if (bl4) break block20;
                return true;
            }
            ++n2;
        } while (!bl2);
        return false;
    }

    public boolean hidesModel() {
        boolean bl;
        block21: {
            block20: {
                MinecraftClient minecraftClient;
                boolean bl2;
                block19: {
                    block18: {
                        boolean bl3;
                        block16: {
                            block17: {
                                bl2 = AmbienceModule.b;
                                bl3 = this.isEnabled();
                                if (bl2) break block16;
                                if (!bl3) break block17;
                                bl3 = this.players.on.getValue();
                                if (bl2) break block16;
                                if (!bl3) break block17;
                                bl3 = this.hideModel.getValue();
                                if (bl2) break block16;
                                if (bl3) break block18;
                            }
                            bl3 = false;
                        }
                        return bl3;
                    }
                    MinecraftClient minecraftClient2 = MinecraftClient.method_1551();
                    minecraftClient = minecraftClient2;
                    if (bl2) break block19;
                    if (minecraftClient.field_61504 == null) break block20;
                    minecraftClient = minecraftClient2;
                }
                bl = minecraftClient.field_61504.method_72772(DebugHudEntries.field_61581);
                if (bl2) break block21;
                if (!bl) break block20;
                bl = true;
                break block21;
            }
            bl = false;
        }
        return bl;
    }

    public boolean hidesArmor() {
        return this.hidesModel();
    }

    public boolean hidesHeldItem() {
        boolean bl;
        block8: {
            block7: {
                HitboxesModule hitboxesModule;
                boolean bl2;
                block6: {
                    bl2 = AmbienceModule.b;
                    hitboxesModule = this;
                    if (bl2) break block6;
                    if (!hitboxesModule.hidesModel()) break block7;
                    hitboxesModule = this;
                }
                bl = hitboxesModule.heldItemOnBox.getValue();
                if (bl2) break block8;
                if (bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean isTeammate(Entity entity) {
        boolean bl;
        boolean bl2;
        block18: {
            block19: {
                bl2 = AmbienceModule.b;
                boolean bl3 = this.teams.getValue();
                if (bl2) return bl3;
                if (!bl3) return false;
                bl = this.team.length;
                if (bl2) break block18;
                if (bl) break block19;
                return false;
            }
            bl = entity instanceof PlayerEntity;
        }
        if (bl2) return bl;
        if (!bl) return false;
        PlayerEntity playerEntity = (PlayerEntity)entity;
        if (bl2) {
            return false;
        }
        String string = playerEntity.method_7334().name().toLowerCase(Locale.ROOT);
        String[] stringArray = this.team;
        int n = stringArray.length;
        int n2 = 0;
        do {
            block20: {
                if (n2 >= n) return false;
                String string2 = stringArray[n2];
                if (bl2) continue;
                boolean bl4 = string2.equals(string);
                if (bl2) return bl4;
                if (!bl4) break block20;
                return true;
            }
            ++n2;
        } while (!bl2);
        return false;
    }

    private static Boolean lambda$new$1() {
        return false;
    }

    private void lambda$new$0() {
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        if (Im.available()) {
            minecraftClient.method_1507((Screen)new TeamEditor(this.teammates));
        }
    }

    
    static {
        block28: {
            block27: {
                block26: {
                    block25: {
                        var13 = new String[20];
                        var11_1 = 0;
                        var10_2 = "{ 8F rHWo0O2+BK<l\u00030 ^J;!O rFW+`S!=M\\,4J?7T\b|!4J';BJ\u0004|+)W\fm*!Ns=RM#)M6\u000ej'/Ts;IO&3J1>B\u000em.2D6&\u0007V:4O:<B\u0004t&3@\u0010q*,Gs;S\\\"`L=rEV7\u0011q&$Fs\"KX6%Qs?H]*,\u0006x=2L$!\u0005m*!N \u0007i#!Z6 T\f|!$\u00030 ^J;!O \u0006i*!Q?!\u000fm.2D6&\u0007P!`Q63DQ\tm*!N>3S\\<\u000bm.2D6&\u0007_&,O\tm*!N>3S\\<";
                        var12_3 = "{ 8F rHWo0O2+BK<l\u00030 ^J;!O rFW+`S!=M\\,4J?7T\b|!4J';BJ\u0004|+)W\fm*!Ns=RM#)M6\u000ej'/Ts;IO&3J1>B\u000em.2D6&\u0007V:4O:<B\u0004t&3@\u0010q*,Gs;S\\\"`L=rEV7\u0011q&$Fs\"KX6%Qs?H]*,\u0006x=2L$!\u0005m*!N \u0007i#!Z6 T\f|!$\u00030 ^J;!O \u0006i*!Q?!\u000fm.2D6&\u0007P!`Q63DQ\tm*!N>3S\\<\u000bm.2D6&\u0007_&,O\tm*!N>3S\\<".length();
                        var9_4 = 42;
                        var8_5 = -1;
lbl7:

                        while (true) {
                            v0 = ++var8_5;
                            v1 = var10_2.substring(v0, v0 + var9_4);
                            v2 = -1;
                            break block25;
                            break;
                        }
lbl12:

                        while (true) {
                            var13[var11_1++] = v3.intern();
                            if ((var8_5 += var9_4) < var12_3) {
                                var9_4 = var10_2.charAt(var8_5);
                                continue;
                            }
                            var10_2 = "q&4A<*BJ\tm*!Ns4NU#";
                            var12_3 = "q&4A<*BJ\tm*!Ns4NU#".length();
                            var9_4 = 8;
                            var8_5 = -1;
lbl21:

                            while (true) {
                                v4 = ++var8_5;
                                v1 = var10_2.substring(v4, v4 + var9_4);
                                v2 = 0;
                                break block25;
                                break;
                            }
                            break;
                        }
lbl26:

                        while (true) {
                            var13[var11_1++] = v3.intern();
                            if ((var8_5 += var9_4) < var12_3) {
                                var9_4 = var10_2.charAt(var8_5);
                                continue;
                            }
                            break block26;
                            break;
                        }
                    }
                    v5 = v1.toCharArray();
                    v6 = v5;
                    v7 = v5.length;
                    var14_6 = 0;
                    do {
                        v6 = v6;
                        v8 = var14_6;
                        v9 = v6[v8];
                        switch (var14_6 % 7) {
                            case 0: {
                                v10 = 57;
                                break;
                            }
                            case 1: {
                                v10 = 79;
                                break;
                            }
                            case 2: {
                                v10 = 64;
                                break;
                            }
                            case 3: {
                                v10 = 35;
                                break;
                            }
                            case 4: {
                                v10 = 83;
                                break;
                            }
                            case 5: {
                                v10 = 82;
                                break;
                            }
                            default: {
                                v10 = 39;
                            }
                        }
                        v6[v8] = (char)(v9 ^ v10);
                        ++var14_6;
lbl65:

                        v7 = v7;
                    } while (v7 > var14_6);
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
                HitboxesModule.b = var13;
                var0_7 = 6186105749245916388L;
                var6_8 = new long[4];
                var3_9 = 0;
                var4_10 = "\u0084\u0080F\u0098\u0093h5\u00ff\u00f6\u0000QH){\u00f3\u008c";
                var5_11 = "\u0084\u0080F\u0098\u0093h5\u00ff\u00f6\u0000QH){\u00f3\u008c".length();
                var2_12 = 0;
                while (true) {
                    var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
                    v11 = var6_8;
                    v12 = var3_9++;
                    v13 = ((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L;
                    v14 = -1;
                    break block27;
                    break;
                }
lbl89:

                while (true) {
                    v11[v12] = v15;
                    if (var2_12 < var5_11) continue;
                    var4_10 = "p\u00f6\u0080\u009d\u007fYB)\u00e7E\u00e4\u00b7\u00e2\u00ac\u0094\u00c7";
                    var5_11 = "p\u00f6\u0080\u009d\u007fYB)\u00e7E\u00e4\u00b7\u00e2\u00ac\u0094\u00c7".length();
                    var2_12 = 0;
                    while (true) {
                        var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
                        v11 = var6_8;
                        v12 = var3_9++;
                        v13 = ((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L;
                        v14 = 0;
                        break block27;
                        break;
                    }
                    break;
                }
lbl102:

                while (true) {
                    v11[v12] = v15;
                    if (var2_12 < var5_11) continue;
                    break block28;
                    break;
                }
            }
            v15 = v13 ^ var0_7;
            switch (v14) {
                default: {
                    continue;
                }
                case 0:
lbl113:

                continue;
            }
        }
        HitboxesModule.d = var6_8;
        HitboxesModule.e = new Integer[4];
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1A8B;
        if (e[n2] == null) {
            HitboxesModule.e[n2] = (int)(d[n2] ^ l);
        }
        return e[n2];
    }
}
