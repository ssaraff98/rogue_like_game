package game.action.itemAction;

import game.action.Action;
import game.display.ObjectDisplayGrid;
import game.displayable.creature.Player;
import game.displayable.item.Item;

public class BlessArmor extends ItemAction {
    private Item item;

    public BlessArmor(Item _owner) {
        super(_owner);
        item = _owner;
    }

    @Override
    public void performAction() {
        ObjectDisplayGrid displayGrid = ObjectDisplayGrid.getObjectDisplayGrid(0, 0, 0, 0);
        Player player = displayGrid.getMainPlayer();
        Item playerEquip = null;

        if (getCharValue() == 'a') {
            playerEquip = player.getArmor();
        }
        else if (getCharValue() == 'w') {
            playerEquip = player.getWeapon();
        }

        if (playerEquip == null) {
            displayGrid.displayStringToTerminal("Info: Scroll has no effect as no item has been equipped by player", 0, displayGrid.getTotalHeight() - 1);
        }
        else {
            int bless_value = getIntValue();
            if (bless_value < 0) {
                displayGrid.displayStringToTerminal("Info: " + playerEquip.getName() + " cursed! " + bless_value + " from its effectiveness", 0, displayGrid.getTotalHeight() - 1);
            }
            else if (bless_value > 0) {
                displayGrid.displayStringToTerminal("Info: " + playerEquip.getName() + " blessed! " + bless_value + " to its effectiveness", 0, displayGrid.getTotalHeight() - 1);
            }
            else {
                displayGrid.displayStringToTerminal("Info: Scroll has no effect", 0, displayGrid.getTotalHeight() - 1);
            }
            playerEquip.setIntValue(playerEquip.getIntValue() + bless_value);
        }
    }
}