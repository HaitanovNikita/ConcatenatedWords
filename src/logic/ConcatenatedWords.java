package logic;

import java.io.*;
import java.util.*;


public class ConcatenatedWords {

    protected static int locationNumber = 90;
    private static int maxLength = Integer.MIN_VALUE;

    protected static ArrayList<String> sortWords(ArrayList<String> validWords) {
        validWords.sort((str1, str2) -> str1.length() == str2.length() ? 0 : str1.length() < str2.length() ? 1 : -1);
        return validWords;
    }


    protected boolean contains(String word, Node root) {
       if(word!=null&&word.isEmpty()==false&&root!=null){
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            if (node.neiteam[word.charAt(i) - locationNumber] == null) return false;
            node = node.neiteam[word.charAt(i) - locationNumber];
        }
        return node.markWord;
       }else  return false;

    }


    protected void markWord(String word, Node root, boolean mark) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            node = node.neiteam[word.charAt(i) - locationNumber];
        }
        node.markWord = mark;
    }


    protected static void writeFile(String pathToFile, ArrayList<String> arrayListResultWords) {
        if (arrayListResultWords == null && arrayListResultWords.size() == 0)
            throw new NullPointerException("ArrayList is empty!!!");
        if (pathToFile == null && pathToFile.isEmpty() == true)
            throw new NullPointerException("Path to file not valid!!!");
        try {
            File file = new File(pathToFile);
            if (!file.exists())
                file.createNewFile();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
            bufferedWriter.write("Result: \n".concat("Words count: "+arrayListResultWords+"\n"));
            for (String word : arrayListResultWords) {
                bufferedWriter.write(word + "\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
}

    public static HashSet<String> readFile(String filePath) throws Exception {
        HashSet<String> hashSetString = new HashSet<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String s = br.readLine();
                while (s != null) {
                    maxLength = Math.max(maxLength, s.length());
                    if (s.isEmpty()==false)
                        hashSetString.add(s.trim());
                    s = br.readLine();
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashSetString;
    }


    public static int getMaxLength() {
        return maxLength;
    }

}



