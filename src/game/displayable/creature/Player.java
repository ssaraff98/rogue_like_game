package game.displayable.creature;

import game.displayable.item.Item;

public class Player extends Creature  {
    private Item sword;
    private Item armor;

    public void setWeapon(Item _sword) {
        sword = _sword;
    }

    public void setArmor(Item _armor) {
        armor = _armor;
    }
}
