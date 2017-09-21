/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class utils {
    private static boolean isMaxOSX = System.getProperty("os.name").toLowerCase().startsWith("mac os x");

    public static final boolean isMacOS() {
        return isMaxOSX;
    }

    public static int tryParse(String text, int defaultValue) {
        int val = -1;
        try {
            val = Integer.parseInt(text);
        }
        catch (Exception ex) {
            val = defaultValue;
        }
        return val;
    }

    public static float tryParse(String text, float defaultValue) {
        float val = -1.0f;
        try {
            text = utils.replace(text, ",", ".");
            val = Float.parseFloat(text);
        }
        catch (Exception ex) {
            val = defaultValue;
        }
        return val;
    }

    public static String replace(String input, String pattern, String newPattern) {
        StringBuffer result = new StringBuffer();
        int startIdx = 0;
        int idxOld = 0;
        while ((idxOld = input.indexOf(pattern, startIdx)) >= 0) {
            result.append(input.substring(startIdx, idxOld));
            result.append(newPattern);
            startIdx = idxOld + pattern.length();
        }
        result.append(input.substring(startIdx));
        return result.toString();
    }

    public static Icon getIcon(String rcName) {
        if (rcName == null) {
            throw new IllegalArgumentException("rcName == null");
        }
        String imgLocation = "/opencellar/rc/toolbars/" + rcName + ".gif";
        URL imageURL = utils.class.getResource(imgLocation);
        if (imageURL != null) {
            return new ImageIcon(imageURL);
        }
        return null;
    }

    public static Icon getFoodIcon(String rcName) {
        if (rcName == null) {
            throw new IllegalArgumentException("rcName == null");
        }
        String imgLocation = "/opencellar/rc/food/" + rcName + ".gif";
        URL imageURL = utils.class.getResource(imgLocation);
        if (imageURL != null) {
            return new ImageIcon(imageURL);
        }
        return null;
    }

    public static /* varargs */ String format(String s, String ... args) {
        if (s == null || s.equals("")) {
            return "";
        }
        StringBuilder builder = new StringBuilder(s);
        for (int i = 0; i < args.length; ++i) {
            String format = "{" + i + "}";
            int index = builder.indexOf(format);
            if (index <= -1) continue;
            builder.replace(index, index + format.length(), args[i]);
        }
        return builder.toString();
    }
}

