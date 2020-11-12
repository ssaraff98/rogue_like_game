package game.displayable.creature;

import game.displayable.item.Item;
import game.display.Char;

import java.util.ArrayList;

public class Player extends Creature {
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
        moves = 0;
    }

    public void setName(String _name) { this.name = _name; }

    public String getName() { return this.name; }

    public void setID(int _room, int _serial) {
        this.room = _room;
        this.serial = _serial;
    }

    public int getRoom() { return this.room; }

    public void setWeapon(Item _sword) {
        sword = _sword;
        inventory.add(sword);
    }

    public void setArmor(Item _armor) {
        armor = _armor;
        inventory.add(armor);
    }

    public int getMoves() { return this.moves; }

    public void setMoves() {
        moves++;
        if (moves == this.getHpMoves()) {
            int hp = this.getHp();
            this.setHp(hp++);
            moves = 0;
        }
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

    }

    public int getInflictedDamage(int maxHit) {
        int damage = getRandom(maxHit);
        if(sword != null) {
            damage += sword.getInflictedDamage();

        }
        return damage;
    }

    @Override
    public void performBeingHitActions(Displayble attacker) {
        int armorProtection = 0;

        for(CreatureAction action: hitActions){
            action.perdormAction();
        }

        if(armor != null) {
            armorProtection = armor.getInflictedDamage();
        }

        int damage = attacker.getInflictedDamage() - armorProtection;

        setHP(hp - ((damage>0)? damage : -damage));

        if(DEBUG > 1){
            System.out.println(CLASSID + ".performBeingHitActions");
            System.out.println("armor mitigation: "+armor.getInflictedDamage());
            System.out.println("encroacher damage: "+attacker+getInflictedDamage());
            System.out.println("damage incurred: "+damage);
            System.out.println("hp after hit: "+hp);

        }

        if(hp<=0){
            performDeathActions(attacker);
        }
    }

//    @Override
//    protected void performMoveActions(int x,int y,Displayble moveToObject){
//        moveCount = ++moveCount % hpMoves;
//        if(moveCount == 0){
//            setHp(hp++);
//            ObjectDisplayGrid.writeHp(hp);
//        }
//        moveToObject.performBeingHitActions(this);
//    }
//
//    @Override
//    protected void performDeathActions(Displayble attacker){
//        ObjectDisplaGrid.showDisplayableGridPoint(this.getPosX(), this.getPosY(), 0);
//        for(CreatureAction action : deathActions) {
//            action.performAction();
//        }
//        ObjectDisplayGrid.writeHp(hp);
//    }
}
