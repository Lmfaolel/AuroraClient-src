package dev.quelchi.aurora.mixins;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets={"me/flashyreese/mods/sodiumextra/client/FrameCounter"})
public abstract class SodiumExtraFpsMixin {
    @Shadow
    private long lastFrameTime;
    @Unique
    private static final MethodHandle aurora$showFps = SodiumExtraFpsMixin.aurora$showFpsHandle();

    @Unique
    private static MethodHandle aurora$showFpsHandle() {
        try {
            MethodHandles.Lookup lookup = MethodHandles.publicLookup();
            Class<?> clazz = Class.forName("me.flashyreese.mods.sodiumextra.client.SodiumExtraClientMod");
            Class<?> clazz2 = Class.forName("me.flashyreese.mods.sodiumextra.client.config.SodiumExtraGameOptions");
            Class<?> clazz3 = Class.forName("me.flashyreese.mods.sodiumextra.client.config.SodiumExtraGameOptions$ExtraSettings");
            MethodHandle methodHandle = lookup.findStatic(clazz, "options", MethodType.methodType(clazz2));
            MethodHandle methodHandle2 = lookup.findGetter(clazz2, "extraSettings", clazz3);
            MethodHandle methodHandle3 = lookup.findGetter(clazz3, "showFps", Boolean.TYPE);
            return MethodHandles.filterReturnValue(MethodHandles.filterReturnValue(methodHandle, methodHandle2), methodHandle3).asType(MethodType.methodType(Boolean.TYPE));
        }
        catch (Throwable throwable) {
            return null;
        }
    }

    @Inject(method={"onFrame()V"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void aurora$onlyWhenShown(CallbackInfo callbackInfo) {
        if (aurora$showFps == null) {
            return;
        }
        try {
            if (aurora$showFps.invokeExact()) {
                return;
            }
        }
        catch (Throwable throwable) {
            return;
        }
        this.lastFrameTime = -1L;
        callbackInfo.cancel();
    }
}
