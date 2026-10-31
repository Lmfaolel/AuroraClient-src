package dev.quelchi.aurora.social;

final class Arq$Seg {
    final int seq;
    final byte[] data;
    long sentAt;
    int tries;

    Arq$Seg(int n, byte[] byArray) {
        this.seq = n;
        this.data = byArray;
    }
}
