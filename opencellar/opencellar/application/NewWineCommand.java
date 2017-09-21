/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Cellar
 *  opencellar.framework.CellarObject
 *  opencellar.framework.ObjectType
 *  opencellar.framework.Wine
 */
package opencellar.application;

import opencellar.application.Command;
import opencellar.application.IApplication;
import opencellar.application.IWindow;
import opencellar.application.WindowType;
import opencellar.framework.Cellar;
import opencellar.framework.CellarObject;
import opencellar.framework.ObjectType;
import opencellar.framework.Wine;

public final class NewWineCommand
extends Command {
    protected NewWineCommand(IApplication app) {
        super(app);
    }

    public void execute() {
        if (super.getApp().activeCellar() == null) {
            super.showWarningMessage(this.getApp().getRS("MSG_NULL_CELLAR"));
            return;
        }
        this.getApp().setCursor(true);
        Wine wine = (Wine)this.getApp().activeCellar().createItem(ObjectType.Wine);
        wine.setName("Nouveau vin");
        IWindow window = this.getApp().createWindow(WindowType.Wine, new Object[]{wine});
        if (window != null) {
            window.show();
        }
        this.getApp().setCursor(false);
    }
}

