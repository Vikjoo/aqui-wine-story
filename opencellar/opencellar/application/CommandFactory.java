/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import opencellar.application.AboutCommand;
import opencellar.application.AdministrationListCommand;
import opencellar.application.AdministrationRackCommand;
import opencellar.application.CancelWineCommand;
import opencellar.application.CloseCellarCommand;
import opencellar.application.CommandManager;
import opencellar.application.DeleteRackCommand;
import opencellar.application.DeleteWineCommand;
import opencellar.application.ExcelCommand;
import opencellar.application.ExitCommand;
import opencellar.application.IApplication;
import opencellar.application.ICommand;
import opencellar.application.NewCellarCommand;
import opencellar.application.NewNoteCommand;
import opencellar.application.NewPurchaseSalesCommand;
import opencellar.application.NewWineCommand;
import opencellar.application.OnlineCommand;
import opencellar.application.OnlineContactCommand;
import opencellar.application.OnlineHelpCommand;
import opencellar.application.OpenCellarCommand;
import opencellar.application.RefreshGridCommand;
import opencellar.application.SaveWineCommand;
import opencellar.application.ShowCellarTextCommand;
import opencellar.application.ShowRackWindowCommand;
import opencellar.application.ShowScriptCommand;
import opencellar.application.ShowWelcomeCommand;
import opencellar.application.ShowWineRackItemsSelector;

public class CommandFactory {
    public static void build(CommandManager manager) {
        if (manager == null) {
            throw new IllegalArgumentException("manager");
        }
        IApplication mainApp = manager.getApp();
        if (mainApp == null) {
            throw new IllegalArgumentException("manager.getApp() == null");
        }
        manager.register("Exit", new ExitCommand(mainApp));
        manager.register("NewCellar", new NewCellarCommand(mainApp));
        manager.register("CloseCellar", new CloseCellarCommand(mainApp));
        manager.register("OpenCellar", new OpenCellarCommand(mainApp));
        manager.register("ListAdmin", new AdministrationListCommand(mainApp));
        manager.register("RackAdmin", new AdministrationRackCommand(mainApp));
        manager.register("NewWine", new NewWineCommand(mainApp));
        manager.register("CancelWine", new CancelWineCommand(mainApp));
        manager.register("SaveWine", new SaveWineCommand(mainApp));
        manager.register("ShowRackItems", new ShowWineRackItemsSelector(mainApp));
        manager.register("OnlineHelp", new OnlineHelpCommand(mainApp));
        manager.register("ShowRackWindow", new ShowRackWindowCommand(mainApp));
        manager.register("About", new AboutCommand(mainApp));
        manager.register("DeleteRack", new DeleteRackCommand(mainApp));
        manager.register("OnlineContact", new OnlineContactCommand(mainApp));
        manager.register("OnlineSite", new OnlineCommand(mainApp));
        manager.register("ShowCellarTextCommand", new ShowCellarTextCommand(mainApp));
        manager.register("ExcelCommand", new ExcelCommand(mainApp));
        manager.register("NewNote", new NewNoteCommand(mainApp));
        manager.register("NewPurchaseSales", new NewPurchaseSalesCommand(mainApp));
        manager.register("DeleteWine", new DeleteWineCommand(mainApp));
        manager.register("RefreshGridWines", new RefreshGridCommand(mainApp));
        manager.register("ShowWelcomePage", new ShowWelcomeCommand(mainApp));
        manager.register("ShowScript", new ShowScriptCommand(mainApp));
    }
}

