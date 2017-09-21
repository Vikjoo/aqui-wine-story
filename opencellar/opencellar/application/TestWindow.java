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

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JInternalFrame;
import opencellar.application.IApplication;
import opencellar.application.IWindowListener;
import opencellar.application.Window;
import opencellar.application.WindowType;
import opencellar.framework.Cellar;
import opencellar.framework.CellarObject;
import opencellar.framework.ObjectType;
import opencellar.framework.Wine;
import opencellar.ui.testLayer;

public class TestWindow
extends Window {
    public TestWindow(IApplication application, IWindowListener systemListener) {
        super(application, systemListener);
    }

    protected final void onCreateFrame() {
        this.getFrame().setClosable(true);
        this.getFrame().setIconifiable(true);
        this.getFrame().setResizable(true);
        this.getFrame().setSize(585, 590);
        this.getFrame().setPreferredSize(new Dimension(585, 590));
        this.setCaption("Un texte pour voir");
        testLayer test = new testLayer();
        test.init(this.getApplication(), (Wine)this.getApplication().activeCellar().createItem(ObjectType.Wine));
        this.getFrame().add(test);
        this.getFrame().pack();
    }

    public WindowType getType() {
        return WindowType.Test;
    }
}

