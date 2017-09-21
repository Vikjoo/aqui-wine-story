/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.AromaticType
 *  opencellar.framework.CarafageType
 *  opencellar.framework.ComplexityType
 *  opencellar.framework.DominantType
 *  opencellar.framework.HarmonizeType
 *  opencellar.framework.MatterType
 *  opencellar.framework.OlfDominantType
 *  opencellar.framework.OlfactiveIntensityType
 *  opencellar.framework.SkeletonType
 *  opencellar.framework.VisualColorType
 *  opencellar.framework.VisualIntensityType
 */
package opencellar.ui;

import opencellar.application.IApplication;
import opencellar.framework.AromaticType;
import opencellar.framework.CarafageType;
import opencellar.framework.ComplexityType;
import opencellar.framework.DominantType;
import opencellar.framework.HarmonizeType;
import opencellar.framework.MatterType;
import opencellar.framework.OlfDominantType;
import opencellar.framework.OlfactiveIntensityType;
import opencellar.framework.SkeletonType;
import opencellar.framework.VisualColorType;
import opencellar.framework.VisualIntensityType;
import opencellar.ui.OCComboBox;

public final class NoteEnumHelper {
    public static final void pushVisualIntensityType(OCComboBox cb, IApplication application) {
        cb.removeAllItems();
        for (int i = 1; i < 9; ++i) {
            cb.addItem(application.getRS("NOTE_CVII" + String.valueOf(i)));
        }
    }

    public static final void select(OCComboBox cb, VisualIntensityType select) {
        if (select == VisualIntensityType.None) {
            cb.setSelectedIndex(0);
        } else if (select == VisualIntensityType.Weak) {
            cb.setSelectedIndex(1);
        } else if (select == VisualIntensityType.Light) {
            cb.setSelectedIndex(2);
        } else if (select == VisualIntensityType.Clear) {
            cb.setSelectedIndex(3);
        } else if (select == VisualIntensityType.Supported) {
            cb.setSelectedIndex(4);
        } else if (select == VisualIntensityType.Sunk) {
            cb.setSelectedIndex(5);
        } else if (select == VisualIntensityType.Deep) {
            cb.setSelectedIndex(6);
        } else if (select == VisualIntensityType.Intense) {
            cb.setSelectedIndex(7);
        }
    }

    public static final VisualIntensityType getVisualIntensityType(OCComboBox cb) {
        VisualIntensityType ret = VisualIntensityType.None;
        if (cb.getSelectedIndex() == 1) {
            ret = VisualIntensityType.Weak;
        } else if (cb.getSelectedIndex() == 2) {
            ret = VisualIntensityType.Light;
        } else if (cb.getSelectedIndex() == 3) {
            ret = VisualIntensityType.Clear;
        } else if (cb.getSelectedIndex() == 4) {
            ret = VisualIntensityType.Supported;
        } else if (cb.getSelectedIndex() == 5) {
            ret = VisualIntensityType.Sunk;
        } else if (cb.getSelectedIndex() == 6) {
            ret = VisualIntensityType.Deep;
        } else if (cb.getSelectedIndex() == 7) {
            ret = VisualIntensityType.Intense;
        }
        return ret;
    }

    public static final void pushVisualColorType(OCComboBox cb, IApplication application) {
        cb.removeAllItems();
        for (int i = 1; i < 21; ++i) {
            cb.addItem(application.getRS("NOTE_CVIC" + String.valueOf(i)));
        }
    }

    public static final void select(OCComboBox cb, VisualColorType select) {
        if (select == VisualColorType.None) {
            cb.setSelectedIndex(0);
        } else if (select == VisualColorType.YellowGreen) {
            cb.setSelectedIndex(1);
        } else if (select == VisualColorType.YellowStraw) {
            cb.setSelectedIndex(2);
        } else if (select == VisualColorType.HoweverBlade) {
            cb.setSelectedIndex(3);
        } else if (select == VisualColorType.YellowGold) {
            cb.setSelectedIndex(4);
        } else if (select == VisualColorType.OldGold) {
            cb.setSelectedIndex(5);
        } else if (select == VisualColorType.Amber) {
            cb.setSelectedIndex(6);
        } else if (select == VisualColorType.DarkAmber) {
            cb.setSelectedIndex(7);
        } else if (select == VisualColorType.RussetRed) {
            cb.setSelectedIndex(8);
        } else if (select == VisualColorType.BrouOfNut) {
            cb.setSelectedIndex(9);
        } else if (select == VisualColorType.Purple) {
            cb.setSelectedIndex(10);
        } else if (select == VisualColorType.Crimson) {
            cb.setSelectedIndex(11);
        } else if (select == VisualColorType.Garnet) {
            cb.setSelectedIndex(12);
        } else if (select == VisualColorType.Ruby) {
            cb.setSelectedIndex(13);
        } else if (select == VisualColorType.Vermilion) {
            cb.setSelectedIndex(14);
        } else if (select == VisualColorType.Brick) {
            cb.setSelectedIndex(15);
        } else if (select == VisualColorType.Orange) {
            cb.setSelectedIndex(16);
        } else if (select == VisualColorType.Tuilee) {
            cb.setSelectedIndex(17);
        } else if (select == VisualColorType.MahoganyTree) {
            cb.setSelectedIndex(18);
        } else if (select == VisualColorType.Brown) {
            cb.setSelectedIndex(19);
        }
    }

    public static final VisualColorType getVisualColorType(OCComboBox cb) {
        VisualColorType ret = VisualColorType.None;
        if (cb.getSelectedIndex() == 1) {
            ret = VisualColorType.YellowGreen;
        } else if (cb.getSelectedIndex() == 2) {
            ret = VisualColorType.YellowStraw;
        } else if (cb.getSelectedIndex() == 3) {
            ret = VisualColorType.HoweverBlade;
        } else if (cb.getSelectedIndex() == 4) {
            ret = VisualColorType.YellowGold;
        } else if (cb.getSelectedIndex() == 5) {
            ret = VisualColorType.OldGold;
        } else if (cb.getSelectedIndex() == 6) {
            ret = VisualColorType.Amber;
        } else if (cb.getSelectedIndex() == 7) {
            ret = VisualColorType.DarkAmber;
        } else if (cb.getSelectedIndex() == 8) {
            ret = VisualColorType.RussetRed;
        } else if (cb.getSelectedIndex() == 9) {
            ret = VisualColorType.BrouOfNut;
        } else if (cb.getSelectedIndex() == 10) {
            ret = VisualColorType.Purple;
        } else if (cb.getSelectedIndex() == 11) {
            ret = VisualColorType.Crimson;
        } else if (cb.getSelectedIndex() == 12) {
            ret = VisualColorType.Garnet;
        } else if (cb.getSelectedIndex() == 13) {
            ret = VisualColorType.Ruby;
        } else if (cb.getSelectedIndex() == 14) {
            ret = VisualColorType.Vermilion;
        } else if (cb.getSelectedIndex() == 15) {
            ret = VisualColorType.Brick;
        } else if (cb.getSelectedIndex() == 16) {
            ret = VisualColorType.Orange;
        } else if (cb.getSelectedIndex() == 17) {
            ret = VisualColorType.Tuilee;
        } else if (cb.getSelectedIndex() == 18) {
            ret = VisualColorType.MahoganyTree;
        } else if (cb.getSelectedIndex() == 19) {
            ret = VisualColorType.Brown;
        }
        return ret;
    }

    public static final void pushOlfactiveIntensityType(OCComboBox cb, IApplication application) {
        cb.removeAllItems();
        for (int i = 1; i < 10; ++i) {
            cb.addItem(application.getRS("NOTE_COFI" + String.valueOf(i)));
        }
    }

    public static final void select(OCComboBox cb, OlfactiveIntensityType select) {
        if (select == OlfactiveIntensityType.None) {
            cb.setSelectedIndex(0);
        } else if (select == OlfactiveIntensityType.Weak) {
            cb.setSelectedIndex(1);
        } else if (select == OlfactiveIntensityType.Discrete) {
            cb.setSelectedIndex(2);
        } else if (select == OlfactiveIntensityType.Moderate) {
            cb.setSelectedIndex(3);
        } else if (select == OlfactiveIntensityType.Aromatic) {
            cb.setSelectedIndex(4);
        } else if (select == OlfactiveIntensityType.Expressive) {
            cb.setSelectedIndex(5);
        } else if (select == OlfactiveIntensityType.Bouquete) {
            cb.setSelectedIndex(6);
        } else if (select == OlfactiveIntensityType.Developed) {
            cb.setSelectedIndex(7);
        } else if (select == OlfactiveIntensityType.Intense) {
            cb.setSelectedIndex(8);
        }
    }

    public static final OlfactiveIntensityType getOlfactiveIntensityType(OCComboBox cb) {
        OlfactiveIntensityType ret = OlfactiveIntensityType.None;
        if (cb.getSelectedIndex() == 1) {
            ret = OlfactiveIntensityType.Weak;
        } else if (cb.getSelectedIndex() == 2) {
            ret = OlfactiveIntensityType.Discrete;
        } else if (cb.getSelectedIndex() == 3) {
            ret = OlfactiveIntensityType.Moderate;
        } else if (cb.getSelectedIndex() == 4) {
            ret = OlfactiveIntensityType.Aromatic;
        } else if (cb.getSelectedIndex() == 5) {
            ret = OlfactiveIntensityType.Expressive;
        } else if (cb.getSelectedIndex() == 6) {
            ret = OlfactiveIntensityType.Bouquete;
        } else if (cb.getSelectedIndex() == 7) {
            ret = OlfactiveIntensityType.Developed;
        } else if (cb.getSelectedIndex() == 8) {
            ret = OlfactiveIntensityType.Intense;
        }
        return ret;
    }

    public static final void pushComplexityType(OCComboBox cb, IApplication application) {
        cb.removeAllItems();
        for (int i = 1; i < 9; ++i) {
            cb.addItem(application.getRS("NOTE_CCON" + String.valueOf(i)));
        }
    }

    public static final void select(OCComboBox cb, ComplexityType select) {
        if (select == ComplexityType.None) {
            cb.setSelectedIndex(0);
        } else if (select == ComplexityType.Ordinary) {
            cb.setSelectedIndex(1);
        } else if (select == ComplexityType.Simple) {
            cb.setSelectedIndex(2);
        } else if (select == ComplexityType.Fin) {
            cb.setSelectedIndex(3);
        } else if (select == ComplexityType.Subtle) {
            cb.setSelectedIndex(4);
        } else if (select == ComplexityType.Elegant) {
            cb.setSelectedIndex(5);
        } else if (select == ComplexityType.Raced) {
            cb.setSelectedIndex(6);
        } else if (select == ComplexityType.Refined) {
            cb.setSelectedIndex(7);
        }
    }

    public static final ComplexityType getComplexityType(OCComboBox cb) {
        ComplexityType ret = ComplexityType.None;
        if (cb.getSelectedIndex() == 1) {
            ret = ComplexityType.Ordinary;
        } else if (cb.getSelectedIndex() == 2) {
            ret = ComplexityType.Simple;
        } else if (cb.getSelectedIndex() == 3) {
            ret = ComplexityType.Fin;
        } else if (cb.getSelectedIndex() == 4) {
            ret = ComplexityType.Subtle;
        } else if (cb.getSelectedIndex() == 5) {
            ret = ComplexityType.Elegant;
        } else if (cb.getSelectedIndex() == 6) {
            ret = ComplexityType.Raced;
        } else if (cb.getSelectedIndex() == 7) {
            ret = ComplexityType.Refined;
        }
        return ret;
    }

    public static final void pushOlfDominantType(OCComboBox cb, IApplication application) {
        cb.removeAllItems();
        for (int i = 1; i < 13; ++i) {
            cb.addItem(application.getRS("NOTE_COFD" + String.valueOf(i)));
        }
    }

    public static final void select(OCComboBox cb, OlfDominantType select) {
        if (select == OlfDominantType.None) {
            cb.setSelectedIndex(0);
        } else if (select == OlfDominantType.Vegetable) {
            cb.setSelectedIndex(1);
        } else if (select == OlfDominantType.Floral) {
            cb.setSelectedIndex(2);
        } else if (select == OlfDominantType.Fruity) {
            cb.setSelectedIndex(3);
        } else if (select == OlfDominantType.Spiced) {
            cb.setSelectedIndex(4);
        } else if (select == OlfDominantType.Timbered) {
            cb.setSelectedIndex(5);
        } else if (select == OlfDominantType.Empyreumatic) {
            cb.setSelectedIndex(6);
        } else if (select == OlfDominantType.Mineral) {
            cb.setSelectedIndex(7);
        } else if (select == OlfDominantType.Fermentaire) {
            cb.setSelectedIndex(8);
        } else if (select == OlfDominantType.Chemical) {
            cb.setSelectedIndex(9);
        } else if (select == OlfDominantType.Honey) {
            cb.setSelectedIndex(10);
        } else if (select == OlfDominantType.Animal) {
            cb.setSelectedIndex(11);
        }
    }

    public static final OlfDominantType getOlfDominantType(OCComboBox cb) {
        OlfDominantType ret = OlfDominantType.None;
        if (cb.getSelectedIndex() == 1) {
            ret = OlfDominantType.Vegetable;
        } else if (cb.getSelectedIndex() == 2) {
            ret = OlfDominantType.Floral;
        } else if (cb.getSelectedIndex() == 3) {
            ret = OlfDominantType.Fruity;
        } else if (cb.getSelectedIndex() == 4) {
            ret = OlfDominantType.Spiced;
        } else if (cb.getSelectedIndex() == 5) {
            ret = OlfDominantType.Timbered;
        } else if (cb.getSelectedIndex() == 6) {
            ret = OlfDominantType.Empyreumatic;
        } else if (cb.getSelectedIndex() == 7) {
            ret = OlfDominantType.Mineral;
        } else if (cb.getSelectedIndex() == 8) {
            ret = OlfDominantType.Fermentaire;
        } else if (cb.getSelectedIndex() == 9) {
            ret = OlfDominantType.Chemical;
        } else if (cb.getSelectedIndex() == 10) {
            ret = OlfDominantType.Honey;
        } else if (cb.getSelectedIndex() == 11) {
            ret = OlfDominantType.Animal;
        }
        return ret;
    }

    public static final void pushHarmonizeType(OCComboBox cb, IApplication application) {
        cb.removeAllItems();
        for (int i = 1; i < 7; ++i) {
            cb.addItem(application.getRS("NOTE_CHAR" + String.valueOf(i)));
        }
    }

    public static final void select(OCComboBox cb, HarmonizeType select) {
        if (select == HarmonizeType.None) {
            cb.setSelectedIndex(0);
        } else if (select == HarmonizeType.Unbalanced) {
            cb.setSelectedIndex(1);
        } else if (select == HarmonizeType.LittleBbalanced) {
            cb.setSelectedIndex(2);
        } else if (select == HarmonizeType.RatherBalancedWell) {
            cb.setSelectedIndex(3);
        } else if (select == HarmonizeType.Balanced) {
            cb.setSelectedIndex(4);
        } else if (select == HarmonizeType.Harmonious) {
            cb.setSelectedIndex(5);
        }
    }

    public static final HarmonizeType getHarmonizeType(OCComboBox cb) {
        HarmonizeType ret = HarmonizeType.None;
        if (cb.getSelectedIndex() == 1) {
            ret = HarmonizeType.Unbalanced;
        } else if (cb.getSelectedIndex() == 2) {
            ret = HarmonizeType.LittleBbalanced;
        } else if (cb.getSelectedIndex() == 3) {
            ret = HarmonizeType.RatherBalancedWell;
        } else if (cb.getSelectedIndex() == 4) {
            ret = HarmonizeType.Balanced;
        } else if (cb.getSelectedIndex() == 5) {
            ret = HarmonizeType.Harmonious;
        }
        return ret;
    }

    public static final void pushSkeletonType(OCComboBox cb, IApplication application) {
        cb.removeAllItems();
        for (int i = 1; i < 12; ++i) {
            cb.addItem(application.getRS("NOTE_CSKE" + String.valueOf(i)));
        }
    }

    public static final void select(OCComboBox cb, SkeletonType select) {
        if (select == SkeletonType.None) {
            cb.setSelectedIndex(0);
        } else if (select == SkeletonType.Fluet) {
            cb.setSelectedIndex(1);
        } else if (select == SkeletonType.Thin) {
            cb.setSelectedIndex(2);
        } else if (select == SkeletonType.Narrow) {
            cb.setSelectedIndex(3);
        } else if (select == SkeletonType.Slender) {
            cb.setSelectedIndex(4);
        } else if (select == SkeletonType.Light) {
            cb.setSelectedIndex(5);
        } else if (select == SkeletonType.Packed) {
            cb.setSelectedIndex(6);
        } else if (select == SkeletonType.Corpulent) {
            cb.setSelectedIndex(7);
        } else if (select == SkeletonType.Robust) {
            cb.setSelectedIndex(8);
        } else if (select == SkeletonType.Framed) {
            cb.setSelectedIndex(9);
        } else if (select == SkeletonType.Enormous) {
            cb.setSelectedIndex(10);
        }
    }

    public static final SkeletonType getSkeletonType(OCComboBox cb) {
        SkeletonType ret = SkeletonType.None;
        if (cb.getSelectedIndex() == 1) {
            ret = SkeletonType.Fluet;
        } else if (cb.getSelectedIndex() == 2) {
            ret = SkeletonType.Thin;
        } else if (cb.getSelectedIndex() == 3) {
            ret = SkeletonType.Narrow;
        } else if (cb.getSelectedIndex() == 4) {
            ret = SkeletonType.Slender;
        } else if (cb.getSelectedIndex() == 5) {
            ret = SkeletonType.Light;
        } else if (cb.getSelectedIndex() == 6) {
            ret = SkeletonType.Packed;
        } else if (cb.getSelectedIndex() == 7) {
            ret = SkeletonType.Corpulent;
        } else if (cb.getSelectedIndex() == 8) {
            ret = SkeletonType.Robust;
        } else if (cb.getSelectedIndex() == 9) {
            ret = SkeletonType.Framed;
        } else if (cb.getSelectedIndex() == 10) {
            ret = SkeletonType.Enormous;
        }
        return ret;
    }

    public static final void pushMatterType(OCComboBox cb, IApplication application) {
        cb.removeAllItems();
        for (int i = 1; i < 8; ++i) {
            cb.addItem(application.getRS("NOTE_CMAT" + String.valueOf(i)));
        }
    }

    public static final void select(OCComboBox cb, MatterType select) {
        if (select == MatterType.None) {
            cb.setSelectedIndex(0);
        } else if (select == MatterType.Miss) {
            cb.setSelectedIndex(1);
        } else if (select == MatterType.Weak) {
            cb.setSelectedIndex(2);
        } else if (select == MatterType.Average) {
            cb.setSelectedIndex(3);
        } else if (select == MatterType.Beautiful) {
            cb.setSelectedIndex(4);
        } else if (select == MatterType.Strong) {
            cb.setSelectedIndex(5);
        } else if (select == MatterType.TooStrong) {
            cb.setSelectedIndex(6);
        }
    }

    public static final MatterType getMatterType(OCComboBox cb) {
        MatterType ret = MatterType.None;
        if (cb.getSelectedIndex() == 1) {
            ret = MatterType.Miss;
        } else if (cb.getSelectedIndex() == 2) {
            ret = MatterType.Weak;
        } else if (cb.getSelectedIndex() == 3) {
            ret = MatterType.Average;
        } else if (cb.getSelectedIndex() == 4) {
            ret = MatterType.Beautiful;
        } else if (cb.getSelectedIndex() == 5) {
            ret = MatterType.Strong;
        } else if (cb.getSelectedIndex() == 6) {
            ret = MatterType.TooStrong;
        }
        return ret;
    }

    public static final void pushDominantType(OCComboBox cb, IApplication application) {
        cb.removeAllItems();
        for (int i = 1; i < 7; ++i) {
            cb.addItem(application.getRS("NOTE_CDOM" + String.valueOf(i)));
        }
    }

    public static final void select(OCComboBox cb, DominantType select) {
        if (select == DominantType.None) {
            cb.setSelectedIndex(0);
        } else if (select == DominantType.Balanced) {
            cb.setSelectedIndex(1);
        } else if (select == DominantType.VeryAcid) {
            cb.setSelectedIndex(2);
        } else if (select == DominantType.Acid) {
            cb.setSelectedIndex(3);
        } else if (select == DominantType.Marrowy) {
            cb.setSelectedIndex(4);
        } else if (select == DominantType.VeryMarrowy) {
            cb.setSelectedIndex(5);
        }
    }

    public static final DominantType getDominantType(OCComboBox cb) {
        DominantType ret = DominantType.None;
        if (cb.getSelectedIndex() == 1) {
            ret = DominantType.Balanced;
        } else if (cb.getSelectedIndex() == 2) {
            ret = DominantType.VeryAcid;
        } else if (cb.getSelectedIndex() == 3) {
            ret = DominantType.Acid;
        } else if (cb.getSelectedIndex() == 4) {
            ret = DominantType.Marrowy;
        } else if (cb.getSelectedIndex() == 5) {
            ret = DominantType.VeryMarrowy;
        }
        return ret;
    }

    public static final void pushAromaticType(OCComboBox cb, IApplication application) {
        cb.removeAllItems();
        for (int i = 1; i < 7; ++i) {
            cb.addItem(application.getRS("NOTE_CARO" + String.valueOf(i)));
        }
    }

    public static final void select(OCComboBox cb, AromaticType select) {
        if (select == AromaticType.None) {
            cb.setSelectedIndex(0);
        } else if (select == AromaticType.VeryWeak) {
            cb.setSelectedIndex(1);
        } else if (select == AromaticType.Weak) {
            cb.setSelectedIndex(2);
        } else if (select == AromaticType.Average) {
            cb.setSelectedIndex(3);
        } else if (select == AromaticType.Strong) {
            cb.setSelectedIndex(4);
        } else if (select == AromaticType.VeryStrong) {
            cb.setSelectedIndex(5);
        }
    }

    public static final AromaticType getAromaticType(OCComboBox cb) {
        AromaticType ret = AromaticType.None;
        if (cb.getSelectedIndex() == 1) {
            ret = AromaticType.VeryWeak;
        } else if (cb.getSelectedIndex() == 2) {
            ret = AromaticType.Weak;
        } else if (cb.getSelectedIndex() == 3) {
            ret = AromaticType.Average;
        } else if (cb.getSelectedIndex() == 4) {
            ret = AromaticType.Strong;
        } else if (cb.getSelectedIndex() == 5) {
            ret = AromaticType.VeryStrong;
        }
        return ret;
    }

    public static final void pushCarafageType(OCComboBox cb, IApplication application) {
        cb.removeAllItems();
        for (int i = 1; i < 10; ++i) {
            cb.addItem(application.getRS("NOTE_CCAR" + String.valueOf(i)));
        }
    }

    public static final void select(OCComboBox cb, CarafageType select) {
        if (select == CarafageType.Zero) {
            cb.setSelectedIndex(0);
        } else if (select == CarafageType.Half) {
            cb.setSelectedIndex(1);
        } else if (select == CarafageType.One) {
            cb.setSelectedIndex(2);
        } else if (select == CarafageType.Two) {
            cb.setSelectedIndex(3);
        } else if (select == CarafageType.Three) {
            cb.setSelectedIndex(4);
        } else if (select == CarafageType.Four) {
            cb.setSelectedIndex(5);
        } else if (select == CarafageType.Six) {
            cb.setSelectedIndex(6);
        } else if (select == CarafageType.Twelve) {
            cb.setSelectedIndex(7);
        } else if (select == CarafageType.TwentyFour) {
            cb.setSelectedIndex(8);
        }
    }

    public static final CarafageType getCarafageType(OCComboBox cb) {
        CarafageType ret = CarafageType.Zero;
        if (cb.getSelectedIndex() == 1) {
            ret = CarafageType.Half;
        } else if (cb.getSelectedIndex() == 2) {
            ret = CarafageType.One;
        } else if (cb.getSelectedIndex() == 3) {
            ret = CarafageType.Two;
        } else if (cb.getSelectedIndex() == 4) {
            ret = CarafageType.Three;
        } else if (cb.getSelectedIndex() == 5) {
            ret = CarafageType.Four;
        } else if (cb.getSelectedIndex() == 6) {
            ret = CarafageType.Six;
        } else if (cb.getSelectedIndex() == 7) {
            ret = CarafageType.Twelve;
        } else if (cb.getSelectedIndex() == 8) {
            ret = CarafageType.TwentyFour;
        }
        return ret;
    }
}

