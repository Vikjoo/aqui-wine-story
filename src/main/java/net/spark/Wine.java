
package net.spark;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;


public final class Wine
extends CellarObject {
    private FoodType m_food;
    static final int LENGTH = 358;
    private ColorType m_color = ColorType.Red;
    private String m_name = "";
    private String m_comment = "";
    private boolean m_manualManagment = false;
    private int m_bottles = 0;
    private String m_ref = "";
    private int m_temperature = 0;
    private int m_year = 0;
    private int m_generalNote = 0;
    private float m_buyPrice = 0.0f;
    private float m_evaluatePrice = 0.0f;
    private float m_degree = 0.0f;
    private int m_consumeMin = 0;
    private int m_consumeMax = 0;
    private int m_bestMin = 0;
    private int m_bestMax = 0;
    private Date m_creationTime = new Date();
    private Date m_updateTime = new Date();
    private String m_ownerId = "0000";
    private String m_imageId = "0000";
    private String m_categoryId = "0000";
    private String m_areaId = "0000";
    private String m_classificationId = "0000";
    private String m_bottleTypeId = "0000";
    private String m_cepageId = "0000";
    private String m_appellationId = "0000";
    private String m_countryId = "0000";
  //  private CellarObjectCollection m_assemblies = null;
	
    private RackItemCollection m_rackItems = null;
 
  //  private CellarObjectCollection m_purchases = null;

 //   private CellarObjectCollection m_notes = null;

    private Cuvee m_internalCuvee = null;
    static final byte RESERVED = 1;
    private HashMap m_prop = new HashMap(26);
	private Category m_category;
	private Area m_area;
	private Classification m_class;
	private Classification m_classification;
	private BottleType m_bottleType;
	private TypeOfWine m_typeOfWine;
	private Name m_appellation;
	private Country m_country;
	private Owner m_owner;

    protected Wine() {
        this.m_food = new FoodType(this);
    }

    public final ObjectType getType() {
        return ObjectType.Wine;
    }

    public final FoodType getFood() {
        return this.m_food;
    }

    public final int getLength() {
        return  358;
    }

    public final ColorType getColor() {
        return this.m_color;
    }

    public final void setColor(ColorType ct) {
        if (this.m_color != ct) {
            this.m_color = ct;
      
        }
    }

    public final String getName() {
        return this.m_name;
    }

    public final void setName(String s) {
    	this.m_name =s;
    }

    public final String getComment() {
        return this.m_comment;
    }

    public final void setComment(String s) {
    	this.m_comment = s;

    }

    public final boolean isManualManagment() {
        return this.m_manualManagment;
    }

    public final void setManualManagment(boolean b) {
      this.m_manualManagment =b;
    }

    public final int getBottles() {
        return this.m_bottles;
    }

    public final void setBottles(int v) {
        if (v > 0 && v < 65536 && v != this.m_bottles) {
            this.m_bottles = v;

        }
    }

    public final String getReference() {
        return this.m_ref;
    }

    public final void setReference(String s) {
        if (!this.m_ref.equals(s)) {
            this.m_ref = s ;

        }
    }

    public final int getTemperature() {
        return this.m_temperature;
    }

    public final void setTemperature(int b) {
            this.m_temperature = b;

    }

    public final int getYear() {
        return this.m_year;
    }

    public final void setYear(int v) {
        if (v > 0 && v < 65536 && v != this.m_year) {
            this.m_year = v;

        }
    }

    public final int getGeneralNote() {
        return this.m_generalNote;
    }

    public final void setGeneralNote(int b) {
            this.m_generalNote = b;

    }

    public final float getBuyPrice() {
        return this.m_buyPrice;
    }

    public final void setBuyPrice(float f) {
        if (f > -1.0f && f != this.m_buyPrice) {
            this.m_buyPrice = f;

        }
    }

    public final float getEvaluatePrice() {
        return this.m_evaluatePrice;
    }

    public final void setEvaluatePrice(float f) {
        if (f > -1.0f && f != this.m_evaluatePrice) {
            this.m_evaluatePrice = f;
        }
    }

    public final float getDegree() {
        return this.m_degree;
    }

    public final void setDegree(float f) {
        if (f > -1.0f && f != this.m_degree) {
            this.m_degree = f;
        }
    }

    public final int getConsumeMin() {
        return this.m_consumeMin;
    }

    public final void setConsumeMin(int v) {
        if (v > 0 && v < 65536 && v != this.m_consumeMin) {
            this.m_consumeMin = v;
        }
    }

    public final int getConsumeMax() {
        return this.m_consumeMax;
    }

    public final void setConsumeMax(int v) {
        if (v > 0 && v < 65536 && v != this.m_consumeMax) {
            this.m_consumeMax = v;
        }
    }

    public final int getBestMin() {
        return this.m_bestMin;
    }

    public final void setBestMin(int v) {
        if (v > 0 && v < 65536 && v != this.m_bestMin) {
            this.m_bestMin = v;
        }
    }

    public final int getBestMax() {
        return this.m_bestMax;
    }

    public final void setBestMax(int v) {
        if (v > 0 && v < 65536 && v != this.m_bestMax) {
            this.m_bestMax = v;
        }
    }

    public final Date getCreationTime() {
        return this.m_creationTime;
    }

    public final Date getLastUpdate() {
        return this.m_updateTime;
    }

    public final Owner getOwner() {
 /*       if (this.m_ownerId.equals("0000")) {
            return Owner.getEmpty();
        }
        Owner co = (Owner)this.getCellar().get(ObjectType.Owner, this.m_ownerId);
        if (co == null) {
            this.m_ownerId = "0000";
            co = Owner.getEmpty();
        }*/
        return this.m_owner;
    }

    public final void setOwner(Owner o) {
        if (o == null) {
            if (!this.m_ownerId.equals("0000")) {
                this.m_ownerId = "0000";
            }
        } else if (!this.m_ownerId.equals(o.getSystemUid())) {
            this.m_ownerId = o.getSystemUid();
        }
    }

    public final Image getImage() {
        if (this.m_imageId.equals("0000")) {
            return null;
        }
        Image img = (Image)this.getCellar().get(ObjectType.Image, this.m_imageId);
        if (img == null) {
            this.m_imageId = "0000";
        }
        return img;
    }

    private RackItemCollection getCellar() {
		// TODO Auto-generated method stub
		return null;
	}

	public final void setImage(Image i) {
        if (i == null) {
            if (!this.m_imageId.equals("0000")) {
                this.m_imageId = "0000";
            }
        } else if (!this.m_imageId.equals(i.getSystemUid())) {
            this.m_imageId = i.getSystemUid();
        }
    }

    public final boolean hasImage() {
        return !this.m_imageId.equals("0000");
    }

    public final Category getCategory() {
        return this.m_category;
    }

    public final void setCategory(Category co) {
        if (co == null) {
            if (!this.m_categoryId.equals("0000")) {
                this.m_categoryId = "0000";
            }
        } else if (!this.m_categoryId.equals(co.getSystemUid())) {
            this.m_categoryId = co.getSystemUid();
        }
    }

    public final Area getArea() {

        return this.m_area;
    }

    public final void setArea(Area co) {
        if (co == null) {
            if (!this.m_areaId.equals("0000")) {
                this.m_areaId = "0000";

            }
        } else if (!this.m_areaId.equals(co.getSystemUid())) {
            this.m_areaId = co.getSystemUid();

        }
    }

    public final Classification getClassification() {

        return this.m_classification;
    }

    public final void setClassification(Classification co) {
    		this.m_classification = co;
    }

    public final BottleType getBottleType() {

        return this.m_bottleType;
    }

    public final void setBottleType(BottleType co) {
	  this.m_bottleType = co;
    }

    public final TypeOfWine getCepage() {
       return this.m_typeOfWine;
    }

    public final void setCepage(TypeOfWine co) {
        if (co == null) {
            if (!this.m_cepageId.equals("0000")) {
                this.m_cepageId = "0000";
            }
        } else if (!this.m_cepageId.equals(co.getSystemUid())) {
            this.m_cepageId = co.getSystemUid();
        }
    }

    public final Name getAppellation() {

        return this.m_appellation;
    }

    public final void setAppellation(Name co) {
    	this.m_appellation=co;
        
    }

    public final Country getCountry() {

        return this.m_country;
    }

    public final void setCountry(Country co) {
     this.m_country = co;
    }

/*    public final CellarObjectCollection getAssemblies() {
        if (this.m_assemblies == null) {
            this.m_assemblies = this.getCellar().getChilds(this, ObjectType.Assembly);
            this.m_assemblies.sort(new AssemblyComparer());
        }
        return this.m_assemblies;
    }

 

    public final RackItemCollection getRackItems() {
        if (this.m_rackItems == null) {
            this.m_rackItems = new RackItemCollection(null);
        }
        return this.m_rackItems;
    }



    public final CellarObjectCollection getPurchasesSales() {
        if (this.m_purchases == null) {
            this.m_purchases = this.getCellar().getChilds(this, ObjectType.PurchaseSales);
            this.m_purchases.sort(new PurchaseSalesComparer());
        }
        return this.m_purchases;
    }



    public final CellarObjectCollection getNotes() {
        if (this.m_notes == null) {
            this.m_notes = this.getCellar().getChilds(this, ObjectType.Note);
            this.m_notes.sort(new NoteComparer());
        }
        return this.m_notes;
    }

*/

    protected final void setInternalCuvee(Cuvee c) {
        this.m_internalCuvee = c;
        this.m_internalCuvee.internalsetWine(this);
    }

    protected final Cuvee getInternalCuvee() {
/*        if (this.m_internalCuvee == null) {
            this.m_internalCuvee = new Cuvee();
            this.m_internalCuvee.setCellar(this.getCellar());
            this.m_internalCuvee.setWine(this);
            this.m_internalCuvee.setState(ObjectState.New);
        }*/
        return this.m_internalCuvee;
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
/*        int i;
        while (this.getRackItems().size() > 0) {
            this.getRackItems().get(0).consume();
        }
        for (i = 0; i < this.getPurchasesSales().size(); ++i) {
            this.getPurchasesSales().get(i).delete();
        }
        for (i = 0; i < this.getNotes().size(); ++i) {
            this.getNotes().get(i).delete();
        }
        super.delete();*/
    }

/*    public final void createBackupPoint() {
        this.m_prop.clear();
        this.m_prop.put("Name", this.getName());
        this.m_prop.put("Comment", this.getComment());
        this.m_prop.put("Reference", this.getReference());
        this.m_prop.put("Temperature", this.getTemperature());
        this.m_prop.put("Year", this.getYear());
        this.m_prop.put("BestMax", this.getBestMax());
        this.m_prop.put("BestMin", this.getBestMin());
        this.m_prop.put("ConsumeMin", this.getConsumeMin());
        this.m_prop.put("ConsumeMax", this.getConsumeMax());
        this.m_prop.put("EvaluatePrice", Float.valueOf(this.getEvaluatePrice()));
        this.m_prop.put("Degree", Float.valueOf(this.getDegree()));
        this.m_prop.put("BuyPrice", Float.valueOf(this.getBuyPrice()));
        this.m_prop.put("Appellation", this.getAppellation());
        this.m_prop.put("Area", this.getArea());
        this.m_prop.put("BottleType", this.getBottleType());
        this.m_prop.put("Category", this.getCategory());
        this.m_prop.put("Color", this.getColor());
        this.m_prop.put("Classification", this.getClassification());
        this.m_prop.put("Cepage", this.getCepage());
        this.m_prop.put("Country", this.getCountry());
        this.m_prop.put("Owner", this.getOwner());
        this.m_prop.put("GeneralNote", this.getGeneralNote());
        this.m_prop.put("Food", this.getFood());
        this.m_prop.put("Cuvee", this.getCuvee());
        this.m_prop.put("Bottles", this.getBottles());
        this.m_prop.put("ManualManagement", this.isManualManagment());
    }*/



    public final float getTotalPurchases() {
        float ret = 0.0f;
/*        for (int i = 0; i < this.getPurchasesSales().size(); ++i) {
            PurchaseSales sales = (PurchaseSales)this.getPurchasesSales().get(i);
            ret += sales.getAmount();
        }*/
        return ret;
    }

    public final float getTotalEvaluate() {
        if (this.isManualManagment()) {
            return (float)this.getBottles() * this.getEvaluatePrice();
        }
        return 0.0f;//(float)this.getRackItems().size() * this.getEvaluatePrice();
    }

    public final String getAllRackItems() {
        StringBuilder builder = new StringBuilder();
/*        Rack rk = null;
        for (int i = 0; i < this.getRackItems().size(); ++i) {
            RackItem item = this.getRackItems().get(i);
            if (item.getParent() != rk) {
                rk = item.getParent();
                builder.append(rk.getName() + " : ");
            }
            builder.append(item.getLegend() + "  ");
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }*/
        return builder.toString();
    }
}

