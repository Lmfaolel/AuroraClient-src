package dev.quelchi.aurora.auth;

import java.util.UUID;

public record MsAuth$Account(UUID uuid, String name, String accessToken, String refreshToken, String xuid) {
}
