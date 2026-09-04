package Week01.assignment_problems;
public class WordProfiler {
    static void WordClassifier(String movie)
    {
        int s=0,m=0,l=0;
        String[] words = movie.replaceAll("[^a-zA-Z0-9 ]", " ").trim().split("\\s+");
        for(String word:words)
          {
            if(word.length()>=1 && word.length()<=4)
            
                s++;
            else if(word.length()>=5 && word.length()<=8)
                m++;
            else
                l++;
            }
            System.out.printf("SHORT: %d\n",s);
            System.out.printf("MEDIUM: %d\n",m);
            System.out.printf("LONG: %d\n",l);
    }
    public static void main(String[] args) {
        String movie="\"Wow!!! What a masterclass movie... I've watched it 2 times already! The director's vision (especially in Act-2) was mind-blowing, and the background score? Absolutely haunting!! A solid 9/10 score from my side; highly recommended for true cinema lovers.\"\n";
        WordClassifier(movie);
        
    }
    
}
