package dev.quelchi.aurora.social;

import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.social.Arq$Seg;
import dev.quelchi.aurora.social.Social;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.TreeMap;
import java.util.function.Consumer;

final class Arq {
    static final int HEAD;
    static final int MSS;
    private static final int WINDOW;
    private static final int DATA = 1;
    private static final int FIN;
    private static final long DEAD_MS;
    private static final long IDLE_MS;
    private final byte[] session;
    private final Consumer<byte[]> wire;
    private final Consumer<byte[]> deliver;
    private final TreeMap<Integer, Arq$Seg> unacked = new TreeMap();
    private final TreeMap<Integer, byte[]> early = new TreeMap();
    private final ArrayDeque<byte[]> ready = new ArrayDeque();
    private final Object draining = new Object();
    private int nextSeq;
    private int rcvNxt;
    private double srtt = 100.0;
    private double rttvar = 50.0;
    private long lastHeard = System.currentTimeMillis();
    private long lastSent;
    private boolean closed;
    private static final long[] a;
    private static final Long[] b;

    Arq(byte[] byArray, Consumer<byte[]> consumer, Consumer<byte[]> consumer2) {
        this.session = byArray;
        int n = Social.a;
        this.wire = consumer;
        this.deliver = consumer2;
        if (Module.a) {
            Social.a = ++n;
        }
    }

    
    void write(byte[] byArray, int n, int n2) throws InterruptedException {
        int n3 = Social.a;
        while (n2 > 0) {
            int n4 = Math.min(n2, 1150);
            byte[] byArray2 = new byte[n4];
            System.arraycopy(byArray, n, byArray2, 0, n4);
            n += n4;
            n2 -= n4;
            Arq arq = this;
            synchronized (arq) {
                block16: {
                    block15: {
                        Arq arq2;
                        block14: {
                            while (this.unacked.size() >= 150) {
                                arq2 = this;
                                if (n3 == 0 && n3 == 0) {
                                    if (arq2.closed) break;
                                    this.wait(Arq.a(17027, 1022128619285398119L));
                                    if (n3 == 0) continue;
                                }
                                break block14;
                            }
                            arq2 = this;
                        }
                        if (n3 != 0) break block15;
                        if (!arq2.closed) break block16;
                        arq2 = arq;
                    }
                    return;
                }
                Arq$Seg arq$Seg = new Arq$Seg(this.nextSeq++, byArray2);
                this.unacked.put(arq$Seg.seq, arq$Seg);
                this.send(arq$Seg, System.currentTimeMillis());
                if (n3 == 0) continue;
            }
        }
    }

    
    void onFrame(byte[] var1_1, int var2_2) {
        var3_3 = Social.a;
        if (var2_2 < 26) {
            return;
        }
        var4_4 = ByteBuffer.wrap(var1_1, 0, var2_2);
        var4_4.position(9);
        var5_5 = var4_4.get();
        var6_6 = var4_4.getInt();
        var7_7 = var4_4.getInt();
        var8_8 = var4_4.getLong();
        var10_9 = this;
        synchronized (var10_9) {
            block21: {
                block22: {
                    block19: {
                        block24: {
                            block23: {
                                block20: {
                                    this.lastHeard = var11_10 = System.currentTimeMillis();
                                    if (var3_3 != 0) break block20;
                                    if ((var5_5 & 2) != 0) {
                                        this.closed = true;
                                        this.notifyAll();
                                        return;
                                    }
                                    v0 = this;
                                    if (var3_3 != 0) break block21;
                                    v0.acked(var7_7, var8_8, var11_10);
                                }
                                if ((var5_5 & 1) == 0) break block22;
                                v1 = var6_6;
                                if (var3_3 != 0) break block23;
                                if (v1 < this.rcvNxt) break block24;
                                v2 = this.early;
                                if (var3_3 != 0) // goto
                                v1 = (int)v2.containsKey(var6_6);
                            }
                            if (v1 == 0) {
                                var13_12 = new byte[var2_2 - 26];
                                System.arraycopy(var1_1, 26, var13_12, 0, var13_12.length);
                                this.early.put(var6_6, var13_12);
                            }
                        }
                        do {
                            v2 = this.early.remove(this.rcvNxt);
lbl39:

                            if ((var13_12 = (byte[])v2) == null) break;
                            v3 = this;
                            if (var3_3 != 0) break block19;
                            v3.ready.add(var13_12);
                            ++this.rcvNxt;
                        } while (var3_3 == 0);
                        this.wire.accept(this.frame(0, 0, null));
                        v3 = this;
                    }
                    v3.lastSent = var11_10;
                }
                v0 = var10_9;
            }
            var10_9 = this.draining;
            synchronized (var10_9) {
                while ((var11_11 = this.nextReady()) != null) {
                    v4 = this.deliver;
                    if (var3_3 != 0) return;
                    v4.accept(var11_11);
                    if (var3_3 == 0) continue;
                }
                v4 = var10_9;
                return;
            }
        }
    }

    private synchronized byte[] nextReady() {
        return this.ready.poll();
    }

    
    private void acked(int var1_1, long var2_2, long var4_3) {
        block44: {
            var7_4 = -1;
            var8_5 = this.unacked.entrySet().iterator();
            var6_6 = Social.a;
            while (var8_5.hasNext()) {
                block43: {
                    block42: {
                        block40: {
                            block41: {
                                block39: {
                                    block34: {
                                        block37: {
                                            block35: {
                                                block38: {
                                                    block36: {
                                                        var9_7 = var8_5.next().getValue();
                                                        v0 = var9_7.seq - var1_1 - 1;
                                                        if (var6_6 != 0) // goto
                                                        var10_8 = v0;
                                                        v1 = var9_7.seq;
                                                        if (var6_6 != 0) break block34;
                                                        if (v1 < var1_1) break block35;
                                                        v1 = var10_8;
                                                        if (var6_6 != 0) break block36;
                                                        if (v1 < 0) break block37;
                                                        v1 = var10_8;
                                                    }
                                                    if (var6_6 != 0) break block38;
                                                    if (v1 >= 64) break block37;
                                                    cfr_temp_0 = (var2_2 >>> var10_8 & 1L) - 0L;
                                                    v1 = cfr_temp_0 == 0L ? 0 : (cfr_temp_0 < 0L ? -1 : 1);
                                                }
                                                if (var6_6 != 0) break block34;
                                                if (v1 == 0) break block37;
                                            }
                                            v1 = 1;
                                            break block34;
                                        }
                                        v1 = 0;
                                    }
                                    var11_9 = v1;
                                    v8 = var11_9;
                                    if (var6_6 != 0) break block39;
                                    if (v8 == 0) {
                                        continue;
                                    }
                                    v8 = var9_7.tries;
                                }
                                if (var6_6 != 0) break block40;
                                if (v8 != 1) break block41;
                                this.rtt(var4_3 - var9_7.sentAt);
                            }
                            v12 = var9_7;
                            if (var6_6 != 0) break block42;
                            v8 = v12.seq;
                        }
                        if (v8 <= var7_4) break block43;
                        v12 = var9_7;
                    }
                    var7_4 = v12.seq;
                }
                var8_5.remove();
                if (var6_6 == 0) continue;
            }
            var8_5 = this.unacked.values().iterator();
            do {
                block45: {
                    v0 = var8_5.hasNext();
lbl99:

                    if (v0 == 0) break;
                    var9_7 = (Arq$Seg)var8_5.next();
                    if (var6_6 != 0) break block44;
                    v15 = var9_7.seq;
                    if (var6_6 != 0) break block45;
                    if (v15 >= var7_4) {
                        break;
                    }
                    cfr_temp_1 = (double)(var4_3 - var9_7.sentAt) - this.srtt;
                    v15 = cfr_temp_1 == 0.0 ? 0 : (cfr_temp_1 > 0.0 ? 1 : -1);
                }
                if (v15 <= 0) continue;
                this.send(var9_7, var4_3);
            } while (var6_6 == 0);
            this.notifyAll();
        }
    }

    private void rtt(long l) {
        this.rttvar = 0.75 * this.rttvar + 0.25 * Math.abs(this.srtt - (double)l);
        this.srtt = 0.875 * this.srtt + 0.125 * (double)l;
    }

    synchronized boolean tick(long l) {
        long l2;
        block23: {
            block24: {
                long l3;
                long l4;
                int n;
                block21: {
                    block20: {
                        boolean bl;
                        block19: {
                            block17: {
                                block18: {
                                    n = Social.a;
                                    bl = this.closed;
                                    if (n != 0) break block17;
                                    if (!bl) break block18;
                                    return false;
                                }
                                long l5 = l - this.lastHeard - Arq.a(5894, 8600927265258608609L);
                                bl = l5 == 0L ? 0 : (l5 < 0L ? -1 : 1);
                            }
                            if (n != 0) break block19;
                            if (bl <= false) break block20;
                            this.closed = true;
                            this.notifyAll();
                            bl = false;
                        }
                        return bl;
                    }
                    double d = Math.max(40.0, Math.min(1000.0, this.srtt + 4.0 * this.rttvar));
                    for (Arq$Seg arq$Seg : this.unacked.values()) {
                        block22: {
                            long l3 = l;
                            l3 = arq$Seg.sentAt;
                            if (n != 0) break block21;
                            if (!((double)(l4 - l3) > Math.min(2000.0, d * (double)(1 << Math.min(arq$Seg.tries - 1, 5))))) break block22;
                            this.send(arq$Seg, l);
                        }
                        if (n == 0) continue;
                    }
                    long l3 = l - this.lastSent;
                    l3 = Arq.a(22265, 2323793934748636696L);
                }
                long l2 = l4 - l3;
                l2 = l2 == 0L ? 0 : (l2 < 0L ? -1 : 1);
                if (n != 0) break block23;
                if (l2 <= 0) break block24;
                this.wire.accept(this.frame(0, 0, null));
                this.lastSent = l;
            }
            l2 = 1;
        }
        return (boolean)l2;
    }

    synchronized boolean closed() {
        return this.closed;
    }

    synchronized void close() {
        Arq arq;
        int n;
        block5: {
            block6: {
                n = Social.a;
                arq = this;
                if (n != 0) break block5;
                if (!arq.closed) break block6;
                return;
            }
            this.closed = true;
            this.notifyAll();
            arq = this;
        }
        byte[] byArray = arq.frame(2, 0, null);
        for (int i = 0; i < 3; ++i) {
            this.wire.accept(byArray);
            if (n == 0) continue;
        }
    }

    private void send(Arq$Seg arq$Seg, long l) {
        arq$Seg.sentAt = l;
        ++arq$Seg.tries;
        this.lastSent = l;
        this.wire.accept(this.frame(1, arq$Seg.seq, arq$Seg.data));
    }

    private byte[] frame(int n, int n2, byte[] byArray) {
        Object object;
        block20: {
            Object object2;
            block21: {
                int n3;
                int n4;
                int n5;
                long l;
                block15: {
                    byte[] byArray2;
                    block18: {
                        block19: {
                            l = 0L;
                            n5 = Social.a;
                            object2 = this.early.keySet().iterator();
                            while (object2.hasNext()) {
                                int n6;
                                int n7;
                                block16: {
                                    int n8;
                                    n4 = n8 = object2.next().intValue();
                                    n3 = this.rcvNxt;
                                    if (n5 != 0) break block15;
                                    n7 = n4 - n3 - 1;
                                    n6 = n7;
                                    if (n5 != 0) break block16;
                                    if (n6 >= 64) {
                                        break;
                                    }
                                    n6 = n7;
                                }
                                if (n6 >= 0) {
                                    l |= 1L << n7;
                                }
                                if (n5 == 0) continue;
                            }
                            n4 = 26;
                            byArray2 = byArray;
                            if (n5 != 0) break block18;
                            if (byArray2 != null) break block19;
                            n3 = 0;
                            break block15;
                        }
                        byArray2 = byArray;
                    }
                    n3 = byArray2.length;
                }
                object2 = ByteBuffer.allocate(n4 + n3);
                object = object2;
                if (n5 != 0) break block20;
                ((ByteBuffer)object).put((byte)68).put(this.session).put((byte)n).putInt(n2).putInt(this.rcvNxt).putLong(l);
                if (byArray == null) break block21;
                ((ByteBuffer)object2).put(byArray);
            }
            object = object2;
        }
        return ((ByteBuffer)object).array();
    }

    
    static {
        block17: {
            block16: {
                block15: {
                    block14: {
                        var9 = 2929048782351440201L;
                        var8_1 = new long[4];
                        var12_2 = 0;
                        var13_3 = "\u0082\u0099\u009b\u0083I\u00d9\u0019\u00df\u0016=\u00aa=I\u00d9\u0019K";
                        var14_4 = "\u0082\u0099\u009b\u0083I\u00d9\u0019\u00df\u0016=\u00aa=I\u00d9\u0019K".length();
                        var11_5 = 0;
                        while (true) {
                            var15_6 = var13_3.substring(var11_5, var11_5 += 8).getBytes("ISO-8859-1");
                            v0 = var8_1;
                            v1 = var12_2++;
                            v2 = ((long)var15_6[0] & 255L) << 56 | ((long)var15_6[1] & 255L) << 48 | ((long)var15_6[2] & 255L) << 40 | ((long)var15_6[3] & 255L) << 32 | ((long)var15_6[4] & 255L) << 24 | ((long)var15_6[5] & 255L) << 16 | ((long)var15_6[6] & 255L) << 8 | (long)var15_6[7] & 255L;
                            v3 = -1;
                            break block14;
                            break;
                        }
lbl14:

                        while (true) {
                            v0[v1] = v4;
                            if (var11_5 < var14_4) continue;
                            var13_3 = "[\u0007\u009d\u00c5I\u00d9\u001d7\u00c8\u00ff\u0082\u00f9I\u00d9\u0019S";
                            var14_4 = "[\u0007\u009d\u00c5I\u00d9\u001d7\u00c8\u00ff\u0082\u00f9I\u00d9\u0019S".length();
                            var11_5 = 0;
                            while (true) {
                                var15_6 = var13_3.substring(var11_5, var11_5 += 8).getBytes("ISO-8859-1");
                                v0 = var8_1;
                                v1 = var12_2++;
                                v2 = ((long)var15_6[0] & 255L) << 56 | ((long)var15_6[1] & 255L) << 48 | ((long)var15_6[2] & 255L) << 40 | ((long)var15_6[3] & 255L) << 32 | ((long)var15_6[4] & 255L) << 24 | ((long)var15_6[5] & 255L) << 16 | ((long)var15_6[6] & 255L) << 8 | (long)var15_6[7] & 255L;
                                v3 = 0;
                                break block14;
                                break;
                            }
                            break;
                        }
lbl27:

                        while (true) {
                            v0[v1] = v4;
                            if (var11_5 < var14_4) continue;
                            break block15;
                            break;
                        }
                    }
                    v4 = v2 ^ var9;
                    switch (v3) {
                        default: {
                            continue;
                        }
                        case 0:
lbl38:

                        continue;
                    }
                }
                Arq.FIN = (int)var8_1[1];
                Arq.MSS = (int)var8_1[2];
                Arq.WINDOW = (int)var8_1[0];
                Arq.HEAD = (int)var8_1[3];
                var0_7 = 1318200130325652162L;
                var6_8 = new long[5];
                var3_9 = 0;
                var4_10 = "lw\u00c7\u0006\u0098\u00a5z\u0011\u001cdd\u0098\u009d\u00ab`\u0097e\u0017\u0091\u00cf\u00feY{\u0003";
                var5_11 = "lw\u00c7\u0006\u0098\u00a5z\u0011\u001cdd\u0098\u009d\u00ab`\u0097e\u0017\u0091\u00cf\u00feY{\u0003".length();
                var2_12 = 0;
                while (true) {
                    var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
                    v5 = var6_8;
                    v6 = var3_9++;
                    v7 = ((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L;
                    v8 = -1;
                    break block16;
                    break;
                }
lbl58:

                while (true) {
                    v5[v6] = v9;
                    if (var2_12 < var5_11) continue;
                    var4_10 = ";\u0093\u00f4\u0012\u00b2\n \u00922t\u00f6ME\ts\n";
                    var5_11 = ";\u0093\u00f4\u0012\u00b2\n \u00922t\u00f6ME\ts\n".length();
                    var2_12 = 0;
                    while (true) {
                        var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
                        v5 = var6_8;
                        v6 = var3_9++;
                        v7 = ((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L;
                        v8 = 0;
                        break block16;
                        break;
                    }
                    break;
                }
lbl71:

                while (true) {
                    v5[v6] = v9;
                    if (var2_12 < var5_11) continue;
                    break block17;
                    break;
                }
            }
            v9 = v7 ^ var0_7;
            switch (v8) {
                default: {
                    continue;
                }
                case 0:
lbl82:

                continue;
            }
        }
        Arq.a = var6_8;
        Arq.b = new Long[5];
        Arq.IDLE_MS = Arq.a(1382, 3015375568568124800L);
        Arq.DEAD_MS = Arq.a(5654, 9096416988330858227L);
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0xE5) & Short.MAX_VALUE;
        if (b[n2] == null) {
            Arq.b[n2] = a[n2] ^ l;
        }
        return b[n2];
    }
}
