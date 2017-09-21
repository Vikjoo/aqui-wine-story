/*
 * Decompiled with CFR 0_122.
 */
package opencellar.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.border.Border;
import opencellar.application.IApplication;
import opencellar.ui.AnimationPane;
import opencellar.ui.MdiDesktopPane;

public class MainLayer
extends JFrame {
    JMenuBar menuBar = new JMenuBar();
    public final MdiDesktopPane desktop = new MdiDesktopPane();
    private JScrollPane scrollPane = new JScrollPane();
    private IApplication m_app;
    JLabel statusBar = new JLabel();
    private AnimationPane m_animationPane;
    public JPanel toolBarsPanel;

    private void createMenuBar() {
        this.setJMenuBar(this.menuBar);
    }

    public JMenuBar getJMenuBar() {
        return this.menuBar;
    }

    public final IApplication getApp() {
        return this.m_app;
    }

    public MainLayer(IApplication app) {
        super("Open Cellar Cross Platform - Beta 1");
        if (app == null) {
            throw new IllegalArgumentException("app == null");
        }
        this.m_app = app;
        this.addWindowListener(new WindowAdapter(){

            public void windowClosing(WindowEvent e) {
                MainLayer.this.m_app.exit();
            }
        });
        this.scrollPane.getViewport().add(this.desktop);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add((Component)this.scrollPane, "Center");
        this.createMenuBar();
        this.statusBar.setText("");
        this.statusBar.setPreferredSize(new Dimension(100, 25));
        this.statusBar.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.statusBar.setHorizontalTextPosition(0);
        this.statusBar.setHorizontalAlignment(0);
        this.getContentPane().add((Component)this.statusBar, "South");
        JPanel expane = new JPanel();
        expane.setBorder(BorderFactory.createTitledBorder(expane.getBorder(), "Volet Open Cellar", 2, 2));
        expane.setPreferredSize(new Dimension(260, 200));
        this.getContentPane().add((Component)expane, "East");
        expane.setVisible(false);
        this.m_animationPane = new AnimationPane();
        this.m_animationPane.setPreferredSize(new Dimension(128, 128));
        expane.add((Component)this.m_animationPane, "Center");
        this.m_animationPane.setVisible(false);
        this.toolBarsPanel = new JPanel();
        this.getContentPane().add((Component)this.toolBarsPanel, "North");
        this.setMinimumSize(new Dimension(950, 700));
        this.setSize(950, 700);
    }

    public final void animate(Image image) {
        this.m_animationPane.setImage(image);
        this.m_animationPane.setVisible(true);
    }

    public final void stopAnimate() {
        this.m_animationPane.setVisible(false);
    }

    public final void setStatusText(String text) {
        this.statusBar.setText(text);
    }

}

