package dev.quelchi.aurora.social;

import dev.quelchi.aurora.social.Arq;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

final class WorldShare$Peer {
    final byte[] sid;
    final InetAddress ip;
    final InetSocketAddress told;
    final long since = System.currentTimeMillis();
    volatile InetSocketAddress direct;
    volatile Socket local;
    Arq arq;

    WorldShare$Peer(byte[] byArray, InetSocketAddress inetSocketAddress) {
        this.sid = byArray;
        this.told = inetSocketAddress;
        this.ip = inetSocketAddress.getAddress();
    }
}
