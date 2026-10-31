package dev.quelchi.aurora.prediction.ac;

import dev.quelchi.aurora.prediction.ac.InputAudit;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;
import org.lwjgl.system.JNI;
import org.lwjgl.system.Library;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.Platform;
import org.lwjgl.system.SharedLibrary;

final class Win32Modules {
    private static final int MAX;
    private static SharedLibrary kernel32;
    private static long enumFn;
    private static long nameFn;
    private static final String[] a;
    private static final long[] b;
    private static final Long[] c;

    private Win32Modules() {
    }

    
    static Set<String> list() {
        block34: {
            block31: {
                block33: {
                    block32: {
                        block29: {
                            block30: {
                                block38: {
                                    var1 = new HashSet<String>();
                                    var0_1 = InputAudit.a;
                                    v0 = Win32Modules.enumFn;
                                    v1 = Win32Modules.a(3402, 2987714518259249237L);
                                    if (var0_1 != 0) break block29;
                                    if (v0 != v1) break block30;
                                    break block38;
                                    catch (Throwable v2) {
                                        throw v2;
                                    }
                                }
                                Win32Modules.lookup();
                            }
                            v0 = Win32Modules.enumFn;
                            v1 = 0L;
                        }
                        if (v0 == v1) {
                            return var1;
                        }
                        var2_2 = MemoryStack.stackPush();
                        var3_4 = var2_2.calloc(16384);
                        var4_6 = var2_2.calloc(4);
                        v5 = JNI.callPPPI((long)Win32Modules.a(3402, 2987714518259249237L), (long)MemoryUtil.memAddress((ByteBuffer)var3_4), (int)16384, (long)MemoryUtil.memAddress((ByteBuffer)var4_6), (long)Win32Modules.enumFn);
                        if (var0_1 == 0) {
                            if (v5 != 0) break block31;
                        }
                        var5_8 = var1;
                        v7 = var2_2;
                        if (var0_1 != 0) break block32;
                        if (v7 == null) break block33;
                        v7 = var2_2;
                    }
                    v7.close();
                }
                return var5_8;
            }
            try {
                block36: {
                    v5 = Math.min(2048, var4_6.getInt(0) / 8);
lbl57:

                    var5_9 = v5;
                    var6_10 = var2_2.calloc(2048);
                    var7_11 = 0;
                    while (var7_11 < var5_9) {
                        block39: {
                            block35: {
                                block40: {
                                    if (var0_1 != 0) break block34;
                                    var8_12 = JNI.callPPI((long)var3_4.getLong(var7_11 * 8), (long)MemoryUtil.memAddress((ByteBuffer)var6_10), (int)1024, (long)Win32Modules.nameFn);
                                    if (var0_1 != 0) break block39;
                                    if (var8_12 <= 0) break block35;
                                    break block40;
                                    catch (Throwable v9) {
                                        throw v9;
                                    }
                                }
                                var1.add(MemoryUtil.memUTF16((long)MemoryUtil.memAddress((ByteBuffer)var6_10), (int)var8_12).toLowerCase());
                            }
                            ++var7_11;
                        }
                        if (var0_1 == 0) continue;
                    }
                    {
                        catch (Throwable var3_5) {
                            block37: {
                                if (var0_1 != 0 || var2_2 == null) break block37;
                                try {
                                    var2_2.close();
                                }
                                catch (Throwable var4_7) {
                                    var3_5.addSuppressed(var4_7);
                                }
                            }
                            throw var3_5;
                        }
                    }
                    v12 = var2_2;
                    if (var0_1 != 0) break block36;
                    if (v12 == null) break block34;
                    v12 = var2_2;
                }
                v12.close();
            }
            catch (Throwable var2_3) {
            }
        }
        return var1;
    }

    
    private static void lookup() {
        block16: {
            block14: {
                block18: {
                    var0 = InputAudit.a;
                    v0 = 0L;
                    if (var0 != 0) // goto
                    Win32Modules.enumFn = v0;
                    if (Platform.get() == Platform.WINDOWS) break block14;
                    break block18;
                    catch (Throwable v1) {
                        throw v1;
                    }
                }
                return;
            }
            try {
                block17: {
                    block20: {
                        block15: {
                            block19: {
                                var3_1 = Win32Modules.a;
                                Win32Modules.kernel32 = Library.loadNative(Win32Modules.class, (String)var3_1[1], (String)var3_1[3]);
                                v0 = Win32Modules.kernel32.getFunctionAddress((CharSequence)var3_1[2]);
lbl20:

                                var1_2 = v0;
                                Win32Modules.nameFn = Win32Modules.kernel32.getFunctionAddress((CharSequence)Win32Modules.a[0]);
                                v3 = var1_2;
                                if (var0 != 0) break block15;
                                if (v3 == 0L) break block16;
                                break block19;
                                catch (Throwable v4) {
                                    throw v4;
                                }
                            }
                            v3 = Win32Modules.nameFn;
                        }
                        if (var0 != 0) break block17;
                        if (v3 == 0L) break block16;
                        break block20;
                        catch (Throwable v6) {
                            throw v6;
                        }
                    }
                    v3 = var1_2;
                }
                Win32Modules.enumFn = v3;
            }
            catch (Throwable var1_3) {
            }
        }
    }

    /*
     * Exception decompiling
     */
    static {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: CONTINUE without a while class org.benf.cfr.reader.bytecode.analysis.parse.statement.AssignmentSimple
         *     at org.benf.cfr.reader.bytecode.analysis.parse.statement.GotoStatement.getTargetStartBlock(GotoStatement.java:102)
         *     at org.benf.cfr.reader.bytecode.analysis.parse.statement.GotoStatement.getStructuredStatement(GotoStatement.java:116)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.getStructuredStatementPlaceHolder(Op03SimpleStatement.java:550)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:727)
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

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x3D1E) & Short.MAX_VALUE;
        if (c[n2] == null) {
            Win32Modules.c[n2] = b[n2] ^ l;
        }
        return c[n2];
    }
}
