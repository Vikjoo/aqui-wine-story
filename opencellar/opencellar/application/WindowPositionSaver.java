/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import javax.swing.JFrame;
import opencellar.application.AppSettings;
import opencellar.application.IApplication;
import opencellar.application.SettingCollection;

public final class WindowPositionSaver {
    private String m_section;
    private JFrame m_frame;
    private IApplication m_app;

    public WindowPositionSaver(IApplication app, JFrame frame, String section) {
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
        if (this.getApp().getSettings().get(this.m_section).get("State", "F") == "T") {
            this.m_frame.setExtendedState(6);
        } else {
            int width = this.getApp().getSettings().get(this.m_section).get("Width", -1);
            if (width > -1) {
                int x = this.getApp().getSettings().get(this.m_section).get("X", -1);
                int y = this.getApp().getSettings().get(this.m_section).get("Y", -1);
                int height = this.getApp().getSettings().get(this.m_section).get("Height", -1);
                this.m_frame.setBounds(x, y, width, height);
                this.getApp().getSettings().get(this.m_section).get("Height", -1);
            }
        }
    }

    public final void save() {
        this.getApp().getSettings().get(this.m_section).set("X", String.valueOf(this.m_frame.getX()));
        this.getApp().getSettings().get(this.m_section).set("Y", String.valueOf(this.m_frame.getY()));
        this.getApp().getSettings().get(this.m_section).set("Width", String.valueOf(this.m_frame.getWidth()));
        this.getApp().getSettings().get(this.m_section).set("Height", String.valueOf(this.m_frame.getHeight()));
        if (this.m_frame.getExtendedState() == 6) {
            this.getApp().getSettings().get(this.m_section).set("State", "T");
        } else {
            this.getApp().getSettings().get(this.m_section).set("State", "F");
        }
    }
}

