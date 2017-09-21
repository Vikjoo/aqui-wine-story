/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.InputStream;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import opencellar.application.AnimationType;

public class AnimationLoader
implements ImageObserver {
    private Hashtable m_image = new Hashtable();

    public Image getImage(AnimationType at) {
        String path = String.valueOf(at.getValue()) + ".gif";
        if (this.m_image.containsKey(path)) {
            return (Image)this.m_image.get(path);
        }
        BufferedImage img = null;
        try {
            img = ImageIO.read(AnimationLoader.class.getResourceAsStream("/opencellar/rc/animate/" + path));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.m_image.put(path, img);
        return img;
    }

    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        if (infoflags != 32) {
            return true;
        }
        return false;
    }
}

