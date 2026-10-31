package dev.quelchi.aurora.mixins;

import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.Mc;
import dev.quelchi.aurora.gui.im.PauseScreen;
import dev.quelchi.aurora.gui.im.TitleMenu;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value={MinecraftClient.class})
public abstract class ImMenusMixin {
    @ModifyVariable(method={"method_1507"}, at={@At(value="HEAD")}, argsOnly=true)
    private Screen aurora$ownMenus(Screen screen) {
        GameMenuScreen gameMenuScreen;
        if (!Im.available()) {
            return screen;
        }
        MinecraftClient minecraftClient = (MinecraftClient)this;
        if (screen instanceof TitleScreen || screen == null && minecraftClient.field_1687 == null) {
            return new TitleMenu();
        }
        if (screen instanceof GameMenuScreen && (gameMenuScreen = (GameMenuScreen)screen).method_53558()) {
            return new PauseScreen();
        }
        gameMenuScreen = screen == null ? null : Mc.own(screen);
        return gameMenuScreen != null ? gameMenuScreen : screen;
    }
}
