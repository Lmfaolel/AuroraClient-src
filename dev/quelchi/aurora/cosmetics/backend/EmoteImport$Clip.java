package dev.quelchi.aurora.cosmetics.backend;

import dev.quelchi.aurora.cosmetics.backend.EmoteImport$Key;
import java.util.List;
import java.util.Map;

public record EmoteImport$Clip(Map<Integer, List<EmoteImport.Key>> channels, float length, float loopStart, boolean loop) {
}
