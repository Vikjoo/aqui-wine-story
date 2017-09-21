/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import opencellar.framework.BytesManager;
import opencellar.framework.CellarObject;
import opencellar.framework.ObjectType;
import opencellar.framework.Utils;
import opencellar.framework.Wine;

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
        return super.getLength() + 255;
    }

    public final String getName() {
        return this.m_name;
    }

    public final void setName(String s) {
        this.m_name = Utils.ensureCapacity(s, 250);
        super.setToUpdate();
    }

    protected final String getWineId() {
        return this.m_wineId;
    }

    protected final void setWine(Wine w) {
        if (w != this.m_wine) {
            this.m_wine = w;
            super.setToUpdate();
        }
    }

    protected final void internalsetWine(Wine w) {
        if (w != this.m_wine) {
            this.m_wine = w;
        }
    }

    protected final void write(BytesManager m) {
        this.m_wineId = this.m_wine.getSystemUid();
        super.write(m);
        m.write(this.m_wineId);
        m.write(Utils.padRight(this.getName(), 250, " "));
        m.write(1);
    }

    protected final void read(BytesManager m) {
        super.read(m);
        this.m_wineId = m.getString(4);
        this.m_name = m.getString(250);
    }
}

