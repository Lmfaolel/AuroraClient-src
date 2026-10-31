package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.quelchi.aurora.prediction.ac.InputAudit;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value={MinecraftClient.class})
public abstract class InputAuditMixin {
    @WrapMethod(method={"method_1583()V"})
    private void aurora$auditUse(Operation<Void> operation) {
        InputAudit.enterVanilla(((MinecraftClient)this).field_1690.field_1904);
        try {
            operation.call(new Object[0]);
        }
        finally {
            InputAudit.exitVanilla();
        }
    }

    @WrapMethod(method={"method_1536()Z"})
    private boolean aurora$auditAttack(Operation<Boolean> operation) {
        InputAudit.enterVanilla(((MinecraftClient)this).field_1690.field_1886);
        try {
            boolean bl = (Boolean)operation.call(new Object[0]);
            return bl;
        }
        finally {
            InputAudit.exitVanilla();
        }
    }
}
