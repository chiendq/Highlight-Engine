package main.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Query {
    private List<Word> keyWords  = new ArrayList<>();;

    public Query(String searchPhrase){
        for (String p : searchPhrase.split(" ")) {
            if(Word.createWord(p).isKeyword()) 
                keyWords.add(Word.createWord(p));
        }
    }

    public List<Word> getKeywords(){
        return keyWords;
    }

    public List<Match> matchAgainst(Doc d){
        List<Match> matchAgainstList = new ArrayList<>();
        List<Word> title = d.getTitle();
        List<Word> body = d.getBody();

        for (Word keyWord : keyWords) {
            int freq = 0;
            for (Word word : title) {
                if(keyWord.equals(word)) freq++;
            }
            for (Word word : body) {
                if(keyWord.equals(word)) freq++;
            }
            if(title.contains(keyWord) ){
                matchAgainstList.add(new Match(d, keyWord, freq, title.indexOf(keyWord))) ;
            }else if(body.contains(keyWord)){
                matchAgainstList.add(new Match(d, keyWord, freq, body.indexOf(keyWord))) ;

            }

        }
        Collections.sort(matchAgainstList,(m1,m2)->m1.compareTo(m2));
        return matchAgainstList;
    }
}
