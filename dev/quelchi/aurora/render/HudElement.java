package dev.quelchi.aurora.render;

import com.google.gson.JsonObject;
import dev.quelchi.aurora.module.DraggableHud;
import dev.quelchi.aurora.render.EffectEngine;

public final class HudElement
implements DraggableHud {
    private final String key;
    private int x;
    private int y;
    private int[] bounds;
    private static final String a;

    public HudElement(String string, int n, int n2) {
        this.key = string;
        this.x = n;
        this.y = n2;
        this.bounds = new int[]{n, n2, 0, 0};
        this.rememberHome();
    }

    public void setBounds(int n, int n2) {
        this.bounds = new int[]{this.x, this.y, n, n2};
    }

    public void clearBounds() {
        this.bounds = new int[]{this.x, this.y, 0, 0};
    }

    @Override
    public int getHudX() {
        return this.x;
    }

    @Override
    public int getHudY() {
        return this.y;
    }

    @Override
    public void setHudPos(int n, int n2) {
        this.x = n;
        this.y = n2;
    }

    @Override
    public String hudKey() {
        return a + this.key;
    }

    @Override
    public int[] getLastBounds() {
        return this.bounds;
    }

    public void write(JsonObject jsonObject) {
        jsonObject.addProperty(this.key + "X", (Number)this.x);
        jsonObject.addProperty(this.key + "Y", (Number)this.y);
    }

    public void read(JsonObject jsonObject) {
        JsonObject jsonObject2;
        block7: {
            block8: {
                int n = EffectEngine.a;
                jsonObject2 = jsonObject;
                if (n != 0) break block7;
                if (!jsonObject2.has(this.key + "X")) break block8;
                this.x = jsonObject.get(this.key + "X").getAsInt();
            }
            jsonObject2 = jsonObject;
        }
        if (jsonObject2.has(this.key + "Y")) {
            this.y = jsonObject.get(this.key + "Y").getAsInt();
        }
    }

    
    static {
        char[] cArray;
        block11: {
            int n;
            int n2;
            block10: {
                char[] cArray2 = "\u00191fD\u0015B\u001aR".toCharArray();
                cArray = cArray2;
                n2 = cArray2.length;
                n = 0;
                if (!true) break block10;
                n2 = n2;
                if (n2 <= n) break block11;
            }
            do {
                cArray = cArray;
                int n3 = n;
                char c = cArray[n3];
                cArray[n3] = (char)(c ^ (switch (n % 7) {
                    case 0 -> 124;
                    case 1 -> 93;
                    case 2 -> 3;
                    case 3 -> 41;
                    case 4 -> 112;
                    case 5 -> 44;
                    default -> 110;
                }));
                ++n;
                n2 = n2;
            } while (n2 > n);
        }
        a = new String(cArray).intern();
    }
}
