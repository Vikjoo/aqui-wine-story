/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.RackItem
 */
package opencellar.ui;

import java.awt.Point;
import javax.swing.JComponent;
import opencellar.framework.RackItem;

public final class RackViewerEvent {
    public static final int COPY = 0;
    public static final int MOVE = 1;
    private RackItem m_risource = null;
    private RackItem m_target = null;
    private int m_dragDropType = 0;
    private JComponent m_source;
    private Point m_location;

    public RackViewerEvent(JComponent source) {
        this.m_source = source;
    }

    public RackViewerEvent(JComponent source, Point location) {
        this.m_source = source;
        this.m_location = location;
    }

    public RackViewerEvent(JComponent source, RackItem target, int dragDropType, Point location) {
        this.m_source = source;
        this.m_location = location;
        this.m_dragDropType = dragDropType;
        this.m_target = target;
    }

    public RackViewerEvent(JComponent comp, RackItem source, RackItem target, int dragDropType, Point location) {
        this.m_source = comp;
        this.m_location = location;
        this.m_dragDropType = dragDropType;
        this.m_target = target;
        this.m_risource = source;
    }

    public final RackItem getRackItemSource() {
        return this.m_risource;
    }

    public final RackItem getRackItemTarget() {
        return this.m_target;
    }

    public final int getDragDropType() {
        return this.m_dragDropType;
    }

    public final JComponent getSource() {
        return this.m_source;
    }

    public final Point getLocation() {
        return this.m_location;
    }
}

