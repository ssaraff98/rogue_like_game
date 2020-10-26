package game.action.itemAction;

import game.displayable.item.Item;

public class BlessCurse extends ItemAction {
    private Item owner;

    public BlessCurse(Item _owner) {
        super(_owner);
        owner = _owner;

        System.out.println("BlessCurser Owner: " + _owner);
    }
}