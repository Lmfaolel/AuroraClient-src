package dev.quelchi.aurora.gui;

import dev.quelchi.aurora.gui.PreviewScene$Figure;
import dev.quelchi.aurora.gui.PreviewScene$Scene;
import java.lang.invoke.MethodHandle;
import java.lang.runtime.ObjectMethods;
import java.util.List;
import net.minecraft.client.gui.ScreenRect;
import org.jetbrains.annotations.Nullable;

public final class PreviewScene$Grid
extends Record
implements PreviewScene$Scene {
    private final List<PreviewScene$Figure> figures;
    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;
    private final float scale;
    @Nullable
    private final ScreenRect scissorArea;
    @Nullable
    private final ScreenRect bounds;

    public PreviewScene$Grid(List<PreviewScene$Figure> list, int n, int n2, int n3, int n4, float f, @Nullable ScreenRect screenRect, @Nullable ScreenRect screenRect2) {
        this.figures = list;
        this.x1 = n;
        this.y1 = n2;
        this.x2 = n3;
        this.y2 = n4;
        this.scale = f;
        this.scissorArea = screenRect;
        this.bounds = screenRect2;
    }

    @Override
    public final String toString() {
        return ObjectMethods.bootstrap("toString", new MethodHandle[]{PreviewScene$Grid.class, "figures;x1;y1;x2;y2;scale;scissorArea;bounds", "figures", "x1", "y1", "x2", "y2", "scale", "scissorArea", "bounds"}, this);
    }

    @Override
    public final int hashCode() {
        return (int)ObjectMethods.bootstrap("hashCode", new MethodHandle[]{PreviewScene$Grid.class, "figures;x1;y1;x2;y2;scale;scissorArea;bounds", "figures", "x1", "y1", "x2", "y2", "scale", "scissorArea", "bounds"}, this);
    }

    @Override
    public final boolean equals(Object object) {
        return (boolean)ObjectMethods.bootstrap("equals", new MethodHandle[]{PreviewScene$Grid.class, "figures;x1;y1;x2;y2;scale;scissorArea;bounds", "figures", "x1", "y1", "x2", "y2", "scale", "scissorArea", "bounds"}, this, object);
    }

    @Override
    public List<PreviewScene$Figure> figures() {
        return this.figures;
    }

    public int comp_4122() {
        return this.x1;
    }

    public int comp_4123() {
        return this.y1;
    }

    public int comp_4124() {
        return this.x2;
    }

    public int comp_4125() {
        return this.y2;
    }

    public float comp_4133() {
        return this.scale;
    }

    @Nullable
    public ScreenRect comp_4128() {
        return this.scissorArea;
    }

    @Nullable
    public ScreenRect comp_4274() {
        return this.bounds;
    }
}
