/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import opencellar.application.CommandCollection;
import opencellar.application.IApplication;

public class CommandManager
extends CommandCollection {
    private IApplication m_application;

    public CommandManager(IApplication application) {
        if (application == null) {
            throw new IllegalArgumentException("application == null");
        }
        this.m_application = application;
    }

    public final IApplication getApp() {
        return this.m_application;
    }
}

