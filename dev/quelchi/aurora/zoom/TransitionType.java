package dev.quelchi.aurora.zoom;

import dev.quelchi.aurora.zoom.TransitionType$1;
import dev.quelchi.aurora.zoom.TransitionType$10;
import dev.quelchi.aurora.zoom.TransitionType$11;
import dev.quelchi.aurora.zoom.TransitionType$12;
import dev.quelchi.aurora.zoom.TransitionType$13;
import dev.quelchi.aurora.zoom.TransitionType$14;
import dev.quelchi.aurora.zoom.TransitionType$2;
import dev.quelchi.aurora.zoom.TransitionType$3;
import dev.quelchi.aurora.zoom.TransitionType$4;
import dev.quelchi.aurora.zoom.TransitionType$5;
import dev.quelchi.aurora.zoom.TransitionType$6;
import dev.quelchi.aurora.zoom.TransitionType$7;
import dev.quelchi.aurora.zoom.TransitionType$8;
import dev.quelchi.aurora.zoom.TransitionType$9;

public abstract class TransitionType
extends Enum<TransitionType> {
    public static final /* enum */ TransitionType Instant;
    public static final /* enum */ TransitionType Linear;
    public static final /* enum */ TransitionType EaseInSine;
    public static final /* enum */ TransitionType EaseOutSine;
    public static final /* enum */ TransitionType EaseInOutSine;
    public static final /* enum */ TransitionType EaseInQuad;
    public static final /* enum */ TransitionType EaseOutQuad;
    public static final /* enum */ TransitionType EaseInOutQuad;
    public static final /* enum */ TransitionType EaseInCubic;
    public static final /* enum */ TransitionType EaseOutCubic;
    public static final /* enum */ TransitionType EaseInOutCubic;
    public static final /* enum */ TransitionType EaseInExp;
    public static final /* enum */ TransitionType EaseOutExp;
    public static final /* enum */ TransitionType EaseInOutExp;
    private static final double LOG2_1023;
    private static final TransitionType[] $VALUES;
    public static boolean a;

    public static TransitionType[] values() {
        return (TransitionType[])$VALUES.clone();
    }

    public static TransitionType valueOf(String string) {
        return Enum.valueOf(TransitionType.class, string);
    }

    public abstract double apply(double var1);

    public double inverse(double d) {
        throw new UnsupportedOperationException();
    }

    public boolean hasInverse() {
        return false;
    }

    
    public TransitionType opposite() {
        TransitionType transitionType;
        switch (this.ordinal()) {
            default: {
                throw new MatchException(null, null);
            }
            case 0: {
                transitionType = Instant;
                return transitionType;
            }
            case 1: {
                transitionType = Linear;
                return transitionType;
            }
            case 2: {
                transitionType = EaseOutSine;
                return transitionType;
            }
            case 3: {
                transitionType = EaseInSine;
                return transitionType;
            }
            case 4: {
                transitionType = EaseInOutSine;
                return transitionType;
            }
            case 5: {
                transitionType = EaseOutQuad;
                return transitionType;
            }
            case 6: {
                transitionType = EaseInQuad;
                return transitionType;
            }
            case 7: {
                transitionType = EaseInOutQuad;
                return transitionType;
            }
            case 8: {
                transitionType = EaseOutCubic;
                return transitionType;
            }
            case 9: {
                transitionType = EaseInCubic;
                return transitionType;
            }
            case 10: {
                transitionType = EaseInOutCubic;
                return transitionType;
            }
            case 11: {
                transitionType = EaseOutExp;
                return transitionType;
            }
            case 12: {
                transitionType = EaseInExp;
                return transitionType;
            }
            case 13: 
        }
        transitionType = EaseInOutExp;
        return transitionType;
    }

    private static TransitionType[] $values() {
        return new TransitionType[]{Instant, Linear, EaseInSine, EaseOutSine, EaseInOutSine, EaseInQuad, EaseOutQuad, EaseInOutQuad, EaseInCubic, EaseOutCubic, EaseInOutCubic, EaseInExp, EaseOutExp, EaseInOutExp};
    }

    
    static {
        block19: {
            block18: {
                var0 = new String[14];
                var4_1 = 0;
                var3_2 = "\u0016\u0004\fT zz&\u0007\u0016R\u0007\u001a\u000b\fE\bzM\u0006\u001f\f\u0011T\bf\r\u0016\u0004\fT zv&\u0011,X\u0007q\u000b\u0016\u0004\fT&aM\u0002\u0010\u001eU\f\u0016\u0004\fT zv&\u0011:I\u0019\n\u0016\u0004\fT zj:\u000b\u001a\t\u0016\u0004\fT z|+\u0015\n\u0016\u0004\fT&aM\u0016\u001d\u000f\f\u0016\u0004\fT&aM\u0010\u0010\u001dX\n\n\u0016\u0004\fT zh&\u0004\u001b\r\u0016\u0004\fT zv&\u0011.D\bp";
                var5_3 = "\u0016\u0004\fT zz&\u0007\u0016R\u0007\u001a\u000b\fE\bzM\u0006\u001f\f\u0011T\bf\r\u0016\u0004\fT zv&\u0011,X\u0007q\u000b\u0016\u0004\fT&aM\u0002\u0010\u001eU\f\u0016\u0004\fT zv&\u0011:I\u0019\n\u0016\u0004\fT zj:\u000b\u001a\t\u0016\u0004\fT z|+\u0015\n\u0016\u0004\fT&aM\u0016\u001d\u000f\f\u0016\u0004\fT&aM\u0010\u0010\u001dX\n\n\u0016\u0004\fT zh&\u0004\u001b\r\u0016\u0004\fT zv&\u0011.D\bp".length();
                var2_4 = 11;
                var1_5 = -1;
lbl7:

                while (true) {
                    v0 = ++var1_5;
                    v1 = var3_2.substring(v0, v0 + var2_4);
                    v2 = -1;
                    break block18;
                    break;
                }
lbl12:

                while (true) {
                    var0[var4_1++] = v3.intern();
                    if ((var1_5 += var2_4) < var5_3) {
                        var2_4 = var3_2.charAt(var1_5);
                        continue;
                    }
                    var3_2 = "\u0016\u0004\fT zv&\u0011<D\u000b}Z\u000b\u0016\u0004\fT&aM\u0000\f\u0011T";
                    var5_3 = "\u0016\u0004\fT zv&\u0011<D\u000b}Z\u000b\u0016\u0004\fT&aM\u0000\f\u0011T".length();
                    var2_4 = 14;
                    var1_5 = -1;
lbl21:

                    while (true) {
                        v4 = ++var1_5;
                        v1 = var3_2.substring(v4, v4 + var2_4);
                        v2 = 0;
                        break block18;
                        break;
                    }
                    break;
                }
lbl26:

                while (true) {
                    var0[var4_1++] = v3.intern();
                    if ((var1_5 += var2_4) < var5_3) {
                        var2_4 = var3_2.charAt(var1_5);
                        continue;
                    }
                    break block19;
                    break;
                }
            }
            v5 = v1.toCharArray();
            v6 = v5;
            v7 = v5.length;
            var6_6 = 0;
            do {
                v6 = v6;
                v8 = var6_6;
                v9 = v6[v8];
                switch (var6_6 % 7) {
                    case 0: {
                        v10 = 83;
                        break;
                    }
                    case 1: {
                        v10 = 101;
                        break;
                    }
                    case 2: {
                        v10 = 127;
                        break;
                    }
                    case 3: {
                        v10 = 49;
                        break;
                    }
                    case 4: {
                        v10 = 105;
                        break;
                    }
                    case 5: {
                        v10 = 20;
                        break;
                    }
                    default: {
                        v10 = 57;
                    }
                }
                v6[v8] = (char)(v9 ^ v10);
                ++var6_6;
lbl65:

                v7 = v7;
            } while (v7 > var6_6);
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
        TransitionType.Instant = new TransitionType$1();
        TransitionType.Linear = new TransitionType$2();
        TransitionType.EaseInSine = new TransitionType$3();
        TransitionType.EaseOutSine = new TransitionType$4();
        TransitionType.EaseInOutSine = new TransitionType$5();
        TransitionType.EaseInQuad = new TransitionType$6();
        TransitionType.EaseOutQuad = new TransitionType$7();
        TransitionType.EaseInOutQuad = new TransitionType$8();
        TransitionType.EaseInCubic = new TransitionType$9();
        TransitionType.EaseOutCubic = new TransitionType$10();
        TransitionType.EaseInOutCubic = new TransitionType$11();
        TransitionType.EaseInExp = new TransitionType$12();
        TransitionType.EaseOutExp = new TransitionType$13();
        TransitionType.EaseInOutExp = new TransitionType$14();
        TransitionType.$VALUES = TransitionType.$values();
        TransitionType.LOG2_1023 = Math.log(1023.0) / Math.log(2.0);
    }
}
