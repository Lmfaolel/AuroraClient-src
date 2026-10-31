package dev.quelchi.aurora.prediction;

import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;

record AnchorTickHold$Held(Hand hand, BlockHitResult hit, int slot, boolean vanilla) {
}
