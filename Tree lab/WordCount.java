/**
 * Defines behavior and stores information of WordCount objects.
 * 
 * @author Grant Reed
 * @version 12/13/2020
 */
public class WordCount implements Comparable<WordCount>
{
    protected String word;
    protected int count;
    protected CircularList lineNums;

    /**
     * Basic Constructor. (not used in this program).
     */
    public WordCount()
    {
        //initializes common variables.
        lineNums = new CircularList();
        count = 1;
    }
    
    /**
     * Constructor that builds a WordCount object with the passed parameters.
     * 
     * @param the line number the string was found.
     * @param the string to be stored.
     */
    public WordCount(String _word, int lnNum)
    {
        //initializes common variables 
        count = 1;
        lineNums = new CircularList();
        //initializes specific passed variables
        word = _word;
        addLineNum(lnNum);
    }
    
    //required for class to compile
    public int compareTo(WordCount other)
    {
        return word.compareTo(other.word);
    }

    /**
     * Builds a string with the information of the current WordCount object.
     * 
     * @return formatted string
     */
    public String toString()
    {
        //creates a string of the class's variables in a readable format.
        String s = String.format("%-14s%-3d%3s" +lineNums.toString(), word, count,"");
        return s.toString();
    }
    
    /**
     * Adds line number to the list of line numbers.
     * 
     * @param line number to be added
     */
    public void addLineNum(int lnNum)
    {
        //checks if this is the first instance of the word.
        if(lineNums.isEmpty())
            lineNums.append(lnNum);
        //checks if the word has already been seen on that line before adding it.
        //only works if the file is read in order.
        else if(lineNums.lastPos() != lnNum)
            lineNums.append(lnNum);
    }
}