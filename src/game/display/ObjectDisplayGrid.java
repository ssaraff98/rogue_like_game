package game.display;

import game.display.Char;
import game.displayable.Displayable;
import game.displayable.Dungeon;
import game.displayable.structure.Passage;
import game.displayable.structure.Room;
import game.displayable.creature.Player;
import game.displayable.creature.Monster;

import asciiPanel.AsciiPanel;
import java.io.IOException;
import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Random;

public class ObjectDisplayGrid extends JFrame implements KeyListener, InputSubject {
    private static final int DEBUG = 0;
    private static final String CLASSID = ".ObjectDisplayGrid";
    private final char[] displayableCharacters = {'#', '.', '+', 'S', 'T', 'H', '@', ']', ')', '?', 'X'};

    private static AsciiPanel terminal;
    private Stack<Char>[][] objectGrid = null;

    private List<InputObserver> inputObservers = null;
    public ArrayList<Integer> xValues = new ArrayList<Integer>();
    public ArrayList<Integer> yValues = new ArrayList<Integer>();

    private static Player mainPlayer;
    private static Monster mainMonster;
    private static ObjectDisplayGrid displayGrid = null;

    private int gameHeight;
    private int width;
    private int topHeight;
    private int bottomHeight;
    private int totalHeight;
    private int hallucinateMoves;
    private boolean hallucinate;

    public ObjectDisplayGrid() {
        gameHeight = -1;
        width = -1;
        topHeight = -1;
        bottomHeight = -1;
    }

    public ObjectDisplayGrid(int _gameHeight, int _width, int _topHeight, int _bottomHeight) {
        gameHeight = _gameHeight;
        width = _width;
        topHeight = _topHeight;
        bottomHeight = _bottomHeight;
        totalHeight = gameHeight + topHeight + bottomHeight;

        terminal = new AsciiPanel(width, totalHeight);
        objectGrid = (Stack<Char>[][]) new Stack[width][totalHeight];

        for(int i = 0; i < width; i++) {
            for(int j = 0; j < totalHeight; j++) {
                objectGrid[i][j] = new Stack<Char>();
            }
        }

        initializeDisplay();
        super.add(terminal);
        super.setSize(width * 9, gameHeight * 16);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
        terminal.setVisible(true);
        super.addKeyListener(this);
        inputObservers = new ArrayList<>();
        super.repaint();
    }

    public static ObjectDisplayGrid getObjectDisplayGrid(int _gameHeight, int _width, int _topHeight, int _bottomHeight) {
        if (displayGrid == null) {
            displayGrid = new ObjectDisplayGrid(_gameHeight, _width, _topHeight, _bottomHeight);
        }
        return displayGrid;
    }

    public void setTopMessageHeight(int _topHeight) { this.topHeight = _topHeight; }

    public int getTopMessageHeight() { return this.topHeight; }

    public void setBottomMessageHeight(int _bottomHeight) { this.bottomHeight = _bottomHeight; }

    public int getBottomMessageHeight() { return this.bottomHeight; }

    public int getTotalHeight() { return this.totalHeight; }

    public static void setMainPlayer(Player _mainPlayer) {
        mainPlayer = _mainPlayer;
    }

    public Player getMainPlayer(){
        return mainPlayer;
    }

    public static void setMainMonster(Monster _mainMonster) {
        mainMonster = _mainMonster;
    }

    public Monster getMainMonster(){
        return mainMonster;
    }

    public void debugObjectGrid(int x, int y) {
        System.out.println("objectGrid[" + x + "][" + y + "]: ");
        for(int i = 0; i < objectGrid[x][y].size(); i++) {
            System.out.println(objectGrid[x][y].get(i).getChar());
        }
    }

    public final void topDisplay() {
        Char ch = new Char(' ');
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < topHeight; j++) {
                addObjectToDisplay(ch, i, j);
            }
        }
        displayStringToTerminal("HP: ", 0, 0);
    }

    public final void bottomDisplay() {
        Char ch = new Char(' ');
        for (int i = 0; i < width; i++) {
            for (int j = topHeight + gameHeight; j < totalHeight; j++) {
                addObjectToDisplay(ch, i, j);
            }
        }
        displayStringToTerminal("Pack: ", 0, totalHeight - 1 - 2);
        displayStringToTerminal(" ", 0, totalHeight - 2);
        displayStringToTerminal("Info: ", 0, totalHeight - 1);
    }

    public final void initializeDisplay() {
        Char ch = new Char(' ');
        for (int i = 0; i < width; i++) {
            for (int j = topHeight; j < topHeight + gameHeight; j++) {
                addObjectToDisplay(ch, i, j);
            }
        }
        topDisplay();
        bottomDisplay();
        terminal.repaint();
    }

    @Override
    public void registerInputObserver(InputObserver observer) {
        if (DEBUG > 0) {
            System.out.println(CLASSID + ".registerInputObserver " + observer.toString());
        }
        inputObservers.add(observer);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (DEBUG > 0) {
            System.out.println(CLASSID + ".keyTyped entered" + e.toString());
        }
        KeyEvent keypress = (KeyEvent) e;
        notifyInputObservers(keypress.getKeyChar());
    }

    private void notifyInputObservers(char ch) {
        for (InputObserver observer : inputObservers) {
            observer.observerUpdate(ch);
            if (DEBUG > 0) {
                System.out.println(CLASSID + ".notifyInputObserver " + ch);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent even) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void fireUp() {
        if (terminal.requestFocusInWindow()) {
            System.out.println(CLASSID + ".ObjectDisplayGrid(...) requestFocusInWindow Succeeded");
        } else {
            System.out.println(CLASSID + ".ObjectDisplayGrid(...) requestFocusInWindow FAILED");
        }
    }

    public void addObjectToDisplay(Char ch, int x, int y) {
        if ((0 <= x) && (x < objectGrid.length)) {
            if ((0 <= y) && (y < objectGrid[0].length)) {
                if (!objectGrid[x][y].contains(ch)) {
                    if (!objectGrid[x][y].isEmpty() && objectGrid[x][y].lastElement().getChar() == '@' && (ch.getChar() == ')' || ch.getChar() == ']')) {
                        // do nothing
                    }
                    else {
                        objectGrid[x][y].push(ch);
                    }
                }
                writeToTerminal(x, y);
            }
        }
    }

    public void removeObjectToDisplay(int x, int y) {
        if ((0 <= x) && (x < objectGrid.length)) {
            if ((0 <= y) && (y < objectGrid[0].length)) {
                if (objectGrid[x][y] != null) {
                    objectGrid[x][y].pop();
                }
                writeToTerminal(x, y);
            }
        }
    }

    public void writeHp(int hp) {
        displayStringToTerminal("HP: " + hp,0,0);
    }

    public void writeToTerminal(int x, int y) {
        char ch = objectGrid[x][y].lastElement().getChar();

        int count1 = 0;
        int count2 = 0;
        int count3 = 0;

        for (Char c: objectGrid[x][y]) {
            if (c.getChar() == '#') {
                count1++;
            }
            else if (c.getChar() == 'X') {
                count2++;
            }
            else if (c.getChar() == ')' || c.getChar() == ']' || c.getChar() == '?') {
                count3++;
            }

            if (count1 >= 1 && count2 >= 1) {
                ch = '+';
                objectGrid[x][y].push(new Char('+'));
                break;
            }

            if (count3 >= 1 && count2 >= 1) {
                ch = 'X';
                break;
            }
        }

        if(ch == '.' || ch == '#' || ch == '+') {
            xValues.add(x);
            yValues.add(y);
        }

        terminal.write(ch, x, y);
        terminal.repaint();
    }

    public void displayStringToTerminal(String temp, int x, int y) {
        terminal.write("                                                              ", x, y);
        terminal.write(temp, x, y);
        terminal.repaint();
    }

    public Char getDisplayChar(int x, int y) {
        if ((0 <= x) && (x < objectGrid.length)) {
            if ((0 <= y) && (y < objectGrid[0].length)) {
                if (DEBUG > 1) {
                    System.out.println(CLASSID + ".getDisplayChar is " + objectGrid[x][y].lastElement().getChar());
                }
                return objectGrid[x][y].lastElement();
            }
        }
        return null;
    }

    public void setHallucination(boolean _hallucinate, int _hallucinateMoves) {
        hallucinate = _hallucinate;
        hallucinateMoves = _hallucinateMoves;
    }

    public void checkHallucinate() {
        if (hallucinate) {
            hallucinateAction();
            hallucinateMoves--;
        }
    }

    public int checkIndex(int i, int length) {
        int new_i = i;
        if (i >= length) {
            new_i -= length;
        }
        return new_i;
    }

    public char changeCharacter(char ch, int i) {
        char new_ch;
        switch(ch) {
            case 'X':
                new_ch = displayableCharacters[checkIndex(i + 0, displayableCharacters.length)];
                break;
            case '#':
                new_ch = displayableCharacters[checkIndex(i + 1, displayableCharacters.length)];
                break;
            case '.':
                new_ch = displayableCharacters[checkIndex(i + 2, displayableCharacters.length)];
                break;
            case '+':
                new_ch = displayableCharacters[checkIndex(i + 3, displayableCharacters.length)];
                break;
            case 'S':
                new_ch = displayableCharacters[checkIndex(i + 4, displayableCharacters.length)];
                break;
            case 'T':
                new_ch = displayableCharacters[checkIndex(i + 5, displayableCharacters.length)];
                break;
            case 'H':
                new_ch = displayableCharacters[checkIndex(i + 6, displayableCharacters.length)];
                break;
            case '@':
                new_ch = displayableCharacters[checkIndex(i + 7, displayableCharacters.length)];
                break;
            case ']':
                new_ch = displayableCharacters[checkIndex(i + 8, displayableCharacters.length)];
                break;
            case ')':
                new_ch = displayableCharacters[checkIndex(i + 9, displayableCharacters.length)];
                break;
            case '?':
                new_ch = displayableCharacters[checkIndex(i + 10, displayableCharacters.length)];
                break;
            default:
                new_ch = ch;
                break;
        }
        return new_ch;
    }

    public void hallucinateAction() {
        if (hallucinateMoves == 0) {
            for (int x = 0; x < objectGrid.length; x++) {
                for (int y = 0; y < objectGrid[0].length; y++) {
                    char displayed_ch = objectGrid[x][y].lastElement().getChar();
                    terminal.write(displayed_ch, x, y);
                }
            }
            hallucinate = false;
        }
        else {
            Random rand = new Random();
            int i = rand.nextInt(displayableCharacters.length - 1);

            for (int x = 0; x < objectGrid.length; x++) {
                for (int y = 0; y < objectGrid[0].length; y++) {
                    char displayed_ch = objectGrid[x][y].lastElement().getChar();
                    char new_displayed_ch = changeCharacter(displayed_ch, i);
                    terminal.write(new_displayed_ch, x, y);
                }
            }
        }

        writeHp(getMainPlayer().getHp());
        displayStringToTerminal("Pack: ", 0, totalHeight - 1 - 2);
        displayStringToTerminal("Info: Nothing seems as it is!", 0, totalHeight - 1);
    }

    public boolean checkPosition(int x, int y) {
        char ch = objectGrid[x][y].lastElement().getChar();
        if (ch == '.' || ch == '#' || ch == '+') {
            return true;
        }
        return false;
    }
}
