
package net.spark;

public enum DominantType {
    None(0),
    Balanced(1),
    VeryAcid(2),
    Acid(3),
    Marrowy(4),
    VeryMarrowy(5);
    
    private int m_value = 0;

    private DominantType(int value) {
        this.m_value = value;
    }

    public int getValue() {
        return this.m_value;
    }

    public static final DominantType parse(int b) {
        DominantType[] enums = DominantType.class.getEnumConstants();
        for (int i = 0; i < enums.length; ++i) {
            if (enums[i].getValue() != b) continue;
            return enums[i];
        }
        return None;
    }
}

