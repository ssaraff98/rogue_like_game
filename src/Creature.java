public class Creature extends Displayable {
    private int hpMoves;
    private int hp;
    private CreatureAction hitAction;
    private CreatureAction deathAction;


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
