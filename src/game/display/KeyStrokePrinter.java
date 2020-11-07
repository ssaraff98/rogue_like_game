package game.display;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import game.displayable.creature.Player;
import game.display.Char;

public class KeyStrokePrinter implements InputObserver, Runnable {

    private static int DEBUG = 1;
    private static String CLASSID = "KeyStrokePrinter";
    private static Queue<Character> inputQueue = null;
    private ObjectDisplayGrid displayGrid;

    public KeyStrokePrinter(ObjectDisplayGrid grid) {
        inputQueue = new ConcurrentLinkedQueue<>();
        displayGrid = grid;
    }

    @Override
    public void observerUpdate(char ch) {
        if (DEBUG > 0) {
            System.out.println(CLASSID + ".observerUpdate receiving character " + ch);
        }
        inputQueue.add(ch);
    }

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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

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
                boolean check = true;

                switch(ch) {
                    case 'X':
                        System.out.println("got an X, ending input checking");
                        break;
                    case '?':
                        displayGrid.displayStringToTerminal("Info: h,l,k,j,i,?,H,c,d,p,r,t,w,E,0-9. H <cmd> for more info", 0, displayGrid.getTotalHeight() - 1);
                        break;
                    case 'c':
                        break;
                    case 'd':
                        break;
                    case 'E':
                        break;
                    case 'i':
                        break;
                    case 'H':
                        processing = false;
                        boolean printed = false;
                        while (!printed) {
                            char next_ch = getNextInput();
                            printed = printInformation(next_ch);
                        }
                        processing = true;
                        break;
                    case 'h':
                    case 'j':
                    case 'k':
                    case 'l':
                        check = moveCharacter(ch, x, y, charStandingOn);
                        if (!check) {
                            return false;
                        }
                        break;
                    case 'p':
                        // displayGrid.getMainPlayer().addToInventory(itemPicked);
                        break;
                    case 'r':
                        break;
                    case 't':
                        break;
                    case 'w':
                        break;
                    default:
                        System.out.println("Character " + ch + " entered on the keyboard");
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

        if (displayGrid.getDisplayChar(nextX, nextY).getChar() != 'X') {
            if (charStandingOn != '#' && charStandingOn != ' ') {
                displayGrid.addObjectToDisplay(new Char(charStandingOn), x, y);
                displayGrid.getMainPlayer().setCharStandingOn(displayGrid.getDisplayChar(nextX, nextY));
                displayGrid.addObjectToDisplay(new Char('@'), nextX, nextY);
                displayGrid.getMainPlayer().setPosX(nextX);
                displayGrid.getMainPlayer().setPosY(nextY);
            }
            else {
                if (displayGrid.getDisplayChar(nextX, nextY).getChar() != '.' && displayGrid.getDisplayChar(nextX, nextY).getChar() != ' ' ) {
                    displayGrid.addObjectToDisplay(new Char(charStandingOn), x, y);
                    displayGrid.getMainPlayer().setCharStandingOn(displayGrid.getDisplayChar(nextX, nextY));
                    displayGrid.addObjectToDisplay(new Char('@'), nextX, nextY);
                    displayGrid.getMainPlayer().setPosX(nextX);
                    displayGrid.getMainPlayer().setPosY(nextY);
                }
            }
        }
        return true;
    }

    public char getNextInput() {
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

    public boolean printInformation(char ch) {
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
                return false;
        }
        return true;
    }
}
