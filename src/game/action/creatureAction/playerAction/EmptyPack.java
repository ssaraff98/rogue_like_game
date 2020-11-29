package game.action.creatureAction.playerAction;

import game.action.creatureAction.CreatureAction;
import game.displayable.creature.Creature;
import game.displayable.creature.Player;
import game.action.Action;
import game.display.ObjectDisplayGrid;
import game.display.Char;
import game.displayable.item.Item;

public class EmptyPack extends CreatureAction {
    private String name;
    private Creature owner;
    private Player owner2;

    public EmptyPack(String _name, Creature _owner) {
        super(_owner);
        name = _name;
        owner2 = (Player)_owner;
        System.out.println("EmptyPack Name:" + _name + " EmptyPack owner:"+_owner);
    }
    public void performAction(){
        System.out.println("EmptyPack...................");
        int x = this.owner2.getPosX();
        int y = this.owner2.getPosY();
        ObjectDisplayGrid displayGrid;
        displayGrid = ObjectDisplayGrid.getObjectDisplayGrid(0,0,0,0);
//        int item_number = 0;
        int sizeofInv = this.owner2.getInventory().size();
        if(sizeofInv == 0){
            System.out.println("size is 0");
        }
        else {
            for (int item_number = 0; item_number < sizeofInv + 1; item_number++) {
                Item item = this.owner2.removeFromInventory(item_number);
                if (item == null) {
                    displayGrid.displayStringToTerminal("Info: No item to be found at position " + item_number, 0, displayGrid.getTotalHeight() - 1);
                } else {
                    item.setPosX(x);
                    item.setPosY(y);

                    System.out.println("ITEM DROPPED FROM ACTION");
                    displayGrid.displayStringToTerminal("Info: Item dropped " + item.getName() + " at (" + item.getPosX() + ", " + item.getPosY() + ")", 0, displayGrid.getTotalHeight() - 1);
                    displayGrid.removeObjectToDisplay(x, y);
                    displayGrid.addObjectToDisplay(new Char(item.getType()), x, y);
                    displayGrid.getMainPlayer().setCharStandingOn(displayGrid.getDisplayChar(x, y));
                    displayGrid.addObjectToDisplay(new Char('@'), x, y);
                }
            }
        }
    }
}