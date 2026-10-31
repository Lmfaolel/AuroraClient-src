package dev.quelchi.aurora.gui;

record AuroraBackdrop$Curtain(float base, float amp, float height, int waves, int ripples, int bands, float phase, int rgb, float alpha, float drift, float rays) {
    float top() {
        return this.base - this.amp * 1.3f - this.height;
    }

    float bottom() {
        return this.base + this.amp * 1.3f + 0.05f;
    }
}
