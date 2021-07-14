import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Tests Wordcount, BSTree, and HashTable objects. Contains main method.
 * 
 * @author Grant Reed
 * @version 12/13/2020
 */
public class Tester
{
    public static final int WordsPerLine = 10;

    public static void main() throws FileNotFoundException
    {
        //build then output hash table
        HashTable ht = new HashTable();
        System.out.println(ht.toString());

        String word; //read from input file
        WordCount wordToFind;  //search for this in the bst
        WordCount wordInTree;  //found in the bst

        //create generic BST, of WordCount here
        BSTree<WordCount> t = new BSTree<WordCount>();

        //want to read word at a time from input file
        Scanner wordsIn = new Scanner(new File("Hamlet.txt"));
        wordsIn.useDelimiter("[^A-Za-z']+");

        int wordCount = 0;
        int lineNum = 1;
        System.out.printf("%3d:  ", lineNum);
        while (wordsIn.hasNext()) {
            word = wordsIn.next();
            ++wordCount;
            System.out.print(word + " ");
            word = word.toLowerCase();
            //searches for word in hash table.
            if(!ht.find(word))
            {
                //instantiates new WordCount from the read word and line number
                wordToFind = new WordCount(word, lineNum);
                //checks if the word is already in the tree 
                //sets a reference to the WordCount in the tree if so.
                if((wordInTree = t.find(wordToFind)) !=null)
                {
                    //increments count for the found word and adds current
                    //line number.
                    wordInTree.count++;
                    wordInTree.addLineNum(lineNum);
                }
                else
                {
                    //adds unfound word to the tree.
                    t.insertBST(wordToFind);
                }
            }
            if (wordCount % WordsPerLine == 0) {
                ++lineNum;
                System.out.printf("\n%3d:  ", lineNum);
            }
        }
        //EOF
        //prints a header for the printed tree.
        System.out.println();
        System.out.printf("%-12s%-8s%3s\n", "Word", "Count","Line numbers");
        System.out.printf("--------------------------------\n");

        //print bst in alpha order
        System.out.println(t.toString());
    }
}
