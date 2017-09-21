/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Note
 *  opencellar.framework.Wine
 *  org.jdesktop.layout.GroupLayout
 *  org.jdesktop.layout.GroupLayout$Group
 *  org.jdesktop.layout.GroupLayout$ParallelGroup
 *  org.jdesktop.layout.GroupLayout$SequentialGroup
 */
package opencellar.ui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import opencellar.application.IApplication;
import opencellar.application.NoteCommandBarManager;
import opencellar.framework.Note;
import opencellar.framework.Wine;
import opencellar.ui.NotePane;
import org.jdesktop.layout.GroupLayout;

public class NoteLayer
extends JDialog
implements ActionListener {
    private IApplication m_app;
    private Wine m_wine;
    private Note m_note;
    private JScrollPane scrollPane;
    private NotePane notePane = null;
    public static final int ACTION_SAVE = 0;
    public static final int ACTION_CANCEL = 1;
    private int m_action = 1;
    private JPanel bottomPane;
    private JButton cancel;
    private JPanel centerPane;
    private JPanel commandBarPane;
    private JScrollBar jScrollBar1;
    private JButton save;

    public NoteLayer(Frame owner) {
        super(owner, true);
        this.initComponents();
        this.customInit();
    }

    private void customInit() {
        this.save.addActionListener(this);
        this.cancel.addActionListener(this);
    }

    public final void setDataSource(IApplication app, Wine wine, Note note) {
        if (app == null) {
            throw new IllegalArgumentException("app == null");
        }
        if (note == null) {
            throw new IllegalArgumentException("note == null");
        }
        this.m_app = app;
        if (wine == null) {
            throw new IllegalArgumentException("wine == null");
        }
        this.m_wine = wine;
        this.m_note = note;
        this.jScrollBar1.addAdjustmentListener(new AdjustmentListener(){

            public void adjustmentValueChanged(AdjustmentEvent e) {
                NoteLayer.this.notePane.setLocation(5, - e.getValue());
            }
        });
        this.notePane = new NotePane();
        this.notePane.setDataSource(this.m_app, this.m_note);
        this.notePane.setLocation(5, 0);
        this.notePane.setSize(573, 900);
        this.notePane.setMinimumSize(new Dimension(573, 900));
        this.centerPane.add(this.notePane);
        NoteCommandBarManager defaultBar = new NoteCommandBarManager(this.commandBarPane, this.m_app);
        this.performTranslation();
    }

    private void performTranslation() {
        this.save.setText(this.m_app.getRS("BTN_SAVE"));
        this.cancel.setText(this.m_app.getRS("BTN_CANCEL"));
    }

    public final int getAction() {
        return this.m_action;
    }

    public final Note getNote() {
        return this.notePane.getNote();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.save) {
            this.m_action = 0;
            this.setVisible(false);
        } else if (e.getSource() == this.cancel) {
            this.m_action = 1;
            this.setVisible(false);
        }
    }

    private void initComponents() {
        this.commandBarPane = new JPanel();
        this.centerPane = new JPanel();
        this.jScrollBar1 = new JScrollBar();
        this.bottomPane = new JPanel();
        this.save = new JButton();
        this.cancel = new JButton();
        this.commandBarPane.setPreferredSize(new Dimension(100, 50));
        GroupLayout commandBarPaneLayout = new GroupLayout((Container)this.commandBarPane);
        this.commandBarPane.setLayout((LayoutManager)commandBarPaneLayout);
        commandBarPaneLayout.setHorizontalGroup((GroupLayout.Group)commandBarPaneLayout.createParallelGroup(1).add(0, 600, 32767));
        commandBarPaneLayout.setVerticalGroup((GroupLayout.Group)commandBarPaneLayout.createParallelGroup(1).add(0, 50, 32767));
        this.getContentPane().add((Component)this.commandBarPane, "North");
        this.centerPane.setLayout(null);
        this.jScrollBar1.setMaximum(490);
        this.jScrollBar1.setUnitIncrement(50);
        this.centerPane.add(this.jScrollBar1);
        this.jScrollBar1.setBounds(583, 0, 17, 410);
        this.getContentPane().add((Component)this.centerPane, "Center");
        this.bottomPane.setPreferredSize(new Dimension(100, 70));
        this.save.setText("save");
        this.cancel.setText("annuler");
        GroupLayout bottomPaneLayout = new GroupLayout((Container)this.bottomPane);
        this.bottomPane.setLayout((LayoutManager)bottomPaneLayout);
        bottomPaneLayout.setHorizontalGroup((GroupLayout.Group)bottomPaneLayout.createParallelGroup(1).add(2, (GroupLayout.Group)bottomPaneLayout.createSequentialGroup().addContainerGap(306, 32767).add((Component)this.save, -2, 129, -2).add(31, 31, 31).add((Component)this.cancel, -2, 111, -2).add(23, 23, 23)));
        bottomPaneLayout.setVerticalGroup((GroupLayout.Group)bottomPaneLayout.createParallelGroup(1).add((GroupLayout.Group)bottomPaneLayout.createSequentialGroup().add(31, 31, 31).add((GroupLayout.Group)bottomPaneLayout.createParallelGroup(3).add((Component)this.cancel).add((Component)this.save)).addContainerGap(16, 32767)));
        this.getContentPane().add((Component)this.bottomPane, "South");
    }

}

