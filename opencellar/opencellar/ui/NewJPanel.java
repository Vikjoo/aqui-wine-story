/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.netbeans.lib.awtextra.AbsoluteConstraints
 *  org.netbeans.lib.awtextra.AbsoluteLayout
 */
package opencellar.ui;

import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import opencellar.ui.OCComboBox;
import opencellar.ui.OCTitle;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

public class NewJPanel
extends JPanel {
    private JLabel jLabel1;
    private JScrollPane jScrollPane1;
    private JSeparator jSeparator1;
    private JSlider jSlider1;
    private JTabbedPane jTabbedPane1;
    private JTextArea jTextArea1;
    private JToggleButton jToggleButton1;
    private JToggleButton jToggleButton2;
    private JToolBar jToolBar1;
    private JToolBar jToolBar2;
    private OCComboBox oCComboBox1;
    private OCTitle oCTitle1;

    public NewJPanel() {
        this.initComponents();
    }

    private void initComponents() {
        this.jToggleButton1 = new JToggleButton();
        this.jSeparator1 = new JSeparator();
        this.jLabel1 = new JLabel();
        this.jToolBar1 = new JToolBar();
        this.jToolBar2 = new JToolBar();
        this.jSlider1 = new JSlider();
        this.jToggleButton2 = new JToggleButton();
        this.jTabbedPane1 = new JTabbedPane();
        this.oCComboBox1 = new OCComboBox();
        this.jScrollPane1 = new JScrollPane();
        this.jTextArea1 = new JTextArea();
        this.oCTitle1 = new OCTitle();
        this.jToggleButton1.setText("jToggleButton1");
        this.setLayout((LayoutManager)new AbsoluteLayout());
        this.add((Component)this.jSeparator1, (Object)new AbsoluteConstraints(75, 16, -1, -1));
        this.jLabel1.setText("jLabel1");
        this.add((Component)this.jLabel1, (Object)new AbsoluteConstraints(20, 74, -1, 20));
        this.jToolBar1.setFloatable(false);
        this.add((Component)this.jToolBar1, (Object)new AbsoluteConstraints(80, 16, -1, -1));
        this.jToolBar2.setFloatable(false);
        this.add((Component)this.jToolBar2, (Object)new AbsoluteConstraints(87, 16, -1, -1));
        this.add((Component)this.jSlider1, (Object)new AbsoluteConstraints(140, 180, -1, -1));
        this.jToggleButton2.setText("jToggleButton2");
        this.add((Component)this.jToggleButton2, (Object)new AbsoluteConstraints(150, 20, -1, -1));
        this.add((Component)this.jTabbedPane1, (Object)new AbsoluteConstraints(359, 15, -1, -1));
        this.oCComboBox1.setModel(new DefaultComboBoxModel<String>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
        this.oCComboBox1.setAlignmentX(1.0f);
        this.add((Component)this.oCComboBox1, (Object)new AbsoluteConstraints(45, 20, 90, -1));
        this.jScrollPane1.setHorizontalScrollBarPolicy(31);
        this.jTextArea1.setColumns(25);
        this.jTextArea1.setLineWrap(true);
        this.jTextArea1.setRows(5);
        this.jScrollPane1.setViewportView(this.jTextArea1);
        this.add((Component)this.jScrollPane1, (Object)new AbsoluteConstraints(90, 70, -1, 40));
        this.oCTitle1.setText("Je suis un titre !");
        this.add((Component)this.oCTitle1, (Object)new AbsoluteConstraints(30, 130, 250, 20));
    }
}

