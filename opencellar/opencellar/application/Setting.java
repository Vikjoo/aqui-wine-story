/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

public final class Setting {
    private String m_key = null;
    private String m_value = null;

    protected Setting(String key, String value) {
        this.m_key = key;
        this.m_value = value;
    }

    public final String getKey() {
        return this.m_key;
    }

    public final void setValue(String value) {
        this.m_value = value;
    }

    public final String getValue() {
        return this.m_value;
    }
}

