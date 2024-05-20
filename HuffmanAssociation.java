package huffmanHomework;
import java.io.*;
import java.util.*;

public class HuffmanAssociation {

    public static void writeToFile(String Path, String content){
        try (PrintWriter writer = new PrintWriter(Path, "UTF-8")) {
            writer.println(content);
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void writeListToLetter(LinkedList list, String Path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Path))) {
            Node current = list.head;
            while (current != null) {
                writer.write( " '" + current.character + "' =  " + current.frequency);
                writer.newLine();
                current = current.next;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String fromFileToString(String Path){
        String content = "";

        File file = new File(Path);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = br.readLine()) != null) {
                for (int i=0; i < line.length(); i++){
                    char currentChar = line.charAt(i);
                    content += currentChar;
                }
            }
            br.close();
        }
        catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    public static Map<Character, Integer> fromLetterToMap(String Path){
        Map<Character, Integer> charMap = new HashMap<Character, Integer>();
        File file = new File(Path);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                for (int i=0; i < line.length(); i++){
                    char currentChar = line.charAt(i);
                    int value = charMap.getOrDefault(currentChar, 0);

                    if (value == 0)
                        charMap.put(currentChar, 0);
                    else{
                        charMap.put(currentChar, charMap.get(currentChar) + 1);
                    }
                }
            }

            br.close();
        }
        catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }

        return charMap;
    }

    public static LinkedList fromMapToList(Map<Character, Integer> map){

        LinkedList list = new LinkedList();

        // TRAVERSING THE MAP
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            String character = String.valueOf(entry.getKey());
            int frequency = ( (int) entry.getValue() );

            Node node = new Node(character, frequency);

            list.sortedInsert(node);
        }
        return list;
    }

    public static void main(String[] args) {

        String letterPath = "src/letter.txt";
        String sourcePath = "src/source.txt";
        String encodedPath = "src/encoded.txt";
        String decodedPath = "src/decoded.txt";


        Map<Character, Integer> letterMap = fromLetterToMap(sourcePath);
        LinkedList letterList = fromMapToList(letterMap);
        writeListToLetter(letterList, letterPath);  //  WRITING LINKED LIST INTO LETTER TEXT FILE

        // STARTING OF CREATING HUFFMAN TREE
        HuffmanTree letterTree = new HuffmanTree(letterList);

        // ENCODING PART OF ALL CHARACTERS
        letterList = fromMapToList(letterMap);
        while (letterList.count() > 0){
            Node node = letterList.poppingTheHead();
            String character = node.character;

            System.out.println("---> encoded " + character + " = " + letterTree.encode(character));
        }

        System.out.println("MESSAGE ENCODING: ");
        String source = fromFileToString(sourcePath);
        String encodedSource = letterTree.encode(source);
        System.out.println(encodedSource);
        writeToFile(encodedPath, encodedSource);


        System.out.println("DECODE OF THE ENCODED MESSAGE:  "); //IT SHOULD INCLUDE EXACTLY THE SAME WITH SOURCE.TXT !!
        String encoded = fromFileToString(encodedPath);
        String decoded = letterTree.decode(encoded);
        System.out.println(decoded);
        writeToFile(decodedPath, decoded);

        //CHECK AGAIN THE FREQ. PART IN LETTER.TXT!!!
    }
}
