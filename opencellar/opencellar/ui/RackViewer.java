/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Cellar
 *  opencellar.framework.CellarObject
 *  opencellar.framework.ICellarListener
 *  opencellar.framework.IRackListener
 *  opencellar.framework.ObjectType
 *  opencellar.framework.PurchaseSales
 *  opencellar.framework.PurchaseSalesWorkqueue
 *  opencellar.framework.PurchaseSalesWorkqueueItem
 *  opencellar.framework.Rack
 *  opencellar.framework.RackComparer
 *  opencellar.framework.RackItem
 *  opencellar.framework.RackItemWorkqueue
 *  opencellar.framework.RackItemWorkqueueItem
 *  opencellar.framework.Wine
 *  opencellar.framework.WorkqueueItemType
 *  org.jdesktop.layout.GroupLayout
 *  org.jdesktop.layout.GroupLayout$Group
 *  org.jdesktop.layout.GroupLayout$ParallelGroup
 *  org.jdesktop.layout.GroupLayout$SequentialGroup
 */
package opencellar.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.InputStream;
import java.util.Comparator;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import opencellar.application.AppSettings;
import opencellar.application.CommandManager;
import opencellar.application.IApplication;
import opencellar.application.ICellarRenderer;
import opencellar.application.ICommand;
import opencellar.application.IConfirmDialogWindow;
import opencellar.application.IDialogWindow;
import opencellar.application.IPreviewRenderer;
import opencellar.application.IWindow;
import opencellar.application.IWineWindow;
import opencellar.application.LegendCollection;
import opencellar.application.PreviewManager;
import opencellar.application.RackWindowEventDispatcher;
import opencellar.application.SettingCollection;
import opencellar.application.WindowType;
import opencellar.application.WineDialogType;
import opencellar.application.WineTabPageType;
import opencellar.application.utils;
import opencellar.framework.Cellar;
import opencellar.framework.CellarObject;
import opencellar.framework.ICellarListener;
import opencellar.framework.IRackListener;
import opencellar.framework.ObjectType;
import opencellar.framework.PurchaseSales;
import opencellar.framework.PurchaseSalesWorkqueue;
import opencellar.framework.PurchaseSalesWorkqueueItem;
import opencellar.framework.Rack;
import opencellar.framework.RackComparer;
import opencellar.framework.RackItem;
import opencellar.framework.RackItemWorkqueue;
import opencellar.framework.RackItemWorkqueueItem;
import opencellar.framework.Wine;
import opencellar.framework.WorkqueueItemType;
import opencellar.ui.IRackViewerListener;
import opencellar.ui.OCComboBox;
import opencellar.ui.PreviewComponent;
import opencellar.ui.RackViewerComponent;
import opencellar.ui.RackViewerDragSource;
import opencellar.ui.RackViewerDropTarget;
import opencellar.ui.RackViewerEvent;
import opencellar.ui.UIHelper;
import org.jdesktop.layout.GroupLayout;

public final class RackViewer
extends JPanel
implements ItemListener,
ActionListener,
IRackViewerListener,
IRackListener,
ICellarListener {
    private CardLayout layout = new CardLayout();
    private JLabel infoLabel;
    private PreviewComponent m_window;
    private int popupDelay = 500;
    private RackViewerDragSource ds;
    private RackViewerDropTarget dt;
    private IApplication m_app;
    private RackViewerComponent viewer = null;
    JPopupMenu popupMenu;
    JMenuItem wineName;
    JMenu open;
    JMenuItem openInfo;
    JMenuItem openRack;
    JMenuItem openNote;
    JMenuItem openSales;
    JMenuItem openImage;
    JMenu options;
    JMenuItem optRacks;
    JMenuItem optSales;
    JMenuItem optNotes;
    JMenuItem optSnif;
    JMenuItem consume;
    static final int DEFAULT_ITEM_COUNT = 8;
    private Timer m_timer;
    private Point itemLocation;
    private Rack m_workingRack = null;
    private RackWindowEventDispatcher m_dispatcher = null;
    private JButton apply;
    private JPanel bottomPane;
    private JPanel centerPane;
    private JPanel commandBarPane1;
    private JPanel commandPane;
    private JLayeredPane contentPane;
    private JLabel legend1;
    private JLabel racksLabel;
    private OCComboBox racksList;
    private JButton showLegends;

    public RackViewer() {
        this.initComponents();
        this.racksList.addItemListener(this);
    }

    private void init() {
        this.viewer = new RackViewerComponent();
        this.viewer.setRenderer(this.m_app.createRenderer());
        this.viewer.setLegends(this.m_app.getLegends());
        this.viewer.addListener(this);
        this.contentPane.add((Component)this.viewer, new Integer(0));
        this.infoLabel = new JLabel(UIHelper.buildExtendedToolTip(null, this.m_app.getRS("MSG_ADD_RACK"), null));
        this.infoLabel.setForeground(Color.GRAY);
        this.infoLabel.setHorizontalTextPosition(0);
        this.infoLabel.setHorizontalAlignment(0);
        this.infoLabel.setCursor(Cursor.getPredefinedCursor(12));
        this.infoLabel.addMouseListener(new MouseListener(){

            public void mouseClicked(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    RackViewer.this.m_app.getCommands().get("RackAdmin").execute();
                }
            }

            public void mouseReleased(MouseEvent e) {
            }
        });
        this.contentPane.add((Component)this.infoLabel, new Integer(-1));
        this.apply.addActionListener(this);
        this.showLegends.addActionListener(this);
        this.ds = new RackViewerDragSource(this.viewer, 3);
        this.dt = new RackViewerDropTarget(this.viewer);
        this.createPopupMenu();
        this.popupDelay = this.m_app.getSettings().get("RackWindow").get("popupDelay", 500);
        if (this.popupDelay < 50) {
            this.popupDelay = 500;
        }
        this.m_timer = new Timer(this.popupDelay, this);
        this.m_timer.setInitialDelay(this.popupDelay);
        this.contentPane.addComponentListener(new ComponentListener(){

            public void componentHidden(ComponentEvent e) {
            }

            public void componentMoved(ComponentEvent e) {
            }

            public void componentResized(ComponentEvent e) {
                RackViewer.this.setInsets();
            }

            public void componentShown(ComponentEvent e) {
            }
        });
        try {
            this.legend1.setIcon(new ImageIcon(ImageIO.read(RackViewer.class.getResourceAsStream("/opencellar/rc/misc/legendhelp.gif"))));
        }
        catch (Exception ex) {
            // empty catch block
        }
        this.setInsets();
    }

    private void setInsets() {
        Insets insets = this.contentPane.getInsets();
        Rectangle bounds = new Rectangle();
        bounds.x = insets.left;
        bounds.y = insets.top;
        bounds.width = this.contentPane.getWidth() - (insets.left + insets.right);
        bounds.height = this.contentPane.getHeight() - (insets.top + insets.bottom);
        this.viewer.setBounds(bounds);
        this.infoLabel.setBounds(bounds);
    }

    private PreviewComponent getWindow() {
        if (this.m_window == null) {
            this.m_window = new PreviewComponent();
            this.m_window.setLocation(30, 30);
            this.m_window.setVisible(true);
            this.m_window.setVisible(false);
            this.contentPane.add((Component)this.m_window, 10);
            this.m_window.addMouseListener(new MouseListener(){

                public void mouseClicked(MouseEvent e) {
                }

                public void mouseEntered(MouseEvent e) {
                    RackViewer.this.hideInfoPane();
                }

                public void mouseExited(MouseEvent e) {
                }

                public void mousePressed(MouseEvent e) {
                }

                public void mouseReleased(MouseEvent e) {
                }
            });
        }
        return this.m_window;
    }

    private void ensureWindowParameters(IPreviewRenderer renderer, RackItem item) {
        Dimension d = renderer.getSize(item.getWine());
        this.getWindow().setMinimumSize(d);
        this.getWindow().setSize(d);
        this.getWindow().setPreferredSize(d);
        this.getWindow().setRenderer(renderer);
        this.getWindow().setItem(item);
    }

    public void setApp(IApplication app) {
        if (app == null) {
            throw new IllegalArgumentException("app == null");
        }
        if (this.m_app == null) {
            this.m_app = app;
            this.init();
            this.loadRacks(null);
            this.performTranslation();
            this.m_app.activeCellar().addlistener((ICellarListener)this);
        }
    }

    private void performTranslation() {
        this.apply.setText(this.m_app.getRS("BTN_CLOSE"));
        this.racksLabel.setText(this.m_app.getRS("RACK_TITLE"));
        this.showLegends.setText(this.m_app.getRS("RACK_LEGENDS"));
    }

    public final Rack getSelectedRack() {
        if (this.racksList.getSelectedIndex() > -1) {
            return (Rack)this.racksList.getSelectedItem();
        }
        return null;
    }

    public final void setSelectedRack(Rack rack) {
        Rack current;
        if (rack != null && (current = this.getSelectedRack()) != rack) {
            for (int i = 0; i < this.racksList.getItemCount(); ++i) {
                Rack rk = (Rack)this.racksList.getItemAt(i);
                if (rk != rack) continue;
                this.racksList.setSelectedIndex(i);
                break;
            }
        }
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == this.racksList) {
            this.viewer.setRack((Rack)e.getItem());
            this.setWorkingRack(this.viewer.getRack());
        }
    }

    private void loadRacks(Rack selected) {
        UIHelper.pushItems(this.racksList, ObjectType.Rack, this.m_app.activeCellar(), (Object)null, (CellarObject)selected, (Comparator)new RackComparer());
        if (this.racksList.getItemCount() > 0) {
            this.viewer.setVisible(true);
            this.infoLabel.setVisible(false);
            if (this.racksList.getSelectedIndex() == -1) {
                this.racksList.setSelectedIndex(0);
            }
        } else if (this.racksList.getItemCount() == 0) {
            this.infoLabel.setVisible(true);
            this.viewer.setVisible(false);
        }
    }

    public void close() {
        this.setWorkingRack(null);
        this.m_app.activeCellar().removeListener((ICellarListener)this);
        this.hideInfoPane();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.apply) {
            UIHelper.findAndCloseInternalFrame(this);
        } else if (e.getSource() == this.wineName) {
            this.showWineWindow();
        } else if (e.getSource() == this.openInfo) {
            this.showWineWindow(this.viewer.getWorkingItem().getWine(), WineTabPageType.General);
        } else if (e.getSource() == this.openImage) {
            this.showWineWindow(this.viewer.getWorkingItem().getWine(), WineTabPageType.Picture);
        } else if (e.getSource() == this.openNote) {
            this.showWineWindow(this.viewer.getWorkingItem().getWine(), WineTabPageType.Notes);
        } else if (e.getSource() == this.openRack) {
            this.showWineWindow(this.viewer.getWorkingItem().getWine(), WineTabPageType.RackItems);
        } else if (e.getSource() == this.openSales) {
            this.showWineWindow(this.viewer.getWorkingItem().getWine(), WineTabPageType.PurchaseSales);
        } else if (e.getSource() == this.m_timer) {
            this.showInfoPane();
        } else if (e.getSource() == this.consume) {
            this.showConfirmDialog(this.viewer.getWorkingItem(), true);
            this.viewer.getWorkingItem().consume();
        } else if (e.getSource() == this.optRacks) {
            IWineWindow wineWindow = this.showWineWindow(this.viewer.getWorkingItem().getWine(), WineTabPageType.RackItems);
            wineWindow.showDialog(WineDialogType.RackItems, new Object[]{this.getSelectedRack()});
        } else if (e.getSource() == this.optNotes) {
            IWineWindow wineWindow = this.showWineWindow(this.viewer.getWorkingItem().getWine(), WineTabPageType.Notes);
            wineWindow.showDialog(WineDialogType.Notes, new Object[0]);
        } else if (e.getSource() == this.optSales) {
            IWineWindow wineWindow = this.showWineWindow(this.viewer.getWorkingItem().getWine(), WineTabPageType.PurchaseSales);
            wineWindow.showDialog(WineDialogType.PurchaseSales, new Object[0]);
        }
    }

    public final JPanel getCommandBarContainer() {
        return this.commandBarPane1;
    }

    private void createPopupMenu() {
        this.wineName = new JMenuItem("");
        this.open = new JMenu(this.m_app.getRS("RACK_MENU_OPEN"));
        this.openInfo = new JMenuItem(this.m_app.getRS("RACK_MENU_OPEN1"));
        this.openRack = new JMenuItem(this.m_app.getRS("RACK_MENU_OPEN2"));
        this.openNote = new JMenuItem(this.m_app.getRS("RACK_MENU_OPEN3"));
        this.openSales = new JMenuItem(this.m_app.getRS("RACK_MENU_OPEN4"));
        this.openImage = new JMenuItem(this.m_app.getRS("RACK_MENU_OPEN5"));
        this.open.add(this.openInfo);
        this.open.add(this.openRack);
        this.open.add(this.openNote);
        this.open.add(this.openSales);
        this.open.add(this.openImage);
        this.options = new JMenu(this.m_app.getRS("RACK_MENU_OPTIONS"));
        this.optRacks = new JMenuItem(this.m_app.getRS("RACK_MENU_OPT1"));
        this.optSales = new JMenuItem(this.m_app.getRS("RACK_MENU_OPT3"));
        this.optNotes = new JMenuItem(this.m_app.getRS("RACK_MENU_OPT2"));
        this.optSnif = new JMenuItem(this.m_app.getRS("RACK_MENU_OPT4"));
        this.options.add(this.optRacks);
        this.options.add(this.optSales);
        this.options.add(this.optNotes);
        this.options.add(this.optSnif);
        this.optSnif.setVisible(false);
        this.consume = new JMenuItem(this.m_app.getRS("RACK_MENU_DRINK"));
        this.wineName.addActionListener(this);
        this.optRacks.addActionListener(this);
        this.optSales.addActionListener(this);
        this.optNotes.addActionListener(this);
        this.optSnif.addActionListener(this);
        this.openInfo.addActionListener(this);
        this.openRack.addActionListener(this);
        this.openNote.addActionListener(this);
        this.openSales.addActionListener(this);
        this.openImage.addActionListener(this);
        this.consume.addActionListener(this);
        this.popupMenu = new JPopupMenu();
        this.popupMenu.add(this.wineName);
        this.popupMenu.addSeparator();
        this.popupMenu.add(this.open);
        this.popupMenu.add(this.options);
        this.popupMenu.addSeparator();
        this.popupMenu.add(this.consume);
        this.popupMenu.addSeparator();
    }

    private void preparePopup(RackItem item) {
        while (this.popupMenu.getComponentCount() > 8) {
            this.popupMenu.remove(8);
        }
        if (item.isEmpty()) {
            this.open.setEnabled(false);
            this.options.setEnabled(false);
            this.consume.setEnabled(false);
            this.wineName.setText("Nouveau vin");
        } else {
            String name = item.getWine().getName().trim();
            if (name.equals("")) {
                name = "*";
            }
            this.wineName.setText(name);
            this.open.setEnabled(true);
            this.options.setEnabled(true);
            this.consume.setEnabled(true);
        }
    }

    public void onPopup(RackViewerEvent evt) {
        RackItem item = this.viewer.getWorkingItem();
        if (item == null) {
            return;
        }
        this.preparePopup(item);
        this.m_dispatcher.notifyOnPopup(this.popupMenu);
        this.hideInfoPane();
        this.popupMenu.show(this.viewer, evt.getLocation().x, evt.getLocation().y);
    }

    public void onDoubleClick(RackViewerEvent evt) {
        this.showWineWindow();
    }

    private void createConsumedElement(RackItem item) {
        PurchaseSales ps = (PurchaseSales)this.m_app.activeCellar().createItem(ObjectType.PurchaseSales);
        ps.setConsumedBottles(1);
        ps.setComment(utils.format(this.m_app.getRS("MSG_CREATE_CONSUME_ITEM"), item.getParent().getName(), item.getLegend()));
        item.getWine().getPurchaseSalesQueue().set(new PurchaseSalesWorkqueueItem(ps, WorkqueueItemType.Add));
        item.getWine().getPurchaseSalesQueue().apply();
    }

    private void createPurchasedElement(RackItem item) {
        PurchaseSales ps = (PurchaseSales)this.m_app.activeCellar().createItem(ObjectType.PurchaseSales);
        ps.setPurchasedBottles(1);
        ps.setUnitPrice(item.getWine().getBuyPrice());
        ps.setComment(utils.format(this.m_app.getRS("MSG_CREATE_SALES_ITEM"), item.getParent().getName(), item.getLegend()));
        item.getWine().getPurchaseSalesQueue().set(new PurchaseSalesWorkqueueItem(ps, WorkqueueItemType.Add));
        item.getWine().getPurchaseSalesQueue().apply();
    }

    private void showWineWindow() {
        RackItem item = this.viewer.getWorkingItem();
        if (item != null) {
            if (!item.isEmpty()) {
                this.showWineWindow(item.getWine(), WineTabPageType.Default);
            } else {
                Wine w = (Wine)this.m_app.activeCellar().createItem(ObjectType.Wine);
                w.setName("Nouveau vin");
                w.getRackItemQueue().set(new RackItemWorkqueueItem(item, WorkqueueItemType.Add));
                this.showWineWindow(w, WineTabPageType.Default);
            }
        }
    }

    public void onDrop(RackViewerEvent evt) {
        if (evt.getDragDropType() == 1) {
            evt.getRackItemSource().moveTo(evt.getRackItemTarget());
        } else {
            evt.getRackItemSource().copyTo(evt.getRackItemTarget());
            this.showConfirmDialog(evt.getRackItemTarget(), false);
        }
    }

    private void showConfirmDialog(RackItem target, boolean consume) {
        IConfirmDialogWindow dw = (IConfirmDialogWindow)((Object)this.m_app.createWindow(WindowType.Confirm, new Object[0]));
        if (dw != null) {
            dw.setOptionKey("Consume");
            dw.setQuestion(this.m_app.getRS("MSG_CREATE_CONSUME_ITEM_TITLE"));
            if (((IDialogWindow)((Object)dw)).showDialog() == 0) {
                if (consume) {
                    this.createConsumedElement(target);
                } else {
                    this.createPurchasedElement(target);
                }
            }
        }
    }

    public void onBeginDrop(RackViewerEvent evt) {
        this.hideInfoPane();
    }

    public void onItemEnter(RackViewerEvent evt) {
        this.itemLocation = evt.getLocation();
        this.m_timer.setInitialDelay(this.popupDelay);
        this.m_timer.start();
    }

    public void onItemLeave(RackViewerEvent evt) {
        this.hideInfoPane();
    }

    private void showInfoPane() {
        this.m_timer.stop();
        if (this.viewer.getHoverItem() != null) {
            boolean empty = this.viewer.getHoverItem().isEmpty();
            if (!empty && !this.popupMenu.isShowing() && this.itemLocation != null) {
                this.ensureWindowParameters(this.m_app.getPreviews().getActive(), this.viewer.getHoverItem());
                Point pt = new Point(this.itemLocation.x, this.itemLocation.y);
                pt.x += 8;
                pt.y += 8;
                if (pt.x + this.getWindow().getWidth() > this.contentPane.getWidth()) {
                    pt.x -= this.getWindow().getWidth() + 8;
                }
                if (pt.y + this.getWindow().getHeight() > this.contentPane.getHeight()) {
                    pt.y -= this.getWindow().getHeight() + 8;
                }
                if (pt.y < 0) {
                    pt.y = 3;
                }
                if (pt.x < 0) {
                    pt.x = 3;
                }
                this.getWindow().setLocation(pt);
                this.getWindow().setVisible(true);
                this.contentPane.moveToFront(this.getWindow());
            }
            this.m_dispatcher.notifyOnRackItemEnter(this.viewer.getHoverItem());
        }
    }

    private void hideInfoPane() {
        this.m_timer.stop();
        this.getWindow().setVisible(false);
        this.m_dispatcher.notifyOnRackItemLeave();
    }

    private final IWineWindow showWineWindow(Wine wine, WineTabPageType tabPage) {
        UIHelper.setCursor(this, true);
        this.hideInfoPane();
        IWindow window = this.m_app.createWindow(WindowType.Wine, new Object[]{wine});
        if (window != null) {
            window.show();
            ((IWineWindow)((Object)window)).setTabPage(tabPage);
        }
        UIHelper.setCursor(this, false);
        return (IWineWindow)((Object)window);
    }

    private void setWorkingRack(Rack rack) {
        if (this.m_workingRack != null) {
            this.m_workingRack.removeListener((IRackListener)this);
        }
        if (rack != null) {
            this.m_workingRack = rack;
            this.m_workingRack.addListener((IRackListener)this);
        }
    }

    public final Wine getActiveWine() {
        return this.viewer.getActiveWine();
    }

    public final void setActiveWine(Wine wine) {
        this.viewer.setActiveWine(wine);
    }

    public void onChange(Rack source) {
        this.viewer.setRack(source);
    }

    public void onNewItem(Cellar source, CellarObject co) {
        this.loadRacks(this.getSelectedRack());
    }

    public void onUpdateItem(Cellar source, CellarObject co) {
        this.loadRacks(this.getSelectedRack());
    }

    public void onDeleteItem(Cellar source, CellarObject co) {
        this.loadRacks(this.getSelectedRack());
    }

    public RackItem getActiveItem() {
        return this.viewer.getWorkingItem();
    }

    public void setDispatcher(RackWindowEventDispatcher dispatcher) {
        this.m_dispatcher = dispatcher;
    }

    private void initComponents() {
        this.commandPane = new JPanel();
        this.commandBarPane1 = new JPanel();
        this.racksLabel = new JLabel();
        this.racksList = new OCComboBox();
        this.bottomPane = new JPanel();
        this.apply = new JButton();
        this.legend1 = new JLabel();
        this.showLegends = new JButton();
        this.centerPane = new JPanel();
        this.contentPane = new JLayeredPane();
        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(441, 469));
        this.commandPane.setPreferredSize(new Dimension(100, 90));
        GroupLayout commandBarPane1Layout = new GroupLayout((Container)this.commandBarPane1);
        this.commandBarPane1.setLayout((LayoutManager)commandBarPane1Layout);
        commandBarPane1Layout.setHorizontalGroup((GroupLayout.Group)commandBarPane1Layout.createParallelGroup(1).add(0, 441, 32767));
        commandBarPane1Layout.setVerticalGroup((GroupLayout.Group)commandBarPane1Layout.createParallelGroup(1).add(0, 50, 32767));
        this.racksLabel.setText("racks");
        this.racksList.setMinimumSize(new Dimension(443, 486));
        GroupLayout commandPaneLayout = new GroupLayout((Container)this.commandPane);
        this.commandPane.setLayout((LayoutManager)commandPaneLayout);
        commandPaneLayout.setHorizontalGroup((GroupLayout.Group)commandPaneLayout.createParallelGroup(1).add((GroupLayout.Group)commandPaneLayout.createSequentialGroup().add(19, 19, 19).add((Component)this.racksLabel, -2, 99, -2).addPreferredGap(0).add((Component)this.racksList, -2, 283, -2).addContainerGap(36, 32767)).add((Component)this.commandBarPane1, -1, -1, 32767));
        commandPaneLayout.setVerticalGroup((GroupLayout.Group)commandPaneLayout.createParallelGroup(1).add(2, (GroupLayout.Group)commandPaneLayout.createSequentialGroup().add((Component)this.commandBarPane1, -2, -1, -2).addPreferredGap(0, 7, 32767).add((GroupLayout.Group)commandPaneLayout.createParallelGroup(3).add((Component)this.racksLabel).add((Component)this.racksList, -2, -1, -2)).addContainerGap()));
        this.add((Component)this.commandPane, "North");
        this.bottomPane.setPreferredSize(new Dimension(100, 80));
        this.apply.setText("apply");
        this.showLegends.setText("legend");
        this.showLegends.setEnabled(false);
        GroupLayout bottomPaneLayout = new GroupLayout((Container)this.bottomPane);
        this.bottomPane.setLayout((LayoutManager)bottomPaneLayout);
        bottomPaneLayout.setHorizontalGroup((GroupLayout.Group)bottomPaneLayout.createParallelGroup(1).add((GroupLayout.Group)bottomPaneLayout.createSequentialGroup().addContainerGap().add((Component)this.legend1, -2, 250, -2).addPreferredGap(0, 44, 32767).add((GroupLayout.Group)bottomPaneLayout.createParallelGroup(2, false).add((Component)this.apply, -1, -1, 32767).add((Component)this.showLegends, -2, 127, -2)).addContainerGap()));
        bottomPaneLayout.setVerticalGroup((GroupLayout.Group)bottomPaneLayout.createParallelGroup(1).add((GroupLayout.Group)bottomPaneLayout.createSequentialGroup().addContainerGap().add((GroupLayout.Group)bottomPaneLayout.createParallelGroup(1).add((GroupLayout.Group)bottomPaneLayout.createSequentialGroup().add((Component)this.showLegends).addPreferredGap(0, 12, 32767).add((Component)this.apply)).add((Component)this.legend1, -1, 58, 32767)).addContainerGap()));
        this.add((Component)this.bottomPane, "South");
        this.contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        GroupLayout centerPaneLayout = new GroupLayout((Container)this.centerPane);
        this.centerPane.setLayout((LayoutManager)centerPaneLayout);
        centerPaneLayout.setHorizontalGroup((GroupLayout.Group)centerPaneLayout.createParallelGroup(1).add(2, (GroupLayout.Group)centerPaneLayout.createSequentialGroup().addContainerGap().add((Component)this.contentPane, -1, 421, 32767).addContainerGap()));
        centerPaneLayout.setVerticalGroup((GroupLayout.Group)centerPaneLayout.createParallelGroup(1).add((GroupLayout.Group)centerPaneLayout.createSequentialGroup().addContainerGap().add((Component)this.contentPane, -1, 277, 32767).addContainerGap()));
        this.add((Component)this.centerPane, "Center");
    }

}

