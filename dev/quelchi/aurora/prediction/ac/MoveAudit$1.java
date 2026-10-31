package dev.quelchi.aurora.prediction.ac;

import dev.quelchi.aurora.prediction.ac.MoveAudit;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;

class MoveAudit$1
implements PlayerInteractEntityC2SPacket.Handler {
    MoveAudit$1() {
    }

    public void method_34219(Hand hand) {
    }

    public void method_34220(Hand hand, Vec3d vec3d) {
    }

    public void method_34218() {
        ++MoveAudit.ownHits;
    }
}
