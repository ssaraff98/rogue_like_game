public class BlessCurseOwner extends ItemAction {
    private Item owner;

    public BlessCurseOwner(Item _owner) {
        super(_owner);
        owner = _owner;

        System.out.println("BlessCurser Owner: " + _owner);
    }
}