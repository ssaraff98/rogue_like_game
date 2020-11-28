package game.action.itemAction;

import game.action.Action;
import game.displayable.item.Item;

public class ItemAction extends Action {
    private Item item;

    public ItemAction(Item _owner) {
        item = _owner;
    }

    @Override
    public void performAction() { }
}
