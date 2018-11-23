package Project01;

public class MergeSort
{
    int[] originalArray;
    int[] temporaryArray;

    public MergeSort(int array[])
    {
        this.originalArray = array;
        this.temporaryArray = new int[array.length];
        sort(0, array.length - 1);
    }

    /**
     * The recursive sort part of the Merge sort.
     * @param first - The first/low index.
     * @param last - The last/high index.
     */
    private void sort(int first, int last)
    {
        if (first < last)
        {
            int middle = first + (last - first) / 2;
            // Left Side.
            sort(first, middle);
            // Right Side.
            sort(middle + 1, last);

            merge(first, middle, last);
        }
    }

    /**
     * Merges the sub-arrays to come up with the solution.
     * @param first - The first/low index.
     * @param middle - The middle index.
     * @param last - The last/high index.
     */
    private void merge(int first, int middle, int last)
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
}
