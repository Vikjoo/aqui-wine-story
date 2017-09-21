/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Cellar
 *  opencellar.framework.CellarObject
 *  opencellar.framework.CellarObjectCollection
 *  opencellar.framework.ColorType
 *  opencellar.framework.ObjectType
 *  opencellar.framework.RackNamingType
 *  opencellar.framework.RackType
 */
package opencellar.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.NumberFormat;
import java.util.Comparator;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.text.Document;
import opencellar.application.IApplication;
import opencellar.application.utils;
import opencellar.framework.Cellar;
import opencellar.framework.CellarObject;
import opencellar.framework.CellarObjectCollection;
import opencellar.framework.ColorType;
import opencellar.framework.ObjectType;
import opencellar.framework.RackNamingType;
import opencellar.framework.RackType;
import opencellar.ui.MaxLengthDocument;
import opencellar.ui.OCAutoCompleteComboBox;
import opencellar.ui.OCComboBox;

public class UIHelper {
    public static final NumberFormat numberFormat = NumberFormat.getInstance();

    public static String getCurrencySymbol() {
        return "\u20ac";
    }

    public static void attachMaxLengthDocument(JTextField target, int maxLength) {
        if (target == null) {
            throw new IllegalArgumentException("target");
        }
        if (maxLength < 1) {
            throw new IllegalArgumentException("maxLength");
        }
        target.setDocument(new MaxLengthDocument(maxLength));
    }

    public static Font getDefaultFont() {
        return (Font)UIManager.get("Label.font");
    }

    public static Font getBoldDefaultFont() {
        return (Font)UIManager.get("Label.font");
    }

    public static Font getStrikeOutFont() {
        Font font = UIHelper.getDefaultFont();
        font = font.deriveFont(3);
        return font;
    }

    public static AttributedString getAttributedString(Font font, Color c, String text) {
        AttributedString attributedString = new AttributedString(text);
        attributedString.addAttribute(TextAttribute.FONT, font);
        attributedString.addAttribute(TextAttribute.FOREGROUND, c);
        return attributedString;
    }

    public static void drawText(Graphics2D g, String text, Rectangle bounds, Font font, Color c) {
        if (text == null) {
            return;
        }
        if (text.trim().equals("")) {
            return;
        }
        int width = bounds.width;
        int x = bounds.x;
        int y = bounds.y;
        AttributedCharacterIterator characterIterator = UIHelper.getAttributedString(font, c, text).getIterator();
        FontRenderContext fontRenderContext = g.getFontRenderContext();
        LineBreakMeasurer measurer = new LineBreakMeasurer(characterIterator, fontRenderContext);
        int h = 0;
        while (measurer.getPosition() < characterIterator.getEndIndex()) {
            TextLayout textLayout = measurer.nextLayout(width);
            y = (int)((float)y + textLayout.getAscent());
            if ((h = (int)((double)h + textLayout.getBounds().getHeight())) >= bounds.height) break;
            textLayout.draw(g, x, y);
            y = (int)((float)y + (textLayout.getDescent() + textLayout.getLeading()));
        }
    }

    public static String buildExtendedToolTip(String caption, String text, String ressource) {
        StringBuilder builder = new StringBuilder();
        builder.append("<html>");
        if (caption != null) {
            builder.append("<b>");
            builder.append(caption);
            builder.append("</b><br>");
        }
        builder.append(utils.replace(text, "\n", "<br>"));
        builder.append("</html>");
        return builder.toString();
    }

    public static void drawTextMiddleLeft(Graphics g, String text, Rectangle bounds, Color foreColor) {
        int ascent = g.getFontMetrics().getMaxAscent();
        int descent = g.getFontMetrics().getMaxDescent();
        int y = bounds.y + (bounds.height / 2 - descent / 2 + ascent / 2);
        g.setColor(foreColor);
        g.drawString(text, bounds.x, y);
    }

    public static void drawTextMiddleRight(Graphics g, String text, Rectangle bounds, Color foreColor) {
        int ascent = g.getFontMetrics().getMaxAscent();
        int descent = g.getFontMetrics().getMaxDescent();
        int yy = bounds.y + (bounds.height / 2 - descent / 2 + ascent / 2);
        int ww = g.getFontMetrics().stringWidth(text);
        g.setColor(foreColor);
        g.drawString(text, bounds.x + bounds.width - ww, yy);
    }

    public static void drawCenterText(Graphics g, String text, Rectangle bounds, Color foreColor) {
        int ascent = g.getFontMetrics().getMaxAscent();
        int descent = g.getFontMetrics().getMaxDescent();
        int yy = bounds.y + (bounds.height / 2 - descent / 2 + ascent / 2);
        int ww = g.getFontMetrics().stringWidth(text);
        g.setColor(foreColor);
        g.drawString(text, bounds.x + bounds.width / 2 - ww / 2, yy);
    }

    public static Image getImage(String path) {
        return Toolkit.getDefaultToolkit().getImage(UIHelper.class.getResource(path));
    }

    public static void select(OCComboBox combo, CellarObject item) {
        if (combo == null) {
            throw new IllegalArgumentException("combo == null");
        }
        if (item == null) {
            return;
        }
        combo.setSelectedItem((Object)item);
    }

    public static void select(OCAutoCompleteComboBox combo, CellarObject item) {
        if (combo == null) {
            throw new IllegalArgumentException("combo == null");
        }
        if (item == null) {
            return;
        }
        combo.setSelectedItem((Object)item);
    }

    public static JInternalFrame findInternalFrame(Component c) {
        for (Component nc = c; nc != null; nc = nc.getParent()) {
            if (!(nc instanceof JInternalFrame)) continue;
            return (JInternalFrame)nc;
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void findAndCloseInternalFrame(Component c) {
        for (Component nc = c; nc != null; nc = nc.getParent()) {
            if (!(nc instanceof JInternalFrame)) continue;
            try {
                try {
                    ((JInternalFrame)nc).setClosed(true);
                }
                catch (Exception ex) {
                }
            }
            catch (Throwable throwable) {}
            break;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void findAndCloseDialog(Component c) {
        for (Component nc = c; nc != null; nc = nc.getParent()) {
            if (!(nc instanceof JDialog)) continue;
            try {
                try {
                    ((JDialog)nc).setVisible(false);
                }
                catch (Exception ex) {
                }
            }
            catch (Throwable throwable) {}
            break;
        }
    }

    public static void setCursor(Component c, boolean wait) {
        if (c == null) {
            throw new IllegalArgumentException("c == null");
        }
        if (wait) {
            c.setCursor(Cursor.getPredefinedCursor(3));
        } else {
            c.setCursor(Cursor.getDefaultCursor());
        }
    }

    public static void pushItems(OCComboBox combo, ObjectType ot, Cellar cellar, Object nullItem, CellarObject selected) {
        UIHelper.pushItems(combo, ot, cellar, nullItem, selected, null);
    }

    public static void pushItems(OCComboBox combo, ObjectType ot, Cellar cellar, Object nullItem, CellarObject selected, Comparator c) {
        if (cellar == null) {
            throw new IllegalArgumentException("cellar == null");
        }
        if (combo == null) {
            throw new IllegalArgumentException("combo == null");
        }
        combo.removeAllItems();
        if (nullItem != null) {
            combo.addItem(nullItem);
        }
        CellarObjectCollection coc = cellar.getCollection(ot);
        if (c == null) {
            coc.sort();
        } else {
            coc.sort(c);
        }
        int size = coc.size();
        for (int i = 0; i < size; ++i) {
            CellarObject co = coc.get(i);
            combo.addItem(co);
            if (co != selected) continue;
            combo.setSelectedIndex(i + (nullItem == null ? 0 : 1));
        }
        if (combo.getSelectedIndex() == -1 && nullItem != null) {
            combo.setSelectedIndex(0);
        }
    }

    public static void push(IApplication application, OCComboBox combo, RackType rt) {
        if (application == null) {
            throw new IllegalArgumentException("app == null");
        }
        if (combo == null) {
            throw new IllegalArgumentException("combo == null");
        }
        combo.removeAllItems();
        combo.addItem(application.getRS("ADM_RACK_NAME1"));
        combo.addItem(application.getRS("ADM_RACK_NAME2"));
        combo.addItem(application.getRS("ADM_RACK_NAME3"));
        combo.addItem(application.getRS("ADM_RACK_NAME4"));
        combo.addItem(application.getRS("ADM_RACK_NAME5"));
        UIHelper.select(combo, rt);
    }

    public static void select(OCComboBox combo, RackType rt) {
        if (combo == null) {
            throw new IllegalArgumentException("combo == null");
        }
        if (rt == RackType.Default) {
            combo.setSelectedIndex(0);
        } else if (rt == RackType.HorizontalShiftOnFirstLine) {
            combo.setSelectedIndex(1);
        } else if (rt == RackType.HorizontalShiftOnSecondLine) {
            combo.setSelectedIndex(2);
        } else if (rt == RackType.VerticalShiftOnFirstLine) {
            combo.setSelectedIndex(3);
        } else if (rt == RackType.VerticalShiftOnSecondLine) {
            combo.setSelectedIndex(4);
        }
    }

    public static RackType getSelectedRackType(OCComboBox combo) {
        if (combo == null) {
            throw new IllegalArgumentException("combo == null");
        }
        int index = combo.getSelectedIndex();
        RackType rt = RackType.Default;
        if (index == 1) {
            rt = RackType.HorizontalShiftOnFirstLine;
        } else if (index == 2) {
            rt = RackType.HorizontalShiftOnSecondLine;
        } else if (index == 3) {
            rt = RackType.VerticalShiftOnFirstLine;
        } else if (index == 4) {
            rt = RackType.VerticalShiftOnSecondLine;
        }
        return rt;
    }

    public static void push(IApplication application, OCComboBox combo, RackNamingType rt) {
        if (application == null) {
            throw new IllegalArgumentException("app == null");
        }
        if (combo == null) {
            throw new IllegalArgumentException("combo == null");
        }
        combo.removeAllItems();
        combo.addItem(application.getRS("ADM_RACK_LEGEND1"));
        combo.addItem(application.getRS("ADM_RACK_LEGEND2"));
        combo.addItem(application.getRS("ADM_RACK_LEGEND3"));
        combo.addItem(application.getRS("ADM_RACK_LEGEND4"));
        combo.addItem(application.getRS("ADM_RACK_LEGEND5"));
        UIHelper.select(combo, rt);
    }

    public static void select(OCComboBox combo, RackNamingType rt) {
        if (combo == null) {
            throw new IllegalArgumentException("combo == null");
        }
        if (rt == RackNamingType.None) {
            combo.setSelectedIndex(0);
        } else if (rt == RackNamingType.BothLetter) {
            combo.setSelectedIndex(1);
        } else if (rt == RackNamingType.BothNumeric) {
            combo.setSelectedIndex(2);
        } else if (rt == RackNamingType.NumericOnXLetterOnY) {
            combo.setSelectedIndex(3);
        } else if (rt == RackNamingType.LetterOnXNumericOnY) {
            combo.setSelectedIndex(4);
        }
    }

    public static RackNamingType getSelectedRackNamingType(OCComboBox combo) {
        if (combo == null) {
            throw new IllegalArgumentException("combo == null");
        }
        int index = combo.getSelectedIndex();
        RackNamingType rnt = RackNamingType.None;
        if (index == 1) {
            rnt = RackNamingType.BothLetter;
        } else if (index == 2) {
            rnt = RackNamingType.BothNumeric;
        } else if (index == 3) {
            rnt = RackNamingType.NumericOnXLetterOnY;
        } else if (index == 4) {
            rnt = RackNamingType.LetterOnXNumericOnY;
        }
        return rnt;
    }

    public static void pushItems(OCAutoCompleteComboBox combo, ObjectType ot, Cellar cellar, Object nullItem, CellarObject selected) {
        UIHelper.pushItems(combo, ot, cellar, nullItem, selected, null);
    }

    public static void pushItems(OCAutoCompleteComboBox combo, ObjectType ot, Cellar cellar, Object nullItem, CellarObject selected, Comparator c) {
        if (cellar == null) {
            throw new IllegalArgumentException("cellar == null");
        }
        if (combo == null) {
            throw new IllegalArgumentException("combo == null");
        }
        combo.removeAllItems();
        if (nullItem != null) {
            combo.addItem(nullItem);
        }
        CellarObjectCollection coc = cellar.getCollection(ot);
        if (c == null) {
            coc.sort();
        } else {
            coc.sort(c);
        }
        int size = coc.size();
        for (int i = 0; i < size; ++i) {
            CellarObject co = coc.get(i);
            combo.addItem(co);
            if (co != selected) continue;
            combo.setSelectedIndex(i + (nullItem == null ? 0 : 1));
        }
        if (combo.getSelectedIndex() == -1 && nullItem != null) {
            combo.setSelectedIndex(0);
        }
    }

    public static void push(IApplication application, OCComboBox combo, ColorType rt) {
        if (application == null) {
            throw new IllegalArgumentException("app == null");
        }
        if (combo == null) {
            throw new IllegalArgumentException("combo == null");
        }
        combo.removeAllItems();
        combo.addItem(application.getRS("WINE_COLOR_RED"));
        combo.addItem(application.getRS("WINE_COLOR_WHITE"));
        combo.addItem(application.getRS("WINE_COLOR_ROSY"));
        combo.addItem(application.getRS("WINE_COLOR_CHAMP"));
        combo.addItem(application.getRS("WINE_COLOR_LIQUEUR"));
        combo.addItem(application.getRS("WINE_COLOR_MISC"));
        UIHelper.select(combo, rt);
    }

    public static void select(OCComboBox combo, ColorType rt) {
        if (combo == null) {
            throw new IllegalArgumentException("combo == null");
        }
        if (rt == ColorType.Red) {
            combo.setSelectedIndex(0);
        } else if (rt == ColorType.White) {
            combo.setSelectedIndex(1);
        } else if (rt == ColorType.Rosy) {
            combo.setSelectedIndex(2);
        } else if (rt == ColorType.Yellow) {
            combo.setSelectedIndex(3);
        } else if (rt == ColorType.LiqueurLike) {
            combo.setSelectedIndex(4);
        } else if (rt == ColorType.Misc) {
            combo.setSelectedIndex(5);
        }
    }

    public static ColorType getSelectedColor(OCComboBox combo) {
        if (combo == null) {
            throw new IllegalArgumentException("combo == null");
        }
        int index = combo.getSelectedIndex();
        ColorType rt = ColorType.Red;
        if (index == 1) {
            rt = ColorType.White;
        } else if (index == 2) {
            rt = ColorType.Rosy;
        } else if (index == 3) {
            rt = ColorType.Yellow;
        } else if (index == 4) {
            rt = ColorType.LiqueurLike;
        } else if (index == 5) {
            rt = ColorType.Misc;
        }
        return rt;
    }
}

