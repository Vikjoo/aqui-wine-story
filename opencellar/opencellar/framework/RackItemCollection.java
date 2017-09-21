/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import java.util.ArrayList;
import java.util.Collections;
import opencellar.framework.CellarObject;
import opencellar.framework.CellarObjectCollection;
import opencellar.framework.ObjectType;
import opencellar.framework.Rack;
import opencellar.framework.RackItem;
import opencellar.framework.RackItemComparer;

public final class RackItemCollection {
    private Rack m_parent = null;
    private ArrayList m_list = new ArrayList();

    protected RackItemCollection(Rack parent) {
        this.m_parent = parent;
    }

    public Rack getParent() {
        return this.m_parent;
    }

    public final int size() {
        return this.m_list.size();
    }

    public final void sort() {
        Collections.sort(this.m_list, new RackItemComparer());
    }

    protected final void add(RackItem ri) {
        this.m_list.add(ri);
    }

    protected final void remove(RackItem ri) {
        this.m_list.remove(ri);
    }

    public final RackItem get(int index) {
        if (index > -1 && index < this.m_list.size()) {
            return (RackItem)this.m_list.get(index);
        }
        return null;
    }

    public final RackItem get(int column, int row) {
        int size = this.m_list.size();
        for (int i = 0; i < size; ++i) {
            RackItem ri = (RackItem)this.m_list.get(i);
            if (ri.getColumn() != column || ri.getRow() != row) continue;
            return ri;
        }
        return null;
    }

    public final boolean contains(RackItem ri) {
        return this.m_list.contains(ri);
    }

    protected final void clear() {
        this.m_list.clear();
        this.m_list.trimToSize();
    }

    public final int getRackCount() {
        this.sort();
        int ret = 0;
        Rack rk = null;
        int size = this.m_list.size();
        for (int i = 0; i < size; ++i) {
            RackItem ri = (RackItem)this.m_list.get(i);
            Rack parent = ri.getParent();
            if (parent == rk) continue;
            rk = parent;
            ++ret;
        }
        return ret;
    }

    public final CellarObjectCollection getRacks() {
        CellarObjectCollection coc = new CellarObjectCollection(ObjectType.Rack);
        int size = this.m_list.size();
        for (int i = 0; i < size; ++i) {
            RackItem ri = (RackItem)this.m_list.get(i);
            Rack parent = ri.getParent();
            if (coc.contains(parent)) continue;
            coc.add(parent);
        }
        return coc;
    }

    public final int getRackItemCount(Rack parent) {
        int ret = 0;
        int size = this.m_list.size();
        for (int i = 0; i < size; ++i) {
            RackItem ri = (RackItem)this.m_list.get(i);
            if (ri.getParent() != parent) continue;
            ++ret;
        }
        return ret;
    }
}

