/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import opencellar.application.CommandBarManager;
import opencellar.application.GridWinesCommandBarManager;
import opencellar.application.IApplication;
import opencellar.application.ICellarDependancy;
import opencellar.application.IGridWinesWindow;
import opencellar.application.IWindow;
import opencellar.application.IWindowListener;
import opencellar.application.Window;
import opencellar.application.WindowPositionSaver2;
import opencellar.application.WindowType;
import opencellar.application.WineCollection;
import opencellar.ui.WineGridLayer;

public final class GridWinesWindow
extends Window
implements IGridWinesWindow,
ICellarDependancy {
    private CommandBarManager m_manager;
    private WineGridLayer layer;
    private WindowPositionSaver2 m_saver;

    protected GridWinesWindow(IApplication application, IWindowListener systemListener) {
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
        this.layer.init(this.getApplication());
        this.m_manager = new GridWinesCommandBarManager(this.layer.getCommandBarPane(), this);
    }

    protected final void onCreateFrame() {
        this.getFrame().setClosable(true);
        this.getFrame().setIconifiable(true);
        this.getFrame().setResizable(true);
        this.getFrame().setSize(585, 590);
        this.getFrame().setPreferredSize(new Dimension(585, 590));
        this.setCaption(this.getApplication().getRS("ID_TEXT_CELLAR"));
        this.layer = new WineGridLayer();
        this.getFrame().add(this.layer);
        this.getFrame().pack();
        this.m_saver = new WindowPositionSaver2(this.getApplication(), this.getFrame(), "GridWines");
        this.m_saver.load();
    }

    protected void onClosing() {
        this.m_saver.save();
        this.layer.close();
    }

    public WindowType getType() {
        return WindowType.WineList;
    }

    public void setDatasource(WineCollection wines) {
        this.layer.setSource(wines);
    }

    public WineCollection getWines() {
        return this.layer.getWines();
    }

    public String getSearchText() {
        return this.layer.getSearchText();
    }

    public void search(String query, boolean inner) {
        this.layer.search(query, inner);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void export(File dest) {
        String tableString = this.layer.getTableString();
        if (tableString != null && tableString.length() > 0) {
            FileOutputStream stream = null;
            try {
                stream = new FileOutputStream(dest, false);
                stream.write(tableString.getBytes());
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            finally {
                if (stream != null) {
                    try {
                        stream.close();
                    }
                    catch (Exception ex) {}
                }
            }
        }
    }
}

