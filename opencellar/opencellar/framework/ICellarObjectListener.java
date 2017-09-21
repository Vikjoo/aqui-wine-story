/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import opencellar.framework.CellarObject;

public interface ICellarObjectListener {
    public void onStateChanged(CellarObject var1);

    public void onPropertyChanged(CellarObject var1, String var2);
}

