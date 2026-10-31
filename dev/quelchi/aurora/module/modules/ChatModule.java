package dev.quelchi.aurora.module.modules;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import dev.quelchi.aurora.gui.ToggleDisplay;
import dev.quelchi.aurora.mixins.ChatHudAccessor;
import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.AutoTextModule;
import dev.quelchi.aurora.module.modules.ChatModule$Language;
import dev.quelchi.aurora.module.modules.NametagsModule;
import dev.quelchi.aurora.module.modules.TierTaggerModule;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.EnumSetting;
import dev.quelchi.aurora.module.setting.KeybindSetting;
import dev.quelchi.aurora.module.setting.StringSetting;
import dev.quelchi.aurora.util.KeyUtil;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public final class ChatModule
extends Module {
    private static ChatModule instance;
    private final BooleanSetting mentionPing;
    private final BooleanSetting translate;
    private final EnumSetting<ChatModule$Language> translateTo;
    private final KeybindSetting translateKey;
    private final KeybindSetting sendKey;
    private final EnumSetting<ChatModule$Language> sendAs;
    private final KeybindSetting hideKey;
    private final KeybindSetting[] commandKeys;
    private final StringSetting[] commands;
    private final boolean[] commandWasDown;
    private static final HttpClient HTTP;
    private static final MessageIndicator TRANSLATION;
    private boolean translating;
    private boolean hidden;
    private boolean hideWasDown;
    private boolean translateWasDown;
    private static final char[] SEPARATORS;
    private static final String[] b;
    private static final long d;
    private static final long[] e;
    private static final Long[] f;

    public static ChatModule get() {
        return instance;
    }

    public ChatModule() {
        String[] stringArray = b;
        super(stringArray[7], stringArray[11], Category.MISC, false);
        boolean bl = AmbienceModule.b;
        this.mentionPing = new BooleanSetting(stringArray[6], true);
        this.translate = new BooleanSetting(stringArray[17], false);
        this.translateTo = new EnumSetting<ChatModule$Language>(stringArray[15], ChatModule$Language.English);
        this.translateKey = new KeybindSetting(stringArray[2], -1);
        this.sendKey = new KeybindSetting(stringArray[0], -1);
        this.sendAs = new EnumSetting<ChatModule$Language>(stringArray[3], ChatModule$Language.English);
        this.hideKey = new KeybindSetting(stringArray[13], -1);
        this.commandKeys = new KeybindSetting[3];
        this.commands = new StringSetting[3];
        this.commandWasDown = new boolean[3];
        instance = this;
        this.addSettings(this.mentionPing, this.translate, this.translateTo, this.translateKey, this.sendKey, this.sendAs, this.hideKey);
        this.translate.children(this.translateTo, this.translateKey);
        this.sendKey.children(this.sendAs);
        for (int i = 0; i < this.commandKeys.length; ++i) {
            stringArray = b;
            this.commandKeys[i] = new KeybindSetting(stringArray[1] + (i + 1), -1);
            this.commands[i] = new StringSetting(stringArray[4], "", 256);
            this.commandKeys[i].children(this.commands[i]);
            this.addSettings(this.commandKeys[i], this.commands[i]);
            if (!bl) continue;
        }
    }

    public static boolean chatHidden() {
        boolean bl;
        block11: {
            block9: {
                ChatModule chatModule;
                boolean bl2;
                block10: {
                    ChatModule chatModule2;
                    block8: {
                        chatModule2 = instance;
                        bl2 = AmbienceModule.b;
                        chatModule = chatModule2;
                        if (bl2) break block8;
                        if (chatModule == null) break block9;
                        chatModule = chatModule2;
                    }
                    if (bl2) break block10;
                    if (!chatModule.isEnabled()) break block9;
                    chatModule = chatModule2;
                }
                bl = chatModule.hidden;
                if (bl2) break block11;
                if (!bl) break block9;
                bl = true;
                break block11;
            }
            bl = false;
        }
        return bl;
    }

    @Override
    public void onDisable() {
        block5: {
            ChatModule chatModule;
            block4: {
                boolean bl = AmbienceModule.b;
                chatModule = this;
                if (bl) break block4;
                chatModule.hidden = false;
                if (!this.translating) break block5;
                chatModule = this;
            }
            chatModule.setTranslating(false);
        }
    }

    @Override
    public void onTick() {
        block44: {
            ChatModule chatModule;
            block47: {
                boolean bl;
                boolean bl2;
                block41: {
                    ChatModule chatModule2;
                    block40: {
                        boolean bl3;
                        BooleanSetting booleanSetting;
                        block42: {
                            block43: {
                                block33: {
                                    block35: {
                                        block34: {
                                            boolean bl4;
                                            String string;
                                            block38: {
                                                block39: {
                                                    boolean bl5;
                                                    block36: {
                                                        block37: {
                                                            bl2 = AmbienceModule.b;
                                                            bl = KeyUtil.isKeyPressedInGame(this.hideKey.getKey());
                                                            if (bl2) break block33;
                                                            if (!bl) break block34;
                                                            chatModule2 = this;
                                                            if (bl2) break block35;
                                                            if (chatModule2.hideWasDown) break block34;
                                                            ChatModule chatModule3 = this;
                                                            bl5 = this.hidden;
                                                            if (bl2) break block36;
                                                            if (bl5) break block37;
                                                            bl5 = true;
                                                            break block36;
                                                        }
                                                        bl5 = false;
                                                    }
                                                    chatModule3.hidden = bl5;
                                                    string = b[14];
                                                    bl4 = this.hidden;
                                                    if (bl2) break block38;
                                                    if (bl4) break block39;
                                                    bl4 = true;
                                                    break block38;
                                                }
                                                bl4 = false;
                                            }
                                            ToggleDisplay.push(string, bl4);
                                        }
                                        this.hideWasDown = bl;
                                        chatModule2 = this;
                                    }
                                    if (bl2) break block41;
                                    bl = KeyUtil.isKeyPressedInGame(chatModule2.translateKey.getKey());
                                }
                                if (!bl) break block40;
                                chatModule2 = this;
                                if (bl2) break block41;
                                if (chatModule2.translateWasDown) break block40;
                                booleanSetting = this.translate;
                                bl3 = this.translate.getValue();
                                if (bl2) break block42;
                                if (bl3) break block43;
                                bl3 = true;
                                break block42;
                            }
                            bl3 = false;
                        }
                        booleanSetting.setValue(bl3);
                        ToggleDisplay.push(b[8], this.translate.getValue());
                    }
                    chatModule2 = this;
                }
                chatModule2.translateWasDown = bl;
                for (int i = 0; i < this.commandKeys.length; ++i) {
                    block45: {
                        ChatModule chatModule4;
                        block46: {
                            if (bl2) break block44;
                            chatModule4 = this;
                            if (bl2) break block45;
                            bl = KeyUtil.isKeyPressedInGame(chatModule4.commandKeys[i].getKey());
                            if (!bl) break block46;
                            chatModule4 = this;
                            if (bl2) break block45;
                            if (chatModule4.commandWasDown[i]) break block46;
                            AutoTextModule.send(this.commands[i].getValue().strip());
                        }
                        chatModule4 = this;
                    }
                    chatModule4.commandWasDown[i] = bl;
                    if (!bl2) continue;
                }
                chatModule = this;
                if (bl2) break block47;
                if (chatModule.translate.getValue() == this.translating) break block44;
                chatModule = this;
            }
            chatModule.setTranslating(this.translate.getValue());
        }
    }

    private static int headerEnd(String string) {
        int n;
        block13: {
            int n2 = -1;
            char[] cArray = SEPARATORS;
            int n3 = cArray.length;
            boolean bl = AmbienceModule.b;
            int n4 = 0;
            while (n4 < n3) {
                block14: {
                    block15: {
                        int n5;
                        block16: {
                            int n6;
                            block17: {
                                char c = cArray[n4];
                                n = string.indexOf(c);
                                if (bl) break block13;
                                n6 = n;
                                if (bl) break block14;
                                if (n6 < 0) break block15;
                                n5 = n2;
                                if (bl) break block16;
                                if (n5 < 0) break block17;
                                n5 = n6;
                                if (bl) break block16;
                                if (n5 >= n2) break block15;
                            }
                            n5 = n6;
                        }
                        n2 = n5;
                    }
                    ++n4;
                }
                if (!bl) continue;
            }
            n = n2;
        }
        return n;
    }

    private static PlayerListEntry senderOf(MinecraftClient minecraftClient, String string) {
        PlayerListEntry playerListEntry;
        block15: {
            int n = ChatModule.headerEnd(string);
            boolean bl = AmbienceModule.b;
            if (n <= 0) {
                return null;
            }
            String string2 = string.substring(0, n);
            PlayerListEntry playerListEntry2 = null;
            int n2 = -1;
            Iterator iterator = minecraftClient.method_1562().method_2880().iterator();
            while (true) {
                block20: {
                    PlayerListEntry playerListEntry3;
                    block19: {
                        String string3;
                        String string4;
                        block17: {
                            block16: {
                                if (!iterator.hasNext()) break;
                                playerListEntry = (PlayerListEntry)iterator.next();
                                if (bl) break block15;
                                playerListEntry3 = playerListEntry;
                                string4 = playerListEntry3.method_2966().name();
                                string3 = string4;
                                if (bl) break block16;
                                if (string3 == null) continue;
                                string3 = string4;
                            }
                            if (bl) break block17;
                            if (string3.isEmpty()) {
                                continue;
                            }
                            string3 = string2;
                        }
                        int n3 = ChatModule.lastWholeName(string3, string4);
                        if (bl) break block19;
                        if (n3 <= n2) break block20;
                        n2 = n3;
                    }
                    playerListEntry2 = playerListEntry3;
                }
                if (bl) break;
            }
            playerListEntry = playerListEntry2;
        }
        return playerListEntry;
    }

    static int lastWholeName(String string, String string2) {
        int n;
        block17: {
            int n2 = string.lastIndexOf(string2);
            boolean bl = AmbienceModule.b;
            while (n2 >= 0) {
                String string3;
                block20: {
                    block21: {
                        int n3;
                        block22: {
                            block23: {
                                int n4;
                                block18: {
                                    block19: {
                                        n = n2 + string2.length();
                                        if (bl) break block17;
                                        n4 = n;
                                        n3 = n2;
                                        if (bl) break block18;
                                        if (n3 == 0) break block19;
                                        string3 = string;
                                        if (bl) break block20;
                                        if (ChatModule.nameChar(string3.charAt(n2 - 1))) break block21;
                                    }
                                    n3 = n4;
                                }
                                if (bl) break block22;
                                if (n3 == string.length()) break block23;
                                string3 = string;
                                if (bl) break block20;
                                if (ChatModule.nameChar(string3.charAt(n4))) break block21;
                            }
                            n3 = n2;
                        }
                        return n3;
                    }
                    string3 = string;
                }
                n2 = string3.lastIndexOf(string2, n2 - 1);
                if (!bl) continue;
            }
            n = -1;
        }
        return n;
    }

    private static boolean nameChar(char c) {
        int c2;
        block8: {
            block10: {
                block9: {
                    boolean bl = AmbienceModule.b;
                    c2 = c;
                    if (bl) break block8;
                    if (c2 == 95) break block9;
                    c2 = c;
                    if (bl) break block8;
                    if (!Character.isLetterOrDigit(c2)) break block10;
                }
                c2 = 1;
                break block8;
            }
            c2 = 0;
        }
        return c2 != 0;
    }

    public Text decorate(Text text) {
        Text text2;
        block40: {
            TierTaggerModule tierTaggerModule;
            block39: {
                TierTaggerModule tierTaggerModule2;
                boolean bl;
                block37: {
                    UUID uUID;
                    PlayerListEntry playerListEntry;
                    block38: {
                        block33: {
                            block34: {
                                Object object;
                                block35: {
                                    TierTaggerModule tierTaggerModule3;
                                    block36: {
                                        block32: {
                                            PlayerListEntry playerListEntry2;
                                            boolean bl2;
                                            block29: {
                                                block30: {
                                                    block28: {
                                                        Text text3;
                                                        block27: {
                                                            bl = AmbienceModule.b;
                                                            text3 = text;
                                                            if (bl) break block27;
                                                            if (text3 != null) break block28;
                                                            text3 = text;
                                                        }
                                                        return text3;
                                                    }
                                                    bl2 = TierTaggerModule.showsInChat();
                                                    MinecraftClient minecraftClient = MinecraftClient.method_1551();
                                                    if (minecraftClient.method_1562() == null) {
                                                        return text;
                                                    }
                                                    String string = text.getString();
                                                    if (string.isEmpty()) {
                                                        return text;
                                                    }
                                                    playerListEntry = ChatModule.senderOf(minecraftClient, string);
                                                    playerListEntry2 = playerListEntry;
                                                    if (bl) break block29;
                                                    if (playerListEntry2 != null) break block30;
                                                    return text;
                                                }
                                                playerListEntry2 = playerListEntry;
                                            }
                                            uUID = playerListEntry2.method_2966().id();
                                            text2 = null;
                                            if (bl2) {
                                                TierTaggerModule tierTaggerModule4;
                                                block31: {
                                                    tierTaggerModule3 = TierTaggerModule.get();
                                                    tierTaggerModule4 = tierTaggerModule3;
                                                    if (bl) break block31;
                                                    if (tierTaggerModule4 == null) break block32;
                                                    tierTaggerModule4 = tierTaggerModule3;
                                                }
                                                text2 = tierTaggerModule4.segmentFor(uUID, playerListEntry.method_2966().name());
                                            }
                                        }
                                        tierTaggerModule3 = NametagsModule.badgeFor(uUID);
                                        tierTaggerModule2 = tierTaggerModule3;
                                        if (bl) break block33;
                                        if (tierTaggerModule2 == null) break block34;
                                        object = text2;
                                        if (bl) break block35;
                                        if (object != null) break block36;
                                        object = tierTaggerModule3;
                                        break block35;
                                    }
                                    object = Text.method_43473().method_10852((Text)tierTaggerModule3).method_10852((Text)Text.method_43470((String)" ")).method_10852(text2);
                                }
                                text2 = object;
                            }
                            tierTaggerModule2 = text2;
                        }
                        if (bl) break block37;
                        if (tierTaggerModule2 != null) break block38;
                        return text;
                    }
                    tierTaggerModule2 = NametagsModule.beforeName(text, playerListEntry.method_2966().name(), uUID, text2);
                }
                TierTaggerModule tierTaggerModule5 = tierTaggerModule2;
                tierTaggerModule = tierTaggerModule5;
                if (bl) break block39;
                if (tierTaggerModule == null) break block40;
                tierTaggerModule = tierTaggerModule5;
            }
            return tierTaggerModule;
        }
        return Text.method_43473().method_10852(text2).method_10852((Text)Text.method_43470((String)" ")).method_10852((Text)text.method_27661());
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void checkMention(Text text) {
        MinecraftClient minecraftClient;
        block48: {
            int n;
            String string;
            String string2;
            boolean bl;
            block47: {
                String string3;
                block45: {
                    block46: {
                        String string4;
                        block43: {
                            block44: {
                                block42: {
                                    MinecraftClient minecraftClient2;
                                    block40: {
                                        block41: {
                                            block39: {
                                                block38: {
                                                    ChatModule chatModule;
                                                    block37: {
                                                        bl = AmbienceModule.b;
                                                        chatModule = this;
                                                        if (bl) break block37;
                                                        if (!chatModule.isEnabled()) return;
                                                        chatModule = this;
                                                    }
                                                    if (chatModule.mentionPing.getValue() && text != null) break block38;
                                                    return;
                                                }
                                                minecraftClient = MinecraftClient.method_1551();
                                                minecraftClient2 = minecraftClient;
                                                if (bl) break block39;
                                                if (minecraftClient2.field_1724 == null) return;
                                                minecraftClient2 = minecraftClient;
                                            }
                                            if (bl) break block40;
                                            if (minecraftClient2.method_1562() != null) break block41;
                                            return;
                                        }
                                        minecraftClient2 = minecraftClient;
                                    }
                                    string2 = minecraftClient2.field_1724.method_7334().name();
                                    string4 = string2;
                                    if (bl) break block42;
                                    if (string4 == null) return;
                                    string4 = string2;
                                }
                                if (bl) break block43;
                                if (!string4.isEmpty()) break block44;
                                return;
                            }
                            string4 = text.getString();
                        }
                        string = string4;
                        string3 = string;
                        if (bl) break block45;
                        if (!string3.isEmpty()) break block46;
                        return;
                    }
                    string3 = string;
                }
                n = ChatModule.headerEnd(string3);
                if (n < 0) {
                    return;
                }
                PlayerListEntry playerListEntry = ChatModule.senderOf(minecraftClient, string);
                if (playerListEntry != null && !minecraftClient.field_1724.method_5667().equals(playerListEntry.method_2966().id())) break block47;
                return;
            }
            String string5 = string.substring(n + 1).toLowerCase(Locale.ROOT);
            if (bl) return;
            if (string5.contains(string2.toLowerCase(Locale.ROOT))) break block48;
            return;
        }
        minecraftClient.method_1483().method_4873((SoundInstance)PositionedSoundInstance.method_47978((RegistryEntry)SoundEvents.field_14793, (float)1.6f));
    }

    private static void googleTranslate(String string, String string2, BiConsumer<String, String> biConsumer) {
        String[] stringArray = b;
        String string3 = stringArray[9] + URLEncoder.encode(string2, StandardCharsets.UTF_8) + stringArray[12] + URLEncoder.encode(string, StandardCharsets.UTF_8);
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(string3)).timeout(Duration.ofSeconds(ChatModule.a(27152, 3958613315241832830L))).build();
        ((CompletableFuture)HTTP.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString()).thenAccept(arg_0 -> ChatModule.lambda$googleTranslate$1(biConsumer, arg_0))).exceptionally(ChatModule::lambda$googleTranslate$2);
    }

    private static String langName(String string) {
        String string2;
        block4: {
            String string3;
            block5: {
                string3 = Locale.forLanguageTag(string).getDisplayLanguage(Locale.ENGLISH);
                boolean bl = AmbienceModule.b;
                string2 = string3;
                if (bl) break block4;
                if (!string2.isEmpty()) break block5;
                string2 = string;
                break block4;
            }
            string2 = string3;
        }
        return string2;
    }

    public boolean translateTyped(int n, TextFieldWidget textFieldWidget) {
        String string;
        String string2;
        String string3;
        block25: {
            block26: {
                boolean bl;
                block23: {
                    block24: {
                        boolean bl2;
                        block22: {
                            int n2;
                            block20: {
                                block21: {
                                    bl2 = AmbienceModule.b;
                                    n2 = this.isEnabled();
                                    if (bl2) break block20;
                                    if (n2 == 0) break block21;
                                    n2 = n;
                                    if (bl2) break block20;
                                    if (n2 == -1) break block21;
                                    n2 = n;
                                    if (bl2) break block20;
                                    if (n2 == this.sendKey.getKey()) break block22;
                                }
                                n2 = 0;
                            }
                            return n2 != 0;
                        }
                        string3 = textFieldWidget.method_1882();
                        bl = string3.isBlank();
                        if (bl2) break block23;
                        if (bl) break block24;
                        string2 = string3;
                        string = "/";
                        if (bl2) break block25;
                        if (!string2.startsWith(string)) break block26;
                    }
                    bl = false;
                }
                return bl;
            }
            string2 = string3;
            string = this.sendAs.getValue().code;
        }
        ChatModule.googleTranslate(string2, string, (arg_0, arg_1) -> ChatModule.lambda$translateTyped$3(textFieldWidget, string3, arg_0, arg_1));
        return true;
    }

    
    private void setTranslating(boolean bl) {
        boolean bl2 = AmbienceModule.b;
        ChatModule chatModule = this;
        if (!bl2) {
            chatModule.translating = bl;
            if (this.mc.field_1705 == null) {
                return;
            }
            chatModule = this;
        }
        ChatHudAccessor chatHudAccessor = (ChatHudAccessor)chatModule.mc.field_1705.method_1743();
        List<ChatHudLine> list = chatHudAccessor.aurora$messages();
        boolean bl3 = bl;
        if (!bl2) {
            if (bl3) {
                for (ChatHudLine chatHudLine : List.copyOf(list.subList(0, Math.min(50, list.size())))) {
                    if (bl2) return;
                    this.translateLine(chatHudLine);
                    if (!bl2) continue;
                }
                if (!bl2) return;
            }
            bl3 = list.removeIf(ChatModule::lambda$setTranslating$4);
        }
        if (!bl3) return;
        chatHudAccessor.aurora$refresh();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void translateLine(ChatHudLine chatHudLine) {
        String string;
        String string2;
        block30: {
            block31: {
                int n;
                String string3;
                boolean bl;
                block29: {
                    ChatModule chatModule;
                    block28: {
                        block27: {
                            ChatModule chatModule2;
                            block26: {
                                bl = AmbienceModule.b;
                                chatModule2 = this;
                                if (bl) break block26;
                                if (!chatModule2.isEnabled()) return;
                                chatModule2 = this;
                            }
                            if (chatModule2.translating && chatHudLine.comp_894() != TRANSLATION) break block27;
                            return;
                        }
                        chatModule = this;
                        if (bl) break block28;
                        if (chatModule.mc.field_1724 == null) return;
                        chatModule = this;
                    }
                    if (chatModule.mc.method_1562() == null) {
                        return;
                    }
                    string3 = chatHudLine.comp_893().getString();
                    n = ChatModule.headerEnd(string3);
                    if (n < 0) {
                        return;
                    }
                    PlayerListEntry playerListEntry = ChatModule.senderOf(this.mc, string3);
                    if (playerListEntry != null && !this.mc.field_1724.method_5667().equals(playerListEntry.method_2966().id())) break block29;
                    return;
                }
                string2 = string3.substring(n + 1).trim();
                string = string2;
                if (bl) break block30;
                if (!string.isEmpty()) break block31;
                return;
            }
            string = this.translateTo.getValue().code;
        }
        String string4 = string;
        ChatModule.googleTranslate(string2, string4, (arg_0, arg_1) -> this.lambda$translateLine$6(string4, string2, chatHudLine, arg_0, arg_1));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void lambda$translateLine$6(String string, String string2, ChatHudLine chatHudLine, String string3, String string4) {
        Object object;
        boolean bl;
        block18: {
            block19: {
                block17: {
                    boolean bl2;
                    block16: {
                        bl = AmbienceModule.b;
                        bl2 = this.translating;
                        if (bl) break block16;
                        if (!bl2) return;
                        object = string4;
                        if (bl) break block17;
                        bl2 = ((String)object).equalsIgnoreCase(string);
                    }
                    if (bl2) return;
                    object = string3;
                }
                if (bl) break block18;
                if (!((String)object).equalsIgnoreCase(string2)) break block19;
                return;
            }
            String[] stringArray = b;
            object = ChatModule.langName(string4) + stringArray[10] + ChatModule.langName(string) + "\n";
        }
        MutableText mutableText = Text.method_43470((String)object).method_27692(Formatting.field_1080).method_10852((Text)Text.method_43470((String)string2).method_27692(Formatting.field_1068));
        MutableText mutableText2 = Text.method_43470((String)b[16]).method_54663((int)d).method_10852((Text)Text.method_43470((String)string3).method_27692(Formatting.field_1080)).method_27694(arg_0 -> ChatModule.lambda$translateLine$5((Text)mutableText, arg_0));
        ChatHudAccessor chatHudAccessor = (ChatHudAccessor)this.mc.field_1705.method_1743();
        List<ChatHudLine> list = chatHudAccessor.aurora$messages();
        for (int i = 0; i < list.size(); ++i) {
            List<ChatHudLine> list2;
            block20: {
                list2 = list;
                if (bl) break block20;
                if (list2.get(i) != chatHudLine) {
                    continue;
                }
                list2 = list;
            }
            list2.add(i, new ChatHudLine(chatHudLine.comp_892(), (Text)mutableText2, null, TRANSLATION));
            chatHudAccessor.aurora$refresh();
            return;
        }
    }

    private static Style lambda$translateLine$5(Text text, Style style) {
        return style.method_10949((HoverEvent)new HoverEvent.ShowText(text));
    }

    private static boolean lambda$setTranslating$4(ChatHudLine chatHudLine) {
        boolean bl = chatHudLine.comp_894() == TRANSLATION;
        return bl;
    }

    private static void lambda$translateTyped$3(TextFieldWidget textFieldWidget, String string, String string2, String string3) {
        block5: {
            TextFieldWidget textFieldWidget2;
            block4: {
                boolean bl = AmbienceModule.b;
                textFieldWidget2 = textFieldWidget;
                if (bl) break block4;
                if (!textFieldWidget2.method_1882().equals(string)) break block5;
                textFieldWidget2 = textFieldWidget;
            }
            textFieldWidget2.method_1852(string2);
        }
    }

    private static Void lambda$googleTranslate$2(Throwable throwable) {
        return null;
    }

    private static void lambda$googleTranslate$1(BiConsumer biConsumer, HttpResponse httpResponse) {
        HttpResponse httpResponse2;
        block7: {
            block8: {
                boolean bl = AmbienceModule.b;
                httpResponse2 = httpResponse;
                if (bl) break block7;
                if (httpResponse2.statusCode() == 200) break block8;
                return;
            }
            httpResponse2 = httpResponse.body();
        }
        JsonArray jsonArray = JsonParser.parseString((String)((String)((Object)httpResponse2))).getAsJsonArray().get(0).getAsJsonArray();
        String string = jsonArray.get(0).getAsString().trim();
        String string2 = jsonArray.get(1).getAsString();
        if (!string.isEmpty()) {
            MinecraftClient.method_1551().execute(() -> ChatModule.lambda$googleTranslate$0(biConsumer, string, string2));
        }
    }

    private static void lambda$googleTranslate$0(BiConsumer biConsumer, String string, String string2) {
        biConsumer.accept(string, string2);
    }

    
    static {
        block24: {
            block23: {
                block25: {
                    block22: {
                        block21: {
                            var15 = new String[18];
                            var13_1 = 0;
                            var12_2 = "LJ\u0006o\b+\u0010l]Gl\u0002g\u001c}K\u0014`\u001c\"\f[W\nl\u001a)\u00158S\u0002x[\rLJ\u0006o\b+\u0010l]Gj\u001e>\u0004QV\u0013n\u0005K]\te\b\u000bLJ\u0006o\b+\u0010lQ\bo\fU]\tu\u0012(\u001f8H\u000eo\u001c\u0004[P\u0006u\tLJ\u0006o\b+\u0010l]KpL\u0013q\b}^7[\u000bh\u001e)\u0005k\rIf\u0014(\u0016t]Ib\u0014*^lJ\u0006o\b+\u0010l]8`T3N{T\u000ed\u00153L|Q\u0004uV$\u0019jW\ndV\"\t>K\u000b<\u001a2\u0005w\u001e\u0013mF\u00038\u21aaG=UY\u0015j\bg\u0018v\u0018\u0004i\u001a3]8L\u0015`\u00154\u001dyL\u000en\u0015kQ{W\nl\u001a)\u00158S\u0002x\bg\u0010v\\G`[,\u0014a\u0018\u0013n[/\u0018|]Gh\u000f\u0003>IZ\tPQ\u0003d[$\u0019yL\u0004[P\u0006u\bTY\tf\u000e&\u0016}";
                            var14_3 = "LJ\u0006o\b+\u0010l]Gl\u0002g\u001c}K\u0014`\u001c\"\f[W\nl\u001a)\u00158S\u0002x[\rLJ\u0006o\b+\u0010l]Gj\u001e>\u0004QV\u0013n\u0005K]\te\b\u000bLJ\u0006o\b+\u0010lQ\bo\fU]\tu\u0012(\u001f8H\u000eo\u001c\u0004[P\u0006u\tLJ\u0006o\b+\u0010l]KpL\u0013q\b}^7[\u000bh\u001e)\u0005k\rIf\u0014(\u0016t]Ib\u0014*^lJ\u0006o\b+\u0010l]8`T3N{T\u000ed\u00153L|Q\u0004uV$\u0019jW\ndV\"\t>K\u000b<\u001a2\u0005w\u001e\u0013mF\u00038\u21aaG=UY\u0015j\bg\u0018v\u0018\u0004i\u001a3]8L\u0015`\u00154\u001dyL\u000en\u0015kQ{W\nl\u001a)\u00158S\u0002x\bg\u0010v\\G`[,\u0014a\u0018\u0013n[/\u0018|]Gh\u000f\u0003>IZ\tPQ\u0003d[$\u0019yL\u0004[P\u0006u\bTY\tf\u000e&\u0016}".length();
                            var11_4 = 20;
                            var10_5 = -1;
lbl7:

                            while (true) {
                                v0 = ++var10_5;
                                v1 = var12_2.substring(v0, v0 + var11_4);
                                v2 = -1;
                                break block21;
                                break;
                            }
lbl12:

                            while (true) {
                                var15[var13_1++] = v3.intern();
                                if ((var10_5 += var11_4) < var14_3) {
                                    var11_4 = var12_2.charAt(var10_5);
                                    continue;
                                }
                                var12_2 = "8\u290fG\tLJ\u0006o\b+\u0010l]";
                                var14_3 = "8\u290fG\tLJ\u0006o\b+\u0010l]".length();
                                var11_4 = 3;
                                var10_5 = -1;
lbl21:

                                while (true) {
                                    v4 = ++var10_5;
                                    v1 = var12_2.substring(v4, v4 + var11_4);
                                    v2 = 0;
                                    break block21;
                                    break;
                                }
                                break;
                            }
lbl26:

                            while (true) {
                                var15[var13_1++] = v3.intern();
                                if ((var10_5 += var11_4) < var14_3) {
                                    var11_4 = var12_2.charAt(var10_5);
                                    continue;
                                }
                                break block22;
                                break;
                            }
                        }
                        v5 = v1.toCharArray();
                        v6 = v5;
                        v7 = v5.length;
                        var16_6 = 0;
                        do {
                            v6 = v6;
                            v8 = var16_6;
                            v9 = v6[v8];
                            switch (var16_6 % 7) {
                                case 0: {
                                    v10 = 24;
                                    break;
                                }
                                case 1: {
                                    v10 = 56;
                                    break;
                                }
                                case 2: {
                                    v10 = 103;
                                    break;
                                }
                                case 3: {
                                    v10 = 1;
                                    break;
                                }
                                case 4: {
                                    v10 = 123;
                                    break;
                                }
                                case 5: {
                                    v10 = 71;
                                    break;
                                }
                                default: {
                                    v10 = 113;
                                }
                            }
                            v6[v8] = (char)(v9 ^ v10);
                            ++var16_6;
lbl65:

                            v7 = v7;
                        } while (v7 > var16_6);
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
                    ChatModule.b = var15;
                    break block25;
lbl77:

                    while (true) {
                        continue;
                        break;
                    }
                }
                var8_7 = 7383229627391096738L;
                while (true)
                ChatModule.d = 5292016136927758429L ^ var8_7;
                var0_8 = 9165741041962709426L;
                var6_9 = new long[2];
                var3_10 = 0;
                var4_11 = "I\u00dc\u0091<wy\u0000\u00c9;\u00a3\u00c6\u00d0$\u00ed\u00ce\u0011";
                var5_12 = "I\u00dc\u0091<wy\u0000\u00c9;\u00a3\u00c6\u00d0$\u00ed\u00ce\u0011".length();
                var2_13 = 0;
                while (true) {
                    break block23;
                    break;
                }
lbl94:

                while (true) {
                    var6_9[v11] = (((long)var7_14[0] & 255L) << 56 | ((long)var7_14[1] & 255L) << 48 | ((long)var7_14[2] & 255L) << 40 | ((long)var7_14[3] & 255L) << 32 | ((long)var7_14[4] & 255L) << 24 | ((long)var7_14[5] & 255L) << 16 | ((long)var7_14[6] & 255L) << 8 | (long)var7_14[7] & 255L) ^ var0_8;
                    if (var2_13 < var5_12) continue;
                    break block24;
                    break;
                }
            }
            var7_14 = var4_11.substring(var2_13, var2_13 += 8).getBytes("ISO-8859-1");
            v11 = var3_10++;
            while (true)
        }
        ChatModule.e = var6_9;
        ChatModule.f = new Long[2];
        ChatModule.HTTP = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(ChatModule.a(9417, 4940596376930762662L))).build();
        ChatModule.TRANSLATION = new MessageIndicator(0, null, null, ChatModule.b[5]);
        ChatModule.SEPARATORS = new char[]{':', '>', '\u00bb', '\u25b6'};
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x1B6E) & Short.MAX_VALUE;
        if (f[n2] == null) {
            ChatModule.f[n2] = e[n2] ^ l;
        }
        return f[n2];
    }
}
