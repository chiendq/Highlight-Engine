import java.util.Arrays;

public class Match implements Comparable<Match>{
    private Doc d;
    private Word w;
    private int freq;
    private int firstIndex;
    
    public Match(Doc d, Word w, int freq, int firstIndex){
        this.d = d;
        this.w = w;
        this.freq = freq;
        this.firstIndex = firstIndex;
    }

    public int getFreq(){
        return freq;
    }

    public int getFirstIndex(){
        return firstIndex;
    }

    public Word getWord(){
        return w;
    }

    @Override
    public int compareTo(Match o) {
        if(getFirstIndex() > o.getFirstIndex())
            return 1;
        else if (getFirstIndex() < o.getFirstIndex())
            return -1;
        return 0;
    }
}
