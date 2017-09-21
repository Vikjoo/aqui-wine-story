/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Wine
 */
package opencellar.application;

import java.awt.Color;
import opencellar.application.OperatorType;
import opencellar.framework.Wine;

public abstract class Legend {
    private OperatorType m_ot = OperatorType.Equals;
    private String m_compareValue = "";
    private boolean m_toValidate = false;
    private Color m_color = Color.CYAN;
    private boolean m_isValidate = false;

    protected Legend() {
    }

    public final OperatorType getOperator() {
        return this.m_ot;
    }

    public final void setOperator(OperatorType ot) {
        if (this.m_ot != ot) {
            this.m_toValidate = true;
            this.m_ot = ot;
        }
    }

    public final String getCompareValue() {
        return this.m_compareValue;
    }

    public final void setCompareValue(String value) {
        if (value == null || value.equals("")) {
            throw new IllegalArgumentException("value == null");
        }
        this.m_toValidate = true;
        this.m_compareValue = value;
    }

    public final void setColor(Color newColor) {
        if (newColor != this.m_color) {
            this.m_color = newColor;
        }
    }

    public final Color getColor() {
        return this.m_color;
    }

    public final boolean validate() {
        if (this.m_toValidate) {
            this.m_isValidate = this.isValid();
        }
        return this.m_isValidate;
    }

    protected boolean isValid() {
        return false;
    }

    public boolean match(Wine w) {
        return false;
    }

    public String toString() {
        return "legend : ";
    }
}

