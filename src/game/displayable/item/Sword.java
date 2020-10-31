package game.displayable.item;

public class Sword extends Item {
    private String name;
    private int room;
    private int serial;

    public Sword(String _name) { this.name = _name; }

    public String getName() { return this.name; }

    public void setID(int _room, int _serial) {
        this.room = _room;
        this.serial = _serial;
    }

    public int getRoom() { return this.room; }
}
