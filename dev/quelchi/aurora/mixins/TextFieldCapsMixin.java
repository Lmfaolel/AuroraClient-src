package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.chat.CapsCommands;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.input.CharInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={TextFieldWidget.class})
public abstract class TextFieldCapsMixin {
    @Inject(method={"method_25400"}, at={@At(value="HEAD")})
    private void aurora$capsState(CharInput charInput, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (MinecraftClient.method_1551().field_1755 instanceof ChatScreen) {
            CapsCommands.typed(charInput.comp_4793(), charInput.comp_4794());
        }
    }
}
