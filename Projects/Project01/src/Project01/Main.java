package Project01;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Random;

public class Main extends Application
{
    public static MainWindowController mainWindowController;
    public static Scene mainWindow;
    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // Loads the FXML for the MainWindow Scene and creates the Scene.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Layouts/MainWindow.fxml"));
        mainWindow = new Scene(loader.load(), 1280, 720);
        mainWindowController = loader.getController();

        // Saves a reference of the Stage object so
        // the Controller class can access it.
        // It also sets the stage.
        stage = primaryStage;
        stage.getIcons().add(
                new Image(Main.class
                        .getResourceAsStream("Drawable/Icon.png")));
        stage.setTitle("Project #01 - Sorting Algorithms and Matrix Multiplication");
        stage.setResizable(true);
        stage.setScene(mainWindow);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public static void startSortingAlgorithmsSimulation()
    {
        int[] sizes = new int[] {10000, 20000, 50000, 100000, 200000, 500000, 1000000};
        ObservableList<RuntimeRow> exchangeSortData = FXCollections.observableArrayList();

        for (int i = 0; i < 5; i++)
        {
            int[] array = new int[sizes[i]];
            fillArrayWithRandomNumbers(array);

            long startTime = System.nanoTime();
            SortingAlgorithms.getInstance().exchangeSort(array, SortingAlgorithms.Order.ASCENDING);
            long elapsedTime = System.nanoTime() - startTime;

            exchangeSortData.add(new RuntimeRow(sizes[i], (double)elapsedTime / 1000000000.0));

            Platform.runLater(() ->
                    Main.mainWindowController.updateTable("Exchange Sort", exchangeSortData));

        }








    }

    /**
     * Fills the given array with random integers.
     * @param array - The array to be filled.
     */
    private static void fillArrayWithRandomNumbers(int[] array)
    {
        Random random = new Random();
        for (int i = 0; i < array.length; i++)
            array[i] = random.nextInt(array.length * 10) + 1;
    }
}
