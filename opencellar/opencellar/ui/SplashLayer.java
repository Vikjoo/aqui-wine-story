/*
 * Decompiled with CFR 0_122.
 */
package opencellar.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JWindow;

public class SplashLayer
extends JWindow {
    private BufferedImage m_back = null;

    public SplashLayer() {
        this.setSize(326, 206);
        this.setEnabled(false);
        this.setCursor(Cursor.getPredefinedCursor(3));
        this.setAlwaysOnTop(true);
        super.setBackground(Color.LIGHT_GRAY);
        try {
            this.m_back = ImageIO.read(SplashLayer.class.getResourceAsStream("/opencellar/rc/splash.jpg"));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        this.setLocation(screenSize.width / 2 - frameSize.width / 2, screenSize.height / 2 - frameSize.height / 2);
    }

    public void paint(Graphics g) {
        if (this.m_back != null) {
            g.drawImage(this.m_back, 0, 0, null);
        }
    }
}

