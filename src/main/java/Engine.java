package main.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Engine{
    private Doc[] docs ;

    public int loadDocs(String dirname) {
        File folder = new File(dirname);
        File[] files = folder.listFiles();
        docs = new Doc[files.length];
        for (int i = 0; i < files.length; i++) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(files[i]));
                docs[i] = new Doc(br.readLine() +"\n"+ br.readLine());
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return files.length;
    }

    public Doc[] getDocs(){
        return  docs;
    }

    public List<Result> search(Query q){
        List<Result> results = new ArrayList<>();
        for (Doc d : getDocs()) {
            if(q.matchAgainst(d).size() !=0){
                results.add(new Result(d, q.matchAgainst(d)));
            }
        }
        results.sort((r1,r2)-> r2.compareTo(r1));        
        return results;
    }

    public String htmlResult(List<Result> results){
        StringBuilder stringBuilder = new StringBuilder();
        for (Result result : results) {
            stringBuilder.append(result.htmlHighlight());
        }
        return stringBuilder.toString();
    }
}