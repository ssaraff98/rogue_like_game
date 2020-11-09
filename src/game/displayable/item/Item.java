package game.displayable.item;

import game.action.itemAction.ItemAction;

import game.displayable.Displayable;
import game.displayable.creature.Creature;

public class Item extends Displayable {
    private Creature creature;

    private ItemAction blessAction;
    private ItemAction hallucinateAction;

    public Creature getCreature() { return this.creature; }

    public void setOwner(Creature _creature) {
        this.creature = _creature;
        System.out.println("Owner: " + this.creature);
    }

    public ItemAction getBlessAction() { return this.blessAction; }

    public void setBlessAction(ItemAction _blessAction) {
        this.blessAction = _blessAction;
    }

    public ItemAction getHallucinateAction() { return this.hallucinateAction; }

    public void setHallucinateAction(ItemAction _hallucinateAction) { this.hallucinateAction = _hallucinateAction; }
}
