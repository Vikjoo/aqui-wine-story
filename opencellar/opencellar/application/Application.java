/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Cellar
 *  opencellar.framework.CellarApplication
 *  opencellar.framework.MisMatchEventArgs
 */
package opencellar.application;

import java.awt.Cursor;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import opencellar.application.AnimationLoader;
import opencellar.application.AnimationType;
import opencellar.application.AppSettings;
import opencellar.application.CommandBarManager;
import opencellar.application.CommandFactory;
import opencellar.application.CommandManager;
import opencellar.application.DefaultRenderer;
import opencellar.application.DialogResult;
import opencellar.application.ExplorerType;
import opencellar.application.FolderType;
import opencellar.application.IApplication;
import opencellar.application.IApplicationListener;
import opencellar.application.ICellarRenderer;
import opencellar.application.IInternalApplicationListener;
import opencellar.application.IWindow;
import opencellar.application.LanguageType;
import opencellar.application.LegendCollection;
import opencellar.application.LegendManager;
import opencellar.application.LockFile;
import opencellar.application.Log;
import opencellar.application.MainCommandBarManager;
import opencellar.application.MainMenuManager;
import opencellar.application.MessageBoxFactory;
import opencellar.application.MessageButtonType;
import opencellar.application.MessageIconType;
import opencellar.application.MessageType;
import opencellar.application.PreviewManager;
import opencellar.application.ResourceManager;
import opencellar.application.RootKitManager;
import opencellar.application.SettingCollection;
import opencellar.application.WindowCollection;
import opencellar.application.WindowManager;
import opencellar.application.WindowPositionSaver;
import opencellar.application.WindowType;
import opencellar.framework.Cellar;
import opencellar.framework.CellarApplication;
import opencellar.framework.MisMatchEventArgs;
import opencellar.ui.MainLayer;
import opencellar.ui.MdiDesktopPane;
import opencellar.ui.SplashLayer;

public final class Application
implements IApplication {
    private LegendManager m_legends;
    private CellarApplication m_cellarApp;
    private MainLayer m_main = null;
    private boolean m_appStarted = false;
    private SplashLayer m_splash;
    private WindowManager m_windows;
    private MainCommandBarManager m_barManager;
    private MainMenuManager m_menuManager;
    private RootKitManager m_rootKits;
    private WindowPositionSaver m_saver;
    private LockFile m_lock;
    private AppSettings m_appSettings = null;
    private String m_path = null;
    private LanguageType m_language = LanguageType.French;
    private ResourceManager m_rc;
    private ArrayList m_listeners = new ArrayList();
    private ArrayList m_internallisteners = new ArrayList();
    private AnimationLoader m_animLoader = new AnimationLoader();
    private CommandManager m_commands;
    private PreviewManager m_previews;

    public Application() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
    }

    public final ICellarRenderer createRenderer() {
        return new DefaultRenderer("Arrondi");
    }

    public LegendCollection getLegends() {
        this.checkAppStarted();
        return this.m_legends;
    }

    public final Cellar activeCellar() {
        this.checkAppStarted();
        return this.m_cellarApp.activeCellar();
    }

    protected CellarApplication getCellarApplication() {
        return this.m_cellarApp;
    }

    public final IWindow activeWindow() {
        return this.m_windows.getActiveWindow();
    }

    public final ExplorerType activeExplorer() {
        return ExplorerType.Home;
    }

    public final void setActiveExplorer(ExplorerType et) {
    }

    public final /* varargs */ IWindow createWindow(WindowType wt, Object ... args) {
        this.checkAppStarted();
        return this.m_windows.create(wt, args);
    }

    public final WindowCollection getWindows() {
        return this.m_windows;
    }

    private boolean isAppStarted() {
        return this.m_appStarted;
    }

    protected final MdiDesktopPane getDesktop() {
        this.checkAppStarted();
        return this.m_main.desktop;
    }

    private final void checkAppStarted() {
        if (!this.m_appStarted) {
            throw new IllegalArgumentException("App started == false");
        }
    }

    public void run() {
        if (!this.m_appStarted) {
            this.m_appStarted = true;
            this.notifyOnStarting();
            this.startSplash();
            this.internalRun();
            this.endSplash();
            this.notifyOnStart();
        }
    }

    private final void startSplash() {
        if (this.m_splash == null) {
            this.m_splash = new SplashLayer();
            this.m_splash.setVisible(true);
        }
    }

    private final void endSplash() {
        if (this.m_splash != null) {
            this.m_splash.dispose();
            this.m_splash = null;
            this.m_main.setVisible(true);
        }
    }

    public final MainLayer getMain() {
        this.checkAppStarted();
        return this.m_main;
    }

    private final void internalRun() {
        this.m_appSettings = new AppSettings(this);
        this.loadSettings();
        this.m_cellarApp = CellarApplication.create();
        this.m_rc = new ResourceManager(this);
        this.m_menuManager = new MainMenuManager(this);
        this.m_rootKits = new RootKitManager(this);
        this.m_windows = new WindowManager(this);
        this.m_barManager = new MainCommandBarManager(this);
        this.m_commands = new CommandManager(this);
        this.m_legends = new LegendManager(this);
        this.m_previews = new PreviewManager(this);
        this.m_rc.load(this.activeLanguage());
        this.m_main = new MainLayer(this);
        this.m_saver = new WindowPositionSaver(this, this.m_main, "MainWindow");
        this.m_saver.load();
        CommandFactory.build(this.m_commands);
        this.m_menuManager.build();
        this.m_barManager.build();
        this.m_previews.build();
        this.m_rootKits.build();
        this.m_rootKits.initialize();
        this.m_legends.load();
        this.internalnotifyOnStartUp();
        this.notifyOnStarting();
        this.m_main.setVisible(true);
        try {
            Thread.sleep(700);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.notifyOnStart();
    }

    public final CommandBarManager getMainCommandBars() {
        this.checkAppStarted();
        return this.m_barManager.getCommandManager();
    }

    protected final MainMenuManager getMenuManager() {
        return this.m_menuManager;
    }

    private final void loadSettings() {
        this.m_language = LanguageType.parse(this.getSettings().get("Env").get("Language"));
    }

    public void exit() {
        this.checkAppStarted();
        this.close();
        this.m_rootKits.uninitialize();
        this.m_saver.save();
        this.m_legends.save();
        this.m_appSettings.save();
        this.m_main.dispose();
        System.exit(0);
    }

    public void close() {
        if (this.m_lock != null) {
            this.m_lock.unlock();
        }
        this.getCellarApplication().close();
    }

    public void open(String path) {
        this.close();
        boolean success = CellarApplication.isValidOpenCellarFile((String)path);
        if (success) {
            this.m_lock = new LockFile(path);
            boolean ret = true;
            if (this.m_lock.isLocked()) {
                this.endSplash();
                this.m_main.setVisible(false);
                if (MessageBoxFactory.createEx(null, "Open Cellar Cross Platform", this.getRS("MSG_FILE_LOCK"), MessageType.Confirm, MessageIconType.Warning, MessageButtonType.YesNo) != DialogResult.Yes) {
                    ret = false;
                }
                this.m_main.setVisible(true);
            }
            if (ret) {
                this.m_lock.lock();
                this.getCellarApplication().open(path);
            }
        } else {
            this.showMessage(null, this.getRS("MSG_INVALID_FILE_FORMAT"), MessageType.Message, MessageIconType.Error, MessageButtonType.Default);
        }
    }

    public AppSettings getSettings() {
        this.checkAppStarted();
        return this.m_appSettings;
    }

    public String getPath(FolderType folder) {
        if (this.m_path == null) {
            this.m_path = System.getProperty("user.dir") + System.getProperty("file.separator");
        }
        if (folder != FolderType.Root && folder != FolderType.Language) {
            String directoryPath = this.m_path + folder.toString().toLowerCase() + System.getProperty("file.separator");
            return directoryPath;
        }
        return this.m_path;
    }

    public final void writeDebug(String value) {
        if (value != null) {
            Log.writeDebug(value);
        }
    }

    public void setLanguage(LanguageType lt) {
        if (this.m_language != lt) {
            this.m_language = lt;
            this.getSettings().get("Env").set("Language", this.m_language.getValue());
        }
    }

    public LanguageType activeLanguage() {
        return this.m_language;
    }

    public String getRS(String key) {
        this.checkAppStarted();
        return this.m_rc.getString(key);
    }

    public void addListener(IApplicationListener listener) {
        if (listener != null) {
            this.m_listeners.add(listener);
        }
    }

    public void removeListener(IApplicationListener listener) {
        if (listener != null) {
            this.m_listeners.remove(listener);
        }
    }

    protected final void notifyOnStarting() {
        int length = this.m_listeners.size();
        for (int i = 0; i < length; ++i) {
            IApplicationListener listener = (IApplicationListener)this.m_listeners.get(i);
            if (listener == null) continue;
            listener.onStarting(this);
        }
    }

    protected final void notifyOnStart() {
        int length = this.m_listeners.size();
        for (int i = 0; i < length; ++i) {
            IApplicationListener listener = (IApplicationListener)this.m_listeners.get(i);
            if (listener == null) continue;
            listener.onStart(this);
        }
    }

    protected final void notifyOnShutDown() {
        int length = this.m_listeners.size();
        for (int i = 0; i < length; ++i) {
            IApplicationListener listener = (IApplicationListener)this.m_listeners.get(i);
            if (listener == null) continue;
            listener.onShutDown(this);
        }
    }

    protected final void notifyOnOpening() {
        int length = this.m_listeners.size();
        for (int i = 0; i < length; ++i) {
            IApplicationListener listener = (IApplicationListener)this.m_listeners.get(i);
            if (listener == null) continue;
            listener.onOpening(this);
        }
    }

    protected final void notifyOnOpen() {
        int length = this.m_listeners.size();
        for (int i = 0; i < length; ++i) {
            IApplicationListener listener = (IApplicationListener)this.m_listeners.get(i);
            if (listener == null) continue;
            listener.onOpen(this);
        }
    }

    protected final void notifyOnClosing() {
        int length = this.m_listeners.size();
        for (int i = 0; i < length; ++i) {
            IApplicationListener listener = (IApplicationListener)this.m_listeners.get(i);
            if (listener == null) continue;
            listener.onClosing(this);
        }
    }

    protected final void notifyOnClose() {
        int length = this.m_listeners.size();
        for (int i = 0; i < length; ++i) {
            IApplicationListener listener = (IApplicationListener)this.m_listeners.get(i);
            if (listener == null) continue;
            listener.onClose(this);
        }
    }

    protected final void notifyOnCreateWindow(IWindow window) {
        int length = this.m_listeners.size();
        for (int i = 0; i < length; ++i) {
            IApplicationListener listener = (IApplicationListener)this.m_listeners.get(i);
            if (listener == null) continue;
            listener.onCreateWindow(this, window);
        }
    }

    protected final void notifyOnCloseWindow(IWindow window) {
        int length = this.m_listeners.size();
        for (int i = 0; i < length; ++i) {
            IApplicationListener listener = (IApplicationListener)this.m_listeners.get(i);
            if (listener == null) continue;
            listener.onCloseWindow(this, window);
        }
    }

    protected final void notifyOnActivateWindow(IWindow window) {
        int length = this.m_listeners.size();
        for (int i = 0; i < length; ++i) {
            IApplicationListener listener = (IApplicationListener)this.m_listeners.get(i);
            if (listener == null) continue;
            listener.onActivateWindow(this, window);
        }
    }

    protected final void notifyOnReadOnly() {
        int length = this.m_listeners.size();
        for (int i = 0; i < length; ++i) {
            IApplicationListener listener = (IApplicationListener)this.m_listeners.get(i);
            if (listener == null) continue;
            listener.onReadOnly(this);
        }
    }

    protected final void notifyOnMisMatchVersion(MisMatchEventArgs args) {
        int length = this.m_listeners.size();
        for (int i = 0; i < length; ++i) {
            IApplicationListener listener = (IApplicationListener)this.m_listeners.get(i);
            if (listener == null) continue;
            listener.onMisMatchVersion(this, args);
        }
    }

    protected void addInternalListener(IInternalApplicationListener listener) {
        if (listener != null) {
            this.m_internallisteners.add(listener);
        }
    }

    protected void removeInternalListener(IInternalApplicationListener listener) {
        if (listener != null) {
            this.m_internallisteners.remove(listener);
        }
    }

    protected final void internalnotifyOnStartUp() {
        int length = this.m_internallisteners.size();
        for (int i = 0; i < length; ++i) {
            IInternalApplicationListener listener = (IInternalApplicationListener)this.m_internallisteners.get(i);
            if (listener == null) continue;
            listener.onStartUp(this);
        }
    }

    protected final void internalnotifyOnShutDown() {
        int length = this.m_internallisteners.size();
        for (int i = 0; i < length; ++i) {
            IInternalApplicationListener listener = (IInternalApplicationListener)this.m_internallisteners.get(i);
            if (listener == null) continue;
            listener.onShutDown(this);
        }
    }

    protected final void internalnotifyOnCellarOpening() {
        int length = this.m_internallisteners.size();
        for (int i = 0; i < length; ++i) {
            IInternalApplicationListener listener = (IInternalApplicationListener)this.m_internallisteners.get(i);
            if (listener == null) continue;
            listener.onCellarOpening(this);
        }
    }

    protected final void internalnotifyOnCellarOpened() {
        int length = this.m_internallisteners.size();
        for (int i = 0; i < length; ++i) {
            IInternalApplicationListener listener = (IInternalApplicationListener)this.m_internallisteners.get(i);
            if (listener == null) continue;
            listener.onCellarOpened(this);
        }
    }

    protected final void internalnotifyOnCellarClosing() {
        int length = this.m_internallisteners.size();
        for (int i = 0; i < length; ++i) {
            IInternalApplicationListener listener = (IInternalApplicationListener)this.m_internallisteners.get(i);
            if (listener == null) continue;
            listener.onCellarClosing(this);
        }
    }

    protected final void internalnotifyOnCellarClosed() {
        int length = this.m_internallisteners.size();
        for (int i = 0; i < length; ++i) {
            IInternalApplicationListener listener = (IInternalApplicationListener)this.m_internallisteners.get(i);
            if (listener == null) continue;
            listener.onCellarClosed(this);
        }
    }

    public void animate(AnimationType at) {
        this.checkAppStarted();
        this.m_main.animate(this.m_animLoader.getImage(at));
    }

    public void stopAnimate() {
        this.checkAppStarted();
        this.m_main.stopAnimate();
    }

    public void setCursor(boolean wait) {
        if (wait) {
            this.m_main.setCursor(Cursor.getPredefinedCursor(3));
        } else {
            this.m_main.setCursor(Cursor.getPredefinedCursor(0));
        }
    }

    public CommandManager getCommands() {
        this.checkAppStarted();
        return this.m_commands;
    }

    public PreviewManager getPreviews() {
        this.checkAppStarted();
        return this.m_previews;
    }

    public final DialogResult showMessage(String title, String message, MessageType mt, MessageIconType mit, MessageButtonType mbt) {
        this.checkAppStarted();
        if (message == null) {
            throw new IllegalArgumentException("message");
        }
        String newTitle = title == null || title.equals("") ? this.getRS("DEFAULT_MSG_CAPTION") : title;
        return MessageBoxFactory.create(this.m_main.desktop, newTitle, message, mt, mit, mbt);
    }
}

