public class EndGame extends CreatureAction {
    private String name;
    private Creature owner;

    public EndGame(String _name, Creature _owner) {
        super(_owner);
        name = _name;
        owner = _owner;
    }
}