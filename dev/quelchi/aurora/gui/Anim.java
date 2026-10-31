package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.gui.Anim$State;
import dev.quelchi.aurora.gui.PreviewScene;
import java.util.HashMap;
import java.util.Map;

public final class Anim {
    public static final float HOVER = 16.0f;
    private static final Map<Object, Anim$State> STATES;
    private static final long EPOCH;
    private static float speed;
    private static long realAt;
    private static long clock;
    private static final long a;

    private Anim() {
    }

    public static synchronized long now() {
        long l = System.nanoTime();
        clock += (long)((float)(l - realAt) * speed);
        realAt = l;
        return clock;
    }

    public static long millis() {
        return Anim.now() / a;
    }

    public static float seconds() {
        return (float)((double)(Anim.now() - EPOCH) / 1.0E9);
    }

    public static float to(Object object, float f, float f2) {
        float f3;
        block21: {
            float f4;
            float f5;
            Anim$State anim$State;
            block19: {
                float f6;
                int n;
                block20: {
                    block17: {
                        block18: {
                            block16: {
                                block15: {
                                    n = PreviewScene.a;
                                    anim$State = STATES.get(object);
                                    if (n != 0) break block15;
                                    if (anim$State != null) break block16;
                                    STATES.put(object, new Anim$State(f));
                                }
                                return f;
                            }
                            long l = Anim.now();
                            f6 = (float)(l - anim$State.nanos) / 1.0E9f;
                            anim$State.nanos = l;
                            f5 = f6;
                            f4 = 0.0f;
                            if (n != 0) break block17;
                            if (!(f5 <= f4)) break block18;
                            return anim$State.value;
                        }
                        f5 = f6;
                        f4 = 0.1f;
                    }
                    if (n != 0) break block19;
                    if (!(f5 > f4)) break block20;
                    f6 = 0.1f;
                }
                anim$State.value += (f - anim$State.value) * (1.0f - (float)Math.exp(-f2 * f6));
                f3 = f - anim$State.value;
                if (n != 0) break block21;
                f5 = Math.abs(f3);
                f4 = 0.0015f;
            }
            if (f5 < f4) {
                anim$State.value = f;
            }
            f3 = anim$State.value;
        }
        return f3;
    }

    public static float px(Object object, float f, float f2) {
        float f3;
        block4: {
            float f4;
            block5: {
                f4 = Anim.to(object, f, f2);
                int n = PreviewScene.a;
                f3 = f - f4;
                if (n != 0) break block4;
                if (!(Math.abs(f3) < 0.5f)) break block5;
                Anim.STATES.get((Object)object).value = f;
                return f;
            }
            f3 = f4;
        }
        return f3;
    }

    public static void reset() {
        STATES.clear();
    }

    public static void reset(Object object) {
        STATES.remove(object);
    }

    public static float outCubic(float f) {
        float f2 = 1.0f - Anim.clamp(f);
        return 1.0f - f2 * f2 * f2;
    }

    private static float clamp(float f) {
        float f2;
        block10: {
            float f3;
            block8: {
                int n;
                block9: {
                    n = PreviewScene.a;
                    f2 = f;
                    f3 = 0.0f;
                    if (n != 0) break block8;
                    if (!(f2 < f3)) break block9;
                    f2 = 0.0f;
                    break block10;
                }
                f2 = f;
                if (n != 0) break block10;
                f3 = 1.0f;
            }
            f2 = f2 > f3 ? 1.0f : f;
        }
        return f2;
    }

    
    static {
        long l = 211324596269552107L;
        a = 0x2EEC699C4FC53ABL ^ l;
        STATES = new HashMap<Object, Anim$State>();
        EPOCH = System.nanoTime();
        speed = 1.0f;
        realAt = EPOCH;
        clock = EPOCH;
    }
}
