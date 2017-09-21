/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import opencellar.framework.Cellar;
import opencellar.framework.ObjectState;
import opencellar.framework.Rack;
import opencellar.framework.RackItem;
import opencellar.framework.RackNamingType;
import opencellar.framework.RackType;

public final class RackBuilder {
    private Rack m_rack;
    private boolean m_enableEvents;

    public RackBuilder(Rack rack) {
        this.m_rack = rack;
        if (this.m_rack != null) {
            this.m_enableEvents = this.m_rack.enableEvents();
            this.m_rack.setEnableEvents(false);
        }
    }

    public RackBuilder(Cellar c) {
        if (c != null) {
            this.m_rack = new Rack();
            this.m_rack.setCellar(c);
            this.m_rack.setName("Empty rack");
            this.m_rack.setRows(10);
            this.m_rack.setColumns(10);
            this.m_rack.setRowSpace(10);
            this.m_rack.setColumnSpace(10);
            this.m_rack.setRackNamingType(RackNamingType.BothLetter);
            this.m_rack.setRackType(RackType.Default);
            this.m_rack.setState(ObjectState.New);
        }
    }

    public final Rack getRack() {
        return this.m_rack;
    }

    public final void close() {
        if (this.m_rack != null) {
            this.m_rack.setEnableEvents(this.m_enableEvents);
        }
    }

    public final RackItem createItem(int row, int column) {
        if (this.m_rack == null) {
            return null;
        }
        return this.m_rack.createItem(column, row);
    }

    public final void removeItem(int row, int column) {
        if (this.m_rack == null) {
            return;
        }
        this.m_rack.removeItem(column, row);
    }

    public final void save() {
        if (this.m_rack == null) {
            return;
        }
        this.m_rack.controlByBuilder = true;
        this.m_rack.save();
        this.m_rack.controlByBuilder = false;
    }

    public final void setSpace(int columnSpace, int rowSpace) {
        if (this.m_rack == null) {
            return;
        }
        this.m_rack.setColumnSpace(columnSpace);
        this.m_rack.setRowSpace(rowSpace);
    }

    public final void setRackType(RackType rt) {
        if (this.m_rack == null) {
            return;
        }
        this.m_rack.setRackType(rt);
    }

    public final void setRackNamingType(RackNamingType rnt) {
        if (this.m_rack == null) {
            return;
        }
        this.m_rack.setRackNamingType(rnt);
    }

    public final void setName(String s) {
        if (this.m_rack == null) {
            return;
        }
        this.m_rack.setName(s);
    }

    public final boolean setRowSize(int row) {
        if (this.m_rack == null) {
            return false;
        }
        boolean success = false;
        if (row != this.m_rack.getRows() && row > 0) {
            if (row < this.m_rack.getRows()) {
                if (this.m_rack.canRemoveRows(row)) {
                    this.m_rack.trimToRows(row);
                    this.m_rack.setRows(row);
                    success = true;
                }
            } else {
                this.m_rack.setRows(row);
                success = true;
            }
        }
        return success;
    }

    public final boolean setColumnSize(int column) {
        if (this.m_rack == null) {
            return false;
        }
        boolean success = false;
        if (column != this.m_rack.getColumns() && column > 0) {
            if (column < this.m_rack.getColumns()) {
                if (this.m_rack.canRemoveColumns(column)) {
                    this.m_rack.trimToColumns(column);
                    this.m_rack.setColumns(column);
                    success = true;
                }
            } else {
                this.m_rack.setColumns(column);
                success = true;
            }
        }
        return success;
    }

    public final boolean canResizeColumn(int column) {
        if (this.m_rack == null) {
            return false;
        }
        if (column < -1) {
            return false;
        }
        if (column == this.m_rack.getColumns()) {
            return true;
        }
        boolean success = false;
        if (column < this.m_rack.getColumns()) {
            if (this.m_rack.canRemoveColumns(column)) {
                success = true;
            }
        } else {
            success = true;
        }
        return success;
    }

    public final boolean canResizeRow(int row) {
        if (this.m_rack == null) {
            return false;
        }
        if (row < -1) {
            return false;
        }
        if (row == this.m_rack.getRows()) {
            return true;
        }
        boolean success = false;
        if (row < this.m_rack.getRows()) {
            if (this.m_rack.canRemoveRows(row)) {
                success = true;
            }
        } else {
            success = true;
        }
        return success;
    }

    public final void setOrder(int b) {
        if (this.m_rack == null) {
            return;
        }
        this.m_rack.setOrder(b);
    }

    public final void notifyUpdate() {
        if (this.m_rack != null) {
            this.m_rack.notifyOnChange();
        }
    }
}

