package dev.quelchi.aurora.module.modules;

import com.google.gson.JsonObject;
import dev.quelchi.aurora.gui.im.Im;
import dev.quelchi.aurora.gui.im.Menu;
import dev.quelchi.aurora.gui.im.Themes;
import dev.quelchi.aurora.gui.im.Themes$Look;
import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.DraggableHud;
import dev.quelchi.aurora.module.ExtraHuds;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.ModuleManager;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.MusicPlayerModule$Align;
import dev.quelchi.aurora.module.modules.MusicPlayerModule$Backdrop;
import dev.quelchi.aurora.module.modules.MusicPlayerModule$Draw;
import dev.quelchi.aurora.module.modules.MusicPlayerModule$Font;
import dev.quelchi.aurora.module.modules.MusicPlayerModule$Highlight;
import dev.quelchi.aurora.module.modules.MusicPlayerModule$Layout;
import dev.quelchi.aurora.module.modules.MusicPlayerModule$LyricsBar;
import dev.quelchi.aurora.module.modules.MusicPlayerModule$Palette;
import dev.quelchi.aurora.module.modules.MusicPlayerModule$PlayerStyle;
import dev.quelchi.aurora.module.modules.MusicPlayerModule$Strip;
import dev.quelchi.aurora.module.modules.MusicPlayerModule$Tok;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import dev.quelchi.aurora.module.setting.ColorSetting;
import dev.quelchi.aurora.module.setting.EnumSetting;
import dev.quelchi.aurora.module.setting.KeybindSetting;
import dev.quelchi.aurora.module.setting.NumberSetting;
import dev.quelchi.aurora.module.setting.Setting;
import dev.quelchi.aurora.music.AlbumColor;
import dev.quelchi.aurora.music.Lyrics;
import dev.quelchi.aurora.music.Lyrics$Line;
import dev.quelchi.aurora.music.Lyrics$Song;
import dev.quelchi.aurora.music.Lyrics$Word;
import dev.quelchi.aurora.music.NowPlaying;
import dev.quelchi.aurora.music.SmtcClient;
import dev.quelchi.aurora.music.SmtcClient$Art;
import dev.quelchi.aurora.render.AaRoundRect;
import dev.quelchi.aurora.render.LyricWords;
import dev.quelchi.aurora.render.SdfText;
import dev.quelchi.aurora.util.KeyUtil;
import java.awt.Color;
import java.lang.invoke.LambdaMetafactory;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;
import org.joml.Matrix3x2fStack;

public final class MusicPlayerModule
extends Module
implements DraggableHud,
ExtraHuds {
    private static final Identifier ART_ID;
    private static final int PANEL_H;
    private static final int RADIUS;
    private static final int PANEL_BG;
    private static final int ARTIST_COL;
    private static final int BAR_BG;
    private static final float SMALL = 0.75f;
    private static final int CARD_H;
    private static final int CARD_PAD;
    private static final int CARD_GAP;
    private static final int CARD_R;
    private static final int CARD_ART_R;
    private static final float SUB_FADE = 0.81f;
    private static final float MUTE_FADE = 0.64f;
    private static final int LYRICS_PAD_X;
    private static final int LYRICS_PAD_Y;
    private static final int LYRICS_ROW;
    private static final int LYRICS_TEXT;
    private static final int LYRICS_R;
    private static final int LYRICS_MIN_W;
    private static final int LYRICS_MAX_W;
    private static final int LYRICS_DARK;
    private static final int PAST_A;
    private static final int FUTURE_A;
    private static final int TICKER_A;
    private static final int FADE_W;
    private static final long GLOW_IN_MS;
    private static final long GLOW_OUT_MS;
    private static final MusicPlayerModule$Font LYRICS_FACE;
    private static final MusicPlayerModule$Font TITLE_FACE;
    private static final MusicPlayerModule$Font SMALL_FACE;
    private static final float LINE_CUR = 1.2f;
    private static final float LINE_REST = 0.85f;
    private static final long SCROLL_NS;
    private static final int LYRIC_LEAD_MS;
    private static final List<Lyrics$Line> PREVIEW;
    private static final long PREVIEW_POS;
    private static MusicPlayerModule instance;
    private final KeybindSetting playPauseKey;
    private final KeybindSetting nextKey;
    private final KeybindSetting prevKey;
    private final BooleanSetting showPlayer;
    private final EnumSetting<MusicPlayerModule$PlayerStyle> style;
    private final BooleanSetting albumArt;
    private final EnumSetting<MusicPlayerModule$Backdrop> background;
    private final BooleanSetting dynamic;
    private final BooleanSetting showProgress;
    private final BooleanSetting dynamicAccent;
    private final ColorSetting accent;
    private final EnumSetting<MusicPlayerModule$Palette> colors;
    private final ColorSetting cardColor;
    private final ColorSetting textColor;
    private final ColorSetting progressColor;
    private final BooleanSetting border;
    private final ColorSetting borderColor;
    private final NumberSetting width;
    private final BooleanSetting showLyrics;
    private final EnumSetting<MusicPlayerModule$Backdrop> lyricsBackground;
    private final ColorSetting lyricsBgColor;
    private final BooleanSetting lyricsDynamic;
    private final ColorSetting lyricsAccent;
    private final BooleanSetting dynamicHighlight;
    private final EnumSetting<MusicPlayerModule$Layout> layout;
    private final NumberSetting linesShown;
    private final EnumSetting<MusicPlayerModule$Align> alignment;
    private final NumberSetting tickerWidth;
    private final EnumSetting<MusicPlayerModule$Highlight> highlight;
    private final BooleanSetting glow;
    private final ColorSetting glowColor;
    private final NumberSetting glowStrength;
    private final NumberSetting wordSize;
    private final NumberSetting spread;
    private final NumberSetting wordTime;
    private final ColorSetting wordColor;
    private final NumberSetting lyricsOffset;
    private int hudX;
    private int hudY;
    private int[] lastBounds;
    private final MusicPlayerModule$LyricsBar lyricsBar;
    private SmtcClient$Art art;
    private NativeImageBackedTexture artTexture;
    private int artTexPx;
    private int artTexR;
    private String artTexKey;
    private float easedFrac;
    private int[] tintFrom;
    private int[] tintTo;
    private long tintStart;
    private String shownKey;
    private int shownLine;
    private long scrollStart;
    private boolean ppDown;
    private boolean nextDown;
    private boolean prevDown;
    private MusicPlayerModule$Strip shownStrip;
    private float shown;
    private long shownNs;
    private double lyricMs;
    private long lyricNs;
    private String lyricKey;
    private MusicPlayerModule$Strip strip;
    private static final String[] b;
    private static final long[] d;
    private static final Integer[] e;
    private static final long[] f;
    private static final Long[] g;

    
    public MusicPlayerModule() {
        block15: {
            block14: {
                var4_1 = MusicPlayerModule.b;
                super(var4_1[15], var4_1[19], Category.HUD, false);
                this.playPauseKey = new KeybindSetting(var4_1[26], -1);
                this.nextKey = new KeybindSetting(var4_1[55], -1);
                this.prevKey = new KeybindSetting(var4_1[58], -1);
                this.showPlayer = new BooleanSetting(var4_1[21], true);
                this.style = new EnumSetting<MusicPlayerModule$PlayerStyle>(var4_1[35], MusicPlayerModule$PlayerStyle.Card);
                this.albumArt = new BooleanSetting(var4_1[6], true);
                this.background = new EnumSetting<MusicPlayerModule$Backdrop>(var4_1[4], MusicPlayerModule$Backdrop.Dark);
                this.dynamic = new BooleanSetting(var4_1[8], false);
                this.showProgress = new BooleanSetting(var4_1[32], true);
                this.dynamicAccent = new BooleanSetting(var4_1[5], false);
                this.accent = new ColorSetting(var4_1[16], -1);
                this.colors = new EnumSetting<MusicPlayerModule$Palette>(var4_1[50], MusicPlayerModule$Palette.Theme);
                this.cardColor = new ColorSetting(var4_1[20], 0);
                this.textColor = new ColorSetting(var4_1[51], 0);
                this.progressColor = new ColorSetting(var4_1[12], 0);
                this.border = new BooleanSetting(var4_1[52], true);
                this.borderColor = new ColorSetting(var4_1[61], 0);
                this.width = new NumberSetting(var4_1[63], 120.0, 260.0, 175.0, 5.0);
                this.showLyrics = new BooleanSetting(var4_1[27], false);
                this.lyricsBackground = new EnumSetting<MusicPlayerModule$Backdrop>(var4_1[47], MusicPlayerModule$Backdrop.None);
                this.lyricsBgColor = new ColorSetting(var4_1[36], 0);
                this.lyricsDynamic = new BooleanSetting(var4_1[62], false);
                this.lyricsAccent = new ColorSetting(var4_1[23], -1, false);
                this.dynamicHighlight = new BooleanSetting(var4_1[43], false);
                this.layout = new EnumSetting<MusicPlayerModule$Layout>(var4_1[28], MusicPlayerModule$Layout.Lines);
                this.linesShown = new NumberSetting(var4_1[30], 1.0, 5.0, 3.0, 1.0);
                this.alignment = new EnumSetting<MusicPlayerModule$Align>(var4_1[48], MusicPlayerModule$Align.Center);
                this.tickerWidth = new NumberSetting(var4_1[17], 120.0, 400.0, 240.0, 10.0);
                this.highlight = new EnumSetting<MusicPlayerModule$Highlight>(var4_1[25], MusicPlayerModule$Highlight.Fill);
                this.glow = new BooleanSetting(var4_1[42], false);
                this.glowColor = new ColorSetting(var4_1[24], -1, false);
                this.glowStrength = new NumberSetting(var4_1[10], 1.0, 10.0, 4.0, 1.0);
                this.wordSize = new NumberSetting(var4_1[0], 0.5, 5.0, 2.0, 0.1);
                this.spread = new NumberSetting(var4_1[59], 2.0, 10.0, 5.0, 0.5);
                this.wordTime = new NumberSetting(var4_1[41], 0.5, 8.0, 3.5, 0.1);
                this.wordColor = new ColorSetting(var4_1[45], -1, false);
                var1_2 = AmbienceModule.b;
                this.lyricsOffset = new NumberSetting(var4_1[46], -1000.0, 1000.0, 0.0, 50.0);
                this.hudX = MusicPlayerModule.a(20179, 6733777843277195981L);
                this.hudY = MusicPlayerModule.a(8478, 6532512517591762179L);
                this.lastBounds = new int[]{4, 40, 0, 0};
                this.lyricsBar = new MusicPlayerModule$LyricsBar();
                this.artTexPx = -1;
                this.artTexR = -1;
                this.shownLine = -1;
                MusicPlayerModule.instance = this;
                this.addSettings(new Setting[]{this.playPauseKey, this.nextKey, this.prevKey, this.showPlayer, this.style, this.albumArt, this.background, this.dynamic, this.showProgress, this.dynamicAccent, this.accent, this.colors, this.cardColor, this.textColor, this.progressColor, this.border, this.borderColor, this.width, this.showLyrics, this.lyricsBackground, this.lyricsBgColor, this.lyricsDynamic, this.lyricsAccent, this.dynamicHighlight, this.layout, this.linesShown, this.alignment, this.tickerWidth, this.wordSize, this.spread, this.wordTime, this.wordColor, this.highlight, this.glow, this.glowColor, this.glowStrength, this.lyricsOffset});
                var2_3 = List.of(new Setting[]{this.showPlayer, this.style, this.albumArt, this.background, this.dynamic, this.showProgress, this.dynamicAccent, this.accent, this.colors, this.cardColor, this.textColor, this.progressColor, this.border, this.borderColor, this.width}).iterator();
                while (var2_3.hasNext()) {
                    var3_4 = var2_3.next();
                    v0 = var3_4;
                    v1 = MusicPlayerModule.b[14];
                    if (!var1_2) {
                        v0.tab(v1);
                        if (!var1_2) continue;
                        break;
                    }
                }
                var4_1 = MusicPlayerModule.b;
                this.playPauseKey.tab(var4_1[44]);
                this.nextKey.tab(var4_1[37]);
                this.prevKey.tab(var4_1[37]);
                for (Setting var3_4 : List.of(new Setting[]{this.showLyrics, this.lyricsBackground, this.lyricsBgColor, this.lyricsDynamic, this.lyricsAccent, this.dynamicHighlight, this.layout, this.linesShown, this.alignment, this.tickerWidth, this.wordSize, this.spread, this.wordTime, this.wordColor, this.highlight, this.glow, this.glowColor, this.glowStrength, this.lyricsOffset})) {
                    v0 = var3_4;
                    if (var1_2) break block14;
                    v1 = MusicPlayerModule.b[1];
lbl79:

                    v0.tab(v1);
                    if (!var1_2) continue;
                }
                this.background.children(new Setting[]{this.dynamic});
                this.dynamic.visibleWhen((Supplier<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, lambda$new$0(), ()Ljava/lang/Boolean;)((MusicPlayerModule)this));
                this.accent.visibleWhen((Supplier<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, lambda$new$1(), ()Ljava/lang/Boolean;)((MusicPlayerModule)this));
                this.colors.visibleWhen((Supplier<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, lambda$new$2(), ()Ljava/lang/Boolean;)((MusicPlayerModule)this));
                v0 = this.colors.children(new Setting[]{this.cardColor, this.textColor, this.progressColor, this.border});
            }
            for (Setting v4 : List.of(this.cardColor, this.textColor, this.progressColor, this.border)) {
                if (!var1_2) {
                    var3_4 = v4;
                    var3_4.visibleWhen((Supplier<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, lambda$new$3(), ()Ljava/lang/Boolean;)((MusicPlayerModule)this));
                    if (!var1_2) continue;
                }
                break block15;
            }
            this.border.children(new Setting[]{this.borderColor});
            this.dynamicAccent.visibleWhen((Supplier<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, lambda$new$4(), ()Ljava/lang/Boolean;)((MusicPlayerModule)this));
            this.showLyrics.children(new Setting[]{this.lyricsBackground, this.lyricsAccent, this.dynamicHighlight, this.layout, this.linesShown, this.alignment, this.tickerWidth, this.wordSize, this.spread, this.wordTime, this.wordColor, this.highlight, this.glow, this.lyricsOffset});
            this.lyricsBackground.children(new Setting[]{this.lyricsBgColor, this.lyricsDynamic});
            this.lyricsBgColor.visibleWhen((Supplier<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, lambda$new$5(), ()Ljava/lang/Boolean;)((MusicPlayerModule)this));
            this.lyricsDynamic.visibleWhen((Supplier<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, lambda$new$6(), ()Ljava/lang/Boolean;)((MusicPlayerModule)this));
            this.glow.children(new Setting[]{this.glowColor, this.glowStrength});
            this.linesShown.visibleWhen((Supplier<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, lambda$new$7(), ()Ljava/lang/Boolean;)((MusicPlayerModule)this));
            this.alignment.visibleWhen((Supplier<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, lambda$new$8(), ()Ljava/lang/Boolean;)((MusicPlayerModule)this));
            v4 = this.tickerWidth.visibleWhen((Supplier<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, lambda$new$9(), ()Ljava/lang/Boolean;)((MusicPlayerModule)this));
        }
        var2_3 = List.of(this.wordSize, this.spread, this.wordTime, this.wordColor).iterator();
        while (var2_3.hasNext()) {
            var3_4 = var2_3.next();
            v5 = var3_4;
            if (!var1_2) {
                v5.visibleWhen((Supplier<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, lambda$new$10(), ()Ljava/lang/Boolean;)((MusicPlayerModule)this));
                if (!var1_2) continue;
                break;
            }
        }
        for (Setting var3_4 : List.of(this.lyricsBackground, this.lyricsAccent, this.highlight)) {
            v5 = var3_4.visibleWhen((Supplier<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, lambda$new$11(), ()Ljava/lang/Boolean;)((MusicPlayerModule)this));
lbl134:

            if (!var1_2) continue;
        }
    }

    public static void onWorldRender(WorldRenderContext worldRenderContext) {
        int n;
        int n2;
        float f;
        float f2;
        float f3;
        String string;
        long l;
        List<Lyrics$Line> list;
        WorldRenderContext worldRenderContext2;
        block44: {
            block43: {
                MusicPlayerModule musicPlayerModule;
                block42: {
                    int n3;
                    Lyrics$Song lyrics$Song;
                    boolean bl;
                    NowPlaying nowPlaying;
                    MusicPlayerModule musicPlayerModule2;
                    block41: {
                        MusicPlayerModule musicPlayerModule3;
                        block39: {
                            block40: {
                                boolean bl2;
                                block37: {
                                    block38: {
                                        block35: {
                                            block36: {
                                                boolean bl3;
                                                block34: {
                                                    block31: {
                                                        MusicPlayerModule musicPlayerModule4;
                                                        block33: {
                                                            block32: {
                                                                block30: {
                                                                    musicPlayerModule2 = instance;
                                                                    nowPlaying = SmtcClient.getTrack();
                                                                    bl = AmbienceModule.b;
                                                                    musicPlayerModule4 = musicPlayerModule2;
                                                                    if (bl) break block30;
                                                                    if (musicPlayerModule4 == null) break block31;
                                                                    musicPlayerModule4 = musicPlayerModule2;
                                                                }
                                                                if (bl) break block32;
                                                                if (!musicPlayerModule4.isEnabled()) break block31;
                                                                musicPlayerModule4 = musicPlayerModule2;
                                                            }
                                                            if (bl) break block33;
                                                            if (!musicPlayerModule4.showLyrics.getValue()) break block31;
                                                            musicPlayerModule4 = musicPlayerModule2;
                                                        }
                                                        if (musicPlayerModule4.layout.getValue() != MusicPlayerModule$Layout.World || nowPlaying == null) break block31;
                                                        bl3 = true;
                                                        break block34;
                                                    }
                                                    bl3 = false;
                                                }
                                                boolean bl4 = bl3;
                                                Lyrics$Song lyrics$Song2 = bl4 ? Lyrics.get(nowPlaying) : Lyrics.NONE;
                                                lyrics$Song = lyrics$Song2;
                                                if (bl) break block35;
                                                if (!lyrics$Song.synced()) break block36;
                                                bl2 = lyrics$Song.lines().isEmpty();
                                                if (bl) break block37;
                                                if (!bl2) break block38;
                                            }
                                            LyricWords.clear();
                                        }
                                        return;
                                    }
                                    musicPlayerModule3 = musicPlayerModule2;
                                    if (bl) break block39;
                                    bl2 = musicPlayerModule3.dynamicHighlight.getValue();
                                }
                                if (!bl2) break block40;
                                n3 = musicPlayerModule2.highlightRgb();
                                break block41;
                            }
                            musicPlayerModule3 = musicPlayerModule2;
                        }
                        n3 = musicPlayerModule3.wordColor.getRGB() & MusicPlayerModule.a(16931, 2613022266237085236L);
                    }
                    int n4 = n3;
                    worldRenderContext2 = worldRenderContext;
                    list = lyrics$Song.lines();
                    l = musicPlayerModule2.lyricClock(nowPlaying);
                    string = nowPlaying.artKey();
                    f3 = musicPlayerModule2.wordSize.getValueFloat();
                    f2 = musicPlayerModule2.spread.getValueFloat();
                    f = musicPlayerModule2.wordTime.getValueFloat();
                    n2 = n4;
                    musicPlayerModule = musicPlayerModule2;
                    if (bl) break block42;
                    if (!musicPlayerModule.glow.getValue()) break block43;
                    musicPlayerModule = musicPlayerModule2;
                }
                n = musicPlayerModule.glowStrength.getValueInt();
                break block44;
            }
            n = 0;
        }
        LyricWords.render(worldRenderContext2, list, l, string, f3, f2, f, n2, n);
    }

    @Override
    public void onEnable() {
        SmtcClient.start();
    }

    @Override
    public void onDisable() {
        SmtcClient.stop();
        this.mc.execute(this::lambda$onDisable$12);
    }

    @Override
    public void onSettingsChanged() {
        block11: {
            MusicPlayerModule musicPlayerModule;
            block10: {
                EnumSetting<Enum> enumSetting;
                block8: {
                    boolean bl;
                    block9: {
                        bl = AmbienceModule.b;
                        enumSetting = this.colors;
                        if (bl) break block8;
                        if (enumSetting.getValue() != MusicPlayerModule$Palette.Custom) break block9;
                        MusicPlayerModule.seed(this.cardColor, MusicPlayerModule.argb(Im.BG, MusicPlayerModule.menuAlpha()));
                        MusicPlayerModule.seed(this.textColor, MusicPlayerModule.argb(Im.INK, 1.0f));
                        MusicPlayerModule.seed(this.progressColor, MusicPlayerModule.argb(Menu.ACCENT, 1.0f));
                        MusicPlayerModule.seed(this.borderColor, MusicPlayerModule.argb(Im.RIM, 1.0f));
                    }
                    musicPlayerModule = this;
                    if (bl) break block10;
                    enumSetting = musicPlayerModule.lyricsBackground;
                }
                if (enumSetting.getValue() == MusicPlayerModule$Backdrop.None) break block11;
                musicPlayerModule = this;
            }
            MusicPlayerModule.seed(musicPlayerModule.lyricsBgColor, MusicPlayerModule.themeLyricsBg());
        }
    }

    private static void seed(ColorSetting colorSetting, int n) {
        block5: {
            ColorSetting colorSetting2;
            block4: {
                boolean bl = AmbienceModule.b;
                colorSetting2 = colorSetting;
                if (bl) break block4;
                if (colorSetting2.getARGB() != 0) break block5;
                colorSetting2 = colorSetting;
            }
            colorSetting2.setARGB(n);
        }
    }

    @Override
    public void onTick() {
        boolean bl = MinecraftClient.method_1551().field_1755 == null;
        boolean bl2 = bl;
        String[] stringArray = b;
        this.ppDown = MusicPlayerModule.edge(this.playPauseKey.getKey(), bl2, this.ppDown, stringArray[54]);
        this.nextDown = MusicPlayerModule.edge(this.nextKey.getKey(), bl2, this.nextDown, stringArray[49]);
        this.prevDown = MusicPlayerModule.edge(this.prevKey.getKey(), bl2, this.prevDown, stringArray[11]);
    }

    
    private static boolean edge(int var0, boolean var1_1, boolean var2_2, String var3_3) {
        block23: {
            block24: {
                block22: {
                    block21: {
                        block20: {
                            var4_4 = AmbienceModule.b;
                            v0 = var1_1;
                            if (var4_4) break block20;
                            if (v0 != 0) {
                            }
                            v0 = var0;
                        }
                        if (var4_4) break block21;
                        if (v0 != -1) {
                        }
                        v3 = var0;
                        if (var4_4) break block22;
                        v0 = (int)KeyUtil.isKeyPressedInGame(v3);
                    }
                    if (v0 != 0) {
                        v3 = true;
                    } else lbl_neg_1000:

                    {
                        v3 = false;
                    }
                }
                var5_5 = v3;
                v6 = var5_5;
                if (var4_4) break block23;
                if (!v6) break block24;
                v6 = var2_2;
                if (var4_4) break block23;
                if (v6) break block24;
                SmtcClient.control(var3_3);
            }
            v6 = var5_5;
        }
        return v6;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public void onHudRender(DrawContext drawContext) {
        MusicPlayerModule musicPlayerModule;
        NowPlaying nowPlaying;
        TextRenderer textRenderer;
        block29: {
            boolean bl;
            block25: {
                block26: {
                    MusicPlayerModule musicPlayerModule2;
                    block27: {
                        block28: {
                            MinecraftClient minecraftClient;
                            MinecraftClient minecraftClient2;
                            block24: {
                                minecraftClient2 = MinecraftClient.method_1551();
                                bl = AmbienceModule.b;
                                this.lastBounds = new int[]{this.hudX, this.hudY, 0, 0};
                                this.lyricsBar.bounds = new int[]{this.lyricsBar.getHudX(), this.lyricsBar.getHudY(), 0, 0};
                                minecraftClient = minecraftClient2;
                                if (bl) break block24;
                                if (minecraftClient.field_1724 == null) return;
                                minecraftClient = minecraftClient2;
                            }
                            if (minecraftClient.field_1690.field_1842) {
                                return;
                            }
                            SmtcClient$Art art = SmtcClient.takePendingArt();
                            if (art != null) {
                                this.art = art;
                            }
                            textRenderer = minecraftClient2.field_1772;
                            nowPlaying = SmtcClient.getTrack();
                            musicPlayerModule = this;
                            if (bl) break block25;
                            if (!musicPlayerModule.showPlayer.getValue()) break block26;
                            musicPlayerModule2 = this;
                            if (bl) break block27;
                            if (musicPlayerModule2.style.getValue() != MusicPlayerModule$PlayerStyle.Card) break block28;
                            this.drawCard(drawContext, textRenderer, nowPlaying);
                            if (!bl) break block26;
                        }
                        musicPlayerModule2 = this;
                    }
                    musicPlayerModule2.drawPanel(drawContext, textRenderer, nowPlaying);
                }
                musicPlayerModule = this;
            }
            if (bl) break block29;
            if (!musicPlayerModule.showLyrics.getValue()) return;
            musicPlayerModule = this;
        }
        musicPlayerModule.drawLyrics(drawContext, textRenderer, nowPlaying);
    }

    private float px(DrawContext drawContext) {
        return Math.max(0.5f, (float)this.mc.method_22683().method_4495() * drawContext.method_51448().m00());
    }

    private boolean artFor(NowPlaying nowPlaying, int n, int n2, float f) {
        int n3;
        block38: {
            block39: {
                block42: {
                    MusicPlayerModule musicPlayerModule;
                    NativeImage nativeImage;
                    int n4;
                    int n5;
                    block43: {
                        boolean bl;
                        block40: {
                            NativeImage nativeImage2;
                            block41: {
                                MusicPlayerModule musicPlayerModule2;
                                block34: {
                                    block35: {
                                        block37: {
                                            block36: {
                                                int n6;
                                                block32: {
                                                    block33: {
                                                        block31: {
                                                            bl = AmbienceModule.b;
                                                            if (nowPlaying == null || this.art == null) break block31;
                                                            n6 = nowPlaying.artKey().equals(this.art.key());
                                                            if (bl) break block32;
                                                            if (n6 != 0) break block33;
                                                        }
                                                        return false;
                                                    }
                                                    n6 = Math.max(1, Math.round((float)n * f));
                                                }
                                                n5 = n6;
                                                n4 = Math.round((float)n2 * f);
                                                musicPlayerModule2 = this;
                                                if (bl) break block34;
                                                if (musicPlayerModule2.artTexture == null) break block35;
                                                n3 = n5;
                                                if (bl) break block36;
                                                if (n3 != this.artTexPx) break block35;
                                                n3 = n4;
                                            }
                                            if (bl) break block37;
                                            if (n3 != this.artTexR) break block35;
                                            n3 = this.art.key().equals(this.artTexKey);
                                        }
                                        if (bl) break block38;
                                        if (n3 != 0) break block39;
                                    }
                                    musicPlayerModule2 = this;
                                }
                                int[] nArray = musicPlayerModule2.art.scaled(n5, n5);
                                nativeImage = new NativeImage(n5, n5, false);
                                block28: for (int i = 0; i < n5; ++i) {
                                    if (bl) break block40;
                                    for (int j = 0; j < n5; ++j) {
                                        nativeImage2 = nativeImage;
                                        if (!bl) {
                                            nativeImage2.method_61941(j, i, nArray[i * n5 + j]);
                                            if (!bl) continue;
                                            continue block28;
                                        }
                                        break block41;
                                    }
                                    if (!bl) continue;
                                }
                                nativeImage2 = nativeImage;
                            }
                            AaRoundRect.roundCorners(nativeImage2, n4);
                        }
                        musicPlayerModule = this;
                        if (bl) break block42;
                        if (musicPlayerModule.artTexture == null) break block43;
                        this.mc.method_1531().method_4615(ART_ID);
                    }
                    this.artTexture = new NativeImageBackedTexture(MusicPlayerModule::lambda$artFor$13, nativeImage);
                    this.artTexture.method_4524();
                    this.mc.method_1531().method_4616(ART_ID, (AbstractTexture)this.artTexture);
                    this.artTexPx = n5;
                    this.artTexR = n4;
                    musicPlayerModule = this;
                }
                musicPlayerModule.artTexKey = this.art.key();
            }
            n3 = 1;
        }
        return n3 != 0;
    }

    private void drawArt(DrawContext drawContext, int n, int n2, int n3) {
        drawContext.method_25302(RenderPipelines.field_56883, ART_ID, n, n2, 0.0f, 0.0f, n3, n3, this.artTexPx, this.artTexPx, this.artTexPx, this.artTexPx);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void drawCard(DrawContext drawContext, TextRenderer textRenderer, NowPlaying nowPlaying) {
        int n;
        int n2;
        block101: {
            int n3;
            String string;
            String string2;
            int n4;
            int n5;
            int n6;
            int n7;
            int n8;
            int n9;
            int n10;
            MusicPlayerModule$Font musicPlayerModule$Font;
            boolean bl;
            MusicPlayerModule$Font musicPlayerModule$Font2;
            block110: {
                MusicPlayerModule musicPlayerModule;
                block108: {
                    block109: {
                        String string3;
                        block107: {
                            NowPlaying nowPlaying2;
                            block105: {
                                block106: {
                                    String string4;
                                    block104: {
                                        NowPlaying nowPlaying3;
                                        block102: {
                                            block103: {
                                                block99: {
                                                    block100: {
                                                        int n11;
                                                        int n12;
                                                        block98: {
                                                            int n13;
                                                            int n14;
                                                            block96: {
                                                                block97: {
                                                                    int n15;
                                                                    block95: {
                                                                        block94: {
                                                                            MusicPlayerModule musicPlayerModule2;
                                                                            float f;
                                                                            block93: {
                                                                                int n16;
                                                                                boolean bl2;
                                                                                MusicPlayerModule$Backdrop musicPlayerModule$Backdrop;
                                                                                int n17;
                                                                                int n18;
                                                                                int n19;
                                                                                int n20;
                                                                                int n21;
                                                                                DrawContext drawContext2;
                                                                                MusicPlayerModule musicPlayerModule3;
                                                                                int n22;
                                                                                block91: {
                                                                                    block92: {
                                                                                        int n23;
                                                                                        MusicPlayerModule$Backdrop musicPlayerModule$Backdrop2;
                                                                                        block89: {
                                                                                            block88: {
                                                                                                block90: {
                                                                                                    block87: {
                                                                                                        int n24;
                                                                                                        block85: {
                                                                                                            block86: {
                                                                                                                int n25;
                                                                                                                block83: {
                                                                                                                    block84: {
                                                                                                                        int n26;
                                                                                                                        block81: {
                                                                                                                            block82: {
                                                                                                                                f = this.px(drawContext);
                                                                                                                                musicPlayerModule$Font2 = TITLE_FACE;
                                                                                                                                bl = AmbienceModule.b;
                                                                                                                                musicPlayerModule$Font = SMALL_FACE;
                                                                                                                                n2 = (int)this.width.getValue();
                                                                                                                                n = 42;
                                                                                                                                n14 = n - 12;
                                                                                                                                int n27 = this.colors.getValue() == MusicPlayerModule$Palette.Custom ? 1 : 0;
                                                                                                                                n10 = n27;
                                                                                                                                n26 = n10;
                                                                                                                                if (bl) break block81;
                                                                                                                                if (n26 == 0) break block82;
                                                                                                                                n26 = this.textColor.getARGB();
                                                                                                                                break block81;
                                                                                                                            }
                                                                                                                            n26 = MusicPlayerModule.argb(Im.INK, 1.0f);
                                                                                                                        }
                                                                                                                        n9 = n26;
                                                                                                                        n25 = n10;
                                                                                                                        if (bl) break block83;
                                                                                                                        if (n25 == 0) break block84;
                                                                                                                        n25 = MusicPlayerModule.fade(n9, 0.81f);
                                                                                                                        break block83;
                                                                                                                    }
                                                                                                                    n25 = MusicPlayerModule.argb(Im.SUB, 1.0f);
                                                                                                                }
                                                                                                                n8 = n25;
                                                                                                                n24 = n10;
                                                                                                                if (bl) break block85;
                                                                                                                if (n24 == 0) break block86;
                                                                                                                n24 = MusicPlayerModule.fade(n9, 0.64f);
                                                                                                                break block85;
                                                                                                            }
                                                                                                            n24 = MusicPlayerModule.argb(Im.MUTE, 1.0f);
                                                                                                        }
                                                                                                        n7 = n24;
                                                                                                        musicPlayerModule$Backdrop2 = this.background.getValue();
                                                                                                        n23 = n10;
                                                                                                        if (bl) break block87;
                                                                                                        if (n23 == 0) break block88;
                                                                                                        n23 = this.border.getValue() ? 1 : 0;
                                                                                                    }
                                                                                                    if (bl) break block89;
                                                                                                    if (n23 == 0) break block90;
                                                                                                    n23 = this.borderColor.getARGB();
                                                                                                    break block89;
                                                                                                }
                                                                                                n23 = 0;
                                                                                                break block89;
                                                                                            }
                                                                                            n23 = musicPlayerModule$Backdrop2 == MusicPlayerModule$Backdrop.None ? 0 : MusicPlayerModule.argb(Im.RIM, 1.0f);
                                                                                        }
                                                                                        n22 = n23;
                                                                                        musicPlayerModule3 = this;
                                                                                        drawContext2 = drawContext;
                                                                                        int n17 = this.hudX;
                                                                                        n17 = this.hudY;
                                                                                        n17 = n2;
                                                                                        n17 = n;
                                                                                        n17 = 6;
                                                                                        musicPlayerModule$Backdrop = musicPlayerModule$Backdrop2;
                                                                                        bl2 = this.dynamic.getValue();
                                                                                        n16 = n10;
                                                                                        if (bl) break block91;
                                                                                        if (n16 == 0) break block92;
                                                                                        n16 = this.cardColor.getARGB();
                                                                                        break block91;
                                                                                    }
                                                                                    n16 = MusicPlayerModule.argb(Im.BG, MusicPlayerModule.menuAlpha());
                                                                                }
                                                                                musicPlayerModule3.backdrop(drawContext2, n21, n20, n19, n18, n17, musicPlayerModule$Backdrop, bl2, n16, n22);
                                                                                musicPlayerModule2 = this;
                                                                                if (bl) break block93;
                                                                                if (!musicPlayerModule2.albumArt.getValue()) break block94;
                                                                                musicPlayerModule2 = this;
                                                                            }
                                                                            n15 = musicPlayerModule2.artFor(nowPlaying, n14, 5, f);
                                                                            if (bl) break block95;
                                                                            if (n15 == 0) break block94;
                                                                            n15 = 1;
                                                                            break block95;
                                                                        }
                                                                        n15 = 0;
                                                                    }
                                                                    n13 = n15;
                                                                    n12 = n13;
                                                                    if (bl) break block96;
                                                                    if (n12 == 0) break block97;
                                                                    this.drawArt(drawContext, this.hudX + 6, this.hudY + 6, n14);
                                                                }
                                                                int n11 = this.hudX;
                                                                n11 = 6;
                                                                if (bl) break block98;
                                                                n12 = n12 + n11;
                                                            }
                                                            n11 = n13 != 0 ? n14 + 7 : 2;
                                                        }
                                                        n6 = n12 + n11;
                                                        n5 = this.hudX + n2 - 6 - 1;
                                                        n4 = Math.max(10, n5 - n6);
                                                        nowPlaying3 = nowPlaying;
                                                        if (bl) break block99;
                                                        if (nowPlaying3 != null) break block100;
                                                        MusicPlayerModule.crisp(drawContext, textRenderer, MusicPlayerModule.trim(textRenderer, SmtcClient.getStatus(), musicPlayerModule$Font, n4), musicPlayerModule$Font, n6, this.hudY + 17, n7, false);
                                                        if (!bl) break block101;
                                                    }
                                                    nowPlaying3 = nowPlaying;
                                                }
                                                if (bl) break block102;
                                                if (!nowPlaying3.title().isBlank()) break block103;
                                                string4 = b[2];
                                                break block104;
                                            }
                                            nowPlaying3 = nowPlaying;
                                        }
                                        string4 = nowPlaying3.title();
                                    }
                                    string2 = string4;
                                    nowPlaying2 = nowPlaying;
                                    if (bl) break block105;
                                    if (!nowPlaying2.artist().isBlank()) break block106;
                                    string3 = b[7];
                                    break block107;
                                }
                                nowPlaying2 = nowPlaying;
                            }
                            string3 = nowPlaying2.artist();
                        }
                        string = string3;
                        musicPlayerModule = this;
                        if (bl) break block108;
                        if (!musicPlayerModule.showProgress.getValue()) break block109;
                        n3 = this.hudY + 6 + 1;
                        break block110;
                    }
                    musicPlayerModule = this;
                }
                n3 = musicPlayerModule.hudY + 12;
            }
            int n28 = n3;
            MusicPlayerModule.crisp(drawContext, textRenderer, MusicPlayerModule.trim(textRenderer, string2, musicPlayerModule$Font2, n4), musicPlayerModule$Font2, n6, n28, n9, false);
            if (bl) return;
            MusicPlayerModule.crisp(drawContext, textRenderer, MusicPlayerModule.trim(textRenderer, string, musicPlayerModule$Font, n4), musicPlayerModule$Font, n6, n28 + 11, n8, false);
            if (!this.showProgress.getValue()) break block101;
            String string5 = MusicPlayerModule.fmt(nowPlaying.progressNow());
            String string6 = MusicPlayerModule.fmt(nowPlaying.durationMs());
            int n29 = this.hudY + n - 6 - 7;
            int n30 = Math.round(MusicPlayerModule.width(textRenderer, string5, musicPlayerModule$Font));
            int n31 = Math.round(MusicPlayerModule.width(textRenderer, string6, musicPlayerModule$Font));
            if (bl) return;
            MusicPlayerModule.crisp(drawContext, textRenderer, string5, musicPlayerModule$Font, n6, n29, n7, false);
            MusicPlayerModule.crisp(drawContext, textRenderer, string6, musicPlayerModule$Font, n5 - n31, n29, n7, false);
            int n32 = n6 + n30 + 5;
            int n33 = n5 - n31 - 5;
            int n34 = n29 + 3;
            if (n33 - n32 > 4) {
                int n35;
                int n36;
                block113: {
                    block114: {
                        block111: {
                            block112: {
                                AaRoundRect.draw(drawContext, n32, n34, n33 - n32, 2, 1, MusicPlayerModule.argb(Im.TRACK, 1.0f));
                                float f = this.eased(nowPlaying);
                                n36 = n32 + Math.round((float)(n33 - n32) * f);
                                n35 = this.dynamicAccent.getValue();
                                if (bl) break block111;
                                if (n35 == 0) break block112;
                                if (this.coverTint() == null) break block112;
                                n35 = MusicPlayerModule.a(31805, 285275476734662709L) | MusicPlayerModule.vivid(this.coverTint()[0]);
                                break block113;
                            }
                            n35 = n10;
                        }
                        if (bl) break block113;
                        if (n35 == 0) break block114;
                        n35 = this.progressColor.getARGB();
                        break block113;
                    }
                    n35 = MusicPlayerModule.argb(Menu.ACCENT, 1.0f);
                }
                int n37 = n35;
                if (bl) return;
                if (n36 - n32 < 2) break block101;
                AaRoundRect.draw(drawContext, n32, n34, 2, 2, 1, n37);
                if (bl) return;
                AaRoundRect.draw(drawContext, n36 - 2, n34, 2, 2, 1, n37);
                if (n36 - n32 <= 2) break block101;
                drawContext.method_25294(n32 + 1, n34, n36 - 1, n34 + 2, n37);
            }
        }
        this.lastBounds = new int[]{this.hudX, this.hudY, n2, n};
    }

    private void backdrop(DrawContext drawContext, int n, int n2, int n3, int n4, int n5, MusicPlayerModule$Backdrop musicPlayerModule$Backdrop, boolean bl, int n6, int n7) {
        int n8;
        int n9;
        int n10;
        int n11;
        int n12;
        int n13;
        int n14;
        DrawContext drawContext2;
        block27: {
            block28: {
                int n15;
                boolean bl2;
                block26: {
                    block25: {
                        int n16;
                        int n17;
                        block24: {
                            int[] nArray;
                            block22: {
                                int[] nArray2;
                                block23: {
                                    block20: {
                                        block21: {
                                            bl2 = AmbienceModule.b;
                                            if (musicPlayerModule$Backdrop != MusicPlayerModule$Backdrop.None) break block20;
                                            if (n7 == 0) break block21;
                                            AaRoundRect.draw(drawContext, n, n2, n3, n4, n5, 0, n7);
                                        }
                                        return;
                                    }
                                    int[] nArray3 = bl ? this.coverTint() : null;
                                    nArray2 = nArray3;
                                    int n18 = musicPlayerModule$Backdrop == MusicPlayerModule$Backdrop.Glass ? Math.min(n6 >>> 24, 140) << 24 : n6 & MusicPlayerModule.a(27147, 1587384705279086105L);
                                    int n19 = n18;
                                    n17 = n19;
                                    nArray = nArray2;
                                    if (bl2) break block22;
                                    if (nArray != null) break block23;
                                    n16 = n6;
                                    break block24;
                                }
                                nArray = nArray2;
                            }
                            n16 = nArray[0];
                        }
                        n15 = n17 | n16 & MusicPlayerModule.a(16931, 2613022266237085236L);
                        if (bl2) break block25;
                        if (musicPlayerModule$Backdrop != MusicPlayerModule$Backdrop.Dark) break block26;
                        AaRoundRect.draw(drawContext, n, n2, n3, n4, n5, n15, n7);
                    }
                    return;
                }
                drawContext2 = drawContext;
                n14 = n;
                n13 = n2;
                n12 = n3;
                n11 = n4;
                n10 = n5;
                n9 = Math.min(n15 >>> 24, 115) << 24;
                n8 = n15 & MusicPlayerModule.a(16931, 2613022266237085236L);
                if (bl2) break block27;
                n9 = n9 | n8;
                if (n7 != 0) break block28;
                n8 = MusicPlayerModule.a(2258, 7865441138599046354L);
                break block27;
            }
            n8 = n7;
        }
        AaRoundRect.draw(drawContext2, n14, n13, n12, n11, n10, n9, n8);
        AaRoundRect.draw(drawContext, n, n2, n3, n4, n5, MusicPlayerModule.a(14, 2539501333789516821L));
        drawContext.method_25294(n + n5, n2 + 1, n + n3 - n5, n2 + 2, MusicPlayerModule.a(15108, 5489306020469710599L));
    }

    private int[] coverTint() {
        int[] nArray;
        block30: {
            MusicPlayerModule musicPlayerModule;
            long l;
            block28: {
                block29: {
                    int[] nArray2;
                    block23: {
                        boolean bl;
                        block24: {
                            int[] nArray3;
                            int[] nArray4;
                            block27: {
                                MusicPlayerModule musicPlayerModule2;
                                block25: {
                                    block26: {
                                        int[] nArray5;
                                        block22: {
                                            SmtcClient$Art smtcClient$Art;
                                            SmtcClient$Art smtcClient$Art2;
                                            block20: {
                                                block21: {
                                                    smtcClient$Art2 = this.art;
                                                    bl = AmbienceModule.b;
                                                    smtcClient$Art = smtcClient$Art2;
                                                    if (bl) break block20;
                                                    if (smtcClient$Art != null) break block21;
                                                    nArray5 = null;
                                                    break block22;
                                                }
                                                smtcClient$Art = smtcClient$Art2;
                                            }
                                            nArray5 = AlbumColor.get(smtcClient$Art.key(), smtcClient$Art2::argb);
                                        }
                                        nArray4 = nArray5;
                                        l = System.currentTimeMillis();
                                        nArray2 = nArray4;
                                        if (bl) break block23;
                                        if (nArray2 == null) break block24;
                                        nArray2 = nArray4;
                                        if (bl) break block23;
                                        if (Arrays.equals(nArray2, this.tintTo)) break block24;
                                        MusicPlayerModule musicPlayerModule3 = this;
                                        musicPlayerModule2 = this;
                                        if (bl) break block25;
                                        if (musicPlayerModule2.tintTo != null) break block26;
                                        nArray3 = nArray4;
                                        break block27;
                                    }
                                    musicPlayerModule2 = this;
                                }
                                nArray3 = musicPlayerModule2.tintAt(l);
                            }
                            musicPlayerModule3.tintFrom = nArray3;
                            this.tintTo = nArray4;
                            this.tintStart = l;
                        }
                        musicPlayerModule = this;
                        if (bl) break block28;
                        nArray2 = musicPlayerModule.tintTo;
                    }
                    if (nArray2 != null) break block29;
                    nArray = null;
                    break block30;
                }
                musicPlayerModule = this;
            }
            nArray = musicPlayerModule.tintAt(l);
        }
        return nArray;
    }

    private int[] tintAt(long l) {
        float f = Math.min(1.0f, (float)(l - this.tintStart) / 400.0f);
        return new int[]{MusicPlayerModule.lerpRgb(this.tintFrom[0], this.tintTo[0], f), MusicPlayerModule.lerpRgb(this.tintFrom[1], this.tintTo[1], f)};
    }

    private static int argb(float[] fArray, float f) {
        int n = Math.round(Math.min(1.0f, Math.max(0.0f, fArray[3] * f)) * 255.0f);
        return n << 24 | Math.round(fArray[0] * 255.0f) << 16 | Math.round(fArray[1] * 255.0f) << 8 | Math.round(fArray[2] * 255.0f);
    }

    private static int vivid(int n) {
        int n2;
        block6: {
            float f;
            float f2;
            block4: {
                int n3;
                block5: {
                    float[] fArray = Color.RGBtoHSB(n >> 16 & 0xFF, n >> 8 & 0xFF, n & 0xFF, null);
                    n3 = Color.HSBtoRGB(fArray[0], Math.min(0.8f, Math.max(0.45f, fArray[1])), 0.95f) & MusicPlayerModule.a(16931, 2613022266237085236L);
                    f2 = (0.2126f * (float)(n3 >> 16 & 0xFF) + 0.7152f * (float)(n3 >> 8 & 0xFF) + 0.0722f * (float)(n3 & 0xFF)) / 255.0f;
                    boolean bl = AmbienceModule.b;
                    float f3 = f2 - 0.6f;
                    f = f3 == 0.0f ? 0 : (f3 > 0.0f ? 1 : -1);
                    if (bl) break block4;
                    if (f < 0) break block5;
                    n2 = n3;
                    break block6;
                }
                f = n3;
            }
            n2 = MusicPlayerModule.lerpRgb((int)f, MusicPlayerModule.a(16931, 2613022266237085236L), (0.6f - f2) / (1.0f - f2));
        }
        return n2;
    }

    private static int fade(int n, float f) {
        return Math.round((float)(n >>> 24) * f) << 24 | n & MusicPlayerModule.a(16931, 2613022266237085236L);
    }

    private static float menuAlpha() {
        return 0.85f - 0.35f * Themes.glass();
    }

    private static int themeLyricsBg() {
        int n = Themes.look() == Themes$Look.DAYLIGHT ? MusicPlayerModule.a(4529, 4045452029605000615L) : MusicPlayerModule.argb(Im.BG, MusicPlayerModule.menuAlpha() * 0.7f);
        return n;
    }

    
    private float eased(NowPlaying nowPlaying) {
        float f;
        block6: {
            boolean bl;
            block5: {
                long l = Math.max(1L, nowPlaying.durationMs());
                bl = AmbienceModule.b;
                f = Math.min(1.0f, Math.max(0.0f, (float)nowPlaying.progressNow() / (float)l));
                if (bl) break block5;
                if (!(Math.abs(f - this.easedFrac) > 0.25f)) break block6;
                this.easedFrac = f;
            }
            if (!bl) return this.easedFrac;
        }
        this.easedFrac += (f - this.easedFrac) * 0.25f;
        return this.easedFrac;
    }

    private static String trim(TextRenderer textRenderer, String string, MusicPlayerModule$Font musicPlayerModule$Font, int n) {
        boolean bl = AmbienceModule.b;
        if (MusicPlayerModule.width(textRenderer, string, musicPlayerModule$Font) <= (float)n) {
            return string;
        }
        String string2 = string;
        while (string2.length() > 1 && MusicPlayerModule.width(textRenderer, string2 + "\u2026", musicPlayerModule$Font) > (float)n) {
            string2 = string2.substring(0, string2.length() - 1);
            if (!bl) continue;
        }
        return string2.stripTrailing() + "\u2026";
    }

    private static void crisp(DrawContext drawContext, TextRenderer textRenderer, String string, MusicPlayerModule$Font musicPlayerModule$Font, float f, float f2, int n, boolean bl) {
        SdfText.draw(drawContext, musicPlayerModule$Font.face(), string, f, f2, musicPlayerModule$Font.size(), n, bl);
    }

    private static float width(TextRenderer textRenderer, String string, MusicPlayerModule$Font musicPlayerModule$Font) {
        return SdfText.width(musicPlayerModule$Font.face(), string, musicPlayerModule$Font.size());
    }

    
    private void drawPanel(DrawContext var1_1, TextRenderer var2_2, NowPlaying var3_3) {
        block63: {
            block52: {
                block61: {
                    block62: {
                        block57: {
                            block59: {
                                block60: {
                                    block58: {
                                        block53: {
                                            block55: {
                                                block56: {
                                                    block54: {
                                                        block51: {
                                                            block50: {
                                                                block48: {
                                                                    block49: {
                                                                        block47: {
                                                                            block46: {
                                                                                block45: {
                                                                                    var5_4 = (int)this.width.getValue();
                                                                                    var6_5 = 44;
                                                                                    var7_6 = 6;
                                                                                    var4_7 = AmbienceModule.b;
                                                                                    var8_8 = 32;
                                                                                    this.backdrop(var1_1, this.hudX, this.hudY, var5_4, 44, 9, this.background.getValue(), this.dynamic.getValue(), MusicPlayerModule.a(20818, 8615110532738577756L), 0);
                                                                                    v0 = this;
                                                                                    if (var4_7) break block45;
                                                                                    if (!v0.albumArt.getValue()) break block46;
                                                                                    v0 = this;
                                                                                }
                                                                                v3 = v0.artFor(var3_3, 32, 6, this.px(var1_1));
                                                                                if (var4_7) break block47;
                                                                                if (v3 == 0) break block46;
                                                                                v3 = 1;
                                                                                break block47;
                                                                            }
                                                                            v3 = 0;
                                                                        }
                                                                        var9_9 = v3;
                                                                        v5 = var9_9;
                                                                        if (var4_7) break block48;
                                                                        if (v5 == 0) break block49;
                                                                        this.drawArt(var1_1, this.hudX + 6, this.hudY + 6, 32);
                                                                    }
                                                                    v5 = this.hudX;
                                                                    v8 = 6;
                                                                    if (var4_7) break block50;
                                                                    v5 = v5 + v8;
                                                                }
                                                                v8 = var9_9 != 0 ? 38 : 0;
                                                            }
                                                            var10_10 = v5 + v8;
                                                            var11_11 = this.hudX + var5_4 - 6;
                                                            var12_12 = Math.max(10, var11_11 - var10_10);
                                                            v11 = var3_3;
                                                            if (var4_7) break block51;
                                                            if (v11 == null) {
                                                            }
                                                            var13_13 = SmtcClient.getStatus();
                                                            var1_1.method_25303(var2_2, MusicPlayerModule.trim(var2_2, var13_13, var12_12), var10_10, this.hudY + 13, MusicPlayerModule.a(6110, 7030140167361442766L));
                                                            if (!var4_7) break block52;
lbl75:

                                                            v11 = var3_3;
                                                        }
                                                        v14 = v11.title();
                                                        if (var4_7) break block53;
                                                        if (v14 == null) break block54;
                                                        v16 = var3_3;
                                                        if (var4_7) break block55;
                                                        if (!v16.title().isBlank()) break block56;
                                                    }
                                                    v14 = MusicPlayerModule.b[57];
                                                    break block53;
                                                }
                                                v16 = var3_3;
                                            }
                                            v14 = v16.title();
                                        }
                                        var13_13 = v14;
                                        v20 = var3_3.artist();
                                        if (var4_7) break block57;
                                        if (v20 == null) break block58;
                                        v22 = var3_3;
                                        if (var4_7) break block59;
                                        if (!v22.artist().isBlank()) break block60;
                                    }
                                    v20 = MusicPlayerModule.b[38];
                                    break block57;
                                }
                                v22 = var3_3;
                            }
                            v20 = v22.artist();
                        }
                        var14_14 = v20;
                        var1_1.method_25303(var2_2, MusicPlayerModule.trim(var2_2, var13_13, var12_12), var10_10, this.hudY + 7, -1);
                        var15_15 = MusicPlayerModule.fmt(var3_3.progressNow()) + MusicPlayerModule.b[18] + MusicPlayerModule.fmt(var3_3.durationMs());
                        var16_16 = (int)((float)var2_2.method_1727(var15_15) * 0.75f);
                        var17_17 = Math.max(10, var12_12 - var16_16 - 4);
                        var1_1.method_51448().pushMatrix();
                        var1_1.method_51448().scale(0.75f, 0.75f);
                        var18_18 = Math.round((float)(this.hudY + 18) / 0.75f);
                        var1_1.method_25303(var2_2, MusicPlayerModule.trim(var2_2, var14_14, (int)((float)var17_17 / 0.75f)), Math.round((float)var10_10 / 0.75f), var18_18, MusicPlayerModule.a(792, 362947213890397963L));
                        if (var4_7) break block61;
                        if (var3_3.durationMs() <= 0L) break block62;
                        var1_1.method_25303(var2_2, var15_15, Math.round((float)(var11_11 - var16_16) / 0.75f), var18_18, MusicPlayerModule.a(8495, 780656399505491237L));
                    }
                    var1_1.method_51448().popMatrix();
                    if (var4_7) break block63;
                }
                if (this.showProgress.getValue()) {
                    var19_19 = this.hudY + 44 - 5 - 3;
                    var20_20 = var11_11 - var10_10;
                    var21_21 = this.eased(var3_3);
                    var1_1.method_25294(var10_10, var19_19, var10_10 + var20_20, var19_19 + 3, MusicPlayerModule.a(31752, 4668143792576747521L));
                    var1_1.method_25294(var10_10, var19_19, var10_10 + Math.round((float)var20_20 * var21_21), var19_19 + 3, this.accent.getARGB());
                }
            }
            this.lastBounds = new int[]{this.hudX, this.hudY, var5_4, 44};
        }
    }

    
    private void drawLyrics(DrawContext var1_1, TextRenderer var2_2, NowPlaying var3_3) {
        block105: {
            block104: {
                block103: {
                    block102: {
                        block101: {
                            block99: {
                                block100: {
                                    block98: {
                                        block96: {
                                            block97: {
                                                block95: {
                                                    block94: {
                                                        block93: {
                                                            block92: {
                                                                block89: {
                                                                    block91: {
                                                                        block90: {
                                                                            block84: {
                                                                                block88: {
                                                                                    block86: {
                                                                                        block87: {
                                                                                            block85: {
                                                                                                block83: {
                                                                                                    block79: {
                                                                                                        block80: {
                                                                                                            block81: {
                                                                                                                block82: {
                                                                                                                    block78: {
                                                                                                                        block76: {
                                                                                                                            block77: {
                                                                                                                                block75: {
                                                                                                                                    block73: {
                                                                                                                                        block74: {
                                                                                                                                            block71: {
                                                                                                                                                block72: {
                                                                                                                                                    var4_4 = AmbienceModule.b;
                                                                                                                                                    v0 = this;
                                                                                                                                                    if (var4_4) break block71;
                                                                                                                                                    if (v0.layout.getValue() != MusicPlayerModule$Layout.World) break block72;
                                                                                                                                                    return;
                                                                                                                                                }
                                                                                                                                                v0 = this;
                                                                                                                                            }
                                                                                                                                            var5_5 = v0.lyricsBar.getHudX();
                                                                                                                                            var6_6 = this.lyricsBar.getHudY();
                                                                                                                                            v3 = var3_3;
                                                                                                                                            if (var4_4) break block73;
                                                                                                                                            if (v3 != null) break block74;
                                                                                                                                            v5 = Lyrics.NONE;
                                                                                                                                            break block75;
                                                                                                                                        }
                                                                                                                                        v3 = var3_3;
                                                                                                                                    }
                                                                                                                                    v5 = Lyrics.get(v3);
                                                                                                                                }
                                                                                                                                var7_7 = v5;
                                                                                                                                var8_8 = var7_7.lines();
                                                                                                                                var9_9 = var7_7.synced();
                                                                                                                                v7 = var3_3 == null ? 0L : this.lyricClock(var3_3);
                                                                                                                                var10_10 = v7;
                                                                                                                                v9 = var3_3;
                                                                                                                                if (var4_4) break block76;
                                                                                                                                if (v9 != null) break block77;
                                                                                                                                v11 = null;
                                                                                                                                break block78;
                                                                                                                            }
                                                                                                                            v9 = var3_3;
                                                                                                                        }
                                                                                                                        v11 = v9.artKey();
                                                                                                                    }
                                                                                                                    var12_11 = v11;
                                                                                                                    v13 = var8_8;
                                                                                                                    if (var4_4) break block79;
                                                                                                                    if (!v13.isEmpty()) break block80;
                                                                                                                    v15 = ModuleManager.editing();
                                                                                                                    if (var4_4) break block81;
                                                                                                                    if (v15) break block82;
                                                                                                                    return;
                                                                                                                }
                                                                                                                var8_8 = MusicPlayerModule.PREVIEW;
                                                                                                                v15 = true;
                                                                                                            }
                                                                                                            var9_9 = v15;
                                                                                                            var10_10 = MusicPlayerModule.b(27173, 6295197273694667631L);
                                                                                                            var12_11 = MusicPlayerModule.b[53];
                                                                                                        }
                                                                                                        v13 = var8_8;
                                                                                                    }
                                                                                                    var13_12 = Lyrics.at(v13, var10_10);
                                                                                                    var14_13 = System.nanoTime();
                                                                                                    v18 = var13_12;
                                                                                                    if (var4_4) break block83;
                                                                                                    if (v18 == this.shownLine) {
                                                                                                    }
                                                                                                    v18 = (int)Objects.equals(var12_11, this.shownKey);
                                                                                                }
                                                                                                if (v18 != 0) break block84;
lbl109:

                                                                                                v21 = this;
                                                                                                v22 = var12_11;
                                                                                                if (var4_4) break block85;
                                                                                                if (v22 == null) break block86;
                                                                                                v22 = var12_11;
                                                                                            }
                                                                                            v25 = v22.equals(this.shownKey);
                                                                                            if (var4_4) break block87;
                                                                                            if (v25 == 0) break block86;
                                                                                            v25 = var13_12;
                                                                                        }
                                                                                        if (v25 != this.shownLine + 1) break block86;
                                                                                        v27 = var14_13;
                                                                                        break block88;
                                                                                    }
                                                                                    v27 = 0L;
                                                                                }
                                                                                v21.scrollStart = v27;
                                                                                this.shownKey = var12_11;
                                                                                this.shownLine = var13_12;
                                                                            }
                                                                            var16_14 = Math.min(1.0f, (float)(var14_13 - this.scrollStart) / 3.0E8f);
                                                                            var16_14 = 1.0f - (1.0f - var16_14) * (1.0f - var16_14) * (1.0f - var16_14);
                                                                            var17_15 = var1_1.method_51448();
                                                                            var17_15.pushMatrix();
                                                                            var18_16 = this.getHudScale();
                                                                            v29 = var18_16;
                                                                            if (var4_4) break block89;
                                                                            if (v29 == 1.0f) break block90;
                                                                            v31 = this;
                                                                            if (var4_4) break block91;
                                                                            if (!v31.hudScalable()) break block90;
                                                                            var17_15.translate((float)this.hudX, (float)this.hudY);
                                                                            var17_15.scale(1.0f / var18_16, 1.0f / var18_16);
                                                                            var17_15.translate((float)(-this.hudX), (float)(-this.hudY));
                                                                        }
                                                                        v31 = this;
                                                                    }
                                                                    v29 = v31.lyricsBar.getHudScale();
                                                                }
                                                                var19_17 = v29;
                                                                if (var4_4) break block92;
                                                                if (var19_17 == 1.0f) break block93;
                                                                var17_15.translate((float)var5_5, (float)var6_6);
                                                                var17_15.scale(var19_17, var19_17);
                                                            }
                                                            var17_15.translate((float)(-var5_5), (float)(-var6_6));
                                                        }
                                                        var20_18 = MusicPlayerModule.LYRICS_FACE;
                                                        var21_19 = this.strip(var2_2, var8_8, var20_18);
                                                        if (!var9_9 || this.layout.getValue() != MusicPlayerModule$Layout.Ticker) break block94;
                                                        v38 = 1;
                                                        break block95;
                                                    }
                                                    v38 = 0;
                                                }
                                                var22_20 = v38;
                                                var23_21 = this.linesShown.getValueInt();
                                                v40 = var22_20;
                                                if (var4_4) break block96;
                                                if (v40 == 0) break block97;
                                                v42 = this.tickerWidth.getValueInt();
                                                break block98;
                                            }
                                            v40 = 150;
                                        }
                                        v42 = Math.max(v40, Math.min(260, Math.round((float)var21_19.widest * 1.2f) + 20));
                                    }
                                    var24_22 = v42;
                                    v44 = 22;
                                    v45 = var22_20;
                                    if (var4_4) break block99;
                                    if (v45 == 0) break block100;
                                    v47 = 0;
                                    break block101;
                                }
                                v45 = var23_21 - 1;
                            }
                            v47 = v45 * 15;
                        }
                        var25_23 = v44 + v47;
                        var26_24 = var6_6 + 7 + 1;
                        var27_25 = var5_5 - var24_22 / 2;
                        var28_26 = this.lyricsBackground.getValue();
                        if (var28_26 != MusicPlayerModule$Backdrop.None && Themes.look() != Themes$Look.DAYLIGHT) break block102;
                        v50 = 0;
                        break block103;
                    }
                    v50 = MusicPlayerModule.argb(Im.RIM, 1.0f);
                }
                var29_27 = v50;
                this.backdrop(var1_1, var27_25, var6_6, var24_22, var25_23, 6, var28_26, this.lyricsDynamic.getValue(), this.lyricsBgColor.getARGB(), var29_27);
                v52 = v53;
                v54 = v53;
                v55 = var1_1;
                v56 = var2_2;
                v57 = var20_18;
                v58 = var10_10;
                v59 = var28_26 == MusicPlayerModule$Backdrop.None;
                v52(v55, v56, v57, v58, v59, var9_9, this.highlightRgb());
                var30_28 = v54;
                if (var4_4) break block104;
                var1_1.method_44379(var27_25, var6_6, var27_25 + var24_22, var6_6 + var25_23);
                SdfText.begin();
                if (var22_20 != 0) {
                }
                this.drawTicker(var30_28, var21_19, var27_25, var26_24, var24_22);
            }
            if (!var4_4) break block105;
lbl302:

            this.drawLines(var30_28, var8_8, var13_12, var16_14, var23_21, var27_25, var26_24, var24_22);
        }
        SdfText.end(var1_1);
        var1_1.method_44380();
        var17_15.popMatrix();
        this.lyricsBar.bounds = new int[]{var27_25, var6_6, var24_22, var25_23};
    }

    private int highlightRgb() {
        MusicPlayerModule musicPlayerModule;
        block7: {
            block8: {
                boolean bl = AmbienceModule.b;
                musicPlayerModule = this;
                if (bl) break block7;
                if (!musicPlayerModule.dynamicHighlight.getValue()) break block8;
                musicPlayerModule = this;
                if (bl) break block7;
                int[] nArray = musicPlayerModule.coverTint();
                if (nArray != null) {
                    return MusicPlayerModule.vivid(nArray[0]);
                }
            }
            musicPlayerModule = this;
        }
        return musicPlayerModule.lyricsAccent.getRGB() & MusicPlayerModule.a(16931, 2613022266237085236L);
    }

    
    private void drawLines(MusicPlayerModule$Draw var1_1, List<Lyrics$Line> var2_2, int var3_3, float var4_4, int var5_5, int var6_6, int var7_7, int var8_8) {
        var9_9 = AmbienceModule.b;
        v0 = var5_5;
        if (!var9_9) {
            v0 = v0 >= 3 ? var5_5 / 3 : 0;
        }
        var10_10 = v0;
        var11_11 = var5_5 - 1 - var10_10;
        var12_12 = (1.0f - var4_4) * 15.0f;
        var13_13 = var8_8 - 20;
        var14_14 = 1.2f;
        var15_15 = 0.85f;
        for (var16_16 = var3_3 - var10_10 - 1; var16_16 <= var3_3 + var11_11; ++var16_16) {
            block36: {
                block35: {
                    v1 = var16_16;
                    if (!var9_9) {
                        if (v1 < 0) continue;
                        v1 = var16_16;
                    }
                    if (v1 >= var2_2.size()) continue;
                    var17_17 = var2_2.get(var16_16);
                    v2 = var17_17.text().isEmpty();
                    if (!var9_9) {
                        if (v2 != 0) continue;
                        v2 = var16_16;
                    }
                    if (var9_9) // goto
                    if (v2 < var3_3 - var10_10) {
                        v3 = 1.0f - var4_4;
                    } else {
                        v2 = var16_16;
lbl27:

                        v3 = v2 == var3_3 + var11_11 ? var4_4 : 1.0f;
                    }
                    var18_18 = v3;
                    v4 = var16_16;
                    if (var9_9) // goto
                    if (v4 == var3_3) {
                        v5 = var4_4;
                    } else {
                        v4 = var16_16;
lbl35:

                        v5 = v4 == var3_3 - 1 ? 1.0f - var4_4 : 0.0f;
                    }
                    var19_19 = v5;
                    v6 = var16_16;
                    if (!var9_9) {
                        v6 = v6 < var3_3 ? 115 : 153;
                    }
                    var20_20 = v6;
                    var21_21 = var15_15 + (var14_14 - var15_15) * var19_19;
                    var22_22 = var1_1.face();
                    var23_23 = MusicPlayerModule.lineWidth(var1_1.tr(), var17_17, var22_22);
                    v7 = var23_23 * var21_21;
                    if (!var9_9) {
                        if (v7 > (float)var13_13) {
                            var21_21 = (float)var13_13 / var23_23;
                        }
                        v7 = var23_23 * var21_21;
                    }
                    var24_24 = v7;
                    v8 = this.alignment.getValue().ordinal();
                    if (var9_9) break block35;
                    switch (v8) {
                        default: {
                            throw new MatchException(null, null);
                        }
                        case 0: {
                            v9 = var6_6 + 10;
                            break block36;
                        }
                        case 2: {
                            v9 = (float)(var6_6 + var8_8 - 10) - var24_24;
                            break block36;
                        }
                        case 1: {
                            v8 = var6_6;
                        }
                    }
                }
                v9 = (float)v8 + ((float)var8_8 - var24_24) / 2.0f;
            }
            var25_25 = v9;
            var26_26 = (float)(var7_7 + (var16_16 - (var3_3 - var10_10)) * 15) + var12_12;
            var27_27 = var1_1.ctx().method_51448();
            var27_27.pushMatrix();
            v10 = var27_27;
            if (var9_9) continue;
            v10.translate(var25_25, var26_26 + (1.0f - var21_21) * 8.0f / 2.0f);
            if (var21_21 != 1.0f) {
                var27_27.scale(var21_21, var21_21);
            }
            this.drawWords(var1_1, var22_22, var17_17, var19_19, var20_20, var18_18);
            v10 = var27_27.popMatrix();
            if (!var9_9) continue;
        }
    }

    private float wordLit(Lyrics$Word lyrics$Word, long l) {
        if (this.highlight.getValue() == MusicPlayerModule$Highlight.Line) {
            return 1.0f;
        }
        return Math.min(1.0f, Math.max(0.0f, (float)(l - lyrics$Word.start()) / (float)Math.max(1L, lyrics$Word.end() - lyrics$Word.start())));
    }

    private float glowFor(Lyrics$Word lyrics$Word, long l) {
        float f = this.highlight.getValue() == MusicPlayerModule$Highlight.Line ? 1.0f : MusicPlayerModule.active(lyrics$Word, l);
        return f;
    }

    
    private void drawWords(MusicPlayerModule$Draw musicPlayerModule$Draw, MusicPlayerModule$Font musicPlayerModule$Font, Lyrics$Line lyrics$Line, float f, int n, float f2) {
        float f3 = MusicPlayerModule.width(musicPlayerModule$Draw.tr(), " ", musicPlayerModule$Font);
        float f4 = 0.0f;
        boolean bl = AmbienceModule.b;
        for (Lyrics$Word lyrics$Word : lyrics$Line.words()) {
            block8: {
                block7: {
                    block6: {
                        if (bl) break block6;
                        if (!musicPlayerModule$Draw.synced()) break block7;
                        this.drawWord(musicPlayerModule$Draw, musicPlayerModule$Font, lyrics$Word.text(), f4, this.wordLit(lyrics$Word, musicPlayerModule$Draw.pos()), f, n, f2, this.glowFor(lyrics$Word, musicPlayerModule$Draw.pos()) * f);
                    }
                    if (!bl) break block8;
                }
                this.drawWord(musicPlayerModule$Draw, musicPlayerModule$Font, lyrics$Word.text(), f4, 1.0f, f, n, f2, 0.0f);
            }
            f4 += MusicPlayerModule.width(musicPlayerModule$Draw.tr(), lyrics$Word.text(), musicPlayerModule$Font) + f3;
            if (!bl) continue;
        }
    }

    private void drawWord(MusicPlayerModule$Draw musicPlayerModule$Draw, MusicPlayerModule$Font musicPlayerModule$Font, String string, float f, float f2, float f3, int n, float f4, float f5) {
        int n2;
        int n3;
        block14: {
            float f6;
            block15: {
                float f7;
                boolean bl;
                block13: {
                    block12: {
                        int n4;
                        block11: {
                            bl = AmbienceModule.b;
                            float f8 = musicPlayerModule$Draw.synced() ? f2 * f3 : 0.0f;
                            f6 = f8;
                            n4 = this.glow.getValue();
                            if (bl) break block11;
                            if (n4 == 0) break block12;
                            f7 = f5;
                            if (bl) break block13;
                            float f9 = f7 - 0.0f;
                            n4 = f9 == 0.0f ? 0 : (f9 > 0.0f ? 1 : -1);
                        }
                        if (n4 > 0) {
                            int n5 = Math.round(255.0f * Math.min(1.0f, f5 * f4));
                            this.glowAt(musicPlayerModule$Draw, musicPlayerModule$Font, string, f, n5);
                        }
                    }
                    f7 = 153.0f + 102.0f * f2;
                }
                float f10 = f7;
                float f11 = ((float)n + (f10 - (float)n) * f3) * f4;
                n3 = Math.round(f11);
                n2 = n3;
                if (bl) break block14;
                if (n2 > 0) break block15;
                return;
            }
            n2 = MusicPlayerModule.lerpRgb(MusicPlayerModule.a(16931, 2613022266237085236L), musicPlayerModule$Draw.lit(), f6);
        }
        int n6 = n2;
        MusicPlayerModule.crisp(musicPlayerModule$Draw.ctx(), musicPlayerModule$Draw.tr(), string, musicPlayerModule$Font, f, 0.0f, n3 << 24 | n6, musicPlayerModule$Draw.shadow());
    }

    private void glowAt(MusicPlayerModule$Draw musicPlayerModule$Draw, MusicPlayerModule$Font musicPlayerModule$Font, String string, float f, int n) {
        int n2;
        block6: {
            MusicPlayerModule musicPlayerModule;
            block4: {
                block5: {
                    boolean bl = AmbienceModule.b;
                    musicPlayerModule = this;
                    if (bl) break block4;
                    if (!musicPlayerModule.dynamicHighlight.getValue()) break block5;
                    n2 = this.highlightRgb();
                    break block6;
                }
                musicPlayerModule = this;
            }
            n2 = musicPlayerModule.glowColor.getRGB() & MusicPlayerModule.a(30331, 2038745063555912302L);
        }
        int n3 = n2;
        SdfText.glow(musicPlayerModule$Draw.ctx(), musicPlayerModule$Font.face(), string, f, 0.0f, musicPlayerModule$Font.size(), n << 24 | n3, this.glowStrength.getValueFloat() / 10.0f);
    }

    private void drawTicker(MusicPlayerModule$Draw musicPlayerModule$Draw, MusicPlayerModule$Strip musicPlayerModule$Strip, int n, int n2, int n3) {
        block17: {
            int n4;
            boolean bl;
            block15: {
                block16: {
                    bl = AmbienceModule.b;
                    n4 = musicPlayerModule$Strip.toks.isEmpty();
                    if (bl) break block15;
                    if (n4 == 0) break block16;
                    return;
                }
                n4 = n;
            }
            float f = (float)n4 + (float)n3 / 2.0f - this.shownOffset(musicPlayerModule$Strip, musicPlayerModule$Strip.at(musicPlayerModule$Draw.pos()));
            Matrix3x2fStack matrix3x2fStack = musicPlayerModule$Draw.ctx().method_51448();
            matrix3x2fStack.pushMatrix();
            matrix3x2fStack.translate(0.0f, (float)n2);
            for (MusicPlayerModule$Tok musicPlayerModule$Tok : musicPlayerModule$Strip.toks) {
                float f2;
                float f3;
                block19: {
                    int n5;
                    block18: {
                        f3 = f + musicPlayerModule$Tok.x;
                        if (bl) break block17;
                        f2 = f3 + musicPlayerModule$Tok.w;
                        n5 = n;
                        if (bl) break block18;
                        if (f2 < (float)n5) continue;
                        f2 = f3;
                        if (bl) break block19;
                        n5 = n + n3;
                    }
                    if (f2 > (float)n5) {
                        continue;
                    }
                    f2 = MusicPlayerModule.active(musicPlayerModule$Tok.word, musicPlayerModule$Draw.pos());
                }
                float f4 = f2;
                float f5 = f3 + musicPlayerModule$Tok.w / 2.0f;
                float f6 = MusicPlayerModule.smooth(Math.min(f5 - (float)n, (float)(n + n3) - f5) / 44.0f);
                if (f6 > 0.0f) {
                    this.drawTickerGlyph(musicPlayerModule$Draw, musicPlayerModule$Tok.text, f3, f4, f6);
                }
                if (!bl) continue;
            }
            matrix3x2fStack.popMatrix();
        }
    }

    private void drawTickerGlyph(MusicPlayerModule$Draw musicPlayerModule$Draw, String string, float f, float f2, float f3) {
        int n;
        int n2;
        block13: {
            block14: {
                int n3;
                boolean bl;
                block10: {
                    float f4;
                    float f5;
                    block12: {
                        block11: {
                            bl = AmbienceModule.b;
                            n3 = this.glow.getValue();
                            if (bl) break block10;
                            if (n3 == 0) break block11;
                            f5 = f2;
                            f4 = 0.0f;
                            if (bl) break block12;
                            if (!(f5 > f4)) break block11;
                            this.glowAt(musicPlayerModule$Draw, musicPlayerModule$Draw.face(), string, f, Math.round(255.0f * Math.min(1.0f, f2 * f3)));
                        }
                        f5 = 184.0f + 71.0f * f2;
                        f4 = f3;
                    }
                    n3 = Math.round(f5 * f4);
                }
                n2 = n3;
                n = n2;
                if (bl) break block13;
                if (n > 0) break block14;
                return;
            }
            n = MusicPlayerModule.lerpRgb(MusicPlayerModule.a(16931, 2613022266237085236L), musicPlayerModule$Draw.lit(), f2);
        }
        int n4 = n;
        MusicPlayerModule.crisp(musicPlayerModule$Draw.ctx(), musicPlayerModule$Draw.tr(), string, musicPlayerModule$Draw.face(), f, 0.0f, n2 << 24 | n4, musicPlayerModule$Draw.shadow());
    }

    
    private float shownOffset(MusicPlayerModule$Strip musicPlayerModule$Strip, float f) {
        float f2;
        block11: {
            boolean bl;
            block8: {
                MusicPlayerModule musicPlayerModule;
                block9: {
                    float f3;
                    float f4;
                    block10: {
                        long l = System.nanoTime();
                        f2 = (float)(l - this.shownNs) / 1.0E9f;
                        bl = AmbienceModule.b;
                        musicPlayerModule = this;
                        if (bl) break block8;
                        musicPlayerModule.shownNs = l;
                        if (musicPlayerModule$Strip != this.shownStrip) break block9;
                        f4 = f2;
                        f3 = 0.5f;
                        if (bl) break block10;
                        if (f4 > f3) break block9;
                        f4 = Math.abs(f - this.shown);
                        f3 = 60.0f;
                    }
                    if (!(f4 > f3)) break block11;
                }
                this.shownStrip = musicPlayerModule$Strip;
                musicPlayerModule = this;
            }
            musicPlayerModule.shown = f;
            if (!bl) return this.shown;
        }
        this.shown += (f - this.shown) * (1.0f - (float)Math.exp(-f2 / 0.12f));
        return this.shown;
    }

    private static float active(Lyrics$Word lyrics$Word, long l) {
        long l2;
        block7: {
            block8: {
                boolean bl = AmbienceModule.b;
                l2 = l;
                if (bl) break block7;
                if (l2 >= lyrics$Word.start()) break block8;
                return 0.0f;
            }
            l2 = l;
        }
        if (l2 < lyrics$Word.end()) {
            return Math.min(1.0f, (float)(l - lyrics$Word.start()) / 120.0f);
        }
        return Math.max(0.0f, 1.0f - (float)(l - lyrics$Word.end()) / 80.0f);
    }

    
    private long lyricClock(NowPlaying nowPlaying) {
        double d;
        double d2;
        long l;
        block14: {
            block15: {
                block11: {
                    MusicPlayerModule musicPlayerModule;
                    block12: {
                        double d3;
                        double d4;
                        boolean bl;
                        block13: {
                            long l2 = System.nanoTime();
                            l = nowPlaying.progressNow();
                            bl = AmbienceModule.b;
                            d2 = (double)(l2 - this.lyricNs) / 1000000.0;
                            musicPlayerModule = this;
                            if (bl) break block11;
                            musicPlayerModule.lyricNs = l2;
                            if (!nowPlaying.artKey().equals(this.lyricKey)) break block12;
                            d4 = d2;
                            if (bl) break block13;
                            if (d4 > 500.0) break block12;
                            d4 = Math.abs((double)l - this.lyricMs);
                        }
                        d = (d3 = d4 - 250.0) == 0.0 ? 0 : (d3 > 0.0 ? 1 : -1);
                        if (bl) break block14;
                        if (d <= 0) break block15;
                    }
                    this.lyricKey = nowPlaying.artKey();
                    musicPlayerModule = this;
                }
                musicPlayerModule.lyricMs = l;
                return l + MusicPlayerModule.b(2025, 1600557549826764461L) + (long)this.lyricsOffset.getValueInt();
            }
            d = (double)nowPlaying.playing();
        }
        if (d != false) {
            this.lyricMs += d2;
        }
        this.lyricMs += ((double)l - this.lyricMs) * Math.min(1.0, d2 / 250.0);
        return Math.round(this.lyricMs) + MusicPlayerModule.b(5142, 5830106151473249622L) + (long)this.lyricsOffset.getValueInt();
    }

    private static float smooth(float f) {
        f = Math.min(1.0f, Math.max(0.0f, f));
        return f * f * (3.0f - 2.0f * f);
    }

    private static float lineWidth(TextRenderer textRenderer, Lyrics$Line lyrics$Line, MusicPlayerModule$Font musicPlayerModule$Font) {
        float f;
        float f2;
        float f3;
        block2: {
            float f4 = 0.0f;
            float f5 = MusicPlayerModule.width(textRenderer, " ", musicPlayerModule$Font);
            Iterator<Lyrics$Word> iterator = lyrics$Line.words().iterator();
            boolean bl = AmbienceModule.b;
            while (iterator.hasNext()) {
                Lyrics$Word lyrics$Word = iterator.next();
                f3 = f4;
                f2 = MusicPlayerModule.width(textRenderer, lyrics$Word.text(), musicPlayerModule$Font);
                f = f5;
                if (!bl) {
                    f4 = f3 + (f2 + f);
                    if (!bl) continue;
                }
                break block2;
            }
            f3 = 0.0f;
            f2 = f4;
            f = f5;
        }
        return Math.max(f3, f2 - f);
    }

    private MusicPlayerModule$Strip strip(TextRenderer textRenderer, List<Lyrics$Line> list, MusicPlayerModule$Font musicPlayerModule$Font) {
        MusicPlayerModule musicPlayerModule;
        block14: {
            block15: {
                block12: {
                    MusicPlayerModule musicPlayerModule2;
                    block13: {
                        boolean bl = AmbienceModule.b;
                        musicPlayerModule2 = this;
                        if (bl) break block12;
                        if (musicPlayerModule2.strip == null) break block13;
                        musicPlayerModule2 = this;
                        if (bl) break block12;
                        if (musicPlayerModule2.strip.src != list) break block13;
                        musicPlayerModule = this;
                        if (bl) break block14;
                        if (musicPlayerModule.strip.face.equals(musicPlayerModule$Font)) break block15;
                    }
                    musicPlayerModule2 = this;
                }
                musicPlayerModule2.strip = new MusicPlayerModule$Strip(textRenderer, list, musicPlayerModule$Font);
            }
            musicPlayerModule = this;
        }
        return musicPlayerModule.strip;
    }

    private static int lerpRgb(int n, int n2, float f) {
        int n3 = Math.round((float)(n >> 16 & 0xFF) + (float)((n2 >> 16 & 0xFF) - (n >> 16 & 0xFF)) * f);
        int n4 = Math.round((float)(n >> 8 & 0xFF) + (float)((n2 >> 8 & 0xFF) - (n >> 8 & 0xFF)) * f);
        int n5 = Math.round((float)(n & 0xFF) + (float)((n2 & 0xFF) - (n & 0xFF)) * f);
        return n3 << 16 | n4 << 8 | n5;
    }

    @Override
    public List<DraggableHud> extraHuds() {
        List<DraggableHud> list;
        block6: {
            block5: {
                MusicPlayerModule musicPlayerModule;
                block4: {
                    boolean bl = AmbienceModule.b;
                    musicPlayerModule = this;
                    if (bl) break block4;
                    if (!musicPlayerModule.showLyrics.getValue()) break block5;
                    musicPlayerModule = this;
                }
                list = List.of(musicPlayerModule.lyricsBar);
                break block6;
            }
            list = List.of();
        }
        return list;
    }

    private static String fmt(long l) {
        long l2;
        block2: {
            block3: {
                boolean bl = AmbienceModule.b;
                l2 = l;
                if (bl) break block2;
                if (l2 >= 0L) break block3;
                l = 0L;
            }
            l2 = l / MusicPlayerModule.b(29353, 5436617138422881250L);
        }
        long l3 = l2;
        return l3 / MusicPlayerModule.b(483, 5947762425455831200L) + ":" + String.format(b[64], l3 % MusicPlayerModule.b(31361, 6565745774526993347L));
    }

    private static String trim(TextRenderer textRenderer, String string, int n) {
        boolean bl = AmbienceModule.b;
        if (textRenderer.method_1727(string) <= n) {
            return string;
        }
        String string2 = "\u2026";
        int n2 = textRenderer.method_1727(string2);
        StringBuilder stringBuilder = new StringBuilder();
        int n3 = 0;
        char[] cArray = string.toCharArray();
        int n4 = cArray.length;
        int n5 = 0;
        while (n5 < n4) {
            block9: {
                char c = cArray[n5];
                int n6 = textRenderer.method_1727(String.valueOf(c));
                if (bl) break block9;
                if (n3 + n6 + n2 > n) {
                    break;
                }
                stringBuilder.append(c);
                n3 += n6;
                ++n5;
            }
            if (!bl) continue;
        }
        return String.valueOf(stringBuilder) + string2;
    }

    @Override
    public void writeExtra(JsonObject jsonObject) {
        String[] stringArray = b;
        jsonObject.addProperty(stringArray[3], (Number)this.hudX);
        jsonObject.addProperty(stringArray[60], (Number)this.hudY);
        jsonObject.addProperty(stringArray[31], (Number)this.lyricsBar.dx);
        jsonObject.addProperty(stringArray[13], (Number)this.lyricsBar.y);
    }

    @Override
    public void readExtra(JsonObject jsonObject) {
        JsonObject jsonObject2;
        block19: {
            block20: {
                boolean bl;
                block18: {
                    String string;
                    block16: {
                        block17: {
                            bl = AmbienceModule.b;
                            jsonObject2 = jsonObject;
                            string = b[33];
                            if (bl) break block16;
                            if (!jsonObject2.has(string)) break block17;
                            this.hudX = jsonObject.get(b[3]).getAsInt();
                        }
                        jsonObject2 = jsonObject;
                        if (bl) break block18;
                        string = b[29];
                    }
                    if (jsonObject2.has(string)) {
                        this.hudY = jsonObject.get(b[60]).getAsInt();
                    }
                    jsonObject2 = jsonObject;
                }
                if (bl) break block19;
                if (!jsonObject2.has(b[39])) break block20;
                this.lyricsBar.dx = jsonObject.get(b[31]).getAsInt();
            }
            jsonObject2 = jsonObject;
        }
        if (jsonObject2.has(b[56])) {
            this.lyricsBar.y = jsonObject.get(b[13]).getAsInt();
        }
    }

    @Override
    public int getHudX() {
        return this.hudX;
    }

    @Override
    public int getHudY() {
        return this.hudY;
    }

    @Override
    public void setHudPos(int n, int n2) {
        this.hudX = n;
        this.hudY = n2;
    }

    @Override
    public int[] getLastBounds() {
        return this.lastBounds;
    }

    private static String lambda$artFor$13() {
        return b[22];
    }

    private void lambda$onDisable$12() {
        block4: {
            MusicPlayerModule musicPlayerModule;
            block5: {
                boolean bl = AmbienceModule.b;
                musicPlayerModule = this;
                if (bl) break block4;
                if (musicPlayerModule.artTexture == null) break block5;
                this.mc.method_1531().method_4615(ART_ID);
            }
            this.artTexture = null;
            this.artTexKey = null;
            musicPlayerModule = this;
        }
        musicPlayerModule.art = null;
    }

    private Boolean lambda$new$11() {
        boolean bl = this.layout.getValue() != MusicPlayerModule$Layout.World;
        return bl;
    }

    private Boolean lambda$new$10() {
        boolean bl = this.layout.getValue() == MusicPlayerModule$Layout.World;
        return bl;
    }

    private Boolean lambda$new$9() {
        boolean bl = this.layout.getValue() == MusicPlayerModule$Layout.Ticker;
        return bl;
    }

    private Boolean lambda$new$8() {
        boolean bl = this.layout.getValue() == MusicPlayerModule$Layout.Lines;
        return bl;
    }

    private Boolean lambda$new$7() {
        boolean bl = this.layout.getValue() == MusicPlayerModule$Layout.Lines;
        return bl;
    }

    private Boolean lambda$new$6() {
        boolean bl = this.lyricsBackground.getValue() != MusicPlayerModule$Backdrop.None;
        return bl;
    }

    private Boolean lambda$new$5() {
        boolean bl = this.lyricsBackground.getValue() != MusicPlayerModule$Backdrop.None;
        return bl;
    }

    private Boolean lambda$new$4() {
        boolean bl;
        block8: {
            block7: {
                MusicPlayerModule musicPlayerModule;
                boolean bl2;
                block6: {
                    bl2 = AmbienceModule.b;
                    musicPlayerModule = this;
                    if (bl2) break block6;
                    if (musicPlayerModule.style.getValue() != MusicPlayerModule$PlayerStyle.Card) break block7;
                    musicPlayerModule = this;
                }
                bl = musicPlayerModule.showProgress.getValue();
                if (bl2) break block8;
                if (!bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    private Boolean lambda$new$3() {
        boolean bl = this.colors.getValue() == MusicPlayerModule$Palette.Custom;
        return bl;
    }

    private Boolean lambda$new$2() {
        boolean bl = this.style.getValue() == MusicPlayerModule$PlayerStyle.Card;
        return bl;
    }

    private Boolean lambda$new$1() {
        boolean bl = this.style.getValue() == MusicPlayerModule$PlayerStyle.Classic;
        return bl;
    }

    private Boolean lambda$new$0() {
        boolean bl = this.background.getValue() != MusicPlayerModule$Backdrop.None;
        return bl;
    }

    
    static {
        block37: {
            block36: {
                block35: {
                    block34: {
                        block33: {
                            block32: {
                                var21 = new String[65];
                                var19_1 = 0;
                                var18_2 = "uU1=\u0002P$X_\u0006nC10AP\u0007wT(7MT#\u0006OO00A{\n`[ 2EQ\"WT'\u000efC-8OJ.\u0002[ :GM9\tcV!,O\u0003\fPN\u000ewT(7MT#\u0002[1-KP9\u0007fC-8OJ.\tCV!,O|,PN\reV,.\u0002P9P_->VK\bRH&/KL8Q\u000erH,>PF>Q\u001a 6NL?\u0007NC10AP\u0014\u0006rV\" GQ\u0005oO00A\u0006cY <LW\fvS 2GQmUS'-J\u0003\u0002\u0015c#lU4yRO,[S->\u0002S,L_/yCM)\u0002I:7AF)\u0002V:+K@>\na[1=\u0002@\"NU1\u000bqR,.\u0002S!CC&+\u0010CO16PBbCV!,O|,PN\u000fjS$1NJ*JNc:MO\"P\neV,.\u0002@\"NU1\tjS$1NJ*JN\frV\" \u0002\fmr[6*G\u000bqR,.\u0002O4PS *\u0006n[:6WW\u0006OO00Az\u000bnS-<Q\u0003>JU47\bNC10AP\tz\frH,>PF>Q\u001a\u00018P\u0006OO00A{\u0006CO16PB\u0005qN:5G\u0010`[ 2EQ\"WT'yAL!MH\baU--PL!Q\u000ewT(7MT#\u0002[1-KP9\bNC10AP\tz\u00a3y\nsc\u0012\u0013c\u0012\n\u001e\u0015[Q$AIc*JL:\u0002O3yJF?G0\u0018i\u0012\u0019}\u0011\u0014si\u007fT%KV&yC\u0003>MT$yUJ9J\u001a0 L@(F\u001a/ PJ.Q0\u0018i\u0012\u0019}\u0014\u0014si\u007fJ>\u0002J/8[J#E\u001a,7\u0002W%KIc4C@%KT&Sy\u0013}\u0018\nzw\u0012\u0013\u0010CT'yVK([\u001a%6NO\"U\u001a\"5MM*(asi\u0018\u0012\u007f\f\ns\u0004UL?F\u001a! \u0002T\"P^\tuU1=\u0002O$D_\u0004eV,.\u0011fC-8OJ.\u0002R*>JO$ER7\baU--PL!Q\nuU1=\u0002@\"NU1\rnC10APmM\\%*GW\n`[ 2EQ\"WT'\tcV*>LN(LN\u0004L_;-\u0006aU/6PP\nv_;-\u0002@\"NU1\u0006`U1=GQ\u0007RH&/KF:\tRV\" RB8Q_\nl_;-\u0002W?CY(\u0007NC10AP\u0014\u0007wT(7MT#\u000erH&/KL8Q\u001a7+C@&\u0006qJ1<CG\u0006OO00Az\f`U1=GQmAU/6P\u0007fC-8OJ.";
                                var20_3 = "uU1=\u0002P$X_\u0006nC10AP\u0007wT(7MT#\u0006OO00A{\n`[ 2EQ\"WT'\u000efC-8OJ.\u0002[ :GM9\tcV!,O\u0003\fPN\u000ewT(7MT#\u0002[1-KP9\u0007fC-8OJ.\tCV!,O|,PN\reV,.\u0002P9P_->VK\bRH&/KL8Q\u000erH,>PF>Q\u001a 6NL?\u0007NC10AP\u0014\u0006rV\" GQ\u0005oO00A\u0006cY <LW\fvS 2GQmUS'-J\u0003\u0002\u0015c#lU4yRO,[S->\u0002S,L_/yCM)\u0002I:7AF)\u0002V:+K@>\na[1=\u0002@\"NU1\u000bqR,.\u0002S!CC&+\u0010CO16PBbCV!,O|,PN\u000fjS$1NJ*JNc:MO\"P\neV,.\u0002@\"NU1\tjS$1NJ*JN\frV\" \u0002\fmr[6*G\u000bqR,.\u0002O4PS *\u0006n[:6WW\u0006OO00Az\u000bnS-<Q\u0003>JU47\bNC10AP\tz\frH,>PF>Q\u001a\u00018P\u0006OO00A{\u0006CO16PB\u0005qN:5G\u0010`[ 2EQ\"WT'yAL!MH\baU--PL!Q\u000ewT(7MT#\u0002[1-KP9\bNC10AP\tz\u00a3y\nsc\u0012\u0013c\u0012\n\u001e\u0015[Q$AIc*JL:\u0002O3yJF?G0\u0018i\u0012\u0019}\u0011\u0014si\u007fT%KV&yC\u0003>MT$yUJ9J\u001a0 L@(F\u001a/ PJ.Q0\u0018i\u0012\u0019}\u0014\u0014si\u007fJ>\u0002J/8[J#E\u001a,7\u0002W%KIc4C@%KT&Sy\u0013}\u0018\nzw\u0012\u0013\u0010CT'yVK([\u001a%6NO\"U\u001a\"5MM*(asi\u0018\u0012\u007f\f\ns\u0004UL?F\u001a! \u0002T\"P^\tuU1=\u0002O$D_\u0004eV,.\u0011fC-8OJ.\u0002R*>JO$ER7\baU--PL!Q\nuU1=\u0002@\"NU1\rnC10APmM\\%*GW\n`[ 2EQ\"WT'\tcV*>LN(LN\u0004L_;-\u0006aU/6PP\nv_;-\u0002@\"NU1\u0006`U1=GQ\u0007RH&/KF:\tRV\" RB8Q_\nl_;-\u0002W?CY(\u0007NC10AP\u0014\u0007wT(7MT#\u000erH&/KL8Q\u001a7+C@&\u0006qJ1<CG\u0006OO00Az\f`U1=GQmAU/6P\u0007fC-8OJ.".length();
                                var17_4 = 9;
                                var16_5 = -1;
lbl7:

                                while (true) {
                                    v0 = ++var16_5;
                                    v1 = var18_2.substring(v0, v0 + var17_4);
                                    v2 = -1;
                                    break block32;
                                    break;
                                }
lbl12:

                                while (true) {
                                    var21[var19_1++] = v3.intern();
                                    if ((var16_5 += var17_4) < var20_3) {
                                        var17_4 = var18_2.charAt(var16_5);
                                        continue;
                                    }
                                    var18_2 = "uS'-J\u0004\u0007\nq=";
                                    var20_3 = "uS'-J\u0004\u0007\nq=".length();
                                    var17_4 = 5;
                                    var16_5 = -1;
lbl21:

                                    while (true) {
                                        v4 = ++var16_5;
                                        v1 = var18_2.substring(v4, v4 + var17_4);
                                        v2 = 0;
                                        break block32;
                                        break;
                                    }
                                    break;
                                }
lbl26:

                                while (true) {
                                    var21[var19_1++] = v3.intern();
                                    if ((var16_5 += var17_4) < var20_3) {
                                        var17_4 = var18_2.charAt(var16_5);
                                        continue;
                                    }
                                    break block33;
                                    break;
                                }
                            }
                            v5 = v1.toCharArray();
                            v6 = v5;
                            v7 = v5.length;
                            var22_7 = 0;
                            do {
                                v6 = v6;
                                v8 = var22_7;
                                v9 = v6[v8];
                                switch (var22_7 % 7) {
                                    case 0: {
                                        v10 = 34;
                                        break;
                                    }
                                    case 1: {
                                        v10 = 58;
                                        break;
                                    }
                                    case 2: {
                                        v10 = 67;
                                        break;
                                    }
                                    case 3: {
                                        v10 = 89;
                                        break;
                                    }
                                    case 4: {
                                        v10 = 34;
                                        break;
                                    }
                                    case 5: {
                                        v10 = 35;
                                        break;
                                    }
                                    default: {
                                        v10 = 77;
                                    }
                                }
                                v6[v8] = (char)(v9 ^ v10);
                                ++var22_7;
lbl65:

                                v7 = v7;
                            } while (v7 > var22_7);
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
                        MusicPlayerModule.b = var21;
                        var8_8 = 7636026913437541153L;
                        var14_9 = new long[33];
                        var11_10 = 0;
                        var12_11 = "\u0004\u00b4j\u00167\u001c8\u00cbb\u0012\u00c7B>\u00b5\u00d3\u00e8\u00e4\u00fe\u00aa\u00c1\u00ec,\u009e\u00a6\u00f55\u00db\u00a9=\u00edT\u00c0\u009e\u00db\u00ef1-\u00d6h\u00d5\u008d8|\u0083N*\u0089\u00e8d\u00a7\u00b6e\u0013\u001d\u00e6\nm\u00d01!0\u00ac.\u00d3}\u009b!\u009a\u00cb\u001c;\u00d5c\u00b5<\u00a3a\u00c8-8EG\u009c\u00ef\u00b2\"\u008f\u0098\u00f7\u00b2\u009f.\u00ed\u00b7kTc\u00c7Ti\u0017\u0004\u00fa\u00ea\u0091\u00b3h\u0093\u0083\u00b9V\u0086s\u0097\u00cd\u008aR\u00efN\u00b0\u00f4\u00a4;\u00b3\u009c\u00f6v\u00a9Rtsb\u0005\u00d5:\t\u0098\u001et\u00c7_\u0012\u0099\u00fbN-J\u008d\u0093\u00c0\u00c4\u00df3-\u001e,\u0014\";\u0014\u0016\u0098\u00fe\u00a1t\u0087\u0002\u00f3W\u00be\u00b1v.S\u009aq\u0000\u0091 \u00a8+\u00a8\u008b\u00f6\u008e\u00fdOP\u00e5B\u0095\u00b3 \u00862\u00d9\u00ed\u00b9\u0003\u00d9\u00d9B\u00ee\u00d8\u00bc\u00bc\u008f}e7\u00af\u0084\u00d3(D\u00f2\u0007\u0081u\\\u008a\u00cb0\f8\u00eeq\u00d1\u00bec\u0016\u00d3\u0091\u00a0\u008cJ\u001f@B9]\u00b0\u00d7-\u0091\u00a6\u00cc\u009e";
                        var13_12 = "\u0004\u00b4j\u00167\u001c8\u00cbb\u0012\u00c7B>\u00b5\u00d3\u00e8\u00e4\u00fe\u00aa\u00c1\u00ec,\u009e\u00a6\u00f55\u00db\u00a9=\u00edT\u00c0\u009e\u00db\u00ef1-\u00d6h\u00d5\u008d8|\u0083N*\u0089\u00e8d\u00a7\u00b6e\u0013\u001d\u00e6\nm\u00d01!0\u00ac.\u00d3}\u009b!\u009a\u00cb\u001c;\u00d5c\u00b5<\u00a3a\u00c8-8EG\u009c\u00ef\u00b2\"\u008f\u0098\u00f7\u00b2\u009f.\u00ed\u00b7kTc\u00c7Ti\u0017\u0004\u00fa\u00ea\u0091\u00b3h\u0093\u0083\u00b9V\u0086s\u0097\u00cd\u008aR\u00efN\u00b0\u00f4\u00a4;\u00b3\u009c\u00f6v\u00a9Rtsb\u0005\u00d5:\t\u0098\u001et\u00c7_\u0012\u0099\u00fbN-J\u008d\u0093\u00c0\u00c4\u00df3-\u001e,\u0014\";\u0014\u0016\u0098\u00fe\u00a1t\u0087\u0002\u00f3W\u00be\u00b1v.S\u009aq\u0000\u0091 \u00a8+\u00a8\u008b\u00f6\u008e\u00fdOP\u00e5B\u0095\u00b3 \u00862\u00d9\u00ed\u00b9\u0003\u00d9\u00d9B\u00ee\u00d8\u00bc\u00bc\u008f}e7\u00af\u0084\u00d3(D\u00f2\u0007\u0081u\\\u008a\u00cb0\f8\u00eeq\u00d1\u00bec\u0016\u00d3\u0091\u00a0\u008cJ\u001f@B9]\u00b0\u00d7-\u0091\u00a6\u00cc\u009e".length();
                        var10_13 = 0;
                        while (true) {
                            var15_14 = var12_11.substring(var10_13, var10_13 += 8).getBytes("ISO-8859-1");
                            v11 = var14_9;
                            v12 = var11_10++;
                            v13 = ((long)var15_14[0] & 255L) << 56 | ((long)var15_14[1] & 255L) << 48 | ((long)var15_14[2] & 255L) << 40 | ((long)var15_14[3] & 255L) << 32 | ((long)var15_14[4] & 255L) << 24 | ((long)var15_14[5] & 255L) << 16 | ((long)var15_14[6] & 255L) << 8 | (long)var15_14[7] & 255L;
                            v14 = -1;
                            break block34;
                            break;
                        }
lbl89:

                        while (true) {
                            v11[v12] = v15;
                            if (var10_13 < var13_12) continue;
                            var12_11 = "bW\u00bc\u00de\u00de\u00c6_\u00b3\u00f4\u00a4c\u00ac\u00e4\u009e\u00bd<";
                            var13_12 = "bW\u00bc\u00de\u00de\u00c6_\u00b3\u00f4\u00a4c\u00ac\u00e4\u009e\u00bd<".length();
                            var10_13 = 0;
                            while (true) {
                                var15_14 = var12_11.substring(var10_13, var10_13 += 8).getBytes("ISO-8859-1");
                                v11 = var14_9;
                                v12 = var11_10++;
                                v13 = ((long)var15_14[0] & 255L) << 56 | ((long)var15_14[1] & 255L) << 48 | ((long)var15_14[2] & 255L) << 40 | ((long)var15_14[3] & 255L) << 32 | ((long)var15_14[4] & 255L) << 24 | ((long)var15_14[5] & 255L) << 16 | ((long)var15_14[6] & 255L) << 8 | (long)var15_14[7] & 255L;
                                v14 = 0;
                                break block34;
                                break;
                            }
                            break;
                        }
lbl102:

                        while (true) {
                            v11[v12] = v15;
                            if (var10_13 < var13_12) continue;
                            break block35;
                            break;
                        }
                    }
                    v15 = v13 ^ var8_8;
                    switch (v14) {
                        default: {
                            continue;
                        }
                        case 0:
lbl113:

                        continue;
                    }
                }
                MusicPlayerModule.d = var14_9;
                MusicPlayerModule.e = new Integer[33];
                MusicPlayerModule.RADIUS = MusicPlayerModule.a(21143, 3259441823965705883L);
                MusicPlayerModule.ARTIST_COL = MusicPlayerModule.a(27436, 7575527644986232630L);
                MusicPlayerModule.PANEL_H = MusicPlayerModule.a(5077, 7809885865998104525L);
                MusicPlayerModule.LYRICS_PAD_X = MusicPlayerModule.a(31276, 3544979517596163607L);
                MusicPlayerModule.LYRICS_R = MusicPlayerModule.a(32037, 5841442736469793070L);
                MusicPlayerModule.FADE_W = MusicPlayerModule.a(5077, 7809885865998104525L);
                MusicPlayerModule.PAST_A = MusicPlayerModule.a(6373, 6203481707732155617L);
                MusicPlayerModule.LYRICS_MIN_W = MusicPlayerModule.a(17739, 44798975320219972L);
                MusicPlayerModule.LYRIC_LEAD_MS = MusicPlayerModule.a(17739, 44798975320219972L);
                MusicPlayerModule.CARD_R = MusicPlayerModule.a(32037, 5841442736469793070L);
                MusicPlayerModule.BAR_BG = MusicPlayerModule.a(20500, 2868033583877846027L);
                MusicPlayerModule.TICKER_A = MusicPlayerModule.a(22822, 9150996395571346751L);
                MusicPlayerModule.CARD_H = MusicPlayerModule.a(20959, 8553412515984799192L);
                MusicPlayerModule.LYRICS_MAX_W = MusicPlayerModule.a(12440, 6963287238405919884L);
                MusicPlayerModule.CARD_GAP = MusicPlayerModule.a(18607, 9146057950537116862L);
                MusicPlayerModule.LYRICS_TEXT = MusicPlayerModule.a(27110, 2609228684382981626L);
                MusicPlayerModule.LYRICS_ROW = MusicPlayerModule.a(19669, 671099292012570840L);
                MusicPlayerModule.CARD_PAD = MusicPlayerModule.a(32037, 5841442736469793070L);
                MusicPlayerModule.CARD_ART_R = MusicPlayerModule.a(3007, 2687526112688517050L);
                MusicPlayerModule.PANEL_BG = MusicPlayerModule.a(2322, 2720968551908781332L);
                MusicPlayerModule.LYRICS_PAD_Y = MusicPlayerModule.a(18607, 9146057950537116862L);
                MusicPlayerModule.FUTURE_A = MusicPlayerModule.a(843, 7077945196715714378L);
                MusicPlayerModule.LYRICS_DARK = MusicPlayerModule.a(18526, 4297727189697752156L);
                var0_15 = 2862001635452669352L;
                var6_16 = new long[10];
                var3_17 = 0;
                var4_18 = "|\u00a9\u00e9\u00ac+\u008a^Wu=m;:\u000b\u00a54w_m\u00f5:\u00df\u00b0hy\u0005\u00eai\u001a\u00b5\u00bf\u00c2\u001e\u00d7j\u0018\u00c0\u00d2\u00ab\u000f9\u0089`\u0089X}U}1\u0081\u008d\u00cel@#\u00930\u008e\u0003\u0010\u00d1f\u00f8_";
                var5_19 = "|\u00a9\u00e9\u00ac+\u008a^Wu=m;:\u000b\u00a54w_m\u00f5:\u00df\u00b0hy\u0005\u00eai\u001a\u00b5\u00bf\u00c2\u001e\u00d7j\u0018\u00c0\u00d2\u00ab\u000f9\u0089`\u0089X}U}1\u0081\u008d\u00cel@#\u00930\u008e\u0003\u0010\u00d1f\u00f8_".length();
                var2_20 = 0;
                while (true) {
                    var7_21 = var4_18.substring(var2_20, var2_20 += 8).getBytes("ISO-8859-1");
                    v16 = var6_16;
                    v17 = var3_17++;
                    v18 = ((long)var7_21[0] & 255L) << 56 | ((long)var7_21[1] & 255L) << 48 | ((long)var7_21[2] & 255L) << 40 | ((long)var7_21[3] & 255L) << 32 | ((long)var7_21[4] & 255L) << 24 | ((long)var7_21[5] & 255L) << 16 | ((long)var7_21[6] & 255L) << 8 | (long)var7_21[7] & 255L;
                    v19 = -1;
                    break block36;
                    break;
                }
lbl153:

                while (true) {
                    v16[v17] = v20;
                    if (var2_20 < var5_19) continue;
                    var4_18 = "p\u00ea\u00d7\u00c3\u00c2*_?l\u00c5a)H\u00c1U\u00a2";
                    var5_19 = "p\u00ea\u00d7\u00c3\u00c2*_?l\u00c5a)H\u00c1U\u00a2".length();
                    var2_20 = 0;
                    while (true) {
                        var7_21 = var4_18.substring(var2_20, var2_20 += 8).getBytes("ISO-8859-1");
                        v16 = var6_16;
                        v17 = var3_17++;
                        v18 = ((long)var7_21[0] & 255L) << 56 | ((long)var7_21[1] & 255L) << 48 | ((long)var7_21[2] & 255L) << 40 | ((long)var7_21[3] & 255L) << 32 | ((long)var7_21[4] & 255L) << 24 | ((long)var7_21[5] & 255L) << 16 | ((long)var7_21[6] & 255L) << 8 | (long)var7_21[7] & 255L;
                        v19 = 0;
                        break block36;
                        break;
                    }
                    break;
                }
lbl166:

                while (true) {
                    v16[v17] = v20;
                    if (var2_20 < var5_19) continue;
                    break block37;
                    break;
                }
            }
            v20 = v18 ^ var0_15;
            switch (v19) {
                default: {
                    continue;
                }
                case 0:
lbl177:

                continue;
            }
        }
        MusicPlayerModule.f = var6_16;
        MusicPlayerModule.g = new Long[10];
        MusicPlayerModule.PREVIEW_POS = MusicPlayerModule.b(24682, 2179390220028545325L);
        MusicPlayerModule.GLOW_IN_MS = MusicPlayerModule.b(6995, 6823574916983157266L);
        MusicPlayerModule.SCROLL_NS = MusicPlayerModule.b(11489, 4134503788340659623L);
        MusicPlayerModule.GLOW_OUT_MS = MusicPlayerModule.b(23778, 1673610913558082983L);
        var16_6 = MusicPlayerModule.b;
        MusicPlayerModule.ART_ID = Identifier.method_60655((String)var16_6[34], (String)var16_6[9]);
        MusicPlayerModule.LYRICS_FACE = new MusicPlayerModule$Font(SdfText.INTER, 10.0f);
        MusicPlayerModule.TITLE_FACE = new MusicPlayerModule$Font(SdfText.SORA, 10.0f);
        MusicPlayerModule.SMALL_FACE = new MusicPlayerModule$Font(SdfText.SORA_LIGHT, 7.5f);
        MusicPlayerModule.PREVIEW = Lyrics.parse(var16_6[40]);
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x241B;
        if (e[n2] == null) {
            MusicPlayerModule.e[n2] = (int)(d[n2] ^ l);
        }
        return e[n2];
    }

    private static long b(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x6942) & Short.MAX_VALUE;
        if (g[n2] == null) {
            MusicPlayerModule.g[n2] = f[n2] ^ l;
        }
        return g[n2];
    }
}
