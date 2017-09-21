/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.jdesktop.layout.GroupLayout
 *  org.jdesktop.layout.GroupLayout$Group
 *  org.jdesktop.layout.GroupLayout$ParallelGroup
 */
package opencellar.ui;

import java.awt.Container;
import java.awt.LayoutManager;
import javax.swing.JInternalFrame;
import org.jdesktop.layout.GroupLayout;

public class NewJInternalFrame
extends JInternalFrame {
    public NewJInternalFrame() {
        this.initComponents();
    }

    private void initComponents() {
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout((LayoutManager)layout);
        layout.setHorizontalGroup((GroupLayout.Group)layout.createParallelGroup(1).add(0, 394, 32767));
        layout.setVerticalGroup((GroupLayout.Group)layout.createParallelGroup(1).add(0, 274, 32767));
        this.pack();
    }
}

