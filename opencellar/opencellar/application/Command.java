/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import opencellar.application.DialogResult;
import opencellar.application.IApplication;
import opencellar.application.ICommand;
import opencellar.application.MessageButtonType;
import opencellar.application.MessageIconType;
import opencellar.application.MessageType;

public class Command
implements ICommand {
    private IApplication m_app;

    protected Command(IApplication application) {
        if (application == null) {
            throw new IllegalArgumentException("application == null");
        }
        this.m_app = application;
    }

    protected final IApplication getApp() {
        return this.m_app;
    }

    public void execute() {
    }

    protected void showWarningMessage(String message) {
        this.getApp().showMessage(null, message, MessageType.Message, MessageIconType.Warning, MessageButtonType.Default);
    }
}

