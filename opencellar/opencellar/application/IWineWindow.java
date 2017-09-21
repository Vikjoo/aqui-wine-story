/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Wine
 */
package opencellar.application;

import opencellar.application.WineDialogType;
import opencellar.application.WineTabPageType;
import opencellar.framework.Wine;

public interface IWineWindow {
    public Wine getWine();

    public void save();

    public void cancel();

    public /* varargs */ void showDialog(WineDialogType var1, Object ... var2);

    public void setTabPage(WineTabPageType var1);

    public WineTabPageType getTabPage();
}

