package game.action.itemAction;

import game.action.Action;
import game.displayable.item.Item;

public class ItemAction extends Action {
    private Item owner;

    public ItemAction(Item _owner) {
        owner = _owner;
    }
}
