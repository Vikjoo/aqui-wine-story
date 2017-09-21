/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import opencellar.application.IApplication;

public interface IInternalApplicationListener {
    public void onStartUp(IApplication var1);

    public void onCellarOpening(IApplication var1);

    public void onCellarOpened(IApplication var1);

    public void onCellarClosing(IApplication var1);

    public void onCellarClosed(IApplication var1);

    public void onShutDown(IApplication var1);
}

