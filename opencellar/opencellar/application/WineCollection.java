/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.CellarObject
 *  opencellar.framework.CellarObjectCollection
 *  opencellar.framework.ObjectType
 *  opencellar.framework.Wine
 */
package opencellar.application;

import java.util.ArrayList;
import opencellar.framework.CellarObject;
import opencellar.framework.CellarObjectCollection;
import opencellar.framework.ObjectType;
import opencellar.framework.Wine;

public final class WineCollection {
    private ArrayList m_list = new ArrayList();

    public WineCollection() {
    }

    public WineCollection(CellarObjectCollection wines) {
        if (wines == null) {
            throw new IllegalArgumentException("wines == null");
        }
        if (wines.getCode() != ObjectType.Wine) {
            throw new IllegalArgumentException("wines.getCode() != ObjectType.Wine");
        }
        for (int i = 0; i < wines.size(); ++i) {
            this.add((Wine)wines.get(i));
        }
    }

    public final int size() {
        return this.m_list.size();
    }

    public final void add(Wine wine) {
        if (wine != null) {
            this.m_list.add(wine);
        }
    }

    public final void remove(Wine wine) {
        if (wine != null) {
            this.m_list.remove((Object)wine);
        }
    }

    public final Wine get(int pos) {
        if (pos > -1 && pos < this.m_list.size()) {
            return (Wine)this.m_list.get(pos);
        }
        return null;
    }
}

