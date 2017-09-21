/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Rack
 *  opencellar.framework.RackItem
 *  opencellar.framework.Wine
 */
package opencellar.application;

import opencellar.application.IRackWindowListener;
import opencellar.framework.Rack;
import opencellar.framework.RackItem;
import opencellar.framework.Wine;

public interface IRackWindow {
    public Rack getRack();

    public void setRack(Rack var1);

    public Wine getActiveWine();

    public void setActiveWine(Wine var1);

    public RackItem getActiveItem();

    public void addRackWindowListener(IRackWindowListener var1);

    public void removeRackWindowListener(IRackWindowListener var1);
}

