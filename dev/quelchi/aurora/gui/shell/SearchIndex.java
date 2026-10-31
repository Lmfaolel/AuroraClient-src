package dev.quelchi.aurora.gui.shell;

import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.gui.shell.SearchIndex$Doc;
import dev.quelchi.aurora.gui.shell.SearchIndex$DocMatch;
import dev.quelchi.aurora.gui.shell.SearchIndex$Entry;
import dev.quelchi.aurora.gui.shell.SearchIndex$ModuleMatch;
import dev.quelchi.aurora.gui.shell.SearchIndex$Result;
import dev.quelchi.aurora.gui.shell.SearchIndex$Span;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.setting.Setting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public final class SearchIndex {
    private static List<SearchIndex$Entry> entries;
    private static String cachedPhrase;
    private static List<SearchIndex$ModuleMatch> cached;
    private static final Map<String, String[]> ALSO;
    public static boolean a;
    private static final String[] b;

    private SearchIndex() {
    }

    private static List<SearchIndex$Entry> entries() {
        List<SearchIndex$Entry> list;
        block4: {
            block5: {
                boolean bl = a;
                list = entries;
                if (bl) break block4;
                if (list != null) break block5;
                SearchIndex.index(Aurora.INSTANCE.getModuleManager().getModules());
            }
            list = entries;
        }
        return list;
    }

    static void index(List<Module> list) {
        block4: {
            ArrayList<SearchIndex$Entry> arrayList = new ArrayList<SearchIndex$Entry>();
            Iterator<Module> iterator = list.iterator();
            boolean bl = a;
            while (iterator.hasNext()) {
                Module module = iterator.next();
                if (!bl) {
                    arrayList.add(new SearchIndex$Entry(module));
                    if (!bl) continue;
                    break;
                }
                break block4;
            }
            entries = arrayList;
            cachedPhrase = null;
        }
    }

    static List<Setting> topLevel(Module module) {
        Set set = Collections.newSetFromMap(new IdentityHashMap());
        boolean bl = a;
        for (Setting object : module.getSettings()) {
            set.addAll(object.getChildren());
            if (!bl) continue;
        }
        ArrayList arrayList = new ArrayList();
        for (Setting setting : module.getSettings()) {
            block6: {
                boolean bl2 = set.contains(setting);
                if (bl || bl2) break block6;
                bl2 = arrayList.add(setting);
            }
            if (!bl) continue;
        }
        return arrayList;
    }

    public static List<Setting> chain(Module module, Setting setting) {
        ArrayList<Setting> arrayList = new ArrayList<Setting>();
        boolean bl = a;
        for (Setting setting2 : SearchIndex.topLevel(module)) {
            if (SearchIndex.find(setting2, setting, arrayList)) {
                return arrayList;
            }
            if (!bl) continue;
        }
        return null;
    }

    private static boolean find(Setting setting, Setting setting2, List<Setting> list) {
        block9: {
            Setting setting32;
            List<Setting> list2;
            boolean bl;
            block7: {
                block8: {
                    bl = a;
                    list2 = list;
                    if (bl) break block7;
                    list2.add(setting);
                    if (setting != setting2) break block8;
                    return true;
                }
                list2 = setting.getChildren();
            }
            for (Setting setting32 : list2) {
                block11: {
                    boolean bl2;
                    block10: {
                        if (bl) break block9;
                        Setting setting4 = setting32;
                        bl2 = SearchIndex.find(setting4, setting2, list);
                        if (bl) break block10;
                        if (!bl2) break block11;
                        bl2 = true;
                    }
                    return bl2;
                }
                if (!bl) continue;
            }
            setting32 = list.remove(list.size() - 1);
        }
        return false;
    }

    
    public static List<SearchIndex$Result> search(String string) {
        boolean bl;
        block27: {
            String string2;
            block26: {
                CharSequence[] charSequenceArray = SearchIndex.words(string);
                bl = a;
                if (charSequenceArray.length == 0) {
                    return List.of();
                }
                string2 = String.join((CharSequence)" ", charSequenceArray);
                if (bl) break block26;
                if (string2.equals(cachedPhrase)) break block27;
                cached = SearchIndex.match((String[])charSequenceArray, string2);
            }
            cachedPhrase = string2;
        }
        ArrayList<SearchIndex$Result> arrayList = new ArrayList<SearchIndex$Result>();
        for (Record record : cached) {
            block32: {
                int n;
                Object object;
                int n2;
                SearchIndex$DocMatch searchIndex$DocMatch;
                block33: {
                    SearchIndex$ModuleMatch searchIndex$ModuleMatch;
                    block31: {
                        block13: while (true) {
                            searchIndex$ModuleMatch = record;
                            if (bl) return arrayList;
                            searchIndex$DocMatch = null;
                            n2 = 0;
                            object = searchIndex$ModuleMatch.docs().iterator();
                            while (object.hasNext()) {
                                block30: {
                                    block29: {
                                        block28: {
                                            SearchIndex$DocMatch searchIndex$DocMatch2;
                                            SearchIndex$DocMatch searchIndex$DocMatch3 = searchIndex$DocMatch2 = object.next();
                                            if (!bl) {
                                                record = searchIndex$DocMatch3.doc();
                                                if (bl) continue block13;
                                                if (!SearchIndex.onScreen((SearchIndex$Doc)record)) continue;
                                                searchIndex$DocMatch3 = searchIndex$DocMatch;
                                            }
                                            if (bl) break block28;
                                            if (searchIndex$DocMatch3 != null) break block29;
                                            searchIndex$DocMatch3 = searchIndex$DocMatch = searchIndex$DocMatch2;
                                        }
                                        if (!bl) break block30;
                                    }
                                    ++n2;
                                }
                                if (!bl) continue;
                            }
                            break;
                        }
                        object = searchIndex$ModuleMatch.entry().module;
                        if (searchIndex$DocMatch == null) break block31;
                        n = searchIndex$ModuleMatch.score();
                        if (bl) break block32;
                        if (n < searchIndex$DocMatch.score()) break block33;
                    }
                    if (searchIndex$ModuleMatch.score() <= 0) break block32;
                    arrayList.add(new SearchIndex$Result((Module)object, searchIndex$ModuleMatch.score(), null, searchIndex$ModuleMatch.why().isEmpty() ? List.of() : List.of(searchIndex$ModuleMatch.why()), 0));
                    if (!bl) break block32;
                }
                n = arrayList.add(new SearchIndex$Result((Module)object, searchIndex$DocMatch.score(), searchIndex$DocMatch.doc().setting(), searchIndex$DocMatch.why(), n2)) ? 1 : 0;
            }
            if (!bl) continue;
        }
        arrayList.sort(Comparator.comparingInt(SearchIndex$Result::score).reversed().thenComparing(SearchIndex::lambda$search$0));
        return arrayList;
    }

    
    private static List<SearchIndex$ModuleMatch> match(String[] stringArray, String string) {
        ArrayList<SearchIndex$ModuleMatch> arrayList = new ArrayList<SearchIndex$ModuleMatch>();
        boolean bl = a;
        for (SearchIndex$Entry searchIndex$Entry : SearchIndex.entries()) {
            block46: {
                ArrayList<Record> arrayList2;
                ArrayList<SearchIndex$DocMatch> arrayList3;
                List<SearchIndex$Span> list;
                int n;
                block44: {
                    block45: {
                        block31: {
                            block34: {
                                int n2;
                                block43: {
                                    int n3;
                                    String[] stringArray2;
                                    block41: {
                                        block42: {
                                            int n4;
                                            block39: {
                                                block40: {
                                                    block37: {
                                                        int n5;
                                                        block38: {
                                                            int n6;
                                                            block35: {
                                                                block36: {
                                                                    String string2;
                                                                    block32: {
                                                                        block33: {
                                                                            n = 0;
                                                                            list = List.of();
                                                                            string2 = searchIndex$Entry.namePhrase;
                                                                            if (bl) break block32;
                                                                            if (!string2.equals(string)) break block33;
                                                                            n = 1000;
                                                                            if (!bl) break block34;
                                                                        }
                                                                        string2 = searchIndex$Entry.namePhrase;
                                                                    }
                                                                    n6 = string2.startsWith(string);
                                                                    if (bl) break block35;
                                                                    if (n6 == 0) break block36;
                                                                    n = 900;
                                                                    if (!bl) break block34;
                                                                }
                                                                n6 = SearchIndex.all(stringArray, searchIndex$Entry.name);
                                                            }
                                                            n4 = n5 = n6;
                                                            if (bl) break block37;
                                                            if (n4 != 2) break block38;
                                                            n = 800;
                                                            if (!bl) break block34;
                                                        }
                                                        n4 = n5;
                                                    }
                                                    if (bl) break block39;
                                                    if (n4 != 1) break block40;
                                                    n = 560;
                                                    if (!bl) break block34;
                                                }
                                                stringArray2 = stringArray;
                                                if (bl) break block41;
                                                n4 = SearchIndex.all(stringArray2, searchIndex$Entry.category);
                                            }
                                            if (n4 != 2) break block42;
                                            n = 250;
                                            if (!bl) break block34;
                                        }
                                        stringArray2 = stringArray;
                                    }
                                    n2 = n3 = SearchIndex.all(stringArray2, searchIndex$Entry.description);
                                    if (bl) break block43;
                                    if (n2 <= 0) break block34;
                                    n2 = n3;
                                }
                                if (!bl) {
                                    n2 = n2 == 2 ? 200 : 120;
                                }
                                n = n2;
                                list = SearchIndex.spans(searchIndex$Entry.module.getDescription(), stringArray);
                            }
                            arrayList3 = new ArrayList<SearchIndex$DocMatch>();
                            for (SearchIndex$Doc searchIndex$Doc : searchIndex$Entry.docs) {
                                if (!bl) {
                                    SearchIndex$DocMatch searchIndex$DocMatch = SearchIndex.score(searchIndex$Entry, searchIndex$Doc, stringArray, string);
                                    if (searchIndex$DocMatch != null) {
                                        arrayList3.add(searchIndex$DocMatch);
                                    }
                                    if (!bl) continue;
                                }
                                break block31;
                            }
                            arrayList2 = arrayList3;
                            if (bl) break block44;
                            arrayList2.sort(Comparator.comparingInt(SearchIndex$DocMatch::score).reversed());
                        }
                        if (n > 0) break block45;
                        arrayList2 = arrayList3;
                        if (bl) break block44;
                        if (arrayList2.isEmpty()) break block46;
                    }
                    arrayList2 = arrayList;
                }
                arrayList2.add((SearchIndex$DocMatch)((Object)new SearchIndex$ModuleMatch(searchIndex$Entry, n, list, arrayList3)));
            }
            if (!bl) continue;
        }
        return arrayList;
    }

    
    private static SearchIndex$DocMatch score(SearchIndex$Entry searchIndex$Entry, SearchIndex$Doc searchIndex$Doc, String[] stringArray, String string) {
        int n;
        boolean bl;
        block31: {
            SearchIndex$Doc searchIndex$Doc2;
            String[] stringArray2;
            block34: {
                block35: {
                    int n2;
                    block32: {
                        block33: {
                            SearchIndex$Doc searchIndex$Doc3;
                            block29: {
                                block30: {
                                    bl = a;
                                    searchIndex$Doc3 = searchIndex$Doc;
                                    if (bl) break block29;
                                    if (!searchIndex$Doc3.ownPhrase().equals(string)) break block30;
                                    n = 700;
                                    if (!bl) break block31;
                                }
                                searchIndex$Doc3 = searchIndex$Doc;
                            }
                            n2 = searchIndex$Doc3.ownPhrase().startsWith(string);
                            if (bl) break block32;
                            if (n2 == 0) break block33;
                            n = 650;
                            if (!bl) break block31;
                        }
                        stringArray2 = stringArray;
                        searchIndex$Doc2 = searchIndex$Doc;
                        if (bl) break block34;
                        n2 = SearchIndex.all(stringArray2, searchIndex$Doc2.own());
                    }
                    if (n2 != 2) break block35;
                    n = 600;
                    if (!bl) break block31;
                }
                stringArray2 = stringArray;
                searchIndex$Doc2 = searchIndex$Doc;
            }
            n = SearchIndex.spread(stringArray2, searchIndex$Doc2.own(), searchIndex$Doc.around());
        }
        int n3 = n;
        if (!bl) {
            if (n3 > 0) {
                return new SearchIndex$DocMatch(searchIndex$Doc, n, SearchIndex.path(searchIndex$Entry, searchIndex$Doc, null, stringArray));
            }
            n3 = 0;
        }
        int n4 = n3;
        block13: do {
            block37: {
                int n5;
                int n6;
                block36: {
                    if (n4 >= searchIndex$Doc.choices().size()) return null;
                    String[] stringArray3 = searchIndex$Doc.choiceWords().get(n4);
                    int n7 = 0;
                    n6 = 2;
                    int n8 = 1;
                    String[] stringArray4 = stringArray;
                    int n9 = stringArray4.length;
                    int n10 = 0;
                    while (n10 < n9) {
                        int n11;
                        int n12;
                        String string2 = stringArray4[n10];
                        if (bl) continue block13;
                        int n13 = n12 = SearchIndex.best(string2, stringArray3);
                        if (!bl) {
                            if (n13 > 0) {
                                ++n7;
                            }
                            n13 = n11 = Math.max(n12, Math.max(SearchIndex.best(string2, searchIndex$Doc.own()), SearchIndex.best(string2, searchIndex$Doc.around())));
                        }
                        if (!bl) {
                            if (n11 == 0) {
                                n8 = 0;
                                if (!bl) break;
                            }
                            n6 = Math.min(n6, n11);
                            ++n10;
                        }
                        if (!bl) continue;
                    }
                    n5 = n8;
                    if (bl) break block36;
                    if (n5 == 0) break block37;
                    n5 = n7;
                }
                if (n5 > 0) {
                    int n14;
                    if (n6 == 2) {
                        n14 = 380;
                        return new SearchIndex$DocMatch(searchIndex$Doc, n14, SearchIndex.path(searchIndex$Entry, searchIndex$Doc, searchIndex$Doc.choices().get(n4), stringArray));
                    }
                    n14 = 300;
                    return new SearchIndex$DocMatch(searchIndex$Doc, n14, SearchIndex.path(searchIndex$Entry, searchIndex$Doc, searchIndex$Doc.choices().get(n4), stringArray));
                }
            }
            ++n4;
        } while (!bl);
        return null;
    }

    private static int spread(String[] stringArray, String[] stringArray2, String[] stringArray3) {
        int n;
        block38: {
            block27: {
                int n2 = 0;
                int n3 = 0;
                String[] stringArray4 = stringArray;
                int n4 = stringArray4.length;
                boolean bl = a;
                for (int i = 0; i < n4; ++i) {
                    int n5;
                    int n6;
                    block34: {
                        block37: {
                            block35: {
                                int n7;
                                block36: {
                                    block32: {
                                        int n8;
                                        block33: {
                                            block30: {
                                                block31: {
                                                    block28: {
                                                        block29: {
                                                            String string = stringArray4[i];
                                                            if (bl) break block27;
                                                            n8 = SearchIndex.best(string, stringArray2);
                                                            n7 = SearchIndex.best(string, stringArray3);
                                                            n6 = n8;
                                                            if (bl) break block28;
                                                            if (n6 != 0) break block29;
                                                            n6 = n7;
                                                            if (bl) break block28;
                                                            if (n6 != 0) break block29;
                                                            return 0;
                                                        }
                                                        n6 = n8;
                                                    }
                                                    if (bl) break block30;
                                                    if (n6 <= 0) break block31;
                                                    n3 = 1;
                                                }
                                                n6 = n2;
                                            }
                                            n5 = n8;
                                            if (bl) break block32;
                                            if (n5 != 2) break block33;
                                            n5 = 10;
                                            break block34;
                                        }
                                        n5 = n8;
                                    }
                                    if (bl) break block35;
                                    if (n5 != 1) break block36;
                                    n5 = 7;
                                    break block34;
                                }
                                n5 = n7;
                            }
                            if (bl) break block34;
                            if (n5 != 2) break block37;
                            n5 = 5;
                            break block34;
                        }
                        n5 = 3;
                    }
                    n2 = n6 + n5;
                    if (!bl) continue;
                }
                n = n3;
                if (bl) break block38;
                if (n == 0) break block27;
                n = 400 + 190 * n2 / (10 * stringArray.length);
                break block38;
            }
            n = 0;
        }
        return n;
    }

    private static boolean onScreen(SearchIndex$Doc searchIndex$Doc) {
        boolean bl;
        block11: {
            SearchIndex$Doc searchIndex$Doc2;
            boolean bl2;
            block9: {
                block10: {
                    bl2 = a;
                    searchIndex$Doc2 = searchIndex$Doc;
                    if (bl2) break block9;
                    if (searchIndex$Doc2.setting().isVisible()) break block10;
                    return false;
                }
                searchIndex$Doc2 = searchIndex$Doc;
            }
            for (Setting setting : searchIndex$Doc2.above()) {
                block13: {
                    boolean bl3;
                    block12: {
                        bl = setting.isVisible();
                        if (bl2) break block11;
                        if (bl2) break block12;
                        if (bl) break block13;
                        bl3 = false;
                    }
                    return bl3;
                }
                if (!bl2) continue;
            }
            bl = true;
        }
        return bl;
    }

    
    private static List<List<SearchIndex$Span>> path(SearchIndex$Entry searchIndex$Entry, SearchIndex$Doc searchIndex$Doc, String string, String[] stringArray) {
        ArrayList<List<SearchIndex$Span>> arrayList;
        block30: {
            boolean bl;
            block26: {
                String string2;
                Object object3;
                Object object2;
                block29: {
                    SearchIndex$Doc searchIndex$Doc2;
                    ArrayList<String> arrayList2;
                    block28: {
                        block27: {
                            arrayList = new ArrayList<List<SearchIndex$Span>>();
                            arrayList2 = new ArrayList<String>();
                            bl = a;
                            if (!searchIndex$Entry.tabbed) break block27;
                            searchIndex$Doc2 = searchIndex$Doc;
                            if (bl) break block28;
                            if (searchIndex$Doc2.tab() != null) {
                                arrayList2.add(searchIndex$Doc.tab());
                            }
                        }
                        searchIndex$Doc2 = searchIndex$Doc;
                    }
                    for (Setting setting : searchIndex$Doc2.above()) {
                        arrayList2.add(setting.getName());
                        if (!bl) continue;
                    }
                    object2 = null;
                    for (Object object3 : arrayList2) {
                        if (!bl) {
                            String string3 = (String)object3;
                            if (!bl) {
                                if (!string3.equalsIgnoreCase((String)object2)) {
                                    arrayList.add(List.of(new SearchIndex$Span(string3, false)));
                                }
                                object2 = string3;
                            }
                            if (!bl) continue;
                        }
                        break block26;
                    }
                    string2 = string;
                    if (bl) break block29;
                    if (string2 != null) break block30;
                    object3 = searchIndex$Doc.setting();
                    if (bl) break block26;
                    string2 = ((Setting)object3).getName();
                }
                if (!string2.equalsIgnoreCase((String)object2)) {
                    arrayList.add(SearchIndex.spans(searchIndex$Doc.setting().getName(), stringArray));
                    if (!bl) return arrayList;
                }
                object3 = arrayList.set(arrayList.size() - 1, SearchIndex.spans(object2, stringArray));
            }
            if (!bl) return arrayList;
        }
        arrayList.add(List.of(new SearchIndex$Span(searchIndex$Doc.setting().getName(), false)));
        arrayList.add(SearchIndex.spans(string, stringArray));
        return arrayList;
    }

    
    private static List<SearchIndex$Span> spans(String string, String[] stringArray) {
        ArrayList<SearchIndex$Span> arrayList = new ArrayList<SearchIndex$Span>();
        int n = 0;
        boolean bl = a;
        int n2 = string.length();
        block6: while (true) {
            int n3 = n;
            block7: while (n3 < n2) {
                String string2;
                int n4;
                int n5;
                block14: {
                    n5 = Character.isLetterOrDigit(string.charAt(n));
                    for (n4 = n; n4 < n2; ++n4) {
                        string2 = string;
                        if (!bl) {
                            n3 = Character.isLetterOrDigit(string2.charAt(n4)) ? 1 : 0;
                            if (bl) continue block7;
                            if (n3 != n5) break;
                            if (!bl) continue;
                        }
                        break block14;
                    }
                    string2 = string.substring(n, n4);
                }
                String string3 = string2;
                boolean bl2 = false;
                if (bl) continue block6;
                if (n5 != 0) {
                    String string4 = SearchIndex.norm(string3.toLowerCase(Locale.ROOT));
                    String[] stringArray2 = stringArray;
                    int n6 = stringArray2.length;
                    int n7 = 0;
                    while (n7 < n6) {
                        String string5 = stringArray2[n7];
                        if (bl) continue block6;
                        if (!bl) {
                            if (SearchIndex.fit(string5, string4) > 0) {
                                bl2 = true;
                                if (!bl) break;
                            }
                            ++n7;
                        }
                        if (!bl) continue;
                    }
                }
                arrayList.add(new SearchIndex$Span(string3, bl2));
                n = n4;
                if (!bl) continue block6;
            }
            break;
        }
        return arrayList;
    }

    static String[] words(String string) {
        ArrayList<String> arrayList;
        block17: {
            arrayList = new ArrayList<String>();
            StringBuilder stringBuilder = new StringBuilder();
            boolean bl = a;
            for (int i = 0; i < string.length(); ++i) {
                StringBuilder stringBuilder2;
                block20: {
                    int n;
                    block18: {
                        block19: {
                            int n2 = Character.toLowerCase(string.charAt(i));
                            if (bl) break block17;
                            n = n2;
                            if (bl) break block18;
                            if (!Character.isLetterOrDigit((char)n)) break block19;
                            stringBuilder.append((char)n2);
                            if (!bl) continue;
                        }
                        stringBuilder2 = stringBuilder;
                        if (bl) break block20;
                        n = stringBuilder2.length();
                    }
                    if (n <= 0) continue;
                    arrayList.add(SearchIndex.norm(stringBuilder.toString()));
                    stringBuilder2 = stringBuilder;
                }
                stringBuilder2.setLength(0);
                if (!bl) continue;
            }
            int n = stringBuilder.length();
            if (bl || n <= 0) break block17;
            n = arrayList.add(SearchIndex.norm(stringBuilder.toString())) ? 1 : 0;
        }
        return (String[])arrayList.toArray(SearchIndex::lambda$words$1);
    }

    private static String norm(String string) {
        String string2;
        block11: {
            String string3;
            block9: {
                boolean bl;
                block10: {
                    bl = a;
                    string2 = string;
                    string3 = b[12];
                    if (bl) break block9;
                    if (!string2.startsWith(string3)) break block10;
                    return b[2] + string.substring(6);
                }
                string2 = string;
                if (bl) break block11;
                string3 = b[1];
            }
            if (string2.equals(string3)) {
                return b[13];
            }
            string2 = string;
        }
        return string2;
    }

    private static int all(String[] stringArray, String[] stringArray2) {
        int n;
        block5: {
            int n2 = 2;
            String[] stringArray3 = stringArray;
            boolean bl = a;
            int n3 = stringArray3.length;
            int n4 = 0;
            while (n4 < n3) {
                block6: {
                    int n5;
                    block7: {
                        String string = stringArray3[n4];
                        n = SearchIndex.best(string, stringArray2);
                        if (bl) break block5;
                        n5 = n;
                        if (bl) break block6;
                        if (n5 != 0) break block7;
                        return 0;
                    }
                    n2 = Math.min(n2, n5);
                    ++n4;
                }
                if (!bl) continue;
            }
            n = n2;
        }
        return n;
    }

    private static int best(String string, String[] stringArray) {
        int n;
        block7: {
            int n2 = 0;
            String[] stringArray2 = stringArray;
            boolean bl = a;
            int n3 = stringArray2.length;
            int n4 = 0;
            while (n4 < n3) {
                block6: {
                    String string2 = stringArray2[n4];
                    if (bl) break block6;
                    n = n2;
                    if (bl) break block7;
                    n2 = Math.max(n, SearchIndex.fit(string, string2));
                    if (n2 == 2) {
                        break;
                    }
                    ++n4;
                }
                if (!bl) continue;
            }
            n = n2;
        }
        return n;
    }

    private static int fit(String string, String stringArray) {
        int n;
        block27: {
            block24: {
                String string2;
                boolean bl;
                block26: {
                    block22: {
                        String[] stringArray2;
                        block21: {
                            String[] stringArray3;
                            block19: {
                                block20: {
                                    bl = a;
                                    stringArray3 = stringArray;
                                    if (bl) break block19;
                                    if (!stringArray3.startsWith(string)) break block20;
                                    return 2;
                                }
                                stringArray3 = ALSO.get(string);
                            }
                            String[] stringArray4 = stringArray3;
                            stringArray2 = stringArray4;
                            if (bl) break block21;
                            if (stringArray2 == null) break block22;
                            stringArray2 = stringArray4;
                        }
                        String[] stringArray5 = stringArray2;
                        int n2 = stringArray5.length;
                        int n3 = 0;
                        while (n3 < n2) {
                            block23: {
                                block25: {
                                    String string3 = stringArray5[n3];
                                    if (bl) break block23;
                                    if (bl) break block24;
                                    if (!stringArray.equals(string3)) break block25;
                                    return 1;
                                }
                                ++n3;
                            }
                            if (!bl) continue;
                        }
                    }
                    string2 = string;
                    if (bl) break block26;
                    if (string2.length() < 5) break block24;
                    string2 = string;
                }
                n = SearchIndex.slip(string2, (String)stringArray);
                if (bl) break block27;
                if (n == 0) break block24;
                n = 1;
                break block27;
            }
            n = 0;
        }
        return n;
    }

    private static boolean slip(String string, String string2) {
        int n;
        block27: {
            boolean bl;
            block25: {
                String string3;
                block26: {
                    boolean bl2;
                    block23: {
                        block24: {
                            bl = a;
                            bl2 = string2.isEmpty();
                            if (bl) break block23;
                            if (bl2) break block24;
                            string3 = string2;
                            if (bl) break block25;
                            if (string3.charAt(0) == string.charAt(0)) break block26;
                        }
                        bl2 = false;
                    }
                    return bl2;
                }
                string3 = string;
            }
            for (int i = (v720291).length() - 1; i <= string.length() + 1; ++i) {
                int n2;
                block30: {
                    block29: {
                        block28: {
                            n = i;
                            if (bl) break block27;
                            if (bl) break block28;
                            if (n < 1) continue;
                            n2 = i;
                        }
                        if (bl) break block29;
                        if (n2 > string2.length()) continue;
                        n2 = SearchIndex.distance(string, string2, i);
                    }
                    if (bl) break block30;
                    if (n2 > 1) continue;
                    n2 = 1;
                }
                return n2 != 0;
            }
            n = 0;
        }
        return n != 0;
    }

    
    private static int distance(String var0, String var1_1, int var2_2) {
        block35: {
            block34: {
                var4_3 = var0.length();
                var3_4 = SearchIndex.a;
                var5_5 = new int[var4_3 + 1][var2_2 + 1];
                var6_6 = 0;
                while (var6_6 <= var4_3) {
                    v0 = var5_5[var6_6];
                    v1 = 0;
                    v2 = var6_6++;
                    if (!var3_4) {
                        v0[v1] = v2;
                        if (!var3_4) continue;
                        break;
                    }
                }
                for (var6_6 = 0; var6_6 <= var2_2; ++var6_6) {
                    v0 = var5_5[0];
                    v1 = var6_6;
                    v2 = var6_6;
lbl22:

                    v0[v1] = v2;
                    if (!var3_4) {
                        if (!var3_4) continue;
                        break;
                    }
                    break block34;
                }
                var6_6 = 1;
            }
            block30: do {
                if (var6_6 > var4_3) break;
                v5 = 1;
                if (var3_4) break block35;
                var7_7 = v5;
                while (var7_7 <= var2_2) {
                    block38: {
                        block39: {
                            block42: {
                                block41: {
                                    block40: {
                                        block36: {
                                            block37: {
                                                if (var3_4) continue block30;
                                                v7 = var0.charAt(var6_6 - 1);
                                                if (var3_4) break block36;
                                                if (v7 != var1_1.charAt(var7_7 - 1)) break block37;
                                                v7 = '\u0000';
                                                break block36;
                                            }
                                            v7 = '\u0001';
                                        }
                                        var8_8 = v7;
                                        var9_9 = Math.min(Math.min(var5_5[var6_6 - 1][var7_7] + 1, var5_5[var6_6][var7_7 - 1] + 1), var5_5[var6_6 - 1][var7_7 - 1] + var8_8);
                                        if (var3_4) break block38;
                                        if (var6_6 <= 1) break block39;
                                        v12 = var7_7;
                                        if (var3_4) break block40;
                                        if (v12 <= 1) break block39;
                                        v15 = var0;
                                        v16 = var6_6 - 1;
                                        if (var3_4) break block41;
                                        v12 = v15.charAt(v16);
                                    }
                                    if (v12 != var1_1.charAt(var7_7 - 2)) break block39;
                                    v15 = var0;
                                    v16 = var6_6 - 2;
                                }
                                v20 = v15.charAt(v16);
                                if (var3_4) break block42;
                                if (v20 != var1_1.charAt(var7_7 - 1)) break block39;
                                v20 = Math.min(var9_9, var5_5[var6_6 - 2][var7_7 - 2] + 1);
                            }
                            var9_9 = v20;
                        }
                        var5_5[var6_6][var7_7] = var9_9;
                        ++var7_7;
                    }
                    if (!var3_4) continue;
                }
                ++var6_6;
            } while (!var3_4);
            v5 = var5_5[var4_3][var2_2];
        }
        return v5;
    }

    private static String[] lambda$words$1(int n) {
        return new String[n];
    }

    private static String lambda$search$0(SearchIndex$Result searchIndex$Result) {
        return searchIndex$Result.module().getName().toLowerCase(Locale.ROOT);
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[21];
                var3_1 = 0;
                var2_2 = "[@{Y#\u0004OQ\u007fL\u0005KLvZ4\u0006@Ln^#{\u0004[J`P\u0006@Ln^#{\u0004[J`P\u0005IOj]'\u0003CFc\f\\Q{[5rjZFtV?\u0004JJtQ\u0005IOj]'\u0006KLvZ3p\u0004OQ{L\u0005[@{Y#\u0007CFcW/lo\u0007GS{V/vr\u0007GS{V/vr\u0003CFc";
                var4_3 = "[@{Y#\u0004OQ\u007fL\u0005KLvZ4\u0006@Ln^#{\u0004[J`P\u0006@Ln^#{\u0004[J`P\u0005IOj]'\u0003CFc\f\\Q{[5rjZFtV?\u0004JJtQ\u0005IOj]'\u0006KLvZ3p\u0004OQ{L\u0005[@{Y#\u0007CFcW/lo\u0007GS{V/vr\u0007GS{V/vr\u0003CFc".length();
                var1_4 = 5;
                var0_5 = -1;
lbl7:

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block18;
                    break;
                }
lbl12:

                while (true) {
                    var5[var3_1++] = v3.intern();
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        continue;
                    }
                    var2_2 = "\\Q{[5rjZFtV?\u0004JJtQ";
                    var4_3 = "\\Q{[5rjZFtV?\u0004JJtQ".length();
                    var1_4 = 12;
                    var0_5 = -1;
lbl21:

                    while (true) {
                        v4 = ++var0_5;
                        v1 = var2_2.substring(v4, v4 + var1_4);
                        v2 = 0;
                        break block18;
                        break;
                    }
                    break;
                }
lbl26:

                while (true) {
                    var5[var3_1++] = v3.intern();
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        continue;
                    }
                    break block19;
                    break;
                }
            }
            v5 = v1.toCharArray();
            v6 = v5;
            v7 = v5.length;
            var6_7 = 0;
            do {
                v6 = v6;
                v8 = var6_7;
                v9 = v6[v8];
                switch (var6_7 % 7) {
                    case 0: {
                        v10 = 40;
                        break;
                    }
                    case 1: {
                        v10 = 35;
                        break;
                    }
                    case 2: {
                        v10 = 26;
                        break;
                    }
                    case 3: {
                        v10 = 53;
                        break;
                    }
                    case 4: {
                        v10 = 70;
                        break;
                    }
                    case 5: {
                        v10 = 2;
                        break;
                    }
                    default: {
                        v10 = 11;
                    }
                }
                v6[v8] = (char)(v9 ^ v10);
                ++var6_7;
lbl65:

                v7 = v7;
            } while (v7 > var6_7);
            v3 = new String(v6);
            switch (v2) {
                default: {
                    continue;
                }
                case 0:
lbl73:

                continue;
            }
        }
        SearchIndex.b = var5;
        SearchIndex.cached = List.of();
        var0_6 = SearchIndex.b;
        SearchIndex.ALSO = Map.of(var0_6[4], new String[]{var0_6[0]}, var0_6[14], new String[]{var0_6[6]}, var0_6[16], new String[]{var0_6[19], var0_6[11]}, var0_6[9], new String[]{var0_6[17], var0_6[7]}, var0_6[7], new String[]{var0_6[17], var0_6[9]}, var0_6[20], new String[]{var0_6[18], var0_6[5]}, var0_6[15], new String[]{var0_6[8], var0_6[10], var0_6[3]}, var0_6[3], new String[]{var0_6[8], var0_6[10]});
    }
}
