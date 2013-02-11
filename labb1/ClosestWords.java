/* Labb 2 i DD1352 Algoritmer, datastrukturer och komplexitet    */
/* Se labbanvisning under kurssidan http://www.csc.kth.se/DD1352 */
/* Ursprunglig författare: Viggo Kann KTH viggo@nada.kth.se      */
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.Arrays;

public class ClosestWords {
  LinkedList<String> closestWords = null;
  HashMap<String,Integer> memo = new HashMap<String,Integer>();
  HashMap<String,Integer> memo4 = new HashMap<String,Integer>();
  HashList memo3 = new HashList(30*(pow(30,4)+pow(30,3)+pow(30,2)+32));
  int closestDistance = -1;
  int iterations = 0;

  int partDist(String w1, String w2, int w1len, int w2len) {
    iterations++;
    //if(w1.substring(w1len).length()+w2.substring(w2len).length() == 2){
    //    if(memo3.get(w1.substring(w1len)+"#"+w2.substring(w2len)) != -1){
    //        return memo3.get(w1.substring(w1len)+"#"+w2.substring(w2len));
    //    }
    //    //if(memo4.get(w1.substring(w1len)+"#"+w2.substring(w2len)) !=  null){
    //    //    return memo4.get(w1.substring(w1len)+"#"+w2.substring(w2len));
    //    //}
    //}
    if(memo.get(w1.substring(w1len)+"#"+w2.substring(w2len))!=null){    //NEW
        return memo.get(w1.substring(w1len)+"#"+w2.substring(w2len));   //NEW
    }                                                                   //NEW
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

    if(w1.substring(w1len).length()+w2.substring(w2len).length() == 2){
        memo3.put(w1.substring(w1len)+"#"+w2.substring(w2len),res);
        memo4.put(w1.substring(w1len)+"#"+w2.substring(w2len),res);
    }
    memo.put(w1.substring(w1len)+"#"+w2.substring(w2len),res);          //NEW
    return res;
  }

  int Distance(String w1, String w2) {
    memo.clear();                                               //NEW
    return partDist(w1, w2, w1.length(), w2.length());
  }

  public ClosestWords(String w, List<String> wordList) {
    for (String s : wordList) {
      int dist = Distance(w, s);
       //System.out.println("d(" + w + "," + s + ")=" + dist);
      if (dist < closestDistance || closestDistance == -1) {
        closestDistance = dist;
        closestWords = new LinkedList<String>();
        closestWords.add(s);
      }
      else if (dist == closestDistance)
        closestWords.add(s);
    }
  }

  int getMinDistance() {
    return closestDistance;
  }

  List<String> getClosestWords() {
    return closestWords;
  }

  class HashList{
    int[] values;

    public HashList(int sizeIn){
        values = new int[sizeIn];
        Arrays.fill(values,-1);
    }

    public void put(String keyIn, int valueIn){
        values[hashFunction(keyIn)] = valueIn;
    }
    public int get(String keyIn){
        return values[hashFunction(keyIn)];
    }

    public int hashFunction(String stringIn){
       // System.out.println("StringIn: "+stringIn);
       // System.out.println("length: "+stringIn.length());
        int length = stringIn.length();
        int value = 0;
        for(int i=0;i<length;i++){
            value += charValue(stringIn.charAt(i)) * pow(30,length-i);
        }
        //System.out.println("VALUE: "+value);
        return value;
    }

    public int charValue(char charIn){
        switch(charIn){
            case 'a': return 0;
            case 'b': return 1;
            case 'c': return 2;
            case 'd': return 3;
            case 'e': return 4;
            case 'f': return 5;
            case 'g': return 6;
            case 'h': return 7;
            case 'i': return 8;
            case 'j': return 9;
            case 'k': return 10;
            case 'l': return 11;
            case 'm': return 12;
            case 'n': return 13;
            case 'o': return 14;
            case 'p': return 15;
            case 'q': return 16;
            case 'r': return 17;
            case 's': return 18;
            case 't': return 19;
            case 'u': return 20;
            case 'v': return 21;
            case 'w': return 22;
            case 'x': return 23;
            case 'y': return 24;
            case 'z': return 25;
            case 'å': return 26;
            case 'ä': return 27;
            case 'ö': return 28;
            case '#': return 29;
        }
        System.out.println("charValue: ERRORERRORERRRORERRORRERORROREROERO");
        return 0;
    }
    }
    public Integer pow(int a,int b){
        int output = 1;
        if(b==0){ return 1; }

        for(int i=0;i<b;i++){
            output = output*a;
        }
        return output;
    }
  
}
