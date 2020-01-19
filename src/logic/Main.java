package logic;

import java.util.ArrayList;
import java.util.Set;

import static logic.ConcatenatedWords.*;

public class Main {
    public static void main(String[] args) {

        try {
            String path = "C:\\Users\\User\\IdeaProjects\\IntegrityVisionConcatenatedWords\\files\\";
            ConcatenatedWords concatenatedWords = new ConcatenatedWords();
            Set<String> words = concatenatedWords.readFile(path + "words.txt");
            Node root = Node.initNodes(words);
            ArrayList<String> validWords = new ArrayList();
            for (String word : words) {
                if (word == null || word.length() == 0) continue;
                concatenatedWords.markWord(word, root, false);
                boolean[] booleans = new boolean[word.length() + 1];
                booleans[0] = true;

                for (int i = 1; i <= word.length(); i++) {
                    for (int j = 1; j <= i && j <= ConcatenatedWords.getMaxLength(); j++) {
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

            sortWords(validWords);
            writeFile(path + "result.txt", validWords);
            System.out.println("Самое длинное слово в файле: "
                    .concat(validWords.get(0)
                    .concat("\n следущее слово после него по длине: "
                    .concat(validWords.get(1)
                    .concat("\n Общее количество составных слов: " + validWords.size())))));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
