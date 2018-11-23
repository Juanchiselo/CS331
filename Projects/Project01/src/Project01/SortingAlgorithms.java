package Project01;

public class SortingAlgorithms
{
    private static SortingAlgorithms instance = null;
    private int[] originalArray;
    private int[] temporaryArray;

    public enum Order
    {
        ASCENDING,
        DESCENDING
    }

    private SortingAlgorithms()
    {
    }

    public static SortingAlgorithms getInstance()
    {
        if(instance == null)
            instance = new SortingAlgorithms();
        return instance;
    }

    /**
     * The Exchange sort. Takes one element at a time
     * and compares it with the next elements after it.
     * @param array - The unsorted array to be sorted.
     * @param order - The order to sort the array in.
     */
    public void exchangeSort(int[] array, Order order)
    {
        int temp;

        for (int i = 0; i < array.length - 1; i++)
        {
            for (int j = i + 1; j < array.length; j++)
            {
                switch(order)
                {
                    case ASCENDING:
                        if(array[i] > array[j])
                        {
                            temp = array[i];
                            array[i] = array[j];
                            array[j] = temp;
                        }
                        break;
                    case DESCENDING:
                        if(array[i] < array[j])
                        {
                            temp = array[i];
                            array[i] = array[j];
                            array[j] = temp;
                        }
                        break;
                }
            }
        }
    }

    /**
     * Public Quicksort method.
     * @param array - The unsorted array to be sorted.
     */
    public void quickSort(int array[])
    {
        quickSort(array, 0, array.length - 1);
    }

    /**
     * The recursive Quicksort method.
     * @param array - The unsorted array to be sorted.
     * @param first - The first index of the partition.
     * @param last - The last index of the partition.
     */
    private void quickSort(int array[], int first, int last)
    {
        int median = partition(array, first, last);

        // The divide and conquer part of the Quicksort.
        if (first < median - 1)
            quickSort(array, first, median - 1);
        if (median < last)
            quickSort(array, median, last);
    }

    /**
     * Partitions the array.
     * @param array - The unsorted array to be sorted.
     * @param first - The first index of the partition.
     * @param last - The last index of the partition.
     * @return -
     */
    private static int partition(int array[], int first, int last)
    {
        int i = first;
        int j = last;
        int temp;
        int pivot = array[(first + last) / 2];

        while (i <= j)
        {
            while (array[i] < pivot)
                i++;
            while (array[j] > pivot)
                j--;
            if (i <= j)
            {
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        return i;
    }
}
