/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import opencellar.application.IApplication;
import opencellar.application.UrlDisplayerCommand;

public final class OnlineHelpCommand
extends UrlDisplayerCommand {
    public OnlineHelpCommand(IApplication app) {
        super(app, "http://www.open-cellar.com/kb.aspx");
    }
}

