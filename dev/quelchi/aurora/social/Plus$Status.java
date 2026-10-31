package dev.quelchi.aurora.social;

import dev.quelchi.aurora.social.Social;

public record Plus$Status(boolean plus, long expires, String source, boolean renews) {
    public static final Plus$Status NONE;
    private static final String a;

    public boolean lifetime() {
        boolean bl;
        block8: {
            block7: {
                Plus$Status plus$Status;
                int n;
                block6: {
                    n = Social.a;
                    plus$Status = this;
                    if (n != 0) break block6;
                    if (!plus$Status.plus) break block7;
                    plus$Status = this;
                }
                long l = plus$Status.expires - 0L;
                bl = l == 0L ? 0 : (l < 0L ? -1 : 1);
                if (n != 0) break block8;
                if (bl) break block7;
                bl = true;
                break block8;
            }
            bl = false;
        }
        return bl;
    }

    public boolean given() {
        return a.equals(this.source);
    }

    
    static {
        char[] cArray;
        block11: {
            int n;
            int n2;
            block10: {
                char[] cArray2 = "\\Q\u0010RD$".toCharArray();
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
                    case 0 -> 49;
                    case 1 -> 48;
                    case 2 -> 126;
                    case 3 -> 39;
                    case 4 -> 37;
                    case 5 -> 72;
                    default -> 67;
                }));
                ++n;
                n2 = n2;
            } while (n2 > n);
        }
        a = new String(cArray).intern();
        NONE = new Plus$Status(false, 0L, "", false);
    }
}
