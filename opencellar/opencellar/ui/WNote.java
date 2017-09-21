/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Note
 *  opencellar.framework.NoteWorkqueue
 *  opencellar.framework.NoteWorkqueueItem
 *  opencellar.framework.Wine
 *  opencellar.framework.WorkqueueItemType
 *  org.jdesktop.layout.GroupLayout
 *  org.jdesktop.layout.GroupLayout$Group
 *  org.jdesktop.layout.GroupLayout$ParallelGroup
 */
package opencellar.ui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import opencellar.application.DialogResult;
import opencellar.application.IApplication;
import opencellar.application.MessageButtonType;
import opencellar.application.MessageIconType;
import opencellar.application.MessageType;
import opencellar.framework.Note;
import opencellar.framework.NoteWorkqueue;
import opencellar.framework.NoteWorkqueueItem;
import opencellar.framework.Wine;
import opencellar.framework.WorkqueueItemType;
import opencellar.ui.CustomEvent;
import opencellar.ui.CustomEventDispatcher;
import opencellar.ui.ICustomListener;
import opencellar.ui.OCLine;
import opencellar.ui.TextNoteViewer;
import org.jdesktop.layout.GroupLayout;

public final class WNote
extends JPanel
implements ActionListener,
ICustomListener {
    private CustomEventDispatcher m_dispatcher = null;
    private IApplication m_app;
    private Wine m_wine;
    private JPopupMenu popupMenu = null;
    private JMenuItem open;
    private JMenuItem delete;
    private JMenuItem cancelDelete;
    private TextNoteViewer viewer;
    private Note m_note;
    private JScrollPane jScrollPane1;
    private JLabel newNoteLabel;
    private OCLine oCLine1;

    public WNote() {
        this.initComponents();
    }

    public void setDatasource(IApplication app, Wine w, CustomEventDispatcher dispatcher) {
        if (app == null) {
            throw new IllegalArgumentException("app == null");
        }
        if (w == null) {
            throw new IllegalArgumentException("wine == null");
        }
        if (dispatcher == null) {
            throw new IllegalArgumentException("dispatcher == null");
        }
        this.m_dispatcher = dispatcher;
        this.m_wine = w;
        this.m_app = app;
        this.viewer = new TextNoteViewer(this.m_wine);
        this.viewer.setDispatcher(this.m_dispatcher);
        this.m_dispatcher.add(this);
        this.jScrollPane1.setViewportView(this.viewer);
        this.popupMenu = new JPopupMenu();
        this.open = new JMenuItem(this.m_app.getRS("SALES_MNU_OPEN"));
        this.delete = new JMenuItem(this.m_app.getRS("SALES_MNU_DELETE"));
        this.cancelDelete = new JMenuItem(this.m_app.getRS("SALES_MNU_CANCEL_DELETE"));
        this.popupMenu.add(this.open);
        this.popupMenu.addSeparator();
        this.popupMenu.add(this.delete);
        this.popupMenu.add(this.cancelDelete);
        this.open.addActionListener(this);
        this.delete.addActionListener(this);
        this.cancelDelete.addActionListener(this);
        this.performTranslation();
        this.newNoteLabel.setCursor(Cursor.getPredefinedCursor(12));
        this.newNoteLabel.addMouseListener(new MouseListener(){

            public void mouseClicked(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    WNote.this.m_dispatcher.setSource(null);
                    WNote.this.m_dispatcher.notify(10);
                }
            }

            public void mouseReleased(MouseEvent e) {
            }
        });
    }

    public void close() {
        this.viewer.close();
    }

    private void performTranslation() {
        this.newNoteLabel.setText(this.m_app.getRS("SALES_NEW"));
    }

    public void eventDispatched(CustomEvent evt) {
        if (evt.getEventId() == 12) {
            this.m_note = (Note)evt.getSource();
            this.showPopup(this.m_note);
        }
    }

    private void showPopup(Note note) {
        WorkqueueItemType type = this.m_wine.getNoteQueue().getType(note);
        if (type == WorkqueueItemType.Delete) {
            this.delete.setVisible(false);
            this.cancelDelete.setVisible(true);
        } else {
            this.delete.setVisible(true);
            this.cancelDelete.setVisible(false);
        }
        this.popupMenu.show(this.viewer, this.viewer.getLastPoint().x, this.viewer.getLastPoint().y);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.open) {
            this.m_dispatcher.setSource((Object)this.m_note);
            this.m_dispatcher.notify(11);
        } else if (e.getSource() == this.cancelDelete) {
            this.m_wine.getNoteQueue().remove(WorkqueueItemType.Delete, this.m_note);
        } else if (e.getSource() == this.delete && this.m_app.showMessage(null, this.m_app.getRS("SALES_DELETE_DIALOG"), MessageType.Confirm, MessageIconType.Question, MessageButtonType.YesNo) == DialogResult.Yes) {
            this.m_wine.getNoteQueue().set(new NoteWorkqueueItem(this.m_note, WorkqueueItemType.Delete));
        }
    }

    private void initComponents() {
        this.jScrollPane1 = new JScrollPane();
        this.oCLine1 = new OCLine();
        this.newNoteLabel = new JLabel();
        this.setLayout(null);
        this.jScrollPane1.setHorizontalScrollBarPolicy(31);
        this.add(this.jScrollPane1);
        this.jScrollPane1.setBounds(10, 50, 520, 400);
        GroupLayout oCLine1Layout = new GroupLayout((Container)this.oCLine1);
        this.oCLine1.setLayout((LayoutManager)oCLine1Layout);
        oCLine1Layout.setHorizontalGroup((GroupLayout.Group)oCLine1Layout.createParallelGroup(1).add(0, 550, 32767));
        oCLine1Layout.setVerticalGroup((GroupLayout.Group)oCLine1Layout.createParallelGroup(1).add(0, 10, 32767));
        this.add(this.oCLine1);
        this.oCLine1.setBounds(0, 30, 550, 10);
        this.newNoteLabel.setText("## cliquez ici pour cr\u00e9er une nouvelle fiche");
        this.add(this.newNoteLabel);
        this.newNoteLabel.setBounds(10, 10, 470, 20);
    }

}

