/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import opencellar.framework.Index;
import opencellar.framework.ObjectType;

public final class TableIndex {
    private ObjectType m_type;
    private HashMap m_list = new HashMap();

    public TableIndex(ObjectType type) {
        this.m_type = type;
    }

    public final ObjectType getType() {
        return this.m_type;
    }

    public final ObjectType getCode() {
        return this.m_type;
    }

    public final int size() {
        return this.m_list.size();
    }

    public final void set(Index i) {
        this.m_list.put(i.getPk(), i);
    }

    public final Index get(String pk) {
        return (Index)this.m_list.get(pk);
    }

    public boolean contains(String pk) {
        return this.m_list.containsKey(pk);
    }

    public void remove(String pk) {
        this.m_list.remove(pk);
    }

    public void clear() {
        this.m_list.clear();
    }

    public ArrayList find(String fk) {
        ArrayList<Index> al = new ArrayList<Index>();
        if (!this.m_list.isEmpty()) {
            for (Map.Entry entry : this.m_list.entrySet()) {
                Index index = (Index)entry.getValue();
                if (!index.getFk().equals(fk)) continue;
                al.add(index);
            }
        }
        return al;
    }
}

