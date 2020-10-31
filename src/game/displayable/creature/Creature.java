package game.displayable.creature;

import game.displayable.Displayable;
import game.action.creatureAction.CreatureAction;

public class Creature extends Displayable {
    private String name;
    private int room;
    private int serial;
    private int hpMoves;
    private int hp;
    private CreatureAction hitAction;
    private CreatureAction deathAction;

    public void setName(String _name) { this.name = _name; }

    public String getName() { return this.name; }

    public void setID(int _room, int _serial) {
        this.room = _room;
        this.serial = _serial;
    }

    public int getRoom() { return this.room; }

    @Override
    public void setHpMoves(int _hpMoves) {
        this.hpMoves = _hpMoves;
        System.out.println("Creature Moves: " + _hpMoves);
    }

    @Override
    public void setHp(int _hp) { this.hp = _hp;
        System.out.println("HitPoints: " + _hp);
    }

    public void setHitAction(CreatureAction _hitAction) {
        this.hitAction = _hitAction;
        System.out.println("Hit Action: " + _hitAction);

    }

    public void setDeathAction(CreatureAction _deathAction) {
        this.deathAction = _deathAction;
        System.out.println("Death Action: " + _deathAction);

    }
}
