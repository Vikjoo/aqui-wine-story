/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import opencellar.framework.BaseItem;
import opencellar.framework.ObjectType;

public class Category
extends BaseItem {
    static final Category empty = new Category("0000");

    protected Category() {
    }

    protected Category(String sysId) {
        super.setSystemUid(sysId);
    }

    public final ObjectType getType() {
        return ObjectType.Category;
    }

    public static final Category getEmpty() {
        return empty;
    }
}

