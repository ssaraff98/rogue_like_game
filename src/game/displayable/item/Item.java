package game.displayable.item;

import game.action.itemAction.ItemAction;

import game.displayable.Displayable;
import game.displayable.creature.Creature;

public class Item extends Displayable {
    private String name;

    private Creature creature;
    private ItemAction blessAction;
    private ItemAction hallucinateAction;

    public String getName() { return this.name; }

    public void setName(String _name) { this.name = _name; }

    public Creature getCreature() { return this.creature; }

    public void setOwner(Creature _creature) {
        this.creature = _creature;
        // System.out.println("Owner: " + this.creature);
    }

    public ItemAction getBlessAction() { return this.blessAction; }

    public void setBlessAction(ItemAction _blessAction) {
        this.blessAction = _blessAction;
        // System.out.println("Bless: " + this.blessAction);
    }

    public ItemAction getHallucinateAction() { return this.hallucinateAction; }

    public void setHallucinateAction(ItemAction _hallucinateAction) {
        this.hallucinateAction = _hallucinateAction;
        // System.out.println("Hallucinate: " + this.hallucinateAction);
    }

    public String getItemName(char type) {
        String name = "";

        if (type == ')') {
            return name;
        }
        else if (type == ']') {
            name = "Armor";
        }
        else if (type == '?') {
            name = "Scroll";
        }
        else {
            System.out.println("No such item found");
        }

        return name;
    }
}
