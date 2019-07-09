import java.io.*;
import java.util.*;

/**
 *
 * @author Benjamin Runco
 */
public class StemmerTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException
    {

        PorterStemmer myStemmer = new PorterStemmer();
        Stemmer trustedStemmer = new Stemmer();

        // Holds list of 370,000+ English words
        ArrayList<String> words = new ArrayList<>();

        try (FileInputStream inputStream = new FileInputStream("words.txt");
             Scanner sc = new Scanner(inputStream, "UTF-8"))
        {

            while (sc.hasNextLine())
            {
                String word = sc.nextLine();
                words.add(word);
            }

            if (sc.ioException() != null)
                throw sc.ioException();
        }

        int numFails = 0;
        for(String word : words)
        {
            String myStem = myStemmer.stem(word);
            char[] w = word.toCharArray();
            trustedStemmer.add(w, w.length);
            trustedStemmer.stem();
            String correctStem = trustedStemmer.toString();

            if(!myStem.equals(correctStem))
            {
                System.out.println("FAIL: " + word);
                System.out.println("My Stemmer: "+ myStem);
                System.out.println("Trusted Stemmer: "+ correctStem);
                System.out.println();
                numFails++;
            }
        }

        System.out.println("SUMMARY: The stemmer test finished with " + numFails + " failures.");
    }
}
