package dev.quelchi.aurora.mixins;

import java.util.List;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.ChatHudLine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={ChatHud.class})
public interface ChatHudAccessor {
    @Accessor(value="field_2061")
    public List<ChatHudLine> aurora$messages();

    @Invoker(value="method_44813")
    public void aurora$refresh();
}
