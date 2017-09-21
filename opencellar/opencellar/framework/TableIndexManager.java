/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import java.util.ArrayList;
import opencellar.framework.ObjectType;
import opencellar.framework.TableIndex;

public final class TableIndexManager {
    private ArrayList m_list = new ArrayList(4);

    protected TableIndexManager() {
        this.init();
    }

    private void init() {
        this.m_list.add(new TableIndex(ObjectType.Image));
        this.m_list.add(new TableIndex(ObjectType.Note));
        this.m_list.add(new TableIndex(ObjectType.PurchaseSales));
        this.m_list.add(new TableIndex(ObjectType.Assembly));
    }

    public final TableIndex get(ObjectType code) {
        for (int i = 0; i < this.m_list.size(); ++i) {
            TableIndex list = (TableIndex)this.m_list.get(i);
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
            ((TableIndex)this.m_list.get(i)).clear();
        }
        this.m_list.clear();
    }
}

