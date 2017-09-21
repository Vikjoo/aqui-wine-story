
package net.spark;


public class BottleType
extends BaseItem {
    static final BottleType empty = new BottleType("0000");

    protected BottleType() {
    }

    protected BottleType(String sysId) {
        super.setSystemUid(sysId);
    }

    public final ObjectType getType() {
        return ObjectType.BottleType;
    }

    public static final BottleType getEmpty() {
        return empty;
    }
}

