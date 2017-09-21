/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import opencellar.application.IApplication;
import opencellar.application.IRootKit;

public class RootKit
implements IRootKit {
    private IApplication m_app;

    public RootKit(IApplication app) {
        this.m_app = app;
    }

    protected final IApplication getApp() {
        return this.m_app;
    }

    public void initialize() {
    }

    public void uninitialize() {
    }
}

