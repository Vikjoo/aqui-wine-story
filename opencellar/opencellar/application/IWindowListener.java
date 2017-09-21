/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import opencellar.application.IWindow;

public interface IWindowListener {
    public void onClose(IWindow var1);

    public void onActivate(IWindow var1);

    public void onCaptionChange(IWindow var1);
}

