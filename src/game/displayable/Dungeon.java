package game.displayable;

import game.displayable.creature.Creature;
import game.displayable.item.Item;
import game.displayable.structure.Passage;
import game.displayable.structure.Room;

import java.util.ArrayList;

public class Dungeon extends Displayable {
    private String name;
    private int width;
    private int gameHeight;

    public Dungeon(String _name, int _width, int _gameHeight) {
        name = _name;
        width = _width;
        gameHeight = _gameHeight;
    }

    public Dungeon getDungeon(String _name, int _width, int _gameHeight) {
        if (this.name == _name && this.width == _width && this.gameHeight == _gameHeight) {
            return this;
        }
        Dungeon dungeon = new Dungeon(_name, _width, _gameHeight);
        return dungeon;
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
