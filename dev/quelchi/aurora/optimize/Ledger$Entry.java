package dev.quelchi.aurora.optimize;

public abstract class Ledger$Entry {
    private final Object key;
    private int expiresAt;

    protected Ledger$Entry(Object object) {
        this.key = object;
    }

    public final Object key() {
        return this.key;
    }

    protected abstract void revert();

    protected void release() {
    }
}
