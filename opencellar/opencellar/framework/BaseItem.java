/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import opencellar.framework.BytesManager;
import opencellar.framework.CellarObject;
import opencellar.framework.ObjectType;
import opencellar.framework.Utils;

public class BaseItem
extends CellarObject {
    static final int LENGTH = 51;
    private String m_name = "";

    protected BaseItem() {
    }

    public ObjectType getType() {
        return ObjectType.None;
    }

    public final int getLength() {
        return super.getLength() + 51;
    }

    protected final void write(BytesManager m) {
        super.write(m);
        m.write(Utils.padRight(this.getName(), 50, " "));
        m.write(true);
    }

    protected final void read(BytesManager m) {
        super.read(m);
        this.setName(m.getString(50));
    }

    public final String getName() {
        return this.m_name;
    }

    public final void setName(String s) {
        this.m_name = Utils.ensureCapacity(s, 50);
        super.setToUpdate();
    }

    public final String toString() {
        return this.m_name.trim();
    }

    public boolean isEmpty() {
        return super.getSystemUid().equals("0000");
    }
}

