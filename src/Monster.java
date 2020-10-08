public class Monster extends Creature {
    private String name;
    private int room;
    private int serial;

    public void setName(String _name) { name = _name; }

    public void setID(int _room, int _serial) {
        room = _room;
        serial = _serial;
    }
}
