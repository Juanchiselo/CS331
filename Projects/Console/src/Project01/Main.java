package Project01;

import java.util.Random;

public class Main
{

    public static void main(String[] args)
    {
        int[] sizes = new int[] {10000, 20000, 50000, 100000, 200000, 500000};
        String[] sortingAlgorithms = new String[] {"Exchange Sort", "Merge Sort", "Quick Sort"};
        double[][] data = new double[sizes.length][3];

        System.out.println("Starting simulation...");
        for (int i = 0; i < 3; i++)
        {
            System.out.println(sortingAlgorithms[i] + "\nSize\t\tTime");
            for (int j = 0; j < sizes.length; j++)
            {
                int[] array = new int[sizes[j]];
                fillArrayWithRandomNumbers(array);
                long startTime;
                long elapsedTime = 0;

                switch(sortingAlgorithms[i])
                {
                    case "Exchange Sort":
                        startTime = System.nanoTime();
                        SortingAlgorithms.getInstance().exchangeSort(array, SortingAlgorithms.Order.ASCENDING);
                        elapsedTime = System.nanoTime() - startTime;
                        break;
                    case "Merge Sort":
                        startTime = System.nanoTime();
                        SortingAlgorithms.getInstance().mergeSort(array);
                        elapsedTime = System.nanoTime() - startTime;
                        break;
                    case "Quick Sort":
                        startTime = System.nanoTime();
                        SortingAlgorithms.getInstance().quickSort(array);
                        elapsedTime = System.nanoTime() - startTime;
                        break;
                }

                System.out.println(sizes[j] + "\t\t" + (double)elapsedTime / 1000000000.0);

                data[j][i] = (double)elapsedTime / 1000000000.0;


            }
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
