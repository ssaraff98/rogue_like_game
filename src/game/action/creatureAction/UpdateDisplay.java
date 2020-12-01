package game.action.creatureAction;

import game.displayable.creature.Creature;
import game.action.Action;
import game.display.ObjectDisplayGrid;
import game.display.Char;

public class UpdateDisplay extends CreatureAction {
    private String name;
    private Creature owner;

    public UpdateDisplay(String _name, Creature _owner) {
        super(_owner);
        name = _name;
        owner = _owner;
    }

    @Override
    public void performAction() {
        System.out.println("Performing action for Update Display");
        ObjectDisplayGrid displayGrid;
        displayGrid = ObjectDisplayGrid.getObjectDisplayGrid(0, 0, 0, 0);
        displayGrid.writeToTerminal(this.owner.getPosX(), this.owner.getPosY());
    }
}