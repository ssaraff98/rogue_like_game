package game.displayable.creature;

import game.action.creatureAction.CreatureAction;
import game.action.itemAction.BlessArmor;
import game.action.itemAction.Hallucinate;
import game.action.itemAction.ItemAction;

import game.display.Char;
import game.display.ObjectDisplayGrid;

import game.displayable.Displayable;
import game.displayable.item.Item;
import game.displayable.item.Armor;
import game.displayable.item.Scroll;
import game.displayable.item.Sword;

import java.util.ArrayList;

public class Player extends Creature {
    private static final int DEBUG = 1;
    private static final String CLASSID ="game.displayble.creature.Player";
    private static final int MAX_PACK_SIZE = 10;

    private String name;
    private int room;
    private int serial;
    private int moves;
    private Char charStandingOn = new Char('.');

    private Item sword;
    private Item armor;
    private ArrayList<Item> inventory = new ArrayList<Item>();

    public Player() {
        this.moves = 0;
    }

    public void setName(String _name) { this.name = _name; }

    public String getName() { return this.name; }

    public void setID(int _room, int _serial) {
        this.room = _room;
        this.serial = _serial;
    }

    public int getRoom() { return this.room; }

    public Item getWeapon() { return this.sword; }

    public boolean equipWeapon(int item_number) {
        if (inventory.size() >= MAX_PACK_SIZE) {
            System.out.println("Item number entered exceeds maximum pack size.");
            return false;
        }

        if (this.sword != null) {
            inventory.add(this.sword);
        }
        setWeapon(inventory.get(item_number));
        inventory.remove(item_number);
        return true;
    }

    public void setWeapon(Item _sword) {
        this.sword = _sword;
        // inventory.add(sword);
    }

    public Item getArmor() { return this.armor; }

    public boolean equipArmor(int item_number) {
        if (inventory.size() >= MAX_PACK_SIZE) {
            System.out.println("Item number entered exceeds maximum pack size.");
            return false;
        }

        if (this.armor != null) {
            inventory.add(this.armor);
        }
        setArmor(inventory.get(item_number));
        inventory.remove(item_number);
        return true;
    }

    public void setArmor(Item _armor) {
        this.armor = _armor;
        // inventory.add(armor);
    }

    public int getMoves() { return this.moves; }

    public boolean setMoves() {
        System.out.println(this.moves + " " + this.getHpMoves());;
        this.moves++;

        // !!!!!! What is happening here? !!!!!!
        if (this.moves == this.getHpMoves()) {
            int hp1 = getHp();
            hp1 = hp1 + 1;
            System.out.println("Printing hp before: "+hp);
            setHp(hp1);
            System.out.println("Printing hp after: "+hp);
            this.moves = 0;
            return true;
        }
        return false;
    }

    public void setCharStandingOn(Char _charStandingOn){
        charStandingOn = _charStandingOn;
    }

    public Char getCharStandingOn(){
        return charStandingOn;
    }

    public ArrayList<Item> getInventory() { return this.inventory; }

    public boolean addToInventory(Item pickedItem) {
        if (inventory.size() >= MAX_PACK_SIZE) {
            System.out.println("Maximum pack size reached. Cannot pick up more items.");
            return false;
        }
        inventory.add(pickedItem);
        return true;
    }

    public Item removeFromInventory(int item_number) {
        if (inventory.size() >= item_number) {
            Item item = inventory.get(item_number);
            inventory.remove(item_number);
            return item;
        }
        return null;
    }

    public void readScroll(int item_number) {
        Scroll item = (Scroll) inventory.get(item_number);
        if (!item.getName().equals("Scroll")) {
            System.out.println("Item chosen is not a scroll");
            return;
        }

        if (item.getRead() == true) {
            System.out.println("This scroll has been read");
            return;
        }
        item.setRead();

        if (item.getBlessAction() != null) {
            BlessArmor blessAction = (BlessArmor) item.getBlessAction();
            blessAction.performAction();
        }
        else if (item.getHallucinateAction() != null) {
            Hallucinate hallucinateAction = (Hallucinate) item.getHallucinateAction();
            hallucinateAction.performAction();
        }
        else {
            System.out.println("No item action associated with scroll");
        }
    }

    public int getInflictedDamage(int maxHit) {
        int damage = getRandom(maxHit);
        if(sword != null) {
            damage += sword.getInflictedDamage(sword.getMaxHit());
        }
        return damage;
    }

    public int performBeingHitActions(Monster monster) {
        for(CreatureAction action: getHitAction()) {
            action.performAction();
        }

        int armorProtection = 0;
        if(armor != null) {
            armorProtection = armor.getInflictedDamage(armor.getMaxHit());
        }

        int damage = monster.getInflictedDamage(monster.getMaxHit()) - armorProtection;

        int hp = getHp();
        if (damage > 0) {
            setHp(hp - damage);
        }
        else {
            setHp(hp + damage);
        }

        if (DEBUG > 1) {
            System.out.println(CLASSID + ".performBeingHitActions");
            System.out.println("Encroacher damage: " + monster);
            System.out.println("HP after hit: " + hp);
        }

        if (hp <= 0) {
            for(CreatureAction action : getDeathAction()) {
                action.performAction();
            }
        }
        return damage;
    }
}
