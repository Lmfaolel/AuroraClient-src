package dev.quelchi.aurora.social;

import java.util.UUID;

public record Social$Msg(long id, UUID from, String body, long at) {
}
