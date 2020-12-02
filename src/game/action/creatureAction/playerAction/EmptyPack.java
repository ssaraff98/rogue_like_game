package game.action.creatureAction.playerAction;

import game.action.Action;
import game.action.creatureAction.CreatureAction;
import game.display.ObjectDisplayGrid;
import game.display.Char;
import game.displayable.creature.Creature;
import game.displayable.creature.Player;
import game.displayable.item.Item;

public class EmptyPack extends CreatureAction {
    private String name;
    private Creature owner;
    private Player owner2;

    public EmptyPack(String _name, Creature _owner) {
        super(_owner);
        name = _name;
        owner2 = (Player)_owner;
        System.out.println("EmptyPack Name:" + _name + " EmptyPack owner:"+_owner);
    }

    // Unused function
}