/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import opencellar.framework.BaseItem;
import opencellar.framework.ObjectType;

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

