/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.CellarApplication
 *  opencellar.framework.ICellarApplicationAdapter
 *  opencellar.framework.ICellarApplicationListener
 *  opencellar.framework.MisMatchEventArgs
 */
package opencellar.application;

import opencellar.application.Application;
import opencellar.application.IApplication;
import opencellar.application.IRootKit;
import opencellar.framework.CellarApplication;
import opencellar.framework.ICellarApplicationAdapter;
import opencellar.framework.ICellarApplicationListener;
import opencellar.framework.MisMatchEventArgs;

public final class ApplicationEventDispatcher
extends ICellarApplicationAdapter
implements IRootKit {
    private Application m_app;

    protected ApplicationEventDispatcher(IApplication application) {
        if (application == null) {
            throw new IllegalArgumentException("application == null");
        }
        if (!(application instanceof Application)) {
            throw new IllegalArgumentException("application != instanceof application");
        }
        this.m_app = (Application)application;
    }

    private final Application getApp() {
        return this.m_app;
    }

    public void onOpen(CellarApplication source) {
        this.getApp().internalnotifyOnCellarOpened();
        this.getApp().notifyOnOpen();
    }

    public void onOpening(CellarApplication source) {
        this.getApp().internalnotifyOnCellarOpening();
        this.getApp().notifyOnOpening();
    }

    public void onClosing(CellarApplication source) {
        this.getApp().notifyOnClosing();
        this.getApp().internalnotifyOnCellarClosing();
    }

    public void onClose(CellarApplication source) {
        this.getApp().notifyOnClose();
        this.getApp().internalnotifyOnCellarClosed();
    }

    public void onMisMatchVersion(CellarApplication source, MisMatchEventArgs args) {
        this.getApp().notifyOnMisMatchVersion(args);
    }

    public void onReadOnly(CellarApplication source) {
        this.getApp().notifyOnReadOnly();
    }

    public void initialize() {
        this.getApp().getCellarApplication().addListener((ICellarApplicationListener)this);
    }

    public void uninitialize() {
    }
}

