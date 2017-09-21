/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Cellar
 *  opencellar.framework.CellarObject
 *  opencellar.framework.ObjectState
 *  opencellar.framework.ObjectType
 *  opencellar.framework.Provider
 *  opencellar.framework.PurchaseSales
 *  opencellar.framework.Wine
 */
package opencellar.ui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import opencellar.application.IApplication;
import opencellar.application.utils;
import opencellar.framework.Cellar;
import opencellar.framework.CellarObject;
import opencellar.framework.ObjectState;
import opencellar.framework.ObjectType;
import opencellar.framework.Provider;
import opencellar.framework.PurchaseSales;
import opencellar.framework.Wine;
import opencellar.ui.ComboItem;
import opencellar.ui.OCAutoCompleteComboBox;
import opencellar.ui.OCComment;
import opencellar.ui.UIHelper;

public class PurchaseSalesDialog
extends JDialog
implements ActionListener {
    private IApplication m_app = null;
    private PurchaseSales m_psales = null;
    static final String EMPTY_STR = "(Vide)";
    static final ComboItem EMPTY_ITEM = new ComboItem("(Vide)");
    public static final int ACTION_SAVE = 0;
    public static final int ACTION_CANCEL = 1;
    private int m_action = 1;
    DateFormat format = DateFormat.getDateInstance(3);
    private JTextField buy;
    private JLabel buyLabel;
    private JButton cancel;
    private OCComment comment;
    private JLabel commentLabel;
    private JTextField consume;
    private JLabel consumeLabel;
    private JTextField date;
    private JLabel dateLabel;
    private JPanel jPanel1;
    private OCAutoCompleteComboBox provider;
    private JButton save;
    private JLabel supplierLabel;
    private JTextField unitPrice;
    private JLabel unitPriceLabel;

    public PurchaseSalesDialog(Frame owner) {
        super(owner, true);
        this.initComponents();
    }

    public final void setDataSource(IApplication app, PurchaseSales psales, Wine wine) {
        if (app == null) {
            throw new IllegalArgumentException("app == null");
        }
        this.m_app = app;
        if (psales == null) {
            throw new IllegalArgumentException("psales == null");
        }
        this.m_psales = psales;
        this.performTranslation();
        this.bindFromData();
        this.init();
    }

    private void performTranslation() {
        if (this.m_psales.getWine() != null) {
            ((TitledBorder)this.jPanel1.getBorder()).setTitle(this.m_psales.getWine().getName().trim());
        } else {
            ((TitledBorder)this.jPanel1.getBorder()).setTitle("Nouveau vin");
        }
        this.dateLabel.setText(this.m_app.getRS("SALES_DATE"));
        this.supplierLabel.setText(this.m_app.getRS("SALES_PROVIDER"));
        this.buyLabel.setText(this.m_app.getRS("SALES_PURCHASE"));
        this.unitPriceLabel.setText(this.m_app.getRS("SALES_UNIT_PRICE"));
        this.consumeLabel.setText(this.m_app.getRS("SALES_CONSUME"));
        this.commentLabel.setText(this.m_app.getRS("SALES_COMMENT"));
        this.save.setText(this.m_app.getRS("BTN_SAVE"));
        this.cancel.setText(this.m_app.getRS("BTN_CANCEL"));
    }

    private void init() {
        this.provider.addFocusListener(new FocusListener(){

            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                if (PurchaseSalesDialog.this.provider.getSelectedIndex() == -1) {
                    String text = PurchaseSalesDialog.this.provider.getSelectedItem().toString();
                    if (text != null && !text.trim().equals("")) {
                        Provider item = (Provider)PurchaseSalesDialog.this.m_app.activeCellar().createItem(ObjectType.Provider);
                        item.setName(text);
                        item.save();
                        UIHelper.pushItems(PurchaseSalesDialog.this.provider, ObjectType.Provider, PurchaseSalesDialog.this.m_app.activeCellar(), (Object)PurchaseSalesDialog.EMPTY_ITEM, (CellarObject)item);
                    } else {
                        PurchaseSalesDialog.this.provider.setSelectedIndex(0);
                    }
                }
            }
        });
        this.cancel.addActionListener(this);
        this.save.addActionListener(this);
    }

    private void bindFromData() {
        UIHelper.pushItems(this.provider, ObjectType.Provider, this.m_app.activeCellar(), (Object)EMPTY_ITEM, (CellarObject)this.m_psales.getProvider());
        this.date.setText(this.format.format(this.m_psales.getDate()));
        this.comment.setText(this.m_psales.getComment());
        this.bind(this.m_psales.getConsumedBottles(), this.consume);
        this.bind(this.m_psales.getPurchasedBottles(), this.buy);
        this.bind(this.m_psales.getUnitPrice(), this.unitPrice);
        if (this.m_psales.getState() == ObjectState.New && this.m_psales.getUnitPrice() == 0.0f && this.m_psales.getWine() != null) {
            this.bind(this.m_psales.getWine().getBuyPrice(), this.unitPrice);
        }
    }

    public final int getAction() {
        return this.m_action;
    }

    public final void bindToData() {
        try {
            this.m_psales.setDate(this.format.parse(this.date.getText()));
        }
        catch (Exception ex) {
            this.m_psales.setDate(new Date());
        }
        this.m_psales.setPurchasedBottles(utils.tryParse(this.buy.getText(), 0));
        this.m_psales.setConsumedBottles(utils.tryParse(this.consume.getText(), 0));
        this.m_psales.setUnitPrice(utils.tryParse(this.unitPrice.getText(), 0.0f));
        this.m_psales.setComment(this.comment.getText());
        if (this.provider.getSelectedIndex() > 0) {
            this.m_psales.setProvider((Provider)this.provider.getSelectedItem());
        } else {
            this.m_psales.setProvider(null);
        }
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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.save) {
            this.m_action = 0;
            this.setVisible(false);
        } else if (e.getSource() == this.cancel) {
            this.m_action = 1;
            this.setVisible(false);
        }
    }

    private void initComponents() {
        this.jPanel1 = new JPanel();
        this.dateLabel = new JLabel();
        this.supplierLabel = new JLabel();
        this.buyLabel = new JLabel();
        this.consumeLabel = new JLabel();
        this.commentLabel = new JLabel();
        this.unitPriceLabel = new JLabel();
        this.date = new JTextField();
        this.provider = new OCAutoCompleteComboBox();
        this.buy = new JTextField();
        this.consume = new JTextField();
        this.unitPrice = new JTextField();
        this.comment = new OCComment();
        this.save = new JButton();
        this.cancel = new JButton();
        this.getContentPane().setLayout(null);
        this.jPanel1.setLayout(null);
        this.jPanel1.setBorder(BorderFactory.createTitledBorder("%nom du vin%"));
        this.dateLabel.setText("date");
        this.jPanel1.add(this.dateLabel);
        this.dateLabel.setBounds(10, 30, 100, 20);
        this.supplierLabel.setText("fournisseur");
        this.jPanel1.add(this.supplierLabel);
        this.supplierLabel.setBounds(10, 60, 100, 20);
        this.buyLabel.setText("achat");
        this.jPanel1.add(this.buyLabel);
        this.buyLabel.setBounds(10, 90, 100, 20);
        this.consumeLabel.setText("consommation");
        this.jPanel1.add(this.consumeLabel);
        this.consumeLabel.setBounds(10, 120, 100, 20);
        this.commentLabel.setText("commentaire");
        this.jPanel1.add(this.commentLabel);
        this.commentLabel.setBounds(10, 150, 100, 20);
        this.unitPriceLabel.setText("prix \u00e0 l'unit\u00e9");
        this.jPanel1.add(this.unitPriceLabel);
        this.unitPriceLabel.setBounds(210, 90, 90, 20);
        this.jPanel1.add(this.date);
        this.date.setBounds(110, 30, 100, 19);
        this.jPanel1.add(this.provider);
        this.provider.setBounds(110, 60, 270, 22);
        this.buy.setHorizontalAlignment(4);
        this.jPanel1.add(this.buy);
        this.buy.setBounds(110, 90, 60, 19);
        this.consume.setHorizontalAlignment(4);
        this.jPanel1.add(this.consume);
        this.consume.setBounds(110, 120, 60, 19);
        this.unitPrice.setHorizontalAlignment(4);
        this.jPanel1.add(this.unitPrice);
        this.unitPrice.setBounds(310, 90, 70, 19);
        this.comment.setBorder(BorderFactory.createEtchedBorder());
        this.jPanel1.add(this.comment);
        this.comment.setBounds(110, 150, 270, 20);
        this.getContentPane().add(this.jPanel1);
        this.jPanel1.setBounds(10, 10, 390, 210);
        this.save.setText("save");
        this.getContentPane().add(this.save);
        this.save.setBounds(150, 230, 110, 23);
        this.cancel.setText("cancel");
        this.getContentPane().add(this.cancel);
        this.cancel.setBounds(290, 230, 100, 23);
    }

}

