/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Cellar
 */
package opencellar.application;

import opencellar.application.AnimationType;
import opencellar.application.Application;
import opencellar.application.IApplication;
import opencellar.application.IInternalApplicationListener;
import opencellar.application.RootKit;
import opencellar.application.utils;
import opencellar.framework.Cellar;
import opencellar.ui.MainLayer;

public final class RKAnimate
extends RootKit
implements IInternalApplicationListener {
    public RKAnimate(IApplication application) {
        super(application);
    }

    public void initialize() {
        ((Application)this.getApp()).addInternalListener(this);
    }

    public void uninitialize() {
    }

    public void onStartUp(IApplication source) {
        ((Application)this.getApp()).getMain().setStatusText(this.getApp().getRS("APP_STATUS_NONE"));
    }

    public void onCellarOpening(IApplication source) {
        this.getApp().animate(AnimationType.Open);
        this.getApp().setCursor(true);
    }

    public void onCellarOpened(IApplication source) {
        this.getApp().stopAnimate();
        String status = utils.format(this.getApp().getRS("APP_STATUS_BAR"), this.getApp().activeCellar().getFilePath());
        ((Application)this.getApp()).getMain().setStatusText(status);
        this.getApp().setCursor(false);
    }

    public void onCellarClosing(IApplication source) {
    }

    public void onCellarClosed(IApplication source) {
        ((Application)this.getApp()).getMain().setStatusText(this.getApp().getRS("APP_STATUS_NONE"));
    }

    public void onShutDown(IApplication source) {
    }
}

