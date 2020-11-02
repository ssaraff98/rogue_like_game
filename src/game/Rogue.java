package game;

import game.display.ObjectDisplayGrid;
import game.displayable.Dungeon;
import game.display.KeyStrokePrinter;

import java.awt.*;
import java.io.File;
import java.util.*;
//import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.lang.reflect.Field;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;


public class Rogue implements Runnable {
    private static final String directory = "xmlFiles/";
    private static final int DEBUG = 0;
    public static final int FRAMESPERSECOND = 60;
    public static final int TIMEPERLOOP = 1000000000 / FRAMESPERSECOND;
    public Thread keyStrokePrinter;
    private boolean isRunning;

    private static Dungeon dungeon = new Dungeon();

    private  Rectangle gameViewArea;

    public Rogue(int gameHeight, int width, int topHeight, int bottomHeight, String fileName) {
        ObjectDisplayGrid.getObjectDisplayGrid(gameHeight, width, topHeight, bottomHeight);
        System.out.flush();
        // displayGrid.refresh();
    }

    public static void render(){
        // displayGrid.refresh();
    }

    @Override
    public void run() {
        isRunning = true;

        while (isRunning) {
            long startTime = System.nanoTime();
            render();
            long endTime = System.nanoTime();
            long sleepTime = TIMEPERLOOP - (endTime - startTime);

            ObjectDisplayGrid displayGrid = ObjectDisplayGrid.getObjectDisplayGrid(0, 0, 0, 0);
            KeyStrokePrinter print = new KeyStrokePrinter(displayGrid);
            print.run();

            if(sleepTime > 0){
                try {
                    Thread.sleep(sleepTime/1000000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace(System.out);
                }
            }
        }

        System.out.println("Run in Rogue");
    }

    public static ObjectDisplayGrid getDisplayGrid(){
        return ObjectDisplayGrid.getObjectDisplayGrid(0, 0, 0, 0);
    }

    public static void main(String[] args) throws Exception{
        String fileName  = null;

        if (args.length == 1) {
            fileName = directory + args[0];
        }
        else {
            System.out.println("Usage: java game.Rogue <xmlfilename>.xml");
            return;
        }

        // Creating a saxParserFactory for creation of a parser
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        saxParserFactory.setNamespaceAware(true);
        saxParserFactory.setValidating(false);

        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            XMLHandler handler = new XMLHandler();
            saxParser.parse(new File(fileName), handler);
            dungeon = handler.getDungeon();
        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace(System.out);
        }

        // Checking if dungeon has been initialized
        if(DEBUG > 0) {
            if(dungeon != null) {
                System.out.println(dungeon);
            }
            else {
                System.out.println("Dungeon is null");
            }
        }

        // Printing game display dimensions
        ObjectDisplayGrid displayGrid = ObjectDisplayGrid.getObjectDisplayGrid(0, 0, 0, 0);
        if(DEBUG > 0) {
            System.out.println("gameHeight: " + dungeon.getHeight());
            System.out.println("width: " + dungeon.getWidth());
            System.out.println("topHeight: " + displayGrid.getTopMessageHeight());
            System.out.println("bottomHeight: " + displayGrid.getBottomMessageHeight());
        }

        dungeon.addObjectToDisplay(displayGrid);

        Rogue game = new Rogue(dungeon.getHeight(), dungeon.getWidth(), displayGrid.getTopMessageHeight(), displayGrid.getBottomMessageHeight(), fileName);
        // System.out.println("SPM: user directory: "+ System.getProperty("user.dir"));

        Thread testThread = new Thread(dungeon);
        testThread.start();
        game.run();

        game.keyStrokePrinter = new Thread(new KeyStrokePrinter(displayGrid));

        game.keyStrokePrinter.start();

        testThread.join();
        game.keyStrokePrinter.join();
        displayGrid.fireUp();
    }
}
