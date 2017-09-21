/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import opencellar.framework.Cellar;
import opencellar.framework.CellarObject;

public interface ICellarListener {
    public void onNewItem(Cellar var1, CellarObject var2);

    public void onUpdateItem(Cellar var1, CellarObject var2);

    public void onDeleteItem(Cellar var1, CellarObject var2);
}

