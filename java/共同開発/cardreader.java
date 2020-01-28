import java.io.File;
import java.io.IOException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlSaxSimpleReader extends DefaultHandler{
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {//[1]
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();//[2]
        SAXParser saxParser = saxParserFactory.newSAXParser();//[3]
        saxParser.parse(new File("C:\\temp\\receipt_one_element.xml"), new XmlSaxSimpleReader());//[4]
    }
    public void startDocument() {//[10]
        System.out.println("[11] ドキュメント開始");
    }
    public void startElement(String uri, String localName, String qName, Attributes attributes) {//[20]
        System.out.println("[21] 要素開始 = " + qName);//[21]
    }
    public void characters(char[] ch, int offset, int length) {//[30]
        System.out.println("[31] テキストデータ = " + new String(ch, offset, length));
    }
    public void endElement(String uri, String localName, String qName) {//[40]
        System.out.println("[41] 要素終了 = " + qName);
    }
    public void endDocument(){//[50]
        System.out.println("[51] ドキュメント終了");
    }
}
