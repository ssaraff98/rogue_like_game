public abstract class Displayable {
    private boolean visible;
    private int maxHit;
    private int hpMoves;
    private int hp;
    private char type;
    private int value;
    private int posX;
    private int posY;
    private int width;
    private int height;

    public void setInvisible() {
        visible = false;
    }

    public void setVisible() {
        visible = true;
    }

    public void setMaxhit(int maxHit) {
        this.maxHit = maxHit;
    }

    public void setHpMove(int hpMoves) {
        this.hpMoves = hpMoves;
    }

    public void setHp(int Hp) {
        this.hp = Hp;
    }
}
