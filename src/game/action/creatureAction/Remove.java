package game.action.creatureAction;

import game.action.Action;
import game.displayable.creature.Creature;
import game.display.ObjectDisplayGrid;
import game.display.Char;

public class Remove extends CreatureAction {
    private String name;
    private Creature owner;

    public Remove(String _name, Creature _owner) {
        super(_owner);
        name = _name;
        owner = _owner;
    }

    @Override
    public void performAction() {
        ObjectDisplayGrid displayGrid;
        displayGrid = ObjectDisplayGrid.getObjectDisplayGrid(0, 0, 0, 0);
        displayGrid.removeObjectToDisplay(this.owner.getPosX(), this.owner.getPosY());
        displayGrid.addObjectToDisplay(new Char('.'), this.owner.getPosX(), this.owner.getPosY());
    }
}