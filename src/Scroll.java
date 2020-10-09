public class Scroll extends Item {
    private String name;
    private int room;
    private int serial;

    public Scroll(String _name) { name = _name; }

    public void setID(int _room, int _serial) {
        this.room = _room;
        this.serial = _serial;
    }
}
