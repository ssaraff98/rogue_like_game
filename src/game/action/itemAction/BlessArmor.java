package game.action.itemAction;

import game.displayable.item.Item;

public class BlessArmor extends ItemAction {
    private Item item;

    public BlessArmor(Item _owner) {
        super(_owner);
        item = _owner;
        // System.out.println("BlessCurser Owner: " + item);
    }

    @Override
    public void performAction() {

    }
}