package dev.quelchi.aurora.util;

import dev.quelchi.aurora.util.KeyUtil;

public final class Names {
    private Names() {
    }

    public static String replaceWhole(String string, String string2, String string3) {
        StringBuilder stringBuilder;
        block67: {
            int n;
            StringBuilder stringBuilder2;
            block56: {
                String string4;
                int n2;
                block54: {
                    block55: {
                        String string5;
                        block52: {
                            block53: {
                                n2 = KeyUtil.a;
                                string5 = string;
                                if (n2 != 0) break block52;
                                if (string5 == null) break block53;
                                string5 = string2;
                                if (n2 != 0) break block52;
                                if (string5 == null) break block53;
                                string5 = string2;
                                if (n2 != 0) break block52;
                                if (string5.isEmpty()) break block53;
                                string4 = string;
                                if (n2 != 0) break block54;
                                if (!string4.isEmpty()) break block55;
                            }
                            string5 = string;
                        }
                        return string5;
                    }
                    string4 = string;
                }
                int n3 = string4.indexOf(string2);
                if (n3 < 0) {
                    return string;
                }
                stringBuilder2 = null;
                n = 0;
                while (n3 >= 0) {
                    block63: {
                        int n4;
                        block64: {
                            int n5;
                            block65: {
                                StringBuilder stringBuilder3;
                                block66: {
                                    int n6;
                                    int n7;
                                    block60: {
                                        block62: {
                                            block61: {
                                                int n8;
                                                block57: {
                                                    block59: {
                                                        block58: {
                                                            if (n2 != 0) break block56;
                                                            n8 = n3;
                                                            if (n2 != 0) break block57;
                                                            if (n8 == 0) break block58;
                                                            n8 = string.charAt(n3 - 1);
                                                            if (n2 != 0) break block57;
                                                            if (Names.isNameChar((char)n8)) break block59;
                                                        }
                                                        n8 = 1;
                                                        break block57;
                                                    }
                                                    n8 = 0;
                                                }
                                                n7 = n8;
                                                n5 = n3 + string2.length();
                                                n6 = n5;
                                                if (n2 != 0) break block60;
                                                if (n6 >= string.length()) break block61;
                                                n6 = string.charAt(n5);
                                                if (n2 != 0) break block60;
                                                if (Names.isNameChar((char)n6)) break block62;
                                            }
                                            n6 = 1;
                                            break block60;
                                        }
                                        n6 = 0;
                                    }
                                    int n9 = n6;
                                    if (n2 != 0) break block63;
                                    if (n7 == 0) break block64;
                                    n4 = n9;
                                    if (n2 != 0) break block63;
                                    if (n4 == 0) break block64;
                                    stringBuilder3 = stringBuilder2;
                                    if (n2 != 0) break block65;
                                    if (stringBuilder3 != null) break block66;
                                    stringBuilder2 = new StringBuilder(string.length());
                                }
                                stringBuilder3 = stringBuilder2.append(string, n, n3).append(string3);
                            }
                            n = n5;
                        }
                        n4 = n3 = string.indexOf(string2, n3 + '\u0001');
                    }
                    if (n2 == 0) continue;
                }
                stringBuilder = stringBuilder2;
                if (n2 != 0) break block67;
                if (stringBuilder != null) break block56;
                return string;
            }
            stringBuilder = stringBuilder2.append(string, n, string.length());
        }
        return stringBuilder.toString();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static boolean isNameChar(char c) {
        int n;
        int n2;
        block32: {
            block33: {
                block30: {
                    block31: {
                        block28: {
                            block29: {
                                n2 = KeyUtil.a;
                                n = c;
                                if (n2 != 0) break block28;
                                if (n < 97) break block29;
                                n = c;
                                if (n2 != 0) return n != 0;
                                if (n <= 122) return 1 != 0;
                            }
                            n = c;
                        }
                        if (n2 != 0) break block30;
                        if (n < 65) break block31;
                        n = c;
                        if (n2 != 0) return n != 0;
                        if (n <= 90) return 1 != 0;
                    }
                    n = c;
                }
                if (n2 != 0) break block32;
                if (n < 48) break block33;
                n = c;
                if (n2 != 0) return n != 0;
                if (n <= 57) return 1 != 0;
            }
            n = c;
        }
        if (n2 != 0) return n != 0;
        if (n != 95) return 0 != 0;
        return 1 != 0;
    }
}
