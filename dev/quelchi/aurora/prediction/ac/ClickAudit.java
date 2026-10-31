package dev.quelchi.aurora.prediction.ac;

import dev.quelchi.aurora.prediction.ac.AntiCheat;
import dev.quelchi.aurora.prediction.ac.InputAudit;
import net.minecraft.client.MinecraftClient;

public final class ClickAudit {
    static final int WINDOW;
    private static final double MAX_CPS = 50.0;
    private static final double DRAG_CPS = 28.0;
    private static final double DRAG_SPREAD = 0.15;
    private static final double MIN_SPREAD_MS = 4.0;
    private static final long CHATTER_NS;
    private static final long[][] presses;
    private static final int[] count;
    private static final long[] lastKept;
    private static final String[] a;
    private static final long[] b;
    private static final Long[] c;

    private ClickAudit() {
    }

    static void reset() {
        ClickAudit.count[1] = 0;
        ClickAudit.count[0] = 0;
        ClickAudit.lastKept[1] = 0L;
        ClickAudit.lastKept[0] = 0L;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void onPress(int n) {
        String string;
        String string2;
        block18: {
            MinecraftClient minecraftClient;
            MinecraftClient minecraftClient2;
            int n2;
            block17: {
                int n3;
                block16: {
                    n2 = InputAudit.a;
                    n3 = n;
                    if (n2 != 0) break block16;
                    if (n3 < 0) return;
                    n3 = n;
                }
                if (n3 > 1) {
                    return;
                }
                minecraftClient2 = MinecraftClient.method_1551();
                minecraftClient = minecraftClient2;
                if (n2 != 0) break block17;
                if (minecraftClient.field_1724 == null) return;
                minecraftClient = minecraftClient2;
            }
            if (minecraftClient.field_1755 != null) {
                return;
            }
            string2 = ClickAudit.add(n, System.nanoTime(), minecraftClient2.method_47599());
            string = string2;
            if (n2 != 0) break block18;
            if (string == null) return;
            String[] stringArray = a;
            string = stringArray[3];
        }
        String string3 = n == 0 ? a[1] : a[5];
        AntiCheat.flag(string, string3 + string2);
    }

    static String add(int n, long l, int n2) {
        block47: {
            double d;
            double d2;
            double d3;
            block48: {
                double d4;
                int n3;
                block46: {
                    double d5;
                    block43: {
                        block45: {
                            block44: {
                                double d6;
                                block41: {
                                    block42: {
                                        double d7;
                                        double d8;
                                        block40: {
                                            long[] lArray;
                                            block39: {
                                                int n4;
                                                block38: {
                                                    block36: {
                                                        long[] lArray2;
                                                        block37: {
                                                            n3 = InputAudit.a;
                                                            lArray2 = lastKept;
                                                            if (n3 != 0) break block36;
                                                            if (lArray2[n] == 0L) break block37;
                                                            long l2 = l - lastKept[n] - ClickAudit.a(28243, 5030794322033287608L);
                                                            n4 = l2 == 0L ? 0 : (l2 < 0L ? -1 : 1);
                                                            if (n3 != 0) break block38;
                                                            if (n4 >= 0) break block37;
                                                            return null;
                                                        }
                                                        lArray = lastKept;
                                                        if (n3 != 0) break block39;
                                                        lArray[n] = l;
                                                        lArray2 = presses[n];
                                                    }
                                                    int n5 = n;
                                                    int n6 = count[n5];
                                                    count[n5] = n6 + 1;
                                                    lArray2[n6] = l;
                                                    n4 = count[n];
                                                }
                                                if (n4 < 20) {
                                                    return null;
                                                }
                                                ClickAudit.count[n] = 0;
                                                lArray = presses[n];
                                            }
                                            long[] lArray3 = lArray;
                                            d6 = (double)(lArray3[19] - lArray3[0]) / 1000000.0 / 19.0;
                                            double d9 = 0.0;
                                            for (int i = 1; i < 20; ++i) {
                                                d8 = lArray3[i] - lArray3[i - 1];
                                                d7 = 1000000.0;
                                                if (n3 == 0) {
                                                    double d10 = d8 / d7 - d6;
                                                    d9 += d10 * d10;
                                                    if (n3 == 0) continue;
                                                }
                                                break block40;
                                            }
                                            d8 = d9;
                                            d7 = 19.0;
                                        }
                                        d3 = Math.sqrt(d8 / d7);
                                        d2 = 1000.0 / d6;
                                        d4 = d2;
                                        if (n3 != 0) break block41;
                                        if (!(d4 > 50.0)) break block42;
                                        return String.format(a[4], d2);
                                    }
                                    d4 = d2;
                                }
                                d5 = 28.0;
                                if (n3 != 0) break block43;
                                if (!(d4 > d5)) break block44;
                                d4 = d3;
                                if (n3 != 0) break block45;
                                if (!(d4 < d6 * 0.15)) break block44;
                                return String.format(a[2], d2, d3);
                            }
                            d4 = d2;
                        }
                        if (n3 != 0) break block46;
                        d5 = 9.0;
                    }
                    if (!(d4 > d5)) break block47;
                    d4 = d3;
                }
                double d11 = d4 - 4.0;
                d = d11 == 0.0 ? 0 : (d11 < 0.0 ? -1 : 1);
                if (n3 != 0) break block48;
                if (d >= 0) break block47;
                d = n2;
            }
            if (d >= 120) {
                return String.format(a[0], d2, d3);
            }
        }
        return null;
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
        int n2 = (n ^ (int)l ^ 0x4FEB) & Short.MAX_VALUE;
        if (c[n2] == null) {
            ClickAudit.c[n2] = b[n2] ^ l;
        }
        return c[n2];
    }
}
