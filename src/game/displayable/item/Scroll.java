package game.displayable.item;

public class Scroll extends Item {
    private String name;
    private int room;
    private int serial;
    private boolean read;

    public Scroll(String _name) {
        this.name = _name;
        this.read = false;
    }

    public String getName() { return this.name; }

    public void setID(int _room, int _serial) {
        this.room = _room;
        this.serial = _serial;
    }

    public int getRoom() { return this.room; }

    public boolean getRead() { return this.read; }

    public void setRead() { this.read = true; }
}
