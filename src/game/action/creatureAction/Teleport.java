package game.action.creatureAction;

import game.displayable.creature.Creature;
import game.display.ObjectDisplayGrid;
import game.display.Char;
import java.util.ArrayList;
import java.util.Random;

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
        System.out.println("Teleport action IMPLEMENTED"+ this.owner.getPosX() + this.owner.getPosY());
        ObjectDisplayGrid displayGrid;
        displayGrid = ObjectDisplayGrid.getObjectDisplayGrid(0,0,0,0);
        System.out.println(getMessage());
        displayGrid.displayStringToTerminal("Info: "+getMessage(), 0, displayGrid.getTotalHeight() - 1);
        displayGrid.removeObjectToDisplay(this.owner.getPosX(), this.owner.getPosY());
        displayGrid.removeObjectToDisplay(this.owner.getPosX(), this.owner.getPosY());
        displayGrid.addObjectToDisplay(new Char('.'), this.owner.getPosX(), this.owner.getPosY());
        ArrayList<Integer> xValues = displayGrid.xValues;
        ArrayList<Integer> yValues = displayGrid.yValues;
        int lengthoflist = xValues.size();
        Random randNum = new Random();
        int nxt = randNum.nextInt(lengthoflist);
        int Xval = xValues.get(nxt);
        int Yval = yValues.get(nxt);
        System.out.println("XYVal " + Xval+"  "+Yval);
        displayGrid.addObjectToDisplay(new Char(this.owner.getType()),Xval, Yval);
        this.owner.setPosX(Xval);
        this.owner.setPosY(Yval);
    }
}