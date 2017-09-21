/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Cellar
 *  opencellar.framework.CellarObject
 *  opencellar.framework.ObjectType
 *  opencellar.framework.PurchaseSales
 *  opencellar.framework.Wine
 */
package opencellar.application;

import opencellar.application.Command;
import opencellar.application.IApplication;
import opencellar.application.IWindow;
import opencellar.application.IWineWindow;
import opencellar.application.WineDialogType;
import opencellar.framework.Cellar;
import opencellar.framework.CellarObject;
import opencellar.framework.ObjectType;
import opencellar.framework.PurchaseSales;
import opencellar.framework.Wine;

public class NewPurchaseSalesCommand
extends Command {
    public NewPurchaseSalesCommand(IApplication app) {
        super(app);
    }

    public void execute() {
        if (super.getApp().activeCellar() == null) {
            super.showWarningMessage(this.getApp().getRS("MSG_NULL_CELLAR"));
            return;
        }
        if (this.getApp().activeWindow() instanceof IWineWindow) {
            IWineWindow wineWindow = (IWineWindow)((Object)this.getApp().activeWindow());
            PurchaseSales sales = (PurchaseSales)this.getApp().activeCellar().createItem(ObjectType.PurchaseSales);
            sales.setWine(wineWindow.getWine());
            wineWindow.showDialog(WineDialogType.PurchaseSales, new Object[]{sales});
        } else {
            super.showWarningMessage("#debug : wineWindow -> null");
        }
    }
}

