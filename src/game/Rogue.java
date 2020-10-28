package game;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import game.display.ObjectDisplayGrid;
import game.displayble.Dungeon;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;



public class Rogue {

    private static final String directory = "xmlFiles/";
    private static final int Debug = 0;
    private boolean isRunning;
    public static final int FRAMESPERSECOND = 60;
    public static final int TIMEPERLOOP = 1000000000/FRAMESPERSECOND;
    private static ObjectDisplayGrid displayGrid = new ObjectDisplayGrid();
    private static Dungeon dungeon = null;

    private  Rectangle gameViewArea;

    public Rogue(int gameHeight, int gameWidth, int topMsgHeight, int bottomMsgHeight, String filename ){
        displayGrid = ObjectDisplayGrid.getObjectDisplayGrid(gameHeight,gameWidth, topMsgHeight,bottomMsgHeight);
        ObjectDisplayGrid.initializeMessageFields();
        System.out.flush();
        displayGrid.refresh();
    }

    public static void render(){
        displayGrid.refresh();
    }

    public void run(){
        isRunning = true;

        while (isRunning){
            long startTime = System.nanoTime();
            render();
            long endTime = System.nanoTime();
            long sleepTime = TIMEPERLOOP - (endTime - startTime);

            if(sleepTime > 0){
                try{
                    Thread.sleep(sleepTime/1000000);
                }catch (InterruptedExecution e){
                    e.printStackTrace(System.out);
                }
            }
        }

        System.out.println("in run in rougue");
    }

    public static ObjectDisplayGrid getDisplayGrid(){
        return displayGrid;
    }

    public static void main(String[] args) {
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
        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace(System.out);
        }


//        something Mridul Added on 10/27/2020
        if(Debug >0){
            if(dungeon!=null){
                System.out.println(dungeon);
            }
        } else{
            System.out.println("dungeon is null");
        }


        if(Debug > 0){
            System.out.println("gameHeight: "+ dungeon.getGameHeight());
            System.out.println("width: " + dungeon.getGameWidth());
            System.out.println("topHeight: "+ObjectDisplayGrid.getTopMessageHeight());
            System.out.println("bottomHeight: "+ObjectDisplayGrid.getBottomMessageHeight());
        }

        Rogue game = new Rogue(dungeon.getGameHeight(),dungeon.getGameWidth(),
                ObjectDisplayGrid.getTopMessageHeight(),
                ObjectDisplayGrid.getBottomMessageHeight(),
                fileName);
        System.out.println("SPM: user directory: "+ System.getProperty("user.dir"));

        Thread tr = new Thread(dungeon);
        tr.start();
        game.run();

        displayGrid.fireUp();
    }
}