package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;

import org.w3c.dom.*;
import org.xml.sax.*;

import model.DaoFactory;
import model.checklist.ChecklistDao_DB;
import model.checklist.ChecklistVo;
import model.checklist_item.Checklist_itemDao_DB;
import model.checklist_item.Checklist_itemVo;
import model.item.ItemDao_DB;
import model.item.ItemVo;
import view.ImportChecklistView;
import view.MenuView;
import view.UseChecklistView;

/**********************************************
 * ImportChecklistController-Class
 * 
 * class checks if imported XML is similar to our XML scheme (XSD);
 * class only inserts items, already included in our database, into the imported checklist
 * 
 **********************************************/

public class ImportChecklistController implements ActionListener{

	private ImportChecklistView view;
	private DaoFactory daofactory = DaoFactory.getInstance();
	private ChecklistDao_DB checklistDao;
	private Checklist_itemDao_DB checklistItemDao;
	private ItemDao_DB itemDao;
	private File fileToSave;
	
	public ImportChecklistController(ImportChecklistView view) {
		this.view=view;
		this.checklistDao = (ChecklistDao_DB) daofactory.getChecklistDao();
		this.checklistItemDao = (Checklist_itemDao_DB) daofactory.getChecklist_itemDao();
		this.itemDao = (ItemDao_DB) daofactory.getItemDao();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		if (src==view.btnChooseFile) {
			//PLZ DONT TOUCH THE EXHIBITS
			JFileChooser file = new JFileChooser();
			int returnVal = file.showOpenDialog(null);
	        if(returnVal == JFileChooser.APPROVE_OPTION) {
	            String path = file.getSelectedFile().getPath();
	            fileToSave = new File(path);
	            fileToSave.getAbsolutePath();
				try {
					importXML();
				} catch (ParserConfigurationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SAXException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

	        } else {
	        	JOptionPane.showMessageDialog(null,"Path not found :(");
	        }
		}
		
		if (src==view.btnImport) {
			//....
			ChecklistVo check = new ChecklistVo(view.textField.getText(), daofactory.getCurrent_user());
			checklistDao.insert(check);
			Document document;
			try {
				document = fileReader(fileToSave);
				NodeList nList = document.getElementsByTagName("item");
				for (int i = 0; i < nList.getLength(); i++) {
	                Node nNode = nList.item(i);
	                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	                    Element eElement = (Element) nNode;
	                    ArrayList<String> itemsByC = itemDao.getCategoryItems(eElement.getElementsByTagName("category").item(0).getTextContent());
	                    for(int j = 0; j < itemsByC.size(); j++) {
	                    	if(itemsByC.get(j).equals(eElement.getElementsByTagName("name").item(0).getTextContent())) {
	                    		addItemtoChecklist(view.textField.getText().toLowerCase(), eElement.getElementsByTagName("name").item(0).getTextContent(), Integer.parseInt(eElement.getElementsByTagName("amount").item(0).getTextContent()));
	                    	}
	                    }
	                }
	            }
			} catch (ParserConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SAXException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			view.dispose();
			UseChecklistView use = new UseChecklistView();
			use.setVisible(true);
			
		}
		if (src == view.btnCancel) {
			view.dispose();
			MenuView menu = new MenuView();
			menu.setVisible(true);
		}
	}
	
	public void importXML() throws ParserConfigurationException, SAXException {
		try {
			Document document = fileReader(fileToSave);
    		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    		Source schemaFile = new StreamSource(new File("XSD/OurSchema.xsd"));
    		Schema schema = schemaFactory.newSchema(schemaFile);
    	    Validator validator = schema.newValidator();
    	    validator.validate(new DOMSource(document));
            //document.getDocumentElement().normalize();
           	view.textArea.append(document.getDocumentElement().getNodeName() + "\n");
           	NodeList nList = document.getElementsByTagName("item");
           	view.textArea.append("----------------------------");
           	for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                view.textArea.append("\nCurrent Element : " + nNode.getNodeName() + "\n");
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    view.textArea.append("category : " + eElement.getElementsByTagName("category").item(0).getTextContent() + "\n");
                    view.textArea.append("item name : " + eElement.getElementsByTagName("name").item(0).getTextContent() + "\n");
                    view.textArea.append("amount : " + eElement.getElementsByTagName("amount").item(0).getTextContent() + "\n");
                }
            }
           	
		} catch(IOException e) {
			JOptionPane.showMessageDialog(null,"Error: XML file could not be imported!");
		}
	}
	
	public void addItemtoChecklist(String checklistName, String item, int amount) {

		ChecklistVo checklistVo = new ChecklistVo(checklistName, daofactory.getCurrent_user());
		checklistVo.setChecklistID(checklistDao.getChecklistID(checklistVo));

		ItemVo itemVo = new ItemVo(item);
		itemVo.setItemID(itemDao.getItemID(itemVo));

		Checklist_itemVo checklistItem = new Checklist_itemVo(checklistVo.getChecklistID(), itemVo.getItemID());
		checklistItemDao.addItem(checklistItem, amount);
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
