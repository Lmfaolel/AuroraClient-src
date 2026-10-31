package dev.quelchi.aurora.social;

public record Badges$Badge(String id, String label, String kind, boolean hidden) {
    private static final String a;

    public boolean earned() {
        return a.equals(this.kind);
    }

    
    static {
        char[] cArray;
        block11: {
            int n;
            int n2;
            block10: {
                char[] cArray2 = "(q\u001f4g\u001c".toCharArray();
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
                    case 0 -> 77;
                    case 1 -> 16;
                    case 2 -> 109;
                    case 3 -> 90;
                    case 4 -> 2;
                    case 5 -> 120;
                    default -> 13;
                }));
                ++n;
                n2 = n2;
            } while (n2 > n);
        }
        a = new String(cArray).intern();
    }
}
