package nz.ac.vuw.ecs.swen225.gp22.persistency;

//import dom4j
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class XmlParser {
    /**
     * This method is used to parse the xml file and return the document
     * @param url the xml file to be parsed
     * @return the document of the xml file
     */
    public Document parse(File url) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        return document;
    }

    /**
     * This function creates a new XML file
     *
     */
    public Document createDocument() {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("root");

        Element gamer1 = root.addElement("Gamer")
                .addAttribute("name", "James")
                .addAttribute("location", "NZ")
                .addText("James gamed");

        Element gamer2 = root.addElement("Gamer")
                .addAttribute("name", "Sol")
                .addAttribute("location", "NZ")
                .addText("Sol Goodguy");

        return document;
    }

    public void write(Document document) throws IOException {
        // write to a file
        FileWriter out = new FileWriter(new File("src/nz/ac/vuw/ecs/swen225/gp22/persistency/levels/", "gamers.xml"));
        document.write(out);
        out.close();
    }

    public static void main(String[] args){
        XmlParser parser = new XmlParser();

        try {
            Document document = parser.createDocument();
            parser.write(document);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Document document = parser.parse(new File("src/nz/ac/vuw/ecs/swen225/gp22/persistency/levels/gamers.xml"));
            System.out.println(document.asXML());
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }


}
