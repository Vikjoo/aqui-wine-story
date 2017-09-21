/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Cellar
 *  opencellar.framework.CellarObjectCollection
 *  opencellar.framework.ObjectType
 */
package opencellar.application;

import opencellar.application.Command;
import opencellar.application.IApplication;
import opencellar.application.IGridWinesWindow;
import opencellar.application.IWindow;
import opencellar.application.WineCollection;
import opencellar.framework.Cellar;
import opencellar.framework.CellarObjectCollection;
import opencellar.framework.ObjectType;

public final class RefreshGridCommand
extends Command {
    public RefreshGridCommand(IApplication app) {
        super(app);
    }

    public void execute() {
        if (super.getApp().activeCellar() == null) {
            super.showWarningMessage(this.getApp().getRS("MSG_NULL_CELLAR"));
            return;
        }
        if (this.getApp().activeWindow() instanceof IGridWinesWindow) {
            IGridWinesWindow winesWindow = (IGridWinesWindow)((Object)this.getApp().activeWindow());
            WineCollection newWc = new WineCollection(this.getApp().activeCellar().getCollection(ObjectType.Wine));
            winesWindow.setDatasource(newWc);
        } else {
            super.showWarningMessage("#debug : gridWinesWindow -> null");
        }
    }
}

