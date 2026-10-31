package dev.quelchi.aurora.render;

import dev.quelchi.aurora.module.modules.MotionBlurModule$Type;

class MotionBlurGpu$1 {
    static final int[] $SwitchMap$dev$quelchi$aurora$module$modules$MotionBlurModule$Type = new int[MotionBlurModule$Type.values().length];

    static {
        try {
            MotionBlurGpu$1.$SwitchMap$dev$quelchi$aurora$module$modules$MotionBlurModule$Type[MotionBlurModule$Type.CAMERA.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
        }
        try {
            MotionBlurGpu$1.$SwitchMap$dev$quelchi$aurora$module$modules$MotionBlurModule$Type[MotionBlurModule$Type.FRAME_BLEND.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
        }
    }
}
