package game.display;

import game.display.Char;
import game.displayable.creature.Player;
import game.displayable.creature.Creature;
import game.displayable.item.Item;
import game.displayable.Dungeon;
import game.displayable.creature.Monster;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.ArrayList;

// Getting keyboard inputs from player
public class KeyStrokePrinter implements InputObserver, Runnable {
    private static int DEBUG = 1;
    private static String CLASSID = "KeyStrokePrinter";
    private static final int MAX_PACK_SIZE = 10;            // Maximum number of items allowed in player pack

    private static Queue<Character> inputQueue = null;      // Queue to store successive inputs made by player
    private Dungeon dungeonBeingParsed;                     // Object reference for dungeon map being used
    private ObjectDisplayGrid displayGrid;                  // Object reference for grid to display game on terminal

    // Constructor
    public KeyStrokePrinter(ObjectDisplayGrid grid, Dungeon dungeon) {
        inputQueue = new ConcurrentLinkedQueue<>();
        dungeonBeingParsed = dungeon;
        displayGrid = grid;
        displayGrid.displayStringToTerminal("HP: " + displayGrid.getMainPlayer().getHp(), 0, 0);
    }

    // Getting keyboard character inputs
    @Override
    public void observerUpdate(char ch) {
        if (DEBUG > 0) {
            System.out.println(CLASSID + ".observerUpdate receiving character " + ch);
        }
        inputQueue.add(ch);
    }

    // Calling function that processes input for game
    @Override
    public void run() {
        displayGrid.registerInputObserver(this);
        boolean working = true;
        while (working) {
            rest();
            working = (processInput(false));
        }
    }

    public void rest() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Mapping keyboard inputs to available player functions
    public boolean processInput(boolean is_H) {
        char ch;
        boolean processing = true;

        while (processing) {
            if (inputQueue.peek() == null) {
                processing = false;
            }
            else {
                ch = inputQueue.poll();
                if (DEBUG > 1) {
                    System.out.println(CLASSID + ".processInput peek is " + ch);
                }

                int x = displayGrid.getMainPlayer().getPosX();
                int y = displayGrid.getMainPlayer().getPosY();
                char charStandingOn = displayGrid.getMainPlayer().getCharStandingOn().getChar();
                char next_ch;
                int item_number;
                boolean check = true;

                if (DEBUG > 1) {
                    System.out.println(CLASSID + ".charStandingOn is " + charStandingOn);
                    System.out.println(CLASSID + ".x is " + x);
                    System.out.println(CLASSID + ".y is " + y);
                }

                if (!displayGrid.getMainPlayer().getReceiveInput()) {
                    return false;
                }

                switch(ch) {
                    case '?':
                        displayGrid.displayStringToTerminal("Info: h,l,k,j,i,?,H,c,d,p,r,t,w,E,0-9. H <cmd> for more info", 0, displayGrid.getTotalHeight() - 1);
                        break;
                    case 'c':
                        break;
                    case 'd':
                        processing = false;
                        item_number = getNextInt();
                        Item item = displayGrid.getMainPlayer().removeFromInventory(item_number);
                        dungeonBeingParsed.addItem(item);
                        if (item == null) {
                            displayGrid.displayStringToTerminal("Info: No item to be found at position " + item_number, 0, displayGrid.getTotalHeight() - 1);
                            break;
                        }
                        item.setPosX(x);
                        item.setPosY(y);

                        displayGrid.displayStringToTerminal("Info: Item dropped " + item.getName() + " at (" + item.getPosX() + ", " + item.getPosY() + ")", 0, displayGrid.getTotalHeight() - 1);
                        displayGrid.removeObjectToDisplay(x, y);
                        displayGrid.addObjectToDisplay(new Char(item.getType()), x, y);
                        displayGrid.getMainPlayer().setCharStandingOn(displayGrid.getDisplayChar(x, y));
                        displayGrid.addObjectToDisplay(new Char('@'), x, y);
                        processing = true;
                        break;
                    case 'E':
                        processing = false;
                        next_ch = getNextChar();
                        if (next_ch == 'Y' || next_ch == 'y') {
                            displayGrid.displayStringToTerminal("Info: Ending game", 0, displayGrid.getTotalHeight() - 1);
                            displayGrid.getMainPlayer().setReceiveInput(false);
                        }
                        else {
                            processing = true;
                        }
                        break;
                    case 'i':
                        ArrayList<Item> inventory = displayGrid.getMainPlayer().getInventory();
                        String inventoryList = "";
                        int pos = 0;
                        for (Item i: inventory) {
                            inventoryList += (pos + ". " + i.getName() + "  ");
                            pos++;
                        }
                        displayGrid.displayStringToTerminal("Pack: " + inventoryList, 0, displayGrid.getTotalHeight() - 1 - 2);
                        break;
                    case 'H':
                        processing = false;
                        next_ch = getNextChar();
                        printInformation(next_ch);
                        processing = true;
                        break;
                    case 'h':
                    case 'j':
                    case 'k':
                    case 'l':
                        check = moveCharacter(ch, x, y, charStandingOn);
                        displayGrid.checkHallucinate();
                        if (!check) {
                            processing = false;
                            return false;
                        }
                        break;
                    case 'p':
                        if (charStandingOn == ')' || charStandingOn == ']' || charStandingOn == '?') {
                            Item itemPicked = dungeonBeingParsed.getItem(x, y, charStandingOn);
                            dungeonBeingParsed.removeItem(itemPicked);
                            if (itemPicked != null) {
                                displayGrid.displayStringToTerminal("Info: Item picked " + itemPicked.getName(), 0, displayGrid.getTotalHeight() - 1);
                                displayGrid.getMainPlayer().addToInventory(itemPicked);
                                displayGrid.removeObjectToDisplay(x, y);
                                displayGrid.removeObjectToDisplay(x, y);
                                displayGrid.getMainPlayer().setCharStandingOn(displayGrid.getDisplayChar(x, y));
                                displayGrid.addObjectToDisplay(new Char('@'), x, y);
                            }
                        }
                        else {
                            displayGrid.displayStringToTerminal("Info: No item to be picked", 0, displayGrid.getTotalHeight() - 1);
                        }
                        break;
                    case 'r':
                        processing = false;
                        item_number = getNextInt();
                        displayGrid.getMainPlayer().readScroll(item_number);
                        processing = true;
                        break;
                    case 't':
                        processing = false;
                        item_number = getNextInt();
                        displayGrid.getMainPlayer().equipWeapon(item_number);
                        processing = true;
                        break;
                    case 'w':
                        processing = false;
                        item_number = getNextInt();
                        displayGrid.getMainPlayer().equipArmor(item_number);
                        processing = true;
                        break;
                    case '0':
                    case '1':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        break;
                    default:
                        System.out.println("Invalid character " + ch);
                }
            }
        }
        return true;
    }

    public boolean moveCharacter(char move, int x, int y, char charStandingOn) {
        int nextX = -1;
        int nextY = -1;

        switch(move) {
            case 'h':
                nextX = x - 1;
                nextY = y;
                break;
            case 'j':
                nextX = x;
                nextY = y + 1;
                break;
            case 'k':
                nextX = x;
                nextY = y - 1;
                break;
            case 'l':
                nextX = x + 1;
                nextY = y;
                break;
            default:
                System.out.println("Invalid player movement");
                System.out.println("Usage: 'h' - move left, 'j' - move down, 'k' - move up, 'l' - move right");
                return false;
        }

        char nextCh = displayGrid.getDisplayChar(nextX, nextY).getChar();
        boolean setHp = false;

        if (nextCh != 'X') {
            if (nextCh == 'H' || nextCh == 'S' || nextCh == 'T') {
                boolean checkingForGameEnd = performAttack(nextX, nextY, x, y);
                if(checkingForGameEnd == false){
                    return false;
                }
            }
            else if (charStandingOn != '#' && charStandingOn != ' ') {
                displayGrid.removeObjectToDisplay(x,y);
                displayGrid.getMainPlayer().setCharStandingOn(displayGrid.getDisplayChar(nextX, nextY));
                displayGrid.addObjectToDisplay(new Char('@'), nextX, nextY);
                displayGrid.getMainPlayer().setPosX(nextX);
                displayGrid.getMainPlayer().setPosY(nextY);
                setHp = displayGrid.getMainPlayer().setMoves();
            }
            else {
                if (nextCh != '.' && nextCh != ' ' ) {
                    displayGrid.removeObjectToDisplay(x,y);
                    displayGrid.getMainPlayer().setCharStandingOn(displayGrid.getDisplayChar(nextX, nextY));
                    displayGrid.addObjectToDisplay(new Char('@'), nextX, nextY);
                    displayGrid.getMainPlayer().setPosX(nextX);
                    displayGrid.getMainPlayer().setPosY(nextY);
                    setHp = displayGrid.getMainPlayer().setMoves();
                }
            }
        }

        if (setHp) {
            displayGrid.writeHp(displayGrid.getMainPlayer().getHp());
        }
        return true;
    }

    public char getNextChar() {
        char ch = ' ';
        boolean processing = false;

        while (!processing) {
            if (inputQueue.peek() == null) {
                processing = false;
            } else {
                ch = inputQueue.poll();
                processing = true;
            }
        }
        return ch;
    }

    public int getNextInt() {
        int item_number = -1;
        boolean processing = false;

        while (!processing) {
            if (inputQueue.peek() == null) {
                processing = false;
            } else {
                item_number = (int) (inputQueue.poll() - '0');
                processing = true;
            }
        }

        if (item_number < 0 || item_number >= MAX_PACK_SIZE) {
            displayGrid.displayStringToTerminal("Info: Invalid input. Item number must be between 0-" + (MAX_PACK_SIZE - 1), 0, displayGrid.getTotalHeight() - 1);
        }
        return item_number;
    }

    public void printInformation(char ch) {
        switch(ch) {
            case '?':
                displayGrid.displayStringToTerminal("Info: '" + ch + "' lists all available commands", 0, displayGrid.getTotalHeight() - 1);
                break;
            case 'c':
                displayGrid.displayStringToTerminal("Info: '" + ch + "' take off armor and place in the pack", 0, displayGrid.getTotalHeight() - 1);
                break;
            case 'd':
                displayGrid.displayStringToTerminal("Info: '" + ch + "<integer>' drop item from item number in the pack", 0, displayGrid.getTotalHeight() - 1);
                break;
            case 'E':
                displayGrid.displayStringToTerminal("Info: '" + ch + "<Y/y>' end game", 0, displayGrid.getTotalHeight() - 1);
                break;
            case 'i':
                displayGrid.displayStringToTerminal("Info: '" + ch + "' show contents of the pack", 0, displayGrid.getTotalHeight() - 1);
                break;
            case 'H':
                displayGrid.displayStringToTerminal("Info: '" + ch + "<next input>' gives more information about the next input character", 0, displayGrid.getTotalHeight() - 1);
                break;
            case 'h':
                displayGrid.displayStringToTerminal("Info: '" + ch + "' move left 1 space", 0, displayGrid.getTotalHeight() - 1);
                break;
            case 'j':
                displayGrid.displayStringToTerminal("Info: '" + ch + "' move down 1 space", 0, displayGrid.getTotalHeight() - 1);
                break;
            case 'k':
                displayGrid.displayStringToTerminal("Info: '" + ch + "' move up 1 space", 0, displayGrid.getTotalHeight() - 1);
                break;
            case 'l':
                displayGrid.displayStringToTerminal("Info: '" + ch + "' move right 1 space", 0, displayGrid.getTotalHeight() - 1);
                break;
            case 'p':
                displayGrid.displayStringToTerminal("Info: '" + ch + "' pick up item under player and put into the pack", 0, displayGrid.getTotalHeight() - 1);
                break;
            case 'r':
                displayGrid.displayStringToTerminal("Info: '" + ch + "<integer>' read the scroll from the item number in the pack", 0, displayGrid.getTotalHeight() - 1);
                break;
            case 't':
                displayGrid.displayStringToTerminal("Info: '" + ch + "<integer>' equip weapon from the item number in the pack", 0, displayGrid.getTotalHeight() - 1);
                break;
            case 'w':
                displayGrid.displayStringToTerminal("Info: '" + ch + "<integer>' equip armor from the item number in the pack", 0, displayGrid.getTotalHeight() - 1);
                break;
            default:
                displayGrid.displayStringToTerminal("Info: Invalid input. Valid characters: h,l,k,j,i,?,H,c,d,p,r,t,w,E,0-9", 0, displayGrid.getTotalHeight() - 1);
        }
    }

    public boolean performAttack(int creature_x, int creature_y, int player_x, int player_y) {
        Player player = displayGrid.getMainPlayer();
        Monster monster = (Monster) dungeonBeingParsed.getMonster(creature_x, creature_y);
        int hp_for_monster = monster.getHp();
        int hp_for_player = player.getHp();

        displayGrid.displayStringToTerminal("HP: " + hp_for_player, 0, 0);
        if (DEBUG > 1) {
            System.out.println("HP Monster: "+ hp_for_monster + " Player HP: "+ hp_for_player);
        }

        int damageMonster = monster.performBeingHitActions(player);
        displayGrid.displayStringToTerminal("Info: Damage to monster " + monster.getType() + " is " + damageMonster + " HP", 0, displayGrid.getTotalHeight() - 1);

        if (monster.getHp() > 0) {
            int playerDamage = player.performBeingHitActions(monster);
            displayGrid.displayStringToTerminal("Info: Damage to player is " + playerDamage + " HP", 0, displayGrid.getTotalHeight() - 1);
        }
        if(monster.getHp() < 0){
            displayGrid.removeObjectToDisplay(creature_x, creature_y);
            displayGrid.removeObjectToDisplay(creature_x, creature_y);
            displayGrid.addObjectToDisplay(new Char('.'), creature_x, creature_y);
        }
        return true;
    }
}
