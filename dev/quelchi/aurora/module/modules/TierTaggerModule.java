package dev.quelchi.aurora.module.modules;

import com.google.gson.JsonObject;
import dev.quelchi.aurora.config.ConfigManager;
import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.NametagsModule;
import dev.quelchi.aurora.module.modules.NametagsModule$TagSide;
import dev.quelchi.aurora.module.modules.TierTaggerModule$TierList;
import dev.quelchi.aurora.module.setting.ActionSetting;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.EnumSetting;
import dev.quelchi.aurora.player.Tiers;
import dev.quelchi.aurora.player.Tiers$Mode;
import dev.quelchi.aurora.player.Tiers$Source;
import java.util.UUID;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;

public final class TierTaggerModule
extends Module {
    private static TierTaggerModule instance;
    private final EnumSetting<TierTaggerModule$TierList> source;
    private String modeSlug;
    private final ActionSetting gamemode;
    private final EnumSetting<NametagsModule$TagSide> side;
    private final BooleanSetting icon;
    private final BooleanSetting showOwn;
    private final BooleanSetting chat;
    private static final String[] b;
    private static final long d;

    public static TierTaggerModule get() {
        return instance;
    }

    public TierTaggerModule() {
        String[] stringArray = b;
        super(stringArray[7], stringArray[2], Category.RENDER, false);
        this.source = new EnumSetting<TierTaggerModule$TierList>(stringArray[5], TierTaggerModule$TierList.MCTiers);
        this.modeSlug = stringArray[0];
        this.gamemode = new ActionSetting(stringArray[1], this::lambda$new$0, this::lambda$new$1);
        this.side = new EnumSetting<NametagsModule$TagSide>(stringArray[8], NametagsModule$TagSide.Left);
        this.icon = new BooleanSetting(stringArray[10], true);
        this.showOwn = new BooleanSetting(stringArray[3], true);
        this.chat = new BooleanSetting(stringArray[9], false);
        instance = this;
        this.addSettings(this.source, this.gamemode, this.side, this.icon, this.showOwn, this.chat);
    }

    public String modeSlug() {
        if (!TierTaggerModule.validFor(Tiers.source(), this.modeSlug)) {
            this.modeSlug = Tiers.modes()[0].slug();
        }
        return this.modeSlug;
    }

    private static boolean validFor(Tiers$Source source, String string) {
        boolean bl;
        block8: {
            Tiers$Mode[] tiers$ModeArray = Tiers.modesFor(source);
            int n = tiers$ModeArray.length;
            int n2 = 0;
            boolean bl2 = AmbienceModule.b;
            while (n2 < n) {
                block7: {
                    block9: {
                        Tiers$Mode tiers$Mode = tiers$ModeArray[n2];
                        if (bl2) break block7;
                        bl = tiers$Mode.slug().equalsIgnoreCase(string);
                        if (bl2) break block8;
                        if (!bl) break block9;
                        return true;
                    }
                    ++n2;
                }
                if (!bl2) continue;
            }
            bl = false;
        }
        return bl;
    }

    private static String labelOf(String string) {
        String string2;
        block8: {
            Tiers$Mode[] tiers$ModeArray = Tiers.modes();
            int n = tiers$ModeArray.length;
            int n2 = 0;
            boolean bl = AmbienceModule.b;
            while (n2 < n) {
                block7: {
                    block9: {
                        Tiers$Mode tiers$Mode = tiers$ModeArray[n2];
                        if (bl) break block7;
                        string2 = tiers$Mode.slug();
                        if (bl) break block8;
                        if (!string2.equalsIgnoreCase(string)) break block9;
                        return tiers$Mode.label();
                    }
                    ++n2;
                }
                if (!bl) continue;
            }
            string2 = string;
        }
        return string2;
    }

    private void cycleMode(int n) {
        block7: {
            Tiers$Mode[] tiers$ModeArray = Tiers.modes();
            int n2 = 0;
            boolean bl = AmbienceModule.b;
            for (int i = 0; i < tiers$ModeArray.length; ++i) {
                int n3;
                block8: {
                    if (bl) break block7;
                    n3 = tiers$ModeArray[i].slug().equalsIgnoreCase(this.modeSlug);
                    if (bl) break block8;
                    if (n3 == 0) continue;
                    n3 = i;
                }
                n2 = n3;
                if (!bl) break;
                if (!bl) continue;
                break;
            }
            this.modeSlug = tiers$ModeArray[Math.floorMod(n2 + n, tiers$ModeArray.length)].slug();
            ConfigManager.save();
        }
    }

    @Override
    public void writeExtra(JsonObject jsonObject) {
        jsonObject.addProperty(b[11], this.modeSlug);
    }

    @Override
    public void readExtra(JsonObject jsonObject) {
        if (jsonObject.has(b[4])) {
            this.modeSlug = jsonObject.get(b[11]).getAsString();
        }
    }

    
    @Override
    public void onSettingsChanged() {
        Tiers.setSource(switch (this.source.getValue().ordinal()) {
            case 1 -> Tiers$Source.PvPHQ;
            case 2 -> Tiers$Source.PvPTiers;
            case 3 -> Tiers$Source.Subtiers;
            default -> Tiers$Source.MCTiers;
        });
    }

    public boolean onRight() {
        boolean bl = this.side.getValue() == NametagsModule$TagSide.Right;
        return bl;
    }

    public Text segment(UUID uUID, String string) {
        TierTaggerModule tierTaggerModule;
        block12: {
            block13: {
                boolean bl;
                block10: {
                    block11: {
                        bl = AmbienceModule.b;
                        tierTaggerModule = this;
                        if (bl) break block10;
                        if (tierTaggerModule.isEnabled()) break block11;
                        return null;
                    }
                    tierTaggerModule = this;
                }
                if (bl) break block12;
                if (tierTaggerModule.showOwn.getValue()) break block13;
                if (!TierTaggerModule.isSelf(uUID)) break block13;
                return null;
            }
            tierTaggerModule = this;
        }
        return tierTaggerModule.build(uUID, string);
    }

    private static boolean isSelf(UUID uUID) {
        boolean bl;
        block6: {
            block5: {
                ClientPlayerEntity clientPlayerEntity;
                boolean bl2;
                block4: {
                    ClientPlayerEntity clientPlayerEntity2 = MinecraftClient.method_1551().field_1724;
                    bl2 = AmbienceModule.b;
                    clientPlayerEntity = clientPlayerEntity2;
                    if (bl2) break block4;
                    if (clientPlayerEntity == null) break block5;
                    clientPlayerEntity = clientPlayerEntity2;
                }
                bl = clientPlayerEntity.method_5667().equals(uUID);
                if (bl2) break block6;
                if (!bl) break block5;
                bl = true;
                break block6;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean showsInChat() {
        boolean bl;
        block11: {
            block9: {
                TierTaggerModule tierTaggerModule;
                boolean bl2;
                block10: {
                    TierTaggerModule tierTaggerModule2;
                    block8: {
                        tierTaggerModule2 = instance;
                        bl2 = AmbienceModule.b;
                        tierTaggerModule = tierTaggerModule2;
                        if (bl2) break block8;
                        if (tierTaggerModule == null) break block9;
                        tierTaggerModule = tierTaggerModule2;
                    }
                    if (bl2) break block10;
                    if (!tierTaggerModule.isEnabled()) break block9;
                    tierTaggerModule = tierTaggerModule2;
                }
                bl = tierTaggerModule.chat.getValue();
                if (bl2) break block11;
                if (!bl) break block9;
                bl = true;
                break block11;
            }
            bl = false;
        }
        return bl;
    }

    public Text segmentFor(UUID uUID, String string) {
        return this.build(uUID, string);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private Text build(UUID uUID, String string) {
        TierTaggerModule tierTaggerModule;
        MutableText mutableText;
        int n;
        String string2;
        block23: {
            String string3;
            boolean bl;
            block21: {
                block22: {
                    block20: {
                        block19: {
                            string2 = Tiers.tierFor(uUID, this.modeSlug());
                            bl = AmbienceModule.b;
                            if (Tiers.failedFor(uUID)) {
                                return Text.method_43470((String)b[6]).method_27692(Formatting.field_1063);
                            }
                            string3 = string2;
                            if (bl) break block19;
                            if (string3 == null) return null;
                            string3 = string2;
                        }
                        if (bl) break block20;
                        if (string3.isEmpty()) return null;
                        string3 = string2;
                    }
                    if (bl) break block21;
                    if (!string3.equals("\u2026")) break block22;
                    return null;
                }
                string3 = string2;
            }
            n = NametagsModule.tierColorOf(string3);
            mutableText = Text.method_43473();
            tierTaggerModule = this;
            if (bl) break block23;
            if (!tierTaggerModule.icon.getValue()) return mutableText.method_10852((Text)Text.method_43470((String)string2).method_27694(arg_0 -> TierTaggerModule.lambda$build$3(n, arg_0)));
            tierTaggerModule = this;
        }
        String string4 = Tiers.glyphFor(tierTaggerModule.modeSlug());
        if (string4 == null) return mutableText.method_10852((Text)Text.method_43470((String)string2).method_27694(arg_0 -> TierTaggerModule.lambda$build$3(n, arg_0)));
        mutableText.method_10852((Text)Text.method_43470((String)string4).method_27694(TierTaggerModule::lambda$build$2));
        return mutableText.method_10852((Text)Text.method_43470((String)string2).method_27694(arg_0 -> TierTaggerModule.lambda$build$3(n, arg_0)));
    }

    private static Style lambda$build$3(int n, Style style) {
        return style.method_27703(TextColor.method_27717((int)n));
    }

    private static Style lambda$build$2(Style style) {
        return style.method_27704(Tiers.ICON_FONT).method_27703(TextColor.method_27717((int)((int)d)));
    }

    private void lambda$new$1() {
        this.cycleMode(1);
    }

    private String lambda$new$0() {
        return TierTaggerModule.labelOf(this.modeSlug);
    }

    
    static {
        block21: {
            block20: {
                block19: {
                    var7 = new String[12];
                    var5_1 = 0;
                    var4_2 = "T\r[v\u0019bv\be\rXz\u0018asG\u0015r\u001ae?\u0001grP\u001f\u0015p\u001b.yC\u0001Pk\u0014id\bq\u0004ZhUa`L\u0004O\u0003Qz\tv\u0005PmUb~Q\u0018\u0004\u00027\nB\nv\u0005Pm!opE\tG\tv\u0005PmU]~F\t\fq\u0004ZhUgy\u0002\u000f]~\u0001";
                    var6_3 = "T\r[v\u0019bv\be\rXz\u0018asG\u0015r\u001ae?\u0001grP\u001f\u0015p\u001b.yC\u0001Pk\u0014id\bq\u0004ZhUa`L\u0004O\u0003Qz\tv\u0005PmUb~Q\u0018\u0004\u00027\nB\nv\u0005Pm!opE\tG\tv\u0005PmU]~F\t\fq\u0004ZhUgy\u0002\u000f]~\u0001".length();
                    var3_4 = 7;
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
                        var4_2 = "e\rXz\u0018asGL\\|\u001a`\u0004O\u0003Qz";
                        var6_3 = "e\rXz\u0018asGL\\|\u001a`\u0004O\u0003Qz".length();
                        var3_4 = 13;
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
                            v10 = 34;
                            break;
                        }
                        case 1: {
                            v10 = 108;
                            break;
                        }
                        case 2: {
                            v10 = 53;
                            break;
                        }
                        case 3: {
                            v10 = 31;
                            break;
                        }
                        case 4: {
                            v10 = 117;
                            break;
                        }
                        case 5: {
                            v10 = 14;
                            break;
                        }
                        default: {
                            v10 = 23;
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
            TierTaggerModule.b = var7;
            break block21;
lbl77:

            while (true) {
                continue;
                break;
            }
        }
        var0_7 = 4309468118907139384L;
        while (true)
        TierTaggerModule.d = -5553381796211897657L ^ var0_7;
    }
}
