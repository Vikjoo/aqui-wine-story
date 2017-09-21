/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.MisMatchEventArgs
 */
package opencellar.application;

import java.io.PrintStream;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import opencellar.application.IApplication;
import opencellar.application.IApplicationListener;
import opencellar.application.IRackWindow;
import opencellar.application.IWindow;
import opencellar.application.RootKit;
import opencellar.application.ScriptHost;
import opencellar.application.WindowType;
import opencellar.framework.MisMatchEventArgs;

public final class RKTest
extends RootKit
implements IApplicationListener {
    public RKTest(IApplication application) {
        super(application);
    }

    public void initialize() {
        StringBuilder builder = new StringBuilder();
        ScriptHost host = new ScriptHost(this.getApp(), builder.toString());
        int status = host.run();
    }

    public void uninitialize() {
    }

    public void onStarting(IApplication source) {
    }

    public void onStart(IApplication source) {
    }

    public void onShutDown(IApplication source) {
    }

    public void onOpening(IApplication source) {
    }

    public void onOpen(IApplication source) {
    }

    public void onClosing(IApplication source) {
    }

    public void onClose(IApplication source) {
    }

    public void onReadOnly(IApplication source) {
    }

    public void onMisMatchVersion(IApplication source, MisMatchEventArgs args) {
    }

    public void onCreateWindow(IApplication source, IWindow window) {
        if (window.getType() == WindowType.Rack) {
            IRackWindow rackWindow = (IRackWindow)((Object)window);
        }
    }

    public void onCloseWindow(IApplication source, IWindow window) {
        if (window.getType() == WindowType.Rack) {
            IRackWindow rackWindow = (IRackWindow)((Object)window);
        }
    }

    public void onActivateWindow(IApplication source, IWindow window) {
    }

    public void onRackItemLeave(IRackWindow source) {
        System.out.println("onRackItemLeave");
    }

    public void onRackItemEnter(IRackWindow source) {
        System.out.println("onRackItemEnter");
    }

    public void onPopup(IRackWindow source, JPopupMenu popup) {
        popup.add(new JMenuItem("Un test pour voir"));
    }
}

