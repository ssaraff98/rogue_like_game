package game.displayable;

import java.util.Random;

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

    public void setHeight(int _height) {
        this.height = _height;
        // System.out.println("Height: " + _height);
    }

    public int getHeight() {
        return this.height;
    }

    public void setHp(int _hp) {
        this.hp = _hp;
        System.out.println("Hitpoint: " + _hp);
    }

    public int getHp() {
        return this.hp;
    }

    public void setHpMoves(int _hpMoves) {
        System.out.println("this is the setHP: "+ _hpMoves);

        this.hpMoves = _hpMoves;
//        System.out.println("HpMoves: " + hpMoves);
    }

    public int getHpMoves() {
        System.out.println("whats coming in: "+this.hpMoves);
        return this.hpMoves;
    }

    public void setIntValue(int _value) {
        this.value = _value;
        // System.out.println("Value: " + value);
    }

    public int getIntValue() {
        return this.value;
    }

    public void setMaxhit(int _maxHit) {
        this.maxHit = _maxHit;
        // System.out.println("MaxHit: " + _maxHit);
    }

    public int getMaxHit() {
        return this.maxHit;
    }

    public void setPosX(int _x) {
        this.posX = _x;
        // System.out.println("PosX: " + _x);
    }

    public int getPosX() {
        return this.posX;
    }

    public void setPosY(int _y) {
        this.posY = _y;
        // System.out.println("PosY: " + _y);
    }

    public int getPosY() {
        return this.posY;
    }

    public void setType(char _type) {
        this.type = _type;
        // System.out.println("Type: " + _type);
    }

    public char getType() {
        return this.type;
    }

    public void setInvisible() {
        this.visible = false;
        // System.out.println("Visible: " + false);
    }

    public void setVisible() {
        this.visible = true;
        // System.out.println("Visible: " + true);
    }

    public boolean getVisible() {
        return this.visible;
    }

    public void setWidth(int _width) {
        this.width = _width;
        // System.out.println("Width: " + _width);
    }

    public int getWidth() {
        return this.width;
    }

    public int getRandom(int _maxHit) {
        Random rand = new Random();
        int damage = rand.nextInt(_maxHit + 1);
        return damage;
    }
}
