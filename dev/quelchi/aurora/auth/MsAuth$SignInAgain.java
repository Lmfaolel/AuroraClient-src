package dev.quelchi.aurora.auth;

import dev.quelchi.aurora.auth.MsAuth$AuthException;

public class MsAuth$SignInAgain
extends MsAuth$AuthException {
    private static final String a;

    public MsAuth$SignInAgain() {
        super(a);
    }

    
    static {
        char[] cArray;
        block11: {
            int n;
            int n2;
            block10: {
                char[] cArray2 = "rv\u0019S^rH\u0001~\u0019\\\u0017u\u0006Up^H\r~\u0006Uw\u0017N^zEBp\u000bS\n".toCharArray();
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
                    case 0 -> 33;
                    case 1 -> 31;
                    case 2 -> 126;
                    case 3 -> 61;
                    case 4 -> 126;
                    case 5 -> 27;
                    default -> 38;
                }));
                ++n;
                n2 = n2;
            } while (n2 > n);
        }
        a = new String(cArray).intern();
    }
}
