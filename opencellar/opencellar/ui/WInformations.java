/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Area
 *  opencellar.framework.BaseItem
 *  opencellar.framework.BottleType
 *  opencellar.framework.Category
 *  opencellar.framework.Cellar
 *  opencellar.framework.CellarObject
 *  opencellar.framework.Classification
 *  opencellar.framework.ColorType
 *  opencellar.framework.Contact
 *  opencellar.framework.Country
 *  opencellar.framework.ICellarListener
 *  opencellar.framework.ICellarObjectListener
 *  opencellar.framework.Name
 *  opencellar.framework.ObjectType
 *  opencellar.framework.Owner
 *  opencellar.framework.TypeOfWine
 *  opencellar.framework.Wine
 *  org.jdesktop.layout.GroupLayout
 *  org.jdesktop.layout.GroupLayout$Group
 *  org.jdesktop.layout.GroupLayout$ParallelGroup
 */
package opencellar.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import opencellar.application.IApplication;
import opencellar.application.utils;
import opencellar.framework.Area;
import opencellar.framework.BaseItem;
import opencellar.framework.BottleType;
import opencellar.framework.Category;
import opencellar.framework.Cellar;
import opencellar.framework.CellarObject;
import opencellar.framework.Classification;
import opencellar.framework.ColorType;
import opencellar.framework.Contact;
import opencellar.framework.Country;
import opencellar.framework.ICellarListener;
import opencellar.framework.ICellarObjectListener;
import opencellar.framework.Name;
import opencellar.framework.ObjectType;
import opencellar.framework.Owner;
import opencellar.framework.TypeOfWine;
import opencellar.framework.Wine;
import opencellar.ui.ComboItem;
import opencellar.ui.FoodSelector;
import opencellar.ui.OCAutoCompleteComboBox;
import opencellar.ui.OCComboBox;
import opencellar.ui.OCComment;
import opencellar.ui.OCNote;
import opencellar.ui.OCTitle;
import opencellar.ui.UIHelper;
import org.jdesktop.layout.GroupLayout;

public class WInformations
extends JPanel
implements ICellarListener,
ICellarObjectListener,
FocusListener,
ChangeListener {
    private FoodSelector foodSelector;
    static final String EMPTY_STR = "(Vide)";
    static final ComboItem EMPTY_ITEM = new ComboItem("(Vide)");
    private IApplication m_app;
    private Wine m_wine;
    private OCAutoCompleteComboBox appellation;
    private JLabel appellationLabel;
    private OCAutoCompleteComboBox area;
    private JLabel areaLabel;
    private JTextField bestMax;
    private JLabel bestMaxLabel;
    private JTextField bestMin;
    private JLabel bestMinLabel;
    private OCAutoCompleteComboBox bottle;
    private JLabel bottleLabel;
    private JTextField buyPrice;
    private JLabel buyPriceLabel;
    private OCAutoCompleteComboBox category;
    private JLabel categoryLabel;
    private OCAutoCompleteComboBox cepage;
    private JLabel cepageLabel;
    private OCAutoCompleteComboBox classement;
    private JLabel classementLabel;
    private OCComboBox color;
    private JLabel colorLabel;
    private OCComment comment;
    private JLabel commentLabel;
    private JTextField consumeMax;
    private JLabel consumeMaxLabel;
    private JTextField consumeMin;
    private JLabel consumeMinLabel;
    private OCAutoCompleteComboBox country;
    private JLabel countryLabel;
    private JTextField cuvee;
    private JLabel cuveeLabel;
    private JTextField degree;
    private JLabel degreeLabel;
    private JTextField estimatePrice;
    private JLabel estimatePriceLabel;
    private JLabel foodLabel;
    private JTextField name;
    private JLabel nameLabel;
    private OCNote note;
    private JLabel noteLabel;
    private OCTitle oCTitle1;
    private OCTitle oCTitle2;
    private OCTitle oCTitle3;
    private OCAutoCompleteComboBox producer;
    private JLabel producerLabel;
    private JTextField reference;
    private JLabel referenceLabel;
    private JTextField temp;
    private JLabel tempLabel;
    private JTextField year;
    private JLabel yearlLabel;

    public WInformations() {
        this.initComponents();
    }

    public void setDatasource(IApplication app, Wine w) {
        if (app == null) {
            throw new IllegalArgumentException("app == null");
        }
        if (w == null) {
            throw new IllegalArgumentException("wine == null");
        }
        this.m_wine = w;
        this.m_app = app;
        this.performTranslation();
        this.initCustomComponents();
        this.loadItems();
        this.bind();
        this.registerFocusObservers();
        this.m_app.activeCellar().addlistener((ICellarListener)this);
        this.m_wine.addListener((ICellarObjectListener)this);
    }

    private void initCustomComponents() {
        this.foodSelector = new FoodSelector();
        this.foodSelector.setLocation(100, 430);
        this.foodSelector.setMinimumSize(new Dimension(400, 40));
        this.foodSelector.setSize(400, 54);
        this.add(this.foodSelector);
    }

    private void registerFocusObservers() {
        int size = this.getComponentCount();
        for (int i = 0; i < size; ++i) {
            JComponent comp = (JComponent)this.getComponent(i);
            if (!(comp instanceof JTextField | comp instanceof OCAutoCompleteComboBox | comp instanceof OCComboBox | comp instanceof OCComment)) continue;
            comp.addFocusListener(this);
        }
        this.note.addChangeListener(this);
    }

    private void bind() {
        this.name.setText(this.m_wine.getName().trim());
        this.reference.setText(this.m_wine.getReference().trim());
        this.cuvee.setText(this.m_wine.getCuvee().trim());
        this.comment.setText(this.m_wine.getComment().trim());
        this.bind(this.m_wine.getYear(), this.year);
        this.bind(this.m_wine.getConsumeMin(), this.consumeMin);
        this.bind(this.m_wine.getConsumeMax(), this.consumeMax);
        this.bind(this.m_wine.getBestMin(), this.bestMin);
        this.bind(this.m_wine.getBestMax(), this.bestMax);
        this.bind(this.m_wine.getTemperature(), this.temp);
        this.bind(this.m_wine.getDegree(), this.degree);
        this.bind(this.m_wine.getEvaluatePrice(), this.estimatePrice);
        this.bind(this.m_wine.getBuyPrice(), this.buyPrice);
        this.note.setNoteEx(this.m_wine.getGeneralNote());
        this.foodSelector.setDatasource(this.m_app, this.m_wine);
    }

    private void bind(int value, JTextField field) {
        if (value != 0) {
            field.setText(String.valueOf(value));
        } else {
            field.setText("");
        }
    }

    private void bind(float value, JTextField field) {
        if (value != 0.0f) {
            field.setText(String.valueOf(value));
        } else {
            field.setText("");
        }
    }

    public void close() {
        this.m_app.activeCellar().removeListener((ICellarListener)this);
        this.m_wine.removeListener((ICellarObjectListener)this);
        this.foodSelector.close();
    }

    private void performTranslation() {
        this.nameLabel.setText(this.m_app.getRS("WIN_NAME"));
        this.referenceLabel.setText(this.m_app.getRS("WIN_REF"));
        this.yearlLabel.setText(this.m_app.getRS("WIN_YEAR"));
        this.producerLabel.setText(this.m_app.getRS("WIN_OWNER"));
        this.countryLabel.setText(this.m_app.getRS("WIN_COUNTRY"));
        this.areaLabel.setText(this.m_app.getRS("WIN_AREA"));
        this.appellationLabel.setText(this.m_app.getRS("WIN_APP"));
        this.cepageLabel.setText(this.m_app.getRS("WIN_CEPAGE"));
        this.categoryLabel.setText(this.m_app.getRS("WIN_CATEGORY"));
        this.bottleLabel.setText(this.m_app.getRS("WIN_BOTTLE"));
        this.classementLabel.setText(this.m_app.getRS("WIN_CLASSIFICATION"));
        this.colorLabel.setText(this.m_app.getRS("WIN_COLOR"));
        this.commentLabel.setText(this.m_app.getRS("WIN_COMMENT"));
        this.bestMinLabel.setText(this.m_app.getRS("WIN_BEST_DATE"));
        this.bestMaxLabel.setText(this.m_app.getRS("WIN_TO"));
        this.consumeMinLabel.setText(this.m_app.getRS("WIN_START_DATE"));
        this.consumeMaxLabel.setText(this.m_app.getRS("WIN_TO"));
        this.tempLabel.setText(this.m_app.getRS("WIN_TEMP"));
        this.degreeLabel.setText(this.m_app.getRS("WIN_DEGREE"));
        this.noteLabel.setText(this.m_app.getRS("WIN_NOTE"));
        this.foodLabel.setText(this.m_app.getRS("WIN_FOOD"));
        this.estimatePriceLabel.setText(this.m_app.getRS("WIN_ESTIMATE"));
        this.buyPriceLabel.setText(this.m_app.getRS("WIN_BUY_PRICE"));
        this.cuveeLabel.setText(this.m_app.getRS("WIN_CUVEE"));
        this.oCTitle1.setText(this.m_app.getRS("WIN_GROUP1"));
        this.oCTitle2.setText(this.m_app.getRS("WIN_GROUP2"));
        this.oCTitle3.setText(this.m_app.getRS("WIN_GROUP3"));
        this.oCTitle1.setToolTipText(UIHelper.buildExtendedToolTip(this.m_app.getRS("WIN_GROUP1_CAPTION"), this.m_app.getRS("WIN_GROUP1_TEXT"), null));
        this.oCTitle2.setToolTipText(UIHelper.buildExtendedToolTip(this.m_app.getRS("WIN_GROUP2_CAPTION"), this.m_app.getRS("WIN_GROUP2_TEXT"), null));
        this.oCTitle3.setToolTipText(UIHelper.buildExtendedToolTip(this.m_app.getRS("WIN_GROUP3_CAPTION"), this.m_app.getRS("WIN_GROUP3_TEXT"), null));
    }

    private void loadItems() {
        UIHelper.pushItems(this.producer, ObjectType.Owner, this.m_app.activeCellar(), (Object)EMPTY_ITEM, (CellarObject)this.m_wine.getOwner());
        UIHelper.pushItems(this.country, ObjectType.Country, this.m_app.activeCellar(), (Object)EMPTY_ITEM, (CellarObject)this.m_wine.getCountry());
        UIHelper.pushItems(this.area, ObjectType.Area, this.m_app.activeCellar(), (Object)EMPTY_ITEM, (CellarObject)this.m_wine.getArea());
        UIHelper.pushItems(this.appellation, ObjectType.Name, this.m_app.activeCellar(), (Object)EMPTY_ITEM, (CellarObject)this.m_wine.getAppellation());
        UIHelper.pushItems(this.cepage, ObjectType.TypeOfWine, this.m_app.activeCellar(), (Object)EMPTY_ITEM, (CellarObject)this.m_wine.getCepage());
        UIHelper.pushItems(this.category, ObjectType.Category, this.m_app.activeCellar(), (Object)EMPTY_ITEM, (CellarObject)this.m_wine.getCategory());
        UIHelper.pushItems(this.bottle, ObjectType.BottleType, this.m_app.activeCellar(), (Object)EMPTY_ITEM, (CellarObject)this.m_wine.getBottleType());
        UIHelper.pushItems(this.classement, ObjectType.Classification, this.m_app.activeCellar(), (Object)EMPTY_ITEM, (CellarObject)this.m_wine.getClassification());
        UIHelper.push(this.m_app, this.color, this.m_wine.getColor());
    }

    private void initComponents() {
        this.referenceLabel = new JLabel();
        this.reference = new JTextField();
        this.yearlLabel = new JLabel();
        this.year = new JTextField();
        this.nameLabel = new JLabel();
        this.name = new JTextField();
        this.producerLabel = new JLabel();
        this.countryLabel = new JLabel();
        this.country = new OCAutoCompleteComboBox();
        this.producer = new OCAutoCompleteComboBox();
        this.areaLabel = new JLabel();
        this.area = new OCAutoCompleteComboBox();
        this.appellationLabel = new JLabel();
        this.appellation = new OCAutoCompleteComboBox();
        this.cepageLabel = new JLabel();
        this.cepage = new OCAutoCompleteComboBox();
        this.cuveeLabel = new JLabel();
        this.cuvee = new JTextField();
        this.categoryLabel = new JLabel();
        this.category = new OCAutoCompleteComboBox();
        this.colorLabel = new JLabel();
        this.color = new OCComboBox();
        this.classementLabel = new JLabel();
        this.classement = new OCAutoCompleteComboBox();
        this.bottleLabel = new JLabel();
        this.bottle = new OCAutoCompleteComboBox();
        this.consumeMinLabel = new JLabel();
        this.consumeMin = new JTextField();
        this.consumeMaxLabel = new JLabel();
        this.consumeMax = new JTextField();
        this.bestMinLabel = new JLabel();
        this.bestMin = new JTextField();
        this.bestMaxLabel = new JLabel();
        this.bestMax = new JTextField();
        this.buyPriceLabel = new JLabel();
        this.buyPrice = new JTextField();
        this.estimatePriceLabel = new JLabel();
        this.estimatePrice = new JTextField();
        this.commentLabel = new JLabel();
        this.foodLabel = new JLabel();
        this.tempLabel = new JLabel();
        this.temp = new JTextField();
        this.degreeLabel = new JLabel();
        this.degree = new JTextField();
        this.oCTitle1 = new OCTitle();
        this.oCTitle2 = new OCTitle();
        this.oCTitle3 = new OCTitle();
        this.comment = new OCComment();
        this.noteLabel = new JLabel();
        this.note = new OCNote();
        this.setLayout(null);
        this.referenceLabel.setText("reference");
        this.add(this.referenceLabel);
        this.referenceLabel.setBounds(10, 10, 80, 20);
        this.reference.setColumns(10);
        this.add(this.reference);
        this.reference.setBounds(100, 10, 90, 19);
        this.yearlLabel.setText("mill\u00e9sime");
        this.add(this.yearlLabel);
        this.yearlLabel.setBounds(220, 10, 80, 20);
        this.year.setHorizontalAlignment(4);
        this.add(this.year);
        this.year.setBounds(310, 10, 50, 19);
        this.nameLabel.setText("name");
        this.add(this.nameLabel);
        this.nameLabel.setBounds(10, 40, 80, 20);
        this.name.setColumns(10);
        this.add(this.name);
        this.name.setBounds(100, 40, 420, 19);
        this.producerLabel.setText("producer");
        this.add(this.producerLabel);
        this.producerLabel.setBounds(10, 70, 80, 20);
        this.countryLabel.setText("country");
        this.add(this.countryLabel);
        this.countryLabel.setBounds(10, 130, 80, 20);
        this.country.setMaximumRowCount(14);
        this.add(this.country);
        this.country.setBounds(100, 130, 170, 22);
        this.producer.setMaximumRowCount(14);
        this.add(this.producer);
        this.producer.setBounds(100, 70, 430, 22);
        this.areaLabel.setText("area");
        this.add(this.areaLabel);
        this.areaLabel.setBounds(280, 130, 70, 20);
        this.area.setMaximumRowCount(14);
        this.add(this.area);
        this.area.setBounds(360, 130, 170, 22);
        this.appellationLabel.setText("appellation");
        this.add(this.appellationLabel);
        this.appellationLabel.setBounds(10, 160, 80, 20);
        this.appellation.setMaximumRowCount(14);
        this.add(this.appellation);
        this.appellation.setBounds(100, 160, 430, 22);
        this.cepageLabel.setText("cepage");
        this.add(this.cepageLabel);
        this.cepageLabel.setBounds(10, 190, 80, 20);
        this.cepage.setMaximumRowCount(14);
        this.add(this.cepage);
        this.cepage.setBounds(100, 190, 170, 22);
        this.cuveeLabel.setText("cuvee");
        this.add(this.cuveeLabel);
        this.cuveeLabel.setBounds(280, 190, 70, 20);
        this.add(this.cuvee);
        this.cuvee.setBounds(360, 190, 160, 19);
        this.categoryLabel.setText("categorie");
        this.add(this.categoryLabel);
        this.categoryLabel.setBounds(10, 250, 80, 20);
        this.category.setMaximumRowCount(14);
        this.add(this.category);
        this.category.setBounds(100, 250, 170, 22);
        this.colorLabel.setText("color");
        this.add(this.colorLabel);
        this.colorLabel.setBounds(280, 250, 70, 20);
        this.add(this.color);
        this.color.setBounds(360, 250, 170, 22);
        this.classementLabel.setText("classement");
        this.add(this.classementLabel);
        this.classementLabel.setBounds(10, 280, 80, 20);
        this.classement.setMaximumRowCount(14);
        this.add(this.classement);
        this.classement.setBounds(100, 280, 170, 22);
        this.bottleLabel.setText("bottle");
        this.add(this.bottleLabel);
        this.bottleLabel.setBounds(280, 280, 70, 20);
        this.bottle.setMaximumRowCount(14);
        this.add(this.bottle);
        this.bottle.setBounds(360, 280, 170, 22);
        this.consumeMinLabel.setText("boire de");
        this.add(this.consumeMinLabel);
        this.consumeMinLabel.setBounds(10, 340, 80, 20);
        this.consumeMin.setHorizontalAlignment(4);
        this.add(this.consumeMin);
        this.consumeMin.setBounds(100, 340, 50, 19);
        this.consumeMaxLabel.setText("\u00e0");
        this.add(this.consumeMaxLabel);
        this.consumeMaxLabel.setBounds(170, 340, 30, 20);
        this.consumeMax.setHorizontalAlignment(4);
        this.add(this.consumeMax);
        this.consumeMax.setBounds(210, 340, 50, 19);
        this.bestMinLabel.setText("apog\u00e9e de");
        this.add(this.bestMinLabel);
        this.bestMinLabel.setBounds(280, 340, 70, 20);
        this.bestMin.setHorizontalAlignment(4);
        this.add(this.bestMin);
        this.bestMin.setBounds(360, 340, 50, 19);
        this.bestMaxLabel.setText("\u00e0");
        this.add(this.bestMaxLabel);
        this.bestMaxLabel.setBounds(430, 340, 30, 20);
        this.bestMax.setHorizontalAlignment(4);
        this.add(this.bestMax);
        this.bestMax.setBounds(460, 340, 60, 19);
        this.buyPriceLabel.setText("prix achat");
        this.add(this.buyPriceLabel);
        this.buyPriceLabel.setBounds(10, 370, 80, 20);
        this.buyPrice.setHorizontalAlignment(4);
        this.add(this.buyPrice);
        this.buyPrice.setBounds(100, 370, 60, 19);
        this.estimatePriceLabel.setText("estimation");
        this.add(this.estimatePriceLabel);
        this.estimatePriceLabel.setBounds(180, 370, 80, 20);
        this.estimatePrice.setHorizontalAlignment(4);
        this.add(this.estimatePrice);
        this.estimatePrice.setBounds(270, 370, 60, 20);
        this.commentLabel.setText("commentaire");
        this.add(this.commentLabel);
        this.commentLabel.setBounds(10, 400, 90, 20);
        this.foodLabel.setText("plats");
        this.add(this.foodLabel);
        this.foodLabel.setBounds(10, 430, 80, 20);
        this.tempLabel.setText("T");
        this.add(this.tempLabel);
        this.tempLabel.setBounds(350, 370, 20, 20);
        this.temp.setHorizontalAlignment(4);
        this.add(this.temp);
        this.temp.setBounds(370, 370, 30, 19);
        this.degreeLabel.setText("degree");
        this.add(this.degreeLabel);
        this.degreeLabel.setBounds(410, 370, 60, 20);
        this.degree.setHorizontalAlignment(4);
        this.add(this.degree);
        this.degree.setBounds(480, 370, 40, 19);
        this.oCTitle1.setForeground(new Color(102, 102, 102));
        this.oCTitle1.setFont(new Font("Arial", 1, 12));
        this.oCTitle1.setText("informations g\u00e9n\u00e9rales");
        GroupLayout oCTitle1Layout = new GroupLayout((Container)this.oCTitle1);
        this.oCTitle1.setLayout((LayoutManager)oCTitle1Layout);
        oCTitle1Layout.setHorizontalGroup((GroupLayout.Group)oCTitle1Layout.createParallelGroup(1).add(0, 550, 32767));
        oCTitle1Layout.setVerticalGroup((GroupLayout.Group)oCTitle1Layout.createParallelGroup(1).add(0, 20, 32767));
        this.add(this.oCTitle1);
        this.oCTitle1.setBounds(0, 100, 550, 20);
        this.oCTitle2.setForeground(new Color(102, 102, 102));
        this.oCTitle2.setFont(new Font("Arial", 1, 12));
        this.oCTitle2.setText("classification");
        GroupLayout oCTitle2Layout = new GroupLayout((Container)this.oCTitle2);
        this.oCTitle2.setLayout((LayoutManager)oCTitle2Layout);
        oCTitle2Layout.setHorizontalGroup((GroupLayout.Group)oCTitle2Layout.createParallelGroup(1).add(0, 550, 32767));
        oCTitle2Layout.setVerticalGroup((GroupLayout.Group)oCTitle2Layout.createParallelGroup(1).add(0, 20, 32767));
        this.add(this.oCTitle2);
        this.oCTitle2.setBounds(0, 220, 550, 20);
        this.oCTitle3.setForeground(new Color(102, 102, 102));
        this.oCTitle3.setFont(new Font("Arial", 1, 12));
        this.oCTitle3.setText("autres informations");
        GroupLayout oCTitle3Layout = new GroupLayout((Container)this.oCTitle3);
        this.oCTitle3.setLayout((LayoutManager)oCTitle3Layout);
        oCTitle3Layout.setHorizontalGroup((GroupLayout.Group)oCTitle3Layout.createParallelGroup(1).add(0, 550, 32767));
        oCTitle3Layout.setVerticalGroup((GroupLayout.Group)oCTitle3Layout.createParallelGroup(1).add(0, 20, 32767));
        this.add(this.oCTitle3);
        this.oCTitle3.setBounds(0, 310, 550, 20);
        this.comment.setBorder(BorderFactory.createEtchedBorder());
        this.add(this.comment);
        this.comment.setBounds(100, 400, 430, 20);
        this.noteLabel.setText("note");
        this.add(this.noteLabel);
        this.noteLabel.setBounds(380, 10, 50, 20);
        GroupLayout noteLayout = new GroupLayout((Container)this.note);
        this.note.setLayout((LayoutManager)noteLayout);
        noteLayout.setHorizontalGroup((GroupLayout.Group)noteLayout.createParallelGroup(1).add(0, 88, 32767));
        noteLayout.setVerticalGroup((GroupLayout.Group)noteLayout.createParallelGroup(1).add(0, 21, 32767));
        this.add(this.note);
        this.note.setBounds(440, 10, 88, 21);
    }

    public void onNewItem(Cellar source, CellarObject co) {
        this.dispatch(co.getType());
    }

    public void onUpdateItem(Cellar source, CellarObject co) {
        this.dispatch(co.getType());
    }

    public void onDeleteItem(Cellar source, CellarObject co) {
        this.dispatch(co.getType());
    }

    private void dispatch(ObjectType type) {
        if (type == ObjectType.Area) {
            this.reloadItems(this.area, type);
        } else if (type == ObjectType.Country) {
            this.reloadItems(this.country, type);
        } else if (type == ObjectType.TypeOfWine) {
            this.reloadItems(this.cepage, type);
        } else if (type == ObjectType.Name) {
            this.reloadItems(this.appellation, type);
        } else if (type == ObjectType.Owner) {
            this.reloadItems(this.producer, type);
        } else if (type == ObjectType.BottleType) {
            this.reloadItems(this.bottle, type);
        } else if (type == ObjectType.Category) {
            this.reloadItems(this.category, type);
        } else if (type == ObjectType.Classification) {
            this.reloadItems(this.classement, type);
        }
    }

    private void reloadItems(OCAutoCompleteComboBox combo, ObjectType type) {
        if (combo == null) {
            return;
        }
        if (combo.getSelectedIndex() > 0) {
            CellarObject co = (CellarObject)combo.getSelectedItem();
            if (co != null) {
                UIHelper.pushItems(combo, type, this.m_app.activeCellar(), (Object)EMPTY_ITEM, co);
            } else {
                UIHelper.pushItems(combo, type, this.m_app.activeCellar(), (Object)EMPTY_ITEM, null);
            }
        } else {
            UIHelper.pushItems(combo, type, this.m_app.activeCellar(), (Object)EMPTY_ITEM, null);
        }
    }

    public void onStateChanged(CellarObject source) {
    }

    public void onPropertyChanged(CellarObject source, String propertyName) {
        if (propertyName.equals("Name")) {
            if (!this.name.getText().equals(this.m_wine.getName())) {
                this.name.setText(this.m_wine.getName());
            }
        } else if (propertyName.equals("Comment")) {
            if (!this.comment.getText().equals(this.m_wine.getComment())) {
                this.comment.setText(this.m_wine.getComment());
            }
        } else if (propertyName.equals("Reference")) {
            if (!this.reference.getText().equals(this.m_wine.getReference())) {
                this.reference.setText(this.m_wine.getReference());
            }
        } else if (propertyName.equals("Cuvee")) {
            if (!this.cuvee.getText().equals(this.m_wine.getCuvee())) {
                this.cuvee.setText(this.m_wine.getCuvee());
            }
        } else if (propertyName.equals("Year")) {
            this.bind(this.m_wine.getYear(), this.year);
        } else if (propertyName.equals("BestMin")) {
            this.bind(this.m_wine.getBestMin(), this.bestMin);
        } else if (propertyName.equals("BestMax")) {
            this.bind(this.m_wine.getBestMax(), this.bestMax);
        } else if (propertyName.equals("ConsumeMin")) {
            this.bind(this.m_wine.getConsumeMin(), this.consumeMin);
        } else if (propertyName.equals("ConsumeMax")) {
            this.bind(this.m_wine.getConsumeMax(), this.consumeMax);
        } else if (propertyName.equals("Temperature")) {
            this.bind(this.m_wine.getTemperature(), this.temp);
        } else if (propertyName.equals("EvaluatePrice")) {
            this.bind(this.m_wine.getEvaluatePrice(), this.estimatePrice);
        } else if (propertyName.equals("BuyPrice")) {
            this.bind(this.m_wine.getBuyPrice(), this.buyPrice);
        } else if (propertyName.equals("Degree")) {
            this.bind(this.m_wine.getDegree(), this.degree);
        } else if (propertyName.equals("Country")) {
            UIHelper.pushItems(this.country, ObjectType.Country, this.m_wine.getCellar(), (Object)EMPTY_ITEM, (CellarObject)this.m_wine.getCountry());
        } else if (propertyName.equals("Area")) {
            UIHelper.pushItems(this.area, ObjectType.Area, this.m_wine.getCellar(), (Object)EMPTY_ITEM, (CellarObject)this.m_wine.getArea());
        } else if (propertyName.equals("Cepage")) {
            UIHelper.pushItems(this.cepage, ObjectType.TypeOfWine, this.m_wine.getCellar(), (Object)EMPTY_ITEM, (CellarObject)this.m_wine.getCepage());
        } else if (propertyName.equals("Appellation")) {
            UIHelper.pushItems(this.appellation, ObjectType.Name, this.m_wine.getCellar(), (Object)EMPTY_ITEM, (CellarObject)this.m_wine.getAppellation());
        } else if (propertyName.equals("BottleType")) {
            UIHelper.pushItems(this.bottle, ObjectType.BottleType, this.m_wine.getCellar(), (Object)EMPTY_ITEM, (CellarObject)this.m_wine.getBottleType());
        } else if (propertyName.equals("Category")) {
            UIHelper.pushItems(this.category, ObjectType.Category, this.m_wine.getCellar(), (Object)EMPTY_ITEM, (CellarObject)this.m_wine.getCategory());
        } else if (propertyName.equals("Owner")) {
            UIHelper.pushItems(this.producer, ObjectType.Owner, this.m_wine.getCellar(), (Object)EMPTY_ITEM, (CellarObject)this.m_wine.getOwner());
        } else if (propertyName.equals("Classification")) {
            UIHelper.pushItems(this.classement, ObjectType.Classification, this.m_wine.getCellar(), (Object)EMPTY_ITEM, (CellarObject)this.m_wine.getClassification());
        } else if (propertyName.equals("Color")) {
            UIHelper.select(this.color, this.m_wine.getColor());
        } else if (propertyName.equals("GeneralNote")) {
            this.note.setNote(this.m_wine.getGeneralNote());
        }
    }

    public void focusGained(FocusEvent e) {
    }

    public void focusLost(FocusEvent e) {
        if (e.getSource() == this.name) {
            this.m_wine.setName(this.name.getText());
        } else if (e.getSource() == this.reference) {
            this.m_wine.setReference(this.reference.getText());
        } else if (e.getSource() == this.cuvee) {
            this.m_wine.setCuvee(this.cuvee.getText());
        } else if (e.getSource() == this.comment) {
            this.m_wine.setComment(this.comment.getText());
        } else if (e.getSource() == this.year) {
            this.m_wine.setYear(utils.tryParse(this.year.getText(), 0));
        } else if (e.getSource() == this.bestMax) {
            this.m_wine.setBestMax(utils.tryParse(this.bestMax.getText(), 0));
        } else if (e.getSource() == this.bestMin) {
            this.m_wine.setBestMin(utils.tryParse(this.bestMin.getText(), 0));
        } else if (e.getSource() == this.consumeMax) {
            this.m_wine.setConsumeMax(utils.tryParse(this.consumeMax.getText(), 0));
        } else if (e.getSource() == this.consumeMin) {
            this.m_wine.setConsumeMin(utils.tryParse(this.consumeMin.getText(), 0));
        } else if (e.getSource() == this.buyPrice) {
            this.m_wine.setBuyPrice(utils.tryParse(this.buyPrice.getText(), 0.0f));
        } else if (e.getSource() == this.estimatePrice) {
            this.m_wine.setEvaluatePrice(utils.tryParse(this.estimatePrice.getText(), 0.0f));
        } else if (e.getSource() == this.degree) {
            this.m_wine.setDegree(utils.tryParse(this.degree.getText(), 0.0f));
        } else if (e.getSource() == this.temp) {
            this.m_wine.setTemperature(utils.tryParse(this.temp.getText(), 0));
        } else if (e.getSource() == this.country) {
            Country co = (Country)this.create(ObjectType.Country, this.country);
            this.m_wine.setCountry(co);
        } else if (e.getSource() == this.area) {
            Area co = (Area)this.create(ObjectType.Area, this.area);
            this.m_wine.setArea(co);
        } else if (e.getSource() == this.category) {
            Category co = (Category)this.create(ObjectType.Category, this.category);
            this.m_wine.setCategory(co);
        } else if (e.getSource() == this.producer) {
            Owner co = (Owner)this.create2(ObjectType.Owner, this.producer);
            this.m_wine.setOwner(co);
        } else if (e.getSource() == this.bottle) {
            BottleType co = (BottleType)this.create(ObjectType.BottleType, this.bottle);
            this.m_wine.setBottleType(co);
        } else if (e.getSource() == this.cepage) {
            TypeOfWine co = (TypeOfWine)this.create(ObjectType.TypeOfWine, this.cepage);
            this.m_wine.setCepage(co);
        } else if (e.getSource() == this.appellation) {
            Name co = (Name)this.create(ObjectType.Name, this.appellation);
            this.m_wine.setAppellation(co);
        } else if (e.getSource() == this.classement) {
            Classification co = (Classification)this.create(ObjectType.Classification, this.classement);
            this.m_wine.setClassification(co);
        } else if (e.getSource() == this.color) {
            this.m_wine.setColor(UIHelper.getSelectedColor(this.color));
        }
    }

    private BaseItem create(ObjectType ot, OCAutoCompleteComboBox combo) {
        if (combo.getSelectedIndex() == 0) {
            return null;
        }
        if (combo.getSelectedIndex() > 0) {
            return (BaseItem)combo.getSelectedItem();
        }
        String text = combo.getSelectedItem().toString();
        if (text != null && !text.trim().equals("")) {
            BaseItem item = (BaseItem)this.m_wine.getCellar().createItem(ot);
            item.setName(text);
            item.save();
            return item;
        }
        combo.setSelectedIndex(0);
        return null;
    }

    private Contact create2(ObjectType ot, OCAutoCompleteComboBox combo) {
        if (combo.getSelectedIndex() == 0) {
            return null;
        }
        if (combo.getSelectedIndex() > 0) {
            return (Contact)combo.getSelectedItem();
        }
        String text = combo.getSelectedItem().toString();
        if (text != null && !text.trim().equals("")) {
            Contact item = (Contact)this.m_wine.getCellar().createItem(ot);
            item.setName(text);
            item.save();
            return item;
        }
        combo.setSelectedIndex(0);
        return null;
    }

    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == this.note) {
            this.m_wine.setGeneralNote(this.note.getNote());
        }
    }
}

