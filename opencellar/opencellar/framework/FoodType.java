/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import opencellar.framework.Wine;

public final class FoodType {
    private Wine m_wine;
    private int m_value = 1;
    protected static final int NONE = 1;
    protected static final int WHITE_MEAT = 2;
    protected static final int FISH = 4;
    protected static final int APERITIFS = 8;
    protected static final int CHEESE = 16;
    protected static final int SHEEL_FISH = 32;
    protected static final int RED_MEAT = 64;
    protected static final int DESSERT = 128;

    protected FoodType(Wine wine) {
        this.m_wine = wine;
    }

    private final void firePropertyChanged() {
        if (this.m_wine != null) {
            this.m_wine.setToUpdate();
            this.m_wine.notifyOnPropertyChanged("Food");
        }
    }

    public FoodType() {
    }

    protected final int getValue() {
        return this.m_value;
    }

    protected final void setValue(int val) {
        this.m_value = val;
    }

    public final boolean isDefined() {
        return this.m_value != 1;
    }

    public final boolean isWhiteMeat() {
        return (this.m_value & 2) == 2;
    }

    public final void setWhiteMeat(boolean v) {
        boolean isw = this.isWhiteMeat();
        if (!isw && v) {
            this.m_value |= 2;
            this.firePropertyChanged();
        } else if (isw && !v) {
            this.m_value -= 2;
            this.firePropertyChanged();
        }
    }

    public final boolean isFish() {
        return (this.m_value & 4) == 4;
    }

    public final void setFish(boolean v) {
        boolean isw = this.isFish();
        if (!isw && v) {
            this.m_value |= 4;
            this.firePropertyChanged();
        } else if (isw && !v) {
            this.m_value -= 4;
            this.firePropertyChanged();
        }
    }

    public final boolean isAperitifs() {
        return (this.m_value & 8) == 8;
    }

    public final void setAperitifs(boolean v) {
        boolean isw = this.isAperitifs();
        if (!isw && v) {
            this.m_value |= 8;
            this.firePropertyChanged();
        } else if (isw && !v) {
            this.m_value -= 8;
            this.firePropertyChanged();
        }
    }

    public final boolean isCheese() {
        return (this.m_value & 16) == 16;
    }

    public final void setCheese(boolean v) {
        boolean isw = this.isCheese();
        if (!isw && v) {
            this.m_value |= 16;
            this.firePropertyChanged();
        } else if (isw && !v) {
            this.m_value -= 16;
            this.firePropertyChanged();
        }
    }

    public final boolean isSheelFish() {
        return (this.m_value & 32) == 32;
    }

    public final void setSheelFish(boolean v) {
        boolean isw = this.isSheelFish();
        if (!isw && v) {
            this.m_value |= 32;
            this.firePropertyChanged();
        } else if (isw && !v) {
            this.m_value -= 32;
            this.firePropertyChanged();
        }
    }

    public final boolean isRedMeat() {
        return (this.m_value & 64) == 64;
    }

    public final void setRedMeat(boolean v) {
        boolean isw = this.isRedMeat();
        if (!isw && v) {
            this.m_value |= 64;
            this.firePropertyChanged();
        } else if (isw && !v) {
            this.m_value -= 64;
            this.firePropertyChanged();
        }
    }

    public final boolean isDessert() {
        return (this.m_value & 128) == 128;
    }

    public final void setDessert(boolean v) {
        boolean isw = this.isDessert();
        if (!isw && v) {
            this.m_value |= 128;
            this.firePropertyChanged();
        } else if (isw && !v) {
            this.m_value -= 128;
            this.firePropertyChanged();
        }
    }

    public void clear() {
        if (this.m_value != 1) {
            this.m_value = 1;
            this.firePropertyChanged();
        }
    }
}

