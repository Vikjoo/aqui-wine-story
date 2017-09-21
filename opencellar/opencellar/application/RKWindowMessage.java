/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Cellar
 *  opencellar.framework.CellarObject
 *  opencellar.framework.CellarObjectCollection
 *  opencellar.framework.ObjectType
 *  opencellar.framework.Rack
 *  opencellar.framework.Wine
 *  org.jdom.Content
 *  org.jdom.Document
 *  org.jdom.Element
 *  org.jdom.input.SAXBuilder
 *  org.jdom.output.Format
 *  org.jdom.output.XMLOutputter
 */
package opencellar.application;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import opencellar.application.AppSettings;
import opencellar.application.Application;
import opencellar.application.CommandManager;
import opencellar.application.FolderType;
import opencellar.application.IApplication;
import opencellar.application.ICellarDependancy;
import opencellar.application.ICommand;
import opencellar.application.IGridWinesWindow;
import opencellar.application.IInternalApplicationListener;
import opencellar.application.IRackWindow;
import opencellar.application.IWindow;
import opencellar.application.IWineWindow;
import opencellar.application.RootKit;
import opencellar.application.SettingCollection;
import opencellar.application.WindowCollection;
import opencellar.application.WindowType;
import opencellar.application.utils;
import opencellar.framework.Cellar;
import opencellar.framework.CellarObject;
import opencellar.framework.CellarObjectCollection;
import opencellar.framework.ObjectType;
import opencellar.framework.Rack;
import opencellar.framework.Wine;
import org.jdom.Content;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class RKWindowMessage
extends RootKit
implements IInternalApplicationListener {
    public RKWindowMessage(IApplication application) {
        super(application);
    }

    public void initialize() {
        ((Application)this.getApp()).addInternalListener(this);
    }

    public void uninitialize() {
    }

    public void onStartUp(IApplication source) {
        String lastOpened = this.getApp().getSettings().get("Env").get("LastOpenedCellar", "");
        if (!lastOpened.equals("")) {
            this.getApp().open(lastOpened);
        }
    }

    public void onCellarOpening(IApplication source) {
    }

    public void onCellarOpened(IApplication source) {
        this.loadPrefs();
    }

    public void onCellarClosing(IApplication source) {
        this.savePrefs();
        this.getApp().getSettings().get("Env").set("LastOpenedCellar", this.getApp().activeCellar().getFilePath());
        ArrayList<IWindow> windows = new ArrayList<IWindow>();
        WindowCollection wc = this.getApp().getWindows();
        for (int i = 0; i < wc.size(); ++i) {
            IWindow win = wc.get(i);
            if (!(win instanceof ICellarDependancy)) continue;
            windows.add(win);
        }
        while (windows.size() > 0) {
            ((IWindow)windows.get(0)).close();
            windows.remove(0);
        }
        windows = null;
    }

    public void onCellarClosed(IApplication source) {
    }

    public void onShutDown(IApplication source) {
        this.closeAllWindows();
    }

    private void closeAllWindows() {
        WindowCollection wc = this.getApp().getWindows();
        while (wc.size() > 0) {
            wc.get(0).close();
        }
    }

    private void savePrefs() {
        this.save();
    }

    private final boolean save() {
        boolean success = false;
        try {
            Element root = new Element("Windows");
            Document document = new Document(root);
            WindowCollection wc = this.getApp().getWindows();
            for (int i = 0; i < wc.size(); ++i) {
                IWindow window = wc.get(i);
                if (window.getType() != WindowType.Wine && window.getType() != WindowType.Rack && window.getType() != WindowType.WineList) continue;
                Element win = new Element("Window");
                String type = window.getType().toString().substring(0, 1);
                String sysId = null;
                if (window instanceof IWineWindow) {
                    sysId = ((IWineWindow)((Object)window)).getWine().getSystemUid();
                } else if (window instanceof IRackWindow) {
                    Rack rack = ((IRackWindow)((Object)window)).getRack();
                    if (rack != null) {
                        sysId = rack.getSystemUid();
                    }
                } else if (window instanceof IGridWinesWindow) {
                    sysId = "0000";
                    type = "G";
                }
                if (sysId == null) continue;
                win.setAttribute("Type", type);
                win.setAttribute("X", String.valueOf(window.getLocation().x));
                win.setAttribute("Y", String.valueOf(window.getLocation().y));
                win.setAttribute("Width", String.valueOf(window.getSize().width));
                win.setAttribute("Height", String.valueOf(window.getSize().height));
                win.setAttribute("SysId", sysId);
                root.addContent((Content)win);
            }
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(document, (OutputStream)new FileOutputStream(this.getFileName()));
            success = true;
        }
        catch (Exception e) {
            this.getApp().writeDebug("wins::save::error");
            e.printStackTrace();
        }
        return success;
    }

    private String getFileName() {
        return this.getApp().getPath(FolderType.Prefs) + this.getApp().activeCellar().getSystemUid() + ".xml";
    }

    private void loadPrefs() {
        File f = new File(this.getFileName());
        if (!f.exists()) {
            if (this.getApp().getWindows().getWindowCount(WindowType.Rack) == 0) {
                this.getApp().getCommands().get("ShowRackWindow").execute();
            }
            return;
        }
        this.getApp().setCursor(true);
        SAXBuilder sxb = new SAXBuilder();
        Document document = null;
        try {
            document = sxb.build(f);
            Element root = document.getRootElement();
            List windows = root.getChildren("Window");
            for (Element current : windows) {
                String type = current.getAttributeValue("Type");
                String sysId = current.getAttributeValue("SysId");
                CellarObject co = null;
                if (type.equals("W")) {
                    co = this.getApp().activeCellar().getCollection(ObjectType.Wine).find(sysId);
                } else if (type.equals("R")) {
                    co = this.getApp().activeCellar().getCollection(ObjectType.Rack).find(sysId);
                } else if (type.equals("G")) {
                    // empty if block
                }
                if (co == null && !type.equals("G")) continue;
                int x = 0;
                int y = 0;
                int width = 0;
                int height = 0;
                x = utils.tryParse(current.getAttributeValue("X"), -1000);
                y = utils.tryParse(current.getAttributeValue("Y"), -1000);
                width = utils.tryParse(current.getAttributeValue("Width"), -1000);
                height = utils.tryParse(current.getAttributeValue("Height"), -1000);
                IWindow window = null;
                if (co instanceof Wine) {
                    window = this.getApp().createWindow(WindowType.Wine, new Object[]{co});
                } else if (co instanceof Rack) {
                    window = this.getApp().createWindow(WindowType.Rack, new Object[0]);
                    ((IRackWindow)((Object)window)).setRack((Rack)co);
                } else if (type.equals("G")) {
                    window = this.getApp().createWindow(WindowType.WineList, new Object[0]);
                }
                if (window == null) continue;
                window.setSize(new Dimension(width, height));
                window.setLocation(new Point(x, y));
                window.show();
            }
        }
        catch (Exception ex) {
            this.getApp().writeDebug("wins::load::error");
            ex.printStackTrace();
        }
        this.getApp().setCursor(false);
        if (this.getApp().getWindows().getWindowCount(WindowType.Rack) == 0) {
            this.getApp().getCommands().get("ShowRackWindow").execute();
        }
    }
}

