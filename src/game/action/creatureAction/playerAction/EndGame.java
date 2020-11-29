package game.action.creatureAction.playerAction;
import game.action.creatureAction.CreatureAction;
import game.action.Action;
import game.displayable.creature.Creature;
import game.displayable.creature.Player;
import game.display.ObjectDisplayGrid;
import game.display.Char;

public class EndGame extends CreatureAction {
    private String name;
    private Creature owner;
    private Player owner2;

    public EndGame(String _name, Creature _owner) {
        super(_owner);
        name = _name;
        owner2 = (Player)_owner;

        System.out.println("Endgame Name: " + _name + " Owner: "+owner2);
    }

    @Override
    public void performAction() {
        ObjectDisplayGrid displayGrid;
        displayGrid = ObjectDisplayGrid.getObjectDisplayGrid(0,0,0,0);
        System.out.println("ENDING GAME INVOKED");
//        displayGrid.displayStringToTerminal(getMessage(), 0, displayGrid.getTotalHeight() - 1);
        displayGrid.removeObjectToDisplay(this.owner2.getPosX(), this.owner2.getPosY());
        // System.exit(0);
//        displayGrid.removeObjectToDisplay(this.owner.getPosX(),this.owner.getPosY());
    }
}