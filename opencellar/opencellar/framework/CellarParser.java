/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import opencellar.framework.Area;
import opencellar.framework.BottleType;
import opencellar.framework.BytesManager;
import opencellar.framework.Category;
import opencellar.framework.Cellar;
import opencellar.framework.CellarObject;
import opencellar.framework.CellarReader;
import opencellar.framework.Classification;
import opencellar.framework.Country;
import opencellar.framework.Cuvee;
import opencellar.framework.Index;
import opencellar.framework.Name;
import opencellar.framework.ObjectType;
import opencellar.framework.Owner;
import opencellar.framework.Provider;
import opencellar.framework.Rack;
import opencellar.framework.RackItem;
import opencellar.framework.RackItemCollection;
import opencellar.framework.RecordHeader;
import opencellar.framework.TypeOfWine;
import opencellar.framework.Wine;

public class CellarParser {
    private Cellar m_cellar;
    private CellarReader m_reader;

    public CellarParser(CellarReader reader, Cellar cellar) {
        this.m_reader = reader;
        this.m_cellar = cellar;
    }

    public Cellar getCellar() {
        return this.m_cellar;
    }

    public CellarReader getReader() {
        return this.m_reader;
    }

    public boolean parseHeader(RecordHeader record, long offset) {
        if (record.getCode() == 100) {
            return true;
        }
        if (record.getCode() == 51) {
            return true;
        }
        if (record.getCode() == 50) {
            return true;
        }
        if (record.getCode() == 101) {
            return true;
        }
        if (record.getCode() == 102) {
            return true;
        }
        if (record.getCode() == 103) {
            return true;
        }
        if (record.getCode() == 104) {
            return true;
        }
        if (record.getCode() == 105) {
            return true;
        }
        if (record.getCode() == 106) {
            return true;
        }
        if (record.getCode() == 60) {
            return true;
        }
        if (record.getCode() == 61) {
            return true;
        }
        if (record.getCode() == 71) {
            return true;
        }
        if (record.getCode() == 80) {
            Index index = new Index();
            index.setPk(this.m_reader.readKey(offset));
            index.setFk(this.m_reader.readKey(offset + 4));
            index.setOffset(offset);
            this.m_cellar.addIndex(ObjectType.Note, index);
            return false;
        }
        if (record.getCode() == 81) {
            Index index = new Index();
            index.setPk(this.m_reader.readKey(offset));
            index.setFk(this.m_reader.readKey(offset + 4));
            index.setOffset(offset);
            this.m_cellar.addIndex(ObjectType.PurchaseSales, index);
            return false;
        }
        if (record.getCode() == 10) {
            return true;
        }
        if (record.getCode() == 72) {
            Index index = new Index();
            index.setPk(this.m_reader.readKey(offset));
            index.setFk(this.m_reader.readKey(offset + 4));
            index.setOffset(offset);
            this.m_cellar.addIndex(ObjectType.Assembly, index);
            return false;
        }
        if (record.getCode() == 70) {
            Index index = new Index();
            index.setPk(this.m_reader.readKey(offset));
            index.setOffset(offset);
            this.m_cellar.addIndex(ObjectType.Image, index);
            return false;
        }
        return false;
    }

    public boolean parseItem(RecordHeader record, BytesManager bm, long offset) {
        if (record.getCode() == 100) {
            Country co = (Country)this.m_cellar.createItem(ObjectType.Country);
            co.read(bm);
            co.setOffset(offset);
            co.setToNone();
            this.m_cellar.add(co);
            return true;
        }
        if (record.getCode() == 51) {
            RackItem co = new RackItem();
            co.setCellar(this.m_cellar);
            co.read(bm);
            co.setOffset(offset);
            co.setToNone();
            this.m_cellar.rackItems.add(co);
            return true;
        }
        if (record.getCode() == 50) {
            Rack co = new Rack();
            co.setCellar(this.m_cellar);
            co.read(bm);
            co.setOffset(offset);
            co.setToNone();
            this.m_cellar.add(co);
            return true;
        }
        if (record.getCode() == 101) {
            Name co = (Name)this.m_cellar.createItem(ObjectType.Name);
            co.read(bm);
            co.setOffset(offset);
            co.setToNone();
            this.m_cellar.add(co);
            return true;
        }
        if (record.getCode() == 71) {
            Cuvee co = new Cuvee();
            co.setCellar(this.m_cellar);
            co.read(bm);
            co.setOffset(offset);
            co.setToNone();
            this.m_cellar.add(co);
            return true;
        }
        if (record.getCode() == 102) {
            TypeOfWine co = (TypeOfWine)this.m_cellar.createItem(ObjectType.TypeOfWine);
            co.read(bm);
            co.setOffset(offset);
            co.setToNone();
            this.m_cellar.add(co);
            return true;
        }
        if (record.getCode() == 103) {
            BottleType co = (BottleType)this.m_cellar.createItem(ObjectType.BottleType);
            co.read(bm);
            co.setOffset(offset);
            co.setToNone();
            this.m_cellar.add(co);
            return true;
        }
        if (record.getCode() == 104) {
            Area co = (Area)this.m_cellar.createItem(ObjectType.Area);
            co.read(bm);
            co.setOffset(offset);
            co.setToNone();
            this.m_cellar.add(co);
            return true;
        }
        if (record.getCode() == 105) {
            Category co = (Category)this.m_cellar.createItem(ObjectType.Category);
            co.read(bm);
            co.setOffset(offset);
            co.setToNone();
            this.m_cellar.add(co);
            return true;
        }
        if (record.getCode() == 106) {
            Classification co = (Classification)this.m_cellar.createItem(ObjectType.Classification);
            co.read(bm);
            co.setOffset(offset);
            co.setToNone();
            this.m_cellar.add(co);
            return true;
        }
        if (record.getCode() == 60) {
            Owner co = (Owner)this.m_cellar.createItem(ObjectType.Owner);
            co.read(bm);
            co.setOffset(offset);
            co.setToNone();
            this.m_cellar.add(co);
            return true;
        }
        if (record.getCode() == 61) {
            Provider co = (Provider)this.m_cellar.createItem(ObjectType.Provider);
            co.read(bm);
            co.setOffset(offset);
            co.setToNone();
            this.m_cellar.add(co);
            return true;
        }
        if (record.getCode() == 10) {
            Wine co = (Wine)this.m_cellar.createItem(ObjectType.Wine);
            co.read(bm);
            co.setOffset(offset);
            co.setToNone();
            this.m_cellar.add(co);
            return true;
        }
        return false;
    }
}

