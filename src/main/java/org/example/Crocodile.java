package org.example;
public class Crocodile implements SpecialObject{

    private int start;

    private int end;
    public Crocodile(int start)
    {
        this.start = start;
        this.end = start <= 5 ? 1 : start-5;
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
