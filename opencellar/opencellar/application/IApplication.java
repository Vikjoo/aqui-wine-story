/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Cellar
 */
package opencellar.application;

import opencellar.application.AnimationType;
import opencellar.application.AppSettings;
import opencellar.application.CommandBarManager;
import opencellar.application.CommandManager;
import opencellar.application.DialogResult;
import opencellar.application.ExplorerType;
import opencellar.application.FolderType;
import opencellar.application.IApplicationListener;
import opencellar.application.ICellarRenderer;
import opencellar.application.IWindow;
import opencellar.application.LanguageType;
import opencellar.application.LegendCollection;
import opencellar.application.MessageButtonType;
import opencellar.application.MessageIconType;
import opencellar.application.MessageType;
import opencellar.application.PreviewManager;
import opencellar.application.WindowCollection;
import opencellar.application.WindowType;
import opencellar.framework.Cellar;

public interface IApplication {
    public static final int Version = 1;

    public ICellarRenderer createRenderer();

    public LegendCollection getLegends();

    public Cellar activeCellar();

    public IWindow activeWindow();

    public ExplorerType activeExplorer();

    public void setActiveExplorer(ExplorerType var1);

    public /* varargs */ IWindow createWindow(WindowType var1, Object ... var2);

    public WindowCollection getWindows();

    public void run();

    public void exit();

    public void close();

    public void open(String var1);

    public AppSettings getSettings();

    public String getPath(FolderType var1);

    public void writeDebug(String var1);

    public void setLanguage(LanguageType var1);

    public LanguageType activeLanguage();

    public String getRS(String var1);

    public void addListener(IApplicationListener var1);

    public void removeListener(IApplicationListener var1);

    public CommandBarManager getMainCommandBars();

    public CommandManager getCommands();

    public void animate(AnimationType var1);

    public void stopAnimate();

    public void setCursor(boolean var1);

    public DialogResult showMessage(String var1, String var2, MessageType var3, MessageIconType var4, MessageButtonType var5);

    public PreviewManager getPreviews();
}

