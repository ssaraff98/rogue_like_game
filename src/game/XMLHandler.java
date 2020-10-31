package game;

import game.action.Action;
import game.action.creatureAction.ChangedDisplayedType;
import game.action.creatureAction.CreatureAction;
import game.action.creatureAction.Remove;
import game.action.creatureAction.Teleport;
import game.action.creatureAction.UpdateDisplay;
import game.action.creatureAction.YouWin;
import game.action.creatureAction.playerAction.DropPack;
import game.action.creatureAction.playerAction.EndGame;
import game.action.itemAction.BlessCurse;
import game.action.itemAction.Hallucinate;
import game.action.itemAction.ItemAction;

import game.display.ObjectDisplayGrid;

import game.displayable.Displayable;
import game.displayable.Dungeon;
import game.displayable.creature.Creature;
import game.displayable.creature.Monster;
import game.displayable.creature.Player;
import game.displayable.item.Armor;
import game.displayable.item.Item;
import game.displayable.item.Scroll;
import game.displayable.item.Sword;
import game.displayable.structure.Passage;
import game.displayable.structure.Room;
import game.displayable.structure.Structure;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Stack;

public class XMLHandler extends DefaultHandler {
    private static final int DEBUG = 1;
    private static final String CLASSID = "XMLHandler";

    // Declaring variable that contains information found while parsing xml file
    private StringBuilder data = null;

    // Declaring stacks to hold all information in order that it is parsed from xml file
    private Stack<Displayable> displayableStack = null;
    private Stack<Action> actionStack = null;

    private ObjectDisplayGrid displayGrid = new ObjectDisplayGrid();
    private Dungeon dungeonBeingParsed = new Dungeon();
    private Room roomBeingParsed = new Room();

    private boolean bActionCharValue = false;
    private boolean bActionIntValue = false;
    private boolean bActionMessage = false;
    private boolean bArmor = false;
    private boolean bCreatureAction = false;
    private boolean bDungeon = false;
    private boolean bHeight = false;
    private boolean bHP = false;
    private boolean bHPMoves = false;
    private boolean bItemIntValue = false;
    private boolean bItemAction = false;
    private boolean bMaxHit = false;
    private boolean bMonster = false;
    private boolean bPassage = false;
    private boolean bPlayer = false;
    private boolean bPosX = false;
    private boolean bPosY = false;
    private boolean bRoom = false;
    private boolean bScroll = false;
    private boolean bSword = false;
    private boolean bType = false;
    private boolean bVisible = false;
    private boolean bWidth = false;

    public Dungeon getDungeon() {
        return dungeonBeingParsed;
    }

    public XMLHandler() {
        displayableStack = new Stack<>();
        actionStack = new Stack<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (DEBUG > 1) {
            System.out.println(CLASSID + ".startElement qName: " + qName);
        }

        // Dungeon tag
        if (qName.equalsIgnoreCase("Dungeon")) {
            bDungeon = true;
            String name = attributes.getValue("name");
            int width = Integer.parseInt(attributes.getValue("width"));
            int gameHeight = Integer.parseInt(attributes.getValue("gameHeight"));
            int topHeight = Integer.parseInt(attributes.getValue("topHeight"));
            int bottomHeight = Integer.parseInt(attributes.getValue("bottomHeight"));

            ObjectDisplayGrid.getObjectDisplayGrid(gameHeight, width, topHeight, bottomHeight);
            dungeonBeingParsed = dungeonBeingParsed.getDungeon(name, width, gameHeight);
            displayableStack.push(dungeonBeingParsed);
        }
        // Armor tag
        else if (qName.equalsIgnoreCase("Armor")) {
            bArmor = true;
            String name = attributes.getValue("name");
            int room = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));

            Armor armor = new Armor(name);
            dungeonBeingParsed.addItem(armor);
            armor.setID(room, serial);

            displayableStack.push(armor);
        }
        // CreatureAction tag
        else if (qName.equalsIgnoreCase("CreatureAction")) {
            bCreatureAction = true;
            String name = attributes.getValue("name");
            String type = attributes.getValue("type");

            // actionStack.push();
        }
        // ItemAction tag
        else if (qName.equalsIgnoreCase("ItemAction")) {
            bItemAction = true;
            String name = attributes.getValue("name");
            String type = attributes.getValue("type");

            // actionStack.push();
        }
        // Monster tag
        else if (qName.equalsIgnoreCase("Monster")) {
            bMonster = true;
            String name = attributes.getValue("name");
            int room = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));

            Monster monster = new Monster();
            dungeonBeingParsed.addCreature(monster);
            monster.setName(name);
            monster.setID(room, serial);
            roomBeingParsed.setCreature(monster);

            displayableStack.push(monster);
        }
        // Passage tag
        else if (qName.equalsIgnoreCase("Passage")) {
            bPassage = true;
            int room1 = Integer.parseInt(attributes.getValue("room1"));
            int room2 = Integer.parseInt(attributes.getValue("room2"));

            Passage passage = new Passage();
            dungeonBeingParsed.addPassage(passage);
            passage.setID(room1, room2);
            passage.setType('#');

            displayableStack.push(passage);
        }
        // Player tag
        else if (qName.equalsIgnoreCase("Player")) {
            bPlayer = true;
            String name = attributes.getValue("name");
            int room = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));

            Player player = new Player();
            dungeonBeingParsed.addCreature(player);
            roomBeingParsed.setCreature(player);

            displayableStack.push(player);
        }
        // Room tag
        else if (qName.equalsIgnoreCase("Room")) {
            bRoom = true;
            int room = Integer.parseInt(attributes.getValue("room"));

            roomBeingParsed = new Room(room);
            dungeonBeingParsed.addRoom(roomBeingParsed);
            roomBeingParsed.setId(room);
            roomBeingParsed.setType('X');

            displayableStack.push(roomBeingParsed);
        }
        // Rooms tag
        else if (qName.equalsIgnoreCase("Rooms")) {
            // Do nothing
        }
        // Scroll tag
        else if (qName.equalsIgnoreCase("Scroll")) {
            bScroll = true;
            String name = attributes.getValue("name");
            int room = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));

            Scroll scroll = new Scroll(name);
            dungeonBeingParsed.addItem(scroll);
            scroll.setID(room, serial);

            displayableStack.push(scroll);
        }
        // Sword tag
        else if (qName.equalsIgnoreCase("Sword")) {
            bSword = true;
            String name = attributes.getValue("name");
            int room = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));

            Sword sword = new Sword(name);
            dungeonBeingParsed.addItem(sword);
            sword.setID(room, serial);

            displayableStack.push(sword);
        }

        // ActionCharValue tag
        else if (qName.equalsIgnoreCase("actionCharValue")) {
            bActionCharValue = true;
        }
        // ActionIntValue tag
        else if (qName.equalsIgnoreCase("actionIntValue")) {
            bActionIntValue = true;
        }
        // ActionMessage tag
        else if (qName.equalsIgnoreCase("actionMessage")) {
            bActionMessage = true;
        }
        // Height tag
        else if (qName.equalsIgnoreCase("height")) {
            bHeight = true;
        }
        // HP tag
        else if (qName.equalsIgnoreCase("hp")) {
            bHP = true;
        }
        // HPMoves tag
        else if (qName.equalsIgnoreCase("hpMoves")) {
            bHPMoves = true;
        }
        // ItemIntValue tag
        else if (qName.equalsIgnoreCase("ItemIntValue")) {
            bItemIntValue = true;
        }
        // MaxHit tag
        else if (qName.equalsIgnoreCase("maxhit")) {
            bMaxHit = true;
        }
        // PosX tag
        else if (qName.equalsIgnoreCase("PosX")) {
            bPosX = true;
        }
        // PosY tag
        else if (qName.equalsIgnoreCase("PosY")) {
            bPosY = true;
        }
        // Type tag
        else if (qName.equalsIgnoreCase("type")) {
            bType = true;
        }
        // Visible tag
        else if (qName.equalsIgnoreCase("visible")) {
            bVisible = true;
        }
        // Width tag
        else if (qName.equalsIgnoreCase("width")) {
            bWidth = true;
        }
        else {
            System.out.println("Unknown qname: " + qName);
        }

        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (bActionCharValue) {
            bActionCharValue = false;
            // actionStack.lastElement().setCharValue(data.toString().charAt(0));
        }
        else if (bActionIntValue) {
            bActionIntValue = false;
            // actionStack.lastElement().setIntValue(Integer.parseInt(data.toString()));
        }
        else if (bActionMessage) {
            bActionMessage = false;
            // actionStack.lastElement().setMessage(data.toString());
        }
        else if (bHeight) {
            bHeight = false;
            displayableStack.lastElement().setHeight(Integer.parseInt(data.toString()));
        }
        else if (bHP) {
            bHP = false;
            displayableStack.lastElement().setHp(Integer.parseInt(data.toString()));
        }
        else if (bHPMoves) {
            bHPMoves = false;
            displayableStack.lastElement().setHpMoves(Integer.parseInt(data.toString()));
        }
        else if (bItemIntValue) {
            bItemIntValue = false;
            displayableStack.lastElement().setIntValue(Integer.parseInt(data.toString()));
        }
        else if (bMaxHit) {
            bMaxHit = false;
            displayableStack.lastElement().setMaxhit(Integer.parseInt(data.toString()));
        }
        else if (bPosX) {
            bPosX = false;
            displayableStack.lastElement().setPosX(Integer.parseInt(data.toString()));
        }
        else if (bPosY) {
            bPosY = false;
            displayableStack.lastElement().setPosY(Integer.parseInt(data.toString()));
        }
        else if (bType) {
            bType = false;
            displayableStack.lastElement().setType(data.toString().charAt(0));
        }
        else if (bVisible) {
            bVisible = false;
            int visible = Integer.parseInt(data.toString());

            if (visible == 1) {
                displayableStack.lastElement().setVisible();
            }
            else if (visible == 0) {
                displayableStack.lastElement().setInvisible();
            }
            else {
                System.out.println("Visible value " + visible + " is invalid");
            }
        }
        else if (bWidth) {
            bWidth = false;
            displayableStack.lastElement().setWidth(Integer.parseInt(data.toString()));
        }

        // Dungeon tag
        if (qName.equalsIgnoreCase("Dungeon")) {
            bDungeon = false;
            // dungeonBeingParsed = null;
            displayableStack.pop();
        }
        // Armor tag
        else if (qName.equalsIgnoreCase("Armor")) {
            bArmor = false;
            displayableStack.pop();
        }
        // CreatureAction tag
        else if (qName.equalsIgnoreCase("CreatureAction")) {
            bCreatureAction = false;
            // actionStack.pop();
        }
        // ItemAction tag
        else if (qName.equalsIgnoreCase("ItemAction")) {
            bItemAction = false;
            // actionStack.pop();
        }
        // Monster tag
        else if (qName.equalsIgnoreCase("Monster")) {
            bMonster = false;
            displayableStack.pop();
        }
        // Passage tag
        else if (qName.equalsIgnoreCase("Passage")) {
            bPassage = false;
            displayableStack.pop();
        }
        // Player tag
        else if (qName.equalsIgnoreCase("Player")) {
            bPlayer = false;
            displayableStack.pop();
        }
        // Room tag
        else if (qName.equalsIgnoreCase("Room")) {
            bRoom = false;
            roomBeingParsed = null;
            displayableStack.pop();
        }
        // Scroll tag
        else if (qName.equalsIgnoreCase("Scroll")) {
            bScroll = false;
            displayableStack.pop();
        }
        // Sword tag
        else if (qName.equalsIgnoreCase("Sword")) {
            bSword = false;
            displayableStack.pop();
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
        if (DEBUG > 1) {
            System.out.println(CLASSID + ".characters: " + new String(ch, start, length));
            System.out.flush();
        }
    }
}