package game.action.itemAction;

import game.display.ObjectDisplayGrid;
import game.displayable.creature.Player;
import game.displayable.item.Item;

public class Hallucinate extends ItemAction {
    private Item item;

    public Hallucinate(Item _owner) {
        super(_owner);
        item = _owner;
        System.out.println("Hallucinate Owner: " + item);
    }

    @Override
    public void performAction() {
        ObjectDisplayGrid displayGrid = ObjectDisplayGrid.getObjectDisplayGrid(0,0,0,0);
        displayGrid.displayStringToTerminal("Info: You start hallucinating for the next " + getIntValue() + " turns!", 0, displayGrid.getTotalHeight() - 1);

        for (int i = 0; i < getIntValue(); i++) {

        }
    }
}