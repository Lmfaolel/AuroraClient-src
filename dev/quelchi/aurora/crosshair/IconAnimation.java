package dev.quelchi.aurora.crosshair;

import dev.quelchi.aurora.crosshair.IconStore;
import dev.quelchi.aurora.gui.IconTex;
import dev.quelchi.aurora.module.Module;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public final class IconAnimation {
    public static final int SIZE;
    public static final int MIN_SPEED = 1;
    public static final int MAX_SPEED;
    private final List<int[][]> frames;
    private int speed;
    private float size;
    private int offsetX;
    private int offsetY;
    private int current;
    private int tickCounter;
    private boolean playedOnce;
    private final int dim;
    private static final long[] a;
    private static final Integer[] b;
    private static final long c;

    public IconAnimation(int n, int n2, int[][] ... nArray) {
        block26: {
            boolean bl;
            block28: {
                block27: {
                    boolean bl2;
                    block25: {
                        IconAnimation iconAnimation;
                        block24: {
                            block23: {
                                int n3;
                                block29: {
                                    block21: {
                                        int[][][] nArray2;
                                        block22: {
                                            block20: {
                                                this.frames = new ArrayList<int[][]>();
                                                this.speed = IconAnimation.a(18925, 7992593042239263052L);
                                                this.size = 1.0f;
                                                this.offsetX = n;
                                                this.offsetY = n2;
                                                bl2 = IconStore.a;
                                                IconAnimation iconAnimation2 = this;
                                                nArray2 = nArray;
                                                if (bl2) break block20;
                                                if (nArray2.length <= 0) break block21;
                                                nArray2 = nArray;
                                            }
                                            if (bl2) break block22;
                                            if (nArray2[0] == null) break block21;
                                            nArray2 = nArray;
                                        }
                                        n3 = nArray2[0].length;
                                        break block29;
                                    }
                                    n3 = 15;
                                }
                                iconAnimation2.dim = n3;
                                for (int[][] nArray3 : nArray) {
                                    iconAnimation = this;
                                    if (!bl2) {
                                        iconAnimation.frames.add(IconAnimation.copy(nArray3));
                                        if (!bl2) continue;
                                        break;
                                    }
                                    break block23;
                                }
                                iconAnimation = this;
                            }
                            if (bl2) break block24;
                            if (!iconAnimation.frames.isEmpty()) break block25;
                            iconAnimation = this;
                        }
                        iconAnimation.frames.add(this.blank());
                    }
                    if (!Module.a) break block26;
                    if (!bl2) break block27;
                    bl = false;
                    break block28;
                }
                bl = true;
            }
            IconStore.a = bl;
        }
    }

    public int dim() {
        return this.dim;
    }

    public int frameCount() {
        return this.frames.size();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public int[][] frame(int n) {
        int n2;
        block4: {
            boolean bl = IconStore.a;
            n2 = n;
            if (bl) break block4;
            if (n2 < 0) return null;
            n2 = n;
        }
        if (n2 < this.frames.size()) return this.frames.get(n);
        return null;
    }

    public int[][] currentFrame() {
        return this.frames.get(Math.min(this.current, this.frames.size() - 1));
    }

    public void addFrame() {
        this.frames.add(this.blank());
    }

    public void duplicateFrame(int n) {
        int[][] nArray = this.frame(n);
        if (nArray != null) {
            this.frames.add(Math.min(n + 1, this.frames.size()), IconAnimation.copy(nArray));
        }
    }

    public boolean removeFrame(int n) {
        int n2;
        block19: {
            block20: {
                boolean bl;
                block18: {
                    int n3;
                    block16: {
                        block17: {
                            bl = IconStore.a;
                            n3 = this.frames.size();
                            if (bl) break block16;
                            if (n3 <= 1) break block17;
                            n3 = n;
                            if (bl) break block16;
                            if (n3 < 0) break block17;
                            n3 = n;
                            if (bl) break block16;
                            if (n3 < this.frames.size()) break block18;
                        }
                        n3 = 0;
                    }
                    return n3 != 0;
                }
                int[][] nArray = this.frames.remove(n);
                IconTex.invalidate(nArray);
                n2 = this.current;
                if (bl) break block19;
                if (n2 < this.frames.size()) break block20;
                this.current = this.frames.size() - 1;
            }
            n2 = 1;
        }
        return n2 != 0;
    }

    public int frameDelay() {
        return Math.max(1, 21 - this.speed);
    }

    public void tick() {
        block18: {
            int n;
            block17: {
                int n2;
                boolean bl;
                block15: {
                    IconAnimation iconAnimation;
                    block16: {
                        IconAnimation iconAnimation2;
                        block13: {
                            block14: {
                                bl = IconStore.a;
                                iconAnimation2 = this;
                                if (bl) break block13;
                                if (!iconAnimation2.frames.isEmpty()) break block14;
                                this.playedOnce = true;
                                return;
                            }
                            iconAnimation2 = this;
                        }
                        iconAnimation = iconAnimation2;
                        n2 = iconAnimation2.tickCounter;
                        if (bl) break block15;
                        iconAnimation.tickCounter = n2 + 1;
                        if (iconAnimation.tickCounter >= this.frameDelay()) break block16;
                        return;
                    }
                    iconAnimation = this;
                    n2 = 0;
                }
                iconAnimation.tickCounter = n2;
                int n3 = this.current;
                n = this.current = (this.current + 1) % this.frames.size();
                if (bl) break block17;
                if (n != 0) break block18;
                n = n3;
            }
            if (n == this.frames.size() - 1) {
                this.playedOnce = true;
            }
        }
    }

    public void reset() {
        this.current = 0;
        this.tickCounter = 0;
        this.playedOnce = false;
    }

    public boolean hasPlayedOnce() {
        return this.playedOnce;
    }

    public int[][] previewFrame(long l) {
        IconAnimation iconAnimation;
        block4: {
            block5: {
                boolean bl = IconStore.a;
                iconAnimation = this;
                if (bl) break block4;
                if (iconAnimation.frames.size() >= 2) break block5;
                return this.frames.get(0);
            }
            iconAnimation = this;
        }
        long l2 = (long)iconAnimation.frameDelay() * c;
        return this.frames.get((int)(l / l2 % (long)this.frames.size()));
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int n) {
        this.speed = Math.clamp((long)n, 1, 100);
    }

    public float getSize() {
        return this.size;
    }

    public void setSize(float f) {
        this.size = Math.clamp(f, 0.5f, 8.0f);
    }

    public int getOffsetX() {
        return this.offsetX;
    }

    public int getOffsetY() {
        return this.offsetY;
    }

    public void setOffset(int n, int n2) {
        this.offsetX = n;
        this.offsetY = n2;
    }

    public IconAnimation copyOf() {
        IconAnimation iconAnimation;
        block4: {
            iconAnimation = new IconAnimation(this.offsetX, this.offsetY, new int[][][]{this.blank()});
            boolean bl = IconStore.a;
            iconAnimation.frames.clear();
            for (int[][] nArray : this.frames) {
                if (!bl) {
                    iconAnimation.frames.add(IconAnimation.copy(nArray));
                    if (!bl) continue;
                    break;
                }
                break block4;
            }
            iconAnimation.speed = this.speed;
            iconAnimation.size = this.size;
        }
        return iconAnimation;
    }

    public void copyFrom(IconAnimation iconAnimation) {
        block9: {
            IconAnimation iconAnimation2;
            int[][] nArray;
            boolean bl;
            Iterator<int[][]> iterator;
            block8: {
                Iterator<int[][]> iterator2;
                iterator = this.frames.iterator();
                bl = IconStore.a;
                while (iterator.hasNext()) {
                    iterator2 = iterator;
                    if (!bl) {
                        nArray = iterator2.next();
                        IconTex.invalidate(nArray);
                        if (!bl) continue;
                        boolean bl2 = !Module.a;
                        Module.a = bl2;
                        break;
                    }
                    break block8;
                }
                this.frames.clear();
                iterator2 = iterator = iconAnimation.frames.iterator();
            }
            while (iterator.hasNext()) {
                nArray = iterator.next();
                iconAnimation2 = this;
                if (!bl) {
                    iconAnimation2.frames.add(IconAnimation.copy(nArray));
                    if (!bl) continue;
                    break;
                }
                break block9;
            }
            this.speed = iconAnimation.speed;
            this.size = iconAnimation.size;
            this.offsetX = iconAnimation.offsetX;
            this.offsetY = iconAnimation.offsetY;
            this.current = 0;
            this.tickCounter = 0;
            iconAnimation2 = this;
        }
        iconAnimation2.playedOnce = false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean matches(IconAnimation iconAnimation) {
        int n;
        boolean bl;
        block32: {
            block33: {
                IconAnimation iconAnimation2;
                block30: {
                    block31: {
                        IconAnimation iconAnimation3;
                        block28: {
                            block29: {
                                block27: {
                                    bl = IconStore.a;
                                    iconAnimation3 = iconAnimation;
                                    if (bl) break block27;
                                    if (iconAnimation3 == null) return false;
                                    iconAnimation3 = this;
                                }
                                if (bl) break block28;
                                if (iconAnimation3.frames.size() == iconAnimation.frames.size()) break block29;
                                return false;
                            }
                            iconAnimation3 = this;
                        }
                        int n2 = iconAnimation3.speed;
                        if (bl) return n2 != 0;
                        if (n2 != iconAnimation.speed) return 0 != 0;
                        iconAnimation2 = this;
                        if (bl) break block30;
                        if (iconAnimation2.size == iconAnimation.size) break block31;
                        return 0 != 0;
                    }
                    iconAnimation2 = this;
                }
                int n3 = iconAnimation2.offsetX;
                if (bl) return n3 != 0;
                if (n3 != iconAnimation.offsetX) return 0 != 0;
                n = this.offsetY;
                if (bl) break block32;
                if (n == iconAnimation.offsetY) break block33;
                return 0 != 0;
            }
            n = 0;
        }
        int n4 = n;
        do {
            block34: {
                if (n4 >= this.frames.size()) return true;
                boolean bl2 = Arrays.deepEquals((Object[])this.frames.get(n4), (Object[])iconAnimation.frames.get(n4));
                if (bl) return bl2;
                if (bl) boolean bl3;
                return bl3;
                if (bl2) break block34;
                return false;
            }
            ++n4;
        } while (!bl);
        return true;
    }

    int[][] blank() {
        return new int[this.dim][this.dim];
    }

    static int[][] copy(int[][] nArray) {
        int[][] nArrayArray;
        block4: {
            int[][] nArrayArray2 = new int[nArray.length][];
            boolean bl = IconStore.a;
            for (int i = 0; i < nArray.length; ++i) {
                nArrayArray = nArrayArray2;
                if (!bl) {
                    nArrayArray[i] = (int[])nArray[i].clone();
                    if (!bl) continue;
                    break;
                }
                break block4;
            }
            nArrayArray = nArrayArray2;
        }
        return nArrayArray;
    }

    
    static {
        long l = 7149070429101187281L;
        long[] lArray = new long[3];
        int n = 0;
        String string = "\u00f9\u00faJ?\u00f4=q\u0097\u007f\u0012\u00fc\f=@\u00ea5\u001d\u00cd\u00b3\u0083\u009e\u00cc\u00dc\u00b1";
        int n2 = "\u00f9\u00faJ?\u00f4=q\u0097\u007f\u0012\u00fc\f=@\u00ea5\u001d\u00cd\u00b3\u0083\u009e\u00cc\u00dc\u00b1".length();
        int n3 = 0;
        do {
            byte[] byArray = string.substring(n3, n3 += 8).getBytes("ISO-8859-1");
            int n4 = n++;
            lArray[n4] = (((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL) ^ l;
        } while (n3 < n2);
        a = lArray;
        b = new Integer[3];
        SIZE = IconAnimation.a(21067, 3378042809856789227L);
        MAX_SPEED = IconAnimation.a(25767, 88984112062249988L);
        long l2 = 5728468470312623729L;
        c = 0x4F7F9BB918025E43L ^ l2;
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x58A1;
        if (b[n2] == null) {
            IconAnimation.b[n2] = (int)(a[n2] ^ l);
        }
        return b[n2];
    }
}
