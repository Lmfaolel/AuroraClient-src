package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.quelchi.aurora.social.FeaturedServers;
import java.util.List;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerServerListWidget;
import net.minecraft.client.gui.widget.EntryListWidget;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={EntryListWidget.class})
public abstract class ServerListHeadingMixin {
    private static final int STRIP = 12;
    private static final int GAP = 12;
    @Shadow
    @Final
    protected int field_62109;

    @Inject(method={"method_73367"}, at={@At(value="HEAD")})
    private void aurora$featuredGap(CallbackInfo callbackInfo) {
        ServerListHeadingMixin serverListHeadingMixin = this;
        if (!(serverListHeadingMixin instanceof MultiplayerServerListWidget)) {
            return;
        }
        MultiplayerServerListWidget multiplayerServerListWidget = (MultiplayerServerListWidget)serverListHeadingMixin;
        int n = ServerListHeadingMixin.aurora$lastFeatured(multiplayerServerListWidget);
        List list = multiplayerServerListWidget.method_25396();
        for (int i = 0; i < list.size(); ++i) {
            ((MultiplayerServerListWidget.Entry)list.get(i)).method_73383(i == n ? this.field_62109 + 12 : this.field_62109);
        }
    }

    @Inject(method={"method_73367"}, at={@At(value="TAIL")})
    private void aurora$makeRoomForHeading(CallbackInfo callbackInfo) {
        ServerListHeadingMixin serverListHeadingMixin = this;
        if (!(serverListHeadingMixin instanceof MultiplayerServerListWidget)) {
            return;
        }
        MultiplayerServerListWidget multiplayerServerListWidget = (MultiplayerServerListWidget)serverListHeadingMixin;
        if (!ServerListHeadingMixin.aurora$hasFeatured(multiplayerServerListWidget)) {
            return;
        }
        for (MultiplayerServerListWidget.Entry entry : multiplayerServerListWidget.method_25396()) {
            entry.method_46419(entry.method_46427() + 12);
        }
    }

    @ModifyReturnValue(method={"method_44395"}, at={@At(value="RETURN")})
    private int aurora$tallerContents(int n) {
        ServerListHeadingMixin serverListHeadingMixin = this;
        if (!(serverListHeadingMixin instanceof MultiplayerServerListWidget)) {
            return n;
        }
        MultiplayerServerListWidget multiplayerServerListWidget = (MultiplayerServerListWidget)serverListHeadingMixin;
        return ServerListHeadingMixin.aurora$hasFeatured(multiplayerServerListWidget) ? n + 12 : n;
    }

    @Unique
    private static boolean aurora$hasFeatured(MultiplayerServerListWidget multiplayerServerListWidget) {
        MultiplayerServerListWidget.ServerEntry serverEntry;
        Object e;
        List list = multiplayerServerListWidget.method_25396();
        return !list.isEmpty() && (e = list.get(0)) instanceof MultiplayerServerListWidget.ServerEntry && FeaturedServers.is((serverEntry = (MultiplayerServerListWidget.ServerEntry)e).method_20133());
    }

    @Unique
    private static int aurora$lastFeatured(MultiplayerServerListWidget multiplayerServerListWidget) {
        MultiplayerServerListWidget.ServerEntry serverEntry;
        Object e;
        List list = multiplayerServerListWidget.method_25396();
        int n = -1;
        int n2 = 0;
        while (n2 < list.size() && (e = list.get(n2)) instanceof MultiplayerServerListWidget.ServerEntry && FeaturedServers.is((serverEntry = (MultiplayerServerListWidget.ServerEntry)e).method_20133())) {
            n = n2++;
        }
        return n >= 0 && n < list.size() - 1 ? n : -1;
    }

    @Inject(method={"method_25311"}, at={@At(value="TAIL")})
    private void aurora$featuredEdge(DrawContext drawContext, int n, int n2, float f, CallbackInfo callbackInfo) {
        ServerListHeadingMixin serverListHeadingMixin = this;
        if (!(serverListHeadingMixin instanceof MultiplayerServerListWidget)) {
            return;
        }
        MultiplayerServerListWidget multiplayerServerListWidget = (MultiplayerServerListWidget)serverListHeadingMixin;
        int n3 = ServerListHeadingMixin.aurora$lastFeatured(multiplayerServerListWidget);
        if (n3 < 0) {
            return;
        }
        int n4 = ((MultiplayerServerListWidget.Entry)multiplayerServerListWidget.method_25396().get(n3)).method_46427() + this.field_62109 + 6;
        int n5 = multiplayerServerListWidget.method_25342();
        drawContext.method_25294(n5, n4, n5 + multiplayerServerListWidget.method_25322(), n4 + 1, 580034226);
    }

    @Inject(method={"method_25311"}, at={@At(value="HEAD")})
    private void aurora$featuredHeading(DrawContext drawContext, int n, int n2, float f, CallbackInfo callbackInfo) {
        ServerListHeadingMixin serverListHeadingMixin = this;
        if (!(serverListHeadingMixin instanceof MultiplayerServerListWidget)) {
            return;
        }
        MultiplayerServerListWidget multiplayerServerListWidget = (MultiplayerServerListWidget)serverListHeadingMixin;
        if (!ServerListHeadingMixin.aurora$hasFeatured(multiplayerServerListWidget)) {
            return;
        }
        if (multiplayerServerListWidget.method_44387() > 0.5) {
            return;
        }
        serverListHeadingMixin = MinecraftClient.method_1551();
        int n3 = multiplayerServerListWidget.method_25342();
        int n4 = n3 + multiplayerServerListWidget.method_25322();
        int n5 = multiplayerServerListWidget.method_46427() + 2;
        String string = "Featured";
        drawContext.method_51433(((MinecraftClient)serverListHeadingMixin).field_1772, string, n3, n5, -6972246, false);
        drawContext.method_25294(n3 + ((MinecraftClient)serverListHeadingMixin).field_1772.method_1727(string) + 6, n5 + 4, n4, n5 + 5, 580034226);
    }
}
