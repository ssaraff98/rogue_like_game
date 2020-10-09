public class Creature extends Displayable {
    private int hpMoves;
    private int hp;

    @Override
    public void setHpMoves(int _hpMoves) {
        this.hpMoves = _hpMoves;
    }

    @Override
    public void setHp(int _hp) { this.hp = _hp; }

    public void setHitAction(CreatureAction _hitAction) {

    }

    public void setDeathAction(CreatureAction _deathAction) {

    }
}
