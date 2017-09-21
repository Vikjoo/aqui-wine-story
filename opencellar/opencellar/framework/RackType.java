/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum RackType {
    Default(1),
    HorizontalShiftOnFirstLine(2),
    HorizontalShiftOnSecondLine(4),
    VerticalShiftOnFirstLine(8),
    VerticalShiftOnSecondLine(16);
    
    private byte m_value = 0;

    private RackType(byte b) {
        this.m_value = b;
    }

    public final byte getValue() {
        return this.m_value;
    }

    public static final RackType parse(byte b) {
        RackType rt = Default;
        switch (b) {
            case 2: {
                rt = HorizontalShiftOnFirstLine;
                break;
            }
            case 4: {
                rt = HorizontalShiftOnSecondLine;
                break;
            }
            case 8: {
                rt = VerticalShiftOnFirstLine;
                break;
            }
            case 16: {
                rt = VerticalShiftOnSecondLine;
            }
        }
        return rt;
    }

    public final String toString() {
        return String.valueOf(this.m_value);
    }
}

