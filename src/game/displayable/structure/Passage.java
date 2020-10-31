package game.displayable.structure;

import game.displayable.Displayable;

import java.util.ArrayList;

public class Passage extends Displayable {
    private String name;
    private int room1;
    private int room2;

    private ArrayList<Integer> posX = new ArrayList<Integer>();
    private ArrayList<Integer> posY = new ArrayList<Integer>();

    public void setName(String _name) { this.name = _name; }

    public void setID(int _room1, int _room2) {
        this.room1 = _room1;
        this.room2 = _room2;
    }

    public void setPosX(int x) { this.posX.add(new Integer(x)); }

    public ArrayList<Integer> getPosXList() { return this.posX; }

    public void setPosY(int y) { this.posY.add(y); }

    public ArrayList<Integer> getPosYList() { return this.posY; }
}
