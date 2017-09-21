/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.RackItem
 */
package opencellar.application;

import javax.swing.JPopupMenu;
import opencellar.application.IRackWindow;
import opencellar.framework.RackItem;

public interface IRackWindowListener {
    public void onRackItemLeave(IRackWindow var1);

    public void onRackItemEnter(IRackWindow var1, RackItem var2);

    public void onPopup(IRackWindow var1, JPopupMenu var2);
}

