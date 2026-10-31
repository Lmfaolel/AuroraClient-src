package dev.quelchi.aurora.render;

import dev.quelchi.aurora.module.modules.CustomCrystalsModule$Mode;
import dev.quelchi.aurora.module.modules.CustomCrystalsModule$Piece;

class CustomCrystalRenderer$1 {
    static final int[] $SwitchMap$dev$quelchi$aurora$module$modules$CustomCrystalsModule$Mode;
    static final int[] $SwitchMap$dev$quelchi$aurora$module$modules$CustomCrystalsModule$Piece;

    static {
        $SwitchMap$dev$quelchi$aurora$module$modules$CustomCrystalsModule$Piece = new int[CustomCrystalsModule$Piece.values().length];
        try {
            CustomCrystalRenderer$1.$SwitchMap$dev$quelchi$aurora$module$modules$CustomCrystalsModule$Piece[CustomCrystalsModule$Piece.OUTER.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
        }
        try {
            CustomCrystalRenderer$1.$SwitchMap$dev$quelchi$aurora$module$modules$CustomCrystalsModule$Piece[CustomCrystalsModule$Piece.INNER.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
        }
        try {
            CustomCrystalRenderer$1.$SwitchMap$dev$quelchi$aurora$module$modules$CustomCrystalsModule$Piece[CustomCrystalsModule$Piece.CORE.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
        }
        $SwitchMap$dev$quelchi$aurora$module$modules$CustomCrystalsModule$Mode = new int[CustomCrystalsModule$Mode.values().length];
        try {
            CustomCrystalRenderer$1.$SwitchMap$dev$quelchi$aurora$module$modules$CustomCrystalsModule$Mode[CustomCrystalsModule$Mode.WIREFRAME.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
        }
        try {
            CustomCrystalRenderer$1.$SwitchMap$dev$quelchi$aurora$module$modules$CustomCrystalsModule$Mode[CustomCrystalsModule$Mode.END_PORTAL.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
        }
        try {
            CustomCrystalRenderer$1.$SwitchMap$dev$quelchi$aurora$module$modules$CustomCrystalsModule$Mode[CustomCrystalsModule$Mode.END_GATEWAY.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
        }
    }
}
