package game.displayable;

import game.display.ObjectDisplayGrid;
import game.display.Char;

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

//    public Creature getMonster(int x, int y){
//        // check x,y for creature , in the creature list.
//        // then return that monster
//    }

    public void addCreature(Creature _creature) { creatures.add(_creature); }

    public void addItem(Item _item) { items.add(_item); }

    public Item getItem(int x, int y, char ch) {
        for (Item i : items) {
            int item_x = i.getPosX();
            int item_y = i.getPosY();
            char item_type = i.getType();

            if(x == item_x && y == item_y && ch == item_type) {
                items.remove(i);
                return i;
            }
        }
        return null;
    }

    public void addPassage(Passage _passage) { passages.add(_passage); }

    public void addRoom(Room _room) { rooms.add(_room); }

    public void addObjectToDisplay(ObjectDisplayGrid displayGrid) {
        int topHeight = displayGrid.getTopMessageHeight();

        for (int j = 0; j < rooms.size(); j++) {
            int xstarting = rooms.get(j).getPosX();
            int ystarting = rooms.get(j).getPosY();
            int xending = xstarting + rooms.get(j).getWidth();
            int yending = ystarting + rooms.get(j).getHeight();

            for(int i = xstarting; i < xending; i++){
                for(int l = ystarting; l < yending; l++){
                    displayGrid.addObjectToDisplay(new Char('.'), i, l+topHeight);
                }
            }

            for (int m = ystarting; m < yending; m++) {
                displayGrid.addObjectToDisplay(new Char('X'), xstarting, m + topHeight);
            }
            for (int m = xstarting; m < xending; m++) {
                displayGrid.addObjectToDisplay(new Char('X'), m, ystarting + topHeight);
            }
            for (int m = yending - 1; m > ystarting; m--) {
                displayGrid.addObjectToDisplay(new Char('X'), xending - 1, m + topHeight);
            }
            for (int m = xending - 1; m > xstarting; m--) {
                displayGrid.addObjectToDisplay(new Char('X'), m, yending - 1 + topHeight);
            }
        }

        for (int i = 0; i < creatures.size(); i++) {
            Creature creature = creatures.get(i);
            int creature_x = creature.getPosX();
            int creature_y = creature.getPosY();

            int room = creature.getRoom();
            int room_x = -1;
            int room_y = -1;

            for (Room r : rooms) {
                if (r.getId() == room) {
                    room_x = r.getPosX();
                    room_y = r.getPosY();
                    break;
                }
            }

            if (room_x == -1 || room_y == -1) {
                System.out.println("Room position is invalid\n");
                System.out.println("Creature - " + creature + " x: " + creature_x + " y: " + creature_y);
                return;
            }

            int x = room_x + creature_x;
            int y = room_y + creature_y + topHeight;
            creature.setPosX(x);
            creature.setPosY(y);

            displayGrid.addObjectToDisplay(new Char(creature.getType()), x, y);
        }

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            int x = item.getPosX();
            int y = item.getPosY();
            item.setPosX(x);
            item.setPosY(y + topHeight);

            displayGrid.addObjectToDisplay(new Char(item.getType()), x, y + topHeight);
        }

        for (int i = 0; i < passages.size(); i++) {
            Passage passage = passages.get(i);
            ArrayList<Integer> posX = passage.getPosXList();
            ArrayList<Integer> posY = passage.getPosYList();

            if (posX.size() != posY.size()) {
                System.out.println("Incorrent number of X and Y positions for passage\n");
            }

            for (int j = 0; j < posX.size() - 1; j++) {
                int x1 = posX.get(j).intValue();
                int y1 = posY.get(j).intValue();
                int x2 = posX.get(j + 1).intValue();
                int y2 = posY.get(j + 1).intValue();

                if (x1 == x2) {
                    int min_y = (y1 < y2)? y1 : y2;
                    int max_y = (y1 < y2)? y2 : y1;

                    for (int k = min_y; k <= max_y; k++) {
                        displayGrid.addObjectToDisplay(new Char('#'), x1, k + topHeight);
                    }
                }

                if (y1 == y2) {
                    int min_x = (x1 < x2)? x1 : x2;
                    int max_x = (x1 < x2)? x2 : x1;

                    for (int k = min_x; k <= max_x; k++) {
                        displayGrid.addObjectToDisplay(new Char('#'), k, y1 + topHeight);
                    }
                }
            }
        }
    }
}
