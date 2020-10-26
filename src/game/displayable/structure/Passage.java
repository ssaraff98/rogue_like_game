package game.displayable.structure;

public class Passage extends Structure {
    private String name;
    private int room1;
    private int room2;

    public void setName(String _name) { this.name = _name; }

    public void setID(int _room1, int _room2) {
        this.room1 = _room1;
        this.room2 = _room2;
    }
}
