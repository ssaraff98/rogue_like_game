package game.displayable.structure;

import game.displayable.creature.Creature;

public class Room extends Structure {
    private int room;
    private Creature creature;

    public Room(int _room) { room = _room; }

    public void setId(int _room) { this.room = _room; }

    public void setCreature(Creature _creature) { this.creature = _creature; }
}
