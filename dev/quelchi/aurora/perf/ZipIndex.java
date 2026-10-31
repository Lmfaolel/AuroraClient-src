package dev.quelchi.aurora.perf;

import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.perf.Tweaks;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public final class ZipIndex {
    private static final Map<ZipFile, String[]> NAMES = Collections.synchronizedMap(new WeakHashMap());

    private ZipIndex() {
    }

    public static Enumeration<ZipEntry> matching(ZipFile zipFile, String string) {
        String[] stringArray;
        int n;
        block13: {
            block12: {
                n = Tweaks.a;
                stringArray = NAMES.get(zipFile);
                if (n != 0) break block12;
                if (stringArray != null) break block13;
                stringArray = ZipIndex.read(zipFile);
            }
            NAMES.put(zipFile, stringArray);
        }
        ArrayList<ZipEntry> arrayList = new ArrayList<ZipEntry>();
        String[] stringArray2 = stringArray;
        int n2 = stringArray2.length;
        int n3 = 0;
        while (n3 < n2) {
            block16: {
                block15: {
                    String string2;
                    block14: {
                        string2 = stringArray2[n3];
                        if (string2.startsWith(string)) break block14;
                        break block15;
                    }
                    ZipEntry zipEntry = zipFile.getEntry(string2);
                    if (n != 0) break block16;
                    if (zipEntry == null) break block15;
                    arrayList.add(zipEntry);
                }
                ++n3;
            }
            if (n == 0) continue;
        }
        Enumeration<ZipEntry> enumeration = Collections.enumeration(arrayList);
        if (Module.a) {
            Tweaks.a = ++n;
        }
        return enumeration;
    }

    private static String[] read(ZipFile zipFile) {
        ArrayList<String> arrayList = new ArrayList<String>(zipFile.size());
        int n = Tweaks.a;
        Enumeration<? extends ZipEntry> enumeration = zipFile.entries();
        while (enumeration.hasMoreElements()) {
            block5: {
                ZipEntry zipEntry = enumeration.nextElement();
                boolean bl = zipEntry.isDirectory();
                if (n != 0 || bl) break block5;
                bl = arrayList.add(zipEntry.getName());
            }
            if (n == 0) continue;
        }
        return arrayList.toArray(new String[0]);
    }
}
