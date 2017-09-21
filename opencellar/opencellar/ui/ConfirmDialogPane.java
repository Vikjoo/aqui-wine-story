/*
 * Decompiled with CFR 0_122.
 */
package opencellar.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import opencellar.application.IApplication;
import opencellar.ui.UIHelper;

public class ConfirmDialogPane
extends JPanel
implements ActionListener {
    private IApplication m_app = null;
    private int m_option = 0;
    private JCheckBox checkAlways;
    private JLabel icon;
    private JButton no;
    private JLabel text;
    private JButton yes;

    public ConfirmDialogPane() {
        this.initComponents();
        this.no.addActionListener(this);
        this.yes.addActionListener(this);
    }

    public void setApplication(IApplication app) {
        if (app == null) {
            throw new IllegalArgumentException("app = null");
        }
        this.m_app = app;
        this.performTranslation();
    }

    private void performTranslation() {
        this.yes.setText(this.m_app.getRS("CUSTOM_YES"));
        this.no.setText(this.m_app.getRS("CUSTOM_NO"));
        this.checkAlways.setText(this.m_app.getRS("CUSTOM_ALWAYS"));
        try {
            this.icon.setIcon(new ImageIcon(ImageIO.read(ConfirmDialogPane.class.getResourceAsStream("/opencellar/rc/misc/question.gif"))));
        }
        catch (Exception ex) {
            // empty catch block
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.yes) {
            this.m_option = 0;
        } else if (e.getSource() == this.no) {
            this.m_option = 1;
        }
        UIHelper.findAndCloseDialog(this);
    }

    public final void reset() {
        this.m_option = 0;
        this.checkAlways.setSelected(false);
    }

    public final int getSelectedOption() {
        return this.m_option;
    }

    public final boolean isDefaultActionChecked() {
        return this.checkAlways.isSelected();
    }

    public final void setText(String s) {
        if (s != null) {
            this.text.setText("<HTML>" + s + "</HTML>");
        }
    }

    private void initComponents() {
        this.icon = new JLabel();
        this.text = new JLabel();
        this.yes = new JButton();
        this.no = new JButton();
        this.checkAlways = new JCheckBox();
        this.setLayout(null);
        this.setMinimumSize(new Dimension(452, 187));
        this.setPreferredSize(new Dimension(452, 187));
        this.add(this.icon);
        this.icon.setBounds(10, 10, 48, 48);
        this.text.setText("the text");
        this.text.setVerticalAlignment(1);
        this.text.setMaximumSize(new Dimension(350, 100));
        this.text.setMinimumSize(new Dimension(350, 100));
        this.text.setPreferredSize(new Dimension(350, 100));
        this.add(this.text);
        this.text.setBounds(80, 10, 350, 100);
        this.yes.setText("yes");
        this.add(this.yes);
        this.yes.setBounds(220, 130, 90, 23);
        this.no.setText("no");
        this.add(this.no);
        this.no.setBounds(340, 130, 90, 23);
        this.checkAlways.setText("always action");
        this.checkAlways.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.checkAlways.setMargin(new Insets(0, 0, 0, 0));
        this.add(this.checkAlways);
        this.checkAlways.setBounds(10, 160, 440, 15);
    }
}

