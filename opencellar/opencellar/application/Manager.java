/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import opencellar.application.Application;
import opencellar.application.IApplication;

public class Manager {
    private IApplication m_app;

    public Manager(IApplication app) {
        if (app == null) {
            throw new IllegalArgumentException("app");
        }
        this.m_app = app;
    }

    public final IApplication getApp() {
        return this.m_app;
    }

    protected final Application internalGetApp() {
        return (Application)this.m_app;
    }

    protected void build() {
    }
}

