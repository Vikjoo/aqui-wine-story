/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import java.awt.Dimension;
import javax.swing.JDialog;
import opencellar.application.AppSettings;
import opencellar.application.IApplication;
import opencellar.application.SettingCollection;

public class WindowPositionSaver3 {
    private String m_section;
    private JDialog m_frame;
    private IApplication m_app;

    public WindowPositionSaver3(IApplication app, JDialog frame, String section) {
        if (app == null) {
            throw new IllegalArgumentException("app == null");
        }
        if (frame == null) {
            throw new IllegalArgumentException("frame == null");
        }
        if (section == null || section.equals("")) {
            throw new IllegalArgumentException("section == null");
        }
        this.m_section = section;
        this.m_frame = frame;
        this.m_app = app;
    }

    public final String getSection() {
        return this.m_section;
    }

    public IApplication getApp() {
        return this.m_app;
    }

    public final void load() {
        int width = this.getApp().getSettings().get(this.m_section).get("Width", -1);
        if (width > -1) {
            int x = this.getApp().getSettings().get(this.m_section).get("X", -1);
            int y = this.getApp().getSettings().get(this.m_section).get("Y", -1);
            int height = this.getApp().getSettings().get(this.m_section).get("Height", -1);
            this.m_frame.setSize(width, height);
            this.m_frame.setPreferredSize(new Dimension(width, height));
            this.m_frame.setLocation(x, y);
        } else {
            this.m_frame.setLocation(0, 0);
        }
    }

    public final void save() {
        this.getApp().getSettings().get(this.m_section).set("X", String.valueOf(this.m_frame.getX()));
        this.getApp().getSettings().get(this.m_section).set("Y", String.valueOf(this.m_frame.getY()));
        this.getApp().getSettings().get(this.m_section).set("Width", String.valueOf(this.m_frame.getWidth()));
        this.getApp().getSettings().get(this.m_section).set("Height", String.valueOf(this.m_frame.getHeight()));
    }
}

