import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Creates behavior for a linear probing HashTable.
 * 
 * @author Grant Reed
 * @version 12/13/2020
 */
public class HashTable
{
    public static final int MAX = 30;

    private String hashTable[];
    /**
     * Constructor that creates a hash table out of an input file.
     */
    public HashTable() throws FileNotFoundException
    {
        hashTable = new String[MAX];

        //be certain array contains all nulls
        for (int i = 0; i < MAX; ++i)
            hashTable[i] = null;

        //read file of common words
        int collisions = 0;
        Scanner commonWords = new Scanner(new File("Common_words.txt"));
        while (commonWords.hasNext()) {
            String word = commonWords.nextLine();
            //creates an integer key for the scanned word.
            int key = hash(word);
            //storing beginning position for later error checking.
            int start = key;
            //checks if there is already a word in the key's position.
            while(hashTable[key] != null)
            {
                //counts every collision that occurs.
                collisions++;
                //increments key if the position is already filled.
                key++;
                //checks if every position in the table has already been checked.
                if(key == start)
                {
                    System.err.println("Table is already full, input file too large.");
                    System.out.println("Table is already full, input file too large.");
                    System.exit(1);
                }
                //handles if the end of the array has been reached.
                if(key >= MAX)
                    key = 0;
            }
            //assigns word to the key's position in the table.
            hashTable[key] = word;
        }
        System.out.println
        ("Number of collisions building the table: " + collisions);        
    }

    public int hash(String word)
    {
        char ch;
        double total;

        total = 0.0;
        for (int i = 0; i < word.length(); ++i) {
            ch = word.charAt(i);
            total += (double) ch;
            total *= Math.PI;
        }
        double decimal = total - (int) total;
        int hash = (int) (MAX * decimal);
        return hash;
    }

    /**
     * Searches for string inside the hash table.
     * 
     * @return true if the string is found.
     * @param word to search for.
     */
    public Boolean find(String word)
    {
        //finding key and starting point.
        int key = hash(word);
        int start = key;
        //checks if key position is filled.
        while(hashTable[key] != null)
        {   
            //returns true if the position contains the searched for word.
            if(hashTable[key].equals(word))
                return true;
            else
            {
                //cycles through positions until word is found or loops
                //through the entire table.
                key++;
                if(key == start)
                    return false;
                if(key >= MAX)
                    key = 0;
            }
        }
        return false;        
    }

    /**
     * Builds a chart string out of the strings in the hash table.
     * 
     * @return built string.
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        //integer to keep track of how many elements are in the table.
        int wordNum = 1;
        //Cycles through each element and appends them to sb.
        for(String word: hashTable)
        {   
            //only adds to the string if there is an element in the current position.
            if(word != null)
            {
                //formats the string as a chart, adds the word number and word.
                sb.append(wordNum);
                sb.append("  " + word + "\n");
                wordNum++;
            }
        }
        return sb.toString();
    }
}
