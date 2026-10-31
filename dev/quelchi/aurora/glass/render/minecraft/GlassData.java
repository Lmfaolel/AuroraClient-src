package dev.quelchi.aurora.glass.render.minecraft;

import dev.quelchi.aurora.glass.render.minecraft.GlassSurfaceState;
import dev.quelchi.aurora.glass.style.GlassStyle;
import dev.quelchi.aurora.module.Module;
import java.nio.ByteBuffer;

final class GlassData {
    static final int COLUMNS;

    private GlassData() {
    }

    static void put(ByteBuffer byteBuffer, GlassSurfaceState glassSurfaceState, boolean bl, boolean bl2, float f, int n) {
        block18: {
            boolean bl3;
            block20: {
                block19: {
                    float f2;
                    ByteBuffer byteBuffer2;
                    boolean bl4;
                    GlassStyle glassStyle;
                    block16: {
                        block17: {
                            float f3;
                            ByteBuffer byteBuffer3;
                            block14: {
                                block15: {
                                    glassStyle = glassSurfaceState.style;
                                    byteBuffer.putFloat(glassSurfaceState.box.width()).putFloat(glassSurfaceState.box.height()).putFloat(glassStyle.radius()).putFloat(glassStyle.opacity());
                                    bl4 = GlassSurfaceState.a;
                                    byteBuffer3 = byteBuffer.putFloat(glassStyle.blur());
                                    f3 = f;
                                    if (bl4) break block14;
                                    byteBuffer3.putFloat(f3).putFloat(glassStyle.saturation()).putFloat(glassStyle.contrast());
                                    byteBuffer.putFloat(glassStyle.noise()).putInt(glassStyle.borderColor()).putInt(glassStyle.shadowColor()).putFloat(glassStyle.shadowSize());
                                    byteBuffer3 = byteBuffer.putInt(glassStyle.innerGlow()).putInt(glassStyle.outerGlow()).putFloat(glassStyle.highlight());
                                    if (!glassStyle.mouseReactive()) break block15;
                                    f3 = 1.0f;
                                    break block14;
                                }
                                f3 = 0.0f;
                            }
                            byteBuffer3.putFloat(f3);
                            byteBuffer2 = byteBuffer;
                            f2 = glassSurfaceState.mouseX;
                            if (bl4) break block16;
                            byteBuffer2 = byteBuffer2.putFloat(f2).putFloat(glassSurfaceState.mouseY).putFloat(glassSurfaceState.visibility);
                            if (!bl) break block17;
                            f2 = 1.0f;
                            break block16;
                        }
                        f2 = 0.0f;
                    }
                    byteBuffer2.putFloat(f2);
                    ByteBuffer byteBuffer4 = byteBuffer;
                    float f4 = bl2 ? 1.0f : 0.0f;
                    byteBuffer4.putFloat(f4).putFloat((float)n * glassSurfaceState.pixelScale);
                    byteBuffer.putFloat(glassStyle.topRightRadius()).putFloat(glassStyle.bottomRightRadius()).putFloat(glassStyle.bottomLeftRadius());
                    byteBuffer.putFloat(glassStyle.chromaticAberration()).putFloat(glassStyle.iridescence()).putFloat(glassStyle.roughness());
                    byteBuffer.putInt(glassStyle.tintBottom()).putFloat(glassStyle.lightAngle()).putFloat(glassStyle.blurSpread()).putFloat(glassStyle.edgeWidth());
                    byteBuffer.putFloat(glassStyle.tintAngle()).putInt(glassStyle.edgeLight()).putFloat(0.0f).putFloat(0.0f);
                    if (!bl4) break block18;
                    if (!Module.a) break block19;
                    bl3 = false;
                    break block20;
                }
                bl3 = true;
            }
            Module.a = bl3;
        }
    }

    
    static {
        long l = 4224627162985119177L;
        long l2 = 0x97B5F1798E31BDEDL ^ l;
        COLUMNS = (int)l2;
    }
}
