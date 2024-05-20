package huffmanHomework;

public class Node {
    public String character;
    public int frequency;
    public Node left, right; //FOR CREATING TREE NODES NEATLY
    public Node next; //FOR CREATING LINKED LIST PROPERLY

    public Node(String character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.right = this.left = null;
    }
}
