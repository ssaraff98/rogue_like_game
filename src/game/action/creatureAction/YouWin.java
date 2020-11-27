package game.action.creatureAction;

import game.displayable.creature.Creature;

public class YouWin extends CreatureAction {
    private String name;
    private Creature owner;

    public YouWin(String _name, Creature _owner) {
        super(_owner);
        name = _name;
        owner = _owner;
    }

    @Override
    public void performAction() {
        System.out.println(getMessage());
        System.out.println("You Win Implemented");

    }
}