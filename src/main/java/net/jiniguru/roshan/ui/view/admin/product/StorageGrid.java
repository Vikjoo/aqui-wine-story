package net.jiniguru.roshan.ui.view.admin.product;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.data.provider.Query;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.components.grid.FooterRow;
import com.vaadin.ui.components.grid.HeaderRow;
import com.vaadin.ui.renderers.NumberRenderer;

public class StorageGrid {
private int baseYear;
private int numberOfYears;
public StorageGrid() {
    Grid<CompanyBudgetHistory> grid = new Grid<>();
    grid.setSizeFull();

    // Set the data provider (ListDataProvider<CompanyBudgetHistory>)
    
    DataProvider<CompanyBudgetHistory, ?> dataProvider = new ListDataProvider(null);
	grid.setDataProvider(dataProvider);

    // Set the selection mode
    grid.setSelectionMode(SelectionMode.SINGLE);

    HeaderRow topHeader = grid.prependHeaderRow();

    grid.addColumn(CompanyBudgetHistory::getCompany)
            .setId("CompanyNameColumn")
            .setCaption("Company");

    // Create a DecimalFormat for later use in column renderers and captions
    // that should display values as currency, dollars in this case
    DecimalFormat dollarFormat = new DecimalFormat("$#,##0.00");

    IntStream.range(baseYear, baseYear + numberOfYears).forEach(year -> {

        Column<?, ?> firstHalfColumn = grid.addColumn(
                budgetHistory -> budgetHistory.getFirstHalfOfYear(year),
                new NumberRenderer(dollarFormat))
                .setStyleGenerator(budgetHistory -> "align-right")
                .setId(year + "H1").setCaption("H1");

        Column<?, ?> secondHalfColumn = grid.addColumn(
                budgetHistory -> budgetHistory.getSecondHalfOfYear(year),
                new NumberRenderer(dollarFormat))
                .setStyleGenerator(budgetHistory -> "align-right")
                .setId(year + "H2").setCaption("H2");

        topHeader.join(firstHalfColumn, secondHalfColumn)
                .setText(year + "");
    });

    // Add a summary footer row to the Grid
    FooterRow footer = grid.appendFooterRow();
    // Update the summary row every time data has changed
    // by collecting the sum of each column's data
    grid.getDataProvider().addDataProviderListener(event -> {

        List<CompanyBudgetHistory> data = event.getSource()
                .fetch(new Query<>()).collect(Collectors.toList());

        IntStream.range(baseYear, baseYear + numberOfYears)
                .forEach(year -> {

                    BigDecimal firstHalfSum = data.stream()
                            .map(budgetHistory -> budgetHistory.getFirstHalfOfYear(year))
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                    BigDecimal secondHalfSum = data.stream()
                            .map(budgetHistory -> budgetHistory.getSecondHalfOfYear(year))
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                    footer.getCell(year + "H1")
                            .setText(dollarFormat.format(firstHalfSum));
                    footer.getCell(year + "H2")
                            .setText(dollarFormat.format(secondHalfSum));
                });
    });
    // Fire a data change event to initialize the summary footer
    grid.getDataProvider().refreshAll();

    // Allow column reordering
    grid.setColumnReorderingAllowed(true);

    // Allow column hiding
    grid.getColumns().stream().forEach(column -> column.setHidable(true));
}
class CompanyBudgetHistory {
    String company;
    Map<Integer, YearlyBudgetInfo> budgetHistory;

    public CompanyBudgetHistory(String company,
            Map<Integer, YearlyBudgetInfo> budgetHistory) {
        this.company = company;
        this.budgetHistory = budgetHistory;
    }

    public String getCompany() {
        return company;
    }

    public BigDecimal getFirstHalfOfYear(int year) {
        if (!budgetHistory.containsKey(year)) {
            return null;
        }
        return budgetHistory.get(year).getFirstHalf();
    }

    public BigDecimal getSecondHalfOfYear(int year) {
        if (!budgetHistory.containsKey(year)) {
            return null;
        }
        return budgetHistory.get(year).getSecondHalf();
    }
}
}

class YearlyBudgetInfo {
    BigDecimal firstHalf;
    BigDecimal secondHalf;

    public YearlyBudgetInfo(BigDecimal firstHalf, BigDecimal secondHalf) {
        this.firstHalf = firstHalf;
        this.secondHalf = secondHalf;
    }

    public BigDecimal getFirstHalf() {
        return firstHalf;
    }

    public void setFirstHalf(BigDecimal firstHalf) {
        this.firstHalf = firstHalf;
    }

    public BigDecimal getSecondHalf() {
        return secondHalf;
    }

    public void setSecondHalf(BigDecimal secondHalf) {
        this.secondHalf = secondHalf;
    }
}
