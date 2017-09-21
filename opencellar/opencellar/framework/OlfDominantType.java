/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
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
    
    private byte m_value = 0;

    private OlfDominantType(byte value) {
        this.m_value = value;
    }

    public byte getValue() {
        return this.m_value;
    }

    public static final OlfDominantType parse(byte b) {
        OlfDominantType[] enums = OlfDominantType.class.getEnumConstants();
        for (int i = 0; i < enums.length; ++i) {
            if (enums[i].getValue() != b) continue;
            return enums[i];
        }
        return None;
    }
}

