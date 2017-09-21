/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Cellar
 *  opencellar.framework.CellarObject
 *  opencellar.framework.ObjectState
 *  opencellar.framework.ObjectType
 *  opencellar.framework.Rack
 *  opencellar.framework.RackBuilder
 *  opencellar.framework.RackComparer
 *  opencellar.framework.RackItem
 *  opencellar.framework.RackNamingType
 *  opencellar.framework.RackType
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
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.InputStream;
import java.util.Comparator;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import opencellar.application.DialogResult;
import opencellar.application.EmptyRack;
import opencellar.application.IApplication;
import opencellar.application.MessageButtonType;
import opencellar.application.MessageIconType;
import opencellar.application.MessageType;
import opencellar.framework.Cellar;
import opencellar.framework.CellarObject;
import opencellar.framework.ObjectState;
import opencellar.framework.ObjectType;
import opencellar.framework.Rack;
import opencellar.framework.RackBuilder;
import opencellar.framework.RackComparer;
import opencellar.framework.RackItem;
import opencellar.framework.RackNamingType;
import opencellar.framework.RackType;
import opencellar.ui.OCComboBox;
import opencellar.ui.RackBuilderComponent;
import opencellar.ui.UIHelper;
import org.jdesktop.layout.GroupLayout;

public class RackEditor
extends JPanel
implements ItemListener,
ChangeListener,
ActionListener {
    private RackBuilderComponent builderComponent;
    private Cellar m_cellar;
    private String deleteMessage = "";
    private String cannotDeleteMessage = "";
    private IApplication m_application;
    EmptyRack empty = new EmptyRack("\"Nouvel emplacement\"");
    private RackBuilder m_builder;
    static final int LIMIT = 27;
    private JSlider column;
    private JSlider columnInt;
    private JLabel columnIntLabel;
    private JLabel columnLabel;
    private JButton create;
    private JButton delete;
    private JPanel jPanel1;
    private OCComboBox legend;
    private JLabel legendLabel;
    private JLabel legendPic;
    private JLabel lineIntLabel;
    private JLabel lineLabel;
    private JPanel optionsLabel;
    private JSlider priority;
    private JLabel priorityLabel;
    private JTextField rackName;
    private OCComboBox racksList;
    private JSlider row;
    private JSlider rowInt;
    private JButton save;
    private JLabel title;
    private OCComboBox type;
    private JLabel typeLabel;
    private JPanel viewerPane;

    public RackEditor() {
        this.initComponents();
        this.init();
    }

    private void init() {
        this.racksList.addItemListener(this);
        this.row.addChangeListener(this);
        this.rowInt.addChangeListener(this);
        this.column.addChangeListener(this);
        this.columnInt.addChangeListener(this);
        this.create.addActionListener(this);
        this.save.addActionListener(this);
        this.delete.addActionListener(this);
        this.type.addItemListener(this);
        this.legend.addItemListener(this);
        this.builderComponent = new RackBuilderComponent();
        this.viewerPane.setLayout(new BorderLayout());
        this.viewerPane.add((Component)this.builderComponent, "Center");
        try {
            this.legendPic.setIcon(new ImageIcon(ImageIO.read(RackEditor.class.getResourceAsStream("/opencellar/rc/misc/rklegend.gif"))));
        }
        catch (Exception ex) {
            // empty catch block
        }
    }

    public final void setCellar(Cellar cellar) {
        if (cellar == null) {
            throw new IllegalArgumentException("cellar == null");
        }
        this.m_cellar = cellar;
        this.loadRacks(null);
    }

    public final void performTranslation(IApplication app) {
        this.title.setText(app.getRS("BTN_SELECT"));
        this.save.setText(app.getRS("BTN_SAVE"));
        this.create.setText(app.getRS("BTN_CREATE"));
        this.delete.setText(app.getRS("BTN_DELETE"));
        this.columnIntLabel.setText(app.getRS("ADM_RACK_SPACE"));
        this.lineIntLabel.setText(app.getRS("ADM_RACK_SPACE"));
        this.columnLabel.setText(app.getRS("ADM_RACK_COLUMN"));
        this.lineLabel.setText(app.getRS("ADM_RACK_ROW"));
        this.legendLabel.setText(app.getRS("ADM_RACK_LEGEND"));
        this.typeLabel.setText(app.getRS("ADM_RACK_TYPE"));
        this.priorityLabel.setText(app.getRS("ADM_RACK_PRIORITY"));
        ((TitledBorder)this.viewerPane.getBorder()).setTitle(app.getRS("ADM_RACK_PREVIEW"));
        String tipTitle = "<b>" + app.getRS("WIN_ADM_RACK_TIP_CAPTION") + "</b><br>";
        String content = app.getRS("ADM_RACK_TYPE6");
        this.priorityLabel.setToolTipText("<html>" + tipTitle + content + "</html>");
        this.deleteMessage = app.getRS("ADM_RACK_CONFIRM_DELETE");
        this.cannotDeleteMessage = app.getRS("ADM_RACK_CANNOT");
        this.m_application = app;
        UIHelper.push(app, this.legend, RackNamingType.BothLetter);
        UIHelper.push(app, this.type, RackType.Default);
    }

    private void loadRacks(Rack selected) {
        UIHelper.pushItems(this.racksList, ObjectType.Rack, this.m_cellar, (Object)this.empty, (CellarObject)selected, (Comparator)new RackComparer());
        if (this.racksList.getItemCount() > 1 && this.racksList.getSelectedIndex() == 0) {
            this.racksList.setSelectedIndex(1);
        }
    }

    private void createBuilder(Object source) {
        this.m_builder = source instanceof EmptyRack ? new RackBuilder(this.m_cellar) : new RackBuilder((Rack)source);
        this.setWorkingRack(this.m_builder.getRack());
    }

    private void setWorkingRack(Rack rack) {
        this.rackName.setText(rack.getName().trim());
        if (rack.getState() == ObjectState.New) {
            this.column.setEnabled(false);
            this.columnInt.setEnabled(false);
            this.row.setEnabled(false);
            this.rowInt.setEnabled(false);
            this.priority.setEnabled(false);
            this.legend.setEnabled(false);
            this.type.setEnabled(false);
            this.create.setEnabled(true);
            this.save.setEnabled(false);
            this.delete.setEnabled(false);
            this.builderComponent.setEnabled(false);
            this.rackName.requestFocusInWindow();
            this.rackName.selectAll();
        } else {
            this.column.setEnabled(true);
            this.columnInt.setEnabled(true);
            this.row.setEnabled(true);
            this.rowInt.setEnabled(true);
            this.priority.setEnabled(true);
            this.legend.setEnabled(true);
            this.type.setEnabled(true);
            this.create.setEnabled(false);
            this.save.setEnabled(true);
            this.delete.setEnabled(true);
            this.builderComponent.setEnabled(true);
        }
        this.column.setValue(rack.getColumns());
        this.columnInt.setValue(rack.getColumnSpace());
        this.row.setValue(rack.getRows());
        this.rowInt.setValue(rack.getRowSpace());
        this.priority.setValue(rack.getOrder());
        UIHelper.select(this.type, rack.getRackType());
        UIHelper.select(this.legend, rack.getRackNamingType());
        this.builderComponent.setBuilder(this.m_builder);
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == this.racksList) {
            this.createBuilder(e.getItem());
        } else if (e.getSource() == this.legend) {
            RackNamingType rt = UIHelper.getSelectedRackNamingType(this.legend);
            this.builderComponent.setRackNamingType(rt);
        } else if (e.getSource() == this.type) {
            RackType rt = UIHelper.getSelectedRackType(this.type);
            this.builderComponent.setRackType(rt);
        }
    }

    public void stateChanged(ChangeEvent e) {
        int newColumn;
        JSlider src;
        if (e.getSource() == this.row) {
            JSlider src2 = (JSlider)e.getSource();
            int newRow = src2.getValue();
            if (!this.builderComponent.setRow(newRow)) {
                src2.setValue(this.builderComponent.getRow());
            }
        } else if (e.getSource() == this.rowInt) {
            JSlider src3 = (JSlider)e.getSource();
            int newRowInt = src3.getValue();
            this.builderComponent.setRowSpace(newRowInt);
        } else if (e.getSource() == this.columnInt) {
            JSlider src4 = (JSlider)e.getSource();
            int newColumnInt = src4.getValue();
            this.builderComponent.setColumnSpace(newColumnInt);
        } else if (e.getSource() == this.column && !this.builderComponent.setColumn(newColumn = (src = (JSlider)e.getSource()).getValue())) {
            src.setValue(this.builderComponent.getColumn());
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.save) {
            this.save();
        } else if (e.getSource() == this.create) {
            this.create();
        } else if (e.getSource() == this.delete) {
            this.delete();
        }
    }

    private void save() {
        UIHelper.setCursor(this, true);
        this.m_builder.setName(this.rackName.getText().trim());
        this.m_builder.setOrder(this.priority.getValue());
        this.builderComponent.bind();
        this.loadRacks(this.m_builder.getRack());
        UIHelper.setCursor(this, false);
    }

    private void delete() {
        UIHelper.setCursor(this, true);
        if (this.m_builder.getRack().isEmpty()) {
            if (this.m_application.showMessage(null, this.deleteMessage, MessageType.Confirm, MessageIconType.Question, MessageButtonType.YesNo) == DialogResult.Yes) {
                this.m_builder.getRack().delete();
                this.loadRacks(null);
            }
        } else {
            this.m_application.showMessage(null, this.cannotDeleteMessage, MessageType.Message, MessageIconType.Warning, MessageButtonType.Default);
        }
        UIHelper.setCursor(this, false);
    }

    private void create() {
        UIHelper.setCursor(this, true);
        this.m_builder.setName(this.rackName.getText().trim());
        this.m_builder.save();
        int rows = this.m_builder.getRack().getRows();
        int columns = this.m_builder.getRack().getColumns();
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                RackItem ri = this.m_builder.createItem(i, j);
                ri.save();
            }
        }
        this.loadRacks(this.m_builder.getRack());
        UIHelper.setCursor(this, false);
    }

    private void initComponents() {
        this.jPanel1 = new JPanel();
        this.title = new JLabel();
        this.racksList = new OCComboBox();
        this.optionsLabel = new JPanel();
        this.rackName = new JTextField();
        this.columnLabel = new JLabel();
        this.columnIntLabel = new JLabel();
        this.column = new JSlider();
        this.columnInt = new JSlider();
        this.lineLabel = new JLabel();
        this.row = new JSlider();
        this.lineIntLabel = new JLabel();
        this.rowInt = new JSlider();
        this.legendLabel = new JLabel();
        this.legend = new OCComboBox();
        this.typeLabel = new JLabel();
        this.type = new OCComboBox();
        this.priorityLabel = new JLabel();
        this.priority = new JSlider();
        this.save = new JButton();
        this.create = new JButton();
        this.legendPic = new JLabel();
        this.delete = new JButton();
        this.viewerPane = new JPanel();
        this.setMinimumSize(new Dimension(720, 480));
        this.setPreferredSize(new Dimension(0, 0));
        this.title.setText("title");
        this.optionsLabel.setBorder(BorderFactory.createTitledBorder("Options"));
        this.columnLabel.setText("column");
        this.columnIntLabel.setText("interval");
        this.column.setMajorTickSpacing(5);
        this.column.setMaximum(26);
        this.column.setMinimum(1);
        this.column.setMinorTickSpacing(1);
        this.column.setPaintTicks(true);
        this.column.setSnapToTicks(true);
        this.column.setValue(10);
        this.columnInt.setMajorTickSpacing(5);
        this.columnInt.setMaximum(26);
        this.columnInt.setMinimum(1);
        this.columnInt.setMinorTickSpacing(1);
        this.columnInt.setPaintTicks(true);
        this.columnInt.setSnapToTicks(true);
        this.columnInt.setValue(10);
        this.lineLabel.setText("line");
        this.row.setMajorTickSpacing(5);
        this.row.setMaximum(26);
        this.row.setMinimum(1);
        this.row.setMinorTickSpacing(1);
        this.row.setPaintTicks(true);
        this.row.setSnapToTicks(true);
        this.row.setValue(10);
        this.lineIntLabel.setText("interval");
        this.rowInt.setMajorTickSpacing(5);
        this.rowInt.setMaximum(26);
        this.rowInt.setMinimum(1);
        this.rowInt.setMinorTickSpacing(1);
        this.rowInt.setPaintTicks(true);
        this.rowInt.setSnapToTicks(true);
        this.rowInt.setValue(10);
        this.legendLabel.setText("legend");
        this.typeLabel.setText("type");
        this.priorityLabel.setText("priority");
        this.priority.setMajorTickSpacing(5);
        this.priority.setMaximum(20);
        this.priority.setMinimum(1);
        this.priority.setMinorTickSpacing(1);
        this.priority.setPaintTicks(true);
        this.priority.setSnapToTicks(true);
        this.priority.setValue(1);
        this.save.setText("save");
        this.create.setText("create");
        this.delete.setText("delete");
        GroupLayout optionsLabelLayout = new GroupLayout((Container)this.optionsLabel);
        this.optionsLabel.setLayout((LayoutManager)optionsLabelLayout);
        optionsLabelLayout.setHorizontalGroup((GroupLayout.Group)optionsLabelLayout.createParallelGroup(1).add((GroupLayout.Group)optionsLabelLayout.createSequentialGroup().addContainerGap().add((GroupLayout.Group)optionsLabelLayout.createParallelGroup(1).add((GroupLayout.Group)optionsLabelLayout.createSequentialGroup().add((Component)this.legendPic, -2, 216, -2).addPreferredGap(0).add((GroupLayout.Group)optionsLabelLayout.createParallelGroup(2).add((Component)this.delete, -1, 119, 32767).add((Component)this.save, -1, 119, 32767)).addContainerGap()).add((GroupLayout.Group)optionsLabelLayout.createSequentialGroup().add((GroupLayout.Group)optionsLabelLayout.createParallelGroup(1, false).add((Component)this.columnLabel, -1, -1, 32767).add((Component)this.columnIntLabel, -1, -1, 32767).add((Component)this.lineLabel, -1, -1, 32767).add((Component)this.lineIntLabel, -1, -1, 32767).add((Component)this.legendLabel, -1, -1, 32767).add((Component)this.typeLabel, -1, -1, 32767).add((Component)this.priorityLabel, -1, -1, 32767)).add(24, 24, 24).add((GroupLayout.Group)optionsLabelLayout.createParallelGroup(1).add(2, (Component)this.column, -1, 279, 32767).add((GroupLayout.Group)optionsLabelLayout.createSequentialGroup().addPreferredGap(0).add((GroupLayout.Group)optionsLabelLayout.createParallelGroup(1).add((GroupLayout.Group)optionsLabelLayout.createSequentialGroup().add((Component)this.legend, -1, 279, 32767).addPreferredGap(0)).add((Component)this.type, -1, 279, 32767))).add((Component)this.columnInt, -1, 279, 32767).add((Component)this.row, -1, 279, 32767).add((Component)this.rowInt, -1, 279, 32767).add((Component)this.priority, -1, 279, 32767)).addContainerGap()).add((GroupLayout.Group)optionsLabelLayout.createSequentialGroup().add((Component)this.rackName, -2, 234, -2).addPreferredGap(0).add((Component)this.create, -1, 97, 32767).add(12, 12, 12)))));
        optionsLabelLayout.setVerticalGroup((GroupLayout.Group)optionsLabelLayout.createParallelGroup(1).add((GroupLayout.Group)optionsLabelLayout.createSequentialGroup().add((GroupLayout.Group)optionsLabelLayout.createParallelGroup(3).add((Component)this.rackName, -2, -1, -2).add((Component)this.create, -1, -1, 32767)).addPreferredGap(0).add((GroupLayout.Group)optionsLabelLayout.createParallelGroup(1).add((Component)this.columnLabel, -1, 48, 32767).add((Component)this.column, -2, -1, -2)).addPreferredGap(0).add((GroupLayout.Group)optionsLabelLayout.createParallelGroup(1).add((Component)this.columnIntLabel, -1, 48, 32767).add((Component)this.columnInt, -2, -1, -2)).addPreferredGap(0).add((GroupLayout.Group)optionsLabelLayout.createParallelGroup(1).add((Component)this.lineLabel, -1, 46, 32767).add((Component)this.row, -2, -1, -2)).addPreferredGap(0).add((GroupLayout.Group)optionsLabelLayout.createParallelGroup(1).add((Component)this.lineIntLabel, -1, 45, 32767).add((Component)this.rowInt, -2, -1, -2)).addPreferredGap(0).add((GroupLayout.Group)optionsLabelLayout.createParallelGroup(3).add((Component)this.legendLabel).add((Component)this.legend, -2, -1, -2)).add(14, 14, 14).add((GroupLayout.Group)optionsLabelLayout.createParallelGroup(3).add((Component)this.typeLabel).add((Component)this.type, -2, -1, -2)).add(12, 12, 12).add((GroupLayout.Group)optionsLabelLayout.createParallelGroup(1).add((Component)this.priorityLabel, -2, 25, -2).add((Component)this.priority, -2, -1, -2)).addPreferredGap(0).add((GroupLayout.Group)optionsLabelLayout.createParallelGroup(1).add((GroupLayout.Group)optionsLabelLayout.createSequentialGroup().add((Component)this.save).addPreferredGap(0).add((Component)this.delete)).add((Component)this.legendPic, -2, 42, -2))));
        GroupLayout jPanel1Layout = new GroupLayout((Container)this.jPanel1);
        this.jPanel1.setLayout((LayoutManager)jPanel1Layout);
        jPanel1Layout.setHorizontalGroup((GroupLayout.Group)jPanel1Layout.createParallelGroup(1).add((GroupLayout.Group)jPanel1Layout.createSequentialGroup().addContainerGap().add((GroupLayout.Group)jPanel1Layout.createParallelGroup(1).add(2, (Component)this.optionsLabel, -1, -1, 32767).add((GroupLayout.Group)jPanel1Layout.createSequentialGroup().add((Component)this.title, -2, 93, -2).addPreferredGap(0).add((Component)this.racksList, -1, 268, 32767).addContainerGap()))));
        jPanel1Layout.setVerticalGroup((GroupLayout.Group)jPanel1Layout.createParallelGroup(1).add((GroupLayout.Group)jPanel1Layout.createSequentialGroup().addContainerGap().add((GroupLayout.Group)jPanel1Layout.createParallelGroup(3).add((Component)this.title).add((Component)this.racksList, -2, -1, -2)).addPreferredGap(0).add((Component)this.optionsLabel, -2, -1, -2).addContainerGap(-1, 32767)));
        this.viewerPane.setBorder(BorderFactory.createTitledBorder("viewer"));
        GroupLayout viewerPaneLayout = new GroupLayout((Container)this.viewerPane);
        this.viewerPane.setLayout((LayoutManager)viewerPaneLayout);
        viewerPaneLayout.setHorizontalGroup((GroupLayout.Group)viewerPaneLayout.createParallelGroup(1).add(0, 303, 32767));
        viewerPaneLayout.setVerticalGroup((GroupLayout.Group)viewerPaneLayout.createParallelGroup(1).add(0, 430, 32767));
        GroupLayout layout = new GroupLayout((Container)this);
        this.setLayout((LayoutManager)layout);
        layout.setHorizontalGroup((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().add((Component)this.jPanel1, -2, -1, -2).addPreferredGap(0).add((Component)this.viewerPane, -1, -1, 32767).addContainerGap()));
        layout.setVerticalGroup((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().add((GroupLayout.Group)layout.createParallelGroup(1).add((Component)this.jPanel1, -1, -1, 32767).add((GroupLayout.Group)layout.createSequentialGroup().add((Component)this.viewerPane, -1, -1, 32767).add(21, 21, 21))).add(1, 1, 1)));
    }
}

