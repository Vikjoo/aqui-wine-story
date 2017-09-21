/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import java.util.ArrayList;
import opencellar.framework.CellarObject;
import opencellar.framework.CellarObjectCollection;
import opencellar.framework.ObjectType;
import opencellar.framework.Rack;
import opencellar.framework.RackItem;
import opencellar.framework.RackItemCollection;
import opencellar.framework.RackItemWorkqueueItem;
import opencellar.framework.Wine;
import opencellar.framework.Workqueue;
import opencellar.framework.WorkqueueItem;
import opencellar.framework.WorkqueueItemType;

public final class RackItemWorkqueue
extends Workqueue {
    protected RackItemWorkqueue(Wine w) {
        super(w, "RackItemWorkqueue");
    }

    public final void sort() {
        super.getWine().getRackItems().sort();
    }

    protected final void postApply() {
        super.getWine().notifyOnPropertyChanged("RackItems");
        CellarObjectCollection coc = this.getRacks();
        for (int i = 0; i < coc.size(); ++i) {
            ((Rack)coc.get(i)).notifyOnChange();
        }
    }

    public final CellarObjectCollection getRacks() {
        CellarObjectCollection coc = new CellarObjectCollection(ObjectType.Rack);
        for (int i = 0; i < this.m_list.size(); ++i) {
            Rack parent = this.get(i).getItem().getParent();
            if (coc.contains(parent)) continue;
            coc.add(parent);
        }
        return coc;
    }

    public CellarObjectCollection getAt(Rack rack) {
        CellarObjectCollection al = new CellarObjectCollection(ObjectType.RackItem);
        for (int j = 0; j < this.m_list.size(); ++j) {
            RackItemWorkqueueItem item = (RackItemWorkqueueItem)this.m_list.get(j);
            if (item.getItem().getParent() != rack || item.getType() != WorkqueueItemType.Add || this.getWine().getRackItems().contains(item.getItem())) continue;
            al.add(item.getItem());
        }
        return al;
    }

    public WorkqueueItemType getAt(RackItem item) {
        for (int i = 0; i < this.m_list.size(); ++i) {
            RackItemWorkqueueItem witem = (RackItemWorkqueueItem)this.m_list.get(i);
            if (witem.getItem() != item) continue;
            return witem.getType();
        }
        return WorkqueueItemType.Unknow;
    }

    public final RackItemWorkqueueItem get(int index) {
        if (this.isValidIndex(index)) {
            return (RackItemWorkqueueItem)this.m_list.get(index);
        }
        return null;
    }

    public WorkqueueItemType getType(RackItem rackItem) {
        return super.get(rackItem);
    }

    public final void set(RackItemWorkqueueItem item) {
        super.set(item);
    }

    public final RackItemWorkqueueItem create(RackItem rackItem, WorkqueueItemType itemType) {
        return new RackItemWorkqueueItem(rackItem, itemType);
    }

    public int getUnpresents(Rack rack) {
        int ret = 0;
        for (int j = 0; j < this.m_list.size(); ++j) {
            RackItemWorkqueueItem item = (RackItemWorkqueueItem)this.m_list.get(j);
            if (item.getItem().getParent() != rack || item.getType() != WorkqueueItemType.Add || this.getWine().getRackItems().contains(item.getItem())) continue;
            ++ret;
        }
        return ret;
    }
}

