/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import opencellar.framework.CellarObject;
import opencellar.framework.Wine;
import opencellar.framework.WorkqueueItemType;

public abstract class WorkqueueItem {
    private CellarObject m_item;
    private WorkqueueItemType m_itemType = WorkqueueItemType.Unknow;

    protected WorkqueueItem(CellarObject item, WorkqueueItemType itemType) {
        this.m_itemType = itemType;
        this.m_item = item;
    }

    protected CellarObject getItem() {
        return this.m_item;
    }

    protected final void setItem(CellarObject o) {
        this.m_item = o;
    }

    public final WorkqueueItemType getType() {
        return this.m_itemType;
    }

    protected abstract void bind(Wine var1);
}

