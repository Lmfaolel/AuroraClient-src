package dev.quelchi.aurora.mixins;

import com.google.common.hash.Hashing;
import dev.quelchi.aurora.duck.PackScanner;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.PackMixer;
import dev.quelchi.aurora.perf.PackScan;
import dev.quelchi.aurora.perf.Tweaks;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.pack.PackScreen;
import net.minecraft.client.gui.screen.pack.ResourcePackOrganizer;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.DirectionalLayoutWidget;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.resource.InputSupplier;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={PackScreen.class})
public abstract class PackScreenMixin {
    @Shadow
    @Final
    private Path field_25474;
    @Shadow
    @Final
    private Map<String, Identifier> field_25789;
    @Shadow
    @Final
    private static Identifier field_25786;
    @Shadow
    @Final
    private ResourcePackOrganizer field_25468;
    @Unique
    private final Set<String> aurora$reading = new HashSet<String>();
    @Unique
    private boolean aurora$scanning;
    @Unique
    private boolean aurora$scanAgain;

    @Shadow
    private void method_29680() {
    }

    @Inject(method={"method_29680"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$scanOffThread(CallbackInfo callbackInfo) {
        if (!Tweaks.PACK_SCAN || PackScan.skip) {
            return;
        }
        callbackInfo.cancel();
        this.aurora$redraw();
        if (this.aurora$scanning) {
            this.aurora$scanAgain = true;
            return;
        }
        this.aurora$scanning = true;
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        Screen screen = (Screen)this;
        PackScan.start(((PackScanner)this.field_25468).aurora$manager()).whenComplete((arg_0, arg_1) -> this.lambda$aurora$scanOffThread$1(minecraftClient, screen, arg_0, arg_1));
    }

    @Unique
    private void aurora$redraw() {
        PackScan.skip = true;
        try {
            this.method_29680();
        }
        finally {
            PackScan.skip = false;
        }
    }

    @ModifyArg(method={"method_25426"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_8132;method_48996(Lnet/minecraft/class_8021;)Lnet/minecraft/class_8021;")})
    private Widget aurora$mixerButton(Widget widget) {
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        if (widget instanceof DirectionalLayoutWidget) {
            DirectionalLayoutWidget directionalLayoutWidget = (DirectionalLayoutWidget)widget;
            if (this.field_25474.equals(minecraftClient.method_1479()) && Im.available()) {
                Screen screen = (Screen)this;
                directionalLayoutWidget.method_52736((Widget)ButtonWidget.method_46430((Text)Text.method_43470((String)"Pack Mixer"), arg_0 -> PackScreenMixin.lambda$aurora$mixerButton$2(minecraftClient, screen, arg_0)).method_46432(100).method_46431());
            }
        }
        return widget;
    }

    @Inject(method={"method_30287"}, at={@At(value="HEAD")}, cancellable=true)
    private void aurora$iconOffThread(ResourcePackProfile resourcePackProfile, CallbackInfoReturnable<Identifier> callbackInfoReturnable) {
        if (!Tweaks.PACK_ICONS) {
            return;
        }
        String string = resourcePackProfile.method_14463();
        Identifier identifier = this.field_25789.get(string);
        callbackInfoReturnable.setReturnValue((Object)(identifier != null ? identifier : field_25786));
        if (identifier != null || !this.aurora$reading.add(string)) {
            return;
        }
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        Screen screen = (Screen)this;
        Util.method_27958().execute(() -> this.lambda$aurora$iconOffThread$4(resourcePackProfile, minecraftClient, string, screen));
    }

    
    @Unique
    private static NativeImage aurora$readIcon(ResourcePackProfile resourcePackProfile) {
        try (ResourcePack resourcePack = resourcePackProfile.method_14458();){
            NativeImage nativeImage;
            block16: {
                InputSupplier inputSupplier = resourcePack.method_14410(new String[]{"pack.png"});
                if (inputSupplier == null) {
                    NativeImage nativeImage2 = null;
                    return nativeImage2;
                }
                InputStream inputStream = (InputStream)inputSupplier.get();
                try {
                    nativeImage = NativeImage.method_4309((InputStream)inputStream);
                    if (inputStream == null) break block16;
                }
                catch (Throwable throwable) {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                    }
                    throw throwable;
                }
                inputStream.close();
            }
            return nativeImage;
        }
        catch (Exception exception) {
            return null;
        }
    }

    private void lambda$aurora$iconOffThread$4(ResourcePackProfile resourcePackProfile, MinecraftClient minecraftClient, String string, Screen screen) {
        NativeImage nativeImage = PackScreenMixin.aurora$readIcon(resourcePackProfile);
        minecraftClient.execute(() -> this.lambda$aurora$iconOffThread$3(string, minecraftClient, screen, nativeImage));
    }

    private void lambda$aurora$iconOffThread$3(String string, MinecraftClient minecraftClient, Screen screen, NativeImage nativeImage) {
        this.aurora$reading.remove(string);
        if (minecraftClient.field_1755 != screen) {
            if (nativeImage != null) {
                nativeImage.close();
            }
            return;
        }
        if (nativeImage == null) {
            this.field_25789.put(string, field_25786);
            return;
        }
        Identifier identifier = Identifier.method_60655((String)"aurora", (String)("pack_icon/" + String.valueOf(Hashing.sha1().hashUnencodedChars((CharSequence)string))));
        minecraftClient.method_1531().method_4616(identifier, (AbstractTexture)new NativeImageBackedTexture(() -> ((Identifier)identifier).toString(), nativeImage));
        this.field_25789.put(string, identifier);
    }

    private static void lambda$aurora$mixerButton$2(MinecraftClient minecraftClient, Screen screen, ButtonWidget buttonWidget) {
        minecraftClient.method_1507((Screen)new PackMixer(screen));
    }

    private void lambda$aurora$scanOffThread$1(MinecraftClient minecraftClient, Screen screen, Void void_, Throwable throwable) {
        minecraftClient.execute(() -> this.lambda$aurora$scanOffThread$0(minecraftClient, screen));
    }

    private void lambda$aurora$scanOffThread$0(MinecraftClient minecraftClient, Screen screen) {
        this.aurora$scanning = false;
        if (minecraftClient.field_1755 != screen) {
            return;
        }
        this.aurora$redraw();
        if (this.aurora$scanAgain) {
            this.aurora$scanAgain = false;
            this.method_29680();
        }
    }
}
