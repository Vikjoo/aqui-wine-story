/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.jdesktop.layout.GroupLayout
 *  org.jdesktop.layout.GroupLayout$Group
 *  org.jdesktop.layout.GroupLayout$ParallelGroup
 *  org.jdesktop.layout.GroupLayout$SequentialGroup
 */
package opencellar.ui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import opencellar.application.IApplication;
import opencellar.application.ScriptHost;
import opencellar.ui.UIHelper;
import org.jdesktop.layout.GroupLayout;

public final class ScriptLayer
extends JPanel
implements ActionListener {
    private IApplication m_app = null;
    protected ScriptHost host = null;
    private JPanel commandBarPane;
    private JButton execute;
    private JScrollPane jScrollPane1;
    private JTextPane scriptContent;
    private JLabel status;

    public ScriptLayer() {
        this.initComponents();
        this.setState("");
    }

    public final String getScript() {
        return this.scriptContent.getText();
    }

    public final void setString(String s) {
        if (s != null) {
            this.scriptContent.setText(s);
        }
    }

    public final void setApplication(IApplication app) {
        if (app == null) {
            throw new IllegalArgumentException("app = null");
        }
        this.m_app = app;
        this.execute.setText(app.getRS("SCRIPT_EXECUTE"));
        this.execute.addActionListener(this);
    }

    public final void execute() {
        UIHelper.setCursor(this, true);
        this.execute.setEnabled(false);
        this.setState("Execution...");
        String script = this.scriptContent.getText();
        this.host = new ScriptHost(this.m_app, script);
        int ret = this.host.run();
        if (ret == 0) {
            this.setState("Ex\u00e9cution termin\u00e9e.");
        } else {
            this.setState("Erreur dans le script.");
        }
        this.execute.setEnabled(true);
        UIHelper.setCursor(this, false);
    }

    private void setState(String state) {
        this.status.setText(state);
    }

    public JPanel getCommandBarContainer() {
        return this.commandBarPane;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.execute) {
            this.execute();
        }
    }

    private void initComponents() {
        this.commandBarPane = new JPanel();
        this.jScrollPane1 = new JScrollPane();
        this.scriptContent = new JTextPane();
        this.execute = new JButton();
        this.status = new JLabel();
        GroupLayout commandBarPaneLayout = new GroupLayout((Container)this.commandBarPane);
        this.commandBarPane.setLayout((LayoutManager)commandBarPaneLayout);
        commandBarPaneLayout.setHorizontalGroup((GroupLayout.Group)commandBarPaneLayout.createParallelGroup(1).add(0, 494, 32767));
        commandBarPaneLayout.setVerticalGroup((GroupLayout.Group)commandBarPaneLayout.createParallelGroup(1).add(0, 32, 32767));
        this.scriptContent.setMinimumSize(new Dimension(494, 343));
        this.scriptContent.setPreferredSize(new Dimension(494, 343));
        this.jScrollPane1.setViewportView(this.scriptContent);
        this.execute.setText("execute");
        this.status.setText("state");
        GroupLayout layout = new GroupLayout((Container)this);
        this.setLayout((LayoutManager)layout);
        layout.setHorizontalGroup((GroupLayout.Group)layout.createParallelGroup(1).add((Component)this.commandBarPane, -1, -1, 32767).add((GroupLayout.Group)layout.createSequentialGroup().add(10, 10, 10).add((Component)this.jScrollPane1, -1, 474, 32767).add(10, 10, 10)).add(2, (GroupLayout.Group)layout.createSequentialGroup().addContainerGap().add((Component)this.status, -2, 334, -2).addPreferredGap(0, 12, 32767).add((Component)this.execute, -2, 128, -2).addContainerGap()));
        layout.setVerticalGroup((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().add((Component)this.commandBarPane, -2, -1, -2).addPreferredGap(0).add((Component)this.jScrollPane1, -1, 265, 32767).addPreferredGap(0).add((GroupLayout.Group)layout.createParallelGroup(3).add((Component)this.execute).add((Component)this.status, -2, 19, -2)).addContainerGap()));
    }
}

