
package net.spark.cellar;


public enum RackNamingType {
    None(1),
    BothLetter(2),
    BothNumeric(4),
    LetterOnXNumericOnY(8),
    NumericOnXLetterOnY(16);
    
    private int m_value = 0;

    private RackNamingType(int b) {
        this.m_value = b;
    }

    public final int getValue() {
        return this.m_value;
    }

    public final String toString() {
        return String.valueOf(this.m_value);
    }

    public static final RackNamingType parse(int b) {
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

