package dev.quelchi.aurora.gui.im;

import dev.quelchi.aurora.gui.Anim;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.ImScreen;
import dev.quelchi.aurora.gui.im.Motion$Fade;
import java.util.HashMap;
import java.util.Map;

public final class Motion {
    public static final float FAST = 0.09f;
    public static final float BASE = 0.15f;
    public static final float SLOW = 0.22f;
    private static final Map<String, Motion$Fade> FADES = new HashMap<String, Motion$Fade>();
    private static final Map<String, Long> SHOTS = new HashMap<String, Long>();

    private Motion() {
    }

    public static float to(String string, boolean bl, float f) {
        float f2;
        Motion$Fade motion$Fade;
        block39: {
            float f3;
            float f4;
            float f5;
            block37: {
                block38: {
                    float f6;
                    boolean bl2;
                    block36: {
                        float f7;
                        block34: {
                            long l;
                            block35: {
                                boolean bl3;
                                block32: {
                                    block33: {
                                        long l2;
                                        block28: {
                                            long l3;
                                            block29: {
                                                block30: {
                                                    Motion$Fade motion$Fade2;
                                                    block31: {
                                                        block26: {
                                                            block27: {
                                                                motion$Fade = FADES.get(string);
                                                                bl2 = ImScreen.a;
                                                                l3 = Im.frames();
                                                                motion$Fade2 = motion$Fade;
                                                                if (bl2) break block26;
                                                                if (motion$Fade2 == null) break block27;
                                                                l2 = l3;
                                                                if (bl2) break block28;
                                                                if (l2 - motion$Fade.seen <= 1L) break block29;
                                                            }
                                                            motion$Fade2 = motion$Fade;
                                                        }
                                                        if (bl2) break block30;
                                                        if (motion$Fade2 != null) break block31;
                                                        motion$Fade = new Motion$Fade();
                                                        FADES.put(string, motion$Fade);
                                                    }
                                                    motion$Fade.seen = l3;
                                                    motion$Fade.on = bl;
                                                    motion$Fade2 = motion$Fade;
                                                }
                                                Motion$Fade motion$Fade3 = motion$Fade;
                                                float f8 = bl ? 1.0f : 0.0f;
                                                motion$Fade3.value = f8;
                                                motion$Fade2.from = f8;
                                                return motion$Fade.value;
                                            }
                                            motion$Fade.seen = l3;
                                            l2 = Anim.now();
                                        }
                                        l = l2;
                                        bl3 = motion$Fade.on;
                                        if (bl2) break block32;
                                        if (bl3 == bl) break block33;
                                        motion$Fade.on = bl;
                                        motion$Fade.from = motion$Fade.value;
                                        motion$Fade.at = l;
                                    }
                                    bl3 = bl;
                                }
                                float f9 = bl3 ? 1.0f : 0.0f;
                                f5 = f9;
                                f7 = f;
                                if (bl2) break block34;
                                if (!(f7 <= 0.0f)) break block35;
                                f6 = 1.0f;
                                break block36;
                            }
                            f7 = (float)(l - motion$Fade.at) / 1.0E9f;
                        }
                        f6 = f7 / f;
                    }
                    f4 = f6;
                    Motion$Fade motion$Fade4 = motion$Fade;
                    f3 = f4;
                    if (bl2) break block37;
                    if (!(f3 >= 1.0f)) break block38;
                    f2 = f5;
                    break block39;
                }
                f3 = motion$Fade.from;
            }
            f2 = f3 + (f5 - motion$Fade.from) * Motion.out(f4);
        }
        motion$Fade4.value = f2;
        return motion$Fade.value;
    }

    public static float out(float f) {
        float f2;
        float f3;
        block27: {
            float f4;
            boolean bl;
            block25: {
                block26: {
                    block23: {
                        block24: {
                            bl = ImScreen.a;
                            f4 = f;
                            if (bl) break block23;
                            if (!(f4 <= 0.0f)) break block24;
                            return 0.0f;
                        }
                        f4 = f;
                    }
                    if (bl) break block25;
                    if (!(f4 >= 1.0f)) break block26;
                    return 1.0f;
                }
                f4 = f;
            }
            f3 = f4;
            for (int i = 0; i < 6; ++i) {
                float f5;
                block30: {
                    block31: {
                        float f6;
                        block28: {
                            f2 = 1.0f;
                            if (bl) break block27;
                            float f7 = f2 - f3;
                            float f8 = 0.6f * f7 * f7 * f3 + 0.6f * f7 * f3 * f3 + f3 * f3 * f3 - f;
                            float f9 = 0.6f * f7 * f7 + 2.4f * f3 * f3;
                            f6 = f9;
                            if (bl) break block28;
                            if (f6 < 1.0E-5f) {
                                break;
                            }
                            f5 = f3;
                            if (bl) break block31;
                            f6 = f3 = f5 - f8 / f9;
                        }
                        if (f6 < 0.0f) {
                            f3 = 0.0f;
                        }
                        f5 = f3;
                    }
                    if (bl) break block30;
                    if (!(f5 > 1.0f)) continue;
                    f5 = 1.0f;
                }
                f3 = f5;
                if (!bl) continue;
            }
            f2 = 1.0f - f3;
        }
        float f10 = f2;
        return 2.4f * f10 * f10 * f3 + 3.0f * f10 * f3 * f3 + f3 * f3 * f3;
    }

    public static void restart(String string) {
        SHOTS.put(string, Anim.now());
    }

    public static float since(String string) {
        Long l = SHOTS.get(string);
        float f = l == null ? 1000000.0f : (float)(Anim.now() - l) / 1.0E9f;
        return f;
    }
}
