package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.MusicPlayerModule;
import dev.quelchi.aurora.module.modules.MusicPlayerModule$Font;
import dev.quelchi.aurora.module.modules.MusicPlayerModule$Tok;
import dev.quelchi.aurora.music.Lyrics$Line;
import dev.quelchi.aurora.music.Lyrics$Word;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.font.TextRenderer;

final class MusicPlayerModule$Strip {
    final List<Lyrics$Line> src;
    final MusicPlayerModule.Font face;
    final List<MusicPlayerModule$Tok> toks = new ArrayList<MusicPlayerModule$Tok>();
    int widest;
    private static final long a;

    MusicPlayerModule$Strip(TextRenderer textRenderer, List<Lyrics$Line> list, MusicPlayerModule.Font font) {
        this.src = list;
        this.face = font;
        boolean bl = AmbienceModule.b;
        float f = MusicPlayerModule.width(textRenderer, " ", font);
        float f2 = 0.0f;
        for (int i = 0; i < list.size(); ++i) {
            float f3;
            block14: {
                List<Record> list2;
                block12: {
                    Lyrics$Line lyrics$Line;
                    block13: {
                        block10: {
                            lyrics$Line = list.get(i);
                            this.widest = Math.max(this.widest, (int)Math.ceil(MusicPlayerModule.lineWidth(textRenderer, lyrics$Line, font)));
                            list2 = lyrics$Line.words();
                            if (bl) break block10;
                            if (list2.isEmpty()) {
                                continue;
                            }
                            list2 = this.toks;
                        }
                        if (bl) break block12;
                        if (list2.isEmpty()) break block13;
                        f2 += f * 4.0f;
                    }
                    list2 = lyrics$Line.words();
                }
                for (Lyrics$Word lyrics$Word : list2) {
                    float f4 = MusicPlayerModule.width(textRenderer, lyrics$Word.text(), font);
                    this.toks.add(new MusicPlayerModule$Tok(lyrics$Word.text(), f2, f4, lyrics$Word, i));
                    f3 = f2 + (f4 + f);
                    if (!bl) {
                        f2 = f3;
                        if (!bl) continue;
                    }
                    break block14;
                }
                f3 = f2 - f;
            }
            f2 = f3;
            if (!bl) continue;
        }
    }

    float cursor(long l) {
        float f;
        block25: {
            MusicPlayerModule$Tok musicPlayerModule$Tok;
            block24: {
                MusicPlayerModule$Tok musicPlayerModule$Tok2;
                block17: {
                    musicPlayerModule$Tok2 = null;
                    Iterator<MusicPlayerModule$Tok> iterator = this.toks.iterator();
                    boolean bl = AmbienceModule.b;
                    while (iterator.hasNext()) {
                        block18: {
                            MusicPlayerModule$Tok musicPlayerModule$Tok3;
                            block19: {
                                Lyrics$Word lyrics$Word;
                                long l2;
                                block22: {
                                    block23: {
                                        block21: {
                                            MusicPlayerModule$Tok musicPlayerModule$Tok4;
                                            block20: {
                                                musicPlayerModule$Tok3 = iterator.next();
                                                if (bl) break block17;
                                                if (bl) break block18;
                                                if (l >= musicPlayerModule$Tok3.word.start()) break block19;
                                                musicPlayerModule$Tok4 = musicPlayerModule$Tok2;
                                                if (bl) break block20;
                                                if (musicPlayerModule$Tok4 != null) break block21;
                                                musicPlayerModule$Tok4 = musicPlayerModule$Tok3;
                                            }
                                            return musicPlayerModule$Tok4.x;
                                        }
                                        l2 = l;
                                        lyrics$Word = musicPlayerModule$Tok2.word;
                                        if (bl) break block22;
                                        if (l2 >= lyrics$Word.end()) break block23;
                                        return MusicPlayerModule$Strip.through(musicPlayerModule$Tok2, l);
                                    }
                                    l2 = 1L;
                                    lyrics$Word = musicPlayerModule$Tok3.word;
                                }
                                long l3 = Math.max(l2, lyrics$Word.start() - musicPlayerModule$Tok2.word.end());
                                float f2 = Math.min(1.0f, Math.max(0.0f, (float)(l - musicPlayerModule$Tok2.word.end()) / (float)l3));
                                return musicPlayerModule$Tok2.x + musicPlayerModule$Tok2.w + (musicPlayerModule$Tok3.x - musicPlayerModule$Tok2.x - musicPlayerModule$Tok2.w) * f2;
                            }
                            musicPlayerModule$Tok2 = musicPlayerModule$Tok3;
                        }
                        if (!bl) continue;
                    }
                    musicPlayerModule$Tok = musicPlayerModule$Tok2;
                    if (bl) break block24;
                    if (musicPlayerModule$Tok != null) break block17;
                    f = 0.0f;
                    break block25;
                }
                musicPlayerModule$Tok = musicPlayerModule$Tok2;
            }
            f = MusicPlayerModule$Strip.through(musicPlayerModule$Tok, l);
        }
        return f;
    }

    float at(long l) {
        float f;
        float f2;
        block2: {
            float f3 = 0.0f;
            boolean bl = AmbienceModule.b;
            for (int i = 0; i < 12; ++i) {
                f2 = f3;
                f = this.cursor(l - a + (long)(i * 150 / 11));
                if (!bl) {
                    f3 = f2 + f;
                    if (!bl) continue;
                }
                break block2;
            }
            f2 = f3;
            f = 12.0f;
        }
        return f2 / f;
    }

    private static float through(MusicPlayerModule$Tok musicPlayerModule$Tok, long l) {
        long l2 = Math.max(1L, musicPlayerModule$Tok.word.end() - musicPlayerModule$Tok.word.start());
        return musicPlayerModule$Tok.x + musicPlayerModule$Tok.w * Math.min(1.0f, Math.max(0.0f, (float)(l - musicPlayerModule$Tok.word.start()) / (float)l2));
    }

    
    static {
        long l = 4955442329336524500L;
        a = 0x44C5448B2109AE9FL ^ l;
    }
}
