
package net.spark;


public enum CarafageType {
    Zero(1),
    Half(2),
    One(3),
    Two(4),
    Three(5),
    Four(6),
    Six(7),
    Twelve(8),
    TwentyFour(9);
    
    private int m_value = 0;

    private CarafageType(int value) {
        this.m_value = value;
    }

    public int getValue() {
        return this.m_value;
    }

    public static final CarafageType parse(byte b) {
        CarafageType[] enums = CarafageType.class.getEnumConstants();
        for (int i = 0; i < enums.length; ++i) {
            if (enums[i].getValue() != b) continue;
            return enums[i];
        }
        return Zero;
    }
}

