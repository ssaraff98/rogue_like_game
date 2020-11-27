package game.action.creatureAction;

import game.displayable.creature.Creature;
import game.display.ObjectDisplayGrid;
import game.display.Char;

public class Teleport extends CreatureAction {
    private String name;
    private Creature owner;

    public Teleport(String _name, Creature _owner) {
        super(_owner);
        name = _name;
        owner = _owner;
    }
    @Override
    public void performAction() {

        System.out.println("Teleport action IMPLEMENTED"+this.owner.getPosX()+this.owner.getPosY());
        ObjectDisplayGrid displayGrid;
        displayGrid = ObjectDisplayGrid.getObjectDisplayGrid(0,0,0,0);
        displayGrid.removeObjectToDisplay(this.owner.getPosX(), this.owner.getPosY());
        displayGrid.removeObjectToDisplay(this.owner.getPosX(), this.owner.getPosY());
        displayGrid.addObjectToDisplay(new Char('.'), this.owner.getPosX(), this.owner.getPosY());
        displayGrid.addObjectToDisplay(new Char(this.owner.getType()), this.owner.getPosX()+1, this.owner.getPosY()+1);

//        displayGrid.removeObjectToDisplay(this.owner.getPosX(),this.owner.getPosY());
    }
}