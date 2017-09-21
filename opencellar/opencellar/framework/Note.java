/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import java.util.Date;
import opencellar.framework.AromaticType;
import opencellar.framework.BytesManager;
import opencellar.framework.CarafageType;
import opencellar.framework.Cellar;
import opencellar.framework.CellarObject;
import opencellar.framework.ComplexityType;
import opencellar.framework.DateTimeUtility;
import opencellar.framework.DominantType;
import opencellar.framework.HarmonizeType;
import opencellar.framework.IIndexable;
import opencellar.framework.MatterType;
import opencellar.framework.ObjectState;
import opencellar.framework.ObjectType;
import opencellar.framework.OlfDominantType;
import opencellar.framework.OlfactiveIntensityType;
import opencellar.framework.SkeletonType;
import opencellar.framework.Utils;
import opencellar.framework.VisualColorType;
import opencellar.framework.VisualIntensityType;
import opencellar.framework.Wine;

public final class Note
extends CellarObject
implements IIndexable {
    static final int LENGTH = 1580;
    private String m_wineId = "0000";
    private int m_redX = 0;
    private int m_redY = 0;
    private int m_whiteX = 0;
    private int m_whiteY = 0;
    private String m_components = "";
    private String m_comment = "";
    private Date m_creationTime = new Date();
    private Date m_lastUpdated = new Date();
    private String m_followingDay = "";
    private int m_pai = 0;
    private String m_name = "";
    private String m_tastingWith = "";
    private String m_visualComment = "";
    private String m_olfactiveComment = "";
    private String m_gustatoryComment = "";
    private String m_gustatoryComment2 = "";
    private int m_gustatoryNote = 0;
    private int m_olfactiveNote = 0;
    private int m_visualNote = 0;
    private CarafageType m_carafage = CarafageType.Zero;
    private VisualIntensityType m_visualIntensity = VisualIntensityType.None;
    private VisualColorType m_visualColor = VisualColorType.None;
    private OlfactiveIntensityType m_olfactiveIntensity = OlfactiveIntensityType.None;
    private MatterType m_matter = MatterType.None;
    private HarmonizeType m_harmonize = HarmonizeType.None;
    private AromaticType m_aromatic = AromaticType.None;
    private AromaticType m_finalaromatic = AromaticType.None;
    private DominantType m_dominant = DominantType.None;
    private SkeletonType m_skeleton = SkeletonType.None;
    private ComplexityType m_complexity = ComplexityType.None;
    private OlfDominantType m_olfDominant = OlfDominantType.None;

    protected Note() {
    }

    public final int getAverage() {
        int ret = (this.getVisualNote() + this.getOlfactiveNote() + this.getGustatoryNote()) / 3;
        return ret;
    }

    public final ObjectType getType() {
        return ObjectType.Note;
    }

    public final int getLength() {
        return super.getLength() + 1580;
    }

    public final Wine getWine() {
        if (this.m_wineId.equals("0000")) {
            return null;
        }
        return (Wine)this.getCellar().get(ObjectType.Wine, this.m_wineId);
    }

    public final void setWine(Wine w) {
        if (w == null) {
            this.m_wineId = "0000";
            super.setToUpdate();
        } else if (!w.getSystemUid().equals(this.m_wineId)) {
            this.m_wineId = w.getSystemUid();
            super.setToUpdate();
        }
    }

    public final void markAsDeleted() {
        super.setState(ObjectState.Delete);
    }

    public final int getRedX() {
        return this.m_redX;
    }

    public final void setRedX(int value) {
        if (Utils.isValidByte(value) && value != this.m_redX) {
            this.m_redX = value;
            super.setToUpdate();
        }
    }

    public final int getRedY() {
        return this.m_redY;
    }

    public final void setRedY(int value) {
        if (Utils.isValidByte(value) && value != this.m_redY) {
            this.m_redY = value;
            super.setToUpdate();
        }
    }

    public final int getWhiteX() {
        return this.m_whiteX;
    }

    public final void setWhiteX(int value) {
        if (Utils.isValidByte(value) && value != this.m_whiteX) {
            this.m_whiteX = value;
            super.setToUpdate();
        }
    }

    public final int getWhiteY() {
        return this.m_whiteY;
    }

    public final void setWhiteY(int value) {
        if (Utils.isValidByte(value) && value != this.m_whiteY) {
            this.m_whiteY = value;
            super.setToUpdate();
        }
    }

    public final String getComponents() {
        return this.m_components;
    }

    public final void setComponents(String s) {
        this.m_components = Utils.ensureCapacity(s, 200);
        super.setToUpdate();
    }

    public final String getComment() {
        return this.m_comment;
    }

    public final void setComment(String s) {
        this.m_comment = Utils.ensureCapacity(s, 200);
        super.setToUpdate();
    }

    public final Date getCreationTime() {
        return this.m_creationTime;
    }

    public final void setCreationTime(Date d) {
        if (d != null) {
            this.m_creationTime = d;
            super.setToUpdate();
        }
    }

    public final Date getLastUpdated() {
        return this.m_lastUpdated;
    }

    public final void setLastUpdated(Date d) {
        if (d != null) {
            this.m_lastUpdated = d;
            super.setToUpdate();
        }
    }

    public final String getFollowingDay() {
        return this.m_followingDay;
    }

    public final void setFollowingDay(String s) {
        this.m_followingDay = Utils.ensureCapacity(s, 100);
        super.setToUpdate();
    }

    public final int getPai() {
        return this.m_pai;
    }

    public final void setPai(int value) {
        if (Utils.isValidByte(value) && value != this.m_pai) {
            this.m_pai = value;
            super.setToUpdate();
        }
    }

    public final String getName() {
        return this.m_name;
    }

    public final void setName(String s) {
        this.m_name = Utils.ensureCapacity(s, 30);
        super.setToUpdate();
    }

    public final String getTastingWith() {
        return this.m_tastingWith;
    }

    public final void setTastingWith(String s) {
        this.m_tastingWith = Utils.ensureCapacity(s, 200);
        super.setToUpdate();
    }

    public final String getVisualComment() {
        return this.m_visualComment;
    }

    public final void setVisualComment(String s) {
        this.m_visualComment = Utils.ensureCapacity(s, 200);
        super.setToUpdate();
    }

    public final String getOlfactiveComment() {
        return this.m_olfactiveComment;
    }

    public final void setOlfactiveComment(String s) {
        this.m_olfactiveComment = Utils.ensureCapacity(s, 200);
        super.setToUpdate();
    }

    public final String getGustatoryComment() {
        return this.m_gustatoryComment;
    }

    public final void setGustatoryComment(String s) {
        this.m_gustatoryComment = Utils.ensureCapacity(s, 200);
        super.setToUpdate();
    }

    public final String getGustatoryComment2() {
        return this.m_gustatoryComment2;
    }

    public final void setGustatoryComment2(String s) {
        this.m_gustatoryComment2 = Utils.ensureCapacity(s, 200);
        super.setToUpdate();
    }

    public final int getGustatoryNote() {
        return this.m_gustatoryNote;
    }

    public final void setGustatoryNote(int value) {
        if (Utils.isValidByte(value) && value != this.m_gustatoryNote) {
            this.m_gustatoryNote = value;
            super.setToUpdate();
        }
    }

    public final int getOlfactiveNote() {
        return this.m_olfactiveNote;
    }

    public final void setOlfactiveNote(int value) {
        if (Utils.isValidByte(value) && value != this.m_olfactiveNote) {
            this.m_olfactiveNote = value;
            super.setToUpdate();
        }
    }

    public final int getVisualNote() {
        return this.m_visualNote;
    }

    public final void setVisualNote(int value) {
        if (Utils.isValidByte(value) && value != this.m_visualNote) {
            this.m_visualNote = value;
            super.setToUpdate();
        }
    }

    public String getFk() {
        return this.m_wineId;
    }

    public final CarafageType getColor() {
        return this.m_carafage;
    }

    public final void setCarafage(CarafageType em) {
        if (this.m_carafage != em) {
            this.m_carafage = em;
            super.setToUpdate();
        }
    }

    public final VisualIntensityType getVisualIntensity() {
        return this.m_visualIntensity;
    }

    public final void setVisualIntensity(VisualIntensityType em) {
        if (this.m_visualIntensity != em) {
            this.m_visualIntensity = em;
            super.setToUpdate();
        }
    }

    public final VisualColorType getVisualColor() {
        return this.m_visualColor;
    }

    public final void setVisualColor(VisualColorType em) {
        if (this.m_visualColor != em) {
            this.m_visualColor = em;
            super.setToUpdate();
        }
    }

    public final OlfactiveIntensityType getOlfactiveIntensity() {
        return this.m_olfactiveIntensity;
    }

    public final void setOlfactiveIntensity(OlfactiveIntensityType em) {
        if (this.m_olfactiveIntensity != em) {
            this.m_olfactiveIntensity = em;
            super.setToUpdate();
        }
    }

    public final MatterType getMatter() {
        return this.m_matter;
    }

    public final void setMatter(MatterType em) {
        if (this.m_matter != em) {
            this.m_matter = em;
            super.setToUpdate();
        }
    }

    public final HarmonizeType getHarmonize() {
        return this.m_harmonize;
    }

    public final void setHarmonize(HarmonizeType em) {
        if (this.m_harmonize != em) {
            this.m_harmonize = em;
            super.setToUpdate();
        }
    }

    public final AromaticType getAromatic() {
        return this.m_aromatic;
    }

    public final void setAromatic(AromaticType em) {
        if (this.m_aromatic != em) {
            this.m_aromatic = em;
            super.setToUpdate();
        }
    }

    public final AromaticType getFinalAromatic() {
        return this.m_finalaromatic;
    }

    public final void setFinalAromatic(AromaticType em) {
        if (this.m_finalaromatic != em) {
            this.m_finalaromatic = em;
            super.setToUpdate();
        }
    }

    public final DominantType getDominant() {
        return this.m_dominant;
    }

    public final void setDominant(DominantType em) {
        if (this.m_dominant != em) {
            this.m_dominant = em;
            super.setToUpdate();
        }
    }

    public final SkeletonType getSkeleton() {
        return this.m_skeleton;
    }

    public final void setSkeleton(SkeletonType em) {
        if (this.m_skeleton != em) {
            this.m_skeleton = em;
            super.setToUpdate();
        }
    }

    public final ComplexityType getComplexity() {
        return this.m_complexity;
    }

    public final void setComplexity(ComplexityType em) {
        if (this.m_complexity != em) {
            this.m_complexity = em;
            super.setToUpdate();
        }
    }

    public final OlfDominantType getOlfDominant() {
        return this.m_olfDominant;
    }

    public final void setOlfDominant(OlfDominantType em) {
        if (this.m_olfDominant != em) {
            this.m_olfDominant = em;
            super.setToUpdate();
        }
    }

    protected final void write(BytesManager m) {
        super.write(m);
        m.write(this.m_wineId);
        m.write(DateTimeUtility.get(this.m_creationTime));
        m.write(DateTimeUtility.get(this.m_lastUpdated));
        m.write(Utils.padRight(this.m_name, 30, " "));
        m.write(Utils.padRight(this.m_tastingWith, 200, " "));
        m.write(Utils.padRight(this.m_comment, 200, " "));
        m.writeByteEx(this.m_visualNote);
        m.write(Utils.padRight(this.m_visualComment, 200, " "));
        m.writeByteEx(this.m_olfactiveNote);
        m.write(Utils.padRight(this.m_olfactiveComment, 200, " "));
        m.writeByteEx(this.m_gustatoryNote);
        m.write(Utils.padRight(this.m_gustatoryComment, 200, " "));
        m.write(Utils.padRight(this.m_gustatoryComment2, 200, " "));
        m.write(Utils.padRight(this.m_followingDay, 100, " "));
        m.writeByteEx(this.m_pai);
        m.write(Utils.padRight(this.m_components, 200, " "));
        m.write(this.m_carafage.getValue());
        m.write(this.m_visualIntensity.getValue());
        m.write(this.m_visualColor.getValue());
        m.write(this.m_olfactiveIntensity.getValue());
        m.write(this.m_matter.getValue());
        m.write(this.m_harmonize.getValue());
        m.write(this.m_aromatic.getValue());
        m.write(this.m_finalaromatic.getValue());
        m.write(this.m_dominant.getValue());
        m.write(this.m_skeleton.getValue());
        m.write(this.m_complexity.getValue());
        m.write(this.m_olfDominant.getValue());
        m.writeByteEx(this.m_redX);
        m.writeByteEx(this.m_redY);
        m.writeByteEx(this.m_whiteX);
        m.writeByteEx(this.m_whiteY);
        m.write(0);
        m.write(0);
        m.write(0);
        m.write(0);
        m.write(0);
        m.write(0);
    }

    protected final void read(BytesManager m) {
        super.read(m);
        this.m_wineId = m.getString(4);
        this.m_creationTime = DateTimeUtility.get(m.getString(10));
        this.m_lastUpdated = DateTimeUtility.get(m.getString(10));
        this.m_name = m.getString(30);
        this.m_tastingWith = m.getString(200);
        this.m_comment = m.getString(200);
        this.m_visualNote = m.getByteEx();
        this.m_visualComment = m.getString(200);
        this.m_olfactiveNote = m.getByteEx();
        this.m_olfactiveComment = m.getString(200);
        this.m_gustatoryNote = m.getByteEx();
        this.m_gustatoryComment = m.getString(200);
        this.m_gustatoryComment2 = m.getString(200);
        this.m_followingDay = m.getString(100);
        this.m_pai = m.getByteEx();
        this.m_components = m.getString(200);
        this.m_carafage = CarafageType.parse(m.getByte());
        this.m_visualIntensity = VisualIntensityType.parse(m.getByte());
        this.m_visualColor = VisualColorType.parse(m.getByte());
        this.m_olfactiveIntensity = OlfactiveIntensityType.parse(m.getByte());
        this.m_matter = MatterType.parse(m.getByte());
        this.m_harmonize = HarmonizeType.parse(m.getByte());
        this.m_aromatic = AromaticType.parse(m.getByte());
        this.m_finalaromatic = AromaticType.parse(m.getByte());
        this.m_dominant = DominantType.parse(m.getByte());
        this.m_skeleton = SkeletonType.parse(m.getByte());
        this.m_complexity = ComplexityType.parse(m.getByte());
        this.m_olfDominant = OlfDominantType.parse(m.getByte());
        this.m_redX = m.getByteEx();
        this.m_redY = m.getByteEx();
        this.m_whiteX = m.getByteEx();
        this.m_whiteY = m.getByteEx();
    }
}

