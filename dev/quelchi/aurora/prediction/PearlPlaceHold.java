package dev.quelchi.aurora.prediction;

import dev.quelchi.aurora.prediction.PearlPlaceHold$Held;
import dev.quelchi.aurora.prediction.PredictionHandshake;
import dev.quelchi.aurora.prediction.ac.InputAudit;
import java.util.ArrayDeque;
import java.util.Deque;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.util.Util;

public final class PearlPlaceHold {
    private static final long MAX_HOLD_MS;
    private static final Deque<PearlPlaceHold$Held> QUEUED;
    private static long holdingUntil;
    private static boolean flushing;
    private static final long[] a;
    private static final Long[] b;

    private PearlPlaceHold() {
    }

    public static void arm() {
        holdingUntil = Util.method_658() + PearlPlaceHold.a(20593, 36627033190585745L);
    }

    public static boolean holds(Packet<?> packet) {
        boolean bl;
        block20: {
            block21: {
                boolean bl2;
                block18: {
                    block19: {
                        block16: {
                            block17: {
                                long l;
                                block14: {
                                    block15: {
                                        bl2 = PredictionHandshake.a;
                                        long l = holdingUntil - 0L;
                                        l = l == 0L ? 0 : (l < 0L ? -1 : 1);
                                        if (bl2) break block14;
                                        if (l == false) break block15;
                                        bl = flushing;
                                        if (bl2) break block16;
                                        if (!bl) break block17;
                                    }
                                    l = 0;
                                }
                                return (boolean)l;
                            }
                            bl = packet instanceof PlayerInteractBlockC2SPacket;
                        }
                        if (bl2) break block18;
                        if (bl) break block19;
                        return false;
                    }
                    long l = Util.method_658() - holdingUntil;
                    bl = l == 0L ? 0 : (l < 0L ? -1 : 1);
                }
                if (bl2) break block20;
                if (bl <= false) break block21;
                PearlPlaceHold.release();
                return false;
            }
            QUEUED.addLast(new PearlPlaceHold$Held(packet, InputAudit.inVanilla()));
            bl = true;
        }
        return bl;
    }

    
    public static void release() {
        holdingUntil = 0L;
        boolean bl = PredictionHandshake.a;
        if (QUEUED.isEmpty()) {
            return;
        }
        ClientPlayNetworkHandler clientPlayNetworkHandler = MinecraftClient.method_1551().method_1562();
        if (bl) return;
        if (clientPlayNetworkHandler == null) {
            QUEUED.clear();
            return;
        }
        flushing = true;
        try {
            do {
                if (QUEUED.isEmpty()) return;
                PearlPlaceHold$Held pearlPlaceHold$Held = QUEUED.pollFirst();
                if (bl) return;
                PearlPlaceHold$Held pearlPlaceHold$Held2 = pearlPlaceHold$Held;
                if (!bl) {
                    if (pearlPlaceHold$Held2.vanilla()) {
                        InputAudit.enterReplay();
                    }
                    try {
                        clientPlayNetworkHandler.method_52787(pearlPlaceHold$Held.packet());
                        if (bl) continue;
                        pearlPlaceHold$Held2 = pearlPlaceHold$Held;
                    }
                    catch (Throwable throwable) {
                        if (!pearlPlaceHold$Held.vanilla()) throw throwable;
                        InputAudit.exitReplay();
                        throw throwable;
                    }
                }
                if (!pearlPlaceHold$Held2.vanilla()) continue;
                InputAudit.exitReplay();
            } while (!bl);
            return;
        }
        finally {
            flushing = false;
        }
    }

    public static void tick() {
        block8: {
            long l;
            block7: {
                boolean bl = PredictionHandshake.a;
                l = holdingUntil;
                if (bl) break block7;
                if (l == 0L) break block8;
                l = Util.method_658();
            }
            if (l > holdingUntil) {
                PearlPlaceHold.release();
            }
        }
    }

    
    static {
        long l = 1931895761324551199L;
        long[] lArray = new long[2];
        int n = 0;
        String string = "\u001aMX\u00e2\"Uzfs\u000e\u00b0#\u0094\u00b08\u00e5";
        int n2 = "\u001aMX\u00e2\"Uzfs\u000e\u00b0#\u0094\u00b08\u00e5".length();
        int n3 = 0;
        do {
            byte[] byArray = string.substring(n3, n3 += 8).getBytes("ISO-8859-1");
            int n4 = n++;
            lArray[n4] = (((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL) ^ l;
        } while (n3 < n2);
        a = lArray;
        b = new Long[2];
        MAX_HOLD_MS = PearlPlaceHold.a(4851, 7620592865942771474L);
        QUEUED = new ArrayDeque<PearlPlaceHold$Held>();
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x5E0) & Short.MAX_VALUE;
        if (b[n2] == null) {
            PearlPlaceHold.b[n2] = a[n2] ^ l;
        }
        return b[n2];
    }
}
