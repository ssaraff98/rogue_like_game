package game.displayable.creature;

import game.action.creatureAction.CreatureAction;
import game.displayable.Displayable;

public class Monster extends Creature {
    private static final int DEBUG = 0;
    private static final String CLASSID ="game.displayble.creature.Monster";

    private String name;
    private int room;
    private int serial;

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

    public int getInflictedDamage(int maxHit) {
        int damage = getRandom(maxHit);
        return damage;
    }

    public void performBeingHitActions(Player player) {
//        for(CreatureAction action: hitActions){
//            action.performAction();
//        }

        int damage = player.getInflictedDamage(player.getMaxHit());

        int hp = getHp();
        if (damage > 0) {
            setHp(hp - damage);
        }
        else {
            setHp(hp + damage);
        }

        if (DEBUG > 1) {
            System.out.println(CLASSID + ".performBeingHitActions");
            System.out.println("encroacher damage: " + player);
            System.out.println("damage incurred: " + damage);
            System.out.println("hp after hit: " + hp);
        }

        if (hp <= 0) {
            // performDeathActions(monster);
        }
    }

//    @Override
//    protected void performMoveActions(int x, int y, Displayble moveToObject) {
//        moveCount = ++moveCount % hpMoves;
//        if (moveCount == 0) {
//            setHp(hp++);
//            ObjectDisplayGrid.writeHp(hp);
//        }
//        moveToObject.performBeingHitActions(this);
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
