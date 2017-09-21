
package net.spark.cellar;


public final class Name
extends BaseItem {
    static final Name empty = new Name("0000");

    protected Name() {
    }

    protected Name(String sysId) {
        super.setSystemUid(sysId);
    }

    public final ObjectType getType() {
        return ObjectType.Name;
    }

    public static final Name getEmpty() {
        return empty;
    }
}

