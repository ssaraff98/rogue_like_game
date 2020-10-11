import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Stack;

// in start element you push to stack
// everytime you encounter a start element (class),
// you push to stack and everytime you encounter close you pop
// in end element you pop from stack


public class XMLHandler extends DefaultHandler{
    // Declaring debug flag and class ID
    private static final int DEBUG = 1;
    private static final String CLASSID = "XMLHandler";

    // Declaring variable that contains information found while parsing xml file
    private StringBuilder data = null;

    // Declaring stacks to hold all information in order that it is parsed from xml file
    private Stack<Displayable> displayableStack = null;
    private Stack<Action> actionStack = null;

    // Declaring variables to store current room and dungeon being parsed
    private Dungeon dungeonBeingParsed = null;
    private Room roomBeingParsed = null;

    // Declaring variables for dungeon dimensions
    private int topHeight = -1;

    // Declaring bX fields corresponding to those in the xml files
    private boolean bActionMessage = false;
    private boolean bActionIntValue = false;
    private boolean bActionCharValue = false;
    private boolean bDungeon = false;
    private boolean bMonster = false; //added M
    private boolean bCreatureAction = false; //added M
    private boolean bScroll = false; //added M
    private boolean bItemAction = false; //added M
    private boolean bHeight = false;
    private boolean bHP = false;
    private boolean bHPMoves = false;
    private boolean bItemIntValue = false;
    private boolean bMaxHit = false;
    private boolean bPosX = false;
    private boolean bPosY = false;
    private boolean bRoom = false;
    private boolean bType = false;
    private boolean bVisible = false;
    private boolean bWidth = false;

    // Making an implicit call to the DefaultHandler zero arg constructor
    public XMLHandler() {
        displayableStack = new Stack<>();
        actionStack = new Stack<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (DEBUG > 1) {
            System.out.println(CLASSID + ".startElement qName: " + qName);
        }

        // Dungeon
        if (qName.equalsIgnoreCase("Dungeon")) {
            bDungeon = true;
            String name = attributes.getValue("name");
            int width = Integer.parseInt(attributes.getValue("width"));
            int gameHeight = Integer.parseInt(attributes.getValue("gameHeight"));
            topHeight = Integer.parseInt(attributes.getValue("topHeight"));
            int bottomHeight = Integer.parseInt(attributes.getValue("bottomHeight"));

            dungeonBeingParsed = new Dungeon();
            dungeonBeingParsed.getDungeon(name, width, gameHeight);

            ObjectDisplayGrid displayableGrid = new ObjectDisplayGrid();
            displayableGrid.getObjectDisplayGrid(gameHeight, width, topHeight);
        }
        //
        else if (qName.equalsIgnoreCase("Room")) {
            bRoom = true;
            String room = attributes.getValue("room")
        }
        else if (qName.equalsIgnoreCase("Monster")) {
            bMonster = true;
            String name = attributes.getValue("name")
            String room = attributes.getValue("room")
            String serial = attributes.getValue("serial")
        }
        else if (qName.equalsIgnoreCase("CreatureAction")) {
            bCreatureAction = true;
            String name = attributes.getValue("name")
            String type = attributes.getValue("type")
        }
        else if (qName.equalsIgnoreCase("Scroll")) {
            bScroll = true;
            String name = attributes.getValue("name")
            String room = attributes.getValue("room")
            String serial = attributes.getValue("serial")

        }
        else if (qName.equalsIgnoreCase("ItemAction")) {
            bItemAction = true;
            String name = attributes.getValue("name")
            String type = attributes.getValue("type")
            

        }
        else if (qName.equalsIgnoreCase("visible")) {
            bvisible = true;
        }
        else if (qName.equalsIgnoreCase("PosX")) {
            bPosX = true;
        }
        else if (qName.equalsIgnoreCase("PosY")) {
            bPosY = true;
        }
        else if (qName.equalsIgnoreCase("width")) {
            bWidth = true;
        }
        else if (qName.equalsIgnoreCase("height")) {
            bHeight = true;
        }
        else if (qName.equalsIgnoreCase("type")) {
            bType = true;
        }
        else if (qName.equalsIgnoreCase("hp")) {
            bHP = true;
        }
        else if (qName.equalsIgnoreCase("maxhit")) {
            bMaxHit = true;
        }
        else if (qName.equalsIgnoreCase("hpMoves")) {
            bHPMoves = true;
        }
        else if (qName.equalsIgnoreCase("Room")) {
            bRoom = true;
        }
        else if (qName.equalsIgnoreCase("actionMessage")) {
            bActionMessage = true;
        }
        else if (qName.equalsIgnoreCase("actionIntValue")) {
            bActionIntValue = true;
        }
    }
}

