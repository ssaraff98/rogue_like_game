public class Armor extends Item {
    private String name;
    private int room;
    private int serial;

    public Armor(String _name) { name = _name;
        System.out.println("Armor Name: " + _name);
    }

    public void setID(int _room, int _serial) {
        this.room = _room;
        this.serial = _serial;
        System.out.println("Armor room: " + _room);

        System.out.println("Action serial: " + _serial);
    }
}
