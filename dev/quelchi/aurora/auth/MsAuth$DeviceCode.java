package dev.quelchi.aurora.auth;

import dev.quelchi.aurora.auth.MsAuth;

public record MsAuth$DeviceCode(String deviceCode, String userCode, String verificationUri, int interval, long expiresAt) {
    private static final String[] a;

    public boolean expired() {
        long l;
        block2: {
            block3: {
                int n = MsAuth.a;
                long l2 = System.currentTimeMillis() - this.expiresAt;
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

    public String signInUrl() {
        String string;
        String string2;
        block16: {
            block17: {
                String string3;
                int n;
                block12: {
                    MsAuth$DeviceCode msAuth$DeviceCode;
                    block14: {
                        block15: {
                            block13: {
                                n = MsAuth.a;
                                string3 = this.verificationUri;
                                if (n != 0) break block12;
                                if (string3 == null) break block13;
                                msAuth$DeviceCode = this;
                                if (n != 0) break block14;
                                if (!msAuth$DeviceCode.verificationUri.isBlank()) break block15;
                            }
                            string3 = a[0];
                            break block12;
                        }
                        msAuth$DeviceCode = this;
                    }
                    string3 = msAuth$DeviceCode.verificationUri;
                }
                String string4 = string3;
                string2 = string4;
                string = string4;
                if (n != 0) break block16;
                if (!string.contains("?")) break block17;
                string = "&";
                break block16;
            }
            string = "?";
        }
        return string2 + string + a[1] + this.userCode;
    }

    
    static {
        block12: {
            var5 = new String[2];
            var3_1 = 0;
            var2_2 = "]4/>vkS\u001a7,9+<\u0015V24=j7\b\u001b#4#*=\u0015[+\u0004Z48s";
            var4_3 = "]4/>vkS\u001a7,9+<\u0015V24=j7\b\u001b#4#*=\u0015[+\u0004Z48s".length();
            var1_4 = 30;
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
                        v6 = 53;
                        break;
                    }
                    case 1: {
                        v6 = 64;
                        break;
                    }
                    case 2: {
                        v6 = 91;
                        break;
                    }
                    case 3: {
                        v6 = 78;
                        break;
                    }
                    case 4: {
                        v6 = 5;
                        break;
                    }
                    case 5: {
                        v6 = 81;
                        break;
                    }
                    default: {
                        v6 = 124;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        MsAuth$DeviceCode.a = var5;
    }
}
