/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import opencellar.framework.CellarObject;
import opencellar.framework.CellarObjectComparer;
import opencellar.framework.ObjectType;

public class CellarObjectCollection {
    private ArrayList m_list = new ArrayList();
    protected boolean internalUse = false;
    private ObjectType m_code;
    protected boolean useFastIndex = false;
    private HashMap m_indexes = new HashMap();
    protected boolean needSorting = true;

    protected CellarObjectCollection(ObjectType code) {
        this.m_code = code;
    }

    public ObjectType getCode() {
        return this.m_code;
    }

    protected void add(CellarObject object) {
        this.m_list.add(object);
        if (this.useFastIndex) {
            this.m_indexes.put(object.getSystemUid(), this.m_list.size() - 1);
        }
    }

    protected void remove(CellarObject object) {
        this.m_list.remove(object);
    }

    public CellarObject get(int index) {
        if (index > -1 && index < this.m_list.size()) {
            return (CellarObject)this.m_list.get(index);
        }
        return null;
    }

    protected CellarObject get(String systemId) {
        for (int i = 0; i < this.m_list.size(); ++i) {
            CellarObject co = (CellarObject)this.m_list.get(i);
            if (!co.getSystemUid().equals(systemId)) continue;
            return co;
        }
        return null;
    }

    public final CellarObject find(String systemId) {
        return this.get(systemId);
    }

    protected CellarObject getFast(String systemUid) {
        Object index = this.m_indexes.get(systemUid);
        if (index != null) {
            int i = Integer.parseInt(index.toString());
            return (CellarObject)this.m_list.get(i);
        }
        return null;
    }

    public int size() {
        return this.m_list.size();
    }

    public boolean contains(CellarObject obj) {
        if (obj == null) {
            return false;
        }
        for (int i = 0; i < this.m_list.size(); ++i) {
            if (!((CellarObject)this.m_list.get(i)).getSystemUid().equals(obj.getSystemUid())) continue;
            return true;
        }
        return false;
    }

    public void sort() {
        if (this.needSorting) {
            Collections.sort(this.m_list, new CellarObjectComparer());
            this.needSorting = false;
        }
    }

    public final void sort(Comparator c) {
        if (c != null) {
            Collections.sort(this.m_list, c);
        }
    }

    protected void clear() {
        this.m_list.clear();
        this.m_list.trimToSize();
    }
}

