/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum WorkqueueItemType {
    Unknow(0),
    Add(1),
    Delete(2);
    
    private int m_value = 0;

    private WorkqueueItemType(int value) {
        this.m_value = value;
    }

    public final int getValue() {
        return this.m_value;
    }
}

