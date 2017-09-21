/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import java.util.ArrayList;
import opencellar.framework.CellarObject;
import opencellar.framework.Wine;
import opencellar.framework.WorkqueueItem;
import opencellar.framework.WorkqueueItemType;

public class Workqueue {
    private String m_propertyName;
    private Wine m_wine;
    protected final ArrayList m_list = new ArrayList();

    protected Workqueue(Wine w, String propertyName) {
        this.m_wine = w;
        this.m_propertyName = propertyName;
    }

    public Wine getWine() {
        return this.m_wine;
    }

    public final void apply() {
        for (int i = 0; i < this.m_list.size(); ++i) {
            WorkqueueItem item = (WorkqueueItem)this.m_list.get(i);
            if (item.getType() == WorkqueueItemType.Unknow) continue;
            item.bind(this.m_wine);
        }
        this.postApply();
        this.sort();
        this.notifyParent();
        this.clear();
    }

    protected void postApply() {
    }

    protected WorkqueueItem get(WorkqueueItemType itemType, CellarObject target) {
        for (int i = 0; i < this.m_list.size(); ++i) {
            WorkqueueItem item = (WorkqueueItem)this.m_list.get(i);
            if (item.getType() != itemType || item.getItem() != target) continue;
            return item;
        }
        return null;
    }

    protected WorkqueueItemType get(CellarObject target) {
        for (int i = 0; i < this.m_list.size(); ++i) {
            WorkqueueItem item = (WorkqueueItem)this.m_list.get(i);
            if (item.getItem() != target) continue;
            return item.getType();
        }
        return WorkqueueItemType.Unknow;
    }

    protected void remove(WorkqueueItemType itemType, CellarObject target) {
        for (int i = 0; i < this.m_list.size(); ++i) {
            WorkqueueItem item = (WorkqueueItem)this.m_list.get(i);
            if (item.getType() != itemType || item.getItem() != target) continue;
            this.m_list.remove(i);
            break;
        }
    }

    protected void set(WorkqueueItem item) {
        if (item.getType() == WorkqueueItemType.Unknow) {
            return;
        }
        if (this.get(item.getType(), item.getItem()) != null) {
            this.notifyParent();
            return;
        }
        if (item.getType() == WorkqueueItemType.Add) {
            this.remove(WorkqueueItemType.Delete, item.getItem());
            this.m_list.add(item);
        } else if (item.getType() == WorkqueueItemType.Delete) {
            this.remove(WorkqueueItemType.Add, item.getItem());
            this.m_list.add(item);
        }
        this.notifyParent();
        this.m_wine.setToUpdate();
    }

    protected String getActivePropertyName() {
        return this.m_propertyName;
    }

    protected void notifyParent() {
        this.m_wine.notifyOnPropertyChanged(this.getActivePropertyName());
    }

    public void sort() {
    }

    public final void clear() {
        this.m_list.clear();
    }

    public final int size() {
        return this.m_list.size();
    }

    public int itemDeleted() {
        return 0;
    }

    public int itemAdded() {
        return 0;
    }

    protected boolean isValidIndex(int index) {
        if (index > -1 && this.m_list.size() > index) {
            return true;
        }
        return false;
    }
}

