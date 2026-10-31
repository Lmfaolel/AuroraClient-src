package dev.quelchi.aurora.social;

import dev.quelchi.aurora.social.Social$Friend;
import java.util.List;

public record Social$Roster(List<Social$Friend> incoming, List<Social$Friend> sent, List<Social$Friend> online, List<Social$Friend> offline) {
}
