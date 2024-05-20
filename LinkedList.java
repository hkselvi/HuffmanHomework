package huffmanHomework;
public class LinkedList {
    public Node head;

    public boolean isEmpty(){
        return head == null;
    }

    public void sortedInsert(Node node) {
        if (head == null)
            head = node;
        else if (node.frequency < head.frequency) {
            node.next = head;
            head = node;
        } else {
            Node iterator = head;
            while (iterator.next != null && node.frequency > iterator.next.frequency ) {
                iterator = iterator.next;
            }
            node.next = iterator.next;
            iterator.next = node;
        }
    }

    public Node poppingTheHead(){  //FOR CREATING HUFFMAN TREE PROPERLY
        if (isEmpty()){
            return null;
        }
        Node node = head;
        head = this.head.next;
        return node;
    }


    public int count() {   // REACHING THE NUMBER OF NODES OF LINKED LIST
        int count = 0;

        if (head == null){
            return count;
        }
        Node iterator = head;
        while (iterator != null){
            count++;
            iterator = iterator.next;
        }
        return count;
    }
}
