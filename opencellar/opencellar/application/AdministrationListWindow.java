/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Cellar
 *  opencellar.framework.ObjectType
 */
package opencellar.application;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JInternalFrame;
import opencellar.application.IApplication;
import opencellar.application.ICellarDependancy;
import opencellar.application.IWindowListener;
import opencellar.application.Window;
import opencellar.application.WindowType;
import opencellar.framework.Cellar;
import opencellar.framework.ObjectType;
import opencellar.ui.CellarObjectEditor;

public class AdministrationListWindow
extends Window
implements ICellarDependancy {
    private CellarObjectEditor m_editor;

    public AdministrationListWindow(IApplication application, IWindowListener systemListener) {
        super(application, systemListener);
    }

    protected final void onCreateFrame() {
        this.m_editor = new CellarObjectEditor();
        this.m_editor.performTranslation(this.getApplication());
        this.m_editor.setCellar(this.getApplication().activeCellar());
        this.getFrame().add(this.m_editor);
        this.getFrame().setClosable(true);
        this.getFrame().setIconifiable(true);
        this.getFrame().setResizable(true);
        this.getFrame().setMinimumSize(new Dimension(630, 500));
        this.getFrame().setSize(new Dimension(630, 500));
        this.getFrame().setPreferredSize(new Dimension(630, 500));
        this.getFrame().pack();
        this.getFrame().setResizable(false);
        this.setCaption(this.getApplication().getRS("ADM_PGE_LIST"));
        this.m_editor.setActiveEditor(ObjectType.Country);
    }

    public void show() {
        super.show();
    }

    public WindowType getType() {
        return WindowType.ListAdministration;
    }
}

