public class Hallucinate extends ItemAction {
    private Item owner;

    public Hallucinate(Item _owner) {
        super(_owner);
        owner = _owner;
        System.out.println("Hallucinate Owner: " + _owner);
    }
}