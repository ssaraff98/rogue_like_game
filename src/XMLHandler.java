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
    int flag = 1;
    // Declaring variable that contains information found while parsing xml file
    private StringBuilder data = null;

    // Declaring stacks to hold all information in order that it is parsed from xml file
    private Stack<Displayable> displayableStack = null;
    private Stack<Action> actionStack = null;

    // Declaring variables to store current room and dungeon being parsed
    private Dungeon dungeonBeingParsed = null;
    private Room roomBeingParsed = null;
//    private StringBuilder data = null;

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
    private boolean bSword = false; //added M
    private boolean bItemAction = false; //added M
    private boolean bPassage = false; //added M
    private boolean bPlayer = false; //added M
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


//
//        System.out.println("Uri - " + uri);
//        System.out.println("Loclnamr - " + localName);
//        System.out.println("qname - " + qName);
//        System.out.println("Attributes - " + attributes.toString());
        data = new StringBuilder();
        // Dungeon
        if (qName.equalsIgnoreCase("Dungeon")) {
            bDungeon = true;
            String name = attributes.getValue("name");
            int width = Integer.parseInt(attributes.getValue("width"));
            int gameHeight = Integer.parseInt(attributes.getValue("gameHeight"));
            topHeight = Integer.parseInt(attributes.getValue("topHeight"));
            int bottomHeight = Integer.parseInt(attributes.getValue("bottomHeight"));

//            System.out.println("width and height" + width + gameHeight);

            dungeonBeingParsed = new Dungeon();
            dungeonBeingParsed.getDungeon(name, width, gameHeight);

            ObjectDisplayGrid displayableGrid = new ObjectDisplayGrid();
            displayableGrid.getObjectDisplayGrid(gameHeight, width, topHeight);
        }
        //

        else if (qName.equalsIgnoreCase("Player")) {
            bPlayer = true;
            System.out.println("\n");
            String name = attributes.getValue("name");
            String room = attributes.getValue("room");
            String serial = attributes.getValue("serial");
            System.out.println("Player Name:"+ name);
            System.out.println("Player Room: "+ room);
            System.out.println("Player Serial: "+serial);
            flag = 1;
        }
        else if (qName.equalsIgnoreCase("Room")) {
            bRoom = true;
            System.out.println("\n");
            String room = attributes.getValue("room");
            System.out.println("room: "+room);
            flag = 1;
        }
        else if (qName.equalsIgnoreCase("Monster")) {
            System.out.println("\n");
            bMonster = true;
            String name = attributes.getValue("name");
            String room = attributes.getValue("room");
            String serial = attributes.getValue("serial");
            System.out.println("Monster Name:"+ name);
            System.out.println("Monster Room: "+ room);
            System.out.println("Monster Serial: "+serial);
            flag = 1;
        }
        else if (qName.equalsIgnoreCase("CreatureAction")) {
            System.out.println("\n");
            bCreatureAction = true;
            String name = attributes.getValue("name");
            String type = attributes.getValue("type");
            System.out.println("CreatureAction Name: "+ name);
            System.out.println("CreatureAction Type: "+type);
            flag = 1;
        }
        else if (qName.equalsIgnoreCase("Sword")) {
            System.out.println("\n");
            bSword = true;
            String name = attributes.getValue("name");
            String room = attributes.getValue("room");
            String serial = attributes.getValue("serial");
            System.out.println("Sword Name:"+ name);
            System.out.println("Sword Room: "+ room);
            System.out.println("Sword Serial: "+serial);
            flag = 1;
        }
        else if (qName.equalsIgnoreCase("Armor")) {
            System.out.println("\n");
            bSword = true;
            String name = attributes.getValue("name");
            String room = attributes.getValue("room");
            String serial = attributes.getValue("serial");
            System.out.println("Armor Name:"+ name);
            System.out.println("Armor Room: "+ room);
            System.out.println("Armor Serial: "+serial);
            flag = 1;
        }
        else if (qName.equalsIgnoreCase("Scroll")) {
            System.out.println("\n");
            bScroll = true;
            String name = attributes.getValue("name");
            String room = attributes.getValue("room");
            String serial = attributes.getValue("serial");
            System.out.println("Scroll Name: "+name);
            System.out.println("Scroll room: "+room);
            System.out.println("Scroll Serial: "+serial);
            flag = 1;

        }
        else if (qName.equalsIgnoreCase("ItemAction")) {
            System.out.println("\n");
            bItemAction = true;
            String name = attributes.getValue("name");
            String type = attributes.getValue("type");
            System.out.println("Item Name: "+name);
            System.out.println("Item Type: "+ type);
            flag = 1;


        }
        else if (qName.equalsIgnoreCase("Passage")) {
            System.out.println("\n");
            bPassage = true;
            String room1 = attributes.getValue("room1");
            String room2 = attributes.getValue("room2");
            System.out.println("Passage Room1: "+room1);
            System.out.println("Passage Room2: "+room2);
            flag = 1;


        }

        else if (qName.equalsIgnoreCase("visible")) {
            flag = 0;
            System.out.print("Visible: ");
            bVisible = true;
        }
        else if (qName.equalsIgnoreCase("PosX")) {
            flag = 0;
            bPosX = true;
            System.out.print("Position X: ");
        }
        else if (qName.equalsIgnoreCase("PosY")) {
            flag = 0;
            bPosY = true;
            System.out.print("Position Y: ");
        }
        else if (qName.equalsIgnoreCase("width")) {
            flag = 0;
            bWidth = true;
            System.out.print("Width: ");
        }
        else if (qName.equalsIgnoreCase("height")) {
            flag = 0;
            bHeight = true;
            System.out.print("Height: ");
        }
        else if (qName.equalsIgnoreCase("type")) {
            flag =0;
            bType = true;
            System.out.print("Type: ");
        }
        else if (qName.equalsIgnoreCase("hp")) {
            flag = 0;
            bHP = true;
            System.out.print("HP: ");
        }
        else if (qName.equalsIgnoreCase("maxhit")) {
            flag = 0;
            bMaxHit = true;
            System.out.print("Max Hit: ");
        }
        else if (qName.equalsIgnoreCase("hpMoves")) {
            flag = 0;
            bHPMoves = true;
            System.out.print("HP Moves: ");
        }
        else if (qName.equalsIgnoreCase("ItemIntValue")) {
            flag = 0;
            bItemIntValue = true;
            System.out.print("ItemIntValue: ");
        }
        else if (qName.equalsIgnoreCase("Room")) {
            flag = 0;
            bRoom = true;
            System.out.print("Room: ");
        }
        else if (qName.equalsIgnoreCase("actionMessage")) {
            flag = 0;
            bActionMessage = true;
            System.out.print("Action Message: ");
        }
        else if (qName.equalsIgnoreCase("actionIntValue")) {
            flag = 0;
            bActionIntValue = true;
            System.out.print("Action Value: ");
        }
        else if (qName.equalsIgnoreCase("actionCharValue")) {
            flag = 0;
            bActionCharValue = true;
            System.out.print("Action Char Value: ");
        }
    }

    public void characters(char ch[], int start, int length) throws SAXException {
            String result = new String(ch, start, length);
            //result.replace(" ","");
            if(result.trim().length()>0){
            System.out.println(new String(ch, start, length));
        }




    }

//    public void ignorableWhitespace (char buf[], int start, int length) throws SAXException { emit("IGNORABLE"); }
//
//    private void emit(String ignorable) {
//    }
//    @Override
//    public void endElement(String uri, String localName, String qName) throws SAXException {
//        Course course;
//        if (bDungeon) {
//            bDungeon = false;
//        } else if (bCredit) {
//            course = (Course) activityBeingParsed;
//            course.setCredit(Integer.parseInt(data.toString()));
//            bCredit = false;
//        } else if (bName) {
//            activityBeingParsed.setName(data.toString());
//            bName = false;
//        } else if (bNumber) {
//            course = (Course) activityBeingParsed;
//            course.setNumber(data.toString());
//            bNumber = false;
//        } else if (bLocation) {
//            activityBeingParsed.setLocation(data.toString());
//            bLocation = false;
//        } else if (bMeetingTime) {
//            activityBeingParsed.setMeetingTime(data.toString());
//            bMeetingTime = false;
//        } else if (bMeetingDay) {
//            activityBeingParsed.setMeetingDay(data.toString());
//            bMeetingDay = false;
//        }
//
//        if (qName.equalsIgnoreCase("Students")) {
//            if (studentCount != maxStudents) {
//                System.out.println("wrong number of students parsed, should be " + maxStudents + ", is " + studentCount);
//            }
//        } else if (qName.equalsIgnoreCase("Student")) {
//            studentBeingParsed = null;
//        } else if (qName.equalsIgnoreCase("Activity")) {
//            activityBeingParsed = null;
//        }
//    }




}

