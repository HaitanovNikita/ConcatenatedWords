package logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;

public class Utilites {

    private static String pathFileDirectory = "C:\\Users\\User\\IdeaProjects\\IntegrityVisionConcatenatedWords\\files\\";
    private static String pathToFileStartData = pathFileDirectory.concat("words.txt");
    private static String pathToFileResultData = pathFileDirectory.concat("result.txt");
    private static int lengthMaxWord = Integer.MIN_VALUE;

    public static String getPathToFileStartData() {
        return pathToFileStartData;
    }

    public static void setPathToFileStartData(String pathToFileStartData) {
        Utilites.pathToFileStartData = pathToFileStartData;
    }

    public static String getPathToFileResultData() {
        return pathToFileResultData;
    }

    public static void setPathToFileResultData(String pathToFileResultData) {
        Utilites.pathToFileResultData = pathToFileResultData;
    }

    public static void colorPrintln(String dataForPrint, int color30To37){
        /*30 - белый, 31 - красный, 32 - темно-желтый, 33 - желтый, 34 - голубой, 35 - лиловый, 36 - бирюзовый, 37 - серый*/
        System.out.println((char)27 + "["+color30To37+"m" + dataForPrint);
    }

    public static HashSet<String> readFile(String filePath) throws Exception {
        HashSet<String> hashSetString = new HashSet<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String s = br.readLine();
            while (s != null) {
                lengthMaxWord = Math.max(lengthMaxWord, s.length());
                if (s.isEmpty()==false)
                    hashSetString.add(s.trim());
                s = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashSetString;
    }

    public static int getLengthMaxWord() {
        return lengthMaxWord;
    }
}
