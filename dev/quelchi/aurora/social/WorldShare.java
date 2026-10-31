package dev.quelchi.aurora.social;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.cosmetics.backend.CosmeticsSync;
import dev.quelchi.aurora.gui.SocialToast;
import dev.quelchi.aurora.social.Arq;
import dev.quelchi.aurora.social.Social;
import dev.quelchi.aurora.social.Social$Friend;
import dev.quelchi.aurora.social.WorldShare$Invite;
import dev.quelchi.aurora.social.WorldShare$Peer;
import java.io.InputStream;
import java.lang.invoke.LambdaMetafactory;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HexFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.ConnectScreen;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.server.integrated.IntegratedServer;

public final class WorldShare {
    public static final String PREFIX;
    private static final long PUNCH_MS;
    private static final long HELLO_MS;
    private static final long JOIN_MS;
    private static volatile DatagramSocket sock;
    private static volatile InetSocketAddress rendezvous;
    private static volatile String ticket;
    private static volatile boolean host;
    private static volatile String world;
    private static volatile String friendName;
    private static volatile String friendWorld;
    private static volatile int lanPort;
    private static volatile long started;
    private static volatile long lastHello;
    private static volatile long lastAck;
    private static volatile ServerSocket listen;
    private static final Map<String, WorldShare$Peer> PEERS;
    private static volatile String mode;
    private static final Set<UUID> INVITED;
    private static volatile List<WorldShare$Invite> invites;
    private static final Set<UUID> DISMISSED;
    private static final String[] a;
    private static final long[] b;
    private static final Integer[] c;
    private static final long[] d;
    private static final Long[] e;

    private WorldShare() {
    }

    public static boolean hosting() {
        boolean bl;
        block5: {
            block4: {
                if (!host || sock == null) break block4;
                bl = true;
                break block5;
            }
            bl = false;
        }
        return bl;
    }

    public static String mode() {
        String string = WorldShare.hosting() ? mode : a[24];
        return string;
    }

    public static void setMode(String string) {
        String string2;
        block10: {
            block11: {
                int n;
                block8: {
                    block9: {
                        n = Social.a;
                        string2 = string;
                        if (n != 0) break block8;
                        if (!string2.equals(a[34])) break block9;
                        WorldShare.stop();
                        return;
                    }
                    string2 = string;
                }
                if (n != 0) break block10;
                mode = string2;
                if (WorldShare.hosting()) break block11;
                WorldShare.host();
                return;
            }
            string2 = a[33];
        }
        WorldShare.thread(string2, WorldShare::lambda$setMode$0);
    }

    public static boolean invited(UUID uUID) {
        return INVITED.contains(uUID);
    }

    public static void invite(UUID uUID, boolean bl) {
        block16: {
            block18: {
                boolean bl2;
                block17: {
                    int n;
                    block15: {
                        block13: {
                            boolean bl3;
                            block14: {
                                n = Social.a;
                                bl3 = bl;
                                if (n != 0) break block13;
                                if (!bl3) break block14;
                                INVITED.add(uUID);
                                if (n == 0) break block13;
                            }
                            bl3 = INVITED.remove(uUID);
                        }
                        bl2 = WorldShare.hosting();
                        if (n != 0) break block15;
                        if (bl2) break block16;
                        bl2 = bl;
                    }
                    if (n != 0) break block17;
                    if (!bl2) break block18;
                    bl2 = host;
                }
                if (!bl2) {
                    mode = a[32];
                    WorldShare.host();
                }
            }
            return;
        }
        WorldShare.send(uUID, bl);
    }

    private static void send(UUID uUID, boolean bl) {
        WorldShare.thread(a[4], () -> WorldShare.lambda$send$1(uUID, bl));
    }

    public static List<WorldShare$Invite> invites() {
        return invites;
    }

    static void invites(Set<UUID> set) {
        block18: {
            DISMISSED.retainAll(set);
            int n = Social.a;
            List<WorldShare$Invite> list = invites;
            ArrayList<WorldShare$Invite> arrayList = new ArrayList<WorldShare$Invite>();
            Iterator<UUID> iterator = set.iterator();
            block12: while (true) {
                boolean bl;
                WorldShare$Invite worldShare$Invite;
                if (!iterator.hasNext()) break;
                Object object2 = iterator.next();
                block13: while (true) {
                    Social$Friend social$Friend;
                    Social$Friend social$Friend2;
                    UUID uUID;
                    block19: {
                        uUID = object2;
                        if (n != 0) break block18;
                        if (DISMISSED.contains(uUID)) {
                            continue block12;
                        }
                        social$Friend2 = Social.friend(uUID);
                        social$Friend = social$Friend2;
                        if (n != 0) break block19;
                        if (social$Friend == null) continue block12;
                        social$Friend = social$Friend2;
                    }
                    if (!social$Friend.server().startsWith(a[23])) {
                        continue block12;
                    }
                    worldShare$Invite = new WorldShare$Invite(uUID, social$Friend2.name(), social$Friend2.server().substring(a[20].length()));
                    arrayList.add(worldShare$Invite);
                    bl = true;
                    for (Object object2 : list) {
                        block20: {
                            if (n != 0) continue block13;
                            Object object3 = object2;
                            boolean bl2 = ((WorldShare$Invite)object3).who().equals(uUID);
                            if (n != 0 || !bl2) break block20;
                            bl2 = bl = false;
                        }
                        if (n == 0) continue;
                    }
                    break;
                }
                if (bl) {
                    SocialToast.invite(worldShare$Invite);
                }
                if (n != 0) break;
            }
            invites = List.copyOf(arrayList);
        }
    }

    public static void dismiss(UUID uUID) {
        DISMISSED.add(uUID);
        ArrayList<WorldShare$Invite> arrayList = new ArrayList<WorldShare$Invite>(invites);
        arrayList.removeIf(arg_0 -> WorldShare.lambda$dismiss$2(uUID, arg_0));
        invites = List.copyOf(arrayList);
    }

    public static void accept(WorldShare$Invite worldShare$Invite) {
        WorldShare.dismiss(worldShare$Invite.who());
        WorldShare.join(worldShare$Invite.who(), worldShare$Invite.name(), a[20] + worldShare$Invite.world());
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static List<UUID> inWorld() {
        MinecraftClient minecraftClient;
        int n;
        MinecraftClient minecraftClient2;
        block14: {
            minecraftClient2 = MinecraftClient.method_1551();
            n = Social.a;
            if (!WorldShare.hosting()) return List.of();
            minecraftClient = minecraftClient2;
            if (n != 0) break block14;
            if (minecraftClient.method_1562() == null) return List.of();
            minecraftClient = minecraftClient2;
        }
        if (minecraftClient.field_1724 == null) {
            return List.of();
        }
        ArrayList<UUID> arrayList = new ArrayList<UUID>();
        for (PlayerListEntry playerListEntry : minecraftClient2.method_1562().method_2880()) {
            block15: {
                UUID uUID = playerListEntry.method_2966().id();
                boolean bl = uUID.equals(minecraftClient2.field_1724.method_5667());
                if (n != 0 || bl) break block15;
                bl = arrayList.add(uUID);
            }
            if (n == 0) continue;
        }
        return arrayList;
    }

    public static String worldName() {
        String string;
        block6: {
            MinecraftClient minecraftClient;
            block4: {
                MinecraftClient minecraftClient2;
                block5: {
                    minecraftClient2 = MinecraftClient.method_1551();
                    int n = Social.a;
                    minecraftClient = minecraftClient2;
                    if (n != 0) break block4;
                    if (minecraftClient.method_1576() != null) break block5;
                    string = world;
                    break block6;
                }
                minecraftClient = minecraftClient2;
            }
            string = minecraftClient.method_1576().method_27728().method_150();
        }
        return string;
    }

    public static boolean joined() {
        boolean bl;
        block5: {
            block4: {
                if (host || sock == null) break block4;
                bl = true;
                break block5;
            }
            bl = false;
        }
        return bl;
    }

    public static String presence() {
        return a[20] + world;
    }

    public static String where(String string) {
        Object object;
        block4: {
            block5: {
                int n = Social.a;
                object = string;
                if (n != 0) break block4;
                if (!((String)object).startsWith(a[20])) break block5;
                object = a[0] + string.substring(a[20].length());
                break block4;
            }
            object = string;
        }
        return object;
    }

    
    public static void host() {
        IntegratedServer integratedServer;
        block27: {
            IntegratedServer integratedServer2;
            block29: {
                boolean bl;
                int n;
                MinecraftClient minecraftClient;
                block25: {
                    block26: {
                        block31: {
                            block24: {
                                block30: {
                                    minecraftClient = MinecraftClient.method_1551();
                                    n = Social.a;
                                    integratedServer2 = minecraftClient.method_1576();
                                    if (integratedServer2 == null) return;
                                    if (host) return;
                                    break block30;
                                    catch (Throwable throwable) {
                                        throw throwable;
                                    }
                                }
                                if (minecraftClient.field_1761 != null) break block24;
                                return;
                                catch (Throwable throwable) {
                                    throw throwable;
                                }
                            }
                            bl = Social.ready();
                            if (n != 0) break block25;
                            if (bl) break block26;
                            break block31;
                            catch (Throwable throwable) {
                                throw throwable;
                            }
                        }
                        WorldShare.toast(a[7]);
                        return;
                    }
                    integratedServer = integratedServer2;
                    if (n != 0) break block27;
                    bl = integratedServer.method_3860();
                }
                if (!bl) {
                    int n2;
                    try (ServerSocket serverSocket = new ServerSocket(0);){
                        n2 = serverSocket.getLocalPort();
                    }
                    catch (Exception exception) {
                        String[] stringArray = a;
                        WorldShare.toast(stringArray[44]);
                        return;
                    }
                    integratedServer = integratedServer2;
                    if (n != 0) break block27;
                    if (integratedServer.method_3763(minecraftClient.field_1761.method_2920(), integratedServer2.method_27728().method_194(), n2)) break block29;
                    WorldShare.toast(a[26]);
                    return;
                }
            }
            lanPort = integratedServer2.method_3756();
            integratedServer = integratedServer2;
        }
        world = integratedServer.method_27728().method_150();
        host = true;
        WorldShare.thread(a[3], WorldShare::lambda$host$3);
    }

    public static void join(UUID uUID, String string, String string2) {
        String[] stringArray;
        Object object;
        block15: {
            String string3;
            block13: {
                block14: {
                    int n;
                    block12: {
                        MinecraftClient minecraftClient;
                        block11: {
                            MinecraftClient minecraftClient2 = MinecraftClient.method_1551();
                            n = Social.a;
                            if (sock != null) {
                                WorldShare.stop();
                            }
                            minecraftClient = minecraftClient2;
                            if (n != 0) break block11;
                            if (minecraftClient.field_1687 == null) break block12;
                            minecraftClient = minecraftClient2;
                        }
                        minecraftClient.method_76795((Screen)new TitleScreen(), false);
                    }
                    string3 = string;
                    if (n != 0) break block13;
                    friendName = string3;
                    if (!string2.startsWith(a[20])) break block14;
                    object = string2.substring(a[20].length());
                    break block15;
                }
                string3 = string;
            }
            stringArray = a;
            object = string3 + stringArray[37];
        }
        friendWorld = object;
        host = false;
        stringArray = a;
        WorldShare.toast(stringArray[17] + string + stringArray[15]);
        WorldShare.thread(stringArray[12], () -> WorldShare.lambda$join$4(uUID));
    }

    public static void stop() {
        boolean bl;
        block18: {
            DatagramSocket datagramSocket;
            block17: {
                boolean bl2;
                int n;
                block16: {
                    block15: {
                        n = Social.a;
                        if (!host || sock == null) break block15;
                        bl2 = true;
                        break block16;
                    }
                    bl2 = false;
                }
                bl = bl2;
                datagramSocket = sock;
                sock = null;
                for (WorldShare$Peer peer : PEERS.values()) {
                    WorldShare.drop(peer);
                    if (n == 0) {
                        if (n == 0) continue;
                        break;
                    }
                    break block17;
                }
                PEERS.clear();
                if (n != 0) break block18;
            }
            if (datagramSocket != null) {
                datagramSocket.close();
            }
            WorldShare.closeQuietly(listen);
            listen = null;
            host = false;
            INVITED.clear();
        }
        if (bl) {
            WorldShare.thread(a[40], WorldShare::lambda$stop$5);
        }
    }

    public static void tick() {
        block6: {
            if (!host || sock == null) break block6;
            if (MinecraftClient.method_1551().method_1576() != null) break block6;
            WorldShare.stop();
        }
    }

    private static JsonObject call(String string, JsonObject jsonObject) throws Exception {
        JsonObject jsonObject2;
        block15: {
            JsonObject jsonObject3;
            block14: {
                int n;
                HttpResponse<String> httpResponse;
                block13: {
                    String[] stringArray;
                    JsonObject jsonObject4;
                    int n2;
                    block11: {
                        block12: {
                            n2 = Social.a;
                            jsonObject4 = jsonObject;
                            if (n2 != 0) break block11;
                            if (jsonObject4 != null) break block12;
                            jsonObject = new JsonObject();
                        }
                        stringArray = a;
                        jsonObject.addProperty(stringArray[43], CosmeticsSync.token());
                        jsonObject4 = jsonObject;
                    }
                    stringArray = a;
                    jsonObject4.addProperty(stringArray[30], mode);
                    httpResponse = Social.post(string, jsonObject.toString());
                    jsonObject3 = JsonParser.parseString((String)httpResponse.body()).getAsJsonObject();
                    n = httpResponse.statusCode();
                    if (n2 != 0) break block13;
                    if (n / 100 == 2) break block14;
                    jsonObject2 = jsonObject3;
                    if (n2 != 0) break block15;
                    n = jsonObject2.has(a[22]) ? 1 : 0;
                }
                if (n == 0) {
                    throw new IllegalStateException(a[2] + httpResponse.statusCode());
                }
            }
            jsonObject2 = jsonObject3;
        }
        return jsonObject2;
    }

    private static void open(JsonObject jsonObject) throws Exception {
        String[] stringArray = a;
        String string = jsonObject.get(stringArray[18]).getAsString();
        int n = string.lastIndexOf(58);
        rendezvous = new InetSocketAddress(InetAddress.getByName(string.substring(0, n)), Integer.parseInt(string.substring(n + 1)));
        ticket = jsonObject.get(stringArray[41]).getAsString();
        DatagramSocket datagramSocket = new DatagramSocket();
        datagramSocket.setReceiveBufferSize(WorldShare.a(23127, 3244362697465128396L));
        datagramSocket.setSendBufferSize(WorldShare.a(7505, 2894464949433770699L));
        lastAck = started = System.currentTimeMillis();
        sock = datagramSocket;
        WorldShare.hello(datagramSocket);
        WorldShare.thread(stringArray[9], () -> WorldShare.lambda$open$6(datagramSocket));
        WorldShare.thread(stringArray[28], () -> WorldShare.lambda$open$7(datagramSocket));
    }

    private static void hello(DatagramSocket datagramSocket) {
        lastHello = System.currentTimeMillis();
        WorldShare.send(datagramSocket, ("A" + ticket).getBytes(StandardCharsets.US_ASCII), rendezvous);
    }

    
    private static void ticker(DatagramSocket var0) {
        var1_1 = Social.a;
        while (WorldShare.sock == var0) {
            block63: {
                block57: {
                    block61: {
                        block62: {
                            block76: {
                                block75: {
                                    block74: {
                                        block59: {
                                            block58: {
                                                block60: {
                                                    block73: {
                                                        block72: {
                                                            block53: {
                                                                block71: {
                                                                    block56: {
                                                                        block54: {
                                                                            block55: {
                                                                                block70: {
                                                                                    block69: {
                                                                                        block68: {
                                                                                            var2_2 = System.currentTimeMillis();
                                                                                            v0 = WorldShare.host;
                                                                                            if (var1_1 != 0) break block53;
                                                                                            if (!v0) // goto
                                                                                            break block68;
                                                                                            catch (InterruptedException v1) {
                                                                                                throw v1;
                                                                                            }
                                                                                        }
                                                                                        v2 = var2_2;
                                                                                        if (var1_1 != 0) break block54;
                                                                                        break block69;
                                                                                        catch (InterruptedException v3) {
                                                                                            throw v3;
                                                                                        }
                                                                                    }
                                                                                    if (v2 - WorldShare.lastHello <= WorldShare.b(7918, 7842892908895593086L)) break block55;
                                                                                    break block70;
                                                                                    catch (InterruptedException v4) {
                                                                                        throw v4;
                                                                                    }
                                                                                }
                                                                                WorldShare.hello(var0);
                                                                            }
                                                                            v6 = var2_2;
                                                                            if (var1_1 != 0) break block56;
                                                                            v2 = v6 - WorldShare.lastAck;
                                                                        }
                                                                        if (v2 <= WorldShare.b(24307, 1245905116584682086L)) break block57;
                                                                        v6 = var2_2;
                                                                    }
                                                                    WorldShare.lastAck = v6;
                                                                    WorldShare.thread(WorldShare.a[3], (Runnable)LambdaMetafactory.metafactory(null, null, null, ()V, lambda$ticker$8(java.net.DatagramSocket ), ()V)((DatagramSocket)var0));
                                                                    if (var1_1 == 0) break block57;
lbl51:

                                                                    v9 = WorldShare.PEERS;
                                                                    if (var1_1 != 0) break block58;
                                                                    break block71;
                                                                    catch (InterruptedException v10) {
                                                                        throw v10;
                                                                    }
                                                                }
                                                                v0 = v9.isEmpty();
                                                            }
                                                            if (!v0) break block60;
                                                            cfr_temp_0 = var2_2 - WorldShare.started - WorldShare.b(19513, 3805939937388605615L);
                                                            v12 = cfr_temp_0 == 0L ? 0 : (cfr_temp_0 < 0L ? -1 : 1);
                                                            if (var1_1 != 0) break block59;
                                                            break block72;
                                                            catch (InterruptedException v13) {
                                                                throw v13;
                                                            }
                                                        }
                                                        if (v12 <= 0) break block60;
                                                        break block73;
                                                        catch (InterruptedException v14) {
                                                            throw v14;
                                                        }
                                                    }
                                                    WorldShare.toast(WorldShare.a[25] + WorldShare.friendName);
                                                    WorldShare.stop();
                                                    return;
                                                }
                                                v9 = WorldShare.PEERS;
                                            }
                                            v12 = v9.isEmpty();
                                        }
                                        if (var1_1 != 0) break block61;
                                        if (v12 != 0) // goto
                                        break block74;
                                        catch (InterruptedException v16) {
                                            throw v16;
                                        }
                                    }
                                    v17 = var2_2;
                                    if (var1_1 != 0) break block62;
                                    break block75;
                                    catch (InterruptedException v18) {
                                        throw v18;
                                    }
                                }
                                if (v17 - WorldShare.started >= WorldShare.b(1465, 125550165029779752L)) break block57;
                                break block76;
                                catch (InterruptedException v19) {
                                    throw v19;
                                }
                            }
                            v17 = var2_2 - WorldShare.lastHello;
                        }
                        v12 = (cfr_temp_1 = v17 - WorldShare.b(21778, 4055659935755086213L)) == 0L ? 0 : (cfr_temp_1 < 0L ? -1 : 1);
                    }
                    if (v12 > 0) {
                        WorldShare.hello(var0);
                    }
                }
                for (WorldShare$Peer var5_5 : WorldShare.PEERS.values()) {
                    block81: {
                        block67: {
                            block65: {
                                block64: {
                                    block66: {
                                        block80: {
                                            block79: {
                                                block78: {
                                                    block77: {
                                                        if (var1_1 != 0) break block63;
                                                        v22 = var5_5;
                                                        if (var1_1 != 0) break block64;
                                                        break block77;
                                                        catch (InterruptedException v23) {
                                                            throw v23;
                                                        }
                                                    }
                                                    if (v22.direct != null) break block66;
                                                    break block78;
                                                    catch (InterruptedException v24) {
                                                        throw v24;
                                                    }
                                                }
                                                cfr_temp_2 = var2_2 - var5_5.since - WorldShare.b(5459, 3652510678357305799L);
                                                v25 = cfr_temp_2 == 0L ? 0 : (cfr_temp_2 < 0L ? -1 : 1);
                                                if (var1_1 != 0) break block65;
                                                break block79;
                                                catch (InterruptedException v26) {
                                                    throw v26;
                                                }
                                            }
                                            if (v25 >= 0) break block66;
                                            break block80;
                                            catch (InterruptedException v27) {
                                                throw v27;
                                            }
                                        }
                                        WorldShare.send(var0, WorldShare.punch('K', var5_5.sid), var5_5.told);
                                    }
                                    v22 = var5_5;
                                }
                                if (var1_1 != 0) break block67;
                                v25 = v22.arq.tick(var2_2);
                            }
                            if (v25 != 0) break block81;
                            v22 = var5_5;
                        }
                        WorldShare.drop(v22);
                    }
                    if (var1_1 == 0) continue;
                }
                try {
                    Thread.sleep(WorldShare.b(7483, 6058124558875943337L));
                }
                catch (InterruptedException var4_4) {
                    return;
                }
            }
            if (var1_1 == 0) continue;
        }
    }

    
    private static void receive(DatagramSocket var0) {
        var2_1 = new byte[2048];
        var1_2 = Social.a;
        var3_3 = new DatagramPacket(var2_1, var2_1.length);
        while (WorldShare.sock == var0) {
            block69: {
                block76: {
                    block75: {
                        block70: {
                            block74: {
                                block73: {
                                    block71: {
                                        block72: {
                                            block68: {
                                                block67: {
                                                    block65: {
                                                        block66: {
                                                            try {
                                                                var3_3.setLength(var2_1.length);
                                                                var0.receive(var3_3);
                                                            }
                                                            catch (Exception var4_5) {
                                                                return;
                                                            }
                                                            var4_4 = var3_3.getLength();
                                                            if (var4_4 < 1) {
                                                                continue;
                                                            }
                                                            var5_6 = (InetSocketAddress)var3_3.getSocketAddress();
                                                            var6_7 = var5_6.equals(WorldShare.rendezvous);
                                                            var7_8 = var2_1[0];
                                                            v1 = var7_8;
                                                            if (var1_2 != 0) break block65;
                                                            if (v1 != 97) break block66;
                                                            v1 = (int)var6_7;
                                                            if (var1_2 != 0) break block65;
                                                            if (v1 == 0) break block66;
                                                            WorldShare.lastAck = System.currentTimeMillis();
                                                            if (var1_2 == 0) continue;
                                                        }
                                                        v1 = var4_4;
                                                    }
                                                    if (v1 < 9) {
                                                        continue;
                                                    }
                                                    var8_9 = Arrays.copyOfRange(var2_1, 1, 9);
                                                    var9_10 = WorldShare.PEERS.get(HexFormat.of().formatHex(var8_9));
                                                    v7 = var7_8;
                                                    if (var1_2 != 0) break block67;
                                                    if (v7 == 80) {
                                                    }
                                                    v7 = (int)var6_7;
                                                }
                                                if (v7 == 0) break block68;
                                                if (var9_10 != null) break block69;
                                                WorldShare.introduced(var0, var8_9, new String(var2_1, 9, var4_4 - 9, StandardCharsets.US_ASCII));
                                                if (var1_2 == 0) break block69;
                                            }
                                            v12 = var9_10;
                                            if (var1_2 != 0) break block70;
                                            if (v12 != null) {
                                            }
                                            v15 = var7_8;
                                            if (var1_2 != 0) break block71;
                                            if (v15 == 75) break block72;
                                            v15 = var7_8;
                                            if (var1_2 != 0) break block71;
                                            if (v15 == 107) {
                                            }
                                        }
                                        v15 = (int)var5_6.getAddress().equals(var9_10.ip);
                                    }
                                    if (var1_2 != 0) break block73;
                                    if (v15 == 0) {
                                        continue;
                                    }
                                    var9_10.direct = var5_6;
                                    if (var1_2 != 0) break block74;
                                    v15 = var7_8;
                                }
                                if (v15 != 75) break block69;
                                WorldShare.send(var0, WorldShare.punch('k', var8_9), var5_6);
                            }
                            if (var1_2 == 0) break block69;
lbl149:

                            v12 = var9_10;
                        }
                        if (v12 == null) break block69;
                        v25 = var7_8;
                        if (var1_2 != 0) break block75;
                        if (v25 != 68) break block69;
                        v25 = (int)var6_7;
                    }
                    if (var1_2 != 0) break block76;
                    if (v25 == 0) {
                    }
                    v25 = (int)var5_6.getAddress().equals(var9_10.ip);
                }
                if (v25 == 0) break block69;
lbl187:

                var9_10.arq.onFrame(var2_1, var4_4);
            }
            if (var1_2 == 0) continue;
        }
    }

    private static void introduced(DatagramSocket datagramSocket, byte[] byArray, String string) {
        WorldShare$Peer worldShare$Peer;
        block12: {
            block11: {
                String[] stringArray = string.split(" ");
                int n = Social.a;
                if (stringArray.length < 2) {
                    return;
                }
                try {
                    worldShare$Peer = new WorldShare$Peer(byArray, new InetSocketAddress(InetAddress.getByName(stringArray[0]), Integer.parseInt(stringArray[1])));
                }
                catch (Exception exception) {
                    return;
                }
                worldShare$Peer.arq = new Arq(byArray, arg_0 -> WorldShare.lambda$introduced$9(datagramSocket, worldShare$Peer, arg_0), arg_0 -> WorldShare.lambda$introduced$10(worldShare$Peer, arg_0));
                if (n != 0) break block11;
                PEERS.put(HexFormat.of().formatHex(byArray), worldShare$Peer);
                if (!host) break block12;
                try {
                    worldShare$Peer.local = new Socket(InetAddress.getLoopbackAddress(), lanPort);
                    worldShare$Peer.local.setTcpNoDelay(true);
                }
                catch (Exception exception) {
                    WorldShare.drop(worldShare$Peer);
                    return;
                }
            }
            String[] stringArray = a;
            WorldShare.thread(stringArray[14], () -> WorldShare.lambda$introduced$11(worldShare$Peer));
            return;
        }
        try {
            ServerSocket serverSocket = new ServerSocket(0, 1, InetAddress.getLoopbackAddress());
            serverSocket.setSoTimeout(15000);
            listen = serverSocket;
            int n = serverSocket.getLocalPort();
            WorldShare.thread(a[39], () -> WorldShare.lambda$introduced$12(worldShare$Peer, serverSocket));
            MinecraftClient minecraftClient = MinecraftClient.method_1551();
            String string2 = friendWorld;
            minecraftClient.execute(() -> WorldShare.lambda$introduced$13(minecraftClient, n, string2));
        }
        catch (Exception exception) {
            WorldShare.stop();
        }
    }

    private static void pump(WorldShare$Peer worldShare$Peer) {
        block12: {
            byte[] byArray = new byte[8192];
            int n = Social.a;
            try {
                Socket socket;
                block11: {
                    Socket socket2 = worldShare$Peer.local;
                    try {
                        int n2;
                        InputStream inputStream = socket2.getInputStream();
                        while ((n2 = inputStream.read(byArray)) > 0) {
                            worldShare$Peer.arq.write(byArray, 0, n2);
                            if (!worldShare$Peer.arq.closed()) continue;
                        }
                    }
                    catch (Throwable throwable) {
                        block13: {
                            if (n != 0 || socket2 == null) break block13;
                            try {
                                socket2.close();
                            }
                            catch (Throwable throwable2) {
                                throwable.addSuppressed(throwable2);
                            }
                        }
                        throw throwable;
                    }
                    socket = socket2;
                    if (n != 0) break block11;
                    if (socket == null) break block12;
                    socket = socket2;
                }
                socket.close();
            }
            catch (Exception exception) {
            }
        }
        WorldShare.drop(worldShare$Peer);
    }

    private static void drop(WorldShare$Peer worldShare$Peer) {
        block12: {
            WorldShare$Peer worldShare$Peer2;
            block11: {
                block10: {
                    int n = Social.a;
                    if (PEERS.remove(HexFormat.of().formatHex(worldShare$Peer.sid)) != null) break block10;
                    worldShare$Peer2 = worldShare$Peer;
                    if (n != 0) break block11;
                    if (!worldShare$Peer2.arq.closed()) break block10;
                    return;
                }
                worldShare$Peer.arq.close();
                worldShare$Peer2 = worldShare$Peer;
            }
            WorldShare.closeQuietly(worldShare$Peer2.local);
            if (host || sock == null) break block12;
            WorldShare.stop();
        }
    }

    private static byte[] punch(char c, byte[] byArray) {
        byte[] byArray2 = new byte[9];
        byArray2[0] = (byte)c;
        System.arraycopy(byArray, 0, byArray2, 1, 8);
        return byArray2;
    }

    private static void send(DatagramSocket datagramSocket, byte[] byArray, InetSocketAddress inetSocketAddress) {
        try {
            datagramSocket.send(new DatagramPacket(byArray, byArray.length, inetSocketAddress));
        }
        catch (Exception exception) {
        }
    }

    
    private static void closeQuietly(AutoCloseable var0) {
        block5: {
            var1_1 = Social.a;
            v0 = var0;
            if (var1_1 == 0) {
                if (v0 != null) break block5;
            }
            return;
        }
        try {
            v0 = var0;
lbl14:

            v0.close();
        }
        catch (Exception var2_2) {
        }
    }

    private static void thread(String string, Runnable runnable) {
        Thread thread = new Thread(runnable, string);
        thread.setDaemon(true);
        thread.start();
    }

    private static void toast(String string) {
        SocialToast.system(a[11], string);
    }

    private static void lambda$introduced$13(MinecraftClient minecraftClient, int n, String string) {
        ConnectScreen.method_36877((Screen)new TitleScreen(), (MinecraftClient)minecraftClient, (ServerAddress)ServerAddress.method_2950((String)(a[1] + n)), (ServerInfo)new ServerInfo(string, string, ServerInfo.ServerType.field_45611), (boolean)false, null);
    }

    private static void lambda$introduced$12(WorldShare$Peer worldShare$Peer, ServerSocket serverSocket) {
        try {
            worldShare$Peer.local = serverSocket.accept();
            worldShare$Peer.local.setTcpNoDelay(true);
        }
        catch (Exception exception) {
            WorldShare.stop();
            return;
        }
        finally {
            WorldShare.closeQuietly(serverSocket);
        }
        WorldShare.pump(worldShare$Peer);
    }

    private static void lambda$introduced$11(WorldShare$Peer worldShare$Peer) {
        WorldShare.pump(worldShare$Peer);
    }

    private static void lambda$introduced$10(WorldShare$Peer worldShare$Peer, byte[] byArray) {
        block5: {
            int n = Social.a;
            try {
                Socket socket;
                block4: {
                    Socket socket2 = worldShare$Peer.local;
                    socket = socket2;
                    if (n != 0) break block4;
                    if (socket == null) break block5;
                    socket = socket2;
                }
                socket.getOutputStream().write(byArray);
            }
            catch (Exception exception) {
                WorldShare.drop(worldShare$Peer);
            }
        }
    }

    private static void lambda$introduced$9(DatagramSocket datagramSocket, WorldShare$Peer worldShare$Peer, byte[] byArray) {
        InetSocketAddress inetSocketAddress;
        byte[] byArray2;
        DatagramSocket datagramSocket2;
        block6: {
            block5: {
                WorldShare$Peer worldShare$Peer2;
                block4: {
                    int n = Social.a;
                    datagramSocket2 = datagramSocket;
                    byArray2 = byArray;
                    worldShare$Peer2 = worldShare$Peer;
                    if (n != 0) break block4;
                    if (worldShare$Peer2.direct == null) break block5;
                    worldShare$Peer2 = worldShare$Peer;
                }
                inetSocketAddress = worldShare$Peer2.direct;
                break block6;
            }
            inetSocketAddress = rendezvous;
        }
        WorldShare.send(datagramSocket2, byArray2, inetSocketAddress);
    }

    private static void lambda$ticker$8(DatagramSocket datagramSocket) {
        try {
            String[] stringArray = a;
            JsonObject jsonObject = WorldShare.call(stringArray[21], null);
            ticket = jsonObject.get(stringArray[35]).getAsString();
            WorldShare.hello(datagramSocket);
        }
        catch (Exception exception) {
        }
    }

    private static void lambda$open$7(DatagramSocket datagramSocket) {
        WorldShare.ticker(datagramSocket);
    }

    private static void lambda$open$6(DatagramSocket datagramSocket) {
        WorldShare.receive(datagramSocket);
    }

    private static void lambda$stop$5() {
        try {
            WorldShare.call(a[16], null);
        }
        catch (Exception exception) {
        }
    }

    private static void lambda$join$4(UUID uUID) {
        block4: {
            int n = Social.a;
            try {
                JsonObject jsonObject;
                block5: {
                    JsonObject jsonObject2 = new JsonObject();
                    String[] stringArray = a;
                    jsonObject2.addProperty(stringArray[6], uUID.toString());
                    jsonObject = WorldShare.call(stringArray[42], jsonObject2);
                    if (n != 0) break block4;
                    if (!jsonObject.has(stringArray[36])) break block5;
                    stringArray = a;
                    WorldShare.toast(jsonObject.get(stringArray[36]).getAsString());
                    return;
                }
                WorldShare.open(jsonObject);
            }
            catch (Exception exception) {
                String[] stringArray = a;
                Aurora.LOG.warn(stringArray[10], (Object)exception.getClass().getSimpleName());
                WorldShare.stop();
                WorldShare.toast(stringArray[31]);
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void lambda$host$3() {
        int n = Social.a;
        try {
            block8: {
                JsonObject jsonObject;
                block9: {
                    String[] stringArray = a;
                    jsonObject = WorldShare.call(stringArray[29], null);
                    if (n != 0) break block8;
                    if (!jsonObject.has(stringArray[36])) break block9;
                    host = false;
                    stringArray = a;
                    WorldShare.toast(jsonObject.get(stringArray[36]).getAsString());
                    return;
                }
                WorldShare.open(jsonObject);
            }
            for (UUID uUID : INVITED) {
                try {
                    if (n != 0) return;
                    WorldShare.send(uUID, true);
                    if (n == 0) continue;
                    return;
                }
                catch (Exception exception) {
                    throw exception;
                    return;
                }
            }
        }
        catch (Exception exception) {
            String[] stringArray = a;
            Aurora.LOG.warn(stringArray[38], (Object)exception.getClass().getSimpleName());
            WorldShare.stop();
            WorldShare.toast(stringArray[8]);
        }
    }

    private static boolean lambda$dismiss$2(UUID uUID, WorldShare$Invite worldShare$Invite) {
        return worldShare$Invite.who().equals(uUID);
    }

    
    private static void lambda$send$1(UUID uUID, boolean bl) {
        int n = Social.a;
        try {
            JsonObject jsonObject2;
            JsonObject jsonObject3 = new JsonObject();
            String[] stringArray = a;
            jsonObject3.addProperty(stringArray[5], uUID.toString());
            jsonObject3.addProperty(stringArray[27], Boolean.valueOf(bl));
            JsonObject jsonObject = jsonObject2 = WorldShare.call(stringArray[13], jsonObject3);
            if (n == 0) {
                if (!jsonObject.has(stringArray[36])) return;
                jsonObject = jsonObject2.get(a[36]);
            }
            WorldShare.toast(jsonObject.getAsString());
            return;
        }
        catch (Exception exception) {
            WorldShare.toast(a[31]);
        }
    }

    private static void lambda$setMode$0() {
        try {
            String[] stringArray = a;
            WorldShare.call(stringArray[21], null);
        }
        catch (Exception exception) {
            WorldShare.toast(a[31]);
        }
    }

    
    static {
        block32: {
            block31: {
                block30: {
                    block29: {
                        block28: {
                            block27: {
                                var21 = new String[45];
                                var19_1 = 0;
                                var18_2 = "2&U7>T\u000eZ\nK{\u0011mg\u0014YTx\u001c\u0005\u0012=R3w\u0010;<T,%[D-&T/3r\u0006\t=\u0012;<T,%[D-&T/3s\u0007\f R&\u0006\u000e(T$2N\u0004\u0012&U7\u0017) A-wS\u0007Z=Ic\u0016O\u001b\u0015;Gc1S\u001b\t=\u00159&S/3TN\u000eiT&6Y\u0001Z\bS18H\b\u0010;<T,%[D-&T/3h\f\u0019?\u0015-&T/3\u001a\u0003\u0015 Hc1[\u0000\u0016,BywA\u0014\u000f3'P*#_I\u000e&\u0006\u00148H\u0005\u001e\u0010;<T,%[D-&T/3p\u0006\u0013'\u0011U(V*xM\u0006\b%Bl>T\u001f\u0013=C\u0010;<T,%[D-&T/3j\u0000\n,\b]:\u000648H\u0005\u001e\u000fU(V*xM\u0006\b%Bl$N\u0006\n\b0&O->T\u000eZ\u0003\u000f-V\u0006\u0013'P*#_\u0006\r&T/3\u0000\u000fU(V*xM\u0006\b%Bl?U\u001a\u000e\u0006\u001e,R\">V\u0006\r&T/3\u0000\u0006\u0014&D,3C\u000f9&S/3TN\u000eiT&6Y\u0001Z\u00179&S/3TN\u000eiI32TI\u000e!Cc U\u001b\u0016-\u0002\u0015'\u0010;<T,%[D-&T/3n\u0000\u0019\"\u000fU(V*xM\u0006\b%Bl?U\u001a\u000e\u0004\u0017&B&\u00159&S/3TN\u000eiT&6Y\u0001Z\bS18H\b\u0006\u0013'P*#_\u0010;<T,%[D-&T/3r\u0006\t=\u0006\u0014&D,3C\u0006\u000e E(2N\u0006\u001e,R\">V\b]:\u000648H\u0005\u001e\u0018-&T/3\u001a\u0001\u0015:R*9]I\u001c(O/2^SZ2[\u0010;<T,%[D-&T/3j\u0000\n,\u0010;<T,%[D-&T/3i\u001d\u00159\u0006\u000e E(2N\u000fU(V*xM\u0006\b%Bl=U\u0000\u0014";
                                var20_3 = "2&U7>T\u000eZ\nK{\u0011mg\u0014YTx\u001c\u0005\u0012=R3w\u0010;<T,%[D-&T/3r\u0006\t=\u0012;<T,%[D-&T/3s\u0007\f R&\u0006\u000e(T$2N\u0004\u0012&U7\u0017) A-wS\u0007Z=Ic\u0016O\u001b\u0015;Gc1S\u001b\t=\u00159&S/3TN\u000eiT&6Y\u0001Z\bS18H\b\u0010;<T,%[D-&T/3h\f\u0019?\u0015-&T/3\u001a\u0003\u0015 Hc1[\u0000\u0016,BywA\u0014\u000f3'P*#_I\u000e&\u0006\u00148H\u0005\u001e\u0010;<T,%[D-&T/3p\u0006\u0013'\u0011U(V*xM\u0006\b%Bl>T\u001f\u0013=C\u0010;<T,%[D-&T/3j\u0000\n,\b]:\u000648H\u0005\u001e\u000fU(V*xM\u0006\b%Bl$N\u0006\n\b0&O->T\u000eZ\u0003\u000f-V\u0006\u0013'P*#_\u0006\r&T/3\u0000\u000fU(V*xM\u0006\b%Bl?U\u001a\u000e\u0006\u001e,R\">V\u0006\r&T/3\u0000\u0006\u0014&D,3C\u000f9&S/3TN\u000eiT&6Y\u0001Z\u00179&S/3TN\u000eiI32TI\u000e!Cc U\u001b\u0016-\u0002\u0015'\u0010;<T,%[D-&T/3n\u0000\u0019\"\u000fU(V*xM\u0006\b%Bl?U\u001a\u000e\u0004\u0017&B&\u00159&S/3TN\u000eiT&6Y\u0001Z\bS18H\b\u0006\u0013'P*#_\u0010;<T,%[D-&T/3r\u0006\t=\u0006\u0014&D,3C\u0006\u000e E(2N\u0006\u001e,R\">V\b]:\u000648H\u0005\u001e\u0018-&T/3\u001a\u0001\u0015:R*9]I\u001c(O/2^SZ2[\u0010;<T,%[D-&T/3j\u0000\n,\u0010;<T,%[D-&T/3i\u001d\u00159\u0006\u000e E(2N\u000fU(V*xM\u0006\b%Bl=U\u0000\u0014".length();
                                var17_4 = 8;
                                var16_5 = -1;
lbl7:

                                while (true) {
                                    v0 = ++var16_5;
                                    v1 = var18_2.substring(v0, v0 + var17_4);
                                    v2 = -1;
                                    break block27;
                                    break;
                                }
lbl12:

                                while (true) {
                                    var21[var19_1++] = v3.intern();
                                    if ((var16_5 += var17_4) < var20_3) {
                                        var17_4 = var18_2.charAt(var16_5);
                                        continue;
                                    }
                                    var18_2 = "\u000e&M&9\u00179&S/3TN\u000eiI32TI\u000e!Cc U\u001b\u0016-";
                                    var20_3 = "\u000e&M&9\u00179&S/3TN\u000eiI32TI\u000e!Cc U\u001b\u0016-".length();
                                    var17_4 = 5;
                                    var16_5 = -1;
lbl21:

                                    while (true) {
                                        v4 = ++var16_5;
                                        v1 = var18_2.substring(v4, v4 + var17_4);
                                        v2 = 0;
                                        break block27;
                                        break;
                                    }
                                    break;
                                }
lbl26:

                                while (true) {
                                    var21[var19_1++] = v3.intern();
                                    if ((var16_5 += var17_4) < var20_3) {
                                        var17_4 = var18_2.charAt(var16_5);
                                        continue;
                                    }
                                    break block28;
                                    break;
                                }
                            }
                            v5 = v1.toCharArray();
                            v6 = v5;
                            v7 = v5.length;
                            var22_6 = 0;
                            do {
                                v6 = v6;
                                v8 = var22_6;
                                v9 = v6[v8];
                                switch (var22_6 % 7) {
                                    case 0: {
                                        v10 = 122;
                                        break;
                                    }
                                    case 1: {
                                        v10 = 73;
                                        break;
                                    }
                                    case 2: {
                                        v10 = 38;
                                        break;
                                    }
                                    case 3: {
                                        v10 = 67;
                                        break;
                                    }
                                    case 4: {
                                        v10 = 87;
                                        break;
                                    }
                                    case 5: {
                                        v10 = 58;
                                        break;
                                    }
                                    default: {
                                        v10 = 105;
                                    }
                                }
                                v6[v8] = (char)(v9 ^ v10);
                                ++var22_6;
lbl65:

                                v7 = v7;
                            } while (v7 > var22_6);
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
                        WorldShare.a = var21;
                        WorldShare.PREFIX = WorldShare.a[20];
                        var8_7 = 1188784862538234052L;
                        var14_8 = new long[2];
                        var11_9 = 0;
                        var12_10 = "\u001d\u0015\u00b1\u00b7\u00ad\f\u001d\b\u001bW\u0004\u00fe\u00802Z\u000f";
                        var13_11 = "\u001d\u0015\u00b1\u00b7\u00ad\f\u001d\b\u001bW\u0004\u00fe\u00802Z\u000f".length();
                        var10_12 = 0;
                        while (true) {
                            break block29;
                            break;
                        }
lbl86:

                        while (true) {
                            var14_8[v11] = (((long)var15_13[0] & 255L) << 56 | ((long)var15_13[1] & 255L) << 48 | ((long)var15_13[2] & 255L) << 40 | ((long)var15_13[3] & 255L) << 32 | ((long)var15_13[4] & 255L) << 24 | ((long)var15_13[5] & 255L) << 16 | ((long)var15_13[6] & 255L) << 8 | (long)var15_13[7] & 255L) ^ var8_7;
                            if (var10_12 < var13_11) continue;
                            break block30;
                            break;
                        }
                    }
                    var15_13 = var12_10.substring(var10_12, var10_12 += 8).getBytes("ISO-8859-1");
                    v11 = var11_9++;
                    while (true)
                }
                WorldShare.b = var14_8;
                WorldShare.c = new Integer[2];
                var0_14 = 7203146566211664618L;
                var6_15 = new long[9];
                var3_16 = 0;
                var4_17 = "7\u00e4\u007f\u00938\u00c0\u001bIP\u00f9\u000e\u0019Y|%A\u000f!:/N8?\u0084bH\u00bc\u0096\u00e4\t\u008cbW'\u00dd\u0096\f\u009b\u00edU[\u00be.\u009a\u0010i\u00d2CQF\u00e4\u00bfi\u00d0\u0000\u00a5";
                var5_18 = "7\u00e4\u007f\u00938\u00c0\u001bIP\u00f9\u000e\u0019Y|%A\u000f!:/N8?\u0084bH\u00bc\u0096\u00e4\t\u008cbW'\u00dd\u0096\f\u009b\u00edU[\u00be.\u009a\u0010i\u00d2CQF\u00e4\u00bfi\u00d0\u0000\u00a5".length();
                var2_19 = 0;
                while (true) {
                    var7_20 = var4_17.substring(var2_19, var2_19 += 8).getBytes("ISO-8859-1");
                    v12 = var6_15;
                    v13 = var3_16++;
                    v14 = ((long)var7_20[0] & 255L) << 56 | ((long)var7_20[1] & 255L) << 48 | ((long)var7_20[2] & 255L) << 40 | ((long)var7_20[3] & 255L) << 32 | ((long)var7_20[4] & 255L) << 24 | ((long)var7_20[5] & 255L) << 16 | ((long)var7_20[6] & 255L) << 8 | (long)var7_20[7] & 255L;
                    v15 = -1;
                    break block31;
                    break;
                }
lbl113:

                while (true) {
                    v12[v13] = v16;
                    if (var2_19 < var5_18) continue;
                    var4_17 = "r\u00bc\u00ef\u00b9a\u00c1P4\u000e\u00b3\u00f0\u00c3D\u0013\u00c1\u001a";
                    var5_18 = "r\u00bc\u00ef\u00b9a\u00c1P4\u000e\u00b3\u00f0\u00c3D\u0013\u00c1\u001a".length();
                    var2_19 = 0;
                    while (true) {
                        var7_20 = var4_17.substring(var2_19, var2_19 += 8).getBytes("ISO-8859-1");
                        v12 = var6_15;
                        v13 = var3_16++;
                        v14 = ((long)var7_20[0] & 255L) << 56 | ((long)var7_20[1] & 255L) << 48 | ((long)var7_20[2] & 255L) << 40 | ((long)var7_20[3] & 255L) << 32 | ((long)var7_20[4] & 255L) << 24 | ((long)var7_20[5] & 255L) << 16 | ((long)var7_20[6] & 255L) << 8 | (long)var7_20[7] & 255L;
                        v15 = 0;
                        break block31;
                        break;
                    }
                    break;
                }
lbl126:

                while (true) {
                    v12[v13] = v16;
                    if (var2_19 < var5_18) continue;
                    break block32;
                    break;
                }
            }
            v16 = v14 ^ var0_14;
            switch (v15) {
                default: {
                    continue;
                }
                case 0:
lbl137:

                continue;
            }
        }
        WorldShare.d = var6_15;
        WorldShare.e = new Long[9];
        WorldShare.PUNCH_MS = WorldShare.b(12464, 3679363629123292195L);
        WorldShare.HELLO_MS = WorldShare.b(24698, 7873778001640234208L);
        WorldShare.JOIN_MS = WorldShare.b(24698, 7873778001640234208L);
        WorldShare.world = "";
        WorldShare.friendName = "";
        WorldShare.friendWorld = "";
        WorldShare.PEERS = new ConcurrentHashMap<String, WorldShare$Peer>();
        WorldShare.mode = WorldShare.a[19];
        WorldShare.INVITED = ConcurrentHashMap.newKeySet();
        WorldShare.invites = List.of();
        WorldShare.DISMISSED = ConcurrentHashMap.newKeySet();
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3F9B;
        if (c[n2] == null) {
            WorldShare.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }

    private static long b(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x7C92) & Short.MAX_VALUE;
        if (e[n2] == null) {
            WorldShare.e[n2] = d[n2] ^ l;
        }
        return e[n2];
    }
}
