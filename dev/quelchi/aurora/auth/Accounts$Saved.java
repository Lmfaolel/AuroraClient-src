package dev.quelchi.aurora.auth;

import dev.quelchi.aurora.auth.MsAuth;
import java.util.UUID;

public record Accounts$Saved(UUID uuid, String name, String refreshToken, String sessionToken, String xuid, long sessionExpiry) {
    private static final long a;

    public Accounts$Saved(UUID uUID, String string, String string2) {
        this(uUID, string, string2, "", "", 0L);
    }

    public boolean sessionUsable() {
        int n;
        block13: {
            block11: {
                int n2;
                block12: {
                    Accounts$Saved accounts$Saved;
                    block10: {
                        n2 = MsAuth.a;
                        accounts$Saved = this;
                        if (n2 != 0) break block10;
                        if (accounts$Saved.sessionToken == null) break block11;
                        accounts$Saved = this;
                    }
                    n = accounts$Saved.sessionToken.isEmpty();
                    if (n2 != 0) break block12;
                    if (n != 0) break block11;
                    long l = System.currentTimeMillis() - (this.sessionExpiry - a);
                    n = l == 0L ? 0 : (l < 0L ? -1 : 1);
                }
                if (n2 != 0) break block13;
                if (n >= 0) break block11;
                n = 1;
                break block13;
            }
            n = 0;
        }
        return n != 0;
    }

    
    static {
        long l = 248734848226145002L;
        a = 0x373AF06CFC7588AL ^ l;
    }
}
