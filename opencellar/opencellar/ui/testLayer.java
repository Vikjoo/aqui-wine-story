/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Wine
 */
package opencellar.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.ListModel;
import opencellar.application.IApplication;
import opencellar.framework.Wine;
import opencellar.ui.WInformations;

public class testLayer
extends JPanel {
    private JButton jButton3;
    private JButton jButton4;
    private JList jList1;
    private JScrollPane jScrollPane1;
    private JTabbedPane jTabbedPane1;
    private JToolBar jToolBar1;

    public testLayer() {
        this.initComponents();
    }

    public void init(IApplication app, Wine w) {
        WInformations info = new WInformations();
        info.setDatasource(app, w);
        JPanel pane = new JPanel();
        this.jTabbedPane1.addTab("Informations", pane);
        pane.setBackground(Color.ORANGE);
        pane.setLayout(new BorderLayout());
        pane.add((Component)info, "Center");
    }

    private void initComponents() {
        this.jTabbedPane1 = new JTabbedPane();
        this.jScrollPane1 = new JScrollPane();
        this.jList1 = new JList();
        this.jToolBar1 = new JToolBar();
        this.jButton3 = new JButton();
        this.jButton4 = new JButton();
        this.setLayout(null);
        this.jTabbedPane1.setTabLayoutPolicy(1);
        this.jScrollPane1.setAutoscrolls(true);
        this.jList1.setModel(new AbstractListModel(){
            String[] strings;

            public int getSize() {
                return this.strings.length;
            }

            public Object getElementAt(int i) {
                return this.strings[i];
            }
        });
        this.jScrollPane1.setViewportView(this.jList1);
        this.jTabbedPane1.addTab("tab1", this.jScrollPane1);
        this.add(this.jTabbedPane1);
        this.jTabbedPane1.setBounds(10, 40, 550, 510);
        this.jToolBar1.setFloatable(false);
        this.jButton3.setText("jButton3");
        this.jToolBar1.add(this.jButton3);
        this.jButton4.setText("jButton4");
        this.jToolBar1.add(this.jButton4);
        this.add(this.jToolBar1);
        this.jToolBar1.setBounds(0, 0, 570, 30);
    }

}

