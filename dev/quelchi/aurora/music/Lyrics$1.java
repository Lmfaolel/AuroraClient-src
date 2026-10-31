package dev.quelchi.aurora.music;

import dev.quelchi.aurora.music.Lyrics;
import dev.quelchi.aurora.music.Lyrics$Song;
import java.util.LinkedHashMap;
import java.util.Map;

class Lyrics$1
extends LinkedHashMap<String, Lyrics$Song> {
    Lyrics$1(int n, float f, boolean bl) {
        super(n, f, bl);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<String, Lyrics$Song> entry) {
        boolean bl;
        block4: {
            block5: {
                boolean bl2 = Lyrics.a;
                bl = this.size();
                if (bl2) break block4;
                if (bl <= 64 != 0) break block5;
                bl = true;
                break block4;
            }
            bl = false;
        }
        return bl;
    }
}
