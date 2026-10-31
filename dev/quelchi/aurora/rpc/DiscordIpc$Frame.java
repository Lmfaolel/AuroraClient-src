package dev.quelchi.aurora.rpc;

import com.google.gson.JsonObject;

record DiscordIpc$Frame(int opcode, JsonObject json) {
}
