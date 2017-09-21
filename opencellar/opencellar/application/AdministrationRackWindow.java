/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Cellar
 */
package opencellar.application;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JInternalFrame;
import opencellar.application.IApplication;
import opencellar.application.ICellarDependancy;
import opencellar.application.IWindowListener;
import opencellar.application.Window;
import opencellar.application.WindowPositionSaver2;
import opencellar.application.WindowType;
import opencellar.framework.Cellar;
import opencellar.ui.RackEditor;

public class AdministrationRackWindow
extends Window
implements ICellarDependancy {
    private RackEditor m_editor;
    private WindowPositionSaver2 m_saver;

    public AdministrationRackWindow(IApplication application, IWindowListener systemListener) {
        super(application, systemListener);
    }

    protected final void onCreateFrame() {
        this.m_editor = new RackEditor();
        this.m_editor.performTranslation(this.getApplication());
        this.m_editor.setCellar(this.getApplication().activeCellar());
        this.getFrame().add(this.m_editor);
        this.getFrame().setClosable(true);
        this.getFrame().setIconifiable(true);
        this.getFrame().setResizable(true);
        this.getFrame().setMinimumSize(new Dimension(800, 580));
        this.getFrame().setSize(new Dimension(800, 580));
        this.setCaption(this.getApplication().getRS("ADM_PGE_RACK"));
        this.m_saver = new WindowPositionSaver2(this.getApplication(), this.getFrame(), "AdminRackWindow");
        this.m_saver.load();
    }

    protected void onClosing() {
        this.m_saver.save();
    }

    public WindowType getType() {
        return WindowType.RackAdministration;
    }
}

