/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Cellar
 *  opencellar.framework.CellarObject
 *  opencellar.framework.ObjectType
 *  opencellar.framework.Rack
 *  opencellar.framework.RackComparer
 *  opencellar.framework.Wine
 *  org.jdesktop.layout.GroupLayout
 *  org.jdesktop.layout.GroupLayout$Group
 *  org.jdesktop.layout.GroupLayout$ParallelGroup
 *  org.jdesktop.layout.GroupLayout$SequentialGroup
 */
package opencellar.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.InputStream;
import java.util.Comparator;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import opencellar.application.DefaultCommandBarManager;
import opencellar.application.IApplication;
import opencellar.application.ICellarRenderer;
import opencellar.framework.Cellar;
import opencellar.framework.CellarObject;
import opencellar.framework.ObjectType;
import opencellar.framework.Rack;
import opencellar.framework.RackComparer;
import opencellar.framework.Wine;
import opencellar.ui.OCComboBox;
import opencellar.ui.RackBuilderComponent;
import opencellar.ui.RackSelectorComponent;
import opencellar.ui.UIHelper;
import org.jdesktop.layout.GroupLayout;

public final class RackSelector
extends JDialog
implements ItemListener,
ActionListener {
    private RackSelectorComponent m_selector;
    private RackBuilderComponent builderComponent;
    private IApplication m_app;
    private Wine m_wine;
    private JButton apply;
    private JPanel bottomPane;
    private JPanel centerPane;
    private JPanel commandBarPane;
    private JPanel commandPane;
    private JPanel contentPane;
    private JLabel legend;
    private JLabel racksLabel;
    private OCComboBox racksList;

    public RackSelector(Frame owner) {
        super(owner, true);
        this.initComponents();
        this.init();
    }

    private void init() {
        this.racksList.addItemListener(this);
        try {
            this.legend.setIcon(new ImageIcon(ImageIO.read(RackSelector.class.getResourceAsStream("/opencellar/rc/misc/selectlegend.gif"))));
        }
        catch (Exception ex) {
            // empty catch block
        }
    }

    public final void setDataSource(IApplication app, Rack rack, Wine wine) {
        if (app == null) {
            throw new IllegalArgumentException("app == null");
        }
        this.m_app = app;
        if (wine == null) {
            throw new IllegalArgumentException("wine == null");
        }
        this.m_wine = wine;
        DefaultCommandBarManager defaultBar = new DefaultCommandBarManager(this.commandBarPane, this.m_app);
        this.performTranslation();
        this.loadRacks(rack);
    }

    private void performTranslation() {
        this.apply.setText(this.m_app.getRS("BTN_APPLY"));
        this.apply.addActionListener(this);
        this.racksLabel.setText(this.m_app.getRS("RACK_TITLE"));
        super.setTitle(this.m_app.getRS("WIN_RCK_CHOOSE_ITEMS_TITLE"));
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == this.racksList) {
            this.ensureRackSelectorCreated();
            this.m_selector.setDatasource((Rack)e.getItem(), this.m_wine);
        }
    }

    private void ensureRackSelectorCreated() {
        if (this.m_selector == null) {
            this.m_selector = new RackSelectorComponent();
            this.m_selector.setRenderer(this.m_app.createRenderer());
            this.contentPane.setLayout(new BorderLayout());
            this.contentPane.add((Component)this.m_selector, "Center");
        }
    }

    public final JPanel getCommandBarContainer() {
        return this.commandBarPane;
    }

    private void loadRacks(Rack selected) {
        UIHelper.pushItems(this.racksList, ObjectType.Rack, this.m_app.activeCellar(), (Object)null, (CellarObject)selected, (Comparator)new RackComparer());
        if (this.racksList.getItemCount() > 0 && this.racksList.getSelectedIndex() == -1) {
            this.racksList.setSelectedIndex(0);
        } else if (this.racksList.getItemCount() == 0) {
            this.apply.setEnabled(false);
        }
    }

    private void initComponents() {
        this.commandPane = new JPanel();
        this.commandBarPane = new JPanel();
        this.racksLabel = new JLabel();
        this.racksList = new OCComboBox();
        this.centerPane = new JPanel();
        this.contentPane = new JPanel();
        this.bottomPane = new JPanel();
        this.apply = new JButton();
        this.legend = new JLabel();
        this.commandPane.setPreferredSize(new Dimension(100, 90));
        GroupLayout commandBarPaneLayout = new GroupLayout((Container)this.commandBarPane);
        this.commandBarPane.setLayout((LayoutManager)commandBarPaneLayout);
        commandBarPaneLayout.setHorizontalGroup((GroupLayout.Group)commandBarPaneLayout.createParallelGroup(1).add(0, 491, 32767));
        commandBarPaneLayout.setVerticalGroup((GroupLayout.Group)commandBarPaneLayout.createParallelGroup(1).add(0, 50, 32767));
        this.racksLabel.setText("racks");
        GroupLayout commandPaneLayout = new GroupLayout((Container)this.commandPane);
        this.commandPane.setLayout((LayoutManager)commandPaneLayout);
        commandPaneLayout.setHorizontalGroup((GroupLayout.Group)commandPaneLayout.createParallelGroup(1).add((GroupLayout.Group)commandPaneLayout.createSequentialGroup().add(19, 19, 19).add((Component)this.racksLabel, -2, 99, -2).addPreferredGap(0).add((Component)this.racksList, -2, 260, -2).add(109, 109, 109)).add((Component)this.commandBarPane, -1, -1, 32767));
        commandPaneLayout.setVerticalGroup((GroupLayout.Group)commandPaneLayout.createParallelGroup(1).add(2, (GroupLayout.Group)commandPaneLayout.createSequentialGroup().add((Component)this.commandBarPane, -2, -1, -2).addPreferredGap(0, 7, 32767).add((GroupLayout.Group)commandPaneLayout.createParallelGroup(3).add((Component)this.racksLabel).add((Component)this.racksList, -2, -1, -2)).addContainerGap()));
        this.getContentPane().add((Component)this.commandPane, "North");
        this.contentPane.setLayout(new BorderLayout());
        this.contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        GroupLayout centerPaneLayout = new GroupLayout((Container)this.centerPane);
        this.centerPane.setLayout((LayoutManager)centerPaneLayout);
        centerPaneLayout.setHorizontalGroup((GroupLayout.Group)centerPaneLayout.createParallelGroup(1).add((GroupLayout.Group)centerPaneLayout.createSequentialGroup().addContainerGap().add((Component)this.contentPane, -1, 471, 32767).addContainerGap()));
        centerPaneLayout.setVerticalGroup((GroupLayout.Group)centerPaneLayout.createParallelGroup(1).add((GroupLayout.Group)centerPaneLayout.createSequentialGroup().addContainerGap().add((Component)this.contentPane, -1, 270, 32767).addContainerGap()));
        this.getContentPane().add((Component)this.centerPane, "Center");
        this.bottomPane.setPreferredSize(new Dimension(100, 80));
        this.apply.setText("apply");
        GroupLayout bottomPaneLayout = new GroupLayout((Container)this.bottomPane);
        this.bottomPane.setLayout((LayoutManager)bottomPaneLayout);
        bottomPaneLayout.setHorizontalGroup((GroupLayout.Group)bottomPaneLayout.createParallelGroup(1).add((GroupLayout.Group)bottomPaneLayout.createSequentialGroup().addContainerGap().add((Component)this.legend, -2, 279, -2).addPreferredGap(0, 73, 32767).add((Component)this.apply, -2, 109, -2).add(20, 20, 20)));
        bottomPaneLayout.setVerticalGroup((GroupLayout.Group)bottomPaneLayout.createParallelGroup(1).add(2, (GroupLayout.Group)bottomPaneLayout.createSequentialGroup().addContainerGap().add((GroupLayout.Group)bottomPaneLayout.createParallelGroup(2).add((Component)this.apply).add((Component)this.legend, -1, 58, 32767)).addContainerGap()));
        this.getContentPane().add((Component)this.bottomPane, "South");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.apply) {
            UIHelper.findAndCloseDialog(this);
        }
    }
}

