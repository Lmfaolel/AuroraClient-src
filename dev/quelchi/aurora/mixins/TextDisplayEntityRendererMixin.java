package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.module.modules.NametagsModule;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.entity.DisplayEntityRenderer;
import net.minecraft.client.render.entity.state.TextDisplayEntityRenderState;
import net.minecraft.entity.decoration.DisplayEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={DisplayEntityRenderer.TextDisplayEntityRenderer.class})
public abstract class TextDisplayEntityRendererMixin {
    @Shadow
    @Final
    private TextRenderer field_42531;

    @Inject(method={"method_62409(Lnet/minecraft/class_8113$class_8123;Lnet/minecraft/class_10071;F)V"}, at={@At(value="RETURN")})
    private void aurora$decorateRiderTag(DisplayEntity.TextDisplayEntity textDisplayEntity, TextDisplayEntityRenderState textDisplayEntityRenderState, float f, CallbackInfo callbackInfo) {
        PlayerEntity playerEntity;
        Object object;
        block7: {
            block6: {
                object = textDisplayEntity.method_5854();
                if (!(object instanceof PlayerEntity)) break block6;
                playerEntity = (PlayerEntity)object;
                if (textDisplayEntityRenderState.field_53589 != null) break block7;
            }
            return;
        }
        object = NametagsModule.get();
        if (object == null) {
            return;
        }
        List list = textDisplayEntityRenderState.field_53589.comp_1247();
        int n = NametagsModule.nameLine(list, playerEntity.method_7334().name());
        if (n < 0) {
            return;
        }
        Text text = NametagsModule.textOf(((DisplayEntity.TextDisplayEntity.TextLine)list.get(n)).comp_1249());
        Text text2 = ((NametagsModule)object).decorate(text, playerEntity.method_5667());
        if (text2 == text) {
            return;
        }
        OrderedText orderedText = text2.method_30937();
        int n2 = this.field_42531.method_30880(orderedText);
        ArrayList<DisplayEntity.TextDisplayEntity.TextLine> arrayList = new ArrayList<DisplayEntity.TextDisplayEntity.TextLine>(list);
        arrayList.set(n, new DisplayEntity.TextDisplayEntity.TextLine(orderedText, n2));
        textDisplayEntityRenderState.field_53589 = new DisplayEntity.TextDisplayEntity.TextLines(arrayList, Math.max(textDisplayEntityRenderState.field_53589.comp_1248(), n2));
    }
}
