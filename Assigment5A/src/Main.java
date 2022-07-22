import java.util.*;
import java.io.*;
    public class Main {
        public static boolean IsTxt(String FileName)
        {
            return(FileName.endsWith(".txt"));
        }
        public static  void HandleFiles(String Path, Map<String,Integer> MainOne)
        {
            File Direcotry=new File(Path);
            String[] FileNames=Direcotry.list();
            Thread[] arr=new Thread[FileNames.length];
            int i=0;
            for(String name: FileNames)
            {
                if(IsTxt(name))
                {
                    ReadDocAndUpdateMap Task1 =new ReadDocAndUpdateMap(MainOne,Path+"\\"+name);
                    arr[i]=new Thread(Task1);
                    arr[i].start();
                    try{
                        arr[i].join();

                    }catch (InterruptedException e)
                    {
                        System.out.println("you interrupt me");
                    }
                    i++;

                }
                if(new File(Path+"\\"+name).isDirectory())
                {
                    HandleFiles(Path+"\\"+name,MainOne);
                }

            }

            return;
        }
        public static void main(String[] args)
        {
            String Path="C:\\Users\\pc\\Downloads\\tryAssigment5Java";
            Map<String,Integer> MainOne=new HashMap<String,Integer>();
            HandleFiles(Path,MainOne);
            System.out.println("stam");
            for (String Value : MainOne.keySet())
            {
                System.out.println("the word is :"+ Value+" the amount is :"+MainOne.get(Value));
            }
        }
    }


