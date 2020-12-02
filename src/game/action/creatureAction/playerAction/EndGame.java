package game.action.creatureAction.playerAction;

import game.action.creatureAction.CreatureAction;
import game.action.Action;
import game.display.ObjectDisplayGrid;
import game.display.Char;
import game.displayable.creature.Creature;
import game.displayable.creature.Player;

public class EndGame extends CreatureAction {
    private String name;
    private Creature owner;
    private Player owner2;

    public EndGame(String _name, Creature _owner) {
        super(_owner);
        name = _name;
        owner2 = (Player)_owner;
    }

    @Override
    public void performAction() {
        ObjectDisplayGrid displayGrid;
        displayGrid = ObjectDisplayGrid.getObjectDisplayGrid(0,0,0,0);
        owner2.setReceiveInput(false);
    }
}