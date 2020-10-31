package game.displayable;

import game.display.ObjectDisplayGrid;

import game.displayable.Displayable;
import game.displayable.creature.Creature;
import game.displayable.item.Item;
import game.displayable.structure.Passage;
import game.displayable.structure.Room;

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
        for (int i = 0; i < creatures.size(); i++) {
            Creature creature = creatures.get(i);
            int x = creature.getPosX();
            int y = creature.getPosY();

            displayGrid.addObjectToDisplay(creature, x, y);
        }

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            int x = item.getPosX();
            int y = item.getPosY();

            displayGrid.addObjectToDisplay(item, x, y);
        }

        for (int i = 0; i < passages.size(); i++) {
            Passage passage = passages.get(i);
            ArrayList<Integer> posX = passage.getPosXList();
            ArrayList<Integer> posY = passage.getPosYList();

            if (posX.size() != posY.size()) {
                System.out.println("Incorrent number of X and Y positions for passage");
            }

            for (int j = 0; j < posX.size() - 1; j++) {
                int x1 = posX.get(j).intValue();
                int y1 = posY.get(j).intValue();
                displayGrid.addObjectToDisplay((Displayable) passage, x, y);
            }
        }

//        for (int i = 0; i < rooms.size(); i++) {
//            displayGrid.addObjectToDisplay(rooms.get(i));
//        }
    }
}
