/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

public final class Index {
    private long m_offset = 0;
    private String m_pk;
    private String m_fk;

    protected Index() {
    }

    public final long getOffset() {
        return this.m_offset;
    }

    public final void setOffset(long l) {
        this.m_offset = l;
    }

    public final String getPk() {
        return this.m_pk;
    }

    public final void setPk(String s) {
        this.m_pk = s;
    }

    public final String getFk() {
        return this.m_fk;
    }

    public final void setFk(String s) {
        this.m_fk = s;
    }
}

