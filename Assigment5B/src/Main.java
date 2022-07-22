import java.util.*;
import java.io.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static boolean IsTxt(String FileName)
    {
        return(FileName.endsWith(".txt"));
    }
    public static  void HandleFiles(String Path, Map<String,Integer> MainOne)
    {
        File Direcotry=new File(Path);
        String[] FileNames=Direcotry.list();
        ExecutorService ex= Executors.newCachedThreadPool();
        for(String name: FileNames)
        {
            if(IsTxt(name))
            {
                ReadDocAndUpdateMap Task1 =new ReadDocAndUpdateMap(MainOne,Path+"\\"+name);
                 ex.execute(Task1);

            }
            if(new File(Path+"\\"+name).isDirectory())
            {
                HandleFiles(Path+"\\"+name,MainOne);
            }

        }
        ex.shutdown();


        while(!ex.isTerminated()) // unlearned function- we should need to let main.thread to wait to the pool to finish before continue with the main
        {
        } // last fix

        return;
    }
    public static void main(String[] args)
    {
        String Path="C:\\Users\\pc\\Downloads\\Assigment5A";
        Map<String,Integer> MainOne=new HashMap<String,Integer>();
        HandleFiles(Path,MainOne);

        for (String Value : MainOne.keySet())
        {
            System.out.println("the word is :"+ Value+" the amount is :"+MainOne.get(Value));
        }
        System.out.println("i want to see");
    }
}
