package dev.quelchi.aurora.prediction;

import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.gui.PredictionFx;
import dev.quelchi.aurora.gui.SocialToast;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.PredictionDialog;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.optimize.ProjectileOptimizer;
import dev.quelchi.aurora.prediction.BuildProof;
import dev.quelchi.aurora.prediction.Prediction;
import dev.quelchi.aurora.prediction.PredictionHandshake$AnchorAt;
import dev.quelchi.aurora.prediction.PredictionHandshake$BreakAt;
import dev.quelchi.aurora.prediction.PredictionHandshake$Caps;
import dev.quelchi.aurora.prediction.PredictionHandshake$Challenge;
import dev.quelchi.aurora.prediction.PredictionHandshake$Flag;
import dev.quelchi.aurora.prediction.PredictionHandshake$Hello;
import dev.quelchi.aurora.prediction.PredictionHandshake$Launch;
import dev.quelchi.aurora.prediction.PredictionHandshake$Motion;
import dev.quelchi.aurora.prediction.PredictionHandshake$PearlFix;
import dev.quelchi.aurora.prediction.PredictionHandshake$PearlLanded;
import dev.quelchi.aurora.prediction.PredictionHandshake$Proof;
import dev.quelchi.aurora.prediction.PredictionHandshake$Refused;
import dev.quelchi.aurora.prediction.ac.AntiCheat;
import dev.quelchi.aurora.util.LoaderPayload;
import java.lang.invoke.LambdaMetafactory;
import java.nio.file.Path;
import java.util.HexFormat;
import java.util.List;
import java.util.function.Function;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.C2SPlayChannelEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.common.CustomPayloadC2SPacket;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public final class PredictionHandshake {
    public static final int PROTOCOL_VERSION;
    public static final int EXPLOSION_CONTRACT;
    private static volatile boolean active;
    private static volatile int maxPlacePerSec;
    private static volatile int maxBreakPerSec;
    private static volatile double maxReach;
    private static volatile byte[][] fingerprint;
    private static volatile boolean fingerprinted;
    private static boolean offered;
    private static boolean offerPending;
    private static boolean accepted;
    private static boolean carry;
    private static volatile boolean handing;
    private static int carryIn;
    public static boolean a;
    private static final String[] b;
    private static final long[] c;
    private static final Integer[] d;

    private PredictionHandshake() {
    }

    private static Identifier chan() {
        String[] stringArray = b;
        return Identifier.method_60655((String)stringArray[11], (String)stringArray[9]);
    }

    public static boolean isActive() {
        return active;
    }

    
    private static synchronized byte[][] fingerprint() {
        block10: {
            block8: {
                block11: {
                    var0 = PredictionHandshake.a;
                    v0 = PredictionHandshake.fingerprinted;
                    if (var0) // goto
                    if (!v0) break block8;
                    break block11;
                    catch (Exception v1) {
                        throw v1;
                    }
                }
                return PredictionHandshake.fingerprint;
            }
            try {
                block9: {
                    v0 = LoaderPayload.inMemory();
lbl18:

                    if (!v0) break block9;
                    PredictionHandshake.fingerprint = BuildProof.fingerprint(LoaderPayload.jar());
                    if (!var0) break block10;
                }
                var2_1 = PredictionHandshake.b;
                var1_2 = FabricLoader.getInstance().getModContainer(var2_1[12]).map((Function<ModContainer, Path>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, lambda$fingerprint$0(net.fabricmc.loader.api.ModContainer ), (Lnet/fabricmc/loader/api/ModContainer;)Ljava/nio/file/Path;)()).orElse(null);
                PredictionHandshake.fingerprint = BuildProof.fingerprint(var1_2);
            }
            catch (Exception var1_3) {
                Aurora.LOG.warn(PredictionHandshake.b[2], (Throwable)var1_3);
            }
        }
        PredictionHandshake.fingerprinted = true;
        return PredictionHandshake.fingerprint;
    }

    public static void sendBreakAt(BlockPos blockPos) {
        block4: {
            ClientPlayNetworkHandler clientPlayNetworkHandler;
            block5: {
                boolean bl = a;
                clientPlayNetworkHandler = MinecraftClient.method_1551().method_1562();
                if (bl) break block4;
                if (clientPlayNetworkHandler != null) break block5;
                return;
            }
            clientPlayNetworkHandler.method_52787((Packet)new CustomPayloadC2SPacket((CustomPayload)new PredictionHandshake$BreakAt(blockPos.method_10263(), blockPos.method_10264(), blockPos.method_10260())));
        }
    }

    public static void sendAnchorAt(BlockPos blockPos) {
        PredictionHandshake.sendRaw(new PredictionHandshake$AnchorAt(blockPos.method_10263(), blockPos.method_10264(), blockPos.method_10260()));
    }

    public static void sendFlag(String string, String string2, int n) {
        PredictionHandshake.sendRaw(new PredictionHandshake$Flag(string, string2, n));
    }

    public static void sendMotion(PlayerEntity playerEntity) {
        Vec3d vec3d = playerEntity.method_18798();
        PredictionHandshake.sendRaw(new PredictionHandshake$Motion(vec3d.field_1352, vec3d.field_1351, vec3d.field_1350, playerEntity.method_24828()));
    }

    public static void sendLaunch(Vec3d vec3d) {
        PredictionHandshake.sendRaw(new PredictionHandshake$Launch(vec3d.field_1352, vec3d.field_1351, vec3d.field_1350));
    }

    public static void sendPearlLanded(Vec3d vec3d) {
        PredictionHandshake.sendRaw(new PredictionHandshake$PearlLanded(vec3d.field_1352, vec3d.field_1351, vec3d.field_1350));
    }

    private static void sendRaw(CustomPayload customPayload) {
        block4: {
            ClientPlayNetworkHandler clientPlayNetworkHandler;
            block5: {
                boolean bl = a;
                clientPlayNetworkHandler = MinecraftClient.method_1551().method_1562();
                if (bl) break block4;
                if (clientPlayNetworkHandler != null) break block5;
                return;
            }
            clientPlayNetworkHandler.method_52787((Packet)new CustomPayloadC2SPacket(customPayload));
        }
    }

    public static void register() {
        block4: {
            boolean bl;
            block6: {
                block5: {
                    PayloadTypeRegistry.playC2S().register(PredictionHandshake$Hello.ID, PredictionHandshake$Hello.CODEC);
                    PayloadTypeRegistry.playC2S().register(PredictionHandshake$BreakAt.ID, PredictionHandshake$BreakAt.CODEC);
                    PayloadTypeRegistry.playC2S().register(PredictionHandshake$Launch.ID, PredictionHandshake$Launch.CODEC);
                    PayloadTypeRegistry.playC2S().register(PredictionHandshake$PearlLanded.ID, PredictionHandshake$PearlLanded.CODEC);
                    PayloadTypeRegistry.playC2S().register(PredictionHandshake$Motion.ID, PredictionHandshake$Motion.CODEC);
                    PayloadTypeRegistry.playC2S().register(PredictionHandshake$Flag.ID, PredictionHandshake$Flag.CODEC);
                    PayloadTypeRegistry.playS2C().register(PredictionHandshake$Caps.ID, PredictionHandshake$Caps.CODEC);
                    PayloadTypeRegistry.playC2S().register(PredictionHandshake$Proof.ID, PredictionHandshake$Proof.CODEC);
                    PayloadTypeRegistry.playS2C().register(PredictionHandshake$Challenge.ID, PredictionHandshake$Challenge.CODEC);
                    PayloadTypeRegistry.playC2S().register(PredictionHandshake$AnchorAt.ID, PredictionHandshake$AnchorAt.CODEC);
                    PayloadTypeRegistry.playS2C().register(PredictionHandshake$Refused.ID, PredictionHandshake$Refused.CODEC);
                    PayloadTypeRegistry.playS2C().register(PredictionHandshake$PearlFix.ID, PredictionHandshake$PearlFix.CODEC);
                    ClientPlayNetworking.registerGlobalReceiver(PredictionHandshake$Refused.ID, PredictionHandshake::lambda$register$1);
                    ClientPlayNetworking.registerGlobalReceiver(PredictionHandshake$PearlFix.ID, PredictionHandshake::lambda$register$2);
                    Thread thread = new Thread(PredictionHandshake::fingerprint, b[5]);
                    thread.setDaemon(true);
                    thread.start();
                    ClientPlayNetworking.registerGlobalReceiver(PredictionHandshake$Challenge.ID, PredictionHandshake::lambda$register$3);
                    ClientPlayNetworking.registerGlobalReceiver(PredictionHandshake$Caps.ID, PredictionHandshake::lambda$register$4);
                    ClientPlayConnectionEvents.JOIN.register(PredictionHandshake::lambda$register$5);
                    C2SPlayChannelEvents.REGISTER.register(PredictionHandshake::lambda$register$6);
                    ClientTickEvents.END_CLIENT_TICK.register(PredictionHandshake::lambda$register$7);
                    ClientPlayConnectionEvents.DISCONNECT.register(PredictionHandshake::lambda$register$8);
                    boolean bl2 = a;
                    if (!bl2) break block4;
                    if (!Module.a) break block5;
                    bl = false;
                    break block6;
                }
                bl = true;
            }
            Module.a = bl;
        }
    }

    public static void transferring() {
        handing = true;
    }

    public static void answered() {
        offerPending = false;
    }

    private static void offer(MinecraftClient minecraftClient) {
        block15: {
            boolean bl;
            block13: {
                boolean bl2;
                block14: {
                    block12: {
                        block11: {
                            bl2 = a;
                            bl = offered;
                            if (bl2) break block11;
                            if (bl) break block12;
                            bl = minecraftClient.method_1542();
                        }
                        if (bl2) break block13;
                        if (!bl) break block14;
                    }
                    return;
                }
                offered = true;
                if (bl2) break block15;
                bl = carry;
            }
            if (bl) {
                Aurora.LOG.info(b[1]);
                carryIn = PredictionHandshake.a(3138, 453414612520624576L);
                return;
            }
            offerPending = true;
            Aurora.LOG.info(b[3]);
        }
    }

    public static void accept() {
        PredictionHandshake$Hello predictionHandshake$Hello;
        accepted = true;
        byte[][] byArray = PredictionHandshake.fingerprint();
        PredictionHandshake$Hello predictionHandshake$Hello2 = predictionHandshake$Hello;
        PredictionHandshake$Hello predictionHandshake$Hello3 = predictionHandshake$Hello;
        int n = 10;
        int n2 = 2;
        String string = byArray == null ? "" : HexFormat.of().formatHex(byArray[0]);
        predictionHandshake$Hello2(n, n2, string);
        PredictionHandshake.sendRaw(predictionHandshake$Hello3);
    }

    private static void lambda$register$8(ClientPlayNetworkHandler clientPlayNetworkHandler, MinecraftClient minecraftClient) {
        int n;
        block12: {
            block11: {
                block13: {
                    boolean bl;
                    block10: {
                        active = false;
                        bl = a;
                        offerPending = false;
                        n = 0;
                        if (bl) break block10;
                        carryIn = n;
                        if (!handing) break block11;
                        n = accepted ? 1 : 0;
                    }
                    if (bl) break block12;
                    if (n != 0) break block13;
                    n = carry ? 1 : 0;
                    if (bl) break block12;
                    if (n == 0) break block11;
                }
                n = 1;
                break block12;
            }
            n = 0;
        }
        carry = n;
        handing = false;
        accepted = false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void lambda$register$7(MinecraftClient minecraftClient) {
        MinecraftClient minecraftClient2;
        block23: {
            int n;
            boolean bl;
            block21: {
                block22: {
                    bl = a;
                    n = carryIn;
                    if (bl) break block21;
                    if (n <= 0) break block22;
                    if (minecraftClient.field_1724 == null) break block22;
                    n = carryIn;
                    if (bl) break block21;
                    carryIn = n - 1;
                    if (carryIn != 0) break block22;
                    PredictionHandshake.accept();
                }
                n = offerPending ? 1 : 0;
            }
            if (n == 0) return;
            minecraftClient2 = minecraftClient;
            if (bl) break block23;
            if (minecraftClient2.field_1724 == null) return;
            minecraftClient2 = minecraftClient;
        }
        if (minecraftClient2.field_1755 != null) {
            return;
        }
        if (!Im.available()) return;
        minecraftClient.method_1507((Screen)new PredictionDialog());
        return;
    }

    private static void lambda$register$6(ClientPlayNetworkHandler clientPlayNetworkHandler, PacketSender packetSender, MinecraftClient minecraftClient, List list) {
        if (list.contains(PredictionHandshake.chan())) {
            PredictionHandshake.offer(minecraftClient);
        }
    }

    private static void lambda$register$5(ClientPlayNetworkHandler clientPlayNetworkHandler, PacketSender packetSender, MinecraftClient minecraftClient) {
        offered = false;
        offerPending = false;
        if (ClientPlayNetworking.canSend(PredictionHandshake$Hello.ID)) {
            PredictionHandshake.offer(minecraftClient);
        }
    }

    private static void lambda$register$4(PredictionHandshake$Caps predictionHandshake$Caps, ClientPlayNetworking.Context context) {
        PredictionHandshake$Caps predictionHandshake$Caps2;
        block16: {
            block17: {
                int n;
                block14: {
                    block15: {
                        PredictionHandshake$Caps predictionHandshake$Caps3;
                        boolean bl;
                        block12: {
                            block13: {
                                bl = a;
                                predictionHandshake$Caps3 = predictionHandshake$Caps;
                                if (bl) break block12;
                                if (predictionHandshake$Caps3.protocol() != -1) break block13;
                                active = false;
                                Aurora.LOG.info(b[13]);
                                SocialToast.system(b[7], b[0]);
                                return;
                            }
                            predictionHandshake$Caps3 = predictionHandshake$Caps;
                        }
                        n = predictionHandshake$Caps3.protocol();
                        if (bl) break block14;
                        if (n != 10) break block15;
                        predictionHandshake$Caps2 = predictionHandshake$Caps;
                        if (bl) break block16;
                        if (predictionHandshake$Caps2.contract() == 2) break block17;
                    }
                    n = 0;
                }
                active = n;
                String[] stringArray = b;
                Aurora.LOG.info(stringArray[6], (Object)predictionHandshake$Caps.protocol(), (Object)predictionHandshake$Caps.contract());
                SocialToast.system(stringArray[4], stringArray[8]);
                return;
            }
            maxPlacePerSec = predictionHandshake$Caps.maxPlace();
            maxBreakPerSec = predictionHandshake$Caps.maxBreak();
            predictionHandshake$Caps2 = predictionHandshake$Caps;
        }
        maxReach = predictionHandshake$Caps2.reach();
        active = true;
        AntiCheat.onActivated();
        Aurora.LOG.info(b[10], new Object[]{maxPlacePerSec, maxBreakPerSec, maxReach});
        PredictionFx.play();
    }

    private static void lambda$register$3(PredictionHandshake$Challenge predictionHandshake$Challenge, ClientPlayNetworking.Context context) {
        block4: {
            byte[][] byArray;
            block5: {
                boolean bl = a;
                byArray = PredictionHandshake.fingerprint();
                if (bl) break block4;
                if (byArray != null) break block5;
                return;
            }
            PredictionHandshake.sendRaw(new PredictionHandshake$Proof(BuildProof.answer(predictionHandshake$Challenge.nonce(), byArray[0], byArray[1])));
        }
    }

    private static void lambda$register$2(PredictionHandshake$PearlFix predictionHandshake$PearlFix, ClientPlayNetworking.Context context) {
        ClientWorld clientWorld;
        block5: {
            ClientWorld clientWorld2;
            block6: {
                block4: {
                    clientWorld2 = MinecraftClient.method_1551().field_1687;
                    boolean bl = a;
                    if (!active) break block4;
                    clientWorld = clientWorld2;
                    if (bl) break block5;
                    if (clientWorld != null) break block6;
                }
                return;
            }
            clientWorld = clientWorld2;
        }
        ProjectileOptimizer.pearlPushed(clientWorld, new Vec3d(predictionHandshake$PearlFix.x(), predictionHandshake$PearlFix.y(), predictionHandshake$PearlFix.z()), new Vec3d(predictionHandshake$PearlFix.vx(), predictionHandshake$PearlFix.vy(), predictionHandshake$PearlFix.vz()));
    }

    private static void lambda$register$1(PredictionHandshake$Refused predictionHandshake$Refused, ClientPlayNetworking.Context context) {
        if (active) {
            Prediction.onRefused(new BlockPos(predictionHandshake$Refused.x(), predictionHandshake$Refused.y(), predictionHandshake$Refused.z()));
        }
    }

    private static Path lambda$fingerprint$0(ModContainer modContainer) {
        Path path;
        block6: {
            Object object;
            block4: {
                block5: {
                    boolean bl = a;
                    object = modContainer.getOrigin();
                    if (bl) break block4;
                    if (!object.getPaths().isEmpty()) break block5;
                    path = null;
                    break block6;
                }
                object = modContainer.getOrigin().getPaths().get(0);
            }
            path = (Path)object;
        }
        return path;
    }

    
    static {
        block28: {
            block27: {
                block26: {
                    block25: {
                        var13 = new String[14];
                        var11_1 = 0;
                        var10_2 = "\bKxQO`B1Vs\u0015QbExJj\\R`^=]=Z@e\u0016:@=ANf\u0016+\\oCCq+9LoZTb\u0016(KxQO`B1Vs\u0015EbD*PxQ\u0006l@=K=ANf\u0016,K|[UeS*$\u001bVhYB#X7M=SOmQ=KmGOmBxMuP\u0006BC*VoT\u0006iW*\u00199LoZTb\u0016(KxQO`B1Vs\u0015IeP=KxQ\n\bKxQO`B1Vs\u0012\u0019LoZTb\u0016>PsRCqF*PsA=9LoZTb\f+@sV\u0006n_+T|AEk\u0016pIoZRl\u0016#D1\u0015ElX,K|VR#M%\u00101\u0015UwW!PsR\u0006uW6PqYG\n\bKxQO`B1Vs$\fQtF\u0006pS*OxG\u0006qC6J=T\u0006g_>_xGCmBxOxGUjY6\u0004+@sV;9LoZTb\u0016(KxQO`B1Vs\u0015G`B1Ox\u0015\u000esZ9Zx\u0015]~\u0019+\u0015=WTfW3\u0019fH\tp\u001axKxTEk\u0016#D4\u00069LoZTb";
                        var12_3 = "\bKxQO`B1Vs\u0015QbExJj\\R`^=]=Z@e\u0016:@=ANf\u0016+\\oCCq+9LoZTb\u0016(KxQO`B1Vs\u0015EbD*PxQ\u0006l@=K=ANf\u0016,K|[UeS*$\u001bVhYB#X7M=SOmQ=KmGOmBxMuP\u0006BC*VoT\u0006iW*\u00199LoZTb\u0016(KxQO`B1Vs\u0015IeP=KxQ\n\bKxQO`B1Vs\u0012\u0019LoZTb\u0016>PsRCqF*PsA=9LoZTb\f+@sV\u0006n_+T|AEk\u0016pIoZRl\u0016#D1\u0015ElX,K|VR#M%\u00101\u0015UwW!PsR\u0006uW6PqYG\n\bKxQO`B1Vs$\fQtF\u0006pS*OxG\u0006qC6J=T\u0006g_>_xGCmBxOxGUjY6\u0004+@sV;9LoZTb\u0016(KxQO`B1Vs\u0015G`B1Ox\u0015\u000esZ9Zx\u0015]~\u0019+\u0015=WTfW3\u0019fH\tp\u001axKxTEk\u0016#D4\u00069LoZTb".length();
                        var9_4 = 41;
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
                            var10_2 = "9LoZTb,9LoZTb\u0016(KxQO`B1Vs\u0015Ut_,ZuPB#Y>_=W_#B0\\=FCq@=K";
                            var12_3 = "9LoZTb,9LoZTb\u0016(KxQO`B1Vs\u0015Ut_,ZuPB#Y>_=W_#B0\\=FCq@=K".length();
                            var9_4 = 6;
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
                                v10 = 88;
                                break;
                            }
                            case 1: {
                                v10 = 57;
                                break;
                            }
                            case 2: {
                                v10 = 29;
                                break;
                            }
                            case 3: {
                                v10 = 53;
                                break;
                            }
                            case 4: {
                                v10 = 38;
                                break;
                            }
                            case 5: {
                                v10 = 3;
                                break;
                            }
                            default: {
                                v10 = 54;
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
                PredictionHandshake.b = var13;
                var0_7 = 8169243763579870582L;
                var6_8 = new long[4];
                var3_9 = 0;
                var4_10 = "\u00bc\u00cb9\u001d\u00b0\u0083\u00bc\u00a2i\u00e1lnf\u00ceu\"";
                var5_11 = "\u00bc\u00cb9\u001d\u00b0\u0083\u00bc\u00a2i\u00e1lnf\u00ceu\"".length();
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
                    var4_10 = "\t^u4{\u00d1+jx[\u001b\u008cH\u00d8Y\u0094";
                    var5_11 = "\t^u4{\u00d1+jx[\u001b\u008cH\u00d8Y\u0094".length();
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
        PredictionHandshake.c = var6_8;
        PredictionHandshake.d = new Integer[4];
        PredictionHandshake.PROTOCOL_VERSION = PredictionHandshake.a(26985, 6760244520297202920L);
        PredictionHandshake.EXPLOSION_CONTRACT = PredictionHandshake.a(17877, 4995272175164237910L);
        PredictionHandshake.maxPlacePerSec = PredictionHandshake.a(7048, 203198943668073992L);
        PredictionHandshake.maxBreakPerSec = PredictionHandshake.a(3138, 453414612520624576L);
        PredictionHandshake.maxReach = 6.0;
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6D82;
        if (d[n2] == null) {
            PredictionHandshake.d[n2] = (int)(c[n2] ^ l);
        }
        return d[n2];
    }
}
