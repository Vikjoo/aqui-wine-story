/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import opencellar.framework.Area;
import opencellar.framework.AssemblyComparer;
import opencellar.framework.AssemblyWorkqueue;
import opencellar.framework.BottleType;
import opencellar.framework.BytesManager;
import opencellar.framework.Category;
import opencellar.framework.Cellar;
import opencellar.framework.CellarObject;
import opencellar.framework.CellarObjectCollection;
import opencellar.framework.Classification;
import opencellar.framework.ColorType;
import opencellar.framework.Country;
import opencellar.framework.Cuvee;
import opencellar.framework.DateTimeUtility;
import opencellar.framework.FoodType;
import opencellar.framework.Image;
import opencellar.framework.Name;
import opencellar.framework.NoteComparer;
import opencellar.framework.NoteWorkqueue;
import opencellar.framework.ObjectState;
import opencellar.framework.ObjectType;
import opencellar.framework.Owner;
import opencellar.framework.PurchaseSales;
import opencellar.framework.PurchaseSalesComparer;
import opencellar.framework.PurchaseSalesWorkqueue;
import opencellar.framework.Rack;
import opencellar.framework.RackItem;
import opencellar.framework.RackItemCollection;
import opencellar.framework.RackItemWorkqueue;
import opencellar.framework.TypeOfWine;
import opencellar.framework.Utils;

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
    private CellarObjectCollection m_assemblies = null;
    private AssemblyWorkqueue m_assemblyQueue;
    private RackItemCollection m_rackItems = null;
    private RackItemWorkqueue m_rackItemQueue;
    private CellarObjectCollection m_purchases = null;
    private PurchaseSalesWorkqueue m_purchaseSalesQueue;
    private CellarObjectCollection m_notes = null;
    private NoteWorkqueue m_noteQueue;
    private Cuvee m_internalCuvee = null;
    static final byte RESERVED = 1;
    private HashMap m_prop = new HashMap(26);

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
        return super.getLength() + 358;
    }

    public final ColorType getColor() {
        return this.m_color;
    }

    public final void setColor(ColorType ct) {
        if (this.m_color != ct) {
            this.m_color = ct;
            super.setToUpdate();
            super.notifyOnPropertyChanged("Color");
        }
    }

    public final String getName() {
        return this.m_name;
    }

    public final void setName(String s) {
        if (!this.m_name.equals(s)) {
            this.m_name = Utils.ensureCapacity(s, 60);
            super.setToUpdate();
            super.notifyOnPropertyChanged("Name");
        }
    }

    public final String getComment() {
        return this.m_comment;
    }

    public final void setComment(String s) {
        if (!this.m_comment.equals(s)) {
            this.m_comment = Utils.ensureCapacity(s, 200);
            super.setToUpdate();
            super.notifyOnPropertyChanged("Comment");
        }
    }

    public final boolean isManualManagment() {
        return this.m_manualManagment;
    }

    public final void setManualManagment(boolean b) {
        if (b != this.m_manualManagment) {
            this.m_manualManagment = b;
            super.setToUpdate();
            super.notifyOnPropertyChanged("ManualManagment");
        }
    }

    public final int getBottles() {
        return this.m_bottles;
    }

    public final void setBottles(int v) {
        if (v > 0 && v < 65536 && v != this.m_bottles) {
            this.m_bottles = v;
            super.setToUpdate();
            super.notifyOnPropertyChanged("Bottles");
        }
    }

    public final String getReference() {
        return this.m_ref;
    }

    public final void setReference(String s) {
        if (!this.m_ref.equals(s)) {
            this.m_ref = Utils.ensureCapacity(s, 10);
            super.setToUpdate();
            super.notifyOnPropertyChanged("Reference");
        }
    }

    public final int getTemperature() {
        return this.m_temperature;
    }

    public final void setTemperature(int b) {
        if (Utils.isValidByte(b) && b != this.m_temperature) {
            this.m_temperature = b;
            super.setToUpdate();
            super.notifyOnPropertyChanged("Temperature");
        }
    }

    public final int getYear() {
        return this.m_year;
    }

    public final void setYear(int v) {
        if (v > 0 && v < 65536 && v != this.m_year) {
            this.m_year = v;
            super.setToUpdate();
            super.notifyOnPropertyChanged("Year");
        }
    }

    public final int getGeneralNote() {
        return this.m_generalNote;
    }

    public final void setGeneralNote(int b) {
        if (Utils.isValidByte(b) && b != this.m_generalNote) {
            this.m_generalNote = b;
            super.setToUpdate();
            super.notifyOnPropertyChanged("GeneralNote");
        }
    }

    public final float getBuyPrice() {
        return this.m_buyPrice;
    }

    public final void setBuyPrice(float f) {
        if (f > -1.0f && f != this.m_buyPrice) {
            this.m_buyPrice = f;
            super.setToUpdate();
            super.notifyOnPropertyChanged("BuyPrice");
        }
    }

    public final float getEvaluatePrice() {
        return this.m_evaluatePrice;
    }

    public final void setEvaluatePrice(float f) {
        if (f > -1.0f && f != this.m_evaluatePrice) {
            this.m_evaluatePrice = f;
            super.setToUpdate();
            super.notifyOnPropertyChanged("EvaluatePrice");
        }
    }

    public final float getDegree() {
        return this.m_degree;
    }

    public final void setDegree(float f) {
        if (f > -1.0f && f != this.m_degree) {
            this.m_degree = f;
            super.setToUpdate();
            super.notifyOnPropertyChanged("Degree");
        }
    }

    public final int getConsumeMin() {
        return this.m_consumeMin;
    }

    public final void setConsumeMin(int v) {
        if (v > 0 && v < 65536 && v != this.m_consumeMin) {
            this.m_consumeMin = v;
            super.setToUpdate();
            super.notifyOnPropertyChanged("ConsumeMin");
        }
    }

    public final int getConsumeMax() {
        return this.m_consumeMax;
    }

    public final void setConsumeMax(int v) {
        if (v > 0 && v < 65536 && v != this.m_consumeMax) {
            this.m_consumeMax = v;
            super.setToUpdate();
            super.notifyOnPropertyChanged("ConsumeMax");
        }
    }

    public final int getBestMin() {
        return this.m_bestMin;
    }

    public final void setBestMin(int v) {
        if (v > 0 && v < 65536 && v != this.m_bestMin) {
            this.m_bestMin = v;
            super.setToUpdate();
            super.notifyOnPropertyChanged("BestMin");
        }
    }

    public final int getBestMax() {
        return this.m_bestMax;
    }

    public final void setBestMax(int v) {
        if (v > 0 && v < 65536 && v != this.m_bestMax) {
            this.m_bestMax = v;
            super.setToUpdate();
            super.notifyOnPropertyChanged("BestMax");
        }
    }

    public final Date getCreationTime() {
        return this.m_creationTime;
    }

    public final Date getLastUpdate() {
        return this.m_updateTime;
    }

    public final Owner getOwner() {
        if (this.m_ownerId.equals("0000")) {
            return Owner.getEmpty();
        }
        Owner co = (Owner)this.getCellar().get(ObjectType.Owner, this.m_ownerId);
        if (co == null) {
            this.m_ownerId = "0000";
            co = Owner.getEmpty();
        }
        return co;
    }

    public final void setOwner(Owner o) {
        if (o == null) {
            if (!this.m_ownerId.equals("0000")) {
                this.m_ownerId = "0000";
                super.setToUpdate();
                super.notifyOnPropertyChanged("Owner");
            }
        } else if (!this.m_ownerId.equals(o.getSystemUid())) {
            this.m_ownerId = o.getSystemUid();
            super.setToUpdate();
            super.notifyOnPropertyChanged("Owner");
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

    public final void setImage(Image i) {
        if (i == null) {
            if (!this.m_imageId.equals("0000")) {
                this.m_imageId = "0000";
                super.setToUpdate();
                super.notifyOnPropertyChanged("Image");
            }
        } else if (!this.m_imageId.equals(i.getSystemUid())) {
            this.m_imageId = i.getSystemUid();
            super.setToUpdate();
            super.notifyOnPropertyChanged("Image");
        }
    }

    public final boolean hasImage() {
        return !this.m_imageId.equals("0000");
    }

    public final Category getCategory() {
        if (this.m_categoryId.equals("0000")) {
            return Category.getEmpty();
        }
        Category co = (Category)this.getCellar().get(ObjectType.Category, this.m_categoryId);
        if (co == null) {
            this.m_categoryId = "0000";
            co = Category.getEmpty();
        }
        return co;
    }

    public final void setCategory(Category co) {
        if (co == null) {
            if (!this.m_categoryId.equals("0000")) {
                this.m_categoryId = "0000";
                super.setToUpdate();
                super.notifyOnPropertyChanged("Category");
            }
        } else if (!this.m_categoryId.equals(co.getSystemUid())) {
            this.m_categoryId = co.getSystemUid();
            super.setToUpdate();
            super.notifyOnPropertyChanged("Category");
        }
    }

    public final Area getArea() {
        if (this.m_areaId.equals("0000")) {
            return Area.getEmpty();
        }
        Area co = (Area)this.getCellar().get(ObjectType.Area, this.m_areaId);
        if (co == null) {
            this.m_areaId = "0000";
            co = Area.getEmpty();
        }
        return co;
    }

    public final void setArea(Area co) {
        if (co == null) {
            if (!this.m_areaId.equals("0000")) {
                this.m_areaId = "0000";
                super.setToUpdate();
                super.notifyOnPropertyChanged("Area");
            }
        } else if (!this.m_areaId.equals(co.getSystemUid())) {
            this.m_areaId = co.getSystemUid();
            super.setToUpdate();
            super.notifyOnPropertyChanged("Area");
        }
    }

    public final Classification getClassification() {
        if (this.m_classificationId.equals("0000")) {
            return Classification.getEmpty();
        }
        Classification co = (Classification)this.getCellar().get(ObjectType.Classification, this.m_classificationId);
        if (co == null) {
            this.m_classificationId = "0000";
            co = Classification.getEmpty();
        }
        return co;
    }

    public final void setClassification(Classification co) {
        if (co == null) {
            if (!this.m_classificationId.equals("0000")) {
                this.m_classificationId = "0000";
                super.setToUpdate();
                super.notifyOnPropertyChanged("Classification");
            }
        } else if (!this.m_classificationId.equals(co.getSystemUid())) {
            this.m_classificationId = co.getSystemUid();
            super.setToUpdate();
            super.notifyOnPropertyChanged("Classification");
        }
    }

    public final BottleType getBottleType() {
        if (this.m_bottleTypeId.equals("0000")) {
            return BottleType.getEmpty();
        }
        BottleType co = (BottleType)this.getCellar().get(ObjectType.BottleType, this.m_bottleTypeId);
        if (co == null) {
            this.m_bottleTypeId = "0000";
            co = BottleType.getEmpty();
        }
        return co;
    }

    public final void setBottleType(BottleType co) {
        if (co == null) {
            if (!this.m_bottleTypeId.equals("0000")) {
                this.m_bottleTypeId = "0000";
                super.setToUpdate();
                super.notifyOnPropertyChanged("BottleType");
            }
        } else if (!this.m_bottleTypeId.equals(co.getSystemUid())) {
            this.m_bottleTypeId = co.getSystemUid();
            super.setToUpdate();
            super.notifyOnPropertyChanged("BottleType");
        }
    }

    public final TypeOfWine getCepage() {
        if (this.m_cepageId.equals("0000")) {
            return TypeOfWine.getEmpty();
        }
        TypeOfWine co = (TypeOfWine)this.getCellar().get(ObjectType.TypeOfWine, this.m_cepageId);
        if (co == null) {
            this.m_cepageId = "0000";
            co = TypeOfWine.getEmpty();
        }
        return co;
    }

    public final void setCepage(TypeOfWine co) {
        if (co == null) {
            if (!this.m_cepageId.equals("0000")) {
                this.m_cepageId = "0000";
                super.setToUpdate();
                super.notifyOnPropertyChanged("Cepage");
            }
        } else if (!this.m_cepageId.equals(co.getSystemUid())) {
            this.m_cepageId = co.getSystemUid();
            super.setToUpdate();
            super.notifyOnPropertyChanged("Cepage");
        }
    }

    public final Name getAppellation() {
        if (this.m_appellationId.equals("0000")) {
            return Name.getEmpty();
        }
        Name co = (Name)this.getCellar().get(ObjectType.Name, this.m_appellationId);
        if (co == null) {
            this.m_appellationId = "0000";
            co = Name.getEmpty();
        }
        return co;
    }

    public final void setAppellation(Name co) {
        if (co == null) {
            if (!this.m_appellationId.equals("0000")) {
                this.m_appellationId = "0000";
                super.setToUpdate();
                super.notifyOnPropertyChanged("Appellation");
            }
        } else if (!this.m_appellationId.equals(co.getSystemUid())) {
            this.m_appellationId = co.getSystemUid();
            super.setToUpdate();
            super.notifyOnPropertyChanged("Appellation");
        }
    }

    public final Country getCountry() {
        if (this.m_countryId.equals("0000")) {
            return Country.getEmpty();
        }
        Country co = (Country)this.getCellar().get(ObjectType.Country, this.m_countryId);
        if (co == null) {
            this.m_countryId = "0000";
            co = Country.getEmpty();
        }
        return co;
    }

    public final void setCountry(Country co) {
        if (co == null) {
            if (!this.m_countryId.equals("0000")) {
                this.m_countryId = "0000";
                super.setToUpdate();
                super.notifyOnPropertyChanged("Country");
            }
        } else if (!this.m_countryId.equals(co.getSystemUid())) {
            this.m_countryId = co.getSystemUid();
            super.setToUpdate();
            super.notifyOnPropertyChanged("Country");
        }
    }

    public final CellarObjectCollection getAssemblies() {
        if (this.m_assemblies == null) {
            this.m_assemblies = this.getCellar().getChilds(this, ObjectType.Assembly);
            this.m_assemblies.sort(new AssemblyComparer());
        }
        return this.m_assemblies;
    }

    public final AssemblyWorkqueue getAssemblyQueue() {
        if (this.m_assemblyQueue == null) {
            this.m_assemblyQueue = new AssemblyWorkqueue(this);
        }
        return this.m_assemblyQueue;
    }

    public final RackItemCollection getRackItems() {
        if (this.m_rackItems == null) {
            this.m_rackItems = new RackItemCollection(null);
        }
        return this.m_rackItems;
    }

    public final RackItemWorkqueue getRackItemQueue() {
        if (this.m_rackItemQueue == null) {
            this.m_rackItemQueue = new RackItemWorkqueue(this);
        }
        return this.m_rackItemQueue;
    }

    public final CellarObjectCollection getPurchasesSales() {
        if (this.m_purchases == null) {
            this.m_purchases = this.getCellar().getChilds(this, ObjectType.PurchaseSales);
            this.m_purchases.sort(new PurchaseSalesComparer());
        }
        return this.m_purchases;
    }

    public final PurchaseSalesWorkqueue getPurchaseSalesQueue() {
        if (this.m_purchaseSalesQueue == null) {
            this.m_purchaseSalesQueue = new PurchaseSalesWorkqueue(this);
        }
        return this.m_purchaseSalesQueue;
    }

    public final CellarObjectCollection getNotes() {
        if (this.m_notes == null) {
            this.m_notes = this.getCellar().getChilds(this, ObjectType.Note);
            this.m_notes.sort(new NoteComparer());
        }
        return this.m_notes;
    }

    public final NoteWorkqueue getNoteQueue() {
        if (this.m_noteQueue == null) {
            this.m_noteQueue = new NoteWorkqueue(this);
        }
        return this.m_noteQueue;
    }

    protected final void setInternalCuvee(Cuvee c) {
        this.m_internalCuvee = c;
        this.m_internalCuvee.internalsetWine(this);
    }

    protected final Cuvee getInternalCuvee() {
        if (this.m_internalCuvee == null) {
            this.m_internalCuvee = new Cuvee();
            this.m_internalCuvee.setCellar(this.getCellar());
            this.m_internalCuvee.setWine(this);
            this.m_internalCuvee.setState(ObjectState.New);
        }
        return this.m_internalCuvee;
    }

    public final String getCuvee() {
        return this.getInternalCuvee().getName();
    }

    public final void setCuvee(String s) {
        if (!this.getInternalCuvee().getName().equals(s)) {
            this.getInternalCuvee().setName(s);
            super.setToUpdate();
            super.notifyOnPropertyChanged("Cuvee");
        }
    }

    public final void save() {
        super.save();
        if (this.getState() != ObjectState.Delete || this.getInternalCuvee().getState() != ObjectState.New) {
            if (this.getState() == ObjectState.Delete && this.getInternalCuvee().getState() != ObjectState.New) {
                this.getInternalCuvee().setWine(this);
                this.getInternalCuvee().delete();
            } else {
                this.getInternalCuvee().setWine(this);
                this.getInternalCuvee().save();
            }
        }
    }

    protected final void write(BytesManager m) {
        this.m_updateTime = new Date();
        super.write(m);
        m.write(Utils.padRight(this.m_ref, 10, " "));
        m.write(Utils.padRight(this.m_name, 60, " "));
        m.write(Utils.padRight(this.m_comment, 200, " "));
        m.write(DateTimeUtility.get(this.m_creationTime));
        m.write(DateTimeUtility.get(this.m_updateTime));
        m.write(this.m_buyPrice);
        m.write(this.m_evaluatePrice);
        m.writeUShort((short)this.m_consumeMin);
        m.writeUShort((short)this.m_consumeMax);
        m.writeUShort((short)this.m_bestMin);
        m.writeUShort((short)this.m_bestMax);
        m.writeByteEx(this.m_generalNote);
        m.writeUShort((short)this.m_year);
        m.writeByteEx((byte)this.m_temperature);
        m.writeByteEx((byte)this.m_food.getValue());
        m.write(this.m_color.getValue());
        m.write(this.m_appellationId);
        m.write(this.m_countryId);
        m.write(this.m_cepageId);
        m.write(this.m_areaId);
        m.write(this.m_bottleTypeId);
        m.write(this.m_classificationId);
        m.write(this.m_ownerId);
        m.write(this.m_imageId);
        m.write(this.m_categoryId);
        m.write(this.m_degree);
        m.write(this.m_manualManagment);
        m.writeUShort((short)this.m_bottles);
        m.write(1);
        m.write(1);
        m.write(1);
    }

    protected final void read(BytesManager m) {
        super.read(m);
        this.m_ref = m.getString(10);
        this.m_name = m.getString(60);
        this.m_comment = m.getString(200);
        this.m_creationTime = DateTimeUtility.get(m.getString(10));
        this.m_updateTime = DateTimeUtility.get(m.getString(10));
        this.m_buyPrice = m.getFloat();
        this.m_evaluatePrice = m.getFloat();
        this.m_consumeMin = m.getUshort();
        this.m_consumeMax = m.getUshort();
        this.m_bestMin = m.getUshort();
        this.m_bestMax = m.getUshort();
        this.m_generalNote = m.getByteEx();
        this.m_year = m.getUshort();
        this.m_temperature = m.getByteEx();
        this.m_food.setValue(m.getByteEx());
        this.m_color = ColorType.parse(m.getByte());
        this.m_appellationId = m.getString(4);
        this.m_countryId = m.getString(4);
        this.m_cepageId = m.getString(4);
        this.m_areaId = m.getString(4);
        this.m_bottleTypeId = m.getString(4);
        this.m_classificationId = m.getString(4);
        this.m_ownerId = m.getString(4);
        this.m_imageId = m.getString(4);
        this.m_categoryId = m.getString(4);
        this.m_degree = m.getFloat();
        this.m_manualManagment = m.getBoolean();
        this.m_bottles = m.getUshort();
    }

    public final void delete() {
        int i;
        while (this.getRackItems().size() > 0) {
            this.getRackItems().get(0).consume();
        }
        for (i = 0; i < this.getPurchasesSales().size(); ++i) {
            this.getPurchasesSales().get(i).delete();
        }
        for (i = 0; i < this.getNotes().size(); ++i) {
            this.getNotes().get(i).delete();
        }
        super.delete();
    }

    public final void createBackupPoint() {
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
    }

    public final void restoreBackupPoint() {
        if (this.m_prop.size() > 0) {
            this.setName((String)this.m_prop.get("Name"));
            this.setComment((String)this.m_prop.get("Comment"));
            this.setReference((String)this.m_prop.get("Reference"));
            this.setTemperature(Integer.parseInt(this.m_prop.get("Temperature").toString()));
            Integer i = new Integer(0);
            Float f = new Float(0.0f);
            i = (Integer)this.m_prop.get("Year");
            this.setYear(i);
            i = (Integer)this.m_prop.get("BestMax");
            this.setBestMax(i);
            i = (Integer)this.m_prop.get("BestMin");
            this.setBestMin(i);
            i = (Integer)this.m_prop.get("ConsumeMin");
            this.setConsumeMin(i);
            i = (Integer)this.m_prop.get("ConsumeMax");
            this.setConsumeMax(i);
            f = (Float)this.m_prop.get("EvaluatePrice");
            this.setEvaluatePrice(f.floatValue());
            f = (Float)this.m_prop.get("Degree");
            this.setDegree(f.floatValue());
            f = (Float)this.m_prop.get("BuyPrice");
            this.setBuyPrice(f.floatValue());
            this.setAppellation((Name)this.m_prop.get("Appellation"));
            this.setArea((Area)this.m_prop.get("Area"));
            this.setBottleType((BottleType)this.m_prop.get("BottleType"));
            this.setCategory((Category)this.m_prop.get("Category"));
            this.setColor((ColorType)((Object)this.m_prop.get("Color")));
            this.setClassification((Classification)this.m_prop.get("Classification"));
            this.setCepage((TypeOfWine)this.m_prop.get("Cepage"));
            this.setCountry((Country)this.m_prop.get("Country"));
            this.setOwner((Owner)this.m_prop.get("Owner"));
            i = (Integer)this.m_prop.get("GeneralNote");
            this.setGeneralNote(i);
            this.m_food = (FoodType)this.m_prop.get("Food");
            this.setCuvee((String)this.m_prop.get("Cuvee"));
            i = (Integer)this.m_prop.get("Bottles");
            this.setBottles(i);
            Boolean b = (Boolean)this.m_prop.get("ManualManagement");
            this.setManualManagment(b);
            this.getNoteQueue().clear();
            this.getRackItemQueue().clear();
            this.getPurchaseSalesQueue().clear();
            this.m_prop.clear();
            super.setToNone();
        }
    }

    public final float getTotalPurchases() {
        float ret = 0.0f;
        for (int i = 0; i < this.getPurchasesSales().size(); ++i) {
            PurchaseSales sales = (PurchaseSales)this.getPurchasesSales().get(i);
            ret += sales.getAmount();
        }
        return ret;
    }

    public final float getTotalEvaluate() {
        if (this.isManualManagment()) {
            return (float)this.getBottles() * this.getEvaluatePrice();
        }
        return (float)this.getRackItems().size() * this.getEvaluatePrice();
    }

    public final String getAllRackItems() {
        StringBuilder builder = new StringBuilder();
        Rack rk = null;
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
        }
        return builder.toString();
    }
}

