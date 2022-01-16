package de.thu.project.main.model.checklist_item;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import de.thu.project.main.model.DaoFactory;
import de.thu.project.main.model.checklist.ChecklistDao_DB;
import de.thu.project.main.model.item.ItemDao_DB;
import de.thu.project.main.view.ImportChecklistView;

public class Import {

	
	private ImportChecklistView view;
	private DaoFactory daofactory = DaoFactory.getInstance();
	private ChecklistDao_DB checklistDao;
	private Checklist_itemDao_DB checklistItemDao;
	private ItemDao_DB itemDao;
	
	public Import(ImportChecklistView view) {
		this.view=view;
		this.checklistDao = (ChecklistDao_DB) daofactory.getChecklistDao();
		this.checklistItemDao = (Checklist_itemDao_DB) daofactory.getChecklist_itemDao();
		this.itemDao = (ItemDao_DB) daofactory.getItemDao();
	}
	
	
	
	public void importXML(File file) throws ParserConfigurationException, SAXException {
		try {
			Document document = fileReader(file);
    		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    		Source schemaFile = new StreamSource(new File("XSD/OurSchema.xsd"));
    		Schema schema = schemaFactory.newSchema(schemaFile);
    	    Validator validator = schema.newValidator();
    	    validator.validate(new DOMSource(document));
            //document.getDocumentElement().normalize();
           	view.taItems.append(document.getDocumentElement().getNodeName() + "\n");
           	NodeList nList = document.getElementsByTagName("item");
           	view.taItems.append("----------------------------");
           	for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                view.taItems.append("\nCurrent Element : " + nNode.getNodeName() + "\n");
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    view.taItems.append("category : " + eElement.getElementsByTagName("category").item(0).getTextContent() + "\n");
                    view.taItems.append("item name : " + eElement.getElementsByTagName("name").item(0).getTextContent() + "\n");
                    view.taItems.append("amount : " + eElement.getElementsByTagName("amount").item(0).getTextContent() + "\n");
                }
            }
           	
		} catch(IOException e) {
			JOptionPane.showMessageDialog(null,"Error: XML file could not be imported!");
		}
	}
	
	public Document fileReader(File file) throws ParserConfigurationException, SAXException {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        Document document = db.parse(file);
	        return document;
		} catch(IOException e) {
			return null;
		}
        
	}
}
