/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import opencellar.application.Command;
import opencellar.application.IApplication;

public final class ExitCommand
extends Command {
    public ExitCommand(IApplication application) {
        super(application);
    }

    public final void execute() {
        super.getApp().exit();
    }
}

