/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import opencellar.framework.BytesManager;
import opencellar.framework.Cellar;
import opencellar.framework.CellarObject;
import opencellar.framework.IIndexable;
import opencellar.framework.ObjectState;
import opencellar.framework.ObjectType;
import opencellar.framework.TypeOfWine;
import opencellar.framework.Utils;
import opencellar.framework.Wine;

public final class Assembly
extends CellarObject
implements IIndexable {
    private static final int LENGTH = 11;
    private int m_percent = 0;
    private String m_wineId = "0000";
    private String m_cepageId = "0000";
    static final byte RESERVED = 0;

    protected Assembly() {
    }

    public final ObjectType getType() {
        return ObjectType.Assembly;
    }

    public final int getLength() {
        return super.getLength() + 11;
    }

    public final int getPercent() {
        return this.m_percent;
    }

    public final void setPercent(int b) {
        if (Utils.isValidByte(b) && this.m_percent != b && b < 101 && b > 0) {
            this.m_percent = b;
            super.setToUpdate();
        }
    }

    public final void setWine(Wine w) {
        this.m_wineId = w == null ? "0000" : w.getSystemUid();
        super.setToUpdate();
    }

    public final Wine getWine() {
        if (this.m_wineId.equals("0000")) {
            return null;
        }
        Wine w = (Wine)this.getCellar().get(ObjectType.Wine, this.m_wineId);
        if (w == null) {
            this.m_wineId = "0000";
        }
        return w;
    }

    public final void setCepage(TypeOfWine tow) {
        this.m_cepageId = tow == null ? "0000" : tow.getSystemUid();
        super.setToUpdate();
    }

    public final TypeOfWine getCepage() {
        if (this.m_cepageId.equals("0000")) {
            return TypeOfWine.getEmpty();
        }
        TypeOfWine tow = (TypeOfWine)this.getCellar().get(ObjectType.TypeOfWine, this.m_cepageId);
        if (tow == null) {
            this.m_cepageId = "0000";
            tow = TypeOfWine.getEmpty();
        }
        return tow;
    }

    public final String getFk() {
        return this.m_wineId;
    }

    public final void markAsDeleted() {
        super.setState(ObjectState.Delete);
    }

    protected final void write(BytesManager m) {
        super.write(m);
        m.write(this.m_wineId);
        m.write(this.m_cepageId);
        m.write((byte)this.getPercent());
        m.write(0);
        m.write(0);
    }

    protected final void read(BytesManager m) {
        super.read(m);
        this.m_wineId = m.getString(4);
        this.m_cepageId = m.getString(4);
        this.setPercent(m.getByteEx());
    }
}

