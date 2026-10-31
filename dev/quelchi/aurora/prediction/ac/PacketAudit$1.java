package dev.quelchi.aurora.prediction.ac;

import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;

class PacketAudit$1
implements PlayerInteractEntityC2SPacket.Handler {
    final boolean[] val$attack;

    PacketAudit$1(boolean[] blArray) {
        this.val$attack = blArray;
    }

    public void method_34219(Hand hand) {
    }

    public void method_34220(Hand hand, Vec3d vec3d) {
    }

    public void method_34218() {
        this.val$attack[0] = true;
    }
}
