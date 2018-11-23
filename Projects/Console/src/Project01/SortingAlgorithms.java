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
     * The public Merge sort method.
     * @param array - The unsorted array to be sorted.
     */
    public void mergeSort(int array[])
    {
        this.originalArray = array;
        this.temporaryArray = new int[array.length];
        mergeSort(0, array.length - 1);
    }

    private void mergeSort(int first, int last)
    {
        if (first < last)
        {
            int middle = first + (last - first) / 2;
            // Below step sorts the left side of the array
            mergeSort(first, middle);
            // Below step sorts the right side of the array
            mergeSort(middle + 1, last);
            // Now merge both sides
            mergeParts(first, middle, last);
        }
    }

    private void mergeParts(int first, int middle, int last)
    {
        for (int i = first; i <= last; i++)
            temporaryArray[i] = originalArray[i];

        int i = first;
        int j = middle + 1;
        int k = first;

        while (i <= middle && j <= last)
        {
            if (temporaryArray[i] <= temporaryArray[j])
            {
                originalArray[k] = temporaryArray[i];
                i++;
            }
            else
                {
                originalArray[k] = temporaryArray[j];
                j++;
            }
            k++;
        }
        while (i <= middle)
        {
            originalArray[k] = temporaryArray[i];
            k++;
            i++;
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
