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

    public void setInvisible() { this.visible = false; }

    public void setVisible() { this.visible = true; }

    public void setMaxhit(int _maxHit) { this.maxHit = _maxHit; }

    public void setHpMoves(int _hpMoves) {
        this.hpMoves = _hpMoves;
    }

    public void setHp(int _hp) { this.hp = _hp; }

    public void setType(char _type) {
        this.type = _type;
    }

    public void setIntValue(int _value) {
        this.value = _value;
    }

    public void setPosX(int _x) {
        this.posX = _x;
    }

    public void setPosY(int _y) { this.posY = _y; }

    public void setWidth(int _width) { this.width = _width; }

    public void setHeight(int _height) { this.height = _height; }
}
