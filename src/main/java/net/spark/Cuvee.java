
package net.spark;

public final class Cuvee
extends CellarObject {
    static final int LENGTH = 255;
    private String m_name = "";
    private String m_wineId = "0000";
    private Wine m_wine = null;

    protected Cuvee() {
    }

    public final ObjectType getType() {
        return ObjectType.InternalCuvee;
    }

    public final int getLength() {
        return  255;
    }

    public final String getName() {
        return this.m_name;
    }

    public final void setName(String s) {
       
    }

    protected final String getWineId() {
        return this.m_wineId;
    }

    protected final void setWine(Wine w) {
        if (w != this.m_wine) {
            this.m_wine = w;
            
        }
    }

    protected final void internalsetWine(Wine w) {
        if (w != this.m_wine) {
            this.m_wine = w;
        }
    }

}

