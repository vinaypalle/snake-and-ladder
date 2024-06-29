package org.example;
public class Mine implements SpecialObject{

    private int start;

    private int skipTurnsCount;
    @Override
    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getSkipTurnsCount() {
        return skipTurnsCount;
    }

    public Mine(int start)
    {
        this.start = start;
        this.skipTurnsCount = 2;
    }
}
