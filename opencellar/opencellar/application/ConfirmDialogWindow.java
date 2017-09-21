/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import java.awt.Container;
import javax.swing.JDialog;
import opencellar.application.AppSettings;
import opencellar.application.DialogWindow;
import opencellar.application.IApplication;
import opencellar.application.IConfirmDialogWindow;
import opencellar.application.SettingCollection;
import opencellar.application.WindowType;
import opencellar.ui.ConfirmDialogPane;

public final class ConfirmDialogWindow
extends DialogWindow
implements IConfirmDialogWindow {
    private ConfirmDialogPane confirmDialog;
    private int m_settingOption = -1;
    private String m_key = null;

    protected ConfirmDialogWindow(IApplication application) {
        super(application);
    }

    public WindowType getType() {
        return WindowType.Confirm;
    }

    protected void onCreateFrame() {
        this.getFrame().setTitle("Open Cellar");
        this.confirmDialog = new ConfirmDialogPane();
        this.confirmDialog.setApplication(this.getApplication());
        this.getFrame().setContentPane(this.confirmDialog);
    }

    public final void setOptionKey(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key == null");
        }
        if (key.equals("")) {
            throw new IllegalArgumentException("key == String.empty");
        }
        this.m_key = key;
        this.m_settingOption = this.getApplication().getSettings().get("ConfirmWindow").get(key, -1);
    }

    public final void setQuestion(String question) {
        this.confirmDialog.setText(question);
    }

    public int showDialog() {
        if (this.m_settingOption != -1) {
            return this.m_settingOption;
        }
        this.confirmDialog.reset();
        this.centerFrame();
        int ret = 0;
        this.getFrame().setVisible(true);
        ret = this.confirmDialog.getSelectedOption();
        if (this.confirmDialog.isDefaultActionChecked()) {
            this.m_settingOption = ret;
            this.getApplication().getSettings().get("ConfirmWindow").set(this.m_key, String.valueOf(ret));
        }
        return ret;
    }
}

