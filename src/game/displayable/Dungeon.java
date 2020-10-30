package game.displayable;

import game.displayable.creature.Creature;
import game.displayable.item.Item;
import game.displayable.structure.Passage;
import game.displayable.structure.Room;
import game.display.ObjectDisplayGrid;

import java.util.ArrayList;

public class Dungeon extends Displayable implements Runnable {
    private String name;
    private int width;
    private int gameHeight;

    private ArrayList<Creature> creatures = new ArrayList<Creature>();
    private ArrayList<Item> items = new ArrayList<Item>();
    private ArrayList<Passage> passages = new ArrayList<Passage>();
    private ArrayList<Room> rooms = new ArrayList<Room>();

    public void run() {
        System.out.println("Run in Dungeon");
    }

    public Dungeon() {
        name = "";
        width = -1;
        gameHeight = -1;
    }

    public Dungeon(String _name, int _width, int _gameHeight) {
        this.name = _name;
        this.width = _width;
        this.gameHeight = _gameHeight;
    }

    public Dungeon getDungeon(String _name, int _width, int _gameHeight) {
        if (this.name == _name && this.width == _width && this.gameHeight == _gameHeight) {
            return this;
        }

        Dungeon dungeon = new Dungeon(_name, _width, _gameHeight);
        return dungeon;
    }

    public int getHeight() { return this.gameHeight; }

    public int getWidth() {
        return this.width;
    }

    public void addCreature(Creature _creature) { creatures.add(_creature); }

    public void addItem(Item _item) { items.add(_item); }

    public void addPassage(Passage _passage) { passages.add(_passage); }

    public void addRoom(Room _room) { rooms.add(_room); }

    public void addObjectToDisplay(ObjectDisplayGrid displayGrid) {
        for (int i = 0; i <= creatures.size(); i++) {
            displayGrid.addObjectToDisplay(creatures.get(i));   // Might need to do displayGrid = ... so it updates the actual object reference
        }
    }
}
