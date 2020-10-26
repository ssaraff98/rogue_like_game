package game.displayable.item;

import game.displayable.Displayable;
import game.displayable.creature.Creature;

public class Item extends Displayable {
    public void setOwner(Creature _owner) {
        System.out.println("owner: " + _owner);

    }
}
