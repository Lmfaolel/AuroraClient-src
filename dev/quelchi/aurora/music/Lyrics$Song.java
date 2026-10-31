package dev.quelchi.aurora.music;

import dev.quelchi.aurora.music.Lyrics$Line;
import java.util.List;

public record Lyrics$Song(List<Lyrics.Line> lines, boolean synced) {
}
