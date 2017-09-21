/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Cellar
 *  opencellar.framework.CellarObjectCollection
 *  opencellar.framework.ObjectType
 *  opencellar.framework.Wine
 *  org.jdesktop.layout.GroupLayout
 *  org.jdesktop.layout.GroupLayout$Group
 *  org.jdesktop.layout.GroupLayout$ParallelGroup
 *  org.jdesktop.layout.GroupLayout$SequentialGroup
 */
package opencellar.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import opencellar.application.FolderType;
import opencellar.application.IApplication;
import opencellar.application.IRackWindow;
import opencellar.application.IWindow;
import opencellar.application.JTableColumnSaver;
import opencellar.application.WindowCollection;
import opencellar.application.WindowType;
import opencellar.application.WineCollection;
import opencellar.application.WineTableModel;
import opencellar.framework.Cellar;
import opencellar.framework.CellarObjectCollection;
import opencellar.framework.ObjectType;
import opencellar.framework.Wine;
import opencellar.ui.TableSorter;
import opencellar.ui.UIHelper;
import org.jdesktop.layout.GroupLayout;

public final class WineGridLayer
extends JPanel
implements ListSelectionListener {
    private IApplication m_app;
    private JTable table;
    private TableSorter sorter;
    private JTableColumnSaver saver;
    private String m_wineId = null;
    private JButton allCellar;
    private JPanel commandBarPane;
    private JPanel contentPane;
    private JButton search;
    private JTextField searchText;
    private JPanel topPane;

    public WineGridLayer() {
        this.initComponents();
    }

    public final void init(IApplication app) {
        if (app == null) {
            throw new IllegalArgumentException("app == null");
        }
        this.m_app = app;
        this.performTranslation();
        this.buildJTable();
    }

    private void performTranslation() {
        this.search.setVisible(false);
        this.searchText.setVisible(false);
        this.allCellar.setVisible(false);
    }

    public final String getSearchText() {
        return this.searchText.getText();
    }

    public final void search(String query, boolean innerSearch) {
    }

    public final WineCollection getWines() {
        return null;
    }

    public final void setSource(WineCollection wines) {
        ((WineTableModel)this.sorter.tableModel).setSource(wines);
    }

    private void buildJTable() {
        this.sorter = new TableSorter(new WineTableModel(this.buildDefault(), this.m_app));
        this.table = new JTable(this.sorter);
        this.sorter.setTableHeader(this.table.getTableHeader());
        this.contentPane.setLayout(new BorderLayout());
        this.table.getTableHeader().setToolTipText("-");
        this.table.setAutoResizeMode(0);
        this.table.setSelectionMode(0);
        TableColumn sysId = this.table.getColumnModel().getColumn(0);
        this.table.getColumnModel().removeColumn(sysId);
        for (int i = 0; i < this.table.getColumnCount(); ++i) {
            this.table.getColumnModel().getColumn(i).setMinWidth(30);
        }
        this.saver = new JTableColumnSaver(this.table, this.m_app.getPath(FolderType.Root) + "grid.xml");
        this.saver.load();
        this.table.getSelectionModel().addListSelectionListener(this);
        this.table.addMouseListener(new MouseListener(){

            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    WineGridLayer.this.showWineWindow();
                }
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }
        });
        JScrollPane scrollPane = new JScrollPane(this.table);
        scrollPane.setHorizontalScrollBarPolicy(32);
        scrollPane.setVerticalScrollBarPolicy(22);
        this.contentPane.add((Component)scrollPane, "Center");
    }

    private void showWineWindow() {
        if (this.m_wineId != null) {
            IWindow wineWindow;
            UIHelper.setCursor(this, true);
            Wine w = ((WineTableModel)this.sorter.getTableModel()).getWineAt(this.m_wineId);
            if (w != null && (wineWindow = this.m_app.createWindow(WindowType.Wine, new Object[]{w})) != null) {
                wineWindow.show();
            }
            UIHelper.setCursor(this, false);
        }
    }

    public void valueChanged(ListSelectionEvent e) {
        int rowIndex;
        this.m_wineId = null;
        if (!e.getValueIsAdjusting() && (rowIndex = this.table.getSelectedRow()) > -1) {
            TableModel tm = this.table.getModel();
            this.m_wineId = (String)tm.getValueAt(rowIndex, 0);
            this.highLightWine();
        }
    }

    private WineCollection buildDefault() {
        return new WineCollection(this.m_app.activeCellar().getCollection(ObjectType.Wine));
    }

    public final JPanel getCommandBarPane() {
        return this.commandBarPane;
    }

    public final void close() {
        this.saver.save();
    }

    private final void highLightWine() {
        Wine w;
        if (this.m_wineId != null && (w = ((WineTableModel)this.sorter.getTableModel()).getWineAt(this.m_wineId)) != null) {
            WindowCollection wc = this.m_app.getWindows().get(WindowType.Rack);
            for (int i = 0; i < wc.size(); ++i) {
                IRackWindow win = (IRackWindow)((Object)wc.get(i));
                win.setActiveWine(w);
            }
        }
    }

    public final String getTableString() {
        StringBuilder builder = new StringBuilder();
        String newline = System.getProperty("line.separator");
        TableModel model = this.table.getModel();
        int columns = model.getColumnCount();
        for (int i = 1; i < model.getColumnCount(); ++i) {
            builder.append("\"" + model.getColumnName(i) + "\";");
        }
        builder.append(newline);
        for (int j = 0; j < model.getRowCount(); ++j) {
            for (int i = 1; i < columns; ++i) {
                builder.append("\"" + model.getValueAt(j, i) + "\";");
            }
            builder.append(newline);
        }
        return builder.toString();
    }

    private void initComponents() {
        this.topPane = new JPanel();
        this.commandBarPane = new JPanel();
        this.searchText = new JTextField();
        this.search = new JButton();
        this.allCellar = new JButton();
        this.contentPane = new JPanel();
        this.setLayout(new BorderLayout());
        this.topPane.setPreferredSize(new Dimension(100, 40));
        GroupLayout commandBarPaneLayout = new GroupLayout((Container)this.commandBarPane);
        this.commandBarPane.setLayout((LayoutManager)commandBarPaneLayout);
        commandBarPaneLayout.setHorizontalGroup((GroupLayout.Group)commandBarPaneLayout.createParallelGroup(1).add(0, 565, 32767));
        commandBarPaneLayout.setVerticalGroup((GroupLayout.Group)commandBarPaneLayout.createParallelGroup(1).add(0, 31, 32767));
        this.search.setText("rechercher");
        this.search.setEnabled(false);
        this.allCellar.setText("toute ma cave");
        this.allCellar.setEnabled(false);
        GroupLayout topPaneLayout = new GroupLayout((Container)this.topPane);
        this.topPane.setLayout((LayoutManager)topPaneLayout);
        topPaneLayout.setHorizontalGroup((GroupLayout.Group)topPaneLayout.createParallelGroup(1).add(2, (GroupLayout.Group)topPaneLayout.createSequentialGroup().addContainerGap().add((Component)this.searchText, -1, 289, 32767).addPreferredGap(0).add((Component)this.search, -2, 110, -2).add(8, 8, 8).add((Component)this.allCellar, -2, 132, -2).addContainerGap()).add((Component)this.commandBarPane, -1, -1, 32767));
        topPaneLayout.setVerticalGroup((GroupLayout.Group)topPaneLayout.createParallelGroup(1).add((GroupLayout.Group)topPaneLayout.createSequentialGroup().add((Component)this.commandBarPane, -2, -1, -2).addPreferredGap(0).add((GroupLayout.Group)topPaneLayout.createParallelGroup(3).add((Component)this.searchText, -2, -1, -2).add((Component)this.search).add((Component)this.allCellar)).addContainerGap(-1, 32767)));
        this.add((Component)this.topPane, "North");
        GroupLayout contentPaneLayout = new GroupLayout((Container)this.contentPane);
        this.contentPane.setLayout((LayoutManager)contentPaneLayout);
        contentPaneLayout.setHorizontalGroup((GroupLayout.Group)contentPaneLayout.createParallelGroup(1).add(0, 565, 32767));
        contentPaneLayout.setVerticalGroup((GroupLayout.Group)contentPaneLayout.createParallelGroup(1).add(0, 299, 32767));
        this.add((Component)this.contentPane, "Center");
    }

}

