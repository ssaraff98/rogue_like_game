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

    public void setInvisible() { this.visible = false;
        System.out.println("Visible: " + false);
    }

    public void setVisible() { this.visible = true;
        System.out.println("Visible: " + true);
    }

    public void setMaxhit(int _maxHit) { this.maxHit = _maxHit;
        System.out.println("MaxHit: " + _maxHit);
    }

    public void setHpMoves(int _hpMoves) {
        this.hpMoves = _hpMoves;
        System.out.println("HpMoves: " + hpMoves);
    }

    public void setHp(int _hp) { this.hp = _hp;
        System.out.println("Hitpoint: " + _hp);
    }

    public void setType(char _type) {
        this.type = _type;
        System.out.println("Type: " + _type);
    }

    public void setIntValue(int _value) {
        this.value = _value;
        System.out.println("Value: " + value);

    }

    public void setPosX(int _x) {
        this.posX = _x;
        System.out.println("PosX: " + _x);

    }

    public void setPosY(int _y) { this.posY = _y;
        System.out.println("PosY: " + _y);
    }

    public void setWidth(int _width) { this.width = _width;
        System.out.println("Width: " + _width);
    }

    public void setHeight(int _height) { this.height = _height;
        System.out.println("Height: " + _height);
    }
}
