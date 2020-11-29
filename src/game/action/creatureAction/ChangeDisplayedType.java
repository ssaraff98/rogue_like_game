package game.action.creatureAction;

import game.displayable.creature.Creature;
import game.action.Action;
import game.display.ObjectDisplayGrid;
import game.display.Char;

public class ChangeDisplayedType extends CreatureAction {
    private String name;
    private Creature owner;

    public ChangeDisplayedType(String _name, Creature _owner) {
        super(_owner);
        name = _name;
        owner = _owner;

        System.out.println("Creature Name: " + _name + " Creature owner: "+_owner);

    }

    @Override
    public void performAction() {
        ObjectDisplayGrid displayGrid;
        displayGrid = ObjectDisplayGrid.getObjectDisplayGrid(0,0,0,0);
//        displayGrid.displayStringToTerminal(getMessage(), 0, displayGrid.getTotalHeight() - 1);
        displayGrid.removeObjectToDisplay(this.owner.getPosX(), this.owner.getPosY());
        displayGrid.removeObjectToDisplay(this.owner.getPosX(), this.owner.getPosY());
        displayGrid.addObjectToDisplay(new Char(getCharValue()), this.owner.getPosX(), this.owner.getPosY());
    }
}
