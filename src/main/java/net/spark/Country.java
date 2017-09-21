/*
 * Decompiled with CFR 0_122.
 */
package net.spark;




public class Country
extends BaseItem {
    static final Country emptyCountry = new Country("0000");

    protected Country() {
    }

    protected Country(String sysId) {
        super.setSystemUid(sysId);
    }

    public final ObjectType getType() {
        return ObjectType.Country;
    }

    public static final Country getEmpty() {
        return emptyCountry;
    }
}

