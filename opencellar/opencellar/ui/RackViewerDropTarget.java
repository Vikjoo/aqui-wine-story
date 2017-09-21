/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.RackItem
 */
package opencellar.ui;

import java.awt.Component;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetContext;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import javax.swing.JComponent;
import opencellar.framework.RackItem;
import opencellar.ui.RackViewerComponent;
import opencellar.ui.RackViewerEvent;

public class RackViewerDropTarget
implements DropTargetListener {
    DropTarget target;
    RackViewerComponent m_viewer;

    public RackViewerDropTarget(RackViewerComponent viewer) {
        this.m_viewer = viewer;
        this.target = new DropTarget(this.m_viewer, this);
    }

    private RackItem getRackItemForEvent(DropTargetDragEvent dtde) {
        Point p = dtde.getLocation();
        DropTargetContext dtc = dtde.getDropTargetContext();
        RackViewerComponent viewer = (RackViewerComponent)dtc.getComponent();
        return viewer.getItemAt(p);
    }

    public void dragEnter(DropTargetDragEvent dtde) {
        RackItem item = this.getRackItemForEvent(dtde);
        if (item == null) {
            dtde.rejectDrag();
        } else if (item.isEmpty()) {
            dtde.acceptDrag(dtde.getDropAction());
        } else {
            dtde.rejectDrag();
        }
    }

    public void dragOver(DropTargetDragEvent dtde) {
        RackItem item = this.getRackItemForEvent(dtde);
        if (item == null) {
            dtde.rejectDrag();
        } else if (item.isEmpty()) {
            dtde.acceptDrag(dtde.getDropAction());
        } else {
            dtde.rejectDrag();
        }
    }

    public void dragExit(DropTargetEvent dte) {
    }

    public void dropActionChanged(DropTargetDragEvent dtde) {
    }

    public void drop(DropTargetDropEvent dtde) {
        Point pt = dtde.getLocation();
        DropTargetContext dtc = dtde.getDropTargetContext();
        RackViewerComponent viewerTarget = (RackViewerComponent)dtc.getComponent();
        RackItem target = viewerTarget.getItemAt(pt);
        if (target == null) {
            dtde.rejectDrop();
            return;
        }
        if (!target.isEmpty()) {
            dtde.rejectDrop();
            return;
        }
        try {
            Transferable tr = dtde.getTransferable();
            DataFlavor[] flavors = tr.getTransferDataFlavors();
            for (int i = 0; i < flavors.length; ++i) {
                if (!tr.isDataFlavorSupported(flavors[i])) continue;
                dtde.acceptDrop(dtde.getDropAction());
                RackItem source = (RackItem)tr.getTransferData(flavors[i]);
                int action = dtde.getDropAction();
                if ((action & 1) != 0) {
                    RackViewerEvent evt = new RackViewerEvent(viewerTarget, source, target, 0, dtde.getLocation());
                    viewerTarget.notifyOnDrop(evt);
                } else if ((action & 2) != 0) {
                    RackViewerEvent evt = new RackViewerEvent(viewerTarget, source, target, 1, dtde.getLocation());
                    viewerTarget.notifyOnDrop(evt);
                }
                dtde.dropComplete(true);
                return;
            }
            dtde.rejectDrop();
        }
        catch (Exception e) {
            e.printStackTrace();
            dtde.rejectDrop();
        }
    }
}

