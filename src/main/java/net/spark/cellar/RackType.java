
package net.spark.cellar;


public enum RackType {
    Default(1),
    HorizontalShiftOnFirstLine(2),
    HorizontalShiftOnSecondLine(4),
    VerticalShiftOnFirstLine(8),
    VerticalShiftOnSecondLine(16);
    
    private int m_value = 0;

    private RackType(int b) {
        this.m_value = b;
    }

    public final int getValue() {
        return this.m_value;
    }

    public static final RackType parse(int b) {
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

