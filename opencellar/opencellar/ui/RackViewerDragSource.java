/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.RackItem
 */
package opencellar.ui;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import javax.swing.JComponent;
import opencellar.framework.RackItem;
import opencellar.ui.RackViewerComponent;
import opencellar.ui.RackViewerEvent;
import opencellar.ui.TransferableRackItem;

public class RackViewerDragSource
implements DragSourceListener,
DragGestureListener {
    DragSource source;
    DragGestureRecognizer recognizer;
    TransferableRackItem transferable;
    RackItem m_oldItem;
    RackViewerComponent m_viewer;

    public RackViewerDragSource(RackViewerComponent viewer, int actions) {
        this.m_viewer = viewer;
        this.source = new DragSource();
        this.recognizer = this.source.createDefaultDragGestureRecognizer(this.m_viewer, actions, this);
    }

    public void dragGestureRecognized(DragGestureEvent dge) {
        RackItem item = this.m_viewer.getWorkingItem();
        if (item == null) {
            return;
        }
        if (item.isEmpty()) {
            return;
        }
        this.m_oldItem = item;
        this.transferable = new TransferableRackItem(item);
        this.source.startDrag(dge, DragSource.DefaultMoveDrop, this.transferable, this);
        RackViewerEvent evt = new RackViewerEvent(this.m_viewer);
        this.m_viewer.notifyOnBeginDrop(evt);
    }

    public void dragEnter(DragSourceDragEvent dsde) {
    }

    public void dragExit(DragSourceEvent dse) {
    }

    public void dragOver(DragSourceDragEvent dsde) {
    }

    public void dropActionChanged(DragSourceDragEvent dsde) {
    }

    public void dragDropEnd(DragSourceDropEvent dsde) {
    }
}

