package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.social.WorldShare$Invite;
import java.util.UUID;

record SocialToast$Toast(String who, String body, UUID from, long shown, WorldShare$Invite invite) {
    private static final long[] a;
    private static final Long[] b;

    long life() {
        long l = this.invite != null ? SocialToast$Toast.a(14346, 4947466457042739649L) : SocialToast$Toast.a(25267, 7123365797704743801L);
        return l;
    }

    
    static {
        long l = 6587969702863002684L;
        long[] lArray = new long[2];
        int n = 0;
        String string = "\u001f\u00c5\u00c5\r\u00ec\u001es\u00dd9\u00b6l\u00f9\u00e3t\u00f4\u00cd";
        int n2 = "\u001f\u00c5\u00c5\r\u00ec\u001es\u00dd9\u00b6l\u00f9\u00e3t\u00f4\u00cd".length();
        int n3 = 0;
        do {
            byte[] byArray = string.substring(n3, n3 += 8).getBytes("ISO-8859-1");
            int n4 = n++;
            lArray[n4] = (((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL) ^ l;
        } while (n3 < n2);
        a = lArray;
        b = new Long[2];
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x59CB) & Short.MAX_VALUE;
        if (b[n2] == null) {
            SocialToast$Toast.b[n2] = a[n2] ^ l;
        }
        return b[n2];
    }
}
