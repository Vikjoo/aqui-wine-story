
package net.spark.cellar;


public enum SkeletonType {
    None(0),
    Fluet(1),
    Thin(2),
    Narrow(3),
    Slender(4),
    Light(5),
    Packed(6),
    Corpulent(7),
    Robust(8),
    Framed(9),
    Enormous(10);
    
    private int m_value = 0;

    private SkeletonType(int value) {
        this.m_value = value;
    }

    public int getValue() {
        return this.m_value;
    }

    public static final SkeletonType parse(int b) {
        SkeletonType[] enums = SkeletonType.class.getEnumConstants();
        for (int i = 0; i < enums.length; ++i) {
            if (enums[i].getValue() != b) continue;
            return enums[i];
        }
        return None;
    }
}

