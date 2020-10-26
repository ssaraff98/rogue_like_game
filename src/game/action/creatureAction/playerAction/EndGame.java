package game.action.creatureAction.playerAction;

import game.action.creatureAction.CreatureAction;
import game.displayable.creature.Creature;

public class EndGame extends CreatureAction {
    private String name;
    private Creature owner;

    public EndGame(String _name, Creature _owner) {
        super(_owner);
        name = _name;
        owner = _owner;

        System.out.println("Endgame Name: " + _name + " Owner: "+owner);
    }
}