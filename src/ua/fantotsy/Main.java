package ua.fantotsy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException, FileNotFoundException, IOException {
        List<Word> list = new ArrayList<>();
        List<Word> text = new ArrayList<>();

        //Getting of text.
        try {
            Scanner scanner = new Scanner(new File("src/book.txt"));
            while (scanner.hasNext()) {
                Word newWord = new Word(scanner.next());
                text.add(newWord);
                boolean contains = false;
                int index = 0;
                for (Word w : list) {
                    if (w.getWord().equals(newWord.getWord())) {
                        contains = true;
                        index = list.indexOf(w);
                        break;
                    }
                }
                if (!contains) {
                    list.add(newWord);
                } else {
                    list.get(index).incrementAmount();
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        //Getting of encoding.
        list = Huffman.getEncoding(list);

        //Writing of encoded text.
        try (FileWriter writer = new FileWriter("src/bookZip.txt", true)) {
            for (int i = 0; i < text.size(); i++) {
                for (Word w2 : list) {
                    if (text.get(i).getWord().equals(w2.getWord())) {
                        writer.write(w2.getCode());
                        if (i < (text.size() - 1)) {
                            writer.write(" ");
                        }
                        break;
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        //Reading of encoded text.
        Word vertex = Huffman.getTree(list);
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/bookZip.txt"));
        String readText = "";
        Word current = vertex;
        int symbol = bufferedReader.read();
        while (symbol != -1) {
            char c = (char) symbol;
            if (c == '0') {
                current = current.getLeft();
            }
            if (c == '1') {
                current = current.getRight();
            }
            if (current.getLeft() == null && current.getRight() == null) {
                readText += current.getWord() + " ";
                current = vertex;
            }
            symbol = bufferedReader.read();
        }
        readText = readText.substring(0, readText.length() - 1);
        try (FileWriter writer = new FileWriter("src/bookUnZip.txt", true)) {
            writer.write(readText);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}