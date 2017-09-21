/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import java.awt.Dimension;
import java.awt.Point;
import opencellar.application.CommandBarManager;
import opencellar.application.IApplication;
import opencellar.application.IWindowListener;
import opencellar.application.WindowType;

public interface IWindow {
    public void show();

    public void close();

    public WindowType getType();

    public String getCaption();

    public void setCaption(String var1);

    public Point getLocation();

    public void setLocation(Point var1);

    public void setSize(Dimension var1);

    public Dimension getSize();

    public boolean supportCommandBars();

    public boolean isModal();

    public CommandBarManager getCommandBars();

    public IApplication getApplication();

    public void addListener(IWindowListener var1);

    public void removeListener(IWindowListener var1);
}

