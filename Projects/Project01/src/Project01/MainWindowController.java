package Project01;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainWindowController
{
    // Tabs
    @FXML private TabPane project01TabPane;
    @FXML private Tab sortingAlgorithmsTab;
    @FXML private Tab matrixMultiplicationTab;

    // Labels
    @FXML private Label statusLabel;

    //Buttons
    @FXML private Button startSimulationButton;
    @FXML private Button startMatrixSimulationButton;

    // TableViews
    @FXML private TableView<RuntimeRow> exchangeSortTableView;
    @FXML private TableView<RuntimeRow> mergeSortTableView;
    @FXML private TableView<RuntimeRow> quickSortTableView;
    @FXML private TableView<RuntimeRow> classicalTableView;
    @FXML private TableView<RuntimeRow> strassenTableView;

    // TableColumns
    @FXML private TableColumn<RuntimeRow, String> nExchangeSortTableColumn;
    @FXML private TableColumn<RuntimeRow, String> timeExchangeSortTableColumn;
    @FXML private TableColumn<RuntimeRow, String> nMergeSortTableColumn;
    @FXML private TableColumn<RuntimeRow, String> timeMergeSortTableColumn;
    @FXML private TableColumn<RuntimeRow, String> nQuickSortTableColumn;
    @FXML private TableColumn<RuntimeRow, String> timeQuickSortTableColumn;
    @FXML private TableColumn<RuntimeRow, String> nClassicalTableColumn;
    @FXML private TableColumn<RuntimeRow, String> timeClassicalTableColumn;
    @FXML private TableColumn<RuntimeRow, String> nStrassenTableColumn;
    @FXML private TableColumn<RuntimeRow, String> timeStrassenTableColumn;

    /**
     * Initializes the tables and its columns.
     */
    public void initialize()
    {
        nExchangeSortTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("n"));

        timeExchangeSortTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("time"));

        nMergeSortTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("n"));

        timeMergeSortTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("time"));

        nQuickSortTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("n"));

        timeQuickSortTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("time"));

        nClassicalTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("n"));

        timeClassicalTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("time"));

        nStrassenTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("n"));

        timeStrassenTableColumn.setCellValueFactory(
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

    /**EVENT HANDLERS**/
    public void onStartSimulationClicked()
    {
        setStatus("Message", "Sorting Algorithms simulation started!");
        startSimulationButton.setDisable(true);
        Main.startSortingAlgorithmsSimulation();
    }

    public void onStartMatrixSimulationClicked()
    {
        setStatus("Message", "Matrix Multiplication simulation started!");
        startMatrixSimulationButton.setDisable(true);
        Main.startMatrixMultiplicationSimulation();
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
            case "Classical":
                table = classicalTableView;
                break;
            case "Strassen":
                table = strassenTableView;
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
