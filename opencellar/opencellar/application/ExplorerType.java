/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum ExplorerType {
    Home(1);
    
    private int m_value = 0;

    private ExplorerType(int value) {
        this.m_value = value;
    }

    protected final int getValue() {
        return this.m_value;
    }
}
