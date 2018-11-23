package Project01;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
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
        int[] sizes = new int[] {10000, 20000, 50000, 100000, 200000, 500000, 1000000, 2000000, 5000000, 10000000, 20000000,
            100000000};
        String[] sortingAlgorithms = new String[] {"Exchange Sort", "Merge Sort", "Quick Sort"};

        ObservableList<RuntimeRow> exchangeSortData = FXCollections.observableArrayList();
        ObservableList<RuntimeRow> mergeSortData = FXCollections.observableArrayList();
        ObservableList<RuntimeRow> quickSortData = FXCollections.observableArrayList();

        for (int i = 0; i < sizes.length; i++)
        {
            exchangeSortData.add(new RuntimeRow(sizes[i], ""));
            mergeSortData.add(new RuntimeRow(sizes[i], ""));
            quickSortData.add(new RuntimeRow(sizes[i], ""));
        }

        Platform.runLater(() ->
                Main.mainWindowController.updateTable("Exchange Sort", exchangeSortData));
        Platform.runLater(() ->
                Main.mainWindowController.updateTable("Merge Sort", mergeSortData));
        Platform.runLater(() ->
                Main.mainWindowController.updateTable("Quick Sort", quickSortData));


            for (int j = 0; j < sortingAlgorithms.length; j++)
            {
                final int JINDEX = j;

                Thread thread = new Thread(() ->
                {
                    for (int i = 0; i < sizes.length; i++)
                    {
                        final int INDEX = i;

                        try
                        {
                            int[] array = new int[sizes[INDEX]];
                            fillArrayWithRandomNumbers(array);
                            long startTime;
                            long elapsedTime = 0;
                            String sortingAlgorithm = sortingAlgorithms[JINDEX];

                            switch(sortingAlgorithm)
                            {
                                case "Exchange Sort":
                                startTime = System.nanoTime();
                                SortingAlgorithms.getInstance().exchangeSort(array, SortingAlgorithms.Order.ASCENDING);
                                elapsedTime = System.nanoTime() - startTime;

                                exchangeSortData.get(INDEX).setTime((double)elapsedTime / 1000000000.0);
                                Platform.runLater(() ->
                                        Main.mainWindowController.updateTable("Exchange Sort", exchangeSortData));
                                    break;
                                case "Merge Sort":
                                    startTime = System.nanoTime();
                                    MergeSort mergeSort = new MergeSort(array);
                                    elapsedTime = System.nanoTime() - startTime;

                                    mergeSortData.get(INDEX).setTime((double)elapsedTime / 1000000000.0);
                                    Platform.runLater(() ->
                                            Main.mainWindowController.updateTable("Merge Sort", mergeSortData));
                                    break;
                                case "Quick Sort":
                                    startTime = System.nanoTime();
                                    SortingAlgorithms.getInstance().quickSort(array);
                                    elapsedTime = System.nanoTime() - startTime;
                                    quickSortData.get(INDEX).setTime((double)elapsedTime / 1000000000.0);

                                    Platform.runLater(() ->
                                            Main.mainWindowController.updateTable("Quick Sort", quickSortData));
                                    break;
                                default:
                                    Platform.runLater(() -> mainWindowController.setStatus("ERROR",
                                            "Not a valid sorting algorithm."));
                            }
                        }
                        catch(OutOfMemoryError exception)
                        {
                            mergeSortData.get(INDEX).setTime("Out of Memory!");
                        }
                    }
                });
                thread.start();
        }
    }

    public static void startMatrixMultiplicationSimulation()
    {
        int[] sizes = new int[] {2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048};
        String[] matrixMultiplicationAlgorithms = new String[] {"Classical", "Strassen"};

        ObservableList<RuntimeRow> classicalData = FXCollections.observableArrayList();
        ObservableList<RuntimeRow> strassenData = FXCollections.observableArrayList();

        for (int i = 0; i < sizes.length; i++)
        {
            classicalData.add(new RuntimeRow(sizes[i], ""));
            strassenData.add(new RuntimeRow(sizes[i], ""));
        }

        Platform.runLater(() ->
                Main.mainWindowController.updateTable("Classical", classicalData));
        Platform.runLater(() ->
                Main.mainWindowController.updateTable("Strassen", strassenData));

        for (int i = 0; i < sizes.length; i++)
        {
            int n = sizes[i];
            int[][] a = new int[n][n];
            int[][] b = new int[n][n];
            fill2DArrayWithRandomNumbers(a, n);
            fill2DArrayWithRandomNumbers(b, n);

            long startTime = System.nanoTime();
            int[][] c = MatrixMultiplication.getInstance().classicalMatrixMultiplication(n, a, b);
            long elapsedTime = System.nanoTime() - startTime;

            classicalData.get(i).setTime((double)elapsedTime / 1000000000.0);

            Platform.runLater(() ->
                    Main.mainWindowController.updateTable("Classical", classicalData));
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

    private static void fill2DArrayWithRandomNumbers(int[][] array, int n)
    {
        Random random = new Random();
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
                array[i][j] = random.nextInt(n * 10) + 1;
        }
    }
}
