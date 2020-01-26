package logic;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

import static logic.ConcatenatedWords.sortWords;
import static logic.ConcatenatedWords.writeFile;
import static logic.Utilites.*;

public class FormativeResult {

    private String absolutePathToFileStartData;
    private String absolutePathToFileResultData;
    private ConcatenatedWords concatenatedWords;
    private Set<String> words;
    private Node root;
    private ArrayList<String> validWords;


    public FormativeResult(String absolutePathToFileStartData, String absolutePathToFileResultData) {
        initNecessaryElem(absolutePathToFileStartData, absolutePathToFileResultData);
        wordProcessing();
        sortWords(validWords);
        printResult("Самое длинное слово в файле: ".concat(validWords.get(0).concat("\n следущее слово после него по длине: ".concat(validWords.get(1).concat("\n Общее количество составных слов: " + validWords.size())))));
    }

    private void initNecessaryElem(String absolutePathToFileStartData, String absolutePathToFileResultData) {
        this.absolutePathToFileStartData = checkInformationAboutFiles(absolutePathToFileStartData, getPathToFileStartData());
        this.absolutePathToFileResultData = checkInformationAboutFiles(absolutePathToFileResultData, getPathToFileResultData());
        concatenatedWords = new ConcatenatedWords();
        try {
            words = Utilites.readFile(this.absolutePathToFileStartData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        root = Node.initNodes(words);
        validWords = new ArrayList();
    }

    private void wordProcessing() {
        for (String word : words) {
            if (word == null || word.length() == 0) continue;
            concatenatedWords.markWord(word, root, false);
            boolean[] booleans = new boolean[word.length() + 1];
            booleans[0] = true;

            for (int i = 1; i <= word.length(); i++) {
                for (int j = 1; j <= i && j <= Utilites.getLengthMaxWord(); j++) {
                    if (booleans[i - j] != true)
                        continue;

                    String partWord = word.substring(i - j, i);
                    if (concatenatedWords.contains(partWord, root)) {
                        booleans[i] = true;
                        break;
                    }
                }
            }
            if (booleans[word.length()]) validWords.add(word);
            concatenatedWords.markWord(word, root, true);
        }
    }

    private String checkInformationAboutFiles(String absolutePathToFile, String defaultPathToFile) {
        String message = "Invalid path to the file,("+absolutePathToFile+")\n by default there will be such a path to the file: ".concat(defaultPathToFile);
        if (absolutePathToFile == null && absolutePathToFile.isEmpty() == true) {
            colorPrintln(message, 32);
            absolutePathToFile = defaultPathToFile;
        }
        File fl = new File(absolutePathToFile);
        if (((fl.exists() == true) && (fl.isFile() == true)) && (fl.canRead() == true | fl.canWrite() == true)) {
            colorPrintln("The file path is correct: " + absolutePathToFile, 34);
        } else {
            colorPrintln(message, 32);
            absolutePathToFile = defaultPathToFile;
        }
        return absolutePathToFile;
    }

    private void printResult(String result) {
        writeFile(absolutePathToFileResultData, validWords);
        colorPrintln(result, 33);
    }


}
