package Utils;

import java.util.HashMap;

public class StringWorker {

    public static HashMap<String,Integer> getWordsStatistics(String text){
        String[] words = text.split("['\\\\/ ,.!?\";:()\n\r\t\\[\\]]");

        HashMap<String,Integer> result = new HashMap<>();
        for(int i = 0; i<words.length;i++){
            if(result.containsKey(words[i])){
                result.put(words[i],result.get(words[i])+1);
            }else{
                result.put(words[i],1);
            }
        }
        return result;
    }
}
