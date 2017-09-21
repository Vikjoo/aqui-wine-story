/*
 * Decompiled with CFR 0_122.
 */
package opencellar.ui;

import java.util.EventListener;
import opencellar.ui.NewCellarPaneEvent;

public interface INewCellarPaneListener
extends EventListener {
    public void onCreate(NewCellarPaneEvent var1);

    public void onAbort();
}

