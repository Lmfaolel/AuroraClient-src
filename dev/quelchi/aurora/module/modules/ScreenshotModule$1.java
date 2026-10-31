package dev.quelchi.aurora.module.modules;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;

class ScreenshotModule$1
implements Transferable {
    final BufferedImage val$img;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    ScreenshotModule$1() {
        void var2_-1;
        this.val$img = var2_-1;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{DataFlavor.imageFlavor};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor dataFlavor) {
        return DataFlavor.imageFlavor.equals(dataFlavor);
    }

    @Override
    public Object getTransferData(DataFlavor dataFlavor) {
        return this.val$img;
    }
}
