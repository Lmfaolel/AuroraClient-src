package dev.quelchi.aurora.gui.shell;

import dev.quelchi.aurora.gui.shell.SearchIndex;
import dev.quelchi.aurora.gui.shell.SearchIndex$Doc;
import dev.quelchi.aurora.gui.shell.Switches;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.setting.EnumSetting;
import dev.quelchi.aurora.module.setting.Setting;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class SearchIndex$Entry {
    final Module module;
    final String[] name;
    final String[] category;
    final String[] description;
    final String namePhrase;
    final List<SearchIndex$Doc> docs;
    final boolean tabbed;

    SearchIndex$Entry(Module module) {
        boolean bl;
        block19: {
            int n;
            block22: {
                block23: {
                    this.docs = new ArrayList<SearchIndex$Doc>();
                    bl = SearchIndex.a;
                    this.module = module;
                    this.name = SearchIndex.words(module.getName());
                    this.namePhrase = String.join((CharSequence)" ", this.name);
                    this.category = SearchIndex.words(module.getCategory().displayName);
                    this.description = SearchIndex.words(module.getDescription());
                    ArrayList<String> arrayList = new ArrayList<String>();
                    for (Setting setting : module.getSettings()) {
                        block20: {
                            Setting setting2;
                            ArrayList<String> arrayList2;
                            block21: {
                                if (bl) break block19;
                                if (setting.getTab() == null) break block20;
                                arrayList2 = arrayList;
                                setting2 = setting;
                                if (bl) break block21;
                                if (arrayList2.contains(setting2.getTab())) break block20;
                                arrayList2 = arrayList;
                                setting2 = setting;
                            }
                            arrayList2.add(setting2.getTab());
                        }
                        if (!bl) continue;
                    }
                    SearchIndex$Entry searchIndex$Entry = this;
                    n = arrayList.size();
                    if (bl) break block22;
                    if (n <= 1) break block23;
                    n = 1;
                    break block22;
                }
                n = 0;
            }
            searchIndex$Entry.tabbed = n;
        }
        for (Setting setting : SearchIndex.topLevel(module)) {
            block24: {
                if (bl) break block24;
                if (setting == module.getToggleKey()) {
                    continue;
                }
                this.index(setting, new ArrayList<Setting>());
            }
            if (!bl) continue;
        }
    }

    private void index(Setting setting, List<Setting> list) {
        block33: {
            Iterator<Setting> iterator;
            String[] stringArray;
            Object object;
            ArrayList<String[]> arrayList;
            ArrayList<Object> arrayList2;
            ArrayList<String> arrayList22;
            String string;
            boolean bl;
            block30: {
                String string2;
                block29: {
                    Setting setting2;
                    block27: {
                        block28: {
                            ArrayList<Object> arrayList3;
                            block26: {
                                block25: {
                                    String string3;
                                    block24: {
                                        Object object2;
                                        block22: {
                                            block23: {
                                                bl = SearchIndex.a;
                                                object2 = list;
                                                if (bl) break block22;
                                                if (!object2.isEmpty()) break block23;
                                                string3 = setting.getTab();
                                                break block24;
                                            }
                                            object2 = list.get(0);
                                        }
                                        string3 = ((Setting)object2).getTab();
                                    }
                                    string = string3;
                                    arrayList22 = new ArrayList<String>(List.of(this.name));
                                    if (!this.tabbed || string == null) break block25;
                                    arrayList22.addAll(List.of(SearchIndex.words(string)));
                                }
                                for (Setting object22 : list) {
                                    arrayList3 = arrayList22;
                                    if (!bl) {
                                        arrayList3.addAll(List.of(SearchIndex.words(object22.getName())));
                                        if (!bl) continue;
                                        break;
                                    }
                                    break block26;
                                }
                                arrayList3 = new ArrayList();
                            }
                            arrayList2 = arrayList3;
                            arrayList = new ArrayList<String[]>();
                            setting2 = setting;
                            if (bl) break block27;
                            if (!(setting2 instanceof EnumSetting)) break block28;
                            object = (EnumSetting)setting;
                            for (Enum enum_ : (Enum[])((Enum)((EnumSetting)object).getValue()).getDeclaringClass().getEnumConstants()) {
                                String string4 = Switches.pretty(enum_.name());
                                arrayList2.add(string4);
                                ArrayList<String> arrayList4 = new ArrayList<String>(List.of(SearchIndex.words(string4)));
                                string2 = enum_.toString();
                                if (bl) break block29;
                                String[] stringArray2 = SearchIndex.words(string2);
                                int n = stringArray2.length;
                                int n2 = 0;
                                while (n2 < n) {
                                    block31: {
                                        block32: {
                                            stringArray = stringArray2;
                                            if (bl) break block30;
                                            String string5 = stringArray[n2];
                                            if (bl) break block31;
                                            if (arrayList4.contains(string5)) break block32;
                                            arrayList4.add(string5);
                                        }
                                        ++n2;
                                    }
                                    if (!bl) continue;
                                }
                                arrayList.add((String[])arrayList4.toArray(SearchIndex$Entry::lambda$index$0));
                                if (!bl) continue;
                            }
                        }
                        setting2 = setting;
                    }
                    string2 = setting2.getName();
                }
                stringArray = SearchIndex.words(string2);
            }
            object = stringArray;
            this.docs.add(new SearchIndex$Doc(setting, List.copyOf(list), string, (String[])object, String.join((CharSequence)" ", (CharSequence[])object), (String[])arrayList22.toArray(SearchIndex$Entry::lambda$index$1), (List<String>)arrayList2, arrayList));
            list.add(setting);
            Iterator<Setting> iterator2 = setting.getChildren().iterator();
            while (iterator2.hasNext()) {
                iterator = iterator2;
                if (!bl) {
                    Setting setting3 = (Setting)iterator.next();
                    this.index(setting3, list);
                    if (!bl) continue;
                }
                break block33;
            }
            iterator = list.remove(list.size() - 1);
        }
    }

    private static String[] lambda$index$1(int n) {
        return new String[n];
    }

    private static String[] lambda$index$0(int n) {
        return new String[n];
    }
}
