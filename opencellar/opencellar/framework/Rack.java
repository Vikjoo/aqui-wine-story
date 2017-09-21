/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import java.util.ArrayList;
import opencellar.framework.BytesManager;
import opencellar.framework.Cellar;
import opencellar.framework.CellarObject;
import opencellar.framework.IRackListener;
import opencellar.framework.ObjectState;
import opencellar.framework.ObjectType;
import opencellar.framework.RackItem;
import opencellar.framework.RackItemCollection;
import opencellar.framework.RackNamingType;
import opencellar.framework.RackType;
import opencellar.framework.Utils;

public final class Rack
extends CellarObject {
    static final int LENGTH = 60;
    private RackItemCollection m_items;
    private int m_columns = 0;
    private int m_columnSpace = 0;
    private int m_rows = 0;
    private int m_rowSpace = 0;
    private int m_order = 1;
    private String m_name = "";
    protected boolean controlByBuilder = false;
    private RackType m_rackType = RackType.Default;
    private RackNamingType m_rackNamingType = RackNamingType.BothLetter;
    static final byte RESERVED = 0;
    private boolean m_enableEvents = true;
    private ArrayList m_listeners = new ArrayList();

    protected Rack() {
        this.m_items = new RackItemCollection(this);
    }

    public final ObjectType getType() {
        return ObjectType.Rack;
    }

    public final int getLength() {
        return super.getLength() + 60;
    }

    public final RackItemCollection getItems() {
        return this.m_items;
    }

    public final int getColumns() {
        return this.m_columns;
    }

    protected final void setColumns(int b) {
        if (Utils.isValidByte(b) && this.m_columns != b) {
            this.m_columns = b;
            super.setToUpdate();
        }
    }

    public final int getColumnSpace() {
        return this.m_columnSpace;
    }

    protected final void setColumnSpace(int b) {
        if (Utils.isValidByte(b) && this.m_columnSpace != b) {
            this.m_columnSpace = b;
            super.setToUpdate();
        }
    }

    public final int getRows() {
        return this.m_rows;
    }

    protected final void setRows(int b) {
        if (Utils.isValidByte(b) && this.m_rows != b) {
            this.m_rows = b;
            super.setToUpdate();
        }
    }

    public final int getRowSpace() {
        return this.m_rowSpace;
    }

    protected final void setRowSpace(int b) {
        if (Utils.isValidByte(b) && this.m_rowSpace != b) {
            this.m_rowSpace = b;
            super.setToUpdate();
        }
    }

    public final int getOrder() {
        return this.m_order;
    }

    protected final void setOrder(int b) {
        if (Utils.isValidByte(b) && this.m_order != b) {
            this.m_order = b;
            super.setToUpdate();
        }
    }

    public final String getName() {
        return this.m_name;
    }

    protected final void setName(String s) {
        this.m_name = Utils.ensureCapacity(s, 50);
        super.setToUpdate();
    }

    public final RackType getRackType() {
        return this.m_rackType;
    }

    protected final void setRackType(RackType rt) {
        if (this.m_rackType != rt) {
            this.m_rackType = rt;
            super.setToUpdate();
        }
    }

    public final RackNamingType getRackNamingType() {
        return this.m_rackNamingType;
    }

    protected final void setRackNamingType(RackNamingType rt) {
        if (this.m_rackNamingType != rt) {
            this.m_rackNamingType = rt;
            super.setToUpdate();
        }
    }

    protected final void write(BytesManager m) {
        if (this.controlByBuilder) {
            super.write(m);
            m.writeByteEx(this.getColumns());
            m.writeByteEx(this.getRows());
            m.writeByteEx(this.getRowSpace());
            m.writeByteEx(this.getColumnSpace());
            m.write(this.getRackType().getValue());
            m.write(this.getRackNamingType().getValue());
            m.write(Utils.padRight(this.getName(), 50, " "));
            m.writeByteEx(this.getOrder());
            m.write(0);
            m.write(0);
            m.write(0);
        }
    }

    protected final void read(BytesManager m) {
        super.read(m);
        this.setColumns(m.getByteEx());
        this.setRows(m.getByteEx());
        this.setRowSpace(m.getByteEx());
        this.setColumnSpace(m.getByteEx());
        this.setRackType(RackType.parse(m.getByte()));
        this.setRackNamingType(RackNamingType.parse(m.getByte()));
        this.setName(m.getString(50));
        this.setOrder(m.getByte());
    }

    public final boolean enableEvents() {
        return this.m_enableEvents;
    }

    public final void setEnableEvents(boolean value) {
        if (this.m_enableEvents != value) {
            this.m_enableEvents = value;
        }
    }

    public final RackItem get(int column, int row) {
        return this.m_items.get(column, row);
    }

    protected final void notifyOnChange() {
        if (this.enableEvents()) {
            for (int i = 0; i < this.m_listeners.size(); ++i) {
                IRackListener listener = (IRackListener)this.m_listeners.get(i);
                if (listener == null) continue;
                listener.onChange(this);
            }
        }
    }

    public final void addListener(IRackListener listener) {
        if (listener != null) {
            this.m_listeners.add(listener);
        }
    }

    public final void removeListener(IRackListener listener) {
        if (listener != null) {
            this.m_listeners.remove(listener);
        }
    }

    public final boolean exists(int column, int row) {
        return this.get(column, row) != null;
    }

    protected final RackItem createItem(int column, int row) {
        RackItem newItem = this.get(column, row);
        if (newItem == null) {
            newItem = new RackItem();
            newItem.setCellar(this.getCellar());
            newItem.setParent(this);
            newItem.setColumn(column);
            newItem.setRow(row);
            newItem.setState(ObjectState.New);
            this.m_items.add(newItem);
        }
        return newItem;
    }

    protected final void removeItem(int column, int row) {
        this.removeItem(this.get(column, row));
    }

    protected final void removeItem(RackItem item) {
        if (item != null) {
            item.consume();
            this.m_items.remove(item);
            item.delete();
        }
    }

    protected final boolean canRemoveRows(int start) {
        for (int row = start; row < this.m_rows; ++row) {
            for (int column = 0; column < this.m_columns; ++column) {
                RackItem item = this.get(column, row);
                if (item == null || item.isEmpty()) continue;
                return false;
            }
        }
        return true;
    }

    protected final void trimToRows(int start) {
        for (int row = start; row < this.m_rows; ++row) {
            for (int column = 0; column < this.m_columns; ++column) {
                this.removeItem(column, row);
            }
        }
    }

    protected final boolean canRemoveColumns(int start) {
        for (int column = start; column < this.m_columns; ++column) {
            for (int row = 0; row < this.m_rows; ++row) {
                RackItem item = this.get(column, row);
                if (item == null || item.isEmpty()) continue;
                return false;
            }
        }
        return true;
    }

    protected final void trimToColumns(int start) {
        for (int column = start; column < this.m_columns; ++column) {
            for (int row = 0; row < this.m_rows; ++row) {
                this.removeItem(column, row);
            }
        }
    }

    public void delete() {
        if (this.isEmpty()) {
            this.controlByBuilder = true;
            this.m_enableEvents = false;
            for (int i = 0; i < this.m_items.size(); ++i) {
                this.m_items.get(i).setState(ObjectState.Delete);
                this.m_items.get(i).save();
            }
            this.m_enableEvents = false;
            this.m_items.clear();
            super.delete();
            this.controlByBuilder = false;
        }
    }

    protected boolean allowDelete() {
        return this.isEmpty();
    }

    public final boolean isEmpty() {
        for (int i = 0; i < this.m_items.size(); ++i) {
            if (this.m_items.get(i).isEmpty()) continue;
            return false;
        }
        return true;
    }

    public String toString() {
        return this.getName().trim();
    }
}

