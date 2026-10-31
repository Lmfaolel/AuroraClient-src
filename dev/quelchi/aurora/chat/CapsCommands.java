package dev.quelchi.aurora.chat;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.mojang.brigadier.tree.RootCommandNode;
import dev.quelchi.aurora.chat.Emoji;
import dev.quelchi.aurora.module.Module;
import java.util.Iterator;
import java.util.Locale;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;

public final class CapsCommands {
    private static boolean caps;

    private CapsCommands() {
    }

    public static void reset() {
        caps = false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void typed(int n, int n2) {
        int n3;
        block18: {
            block19: {
                int n4;
                boolean bl;
                block17: {
                    int n5;
                    block16: {
                        int n6;
                        block15: {
                            bl = Emoji.a;
                            n6 = n;
                            if (bl) break block15;
                            if (!Character.isLetter(n6)) return;
                            n5 = n;
                            if (bl) break block16;
                            n6 = Character.isUpperCase(n5) ? 1 : 0;
                        }
                        if (n6 == Character.isLowerCase(n)) {
                            return;
                        }
                        n4 = n2;
                        if (bl) break block17;
                        n5 = n4 & 1;
                    }
                    n4 = n5 != 0 ? 1 : 0;
                }
                int n7 = n4;
                n3 = n;
                if (bl) break block18;
                if (Character.isUpperCase(n3) == n7) break block19;
                n3 = 1;
                break block18;
            }
            n3 = 0;
        }
        caps = n3;
    }

    public static String fix(String string) {
        RootCommandNode rootCommandNode;
        String string2;
        block16: {
            CommandDispatcher commandDispatcher;
            block14: {
                CommandDispatcher commandDispatcher2;
                block15: {
                    CommandDispatcher commandDispatcher3;
                    boolean bl;
                    block13: {
                        ClientPlayNetworkHandler clientPlayNetworkHandler;
                        block11: {
                            ClientPlayNetworkHandler clientPlayNetworkHandler2;
                            block12: {
                                bl = Emoji.a;
                                if (!caps) {
                                    return string;
                                }
                                clientPlayNetworkHandler2 = MinecraftClient.method_1551().method_1562();
                                clientPlayNetworkHandler = clientPlayNetworkHandler2;
                                if (bl) break block11;
                                if (clientPlayNetworkHandler != null) break block12;
                                commandDispatcher3 = null;
                                break block13;
                            }
                            clientPlayNetworkHandler = clientPlayNetworkHandler2;
                        }
                        commandDispatcher3 = clientPlayNetworkHandler.method_2886();
                    }
                    commandDispatcher2 = commandDispatcher3;
                    string2 = string;
                    commandDispatcher = commandDispatcher2;
                    if (bl) break block14;
                    if (commandDispatcher != null) break block15;
                    rootCommandNode = new RootCommandNode();
                    break block16;
                }
                commandDispatcher = commandDispatcher2;
            }
            rootCommandNode = commandDispatcher.getRoot();
        }
        return CapsCommands.literals(string2, rootCommandNode);
    }

    static String literals(String string, CommandNode<?> commandNode) {
        String string2;
        block39: {
            boolean bl;
            block41: {
                block40: {
                    boolean bl2;
                    block31: {
                        StringBuilder stringBuilder = new StringBuilder(string);
                        int n = 0;
                        bl2 = Emoji.a;
                        while (n <= string.length()) {
                            CommandNode commandNode2;
                            int n2;
                            block38: {
                                CommandNode commandNode3;
                                CommandNode commandNode4;
                                block36: {
                                    block37: {
                                        block33: {
                                            int n3;
                                            block32: {
                                                string2 = string;
                                                if (bl2) break block31;
                                                n2 = string2.indexOf(32, n);
                                                n3 = n2;
                                                if (bl2) break block32;
                                                if (n3 >= 0) break block33;
                                                n3 = string.length();
                                            }
                                            n2 = n3;
                                        }
                                        String string3 = string.substring(n, n2);
                                        commandNode4 = null;
                                        Iterator iterator = commandNode.getChildren().iterator();
                                        block29: while (true) {
                                            Iterator iterator2 = iterator;
                                            while (iterator2.hasNext()) {
                                                CommandNode commandNode5;
                                                block35: {
                                                    CommandNode commandNode6;
                                                    block34: {
                                                        iterator2 = iterator;
                                                        if (bl2) continue;
                                                        commandNode6 = (CommandNode)iterator2.next();
                                                        commandNode5 = commandNode6;
                                                        if (bl2) break block34;
                                                        if (!(commandNode5 instanceof LiteralCommandNode)) continue block29;
                                                        commandNode5 = commandNode6;
                                                    }
                                                    if (bl2) break block35;
                                                    if (!commandNode5.getName().equalsIgnoreCase(string3)) continue block29;
                                                    commandNode5 = commandNode6;
                                                }
                                                commandNode4 = commandNode5;
                                                if (!bl2) continue block29;
                                            }
                                            break;
                                        }
                                        commandNode3 = commandNode4;
                                        if (bl2) break block36;
                                        if (commandNode3 != null) break block37;
                                        if (n != 0) break;
                                        stringBuilder.replace(0, n2, string3.toLowerCase(Locale.ROOT));
                                        if (!bl2) break;
                                    }
                                    stringBuilder.replace(n, n2, commandNode4.getName());
                                    commandNode2 = commandNode4;
                                    if (bl2) break block38;
                                    commandNode3 = commandNode2.getRedirect();
                                }
                                commandNode2 = commandNode3 != null ? commandNode4.getRedirect() : commandNode4;
                            }
                            commandNode = commandNode2;
                            n = n2 + 1;
                            if (!bl2) continue;
                        }
                        string2 = stringBuilder.toString();
                    }
                    if (!Module.a) break block39;
                    if (!bl2) break block40;
                    bl = false;
                    break block41;
                }
                bl = true;
            }
            Emoji.a = bl;
        }
        return string2;
    }
}
