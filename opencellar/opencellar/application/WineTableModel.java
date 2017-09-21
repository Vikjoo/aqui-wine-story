/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Area
 *  opencellar.framework.BottleType
 *  opencellar.framework.Category
 *  opencellar.framework.Classification
 *  opencellar.framework.ColorType
 *  opencellar.framework.Country
 *  opencellar.framework.Name
 *  opencellar.framework.Owner
 *  opencellar.framework.RackItemCollection
 *  opencellar.framework.TypeOfWine
 *  opencellar.framework.Wine
 */
package opencellar.application;

import javax.swing.table.AbstractTableModel;
import opencellar.application.IApplication;
import opencellar.application.WineCollection;
import opencellar.framework.Area;
import opencellar.framework.BottleType;
import opencellar.framework.Category;
import opencellar.framework.Classification;
import opencellar.framework.ColorType;
import opencellar.framework.Country;
import opencellar.framework.Name;
import opencellar.framework.Owner;
import opencellar.framework.RackItemCollection;
import opencellar.framework.TypeOfWine;
import opencellar.framework.Wine;

public class WineTableModel
extends AbstractTableModel {
    String[] columnsName = null;
    static final int COLUMNS = 28;
    private IApplication m_app = null;
    private WineCollection m_wines = null;
    private Object[][] data;

    public WineTableModel(WineCollection wines, IApplication app) {
        if (wines == null) {
            throw new IllegalArgumentException("wines == null");
        }
        if (app == null) {
            throw new IllegalArgumentException("app == null");
        }
        this.m_app = app;
        this.m_wines = wines;
        this.fillColumnNames();
        this.fillArray();
    }

    private void fillColumnNames() {
        this.columnsName = new String[28];
        this.columnsName[0] = "";
        this.columnsName[1] = this.m_app.getRS("GRID_Reference");
        this.columnsName[2] = this.m_app.getRS("GRID_Year");
        this.columnsName[3] = this.m_app.getRS("GRID_GeneralNote");
        this.columnsName[4] = this.m_app.getRS("GRID_Name");
        this.columnsName[5] = this.m_app.getRS("GRID_BuyPrice");
        this.columnsName[6] = this.m_app.getRS("GRID_EvaluatePrice");
        this.columnsName[7] = this.m_app.getRS("GRID_Country");
        this.columnsName[8] = this.m_app.getRS("GRID_Area");
        this.columnsName[9] = this.m_app.getRS("GRID_Appellation");
        this.columnsName[10] = this.m_app.getRS("GRID_Classification");
        this.columnsName[11] = this.m_app.getRS("GRID_BottleType");
        this.columnsName[12] = this.m_app.getRS("GRID_ConsumeMin");
        this.columnsName[13] = this.m_app.getRS("GRID_ConsumeMax");
        this.columnsName[14] = this.m_app.getRS("GRID_BestMin");
        this.columnsName[15] = this.m_app.getRS("GRID_BestMax");
        this.columnsName[16] = this.m_app.getRS("GRID_Comment");
        this.columnsName[17] = this.m_app.getRS("GRID_Degree");
        this.columnsName[18] = this.m_app.getRS("GRID_Temperature");
        this.columnsName[19] = this.m_app.getRS("GRID_Cuvee");
        this.columnsName[20] = this.m_app.getRS("GRID_Owner");
        this.columnsName[21] = this.m_app.getRS("WIN_BOTTLES");
        this.columnsName[22] = this.m_app.getRS("GRID_Cepage");
        this.columnsName[23] = this.m_app.getRS("GRID_Grid_TotalEvaluatePrice");
        this.columnsName[24] = this.m_app.getRS("GRID_Grid_TotalBuyPrice");
        this.columnsName[25] = this.m_app.getRS("GRID_Grid_RackItems");
        this.columnsName[26] = this.m_app.getRS("GRID_WineColorString");
        this.columnsName[27] = this.m_app.getRS("GRID_Category");
    }

    private void fillArray() {
        this.data = new Object[this.m_wines.size()][28];
        for (int i = 0; i < this.m_wines.size(); ++i) {
            Wine w = this.m_wines.get(i);
            this.data[i][0] = w.getSystemUid();
            this.data[i][1] = w.getReference();
            this.data[i][2] = w.getYear();
            this.data[i][3] = w.getGeneralNote();
            this.data[i][4] = w.getName();
            this.data[i][5] = Float.valueOf(w.getBuyPrice());
            this.data[i][6] = Float.valueOf(w.getEvaluatePrice());
            this.data[i][7] = w.getCountry().getName();
            this.data[i][8] = w.getArea().getName();
            this.data[i][9] = w.getAppellation().getName();
            this.data[i][10] = w.getClassification().getName();
            this.data[i][11] = w.getBottleType().getName();
            this.data[i][12] = w.getConsumeMin();
            this.data[i][13] = w.getConsumeMax();
            this.data[i][14] = w.getBestMin();
            this.data[i][15] = w.getBestMax();
            this.data[i][16] = w.getComment();
            this.data[i][17] = Float.valueOf(w.getDegree());
            this.data[i][18] = w.getTemperature();
            this.data[i][19] = w.getCuvee();
            this.data[i][20] = w.getOwner().getName();
            this.data[i][21] = w.isManualManagment() ? Integer.valueOf(w.getBottles()) : Integer.valueOf(w.getRackItems().size());
            this.data[i][22] = w.getCepage().getName();
            this.data[i][23] = Float.valueOf(w.getTotalEvaluate());
            this.data[i][24] = Float.valueOf(w.getTotalPurchases());
            this.data[i][25] = w.getAllRackItems();
            ColorType ct = w.getColor();
            this.data[i][26] = ct == ColorType.Red ? this.m_app.getRS("WINE_COLOR_RED") : (ct == ColorType.Rosy ? this.m_app.getRS("WINE_COLOR_ROSY") : (ct == ColorType.White ? this.m_app.getRS("WINE_COLOR_WHITE") : (ct == ColorType.Yellow ? this.m_app.getRS("WINE_COLOR_CHAMP") : (ct == ColorType.LiqueurLike ? this.m_app.getRS("WINE_COLOR_LIQUEUR") : this.m_app.getRS("WINE_COLOR_MISC")))));
            this.data[i][27] = w.getCategory().getName();
        }
    }

    public final Wine getWineAt(String sysId) {
        for (int i = 0; i < this.m_wines.size(); ++i) {
            if (!this.m_wines.get(i).getSystemUid().equals(sysId)) continue;
            return this.m_wines.get(i);
        }
        return null;
    }

    public final WineCollection getSortedList() {
        return null;
    }

    public int getRowCount() {
        return this.m_wines.size();
    }

    public int getColumnCount() {
        return 28;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.data[rowIndex][columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public String getColumnName(int column) {
        return this.columnsName[column];
    }

    public Class getColumnClass(int c) {
        return this.getValueAt(0, c).getClass();
    }

    public void setSource(WineCollection wines) {
        this.m_wines = wines;
        this.fillArray();
        super.fireTableDataChanged();
    }
}

