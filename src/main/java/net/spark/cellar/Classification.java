
package net.spark.cellar;


public class Classification
extends BaseItem {
    static final Classification empty = new Classification("0000");

    protected Classification() {
    }

    protected Classification(String sysId) {
        super.setSystemUid(sysId);
    }

    public final ObjectType getType() {
        return ObjectType.Classification;
    }

    public static final Classification getEmpty() {
        return empty;
    }
}

