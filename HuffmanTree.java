package huffmanHomework;
public class HuffmanTree {
    private Node root;
    public HuffmanTree(LinkedList list){
        Node tempRoot;
        while (true) {
            Node leftNode = list.poppingTheHead();
            Node rightNode = list.poppingTheHead();
            tempRoot = new Node(leftNode.character + rightNode.character, leftNode.frequency + rightNode.frequency);
            tempRoot.left = leftNode;
            tempRoot.right = rightNode;
            if (list.isEmpty())
                break;
            list.sortedInsert(tempRoot);
        }
        root = tempRoot;
    }

    public String decode(String encodedChar){
        String decodedChar = "";
        Node iterator = root;
        for (char c : encodedChar.toCharArray()){
            if (c == '0'){
                iterator = iterator.left;
            }
            else {
                iterator = iterator.right;
            }
            if (iterator.character.length() == 1){
                decodedChar += iterator.character;
                iterator = root;
            }
        }
        return decodedChar;
    }

    public String encode(String Char){
        String encodedCharacter = "";

        for (char c : Char.toCharArray()){
            String character = String.valueOf(c);

            Node iterator = root;
            while(iterator != null){
                if (iterator.character.length() == 1)
                    break;
                if (iterator.left != null & iterator.left.character.contains(character)){
                    encodedCharacter += "0";
                    iterator = iterator.left;
                }
                else{
                    encodedCharacter += "1";
                    iterator = iterator.right;
                }
            }
        }
        return encodedCharacter;
    }
}