package game.action.creatureAction;

import game.action.Action;
import game.displayable.creature.Creature;

public class Remove extends CreatureAction {
    private String name;
    private Creature owner;

    public Remove(String _name, Creature _owner) {
        super(_owner);
        name = _name;
        owner = _owner;
    }
}