/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import opencellar.framework.CellarObject;
import opencellar.framework.CellarObjectCollection;
import opencellar.framework.PurchaseSales;
import opencellar.framework.Wine;
import opencellar.framework.WorkqueueItem;
import opencellar.framework.WorkqueueItemType;

public final class PurchaseSalesWorkqueueItem
extends WorkqueueItem {
    public PurchaseSalesWorkqueueItem(PurchaseSales item, WorkqueueItemType itemType) {
        super(item, itemType);
    }

    protected final void bind(Wine w) {
        if (this.getType() == WorkqueueItemType.Add) {
            PurchaseSales parent = this.getItem();
            parent.setWine(w);
            parent.save();
            if (!w.getPurchasesSales().contains(parent)) {
                w.getPurchasesSales().add(parent);
            }
        } else if (this.getType() == WorkqueueItemType.Delete) {
            PurchaseSales parent = this.getItem();
            parent.markAsDeleted();
            parent.setWine(null);
            if (w.getPurchasesSales().contains(parent)) {
                w.getPurchasesSales().remove(parent);
            }
            parent.save();
        }
    }

    public final PurchaseSales getItem() {
        return (PurchaseSales)super.getItem();
    }
}

