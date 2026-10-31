package dev.quelchi.aurora.social;

import dev.quelchi.aurora.social.Social;
import java.util.UUID;

public record Social$Friend(UUID uuid, String name, String state, boolean online, long playtime, int unread, boolean typing, String server, long lastAt, String status) {
    private static final String[] a;

    public boolean talked() {
        long l;
        block2: {
            block3: {
                int n = Social.a;
                long l2 = this.lastAt - 0L;
                l = l2 == 0L ? 0 : (l2 < 0L ? -1 : 1);
                if (n != 0) break block2;
                if (l <= 0) break block3;
                l = 1;
                break block2;
            }
            l = 0;
        }
        return (boolean)l;
    }

    public boolean incoming() {
        return a[0].equals(this.state);
    }

    public boolean outgoing() {
        return a[1].equals(this.state);
    }

    public boolean accepted() {
        return a[2].equals(this.state);
    }

    
    static {
        block12: {
            var5 = new String[3];
            var3_1 = 0;
            var2_2 = "h`aH3\u0010]f\bn{v@1\u0010]f\u0007g|kB0\u001d@";
            var4_3 = "h`aH3\u0010]f\bn{v@1\u0010]f\u0007g|kB0\u001d@".length();
            var1_4 = 8;
            var0_5 = -1;
lbl7:

            while (true) {
                continue;
                break;
            }
lbl9:

            while (true) {
                var5[var3_1++] = new String(v0).intern();
                if ((var0_5 += var1_4) < var4_3) {
                    var1_4 = var2_2.charAt(var0_5);
                    continue;
                }
                break block12;
                break;
            }
            v1 = ++var0_5;
            v2 = var2_2.substring(v1, v1 + var1_4).toCharArray();
            v0 = v2;
            v3 = v2.length;
            var6_6 = 0;
            do {
                v0 = v0;
                v4 = var6_6;
                v5 = v0[v4];
                switch (var6_6 % 7) {
                    case 0: {
                        v6 = 1;
                        break;
                    }
                    case 1: {
                        v6 = 14;
                        break;
                    }
                    case 2: {
                        v6 = 2;
                        break;
                    }
                    case 3: {
                        v6 = 39;
                        break;
                    }
                    case 4: {
                        v6 = 94;
                        break;
                    }
                    case 5: {
                        v6 = 121;
                        break;
                    }
                    default: {
                        v6 = 51;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        Social$Friend.a = var5;
    }
}
