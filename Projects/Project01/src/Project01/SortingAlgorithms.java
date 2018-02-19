package Project01;

public class SortingAlgorithms
{
    private static SortingAlgorithms instance = null;

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

    public void mergeSort()
    {

    }

    public void quickSort()
    {

    }
}
