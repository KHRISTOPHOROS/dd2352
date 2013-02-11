import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;

public class Main{
    static int iterations = 0;
    static HashMap<String,Integer> memo = new HashMap<String,Integer>();
    public static void main(String args[]) throws IOException{

        String w1 = "labs";
        String w2 = "blad";

        System.out.println("X  Y  RES");
        for(int x=1;x<5;x++){
            for(int y=1;y<5;y++){
                System.out.println(x+"  "+y+"   "+partDist(w1,w2,x,y));

            }
        }
        System.out.println("ITERATIONS: "+iterations);
    }

    public static int partDist(String w1, String w2, int w1len, int w2len) {
        //HashMap<Integer,String> memo = new HashMap<Integer,String>();
        iterations++;
        if(memo.get(w1.substring(w1len)+"#"+w2.substring(w2len))!=null){
            return memo.get(w1.substring(w1len)+"#"+w2.substring(w2len));
        }

        if (w1len == 0)
          return w2len;
        if (w2len == 0)
          return w1len;
        int res = partDist(w1, w2, w1len - 1, w2len - 1) + (w1.charAt(w1len - 1) == w2.charAt(w2len - 1) ? 0 : 1);
        int addLetter = partDist(w1, w2, w1len - 1, w2len) + 1;
        if (addLetter < res)
          res = addLetter;
        int deleteLetter = partDist(w1, w2, w1len, w2len - 1) + 1;
        if (deleteLetter < res)
          res = deleteLetter;

        memo.put(w1.substring(w1len)+"#"+w2.substring(w2len),res);
        return res;
      }

}
