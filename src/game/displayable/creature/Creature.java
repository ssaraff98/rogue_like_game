package game.displayable.creature;

import game.action.creatureAction.CreatureAction;
import game.displayable.Displayable;

import java.util.ArrayList;

public class Creature extends Displayable {
    private String name;
    private int room;
    private int serial;
    public int hpMoves;
    public int hp;

    private ArrayList<CreatureAction> hitAction = new ArrayList<CreatureAction>();
    private ArrayList<CreatureAction> deathAction = new ArrayList<CreatureAction>();

    public void setName(String _name) { this.name = _name; }

    public String getName() { return this.name; }

    public void setID(int _room, int _serial) {
        this.room = _room;
        this.serial = _serial;
    }

    public int getRoom() { return this.room; }

    @Override
    public int getHp() {
        return this.hp;
    }

    @Override
    public void setHp(int _hp) {
        int old_hp = this.hp;
        if (old_hp > _hp && _hp > 0) {
            for (CreatureAction hitAct: hitAction) {
                hitAct.performAction();
            }
//            loop through hit action and call doaction.
        }
        this.hp = _hp;

        if (_hp <= 0) {
            for (CreatureAction deathAct: deathAction) {
                deathAct.performAction();
            }
        }
    }

    public ArrayList<CreatureAction> getDeathAction() { return this.deathAction; }

    public void setDeathAction(CreatureAction _deathAction) {
        this.deathAction.add(_deathAction);
        // System.out.println("Death: " + this.deathAction);
    }

    public ArrayList<CreatureAction> getHitAction() { return this.hitAction; }

    public void setHitAction(CreatureAction _hitAction) {
        this.hitAction.add(_hitAction);
        // System.out.println("Hit: " + this.hitAction);
    }
}
