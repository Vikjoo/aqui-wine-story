/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum RackNamingType {
    None(1),
    BothLetter(2),
    BothNumeric(4),
    LetterOnXNumericOnY(8),
    NumericOnXLetterOnY(16);
    
    private byte m_value = 0;

    private RackNamingType(byte b) {
        this.m_value = b;
    }

    public final byte getValue() {
        return this.m_value;
    }

    public final String toString() {
        return String.valueOf(this.m_value);
    }

    public static final RackNamingType parse(byte b) {
        RackNamingType rt = None;
        switch (b) {
            case 2: {
                rt = BothLetter;
                break;
            }
            case 4: {
                rt = BothNumeric;
                break;
            }
            case 8: {
                rt = LetterOnXNumericOnY;
                break;
            }
            case 16: {
                rt = NumericOnXLetterOnY;
            }
        }
        return rt;
    }
}

