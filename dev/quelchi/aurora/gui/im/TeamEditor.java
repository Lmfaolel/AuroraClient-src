package dev.quelchi.aurora.gui.im;

import com.mojang.authlib.GameProfile;
import dev.quelchi.aurora.config.ConfigManager;
import dev.quelchi.aurora.gui.im.Edit;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.ImScreen;
import dev.quelchi.aurora.gui.im.Menu;
import dev.quelchi.aurora.gui.im.Ui;
import dev.quelchi.aurora.module.modules.HitboxesModule;
import dev.quelchi.aurora.module.setting.StringSetting;
import dev.quelchi.aurora.util.SkinFetcher;
import imgui.ImGui;
import imgui.type.ImString;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.MinecraftClient;

public final class TeamEditor
extends ImScreen {
    private static final UUID ASKED;
    private static final Map<String, UUID> IDS;
    private final StringSetting backing;
    private final List<String> names = new ArrayList<String>();
    private final ImString name = new ImString(17);
    private boolean focus = true;
    private static final String[] b;

    public TeamEditor(StringSetting stringSetting) {
        super(b[5]);
        boolean bl = ImScreen.a;
        this.backing = stringSetting;
        String[] stringArray = stringSetting.getValue().split(",");
        int n = stringArray.length;
        int n2 = 0;
        while (n2 < n) {
            block5: {
                block6: {
                    String string = stringArray[n2];
                    if (bl) break block5;
                    if (string.isBlank()) break block6;
                    this.names.add(string.trim());
                }
                ++n2;
            }
            if (!bl) continue;
        }
    }

    @Override
    protected boolean key(int n) {
        boolean bl;
        block8: {
            block9: {
                int n2;
                block6: {
                    block7: {
                        boolean bl2 = ImScreen.a;
                        n2 = n;
                        if (bl2) break block6;
                        if (n2 != 256) break block7;
                        bl = Im.typing();
                        if (bl2) break block8;
                        if (!bl) break block9;
                    }
                    n2 = 0;
                }
                return n2 != 0;
            }
            Edit.back();
            bl = true;
        }
        return bl;
    }

    private static UUID uuid(String string) {
        Object object;
        Object object22;
        block22: {
            block23: {
                String string2;
                boolean bl;
                block20: {
                    block18: {
                        MinecraftClient minecraftClient;
                        block17: {
                            MinecraftClient minecraftClient2 = MinecraftClient.method_1551();
                            bl = ImScreen.a;
                            minecraftClient = minecraftClient2;
                            if (bl) break block17;
                            if (minecraftClient.method_1562() == null) break block18;
                            minecraftClient = minecraftClient2;
                        }
                        for (Object object22 : minecraftClient.method_1562().method_2880()) {
                            block21: {
                                Object object3;
                                block19: {
                                    object3 = object22;
                                    if (bl) break block19;
                                    string2 = object3.method_2966().name();
                                    if (bl) break block20;
                                    if (!string2.equalsIgnoreCase(string)) break block21;
                                    object3 = object22;
                                }
                                return object3.method_2966().id();
                            }
                            if (!bl) continue;
                        }
                    }
                    string2 = string.toLowerCase(Locale.ROOT);
                }
                String string3 = string2;
                object22 = IDS.putIfAbsent(string3, ASKED);
                object = object22;
                if (bl) break block22;
                if (object != null) break block23;
                SkinFetcher.fetchProfile(string).thenAccept(arg_0 -> TeamEditor.lambda$uuid$1(string3, arg_0));
            }
            object = object22;
        }
        Object object4 = object == ASKED ? null : object22;
        return object4;
    }

    @Override
    protected void draw() {
        block23: {
            block24: {
                float f;
                float f2;
                int n;
                boolean bl;
                block20: {
                    float f3;
                    float f4;
                    block18: {
                        float f5;
                        float f6;
                        String[] stringArray;
                        block19: {
                            stringArray = b;
                            Menu.begin(stringArray[1], 0.0f);
                            Edit.bar(stringArray[4], stringArray[8]);
                            f4 = Menu.x + 144.0f + 20.0f;
                            float f7 = Menu.y + 56.0f + 20.0f;
                            f3 = Math.min(480.0f, Menu.w - 144.0f - 40.0f);
                            f6 = Menu.y + Menu.h - 1.0f;
                            float f8 = Ui.width(Im.sora12r, stringArray[10]) + 32.0f;
                            Edit.field(stringArray[11], stringArray[0], this.name, f4, f7, f3 - f8 - 8.0f, this.focus);
                            this.focus = false;
                            boolean bl2 = Ui.entered();
                            bl = ImScreen.a;
                            bl2 |= Menu.button(stringArray[12], f4 + f3 - f8, f7, f8, 34.0f, true);
                            if (bl2) {
                                this.add();
                            }
                            f5 = f7 + 48.0f;
                            if (bl) break block18;
                            if (!this.names.isEmpty()) break block19;
                            Ui.text(Im.sub, f4 + 2.0f, f5 + 6.0f, Im.MUTE, b[3]);
                            Menu.end();
                            return;
                        }
                        stringArray = b;
                        Edit.list(stringArray[6], f4, f5, f3, f6 - f5);
                    }
                    float f9 = ImGui.getCursorScreenPosY();
                    n = -1;
                    int n2 = 0;
                    while (n2 < this.names.size()) {
                        String string;
                        block21: {
                            block22: {
                                ImGui.pushID((int)n2);
                                Edit.row(b[9], f4, f9, f3, 46.0f, false);
                                Ui.head(TeamEditor.uuid(this.names.get(n2)), f4 + 12.0f, f9 + 9.0f, 28.0f);
                                Ui.text(Im.sora13, f4 + 52.0f, Ui.mid(Im.sora13, f9, 46.0f), Im.INK, this.names.get(n2));
                                f2 = f4 + f3;
                                f = 36.0f;
                                if (bl) break block20;
                                ImGui.setCursorScreenPos((float)(f2 - f), (float)(f9 + 11.0f));
                                string = b[2];
                                if (bl) break block21;
                                if (!ImGui.invisibleButton((String)string, (float)24.0f, (float)24.0f)) break block22;
                                n = n2;
                            }
                            string = b[13];
                        }
                        float f10 = f4 + f3 - 30.5f;
                        float f11 = f9 + 16.5f;
                        float f12 = 13.0f;
                        float[] fArray = ImGui.isItemHovered() ? Im.DANGER : Im.MUTE;
                        Ui.icon(string, f10, f11, f12, fArray);
                        ImGui.popID();
                        ++n2;
                        f9 += 50.0f;
                        if (!bl) continue;
                    }
                    f2 = f4;
                    f = f9;
                }
                Edit.endList(f2, f);
                if (bl) break block23;
                if (n < 0) break block24;
                this.names.remove(n);
                this.save();
            }
            Menu.end();
        }
    }

    private void add() {
        block11: {
            TeamEditor teamEditor;
            boolean bl;
            String string;
            block9: {
                block10: {
                    string = this.name.get().replaceAll(b[7], "");
                    bl = ImScreen.a;
                    teamEditor = this;
                    if (bl) break block9;
                    teamEditor.name.set("");
                    this.focus = true;
                    if (!string.isEmpty()) break block10;
                    return;
                }
                teamEditor = this;
            }
            for (String string2 : teamEditor.names) {
                block12: {
                    if (bl) break block11;
                    if (!string2.equalsIgnoreCase(string)) break block12;
                    return;
                }
                if (!bl) continue;
            }
            this.names.add(string);
            this.save();
        }
    }

    private void save() {
        block4: {
            block5: {
                this.backing.setValue(String.join((CharSequence)",", this.names));
                boolean bl = ImScreen.a;
                HitboxesModule hitboxesModule = HitboxesModule.get();
                if (bl) break block4;
                if (hitboxesModule == null) break block5;
                hitboxesModule.onSettingsChanged();
            }
            ConfigManager.save();
        }
    }

    private static void lambda$uuid$1(String string, Optional optional) {
        optional.ifPresent(arg_0 -> TeamEditor.lambda$uuid$0(string, arg_0));
    }

    private static void lambda$uuid$0(String string, GameProfile gameProfile) {
        IDS.put(string, gameProfile.id());
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[14];
                var3_1 = 0;
                var2_2 = "FHuw\u0004(\u000f|Phw\u0007_^agH,\\\u0006`Th}R,>\\^g}@0\u000fkTq<\u0004\u0007N\u007fTv2E;J2\\dfG!Jv\u0011rzK%J>\u0011d|@iLsB`2@&Ja\u0011k}PiBsEqwVg\bZXqpK1Ja\tFTd\u007fI([wB\u0004fTd\u007f\rIoD?~(\u0002h\u0001(+{\u0014\tFTd\u007fI([wB\u0003`^r\u0003SUa\u00061\u0012ksI,";
                var4_3 = "FHuw\u0004(\u000f|Phw\u0007_^agH,\\\u0006`Th}R,>\\^g}@0\u000fkTq<\u0004\u0007N\u007fTv2E;J2\\dfG!Jv\u0011rzK%J>\u0011d|@iLsB`2@&Ja\u0011k}PiBsEqwVg\bZXqpK1Ja\tFTd\u007fI([wB\u0004fTd\u007f\rIoD?~(\u0002h\u0001(+{\u0014\tFTd\u007fI([wB\u0003`^r\u0003SUa\u00061\u0012ksI,".length();
                var1_4 = 11;
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
                    var2_2 = "SUa\u0005q]jaA";
                    var4_3 = "SUa\u0005q]jaA".length();
                    var1_4 = 3;
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
                        v10 = 49;
                        break;
                    }
                    case 2: {
                        v10 = 5;
                        break;
                    }
                    case 3: {
                        v10 = 18;
                        break;
                    }
                    case 4: {
                        v10 = 36;
                        break;
                    }
                    case 5: {
                        v10 = 73;
                        break;
                    }
                    default: {
                        v10 = 47;
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
        TeamEditor.b = var5;
        TeamEditor.ASKED = new UUID(0L, 0L);
        TeamEditor.IDS = new ConcurrentHashMap<String, UUID>();
    }
}
