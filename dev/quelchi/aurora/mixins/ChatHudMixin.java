package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.mixins.ChatHudAccessor;
import dev.quelchi.aurora.module.ModuleManager;
import dev.quelchi.aurora.module.modules.AntiAltModule;
import dev.quelchi.aurora.module.modules.ChatModule;
import dev.quelchi.aurora.module.modules.GuiTweaksModule;
import dev.quelchi.aurora.module.modules.GuiTweaksModule$Key;
import dev.quelchi.aurora.module.modules.GuiTweaksModule$Part;
import java.util.List;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ChatHud.class})
public abstract class ChatHudMixin {
    @Unique
    private boolean aurora$chatPushed;

    @ModifyVariable(method={"method_44811(Lnet/minecraft/class_2561;Lnet/minecraft/class_7469;Lnet/minecraft/class_7591;)V"}, at={@At(value="HEAD")}, argsOnly=true, ordinal=0)
    private Text divine$spoofChatName(Text text) {
        AntiAltModule antiAltModule = AntiAltModule.get();
        Text text2 = antiAltModule != null ? antiAltModule.replaceInChat(text) : text;
        ChatModule chatModule = ChatModule.get();
        if (chatModule != null) {
            chatModule.checkMention(text);
            text2 = chatModule.decorate(text2);
        }
        return text2;
    }

    @Inject(method={"method_44811(Lnet/minecraft/class_2561;Lnet/minecraft/class_7469;Lnet/minecraft/class_7591;)V"}, at={@At(value="RETURN")})
    private void aurora$translate(Text text, MessageSignatureData messageSignatureData, MessageIndicator messageIndicator, CallbackInfo callbackInfo) {
        ChatModule chatModule = ChatModule.get();
        List<ChatHudLine> list = ((ChatHudAccessor)((Object)this)).aurora$messages();
        if (chatModule != null && !list.isEmpty()) {
            chatModule.translateLine(list.get(0));
        }
    }

    @Inject(method={"method_75804(Lnet/minecraft/class_332;Lnet/minecraft/class_327;IIIZZ)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$hideChat(DrawContext drawContext, TextRenderer textRenderer, int n, int n2, int n3, boolean bl, boolean bl2, CallbackInfo callbackInfo) {
        boolean bl3;
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        boolean bl4 = minecraftClient.field_1755 instanceof ChatScreen;
        boolean bl5 = bl3 = GuiTweaksModule.partHidden(GuiTweaksModule$Key.CHAT) && !ModuleManager.editing();
        if ((ChatModule.chatHidden() || bl3) && !bl4) {
            callbackInfo.cancel();
            return;
        }
        this.aurora$placeChat(drawContext);
    }

    @Unique
    private void aurora$placeChat(DrawContext drawContext) {
        this.aurora$chatPushed = false;
        GuiTweaksModule$Part guiTweaksModule$Part = GuiTweaksModule.partOrNull(GuiTweaksModule$Key.CHAT);
        if (guiTweaksModule$Part == null) {
            return;
        }
        int[] nArray = guiTweaksModule$Part.getLastBounds();
        guiTweaksModule$Part.reportAnchor(2, drawContext.method_51443() - 48, nArray[2], nArray[3]);
        if (guiTweaksModule$Part.isDefault()) {
            return;
        }
        drawContext.method_51448().pushMatrix();
        this.aurora$chatPushed = true;
        guiTweaksModule$Part.applyTransform(drawContext);
    }

    @Inject(method={"method_75804(Lnet/minecraft/class_332;Lnet/minecraft/class_327;IIIZZ)V"}, at={@At(value="RETURN")})
    private void aurora$chatReturn(DrawContext drawContext, TextRenderer textRenderer, int n, int n2, int n3, boolean bl, boolean bl2, CallbackInfo callbackInfo) {
        if (this.aurora$chatPushed) {
            drawContext.method_51448().popMatrix();
            this.aurora$chatPushed = false;
        }
    }
}
