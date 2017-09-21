/*
 * Decompiled with CFR 0_122.
 */
package opencellar.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import opencellar.application.AppSettings;
import opencellar.application.CommandManager;
import opencellar.application.DialogResult;
import opencellar.application.IApplication;
import opencellar.application.ICommand;
import opencellar.application.MessageButtonType;
import opencellar.application.MessageIconType;
import opencellar.application.MessageType;
import opencellar.application.SettingCollection;

public final class WelcomeLayer
extends JInternalFrame
implements MouseListener {
    private IApplication m_app;
    boolean inEditor = false;
    private JLabel back;
    private JLabel helpCenter;
    private JLabel newCellar;
    private JLabel openCellar;
    private JCheckBox showAgain;

    public WelcomeLayer(IApplication app) {
        if (app == null) {
            throw new IllegalArgumentException("app == null");
        }
        this.m_app = app;
        this.initComponents();
        this.initCustom();
    }

    private void initCustom() {
        super.setBackground(Color.WHITE);
        try {
            this.back.setIcon(new ImageIcon(ImageIO.read(WelcomeLayer.class.getResourceAsStream("/opencellar/rc/backform.gif"))));
        }
        catch (Exception ex) {
            // empty catch block
        }
        String s = this.m_app.getSettings().get("App").get("StartPage", "T");
        if (s.startsWith("T")) {
            this.showAgain.setSelected(true);
        } else {
            this.showAgain.setSelected(false);
        }
        this.showAgain.setText(this.m_app.getRS("WEL_PGE_SHOW"));
        this.showAgain.addItemListener(new ItemListener(){

            public void itemStateChanged(ItemEvent e) {
                if (!WelcomeLayer.this.showAgain.isSelected() && !WelcomeLayer.this.inEditor) {
                    WelcomeLayer.this.m_app.showMessage(null, WelcomeLayer.this.m_app.getRS("WIN_START_DONT_SHOW_ALERT"), MessageType.Message, MessageIconType.Information, MessageButtonType.Default);
                }
                WelcomeLayer.this.m_app.getSettings().get("App").set("StartPage", String.valueOf(WelcomeLayer.this.showAgain.isSelected()).toUpperCase());
            }
        });
        try {
            this.newCellar.setIcon(new ImageIcon(ImageIO.read(WelcomeLayer.class.getResourceAsStream("/opencellar/rc/misc/puce.gif"))));
            this.openCellar.setIcon(new ImageIcon(ImageIO.read(WelcomeLayer.class.getResourceAsStream("/opencellar/rc/misc/puce.gif"))));
            this.helpCenter.setIcon(new ImageIcon(ImageIO.read(WelcomeLayer.class.getResourceAsStream("/opencellar/rc/misc/puce.gif"))));
        }
        catch (Exception ex) {
            // empty catch block
        }
        this.newCellar.setText(this.m_app.getRS("WEL_PGE_NEW_CELLAR"));
        this.openCellar.setText(this.m_app.getRS("MNU_FILE_OPEN"));
        this.helpCenter.setText(this.m_app.getRS("MNU_HELP_CENTER"));
        this.newCellar.setCursor(Cursor.getPredefinedCursor(12));
        this.openCellar.setCursor(Cursor.getPredefinedCursor(12));
        this.helpCenter.setCursor(Cursor.getPredefinedCursor(12));
        this.newCellar.addMouseListener(this);
        this.openCellar.addMouseListener(this);
        this.helpCenter.addMouseListener(this);
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            if (e.getSource() == this.newCellar) {
                this.m_app.getCommands().get("NewCellar").execute();
            } else if (e.getSource() == this.openCellar) {
                this.m_app.getCommands().get("OpenCellar").execute();
            } else if (e.getSource() == this.helpCenter) {
                this.m_app.getCommands().get("OnlineHelp").execute();
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public final boolean getOption() {
        return this.showAgain.isSelected();
    }

    public final void setOption(boolean b) {
        this.inEditor = true;
        this.showAgain.setSelected(b);
        this.inEditor = false;
    }

    private void initComponents() {
        this.showAgain = new JCheckBox();
        this.newCellar = new JLabel();
        this.openCellar = new JLabel();
        this.helpCenter = new JLabel();
        this.back = new JLabel();
        this.getContentPane().setLayout(null);
        this.showAgain.setBackground(new Color(255, 255, 255));
        this.showAgain.setSelected(true);
        this.showAgain.setText("dont show again");
        this.showAgain.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.showAgain.setMargin(new Insets(0, 0, 0, 0));
        this.getContentPane().add(this.showAgain);
        this.showAgain.setBounds(10, 340, 560, 15);
        this.newCellar.setBackground(new Color(255, 255, 255));
        this.newCellar.setText("newCellar");
        this.getContentPane().add(this.newCellar);
        this.newCellar.setBounds(300, 180, 290, 14);
        this.openCellar.setBackground(new Color(255, 255, 255));
        this.openCellar.setText("openCellar");
        this.getContentPane().add(this.openCellar);
        this.openCellar.setBounds(300, 220, 290, 14);
        this.helpCenter.setBackground(new Color(255, 255, 255));
        this.helpCenter.setText("help center");
        this.getContentPane().add(this.helpCenter);
        this.helpCenter.setBounds(300, 260, 290, 14);
        this.getContentPane().add(this.back);
        this.back.setBounds(0, 0, 610, 400);
        this.pack();
    }

}

