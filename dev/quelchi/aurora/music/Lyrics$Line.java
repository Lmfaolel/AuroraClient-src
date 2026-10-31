package dev.quelchi.aurora.music;

import dev.quelchi.aurora.music.Lyrics$Word;
import java.util.List;

public record Lyrics$Line(long ms, String text, List<Lyrics$Word> words) {
}
