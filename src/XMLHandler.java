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
    private String name = null;
    private int width = -1;
    private int gameHeight = -1;
    private int topHeight = -1;
    private int bottomHeight = -1;

    // Declaring bX fields corresponding to those in the xml files
    private boolean bActionMessage = false;
    private boolean bActionIntValue = false;
    private boolean bActionCharValue = false;
    private boolean bDungeon = false;
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
            name = attributes.getValue("name");
            width = Integer.parseInt(attributes.getValue("width"));
            gameHeight = Integer.parseInt(attributes.getValue("gameHeight"));
            topHeight = Integer.parseInt(attributes.getValue("topHeight"));
            bottomHeight = Integer.parseInt(attributes.getValue("bottomHeight"));
            dungeonBeingParsed = new Dungeon();
            dungeonBeingParsed.getDungeon(name, width, gameHeight);
        }
        //
        else if (qName.equalsIgnoreCase("Room")) {

        }
    }
}
