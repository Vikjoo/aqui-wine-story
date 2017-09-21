
package net.spark.cellar;

import java.util.Date;


public final class Note
extends CellarObject{
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
	private Wine m_wine;

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
        return 1580;
    }

    public final Wine getWine() {

        return this.m_wine;
    }

    public final void setWine(Wine w) {
        if (w == null) {
            this.m_wineId = "0000";
            
        } else if (!w.getSystemUid().equals(this.m_wineId)) {
            this.m_wineId = w.getSystemUid();
            
        }
    }

    public final void markAsDeleted() {
       // super.setState(ObjectState.Delete);
    }

    public final int getRedX() {
        return this.m_redX;
    }

    public final void setRedX(int value) {
            this.m_redX = value;
    }

    public final int getRedY() {
        return this.m_redY;
    }

    public final void setRedY(int value) {
            this.m_redY = value;
 
    }

    public final int getWhiteX() {
        return this.m_whiteX;
    }

    public final void setWhiteX(int value) {
            this.m_whiteX = value;
    }

    public final int getWhiteY() {
        return this.m_whiteY;
    }

    public final void setWhiteY(int value) {
 
            this.m_whiteY = value;
 
    }

    public final String getComponents() {
        return this.m_components;
    }

    public final void setComponents(String s) {
        this.m_components = s;
        
    }

    public final String getComment() {
        return this.m_comment;
    }

    public final void setComment(String s) {
        this.m_comment = s;
        
    }

    public final Date getCreationTime() {
        return this.m_creationTime;
    }

    public final void setCreationTime(Date d) {
        if (d != null) {
            this.m_creationTime = d;
            
        }
    }

    public final Date getLastUpdated() {
        return this.m_lastUpdated;
    }

    public final void setLastUpdated(Date d) {
        if (d != null) {
            this.m_lastUpdated = d;
            
        }
    }

    public final String getFollowingDay() {
        return this.m_followingDay;
    }

    public final void setFollowingDay(String s) {
        this.m_followingDay = s;
        
    }

    public final int getPai() {
        return this.m_pai;
    }

    public final void setPai(int value) {
            this.m_pai = value;
    }

    public final String getName() {
        return this.m_name;
    }

    public final void setName(String s) {
        this.m_name = s;
        
    }

    public final String getTastingWith() {
        return this.m_tastingWith;
    }

    public final void setTastingWith(String s) {
        this.m_tastingWith = s;
        
    }

    public final String getVisualComment() {
        return this.m_visualComment;
    }

    public final void setVisualComment(String s) {
        this.m_visualComment = s;
        
    }

    public final String getOlfactiveComment() {
        return this.m_olfactiveComment;
    }

    public final void setOlfactiveComment(String s) {
        this.m_olfactiveComment = s;
        
    }

    public final String getGustatoryComment() {
        return this.m_gustatoryComment;
    }

    public final void setGustatoryComment(String s) {
        this.m_gustatoryComment = s;
        
    }

    public final String getGustatoryComment2() {
        return this.m_gustatoryComment2;
    }

    public final void setGustatoryComment2(String s) {
        this.m_gustatoryComment2 = s;
        
    }

    public final int getGustatoryNote() {
        return this.m_gustatoryNote;
    }

    public final void setGustatoryNote(int value) {
            this.m_gustatoryNote = value;
    }

    public final int getOlfactiveNote() {
        return this.m_olfactiveNote;
    }

    public final void setOlfactiveNote(int value) {
            this.m_olfactiveNote = value;
    }

    public final int getVisualNote() {
        return this.m_visualNote;
    }

    public final void setVisualNote(int value) {
            this.m_visualNote = value;
    }

    public String getFk() {
        return this.m_wineId;
    }

    public final CarafageType getColor() {
        return this.m_carafage;
    }

    public final void setCarafage(CarafageType em) {
            this.m_carafage = em;
    }

    public final VisualIntensityType getVisualIntensity() {
        return this.m_visualIntensity;
    }

    public final void setVisualIntensity(VisualIntensityType em) {
            this.m_visualIntensity = em;
    }

    public final VisualColorType getVisualColor() {
        return this.m_visualColor;
    }

    public final void setVisualColor(VisualColorType em) {
            this.m_visualColor = em;
    }

    public final OlfactiveIntensityType getOlfactiveIntensity() {
        return this.m_olfactiveIntensity;
    }

    public final void setOlfactiveIntensity(OlfactiveIntensityType em) {
            this.m_olfactiveIntensity = em;
    }

    public final MatterType getMatter() {
        return this.m_matter;
    }

    public final void setMatter(MatterType em) {
            this.m_matter = em;
    }

    public final HarmonizeType getHarmonize() {
        return this.m_harmonize;
    }

    public final void setHarmonize(HarmonizeType em) {
            this.m_harmonize = em;
    }

    public final AromaticType getAromatic() {
        return this.m_aromatic;
    }

    public final void setAromatic(AromaticType em) {
            this.m_aromatic = em;
    }

    public final AromaticType getFinalAromatic() {
        return this.m_finalaromatic;
    }

    public final void setFinalAromatic(AromaticType em) {
            this.m_finalaromatic = em;
    }

    public final DominantType getDominant() {
        return this.m_dominant;
    }

    public final void setDominant(DominantType em) {
            this.m_dominant = em;
    }

    public final SkeletonType getSkeleton() {
        return this.m_skeleton;
    }

    public final void setSkeleton(SkeletonType em) {
            this.m_skeleton = em;
    }

    public final ComplexityType getComplexity() {
        return this.m_complexity;
    }

    public final void setComplexity(ComplexityType em) {
            this.m_complexity = em;
    }

    public final OlfDominantType getOlfDominant() {
        return this.m_olfDominant;
    }

    public final void setOlfDominant(OlfDominantType em) {
            this.m_olfDominant = em;
    }


 
}

