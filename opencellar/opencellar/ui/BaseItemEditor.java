/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.BaseItem
 *  opencellar.framework.Cellar
 *  opencellar.framework.CellarObject
 *  opencellar.framework.ICellarListener
 *  opencellar.framework.ObjectType
 *  org.jdesktop.layout.GroupLayout
 *  org.jdesktop.layout.GroupLayout$Group
 *  org.jdesktop.layout.GroupLayout$ParallelGroup
 *  org.jdesktop.layout.GroupLayout$SequentialGroup
 */
package opencellar.ui;

import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import opencellar.application.IApplication;
import opencellar.framework.BaseItem;
import opencellar.framework.Cellar;
import opencellar.framework.CellarObject;
import opencellar.framework.ICellarListener;
import opencellar.framework.ObjectType;
import opencellar.ui.OCComboBox;
import opencellar.ui.UIHelper;
import org.jdesktop.layout.GroupLayout;

public class BaseItemEditor
extends JPanel
implements ICellarListener,
ActionListener,
ItemListener {
    private JButton create;
    private JButton delete;
    private JTextField itemText;
    private OCComboBox listItem;
    private JLabel title;
    private JButton update;
    private Cellar m_cellar;
    private ObjectType m_type;
    private String cannotPerfomOperation = null;
    private String deleteMessage = null;
    static final String CREATE = "CREATE";
    static final String UPDATE = "UPDATE";
    static final String DELETE = "DELETE";

    public BaseItemEditor() {
        this.initComponents();
        this.register();
    }

    private void register() {
        this.listItem.addItemListener(this);
        this.create.addActionListener(this);
        this.create.setActionCommand("CREATE");
        this.update.addActionListener(this);
        this.update.setActionCommand("UPDATE");
        this.delete.addActionListener(this);
        this.delete.setActionCommand("DELETE");
        this.resetUI();
    }

    private void resetUI() {
        if (this.listItem.getSelectedIndex() == -1) {
            this.update.setEnabled(false);
            this.delete.setEnabled(false);
            this.itemText.setText("");
        }
    }

    private void initComponents() {
        this.title = new JLabel();
        this.listItem = new OCComboBox();
        this.itemText = new JTextField();
        this.create = new JButton();
        this.update = new JButton();
        this.delete = new JButton();
        this.title.setText("title");
        this.listItem.setMaximumRowCount(15);
        this.create.setText("create");
        this.update.setText("update");
        this.delete.setText("delete");
        GroupLayout layout = new GroupLayout((Container)this);
        this.setLayout((LayoutManager)layout);
        layout.setHorizontalGroup((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().addContainerGap().add((GroupLayout.Group)layout.createParallelGroup(1).add((Component)this.title).add((Component)this.itemText, -1, 299, 32767).add((GroupLayout.Group)layout.createSequentialGroup().add((Component)this.create, -1, 71, 32767).add(37, 37, 37).add((Component)this.update, -1, 75, 32767).addPreferredGap(0, 43, 32767).add((Component)this.delete, -1, 73, 32767)).add((Component)this.listItem, -1, 299, 32767)).addContainerGap()));
        layout.setVerticalGroup((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().add((Component)this.title).add(14, 14, 14).add((Component)this.listItem, -2, -1, -2).add(24, 24, 24).add((Component)this.itemText, -2, -1, -2).add(21, 21, 21).add((GroupLayout.Group)layout.createParallelGroup(3).add((Component)this.delete).add((Component)this.create).add((Component)this.update)).addContainerGap(91, 32767)));
    }

    public final void setMode(Cellar cellar, ObjectType ot) {
        if (cellar == null) {
            throw new IllegalArgumentException("cellar");
        }
        if (this.m_cellar == null) {
            cellar.addlistener((ICellarListener)this);
        }
        this.m_cellar = cellar;
        this.m_type = ot;
        UIHelper.pushItems(this.listItem, this.m_type, this.m_cellar, (Object)null, null);
        this.resetUI();
    }

    public final void performTranslation(IApplication app) {
        this.title.setText(app.getRS("LST_SUB_SELECT"));
        this.update.setText(app.getRS("BTN_UPDATE"));
        this.create.setText(app.getRS("BTN_CREATE"));
        this.delete.setText(app.getRS("BTN_DELETE"));
        this.cannotPerfomOperation = app.getRS("LST_ERROR_VALUE");
        this.deleteMessage = app.getRS("ADM_RACK_CONFIRM_DELETE");
    }

    public final void close() {
        if (this.m_cellar != null) {
            this.m_cellar.removeListener((ICellarListener)this);
        }
    }

    public void onNewItem(Cellar source, CellarObject co) {
        this.reload(co.getType());
    }

    public void onUpdateItem(Cellar source, CellarObject co) {
        this.reload(co.getType());
    }

    public void onDeleteItem(Cellar source, CellarObject co) {
        this.reload(co.getType());
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "CREATE") {
            String text = this.itemText.getText().trim();
            if (!text.equals("")) {
                BaseItem item = (BaseItem)this.m_cellar.createItem(this.m_type);
                item.setName(text);
                item.save();
                UIHelper.select(this.listItem, (CellarObject)item);
            } else {
                this.showWarningMessage();
            }
        } else if (e.getActionCommand() == "UPDATE") {
            String text = this.itemText.getText().trim();
            if (!text.equals("")) {
                BaseItem item = (BaseItem)this.getSelectedItem();
                item.setName(text);
                item.save();
            } else {
                this.showWarningMessage();
            }
        } else if (e.getActionCommand() == "DELETE" && JOptionPane.showConfirmDialog(this, this.deleteMessage, "Open Cellar", 0, 3) == 0) {
            BaseItem item = (BaseItem)this.getSelectedItem();
            item.delete();
            this.listItem.setSelectedIndex(-1);
            this.resetUI();
        }
    }

    private void showWarningMessage() {
        JOptionPane.showMessageDialog(this, this.cannotPerfomOperation, "Open Cellar", 2);
    }

    private CellarObject getSelectedItem() {
        if (this.listItem.getSelectedIndex() > -1) {
            return (CellarObject)this.listItem.getSelectedItem();
        }
        return null;
    }

    private void reload(ObjectType targetType) {
        if (targetType == this.m_type) {
            UIHelper.pushItems(this.listItem, this.m_type, this.m_cellar, (Object)null, this.getSelectedItem());
        }
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getItem() == null) {
            this.update.setEnabled(false);
            this.delete.setEnabled(false);
            this.itemText.setText("");
        } else {
            this.update.setEnabled(true);
            this.delete.setEnabled(true);
            this.itemText.setText(((BaseItem)e.getItem()).getName().trim());
        }
    }
}

