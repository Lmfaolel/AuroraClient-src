package dev.quelchi.aurora.music;

import dev.quelchi.aurora.music.Lyrics;

public record NowPlaying(String artKey, String title, String artist, String album, long durationMs, long progressMs, boolean playing, long stampNanos) {
    private static final long a;

    public long progressNow() {
        long l;
        block10: {
            long l2;
            block11: {
                NowPlaying nowPlaying;
                boolean bl;
                block8: {
                    block9: {
                        bl = Lyrics.a;
                        nowPlaying = this;
                        if (bl) break block8;
                        if (nowPlaying.playing) break block9;
                        return this.progressMs;
                    }
                    nowPlaying = this;
                }
                l2 = nowPlaying.progressMs + (System.nanoTime() - this.stampNanos) / a;
                l = this.durationMs;
                if (bl) break block10;
                if (l <= 0L) break block11;
                l = Math.min(l2, this.durationMs);
                break block10;
            }
            l = l2;
        }
        return l;
    }

    
    static {
        long l = 3507127562876428690L;
        a = 0x30ABD1EFD73463D2L ^ l;
    }
}
