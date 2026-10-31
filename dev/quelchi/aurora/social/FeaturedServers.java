package dev.quelchi.aurora.social;

import dev.quelchi.aurora.social.FeaturedServers$Featured;
import dev.quelchi.aurora.social.Social;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.option.ServerList;

public final class FeaturedServers {
    private static final String[][] PARTNERS;

    private FeaturedServers() {
    }

    public static List<ServerInfo> fresh() {
        ArrayList<ServerInfo> arrayList;
        block4: {
            ArrayList<ServerInfo> arrayList2 = new ArrayList<ServerInfo>(PARTNERS.length);
            String[][] stringArray = PARTNERS;
            int n = stringArray.length;
            int n2 = Social.a;
            for (int i = 0; i < n; ++i) {
                String[] stringArray2 = stringArray[i];
                arrayList = arrayList2;
                if (n2 == 0) {
                    arrayList.add(new FeaturedServers$Featured(stringArray2[0], stringArray2[1]));
                    if (n2 == 0) continue;
                    break;
                }
                break block4;
            }
            arrayList = arrayList2;
        }
        return arrayList;
    }

    public static boolean is(ServerInfo serverInfo) {
        return serverInfo instanceof FeaturedServers$Featured;
    }

    public static int leading(ServerList serverList) {
        int n;
        block7: {
            int n2;
            int n3 = Social.a;
            for (n2 = 0; n2 < serverList.method_2984(); ++n2) {
                n = FeaturedServers.is(serverList.method_2982(n2)) ? 1 : 0;
                if (n3 != 0 || n3 != 0) break block7;
                if (n == 0) break;
                continue;
            }
            n = n2;
        }
        return n;
    }

    public static int indexOf(ServerList serverList, ServerInfo serverInfo) {
        int n = Social.a;
        for (int i = 0; i < serverList.method_2984(); ++i) {
            if (serverList.method_2982(i) != serverInfo) continue;
            return i;
        }
        return -1;
    }

    
    static {
        block12: {
            var0 = new String[2];
            var4_1 = 0;
            var3_2 = "Cr}\"\\3\u0018EeC\u000fcr}\"\\3\u0018eeceS3\fw";
            var5_3 = "Cr}\"\\3\u0018EeC\u000fcr}\"\\3\u0018eeceS3\fw".length();
            var2_4 = 10;
            var1_5 = -1;
lbl7:

            while (true) {
                continue;
                break;
            }
lbl9:

            while (true) {
                var0[var4_1++] = new String(v0).intern();
                if ((var1_5 += var2_4) < var5_3) {
                    var2_4 = var3_2.charAt(var1_5);
                    continue;
                }
                break block12;
                break;
            }
            v1 = ++var1_5;
            v2 = var3_2.substring(v1, v1 + var2_4).toCharArray();
            v0 = v2;
            v3 = v2.length;
            var6_6 = 0;
            do {
                v0 = v0;
                v4 = var6_6;
                v5 = v0[v4];
                switch (var6_6 % 7) {
                    case 0: {
                        v6 = 21;
                        break;
                    }
                    case 1: {
                        v6 = 19;
                        break;
                    }
                    case 2: {
                        v6 = 19;
                        break;
                    }
                    case 3: {
                        v6 = 75;
                        break;
                    }
                    case 4: {
                        v6 = 48;
                        break;
                    }
                    case 5: {
                        v6 = 95;
                        break;
                    }
                    default: {
                        v6 = 121;
                    }
                }
                v0[v4] = (char)(v5 ^ v6);
                ++var6_6;
lbl48:

                v3 = v3;
            } while (v3 > var6_6);
            while (true)
        }
        FeaturedServers.PARTNERS = new String[][]{{var0[0], var0[1]}};
    }
}
