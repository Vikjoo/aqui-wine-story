/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.MisMatchEventArgs
 */
package opencellar.application;

import opencellar.application.IApplication;
import opencellar.application.IWindow;
import opencellar.framework.MisMatchEventArgs;

public interface IApplicationListener {
    public void onStarting(IApplication var1);

    public void onStart(IApplication var1);

    public void onShutDown(IApplication var1);

    public void onOpening(IApplication var1);

    public void onOpen(IApplication var1);

    public void onClosing(IApplication var1);

    public void onClose(IApplication var1);

    public void onReadOnly(IApplication var1);

    public void onMisMatchVersion(IApplication var1, MisMatchEventArgs var2);

    public void onCreateWindow(IApplication var1, IWindow var2);

    public void onCloseWindow(IApplication var1, IWindow var2);

    public void onActivateWindow(IApplication var1, IWindow var2);
}

