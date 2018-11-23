import java.text.DecimalFormat;
import java.util.*;

public class Main
{
    private static DecimalFormat decimalFormat;

    public static void main(String[] args)
    {
        decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        Scanner scanner = new Scanner(System.in);
        String choice;
        int knapsackCapacity;

        System.out.println("=============================================\n"
                         + "       Project #02 - Knapsack Problems\n"
                         + "=============================================\n\n"
                         + "Choose a Knapsack Problem:\n"
                         + "\t1) Fractional Knapsack\n"
                         + "\t2) 0/1 Knapsack");

        do {
            System.out.print("\nChoice: ");
            choice = scanner.nextLine();
        }while(!choice.equals("1") && !choice.equals("2"));

        System.out.print("Please enter the capacity of the Knapsack (W): ");
        knapsackCapacity = scanner.nextInt();

        System.out.println("\nReading in Input.txt file to get items "
                + "with their weights and profits...");
        ArrayList<Item> items = FileHandler.getInstance().readFile();

        if(choice.equals("1"))
            fractionalKnapsack(knapsackCapacity, items);
        else
            zeroOneKnapsack(knapsackCapacity, items);
    }

    /**
     * The Fractional Knapsack Greedy algorithm.
     * @param knapsackCapacity - The capacity of the knapsack.
     * @param items - The available items.
     */
    private static void fractionalKnapsack(int knapsackCapacity,
                                          ArrayList<Item> items)
    {
        int usedWeight = 0;
        double totalProfit = 0;
        ArrayList<Item> knapsack = new ArrayList<>();
        double fractionalProfit = 0;
        double fractionOfItem = 0;

        System.out.println("\nItems:\n\tItem\tWeight\tProfit\tRatio");
        printItems(items);

        // Sort items based on their Profit to Weight ratio
        // in a non-increasing order.
        Collections.sort(items, new ItemComparator());

        System.out.println("\nSorted Items:\n\tItem\tWeight\tProfit\tRatio");
        printItems(items);

        for (int i = 0; i < items.size(); i++)
        {
            // Add the item completely if if fits within
            // the knapsack constraints.
            if(items.get(i).getWeight() + usedWeight <= knapsackCapacity)
            {
                knapsack.add(items.get(i));
                usedWeight += items.get(i).getWeight();
                totalProfit += items.get(i).getProfit();
            }
            // Add a fraction of the item to completely fill the knapsack
            // and stop looking at the next items because the knapsack is full.
            else
            {
                fractionOfItem = ((double)knapsackCapacity - usedWeight)
                        / items.get(i).getWeight();
                fractionalProfit = items.get(i).getProfit()
                        * fractionOfItem;
                totalProfit += fractionalProfit;
                knapsack.add(items.get(i));
                break;
            }
        }

        System.out.print("\nItems in a " + knapsackCapacity + " (W) knapsack that maximize the total profit:\n");
        for (int i = 0; i < knapsack.size(); i++)
        {
            if(i < knapsack.size() - 1)
                System.out.println("\t1 of item #" + knapsack.get(i).getItemNumber()
                        + " - $" + knapsack.get(i).getProfit());
            else
                System.out.println("\t" + decimalFormat.format(fractionOfItem)
                    + " of item #" + knapsack.get(i).getItemNumber()
                    + " - $" + decimalFormat.format(fractionalProfit));

        }
        System.out.println("Total profit: $" + decimalFormat.format(totalProfit));
    }

    /**
     * The 0/1 Knapsack Dynamic Programming algorithm.
     * @param knapsackCapacity
     * @param items
     */
    private static void zeroOneKnapsack(int knapsackCapacity,
                                ArrayList<Item> items)
    {
        ArrayList<Item> knapsack = new ArrayList<>();

        System.out.println("\nItems:\n\tItem\tWeight\tProfit");
        for (int i = 0; i < items.size(); i++)
        {
            System.out.println("\t#" + items.get(i).getItemNumber()
                    + "\t\t" + items.get(i).getWeight()
                    + "\t\t$" + items.get(i).getProfit());
        }

        // The item vs knapsack capacity table.
        int K[][] = new int[items.size() + 1][knapsackCapacity + 1];

        // Fills the table with profit values for all possible
        // combinations of items and knapsacks of varying capacities
        // up until the actual given capacity.
        for (int i = 0; i <= items.size(); i++)
        {
            for (int weight = 0; weight <= knapsackCapacity; weight++)
            {
                // If there is no item or the weight is zero.
                // This fills out the first row with zeroes since
                // there are not items to test.
                if (i == 0 || weight == 0)
                    K[i][weight] = 0;
                else if (items.get(i - 1).getWeight() <= weight)
                {
                    int currentProfit = items.get(i - 1).getProfit()
                            + K[i - 1][weight-items.get(i - 1).getWeight()];
                    int previousProfit = K[i - 1][weight];

                    if(currentProfit > previousProfit)
                        K[i][weight] = currentProfit;
                    else
                        K[i][weight] = previousProfit;
                }
                else
                    K[i][weight] = K[i - 1][weight];
            }
        }

        System.out.print("\nItems in a " + knapsackCapacity
                + " (W) knapsack that maximize the total profit:\n");
//        for (int i = 0; i < knapsack.size(); i++)
//        {
//            System.out.println("\t1 of item #" + knapsack.get(i).getItemNumber()
//                        + " - $" + knapsack.get(i).getProfit());
//        }

        System.out.println("Total profit: $" + K[items.size()][knapsackCapacity]);
    }

    /**
     * ItemComprator to compare items based on their Profit to Weight
     * ratio so they can be sorted in a non-increasing order.
     */
    static class ItemComparator implements Comparator<Item>
    {
        public int compare(Item item1, Item item2)
        {
            if(item1.getRatio() > item2.getRatio())
                return -1;
            else if(item1.getRatio() < item2.getRatio())
                return 1;
            else
                return 0;
        }
    }

    /**
     * Helper function to print the available items.
     * @param items - The ArrayList of available items.
     */
    private static void printItems(ArrayList<Item> items)
    {
        for (int i = 0; i < items.size(); i++)
        {
            System.out.println("\t#" + items.get(i).getItemNumber()
                    + "\t\t" + items.get(i).getWeight()
                    + "\t\t$" + items.get(i).getProfit() + "\t\t"
                    + decimalFormat.format(items.get(i).getRatio()));
        }
    }
}
