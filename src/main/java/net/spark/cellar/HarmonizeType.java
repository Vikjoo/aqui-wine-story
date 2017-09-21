
package net.spark.cellar;


public enum HarmonizeType {
    None(0),
    Unbalanced(1),
    LittleBbalanced(2),
    RatherBalancedWell(3),
    Balanced(4),
    Harmonious(5);
    
    private int m_value = 0;

    private HarmonizeType(int value) {
        this.m_value = value;
    }

    public int getValue() {
        return this.m_value;
    }

    public static final HarmonizeType parse(int b) {
        HarmonizeType[] enums = HarmonizeType.class.getEnumConstants();
        for (int i = 0; i < enums.length; ++i) {
            if (enums[i].getValue() != b) continue;
            return enums[i];
        }
        return None;
    }
}

