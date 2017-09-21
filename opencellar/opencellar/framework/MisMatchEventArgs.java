/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

public class MisMatchEventArgs {
    private byte m_cellarVersion;
    private byte m_appVersion;
    private String m_cellarPath;

    protected MisMatchEventArgs(byte cellarVersion, byte appVersion, String cellarPath) {
        this.m_appVersion = appVersion;
        this.m_cellarVersion = cellarVersion;
        this.m_cellarPath = cellarPath;
    }

    public byte getCellarVersion() {
        return this.m_cellarVersion;
    }

    public byte getAppVersion() {
        return this.m_appVersion;
    }

    public String getCellarPath() {
        return this.m_cellarPath;
    }

    public boolean isHighter() {
        return this.m_cellarVersion > this.m_appVersion;
    }

    public boolean isSmaller() {
        return this.m_cellarVersion < this.m_appVersion;
    }
}

