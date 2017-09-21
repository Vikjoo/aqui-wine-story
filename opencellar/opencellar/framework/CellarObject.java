/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import java.util.ArrayList;
import opencellar.framework.BytesManager;
import opencellar.framework.Cellar;
import opencellar.framework.ICellarObjectListener;
import opencellar.framework.ObjectState;
import opencellar.framework.ObjectType;

public abstract class CellarObject {
    private Cellar m_cellar;
    private String m_systemUid = "1111";
    private ObjectState m_state = ObjectState.None;
    private ArrayList<ICellarObjectListener> m_listeners = new ArrayList();
    private long m_offset = -1;

    protected CellarObject() {
    }

    public final Cellar getCellar() {
        return this.m_cellar;
    }

    protected final void setCellar(Cellar cellar) {
        this.m_cellar = cellar;
    }

    public final String getSystemUid() {
        return this.m_systemUid;
    }

    protected final void setSystemUid(String key) {
        this.m_systemUid = key;
    }

    protected final void setState(ObjectState state) {
        if (this.m_state != ObjectState.New) {
            this.m_state = state;
            this.notifyOnStateChanged();
        }
    }

    protected final void setToNone() {
        if (this.m_state != ObjectState.Delete && this.m_state != ObjectState.None) {
            this.m_state = ObjectState.None;
            this.notifyOnStateChanged();
        }
    }

    protected final void setToUpdate() {
        this.setState(ObjectState.Update);
    }

    public ObjectState getState() {
        return this.m_state;
    }

    public void save() {
        if (this.m_state != ObjectState.None) {
            this.getCellar().save(this);
        }
    }

    private void notifyOnStateChanged() {
        for (ICellarObjectListener listener : this.m_listeners) {
            if (listener == null) continue;
            listener.onStateChanged(this);
        }
    }

    protected final void notifyOnPropertyChanged(String propertyName) {
        for (ICellarObjectListener listener : this.m_listeners) {
            if (listener == null) continue;
            listener.onPropertyChanged(this, propertyName);
        }
    }

    public void addListener(ICellarObjectListener listener) {
        if (listener != null) {
            this.m_listeners.add(listener);
        }
    }

    public void removeListener(ICellarObjectListener listener) {
        if (listener != null) {
            this.m_listeners.remove(listener);
        }
    }

    public abstract ObjectType getType();

    protected final long getOffset() {
        return this.m_offset;
    }

    protected final void setOffset(long value) {
        this.m_offset = value;
    }

    protected int getLength() {
        return 4;
    }

    protected boolean allowUpdate() {
        return true;
    }

    protected boolean allowDelete() {
        return true;
    }

    protected void write(BytesManager m) {
        m.write(this.m_systemUid);
    }

    protected void read(BytesManager m) {
        this.m_systemUid = m.getString(4);
    }

    public void delete() {
        if (this.allowDelete()) {
            this.setState(ObjectState.Delete);
            this.save();
        }
    }

    public final boolean isSameCellar(CellarObject co) {
        if (co == null) {
            return false;
        }
        return this.getCellar() == co.getCellar();
    }
}

