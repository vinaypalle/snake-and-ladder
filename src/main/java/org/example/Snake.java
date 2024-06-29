package org.example;
public class Snake implements SpecialObject{

    private int start;
    private int end;

    public Snake(int start, int end) {
        this.start = start;
        this.end = end;
    }
    @Override
    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }
}
