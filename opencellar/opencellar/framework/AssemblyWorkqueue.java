/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import java.util.ArrayList;
import java.util.Comparator;
import opencellar.framework.Assembly;
import opencellar.framework.AssemblyComparer;
import opencellar.framework.AssemblyWorkqueueItem;
import opencellar.framework.CellarObject;
import opencellar.framework.CellarObjectCollection;
import opencellar.framework.Wine;
import opencellar.framework.Workqueue;
import opencellar.framework.WorkqueueItem;
import opencellar.framework.WorkqueueItemType;

public final class AssemblyWorkqueue
extends Workqueue {
    protected AssemblyWorkqueue(Wine w) {
        super(w, "AssemblyWorkqueue");
    }

    public final void sort() {
        super.getWine().getAssemblies().sort(new AssemblyComparer());
    }

    public final AssemblyWorkqueueItem get(int index) {
        if (this.isValidIndex(index)) {
            return (AssemblyWorkqueueItem)this.m_list.get(index);
        }
        return null;
    }

    public WorkqueueItemType getType(Assembly assembly) {
        return super.get(assembly);
    }

    public final void set(AssemblyWorkqueueItem item) {
        super.set(item);
    }

    public final AssemblyWorkqueueItem create(Assembly assembly, WorkqueueItemType itemType) {
        return new AssemblyWorkqueueItem(assembly, itemType);
    }
}

