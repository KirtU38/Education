import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class XMLHandlerTest extends DefaultHandler {


    @Override
    public void startElement(String uri, String localName, String tag, Attributes attributes) {
        if (tag.equals("voter")) {
            System.out.println(attributes.getValue("name"));

        } else if (tag.equals("visit")) {
            System.out.println(attributes.getValue("station"));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

    }


}
