package ua.fantotsy;

import java.util.List;
import java.util.PriorityQueue;

public class Huffman {
    public static Word getTree(List<Word> list) {
        PriorityQueue<Word> queue = new PriorityQueue<>();
        queue.addAll(list);
        while (queue.size() > 1) {
            Word w1 = queue.poll();
            Word w2 = queue.poll();
            Word w = new Word(w1.getAmount() + w2.getAmount(), w1, w2);
            queue.add(w);
        }
        return queue.poll();
    }

    public static List<Word> getEncoding(List<Word> list) {
        Word vertex = getTree(list);
        for (int i = 0; i < list.size(); i++) {
            Word current = vertex;
            Word previous = null;
            String code = "";
            while (vertex.getLeft() != null || vertex.getRight() != null) {
                if (current.getLeft() != null) {
                    if (current.getLeft().isEmpty()) {
                        i--;
                        current.setLeft(null);
                        if (current.getLeft() == null && current.getRight() == null) {
                            current.setEmpty(true);
                        }
                        break;
                    }
                    if (previous == null) {
                        previous = vertex;
                    } else {
                        previous = current;
                    }
                    current = current.getLeft();
                    code += "0";
                    continue;
                }
                if (current.getRight() != null) {
                    if (current.getRight().isEmpty()) {
                        i--;
                        current.setRight(null);
                        if (current.getLeft() == null && current.getRight() == null) {
                            current.setEmpty(true);
                        }
                        break;
                    }
                    if (previous == null) {
                        previous = vertex;
                    } else {
                        previous = current;
                    }
                    current = current.getRight();
                    code += "1";
                    continue;
                }
                int index = list.indexOf(current);
                list.get(index).setCode(code);
                System.out.println(code);
                if (previous.getLeft() != null) {
                    previous.setLeft(null);
                } else {
                    previous.setRight(null);
                }
                if (previous.getLeft() == null && previous.getRight() == null) {
                    previous.setEmpty(true);
                }
                break;
            }
        }
        return list;
    }
}