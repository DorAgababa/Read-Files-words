import java.util.*;
import java.io.*;
public class ReadDocAndUpdateMap implements Runnable{
    public static Map<String,Integer> WordHash;
    public String PathDoc;
    public ReadDocAndUpdateMap( Map<String,Integer> wordhash, String pathdoc)
    {
     WordHash = wordhash;
     PathDoc = pathdoc;
    }
    public static Map<String,Integer> GetWordHash()
    {


        return WordHash;
    }
    @Override
    public  void run() {
        try {

            Scanner myReader = new Scanner(new File(PathDoc));
            String tmpWord;
            while (myReader.hasNext())
            {
                tmpWord=myReader.next().toLowerCase();
                synchronized (WordHash)
                {
                    if(WordHash.containsKey(tmpWord))
                        WordHash.put(tmpWord,WordHash.get(tmpWord)+1);
                    else
                        WordHash.put(tmpWord,1);
                }

            }

        }catch (FileNotFoundException e){System.out.println("no such file");return;}
        System.out.println("Thread has ended");
    }
}
