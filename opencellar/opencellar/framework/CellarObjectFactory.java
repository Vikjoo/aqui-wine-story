/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import opencellar.framework.Area;
import opencellar.framework.Assembly;
import opencellar.framework.BottleType;
import opencellar.framework.Category;
import opencellar.framework.Cellar;
import opencellar.framework.CellarObject;
import opencellar.framework.Classification;
import opencellar.framework.Country;
import opencellar.framework.Image;
import opencellar.framework.Name;
import opencellar.framework.Note;
import opencellar.framework.ObjectType;
import opencellar.framework.Owner;
import opencellar.framework.Provider;
import opencellar.framework.PurchaseSales;
import opencellar.framework.TypeOfWine;
import opencellar.framework.Wine;

public final class CellarObjectFactory {
    protected static final CellarObject create(Cellar c, ObjectType type) {
        CellarObject co = null;
        if (type == ObjectType.Country) {
            co = new Country();
            co.setCellar(c);
        } else if (type == ObjectType.TypeOfWine) {
            co = new TypeOfWine();
            co.setCellar(c);
        } else if (type == ObjectType.Name) {
            co = new Name();
            co.setCellar(c);
        } else if (type == ObjectType.BottleType) {
            co = new BottleType();
            co.setCellar(c);
        } else if (type == ObjectType.Area) {
            co = new Area();
            co.setCellar(c);
        } else if (type == ObjectType.Category) {
            co = new Category();
            co.setCellar(c);
        } else if (type == ObjectType.Classification) {
            co = new Classification();
            co.setCellar(c);
        } else if (type == ObjectType.Owner) {
            co = new Owner();
            co.setCellar(c);
        } else if (type == ObjectType.Provider) {
            co = new Provider();
            co.setCellar(c);
        } else if (type == ObjectType.PurchaseSales) {
            co = new PurchaseSales();
            co.setCellar(c);
        } else if (type == ObjectType.Wine) {
            co = new Wine();
            co.setCellar(c);
        } else if (type == ObjectType.Assembly) {
            co = new Assembly();
            co.setCellar(c);
        } else if (type == ObjectType.Note) {
            co = new Note();
            co.setCellar(c);
        } else if (type == ObjectType.Image) {
            co = new Image();
            co.setCellar(c);
        }
        return co;
    }
}

