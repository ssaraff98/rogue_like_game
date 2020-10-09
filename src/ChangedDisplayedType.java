public class ChangedDisplayedType extends CreatureAction {
    private String name;
    private Creature owner;

    public ChangedDisplayedType(String _name, Creature _owner) {
        super(_owner);
        name = _name;
        owner = _owner;
    }
}