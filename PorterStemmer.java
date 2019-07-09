/**
 *
 * @author Benjamin Runco
 */
public class PorterStemmer 
{
    private char[] vowels = {'A', 'a', 'E', 'e', 'I', 'i', 'O', 'o', 'U', 'u'};
    
    public PorterStemmer() {}
    
    public String stem(String word)
    {
        word = word.toLowerCase();
        
        if(word.length() < 3)
            return word;
        
        word = performStep1a(word);
        word = performStep1b(word);
        word = performStep1c(word);
        word = performStep2(word);
        word = performStep3(word);
        word = performStep4(word);
        word = performStep5a(word);
        word = performStep5b(word);
        return word;
    }
    
    private String performStep1a(String word)
    {   
        if(word.endsWith("sses"))
        {
            return replaceSuffix(word, "sses", "ss");
        }
        else if(word.endsWith("ies"))
        {
            return replaceSuffix(word, "ies", "i");
        }
        else if(word.endsWith("ss")) 
        {
            return replaceSuffix(word, "ss", "ss");
        }
        else if(word.endsWith("s"))
        {
            return replaceSuffix(word, "s", "");
        }
        
        return word;
    }
    
    private String performStep1b(String word)
    {        
        boolean success = false;
        if(word.endsWith("eed"))
        {
            String stem = replaceSuffix(word, "eed", "");
            int measure = getMeasure(stem);
            
            if(measure > 0)
                return replaceSuffix(word, "eed", "ee");
        }
        else if(word.endsWith("ed"))
        {
           String stem = replaceSuffix(word, "ed", "");
           if(containsVowel(stem))
           {
               word = replaceSuffix(word, "ed", "");
               success = true;
           }
               

        }
        else if(word.endsWith("ing"))
        {
           String stem = replaceSuffix(word, "ing", "");
           if(containsVowel(stem))
           {
               word = replaceSuffix(word, "ing", "");
               success = true;
           }
        }
        
        if(success)
        {
            if(word.endsWith("at"))
            {
                return replaceSuffix(word, "at", "ate");   
            }
            else if(word.endsWith("bl"))
            {
                return replaceSuffix(word, "bl", "ble");
            }
            else if(word.endsWith("iz"))
            {
                return replaceSuffix(word, "iz", "ize");
            }
            else if(this.endsWithDoubleConsonant(word, new char[] {'l', 's', 'z'}))
            {
                return word.substring(0, word.length()-1);
            }
            else if(getMeasure(word) == 1 && endsWithCVC(word))
            {
                return word + "e";
            }
        }
        
        return word;
    }
    
    private String performStep1c(String word)
    {
        if(word.endsWith("y"))
        {
            String stem = replaceSuffix(word, "y", "");
            if(containsVowel(stem))
                return replaceSuffix(word, "y", "i");
        }
        
        return word;
    }
    
    private String performStep2(String word)
    {
        if(word.endsWith("ational"))
        {
            String stem = replaceSuffix(word, "ational", "");
            if(getMeasure(stem) > 0)
            {
                return replaceSuffix(word, "ational", "ate");
            }            
        }
        else if(word.endsWith("tional"))
        {
            String stem = replaceSuffix(word, "tional", "");
            if(getMeasure(stem) > 0)
            {
                return replaceSuffix(word, "tional", "tion");
            }
        }
        else if(word.endsWith("enci"))
        {
            String stem = replaceSuffix(word, "enci", "");
            if(getMeasure(stem) > 0)
            {
                return replaceSuffix(word, "enci", "ence");
            }
        }
        else if(word.endsWith("anci"))
        {
            String stem = replaceSuffix(word, "anci", "");
            if(getMeasure(stem) > 0)
            {
                return replaceSuffix(word, "anci", "ance");
            }
        }
        else if(word.endsWith("izer"))
        {
            String stem = replaceSuffix(word, "izer", "");
            if(getMeasure(stem) > 0)
            {
                return replaceSuffix(word, "izer", "ize");
            }
        }
        else if(word.endsWith("bli"))
        {
            String stem = replaceSuffix(word, "bli", "");
            if(getMeasure(stem) > 0)
            {
                return replaceSuffix(word, "bli", "ble");
            }
        }
        else if(word.endsWith("alli"))
        {
            String stem = replaceSuffix(word, "alli", "");
            if(getMeasure(stem) > 0)
            {
                return replaceSuffix(word, "alli", "al");
            }
        }
        else if(word.endsWith("entli"))
        {
            String stem = replaceSuffix(word, "entli", "");
            if(getMeasure(stem) > 0)
            {
                return replaceSuffix(word, "entli", "ent");
            }
        }
        else if(word.endsWith("eli"))
        {
            String stem = replaceSuffix(word, "eli", "");
            if(getMeasure(stem) > 0)
            {
                return replaceSuffix(word, "eli", "e");
            }
        }
        else if(word.endsWith("ousli"))
        {
            String stem = replaceSuffix(word, "ousli", "");
            if(getMeasure(stem) > 0)
            {
                return replaceSuffix(word, "ousli", "ous");
            }
        }
        else if(word.endsWith("ization"))
        {
            String stem = replaceSuffix(word, "ization", "");
            if(getMeasure(stem) > 0)
            {
                return replaceSuffix(word, "ization", "ize");
            }
        }
        else if(word.endsWith("ation"))
        {
            String stem = replaceSuffix(word, "ation", "");
            if(getMeasure(stem) > 0)
            {
                return replaceSuffix(word, "ation", "ate");
            }
        }
        else if(word.endsWith("ator"))
        {
            String stem = replaceSuffix(word, "ator", "");
            if(getMeasure(stem) > 0)
            {
                return replaceSuffix(word, "ator", "ate");
            }
        }
        else if(word.endsWith("alism"))
        {
            String stem = replaceSuffix(word, "alism", "");
            if(getMeasure(stem) > 0)
            {
                return replaceSuffix(word, "alism", "al");
            }
        }
        else if(word.endsWith("iveness"))
        {
            String stem = replaceSuffix(word, "iveness", "");
            if(getMeasure(stem) > 0)
            {
                return replaceSuffix(word, "iveness", "ive");
            }
        }
        else if(word.endsWith("fulness"))
        {
            String stem = replaceSuffix(word, "fulness", "");
            if(getMeasure(stem) > 0)
            {
                return replaceSuffix(word, "fulness", "ful");
            }
        }
        else if(word.endsWith("ousness"))
        {
            String stem = replaceSuffix(word, "ousness", "");
            if(getMeasure(stem) > 0)
            {
                return replaceSuffix(word, "ousness", "ous");
            }
        }
        else if(word.endsWith("aliti"))
        {
            String stem = replaceSuffix(word, "aliti", "");
            if(getMeasure(stem) > 0)
            {
                return replaceSuffix(word, "aliti", "al");
            }
        }
        else if(word.endsWith("iviti"))
        {
            String stem = replaceSuffix(word, "iviti", "");
            if(getMeasure(stem) > 0)
            {
                return replaceSuffix(word, "iviti", "ive");
            }
        }
        else if(word.endsWith("biliti"))
        {
            String stem = replaceSuffix(word, "biliti", "");
            if(getMeasure(stem) > 0)
            {
                return replaceSuffix(word, "biliti", "ble");
            }
        }
        else if(word.endsWith("logi"))
        {
            String stem = replaceSuffix(word, "logi", "");
            if(getMeasure(stem) > 0)
            {
                return replaceSuffix(word, "logi", "log");
            }
        }
        
        return word;
    }
    
    private String performStep3(String word)
    {
        if(word.endsWith("icate"))
        {
            String stem = replaceSuffix(word, "icate", "");
            if(getMeasure(stem) > 0)
            {
                return replaceSuffix(word, "icate", "ic");
            }            
        }
        else if(word.endsWith("ative"))
        {
            String stem = replaceSuffix(word, "ative", "");
            if(getMeasure(stem) > 0)
            {
                return replaceSuffix(word, "ative", "");
            }
        }
        else if(word.endsWith("alize"))
        {
            String stem = replaceSuffix(word, "alize", "");
            if(getMeasure(stem) > 0)
            {
                return replaceSuffix(word, "alize", "al");
            }
        }
        else if(word.endsWith("iciti"))
        {
            String stem = replaceSuffix(word, "iciti", "");
            if(getMeasure(stem) > 0)
            {
                return replaceSuffix(word, "iciti", "ic");
            }
        }
        else if(word.endsWith("ical"))
        {
            String stem = replaceSuffix(word, "ical", "");
            if(getMeasure(stem) > 0)
            {
                return replaceSuffix(word, "ical", "ic");
            }
        }
        else if(word.endsWith("ful"))
        {
            String stem = replaceSuffix(word, "ful", "");
            if(getMeasure(stem) > 0)
            {
                return replaceSuffix(word, "ful", "");
            }
        }  
        else if(word.endsWith("ness"))
        {
            String stem = replaceSuffix(word, "ness", "");
            if(getMeasure(stem) > 0)
            {
                return replaceSuffix(word, "ness", "");
            }
        }
        
        return word;
    }
    
    private String performStep4(String word)
    {
        if(word.endsWith("al"))
        {
            String stem = replaceSuffix(word, "al", "");
            if(getMeasure(stem) > 1)
            {
                return replaceSuffix(word, "al", "");
            }            
        }
        else if(word.endsWith("ance"))
        {
            String stem = replaceSuffix(word, "ance", "");
            if(getMeasure(stem) > 1)
            {
                return replaceSuffix(word, "ance", "");
            }
        }
        else if(word.endsWith("ence"))
        {
            String stem = replaceSuffix(word, "ence", "");
            if(getMeasure(stem) > 1)
            {
                return replaceSuffix(word, "ence", "");
            }
        }
        else if(word.endsWith("er"))
        {
            String stem = replaceSuffix(word, "er", "");
            if(getMeasure(stem) > 1)
            {
                return replaceSuffix(word, "er", "");
            }
        }
        else if(word.endsWith("ic"))
        {
            String stem = replaceSuffix(word, "ic", "");
            if(getMeasure(stem) > 1)
            {
                return replaceSuffix(word, "ic", "");
            }
        }
        else if(word.endsWith("able"))
        {
            String stem = replaceSuffix(word, "able", "");
            if(getMeasure(stem) > 1)
            {
                return replaceSuffix(word, "able", "");
            }
        }
        else if(word.endsWith("ible"))
        {
            String stem = replaceSuffix(word, "ible", "");
            if(getMeasure(stem) > 1)
            {
                return replaceSuffix(word, "ible", "");
            }
        }
        else if(word.endsWith("ant"))
        {
            String stem = replaceSuffix(word, "ant", "");
            if(getMeasure(stem) > 1)
            {
                return replaceSuffix(word, "ant", "");
            }
        }
        else if(word.endsWith("ement"))
        {
            String stem = replaceSuffix(word, "ement", "");
            if(getMeasure(stem) > 1)
            {
                return replaceSuffix(word, "ement", "");
            }
        }
        else if(word.endsWith("ment"))
        {
            String stem = replaceSuffix(word, "ment", "");
            if(getMeasure(stem) > 1)
            {
                return replaceSuffix(word, "ment", "");
            }
        }
        else if(word.endsWith("ent"))
        {
            String stem = replaceSuffix(word, "ent", "");
            if(getMeasure(stem) > 1)
            {
                return replaceSuffix(word, "ent", "");
            }
        }
        else if(word.endsWith("ion"))
        {
            String stem = replaceSuffix(word, "ion", "");
            char lastLetter = stem.charAt(stem.length()-1);
            if(getMeasure(stem) > 1 && (lastLetter == 's' || lastLetter == 't'))
            {
                return replaceSuffix(word, "ion", "");
            }
        }
        else if(word.endsWith("ou"))
        {
            String stem = replaceSuffix(word, "ou", "");
            if(getMeasure(stem) > 1)
            {
                return replaceSuffix(word, "ou", "");
            }
        }
        else if(word.endsWith("ism"))
        {
            String stem = replaceSuffix(word, "ism", "");
            if(getMeasure(stem) > 1)
            {
                return replaceSuffix(word, "ism", "");
            }
        }
        else if(word.endsWith("ate"))
        {
            String stem = replaceSuffix(word, "ate", "");
            if(getMeasure(stem) > 1)
            {
                return replaceSuffix(word, "ate", "");
            }
        }
        else if(word.endsWith("iti"))
        {
            String stem = replaceSuffix(word, "iti", "");
            if(getMeasure(stem) > 1)
            {
                return replaceSuffix(word, "iti", "");
            }
        }
        else if(word.endsWith("ous"))
        {
            String stem = replaceSuffix(word, "ous", "");
            if(getMeasure(stem) > 1)
            {
                return replaceSuffix(word, "ous", "");
            }
        }
        else if(word.endsWith("ive"))
        {
            String stem = replaceSuffix(word, "ive", "");
            if(getMeasure(stem) > 1)
            {
                return replaceSuffix(word, "ive", "");
            }
        }
        else if(word.endsWith("ize"))
        {
            String stem = replaceSuffix(word, "ize", "");
            if(getMeasure(stem) > 1)
            {
                return replaceSuffix(word, "ize", "");
            }
        }    
        return word;
    }
    
    private String performStep5a(String word)
    {
        if(word.endsWith("e"))
        {
            String stem = replaceSuffix(word, "e", "");
            if(getMeasure(stem) > 1)
            {
                return replaceSuffix(word, "e", "");
            }
            else if(getMeasure(stem) ==  1 && !endsWithCVC(stem))
            {
                return replaceSuffix(word, "e", "");
            }
        }
        
        return word;
    }
    
    private String performStep5b(String word)
    {
        boolean endsWithDoubleConsonant = endsWithDoubleConsonant(word, new char[] {});
        char lastLetter = word.charAt(word.length()-1);
        if(getMeasure(word) > 1 && endsWithDoubleConsonant && lastLetter == 'l')
        {
            return word.substring(0, word.length()-1);
        }

        return word;
    }
    
    private boolean isVowel(char c)
    {
        return isVowel(' ', c);
    }
    
    private boolean isVowel(char p, char c)
    {
        // y is first character
        if(p == ' ' && (c == 'y' || c == 'Y'))
            return false;
        
        boolean pIsVowel = false;
        for(int i = 0; i < vowels.length; i++)
        {
            if(c == vowels[i])
                return true;
            
            if(p == vowels[i])
                pIsVowel = true;
        }
        
        // cover 'y' special case
        return (c == 'y' || c == 'Y') && !pIsVowel;
    }
    
    private boolean containsVowel(String stem)
    {
        for(int i = 0; i < stem.length(); i++)
        {
            if(i == 0)
            {
                if(isVowel(stem.charAt(i)))
                    return true;
            }
            else
            {
                if(isVowel(stem.charAt(i-1), stem.charAt(i)))
                    return true;
            }
        }
        
        return false;
    }
    
    private boolean endsWithDoubleConsonant(String stem, char[] exceptions)
    {
        char lastLetter =  stem.charAt(stem.length()-1);
        char secondLastLetter =  stem.charAt(stem.length()-2);
        
        boolean lastLetterVowel = isVowel(secondLastLetter, lastLetter);
        if(!lastLetterVowel && lastLetter == secondLastLetter)
        {
            for(int i = 0; i < exceptions.length; i++)
            {
                if(lastLetter == exceptions[i])
                    return false;
            }
            
            return true;
        }
        
        return false;
    }
    
    private boolean endsWithCVC(String stem)
    {
        if(stem.length() < 3)
            return false;
        
        char lastLetter = stem.charAt(stem.length()-1);
        char secondLastLetter = stem.charAt(stem.length()-2);
        char thirdLastLetter = stem.charAt(stem.length()-3);
        
        boolean lastLetterVowel = isVowel(secondLastLetter, lastLetter);
        boolean secondLastLetterVowel = isVowel(thirdLastLetter, secondLastLetter);
        boolean thirdLastLetterVowel;
        
        if(stem.length() == 3)
        {
            thirdLastLetterVowel = isVowel(thirdLastLetter);
        }
        else
        {
            char fourthLastLetter = stem.charAt(stem.length()-4);
            thirdLastLetterVowel = isVowel(fourthLastLetter, thirdLastLetter);
        }
        
        if(!thirdLastLetterVowel && secondLastLetterVowel && !lastLetterVowel)
        {
            if(lastLetter == 'w' || lastLetter == 'x' || lastLetter == 'y')
                return false;
            
            return true;
        }
        
        return false;
    }
    
    private String replaceSuffix(String word, String suffix, String replacement)
    {
        if(!word.endsWith(suffix))
            return word;
        
        return word.substring(0, word.length() - suffix.length()) + replacement;
    }
    
    private int getMeasure(String stem)
    {
        String translation = translateWord(stem);
        StringBuilder translationSB = new StringBuilder(translation);

        if(translation.length() < 2)
            return 0;
        
        char firstRun = translation.charAt(0);
        char lastRun = translation.charAt(translation.length()-1);
                
        if(firstRun == 'C' && lastRun == 'V')
        {
            // remove first run and last run
            translationSB.deleteCharAt(0);
            translationSB.deleteCharAt(translationSB.length()-1);
        }
        else if(firstRun == 'C' && lastRun == 'C')
        {
            // remove first run
            translationSB.deleteCharAt(0);
        }
        else if(firstRun == 'V' && lastRun == 'V')
        {
            // remove last run
            translationSB.deleteCharAt(translationSB.length()-1);
        }
        else
        {
            // remove nothing
        }
        
        // return number of VC pairs after removal
        return translationSB.length()/2;
    }
    
    private String translateWord(String word)
    {    
        if(word.length() == 0)
            return "";
        
        if(word.length() == 1)
        {
            if(isVowel(word.charAt(0)))
                return "V";
            else
                return "C";
        }
        
        boolean runIsVowel = isVowel(word.charAt(0));
        String translation = "";
        
        for(int i = 0; i < word.length(); i++)
        {
            boolean currentIsVowel;
            if(i == 0)
                currentIsVowel = isVowel(word.charAt(i));
            else
                currentIsVowel = isVowel(word.charAt(i-1), word.charAt(i));
                        
            if(currentIsVowel != runIsVowel)
            {
                if(runIsVowel)
                {
                    translation = translation + "V";
                }  
                else
                {
                    translation = translation + "C";
                }
                    
                runIsVowel = currentIsVowel;
            }
        }
        
        char last = word.charAt(word.length()-1);
        char secondLast = word.charAt(word.length()-2);
        
        if(isVowel(secondLast, last))
        {
            translation = translation + "V";
        }
        else
        {
            translation = translation + "C";
        }
            
        return translation;
    }
}
