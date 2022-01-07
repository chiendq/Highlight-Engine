package main.java;

import java.util.List;

public class Result implements Comparable<Result>{
    private int totalFrequency = 0;
    private double averageFirstIndex = 0;
    private Doc d;
    List<Match> matches;

    public Result(Doc d, List<Match> matches){
        this.matches = matches;
        this.d = d;
        int totalFirstIndex = 0;
        for (Match match : matches) {
            totalFrequency+=match.getFreq();
            totalFirstIndex+=match.getFirstIndex();
        }
        averageFirstIndex =  totalFirstIndex/matches.size();
    }

    public List<Match> getMatches(){
        return matches;
    }

    public Doc getDoc(){
        return d;
    }

    public int getTotalFrequency(){
        return totalFrequency;
    }

    public double getAverageFirstIndex(){
        return averageFirstIndex;
    }

    public String htmlHighlight(){
        StringBuilder html = new StringBuilder();
        html.append("<h3>");
        for (Word word : d.getTitle()){
            for (Match match : matches)  {
                String textPart = match.getWord().getText();
                if(word.getText().equalsIgnoreCase(textPart)){
                    word.setWord(word.getPrefix() + "<u>" + word.getText() + "</u>" + word.getSuffix());
                }
            }
        }
        d.getTitle().forEach(t -> html.append(t.toString() + " "));
        html.deleteCharAt(html.length()-1);
        html.append("</h3><p>");
        for (Word word : d.getBody()){
            for (Match match : matches)  {
                String textPart = match.getWord().getText();
                if(word.getText().equalsIgnoreCase(textPart)){
                    word.setWord(word.getPrefix() + "<b>" + word.getText() + "</b>" + word.getSuffix());
                }
            }
        }
        d.getBody().forEach(t -> html.append(t.toString() + " "));
        html.deleteCharAt(html.length()-1);
        html.append("</p>");
        return html.toString();
    }
    public int compareTo(Result o){ // B = o
        int matchesSizeComparer = getMatches().size(); //A
        int matchesSizeCompared = o.getMatches().size(); //B

        int totalFreqComparer = getTotalFrequency();//A
        int totalFreqCompared = o.getTotalFrequency();//B

        double averageFirstIndexComparer = getAverageFirstIndex(); // A
        double averageFirstIndexCompared = o.getAverageFirstIndex();//b

        if(matchesSizeComparer == matchesSizeCompared){
            if(totalFreqComparer == totalFreqCompared){
                if(averageFirstIndexComparer == averageFirstIndexCompared){
                    return 0;
                }else if(averageFirstIndexComparer < averageFirstIndexCompared){
                    return 1;
                }else{
                    return -1;
                }
            }else if(totalFreqComparer > totalFreqCompared){
                return 1;
            }else{
                return -1;
            }
        }else if(matchesSizeComparer > matchesSizeCompared){
            return 1;
        }
        return -1;
    }
}
