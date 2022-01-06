package model.checklist_item;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.interactive.form.*;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import model.DaoFactory;
import model.checklist.ChecklistDao_DB;
import model.checklist.ChecklistVo;
import model.item.ItemDao_DB;
import model.item.ItemVo;


//Source: https://www.baeldung.com/java-pdf-creation
// https://stackoverflow.com/questions/14602821/how-to-check-a-check-box-in-pdf-form-using-java-pdfbox-api


public class Export {
	private String checklist;
	private String path;
	private ArrayList<String> allItems;

	private ChecklistDao_DB checkDao;
	private Checklist_itemDao_DB checkItemDao;
	private ItemDao_DB itemDao;
	private DaoFactory daofactory = DaoFactory.getInstance();

	public Export(String checklist, String path) {
		this.checklist = checklist;
		this.path = path;
		this.checkDao = (ChecklistDao_DB) daofactory.getChecklistDao();
		this.itemDao = (ItemDao_DB) daofactory.getItemDao();
		this.checkItemDao = (Checklist_itemDao_DB) daofactory.getChecklist_itemDao();
	}

	/*
	 * Creates a PDF document with all items of the chosen checklist
	 * and saves the PDF in the chosen file path
	 */
	public void createPDF() {
		ChecklistVo checklistVo = new ChecklistVo(checklist, daofactory.getCurrent_user());
		checklistVo.setChecklistID(checkDao.getChecklistID(checklistVo));

		allItems = checkItemDao.getItemsC(checklistVo.getChecklistID());

		try {
			PDDocument document = new PDDocument();

			PDPage firstPage = new PDPage();
			document.addPage(firstPage);
			PDAcroForm acroForm = new PDAcroForm(document);
			document.getDocumentCatalog().setAcroForm(acroForm);

			PDPageContentStream content = new PDPageContentStream(document, firstPage );
			content.beginText();
			content.setFont(PDType1Font.HELVETICA_BOLD, 18);
			content.newLineAtOffset(50, 740);

			content.showText(checklist);
			content.endText();
			
			int j = 700;
			for(int i = 0; i < allItems.size(); i++) {

				ItemVo item = new ItemVo(allItems.get(i));
				item.setItemID(itemDao.getItemID(item));
				Checklist_itemVo checkItem = new Checklist_itemVo(checklistVo.getChecklistID(), item.getItemID());
				
				int amount = checkItemDao.getAmount(checkItem);
				String itemName = allItems.get(i);

				/*create new page if page is full*/
				if (j<75) {
					content.close();
					firstPage = new PDPage();
					document.addPage(firstPage);
					j = 725;
					content = new PDPageContentStream(document, firstPage );
				}
				
				content.addRect(50, j-1, 10, 10);
				content.stroke();
				
				content.beginText();
				content.setFont(PDType1Font.HELVETICA, 12);
				content.newLine();
				content.newLineAtOffset(75, j);
				content.showText(itemName + " x" + amount);
				j-=25;
				content.endText();
			}
			content.close();
			document.save(path);
			document.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Creates a XML document with all items of the chosen checklist
	 * and saves the XML in the chosen file path
	 */
	public void createXML() {
		
		ChecklistVo checklistVo = new ChecklistVo(checklist, daofactory.getCurrent_user());
		checklistVo.setChecklistID(checkDao.getChecklistID(checklistVo));
		allItems = checkItemDao.getItemsC(checklistVo.getChecklistID());
		Element root = new Element("checklist");
		Document doc = new Document();
		
		for (int i = 0; i < allItems.size(); i++) {
			Element child = new Element("item");
			ItemVo item = new ItemVo(allItems.get(i));
			item.setItemID(itemDao.getItemID(item));
			
			Checklist_itemVo checkItem = new Checklist_itemVo(checklistVo.getChecklistID(), item.getItemID());
			int amount = checkItemDao.getAmount(checkItem);
			item.setItemID(itemDao.getItemID(item));
			
			child.addContent(new Element("category").addContent(itemDao.getCategoryID(item)));
			child.addContent(new Element("name").addContent(allItems.get(i)));
			child.addContent(new Element("amount").addContent(amount+""));
			root.addContent(child);	
		}
		
		doc.setRootElement(root);
		XMLOutputter outter = new XMLOutputter();
		outter.setFormat(Format.getPrettyFormat());
		
		try {
			outter.output(doc, new FileWriter(new File(path)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
