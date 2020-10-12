import java.util.ArrayList;

public class Dungeon {
    private String name;
    private int width;
    private int gameHeight;

    public void getDungeon(String _name, int _width, int _gameHeight) {
        System.out.println("Dungeon name: " + _name + "\n"+"width: " + _width + "\n"+"gameHeight: " + _gameHeight);
    }

    public void addRoom(Room _room) {
        System.out.println("Room: " +  _room);

    }

    public void addCreature(Creature _creature) {
        System.out.println("Creature: " + _creature);

    }

    public void addPassage(Passage _passage) {
        System.out.println("Passage: " + _passage);

    }

    public void addItem(Item _item) {
        System.out.println("Item: " + _item);

    }
}
