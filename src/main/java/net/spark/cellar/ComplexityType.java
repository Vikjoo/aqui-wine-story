
package net.spark.cellar;


public enum ComplexityType {
    None(0),
    Ordinary(1),
    Simple(2),
    Fin(3),
    Subtle(4),
    Elegant(5),
    Raced(6),
    Refined(7);
    
    private int m_value = 0;

    private ComplexityType(int value) {
        this.m_value = value;
    }

    public int getValue() {
        return this.m_value;
    }

    public static final ComplexityType parse(int b) {
        ComplexityType[] enums = ComplexityType.class.getEnumConstants();
        for (int i = 0; i < enums.length; ++i) {
            if (enums[i].getValue() != b) continue;
            return enums[i];
        }
        return None;
    }
}

