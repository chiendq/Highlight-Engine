package main.java;

import java.util.ArrayList;
import java.util.List;

public class Doc {
    private List<Word> title;
    private List<Word> body;
    public Doc(String content){
        String[] arr = content.split("\n");
        String titleString = arr[0];
        String bodyString = arr[1];
        title = new ArrayList<>();
        body = new ArrayList<>();

        for (String t : titleString.split(" ")) {
            title.add(Word.createWord(t));
        } 
        for (String b : bodyString.split(" ")) {
            body.add(Word.createWord(b));
        } 
    }

    public List<Word> getTitle(){
        return title;
    }

    public List<Word> getBody(){
        return body;
    }

    @Override
    public boolean equals(Object o) {
        boolean flag = true;
        for (int i = 0; i < title.size(); i++) {
            if(!title.get(i).equals(((Doc) o).getTitle().get(i))){
                flag = false;
                return flag;
            }
        }
        for (int i = 0; i < body.size(); i++) {
            if(!body.get(i).equals(((Doc) o).getBody().get(i))){
                flag = false;
                return flag;
            }
        }
        return true;
    }
}
