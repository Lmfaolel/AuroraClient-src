package dev.quelchi.aurora.prediction.ac;

import dev.quelchi.aurora.prediction.ac.InputAudit;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.lwjgl.system.JNI;
import org.lwjgl.system.Library;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.Platform;
import org.lwjgl.system.SharedLibrary;

final class Win32Memory {
    private static final int MEM_COMMIT;
    private static final int MEM_PRIVATE;
    private static final int MEM_IMAGE;
    private static final int PAGE_GUARD;
    private static final int PAGE_NOACCESS = 1;
    private static final int TH32CS_SNAPTHREAD;
    private static final int THREAD_QUERY_INFORMATION;
    private static final int THREAD_QUERY_SET_WIN32_START_ADDRESS;
    private static final long TOP;
    private static SharedLibrary kernel32;
    private static SharedLibrary ntdll;
    private static long virtualQuery;
    private static long readMemory;
    private static long snapshot;
    private static long threadFirst;
    private static long threadNext;
    private static long openThread;
    private static long closeHandle;
    private static long processId;
    private static long queryThread;
    private static final String[] a;
    private static final long[] b;
    private static final Integer[] c;
    private static final long[] d;
    private static final Long[] e;

    private Win32Memory() {
    }

    
    static String scan() {
        int n = InputAudit.a;
        long l = virtualQuery;
        long l2 = Win32Memory.b(504, 7757968344017301995L);
        if (n == 0) {
            if (l == l2) {
                Win32Memory.lookup();
            }
            l = virtualQuery;
            l2 = 0L;
        }
        if (l == l2) {
            return null;
        }
        try {
            String string2;
            String string = string2 = Win32Memory.foreignThread();
            if (n != 0) return string;
            if (string != null) {
                string = string2;
                return string;
            }
            string = Win32Memory.peInPrivateMemory();
            return string;
        }
        catch (Throwable throwable) {
            return null;
        }
    }

    /*
     * Exception decompiling
     */
    private static String foreignThread() {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [4[TRYBLOCK]], but top level block is 44[WHILELOOP]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:845)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1042)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:929)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:73)
         *     at org.benf.cfr.reader.Main.main(Main.java:49)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    
    private static String peInPrivateMemory() {
        MemoryStack memoryStack;
        String string;
        HashSet<Long> hashSet = new HashSet<Long>();
        HashMap<Long, long[]> hashMap = new HashMap<Long, long[]>();
        int n = InputAudit.a;
        MemoryStack memoryStack2 = MemoryStack.stackPush();
        try {
            int n2;
            ByteBuffer byteBuffer = memoryStack2.calloc(48);
            long l = Win32Memory.b(5946, 1337825242135062304L);
            while (l < Win32Memory.b(32402, 3262493727829495433L)) {
                block58: {
                    long l2;
                    int n3;
                    long l3;
                    long l4;
                    block54: {
                        block57: {
                            block55: {
                                int n4;
                                int n5;
                                block56: {
                                    long l5 = l;
                                    if (n == 0) {
                                        if (!Win32Memory.query(l5, byteBuffer)) break;
                                        l5 = byteBuffer.getLong(0);
                                    }
                                    l4 = l5;
                                    long l52 = byteBuffer.getLong(8);
                                    l3 = byteBuffer.getLong(24);
                                    n2 = byteBuffer.getInt(32);
                                    n5 = byteBuffer.getInt(36);
                                    int n6 = byteBuffer.getInt(40);
                                    n3 = n2;
                                    if (n != 0) break block54;
                                    if (n3 != 4096) break block55;
                                    n3 = n6;
                                    if (n != 0) break block54;
                                    if (n3 != Win32Memory.a(16433, 6609761124369462694L)) break block55;
                                    n4 = n5;
                                    if (n != 0) break block56;
                                    if (Win32Memory.executable(n4)) {
                                        hashSet.add(l52);
                                    }
                                    l2 = l4;
                                    if (n != 0) break block57;
                                    long l6 = l2 - l52;
                                    n4 = l6 == 0L ? 0 : (l6 < 0L ? -1 : 1);
                                }
                                if (n4 == 0) {
                                    hashMap.put(l4, new long[]{n5, l3});
                                }
                            }
                            l2 = l3;
                        }
                        if (n != 0) break block58;
                        long l7 = l2 - 0L;
                        n3 = l7 == 0L ? 0 : (l7 < 0L ? -1 : 1);
                    }
                    if (n3 <= 0) break;
                    l2 = l = l4 + l3;
                }
                if (n == 0) continue;
            }
            ByteBuffer byteBuffer2 = memoryStack2.calloc(1024);
            ByteBuffer byteBuffer3 = memoryStack2.calloc(8);
            Iterator iterator = hashSet.iterator();
            while (iterator.hasNext()) {
                ByteBuffer byteBuffer4;
                long l9;
                block60: {
                    int n5;
                    block59: {
                        long[] lArray2;
                        l9 = (Long)iterator.next();
                        if (n != 0) return null;
                        long[] lArray = lArray2 = (long[])hashMap.get(l9);
                        if (n == 0) {
                            if (lArray == null) continue;
                            lArray = lArray2;
                        }
                        long l8 = lArray[0];
                        if (n == 0) {
                            if (!Win32Memory.readable((int)l8)) continue;
                            l8 = lArray2[1];
                        }
                        long l10 = Win32Memory.b(30960, 1049482637761939689L);
                        if (n == 0) {
                            if (l8 < l10) continue;
                            l8 = Win32Memory.b(504, 7757968344017301995L);
                            l10 = l9;
                        }
                        n5 = JNI.callPPPPPI((long)l8, (long)l10, (long)MemoryUtil.memAddress((ByteBuffer)byteBuffer2), (long)Win32Memory.b(28739, 6437558790435340379L), (long)MemoryUtil.memAddress((ByteBuffer)byteBuffer3), (long)readMemory);
                        if (n != 0) break block59;
                        if (n5 == 0) continue;
                        byteBuffer4 = byteBuffer2;
                        if (n != 0) break block60;
                        n5 = byteBuffer4.getShort(0);
                    }
                    if (n5 != 23117) continue;
                    byteBuffer4 = byteBuffer2;
                }
                int n6 = n2 = byteBuffer4.getInt(60);
                if (n == 0) {
                    if (n6 <= 0) break block52;
                    n6 = n2;
                }
                if (n == 0) {
                    if (n6 >= 1020) break block52;
                    n6 = byteBuffer2.getInt(n2);
                }
                if (n6 != 17744) break block52;
                string = a[7] + Long.toHexString(l9);
                memoryStack = memoryStack2;
                if (n != 0) break block53;
            }
        }
        catch (Throwable throwable) {
            if (n != 0) throw throwable;
            if (memoryStack2 == null) throw throwable;
            try {
                memoryStack2.close();
                throw throwable;
            }
            catch (Throwable throwable2) {
                throwable.addSuppressed(throwable2);
            }
            throw throwable;
        }
        {
            block52: {
                block53: {
                    if (memoryStack == null) return string;
                    memoryStack = memoryStack2;
                }
                memoryStack.close();
                return string;
            }
            if (n == 0) continue;
        }
        MemoryStack memoryStack3 = memoryStack2;
        if (n == 0) {
            if (memoryStack3 == null) return null;
            memoryStack3 = memoryStack2;
        }
        memoryStack3.close();
        return null;
    }

    private static boolean query(long l, ByteBuffer byteBuffer) {
        long l2;
        block2: {
            block3: {
                int n = InputAudit.a;
                long l3 = JNI.callPPPP((long)l, (long)MemoryUtil.memAddress((ByteBuffer)byteBuffer), (long)Win32Memory.b(4709, 8774668109243114104L), (long)virtualQuery) - 0L;
                l2 = l3 == 0L ? 0 : (l3 < 0L ? -1 : 1);
                if (n != 0) break block2;
                if (l2 == false) break block3;
                l2 = 1;
                break block2;
            }
            l2 = 0;
        }
        return (boolean)l2;
    }

    private static boolean executable(int n) {
        int n2;
        block10: {
            block9: {
                int n3;
                block8: {
                    int n4 = InputAudit.a;
                    n2 = n;
                    n3 = 240;
                    if (n4 != 0) break block8;
                    if ((n2 & n3) == 0) break block9;
                    n2 = n;
                    if (n4 != 0) break block10;
                    n3 = 256;
                }
                if ((n2 & n3) != 0) break block9;
                n2 = 1;
                break block10;
            }
            n2 = 0;
        }
        return n2 != 0;
    }

    private static boolean readable(int n) {
        int n2;
        block10: {
            block9: {
                int n3;
                block8: {
                    int n4 = InputAudit.a;
                    n2 = n;
                    n3 = 257;
                    if (n4 != 0) break block8;
                    if ((n2 & n3) != 0) break block9;
                    n2 = n;
                    if (n4 != 0) break block10;
                    n3 = 238;
                }
                if ((n2 & n3) == 0) break block9;
                n2 = 1;
                break block10;
            }
            n2 = 0;
        }
        return n2 != 0;
    }

    
    private static void lookup() {
        block40: {
            block38: {
                block48: {
                    var0 = InputAudit.a;
                    v0 = 0L;
                    if (var0 != 0) // goto
                    Win32Memory.virtualQuery = v0;
                    if (Platform.get() == Platform.WINDOWS) break block38;
                    break block48;
                    catch (Throwable v1) {
                        throw v1;
                    }
                }
                return;
            }
            try {
                block47: {
                    block46: {
                        block56: {
                            block55: {
                                block45: {
                                    block54: {
                                        block44: {
                                            block53: {
                                                block43: {
                                                    block52: {
                                                        block42: {
                                                            block41: {
                                                                block51: {
                                                                    block50: {
                                                                        block39: {
                                                                            block49: {
                                                                                var3_1 = Win32Memory.a;
                                                                                Win32Memory.kernel32 = Library.loadNative(Win32Memory.class, (String)var3_1[9], (String)var3_1[8]);
                                                                                Win32Memory.ntdll = Library.loadNative(Win32Memory.class, (String)var3_1[0], (String)var3_1[10]);
                                                                                Win32Memory.readMemory = Win32Memory.kernel32.getFunctionAddress((CharSequence)var3_1[13]);
                                                                                Win32Memory.snapshot = Win32Memory.kernel32.getFunctionAddress((CharSequence)var3_1[4]);
                                                                                Win32Memory.threadFirst = Win32Memory.kernel32.getFunctionAddress((CharSequence)var3_1[12]);
                                                                                Win32Memory.threadNext = Win32Memory.kernel32.getFunctionAddress((CharSequence)var3_1[11]);
                                                                                Win32Memory.openThread = Win32Memory.kernel32.getFunctionAddress((CharSequence)var3_1[6]);
                                                                                Win32Memory.closeHandle = Win32Memory.kernel32.getFunctionAddress((CharSequence)var3_1[2]);
                                                                                Win32Memory.processId = Win32Memory.kernel32.getFunctionAddress((CharSequence)var3_1[5]);
                                                                                Win32Memory.queryThread = Win32Memory.ntdll.getFunctionAddress((CharSequence)var3_1[3]);
                                                                                v0 = Win32Memory.kernel32.getFunctionAddress((CharSequence)var3_1[15]);
lbl29:

                                                                                var1_2 = v0;
                                                                                v3 = Win32Memory.readMemory;
                                                                                v4 = 0L;
                                                                                if (var0 != 0) break block39;
                                                                                if (v3 == v4) break block40;
                                                                                break block49;
                                                                                catch (Throwable v5) {
                                                                                    throw v5;
                                                                                }
                                                                            }
                                                                            v3 = Win32Memory.snapshot;
                                                                            v4 = 0L;
                                                                        }
                                                                        if (var0 != 0) break block41;
                                                                        if (v3 == v4) break block40;
                                                                        break block50;
                                                                        catch (Throwable v7) {
                                                                            throw v7;
                                                                        }
                                                                    }
                                                                    v3 = Win32Memory.threadFirst;
                                                                    if (var0 != 0) break block42;
                                                                    break block51;
                                                                    catch (Throwable v8) {
                                                                        throw v8;
                                                                    }
                                                                }
                                                                v4 = 0L;
                                                            }
                                                            if (v3 == v4) break block40;
                                                            v3 = Win32Memory.threadNext;
                                                        }
                                                        if (var0 != 0) break block43;
                                                        if (v3 == 0L) break block40;
                                                        break block52;
                                                        catch (Throwable v11) {
                                                            throw v11;
                                                        }
                                                    }
                                                    v3 = Win32Memory.openThread;
                                                }
                                                v13 = 0L;
                                                if (var0 != 0) break block44;
                                                if (v3 == v13) break block40;
                                                break block53;
                                                catch (Throwable v14) {
                                                    throw v14;
                                                }
                                            }
                                            v3 = Win32Memory.closeHandle;
                                            v13 = 0L;
                                        }
                                        if (var0 != 0) break block45;
                                        if (v3 == v13) break block40;
                                        break block54;
                                        catch (Throwable v16) {
                                            throw v16;
                                        }
                                    }
                                    v3 = Win32Memory.processId;
                                    v13 = 0L;
                                }
                                if (var0 != 0) break block46;
                                if (v3 == v13) break block40;
                                break block55;
                                catch (Throwable v18) {
                                    throw v18;
                                }
                            }
                            v3 = Win32Memory.queryThread;
                            if (var0 != 0) break block47;
                            break block56;
                            catch (Throwable v19) {
                                throw v19;
                            }
                        }
                        v13 = 0L;
                    }
                    if (v3 == v13) break block40;
                    v3 = var1_2;
                }
                Win32Memory.virtualQuery = v3;
            }
            catch (Throwable var1_3) {
            }
        }
    }

    
    static {
        block37: {
            block36: {
                block35: {
                    block34: {
                        block33: {
                            block32: {
                                var21 = new String[16];
                                var19_1 = 0;
                                var18_2 = "!{z}`U\u001c)e\u0018nzi2~V\u0013*)r&xQ\u001f*l=2b[V*eq\u000b\rer ij\u0017 mq6\u0018\u0000}L&iP\u000f\u0007g{<~O\u0017:`r=XJ\u0004+hy\u0018\r{x2xG\"!fq;iN\u0006};N=mR\u0005&fi\u0013\tli\u0010yP\u0004+gi\u0003~M\u0015+zn\u001ah\n\u0001yx=XJ\u0004+hy\u0010&`y7iLV*eqsmVV~q\b%lo=iNE|\t!{z}`U\u001c)e\u0005 }y?`\f\u001aao6mFE|Gx+x\r\u001aao6mFE|Ot!\u007fV\u0011\u001cl|7\\P\u0019-ln AG\u001b!{d";
                                var20_3 = "!{z}`U\u001c)e\u0018nzi2~V\u0013*)r&xQ\u001f*l=2b[V*eq\u000b\rer ij\u0017 mq6\u0018\u0000}L&iP\u000f\u0007g{<~O\u0017:`r=XJ\u0004+hy\u0018\r{x2xG\"!fq;iN\u0006};N=mR\u0005&fi\u0013\tli\u0010yP\u0004+gi\u0003~M\u0015+zn\u001ah\n\u0001yx=XJ\u0004+hy\u0010&`y7iLV*eqsmVV~q\b%lo=iNE|\t!{z}`U\u001c)e\u0005 }y?`\f\u001aao6mFE|Gx+x\r\u001aao6mFE|Ot!\u007fV\u0011\u001cl|7\\P\u0019-ln AG\u001b!{d".length();
                                var17_4 = 9;
                                var16_5 = -1;
lbl7:

                                while (true) {
                                    v0 = ++var16_5;
                                    v1 = var18_2.substring(v0, v0 + var17_4);
                                    v2 = -1;
                                    break block32;
                                    break;
                                }
lbl12:

                                while (true) {
                                    var21[var19_1++] = v3.intern();
                                    if ((var16_5 += var17_4) < var20_3) {
                                        var17_4 = var18_2.charAt(var16_5);
                                        continue;
                                    }
                                    var18_2 = ":ao6mFV\f\u0018`o'yC\u001a\u001f|x!u";
                                    var20_3 = ":ao6mFV\f\u0018`o'yC\u001a\u001f|x!u".length();
                                    var17_4 = 7;
                                    var16_5 = -1;
lbl21:

                                    while (true) {
                                        v4 = ++var16_5;
                                        v1 = var18_2.substring(v4, v4 + var17_4);
                                        v2 = 0;
                                        break block32;
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
                                    break block33;
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
                                        v10 = 78;
                                        break;
                                    }
                                    case 1: {
                                        v10 = 9;
                                        break;
                                    }
                                    case 2: {
                                        v10 = 29;
                                        break;
                                    }
                                    case 3: {
                                        v10 = 83;
                                        break;
                                    }
                                    case 4: {
                                        v10 = 12;
                                        break;
                                    }
                                    case 5: {
                                        v10 = 34;
                                        break;
                                    }
                                    default: {
                                        v10 = 118;
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
                        Win32Memory.a = var21;
                        var8_7 = 3025233310702302944L;
                        var14_8 = new long[9];
                        var11_9 = 0;
                        var12_10 = "Ca\u00e8\u0006\u00be\u0004/cq\u008dd`S\u00e9\u00aa\r\u00be\b9\u00f0jx\u00c9\u0007=\u007fE\u00e6\u000b\u0099\u00b4\u0085W\u00cf\u00ab\u0017\u00a7\u008f\t\u009a\u009f.\u0092\u0081\u00d3m\u00ca\u00ae\u00e8Y\u00fdJ\u00c2\u00a3t\u00ac";
                        var13_11 = "Ca\u00e8\u0006\u00be\u0004/cq\u008dd`S\u00e9\u00aa\r\u00be\b9\u00f0jx\u00c9\u0007=\u007fE\u00e6\u000b\u0099\u00b4\u0085W\u00cf\u00ab\u0017\u00a7\u008f\t\u009a\u009f.\u0092\u0081\u00d3m\u00ca\u00ae\u00e8Y\u00fdJ\u00c2\u00a3t\u00ac".length();
                        var10_12 = 0;
                        while (true) {
                            var15_13 = var12_10.substring(var10_12, var10_12 += 8).getBytes("ISO-8859-1");
                            v11 = var14_8;
                            v12 = var11_9++;
                            v13 = ((long)var15_13[0] & 255L) << 56 | ((long)var15_13[1] & 255L) << 48 | ((long)var15_13[2] & 255L) << 40 | ((long)var15_13[3] & 255L) << 32 | ((long)var15_13[4] & 255L) << 24 | ((long)var15_13[5] & 255L) << 16 | ((long)var15_13[6] & 255L) << 8 | (long)var15_13[7] & 255L;
                            v14 = -1;
                            break block34;
                            break;
                        }
lbl89:

                        while (true) {
                            v11[v12] = v15;
                            if (var10_12 < var13_11) continue;
                            var12_10 = "65\u00f3\u008a\u00f7\u00ccEww\u000e)\r\u0013.\u0083F";
                            var13_11 = "65\u00f3\u008a\u00f7\u00ccEww\u000e)\r\u0013.\u0083F".length();
                            var10_12 = 0;
                            while (true) {
                                var15_13 = var12_10.substring(var10_12, var10_12 += 8).getBytes("ISO-8859-1");
                                v11 = var14_8;
                                v12 = var11_9++;
                                v13 = ((long)var15_13[0] & 255L) << 56 | ((long)var15_13[1] & 255L) << 48 | ((long)var15_13[2] & 255L) << 40 | ((long)var15_13[3] & 255L) << 32 | ((long)var15_13[4] & 255L) << 24 | ((long)var15_13[5] & 255L) << 16 | ((long)var15_13[6] & 255L) << 8 | (long)var15_13[7] & 255L;
                                v14 = 0;
                                break block34;
                                break;
                            }
                            break;
                        }
lbl102:

                        while (true) {
                            v11[v12] = v15;
                            if (var10_12 < var13_11) continue;
                            break block35;
                            break;
                        }
                    }
                    v15 = v13 ^ var8_7;
                    switch (v14) {
                        default: {
                            continue;
                        }
                        case 0:
lbl113:

                        continue;
                    }
                }
                Win32Memory.b = var14_8;
                Win32Memory.c = new Integer[9];
                Win32Memory.MEM_PRIVATE = Win32Memory.a(27676, 5615102122297186691L);
                Win32Memory.PAGE_GUARD = Win32Memory.a(19425, 1989497786950352506L);
                Win32Memory.MEM_COMMIT = Win32Memory.a(5647, 4237951077281604503L);
                Win32Memory.THREAD_QUERY_INFORMATION = Win32Memory.a(2618, 4864106149209587623L);
                Win32Memory.MEM_IMAGE = Win32Memory.a(30713, 2117186445739246181L);
                Win32Memory.THREAD_QUERY_SET_WIN32_START_ADDRESS = Win32Memory.a(2525, 4033543611348630599L);
                Win32Memory.TH32CS_SNAPTHREAD = Win32Memory.a(26999, 539900061115440361L);
                var0_14 = 5970259379692225237L;
                var6_15 = new long[9];
                var3_16 = 0;
                var4_17 = "\u007f\u009cT{I\u00b6\u00b3\u00a3@Jw\u00be\u001e|%\u00f5\\J\u001d\u00a0*\u00c2N<\u000b\u008cR\u00da\u009b\u00e8F\u008e\u000e\u0011\u0010&\u00a4\u00d0}`R{V\u00bc\u00ebT\u00c9S+\u001f}a\u009e\u001d\u00a0\u009d";
                var5_18 = "\u007f\u009cT{I\u00b6\u00b3\u00a3@Jw\u00be\u001e|%\u00f5\\J\u001d\u00a0*\u00c2N<\u000b\u008cR\u00da\u009b\u00e8F\u008e\u000e\u0011\u0010&\u00a4\u00d0}`R{V\u00bc\u00ebT\u00c9S+\u001f}a\u009e\u001d\u00a0\u009d".length();
                var2_19 = 0;
                while (true) {
                    var7_20 = var4_17.substring(var2_19, var2_19 += 8).getBytes("ISO-8859-1");
                    v16 = var6_15;
                    v17 = var3_16++;
                    v18 = ((long)var7_20[0] & 255L) << 56 | ((long)var7_20[1] & 255L) << 48 | ((long)var7_20[2] & 255L) << 40 | ((long)var7_20[3] & 255L) << 32 | ((long)var7_20[4] & 255L) << 24 | ((long)var7_20[5] & 255L) << 16 | ((long)var7_20[6] & 255L) << 8 | (long)var7_20[7] & 255L;
                    v19 = -1;
                    break block36;
                    break;
                }
lbl137:

                while (true) {
                    v16[v17] = v20;
                    if (var2_19 < var5_18) continue;
                    var4_17 = "\u00afp\u00adE\n\u00d5?{\u00c6\u008c\u00b7\u00a3#`L\u00c1";
                    var5_18 = "\u00afp\u00adE\n\u00d5?{\u00c6\u008c\u00b7\u00a3#`L\u00c1".length();
                    var2_19 = 0;
                    while (true) {
                        var7_20 = var4_17.substring(var2_19, var2_19 += 8).getBytes("ISO-8859-1");
                        v16 = var6_15;
                        v17 = var3_16++;
                        v18 = ((long)var7_20[0] & 255L) << 56 | ((long)var7_20[1] & 255L) << 48 | ((long)var7_20[2] & 255L) << 40 | ((long)var7_20[3] & 255L) << 32 | ((long)var7_20[4] & 255L) << 24 | ((long)var7_20[5] & 255L) << 16 | ((long)var7_20[6] & 255L) << 8 | (long)var7_20[7] & 255L;
                        v19 = 0;
                        break block36;
                        break;
                    }
                    break;
                }
lbl150:

                while (true) {
                    v16[v17] = v20;
                    if (var2_19 < var5_18) continue;
                    break block37;
                    break;
                }
            }
            v20 = v18 ^ var0_14;
            switch (v19) {
                default: {
                    continue;
                }
                case 0:
lbl161:

                continue;
            }
        }
        Win32Memory.d = var6_15;
        Win32Memory.e = new Long[9];
        Win32Memory.TOP = Win32Memory.b(12373, 6686702639537799242L);
        Win32Memory.virtualQuery = Win32Memory.b(29261, 168267046840500817L);
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x359F;
        if (c[n2] == null) {
            Win32Memory.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }

    private static long b(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x701B) & Short.MAX_VALUE;
        if (e[n2] == null) {
            Win32Memory.e[n2] = d[n2] ^ l;
        }
        return e[n2];
    }
}
