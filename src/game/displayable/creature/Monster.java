package game.displayable.creature;

public class Monster extends Creature {
    private String name;
    private int room;
    private int serial;

    public void setName(String _name) { this.name = _name; }

    public void setID(int _room, int _serial) {
        this.room = _room;
        this.serial = _serial;
    }
}
