/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import opencellar.application.CommandBarManager;
import opencellar.application.IApplication;
import opencellar.application.IScriptWindow;
import opencellar.application.IWindow;
import opencellar.application.IWindowListener;
import opencellar.application.ScriptCommandBarManager;
import opencellar.application.Window;
import opencellar.application.WindowPositionSaver2;
import opencellar.application.WindowType;
import opencellar.ui.ScriptLayer;

public class ScriptWindow
extends Window
implements IScriptWindow {
    private CommandBarManager m_manager;
    private ScriptLayer layer;
    private WindowPositionSaver2 m_saver;

    public ScriptWindow(IApplication application, IWindowListener systemListener) {
        super(application, systemListener);
        this.setDataSource();
    }

    public boolean supportCommandBars() {
        return true;
    }

    public CommandBarManager getCommandBars() {
        return this.m_manager;
    }

    private final void setDataSource() {
        this.layer.setApplication(this.getApplication());
        this.m_manager = new ScriptCommandBarManager(this.layer.getCommandBarContainer(), this);
    }

    protected final void onCreateFrame() {
        this.getFrame().setClosable(true);
        this.getFrame().setIconifiable(true);
        this.getFrame().setResizable(true);
        this.getFrame().setMinimumSize(new Dimension(530, 430));
        this.getFrame().setPreferredSize(new Dimension(530, 430));
        this.setCaption(this.getApplication().getRS("SCRIPT_TITLE"));
        this.layer = new ScriptLayer();
        this.getFrame().add(this.layer);
        this.getFrame().pack();
        this.m_saver = new WindowPositionSaver2(this.getApplication(), this.getFrame(), "ScriptWindow");
        this.m_saver.load();
    }

    protected void onClosing() {
        this.m_saver.save();
    }

    public final WindowType getType() {
        return WindowType.Script;
    }

    public void setScript(String s) {
        this.layer.setString(s);
    }

    public String getScript() {
        return this.layer.getScript();
    }

    public void execute() {
        this.layer.execute();
    }
}

