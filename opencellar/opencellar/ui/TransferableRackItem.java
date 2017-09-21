/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.RackItem
 */
package opencellar.ui;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import opencellar.framework.RackItem;

public class TransferableRackItem
implements Transferable {
    public static DataFlavor RACK_ITEM_FLAVOR = new DataFlavor(RackItem.class, "RackItem");
    DataFlavor[] flavors = new DataFlavor[]{RACK_ITEM_FLAVOR};
    RackItem m_item;

    public TransferableRackItem(RackItem item) {
        this.m_item = item;
    }

    public synchronized DataFlavor[] getTransferDataFlavors() {
        return this.flavors;
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.getRepresentationClass() == RackItem.class;
    }

    public synchronized Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (this.isDataFlavorSupported(flavor)) {
            return this.m_item;
        }
        throw new UnsupportedFlavorException(flavor);
    }
}

