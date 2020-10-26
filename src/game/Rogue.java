package game;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

public class Rogue {
    private static final String directory = "xmlFiles/";

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
    }
}