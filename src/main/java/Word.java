import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Word {

    public static Set<String> stopWords = new LinkedHashSet<String>();
    
    private String word;

    private Word(String word){
        this.word = word;
    }
    
    public static Word createWord(String rawText){
        return new Word(rawText);
    }

    public void setWord(String word){
        this.word = word;
    }

    public boolean isKeyword(){
        String checkWord = word;
        Pattern textPartPattern = Pattern.compile("([a-zA-Z0-9[-]]+)");
        Matcher matcher = textPartPattern.matcher(word);
        
        if(matcher.find()) 
            checkWord = matcher.group(0);

        for (String sw : stopWords) 
            if(checkWord.equalsIgnoreCase(sw))   
                return false;

        Pattern wordPattern = Pattern.compile("^[a-zA-Z[-]]+$");
        return wordPattern.matcher(checkWord).find() ? true : false ;
    }

    public  String getText(){
        String checkWord = word;
        Pattern textPartPattern = Pattern.compile("([a-zA-Z0-9[-]]+)");
        Matcher matcher = textPartPattern.matcher(word);

        if(matcher.find()) 
            checkWord = matcher.group(0);

        Pattern wordPattern = Pattern.compile("^[a-zA-Z[-]]+$");
        return wordPattern.matcher(checkWord).find() ? checkWord : word ;
    }

    public String getPrefix(){
        return isKeyword() ? word.substring(0,word.indexOf(getText())) : "" ;
    }

    private  String getPrefix(int a){
        return "";
    }

    public String getSuffix(){
        return isKeyword() ? word.substring(getPrefix().length() + getText().length()) : "";
    }

    public static boolean loadStopWords(String fileName){
        try {
            Scanner readFile = new Scanner(new File(fileName));
            while(readFile.hasNextLine()){
                String line = readFile.nextLine();
                stopWords.add(line);
            }
            readFile.close();
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    public boolean equals(Object o){
        Word temp = (Word) o;
        return getText().equalsIgnoreCase(temp.getText());
    }

    @Override
    public String toString() {
        return word;
    }

    public static void main(String[] args) {
        System.out.println(Word.createWord("John-'s-best").getText());
    }
}
