
package net.spark;



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
        return  10;
    }

    public final Wine getWine() {

        return this.m_wine;
    }

    public final void setWine(Wine w) {
     
            this.m_wine = w;
 
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

            this.m_column = b;

    }

    public final int getRow() {
        return this.m_row;
    }

    protected final void setRow(int b) {

            this.m_row = b;

    }



    public final boolean isEmpty() {
        return this.getWine() == null;
    }

    public final void consume() {
        this.consume(true);
    }

    protected final void consume(boolean fireEvent) {
/*        if (!this.isEmpty()) {
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
        }*/
    }

    public final void copyTo(RackItem target) {
/*        if (target != null && target != this && !this.isEmpty()) {
            target.consume();
            target.setWine(this.getWine());
            target.save();
            this.getWine().getRackItems().add(target);
            
            target.getParent().notifyOnChange();
        }*/
    }

    public final void moveTo(RackItem target) {
/*        if (target != null && target != this && !this.isEmpty()) {
            target.consume();
            Wine wine = this.getWine();
            target.setWine(wine);
            target.save();
            wine.getRackItems().add(target);
            this.consume();
            
            if (this.getParent() != target.getParent()) {
                target.getParent().notifyOnChange();
            }
        }*/
    }
}

