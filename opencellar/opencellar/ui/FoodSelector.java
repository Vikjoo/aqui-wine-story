/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.CellarObject
 *  opencellar.framework.FoodType
 *  opencellar.framework.ICellarObjectListener
 *  opencellar.framework.Wine
 */
package opencellar.ui;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import opencellar.application.IApplication;
import opencellar.application.utils;
import opencellar.framework.CellarObject;
import opencellar.framework.FoodType;
import opencellar.framework.ICellarObjectListener;
import opencellar.framework.Wine;

public class FoodSelector
extends JPanel
implements ICellarObjectListener,
MouseListener {
    private Wine m_wine = null;
    private IApplication m_app = null;
    private HashMap pictures;
    private JLabel aperitifs;
    private JLabel clear;
    private JLabel crustaces;
    private JLabel dessert;
    private JLabel fromage;
    private JLabel poissons;
    private JLabel viandeBlanche;
    private JLabel viandeRouge;

    public FoodSelector() {
        this.initComponents();
    }

    public final void setDatasource(IApplication app, Wine wine) {
        if (app == null) {
            throw new IllegalArgumentException("app == null");
        }
        if (wine == null) {
            throw new IllegalArgumentException("wine == null");
        }
        this.m_wine = wine;
        this.m_app = app;
        this.initCustomComponents();
        this.setPictures();
        this.m_wine.addListener((ICellarObjectListener)this);
    }

    public final void close() {
        this.m_wine.removeListener((ICellarObjectListener)this);
    }

    private void initCustomComponents() {
        int labels = 0;
        for (int i = 0; i < this.getComponentCount(); ++i) {
            if (!(this.getComponent(i) instanceof JLabel)) continue;
            JLabel theLabel = (JLabel)this.getComponent(i);
            theLabel.setLocation(theLabel.getLocation().x + labels * 4, theLabel.getLocation().y);
            theLabel.setCursor(Cursor.getPredefinedCursor(12));
            theLabel.addMouseListener(this);
            ++labels;
        }
        this.clear.setToolTipText("Effacer les associations");
        this.aperitifs.setToolTipText("Ap\u00e9ritifs");
        this.crustaces.setToolTipText("Crustac\u00e9s");
        this.poissons.setToolTipText("Poissons");
        this.viandeBlanche.setToolTipText("Viandes blanches");
        this.viandeRouge.setToolTipText("Viandes rouges");
        this.fromage.setToolTipText("Fromages");
        this.dessert.setToolTipText("Desserts");
        this.pictures = new HashMap();
        this.pictures.put("ClearOn", utils.getFoodIcon("1"));
        this.pictures.put("ApOn", utils.getFoodIcon("8"));
        this.pictures.put("ApOff", utils.getFoodIcon("8_d"));
        this.pictures.put("CrOn", utils.getFoodIcon("32"));
        this.pictures.put("CrOff", utils.getFoodIcon("32_d"));
        this.pictures.put("PoOn", utils.getFoodIcon("4"));
        this.pictures.put("PoOff", utils.getFoodIcon("4_d"));
        this.pictures.put("VrOn", utils.getFoodIcon("64"));
        this.pictures.put("VrOff", utils.getFoodIcon("64_d"));
        this.pictures.put("VbOn", utils.getFoodIcon("2"));
        this.pictures.put("VbOff", utils.getFoodIcon("2_d"));
        this.pictures.put("FrOn", utils.getFoodIcon("16"));
        this.pictures.put("FrOff", utils.getFoodIcon("16_d"));
        this.pictures.put("DeOn", utils.getFoodIcon("128"));
        this.pictures.put("DeOff", utils.getFoodIcon("128_d"));
        this.clear.setIcon((Icon)this.pictures.get("ClearOn"));
    }

    private void setPictures() {
        FoodType fd = this.m_wine.getFood();
        if (fd.isAperitifs()) {
            this.aperitifs.setIcon((Icon)this.pictures.get("ApOn"));
        } else {
            this.aperitifs.setIcon((Icon)this.pictures.get("ApOff"));
        }
        if (fd.isSheelFish()) {
            this.crustaces.setIcon((Icon)this.pictures.get("CrOn"));
        } else {
            this.crustaces.setIcon((Icon)this.pictures.get("CrOff"));
        }
        if (fd.isFish()) {
            this.poissons.setIcon((Icon)this.pictures.get("PoOn"));
        } else {
            this.poissons.setIcon((Icon)this.pictures.get("PoOff"));
        }
        if (fd.isRedMeat()) {
            this.viandeRouge.setIcon((Icon)this.pictures.get("VrOn"));
        } else {
            this.viandeRouge.setIcon((Icon)this.pictures.get("VrOff"));
        }
        if (fd.isWhiteMeat()) {
            this.viandeBlanche.setIcon((Icon)this.pictures.get("VbOn"));
        } else {
            this.viandeBlanche.setIcon((Icon)this.pictures.get("VbOff"));
        }
        if (fd.isCheese()) {
            this.fromage.setIcon((Icon)this.pictures.get("FrOn"));
        } else {
            this.fromage.setIcon((Icon)this.pictures.get("FrOff"));
        }
        if (fd.isDessert()) {
            this.dessert.setIcon((Icon)this.pictures.get("DeOn"));
        } else {
            this.dessert.setIcon((Icon)this.pictures.get("DeOff"));
        }
    }

    public void onStateChanged(CellarObject source) {
    }

    public void onPropertyChanged(CellarObject source, String propertyName) {
        if (propertyName.equals("Food")) {
            this.setPictures();
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        JLabel sender = (JLabel)e.getSource();
        if (sender == this.clear) {
            this.m_wine.getFood().clear();
        } else if (sender == this.aperitifs) {
            this.m_wine.getFood().setAperitifs(!this.m_wine.getFood().isAperitifs());
        } else if (sender == this.crustaces) {
            this.m_wine.getFood().setSheelFish(!this.m_wine.getFood().isSheelFish());
        } else if (sender == this.poissons) {
            this.m_wine.getFood().setFish(!this.m_wine.getFood().isFish());
        } else if (sender == this.viandeBlanche) {
            this.m_wine.getFood().setWhiteMeat(!this.m_wine.getFood().isWhiteMeat());
        } else if (sender == this.viandeRouge) {
            this.m_wine.getFood().setRedMeat(!this.m_wine.getFood().isRedMeat());
        } else if (sender == this.fromage) {
            this.m_wine.getFood().setCheese(!this.m_wine.getFood().isCheese());
        } else if (sender == this.dessert) {
            this.m_wine.getFood().setDessert(!this.m_wine.getFood().isDessert());
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    private void initComponents() {
        this.clear = new JLabel();
        this.aperitifs = new JLabel();
        this.crustaces = new JLabel();
        this.poissons = new JLabel();
        this.viandeRouge = new JLabel();
        this.viandeBlanche = new JLabel();
        this.fromage = new JLabel();
        this.dessert = new JLabel();
        this.setLayout(null);
        this.add(this.clear);
        this.clear.setBounds(10, 0, 32, 32);
        this.add(this.aperitifs);
        this.aperitifs.setBounds(60, 0, 32, 32);
        this.add(this.crustaces);
        this.crustaces.setBounds(100, 0, 32, 32);
        this.add(this.poissons);
        this.poissons.setBounds(140, 0, 32, 32);
        this.add(this.viandeRouge);
        this.viandeRouge.setBounds(180, 0, 32, 32);
        this.add(this.viandeBlanche);
        this.viandeBlanche.setBounds(220, 0, 32, 32);
        this.add(this.fromage);
        this.fromage.setBounds(260, 0, 32, 32);
        this.add(this.dessert);
        this.dessert.setBounds(300, 0, 32, 32);
    }
}

