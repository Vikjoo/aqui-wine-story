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
 *  opencellar.framework.Note
 *  opencellar.framework.OlfDominantType
 *  opencellar.framework.OlfactiveIntensityType
 *  opencellar.framework.SkeletonType
 *  opencellar.framework.VisualColorType
 *  opencellar.framework.VisualIntensityType
 *  org.jdesktop.layout.GroupLayout
 *  org.jdesktop.layout.GroupLayout$Group
 *  org.jdesktop.layout.GroupLayout$ParallelGroup
 */
package opencellar.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import opencellar.application.IApplication;
import opencellar.application.utils;
import opencellar.framework.AromaticType;
import opencellar.framework.CarafageType;
import opencellar.framework.ComplexityType;
import opencellar.framework.DominantType;
import opencellar.framework.HarmonizeType;
import opencellar.framework.MatterType;
import opencellar.framework.Note;
import opencellar.framework.OlfDominantType;
import opencellar.framework.OlfactiveIntensityType;
import opencellar.framework.SkeletonType;
import opencellar.framework.VisualColorType;
import opencellar.framework.VisualIntensityType;
import opencellar.ui.NoteEnumHelper;
import opencellar.ui.OCComboBox;
import opencellar.ui.OCComment;
import opencellar.ui.OCNote;
import opencellar.ui.OCTitle;
import org.jdesktop.layout.GroupLayout;

public class NotePane
extends JPanel {
    private Note m_note = null;
    private IApplication m_app = null;
    DateFormat m_dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private OCComboBox carafage;
    private JLabel carafageLabel;
    private OCComment comment;
    private JTextField date;
    private JLabel dateLabel;
    private OCComment finalComment;
    private JLabel finalCommentLabel;
    private OCComment gusComment;
    private JLabel gusCommentLabel;
    private JLabel gusDevelopementLabel;
    private OCComboBox gusDominant;
    private JLabel gusDominantLabel;
    private OCComment gusFinalComment;
    private JLabel gusFinalCommentLabel;
    private JLabel gusFinalLabel;
    private OCComboBox gusFinalPuissanceArom;
    private JLabel gusFinalPuissanceAromLabel;
    private OCComboBox gusMatter;
    private JLabel gusMatterLabel;
    private OCNote gusNote;
    private JLabel gusPai;
    private OCComboBox gusPuissanceArom;
    private JLabel gusPuissanceAromLabel;
    private OCComboBox gusSkeleton;
    private JLabel gusSkeletonLabel;
    private OCComboBox harmony;
    private JLabel harmonyLabel;
    private OCComboBox intensity;
    private JLabel intensityLabel;
    private JTextField name;
    private JLabel nameLabel;
    private OCTitle oCTitle1;
    private OCTitle oCTitle2;
    private OCTitle oCTitle3;
    private OCTitle oCTitle4;
    private OCTitle oCTitle5;
    private OCComment olfComment;
    private JLabel olfCommentLabel;
    private OCComment olfComposantes;
    private JLabel olfComposantesLabel;
    private JLabel olfDominanteLabel;
    private OCComboBox olfDominantes;
    private OCComboBox olfFinComplex;
    private JLabel olfFinComplexLabel;
    private OCComboBox olfIntensity;
    private JLabel olfIntensityLabel;
    private OCNote olfNote;
    private JTextField pai;
    private JLabel participantsLabel;
    private OCComboBox teinte;
    private JLabel teinteLabel;
    private OCComment tomorrow;
    private JLabel tomorrowLabel;
    private OCComment visualComment;
    private JLabel visualCommentLabel;
    private OCNote visualNote;

    public NotePane() {
        this.initComponents();
    }

    public void setDataSource(IApplication application, Note note) {
        if (application == null) {
            throw new IllegalArgumentException("application == null");
        }
        if (note == null) {
            throw new IllegalArgumentException("note == null");
        }
        this.m_app = application;
        this.m_note = note;
        this.performTranslation();
        this.loadListItems();
        this.bindToUI();
    }

    private void bindToUI() {
        this.name.setText(this.m_note.getName());
        this.date.setText(this.m_dateFormat.format(this.m_note.getCreationTime()));
        this.olfComment.setText(this.m_note.getOlfactiveComment());
        this.olfComposantes.setText(this.m_note.getComponents());
        this.comment.setText(this.m_note.getTastingWith());
        this.visualComment.setText(this.m_note.getVisualComment());
        this.gusComment.setText(this.m_note.getGustatoryComment());
        this.gusFinalComment.setText(this.m_note.getGustatoryComment2());
        this.finalComment.setText(this.m_note.getComment());
        this.tomorrow.setText(this.m_note.getFollowingDay());
        this.pai.setText(String.valueOf(this.m_note.getPai()));
        NoteEnumHelper.select(this.carafage, this.m_note.getColor());
        NoteEnumHelper.select(this.harmony, this.m_note.getHarmonize());
        NoteEnumHelper.select(this.intensity, this.m_note.getVisualIntensity());
        NoteEnumHelper.select(this.teinte, this.m_note.getVisualColor());
        NoteEnumHelper.select(this.olfIntensity, this.m_note.getOlfactiveIntensity());
        NoteEnumHelper.select(this.olfDominantes, this.m_note.getOlfDominant());
        NoteEnumHelper.select(this.olfFinComplex, this.m_note.getComplexity());
        NoteEnumHelper.select(this.gusSkeleton, this.m_note.getSkeleton());
        NoteEnumHelper.select(this.gusMatter, this.m_note.getMatter());
        NoteEnumHelper.select(this.gusDominant, this.m_note.getDominant());
        NoteEnumHelper.select(this.gusFinalPuissanceArom, this.m_note.getFinalAromatic());
        NoteEnumHelper.select(this.gusPuissanceArom, this.m_note.getAromatic());
        this.gusNote.setNoteEx(this.m_note.getGustatoryNote());
        this.olfNote.setNoteEx(this.m_note.getOlfactiveNote());
        this.visualNote.setNoteEx(this.m_note.getVisualNote());
    }

    private void bindToData() {
        this.m_note.setName(this.name.getText());
        try {
            this.m_note.setCreationTime(this.m_dateFormat.parse(this.date.getText()));
        }
        catch (Exception ex) {
            this.m_note.setCreationTime(new Date());
        }
        this.m_note.setOlfactiveComment(this.olfComment.getText());
        this.m_note.setComponents(this.olfComposantes.getText());
        this.m_note.setTastingWith(this.comment.getText());
        this.m_note.setVisualComment(this.visualComment.getText());
        this.m_note.setGustatoryComment(this.gusComment.getText());
        this.m_note.setGustatoryComment2(this.gusFinalComment.getText());
        this.m_note.setComment(this.finalComment.getText());
        this.m_note.setFollowingDay(this.tomorrow.getText());
        this.m_note.setPai(utils.tryParse(this.pai.getText(), 0));
        this.m_note.setCarafage(NoteEnumHelper.getCarafageType(this.carafage));
        this.m_note.setHarmonize(NoteEnumHelper.getHarmonizeType(this.harmony));
        this.m_note.setVisualIntensity(NoteEnumHelper.getVisualIntensityType(this.intensity));
        this.m_note.setVisualColor(NoteEnumHelper.getVisualColorType(this.teinte));
        this.m_note.setOlfactiveIntensity(NoteEnumHelper.getOlfactiveIntensityType(this.olfIntensity));
        this.m_note.setOlfDominant(NoteEnumHelper.getOlfDominantType(this.olfDominantes));
        this.m_note.setComplexity(NoteEnumHelper.getComplexityType(this.olfFinComplex));
        this.m_note.setSkeleton(NoteEnumHelper.getSkeletonType(this.gusSkeleton));
        this.m_note.setMatter(NoteEnumHelper.getMatterType(this.gusMatter));
        this.m_note.setDominant(NoteEnumHelper.getDominantType(this.gusDominant));
        this.m_note.setFinalAromatic(NoteEnumHelper.getAromaticType(this.gusFinalPuissanceArom));
        this.m_note.setAromatic(NoteEnumHelper.getAromaticType(this.gusPuissanceArom));
        this.m_note.setGustatoryNote(this.gusNote.getNote());
        this.m_note.setOlfactiveNote(this.olfNote.getNote());
        this.m_note.setVisualNote(this.visualNote.getNote());
    }

    public final Note getNote() {
        this.bindToData();
        return this.m_note;
    }

    private void loadListItems() {
        NoteEnumHelper.pushCarafageType(this.carafage, this.m_app);
        NoteEnumHelper.pushHarmonizeType(this.harmony, this.m_app);
        NoteEnumHelper.pushVisualIntensityType(this.intensity, this.m_app);
        NoteEnumHelper.pushVisualColorType(this.teinte, this.m_app);
        NoteEnumHelper.pushOlfDominantType(this.olfDominantes, this.m_app);
        NoteEnumHelper.pushOlfactiveIntensityType(this.olfIntensity, this.m_app);
        NoteEnumHelper.pushComplexityType(this.olfFinComplex, this.m_app);
        NoteEnumHelper.pushSkeletonType(this.gusSkeleton, this.m_app);
        NoteEnumHelper.pushAromaticType(this.gusFinalPuissanceArom, this.m_app);
        NoteEnumHelper.pushAromaticType(this.gusPuissanceArom, this.m_app);
        NoteEnumHelper.pushMatterType(this.gusMatter, this.m_app);
        NoteEnumHelper.pushDominantType(this.gusDominant, this.m_app);
    }

    private void performTranslation() {
        this.oCTitle1.setText(this.m_app.getRS("NOTE_INFOS"));
        this.oCTitle2.setText(this.m_app.getRS("NOTE_VISUAL_TITLE"));
        this.oCTitle3.setText(this.m_app.getRS("NOTE_OLF_VISUAL"));
        this.oCTitle4.setText(this.m_app.getRS("NOTE_GUS_TITLE"));
        this.oCTitle5.setText(this.m_app.getRS("NOTE_QUA"));
        this.nameLabel.setText(this.m_app.getRS("NOTE_DISPLAY"));
        this.dateLabel.setText(this.m_app.getRS("NOTE_DATE"));
        this.carafageLabel.setText(this.m_app.getRS("NOTE_CARA"));
        this.participantsLabel.setText(this.m_app.getRS("NOTE_PARTI"));
        this.intensityLabel.setText(this.m_app.getRS("NOTE_INTENSITY"));
        this.teinteLabel.setText(this.m_app.getRS("NOTE_TEINTE"));
        this.visualCommentLabel.setText(this.m_app.getRS("NOTE_COMMENT"));
        this.olfCommentLabel.setText(this.m_app.getRS("NOTE_COMMENT"));
        this.olfComposantesLabel.setText(this.m_app.getRS("NOTE_COM"));
        this.olfDominanteLabel.setText(this.m_app.getRS("NOTE_DOM"));
        this.olfFinComplexLabel.setText(this.m_app.getRS("NOTE_COMPLEXITY"));
        this.olfIntensityLabel.setText(this.m_app.getRS("NOTE_INTENSITY"));
        this.gusCommentLabel.setText(this.m_app.getRS("NOTE_COMMENT"));
        this.gusDevelopementLabel.setText(this.m_app.getRS("NOTE_DEV"));
        this.gusDominantLabel.setText(this.m_app.getRS("NOTE_DOM"));
        this.gusFinalCommentLabel.setText(this.m_app.getRS("NOTE_COMMENT"));
        this.gusFinalLabel.setText(this.m_app.getRS("NOTE_FIN"));
        this.gusFinalPuissanceAromLabel.setText(this.m_app.getRS("NOTE_PUI"));
        this.gusMatterLabel.setText(this.m_app.getRS("NOTE_MAT"));
        this.gusPai.setText(this.m_app.getRS("NOTE_PAI"));
        this.gusPuissanceAromLabel.setText(this.m_app.getRS("NOTE_PUI"));
        this.gusSkeletonLabel.setText(this.m_app.getRS("NOTE_SQE"));
        this.finalCommentLabel.setText(this.m_app.getRS("NOTE_COMMENT"));
        this.harmonyLabel.setText(this.m_app.getRS("NOTE_HAR"));
        this.tomorrowLabel.setText(this.m_app.getRS("NOTE_TOM"));
    }

    private void initComponents() {
        this.oCTitle1 = new OCTitle();
        this.nameLabel = new JLabel();
        this.name = new JTextField();
        this.dateLabel = new JLabel();
        this.date = new JTextField();
        this.carafageLabel = new JLabel();
        this.carafage = new OCComboBox();
        this.participantsLabel = new JLabel();
        this.comment = new OCComment();
        this.visualNote = new OCNote();
        this.oCTitle2 = new OCTitle();
        this.intensityLabel = new JLabel();
        this.intensity = new OCComboBox();
        this.teinteLabel = new JLabel();
        this.teinte = new OCComboBox();
        this.visualCommentLabel = new JLabel();
        this.visualComment = new OCComment();
        this.olfNote = new OCNote();
        this.oCTitle3 = new OCTitle();
        this.olfIntensityLabel = new JLabel();
        this.olfIntensity = new OCComboBox();
        this.olfDominanteLabel = new JLabel();
        this.olfDominantes = new OCComboBox();
        this.olfFinComplexLabel = new JLabel();
        this.olfFinComplex = new OCComboBox();
        this.olfComposantesLabel = new JLabel();
        this.olfComposantes = new OCComment();
        this.olfCommentLabel = new JLabel();
        this.olfComment = new OCComment();
        this.oCTitle4 = new OCTitle();
        this.gusNote = new OCNote();
        this.gusDevelopementLabel = new JLabel();
        this.gusSkeletonLabel = new JLabel();
        this.gusSkeleton = new OCComboBox();
        this.gusMatterLabel = new JLabel();
        this.gusMatter = new OCComboBox();
        this.gusPuissanceAromLabel = new JLabel();
        this.gusPuissanceArom = new OCComboBox();
        this.gusCommentLabel = new JLabel();
        this.gusComment = new OCComment();
        this.gusFinalLabel = new JLabel();
        this.gusPai = new JLabel();
        this.pai = new JTextField();
        this.gusFinalPuissanceAromLabel = new JLabel();
        this.gusFinalPuissanceArom = new OCComboBox();
        this.gusDominantLabel = new JLabel();
        this.gusDominant = new OCComboBox();
        this.gusFinalCommentLabel = new JLabel();
        this.gusFinalComment = new OCComment();
        this.oCTitle5 = new OCTitle();
        this.harmonyLabel = new JLabel();
        this.harmony = new OCComboBox();
        this.finalCommentLabel = new JLabel();
        this.finalComment = new OCComment();
        this.tomorrowLabel = new JLabel();
        this.tomorrow = new OCComment();
        this.setLayout(null);
        this.setMinimumSize(new Dimension(573, 881));
        this.oCTitle1.setForeground(new Color(102, 102, 102));
        this.oCTitle1.setFont(new Font("Arial", 1, 12));
        this.oCTitle1.setText("informations g\u00e9n\u00e9rales");
        GroupLayout oCTitle1Layout = new GroupLayout((Container)this.oCTitle1);
        this.oCTitle1.setLayout((LayoutManager)oCTitle1Layout);
        oCTitle1Layout.setHorizontalGroup((GroupLayout.Group)oCTitle1Layout.createParallelGroup(1).add(0, 590, 32767));
        oCTitle1Layout.setVerticalGroup((GroupLayout.Group)oCTitle1Layout.createParallelGroup(1).add(0, 20, 32767));
        this.add(this.oCTitle1);
        this.oCTitle1.setBounds(0, 0, 590, 20);
        this.nameLabel.setText("nom");
        this.add(this.nameLabel);
        this.nameLabel.setBounds(10, 30, 160, 20);
        this.add(this.name);
        this.name.setBounds(180, 30, 380, 19);
        this.dateLabel.setText("date");
        this.add(this.dateLabel);
        this.dateLabel.setBounds(10, 60, 80, 20);
        this.add(this.date);
        this.date.setBounds(180, 60, 100, 19);
        this.carafageLabel.setText("carafage");
        this.add(this.carafageLabel);
        this.carafageLabel.setBounds(300, 60, 80, 20);
        this.add(this.carafage);
        this.carafage.setBounds(390, 60, 170, 22);
        this.participantsLabel.setText("participants");
        this.add(this.participantsLabel);
        this.participantsLabel.setBounds(10, 90, 160, 20);
        this.comment.setBorder(BorderFactory.createEtchedBorder());
        this.comment.setFont(new Font("Tahoma", 0, 12));
        this.comment.setMinimumSize(new Dimension(204, 22));
        this.comment.setPreferredSize(new Dimension(204, 22));
        this.add(this.comment);
        this.comment.setBounds(180, 90, 380, 20);
        GroupLayout visualNoteLayout = new GroupLayout((Container)this.visualNote);
        this.visualNote.setLayout((LayoutManager)visualNoteLayout);
        visualNoteLayout.setHorizontalGroup((GroupLayout.Group)visualNoteLayout.createParallelGroup(1).add(0, 88, 32767));
        visualNoteLayout.setVerticalGroup((GroupLayout.Group)visualNoteLayout.createParallelGroup(1).add(0, 21, 32767));
        this.add(this.visualNote);
        this.visualNote.setBounds(480, 120, 88, 21);
        this.oCTitle2.setForeground(new Color(102, 102, 102));
        this.oCTitle2.setFont(new Font("Arial", 1, 12));
        this.oCTitle2.setText("observations visuelles");
        GroupLayout oCTitle2Layout = new GroupLayout((Container)this.oCTitle2);
        this.oCTitle2.setLayout((LayoutManager)oCTitle2Layout);
        oCTitle2Layout.setHorizontalGroup((GroupLayout.Group)oCTitle2Layout.createParallelGroup(1).add(0, 480, 32767));
        oCTitle2Layout.setVerticalGroup((GroupLayout.Group)oCTitle2Layout.createParallelGroup(1).add(0, 20, 32767));
        this.add(this.oCTitle2);
        this.oCTitle2.setBounds(0, 120, 480, 20);
        this.intensityLabel.setText("intensit\u00e9");
        this.add(this.intensityLabel);
        this.intensityLabel.setBounds(10, 150, 160, 20);
        this.add(this.intensity);
        this.intensity.setBounds(180, 150, 370, 22);
        this.teinteLabel.setText("teinte");
        this.add(this.teinteLabel);
        this.teinteLabel.setBounds(10, 180, 160, 20);
        this.add(this.teinte);
        this.teinte.setBounds(180, 180, 370, 22);
        this.visualCommentLabel.setText("commentaire");
        this.add(this.visualCommentLabel);
        this.visualCommentLabel.setBounds(10, 210, 160, 20);
        this.visualComment.setBorder(BorderFactory.createEtchedBorder());
        this.visualComment.setFont(new Font("Tahoma", 0, 12));
        this.visualComment.setMinimumSize(new Dimension(204, 22));
        this.visualComment.setPreferredSize(new Dimension(204, 22));
        this.add(this.visualComment);
        this.visualComment.setBounds(180, 210, 380, 20);
        GroupLayout olfNoteLayout = new GroupLayout((Container)this.olfNote);
        this.olfNote.setLayout((LayoutManager)olfNoteLayout);
        olfNoteLayout.setHorizontalGroup((GroupLayout.Group)olfNoteLayout.createParallelGroup(1).add(0, 88, 32767));
        olfNoteLayout.setVerticalGroup((GroupLayout.Group)olfNoteLayout.createParallelGroup(1).add(0, 21, 32767));
        this.add(this.olfNote);
        this.olfNote.setBounds(480, 240, 88, 21);
        this.oCTitle3.setForeground(new Color(102, 102, 102));
        this.oCTitle3.setFont(new Font("Arial", 1, 12));
        this.oCTitle3.setText("observations olfactives");
        GroupLayout oCTitle3Layout = new GroupLayout((Container)this.oCTitle3);
        this.oCTitle3.setLayout((LayoutManager)oCTitle3Layout);
        oCTitle3Layout.setHorizontalGroup((GroupLayout.Group)oCTitle3Layout.createParallelGroup(1).add(0, 480, 32767));
        oCTitle3Layout.setVerticalGroup((GroupLayout.Group)oCTitle3Layout.createParallelGroup(1).add(0, 20, 32767));
        this.add(this.oCTitle3);
        this.oCTitle3.setBounds(0, 240, 480, 20);
        this.olfIntensityLabel.setText("intensit\u00e9");
        this.add(this.olfIntensityLabel);
        this.olfIntensityLabel.setBounds(10, 270, 160, 20);
        this.add(this.olfIntensity);
        this.olfIntensity.setBounds(180, 270, 370, 22);
        this.olfDominanteLabel.setText("dominantes");
        this.add(this.olfDominanteLabel);
        this.olfDominanteLabel.setBounds(10, 300, 160, 20);
        this.add(this.olfDominantes);
        this.olfDominantes.setBounds(180, 300, 370, 22);
        this.olfFinComplexLabel.setText("finesse et complexit\u00e9");
        this.add(this.olfFinComplexLabel);
        this.olfFinComplexLabel.setBounds(10, 330, 160, 20);
        this.add(this.olfFinComplex);
        this.olfFinComplex.setBounds(180, 330, 370, 22);
        this.olfComposantesLabel.setText("composantes");
        this.add(this.olfComposantesLabel);
        this.olfComposantesLabel.setBounds(10, 360, 160, 20);
        this.olfComposantes.setBorder(BorderFactory.createEtchedBorder());
        this.olfComposantes.setFont(new Font("Tahoma", 0, 12));
        this.olfComposantes.setMinimumSize(new Dimension(204, 22));
        this.olfComposantes.setPreferredSize(new Dimension(204, 22));
        this.add(this.olfComposantes);
        this.olfComposantes.setBounds(180, 360, 380, 20);
        this.olfCommentLabel.setText("commentaires");
        this.add(this.olfCommentLabel);
        this.olfCommentLabel.setBounds(10, 390, 160, 20);
        this.olfComment.setBorder(BorderFactory.createEtchedBorder());
        this.olfComment.setFont(new Font("Tahoma", 0, 12));
        this.olfComment.setMinimumSize(new Dimension(204, 22));
        this.olfComment.setPreferredSize(new Dimension(204, 22));
        this.add(this.olfComment);
        this.olfComment.setBounds(180, 390, 380, 20);
        this.oCTitle4.setForeground(new Color(102, 102, 102));
        this.oCTitle4.setFont(new Font("Arial", 1, 12));
        this.oCTitle4.setText("observations gustatives");
        GroupLayout oCTitle4Layout = new GroupLayout((Container)this.oCTitle4);
        this.oCTitle4.setLayout((LayoutManager)oCTitle4Layout);
        oCTitle4Layout.setHorizontalGroup((GroupLayout.Group)oCTitle4Layout.createParallelGroup(1).add(0, 480, 32767));
        oCTitle4Layout.setVerticalGroup((GroupLayout.Group)oCTitle4Layout.createParallelGroup(1).add(0, 20, 32767));
        this.add(this.oCTitle4);
        this.oCTitle4.setBounds(0, 420, 480, 20);
        GroupLayout gusNoteLayout = new GroupLayout((Container)this.gusNote);
        this.gusNote.setLayout((LayoutManager)gusNoteLayout);
        gusNoteLayout.setHorizontalGroup((GroupLayout.Group)gusNoteLayout.createParallelGroup(1).add(0, 88, 32767));
        gusNoteLayout.setVerticalGroup((GroupLayout.Group)gusNoteLayout.createParallelGroup(1).add(0, 21, 32767));
        this.add(this.gusNote);
        this.gusNote.setBounds(480, 420, 88, 21);
        this.gusDevelopementLabel.setFont(new Font("Tahoma", 2, 11));
        this.gusDevelopementLabel.setText("developpement en bouche");
        this.add(this.gusDevelopementLabel);
        this.gusDevelopementLabel.setBounds(10, 450, 230, 20);
        this.gusSkeletonLabel.setText("squelette");
        this.add(this.gusSkeletonLabel);
        this.gusSkeletonLabel.setBounds(10, 480, 160, 20);
        this.add(this.gusSkeleton);
        this.gusSkeleton.setBounds(180, 480, 370, 22);
        this.gusMatterLabel.setText("matiere");
        this.add(this.gusMatterLabel);
        this.gusMatterLabel.setBounds(10, 510, 160, 20);
        this.add(this.gusMatter);
        this.gusMatter.setBounds(180, 510, 370, 22);
        this.gusPuissanceAromLabel.setText("puissance aromatique");
        this.add(this.gusPuissanceAromLabel);
        this.gusPuissanceAromLabel.setBounds(10, 540, 160, 20);
        this.add(this.gusPuissanceArom);
        this.gusPuissanceArom.setBounds(180, 540, 370, 22);
        this.gusCommentLabel.setText("commentaires");
        this.add(this.gusCommentLabel);
        this.gusCommentLabel.setBounds(10, 570, 160, 20);
        this.gusComment.setBorder(BorderFactory.createEtchedBorder());
        this.gusComment.setFont(new Font("Tahoma", 0, 12));
        this.gusComment.setMinimumSize(new Dimension(204, 22));
        this.gusComment.setPreferredSize(new Dimension(204, 22));
        this.add(this.gusComment);
        this.gusComment.setBounds(180, 570, 380, 20);
        this.gusFinalLabel.setFont(new Font("Tahoma", 2, 11));
        this.gusFinalLabel.setText("final");
        this.add(this.gusFinalLabel);
        this.gusFinalLabel.setBounds(10, 610, 230, 20);
        this.gusPai.setText("pai en caudalies");
        this.add(this.gusPai);
        this.gusPai.setBounds(300, 610, 180, 20);
        this.add(this.pai);
        this.pai.setBounds(510, 610, 50, 19);
        this.gusFinalPuissanceAromLabel.setText("puissance aromatique");
        this.add(this.gusFinalPuissanceAromLabel);
        this.gusFinalPuissanceAromLabel.setBounds(10, 640, 160, 20);
        this.add(this.gusFinalPuissanceArom);
        this.gusFinalPuissanceArom.setBounds(180, 640, 370, 22);
        this.gusDominantLabel.setText("dominantes");
        this.add(this.gusDominantLabel);
        this.gusDominantLabel.setBounds(10, 670, 160, 20);
        this.add(this.gusDominant);
        this.gusDominant.setBounds(180, 670, 370, 22);
        this.gusFinalCommentLabel.setText("commentaires");
        this.add(this.gusFinalCommentLabel);
        this.gusFinalCommentLabel.setBounds(10, 700, 160, 20);
        this.gusFinalComment.setBorder(BorderFactory.createEtchedBorder());
        this.gusFinalComment.setFont(new Font("Tahoma", 0, 12));
        this.gusFinalComment.setMinimumSize(new Dimension(204, 22));
        this.gusFinalComment.setPreferredSize(new Dimension(204, 22));
        this.add(this.gusFinalComment);
        this.gusFinalComment.setBounds(180, 700, 380, 20);
        this.oCTitle5.setForeground(new Color(102, 102, 102));
        this.oCTitle5.setFont(new Font("Arial", 1, 12));
        this.oCTitle5.setText("qualit\u00e9 d'ensemble");
        GroupLayout oCTitle5Layout = new GroupLayout((Container)this.oCTitle5);
        this.oCTitle5.setLayout((LayoutManager)oCTitle5Layout);
        oCTitle5Layout.setHorizontalGroup((GroupLayout.Group)oCTitle5Layout.createParallelGroup(1).add(0, 590, 32767));
        oCTitle5Layout.setVerticalGroup((GroupLayout.Group)oCTitle5Layout.createParallelGroup(1).add(0, 20, 32767));
        this.add(this.oCTitle5);
        this.oCTitle5.setBounds(0, 730, 590, 20);
        this.harmonyLabel.setText("harmonie");
        this.add(this.harmonyLabel);
        this.harmonyLabel.setBounds(10, 760, 160, 20);
        this.add(this.harmony);
        this.harmony.setBounds(180, 760, 370, 22);
        this.finalCommentLabel.setText("commentaires");
        this.finalCommentLabel.setMaximumSize(null);
        this.finalCommentLabel.setMinimumSize(new Dimension(573, 881));
        this.add(this.finalCommentLabel);
        this.finalCommentLabel.setBounds(10, 790, 160, 20);
        this.finalComment.setBorder(BorderFactory.createEtchedBorder());
        this.finalComment.setFont(new Font("Tahoma", 0, 12));
        this.finalComment.setMinimumSize(new Dimension(204, 22));
        this.finalComment.setPreferredSize(new Dimension(204, 22));
        this.add(this.finalComment);
        this.finalComment.setBounds(180, 790, 380, 20);
        this.tomorrowLabel.setText("le lendemain");
        this.add(this.tomorrowLabel);
        this.tomorrowLabel.setBounds(10, 820, 160, 20);
        this.tomorrow.setBorder(BorderFactory.createEtchedBorder());
        this.tomorrow.setFont(new Font("Tahoma", 0, 12));
        this.tomorrow.setMinimumSize(new Dimension(204, 22));
        this.tomorrow.setPreferredSize(new Dimension(204, 22));
        this.add(this.tomorrow);
        this.tomorrow.setBounds(180, 820, 380, 20);
    }
}

