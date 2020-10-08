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

    public void setInvisible() { visible = false; }

    public void setVisible() { visible = true; }

    public void setMaxhit(int _maxHit) { maxHit = _maxHit; }

    public void setHpMoves(int _hpMoves) {
        hpMoves = _hpMoves;
    }

    public void setHp(int _hp) { hp = _hp; }

    public void setType(char _type) {
        type = _type;
    }

    public void setIntValue(int _value) {
        value = _value;
    }

    public void setPosX(int _x) {
        posX = _x;
    }

    public void setPosY(int _y) { posY = _y; }

    public void setWidth(int _width) { width = _width; }

    public void setHeight(int _height) { height = _height; }
}
