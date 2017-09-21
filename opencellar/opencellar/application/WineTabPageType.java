/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum WineTabPageType {
    Default(-1),
    Picture(4),
    General(0),
    PurchaseSales(2),
    Notes(3),
    RackItems(1);
    
    private int m_value;

    private WineTabPageType(int value) {
        this.m_value = value;
    }

    public final int getValue() {
        return this.m_value;
    }

    public static final WineTabPageType parse(int v) {
        WineTabPageType[] enums = WineTabPageType.class.getEnumConstants();
        for (int i = 0; i < enums.length; ++i) {
            if (enums[i].getValue() != v) continue;
            return enums[i];
        }
        return General;
    }
}

