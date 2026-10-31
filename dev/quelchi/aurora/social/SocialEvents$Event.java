package dev.quelchi.aurora.social;

import dev.quelchi.aurora.social.SocialEvents$Kind;
import java.util.UUID;

public record SocialEvents$Event(SocialEvents$Kind kind, UUID who, String name, String body) {
}
