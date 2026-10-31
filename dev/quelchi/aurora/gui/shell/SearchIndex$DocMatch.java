package dev.quelchi.aurora.gui.shell;

import dev.quelchi.aurora.gui.shell.SearchIndex$Doc;
import dev.quelchi.aurora.gui.shell.SearchIndex$Span;
import java.util.List;

record SearchIndex$DocMatch(SearchIndex$Doc doc, int score, List<List<SearchIndex$Span>> why) {
}
