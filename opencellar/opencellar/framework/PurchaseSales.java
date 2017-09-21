/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import java.util.Date;
import opencellar.framework.BytesManager;
import opencellar.framework.Cellar;
import opencellar.framework.CellarObject;
import opencellar.framework.DateTimeUtility;
import opencellar.framework.IIndexable;
import opencellar.framework.ObjectState;
import opencellar.framework.ObjectType;
import opencellar.framework.Provider;
import opencellar.framework.Utils;
import opencellar.framework.Wine;

public final class PurchaseSales
extends CellarObject
implements IIndexable {
    static final int LENGTH = 279;
    private String m_wineId = "0000";
    private Date m_date = new Date();
    private String m_comment;
    private String m_provId = "0000";
    private int m_purchasedBottles = 0;
    private int m_consumedBottles = 0;
    private float m_unitPrice = 0.0f;

    protected PurchaseSales() {
    }

    protected PurchaseSales(Wine w) {
        this.m_wineId = w.getSystemUid();
    }

    public final ObjectType getType() {
        return ObjectType.PurchaseSales;
    }

    public final int getLength() {
        return super.getLength() + 279;
    }

    public final Wine getWine() {
        if (this.m_wineId.equals("0000")) {
            return null;
        }
        return (Wine)this.getCellar().get(ObjectType.Wine, this.m_wineId);
    }

    public final void setWine(Wine w) {
        if (w == null) {
            this.m_wineId = "0000";
            super.setToUpdate();
        } else if (!w.getSystemUid().equals(this.m_wineId)) {
            this.m_wineId = w.getSystemUid();
            super.setToUpdate();
        }
    }

    public final Date getDate() {
        return this.m_date;
    }

    public final void setDate(Date d) {
        if (d != null) {
            this.m_date = d;
            super.setToUpdate();
        }
    }

    public final String getComment() {
        return this.m_comment;
    }

    public final void setComment(String s) {
        this.m_comment = Utils.ensureCapacity(s, 255);
        super.setToUpdate();
    }

    public final Provider getProvider() {
        if (this.m_provId.equals("0000")) {
            return null;
        }
        return (Provider)this.getCellar().get(ObjectType.Provider, this.m_provId);
    }

    public final void setProvider(Provider p) {
        if (p == null) {
            this.m_provId = "0000";
            super.setToUpdate();
        } else if (!p.getSystemUid().equals(this.m_provId)) {
            this.m_provId = p.getSystemUid();
            super.setToUpdate();
        }
    }

    public final int getPurchasedBottles() {
        return this.m_purchasedBottles;
    }

    public final void setPurchasedBottles(int b) {
        if (Utils.isValidByte(b)) {
            this.m_purchasedBottles = b;
            super.setToUpdate();
        }
    }

    public final int getConsumedBottles() {
        return this.m_consumedBottles;
    }

    public final void setConsumedBottles(int b) {
        if (Utils.isValidByte(b)) {
            this.m_consumedBottles = b;
            super.setToUpdate();
        }
    }

    public final float getUnitPrice() {
        return this.m_unitPrice;
    }

    public final void setUnitPrice(float f) {
        if (f > 0.0f) {
            this.m_unitPrice = f;
            super.setToUpdate();
        }
    }

    public final float getAmount() {
        return this.m_unitPrice * (float)this.m_purchasedBottles;
    }

    protected final void write(BytesManager m) {
        super.write(m);
        m.write(this.m_wineId);
        m.write(this.m_provId);
        m.write(DateTimeUtility.get(this.getDate()));
        m.write(this.getUnitPrice());
        m.writeByteEx(this.getConsumedBottles());
        m.writeByteEx(this.getPurchasedBottles());
        m.write(Utils.padRight(this.getComment(), 255, " "));
    }

    protected final void read(BytesManager m) {
        super.read(m);
        this.m_wineId = m.getString(4);
        this.m_provId = m.getString(4);
        this.m_date = DateTimeUtility.get(m.getString(10));
        this.m_unitPrice = m.getFloat();
        this.m_consumedBottles = m.getByteEx();
        this.m_purchasedBottles = m.getByteEx();
        this.m_comment = m.getString(255);
    }

    public final String getFk() {
        return this.m_wineId;
    }

    public final boolean isConsumed() {
        return this.m_consumedBottles > 0;
    }

    public final boolean isBought() {
        return this.m_purchasedBottles > 0;
    }

    public final void markAsDeleted() {
        super.setState(ObjectState.Delete);
    }
}

