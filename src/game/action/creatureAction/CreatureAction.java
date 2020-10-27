package game.action.creatureAction;

import game.action.Action;
import game.displayable.creature.Creature;

public class CreatureAction extends Action {
    private Creature owner;

    public CreatureAction(Creature _owner) {
        owner = _owner;
    }
}
