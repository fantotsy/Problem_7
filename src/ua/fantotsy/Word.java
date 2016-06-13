package ua.fantotsy;

public class Word implements Comparable<Word> {
    private String word;
    private Integer amount;
    private String code;
    private boolean empty;

    private Word left;
    private Word right;

    public String getWord() {
        return word;
    }

    public Integer getAmount() {
        return amount;
    }

    public Word getLeft() {
        return left;
    }

    public Word getRight() {
        return right;
    }

    public void setLeft(Word left) {
        this.left = left;
    }

    public void setRight(Word right) {
        this.right = right;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public Word(String word) {
        this.word = word;
        amount = 1;
        code = null;
        empty = false;
        left = null;
        right = null;
    }

    public Word(Integer amount, Word left, Word right) {
        word = null;
        this.amount = amount;
        code = null;
        empty = false;
        this.left = left;
        this.right = right;
    }

    public void incrementAmount() {
        amount++;
    }

    public void makeNull() {
        word = null;
        amount = null;
        code = null;
        left = null;
        right = null;
    }

    @Override
    public int compareTo(Word word) {
        return amount.compareTo(word.amount);
    }
}
