/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.jdesktop.layout.GroupLayout
 *  org.jdesktop.layout.GroupLayout$Group
 *  org.jdesktop.layout.GroupLayout$ParallelGroup
 *  org.jdesktop.layout.GroupLayout$SequentialGroup
 */
package opencellar.ui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.EventListener;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.EventListenerList;
import javax.swing.filechooser.FileFilter;
import opencellar.application.IApplication;
import opencellar.application.OpenCellarFileFilter;
import opencellar.ui.INewCellarPaneListener;
import opencellar.ui.NewCellarPaneEvent;
import org.jdesktop.layout.GroupLayout;

public class NewCellarPane
extends JPanel
implements ActionListener {
    final String SELECT = "select";
    final String CREATE = "create";
    final String CANCEL = "cancel";
    private ButtonGroup buttonGroup1;
    private JButton cancelButton;
    private JButton createButton;
    private JLabel descLabel;
    private JLabel jLabel1;
    private JRadioButton jRadioButton1;
    private JRadioButton jRadioButton2;
    private JSeparator jSeparator1;
    private JSeparator jSeparator2;
    private JButton selectFileButton;
    private JTextField selectFileText;

    public NewCellarPane() {
        this.initComponents();
        this.initComponentsEx();
    }

    private void initComponentsEx() {
        this.createButton.addActionListener(this);
        this.cancelButton.addActionListener(this);
        this.selectFileButton.addActionListener(this);
        this.jRadioButton1.setSelected(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("select")) {
            this.showFileDialog();
        } else if (e.getActionCommand().equals("create")) {
            this.notifyOnCreate();
        } else if (e.getActionCommand().equals("cancel")) {
            this.notifyOnAbort();
        }
    }

    private void showFileDialog() {
        JFileChooser file = new JFileChooser();
        file.setAcceptAllFileFilterUsed(false);
        file.setFileFilter(new OpenCellarFileFilter());
        int ret = file.showSaveDialog(this);
        if (ret == 0) {
            String filePath = file.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".oc")) {
                filePath = filePath + ".oc";
            }
            this.selectFileText.setText(filePath);
            this.createButton.setEnabled(true);
        }
    }

    public final void performTranslatation(IApplication app) {
        this.cancelButton.setText(app.getRS("BTN_CANCEL"));
        this.createButton.setText(app.getRS("BTN_CREATE"));
        this.selectFileButton.setText(app.getRS("BTN_SELECT"));
        this.jRadioButton1.setText(app.getRS("WZD_NEW_STEP2_TEMPLATE"));
        this.jRadioButton2.setText(app.getRS("WZD_NEW_STEP2_EMPTY"));
        this.descLabel.setText(app.getRS("WZD_NEW_TITLE"));
    }

    private void initComponents() {
        this.buttonGroup1 = new ButtonGroup();
        this.descLabel = new JLabel();
        this.jSeparator1 = new JSeparator();
        this.jSeparator2 = new JSeparator();
        this.createButton = new JButton();
        this.cancelButton = new JButton();
        this.selectFileText = new JTextField();
        this.selectFileButton = new JButton();
        this.jRadioButton1 = new JRadioButton();
        this.jRadioButton2 = new JRadioButton();
        this.jLabel1 = new JLabel();
        this.descLabel.setText("Assistant nouvelle cave");
        this.createButton.setText("Cr\u00e9er");
        this.createButton.setActionCommand("create");
        this.createButton.setEnabled(false);
        this.cancelButton.setText("Annuler");
        this.cancelButton.setActionCommand("cancel");
        this.selectFileText.setEditable(false);
        this.selectFileButton.setText("S\u00e9lectionner");
        this.selectFileButton.setActionCommand("select");
        this.buttonGroup1.add(this.jRadioButton1);
        this.jRadioButton1.setText("Utiliser le mod\u00e8le");
        this.jRadioButton1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.jRadioButton1.setMargin(new Insets(0, 0, 0, 0));
        this.buttonGroup1.add(this.jRadioButton2);
        this.jRadioButton2.setText("Cr\u00e9er une cave vide");
        this.jRadioButton2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.jRadioButton2.setMargin(new Insets(0, 0, 0, 0));
        this.jLabel1.setText("Emplacement de la cave sur le disque dur");
        GroupLayout layout = new GroupLayout((Container)this);
        this.setLayout((LayoutManager)layout);
        layout.setHorizontalGroup((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().addContainerGap().add((GroupLayout.Group)layout.createParallelGroup(1).add((Component)this.descLabel, -1, 335, 32767).add(2, (GroupLayout.Group)layout.createSequentialGroup().add((GroupLayout.Group)layout.createParallelGroup(2).add((GroupLayout.Group)layout.createSequentialGroup().add((GroupLayout.Group)layout.createParallelGroup(1).add((Component)this.jLabel1).add((Component)this.selectFileText, -1, 228, 32767)).addPreferredGap(0).add((Component)this.selectFileButton)).add(1, (GroupLayout.Group)layout.createSequentialGroup().add((Component)this.cancelButton, -2, 82, -2).addPreferredGap(0, 161, 32767).add((Component)this.createButton, -2, 82, -2))).addContainerGap()))).add((GroupLayout.Group)layout.createSequentialGroup().add(32, 32, 32).add((Component)this.jRadioButton1, -1, 303, 32767).addContainerGap()).add((GroupLayout.Group)layout.createSequentialGroup().add(32, 32, 32).add((Component)this.jRadioButton2, -1, 303, 32767).addContainerGap()).add((Component)this.jSeparator1, -1, 345, 32767).add((Component)this.jSeparator2, -1, 345, 32767));
        layout.setVerticalGroup((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().add((Component)this.descLabel, -2, 23, -2).addPreferredGap(0).add((Component)this.jSeparator2, -2, 10, -2).add(9, 9, 9).add((Component)this.jLabel1).addPreferredGap(0).add((GroupLayout.Group)layout.createParallelGroup(3).add((Component)this.selectFileText, -2, -1, -2).add((Component)this.selectFileButton, -2, 23, -2)).add(18, 18, 18).add((Component)this.jRadioButton1).add(23, 23, 23).add((Component)this.jRadioButton2).add(18, 18, 18).add((Component)this.jSeparator1, -2, 10, -2).addPreferredGap(0).add((GroupLayout.Group)layout.createParallelGroup(3).add((Component)this.cancelButton).add((Component)this.createButton)).addContainerGap(-1, 32767)));
    }

    public void addNewCellarPaneListener(INewCellarPaneListener listener) {
        this.listenerList.add(INewCellarPaneListener.class, listener);
    }

    public void removeNewCellarPaneListener(INewCellarPaneListener listener) {
        this.listenerList.remove(INewCellarPaneListener.class, listener);
    }

    private void notifyOnCreate() {
        INewCellarPaneListener[] listeners = (INewCellarPaneListener[])this.listenerList.getListeners(INewCellarPaneListener.class);
        NewCellarPaneEvent evt = new NewCellarPaneEvent(this.selectFileText.getText(), this.jRadioButton1.isSelected());
        for (int i = 0; i < listeners.length; ++i) {
            listeners[i].onCreate(evt);
        }
    }

    private void notifyOnAbort() {
        INewCellarPaneListener[] listeners = (INewCellarPaneListener[])this.listenerList.getListeners(INewCellarPaneListener.class);
        for (int i = 0; i < listeners.length; ++i) {
            listeners[i].onAbort();
        }
    }
}

