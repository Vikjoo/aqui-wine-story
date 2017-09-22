
package net.spark.cellar;

import java.util.Date;
import java.util.HashMap;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public final class Wine extends CellarObject {

	
	@Transient
	private FoodType food;
	static final int LENGTH = 358;
	private ColorType color = ColorType.Red;
	private String name = "";
	private String comment = "";
	private boolean manualManagment = false;
	private int bottles = 0;
	private String ref = "";
	private int temperature = 0;
	private int year = 0;
	private int generalNote = 0;
	private float buyPrice = 0.0f;
	private float evaluatePrice = 0.0f;
	private float degree = 0.0f;
	private int consumeMin = 0;
	private int consumeMax = 0;
	private int bestMin = 0;
	private int bestMax = 0;
	private Date creationTime = new Date();
	private Date updateTime = new Date();
	@OneToOne(cascade=CascadeType.ALL)
	private Image image = null;
	// private CellarObjectCollection m_assemblies = null;
	@Transient
	private RackItemCollection m_rackItems = null;

	// private CellarObjectCollection m_purchases = null;

	// private CellarObjectCollection m_notes = null;

	private Cuvee internalCuvee = null;
	static final byte RESERVED = 1;
	private HashMap m_prop = new HashMap(26);
	private Category category;
	private Area area;

	private Classification classification;
	private BottleType bottleType;
	private TypeOfWine typeOfWine;
	private Name appellation;
	private Country country;
	private Owner owner;

	public Wine() {
		//this.food = new FoodType(this);
	}

	public final ObjectType getType() {
		return ObjectType.Wine;
	}

	public final FoodType getFood() {
		return this.food;
	}

	public final int getLength() {
		return 358;
	}

	public final ColorType getColor() {
		return this.color;
	}

	public final void setColor(ColorType ct) {

			this.color = ct;

	}

	public final String getName() {
		return this.name;
	}

	public final void setName(String s) {
		this.name = s;
	}

	public final String getComment() {
		return this.comment;
	}

	public final void setComment(String s) {
		this.comment = s;

	}

	public final boolean isManualManagment() {
		return this.manualManagment;
	}

	public final void setManualManagment(boolean b) {
		this.manualManagment = b;
	}

	public final int getBottles() {
		return this.bottles;
	}

	public final void setBottles(int v) {

			this.bottles = v;

	}

	public final String getReference() {
		return this.ref;
	}

	public final void setReference(String s) {
	
			this.ref = s;

	}

	public final int getTemperature() {
		return this.temperature;
	}

	public final void setTemperature(int b) {
		this.temperature = b;

	}

	public final int getYear() {
		return this.year;
	}

	public final void setYear(int v) {
		
			this.year = v;

	}

	public final int getGeneralNote() {
		return this.generalNote;
	}

	public final void setGeneralNote(int b) {
		this.generalNote = b;

	}

	public final float getBuyPrice() {
		return this.buyPrice;
	}

	public final void setBuyPrice(float f) {
		if (f > -1.0f && f != this.buyPrice) {
			this.buyPrice = f;

		}
	}

	public final float getEvaluatePrice() {
		return this.evaluatePrice;
	}

	public final void setEvaluatePrice(float f) {
		
			this.evaluatePrice = f;
		
	}

	public final float getDegree() {
		return this.degree;
	}

	public final void setDegree(float f) {
		
			this.degree = f;
		
	}

	public final int getConsumeMin() {
		return this.consumeMin;
	}

	public final void setConsumeMin(int v) {
		
			this.consumeMin = v;
		
	}

	public final int getConsumeMax() {
		return this.consumeMax;
	}

	public final void setConsumeMax(int v) {
		if (v > 0 && v < 65536 && v != this.consumeMax) {
			this.consumeMax = v;
		}
	}

	public final int getBestMin() {
		return this.bestMin;
	}

	public final void setBestMin(int v) {
		if (v > 0 && v < 65536 && v != this.bestMin) {
			this.bestMin = v;
		}
	}

	public final int getBestMax() {
		return this.bestMax;
	}

	public final void setBestMax(int v) {
		if (v > 0 && v < 65536 && v != this.bestMax) {
			this.bestMax = v;
		}
	}

	public final Date getCreationTime() {
		return this.creationTime;
	}

	public final Date getLastUpdate() {
		return this.updateTime;
	}

	public final Owner getOwner() {
		return this.owner;
	}

	public final void setOwner(Owner o) {
		this.owner = o;
	}

	public final Image getImage() {

		return this.image;
	}

	private RackItemCollection getCellar() {
		return null;
	}

	public final void setImage(Image i) {

		this.image = i;

	}

	public final boolean hasImage() {
		return !this.image.equals("0000");
	}

	public final Category getCategory() {
		return this.category;
	}

	public final void setCategory(Category co) {
		this.category = co;
	}

	public final Area getArea() {

		return this.area;
	}

	public final void setArea(Area co) {
		this.area = co;
	}

	public final Classification getClassification() {

		return this.classification;
	}

	public final void setClassification(Classification co) {
		this.classification = co;
	}

	public final BottleType getBottleType() {

		return this.bottleType;
	}

	public final void setBottleType(BottleType co) {
		this.bottleType = co;
	}

	public final TypeOfWine getCepage() {
		return this.typeOfWine;
	}

	public final void setCepage(TypeOfWine co) {
		this.typeOfWine = co;
	}

	public final Name getAppellation() {

		return this.appellation;
	}

	public final void setAppellation(Name co) {
		this.appellation = co;

	}

	public final Country getCountry() {

		return this.country;
	}

	public final void setCountry(Country co) {
		this.country = co;
	}

	/*
	 * public final CellarObjectCollection getAssemblies() { if (this.m_assemblies
	 * == null) { this.m_assemblies = this.getCellar().getChilds(this,
	 * ObjectType.Assembly); this.m_assemblies.sort(new AssemblyComparer()); }
	 * return this.m_assemblies; }
	 * 
	 * 
	 * 
	 * public final RackItemCollection getRackItems() { if (this.m_rackItems ==
	 * null) { this.m_rackItems = new RackItemCollection(null); } return
	 * this.m_rackItems; }
	 * 
	 * 
	 * 
	 * public final CellarObjectCollection getPurchasesSales() { if
	 * (this.m_purchases == null) { this.m_purchases =
	 * this.getCellar().getChilds(this, ObjectType.PurchaseSales);
	 * this.m_purchases.sort(new PurchaseSalesComparer()); } return
	 * this.m_purchases; }
	 * 
	 * 
	 * 
	 * public final CellarObjectCollection getNotes() { if (this.m_notes == null) {
	 * this.m_notes = this.getCellar().getChilds(this, ObjectType.Note);
	 * this.m_notes.sort(new NoteComparer()); } return this.m_notes; }
	 * 
	 */

	protected final void setInternalCuvee(Cuvee c) {
		this.internalCuvee = c;
		this.internalCuvee.internalsetWine(this);
	}

	protected final Cuvee getInternalCuvee() {

		return this.internalCuvee;
	}

	public final String getCuvee() {
		return this.getInternalCuvee().getName();
	}

	public final void setCuvee(String s) {
		if (!this.getInternalCuvee().getName().equals(s)) {
			this.getInternalCuvee().setName(s);

		}
	}

	public final void delete() {
		/*
		 * int i; while (this.getRackItems().size() > 0) {
		 * this.getRackItems().get(0).consume(); } for (i = 0; i <
		 * this.getPurchasesSales().size(); ++i) {
		 * this.getPurchasesSales().get(i).delete(); } for (i = 0; i <
		 * this.getNotes().size(); ++i) { this.getNotes().get(i).delete(); }
		 * super.delete();
		 */
	}

	/*
	 * public final void createBackupPoint() { this.m_prop.clear();
	 * this.m_prop.put("Name", this.getName()); this.m_prop.put("Comment",
	 * this.getComment()); this.m_prop.put("Reference", this.getReference());
	 * this.m_prop.put("Temperature", this.getTemperature());
	 * this.m_prop.put("Year", this.getYear()); this.m_prop.put("BestMax",
	 * this.getBestMax()); this.m_prop.put("BestMin", this.getBestMin());
	 * this.m_prop.put("ConsumeMin", this.getConsumeMin());
	 * this.m_prop.put("ConsumeMax", this.getConsumeMax());
	 * this.m_prop.put("EvaluatePrice", Float.valueOf(this.getEvaluatePrice()));
	 * this.m_prop.put("Degree", Float.valueOf(this.getDegree()));
	 * this.m_prop.put("BuyPrice", Float.valueOf(this.getBuyPrice()));
	 * this.m_prop.put("Appellation", this.getAppellation());
	 * this.m_prop.put("Area", this.getArea()); this.m_prop.put("BottleType",
	 * this.getBottleType()); this.m_prop.put("Category", this.getCategory());
	 * this.m_prop.put("Color", this.getColor()); this.m_prop.put("Classification",
	 * this.getClassification()); this.m_prop.put("Cepage", this.getCepage());
	 * this.m_prop.put("Country", this.getCountry()); this.m_prop.put("Owner",
	 * this.getOwner()); this.m_prop.put("GeneralNote", this.getGeneralNote());
	 * this.m_prop.put("Food", this.getFood()); this.m_prop.put("Cuvee",
	 * this.getCuvee()); this.m_prop.put("Bottles", this.getBottles());
	 * this.m_prop.put("ManualManagement", this.isManualManagment()); }
	 */

	public final float getTotalPurchases() {
		float ret = 0.0f;
		/*
		 * for (int i = 0; i < this.getPurchasesSales().size(); ++i) { PurchaseSales
		 * sales = (PurchaseSales)this.getPurchasesSales().get(i); ret +=
		 * sales.getAmount(); }
		 */
		return ret;
	}

	public final float getTotalEvaluate() {
		if (this.isManualManagment()) {
			return (float) this.getBottles() * this.getEvaluatePrice();
		}
		return 0.0f;// (float)this.getRackItems().size() * this.getEvaluatePrice();
	}

	public final String getAllRackItems() {
		StringBuilder builder = new StringBuilder();
		/*
		 * Rack rk = null; for (int i = 0; i < this.getRackItems().size(); ++i) {
		 * RackItem item = this.getRackItems().get(i); if (item.getParent() != rk) { rk
		 * = item.getParent(); builder.append(rk.getName() + " : "); }
		 * builder.append(item.getLegend() + "  "); } if (builder.length() > 0) {
		 * builder.deleteCharAt(builder.length() - 1); }
		 */
		return builder.toString();
	}
}
