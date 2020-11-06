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
                return checkInputCharacter(ch, is_H);
            }
        }
        return true;
    }

    public boolean checkInputCharacter(char ch, boolean is_H) {
        int x = displayGrid.getMainPlayer().getPosX();
        int y = displayGrid.getMainPlayer().getPosY();
        char charStandingOn = displayGrid.getMainPlayer().getCharStandingOn().getChar();
        boolean check = false;

        switch(ch) {
            case 'X':
                System.out.println("got an X, ending input checking");
                break;
            case '?':
                displayGrid.displayStringToTerminal("Info: h,l,k,j,i,?,H,c,d,p,R,T,w,E,0-9. H <cmd> for more info", 0, displayGrid.getTotalHeight() - 1);
                break;
            case 'c':
                break;
            case 'd':
                break;
            case 'i':
                break;
            case 'H':
                processInput(true);
                break;
            case 'h':
            case 'j':
            case 'k':
            case 'l':
                check = moveCharacter(ch, x, y, charStandingOn, is_H);
                break;
            default:
                System.out.println("Character " + ch + " entered on the keyboard");
        }
        return check;
    }

    public boolean moveCharacter(char move, int x, int y, char charStandingOn, boolean is_H) {
        int nextX = -1;
        int nextY = -1;

        switch(move) {
            case 'h':
                if (is_H) {
                    displayGrid.displayStringToTerminal("Info: h: move left 1 space", 0, displayGrid.getTotalHeight() - 1);
                }
                nextX = x - 1;
                nextY = y;
                break;
            case 'j':
                if (is_H) {
                    displayGrid.displayStringToTerminal("Info: h: move down 1 space", 0, displayGrid.getTotalHeight() - 1);
                }
                nextX = x;
                nextY = y + 1;
                break;
            case 'k':
                if (is_H) {
                    displayGrid.displayStringToTerminal("Info: h: move up 1 space", 0, displayGrid.getTotalHeight() - 1);
                }
                nextX = x;
                nextY = y - 1;
                break;
            case 'l':
                if (is_H) {
                    displayGrid.displayStringToTerminal("Info: h: move right 1 space", 0, displayGrid.getTotalHeight() - 1);
                }
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
}
