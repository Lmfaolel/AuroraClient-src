package dev.quelchi.aurora.gui.shell;

import dev.quelchi.aurora.gui.shell.SearchIndex$DocMatch;
import dev.quelchi.aurora.gui.shell.SearchIndex$Entry;
import dev.quelchi.aurora.gui.shell.SearchIndex$Span;
import java.util.List;

record SearchIndex$ModuleMatch(SearchIndex$Entry entry, int score, List<SearchIndex$Span> why, List<SearchIndex$DocMatch> docs) {
}
