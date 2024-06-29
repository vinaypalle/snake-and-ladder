package org.example;
public class Cell {

    SpecialObject specialObject;
    Player player;

    public SpecialObject getSpecialObject() {
        return specialObject;
    }

    public void setSpecialObject(SpecialObject specialObject) {
        this.specialObject = specialObject;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Cell()
    {
        this.specialObject = null;
        this.player = null;
    }
}
