package Project01;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainWindowController
{
    // Tabs
    @FXML private TabPane mainWindowTabPane;
    @FXML private Tab homework01Tab;
    @FXML private TabPane project01TabPane;
    @FXML private Tab sortingAlgorithmsTab;
    @FXML private Tab matrixMultiplicationTab;

    // Labels
    @FXML private Label statusLabel;

    //Buttons
    @FXML private Button startSimulationButton;

    // TableViews
    @FXML private TableView<RuntimeRow> exchangeSortTableView;
    @FXML private TableView<RuntimeRow> mergeSortTableView;
    @FXML private TableView<RuntimeRow> quickSortTableView;

    // TableColumns
    @FXML private TableColumn<RuntimeRow, String> nExchangeSortTableColumn;
    @FXML private TableColumn<RuntimeRow, String> timeExchangeSortTableColumn;
    @FXML private TableColumn<RuntimeRow, String> nMergeSortTableColumn;
    @FXML private TableColumn<RuntimeRow, String> timeMergeSortTableColumn;
    @FXML private TableColumn<RuntimeRow, String> nQuickSortTableColumn;
    @FXML private TableColumn<RuntimeRow, String> timeQuickSortTableColumn;

    public void initialize()
    {
        nExchangeSortTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("n"));

        timeExchangeSortTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("time"));
    }

    /**NAVIGATION HANDLERS**/

    public void onSortingAlgorithmsSelected()
    {
        project01TabPane.getSelectionModel().select(sortingAlgorithmsTab);
    }

    public void onMatrixMultiplicationSelected()
    {
        project01TabPane.getSelectionModel().select(matrixMultiplicationTab);
    }


    public void onStartSimulationClicked()
    {
        Main.startSortingAlgorithmsSimulation();
    }

    /**
     * Updates the table with new data.
     * @param tableName - The name of the table to update.
     * @param data - The new data to update the table with.
     */
    public void updateTable(String tableName, ObservableList<RuntimeRow> data)
    {
        TableView<RuntimeRow> table = null;

        switch (tableName)
        {
            case "Exchange Sort":
                table = exchangeSortTableView;
                break;
            case "Merge Sort":
                table = mergeSortTableView;
                break;
            case "Quick Sort":
                table = quickSortTableView;
                break;
        }

        table.setItems(data);
        table.refresh();
    }



    /**
     * Displays the status messages located in the status bar.
     * @param type - The type of the status message.
     * @param message - The message to display.
     */
    public void setStatus(String type, String message)
    {
        if(type.equals("ERROR"))
            statusLabel.setStyle("-fx-text-fill: red");
        else
            statusLabel.setStyle("-fx-text-fill: white");

        statusLabel.setText(type + ": " + message);
    }
}
