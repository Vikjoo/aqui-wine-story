/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import opencellar.application.Command;
import opencellar.application.IApplication;

public final class AboutCommand
extends Command {
    public AboutCommand(IApplication app) {
        super(app);
    }

    public void execute() {
        final JWindow d = new JWindow();
        d.setAlwaysOnTop(true);
        d.setSize(326, 206);
        JLabel label = new JLabel();
        try {
            label.setIcon(new ImageIcon(ImageIO.read(AboutCommand.class.getResourceAsStream("/opencellar/rc/splash.jpg"))));
        }
        catch (Exception ex) {
            // empty catch block
        }
        label.addMouseListener(new MouseListener(){

            public void mouseClicked(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                d.dispose();
            }

            public void mouseReleased(MouseEvent e) {
            }
        });
        d.getContentPane().add((Component)label, "Center");
        d.setLocationRelativeTo(null);
        d.setVisible(true);
    }

}

