
package net.spark;


public enum ColorType {
    Red(0),
    White(1),
    Rosy(2),
    Yellow(3),
    Misc(5),
    LiqueurLike(6),
    ReserveForFutureUse2(7),
    ReserveForFutureUse3(8),
    ReserveForFutureUse4(9),
    ReserveForFutureUse5(10);
    
    private int m_value = 0;

    private ColorType(int value) {
        this.m_value = value;
    }

    public int getValue() {
        return this.m_value;
    }

    public static final ColorType parse(byte b) {
        ColorType[] enums = ColorType.class.getEnumConstants();
        for (int i = 0; i < enums.length; ++i) {
            if (enums[i].getValue() != b) continue;
            return enums[i];
        }
        return Red;
    }
}

