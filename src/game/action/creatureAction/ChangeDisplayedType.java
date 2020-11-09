package game.action.creatureAction;

import game.displayable.creature.Creature;

public class ChangeDisplayedType extends CreatureAction {
    private String name;
    private Creature owner;

    public ChangeDisplayedType(String _name, Creature _owner) {
        super(_owner);
        name = _name;
        owner = _owner;

        System.out.println("Creature Name: " + _name + " Creature owner: "+_owner);

    }
}