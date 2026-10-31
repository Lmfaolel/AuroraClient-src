package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.social.SocialEvents$Kind;

class SocialToast$1 {
    static final int[] $SwitchMap$dev$quelchi$aurora$social$SocialEvents$Kind = new int[SocialEvents$Kind.values().length];

    static {
        try {
            SocialToast$1.$SwitchMap$dev$quelchi$aurora$social$SocialEvents$Kind[SocialEvents$Kind.REQUEST.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
        }
        try {
            SocialToast$1.$SwitchMap$dev$quelchi$aurora$social$SocialEvents$Kind[SocialEvents$Kind.ONLINE.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
        }
    }
}
