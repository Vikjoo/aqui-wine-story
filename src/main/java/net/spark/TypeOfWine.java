
package net.spark;


public class TypeOfWine
extends BaseItem {
    static final TypeOfWine empty = new TypeOfWine();

    protected TypeOfWine() {
    }

    public final ObjectType getType() {
        return ObjectType.TypeOfWine;
    }

    public static final TypeOfWine getEmpty() {
        return empty;
    }
}

