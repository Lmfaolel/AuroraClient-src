package dev.quelchi.aurora;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.quelchi.aurora.Aurora$1;
import dev.quelchi.aurora.Aurora$2;
import dev.quelchi.aurora.auth.Accounts;
import dev.quelchi.aurora.config.ConfigManager;
import dev.quelchi.aurora.cosmetics.backend.CosmeticsSync;
import dev.quelchi.aurora.cosmetics.content.Emotes;
import dev.quelchi.aurora.cosmetics.renderer.EmoteEngine;
import dev.quelchi.aurora.cosmetics.renderer.MaceEffects;
import dev.quelchi.aurora.cosmetics.renderer.wings.WingFeature;
import dev.quelchi.aurora.glass.GlassKit;
import dev.quelchi.aurora.gui.Mannequin;
import dev.quelchi.aurora.gui.PredictionFx;
import dev.quelchi.aurora.gui.Round;
import dev.quelchi.aurora.gui.SocialToast;
import dev.quelchi.aurora.gui.TitleHome;
import dev.quelchi.aurora.gui.ToggleDisplay;
import dev.quelchi.aurora.gui.UpdatePrompt;
import dev.quelchi.aurora.gui.VectorIcons;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.ImScreen;
import dev.quelchi.aurora.gui.im.ModsScreen;
import dev.quelchi.aurora.gui.lq.AuroraGlass;
import dev.quelchi.aurora.gui.lq.HudBridge;
import dev.quelchi.aurora.gui.shell.RewardsBadge;
import dev.quelchi.aurora.input.AuroraKeys;
import dev.quelchi.aurora.input.EmoteKey;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.ModuleManager;
import dev.quelchi.aurora.module.modules.MusicPlayerModule;
import dev.quelchi.aurora.module.modules.RendersModule;
import dev.quelchi.aurora.module.modules.ScreenshotModule;
import dev.quelchi.aurora.optimize.ConsumableOptimizer;
import dev.quelchi.aurora.optimize.CrystalPrediction;
import dev.quelchi.aurora.optimize.GhostAnchor;
import dev.quelchi.aurora.optimize.KeptCrystals;
import dev.quelchi.aurora.optimize.MarlowProtocol;
import dev.quelchi.aurora.optimize.ProjectileOptimizer;
import dev.quelchi.aurora.perf.WinTuning;
import dev.quelchi.aurora.prediction.GlidePrediction;
import dev.quelchi.aurora.prediction.MeleePrediction;
import dev.quelchi.aurora.prediction.Prediction;
import dev.quelchi.aurora.prediction.PredictionHandshake;
import dev.quelchi.aurora.prediction.ac.AntiCheat;
import dev.quelchi.aurora.render.AuroraSky;
import dev.quelchi.aurora.render.CloudLayer;
import dev.quelchi.aurora.render.EffectEngine;
import dev.quelchi.aurora.render.HitboxRenderer;
import dev.quelchi.aurora.render.MarkerRenderer;
import dev.quelchi.aurora.render.PlayerMask;
import dev.quelchi.aurora.render.SkyExtras;
import dev.quelchi.aurora.render.StarField;
import dev.quelchi.aurora.render.StormRenderer;
import dev.quelchi.aurora.render.TrailRenderer;
import dev.quelchi.aurora.render.WindowIcon;
import dev.quelchi.aurora.social.Social;
import dev.quelchi.aurora.social.WorldShare;
import dev.quelchi.aurora.util.BorderlessFit;
import dev.quelchi.aurora.util.CrashReports;
import dev.quelchi.aurora.util.KeyUtil;
import dev.quelchi.aurora.util.TotemPops;
import dev.quelchi.aurora.util.Updater;
import java.util.Map;
import java.util.UUID;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderEvents;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.resource.ResourceType;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Aurora
implements ClientModInitializer {
    public static final Logger LOG;
    public static Aurora INSTANCE;
    public static MinecraftClient mc;
    public static boolean booted;
    private int openGuiKey = (int)c;
    private boolean openKeyWasDown;
    private ModuleManager moduleManager;
    public static final String COSMETICS_URL;
    private boolean worldWasLoaded;
    public static boolean a;
    private static final String[] b;
    private static final long c;

    public void onInitializeClient() {
        block12: {
            boolean bl;
            block14: {
                block13: {
                    boolean bl2;
                    block10: {
                        block11: {
                            GlassKit.init();
                            INSTANCE = this;
                            bl2 = a;
                            mc = MinecraftClient.method_1551();
                            if (bl2) break block10;
                            if (!Boolean.getBoolean(b[32])) break block11;
                            if (Util.method_668() == Util.OperatingSystem.field_1137) break block11;
                            System.setProperty(b[30], b[14]);
                        }
                        this.moduleManager = new ModuleManager();
                        ConfigManager.load();
                        Accounts.load();
                        MarlowProtocol.register();
                        PredictionHandshake.register();
                        Prediction.register();
                        GlidePrediction.register();
                        MeleePrediction.register();
                        AntiCheat.register();
                        KeptCrystals.register();
                        GhostAnchor.register();
                        ConsumableOptimizer.register();
                        CrystalPrediction.register();
                        ProjectileOptimizer.register();
                        BlockRenderLayerMap.putBlock((Block)Blocks.field_10343, (BlockRenderLayer)BlockRenderLayer.field_60926);
                        AuroraKeys.register();
                        ClientTickEvents.END_CLIENT_TICK.register(this::onEndTick);
                        ClientTickEvents.END_CLIENT_TICK.register(Emotes::tick);
                        ClientTickEvents.END_CLIENT_TICK.register(EmoteKey::tick);
                        ClientTickEvents.END_CLIENT_TICK.register(Aurora::lambda$onInitializeClient$0);
                        VectorIcons.warm();
                        ClientTickEvents.END_CLIENT_TICK.register(Aurora::lambda$onInitializeClient$1);
                        ClientTickEvents.END_CLIENT_TICK.register(Aurora::lambda$onInitializeClient$2);
                        ClientTickEvents.END_CLIENT_TICK.register(Aurora::lambda$onInitializeClient$3);
                        HudRenderCallback.EVENT.register(this::lambda$onInitializeClient$4);
                        WorldRenderEvents.AFTER_ENTITIES.register(PlayerMask::onWorldRender);
                        WorldRenderEvents.AFTER_ENTITIES.register(StarField::onWorldRender);
                        WorldRenderEvents.AFTER_ENTITIES.register(AuroraSky::onWorldRender);
                        WorldRenderEvents.AFTER_ENTITIES.register(SkyExtras::onWorldRender);
                        WorldRenderEvents.AFTER_ENTITIES.register(CloudLayer::onWorldRender);
                        WorldRenderEvents.END_MAIN.register(StormRenderer::onWorldRender);
                        WorldRenderEvents.END_MAIN.register(MusicPlayerModule::onWorldRender);
                        WorldRenderEvents.AFTER_ENTITIES.register(MarkerRenderer::onWorldRender);
                        WorldRenderEvents.AFTER_ENTITIES.register(HitboxRenderer::onWorldRender);
                        WorldRenderEvents.AFTER_ENTITIES.register(TrailRenderer::onWorldRender);
                        WorldRenderEvents.AFTER_ENTITIES.register(EffectEngine::onWorldRender);
                        ClientPlayConnectionEvents.DISCONNECT.register(Aurora::lambda$onInitializeClient$5);
                        WingFeature.register();
                        WorldRenderEvents.BEFORE_ENTITIES.register(Aurora::lambda$onInitializeClient$6);
                        WorldRenderEvents.BEFORE_ENTITIES.register(Aurora::lambda$onInitializeClient$7);
                        ClientCommandRegistrationCallback.EVENT.register(Aurora::lambda$onInitializeClient$11);
                        AuroraGlass.init();
                        ClientCommandRegistrationCallback.EVENT.register(Aurora::lambda$onInitializeClient$13);
                        ClientCommandRegistrationCallback.EVENT.register(Aurora::lambda$onInitializeClient$15);
                        TitleHome.removeOldFiles();
                        String[] stringArray = b;
                        CosmeticsSync.configure(stringArray[15]);
                        CosmeticsSync.loadCachedContent();
                        CosmeticsSync.fetchReleases();
                        CrashReports.scan();
                        Aurora.preloadGuiClasses();
                        ResourceManagerHelper.get((ResourceType)ResourceType.field_14188).registerReloadListener((IdentifiableResourceReloadListener)new Aurora$1(this));
                        booted = true;
                        ClientTickEvents.END_CLIENT_TICK.register((Object)new Aurora$2(this));
                        WinTuning.apply();
                        LOG.info(stringArray[16], (Object)this.moduleManager.getModules().size());
                    }
                    if (!bl2) break block12;
                    if (!Module.a) break block13;
                    bl = false;
                    break block14;
                }
                bl = true;
            }
            Module.a = bl;
        }
    }

    private static void preloadGuiClasses() {
        String[] stringArray = new String[8];
        String[] stringArray2 = b;
        stringArray[0] = stringArray2[17];
        stringArray[1] = stringArray2[25];
        stringArray[2] = stringArray2[5];
        stringArray[3] = stringArray2[26];
        stringArray[4] = stringArray2[3];
        stringArray[5] = stringArray2[18];
        stringArray[6] = stringArray2[4];
        stringArray[7] = stringArray2[13];
        String[] stringArray3 = stringArray;
        ClassLoader classLoader = Aurora.class.getClassLoader();
        String[] stringArray4 = stringArray3;
        boolean bl = a;
        for (String string : stringArray4) {
            try {
                Class.forName(string, true, classLoader);
                continue;
            }
            catch (Throwable throwable) {
                LOG.warn(b[1], (Object)string, (Object)throwable);
            }
            if (!bl) continue;
        }
    }

    
    private void onEndTick(MinecraftClient var1_1) {
        block65: {
            block63: {
                block64: {
                    block55: {
                        block62: {
                            block57: {
                                block56: {
                                    block58: {
                                        block60: {
                                            block61: {
                                                block59: {
                                                    block54: {
                                                        block53: {
                                                            var3_2 = AuroraKeys.menuCode();
                                                            var2_3 = Aurora.a;
                                                            v0 = var3_2;
                                                            if (var2_3) break block53;
                                                            if (v0 != -1) {
                                                            }
                                                            v2 = var3_2;
                                                            if (var2_3) break block54;
                                                            v0 = (int)KeyUtil.isKeyPressed(v2);
                                                        }
                                                        if (v0 != 0) {
                                                            v2 = 1;
                                                        } else lbl_neg_1000:

                                                        {
                                                            v2 = 0;
                                                        }
                                                    }
                                                    var4_4 = v2;
                                                    if (var2_3) break block55;
                                                    if (var4_4 == 0) break block56;
                                                    v6 = this;
                                                    if (var2_3) break block57;
                                                    if (v6.openKeyWasDown) break block56;
                                                    v9 = var1_1;
                                                    if (var2_3) break block58;
                                                    if (v9.field_1755 == null) {
                                                    }
                                                    if (!Im.available()) break block59;
                                                    var1_1.method_1507((Screen)new ModsScreen());
                                                    if (!var2_3) break block56;
                                                }
                                                v14 = var1_1.method_1566();
                                                v15 = SystemToast.Type.field_47588;
                                                v16 = Text.method_43470((String)Aurora.b[31]);
                                                v17 = Aurora.b[20];
                                                if (var2_3) break block60;
                                                if (System.getenv(v17) == null) break block61;
                                                v17 = Aurora.b[29];
                                                break block60;
                                            }
                                            v17 = Aurora.b[0];
                                        }
                                        SystemToast.method_1990((ToastManager)v14, (SystemToast.Type)v15, (Text)v16, (Text)Text.method_43470((String)v17));
                                        if (!var2_3) break block56;
lbl95:

                                        v9 = var1_1;
                                    }
                                    var6_5 = v9.field_1755;
                                    if (var2_3) break block55;
                                    if (!(var6_5 instanceof ImScreen)) break block56;
                                    var5_6 = (ImScreen)var6_5;
                                    if (var2_3) break block55;
                                    if (!var5_6.shutsOnMenuKey()) break block56;
                                    v24 = Im.typing();
                                    if (var2_3) break block62;
                                    if (v24) break block56;
                                    var5_6.method_25419();
                                }
                                this.openKeyWasDown = var4_4;
                                v6 = this;
                            }
                            v6.moduleManager.onTick();
                            BorderlessFit.tick(var1_1);
                            TrailRenderer.tick();
                            MaceEffects.tick();
                            v24 = false;
                        }
                        Social.tick(v24);
                        WorldShare.tick();
                        if (var2_3) break block63;
                        RewardsBadge.tick();
                    }
                    if (var1_1.field_1687 == null) {
                        v29 = this;
                        if (var2_3) break block64;
                    }
                    if (!v29.worldWasLoaded) break block65;
                    CosmeticsSync.reset();
                    Social.clear();
                    v29 = this;
                }
                v29.worldWasLoaded = false;
                if (!var2_3) break block65;
lbl173:

                this.worldWasLoaded = true;
            }
            CosmeticsSync.tick();
        }
    }

    public ModuleManager getModuleManager() {
        return this.moduleManager;
    }

    public int getOpenGuiKey() {
        return this.openGuiKey;
    }

    public void setOpenGuiKey(int n) {
        this.openGuiKey = n;
    }

    private static void lambda$onInitializeClient$15(CommandDispatcher commandDispatcher, CommandRegistryAccess commandRegistryAccess) {
        String[] stringArray = b;
        commandDispatcher.register((LiteralArgumentBuilder)ClientCommandManager.literal((String)stringArray[23]).then(ClientCommandManager.argument((String)stringArray[2], (ArgumentType)StringArgumentType.greedyString()).executes(Aurora::lambda$onInitializeClient$14)));
    }

    private static int lambda$onInitializeClient$14(CommandContext commandContext) throws CommandSyntaxException {
        Object object;
        FabricClientCommandSource fabricClientCommandSource;
        block13: {
            String string;
            block11: {
                String string2;
                block12: {
                    String string3;
                    boolean bl;
                    block10: {
                        ScreenshotModule screenshotModule;
                        block8: {
                            ScreenshotModule screenshotModule2;
                            block9: {
                                screenshotModule2 = ScreenshotModule.get();
                                bl = a;
                                screenshotModule = screenshotModule2;
                                if (bl) break block8;
                                if (screenshotModule != null) break block9;
                                string3 = b[6];
                                break block10;
                            }
                            screenshotModule = screenshotModule2;
                        }
                        String[] stringArray = b;
                        string3 = screenshotModule.delete(StringArgumentType.getString((CommandContext)commandContext, (String)stringArray[34]));
                    }
                    string2 = string3;
                    fabricClientCommandSource = (FabricClientCommandSource)commandContext.getSource();
                    string = string2;
                    if (bl) break block11;
                    if (string != null) break block12;
                    object = b[9];
                    break block13;
                }
                string = string2;
            }
            object = b[8] + string;
        }
        fabricClientCommandSource.sendFeedback((Text)Text.method_43470((String)object));
        return 1;
    }

    private static void lambda$onInitializeClient$13(CommandDispatcher commandDispatcher, CommandRegistryAccess commandRegistryAccess) {
        commandDispatcher.register((LiteralArgumentBuilder)ClientCommandManager.literal((String)b[7]).executes(Aurora::lambda$onInitializeClient$12));
    }

    private static int lambda$onInitializeClient$12(CommandContext commandContext) throws CommandSyntaxException {
        Object object;
        FabricClientCommandSource fabricClientCommandSource;
        block13: {
            String string;
            block11: {
                String string2;
                block12: {
                    String string3;
                    boolean bl;
                    block10: {
                        ScreenshotModule screenshotModule;
                        block8: {
                            ScreenshotModule screenshotModule2;
                            block9: {
                                screenshotModule2 = ScreenshotModule.get();
                                bl = a;
                                screenshotModule = screenshotModule2;
                                if (bl) break block8;
                                if (screenshotModule != null) break block9;
                                string3 = b[12];
                                break block10;
                            }
                            screenshotModule = screenshotModule2;
                        }
                        string3 = screenshotModule.copyLastReason();
                    }
                    string2 = string3;
                    fabricClientCommandSource = (FabricClientCommandSource)commandContext.getSource();
                    string = string2;
                    if (bl) break block11;
                    if (string != null) break block12;
                    object = b[11];
                    break block13;
                }
                string = string2;
            }
            object = b[33] + string;
        }
        fabricClientCommandSource.sendFeedback((Text)Text.method_43470((String)object));
        return 1;
    }

    private static void lambda$onInitializeClient$11(CommandDispatcher commandDispatcher, CommandRegistryAccess commandRegistryAccess) {
        commandDispatcher.register((LiteralArgumentBuilder)ClientCommandManager.literal((String)b[27]).executes(Aurora::lambda$onInitializeClient$10));
    }

    private static int lambda$onInitializeClient$10(CommandContext commandContext) throws CommandSyntaxException {
        Map<UUID, Integer> map;
        MinecraftClient minecraftClient;
        block10: {
            Map<UUID, Integer> map2;
            block11: {
                block8: {
                    block9: {
                        minecraftClient = MinecraftClient.method_1551();
                        boolean bl = a;
                        map2 = TotemPops.all();
                        if (bl) break block8;
                        if (minecraftClient.method_1562() == null) break block9;
                        map = map2;
                        if (bl) break block10;
                        if (!map.isEmpty()) break block11;
                    }
                    ((FabricClientCommandSource)commandContext.getSource()).sendFeedback((Text)Text.method_43470((String)b[22]));
                }
                return 1;
            }
            ((FabricClientCommandSource)commandContext.getSource()).sendFeedback((Text)Text.method_43470((String)b[21]));
            map = map2;
        }
        map.entrySet().stream().sorted(Aurora::lambda$onInitializeClient$8).forEach(arg_0 -> Aurora.lambda$onInitializeClient$9(minecraftClient, commandContext, arg_0));
        return 1;
    }

    private static void lambda$onInitializeClient$9(MinecraftClient minecraftClient, CommandContext commandContext, Map.Entry entry) {
        String string;
        block6: {
            Object object;
            block4: {
                block5: {
                    PlayerListEntry playerListEntry = minecraftClient.method_1562().method_2871((UUID)entry.getKey());
                    boolean bl = a;
                    object = playerListEntry;
                    if (bl) break block4;
                    if (object == null) break block5;
                    string = playerListEntry.method_2966().name();
                    break block6;
                }
                object = entry.getKey();
            }
            string = ((UUID)object).toString().substring(0, 8);
        }
        String string2 = string;
        String[] stringArray = b;
        ((FabricClientCommandSource)commandContext.getSource()).sendFeedback((Text)Text.method_43470((String)(stringArray[28] + string2 + stringArray[24] + String.valueOf(entry.getValue()))));
    }

    private static int lambda$onInitializeClient$8(Map.Entry entry, Map.Entry entry2) {
        return (Integer)entry2.getValue() - (Integer)entry.getValue();
    }

    private static void lambda$onInitializeClient$7(WorldRenderContext worldRenderContext) {
        RendersModule.captureGlassBackground();
    }

    private static void lambda$onInitializeClient$6(WorldRenderContext worldRenderContext) {
        Mannequin.endGuiPass();
    }

    private static void lambda$onInitializeClient$5(ClientPlayNetworkHandler clientPlayNetworkHandler, MinecraftClient minecraftClient) {
        EffectEngine.clear();
    }

    private void lambda$onInitializeClient$4(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        HudBridge.apply();
        this.moduleManager.onHudRender(drawContext);
        HudBridge.sync();
        ToggleDisplay.render(drawContext);
        UpdatePrompt.render(drawContext, MinecraftClient.method_1551().method_22683().method_4486());
        SocialToast.render(drawContext);
        PredictionFx.render(drawContext);
    }

    private static void lambda$onInitializeClient$3(MinecraftClient minecraftClient) {
        WindowIcon.applyOnce();
    }

    private static void lambda$onInitializeClient$2(MinecraftClient minecraftClient) {
        Updater.checkOnce();
    }

    private static void lambda$onInitializeClient$1(MinecraftClient minecraftClient) {
        EmoteEngine.tick();
    }

    private static void lambda$onInitializeClient$0(MinecraftClient minecraftClient) {
        Round.warmStep();
    }

    
    static {
        block21: {
            block20: {
                block19: {
                    var7 = new String[35];
                    var5_1 = 0;
                    var4_2 = "<d\u00101QIx\u0005x\nrE\u001bu\u001cr\u0011pDB9\u0002q\u00101TWv\u0016{\u0006u\u001a\u001bj\u0010uCe^^9\u0019\u007f\u0004\u00146\u007f\u0016}R\u001bw\u001adCaD^u\u001aq\u00071MF\u0004\u0013y\u000ft!\u0011u\u0015?GN|\u0019s\u000bx\u0018Zl\u0007\u007f\u0011p\u0018\\l\u001c>.tXN[\u0000d\u0017~X%\u0011u\u0015?GN|\u0019s\u000bx\u0018Zl\u0007\u007f\u0011p\u0018\\l\u001c>\"dDTk\u0014R\u0002r]_k\u001a`\u001c\u0011u\u0015?GN|\u0019s\u000bx\u0018Zl\u0007\u007f\u0011p\u0018\\l\u001c>7ySV|\u001d\u0006s\u0011tSUj\u001d\u007f\u00171[T}\u0000|\u00061CUx\u0003q\n}WYu\u0010\n\u0014e\u0011~DZz\u001a`\u001a\u00116\u007f\u0016}RU>\u00010\u0007tZ^m\u0010*C\u0013&s\u0011tSUj\u001d\u007f\u00171R^u\u0010d\u0006u\u0018\u00064e\u0011~DZ\u001f&s\u0011tSUj\u001d\u007f\u00171UTi\u001cu\u00071BT9\u0016|\naTTx\u0007tM\u001d\u0006s\u0011tSUj\u001d\u007f\u00171[T}\u0000|\u00061CUx\u0003q\n}WYu\u0010%\u0011u\u0015?GN|\u0019s\u000bx\u0018Zl\u0007\u007f\u0011p\u0018\\l\u001c>\u0010ySWu[G\u0002cRIv\u0017u\u0005\u0013q\u000fbS\u0015\u001dd\u0017aE\u00016Zq\u0016cYIx\u0018\u007f\u0007?NBc\u001f4e\u0011~DZ9\u001c~\ne_Zu\u001cj\u0006u\u0016\u0013b\b0\u000e~RNu\u0010cJ\u001c\u0011u\u0015?GN|\u0019s\u000bx\u0018Zl\u0007\u007f\u0011p\u0018\\l\u001c>1~CU} \u0011u\u0015?GN|\u0019s\u000bx\u0018Zl\u0007\u007f\u0011p\u0018\\l\u001c>7xBW|=\u007f\u000et\u0015\u001dd\u0017aE\u00016Zq\u0016cYIx\u0018\u007f\u0007?NBc\u000f%_)P`dW4D*Gs\u007fP'\u000b!\u007f\u0017t[\u001bi\u001a`\u0010+\u001b;\u007fCeYO|\u00180\u0013~FH9\u0007u\u0000~D_|\u00110\u001atB\u0015\f\u0014e\u0011~DZ}\u0010|\u0006eS\u0003U\u2706C\u001c\u0011u\u0015?GN|\u0019s\u000bx\u0018Zl\u0007\u007f\u0011p\u0018\\l\u001c>$}WHj\u001f\u0011u\u0015?GN|\u0019s\u000bx\u0018Zl\u0007\u007f\u0011p\u0018\\l\u001c>+dRhm\f|\u0006\b\u0006x\ffFTi\u0006\u0002U0,!x\u00061[^w\u00000\u0007~SHwRdCfYIrU\u007f\r1FSv\u001buC}WNw\u0016x\u0006cE\u001b`\u0010d\u0011\u001fq\u0015p\u0018Zn\u0001>\u000btW_u\u0010c\u0010\u001b4e\u0011~DZ>\u00060\u000etXN9\u0016\u007f\u0016}RU>\u00010\u000f~W_\u0011\u001fq\u0015p\u0018Zn\u0001>\u000btW_u\u0010c\u0010";
                    var6_3 = "<d\u00101QIx\u0005x\nrE\u001bu\u001cr\u0011pDB9\u0002q\u00101TWv\u0016{\u0006u\u001a\u001bj\u0010uCe^^9\u0019\u007f\u0004\u00146\u007f\u0016}R\u001bw\u001adCaD^u\u001aq\u00071MF\u0004\u0013y\u000ft!\u0011u\u0015?GN|\u0019s\u000bx\u0018Zl\u0007\u007f\u0011p\u0018\\l\u001c>.tXN[\u0000d\u0017~X%\u0011u\u0015?GN|\u0019s\u000bx\u0018Zl\u0007\u007f\u0011p\u0018\\l\u001c>\"dDTk\u0014R\u0002r]_k\u001a`\u001c\u0011u\u0015?GN|\u0019s\u000bx\u0018Zl\u0007\u007f\u0011p\u0018\\l\u001c>7ySV|\u001d\u0006s\u0011tSUj\u001d\u007f\u00171[T}\u0000|\u00061CUx\u0003q\n}WYu\u0010\n\u0014e\u0011~DZz\u001a`\u001a\u00116\u007f\u0016}RU>\u00010\u0007tZ^m\u0010*C\u0013&s\u0011tSUj\u001d\u007f\u00171R^u\u0010d\u0006u\u0018\u00064e\u0011~DZ\u001f&s\u0011tSUj\u001d\u007f\u00171UTi\u001cu\u00071BT9\u0016|\naTTx\u0007tM\u001d\u0006s\u0011tSUj\u001d\u007f\u00171[T}\u0000|\u00061CUx\u0003q\n}WYu\u0010%\u0011u\u0015?GN|\u0019s\u000bx\u0018Zl\u0007\u007f\u0011p\u0018\\l\u001c>\u0010ySWu[G\u0002cRIv\u0017u\u0005\u0013q\u000fbS\u0015\u001dd\u0017aE\u00016Zq\u0016cYIx\u0018\u007f\u0007?NBc\u001f4e\u0011~DZ9\u001c~\ne_Zu\u001cj\u0006u\u0016\u0013b\b0\u000e~RNu\u0010cJ\u001c\u0011u\u0015?GN|\u0019s\u000bx\u0018Zl\u0007\u007f\u0011p\u0018\\l\u001c>1~CU} \u0011u\u0015?GN|\u0019s\u000bx\u0018Zl\u0007\u007f\u0011p\u0018\\l\u001c>7xBW|=\u007f\u000et\u0015\u001dd\u0017aE\u00016Zq\u0016cYIx\u0018\u007f\u0007?NBc\u000f%_)P`dW4D*Gs\u007fP'\u000b!\u007f\u0017t[\u001bi\u001a`\u0010+\u001b;\u007fCeYO|\u00180\u0013~FH9\u0007u\u0000~D_|\u00110\u001atB\u0015\f\u0014e\u0011~DZ}\u0010|\u0006eS\u0003U\u2706C\u001c\u0011u\u0015?GN|\u0019s\u000bx\u0018Zl\u0007\u007f\u0011p\u0018\\l\u001c>$}WHj\u001f\u0011u\u0015?GN|\u0019s\u000bx\u0018Zl\u0007\u007f\u0011p\u0018\\l\u001c>+dRhm\f|\u0006\b\u0006x\ffFTi\u0006\u0002U0,!x\u00061[^w\u00000\u0007~SHwRdCfYIrU\u007f\r1FSv\u001buC}WNw\u0016x\u0006cE\u001b`\u0010d\u0011\u001fq\u0015p\u0018Zn\u0001>\u000btW_u\u0010c\u0010\u001b4e\u0011~DZ>\u00060\u000etXN9\u0016\u007f\u0016}RU>\u00010\u000f~W_\u0011\u001fq\u0015p\u0018Zn\u0001>\u000btW_u\u0010c\u0010".length();
                    var3_4 = 45;
                    var2_5 = -1;
lbl7:

                    while (true) {
                        v0 = ++var2_5;
                        v1 = var4_2.substring(v0, v0 + var3_4);
                        v2 = -1;
                        break block19;
                        break;
                    }
lbl12:

                    while (true) {
                        var7[var5_1++] = v3.intern();
                        if ((var2_5 += var3_4) < var6_3) {
                            var3_4 = var4_2.charAt(var2_5);
                            continue;
                        }
                        var4_2 = "6\u007f\u0016}RU>\u00010\u0000~FB#U\u0004\u0013y\u000ft";
                        var6_3 = "6\u007f\u0016}RU>\u00010\u0000~FB#U\u0004\u0013y\u000ft".length();
                        var3_4 = 15;
                        var2_5 = -1;
lbl21:

                        while (true) {
                            v4 = ++var2_5;
                            v1 = var4_2.substring(v4, v4 + var3_4);
                            v2 = 0;
                            break block19;
                            break;
                        }
                        break;
                    }
lbl26:

                    while (true) {
                        var7[var5_1++] = v3.intern();
                        if ((var2_5 += var3_4) < var6_3) {
                            var3_4 = var4_2.charAt(var2_5);
                            continue;
                        }
                        break block20;
                        break;
                    }
                }
                v5 = v1.toCharArray();
                v6 = v5;
                v7 = v5.length;
                var8_6 = 0;
                do {
                    v6 = v6;
                    v8 = var8_6;
                    v9 = v6[v8];
                    switch (var8_6 % 7) {
                        case 0: {
                            v10 = 117;
                            break;
                        }
                        case 1: {
                            v10 = 16;
                            break;
                        }
                        case 2: {
                            v10 = 99;
                            break;
                        }
                        case 3: {
                            v10 = 17;
                            break;
                        }
                        case 4: {
                            v10 = 54;
                            break;
                        }
                        case 5: {
                            v10 = 59;
                            break;
                        }
                        default: {
                            v10 = 25;
                        }
                    }
                    v6[v8] = (char)(v9 ^ v10);
                    ++var8_6;
lbl65:

                    v7 = v7;
                } while (v7 > var8_6);
                v3 = new String(v6);
                switch (v2) {
                    default: {
                        continue;
                    }
                    case 0:
lbl73:

                    continue;
                }
            }
            Aurora.b = var7;
            Aurora.COSMETICS_URL = Aurora.b[19];
            break block21;
lbl79:

            while (true) {
                continue;
                break;
            }
        }
        var0_7 = 2787438052142718146L;
        while (true)
        Aurora.c = 7170770550751468954L ^ var0_7;
        Aurora.LOG = LoggerFactory.getLogger((String)Aurora.b[10]);
    }
}
