/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import opencellar.application.IApplication;
import opencellar.application.IOpenCellarListener;
import opencellar.application.IPreviewRenderer;
import opencellar.application.OpenCellarEventDispatcher;
import opencellar.application.Preview1;
import opencellar.application.PreviewCollection;

public final class PreviewManager
extends PreviewCollection {
    private final IApplication application;
    private IPreviewRenderer activePreview = null;
    private OpenCellarEventDispatcher m_dispatcher;
    public static final int EVENT_ACTIVATE_CHANGE = 1;

    protected PreviewManager(IApplication app) {
        if (app == null) {
            throw new IllegalArgumentException("app");
        }
        this.application = app;
        this.m_dispatcher = new OpenCellarEventDispatcher(this);
    }

    public final IApplication getApplication() {
        return this.application;
    }

    protected void build() {
        this.enableEvents = false;
        this.add(new Preview1());
        this.enableEvents = true;
    }

    public IPreviewRenderer getActive() {
        if (!this.contains(this.activePreview)) {
            this.activePreview = this.get(0);
        }
        return this.activePreview;
    }

    public final void setActive(IPreviewRenderer renderer) {
        if (this.contains(renderer)) {
            this.activePreview = renderer;
            this.m_dispatcher.dispatch(1);
        }
    }

    public final void addOpenCellarListener(IOpenCellarListener listener) {
        this.m_dispatcher.add(listener);
    }

    public final void removeOpenCellarListener(IOpenCellarListener listener) {
        this.m_dispatcher.remove(listener);
    }
}

