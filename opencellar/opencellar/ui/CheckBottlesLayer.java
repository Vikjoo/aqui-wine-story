/*
 * Decompiled with CFR 0_122.
 */
package opencellar.ui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.border.Border;
import opencellar.application.Application;
import opencellar.application.IApplication;
import opencellar.application.utils;
import opencellar.ui.MainLayer;
import opencellar.ui.UIHelper;

public final class CheckBottlesLayer
extends JDialog
implements ActionListener {
    private IApplication m_app = null;
    private int m_bottles = 0;
    public static final int ACTION_IGNORE = -1;
    public static final int ACTION_CREATE_PURCHASE_SALES = 0;
    public static final int ACTION_REMOVE_BOTTLES = 1;
    public static final int ACTION_ADD_BOTTLES = 2;
    private int m_action = -1;
    private JCheckBox checkAlways;
    private JButton createPurchase;
    private JLabel icon;
    private JButton ignore;
    private JButton moreBottles;
    private JButton removeBottles;
    private JLabel text;

    public CheckBottlesLayer(IApplication app, int bottles) {
        super((Frame)((Application)app).getMain(), true);
        this.initComponents();
        this.m_app = app;
        this.m_bottles = bottles;
        this.performTranslation();
        this.customInit();
    }

    private void customInit() {
        this.removeBottles.addActionListener(this);
        this.ignore.addActionListener(this);
        this.createPurchase.addActionListener(this);
        this.moreBottles.addActionListener(this);
    }

    private void performTranslation() {
        this.setTitle(this.m_app.getRS("CHECK_WIN_TITLE"));
        this.ignore.setText(this.m_app.getRS("CHECK_WIN_SKIP"));
        this.createPurchase.setText(this.m_app.getRS("CHECK_WIN_SALES"));
        this.moreBottles.setText(utils.format(this.m_app.getRS("CHECK_WIN_ADD"), String.valueOf(Math.abs(this.getBottles()))));
        this.removeBottles.setText(utils.format(this.m_app.getRS("CHECK_WIN_REMOVE"), String.valueOf(Math.abs(this.getBottles()))));
        this.checkAlways.setText(this.m_app.getRS("CHECK_WIN_DONT_SHOW"));
        String content = utils.replace(this.m_app.getRS("CHECK_WIN_TEXT"), "\n", "<br>");
        this.text.setText("<HTML>" + utils.format(content, String.valueOf(this.getBottles())) + "</HTML>");
        try {
            this.icon.setIcon(new ImageIcon(ImageIO.read(CheckBottlesLayer.class.getResourceAsStream("/opencellar/rc/misc/warning.gif"))));
        }
        catch (Exception ex) {
            // empty catch block
        }
    }

    public final int getBottles() {
        return this.m_bottles;
    }

    public final int getAction() {
        return this.m_action;
    }

    private void setAction(int action) {
        this.m_action = action;
        UIHelper.findAndCloseDialog(this);
    }

    public final void setButtons(boolean addBottles, boolean remBottles, boolean createPurchaseSales) {
        this.moreBottles.setVisible(addBottles);
        this.removeBottles.setVisible(remBottles);
        this.createPurchase.setVisible(createPurchaseSales);
    }

    public final boolean isDefaultOperation() {
        return this.checkAlways.isSelected();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.moreBottles) {
            this.setAction(2);
        } else if (e.getSource() == this.removeBottles) {
            this.setAction(1);
        } else if (e.getSource() == this.createPurchase) {
            this.setAction(0);
        } else if (e.getSource() == this.ignore) {
            this.setAction(-1);
        }
    }

    private void initComponents() {
        this.icon = new JLabel();
        this.text = new JLabel();
        this.moreBottles = new JButton();
        this.createPurchase = new JButton();
        this.checkAlways = new JCheckBox();
        this.ignore = new JButton();
        this.removeBottles = new JButton();
        this.getContentPane().setLayout(null);
        this.setDefaultCloseOperation(0);
        this.setCursor(new Cursor(0));
        this.setResizable(false);
        this.getContentPane().add(this.icon);
        this.icon.setBounds(10, 10, 64, 64);
        this.text.setText("the text");
        this.text.setVerticalAlignment(1);
        this.text.setMaximumSize(new Dimension(350, 100));
        this.text.setMinimumSize(new Dimension(350, 100));
        this.text.setPreferredSize(new Dimension(350, 100));
        this.getContentPane().add(this.text);
        this.text.setBounds(110, 10, 390, 100);
        this.moreBottles.setText("placer 100 bouteilles");
        this.getContentPane().add(this.moreBottles);
        this.moreBottles.setBounds(100, 130, 180, 23);
        this.createPurchase.setText("cr\u00e9er une fiche achats consos");
        this.getContentPane().add(this.createPurchase);
        this.createPurchase.setBounds(290, 130, 210, 23);
        this.checkAlways.setText("always action");
        this.checkAlways.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.checkAlways.setMargin(new Insets(0, 0, 0, 0));
        this.getContentPane().add(this.checkAlways);
        this.checkAlways.setBounds(10, 180, 350, 15);
        this.ignore.setText("ignore");
        this.getContentPane().add(this.ignore);
        this.ignore.setBounds(380, 170, 120, 23);
        this.removeBottles.setText("enlever 100 bouteilles");
        this.removeBottles.setMinimumSize(new Dimension(131, 23));
        this.removeBottles.setPreferredSize(new Dimension(131, 23));
        this.getContentPane().add(this.removeBottles);
        this.removeBottles.setBounds(100, 130, 180, 23);
    }
}

