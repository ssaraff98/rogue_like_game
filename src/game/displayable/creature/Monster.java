package game.displayable.creature;
//package game.display;

public class Monster extends Creature {
    private String name;
    private int room;
    private int serial;
//    private Item sword;
//    private Item armor;
//    private ObjectDisplayGrid displayGrid;
//    displayGrid = ObjectDisplayGrid grid;

    public void setName(String _name) {
        this.name = _name;
    }

    public String getName() {
        return this.name;
    }

    public void setID(int _room, int _serial) {
        this.room = _room;
        this.serial = _serial;
    }

    public int getRoom() {
        return this.room;
    }


    private int moveCount = 0;

//    @Override
//    public void initializeDisplayofSelf() {
//        if (DEBUG > 1) {
//            System.out.println(CLASSID + ".initializeDisplayOfSelf type " + type);
//        }
//        System.out.println(CLASSID + ".initializeDisplayOfSelf posX" + posX + ",poxY" + posY + ", type: " + type + ", hp" + hp);
//        super.initializeDisplayOfSelf();
//        ObjectDisplayGrid.writeUp(hp);
//    }
//
//    @Override
//    public void performBeingHitActions(Displayble attacker) {
//        if (DEBUG > 1) {
//            System.out.println(CLASSID + ".performBeingHitActions intial hp: " + hp);
//        }
//        for (CreatureAction action : hitActions) {
//            action.performAction();
//        }
//        int armorProtection = 0;
//        if (armor != null) {
//            armorProtection = armor.getInflictedDamage();
//        }
//        int damage = attacker.getInflictedDamage() - armorProtection;
//        setHP(hp - ((damage > 0) ? damage : -damage));
//        if (DEBUG > 1) {
//            System.out.println(CLASSID + ".performBeingHitActions");
//            System.out.println("armor mitigation: " + armor.getInflictedDamage());
//            System.out.println("encroacher damage: " + attacker + getInflictedDamage());
//            System.out.println("damage incurred: " + damage);
//            System.out.println("hp after hit: " + hp);
//
//        }
//        if (hp <= 0) {
//            performDeathActions(attacker);
//        }
//    }

//    @Override
//    protected void performMoveActions(int x, int y, Displayble moveToObject) {
//        moveCount = ++moveCount % hpMoves;
//        if (moveCount == 0) {
//            setHp(hp++);
//            ObjectDisplayGrid.writeHp(hp);
//        }
//        moveToObject.performBeingHitActions(this);
//    }

//    @Override
//    public int getInflictedDamage() {
//        int damage = getRandom(maxHit);
//        if (weapon != null) {
//            damage += weapon.getInflictedDamage();
//
//        }
//        return damage;
//    }
//
//    @Override
//    protected void performDeathActions(Displayble attacker) {
//        displayGrid.showDisplayableGridPoint(this.getPosX(), this.getPosY(), 0);
//        for (CreatureAction action : deathActions) {
//            action.performAction();
//        }
//        displayGrid.writeHp(hp);
//    }
}
