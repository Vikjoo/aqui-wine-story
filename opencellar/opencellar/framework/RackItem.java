/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import opencellar.framework.BytesManager;
import opencellar.framework.Cellar;
import opencellar.framework.CellarObject;
import opencellar.framework.ObjectType;
import opencellar.framework.Rack;
import opencellar.framework.RackItemCollection;
import opencellar.framework.RackNamingType;
import opencellar.framework.Utils;
import opencellar.framework.Wine;

public final class RackItem
extends CellarObject {
    static final int LENGTH = 10;
    private String m_wineId = "0000";
    private Wine m_wine = null;
    private String m_parentId = "0000";
    private Rack m_parent = null;
    private int m_column = 0;
    private int m_row = 0;

    protected RackItem() {
    }

    public final ObjectType getType() {
        return ObjectType.RackItem;
    }

    public final int getLength() {
        return super.getLength() + 10;
    }

    public final Wine getWine() {
        if (this.m_wineId.equals("0000")) {
            return null;
        }
        if (this.m_wine == null) {
            this.m_wine = (Wine)this.getCellar().get(ObjectType.Wine, this.m_wineId);
        }
        return this.m_wine;
    }

    public final void setWine(Wine w) {
        if (w == null) {
            this.m_wineId = "0000";
            this.m_wine = null;
            super.setToUpdate();
        } else if (!w.getSystemUid().equals(this.m_wineId)) {
            this.m_wineId = w.getSystemUid();
            this.m_wine = w;
            super.setToUpdate();
        }
    }

    public final String getLegend() {
        return this.getLegend(this.m_row, true) + ";" + this.getLegend(this.m_column, false);
    }

    private final String getLegend(int value, boolean y) {
        String charToDraw = "";
        RackNamingType rnt = this.getParent().getRackNamingType();
        if (y) {
            if (rnt == RackNamingType.BothLetter || rnt == RackNamingType.NumericOnXLetterOnY) {
                charToDraw = String.valueOf((char)(65 + value));
            } else if (rnt == RackNamingType.BothNumeric || rnt == RackNamingType.LetterOnXNumericOnY) {
                charToDraw = String.valueOf(value + 1);
            }
        } else if (rnt == RackNamingType.BothLetter || rnt == RackNamingType.LetterOnXNumericOnY) {
            charToDraw = String.valueOf((char)(65 + value));
        } else if (rnt == RackNamingType.BothNumeric || rnt == RackNamingType.NumericOnXLetterOnY) {
            charToDraw = String.valueOf(value + 1);
        }
        if (charToDraw.equals("")) {
            charToDraw = String.valueOf(value);
        }
        return charToDraw;
    }

    protected final String getWineId() {
        return this.m_wineId;
    }

    public final Rack getParent() {
        return this.m_parent;
    }

    protected final void setParent(Rack r) {
        this.m_parent = r;
    }

    protected final String getParentId() {
        return this.m_parentId;
    }

    public final int getColumn() {
        return this.m_column;
    }

    protected final void setColumn(int b) {
        if (Utils.isValidByte(b)) {
            this.m_column = b;
            super.setToUpdate();
        }
    }

    public final int getRow() {
        return this.m_row;
    }

    protected final void setRow(int b) {
        if (Utils.isValidByte(b)) {
            this.m_row = b;
            super.setToUpdate();
        }
    }

    protected final void write(BytesManager m) {
        if (this.m_parent != null) {
            super.write(m);
            this.m_parentId = this.m_parent.getSystemUid();
            m.write(this.getParentId());
            m.write(this.getWineId());
            m.writeByteEx(this.getColumn());
            m.writeByteEx(this.getRow());
        }
    }

    protected final void read(BytesManager m) {
        super.read(m);
        this.m_parentId = m.getString(4);
        this.m_wineId = m.getString(4);
        this.m_column = m.getByteEx();
        this.m_row = m.getByteEx();
    }

    public final boolean isEmpty() {
        return this.getWine() == null;
    }

    public final void consume() {
        this.consume(true);
    }

    protected final void consume(boolean fireEvent) {
        if (!this.isEmpty()) {
            Wine w = this.getWine();
            this.setWine(null);
            if (w.getRackItems().contains(this)) {
                w.getRackItems().remove(this);
            }
            if (fireEvent) {
                w.notifyOnPropertyChanged("RackItems");
            }
            this.save();
            if (fireEvent) {
                this.getParent().notifyOnChange();
            }
        }
    }

    public final void copyTo(RackItem target) {
        if (target != null && target != this && !this.isEmpty()) {
            target.consume();
            target.setWine(this.getWine());
            target.save();
            this.getWine().getRackItems().add(target);
            this.getWine().notifyOnPropertyChanged("RackItems");
            target.getParent().notifyOnChange();
        }
    }

    public final void moveTo(RackItem target) {
        if (target != null && target != this && !this.isEmpty()) {
            target.consume();
            Wine wine = this.getWine();
            target.setWine(wine);
            target.save();
            wine.getRackItems().add(target);
            this.consume();
            wine.notifyOnPropertyChanged("RackItems");
            if (this.getParent() != target.getParent()) {
                target.getParent().notifyOnChange();
            }
        }
    }
}

