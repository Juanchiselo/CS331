import java.io.*;
import java.util.ArrayList;

public class FileHandler
{
    private static FileHandler instance = null;
    private String line;
    private String fileName;
    private FileReader fileReader;
    private BufferedReader bufferedReader;

    public static FileHandler getInstance()
    {
        if(instance == null)
            instance = new FileHandler();
        return instance;
    }

    private FileHandler()
    {
    }

    private void openFile()
    {
        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
        } catch(FileNotFoundException e) {
            System.err.println("ERROR: Unable to open file '" + fileName + "'.");
        }
    }

    private void closeFile()
    {
        try {
            bufferedReader.close();
            fileReader.close();
        } catch(IOException e) {
            System.err.println("ERROR: Unable to close file '" + fileName + "'.");
        }
    }

   
    public ArrayList<Item> readFile()
    {
        fileName = "C:\\Input.txt";
        ArrayList<Item> items = new ArrayList<>();
        int index = 1;
        
        openFile();
        try
        {
            while((line = bufferedReader.readLine()) != null)
            {
                String[] attributes = line.split(",");
                Item item = new Item(index++, Integer.parseInt(attributes[0]),
                        Integer.parseInt(attributes[1]));
                items.add(item);
            }
        } catch (IOException e) {
            System.err.println("ERROR: Unable to read from file '" + fileName + "'.");
        } finally {
            closeFile();
        }

        return items;
    }
}