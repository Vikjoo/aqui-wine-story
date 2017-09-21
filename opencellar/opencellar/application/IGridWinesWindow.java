/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import java.io.File;
import opencellar.application.WineCollection;

public interface IGridWinesWindow {
    public void setDatasource(WineCollection var1);

    public WineCollection getWines();

    public String getSearchText();

    public void search(String var1, boolean var2);

    public void export(File var1);
}

