/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import opencellar.framework.CellarApplication;
import opencellar.framework.MisMatchEventArgs;

public interface ICellarApplicationListener {
    public void onOpen(CellarApplication var1);

    public void onOpening(CellarApplication var1);

    public void onClosing(CellarApplication var1);

    public void onClose(CellarApplication var1);

    public void onMisMatchVersion(CellarApplication var1, MisMatchEventArgs var2);

    public void onReadOnly(CellarApplication var1);
}

