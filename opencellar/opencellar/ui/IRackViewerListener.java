/*
 * Decompiled with CFR 0_122.
 */
package opencellar.ui;

import opencellar.ui.RackViewerEvent;

public interface IRackViewerListener {
    public void onPopup(RackViewerEvent var1);

    public void onDoubleClick(RackViewerEvent var1);

    public void onDrop(RackViewerEvent var1);

    public void onBeginDrop(RackViewerEvent var1);

    public void onItemEnter(RackViewerEvent var1);

    public void onItemLeave(RackViewerEvent var1);
}

