package game.action.creatureAction.playerAction;

import game.action.Action;
import game.action.creatureAction.CreatureAction;
import game.display.ObjectDisplayGrid;
import game.display.Char;
import game.displayable.creature.Creature;
import game.displayable.creature.Player;
import game.displayable.item.Item;

public class DropPack extends CreatureAction {
    private String name;
    private Creature owner;
    private Player owner2;

    public DropPack(String _name, Creature _owner) {
        super(_owner);
        name = _name;
        owner2 = (Player)_owner;
    }

    @Override
    public void performAction() {
        ObjectDisplayGrid displayGrid;
        displayGrid = ObjectDisplayGrid.getObjectDisplayGrid(0, 0, 0, 0);

        int x = this.owner2.getPosX();
        int y = this.owner2.getPosY();

        if (this.owner2.getInventory().size() == 0) {
            displayGrid.displayStringToTerminal("Info: No item in the pack", 0, displayGrid.getTotalHeight() - 1);
        }
        else {
            int item_number = 0;
            Item item = this.owner2.removeFromInventory(item_number);
            if (item == null) {
                displayGrid.displayStringToTerminal("Info: No item to be found at position " + item_number, 0, displayGrid.getTotalHeight() - 1);
            } else {
                item.setPosX(x);
                item.setPosY(y);

                displayGrid.displayStringToTerminal("Info: Item " + item.getName() + " dropped at (" + item.getPosX() + ", " + item.getPosY() + ")", 0, displayGrid.getTotalHeight() - 1);
                displayGrid.removeObjectToDisplay(x, y);
                displayGrid.addObjectToDisplay(new Char(item.getType()), x, y);
                displayGrid.getMainPlayer().setCharStandingOn(displayGrid.getDisplayChar(x, y));
                displayGrid.addObjectToDisplay(new Char('@'), x, y);
            }
        }
    }
}