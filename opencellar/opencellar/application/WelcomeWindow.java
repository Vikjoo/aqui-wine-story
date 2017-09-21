/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameListener;
import opencellar.application.Application;
import opencellar.application.IApplication;
import opencellar.application.IWelcomeWindow;
import opencellar.application.IWindowListener;
import opencellar.application.Window;
import opencellar.application.WindowType;
import opencellar.ui.MdiDesktopPane;
import opencellar.ui.WelcomeLayer;

public class WelcomeWindow
extends Window
implements IWelcomeWindow {
    protected WelcomeWindow(IApplication application, IWindowListener systemListener) {
        super(application, systemListener);
    }

    protected void createFrame() {
        this.frame = new WelcomeLayer(this.getApplication());
        this.frame.addInternalFrameListener(this);
        this.frame.setClosable(true);
        this.frame.setSize(new Dimension(620, 400));
        this.frame.setMinimumSize(new Dimension(620, 400));
        this.frame.setTitle(this.getApplication().getRS("WEL_PGE"));
        Rectangle rect = this.getParentBounds();
        Point newLoc = new Point(0, 0);
        newLoc.x = rect.width / 2 - 310;
        newLoc.y = rect.height / 2 - 180;
        this.frame.setLocation(newLoc);
        ((Application)this.getApplication()).getDesktop().add(this.frame);
    }

    public boolean getShowAgain() {
        return ((WelcomeLayer)this.frame).getOption();
    }

    public void setShowAgain(boolean b) {
        ((WelcomeLayer)this.frame).setOption(b);
    }

    public WindowType getType() {
        return WindowType.Welcome;
    }

    protected void onClosing() {
    }
}

