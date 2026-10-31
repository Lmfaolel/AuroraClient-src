package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.gui.shell.AccountFlow;
import net.minecraft.client.network.ClientLoginNetworkHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TextContent;
import net.minecraft.text.TranslatableTextContent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ClientLoginNetworkHandler.class})
public abstract class ClientLoginNetworkHandlerMixin {
    @Inject(method={"method_2892(Ljava/lang/String;)Lnet/minecraft/class_2561;"}, at={@At(value="RETURN")})
    private void aurora$renewSession(String string, CallbackInfoReturnable<Text> callbackInfoReturnable) {
        if (ClientLoginNetworkHandlerMixin.aurora$invalidSession((Text)callbackInfoReturnable.getReturnValue())) {
            AccountFlow.renewSession();
        }
    }

    @Unique
    private static boolean aurora$invalidSession(Text text) {
        Object[] objectArray;
        if (text == null || !((objectArray = text.method_10851()) instanceof TranslatableTextContent)) {
            return false;
        }
        TranslatableTextContent translatableTextContent = (TranslatableTextContent)objectArray;
        for (Object object : translatableTextContent.method_11023()) {
            TranslatableTextContent translatableTextContent2;
            Text text2;
            TextContent textContent;
            if (!(object instanceof Text) || !((textContent = (text2 = (Text)object).method_10851()) instanceof TranslatableTextContent) || !"disconnect.loginFailedInfo.invalidSession".equals((translatableTextContent2 = (TranslatableTextContent)textContent).method_11022())) continue;
            return true;
        }
        return false;
    }
}
