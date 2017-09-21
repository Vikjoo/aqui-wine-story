/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Cellar
 *  opencellar.framework.Rack
 *  opencellar.framework.Wine
 */
package opencellar.application;

import opencellar.application.AdministrationListWindow;
import opencellar.application.AdministrationRackWindow;
import opencellar.application.ConfirmDialogWindow;
import opencellar.application.GridWinesWindow;
import opencellar.application.IApplication;
import opencellar.application.IRackWindow;
import opencellar.application.IWindow;
import opencellar.application.IWindowListener;
import opencellar.application.IWineWindow;
import opencellar.application.NavigatorWindow;
import opencellar.application.NewCellarWindow;
import opencellar.application.RackWindow;
import opencellar.application.ScriptWindow;
import opencellar.application.TestWindow;
import opencellar.application.WelcomeWindow;
import opencellar.application.WindowCollection;
import opencellar.application.WindowType;
import opencellar.application.WineWindow;
import opencellar.framework.Cellar;
import opencellar.framework.Rack;
import opencellar.framework.Wine;

final class WindowFactory {
    private static IWindow confirmWindow = null;

    WindowFactory() {
    }

    protected static final /* varargs */ IWindow create(IApplication app, WindowType wt, IWindowListener systemListener, Object ... args) {
        IWindow window = null;
        if (wt == WindowType.Test) {
            window = new TestWindow(app, systemListener);
        } else if (wt == WindowType.NewCellar) {
            window = new NewCellarWindow(app);
        } else if (wt == WindowType.Navigator) {
            window = new NavigatorWindow(app);
        } else if (wt == WindowType.Wine) {
            window = WindowFactory.getWineWindow(app, systemListener, args);
        } else if (wt == WindowType.Rack) {
            window = WindowFactory.getRackWindow(app, systemListener, args);
        } else if (wt == WindowType.ListAdministration && app.activeCellar() != null) {
            window = app.getWindows().getOne(WindowType.ListAdministration);
            if (window == null) {
                window = new AdministrationListWindow(app, systemListener);
            }
        } else if (wt == WindowType.Script) {
            window = app.getWindows().getOne(WindowType.Script);
            if (window == null) {
                window = new ScriptWindow(app, systemListener);
            }
        } else if (wt == WindowType.Welcome) {
            window = app.getWindows().getOne(WindowType.Welcome);
            if (window == null) {
                window = new WelcomeWindow(app, systemListener);
            }
        } else if (wt == WindowType.RackAdministration && app.activeCellar() != null) {
            window = app.getWindows().getOne(WindowType.RackAdministration);
            if (window == null) {
                window = new AdministrationRackWindow(app, systemListener);
            }
        } else if (wt == WindowType.WineList && app.activeCellar() != null) {
            window = app.getWindows().getOne(WindowType.WineList);
            if (window == null) {
                window = new GridWinesWindow(app, systemListener);
            }
        } else if (wt == WindowType.Confirm) {
            if (confirmWindow == null) {
                confirmWindow = new ConfirmDialogWindow(app);
            }
            return confirmWindow;
        }
        return window;
    }

    private static /* varargs */ IWindow getWineWindow(IApplication app, IWindowListener systemListener, Object ... args) {
        IWindow window = null;
        if (app.activeCellar() == null) {
            return null;
        }
        if (args == null) {
            return null;
        }
        if (args.length == 1 && args[0] instanceof Wine) {
            Wine wine = (Wine)args[0];
            WindowCollection wc = app.getWindows().get(WindowType.Wine);
            for (int i = 0; i < wc.size(); ++i) {
                IWineWindow wineWindow = (IWineWindow)((Object)wc.get(i));
                if (wineWindow.getWine() != wine) continue;
                window = wc.get(i);
                break;
            }
            if (window == null) {
                window = new WineWindow(app, wine, systemListener);
            }
        }
        return window;
    }

    private static /* varargs */ IWindow getRackWindow(IApplication app, IWindowListener systemListener, Object ... args) {
        IWindow window = null;
        if (app.activeCellar() == null) {
            return null;
        }
        if (args.length == 1 && args[0] instanceof Rack) {
            Rack rack = (Rack)args[0];
            WindowCollection wc = app.getWindows().get(WindowType.Rack);
            for (int i = 0; i < wc.size(); ++i) {
                IRackWindow rackWindow = (IRackWindow)((Object)wc.get(i));
                if (rackWindow.getRack() != rack) continue;
                window = wc.get(i);
                break;
            }
            if (window == null) {
                window = new RackWindow(app, rack, systemListener);
            }
        } else {
            window = new RackWindow(app, null, systemListener);
        }
        return window;
    }
}

