package dev.quelchi.aurora.glass.ui;

import dev.quelchi.aurora.glass.ui.LayoutSpec;
import dev.quelchi.aurora.glass.ui.UiBounds;
import dev.quelchi.aurora.glass.ui.UiContext;
import dev.quelchi.aurora.glass.ui.layout.Layout;
import dev.quelchi.aurora.module.Module;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class UiComponent {
    private final List<UiComponent> children;
    private final List<UiComponent> childView;
    private final LayoutSpec layoutSpec;
    private UiComponent parent;
    private UiBounds bounds;
    private Layout layout;
    private boolean visible;
    private boolean enabled;
    private boolean clip;
    private boolean mounted;
    private boolean layoutDirty;
    private boolean hovered;
    private boolean focused;
    private boolean active;

    public UiComponent() {
        block4: {
            boolean bl;
            block6: {
                block5: {
                    this.children = new ArrayList<UiComponent>();
                    this.childView = Collections.unmodifiableList(this.children);
                    this.layoutSpec = new LayoutSpec(this::invalidateLayout);
                    this.bounds = new UiBounds(0, 0, 0, 0);
                    this.visible = true;
                    boolean bl2 = UiBounds.a;
                    this.enabled = true;
                    this.layoutDirty = true;
                    if (!bl2) break block4;
                    if (!Module.a) break block5;
                    bl = false;
                    break block6;
                }
                bl = true;
            }
            Module.a = bl;
        }
    }

    public List<UiComponent> children() {
        return this.childView;
    }

    public void clear() {
        block8: {
            Iterator<UiComponent> iterator = this.children.iterator();
            boolean bl = UiBounds.a;
            while (iterator.hasNext()) {
                block7: {
                    block9: {
                        UiComponent uiComponent = iterator.next();
                        if (bl) break block7;
                        if (bl) break block8;
                        if (!uiComponent.mounted) break block9;
                        uiComponent.unmount();
                    }
                    uiComponent.parent = null;
                }
                if (!bl) continue;
            }
            this.children.clear();
            this.invalidateLayout();
        }
    }

    public boolean visible() {
        return this.visible;
    }

    public boolean enabled() {
        return this.enabled;
    }

    public boolean active() {
        return this.active;
    }

    public boolean layoutDirty() {
        boolean bl;
        block11: {
            UiComponent uiComponent;
            boolean bl2;
            block9: {
                block10: {
                    bl2 = UiBounds.a;
                    uiComponent = this;
                    if (bl2) break block9;
                    if (!uiComponent.layoutDirty) break block10;
                    return true;
                }
                uiComponent = this;
            }
            for (UiComponent uiComponent2 : uiComponent.children) {
                block13: {
                    boolean bl3;
                    block12: {
                        bl = uiComponent2.layoutDirty();
                        if (bl2) break block11;
                        if (bl2) break block12;
                        if (!bl) break block13;
                        bl3 = true;
                    }
                    return bl3;
                }
                if (!bl2) continue;
            }
            bl = false;
        }
        return bl;
    }

    public void invalidateLayout() {
        block9: {
            UiComponent uiComponent;
            block10: {
                UiComponent uiComponent2;
                block8: {
                    boolean bl = UiBounds.a;
                    uiComponent2 = this;
                    if (bl) break block8;
                    if (uiComponent2.layoutDirty) break block9;
                    uiComponent = this;
                    if (bl) break block10;
                    uiComponent.layoutDirty = true;
                    uiComponent2 = this.parent;
                }
                if (uiComponent2 == null) break block9;
                uiComponent = this.parent;
            }
            uiComponent.invalidateLayout();
        }
    }

    public void layout(UiBounds uiBounds) {
        block5: {
            UiComponent uiComponent;
            block4: {
                this.bounds = uiBounds;
                this.layoutDirty = false;
                boolean bl = UiBounds.a;
                uiComponent = this;
                if (bl) break block4;
                if (uiComponent.layout == null) break block5;
                uiComponent = this;
            }
            uiComponent.layout.layout(this, uiBounds);
        }
    }

    public final void render(UiContext uiContext) {
        block20: {
            UiComponent uiComponent4;
            block26: {
                boolean bl;
                block24: {
                    block25: {
                        boolean bl2;
                        block23: {
                            block22: {
                                UiComponent uiComponent2;
                                block21: {
                                    block19: {
                                        bl = UiBounds.a;
                                        UiComponent uiComponent3 = this;
                                        if (bl) break block19;
                                        if (!uiComponent3.visible) break block20;
                                        uiComponent3 = this;
                                    }
                                    uiComponent2 = this;
                                    if (bl) break block21;
                                    if (!uiComponent2.enabled) break block22;
                                    uiComponent2 = this;
                                }
                                bl2 = uiComponent2.bounds.contains(uiContext.mouseX(), uiContext.mouseY());
                                if (bl) break block23;
                                if (!bl2) break block22;
                                bl2 = true;
                                break block23;
                            }
                            bl2 = false;
                        }
                        uiComponent3.hovered = bl2;
                        if (bl) break block24;
                        if (!this.clip) break block25;
                        uiContext.graphics().method_44379(this.bounds.x(), this.bounds.y(), this.bounds.right(), this.bounds.bottom());
                        uiContext.glass().stats().clip();
                    }
                    this.draw(uiContext);
                }
                for (UiComponent uiComponent4 : this.children) {
                    if (!bl) {
                        UiComponent uiComponent5 = uiComponent4;
                        uiComponent5.render(uiContext);
                        if (!bl) continue;
                    }
                    break block26;
                }
                uiComponent4 = this;
            }
            if (uiComponent4.clip) {
                uiContext.graphics().method_44380();
            }
        }
    }

    public void tick() {
        Iterator<UiComponent> iterator = this.children.iterator();
        boolean bl = UiBounds.a;
        while (iterator.hasNext()) {
            block6: {
                UiComponent uiComponent;
                block5: {
                    UiComponent uiComponent2 = iterator.next();
                    uiComponent = uiComponent2;
                    if (bl) break block5;
                    if (!uiComponent.visible) break block6;
                    uiComponent = uiComponent2;
                }
                uiComponent.tick();
            }
            if (!bl) continue;
        }
    }

    public final void mount() {
        block6: {
            UiComponent uiComponent;
            boolean bl;
            block5: {
                bl = UiBounds.a;
                uiComponent = this;
                if (bl) break block5;
                if (uiComponent.mounted) break block6;
                this.mounted = true;
                this.onMount();
                uiComponent = this;
            }
            for (UiComponent uiComponent2 : uiComponent.children) {
                uiComponent2.mount();
                if (!bl) continue;
            }
        }
    }

    public final void unmount() {
        block9: {
            block10: {
                UiComponent uiComponent;
                boolean bl;
                block8: {
                    bl = UiBounds.a;
                    uiComponent = this;
                    if (bl) break block8;
                    if (!uiComponent.mounted) break block9;
                    uiComponent = this;
                }
                for (UiComponent uiComponent2 : uiComponent.children) {
                    if (!bl) {
                        uiComponent2.unmount();
                        if (!bl) continue;
                        break;
                    }
                    break block10;
                }
                this.onUnmount();
                this.mounted = false;
                this.focused = false;
            }
            this.active = false;
        }
    }

    protected void draw(UiContext uiContext) {
    }

    protected void onMount() {
    }

    protected void onUnmount() {
    }
}
