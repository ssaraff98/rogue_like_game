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
    private boolean receiveInput = true;
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

    public void setReceiveInput(boolean value) {
        this.receiveInput = value;
    }

    public boolean getReceiveInput() {
        return this.receiveInput;
    }

    public int getRoom() { return this.room; }

    public Item getWeapon() { return this.sword; }

    public void equipWeapon(int item_number) {
        ObjectDisplayGrid displayGrid = ObjectDisplayGrid.getObjectDisplayGrid(0, 0, 0, 0);

        if (inventory.size() >= MAX_PACK_SIZE) {
            displayGrid.displayStringToTerminal("Info: Item number entered exceeds maximum pack size", 0, displayGrid.getTotalHeight() - 1);
            return;
        }
        else if (inventory.get(item_number).getType() != ')') {
            displayGrid.displayStringToTerminal("Info: Item chosen is not a sword", 0, displayGrid.getTotalHeight() - 1);
            return;
        }
        else if (this.sword != null && this.sword.equals(inventory.get(item_number))) {
            displayGrid.displayStringToTerminal("Info: Item chosen was already equipped", 0, displayGrid.getTotalHeight() - 1);
            return;
        }

        if (this.sword != null && !this.sword.equals(inventory.get(item_number))) {
            this.sword.setName((this.sword.getIntValue() > 0 ? "+" : "") + this.sword.getIntValue() + " Sword");
            inventory.add(this.sword);
        }
        this.sword = inventory.get(item_number);
        this.sword.setName(this.sword.getName() + " (w)");
        displayGrid.displayStringToTerminal("Info: Equipped " + this.sword.getName(), 0, displayGrid.getTotalHeight() - 1);
    }

    public void setWeapon(Item _sword) {
        this.sword = _sword;
        this.sword.setName(this.sword.getName() + " (w)");
        inventory.add(sword);
    }

    public Item getArmor() { return this.armor; }

    public void equipArmor(int item_number) {
        ObjectDisplayGrid displayGrid = ObjectDisplayGrid.getObjectDisplayGrid(0, 0, 0, 0);
        Item item = inventory.get(item_number);

        if (inventory.size() >= MAX_PACK_SIZE) {
            displayGrid.displayStringToTerminal("Info: Item number entered exceeds maximum pack size", 0, displayGrid.getTotalHeight() - 1);
            return;
        }
        else if (item.getType() != ']') {
            displayGrid.displayStringToTerminal("Info: Item chosen is not an armor", 0, displayGrid.getTotalHeight() - 1);
            return;
        }
        else if (this.armor != null && this.armor.equals(inventory.get(item_number))) {
            displayGrid.displayStringToTerminal("Info: Item chosen was already equipped", 0, displayGrid.getTotalHeight() - 1);
            return;
        }

        if (this.armor != null && !this.armor.equals(item)) {
            this.armor.setName((this.armor.getIntValue() > 0 ? "+" : "") + this.armor.getIntValue() + " Armor");
            inventory.add(this.armor);
        }
        this.armor = item;
        this.armor.setName(this.armor.getName() + " (a)");
        displayGrid.displayStringToTerminal("Info: Equipped " + this.armor.getName(), 0, displayGrid.getTotalHeight() - 1);
    }

    public void setArmor(Item _armor) {
        this.armor = _armor;
        this.armor.setName(this.armor.getName() + " (a)");
        inventory.add(armor);
    }

    public int getMoves() { return this.moves; }

    public boolean setMoves() {
        System.out.println(this.moves + " " + this.getHpMoves());;
        this.moves++;

        if (this.moves == this.getHpMoves()) {
            setHp(getHp() + 1);
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
        if (inventory.size() - 1 >= item_number) {
            Item item = inventory.get(item_number);
            inventory.remove(item_number);

            if (item.equals(this.sword)) {
                item.setName((item.getIntValue() > 0 ? "+" : "") + item.getIntValue() + " Sword");
                this.sword = null;
            }
            else if (item.equals(this.armor)) {
                item.setName((item.getIntValue() > 0 ? "+" : "") + item.getIntValue() + " Armor");
                this.armor = null;
            }
            return item;
        }
        return null;
    }

    public void readScroll(int item_number) {
        ObjectDisplayGrid displayGrid = ObjectDisplayGrid.getObjectDisplayGrid(0, 0, 0, 0);

        Scroll item = (Scroll) inventory.get(item_number);
        if (!item.getName().equals("Scroll")) {
            displayGrid.displayStringToTerminal("Info: Item chosen is not a scroll", 0, displayGrid.getTotalHeight() - 1);
            return;
        }

        if (item.getRead() == true) {
            displayGrid.displayStringToTerminal("Info: Scroll chosen has already been read", 0, displayGrid.getTotalHeight() - 1);
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
            displayGrid.displayStringToTerminal("Info: No action associated with the scroll chosen", 0, displayGrid.getTotalHeight() - 1);
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
