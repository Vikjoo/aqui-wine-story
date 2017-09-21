/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Cellar
 *  opencellar.framework.Rack
 */
package opencellar.application;

import opencellar.application.Command;
import opencellar.application.DialogResult;
import opencellar.application.IApplication;
import opencellar.application.IRackWindow;
import opencellar.application.IWindow;
import opencellar.application.MessageButtonType;
import opencellar.application.MessageIconType;
import opencellar.application.MessageType;
import opencellar.framework.Cellar;
import opencellar.framework.Rack;

public final class DeleteRackCommand
extends Command {
    public DeleteRackCommand(IApplication app) {
        super(app);
    }

    public void execute() {
        if (super.getApp().activeCellar() == null) {
            super.showWarningMessage(this.getApp().getRS("MSG_NULL_CELLAR"));
            return;
        }
        if (this.getApp().activeWindow() instanceof IRackWindow) {
            IRackWindow rackWindow = (IRackWindow)((Object)this.getApp().activeWindow());
            Rack rack = rackWindow.getRack();
            if (rack == null) {
                return;
            }
            if (!rack.isEmpty()) {
                this.getApp().showMessage(null, this.getApp().getRS("ADM_RACK_CANNOT"), MessageType.Message, MessageIconType.Warning, MessageButtonType.Default);
                return;
            }
            if (this.getApp().showMessage(null, this.getApp().getRS("ADM_RACK_CONFIRM_DELETE"), MessageType.Confirm, MessageIconType.Question, MessageButtonType.YesNo) == DialogResult.Yes) {
                this.getApp().setCursor(true);
                rack.delete();
                this.getApp().setCursor(false);
            }
        } else {
            super.showWarningMessage("#debug : rackWindow -> null");
        }
    }
}

