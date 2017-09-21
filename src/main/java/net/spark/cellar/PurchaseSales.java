/*
 * Decompiled with CFR 0_122.
 */
package net.spark.cellar;

import java.util.Date;

public final class PurchaseSales extends CellarObject {
	static final int LENGTH = 279;
	private String m_wineId = "0000";
	private Date m_date = new Date();
	private String m_comment;
	private String m_provId = "0000";
	private int m_purchasedBottles = 0;
	private int m_consumedBottles = 0;
	private float m_unitPrice = 0.0f;
	private Provider m_provider;
	private Wine m_wine;

	protected PurchaseSales() {
	}

	protected PurchaseSales(Wine w) {
		this.m_wineId = w.getSystemUid();
	}

	public final ObjectType getType() {
		return ObjectType.PurchaseSales;
	}

	public final int getLength() {
		return 279;
	}

	public final Wine getWine() {
		return this.m_wine;
	}

	public final void setWine(Wine w) {
		this.m_wine = w;
	}

	public final Date getDate() {
		return this.m_date;
	}

	public final void setDate(Date d) {
		if (d != null) {
			this.m_date = d;

		}
	}

	public final String getComment() {
		return this.m_comment;
	}

	public final void setComment(String s) {
		this.m_comment = s;

	}

	public final Provider getProvider() {
		return this.m_provider;
	}

	public final void setProvider(Provider p) {
		this.m_provider = p;
	}

	public final int getPurchasedBottles() {
		return this.m_purchasedBottles;
	}

	public final void setPurchasedBottles(int b) {

		this.m_purchasedBottles = b;

	}

	public final int getConsumedBottles() {
		return this.m_consumedBottles;
	}

	public final void setConsumedBottles(int b) {

		this.m_consumedBottles = b;

	}

	public final float getUnitPrice() {
		return this.m_unitPrice;
	}

	public final void setUnitPrice(float f) {

		this.m_unitPrice = f;

	}

	public final float getAmount() {
		return this.m_unitPrice * (float) this.m_purchasedBottles;
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

}
