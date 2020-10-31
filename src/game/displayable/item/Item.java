package game.displayable.item;

import game.displayable.Displayable;
import game.displayable.creature.Creature;

public class Item extends Displayable {
    private String name;
    private int room;
    private int serial;

    public void setName(String _name) { this.name = _name; }

    public String getName() { return this.name; }

    public void setID(int _room, int _serial) {
        this.room = _room;
        this.serial = _serial;
    }

    public int getRoom() { return this.room; }

    public void setOwner(Creature _owner) {
        System.out.println("owner: " + _owner);

    }
}
