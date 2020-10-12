import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

public class Test {
    private static final String directory = "xmlfiles/";

    public static void main(String[] args) {
        String fileName = directory + "wear.xml";

//        String fileName  = null;

        // Checking if file name has been passed in
        // If only 1 argument is passed, getting file name
        // Else, printing usage message
//        if (args.length == 1) {
//            fileName = directory + args[0];
//        }
//        else {
//            System.out.println("Usage: java Test <xmlfilename>.xml");
//            return;
//        }

        // Creating a saxParserFactory for creation of a parser
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        saxParserFactory.setNamespaceAware(true);
        saxParserFactory.setValidating(false);
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
//            saxParser.setPreserveWhitespace(false);
            XMLHandler handler = new XMLHandler();
            saxParser.parse(new File(fileName), handler);
        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
