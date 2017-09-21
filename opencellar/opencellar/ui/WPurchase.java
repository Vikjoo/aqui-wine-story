/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.CellarObject
 *  opencellar.framework.CellarObjectCollection
 *  opencellar.framework.ICellarObjectListener
 *  opencellar.framework.PurchaseSales
 *  opencellar.framework.PurchaseSalesWorkqueue
 *  opencellar.framework.PurchaseSalesWorkqueueItem
 *  opencellar.framework.RackItemCollection
 *  opencellar.framework.Wine
 *  opencellar.framework.WorkqueueItemType
 *  org.jdesktop.layout.GroupLayout
 *  org.jdesktop.layout.GroupLayout$Group
 *  org.jdesktop.layout.GroupLayout$ParallelGroup
 */
package opencellar.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import opencellar.application.DialogResult;
import opencellar.application.IApplication;
import opencellar.application.MessageButtonType;
import opencellar.application.MessageIconType;
import opencellar.application.MessageType;
import opencellar.framework.CellarObject;
import opencellar.framework.CellarObjectCollection;
import opencellar.framework.ICellarObjectListener;
import opencellar.framework.PurchaseSales;
import opencellar.framework.PurchaseSalesWorkqueue;
import opencellar.framework.PurchaseSalesWorkqueueItem;
import opencellar.framework.RackItemCollection;
import opencellar.framework.Wine;
import opencellar.framework.WorkqueueItemType;
import opencellar.ui.CustomEvent;
import opencellar.ui.CustomEventDispatcher;
import opencellar.ui.ICustomListener;
import opencellar.ui.OCLine;
import opencellar.ui.OCTitle;
import opencellar.ui.TextPurchaseSalesViewer;
import opencellar.ui.UIHelper;
import org.jdesktop.layout.GroupLayout;

public final class WPurchase
extends JPanel
implements ICellarObjectListener,
ICustomListener,
ActionListener {
    private JPopupMenu popupMenu = null;
    private JMenuItem open;
    private JMenuItem delete;
    private JMenuItem cancelDelete;
    private IApplication m_app;
    private Wine m_wine;
    private CustomEventDispatcher m_dispatcher = null;
    private TextPurchaseSalesViewer viewer;
    private DecimalFormat m_df = new DecimalFormat("#.00");
    private PurchaseSales m_sales;
    private JLabel amout;
    private JLabel amoutLabel;
    private JLabel averagePrice;
    private JLabel averagePriceLabel;
    private JTextField bottleCount;
    private JLabel bottleCountLabel;
    private JLabel buyBottles;
    private JLabel buyBottlesLabel;
    private JLabel consumeBottles;
    private JLabel consumeBottlesLabel;
    private JLabel estimate;
    private JLabel estimateLabel;
    private JScrollPane jScrollPane1;
    private JCheckBox manuelManagment;
    private JLabel newPurchaseLabel;
    private OCLine oCLine1;
    private OCTitle oCTitle1;

    public WPurchase() {
        this.initComponents();
    }

    public void setDatasource(IApplication app, Wine w, CustomEventDispatcher dispatcher) {
        if (app == null) {
            throw new IllegalArgumentException("app == null");
        }
        if (w == null) {
            throw new IllegalArgumentException("wine == null");
        }
        if (dispatcher == null) {
            throw new IllegalArgumentException("dispatcher == null");
        }
        this.m_dispatcher = dispatcher;
        this.m_wine = w;
        this.m_app = app;
        this.viewer = new TextPurchaseSalesViewer(this.m_wine);
        this.viewer.setDispatcher(this.m_dispatcher);
        this.m_dispatcher.add(this);
        this.jScrollPane1.setViewportView(this.viewer);
        this.popupMenu = new JPopupMenu();
        this.open = new JMenuItem(this.m_app.getRS("SALES_MNU_OPEN"));
        this.delete = new JMenuItem(this.m_app.getRS("SALES_MNU_DELETE"));
        this.cancelDelete = new JMenuItem(this.m_app.getRS("SALES_MNU_CANCEL_DELETE"));
        this.popupMenu.add(this.open);
        this.popupMenu.addSeparator();
        this.popupMenu.add(this.delete);
        this.popupMenu.add(this.cancelDelete);
        this.open.addActionListener(this);
        this.delete.addActionListener(this);
        this.cancelDelete.addActionListener(this);
        this.performTranslation();
        this.calc();
        this.newPurchaseLabel.setCursor(Cursor.getPredefinedCursor(12));
        this.newPurchaseLabel.addMouseListener(new MouseListener(){

            public void mouseClicked(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    WPurchase.this.m_dispatcher.setSource(null);
                    WPurchase.this.m_dispatcher.notify(0);
                }
            }

            public void mouseReleased(MouseEvent e) {
            }
        });
        this.m_wine.addListener((ICellarObjectListener)this);
    }

    public void close() {
        this.viewer.close();
        this.m_wine.removeListener((ICellarObjectListener)this);
    }

    private void performTranslation() {
        this.newPurchaseLabel.setText(this.m_app.getRS("SALES_NEW"));
        this.oCTitle1.setText(this.m_app.getRS("SALES_STATS"));
        this.manuelManagment.setText(this.m_app.getRS("SALES_MANUAL"));
        this.estimateLabel.setText(this.m_app.getRS("SALES_ESTIMATE_PRICE"));
        this.amoutLabel.setText(this.m_app.getRS("SALES_TOTAL_PRICE"));
        this.buyBottlesLabel.setText(this.m_app.getRS("SALES_BOTTLES_BUY"));
        this.averagePriceLabel.setText(this.m_app.getRS("SALES_AVG_BUY_PRICE"));
        this.consumeBottlesLabel.setText(this.m_app.getRS("SALES_BOTTLES_CONSUME"));
        this.bottleCountLabel.setText(this.m_app.getRS("SALES_TOTAL_BOTTLES"));
        this.oCTitle1.setToolTipText(UIHelper.buildExtendedToolTip(this.m_app.getRS("WIN_STATS_CAPTION"), this.m_app.getRS("WIN_STATS_TEXT"), null));
        this.viewer.setStringResources(this.m_app.getRS("SALES_ITEMS_CONSUME"), this.m_app.getRS("SALES_ITEMS_SOLD"), this.m_app.getRS("SALES_ITEMS_TOTAL"));
    }

    public void onStateChanged(CellarObject source) {
    }

    public void onPropertyChanged(CellarObject source, String propertyName) {
        if (propertyName.equals("RackItems")) {
            this.calc();
        } else if (propertyName.equals("PurchaseSales")) {
            this.calc();
        }
    }

    private void calc() {
        int bottles = this.m_wine.getRackItems().size();
        if (this.m_wine.isManualManagment()) {
            bottles = this.m_wine.getBottles();
        }
        float total = 0.0f;
        int consume = 0;
        int buy = 0;
        for (int i = 0; i < this.m_wine.getPurchasesSales().size(); ++i) {
            PurchaseSales ps = (PurchaseSales)this.m_wine.getPurchasesSales().get(i);
            consume += ps.getConsumedBottles();
            buy += ps.getPurchasedBottles();
            total += (float)ps.getPurchasedBottles() * ps.getUnitPrice();
        }
        this.bottleCount.setText(String.valueOf(bottles));
        this.consumeBottles.setText(String.valueOf(consume));
        this.buyBottles.setText(String.valueOf(buy));
        this.amout.setText(this.m_df.format(total) + " " + UIHelper.getCurrencySymbol());
        if (buy > 0) {
            this.averagePrice.setText(this.m_df.format(total / (float)buy) + " " + UIHelper.getCurrencySymbol());
        } else {
            this.averagePrice.setText("-");
        }
        this.estimate.setText(this.m_df.format((float)bottles * this.m_wine.getEvaluatePrice()) + " " + UIHelper.getCurrencySymbol());
    }

    public void eventDispatched(CustomEvent evt) {
        if (evt.getEventId() == 2) {
            this.m_sales = (PurchaseSales)evt.getSource();
            this.showPopup(this.m_sales);
        }
    }

    private void showPopup(PurchaseSales psales) {
        WorkqueueItemType type = this.m_wine.getPurchaseSalesQueue().getType(psales);
        if (type == WorkqueueItemType.Delete) {
            this.delete.setVisible(false);
            this.cancelDelete.setVisible(true);
        } else {
            this.delete.setVisible(true);
            this.cancelDelete.setVisible(false);
        }
        this.popupMenu.show(this.viewer, this.viewer.getLastPoint().x, this.viewer.getLastPoint().y);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.open) {
            this.m_dispatcher.setSource((Object)this.m_sales);
            this.m_dispatcher.notify(1);
        } else if (e.getSource() == this.cancelDelete) {
            this.m_wine.getPurchaseSalesQueue().remove(WorkqueueItemType.Delete, this.m_sales);
        } else if (e.getSource() == this.delete && this.m_app.showMessage(null, this.m_app.getRS("SALES_DELETE_DIALOG"), MessageType.Confirm, MessageIconType.Question, MessageButtonType.YesNo) == DialogResult.Yes) {
            this.m_wine.getPurchaseSalesQueue().set(new PurchaseSalesWorkqueueItem(this.m_sales, WorkqueueItemType.Delete));
        }
    }

    private void initComponents() {
        this.newPurchaseLabel = new JLabel();
        this.oCTitle1 = new OCTitle();
        this.buyBottlesLabel = new JLabel();
        this.averagePriceLabel = new JLabel();
        this.consumeBottlesLabel = new JLabel();
        this.bottleCountLabel = new JLabel();
        this.amoutLabel = new JLabel();
        this.estimateLabel = new JLabel();
        this.manuelManagment = new JCheckBox();
        this.oCLine1 = new OCLine();
        this.jScrollPane1 = new JScrollPane();
        this.bottleCount = new JTextField();
        this.buyBottles = new JLabel();
        this.consumeBottles = new JLabel();
        this.averagePrice = new JLabel();
        this.amout = new JLabel();
        this.estimate = new JLabel();
        this.setLayout(null);
        this.newPurchaseLabel.setText("## cliquez ici pour cr\u00e9er une nouvelle fiche");
        this.add(this.newPurchaseLabel);
        this.newPurchaseLabel.setBounds(10, 10, 470, 20);
        this.oCTitle1.setForeground(new Color(102, 102, 102));
        this.oCTitle1.setFont(new Font("Arial", 1, 12));
        this.oCTitle1.setText("statistiques");
        GroupLayout oCTitle1Layout = new GroupLayout((Container)this.oCTitle1);
        this.oCTitle1.setLayout((LayoutManager)oCTitle1Layout);
        oCTitle1Layout.setHorizontalGroup((GroupLayout.Group)oCTitle1Layout.createParallelGroup(1).add(0, 550, 32767));
        oCTitle1Layout.setVerticalGroup((GroupLayout.Group)oCTitle1Layout.createParallelGroup(1).add(0, 20, 32767));
        this.add(this.oCTitle1);
        this.oCTitle1.setBounds(0, 40, 550, 20);
        this.buyBottlesLabel.setText("nombre de bouteilles achet\u00e9es");
        this.add(this.buyBottlesLabel);
        this.buyBottlesLabel.setBounds(10, 70, 220, 20);
        this.averagePriceLabel.setText("moyenne des achats");
        this.add(this.averagePriceLabel);
        this.averagePriceLabel.setBounds(300, 70, 130, 20);
        this.consumeBottlesLabel.setText("nombre de bouteilles consomm\u00e9es");
        this.add(this.consumeBottlesLabel);
        this.consumeBottlesLabel.setBounds(10, 100, 230, 20);
        this.bottleCountLabel.setText("nombre de bouteilles en cave");
        this.add(this.bottleCountLabel);
        this.bottleCountLabel.setBounds(10, 130, 220, 20);
        this.amoutLabel.setText("total des achats");
        this.add(this.amoutLabel);
        this.amoutLabel.setBounds(300, 100, 130, 20);
        this.estimateLabel.setText("estimation");
        this.add(this.estimateLabel);
        this.estimateLabel.setBounds(300, 130, 130, 20);
        this.manuelManagment.setText("g\u00e9rer manuellement les bouteilles pour ce vin");
        this.manuelManagment.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.manuelManagment.setEnabled(false);
        this.manuelManagment.setMargin(new Insets(0, 0, 0, 0));
        this.add(this.manuelManagment);
        this.manuelManagment.setBounds(10, 160, 490, 15);
        GroupLayout oCLine1Layout = new GroupLayout((Container)this.oCLine1);
        this.oCLine1.setLayout((LayoutManager)oCLine1Layout);
        oCLine1Layout.setHorizontalGroup((GroupLayout.Group)oCLine1Layout.createParallelGroup(1).add(0, 550, 32767));
        oCLine1Layout.setVerticalGroup((GroupLayout.Group)oCLine1Layout.createParallelGroup(1).add(0, 10, 32767));
        this.add(this.oCLine1);
        this.oCLine1.setBounds(0, 180, 550, 10);
        this.jScrollPane1.setHorizontalScrollBarPolicy(31);
        this.add(this.jScrollPane1);
        this.jScrollPane1.setBounds(10, 200, 520, 260);
        this.bottleCount.setHorizontalAlignment(4);
        this.bottleCount.setText("0");
        this.bottleCount.setEnabled(false);
        this.add(this.bottleCount);
        this.bottleCount.setBounds(240, 130, 40, 19);
        this.buyBottles.setHorizontalAlignment(4);
        this.buyBottles.setText("0");
        this.add(this.buyBottles);
        this.buyBottles.setBounds(240, 70, 40, 20);
        this.consumeBottles.setHorizontalAlignment(4);
        this.consumeBottles.setText("0");
        this.add(this.consumeBottles);
        this.consumeBottles.setBounds(240, 100, 40, 20);
        this.averagePrice.setHorizontalAlignment(4);
        this.averagePrice.setText("0");
        this.add(this.averagePrice);
        this.averagePrice.setBounds(450, 70, 70, 20);
        this.amout.setHorizontalAlignment(4);
        this.amout.setText("0");
        this.add(this.amout);
        this.amout.setBounds(450, 100, 70, 20);
        this.estimate.setHorizontalAlignment(4);
        this.estimate.setText("0");
        this.add(this.estimate);
        this.estimate.setBounds(450, 130, 70, 20);
    }

}

