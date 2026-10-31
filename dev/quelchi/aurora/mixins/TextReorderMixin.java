package dev.quelchi.aurora.mixins;

import com.ibm.icu.lang.UCharacter;
import java.util.List;
import net.minecraft.client.resource.language.ReorderingUtil;
import net.minecraft.text.OrderedText;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.TextReorderingProcessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ReorderingUtil.class})
public abstract class TextReorderMixin {
    @Inject(method={"method_30922(Lnet/minecraft/class_5348;Z)Lnet/minecraft/class_5481;"}, at={@At(value="HEAD")}, cancellable=true)
    private static void aurora$plainRun(StringVisitable stringVisitable, boolean bl, CallbackInfoReturnable<OrderedText> callbackInfoReturnable) {
        if (bl) {
            return;
        }
        TextReorderingProcessor textReorderingProcessor = TextReorderingProcessor.method_30943((StringVisitable)stringVisitable, UCharacter::getMirror, TextReorderMixin::lambda$aurora$plainRun$0);
        String string = textReorderingProcessor.method_30939();
        for (int i = 0; i < string.length(); ++i) {
            if (string.charAt(i) < '\u0590') continue;
            return;
        }
        callbackInfoReturnable.setReturnValue((Object)OrderedText.method_30749((List)textReorderingProcessor.method_30940(0, string.length(), false)));
    }

    private static String lambda$aurora$plainRun$0(String string) {
        return string;
    }
}
