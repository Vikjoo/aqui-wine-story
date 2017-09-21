/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import opencellar.framework.Assembly;
import opencellar.framework.CellarObject;
import opencellar.framework.CellarObjectCollection;
import opencellar.framework.Wine;
import opencellar.framework.WorkqueueItem;
import opencellar.framework.WorkqueueItemType;

public final class AssemblyWorkqueueItem
extends WorkqueueItem {
    public AssemblyWorkqueueItem(Assembly item, WorkqueueItemType itemType) {
        super(item, itemType);
    }

    protected final void bind(Wine w) {
        if (this.getType() == WorkqueueItemType.Add) {
            Assembly parent = this.getItem();
            parent.setWine(w);
            parent.save();
            if (!w.getAssemblies().contains(parent)) {
                w.getAssemblies().add(parent);
            }
        } else if (this.getType() == WorkqueueItemType.Delete) {
            Assembly parent = this.getItem();
            parent.markAsDeleted();
            parent.setWine(null);
            if (w.getAssemblies().contains(parent)) {
                w.getAssemblies().remove(parent);
            }
            parent.save();
        }
    }

    public final Assembly getItem() {
        return (Assembly)super.getItem();
    }
}

