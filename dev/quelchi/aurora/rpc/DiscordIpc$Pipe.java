package dev.quelchi.aurora.rpc;

import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.rpc.DiscordIpc;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.StandardProtocolFamily;
import java.net.UnixDomainSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

record DiscordIpc$Pipe(RandomAccessFile file, SocketChannel sock) {
    private static final String[] a;
    private static final long[] b;
    private static final Long[] c;

    static DiscordIpc$Pipe open(int n) throws Exception {
        DiscordIpc$Pipe discordIpc$Pipe;
        block9: {
            boolean bl;
            block11: {
                block10: {
                    boolean bl2 = DiscordIpc.a;
                    if (DiscordIpc.WINDOWS) {
                        return new DiscordIpc$Pipe(new RandomAccessFile(DiscordIpc.pipePath(n), a[0]), null);
                    }
                    SocketChannel socketChannel = SocketChannel.open(StandardProtocolFamily.UNIX);
                    try {
                        socketChannel.connect(UnixDomainSocketAddress.of(DiscordIpc.pipePath(n)));
                        socketChannel.configureBlocking(false);
                    }
                    catch (Exception exception) {
                        socketChannel.close();
                        throw exception;
                    }
                    discordIpc$Pipe = new DiscordIpc$Pipe(null, socketChannel);
                    if (!Module.a) break block9;
                    if (!bl2) break block10;
                    bl = false;
                    break block11;
                }
                bl = true;
            }
            DiscordIpc.a = bl;
        }
        return discordIpc$Pipe;
    }

    void write(byte[] byArray) throws Exception {
        boolean bl;
        block16: {
            DiscordIpc$Pipe discordIpc$Pipe;
            block15: {
                bl = DiscordIpc.a;
                discordIpc$Pipe = this;
                if (bl) break block15;
                if (discordIpc$Pipe.file == null) break block16;
                discordIpc$Pipe = this;
            }
            discordIpc$Pipe.file.write(byArray);
            return;
        }
        ByteBuffer byteBuffer = ByteBuffer.wrap(byArray);
        long l = System.currentTimeMillis() + DiscordIpc$Pipe.a(130, 2265563886068498000L);
        while (true) {
            long l2;
            block18: {
                int n;
                block17: {
                    if (!byteBuffer.hasRemaining()) break;
                    n = this.sock.write(byteBuffer);
                    if (bl) break block17;
                    if (n > 0) {
                        continue;
                    }
                    l2 = System.currentTimeMillis();
                    if (bl) break block18;
                    long l3 = l2 - l;
                    n = l3 == 0L ? 0 : (l3 < 0L ? -1 : 1);
                }
                if (n > 0) {
                    throw new IOException(a[1]);
                }
                l2 = DiscordIpc$Pipe.a(327, 7929822333584474004L);
            }
            Thread.sleep(l2);
            if (bl) break;
        }
    }

    int readSome(byte[] byArray, int n, int n2) throws Exception {
        long l;
        block8: {
            long l2;
            block9: {
                DiscordIpc$Pipe discordIpc$Pipe;
                boolean bl;
                block6: {
                    block7: {
                        bl = DiscordIpc.a;
                        discordIpc$Pipe = this;
                        if (bl) break block6;
                        if (discordIpc$Pipe.sock == null) break block7;
                        return this.sock.read(ByteBuffer.wrap(byArray, n, n2));
                    }
                    discordIpc$Pipe = this;
                }
                l2 = discordIpc$Pipe.file.length() - this.file.getFilePointer();
                long l3 = l2 - 0L;
                l = l3 == 0L ? 0 : (l3 < 0L ? -1 : 1);
                if (bl) break block8;
                if (l > 0) break block9;
                l = 0;
                break block8;
            }
            l = this.file.read(byArray, n, (int)Math.min((long)n2, l2));
        }
        return (int)l;
    }

    
    static {
        block16: {
            block15: {
                block14: {
                    var13 = new String[2];
                    var11_1 = 0;
                    var10_2 = "H\u0003\u0017~\u001d:\u001b'1\u0000\u001a\u0007=\u001783\u0001^T;\u001d)'\rT\u0013";
                    var12_3 = "H\u0003\u0017~\u001d:\u001b'1\u0000\u001a\u0007=\u001783\u0001^T;\u001d)'\rT\u0013".length();
                    var9_4 = 2;
                    var8_5 = -1;
lbl7:

                    while (true) {
                        continue;
                        break;
                    }
lbl9:

                    while (true) {
                        var13[var11_1++] = new String(v0).intern();
                        if ((var8_5 += var9_4) < var12_3) {
                            var9_4 = var10_2.charAt(var8_5);
                            continue;
                        }
                        break block14;
                        break;
                    }
                    v1 = ++var8_5;
                    v2 = var10_2.substring(v1, v1 + var9_4).toCharArray();
                    v0 = v2;
                    v3 = v2.length;
                    var14_6 = 0;
                    do {
                        v0 = v0;
                        v4 = var14_6;
                        v5 = v0[v4];
                        switch (var14_6 % 7) {
                            case 0: {
                                v6 = 58;
                                break;
                            }
                            case 1: {
                                v6 = 116;
                                break;
                            }
                            case 2: {
                                v6 = 73;
                                break;
                            }
                            case 3: {
                                v6 = 120;
                                break;
                            }
                            case 4: {
                                v6 = 72;
                                break;
                            }
                            case 5: {
                                v6 = 67;
                                break;
                            }
                            default: {
                                v6 = 100;
                            }
                        }
                        v0[v4] = (char)(v5 ^ v6);
                        ++var14_6;
lbl48:

                        v3 = v3;
                    } while (v3 > var14_6);
                    while (true)
                }
                DiscordIpc$Pipe.a = var13;
                var0_7 = 1847560872701027841L;
                var6_8 = new long[2];
                var3_9 = 0;
                var4_10 = "\u0006\u00d3<\u008a\u00fbQ\u009f\u0081w\u00af\u00b9\u00b6\u00d3\u0003\u0099\u0090";
                var5_11 = "\u0006\u00d3<\u008a\u00fbQ\u009f\u0081w\u00af\u00b9\u00b6\u00d3\u0003\u0099\u0090".length();
                var2_12 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl63:

                while (true) {
                    var6_8[v7] = (((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L) ^ var0_7;
                    if (var2_12 < var5_11) continue;
                    break block16;
                    break;
                }
            }
            var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
            v7 = var3_9++;
            while (true)
        }
        DiscordIpc$Pipe.b = var6_8;
        DiscordIpc$Pipe.c = new Long[2];
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x7ED2) & Short.MAX_VALUE;
        if (c[n2] == null) {
            DiscordIpc$Pipe.c[n2] = b[n2] ^ l;
        }
        return c[n2];
    }
}
