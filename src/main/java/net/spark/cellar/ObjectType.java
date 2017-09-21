/*
 * Decompiled with CFR 0_122.
 */
package net.spark.cellar;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum ObjectType {
    None(0),
    Wine(10),
    Header(11),
    Country(100),
    Name(101),
    TypeOfWine(102),
    BottleType(103),
    Area(104),
    Category(105),
    Classification(106),
    Rack(50),
    RackItem(51),
    Owner(60),
    Provider(61),
    Image(70),
    InternalCuvee(71),
    Assembly(72),
    Note(80),
    PurchaseSales(81),
    Tracker(85);
    
    private int m_value;

    private ObjectType(int value) {
        this.m_value = value;
    }

    public final int getValue() {
        return this.m_value;
    }

    public final String toStringName() {
        return super.toString();
    }

    public final String toString() {
        return String.valueOf(this.m_value);
    }
}

