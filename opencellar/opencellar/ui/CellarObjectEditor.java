/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Cellar
 *  opencellar.framework.ObjectType
 *  org.jdesktop.layout.GroupLayout
 *  org.jdesktop.layout.GroupLayout$Group
 *  org.jdesktop.layout.GroupLayout$ParallelGroup
 *  org.jdesktop.layout.GroupLayout$SequentialGroup
 */
package opencellar.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import opencellar.application.IApplication;
import opencellar.framework.Cellar;
import opencellar.framework.ObjectType;
import opencellar.ui.BaseItemEditor;
import opencellar.ui.ContactItemEditor;
import org.jdesktop.layout.GroupLayout;

public class CellarObjectEditor
extends JPanel
implements ActionListener,
ListSelectionListener {
    private Cellar m_cellar;
    private BaseItemEditor baseItemEditor;
    private ContactItemEditor contactItemEditor;
    private JButton close;
    private JPanel content;
    private JScrollPane jScrollPane1;
    private JSeparator jSeparator1;
    private JList objectTypeList;

    public CellarObjectEditor() {
        this.initComponents();
        this.buildEditor();
        this.register();
    }

    public final void setCellar(Cellar cellar) {
        if (cellar == null) {
            throw new IllegalArgumentException("cellar");
        }
        this.m_cellar = cellar;
    }

    private void register() {
        this.close.addActionListener(this);
        this.objectTypeList.addListSelectionListener(this);
    }

    public final void close() {
        this.baseItemEditor.close();
    }

    public void performTranslation(IApplication app) {
        this.baseItemEditor.performTranslation(app);
        this.contactItemEditor.performTranslation(app);
        Vector<_ObjectType> items = new Vector<_ObjectType>();
        items.add(new _ObjectType(ObjectType.Country, app.getRS("OBJ_COUNTRY")));
        items.add(new _ObjectType(ObjectType.Area, app.getRS("OBJ_AREA")));
        items.add(new _ObjectType(ObjectType.Name, app.getRS("OBJ_NAME")));
        items.add(new _ObjectType(ObjectType.TypeOfWine, app.getRS("OBJ_TYPEOFWINE")));
        items.add(new _ObjectType(ObjectType.Classification, app.getRS("OBJ_CLASSIFICATION")));
        items.add(new _ObjectType(ObjectType.Category, app.getRS("OBJ_CATEGORY")));
        items.add(new _ObjectType(ObjectType.BottleType, app.getRS("OBJ_BOTTLETYPE")));
        items.add(new _ObjectType(ObjectType.Provider, app.getRS("OBJ_PROVIDER")));
        items.add(new _ObjectType(ObjectType.Owner, app.getRS("OBJ_OWNER")));
        this.objectTypeList.setListData(items);
        this.close.setText(app.getRS("BTN_CLOSE"));
    }

    private void initComponents() {
        this.jScrollPane1 = new JScrollPane();
        this.objectTypeList = new JList();
        this.jSeparator1 = new JSeparator();
        this.close = new JButton();
        this.content = new JPanel();
        this.objectTypeList.setSelectionMode(0);
        this.jScrollPane1.setViewportView(this.objectTypeList);
        this.close.setText("close");
        GroupLayout contentLayout = new GroupLayout((Container)this.content);
        this.content.setLayout((LayoutManager)contentLayout);
        contentLayout.setHorizontalGroup((GroupLayout.Group)contentLayout.createParallelGroup(1).add(0, 456, 32767));
        contentLayout.setVerticalGroup((GroupLayout.Group)contentLayout.createParallelGroup(1).add(0, 375, 32767));
        GroupLayout layout = new GroupLayout((Container)this);
        this.setLayout((LayoutManager)layout);
        layout.setHorizontalGroup((GroupLayout.Group)layout.createParallelGroup(1).add(2, (Component)this.jSeparator1, -1, 631, 32767).add((GroupLayout.Group)layout.createSequentialGroup().addContainerGap().add((Component)this.jScrollPane1, -2, 149, -2).addPreferredGap(0).add((Component)this.content, -1, -1, 32767).addContainerGap()).add(2, (GroupLayout.Group)layout.createSequentialGroup().addContainerGap(564, 32767).add((Component)this.close).addContainerGap()));
        layout.setVerticalGroup((GroupLayout.Group)layout.createParallelGroup(1).add(2, (GroupLayout.Group)layout.createSequentialGroup().addContainerGap().add((GroupLayout.Group)layout.createParallelGroup(1).add((Component)this.content, -1, -1, 32767).add((Component)this.jScrollPane1, -2, 268, -2)).add(17, 17, 17).add((Component)this.jSeparator1, -2, 10, -2).addPreferredGap(0).add((Component)this.close).addContainerGap()));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.close) {
            for (Container c = this.getParent(); c != null; c = c.getParent()) {
                if (!(c instanceof JInternalFrame)) continue;
                try {
                    try {
                        ((JInternalFrame)c).setClosed(true);
                    }
                    catch (Exception ex) {
                    }
                }
                catch (Throwable throwable) {}
                break;
            }
        }
    }

    public void valueChanged(ListSelectionEvent e) {
        if (this.objectTypeList.getSelectedIndex() > -1) {
            _ObjectType ot = (_ObjectType)this.objectTypeList.getSelectedValue();
            this.setActiveEditor(ot.getType());
        }
    }

    public void setActiveEditor(ObjectType ot) {
        this.setCursor(true);
        if (ot == ObjectType.Owner | ot == ObjectType.Provider) {
            if (this.baseItemEditor.getParent() != null) {
                this.content.remove(this.baseItemEditor);
            }
            if (this.contactItemEditor.getParent() == null) {
                this.content.add((Component)this.contactItemEditor, "Center");
                this.contactItemEditor.setVisible(true);
            }
            this.contactItemEditor.setMode(this.m_cellar, ot);
        } else {
            if (this.contactItemEditor.getParent() != null) {
                this.content.remove(this.contactItemEditor);
            }
            if (this.baseItemEditor.getParent() == null) {
                this.content.add((Component)this.baseItemEditor, "Center");
                this.baseItemEditor.setVisible(true);
            }
            this.baseItemEditor.setMode(this.m_cellar, ot);
        }
        this.content.repaint();
        this.setCursor(false);
    }

    private void setCursor(boolean wait) {
        if (wait) {
            this.getParent().setCursor(Cursor.getPredefinedCursor(3));
        } else {
            this.getParent().setCursor(Cursor.getPredefinedCursor(0));
        }
    }

    private void buildEditor() {
        this.baseItemEditor = new BaseItemEditor();
        this.baseItemEditor.setVisible(false);
        this.content.setLayout(new BorderLayout());
        this.contactItemEditor = new ContactItemEditor();
        this.contactItemEditor.setVisible(false);
    }

    class _ObjectType {
        private ObjectType m_type;
        private String m_name;

        protected _ObjectType(ObjectType ot, String name) {
            this.m_type = ot;
            this.m_name = name;
        }

        public ObjectType getType() {
            return this.m_type;
        }

        public String toString() {
            return this.m_name;
        }
    }

}

