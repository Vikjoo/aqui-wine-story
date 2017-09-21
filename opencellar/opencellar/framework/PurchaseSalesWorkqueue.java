/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import java.util.ArrayList;
import java.util.Comparator;
import opencellar.framework.CellarObject;
import opencellar.framework.CellarObjectCollection;
import opencellar.framework.PurchaseSales;
import opencellar.framework.PurchaseSalesComparer;
import opencellar.framework.PurchaseSalesWorkqueueItem;
import opencellar.framework.Wine;
import opencellar.framework.Workqueue;
import opencellar.framework.WorkqueueItem;
import opencellar.framework.WorkqueueItemType;

public final class PurchaseSalesWorkqueue
extends Workqueue {
    protected PurchaseSalesWorkqueue(Wine w) {
        super(w, "PurchaseSalesWorkqueue");
    }

    public final void sort() {
        super.getWine().getPurchasesSales().sort(new PurchaseSalesComparer());
    }

    protected final void postApply() {
        super.getWine().notifyOnPropertyChanged("PurchaseSales");
    }

    public final PurchaseSalesWorkqueueItem get(int index) {
        if (this.isValidIndex(index)) {
            return (PurchaseSalesWorkqueueItem)this.m_list.get(index);
        }
        return null;
    }

    public WorkqueueItemType getType(PurchaseSales psales) {
        return super.get(psales);
    }

    public final void set(PurchaseSalesWorkqueueItem item) {
        super.set(item);
    }

    public final void remove(WorkqueueItemType type, PurchaseSales sales) {
        super.remove(type, sales);
        super.notifyParent();
    }

    public final PurchaseSalesWorkqueueItem create(PurchaseSales psales, WorkqueueItemType itemType) {
        return new PurchaseSalesWorkqueueItem(psales, itemType);
    }
}

