/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import opencellar.application.Command;
import opencellar.application.IApplication;

public final class CloseCellarCommand
extends Command {
    public CloseCellarCommand(IApplication application) {
        super(application);
    }

    public void execute() {
        this.getApp().close();
    }
}

