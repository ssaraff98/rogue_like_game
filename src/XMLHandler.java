import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

// in start element you push to stack
// everytime you encounter a start element (class), you push to stack and everytime you encounter close you pop
// in end element you pop from stack

public class XMLHandler extends DefaultHandler{

}
