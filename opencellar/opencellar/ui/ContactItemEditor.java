/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Cellar
 *  opencellar.framework.CellarObject
 *  opencellar.framework.Contact
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
import opencellar.framework.Cellar;
import opencellar.framework.CellarObject;
import opencellar.framework.Contact;
import opencellar.framework.ICellarListener;
import opencellar.framework.ObjectType;
import opencellar.ui.OCComboBox;
import opencellar.ui.UIHelper;
import org.jdesktop.layout.GroupLayout;

public class ContactItemEditor
extends JPanel
implements ICellarListener,
ActionListener,
ItemListener {
    private JTextField address1;
    private JTextField address2;
    private JLabel addressLabel1;
    private JLabel addressLabel2;
    private JLabel cityLabel;
    private JTextField cityText;
    private JButton create;
    private JButton delete;
    private JLabel faxLabel;
    private JTextField faxText;
    private JTextField itemName;
    private OCComboBox listItem;
    private JLabel mailLabel;
    private JTextField mailText;
    private JLabel nameLabel;
    private JLabel phoneLabel;
    private JTextField phoneText;
    private JLabel title;
    private JButton update;
    private JLabel webLabel;
    private JTextField webText;
    private JLabel zipLabel;
    private JTextField zipText;
    private Cellar m_cellar;
    private ObjectType m_type;
    private String cannotPerfomOperation = null;
    private String deleteMessage = null;
    static final String CREATE = "CREATE";
    static final String UPDATE = "UPDATE";
    static final String DELETE = "DELETE";

    public ContactItemEditor() {
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
            this.display(null);
        }
    }

    private void initComponents() {
        this.title = new JLabel();
        this.listItem = new OCComboBox();
        this.itemName = new JTextField();
        this.create = new JButton();
        this.update = new JButton();
        this.delete = new JButton();
        this.nameLabel = new JLabel();
        this.addressLabel1 = new JLabel();
        this.address1 = new JTextField();
        this.addressLabel2 = new JLabel();
        this.address2 = new JTextField();
        this.zipLabel = new JLabel();
        this.zipText = new JTextField();
        this.cityLabel = new JLabel();
        this.cityText = new JTextField();
        this.phoneLabel = new JLabel();
        this.phoneText = new JTextField();
        this.faxLabel = new JLabel();
        this.faxText = new JTextField();
        this.webLabel = new JLabel();
        this.webText = new JTextField();
        this.mailLabel = new JLabel();
        this.mailText = new JTextField();
        this.title.setText("title");
        this.listItem.setMaximumRowCount(15);
        this.create.setText("create");
        this.update.setText("update");
        this.delete.setText("delete");
        this.nameLabel.setText("name");
        this.addressLabel1.setText("name");
        this.addressLabel2.setText("name");
        this.zipLabel.setText("name");
        this.cityLabel.setText("name");
        this.phoneLabel.setText("name");
        this.faxLabel.setText("name");
        this.webLabel.setText("name");
        this.mailLabel.setText("name");
        GroupLayout layout = new GroupLayout((Container)this);
        this.setLayout((LayoutManager)layout);
        layout.setHorizontalGroup((GroupLayout.Group)layout.createParallelGroup(1).add(2, (GroupLayout.Group)layout.createSequentialGroup().addContainerGap().add((GroupLayout.Group)layout.createParallelGroup(2).add(1, (Component)this.listItem, -1, 367, 32767).add(1, (Component)this.title).add(1, (GroupLayout.Group)layout.createSequentialGroup().add((GroupLayout.Group)layout.createParallelGroup(2, false).add((Component)this.mailLabel, -1, -1, 32767).add((Component)this.webLabel, -1, -1, 32767).add(1, (Component)this.nameLabel, -1, -1, 32767).add(1, (Component)this.addressLabel1, -1, 67, 32767).add((Component)this.addressLabel2, -1, 67, 32767).add((Component)this.zipLabel, -1, 67, 32767).add((Component)this.phoneLabel, -1, 67, 32767)).add(7, 7, 7).add((GroupLayout.Group)layout.createParallelGroup(2).add((Component)this.mailText, -1, 293, 32767).add(1, (Component)this.address2, -1, 293, 32767).add((Component)this.address1, -1, 293, 32767).add(1, (Component)this.itemName, -1, 293, 32767).add(1, (GroupLayout.Group)layout.createSequentialGroup().add((GroupLayout.Group)layout.createParallelGroup(1, false).add((Component)this.phoneText).add((Component)this.zipText, -1, 115, 32767)).add(18, 18, 18).add((GroupLayout.Group)layout.createParallelGroup(2).add((Component)this.faxLabel, -2, 37, -2).add((Component)this.cityLabel, -2, 37, -2)).addPreferredGap(0).add((GroupLayout.Group)layout.createParallelGroup(1).add((Component)this.faxText, -1, 119, 32767).add((Component)this.cityText, -1, 119, 32767))).add((Component)this.webText, -1, 293, 32767))).add(1, (GroupLayout.Group)layout.createSequentialGroup().add((Component)this.create, -1, 87, 32767).add(37, 37, 37).add((Component)this.update, -1, 91, 32767).addPreferredGap(0, 63, 32767).add((Component)this.delete, -1, 89, 32767))).addContainerGap()));
        layout.setVerticalGroup((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().add((Component)this.title).add(14, 14, 14).add((Component)this.listItem, -2, -1, -2).add(24, 24, 24).add((GroupLayout.Group)layout.createParallelGroup(3).add((Component)this.nameLabel).add((Component)this.itemName, -2, -1, -2)).add(13, 13, 13).add((GroupLayout.Group)layout.createParallelGroup(3).add((Component)this.addressLabel1).add((Component)this.address1, -2, -1, -2)).add(11, 11, 11).add((GroupLayout.Group)layout.createParallelGroup(3).add((Component)this.addressLabel2).add((Component)this.address2, -2, -1, -2)).add(11, 11, 11).add((GroupLayout.Group)layout.createParallelGroup(3).add((Component)this.zipLabel).add((Component)this.zipText, -2, -1, -2).add((Component)this.cityLabel).add((Component)this.cityText, -2, -1, -2)).add(11, 11, 11).add((GroupLayout.Group)layout.createParallelGroup(3).add((Component)this.phoneLabel).add((Component)this.phoneText, -2, -1, -2).add((Component)this.faxLabel).add((Component)this.faxText, -2, -1, -2)).add(15, 15, 15).add((GroupLayout.Group)layout.createParallelGroup(3).add((Component)this.webText, -2, -1, -2).add((Component)this.webLabel)).add(15, 15, 15).add((GroupLayout.Group)layout.createParallelGroup(3).add((Component)this.mailLabel).add((Component)this.mailText, -2, -1, -2)).addPreferredGap(0, 16, 32767).add((GroupLayout.Group)layout.createParallelGroup(3).add((Component)this.delete).add((Component)this.create).add((Component)this.update)).addContainerGap()));
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
        this.webLabel.setText(app.getRS("LST_WEB"));
        this.mailLabel.setText(app.getRS("LST_EMAIL"));
        this.addressLabel1.setText(app.getRS("LST_ADDRESS"));
        this.addressLabel2.setText("");
        this.cityLabel.setText(app.getRS("LST_CITY"));
        this.zipLabel.setText(app.getRS("LST_ZIP"));
        this.faxLabel.setText(app.getRS("LST_FAX"));
        this.phoneLabel.setText(app.getRS("LST_PHONE"));
        this.nameLabel.setText(app.getRS("LST_NAME"));
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
            String text = this.itemName.getText().trim();
            if (!text.equals("")) {
                Contact item = (Contact)this.m_cellar.createItem(this.m_type);
                this.bind(item);
                item.save();
                UIHelper.select(this.listItem, (CellarObject)item);
            } else {
                this.showWarningMessage();
            }
        } else if (e.getActionCommand() == "UPDATE") {
            String text = this.itemName.getText().trim();
            if (!text.equals("")) {
                Contact item = (Contact)this.getSelectedItem();
                this.bind(item);
                item.save();
            } else {
                this.showWarningMessage();
            }
        } else if (e.getActionCommand() == "DELETE" && JOptionPane.showConfirmDialog(this, this.deleteMessage, "Open Cellar", 0, 3) == 0) {
            Contact item = (Contact)this.getSelectedItem();
            item.delete();
            this.listItem.setSelectedIndex(-1);
            this.resetUI();
        }
    }

    private void display(Contact ct) {
        if (ct != null) {
            this.itemName.setText(ct.getName().trim());
            this.webText.setText(ct.getWeb().trim());
            this.mailText.setText(ct.getEmail().trim());
            this.phoneText.setText(ct.getPhone().trim());
            this.faxText.setText(ct.getFax().trim());
            this.cityText.setText(ct.getCity().trim());
            this.zipText.setText(ct.getZipCode().trim());
            this.address1.setText(ct.getAddress1().trim());
            this.address2.setText(ct.getAddress2().trim());
        } else {
            String empty = "";
            this.itemName.setText("");
            this.webText.setText("");
            this.mailText.setText("");
            this.phoneText.setText("");
            this.faxText.setText("");
            this.cityText.setText("");
            this.zipText.setText("");
            this.address1.setText("");
            this.address2.setText("");
        }
    }

    private void bind(Contact ct) {
        if (ct != null) {
            ct.setName(this.itemName.getText().trim());
            ct.setWeb(this.webText.getText().trim());
            ct.setEmail(this.mailText.getText().trim());
            ct.setPhone(this.phoneText.getText().trim());
            ct.setFax(this.faxText.getText().trim());
            ct.setCity(this.cityText.getText().trim());
            ct.setZipCode(this.zipText.getText().trim());
            ct.setAddress1(this.address1.getText().trim());
            ct.setAddress2(this.address2.getText().trim());
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
            this.display(null);
        } else {
            this.update.setEnabled(true);
            this.delete.setEnabled(true);
            this.display((Contact)e.getItem());
        }
    }
}

