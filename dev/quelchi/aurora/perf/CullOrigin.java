package dev.quelchi.aurora.perf;

import dev.quelchi.aurora.perf.Tweaks;

public final class CullOrigin {
    private static volatile Thread owner;
    private static Object value;

    private CullOrigin() {
    }

    public static Object get(ThreadLocal<Object> threadLocal) {
        Object object;
        block10: {
            block11: {
                int n;
                block8: {
                    Thread thread;
                    block9: {
                        thread = Thread.currentThread();
                        n = Tweaks.a;
                        object = owner;
                        if (n != 0) break block8;
                        if (object != null) break block9;
                        CullOrigin.claim(thread);
                    }
                    object = thread;
                }
                if (n != 0) break block10;
                if (object != owner) break block11;
                return value;
            }
            object = threadLocal.get();
        }
        return object;
    }

    
    public static void set(ThreadLocal<Object> threadLocal, Object object) {
        block11: {
            Object object2;
            int n;
            block10: {
                Thread thread = Thread.currentThread();
                n = Tweaks.a;
                object2 = owner;
                if (n == 0) {
                    if (object2 == null) {
                        CullOrigin.claim(thread);
                    }
                    object2 = thread;
                }
                if (n != 0) break block10;
                if (object2 != owner) break block11;
                object2 = object;
            }
            value = object2;
            if (n == 0) return;
        }
        threadLocal.set(object);
    }

    public static void remove(ThreadLocal<Object> threadLocal) {
        block11: {
            block10: {
                Thread thread;
                int n;
                block8: {
                    Thread thread2;
                    block9: {
                        thread2 = Thread.currentThread();
                        n = Tweaks.a;
                        thread = owner;
                        if (n != 0) break block8;
                        if (thread != null) break block9;
                        CullOrigin.claim(thread2);
                    }
                    thread = thread2;
                }
                if (thread != owner) break block10;
                value = null;
                if (n == 0) break block11;
            }
            threadLocal.remove();
        }
    }

    private static synchronized void claim(Thread thread) {
        block3: {
            Thread thread2;
            block2: {
                int n = Tweaks.a;
                thread2 = owner;
                if (n != 0) break block2;
                if (thread2 != null) break block3;
                thread2 = thread;
            }
            owner = thread2;
        }
    }
}
