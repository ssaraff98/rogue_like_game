package game.action.creatureAction;

import game.display.ObjectDisplayGrid;
import game.display.Char;
import game.displayable.creature.Creature;

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
        ObjectDisplayGrid displayGrid;
        displayGrid = ObjectDisplayGrid.getObjectDisplayGrid(0,0,0,0);
        ArrayList<Integer> x_pos = displayGrid.xValues;
        ArrayList<Integer> y_pos = displayGrid.yValues;
        Random randNum = new Random();

        System.out.println(getMessage());
        displayGrid.displayStringToTerminal("Info: " + getMessage(), 0, displayGrid.getTotalHeight() - 1);

        displayGrid.removeObjectToDisplay(this.owner.getPosX(), this.owner.getPosY());
        displayGrid.removeObjectToDisplay(this.owner.getPosX(), this.owner.getPosY());
        displayGrid.addObjectToDisplay(new Char('.'), this.owner.getPosX(), this.owner.getPosY());

        int x = -1;
        int y = -1;

        do {
            int i = randNum.nextInt(x_pos.size());
            x = x_pos.get(i);
            y = y_pos.get(i);
            System.out.println("x: " + x + " y:" + y);
        }
        while (!displayGrid.checkPosition(x, y));

        if (x == -1 && y == -1) {
            System.out.println("Could not find position to teleport monster");
            return;
        }

        displayGrid.addObjectToDisplay(new Char(this.owner.getType()), x, y);
        this.owner.setPosX(x);
        this.owner.setPosY(y);
    }
}