package dev.quelchi.aurora.profile;

import com.google.gson.JsonObject;
import dev.quelchi.aurora.module.Module;
import java.nio.file.Path;

public record Profile(String name, long createdAt, long updatedAt, String code, String summary, Path file, JsonObject data, String from) {
    public static int a;
    private static final String b;

    public Profile {
        this.name = string;
        this.createdAt = l;
        this.updatedAt = l2;
        this.code = string2;
        this.summary = string3;
        this.file = path;
        int n = a;
        this.data = jsonObject;
        this.from = string4;
        if (Module.a) {
            a = ++n;
        }
    }

    public String describe() {
        String string;
        block8: {
            Profile profile;
            block10: {
                block11: {
                    block9: {
                        int n = a;
                        string = this.summary;
                        if (n != 0) break block8;
                        if (string == null) break block9;
                        profile = this;
                        if (n != 0) break block10;
                        if (!profile.summary.isEmpty()) break block11;
                    }
                    string = b;
                    break block8;
                }
                profile = this;
            }
            string = profile.summary;
        }
        return string;
    }

    
    static {
        char[] cArray;
        block11: {
            int n;
            int n2;
            block10: {
                char[] cArray2 = "hBs\u0014/\u000b\u0016ZRu\u000f4\u0004QZ".toCharArray();
                cArray = cArray2;
                n2 = cArray2.length;
                n = 0;
                if (!true) break block10;
                n2 = n2;
                if (n2 <= n) break block11;
            }
            do {
                cArray = cArray;
                int n3 = n;
                char c = cArray[n3];
                cArray[n3] = (char)(c ^ (switch (n % 7) {
                    case 0 -> 41;
                    case 1 -> 55;
                    case 2 -> 1;
                    case 3 -> 123;
                    case 4 -> 93;
                    case 5 -> 106;
                    default -> 54;
                }));
                ++n;
                n2 = n2;
            } while (n2 > n);
        }
        b = new String(cArray).intern();
    }
}
