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
        PorterStemmer stemmer = new PorterStemmer();
        
        FileInputStream inputStream = null;
        Scanner sc = null;
        
        // Holds correct word -> stem mappings 
        HashMap<String, String> map = new HashMap<>();
        
        // Holds words to be tested with the stemmer
        ArrayList inputWords = new ArrayList();
        
        try
        {
            inputStream = new FileInputStream("correctmappings.txt");
            sc = new Scanner(inputStream, "UTF-8");
            
            while(sc.hasNextLine())
            {
                String line = sc.nextLine();
                String[] pair = line.split(" ");
                map.put(pair[0], pair[1]);
                inputWords.add(pair[0]);
            }
            
            if (sc.ioException() != null)
                throw sc.ioException();
        }
        finally
        {
            if (inputStream != null)
                inputStream.close();
            
            if(sc != null)
                sc.close();
        }
        
        for(int i = 0; i < inputWords.size(); i++)
        {
            String input = (String) inputWords.get(i);
            String result = stemmer.stem(input);
            String correctResult = map.get(input);
            boolean correct = correctResult.equals(result);
            
            if(correct)
            {
                System.out.println(input+" -> "+result+" PASS");
            }
            else
            {
                System.out.println(input+" -> "+result+" FAIL");
            }
        }
    }    
}
