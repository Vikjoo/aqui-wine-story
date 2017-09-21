/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import opencellar.application.IApplication;
import opencellar.application.MenuItem;
import opencellar.application.MenuItemCollection;

public class MenuItemManager
extends MenuItemCollection {
    private IApplication m_app;

    protected MenuItemManager(IApplication app) {
        super(null);
        this.m_app = app;
    }

    public final IApplication getApp() {
        return this.m_app;
    }
}

