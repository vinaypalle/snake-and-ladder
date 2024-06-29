package org.example;
public class Player {

    String name;
    int Pos;
    int skipTurns;

    public int getSkipTurns() {
        return skipTurns;
    }

    public void setSkipTurns(int skipTurns) {
        this.skipTurns = skipTurns;
    }

    public Player(String name, int startPos) {
        this.name = name;
        this.Pos = startPos;
        this.skipTurns = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPos() {
        return Pos;
    }

    public void setPos(int startPos) {
        this.Pos = startPos;
    }
}
