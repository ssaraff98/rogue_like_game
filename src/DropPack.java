public class DropPack extends CreatureAction {
    private String name;
    private Creature owner;

    public DropPack(String _name, Creature _owner) {
        super(_owner);
        name = _name;
        owner = _owner;
    }
}