package logic;

import java.util.Set;

import static logic.ConcatenatedWords.locationNumber;

public class Node {

    Node[] neiteam = new Node[55];
    boolean markWord;

    public Node() {
    }

    public static Node initNodes(Set<String> words) {
        Node root = new Node();
        for (String word : words) {
            char[] chars = word.toCharArray();
            Node buildRootNode = root;
            for (int i = 0; i < chars.length; i++) {
                buildRootNode = buildRootNode.neiteam[chars[i] - locationNumber] == null ? buildRootNode.neiteam[chars[i] - locationNumber] = new Node() : buildRootNode.neiteam[chars[i] - locationNumber];
            }
            buildRootNode.markWord = true;
        }
        return root;
    }
}
