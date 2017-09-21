
package net.spark.cellar;


public enum OlfDominantType {
    None(0),
    Vegetable(1),
    Floral(2),
    Fruity(3),
    Spiced(4),
    Timbered(5),
    Empyreumatic(6),
    Mineral(7),
    Fermentaire(8),
    Chemical(9),
    Honey(10),
    Animal(11);
    
    private int m_value = 0;

    private OlfDominantType(int value) {
        this.m_value = value;
    }

    public int getValue() {
        return this.m_value;
    }

    public static final OlfDominantType parse(int b) {
        OlfDominantType[] enums = OlfDominantType.class.getEnumConstants();
        for (int i = 0; i < enums.length; ++i) {
            if (enums[i].getValue() != b) continue;
            return enums[i];
        }
        return None;
    }
}

