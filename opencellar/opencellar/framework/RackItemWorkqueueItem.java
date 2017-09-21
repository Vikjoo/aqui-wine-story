/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import opencellar.framework.CellarObject;
import opencellar.framework.ObjectState;
import opencellar.framework.RackItem;
import opencellar.framework.RackItemCollection;
import opencellar.framework.Wine;
import opencellar.framework.WorkqueueItem;
import opencellar.framework.WorkqueueItemType;

public class RackItemWorkqueueItem
extends WorkqueueItem {
    public RackItemWorkqueueItem(RackItem item, WorkqueueItemType itemType) {
        super(item, itemType);
    }

    protected final void bind(Wine w) {
        RackItem parent = this.getItem();
        if (parent == null) {
            return;
        }
        if (parent.getState() == ObjectState.Delete) {
            return;
        }
        if (this.getType() == WorkqueueItemType.Add) {
            parent.setWine(w);
            parent.save();
            if (!w.getRackItems().contains(parent)) {
                w.getRackItems().add(parent);
            }
        } else if (this.getType() == WorkqueueItemType.Delete) {
            parent.consume(false);
        }
    }

    public final RackItem getItem() {
        return (RackItem)super.getItem();
    }
}

