package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.prediction.ac.InputAudit;
import net.minecraft.client.Keyboard;
import net.minecraft.client.input.KeyInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Keyboard.class})
public abstract class KeyboardAuditMixin {
    @Inject(method={"method_1466(JILnet/minecraft/class_11908;)V"}, at={@At(value="HEAD")})
    private void aurora$auditKey(long l, int n, KeyInput keyInput, CallbackInfo callbackInfo) {
        InputAudit.onKey(l, keyInput.comp_4795(), keyInput.comp_4796(), n);
    }
}
