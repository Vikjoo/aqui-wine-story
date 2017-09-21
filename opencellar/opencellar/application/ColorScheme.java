/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.ColorType
 *  opencellar.framework.Wine
 */
package opencellar.application;

import java.awt.Color;
import opencellar.framework.ColorType;
import opencellar.framework.Wine;

public class ColorScheme {
    public static final Color RK_WINE_ADDED = new Color(107, 250, 106);
    public static final Color RED_WINE = new Color(182, 39, 39);
    public static final Color WHITE_WINE = new Color(233, 239, 121);
    public static final Color MISC_WINE = new Color(1, 223, 0);
    public static final Color ROSY_WINE = new Color(247, 151, 151);
    public static final Color YELLOW_WINE = Color.ORANGE;
    public static final Color LIKEUR_WINE = new Color(193, 128, 233);
    public static final Color RK_WINE = new Color(1, 223, 0);

    public static Color getWineColor(Wine wine) {
        if (wine == null) {
            throw new IllegalArgumentException("wine == null");
        }
        if (wine.getColor() == ColorType.Red) {
            return RED_WINE;
        }
        if (wine.getColor() == ColorType.White) {
            return WHITE_WINE;
        }
        if (wine.getColor() == ColorType.Rosy) {
            return ROSY_WINE;
        }
        if (wine.getColor() == ColorType.Misc) {
            return MISC_WINE;
        }
        if (wine.getColor() == ColorType.Yellow) {
            return YELLOW_WINE;
        }
        if (wine.getColor() == ColorType.LiqueurLike) {
            return LIKEUR_WINE;
        }
        return RED_WINE;
    }
}

