
package net.spark.cellar;

import java.util.ArrayList;


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
       // this.m_items = new RackItemCollection(this);
    }

    public final ObjectType getType() {
        return ObjectType.Rack;
    }

    public final int getLength() {
        return 60;
    }

    public final RackItemCollection getItems() {
        return this.m_items;
    }

    public final int getColumns() {
        return this.m_columns;
    }

    protected final void setColumns(int b) {

            this.m_columns = b;

    }

    public final int getColumnSpace() {
        return this.m_columnSpace;
    }

    protected final void setColumnSpace(int b) {

            this.m_columnSpace = b;

    }

    public final int getRows() {
        return this.m_rows;
    }

    protected final void setRows(int b) {

            this.m_rows = b;
            
    }

    public final int getRowSpace() {
        return this.m_rowSpace;
    }

    protected final void setRowSpace(int b) {

            this.m_rowSpace = b;

    }

    public final int getOrder() {
        return this.m_order;
    }

    protected final void setOrder(int b) {

            this.m_order = b;

    }

    public final String getName() {
        return this.m_name;
    }

    protected final void setName(String s) {
        this.m_name = s;
        
    }

    public final RackType getRackType() {
        return this.m_rackType;
    }

    protected final void setRackType(RackType rt) {

            this.m_rackType = rt;
  
    }

    public final RackNamingType getRackNamingType() {
        return this.m_rackNamingType;
    }

	protected final void setRackNamingType(RackNamingType rt) {
        if (this.m_rackNamingType != rt) {
            this.m_rackNamingType = rt;
            
        }
    }

  



    public final boolean enableEvents() {
        return this.m_enableEvents;
    }

    public final void setEnableEvents(boolean value) {

            this.m_enableEvents = value;

    }

    public final RackItem get(int column, int row) {
        return this.m_items.get(column, row);
    }


 

    public final boolean exists(int column, int row) {
        return this.get(column, row) != null;
    }

    protected final RackItem createItem(int column, int row) {
        RackItem newItem = this.get(column, row);
/*        if (newItem == null) {
            newItem = new RackItem();
            newItem.setCellar(this.getCellar());
            newItem.setParent(this);
            newItem.setColumn(column);
            newItem.setRow(row);
            newItem.setState(ObjectState.New);
            this.m_items.add(newItem);
        }*/
        return newItem;
    }

    private Cellar getCellar() {
		// TODO Auto-generated method stub
		return null;
	}

	protected final void removeItem(int column, int row) {
        this.removeItem(this.get(column, row));
    }

    protected final void removeItem(RackItem item) {
/*        if (item != null) {
            item.consume();
            this.m_items.remove(item);
            item.delete();
        }*/
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
/*        if (this.isEmpty()) {
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
        }*/
    }

    protected boolean allowDelete() {
        return this.isEmpty();
    }

    public final boolean isEmpty() {
/*        for (int i = 0; i < this.m_items.size(); ++i) {
            if (this.m_items.get(i).isEmpty()) continue;
            return false;
        }*/
        return true;
    }

    public String toString() {
        return this.getName().trim();
    }
}

