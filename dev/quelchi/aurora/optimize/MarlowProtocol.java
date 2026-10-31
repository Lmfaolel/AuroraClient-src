package dev.quelchi.aurora.optimize;

import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.OptimizersModule;
import dev.quelchi.aurora.optimize.MarlowProtocol$Challenge;
import dev.quelchi.aurora.optimize.MarlowProtocol$ChallengeResponse;
import dev.quelchi.aurora.optimize.MarlowProtocol$OptOut;
import dev.quelchi.aurora.optimize.MarlowProtocol$OptOutAck;
import dev.quelchi.aurora.optimize.MarlowProtocol$Version;
import dev.quelchi.aurora.optimize.ProjectileOptimizer;
import dev.quelchi.aurora.prediction.Prediction;
import dev.quelchi.aurora.util.BuildInfo;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

public final class MarlowProtocol {
    private static final String NAMESPACE;
    private static final int MAJOR;
    private static final int MINOR = 0;
    private static final int PATCH = 0;
    private static final int LOADER_FABRIC = 0;
    private static final int COMMIT_MAX;
    private static final long BUILD_TIMESTAMP;
    private static volatile boolean optedOut;
    private static final AtomicBoolean NOTIFIED;
    private static volatile boolean announced;
    private static final String[] a;
    private static final long b;

    private MarlowProtocol() {
    }

    private static Identifier chan(String string) {
        return Identifier.method_60655((String)a[2], (String)string);
    }

    public static boolean optedOut() {
        return optedOut;
    }

    
    private static boolean running() {
        boolean bl;
        boolean bl2;
        block7: {
            OptimizersModule optimizersModule;
            block6: {
                OptimizersModule optimizersModule2 = OptimizersModule.get();
                bl2 = ProjectileOptimizer.a;
                optimizersModule = optimizersModule2;
                if (bl2) break block6;
                if (optimizersModule == null) break block7;
                optimizersModule = optimizersModule2;
            }
            bl = optimizersModule.crystals();
            if (bl2) return bl;
            if (bl) return true;
        }
        bl = Prediction.active();
        if (bl2) return bl;
        if (bl) return true;
        return false;
    }

    private static MarlowProtocol$Version version() {
        return new MarlowProtocol$Version(2, 0, 0, false, 0, "", false, BUILD_TIMESTAMP);
    }

    public static void register() {
        block4: {
            boolean bl;
            block6: {
                block5: {
                    PayloadTypeRegistry.configurationS2C().register(MarlowProtocol$OptOut.ID, MarlowProtocol$OptOut.CODEC);
                    PayloadTypeRegistry.playS2C().register(MarlowProtocol$OptOut.ID, MarlowProtocol$OptOut.CODEC);
                    PayloadTypeRegistry.playS2C().register(MarlowProtocol$Challenge.ID, MarlowProtocol$Challenge.CODEC);
                    boolean bl2 = ProjectileOptimizer.a;
                    PayloadTypeRegistry.playC2S().register(MarlowProtocol$OptOutAck.ID, MarlowProtocol$OptOutAck.CODEC);
                    PayloadTypeRegistry.playC2S().register(MarlowProtocol$ChallengeResponse.ID, MarlowProtocol$ChallengeResponse.CODEC);
                    PayloadTypeRegistry.playC2S().register(MarlowProtocol$Version.ID, MarlowProtocol$Version.CODEC);
                    ClientPlayNetworking.registerGlobalReceiver(MarlowProtocol$OptOut.ID, MarlowProtocol::lambda$register$2);
                    ClientPlayNetworking.registerGlobalReceiver(MarlowProtocol$Challenge.ID, MarlowProtocol::lambda$register$3);
                    ClientTickEvents.END_CLIENT_TICK.register(MarlowProtocol::lambda$register$4);
                    ClientPlayConnectionEvents.JOIN.register(MarlowProtocol::lambda$register$5);
                    ClientPlayConnectionEvents.DISCONNECT.register(MarlowProtocol::lambda$register$6);
                    Aurora.LOG.debug(a[0]);
                    if (!Module.a) break block4;
                    if (!bl2) break block5;
                    bl = false;
                    break block6;
                }
                bl = true;
            }
            ProjectileOptimizer.a = bl;
        }
    }

    private static void reply(CustomPayload customPayload) {
        try {
            ClientPlayNetworking.send((CustomPayload)customPayload);
        }
        catch (Exception exception) {
        }
    }

    private static void lambda$register$6(ClientPlayNetworkHandler clientPlayNetworkHandler, MinecraftClient minecraftClient) {
        announced = false;
        optedOut = false;
        NOTIFIED.set(false);
    }

    private static void lambda$register$5(ClientPlayNetworkHandler clientPlayNetworkHandler, PacketSender packetSender, MinecraftClient minecraftClient) {
        boolean bl;
        block10: {
            block11: {
                boolean bl2;
                block8: {
                    block9: {
                        bl2 = ProjectileOptimizer.a;
                        bl = false;
                        if (bl2) break block8;
                        announced = bl;
                        if (minecraftClient == null) break block9;
                        bl = minecraftClient.method_1542();
                        if (bl2) break block8;
                        if (!bl) break block9;
                        return;
                    }
                    bl = MarlowProtocol.running();
                }
                if (bl2) break block10;
                if (bl) break block11;
                return;
            }
            bl = true;
        }
        announced = bl;
        packetSender.sendPacket((CustomPayload)MarlowProtocol.version());
    }

    private static void lambda$register$4(MinecraftClient minecraftClient) {
        boolean bl;
        block18: {
            block19: {
                block14: {
                    boolean bl2;
                    block17: {
                        MinecraftClient minecraftClient2;
                        block16: {
                            block15: {
                                bl2 = ProjectileOptimizer.a;
                                if (announced) break block14;
                                minecraftClient2 = minecraftClient;
                                if (bl2) break block15;
                                if (minecraftClient2.field_1724 == null) break block14;
                                minecraftClient2 = minecraftClient;
                            }
                            if (bl2) break block16;
                            if (minecraftClient2.method_1562() == null) break block14;
                            minecraftClient2 = minecraftClient;
                        }
                        bl = minecraftClient2.method_1542();
                        if (bl2) break block17;
                        if (bl) break block14;
                        bl = MarlowProtocol.running();
                    }
                    if (bl2) break block18;
                    if (bl) break block19;
                }
                return;
            }
            bl = true;
        }
        announced = bl;
        MarlowProtocol.reply(MarlowProtocol.version());
    }

    private static void lambda$register$3(MarlowProtocol$Challenge marlowProtocol$Challenge, ClientPlayNetworking.Context context) {
        if (MarlowProtocol.running()) {
            MarlowProtocol.reply(new MarlowProtocol$ChallengeResponse(marlowProtocol$Challenge.id()));
        }
    }

    private static void lambda$register$2(MarlowProtocol$OptOut marlowProtocol$OptOut, ClientPlayNetworking.Context context) {
        optedOut = true;
        MarlowProtocol.reply(new MarlowProtocol$OptOutAck());
        if (NOTIFIED.compareAndSet(false, true)) {
            MinecraftClient minecraftClient = context.client();
            CompletableFuture.delayedExecutor(b, TimeUnit.SECONDS).execute(() -> MarlowProtocol.lambda$register$1(minecraftClient));
        }
    }

    private static void lambda$register$1(MinecraftClient minecraftClient) {
        minecraftClient.execute(() -> MarlowProtocol.lambda$register$0(minecraftClient));
    }

    private static void lambda$register$0(MinecraftClient minecraftClient) {
        block5: {
            MinecraftClient minecraftClient2;
            block4: {
                boolean bl = ProjectileOptimizer.a;
                minecraftClient2 = minecraftClient;
                if (bl) break block4;
                if (minecraftClient2.field_1724 == null) break block5;
                minecraftClient2 = minecraftClient;
            }
            minecraftClient2.field_1724.method_7353((Text)Text.method_43470((String)a[3]).method_27692(Formatting.field_1061), false);
        }
    }

    
    static {
        block25: {
            block24: {
                block23: {
                    block22: {
                        block21: {
                            var15 = new String[4];
                            var13_1 = 0;
                            var12_2 = "HLtHE~Kj]rMG`\u0011`_&TXf\u001fjNiH\n{\u000ebDuPO{\u000ea\rhLtHE~\bwTuPKe";
                            var14_3 = "HLtHE~Kj]rMG`\u0011`_&TXf\u001fjNiH\n{\u000ebDuPO{\u000ea\rhLtHE~\bwTuPKe".length();
                            var11_4 = 36;
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
                                var12_2 = "hLtHE~\bwTuPKe8HLtHE~Kj]rMG`\u0011`_&@Cz\ngAc@\u0010)\u001fmDu\u0004Yl\u0019sHt\u0004Kz\u0000`I&BE{KlY&KLoE";
                                var14_3 = "hLtHE~\bwTuPKe8HLtHE~Kj]rMG`\u0011`_&@Cz\ngAc@\u0010)\u001fmDu\u0004Yl\u0019sHt\u0004Kz\u0000`I&BE{KlY&KLoE".length();
                                var11_4 = 13;
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
                                    v10 = 5;
                                    break;
                                }
                                case 1: {
                                    v10 = 45;
                                    break;
                                }
                                case 2: {
                                    v10 = 6;
                                    break;
                                }
                                case 3: {
                                    v10 = 36;
                                    break;
                                }
                                case 4: {
                                    v10 = 42;
                                    break;
                                }
                                case 5: {
                                    v10 = 9;
                                    break;
                                }
                                default: {
                                    v10 = 107;
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
                    MarlowProtocol.a = var15;
                    MarlowProtocol.NAMESPACE = MarlowProtocol.a[1];
                    var3_7 = 4529699799723715958L;
                    var2_8 = new long[2];
                    var6_9 = 0;
                    var7_10 = "\"\u00f6\u00d5\u00f5\u00ed\u00b0\u00adt\u00d2\u0015\u0011F\u00ed\u00b0\u00ad^";
                    var8_11 = "\"\u00f6\u00d5\u00f5\u00ed\u00b0\u00adt\u00d2\u0015\u0011F\u00ed\u00b0\u00ad^".length();
                    var5_12 = 0;
                    while (true) {
                        break block23;
                        break;
                    }
lbl86:

                    while (true) {
                        var2_8[v11] = (((long)var9_13[0] & 255L) << 56 | ((long)var9_13[1] & 255L) << 48 | ((long)var9_13[2] & 255L) << 40 | ((long)var9_13[3] & 255L) << 32 | ((long)var9_13[4] & 255L) << 24 | ((long)var9_13[5] & 255L) << 16 | ((long)var9_13[6] & 255L) << 8 | (long)var9_13[7] & 255L) ^ var3_7;
                        if (var5_12 < var8_11) continue;
                        break block24;
                        break;
                    }
                }
                var9_13 = var7_10.substring(var5_12, var5_12 += 8).getBytes("ISO-8859-1");
                v11 = var6_9++;
                while (true)
            }
            MarlowProtocol.COMMIT_MAX = (int)var2_8[1];
            MarlowProtocol.MAJOR = (int)var2_8[0];
            break block25;
lbl102:

            while (true) {
                continue;
                break;
            }
        }
        var0_14 = 3987236643355740866L;
        while (true)
        MarlowProtocol.b = 3987236643355740864L ^ var0_14;
        MarlowProtocol.BUILD_TIMESTAMP = BuildInfo.builtMillis();
        MarlowProtocol.NOTIFIED = new AtomicBoolean();
    }
}
