/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import java.util.ArrayList;
import opencellar.framework.CellarObjectCollection;
import opencellar.framework.ObjectType;

public final class CellarObjectCollectionManager {
    private ArrayList m_list = new ArrayList(13);

    protected CellarObjectCollectionManager() {
        this.init();
    }

    private void init() {
        this.m_list.add(new CellarObjectCollection(ObjectType.Wine));
        this.get((ObjectType)ObjectType.Wine).useFastIndex = true;
        this.m_list.add(new CellarObjectCollection(ObjectType.TypeOfWine));
        this.m_list.add(new CellarObjectCollection(ObjectType.Category));
        this.m_list.add(new CellarObjectCollection(ObjectType.Classification));
        this.m_list.add(new CellarObjectCollection(ObjectType.BottleType));
        this.m_list.add(new CellarObjectCollection(ObjectType.Area));
        this.m_list.add(new CellarObjectCollection(ObjectType.Country));
        this.m_list.add(new CellarObjectCollection(ObjectType.Name));
        this.m_list.add(new CellarObjectCollection(ObjectType.Provider));
        this.m_list.add(new CellarObjectCollection(ObjectType.Owner));
        this.m_list.add(new CellarObjectCollection(ObjectType.Rack));
        this.m_list.add(new CellarObjectCollection(ObjectType.Tracker));
        CellarObjectCollection cuvees = new CellarObjectCollection(ObjectType.InternalCuvee);
        cuvees.internalUse = true;
        this.m_list.add(cuvees);
    }

    public final CellarObjectCollection get(ObjectType code) {
        for (int i = 0; i < this.m_list.size(); ++i) {
            CellarObjectCollection list = (CellarObjectCollection)this.m_list.get(i);
            if (list.getCode() != code) continue;
            return list;
        }
        return null;
    }

    public final int size() {
        return this.m_list.size();
    }

    public final void clear() {
        for (int i = 0; i < this.m_list.size(); ++i) {
            ((CellarObjectCollection)this.m_list.get(i)).clear();
        }
        this.m_list.clear();
    }
}

