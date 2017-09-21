/*
 * Decompiled with CFR 0_122.
 */
package opencellar.ui;

public class NewCellarPaneEvent {
    private boolean m_useTemplate;
    private String m_path;

    public NewCellarPaneEvent(String path, boolean useTemplate) {
        this.m_path = path;
        this.m_useTemplate = useTemplate;
    }

    public final String getPath() {
        return this.m_path;
    }

    public final boolean isTemplateUsed() {
        return this.m_useTemplate;
    }
}

