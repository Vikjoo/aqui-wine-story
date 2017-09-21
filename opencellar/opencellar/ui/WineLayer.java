/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Cellar
 *  opencellar.framework.CellarObject
 *  opencellar.framework.CellarObjectCollection
 *  opencellar.framework.Image
 *  opencellar.framework.Note
 *  opencellar.framework.NoteWorkqueue
 *  opencellar.framework.NoteWorkqueueItem
 *  opencellar.framework.ObjectType
 *  opencellar.framework.PurchaseSales
 *  opencellar.framework.PurchaseSalesWorkqueue
 *  opencellar.framework.PurchaseSalesWorkqueueItem
 *  opencellar.framework.Rack
 *  opencellar.framework.RackItemCollection
 *  opencellar.framework.RackItemWorkqueue
 *  opencellar.framework.Wine
 *  opencellar.framework.WorkqueueItemType
 *  org.jdesktop.layout.GroupLayout
 *  org.jdesktop.layout.GroupLayout$Group
 *  org.jdesktop.layout.GroupLayout$ParallelGroup
 */
package opencellar.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import opencellar.application.AppSettings;
import opencellar.application.Application;
import opencellar.application.DialogResult;
import opencellar.application.IApplication;
import opencellar.application.IConfirmDialogWindow;
import opencellar.application.IDialogWindow;
import opencellar.application.IWindow;
import opencellar.application.MessageButtonType;
import opencellar.application.MessageIconType;
import opencellar.application.MessageType;
import opencellar.application.SettingCollection;
import opencellar.application.WindowPositionSaver3;
import opencellar.application.WindowType;
import opencellar.application.WineTabPageType;
import opencellar.framework.Cellar;
import opencellar.framework.CellarObject;
import opencellar.framework.CellarObjectCollection;
import opencellar.framework.Image;
import opencellar.framework.Note;
import opencellar.framework.NoteWorkqueue;
import opencellar.framework.NoteWorkqueueItem;
import opencellar.framework.ObjectType;
import opencellar.framework.PurchaseSales;
import opencellar.framework.PurchaseSalesWorkqueue;
import opencellar.framework.PurchaseSalesWorkqueueItem;
import opencellar.framework.Rack;
import opencellar.framework.RackItemCollection;
import opencellar.framework.RackItemWorkqueue;
import opencellar.framework.Wine;
import opencellar.framework.WorkqueueItemType;
import opencellar.ui.CheckBottlesLayer;
import opencellar.ui.CustomEvent;
import opencellar.ui.CustomEventDispatcher;
import opencellar.ui.ICustomListener;
import opencellar.ui.MainLayer;
import opencellar.ui.NoteLayer;
import opencellar.ui.OCLine;
import opencellar.ui.OCPicture;
import opencellar.ui.PurchaseSalesDialog;
import opencellar.ui.RackSelector;
import opencellar.ui.TextRackViewer;
import opencellar.ui.UIHelper;
import opencellar.ui.WInformations;
import opencellar.ui.WNote;
import opencellar.ui.WPurchase;
import org.jdesktop.layout.GroupLayout;

public final class WineLayer
extends JPanel
implements ICustomListener {
    private WInformations info;
    private WPurchase purchase;
    private WNote note;
    private Wine m_wine;
    private IApplication m_app;
    private TextRackViewer textRackViewer;
    private String m_imagePath = null;
    private JLabel imageLabel;
    private OCPicture image;
    private CustomEventDispatcher dispatcher = new CustomEventDispatcher(null);
    private JPanel commandBarPane;
    private JTabbedPane tabbedPane;

    public WineLayer() {
        this.initComponents();
    }

    public JPanel getCommandBarPane() {
        return this.commandBarPane;
    }

    public void init(IApplication app, Wine w) {
        if (app == null) {
            throw new IllegalArgumentException("app == null");
        }
        if (w == null) {
            throw new IllegalArgumentException("wine == null");
        }
        this.m_app = app;
        this.m_wine = w;
        this.dispatcher.add(this);
        this.m_wine.createBackupPoint();
        this.info = new WInformations();
        this.info.setDatasource(app, w);
        JPanel paneInfo = new JPanel();
        this.tabbedPane.addTab(app.getRS("WIN_TAB_INFO"), paneInfo);
        paneInfo.setLayout(new BorderLayout());
        paneInfo.add((Component)this.info, "Center");
        JScrollPane paneRackItems = new JScrollPane();
        paneRackItems.setAutoscrolls(true);
        paneRackItems.setHorizontalScrollBarPolicy(31);
        this.textRackViewer = new TextRackViewer(this.m_wine);
        this.textRackViewer.addPropertyChangeListener("rack", new PropertyChangeListener(){

            public void propertyChange(PropertyChangeEvent evt) {
                WineLayer.this.showRackItemsWindow(WineLayer.this.textRackViewer.getRack());
            }
        });
        this.textRackViewer.setPlaceBottlesString(app.getRS("WIN_RCK_SHOW_CHOOSE_ITEMS"));
        paneRackItems.setViewportView(this.textRackViewer);
        this.tabbedPane.addTab(app.getRS("WIN_TAB_RACK"), paneRackItems);
        this.purchase = new WPurchase();
        this.purchase.setDatasource(app, w, this.dispatcher);
        JPanel paneSales = new JPanel();
        this.tabbedPane.addTab(app.getRS("WIN_TAB_SALES"), paneSales);
        paneSales.setLayout(new BorderLayout());
        paneSales.add((Component)this.purchase, "Center");
        this.note = new WNote();
        this.note.setDatasource(app, w, this.dispatcher);
        JPanel paneNotes = new JPanel();
        this.tabbedPane.addTab(app.getRS("WIN_TAB_NOTES"), paneNotes);
        paneNotes.setLayout(new BorderLayout());
        paneNotes.add((Component)this.note, "Center");
        JPanel paneImage = new JPanel();
        this.tabbedPane.addTab(app.getRS("WIN_TAB_IMAGE"), paneImage);
        paneImage.setLayout(null);
        this.imageLabel = new JLabel();
        this.imageLabel.setCursor(Cursor.getPredefinedCursor(12));
        this.imageLabel.addMouseListener(new MouseListener(){

            public void mouseClicked(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                WineLayer.this.showImageDialog();
            }

            public void mouseReleased(MouseEvent e) {
            }
        });
        this.imageLabel.setText(this.m_app.getRS("WIN_SELECT_IMG"));
        this.imageLabel.setBounds(5, 5, 535, 25);
        this.imageLabel.setBackground(Color.ORANGE);
        paneImage.add(this.imageLabel);
        OCLine line = new OCLine();
        line.setBounds(5, 27, 535, 1);
        paneImage.add(line);
        this.image = new OCPicture();
        this.image.setBounds(5, 35, 535, 430);
        paneImage.add(this.image);
        this.setPicture();
    }

    public final void showImageDialog() {
        JFileChooser file = new JFileChooser();
        file.setAcceptAllFileFilterUsed(true);
        int ret = file.showOpenDialog(this);
        if (ret == 0) {
            String filePath = file.getSelectedFile().getAbsolutePath();
            this.setWineImage(filePath);
        }
    }

    private void saveImage() {
        if (this.m_imagePath != null) {
            Image im = (Image)this.m_wine.getCellar().createItem(ObjectType.Image);
            im.capture(this.m_imagePath);
            im.save();
            this.m_wine.setImage(im);
            this.imageLabel.setText(this.m_app.getRS("WIN_UPDATE_IMG"));
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void setWineImage(String path) {
        File file = new File(path);
        if (!file.exists()) {
            this.m_imagePath = null;
            return;
        }
        if (file.length() > 64512) {
            this.m_app.showMessage(null, this.m_app.getRS("WIN_IMAGE_TOO_BIG"), MessageType.Message, MessageIconType.Warning, MessageButtonType.Default);
            this.m_imagePath = null;
            return;
        }
        BufferedImage newImage = null;
        try {
            newImage = ImageIO.read(file);
        }
        catch (Exception ex) {
            this.m_app.showMessage(null, this.m_app.getRS("WIN_IMAGE_BAD_FORMAT"), MessageType.Message, MessageIconType.Warning, MessageButtonType.Default);
            this.m_imagePath = null;
        }
        finally {
            if (newImage != null) {
                this.image.setImage(newImage);
                this.m_imagePath = path;
            }
        }
    }

    private void setPicture() {
        if (this.m_wine.hasImage()) {
            try {
                java.awt.Image img = this.m_wine.getImage().getImage();
                this.image.setImage(img);
                this.imageLabel.setText(this.m_app.getRS("WIN_UPDATE_IMG"));
            }
            catch (Exception ex) {
                this.imageLabel.setText(this.m_app.getRS("WIN_CANNOT_LOAD_IMG"));
            }
        }
    }

    public void close() {
        this.info.close();
        this.textRackViewer.close();
        this.purchase.close();
        this.note.close();
        this.m_wine.restoreBackupPoint();
    }

    public void save() {
        this.saveImage();
        this.m_wine.save();
        this.m_wine.getRackItemQueue().apply();
        this.m_wine.getPurchaseSalesQueue().apply();
        this.m_wine.getNoteQueue().apply();
        this.m_imagePath = null;
        this.ensureWinePlaced();
        this.processVerification();
        this.m_wine.createBackupPoint();
    }

    private void processVerification() {
        int bottlesCount;
        if (this.m_wine.isManualManagment() && (bottlesCount = this.m_wine.getBottles()) < this.m_wine.getRackItems().size()) {
            this.m_app.showMessage(null, this.m_app.getRS("WIN_MSG_ALERT_BOTTLES"), MessageType.Message, MessageIconType.Warning, MessageButtonType.Default);
            this.m_wine.setBottles(this.m_wine.getRackItems().size());
            this.m_wine.save();
        }
        if (!this.m_app.getSettings().get("WineWindow").get("DontCheckBottles", "0").equals("1")) {
            boolean theFlag = this.checkBottles();
            while (theFlag) {
                theFlag = this.checkBottles();
            }
        }
    }

    private boolean checkBottles() {
        int bottles = this.getBottles();
        boolean result = false;
        int realPurchase = this.getDiff();
        int ensure = bottles - realPurchase;
        if (Math.abs(ensure) > 254) {
            this.m_app.showMessage(null, "Coh\u00e9rence des stocks.\nImpossible de continuer la diff\u00e9rence est trop importante.", MessageType.Message, MessageIconType.Warning, MessageButtonType.Default);
            return false;
        }
        if (bottles != realPurchase) {
            CheckBottlesLayer bottlesPage = new CheckBottlesLayer(this.m_app, bottles - realPurchase);
            bottlesPage.setButtons(bottles > realPurchase, bottles < realPurchase, true);
            bottlesPage.setMinimumSize(new Dimension(519, 240));
            bottlesPage.setSize(new Dimension(519, 240));
            bottlesPage.setPreferredSize(new Dimension(519, 240));
            JInternalFrame f = UIHelper.findInternalFrame(this);
            Point loc = f.getLocationOnScreen();
            bottlesPage.setLocation(loc.x + 50, loc.y + 40);
            bottlesPage.setModal(true);
            bottlesPage.setVisible(true);
            if (bottlesPage.getAction() == 0) {
                PurchaseSales purchase = (PurchaseSales)this.m_wine.getCellar().createItem(ObjectType.PurchaseSales);
                purchase.setComment("Ajustement automatique");
                if (bottles - realPurchase > 0) {
                    purchase.setPurchasedBottles(bottles - realPurchase);
                    purchase.setUnitPrice(this.m_wine.getBuyPrice());
                } else {
                    purchase.setConsumedBottles(realPurchase - bottles);
                }
                this.m_wine.getPurchaseSalesQueue().set(new PurchaseSalesWorkqueueItem(purchase, WorkqueueItemType.Add));
                this.m_wine.getPurchaseSalesQueue().apply();
                bottles = this.getBottles();
                realPurchase = this.getDiff();
                if (bottles != realPurchase) {
                    result = true;
                }
            } else if (bottlesPage.getAction() == 2) {
                this.showRackItemsWindow(null);
                this.m_wine.getRackItemQueue().apply();
                bottles = this.getBottles();
                realPurchase = this.getDiff();
                if (bottles != realPurchase) {
                    result = true;
                }
            } else if (bottlesPage.getAction() == 1) {
                this.showRackItemsWindow(null);
                this.m_wine.getRackItemQueue().apply();
                bottles = this.getBottles();
                realPurchase = this.getDiff();
                if (bottles != realPurchase) {
                    result = true;
                }
            } else if (bottlesPage.getAction() == -1 && bottlesPage.isDefaultOperation()) {
                this.m_app.getSettings().get("WineWindow").set("DontCheckBottles", "0");
            }
        }
        return result;
    }

    private int getDiff() {
        int bottlesBuy = 0;
        int bottlesConsume = 0;
        for (int i = 0; i < this.m_wine.getPurchasesSales().size(); ++i) {
            PurchaseSales sales = (PurchaseSales)this.m_wine.getPurchasesSales().get(i);
            bottlesBuy += sales.getPurchasedBottles();
            bottlesConsume += sales.getConsumedBottles();
        }
        if (this.m_app.getSettings().get("WineWindow").get("CountNotes", "0").equals("1")) {
            bottlesConsume += this.m_wine.getNotes().size();
        }
        return bottlesBuy - bottlesConsume;
    }

    private int getBottles() {
        return this.m_wine.isManualManagment() ? this.m_wine.getBottles() : this.m_wine.getRackItems().size();
    }

    private void ensureWinePlaced() {
        if (this.m_app.getSettings().get("WineWindow").get("CheckPurchaseSales", "F").equals("T") && this.m_wine.getPurchasesSales().size() > 0) {
            return;
        }
        if (this.m_wine.isManualManagment()) {
            return;
        }
        if (this.m_wine.getRackItems().size() == 0) {
            IConfirmDialogWindow dw = (IConfirmDialogWindow)((Object)this.m_app.createWindow(WindowType.Confirm, new Object[0]));
            dw.setOptionKey("PlaceRackItems");
            dw.setQuestion(this.m_app.getRS("MSG_CHOOSE_ITEMS"));
            if (((IDialogWindow)((Object)dw)).showDialog() == 0) {
                this.showRackItemsWindow(null);
                if (this.m_wine.getRackItemQueue().size() > 0) {
                    this.m_wine.getRackItemQueue().apply();
                }
            }
        }
    }

    public void cancel() {
        this.m_wine.restoreBackupPoint();
    }

    public boolean hasChange() {
        return this.m_imagePath != null;
    }

    public final void showRackItemsWindow(Rack rack) {
        RackSelector dialog = new RackSelector(((Application)this.m_app).getMain());
        WindowPositionSaver3 wps = new WindowPositionSaver3(this.m_app, dialog, "RackItemsSelector");
        dialog.setDataSource(this.m_app, rack, this.m_wine);
        dialog.setPreferredSize(new Dimension(500, 500));
        dialog.setSize(new Dimension(500, 500));
        wps.load();
        dialog.setModal(true);
        dialog.setVisible(true);
        wps.save();
        dialog.dispose();
    }

    public final void showPurchaseSalesWindow(PurchaseSales sales) {
        PurchaseSalesDialog dialog = new PurchaseSalesDialog(((Application)this.m_app).getMain());
        JInternalFrame f = UIHelper.findInternalFrame(this);
        Point loc = f.getLocationOnScreen();
        dialog.setLocation(loc.x + 100, loc.y + 10);
        dialog.setDataSource(this.m_app, sales, this.m_wine);
        dialog.setResizable(false);
        dialog.setPreferredSize(new Dimension(448, 294));
        dialog.setSize(new Dimension(448, 294));
        dialog.setMinimumSize(new Dimension(448, 294));
        dialog.setTitle(this.m_app.getRS("SALES_TITLE"));
        dialog.setModal(true);
        dialog.setVisible(true);
        if (dialog.getAction() == 0) {
            dialog.bindToData();
            this.m_wine.getPurchaseSalesQueue().set(new PurchaseSalesWorkqueueItem(sales, WorkqueueItemType.Add));
        }
        dialog.dispose();
    }

    public final void showNoteWindow(Note note) {
        NoteLayer dialog = new NoteLayer(((Application)this.m_app).getMain());
        JInternalFrame f = UIHelper.findInternalFrame(this);
        Point loc = f.getLocationOnScreen();
        dialog.setLocation(loc.x + 10, loc.y + 50);
        dialog.setDataSource(this.m_app, this.m_wine, note);
        dialog.setResizable(true);
        Dimension winSize = new Dimension(630, 570);
        dialog.setPreferredSize(winSize);
        dialog.setSize(winSize);
        dialog.setMinimumSize(winSize);
        dialog.setTitle(this.m_app.getRS("NOTE_TITLE"));
        dialog.pack();
        dialog.setModal(true);
        dialog.setVisible(true);
        if (dialog.getAction() == 0) {
            this.m_wine.getNoteQueue().set(new NoteWorkqueueItem(dialog.getNote(), WorkqueueItemType.Add));
        }
        dialog.dispose();
    }

    public final WineTabPageType getTabPage() {
        return WineTabPageType.parse(this.tabbedPane.getSelectedIndex());
    }

    public final void setTabPage(WineTabPageType tab) {
        if (tab == null) {
            return;
        }
        if (tab != WineTabPageType.Default) {
            this.tabbedPane.setSelectedIndex(tab.getValue());
        }
    }

    public void eventDispatched(CustomEvent evt) {
        Note note;
        if (evt.getSource() == null && evt.getEventId() == 0) {
            this.newPurchaseSales();
        } else if (evt.getSource() instanceof PurchaseSales && evt.getEventId() == 1) {
            PurchaseSales sales = (PurchaseSales)evt.getSource();
            if (sales != null) {
                this.showPurchaseSalesWindow(sales);
            }
        } else if (evt.getSource() == null && evt.getEventId() == 10) {
            this.newNote();
        } else if (evt.getSource() instanceof Note && evt.getEventId() == 11 && (note = (Note)evt.getSource()) != null) {
            this.showNoteWindow(note);
        }
    }

    private void newPurchaseSales() {
        PurchaseSales psales = (PurchaseSales)this.m_app.activeCellar().createItem(ObjectType.PurchaseSales);
        psales.setWine(this.m_wine);
        this.showPurchaseSalesWindow(psales);
    }

    private void newNote() {
        Note note = (Note)this.m_app.activeCellar().createItem(ObjectType.Note);
        note.setWine(this.m_wine);
        this.showNoteWindow(note);
    }

    private void initComponents() {
        this.tabbedPane = new JTabbedPane();
        this.commandBarPane = new JPanel();
        this.setLayout(null);
        this.tabbedPane.setTabLayoutPolicy(1);
        this.add(this.tabbedPane);
        this.tabbedPane.setBounds(10, 40, 550, 510);
        GroupLayout commandBarPaneLayout = new GroupLayout((Container)this.commandBarPane);
        this.commandBarPane.setLayout((LayoutManager)commandBarPaneLayout);
        commandBarPaneLayout.setHorizontalGroup((GroupLayout.Group)commandBarPaneLayout.createParallelGroup(1).add(0, 570, 32767));
        commandBarPaneLayout.setVerticalGroup((GroupLayout.Group)commandBarPaneLayout.createParallelGroup(1).add(0, 40, 32767));
        this.add(this.commandBarPane);
        this.commandBarPane.setBounds(0, 0, 570, 40);
    }

}

