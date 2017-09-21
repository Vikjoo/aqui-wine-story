/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import java.util.ArrayList;
import opencellar.application.ApplicationEventDispatcher;
import opencellar.application.IApplication;
import opencellar.application.IRootKit;
import opencellar.application.Manager;
import opencellar.application.RKActiveWine;
import opencellar.application.RKAnimate;
import opencellar.application.RKDirectories;
import opencellar.application.RKError;
import opencellar.application.RKMacOSX;
import opencellar.application.RKWelcome;
import opencellar.application.RKWindowMenuItem;
import opencellar.application.RKWindowMessage;

public class RootKitManager
extends Manager
implements IRootKit {
    private ArrayList m_list = new ArrayList();

    public RootKitManager(IApplication application) {
        super(application);
    }

    public final void build() {
        this.m_list.add(new RKError(this.getApp()));
        this.m_list.add(new RKDirectories(this.getApp()));
        this.m_list.add(new RKWindowMenuItem(this.getApp()));
        this.m_list.add(new ApplicationEventDispatcher(this.getApp()));
        this.m_list.add(new RKAnimate(this.getApp()));
        this.m_list.add(new RKWindowMessage(this.getApp()));
        this.m_list.add(new RKActiveWine(this.getApp()));
        this.m_list.add(new RKMacOSX(this.getApp()));
        this.m_list.add(new RKWelcome(this.getApp()));
    }

    public final void initialize() {
        int length = this.m_list.size();
        for (int i = 0; i < length; ++i) {
            ((IRootKit)this.m_list.get(i)).initialize();
        }
    }

    public void uninitialize() {
        int length = this.m_list.size();
        for (int i = 0; i < length; ++i) {
            ((IRootKit)this.m_list.get(i)).uninitialize();
        }
    }
}

