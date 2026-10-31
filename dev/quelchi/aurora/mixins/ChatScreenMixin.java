package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.chat.CapsCommands;
import dev.quelchi.aurora.chat.EmojiSuggest;
import dev.quelchi.aurora.module.modules.ChatModule;
import dev.quelchi.aurora.module.modules.GuiTweaksModule;
import dev.quelchi.aurora.module.modules.GuiTweaksModule$Key;
import dev.quelchi.aurora.module.modules.GuiTweaksModule$Part;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.input.KeyInput;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ChatScreen.class})
public abstract class ChatScreenMixin {
    @Shadow
    @Final
    protected TextFieldWidget field_2382;

    @Inject(method={"method_25426"}, at={@At(value="HEAD")})
    private void aurora$capsReset(CallbackInfo callbackInfo) {
        CapsCommands.reset();
    }

    @ModifyArg(method={"method_44056"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_634;method_45730(Ljava/lang/String;)V")})
    private String aurora$capsCommand(String string) {
        return CapsCommands.fix(string);
    }

    @ModifyArg(method={"method_25402(Lnet/minecraft/class_11909;Z)Z"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_12225$class_12226;<init>(Lnet/minecraft/class_327;II)V")}, index=1)
    private int aurora$chatClickX(int n) {
        GuiTweaksModule$Part guiTweaksModule$Part = GuiTweaksModule.partOrNull(GuiTweaksModule$Key.CHAT);
        return guiTweaksModule$Part == null || guiTweaksModule$Part.isDefault() ? n : guiTweaksModule$Part.toLocalX(n);
    }

    @ModifyArg(method={"method_25402(Lnet/minecraft/class_11909;Z)Z"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_12225$class_12226;<init>(Lnet/minecraft/class_327;II)V")}, index=2)
    private int aurora$chatClickY(int n) {
        GuiTweaksModule$Part guiTweaksModule$Part = GuiTweaksModule.partOrNull(GuiTweaksModule$Key.CHAT);
        return guiTweaksModule$Part == null || guiTweaksModule$Part.isDefault() ? n : guiTweaksModule$Part.toLocalY(n);
    }

    @Inject(method={"method_25394"}, at={@At(value="TAIL")})
    private void aurora$emojiStrip(DrawContext drawContext, int n, int n2, float f, CallbackInfo callbackInfo) {
        EmojiSuggest.update(this.field_2382.method_1882(), this.field_2382.method_1881());
        EmojiSuggest.render(drawContext, ((ChatScreen)this).field_22790);
    }

    @Inject(method={"method_25404"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$emojiKeys(KeyInput keyInput, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        ChatModule chatModule = ChatModule.get();
        if (chatModule != null && chatModule.translateTyped(keyInput.comp_4795(), this.field_2382)) {
            callbackInfoReturnable.setReturnValue((Object)true);
            return;
        }
        if (!EmojiSuggest.open()) {
            return;
        }
        switch (keyInput.comp_4795()) {
            case 257: 
            case 258: {
                String string = EmojiSuggest.complete(this.field_2382.method_1882(), this.field_2382.method_1881());
                if (string == null) {
                    return;
                }
                this.field_2382.method_1852(string);
                this.field_2382.method_1883(EmojiSuggest.caret(), false);
                EmojiSuggest.close();
                callbackInfoReturnable.setReturnValue((Object)true);
                break;
            }
            case 263: {
                EmojiSuggest.move(-1);
                callbackInfoReturnable.setReturnValue((Object)true);
                break;
            }
            case 262: {
                EmojiSuggest.move(1);
                callbackInfoReturnable.setReturnValue((Object)true);
                break;
            }
            case 256: {
                EmojiSuggest.dismiss();
                callbackInfoReturnable.setReturnValue((Object)true);
                break;
            }
        }
    }
}
