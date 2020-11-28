package game.action.creatureAction;

import game.action.Action;
import game.displayable.creature.Creature;

public class CreatureAction extends Action {
    private Creature creature;

    public CreatureAction(Creature _creature) {
        creature = _creature;
    }

    @Override
    public void performAction() { }
}
