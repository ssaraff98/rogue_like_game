package game.display;

import game.displayable.Displayable;
import game.displayable.Dungeon;

import asciiPanel.AsciiPanel;
import java.io.IOException;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ObjectDisplayGrid extends JFrame implements KeyListener, InputSubject {
    private static final int DEBUG = 0;
    private static final String CLASSID = ".ObjectDisplayGrid";

    private static AsciiPanel terminal;
    private Stack<Displayable>[][] objectGrid = null;

    private List<InputObserver> inputObservers = null;

    private static ObjectDisplayGrid instance = null;
    private int gameHeight;
    private int width;
    private int topHeight;
    private int bottomHeight;

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

        terminal = new AsciiPanel(width, gameHeight);
        objectGrid = (Stack<Displayable>[][]) new Stack[width][gameHeight];

        for(int i = 0; i < width; i++) {
            for(int j = 0; j < gameHeight; j++) {
                objectGrid[i][j] = new Stack<Displayable>();
            }
        }

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
        if (instance == null) {
            instance = new ObjectDisplayGrid(_gameHeight, _width, _topHeight, _bottomHeight);
        }

        return instance;
    }

    public void setTopMessageHeight(int _topHeight) { this.topHeight = _topHeight; }

    public int getTopMessageHeight() { return this.topHeight; }

    public void setBottomMessageHeight(int _bottomHeight) { this.bottomHeight = _bottomHeight; }

    public int getBottomMessageHeight() { return this.bottomHeight; }

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

    public void addObjectToDisplay(Displayable element, int x, int y) {
        System.out.println(element + " " + x + " " + y);

        if ((0 <= x) && (x < objectGrid.length)) {
            if ((0 <= y) && (y < objectGrid[0].length)) {
                objectGrid[x][y].push(element);
                writeToTerminal(x, y);
            }
        }
    }

    private void writeToTerminal(int x, int y) {
        char ch = objectGrid[x][y].lastElement().getType();
        terminal.write(ch, x, y);
        terminal.repaint();
    }
}
