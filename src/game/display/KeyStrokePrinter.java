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

    public void rest() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean processInput() {

        char ch;

        boolean processing = true;
        while (processing) {
            if (inputQueue.peek() == null) {
                processing = false;
            } else {
                ch = inputQueue.poll();
                if (DEBUG > 1) {
                    System.out.println(CLASSID + ".processInput peek is " + ch);
                }
                int charXPos = displayGrid.getMainPlayer().getPosX();
                int charYPos = displayGrid.getMainPlayer().getPosY();
                char charStandingOn = displayGrid.getMainPlayer().getCharStandingOn().getChar();

                if (ch == 'X') {
                    System.out.println("got an X, ending input checking");
                    return false;
                 /*
                Logic for moving player:
                Check ahead to see if there is a wall
                If there is no wall, see if the space ahead is available to walk on
                Restore the char the player was currently on and store the char the player is about to move to
                Move player and update position
                */
            } else if (ch == 'h') {
                System.out.println("move left");

                if (displayGrid.getDisplayChar(charXPos - 1, charYPos).getChar() != 'X') {
                    if (charStandingOn != '#' && charStandingOn!=' ') {
                        displayGrid.addObjectToDisplay(new Char(charStandingOn), charXPos, charYPos);
                        displayGrid.getMainPlayer().setCharStandingOn(displayGrid.getDisplayChar(charXPos - 1, charYPos));
                        displayGrid.addObjectToDisplay(new Char('@'), charXPos - 1, charYPos);
                        displayGrid.getMainPlayer().setPosX(charXPos - 1);
                        System.out.println("Char standing on: " + displayGrid.getMainPlayer().getCharStandingOn().getChar());
                    } else {
                        if (displayGrid.getDisplayChar(charXPos - 1, charYPos).getChar() != '.' && displayGrid.getDisplayChar(charXPos - 1, charYPos).getChar() != ' ') {
                            displayGrid.addObjectToDisplay(new Char(charStandingOn), charXPos, charYPos);
                            displayGrid.getMainPlayer().setCharStandingOn(displayGrid.getDisplayChar(charXPos - 1, charYPos));
                            displayGrid.addObjectToDisplay(new Char('@'), charXPos - 1, charYPos);
                            displayGrid.getMainPlayer().setPosX(charXPos - 1);
                            System.out.println("Char standing on: " + displayGrid.getMainPlayer().getCharStandingOn().getChar());
                        }
                    }
                }

            } else if (ch == 'l') {
                System.out.println("move right");

                if (displayGrid.getDisplayChar(charXPos + 1, charYPos).getChar() != 'X') {
                    if (charStandingOn != '#' && charStandingOn != ' ') {
                        displayGrid.addObjectToDisplay(new Char(charStandingOn), charXPos, charYPos);
                        displayGrid.getMainPlayer().setCharStandingOn(displayGrid.getDisplayChar(charXPos + 1, charYPos));
                        displayGrid.addObjectToDisplay(new Char('@'), charXPos + 1, charYPos);
                        displayGrid.getMainPlayer().setPosX(charXPos + 1);
                        System.out.println("Char standing on: " + displayGrid.getMainPlayer().getCharStandingOn().getChar());
                    } else {
                        if (displayGrid.getDisplayChar(charXPos + 1, charYPos).getChar() != '.' && displayGrid.getDisplayChar(charXPos + 1, charYPos).getChar() != ' ' ) {
                            displayGrid.addObjectToDisplay(new Char(charStandingOn), charXPos, charYPos);
                            displayGrid.getMainPlayer().setCharStandingOn(displayGrid.getDisplayChar(charXPos + 1, charYPos));
                            displayGrid.addObjectToDisplay(new Char('@'), charXPos + 1, charYPos);
                            displayGrid.getMainPlayer().setPosX(charXPos + 1);
                            System.out.println("Char standing on: " + displayGrid.getMainPlayer().getCharStandingOn().getChar());
                        }
                    }
                }

            } else if (ch == 'k') {
                System.out.println("move up");



                if (displayGrid.getDisplayChar(charXPos, charYPos - 1).getChar() != 'X') {
                    if (charStandingOn != '#' && charStandingOn!=' ') {
                        displayGrid.addObjectToDisplay(new Char(charStandingOn), charXPos, charYPos);
                        displayGrid.getMainPlayer().setCharStandingOn(displayGrid.getDisplayChar(charXPos, charYPos - 1));
                        displayGrid.addObjectToDisplay(new Char('@'), charXPos, charYPos - 1);
                        displayGrid.getMainPlayer().setPosY(charYPos - 1);
                        System.out.println("Char standing on: " + displayGrid.getMainPlayer().getCharStandingOn().getChar());
                    } else {
                        if (displayGrid.getDisplayChar(charXPos, charYPos - 1).getChar() != '.' && displayGrid.getDisplayChar(charXPos, charYPos - 1).getChar() != ' ') {
                            displayGrid.addObjectToDisplay(new Char(charStandingOn), charXPos, charYPos);
                            displayGrid.getMainPlayer().setCharStandingOn(displayGrid.getDisplayChar(charXPos, charYPos - 1));
                            displayGrid.addObjectToDisplay(new Char('@'), charXPos, charYPos - 1);
                            displayGrid.getMainPlayer().setPosY(charYPos - 1);
                            System.out.println("Char standing on: " + displayGrid.getMainPlayer().getCharStandingOn().getChar());
                        }
                    }
                }

            } else if (ch == 'j') {
                System.out.println("move down");


                if (displayGrid.getDisplayChar(charXPos, charYPos + 1).getChar() != 'X') {
                    if (charStandingOn != '#' && charStandingOn!=' ') {
                        displayGrid.addObjectToDisplay(new Char(charStandingOn), charXPos, charYPos);
                        displayGrid.getMainPlayer().setCharStandingOn(displayGrid.getDisplayChar(charXPos, charYPos + 1));
                        displayGrid.addObjectToDisplay(new Char('@'), charXPos, charYPos + 1);
                        displayGrid.getMainPlayer().setPosY(charYPos + 1);
                        System.out.println("Char standing on: " + displayGrid.getMainPlayer().getCharStandingOn().getChar());
                    } else {
                        if (displayGrid.getDisplayChar(charXPos, charYPos + 1).getChar() != '.' && displayGrid.getDisplayChar(charXPos, charYPos + 1).getChar() != ' ') {
                            displayGrid.addObjectToDisplay(new Char(charStandingOn), charXPos, charYPos);
                            displayGrid.getMainPlayer().setCharStandingOn(displayGrid.getDisplayChar(charXPos, charYPos +1));
                            displayGrid.addObjectToDisplay(new Char('@'), charXPos, charYPos + 1);
                            displayGrid.getMainPlayer().setPosY(charYPos + 1);
                            System.out.println("Char standing on: " + displayGrid.getMainPlayer().getCharStandingOn().getChar());
                        }
                    }
                }
            } else {
                System.out.println("character " + ch + " entered on the keyboard");
            }
        }
    }
        return true;
}

    @Override
    public void run() {
        displayGrid.registerInputObserver(this);
        boolean working = true;
        while (working) {
            rest();
            working = (processInput( ));
        }
    }
}
